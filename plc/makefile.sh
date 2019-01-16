#/bin/bash

	java -jar ../java-cup/java-cup-11b.jar PLC.cup
	java -jar ../jflex/jflex-1.6.1.jar PLC.flex
	javac -classpath ../java-cup/java-cup-11b-runtime.jar *.java
	java -classpath .:../java-cup/java-cup-11b-runtime.jar PLC ../tests/plc/a1.pl prog.ctd; ./ctd prog.ctd 
	java -classpath .:../java-cup/java-cup-11b-runtime.jar PLC ../tests/plc/a0.pl prog.ctd; ./ctd prog.ctd 
	java -classpath .:../java-cup/java-cup-11b-runtime.jar PLC ../tests/plc/a2.pl prog.ctd; ./ctd prog.ctd 
	java -classpath .:../java-cup/java-cup-11b-runtime.jar PLC ../tests/plc/b1.pl prog.ctd; ./ctd prog.ctd 
	java -classpath .:../java-cup/java-cup-11b-runtime.jar PLC ../tests/plc/b2.pl prog.ctd; ./ctd prog.ctd 
	java -classpath .:../java-cup/java-cup-11b-runtime.jar PLC ../tests/plc/c1.pl prog.ctd; ./ctd prog.ctd 
	java -classpath .:../java-cup/java-cup-11b-runtime.jar PLC ../tests/plc/c2.pl prog.ctd; ./ctd prog.ctd 
	java -classpath .:../java-cup/java-cup-11b-runtime.jar PLC ../tests/plc/d1.pl prog.ctd; ./ctd prog.ctd 
	java -classpath .:../java-cup/java-cup-11b-runtime.jar PLC ../tests/plc/d2.pl prog.ctd; ./ctd prog.ctd 
	java -classpath .:../java-cup/java-cup-11b-runtime.jar PLC ../tests/plc/e1.pl prog.ctd; ./ctd prog.ctd 
	java -classpath .:../java-cup/java-cup-11b-runtime.jar PLC ../tests/plc/e2.pl prog.ctd; ./ctd prog.ctd 
	java -classpath .:../java-cup/java-cup-11b-runtime.jar PLC ../tests/plc/g1.pl prog.ctd; ./ctd prog.ctd 
	java -classpath .:../java-cup/java-cup-11b-runtime.jar PLC ../tests/plc/g2.pl prog.ctd; ./ctd prog.ctd 
	java -classpath .:../java-cup/java-cup-11b-runtime.jar PLC ../tests/plc/h1.pl prog.ctd; ./ctd prog.ctd 
	java -classpath .:../java-cup/java-cup-11b-runtime.jar PLC ../tests/plc/h2.pl prog.ctd; ./ctd prog.ctd 
	rm *.class sym.java parser.java Yylex.java
