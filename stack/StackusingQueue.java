package stack;

import queue.Queue;

public class StackusingQueue {

    int size;
    Queue q1;
    Queue q2;

    public StackusingQueue(int size) {
        this.size = size;
        this.q1 = new Queue(size);
        this.q2 = new Queue(size);
    }
    
    // Makking push operation costly
    // public void push(int val) {
    //     q2.enQueue(val);
    //     while(!q1.isEmpty()) {
    //         q2.enQueue(q1.deQueue());
    //     }

    //     q1 = q2;
    //     q2 = new Queue(size);
    // }

    // public int pop() {
    //     if(q1.isEmpty()) {
    //         System.out.println("Stack is empty!");
    //         return -1;
    //     }
    //     return q1.deQueue();
    // }

    // Making pop operation costly
    public void push(int val) {
        q1.enQueue(val);
    }

    public int pop() {
        int val = -1;
        if(q1.isEmpty()) {
            System.out.println("Stack is empty!");
            return val;
        } else {
            while(!q1.isEmpty()) {
                val = q1.deQueue();
                if(!q1.isEmpty()) {
                    q2.enQueue(val);
                }
            }

            q1 = q2;
            q2 = new Queue(size);
        }
        return val;
    }

    public int top() {
        int val = -1;
        if(q1.isEmpty()) {
            System.out.println("Stack is empty!");
            return val;
        } else {
            while(!q1.isEmpty()) {
                val = q1.deQueue();
                q2.enQueue(val);
            }

            q1 = q2;
            q2 = new Queue(size);
        }
        return val;
    }

    public boolean isEmpty() {
        return q1.isEmpty();
    }
    
    public static void main(String[] args) {
        StackusingQueue s = new StackusingQueue(3);

        System.out.println(s.isEmpty());
        System.out.println(s.top());
        System.out.println(s.pop());
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        System.out.println(s.top());
        System.out.println(s.isEmpty());
        System.out.println(s.pop());
        System.out.println(s.top());
        System.out.println(s.pop());
        System.out.println(s.pop());
        System.out.println(s.pop());
    }
}
