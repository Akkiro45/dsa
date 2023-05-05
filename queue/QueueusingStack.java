package queue;

import stack.Stack;

public class QueueusingStack {
    
    int size;
    Stack s1;
    Stack s2;

    public QueueusingStack(int size) {
        this.size = size;
        this.s1 = new Stack(size);
        this.s2 = new Stack(size);
    }

    public void enQueue(int num) {
        s1.push(num);
    }

    public int deQueue() {
        if(s1.empty() && s2.empty()) {
            System.out.println("Queue is empty!");
            return -1;
        } else if(s2.empty()) {
            while(!s1.empty()) {
                s2.push(s1.pop());
            }
        }
        return s2.pop();
    }

    public int peek() {
        if(s1.empty() && s2.empty()) {
            System.out.println("Queue is empty!");
            return -1;
        } else if(s2.empty()) {
            while(!s1.empty()) {
                s2.push(s1.pop());
            }
        }
        return s2.top();
    }

    public boolean isEmpty() {
        return s1.empty() && s2.empty();
    }
    public static void main(String[] args) {
        QueueusingStack q = new QueueusingStack(3);

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
