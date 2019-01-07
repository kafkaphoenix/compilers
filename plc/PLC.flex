import java_cup.runtime.*;
%%
%cup

%{
	private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }
%}

%%

"typedef"           {return new Symbol(sym.TYPEDEF);}
"int"               {return new Symbol(sym.INT);}
"char"              {return new Symbol(sym.CHAR);}
"float"             {return new Symbol(sym.FLOAT);}
"true"              {return new Symbol(sym.TRUE);}
"false"             {return new Symbol(sym.FALSE);}
[\ \n\t]            {}
"?"                 {return new Symbol(sym.INTERR, GenTag.genTag());}
":"                 {return new Symbol(sym.DP, GenTag.genTag());}
";"                 {return new Symbol(sym.PYC);}
","                 {return new Symbol(sym.COMA);}

"="		            {return new Symbol(sym.ASIG);}
"=="		        {return new Symbol(sym.IGUAL);}
"!="		        {return new Symbol(sym.DISTINTO);}
">"		            {return new Symbol(sym.MAYOR);}
"<"		            {return new Symbol(sym.MENOR);}
">=" 	            {return new Symbol(sym.MAYORIG);}
"<="		        {return new Symbol(sym.MENORIG);}

"if"		        {return new Symbol(sym.IF, GenTag.genTag());}
"elseif"		    {return new Symbol(sym.ELSEIF, GenTag.genTag());}
"else"		        {return new Symbol(sym.ELSE, GenTag.genTag());}
"for"		        {return new Symbol(sym.FOR, new DoubleTag());}
"do"		        {return new Symbol(sym.DO, GenTag.genTag());}
"while"		        {return new Symbol(sym.WHILE, GenTag.genTag());}
"switch"		    {return new Symbol(sym.SWITCH);}
"case"  	        {return new Symbol(sym.CASE);}
"break"		        {return new Symbol(sym.BREAK);}
"default"		    {return new Symbol(sym.DEFAULT);}

"print"             {return new Symbol(sym.PRINT);}

"+"		            {return new Symbol(sym.MAS);}
"-"		            {return new Symbol(sym.MENOS);}
"++"		        {return new Symbol(sym.MASMAS);}
"--"		        {return new Symbol(sym.MENOSMENOS);}
"*"		            {return new Symbol(sym.POR);}
"/"		            {return new Symbol(sym.DIV);}
"%"                 {return new Symbol(sym.MODULO);}
"+="                {return new Symbol(sym.MASEQ);}
"-="                {return new Symbol(sym.MENOSEQ);}
"*="                {return new Symbol(sym.POREQ);}
"/="                {return new Symbol(sym.DIVEQ);}

"("                 {return new Symbol(sym.AP);}
")"                 {return new Symbol(sym.CP);}
"{"                 {return new Symbol(sym.ALL);}
"}"                 {return new Symbol(sym.CLL);}

"||"		        {return new Symbol(sym.OR);}
"&&"		        {return new Symbol(sym.AND);}
"!"		            {return new Symbol(sym.NOT);}

[a-zA-Z_]+          {return new Symbol(sym.IDENT, yytext());}
0|[1-9][0-9]*       {return new Symbol(sym.NUM, yytext());}

[^]		{throw new Error("Error: Invalid argument <"+yytext()+">");}