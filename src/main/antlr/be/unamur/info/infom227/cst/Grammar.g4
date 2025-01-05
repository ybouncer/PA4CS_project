grammar Grammar;

/*
 * Lexer Rules
 */

// Keywords

INT: 'int';

BOOL: 'bool';

PRINT: 'print';

IF: 'if';

ELSE: 'else';

WHILE: 'while';


// Separators / Punctuators

LBRACE: '{';

RBRACE: '}';

LPAR: '(';

RPAR: ')';

SEMICOLON: ';';


// Operators

ASSIGN: '=';

NOT: 'not';

AND: 'and';

OR: 'or';

ADD: '+';

SUBTRACT: '-';

MULTIPLY: '*';

DIVIDE: '/';

GREATER: '>';

GREATER_EQUAL: '>=';

LESS: '<';

LESS_EQUAL: '<=';

EQUAL: '==';

DIFFERENT: '!=';


// Literals

TRUE: 'True';

FALSE: 'False';

fragment DIGIT: [0-9];

NUMBER: DIGIT+;


// Identifiers

fragment LETTER: [a-zA-Z];

IDENTIFIER: LETTER (LETTER | DIGIT)*;


// Comments -> ignored

COMMENT: ('/*'(.*?)'*/'|'//'.*?'\r'?('\n'|EOF)) -> skip;


// Whitespaces -> ignored

NEWLINE: '\r'?'\n'  -> skip ;

WS: [ \t]+ -> skip ;


/*
 * Parser Rules
 */


// Programs

program: scope;

scope: declareStatement* statements;

statements: statement*;


// Statements

statement: assignStatement | printStatement | ifStatement | whileStatement;

// EXTENDED to handle DYNAMIC array declaration e.g int tab[2];
declareStatement: type IDENTIFIER (LBRACE NUMBER RBRACE)? SEMICOLON
;

// EXTENDED to handle DYNAMIC array assign e.g tab[1] = 2;
assignStatement: IDENTIFIER (LBRACE expression RBRACE)? ASSIGN (expression | LBRACE scope expression RBRACE) SEMICOLON;

printStatement: PRINT expression SEMICOLON;

ifStatement: IF LPAR expression RPAR LBRACE if=statements RBRACE ELSE LBRACE else=statements RBRACE;

whileStatement: WHILE LPAR expression RPAR LBRACE statements RBRACE;


// Types EXTENDED

type: INT | BOOL
          | INT LBRACE RBRACE | BOOL LBRACE RBRACE; // Array types e.g int[]

// Expressions

expression: disjunction;

disjunction: conjunction (OR conjunction)+ | passthrough=conjunction;

conjunction: inversion (AND inversion)+ | passthrough=inversion;

inversion: NOT inversion | comparison;

comparison: left=comparison (LESS | GREATER | EQUAL | DIFFERENT | GREATER_EQUAL | LESS_EQUAL) right=sum | passthrough=sum;

sum: left=sum (ADD | SUBTRACT) right=product | passthrough=product;

product: left=product (MULTIPLY | DIVIDE) right=factor | passthrough=factor;

factor: (ADD | SUBTRACT) atom | atom;

// EXTENDED to handle array access e.g tab[1] or tab[1] = 2;
atom: IDENTIFIER (LBRACE expression RBRACE)? | NUMBER | TRUE | FALSE | LPAR expression RPAR;