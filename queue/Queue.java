package queue;

public class Queue {
    
    int f = -1;
    int r = -1;
    int n;
    int[] queue;

    public Queue(int n) {
        this.n = n;
        this.queue = new int[n];
    }

    public void enQueue(int val) {
        if(f == -1 && r == -1) {
            f++;
            r++;
            queue[r] = val;
        } else if(r == n-1) {
            System.out.println("Queue is full!");
        } else {
            queue[++r] = val;
        }
    }

    public int deQueue() {
        int val = -1;
        if(f == -1) {
            System.out.println("Queue is empty!");
            return val;
        } else if(f == r) {
            val = queue[f];
            queue[f] = 0;
            f = -1;
            r = -1;
            return val;
        } else {
            val = queue[f];
            queue[f++] = 0;
            return val;
        }
    }

    public boolean isEmpty() {
        return f == -1 && r == -1;
    }

    public int peek() {
        int val = -1;
        if(f == -1) {
            System.out.println("Queue is empty!");
            return val;
        } else {
            return queue[f];
        }
    }

    public static void main(String[] args) {
        Queue q = new Queue(3);

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
