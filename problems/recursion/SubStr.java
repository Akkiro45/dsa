package problems.recursion;

public class SubStr {
    public static void main(String[] args) {
        substr("ABCDEFG", "");
    }

    private static void substr(String str, String res) {
        if(str.length() == 0) {
            System.out.println(res);
            return;
        }
        String curr = str.substring(0, 1);
        str = str.substring(1);
        substr(str, res + curr);
        substr(str, res);
    }
}
