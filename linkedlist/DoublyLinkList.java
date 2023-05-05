package linkedlist;

public class DoublyLinkList {
    
    Node head = null;

    static class Node {
        int data;
        Node prev;
        Node next;

        public Node(int data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

    // Insert
    private void insertAtEnd(int data) {
        Node node = new Node(data);
        
        if(head == null) {
            node.next = null;
            node.prev = null;
            head = node;
            return;
        }

        Node pointer = head;
        while(pointer.next != null) {
            pointer = pointer.next;
        }

        pointer.next = node;
        node.prev = pointer;
        node.next = null;
    }

    private void insertAtBeg(int data) {
        Node node = new Node(data);

        if(head == null) {
            head = node;
            return;
        }

        node.next = head;
        head.prev = node;
        head = node;
    }

    private void appendLastKToStart(int k) {
        Node pointer = head;
        int i = 1;

        if(pointer == null) {
            return;
        }
        
        while(pointer.next != null) {
            i++;
            pointer = pointer.next;
        }

        if(i >= k) {
            pointer.next = head;

            while(pointer.prev != null && k != 0) {
                pointer = pointer.prev;
                k--;
            }

            head = pointer.next;
            pointer.next = null;
        }
    }

    private static void concateList(Node head1, Node head2) {
        Node pointer = head1;

        while(pointer.next != null) {
            pointer = pointer.next;
        }

        pointer.next = head2;
        head2.prev = pointer;
    }

    private static int length(Node head) {
        int i = 0;
        Node pointer = head;
        
        while(pointer != null && pointer.next != null) {
            i++;
            pointer = pointer.next;
        }
        
        return i;
    }

    private static Node findIntersection(Node head1, Node head2) {
        int l1 = length(head1);
        int l2 = length(head2);
        int i = 0;

        Node pointer1;
        Node pointer2;

        if(l1 > l2) {
            pointer1 = head1;
            pointer2 = head2;
        } else {
            pointer1 = head2;
            pointer2 = head1;
        }

        while(pointer1 != null && pointer1.next != null && i != Math.abs(l1 - l2)) {
            i++;
            pointer1 = pointer1.next;
        }

        while(pointer1.next != null && pointer2.next != null && pointer1 != pointer2) {
            pointer1 = pointer1.next;
            pointer2 = pointer2.next;
        }

        return pointer1;
    }

    // Remove
    private void remove(int data) {
        Node pointer = head;
        if(pointer == null) {
            return;
        }

        while(pointer != null && pointer.data != data) {
            pointer = pointer.next;
        }

        if(pointer != null) {
            if(pointer.prev != null) {
                pointer.prev.next = pointer.next;
            } else {
                removeAtBeg();
            }
            if(pointer.next != null) {
                pointer.next.prev = pointer.prev;
            }
        }
    }

    private void removeAtBeg() {
        head = head.next;
        if(head != null) {
            head.prev = null;
        }
    }

    // Display
    private void display() {
        Node pointer = head;

        while(pointer != null) {
            System.out.print(pointer.data + " ");
            pointer = pointer.next;
        }
        System.out.println();
    }

    private static void display(Node head) {
        Node pointer = head;

        while(pointer != null) {
            System.out.print(pointer.data + " ");
            pointer = pointer.next;
        }
        System.out.println();
    }

    private static Node mregeSortedList(Node head1, Node head2) {
        Node start = new Node(0);
        Node p1 = start;
        Node p2 = head1;
        Node p3 = head2;

        while(p2 != null && p3 != null) {
            if(p2.data < p3.data) {
                p1.next = p2;
                p2 = p2.next;
            } else {
                p1.next = p3;
                p3 = p3.next;
            }
            p1.next.prev = p1;
            p1 = p1.next;
        }

        if(p2 != null) {
            p1.next = p2;
            p1.next.prev = p1;
        }

        if(p3 != null) {
            p1.next = p3;
            p1.next.prev = p1;
        }

        if(start.next != null) {
            start.next.prev = null;
        }
        return start.next;
    }

    private static Node mregeSortedListRec(Node head1, Node head2) {
        Node pointer = null;

        if(head1 == null) {
            return head2;
        }
        if(head2 == null) {
            return head1;
        }

        if(head1.data < head2.data) {
            pointer = head1;
            pointer.next = mregeSortedListRec(head1.next, head2);
        } else {
            pointer = head2;
            pointer.next = mregeSortedListRec(head1, head2.next);
        }

        pointer.next.prev = pointer;

        return pointer;
    }
    
    public static void main(String[] args) {
        DoublyLinkList list = new DoublyLinkList();
        list.insertAtEnd(3);
        list.insertAtEnd(4);
        list.display();
        list.insertAtBeg(1);
        list.display();
        list.remove(1);
        list.display();
        list.remove(3);
        list.display();
        list.remove(4);
        list.display();
        list.insertAtEnd(3);
        list.insertAtEnd(4);
        list.insertAtEnd(5);
        list.insertAtEnd(1);
        list.insertAtEnd(2);
        list.display();
        list.appendLastKToStart(2);
        list.display();

        // Intersection between two list
        DoublyLinkList list1 = new DoublyLinkList();
        DoublyLinkList list2 = new DoublyLinkList();
        DoublyLinkList list3 = new DoublyLinkList();
        list1.insertAtEnd(1);
        list1.insertAtEnd(2);
        list1.insertAtEnd(3);
        list1.insertAtEnd(4);

        list2.insertAtEnd(7);
        list2.insertAtEnd(8);

        list3.insertAtEnd(5);
        list3.insertAtEnd(6);

        DoublyLinkList.concateList(list1.head, list3.head);
        list1.display();
        DoublyLinkList.concateList(list2.head, list3.head);
        list2.display();

        System.out.println(DoublyLinkList.findIntersection(list1.head, list2.head).data);

        list1 = new DoublyLinkList();
        list2 = new DoublyLinkList();

        list1.insertAtEnd(1);
        list1.insertAtEnd(4);
        list1.insertAtEnd(5);
        list1.insertAtEnd(7);

        list2.insertAtEnd(2);
        list2.insertAtEnd(3);
        list2.insertAtEnd(6);

        // display(DoublyLinkList.mregeSortedList(list1.head, list2.head));
        display(DoublyLinkList.mregeSortedListRec(list1.head, list2.head));
    }
}
