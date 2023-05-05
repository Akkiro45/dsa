package queue;

import stack.Stack;

public class QueueusingStackRec {
    
    int size;
    Stack s;

    public QueueusingStackRec(int size) {
        this.size = size;
        this.s = new Stack(size);
    }

    public void enQueue(int num) {
        s.push(num);
    }

    private int popBottomOfStack() {
        if(s.empty()) {
            return -1;
        }

        int num = s.pop();
        int f = popBottomOfStack();
        if(f == -1) {
            return num;
        }
        s.push(num);
        return f;
    }

    private int getBottomOfStack() {
        if(s.empty()) {
            return -1;
        }

        int num = s.pop();
        int f = getBottomOfStack();
        s.push(num);
        if(f == -1) {
            return num;
        }
        return f;
    }

    public int deQueue() {
        if(s.empty()) {
            System.out.println("Queue is empty!");
            return -1;
        }
        return popBottomOfStack();
    }

    public int peek() {
        if(s.empty()) {
            System.out.println("Queue is empty!");
            return -1;
        }
        return getBottomOfStack();
    }

    public boolean isEmpty() {
        return s.empty();
    }

    public static void main(String[] args) {
        QueueusingStackRec q = new QueueusingStackRec(3);

        System.out.println(q.isEmpty());
        System.out.println(q.peek());
        System.out.println(q.deQueue());
        q.enQueue(1);
        q.enQueue(2);
        q.enQueue(3);
        q.enQueue(4);
        System.out.println(q.peek());
        System.out.println(q.isEmpty());
        System.out.println(q.deQueue());
        System.out.println(q.peek());
        System.out.println(q.deQueue());
        System.out.println(q.deQueue());
        System.out.println(q.deQueue());
    }
}
