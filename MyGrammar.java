import computation.contextfreegrammar.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyGrammar {
	public static ContextFreeGrammar makeGrammar() {
		// You can write your code here to make the context-free grammar from the assignment

		// Variables V = {T,F,E,C,E1,A,B,D,G,H,P,S,M}
		Variable T = new Variable('T');
		Variable F = new Variable('F');
		Variable E = new Variable('E');
		Variable C = new Variable('C');
		Variable E1 = new Variable("E1");
		Variable A = new Variable('A');
		Variable B = new Variable('B');
		Variable D = new Variable('D');
		Variable G = new Variable('G');
		Variable H = new Variable('H');
		Variable P = new Variable('P');
		Variable S = new Variable('S');
		Variable M = new Variable('M');

		// Terminals Sigma = {+,*,-,1,0,x}
		Terminal plus = new Terminal('+');
		Terminal times = new Terminal('*');
		Terminal minus = new Terminal('-');
		Terminal one = new Terminal('1');
		Terminal zero = new Terminal('0');
		Terminal x = new Terminal('x');

		// Rules
		Rule r0 = new Rule(E1, new Word(E,A));
		Rule r1 = new Rule(A, new Word(P,T));
		Rule r2 = new Rule(E1, new Word(T,B));
		Rule r3 = new Rule(B, new Word(S,F));
		Rule r4 = new Rule(E1, new Word(M,C));
		Rule r5 = new Rule(E1, new Word(one));
		Rule r6 = new Rule(E1, new Word(zero));
		Rule r7 = new Rule(E1, new Word(x));
		Rule r8 = new Rule(E, new Word(E,D));
		Rule r9 = new Rule(D, new Word(P,T));
		Rule r10 = new Rule(E, new Word(T,G));
		Rule r11 = new Rule(G, new Word(S,F));
		Rule r12 = new Rule(E, new Word(M,C));
		Rule r13 = new Rule(E, new Word(one));
		Rule r14 = new Rule(E, new Word(zero));
		Rule r15 = new Rule(E, new Word(x));
		Rule r16 = new Rule(T, new Word(T,H));
		Rule r17 = new Rule(H, new Word(S,F));
		Rule r18 = new Rule(T, new Word(M,C));
		Rule r19 = new Rule(T, new Word(one));
		Rule r20 = new Rule(T, new Word(zero));
		Rule r21 = new Rule(T, new Word(x));
		Rule r22 = new Rule(F, new Word(one));
		Rule r23 = new Rule(F, new Word(zero));
		Rule r24 = new Rule(F, new Word(x));
		Rule r25 = new Rule(F, new Word(M,C));
		Rule r26 = new Rule(C, new Word(one));
		Rule r27 = new Rule(C, new Word(zero));
		Rule r28 = new Rule(C, new Word(x));
		Rule r29 = new Rule(P, new Word(plus));
		Rule r30 = new Rule(S, new Word(times));
		Rule r31 = new Rule(M, new Word(minus));

		// Create a rules ArrayList to store the rules.
		List<Rule> rules = new ArrayList<Rule>();
		rules.addAll(Arrays.asList(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, 
		r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27,
		r28, r29, r30, r31));

		// Context-free grammar
		ContextFreeGrammar cfg = new ContextFreeGrammar(rules);

		return cfg;
	}
}
