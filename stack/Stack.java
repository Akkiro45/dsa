package stack;

public class Stack {

    int top = -1;
    int size;
    int[] stack;

    public Stack() {
        this.size = 5;
        this.stack = new int[size];
    }

    public Stack(int size) {
        this.size = size;
        this.stack = new int[size];
    }

    public void push(int num) {
        if(top != size - 1) {
            stack[++top] = num;
        } else {
            System.out.println("Stack overflow!");
        }
    }

    public int pop() {
        if(top == -1) {
            System.out.println("Stack underflow!");
            return -1;
        } else {
            int temp = stack[top];
            stack[top--] = 0;
            return temp;
        }
    }

    public int top() {
        if(top == -1) {
            System.out.println("Stack underflow!");
            return -1;
        } else {
            return stack[top];
        }
    }

    public boolean empty() {
        return top == -1;
    }

    public static void main(String[] args) {
        Stack s = new Stack(6);

        System.out.println(s.empty());
        s.pop();
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        s.push(5);
        s.pop();
        s.push(6);
        s.push(7);
        System.out.println(s.top());;
        System.out.println(s.empty());
    }
}
