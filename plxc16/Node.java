import java.util.ArrayList;
import java.util.List;

public class Node {
    private String name;
    private int scope;
    private String type;
    private int size;
    private String whoIam; //var, array, pointer, num
    private final static int NUM = -1;

    // Arrays
    private List<Node> array;
    private Node next;

    // Multi Arrays
    private int [] multiDim;

    // Pointers
    private int pointerLevel;

    // Variable's Constructor
    public Node (String name, int scope, String type) {
        this.name = name;
        this.scope = scope;
        this.type = type;

        this.whoIam = "var";

        next = null;
        pointerLevel = 0;
        array = null;
    }

    // Numbers' Constructor
    public Node (int value, int scope, String type) {
        this.name = value+"";
        this.scope = scope;
        this.type = type;

        this.whoIam = "num";

        next = null;
        pointerLevel = 0;
        array = null;
    }

    // Array and Pointer's Constructor
    public Node (String name, int scope, String type, String whoIam, int sizeOrPointerLevel) {
        this.name = name;
        this.scope = scope;
        this.type = type;

        this.whoIam = whoIam;

        if (this.whoIam.equals("pointer")) {
            pointerLevel = sizeOrPointerLevel;
            array = null;
        } else if (this.whoIam.equals("array")) {
            size = sizeOrPointerLevel;
            array = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                array.add(new Node(NUM, scope, type));
            }
        }

    }

    public int[] getMultiDim() {
        return multiDim;
    }

    public void setMultiDim(int[] l) {
        multiDim = l;
    }

    public void addNodeToArray(Node n, int pos) {
        array.add(pos, n);
    }

    public void addNodeToArray(Node n) {
        array.add(n);
    }

    public void setNodeToArray(Node n, int pos) {
        array.set(pos, n);
    }

    public String getName() {
        return name;
    }

    public int getScope() {
        return scope;
    }

    public String getType() {
        return type;
    }

    public int getTypeN() {
        int typeN = -1;
        if (type.equals("int")){
            typeN = Prints.INT;
        } else if (type.equals("float")) {
            typeN = Prints.FLOAT;
        }
        return typeN;
    }

    public int getSize() {
        return size;
    }

    public String getWhoIam() {
        return whoIam;
    }

    public Boolean isPointer() {
        return whoIam.equals("pointer");
    }

    public Boolean isArray() {
        return whoIam.equals("array");
    }

    public List<Node> getArray() {
        return array;
    }

    public Node getNext() {
        return next;
    }

    public int getPointerLevel() {
        return pointerLevel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScope(int scope) {
        this.scope = scope;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setWhoIam(String whoIam) {
        this.whoIam = whoIam;
    }

    public void setArray(List<Node> array) {
        this.array = array;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public void setPointerLevel(int pointerLevel) {
        this.pointerLevel = pointerLevel;
    }

    @Override
    public String toString() {
        String str = "";
        if (whoIam.equals("var")) {
            str = "\t" + name + "  |  " + type + "  |  " + scope + "  |  " + whoIam + "  |  " + "  |  ";
        } else if (whoIam.equals("pointer")) {
            str = "\t" + name + "  |  " + type + "  |  " + scope + "  |  " + whoIam + "  |  " + pointerLevel + "  |  ";
        } else if (whoIam.equals("array")) {
            StringBuilder sb = new StringBuilder();
            sb.append("\t" + name + "  |  " + type + "  |  " + scope + "  |  " + whoIam + "  |  " + "  |  " + size);
            for (Node n : array) {
                if (n.getWhoIam().equals("var")) {
                    sb.append("\t" + name + "  |  " + type + "  |  " + scope + "  |  " + whoIam + "  |  " + "  |  ");
                } else if (n.getWhoIam().equals("pointer")) {
                    sb.append("\t" + name + "  |  " + type + "  |  " + scope + "  |  " + whoIam + "  |  " + pointerLevel + "  |  ");
                } 
            }
            str = sb.toString();
        }

        return str;
    }
}