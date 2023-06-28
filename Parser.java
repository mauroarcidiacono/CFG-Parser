import computation.contextfreegrammar.*;
import computation.parser.*;
import computation.parsetree.*;
import computation.derivation.*;
import java.util.ArrayList;
import java.util.List;

public class Parser implements IParser {
  public boolean isInLanguage(ContextFreeGrammar cfg, Word w){
    
    List<Derivation> derivations = getDerivations(cfg, w);

    for (Derivation d : derivations) {
      if (d.getLatestWord().equals(w)) {
        return true;
      }
    }
    return false;
  }

  public List<Derivation> getDerivations(ContextFreeGrammar cfg, Word w) {
    
    // derivations1 stores the derivation of words in each step.
    // ders1 stores the derivation objects for each word.
    List<Word> derivations1 = new ArrayList<Word>();
    List<Word> derivations2 = new ArrayList<Word>();
    List<Derivation> ders1 = new ArrayList<Derivation>();
    List<Derivation> ders2 = new ArrayList<Derivation>();

    // Add the start variable to the derivations list and add the initial Derivation object to ders1.
    derivations1.add(new Word(cfg.getStartVariable()));
    Derivation initial = new Derivation(new Word(cfg.getStartVariable()));
    ders1.add(initial);
    ders2.add(initial);

    // Calculate the length of the String to analyse.
    int n = w.toString().length();

    // Loop 2*n-1 steps.
    for (int i = 0; i < 2 * n - 1; i++) {

      // Loop to consider each word of the list.
      for (int j = 0; j < derivations1.size(); j++) {

        // Dive into each variable of the element and compare with the left side of each rule.
        for (Rule rule : cfg.getRules()) {
          
          int count_repeat = derivations1.get(j).count(rule.getVariable());
          if (count_repeat == 0) {
            continue;
          }

          for (int k = 0; k < count_repeat; k++) {

            int indx = derivations1.get(j).indexOfNth(rule.getVariable(), k);
            derivations2.add(derivations1.get(j).replace(indx, rule.getExpansion()));

            for (Derivation individualDer : ders1) {
              if (individualDer.getLatestWord().equals(derivations1.get(j))) {
                Derivation newDerivation = new Derivation(individualDer);
                newDerivation.addStep(derivations1.get(j).replace(indx, rule.getExpansion()), rule, indx);
                ders2.add(newDerivation);
              }
            }
          }
        }
      }

      // Save the step results in derivations 1 and empty derivations 2 to iterate in the next step if required.
      derivations1.clear();
      derivations1.addAll(derivations2);
      derivations2.clear();
      ders1.clear();
      ders1.addAll(ders2);
      ders2.clear();
    }
    return ders1;
  }

  public ParseTreeNode generateParseTree(ContextFreeGrammar cfg, Word w) {

    List<Derivation> derivations = getDerivations(cfg, w);
    // ptn stores the nodes and it is updated from leaf nodes to the start variable.
    List<ParseTreeNode> ptn = new ArrayList<ParseTreeNode>();

    // Add the leaf nodes to the ParseTreeNode list. 
    for (Symbol s : w) {
      ptn.add(new ParseTreeNode(s));
    }
    
    for (Derivation d : derivations) {
      if (d.getLatestWord().equals(w)) {

        for (Step s : d) {
          int index = s.getIndex();
          Rule r = s.getRule();
          // If index is -1, break since the loop reached the step of the start variable.
          if (index == -1) {
            break;
          }
          
          Word e = r.getExpansion();
          Symbol v = s.getRule().getVariable();

          // Conditionals for the cases where there is one or two parent nodes.
          if (e.toString().length() == 1) {
            ParseTreeNode node = new ParseTreeNode(v, ptn.get(index));
            ptn.remove(index);
            ptn.add(index, node);
          } else {
            ParseTreeNode node = new ParseTreeNode(v, ptn.get(index), ptn.get(index + 1));
            ptn.remove(index);
            ptn.remove(index);
            ptn.add(index, node);
          }
        }
      }
      break;
    }
    return ptn.get(0);
  }
}