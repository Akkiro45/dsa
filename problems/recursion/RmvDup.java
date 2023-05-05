package problems.recursion;

public class RmvDup {
    public static void main(String[] args) {
        System.out.println(removeDup("aaaaaabbbbcdd"));
    }

    private static String removeDup(String str) {
        if(str.length() == 0) {
            return "";
        }

        String curr = str.substring(0, 1);
        String res = ""; 
        if(str.length() != 1) {
            res = removeDup(str.substring(1));
        } else {
            res = str;
        }
        if(curr.equals(res.substring(0, 1))) {
            return res;
        } else {
            return curr + res;
        }
    }
}
