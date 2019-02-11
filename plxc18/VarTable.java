import java.util.*;
import java.io.*;

public class VarTable {

    private static int scope = 0;
    private static String type = "";
    private static PrintStream out=PLXC.out;

    private static List<Node> variables = new ArrayList<>();

    public static void declareVar(String name) {
        declareVar(name, "var", 0);
    }

    public static void declareVar(String name, String whoIam, int sizeOrPointerLevel) {

        for (Node node : variables) {
            if ((node.getName()).equals(name) && (node.getScope() == scope)) { // you can't have two vars with the same name in the same scope
                out.println("\t# variable ya declarada " + name); 
                Prints.error();
                Prints.halt();
            }
        }
        
        if (whoIam.equals("var")) {
            variables.add(new Node(name, scope, type));
        } else if (whoIam.equals("array") || whoIam.equals("pointer")) {
            variables.add(new Node(name, scope, type, whoIam, sizeOrPointerLevel));
        }

        //showTable();
    }

    public static void addToArray(String name, String type, int pos, String whoIam, int sizeOrPointerLevel, String arrayName) {
              
        Node node = findNode(arrayName);
        type = node.getType();

        if (pos != -1) {
            if (whoIam.equals("var")) {
                node.setNodeToArray(new Node(name, node.getScope(), type), pos);
            } else {
                node.setNodeToArray(new Node(name, node.getScope(), type, whoIam, sizeOrPointerLevel), pos);
            }
        } else {
            if (whoIam.equals("var")) {
                node.addNodeToArray(new Node(name, node.getScope(), type));
            } else {
                node.addNodeToArray(new Node(name, node.getScope(), type, whoIam, sizeOrPointerLevel));
            }
        }
    }

    public static void addToArrayShiftRight(String name, String type, int pos, String whoIam, int sizeOrPointerLevel, String arrayName) {
              
        Node node = findNode(arrayName);
        type = node.getType();

        if (pos != -1) {
            if (whoIam.equals("var")) {
                node.addNodeToArray(new Node(name, node.getScope(), type), pos);
            } else {
                node.addNodeToArray(new Node(name, node.getScope(), type, whoIam, sizeOrPointerLevel), pos);
            }
        } else {
            if (whoIam.equals("var")) {
                node.addNodeToArray(new Node(name, node.getScope(), type));
            } else {
                node.addNodeToArray(new Node(name, node.getScope(), type, whoIam, sizeOrPointerLevel));
            }
        }
    }

    public static Node getElementInArray(String id, Tuple pos) { //Supongo que entra numero
        Node array = findNode(id);
        Node element = (array.getArray()).get(Integer.parseInt(pos.getA()+""));

        return element;
    }

    public static Node findNode(String name) {
        int max = -1;
        if (name.contains("_")) {
            name = name.substring(0, name.indexOf("_"));
        }

        Node node = null;
        for (Node n : variables) {
            if ((n.getName()).equals(name)) { //gives the Node with max Scope
                max = n.getScope() > max ? n.getScope() : max;
                node = n;
            }
        }

        if (max == -1) {
            out.println("\t# variable no declarada " + name);
            //showTable();
            //Prints.showTable();
            Prints.error();
            Prints.halt();
        }

        return node;
    }

    public static Node getNode(String name) { //Gives null if doesn't exist
        int max = -1;
        if (name.contains("_")) {
            name = name.substring(0, name.indexOf("_"));
        }

        Node node = null;
        for (Node n : variables) {
            if ((n.getName()).equals(name)) { //gives the Node with max Scope
                max = n.getScope() > max ? n.getScope() : max;
                node = n;
            }
        }

        return node;
    }


    public static int varType(String var) {
        return (findNode(var)).getTypeN();
    }

    public static String varTypeS(String var) {
        return (findNode(var)).getType();
    }

    public static void clearScope() {

        Iterator <Node> iter = variables.iterator();
        //currentScope();
        //showTable();
        while (iter.hasNext()) {
            Node t = iter.next();

            if (t.getScope() == scope) {
                iter.remove();
            }
        }
        //showTable();
    }

    public static List getTV() {
        return variables;
    }

    public static void clear()
	{
		variables.clear();
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
        out.println("\tID | Type | Scope | Type | Ptr Level | Size");
        for (Node n : variables) {
            out.println(n.toString());
        }
        out.println("# End Var");
    }
}