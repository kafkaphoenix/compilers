public class GenTag {

    private static int tag, label;

    public GenTag() {
        tag = 0;
        label = 0;
    }

    public static String genTemp () {
        return "$t" + tag++;
    }
    
    public static String genTag () {
        return "label" + label++;
    }
    
}