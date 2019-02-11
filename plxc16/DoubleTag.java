
public class DoubleTag {

    private String t, f, next;

    public DoubleTag () {
        t = GenTag.genTag(); //True
        f = GenTag.genTag(); //False
    }

    public DoubleTag (String a) {
        t = ""; 
        f = ""; 
        next = "";
    }

    public String getT(){
        return t;
    }

    public String getF(){
        return f;
    }

    public String getNext(){
        return next;
    }

    public void setT(String t){
        this.t = t;
    }

    public void setF(String f){
        this.f = f;
    }

    public void setNext(String next){
        this.next = next;
    }

    public void permute()
    {
      String aux = t;
      t=f;
      f=aux;
    }

}