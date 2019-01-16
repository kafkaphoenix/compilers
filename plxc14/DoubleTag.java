
public class DoubleTag {

    private String t, f;

    public DoubleTag () {
        t = GenTag.genTag(); //True
        f = GenTag.genTag();; //False
    }

    public String getT(){
        return t;
    }

    public String getF(){
        return f;
    }

    public void setT(String t){
        this.t = t;
    }

    public void setF(String f){
        this.f = f;
    }

    public void permute()
    {
      String aux = t;
      t=f;
      f=aux;
    }

}