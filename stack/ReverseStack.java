package stack;

public class ReverseStack {
    public static void main(String[] args) {
        int n = 10;
        Stack s = new Stack(n);

        for(int i=1; i<=n; i++) {
            s.push(i);
            System.out.print(s.top() + " ");
        }
        System.out.println();
        reverseStack(s);
        for(int i=1; i<=n; i++) {
            System.out.print(s.pop() + " ");
        }
    }

    private static void reverseStack(Stack s) {
        if(s.empty()) {
            return;
        }

        int num = s.pop();
        reverseStack(s);
        insertAtBottom(s, num);
    }

    private static void insertAtBottom(Stack s, int num) {
        if(s.empty()) {
            s.push(num);
            return;
        }

        int popedNum = s.pop();
        insertAtBottom(s, num);
        s.push(popedNum);
    }
}
