package problems.recursion;

public class PermutationOfStr {
    public static void main(String[] args) {
        permutation("ABC", "");
    }

    private static void permutation(String str, String res) {
        if(str.length() == 0) {
            System.out.println(res);
            return;
        }

        for(int i=0; i<str.length(); i++) {
            String curr = str.substring(i, i+1);
            String ros = str.substring(0, i) + str.substring(i+1);
            permutation(ros, res + curr);
        }
    }
}
