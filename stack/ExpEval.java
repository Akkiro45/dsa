package stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class ExpEval {
    public static void main(String[] args) {
        String prefixExp = "-+7*45+20";
        // String prefixExp = "+*/+46257";
        System.out.println("Prefix exp: " + prefixExp);
        System.out.println("Prefix exp res: " + prefixEval(prefixExp));
        System.out.println();

        String postfixExp = "46+2/5*7+";
        // String postfixExp = "745*+20+-";
        System.out.println("Postfix exp: " + postfixExp);
        System.out.println("Postfix exp res: " + postfixEval(postfixExp));
        System.out.println();

        String infixExp = "((a-(b/c))*((a/k)-l))";
        // String infixExp = "((a-b/c)*(a/k-l))";
        System.out.println("Infix exp: " + infixExp);
        System.out.println("Postfix exp: " + infixToPostfix(infixExp));
        System.out.println();

        System.out.println("Infix exp: " + infixExp);
        System.out.println("Prefix exp: " + infixToPrefix(infixExp));
        System.out.println();

        System.out.println("Prefix exp: " + infixToPrefix(infixExp));
        System.out.println("Infix exp: " + prefixToInfix(infixToPrefix(infixExp)));
        System.out.println();

        System.out.println("Postfix exp: " + infixToPostfix(infixExp));
        System.out.println("Infix exp: " + postfixToInfix(infixToPostfix(infixExp)));
        System.out.println();
    }

    public static int prefixEval(String exp) {
        int n = exp.length();
        Deque<Integer> s = new ArrayDeque<>(n);
        char ch;
        for(int i=n-1; i>=0; i--) {
            ch = exp.charAt(i);
            if(ch >= '0' && ch <= '9') {
                s.push(ch - '0');
            } else {
                int operand1 = s.pop();
                int operand2 = s.pop();
                switch(ch) {
                    case '+':
                        s.push(operand1 + operand2);
                        break;
                    case '-':
                        s.push(operand1 - operand2);
                        break;
                    case '*':
                        s.push(operand1 * operand2);
                        break;
                    case '/':
                        s.push(operand1 / operand2);
                        break;
                    case '^':
                        s.push((int) Math.pow(operand1, operand2));
                        break;
                }
            }
        }
        return s.pop();
    }

    public static int postfixEval(String exp) {
        int n = exp.length();
        Deque<Integer> s = new ArrayDeque<>(n);
        char ch;
        for(int i=0; i<n; i++) {
            ch = exp.charAt(i);
            if(ch >= '0' && ch <= '9') {
                s.push(ch - '0');
            } else {
                int operand2 = s.pop();
                int operand1 = s.pop();
                switch(ch) {
                    case '+':
                        s.push(operand1 + operand2);
                        break;
                    case '-':
                        s.push(operand1 - operand2);
                        break;
                    case '*':
                        s.push(operand1 * operand2);
                        break;
                    case '/':
                        s.push(operand1 / operand2);
                        break;
                    case '^':
                        s.push((int) Math.pow(operand1, operand2));
                        break;
                }
            }
        }
        return s.pop();
    }

    public static String infixToPostfix(String exp) {
        Deque<Character> s = new ArrayDeque<>();
        StringBuilder postfixExp = new  StringBuilder("");
        int n = exp.length();
        char ch, stackCh;

        for(int i=0; i<n; i++) {
            ch = exp.charAt(i);
            if(ch == '(') {
                s.push(ch);
            } else if(ch >= 'a' && ch <= 'z') {
                postfixExp.append(ch);
            } else if(ch == ')') {
                stackCh = s.pop();
                while(stackCh != '(') {
                    postfixExp.append(stackCh);
                    stackCh = s.pop();
                }
            } else {
                while(getOperatorPrecedence(s.peek()) > getOperatorPrecedence(ch)) {
                    postfixExp.append(s.pop());
                }
                s.push(ch);
            }
        }

        return postfixExp.toString();
    }

    private static String infixToPrefix(String exp) {
        Deque<Character> s = new ArrayDeque<>();
        StringBuilder prefixExp = new StringBuilder("");
        int n = exp.length();
        char ch, popedCh;

        for(int i=n-1; i>=0; i--) {
            ch = exp.charAt(i);

            if(ch == ')') {
                s.push(ch);
            } else if(ch >= 'a' && ch <= 'z') {
                prefixExp.append(ch);
            } else if(ch == '(') {
                popedCh = s.pop();
                while(popedCh != ')') {
                    prefixExp.append(popedCh);
                    popedCh = s.pop();
                }
            } else {
                while(getOperatorPrecedence(s.peek()) > getOperatorPrecedence(ch)) {
                    prefixExp.append(s.pop());
                }
                s.push(ch);
            }
        }

        return prefixExp.reverse().toString();
    }

    private static String prefixToInfix(String exp) {
        Deque<String> s = new ArrayDeque<>();
        int n = exp.length();
        char ch;
        String operand1, operand2;

        for(int i=n-1; i>=0; i--) {
            ch = exp.charAt(i);
            if(ch >= 'a' && ch <= 'z') {
                s.push(Character.toString(ch));
            } else {
                operand1 = s.pop();
                operand2 = s.pop();
                s.push("(" + operand1 + Character.toString(ch) + operand2 + ")");
            }
        }

        return s.pop();
    }

    private static String postfixToInfix(String exp) {
        Deque<String> s = new ArrayDeque<>();
        int n = exp.length();
        char ch;
        String operand1, operand2;

        for(int i=0; i<n; i++) {
            ch = exp.charAt(i);
            if(ch >= 'a' && ch <= 'z') {
                s.push(Character.toString(ch));
            } else {
                operand2 = s.pop();
                operand1 = s.pop();
                s.push("(" + operand1 + Character.toString(ch) + operand2 + ")");
            }
        }

        return s.pop();
    }

    private static int getOperatorPrecedence(Character operator) {
        switch(operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return 0;
        }
    }

}
