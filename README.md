# Java Parser

This repository contains a parser of a context-free grammar G written in Java. 

Let G=(V,Σ,R,E), where E is the start symbol, and the variables V, terminals Σ, and rules R, are as follows:

V={T,F,E,C}

Σ={+,\*,-,1,0,x}

R={
           E→E+T,
           E→T,
           T→T*F,
           T→F,
           F→C,
           F→-C,
           C→1,
           C→0,
           C→x
}

The grammar was transformed into Chomsky Normal Form to take advantage of the property of the form in which 2n-1 steps are needed to obtain a derivation where n is the word length. The parser uses a brute force approach which causes performance overhead when the word to parse is larger than four letters. After determining if the word belongs to the language with the isInLanguage() method that is in the Parser class that implements the IParser interface, a parse tree is generated with the generateParseTree() method. 
