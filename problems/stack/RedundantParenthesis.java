package problems.stack;
import java.util.ArrayDeque;
import java.util.Deque;

public class RedundantParenthesis {

    private static boolean isOpening(char ch) {
        return ch == '(';
    }

    private static boolean isClosing(char ch) {
        return ch == ')';
    }

    private static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch =='^';
    }

    private static boolean haveRedundantParenthesis(String exp) {
        int n = exp.length();
        char ch;
        Deque<Character> s = new ArrayDeque<>(n);

        for(int i=0; i<n; i++) {
            ch = exp.charAt(i);
            if(isOperator(ch) || isOpening(ch)) {
                s.push(ch);
            } else if(isClosing(ch)) {
                if(s.isEmpty() || isOpening(s.peek())) {
                    return true;
                }
                while(!isOpening(s.pop())) {
                    continue;  
                }
            }
        }
        
        return false;
    }

    public static void main(String[] args) {
        String exp1 = "((a+b))"; // true
        System.out.println(haveRedundantParenthesis(exp1));
        
        String exp2 = "(a+(a+b))"; // false
        System.out.println(haveRedundantParenthesis(exp2));

        String exp3 = "(a+(b)/c)"; // true
        System.out.println(haveRedundantParenthesis(exp3));

        String exp4 = "(a+b*(c-d))"; // false
        System.out.println(haveRedundantParenthesis(exp4));
    }
}
