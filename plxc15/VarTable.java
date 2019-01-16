import java.util.*;
import java.io.*;

public class VarTable {

    private static int scope = 0;
    private static String type = "";
    private static PrintStream out=PLXC.out;

    // identifier, scope, type, size (arrays)
    private static List<Tuple<String, Tuple<Integer, Tuple<String, Integer>>>> variables = new ArrayList<Tuple<String, Tuple<Integer, Tuple<String, Integer>>>>();

    // returns the variables with the highest scope available
    public static String getVar(String id) {
        int max = -1;
        for (Tuple<String, Tuple<Integer, Tuple<String, Integer>>> t : variables) {
            if (getName(t).equals(id)) {
                max = getScope(t) > max ? getScope(t) : max;
            }
        }

        if (max == -1) {
            out.println("\t# variable no declarada");
            Prints.error();
            Prints.halt();
        }

        return id + "_" + max;
    }

    public static void declareVar(String name, int scope, String type, int size) {

        for (Tuple<String, Tuple<Integer, Tuple<String, Integer>>> t : variables) {
            if (getName(t).equals(name) && getScope(t) == scope) {
                out.println("\t# variable ya declarada");
                Prints.error();
                Prints.halt();
            }
        }
        
        variables.add(new Tuple(name, new Tuple(scope, new Tuple(type, size))));
    }

    public static void clearScope() {

        Iterator <Tuple<String, Tuple<Integer, Tuple<String, Integer>>>> iter = variables.iterator();
        //currentScope();
        //showTable();
        while (iter.hasNext()) {
            Tuple<String, Tuple<Integer, Tuple<String, Integer>>> t = iter.next();

            if (getScope(t) == scope) {
                iter.remove();
            }
        }
        //showTable();
    }

    public static void clear()
	{
		variables.clear();
	}   

    public static String getName(Tuple<String, Tuple<Integer, Tuple<String, Integer>>> t) {
        return t.getA();
    }
    
    public static int getScope(Tuple<String, Tuple<Integer, Tuple<String, Integer>>> t) {
        return t.getB().getA();
    }
    
    public static String getType(Tuple<String, Tuple<Integer, Tuple<String, Integer>>> t) {
        return t.getB().getB().getA();
    }
    
    public static int getSize(Tuple<String, Tuple<Integer, Tuple<String, Integer>>> t) {
        return t.getB().getB().getB();
    }

    public static void climb() {
        ++scope;
    }
    
    public static void fall() {
        scope = scope == 0 ? scope : (scope - 1);
    }
    
    public static int currentScope() {
        return scope;
    }

    public static void setCurrentType(String current) {
        type = current;
    }

    public static String currentType() {
        return type;
    }

    public static void showTable() {
        out.println("# Var");
        out.println(variables.toString());
        out.println("# End Var");
    }
}