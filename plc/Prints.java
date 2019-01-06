import java.io.*;

public class Prints {

    private static PrintStream out=PLC.out;

    public static final int MAS = 1;
    public static final int MENOS = 2;
    public static final int POR = 3;
    public static final int DIV = 4;
    public static final int MODULO = 5;
    public static final int MENOSUNARIO = 6;

    public static final int IGUAL = 7;
	public static final int MAYOR = 8;
	public static final int MENOR = 9;
	public static final int MAYORIG = 10;
	public static final int MENORIG = 11;
	public static final int DISTINTO = 12;

    public static void label(String l) {
        out.println(l + ":");
    }

    public static void _goto(String l) {
        out.println("\tgoto " + l + ";");
    } 
    
    public static void _print(String e) {
        out.println("\tprint " + e + ";");
    }

    public static void _typedef(String type, String ident) {
        out.println("\ttypedef " + type + " " + ident + ";");
    }

    public static void asig(String ident, String exp) {
        out.println("\t" + ident + " = " + exp + ";");
    }

    public static void error() {
        out.println("\terror;");
    }
    
    public static void halt() {
        out.println("\thalt;");
    }

    public static String operate(String e1, int oper, String e2) {
        String temp = GenTag.genTemp();
        switch (oper) {
          case MAS:
            out.println("\t" + temp + " = " + e1 + " + " + e2 + ";");
            break;
          case MENOS:
            out.println("\t" + temp + " = " + e1 + " - " + e2 + ";");
            break;
          case POR:
            out.println("\t" + temp + " = " + e1 + " * " + e2 + ";");
            break;
          case DIV:
            out.println("\t" + temp + " = " + e1 + " / " + e2 + ";");
            break;
          case MODULO:
            out.println("\t" + temp + " = " + e1 + " % " + e2 + ";");
            break;
          case MENOSUNARIO:
            out.println("\t" + temp + " = -" + e2 + ";");
            break;
          default: 
            out.println("Error: code generation failed with arguments: \texp1: " + e1 + "\texp2: " + "\toperation number: " + oper);
            break;
        }

        return temp;
    }

    public static DoubleTag conditional (int oper, String e1, String e2) {
        DoubleTag tag = new DoubleTag();
    
        switch (oper) {
          case IGUAL:
            out.println("\tif (" + e1 + " == " + e2 + ") goto " + tag.getT() + ";");
            out.println("\tgoto "+ tag.getF()+";");
            break;
          case DISTINTO:
            out.println("\tif (" + e1 + " == " + e2 + ") goto " + tag.getF() + ";");
            out.println("\tgoto "+ tag.getT()+";");
            break;
          case MENOR:
            out.println("\tif (" + e1 + " < " + e2 + ") goto " + tag.getT() + ";");
            out.println("\tgoto "+ tag.getF()+";");
            break;
          case MAYOR:
            out.println("\tif (" + e2 + " < " + e1 + ") goto " + tag.getT() + ";");
            out.println("\tgoto "+ tag.getF()+";");
            break;
          case MENORIG:
            out.println("\tif (" + e2 + " < " + e1 + ") goto " + tag.getF() + ";" );
            out.println("\tgoto "+ tag.getT()+";");
            break;
          case MAYORIG:
            out.println("\tif (" + e1 + " < " + e2 + ") goto " + tag.getF() + ";");
            out.println("\tgoto "+ tag.getT()+";");
            break;
          default:
            out.println("Error: code generation failed with arguments: \texp1: " + e1 + "\texp2: " + "\tconditional number: " + oper);
            break;
        }
    
        return tag;
    }
}