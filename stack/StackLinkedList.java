package stack;

public class StackLinkedList {

    Node top = null;
    
    static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
            this.next = null;
        }
    }

    public void push(int val) {
        Node node = new Node(val);
        if(top == null) {
            top = node;
            return;
        } 
        node.next = top;
        top = node;
    }

    public int pop() {
        if(top == null) {
            System.out.println("Stack underflow!");
            return -1;
        }
        Node temp = top;
        top = top.next;
        return temp.val;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int peek() {
        if(top == null) {
            System.out.println("Stack underflow!");
            return -1;
        }
        return top.val;
    }

    public static void main(String[] args) {
        StackLinkedList s = new StackLinkedList();
        s.pop();
        System.out.println(s.isEmpty());
        System.out.println(s.peek());
        s.push(1);
        s.push(2);
        System.out.println(s.isEmpty());
        System.out.println(s.peek());
        s.pop();
        System.out.println(s.peek());
    }

}
