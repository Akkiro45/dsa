package linkedlist;

public class List {

    Node head;

    static class Node {
        int val;
        Node next;
    
        Node(int val) {
            this.val = val;
            this.next = null;
        }
    }

    // Add Operations
    private void addAtEnd(int val) {
        Node node = new Node(val);
        Node pointer = head;

        if(pointer == null) {
            head = node;
        } else {
            while(pointer.next != null) {
                pointer = pointer.next;
            }
            pointer.next = node;
        }
    }

    private void addAtBeg(int val) {
        Node node = new Node(val);

        if(head == null) {
            head = node;
            return;
        }

        node.next = head;
        head = node;
    }

    private void addAtPos(int val, int pos) {
        Node node = new Node(val);
        Node pointer = head;
        Node prevPointer = null;

        if(pointer == null) {
            if(pos == 0) {
                head = node;
            }
            return;
        }
        if(pos == 0) {
            node.next = head;
            head = node;
            return;
        }

        int i=0;
        while(i != pos && pointer != null) {
            prevPointer = pointer;
            pointer = pointer.next;
            i++;
        }

        if(i == pos) {
            node.next = prevPointer.next;
            prevPointer.next = node;
        }
    }

    private void addAtEle(int val, int ele) {
        Node node = new Node(val);
        Node pointer = head;
        Node prevPointer = null;

        if(head == null) {
            return;
        }

        while(pointer != null) {
            if(pointer.val == ele) {
                if(prevPointer == null) {
                    node.next = head;
                    head = node;
                } else {
                    node.next = prevPointer.next;
                    prevPointer.next = node;
                }
                break;
            }
            prevPointer = pointer;
            pointer = pointer.next;
        }
    
    }

    // Update Operations
    private void updateAtPos(int val, int pos) {
        Node pointer = head;

        int i = 0;
        while(pointer != null) {
            if(i == pos) {
                pointer.val = val;
                return;
            }
            pointer = pointer.next;
            i++;
        }
    }

    private void updateEle(int val, int ele) {
        Node pointer = head;

        while(pointer != null) {
            if(pointer.val == ele) {
                pointer.val = val;
                return;
            }
            pointer = pointer.next;
        }
    }

    // Delete Operations
    private void delAtBeg() {
        Node pointer = head;

        if(pointer == null) {
            return;
        }

        head = head.next;
    }

    private void delAtEnd() {
        Node pointer = head;
        Node prevPointer = null;

        if(pointer == null) {
            return;
        }

        while(pointer.next != null) {
            prevPointer = pointer;
            pointer = pointer.next;
        }

        prevPointer.next = null;
    }

    private void delEle(int val) {
        Node pointer = head;
        Node prevPointer = null;

        if(pointer == null) {
            return;
        }

        while(pointer != null) {
            if(pointer.val == val) {
                if(prevPointer == null) {
                    head = head.next;
                } else {
                    prevPointer.next = pointer.next;
                }
                return;
            }
            prevPointer = pointer;
            pointer = pointer.next;
        }
    }

    private void delAtPos(int pos) {
        Node pointer = head;
        Node prevPointer = null;

        if(pointer == null) {
            return;
        }

        int i = 0;
        while(pointer != null) {
            if(i == pos) {
                if(prevPointer == null) {
                    head = head.next;
                } else {
                    prevPointer.next = pointer.next;
                }
                return;
            }
            i++;
            prevPointer = pointer;
            pointer = pointer.next;
        }
    }

    // Find Opration
    private Integer findByPos(int pos) {
        Node pointer = head;

        int i = 0;
        while(pointer != null) {
            if(i == pos) {
                return pointer.val;
            }
            pointer = pointer.next;
            i++;
        }

        return null;
    }

    private Integer findPosByVal(int val) {
        Node pointer = head;

        int i = 0;
        while(pointer != null) {
            if(pointer.val == val) {
                return i;
            }
            pointer = pointer.next;
            i++;
        }

        return null;
    }

    // Display
    private void display() {
        Node pointer = head;
        while(pointer != null) {
            System.out.print(pointer.val + " ");
            pointer = pointer.next;
        }
        System.out.println();
    }

    // Reverse 
    private void reverse() {
        Node prev = null;
        Node curr = head;
        Node next;

        while(curr != null) {
            next = curr.next;
            curr.next = prev;

            prev = curr;
            curr = next;
        }

        head = prev;
    }

    private Node reverseRecursion(Node pointer) {
        if(pointer == null) {
            pointer = head;
        }
        if(pointer.next == null) {
            head = pointer;
            return pointer;
        }

        Node prev = reverseRecursion(pointer.next);
        prev.next = pointer;
        prev.next.next = null;
        return prev.next;
    }

    private Node reverseKNodes(Node head, int k) {
        Node prev = null;
        Node beg = head;
        Node curr = head;
        Node next = null;

        int i=0;
        while(curr != null && i != k) {
            next = curr.next;
            curr.next = prev;

            prev = curr;
            curr = next;
            i++;
        }

        
        if(next != null && next.next != null) {
            beg.next = reverseKNodes(next, k);
        } else {
            beg.next = next;
        }
        return prev;
    }

    private void makeCycle(int i) {
        Node pointer = head;
        Node cycleStart = null;
        while(pointer.next != null) {
            if(i != 0) {
                i++;
                cycleStart = pointer;
            }
            pointer = pointer.next;
        }
        pointer.next = cycleStart;
    }

    private boolean detactCycle() {
        Node slow = head;
        Node fast = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast) {
                return true;
            }
        }

        return false;
    }

    private void removeCycle() {
        Node slow = head;
        Node fast = head;
        boolean isCycle = false;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast) {
                isCycle = true;
                break;
            }
        }

        if(isCycle) {
            slow = head;
            while(slow.next != fast.next) {
                slow = slow.next;
                fast = fast.next;
            }
            fast.next = null;
        }
    }

    private void putEvenAfterOdd() {
        if(head == null || head.next == null) {
            return;
        }
        Node odd = head;
        Node even = head.next;
        Node evenStart = even;

        while(odd.next != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }

        odd.next = evenStart;
    }

    private void putOddAfterEven() {
        if(head == null || head.next == null) {
            return;
        }
        Node odd = head;
        Node even = head.next;
        Node evenStart = even;

        while(odd.next != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            if(odd.next != null) {
                even.next = odd.next;
                even = even.next;
            }
        }
        
        even.next = head;
        head = evenStart;
        odd.next = null;
    }

    public static void main(String[] args) {
        List list = new List();
        
        list.addAtPos(0, 0);
        list.addAtEnd(2);
        list.addAtEnd(3);
        list.addAtBeg(1);
        list.addAtPos(4, 0);
        list.addAtPos(9, 9);
        list.addAtEle(5, 4);
        list.updateAtPos(6, 0);
        list.updateAtPos(5, 1);
        list.updateEle(7, 3);
        list.display();
        System.out.println(list.findByPos(0));
        System.out.println(list.findByPos(10));
        System.out.println(list.findByPos(4));
        System.out.println(list.findPosByVal(0));
        System.out.println(list.findPosByVal(10));
        list.delAtBeg();
        list.display();
        list.delAtEnd();
        list.display();
        list.delEle(5);
        list.display();
        list.delAtPos(1);
        list.display();
        list.addAtEnd(3);
        list.display();
        list.reverse();
        list.display();
        list.reverseRecursion(null);
        list.display();
        list.addAtEnd(4);
        list.addAtEnd(5);
        list.addAtEnd(6);
        list.display();
        list.head = list.reverseKNodes(list.head, 3);
        list.display();
        System.out.println(list.detactCycle());
        List cycleList = new List();
        cycleList.addAtEnd(0);
        cycleList.addAtEnd(1);
        cycleList.addAtEnd(2);
        cycleList.addAtEnd(3);
        cycleList.addAtEnd(4);
        cycleList.addAtEnd(5);
        cycleList.display();
        cycleList.makeCycle(2);
        System.out.println(cycleList.detactCycle());
        cycleList.removeCycle();
        cycleList.display();
        List list1 =new List();
        list1.addAtEnd(1);
        list1.addAtEnd(2);
        list1.addAtEnd(3);
        list1.addAtEnd(4);
        list1.addAtEnd(5);
        list1.addAtEnd(6);
        list1.putEvenAfterOdd();
        list1.putOddAfterEven();
        list1.display();
    }
}
