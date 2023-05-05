package queue;

public class QueueLinkedList {

    static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
            this.next = null;
        }
    }

    Node f = null;
    Node r = null;

    public void enQueue(int val) {
        Node node = new Node(val);

        if(f == null && r == null) {
            f = node;
            r = node;
        } else {
            r.next = node;
            r = node;
        }
    }

    public int deQueue() {
        int val = -1;
        Node temp;

        if(f == null) {
            System.out.println("Queue is empty!");
        } else if(f == r) {
            val = f.val;
            temp = f;
            temp = null;
            f = null;
            r = null;
        } else {
            val = f.val;
            temp = f;
            f = f.next;
            temp = null;
        }
        return val;
    }

    public int peek() {
        int val = -1;
        if(f == null) {
            System.out.println("Queue is empty!");
        } else {
            return f.val;
        }
        return val;
    }

    public boolean isEmpty() {
        return f == null;
    }

    public static void main(String[] args) {
        QueueLinkedList q = new QueueLinkedList();

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
        System.out.println(q.deQueue());
    }
}
