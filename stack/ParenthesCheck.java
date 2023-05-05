package stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class ParenthesCheck {
    public static void main(String[] args) {
        String parenthes1 = "[{()}]";
        System.out.println(parenthes1 + " : " + checkIsValid(parenthes1));

        String parenthes2 = "[{()]}";
        System.out.println(parenthes2 + " : " + checkIsValid(parenthes2));
    }

    private static boolean checkIsValid(String parenthes) {
        Deque<Character> s = new ArrayDeque<>();
        int n = parenthes.length();
        char ch;

        for(int i=0; i<n; i++) {
            ch = parenthes.charAt(i);
            if(isOpening(ch)) {
                s.push(ch);
            } else if(s.peek() == getOpening(ch)) {
                s.pop();
            } else {
                return false;
            }
        }

        return s.isEmpty();
    }

    private static boolean isOpening(char ch) {
        return ch == '{' || ch == '[' || ch == '(';
    }

    private static char getOpening(char ch) {
        switch(ch) {
            case '}':
                return '{';
            case ']':
                return '[';
            case ')':
                return '(';
            default: 
                return '-';
        }
    }
}
