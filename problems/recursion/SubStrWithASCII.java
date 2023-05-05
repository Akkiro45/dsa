package problems.recursion;

public class SubStrWithASCII {
    public static void main(String[] args) {
        substr("ABC", "");
    }

    private static void substr(String str, String res) {
        if(str.length() == 0) {
            System.out.println(res);
            return;
        }

        char curr = str.charAt(0);
        str = str.substring(1);
        substr(str, res + Character.toString(curr));
        substr(str, res + (int) curr);
        substr(str, res);
    }
}
