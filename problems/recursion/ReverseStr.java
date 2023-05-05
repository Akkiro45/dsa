package problems.recursion;

public class ReverseStr {
    public static void main(String[] args) {
        String str = "binod";
        System.out.println(reverse(str, 0));
    }

    private static String reverse(String str, int i) {
        if(i == str.length() - 1) {
            return Character.toString(str.charAt(i));
        }
        return reverse(str, i+1) + Character.toString(str.charAt(i));
    }
}
