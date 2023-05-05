package problems.recursion;

public class ReplacePI {
    public static void main(String[] args) {
        System.out.println(replacePI("pippxmsnspiisndpi", 0, ""));
    }

    private static String replacePI(String str, int i, String res) {
        if(i >= str.length() - 1) {
            return res;
        }

        if(str.charAt(i) == 'p' && str.charAt(i+1) == 'i') {
            res += "3.14";
            i += 2;
        } else {
            res += str.substring(i, i+1);
            i++;
        }

        return replacePI(str, i, res);
    }
}
