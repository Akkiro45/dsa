package linkedlist;

public class CircularLinkedList {
    
    Node head = null;

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private void addAtBeg(int data) {
        Node node = new Node(data);
        Node pointer = head;

        if(head == null) {
            node.next = node;
            head = node;
            return;
        }

        while(pointer.next != head) {
            pointer = pointer.next;
        }

        pointer.next = node;
        node.next = head;
        head = node;
    }

    private void addAtEnd(int data) {
        Node node = new Node(data);
        Node pointer = head;

        if(head == null) {
            addAtBeg(data);
            return;
        }

        while(pointer.next != head) {
            pointer = pointer.next;
        }
        pointer.next = node;
        node.next = head;
    }

    private void removeAtBeg() {
        if(head == null) {
            return;
        }
        Node pointer = head;

        while(pointer.next != head) {
            pointer = pointer.next;
        }

        if(pointer == head) {
            head = null;
            return;
        }

        pointer.next = head.next;
        head = head.next;
    }

    private void removeAtEnd() {
        if(head == null) {
            return;
        }

        Node currPointer = head;
        Node prevPointer = null;

        while(currPointer.next != head) {
            prevPointer = currPointer;
            currPointer = currPointer.next;
        }

        if(currPointer == head) {
            head = null;
            return;
        }

        prevPointer.next = head;        
    }

    private void remove(int data) {
        if(head == null) {
            return;
        }

        Node pointer = head;
        Node prevPointer = null;
        while(pointer.next != head && pointer.data != data) {
            prevPointer = pointer;
            pointer = pointer.next;
        }

        if(pointer.data != data) {
            return;
        }
        if(pointer == head) {
            removeAtBeg();
            return;
        }
        if(pointer.next == head) {
            removeAtEnd();
            return;
        }
        prevPointer.next = pointer.next;
    }

    private void display() {
        Node pointer = head;

        if(pointer == null) {
            return;
        }

        System.out.print(pointer.data + " ");
        pointer = pointer.next;
        while(pointer != head) {
            System.out.print(pointer.data + " ");
            pointer = pointer.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        CircularLinkedList list = new CircularLinkedList();

        list.display();
        list.addAtBeg(4);
        list.addAtEnd(5);
        list.addAtBeg(3);
        list.display();
        list.display();
        list.removeAtEnd();
        list.removeAtBeg();
        list.remove(4);
        list.display();
    }
}
