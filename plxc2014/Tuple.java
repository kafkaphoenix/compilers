public class Tuple<A,B> {
  private  A a;
  private  B b;

  public Tuple(A x, B y){
    a = x;
    b = y;
  }

  public A getA() {
    return a;
  }

  public B getB() {
    return b;
  }

  public void setA(A x) {
    a = x;
  }

  public void setB(B y) {
    b = y;
  }

  @Override
  public  String toString() {
    return "(" + a + ", " + b + ")";
  }

}
