/*
 * Compile all your grammars using
 *       java -jar ../../../lib/antlr.jar *.g4
 * then Refresh in Eclipse.
 */
grammar Abc;
import Configuration;

root: headers body EOF;

headers: indexHeader titleHeader otherHeader* keyHeader;

indexHeader: 'X:' NUMBER NEWLINE;
titleHeader: 'T:' name NEWLINE;
keyHeader: 'K:' key sharp? SMALL_M? NEWLINE;

otherHeader: (meterHeader | lengthHeader | tempoHeader | composerHeader | voiceHeader);
meterHeader: 'M:' (fraction | BIG_C | BIG_C BAR) NEWLINE;
lengthHeader: 'L:' fraction NEWLINE;
tempoHeader: 'Q:' fraction EQUAL NUMBER NEWLINE;
composerHeader: 'C:' name NEWLINE;
voiceHeader: 'V:' name NEWLINE;

body: (section | BAR | NEWLINE | voiceHeader | comment | repeat_start | repeat_end
	| end_bar
)+;

sharp: '#' | SMALL_B;
comment: '%' name? NEWLINE;
repeat_start: BAR COLON;
repeat_end: COLON BAR;
ending: LEFT_C NUMBER;
section: (note | rest | chord | tuplet | ending)+;
chord: LEFT_C note+ RIGHT_C;
tuplet: '(' NUMBER note+;
note: accidentals? (key COMMA* | lower_key SINGLE_Q*) note_length?;
rest: SMALL_Z note_length?;
note_length: fraction | NUMBER | SLASH NUMBER | SLASH | NUMBER SLASH;
accidentals: FLAT+ | UPPER+ | EQUAL;
end_bar: BAR RIGHT_C;

fraction: NUMBER SLASH NUMBER;

lower_key: LOWER_KEY | SMALL_B;
key: KEY | BIG_C;
name: (ALPHA | SPACE | NUMBER | DOT | key | SINGLE_Q | COMMA | lower_key
	| SMALL_Z | SMALL_M
)+;

SMALL_M: 'm';
BAR: '|';
LEFT_C: '[';
RIGHT_C: ']';
COLON: ':';
SMALL_Z: 'z';
SMALL_B: 'b';
BIG_C: 'C';
FLAT: '_';
UPPER: '^';
KEY: ('D' | 'E' | 'F' | 'G' | 'A' | 'B');
LOWER_KEY: ('c' | 'd' | 'e' | 'f' | 'g' | 'a');
EQUAL: '=';
COMMA: ',';
SINGLE_Q: '\'';
NUMBER: [0-9]+;
SPACE: ' ' -> skip;
DOT: '.';
SLASH: '/';
NEWLINE: '\n' | '\r\n';
ALPHA: ([a-zA-Z]);
