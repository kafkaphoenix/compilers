import java.io.*;
import java.util.List;
import java.util.regex.Pattern;

public class Prints {

    private static PrintStream out=PLXC.out;
    public static Node[] temps = new Node[100];
    public static int ntemps = 0;

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
  
    public static final int MASEQ = 13;
    public static final int MENOSEQ = 14;
    public static final int POREQ = 15;
    public static final int DIVEQ = 16;
    public static final int MODEQ = 17;

    public static final int VAR = 20;
    public static final int TEMP = 21;
    public static final int INT = 22;
    public static final int FLOAT = 23;

  // *************************************************************************** Temps

    public static String newTemp(String type) {
      String aux = GenTag.genTemp();
      Node node = new Node(aux, VarTable.currentScope(), type);
      temps[GenTag.tag - 1] = node;
      ++ntemps;
      return aux;
    }

    public static String newTempArray(String id) {
      String aux = GenTag.genTemp();
      Node array = VarTable.findNode(id);
      Node node = new Node(aux, array.getScope(), array.getType(), "array", array.getSize());
      temps[GenTag.tag - 1] = node;
      ++ntemps;
      return aux;
    }

    public static int tempType(String temp) {
      Node n = temps[Integer.parseInt(temp.substring(temp.indexOf("t") + 1))];
      return n.getTypeN();
    }

    public static Node getNodeTemp(String temp) {
      Node n = temps[Integer.parseInt(temp.substring(temp.indexOf("t") + 1))];
      return n;
    }

    public static Boolean isTempPointer(String temp) {
      Node n = temps[Integer.parseInt(temp.substring(temp.indexOf("t") + 1))];
      return n.isPointer();
    }

    public static Boolean isTempArray(String temp) {
      Node n = temps[Integer.parseInt(temp.substring(temp.indexOf("t") + 1))];
      return n.isArray();
    }

    public static int varType(String var) {
      Node n = VarTable.findNode(var);
      return n.getTypeN();
    }

    public static void showTable() {
      out.println("# Temps");
      out.println("\tID | Type | Scope | Type | Ptr Level | Size");
      for (int i = 0; i < ntemps; ++i) {
          out.println(temps[i].toString());
      }
      out.println("# End Var");
  }

  // *************************************************************************** messages

    public static void label(String l) {
      out.println(l + ":");
    }

    public static void _goto(String l) {
      out.println("\tgoto " + l + ";");
    } 
    
    public static void _print(String e) {
      out.println("\tprint " + e + ";");
    }

    public static void elvis(String e1, String l) {
      out.println("\tif (" + e1 + " != 0) goto " + l + ";");
    }

    public static void _switch(String var, String case_, String label) {
      out.println("\tif (" + var + " != " + case_ + ") goto " + label + ";");
    }

    public static void asig(Tuple id, Tuple exp) {
      Node n1 = VarTable.getNode(id.getA()+"");
      Node n2 = VarTable.getNode(exp.getA()+"");

      if (n1 == null && n2 == null) {
        out.println("\t" + id.getA() + " = " + exp.getA() + ";");
      } else {
        if (!id.getB().equals(exp.getB())){
          if (id.getB().equals("int") && exp.getB().equals("float")){
            comment("Can't assign float to int");
            error();
            halt();
          } else if (id.getB().equals("float") && exp.getB().equals("int")) {
              if (n1 != null && n2 != null && n1.isArray() && n2.isArray()) {
                comment("Type Error, arrays can't have different types");
                error();
                halt();
              }
              out.println("\t" + id.getA() + " = (float) " + exp.getA() + ";");
          } else if (id.getB().equals("char") && exp.getB().equals("char")) {
            out.println("\t" + id.getA() + " = " + exp.getA() + ";");
            _printc(id.getA()+"");
          } else if (id.getB().equals("char") || exp.getB().equals("char")) {
            comment("Type Error");
            error();
            halt();
          }
        } else {
  
          if (n1 != null && n2 != null && n1.isArray() && n2.isArray()) {
            arrayAsig(n1, n2);
          }
  
          out.println("\t" + id.getA() + " = " + exp.getA() + ";");
        }
      }
      
    }

    public static void comment(String msg) {
      out.println("\t# " + msg);
    }

    public static void nocomment(String msg) {
      out.println("\t" + msg);
    }

    public static Tuple operate(Tuple e1, int oper, Tuple e2) {
       
      String type = "";
      String opr= " ";
      String temp1 = "";
      if (e1 != null) {
        temp1 = e1.getA() + "";
      }
      String temp2 = e2.getA() + "";

      /*if (getType(temp1) == VAR) {
        if ((VarTable.findNode(temp1)).isPointer() && oper != POR) {
          Prints.complexError(1);
        }
      } else if (getType(temp1) == TEMP) {
        if (isTempPointer(temp1) && oper != POR) {
          Prints.complexError(1);
        }
      }
      if (getType(temp2) == VAR) {
        if ((VarTable.findNode(temp2)).isPointer() && oper != POR) {
          Prints.complexError(1);
        }
      } else if (getType(temp2) == TEMP) {
        if (isTempPointer(temp2) && oper != POR) {
          Prints.complexError(1);
        }
      }*/
      
      if (e1.getB().equals("float") && e2.getB().equals("float")) {
        type = "float";
        opr = "r ";
      } else if (e1.getB().equals("int") && e2.getB().equals("int")) {
        type = "int";
      } else if (e1.getB().equals("float") && e2.getB().equals("int")) {
        temp2 = GenTag.genTemp();
        opr = "r ";
        out.println("\t" + temp2 + " = (float) " + e2.getA() + ";");
        type = "float";
      } else if (e1.getB().equals("int") && e2.getB().equals("float")) {
        temp1 = GenTag.genTemp();
        out.println("\t" + temp1 + " = (float) " + e1.getA() + ";");
        type = "float";
        opr = "r ";
      }    
      String temp = GenTag.genTemp();

      switch (oper) {
        case MAS:
          out.println("\t" + temp + " = " + temp1 + " +" + opr + temp2 + ";");
          break;
        case MENOS:
          out.println("\t" + temp + " = " + temp1 + " -" + opr + temp2 + ";");
          break;
        case POR:
          out.println("\t" + temp + " = " + temp1 + " *" + opr + temp2 + ";");
          break;
        case DIV:
          out.println("\t" + temp + " = " + temp1 + " /" + opr + temp2 + ";");
          break;
        case MODULO:
          String t0 = temp;
          String t1 = GenTag.genTemp();
          temp = GenTag.genTemp();
          out.println("\t" + t0 + " = " + temp1 + " /" + opr + temp2 + ";");
          out.println("\t" + t1 + " = " + t0 + " *" + opr + temp2 + ";");
          out.println("\t" + temp + " = " + temp1 + " -" + opr + t1 + ";");
          break;
        case MENOSUNARIO:
          out.println("\t" + temp + " = -" + temp2 + ";");
          break;
        default: 
          out.println("Error: code generation failed with arguments: \texp1: " + temp1 + "\texp2: " + temp2 + "\toperation number: " + oper + opr);
          break;
      }

      return new Tuple(temp, type);
    }

    public static void operateEQ(Tuple id, int oper, Tuple e){
      
      String temp = e.getA() + "";
      String opr= " ";
      if (id.getB().equals("int") && e.getB().equals("float")){
        comment("Can't assign float to int");
        error();
        halt();
      } else if (id.getB().equals("float") && e.getB().equals("int")){
        temp = GenTag.genTemp();
        opr = "r ";
        out.println("\t" + temp + " = (float) " + e.getA() + ";");
      } else if (id.getB().equals("float") && e.getB().equals("float")){
        opr = "r ";
      } else {

      }

      switch(oper) {
        case MASEQ:
          out.println("\t" + id.getA() + " = " + id.getA() + " +" + opr + temp + ";");
          break;
        case MENOSEQ:
          out.println("\t" + id.getA() + " = " + id.getA() + " -" + opr + temp + ";");
          break;
        case POREQ:
          out.println("\t" + id.getA() + " = " + id.getA() + " *" + opr + temp + ";");
          break;
        case DIVEQ:
          out.println("\t" + id.getA() + " = " + id.getA() + " /" + opr + temp + ";");
          break;
        case MODEQ:
          String t0 = GenTag.genTemp();
          String t1 = GenTag.genTemp();
          out.println("\t" + t0 + " = " + id.getA() + " /" + opr + temp + ";");
          out.println("\t" + t1 + " = " + t0 + " *" + opr + temp + ";");
          out.println("\t" + id.getA() + " = " + id.getA() + " -" + opr + t1 + ";");
          break;
        default:
          out.println("Error: code generation failed with arguments: \tid:" + id.getA() + "\texp: " + temp + "\toperation number: " + oper + opr);
          break;
      }
    }

    public static DoubleTag conditional (int oper, Tuple e1, Tuple e2) {
        DoubleTag tag = new DoubleTag();
    
        switch (oper) {
          case IGUAL:
            out.println("\tif (" + e1.getA() + " == " + e2.getA() + ") goto " + tag.getT() + ";");
            out.println("\tgoto "+ tag.getF()+";");
            break;
          case DISTINTO:
            out.println("\tif (" + e1.getA() + " == " + e2.getA() + ") goto " + tag.getF() + ";");
            out.println("\tgoto "+ tag.getT()+";");
            break;
          case MENOR:
            out.println("\tif (" + e1.getA() + " < " + e2.getA() + ") goto " + tag.getT() + ";");
            out.println("\tgoto "+ tag.getF()+";");
            break;
          case MAYOR:
            out.println("\tif (" + e2.getA() + " < " + e1.getA() + ") goto " + tag.getT() + ";");
            out.println("\tgoto "+ tag.getF()+";");
            break;
          case MENORIG:
            out.println("\tif (" + e2.getA() + " < " + e1.getA() + ") goto " + tag.getF() + ";" );
            out.println("\tgoto "+ tag.getT()+";");
            break;
          case MAYORIG:
            out.println("\tif (" + e1.getA() + " < " + e2.getA() + ") goto " + tag.getF() + ";");
            out.println("\tgoto "+ tag.getT()+";");
            break;
          default:
            out.println("Error: code generation failed with arguments: \texp1: " + e1.getA() + "\texp2: " + e2.getA() + "\tconditional number: " + oper);
            break;
        }
    
        return tag;
    }

    // *************************************************************************** Cast

    public static Tuple<String, String> cast(String type, Tuple e){
      
      String t = GenTag.genTemp();
      if (type.equals("int") && !e.getB().equals("int")) {
        out.println("\t" + t + " = (int) " + e.getA() + ";");
      } else if (type.equals("float") && !e.equals("float")) {
        out.println("\t" + t + " = (float) " + e.getA() + ";");
      }

      return new Tuple(t, type);
    }

    // *************************************************************************** Type

    public static boolean isVar(String in){
      Pattern p = Pattern.compile("[_a-zA-Z][_a-zA-Z0-9]*");
      return Pattern.matches(p.pattern(),in);
    }
  
    public static boolean isTemp(String in){
      Pattern p = Pattern.compile("[$]?t[0-9]+");
      return Pattern.matches(p.pattern(),in);
    }
  
    public static boolean isInt(String in){
      Pattern p = Pattern.compile("[1-9][0-9]*|0");
      return Pattern.matches(p.pattern(),in);
    }
  
    public static boolean isFloat(String in){
      Pattern p = Pattern.compile("[-]?[0-9]*\\.[0-9]+[eE]*[+-]?[0-9]*");
      return Pattern.matches(p.pattern(), in);
    }

    public static int getType(String in){
      int type=-1;
      if (isTemp(in)){
        type=21;
      } else if(isVar(in)){
        type=20;
      } else if(isInt(in)){
        type=22;
      } else if(isFloat(in)){
        type=23;
      }
      return type;
    }

    // *************************************************************************** Pointers

    public static String derefPointer(String id) {
      Node n = VarTable.findNode(id);
      int level = n.getPointerLevel();
      return pointerLoop(id, level);
    }

    public static String pointerLoop(String id, int level)
    {
      Node n = VarTable.findNode(id);
      String temp = "";
      String temp2 = "";
      for (int i = 0; i < level; ++i) {
        temp = newTemp(n.getType());
        if (i == 0) {
          out.println("\t" + temp + " = * " + id + ";");
        } else {
          out.println("\t" + temp + " = * " + temp2 + ";");
        }
        temp2 = temp;
      }

      return temp2;
    }

    public static String pointerAsig(String id, String exp) {

      Node n = VarTable.findNode(id);

      if (n.getPointerLevel() > 1) { //returns id as a temp
        id = pointerLoop(id, n.getPointerLevel() - 1);
      }

      int idType = getType(id);
      int expType = getType(exp);

      if (idType == VAR && expType == VAR) {
        idType = varType(id);
        expType = varType(exp);
        if (idType == expType) {
          out.println("\t" + id + " = " + exp + ";");
        }
      } else {
        if (idType == TEMP) {
          idType = tempType(id);
        } else if (idType == VAR) {
          idType = varType(id);
        }
        
        if (expType == TEMP) {
          expType = tempType(exp);
        } else if (expType == VAR) {
          expType = varType(exp);
        }

        if(idType==expType){
          out.println("\t* " + id + " = " + exp + ";");
        }
      }

      if (idType == FLOAT && expType == INT) {
        String temp = newTemp("float");
        out.println("\t" + temp + " = (float) " + exp + ";");
        out.println("\t* " + id + " = " + temp + ";");
      } else if (idType == INT && expType == FLOAT) {
        comment("Can't assign float to int");
        error();
        halt();
      }
      
      return id;
    }

    public static String pointerAsigAmp(String id, String exp, boolean isId, int level) {
      Node n = VarTable.findNode(id);
      String temp = "";
      String aux = "";

      if (level > 1 && !isId) { // * id = & x
        aux = pointerLoop(id, level - 1);
        temp = newTemp(n.getType());
        out.println("\t" + temp + " = & " + exp + ";");
        out.println("\t* " + aux + " = " + temp + ";");
      } else { // id = & x
        temp = newTemp(n.getType());
        out.println("\t" + temp + " = & " + exp + ";");
        out.println("\t" + id + " = " + temp + ";");
      }

      return temp;
    }

    // *************************************************************************** Arrays

    public static void checkRange(String id, Tuple posOrVar) {
      Node n = VarTable.findNode(id);
      
      DoubleTag aux = new DoubleTag();
      comment("Comprobacion de rango");
      out.println("\tif (" + posOrVar.getA() + " < 0) goto " + aux.getT() + ";");
      out.println("\tif (" + n.getSize() + " < " + posOrVar.getA() + ") goto " + aux.getT() + ";");
      out.println("\tif (" + n.getSize() + " == " + posOrVar.getA() + ") goto " + aux.getT() + ";");
      _goto(aux.getF());
      label(aux.getT());
      error();
      out.println("\thalt;");
      label(aux.getF());
    }

    public static void initArray(String id, String temp, int pos, String exp) {
      Node n = VarTable.findNode(id);
      int length = n.getSize();
      if (pos >= length) {
        complexError(2);
      } else {
        n.addNodeToArray(new Node(exp, n.getScope(), n.getType()), pos);
        out.println("\t" + temp + "[" + pos + "] = " + exp + ";");
      }
    }

    public static void initArray2(String id, String temp, String temp2, int tamUsed, boolean isInExp) {
      // a = {1, 2, 3, 4};
      // int a[4] = {1, 2, 3, 4};
      Node n = VarTable.findNode(id);
      if (isInExp) {
        for (int i = 0; i < tamUsed; ++i) {
          out.println("\t" + temp2 + " = " + temp + "[" + i + "];");
          out.println("\t" + id + "[" + i + "] = " + temp2 + ";");
        }
      } else {
        for (int i = 0; i < n.getSize(); ++i) {
          out.println("\t" + temp2 + " = " + temp + "[" + i + "];");
          out.println("\t" + id + "[" + i + "] = " + temp2 + ";");
        }
      }
      if (isInExp) {
        out.println("\t" + id + " = " + temp + ";");
      }
    }
    public static void getArrayValue(String id, Tuple pos, String temp) {
      // Variable
      checkRange(id, pos);
      out.println("\t" + temp + " = " + id + "[" + pos.getA() + "];");
    }

    public static void arrayAsig(Tuple id, Tuple e1, Tuple e2) {//a[1] = 1;
      Node n = VarTable.findNode(id.getA()+"");
      n.addNodeToArray(new Node(e2.getA()+"", n.getScope(), id.getB()+""));
      String ida = id.getA() + "[" + e1.getA() + "]";
      if (!id.getB().equals(e2.getB())) {
        if (id.getB().equals("int") && e2.getB().equals("float")){
          comment("Can't assign float to int");
          error();
          halt();
        } else {
          String temp = GenTag.genTemp();
          out.println("\t" + temp + " = (float) " + e2.getA() + ";");
          out.println("\t" + ida + " = " + temp + ";");
        }
      } else {
        out.println("\t" + ida + " = " + e2.getA() + ";");
      }
    }

    public static void arrayAsig(Node n1, Node n2) { //a = b
      List<Node> list = n2.getArray();
      String temp = GenTag.genTemp();
      if (n1.getSize() >= n2.getSize()) {
        for (int i = 0; i < n2.getSize(); ++i) {
          out.println("\t" + temp + " = " + n2.getName() + "_" + n2.getScope() + "[" + i + "];");
          out.println("\t" + n1.getName() + "_" + n1.getScope() + "[" + i + "] = " + temp + ";");
          n1.addNodeToArray(list.get(i));
        }
      } else {
        comment("Incompatible Arrays");
        error();
        halt();
      }
    }

    // *************************************************************************** Errors

    public static void complexError(int errorCode){

      switch (errorCode) {
        case 0:
          out.println("\tError: SyntaxError, unexpected number when it was expected variable");
          break;
        case 1:
          out.println("\tError: Arithmetic operation with pointers unsupported");
          break;
        case 2:
          out.println("\tError: ArrayIndexOutOfBoundsException");
          break;
        default:
          out.println("\tError: Unknow error");
          break;
      }
        halt();
    }

    public static void error() {
      out.println("\terror;");
    }
    
    public static void halt() {
      out.println("\thalt;");
      VarTable.clear();
      System.exit(0);
    }
}
