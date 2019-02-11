import java_cup.runtime.*;
%%
%cup
LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]

// Comments
Comment =  {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}

TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment     = "//" {InputCharacter}* {LineTerminator}?
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent       = ( [^*] | \*+ [^/*] )*

%%

"int"               {return new Symbol(sym.INT);}
"float"             {return new Symbol(sym.FLOAT);}
"(int)"             {return new Symbol(sym.CAST_INT);}
"(float)"           {return new Symbol(sym.CAST_FLOAT);}
[\ \n\t]            {}
":"                 {return new Symbol(sym.DP, new Tuple(GenTag.genTemp(), ""));}
"?"                 {return new Symbol(sym.INTERR, GenTag.genTag());}
"?:"                {return new Symbol(sym.ELVIS, new Tuple(GenTag.genTemp(), ""));}
";"                 {return new Symbol(sym.PYC);}
","                 {return new Symbol(sym.COMA);}
"&"					{return new Symbol(sym.AMP);}

"="		              {return new Symbol(sym.ASIG);}
"=="		            {return new Symbol(sym.IGUAL);}
"!="		            {return new Symbol(sym.DISTINTO);}
">"		              {return new Symbol(sym.MAYOR);}
"<"		              {return new Symbol(sym.MENOR);}
">=" 	              {return new Symbol(sym.MAYORIG);}
"<="		            {return new Symbol(sym.MENORIG);}

"if"		            {return new Symbol(sym.IF, GenTag.genTag());}
"elseif"		        {return new Symbol(sym.ELSEIF, GenTag.genTag());}
"else"		          {return new Symbol(sym.ELSE);}
"for"		            {return new Symbol(sym.FOR, new DoubleTag());}
"to"                {return new Symbol(sym.TO, new DoubleTag());}
"downto"            {return new Symbol(sym.DOWNTO, new DoubleTag());}
"step"              {return new Symbol(sym.STEP);}
"in"		            {return new Symbol(sym.IN);}
"do"		            {return new Symbol(sym.DO, GenTag.genTag());}
"while"		          {return new Symbol(sym.WHILE, GenTag.genTag());}
"switch"		        {return new Symbol(sym.SWITCH, new DoubleTag("empty"));}
"case"  	          {return new Symbol(sym.CASE, GenTag.genTag());}
"break"		          {return new Symbol(sym.BREAK);}
"default"		        {return new Symbol(sym.DEFAULT);}

"print"             {return new Symbol(sym.PRINT);}

"+"		              {return new Symbol(sym.MAS);}
"-"		              {return new Symbol(sym.MENOS);}
"++"		            {return new Symbol(sym.MASMAS);}
"--"		            {return new Symbol(sym.MENOSMENOS);}
"*"		              {return new Symbol(sym.POR);}
"/"		              {return new Symbol(sym.DIV);}
"%"                 {return new Symbol(sym.MODULO);}
"+="                {return new Symbol(sym.MASEQ);}
"-="                {return new Symbol(sym.MENOSEQ);}
"*="                {return new Symbol(sym.POREQ);}
"/="                {return new Symbol(sym.DIVEQ);}
"%="                {return new Symbol(sym.MODEQ);}

"("                 {return new Symbol(sym.AP);}
")"                 {return new Symbol(sym.CP);}
"{"                 {return new Symbol(sym.ALL);}
"}"                 {return new Symbol(sym.CLL);}
"["                 {return new Symbol(sym.AC);}
"]"                 {return new Symbol(sym.CC);}

"||"		            {return new Symbol(sym.OR);}
"&&"		            {return new Symbol(sym.AND);}
"!"		              {return new Symbol(sym.NOT);}

{Comment}           {}

0|[1-9][0-9]*       {return new Symbol(sym.NUM, yytext());}
(0|[1-9][0-9]*)\.[0-9]+([eE][+-]?(0|[1-9][0-9]*))? {return new Symbol(sym.FLOATNUM, yytext());}
[_a-zA-Z][_a-zA-Z0-9]*         {return new Symbol(sym.IDENT, yytext());}

[^]		              {throw new Error("Error: Invalid argument <"+yytext()+">");}