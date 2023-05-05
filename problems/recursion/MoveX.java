package problems.recursion;

public class MoveX {
    public static void main(String[] args) {
        System.out.println(moveX("ashxssxkksxxjdkd"));
    }

    private static String moveX(String str) {
        if(str.length() == 1) {
            return str;
        }

        char curr = str.charAt(0);
        String subStr = str.substring(1);
        if(curr == 'x') {
            return moveX(subStr) + "x";
        } else {
            return Character.toString(curr) + moveX(subStr);
        }
    }
}
