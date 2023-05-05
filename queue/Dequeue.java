package queue;

public class Dequeue {
    
    int size = 0;
    int f = -1;
    int r = -1;
    int[] dequeue;

    public Dequeue() {
        this.size = 0;
        this.dequeue = new int[size];
    }

    public Dequeue(int size) {
        this.size = size;
        this.dequeue = new int[size];
    }

    public void add(int val) {
        addLast(val);
    }

    public void push(int val) {
        addFirst(val);
    }

    public void addFirst(int val) {
        if(f == 0) {
            System.out.println("Dequeue is full!");
        } else if(f == -1) {
            dequeue[++f] = val;
            r++;
        } else {
            dequeue[--f] = val;
        }
    }

    public void addLast(int val) {
        if(r == size - 1) {
            System.out.println("Dequeue is full!");
        } else if(r == -1) {
            dequeue[++r] = val;
            f++;
        } else {
            dequeue[++r] = val;
        }
    }

    public int remove() {
        return removeFirst();
    }

    public int pop() {
        return removeFirst();
    }

    public int removeFirst() {
        int val = -1;
        if(f == -1) {
            System.out.println("Dequeue is empty!");
        } else if(f == r) {
            val = dequeue[f];
            f = -1;
            r = -1;
        } else {
            val = dequeue[f];
            f++;
        }
        return val;
    }

    public int removeLast() {
        int val = -1;
        if(r == -1) {
            System.out.println("Dequeue is empty!");
        } else if(f == r) {
            val = dequeue[r];
            f = -1;
            r = -1;
        } else {
            val = dequeue[r];
            r--;
        }
        return val;
    }

    public int peek() {
        return peekFirst();
    }

    public int top() {
        return peekFirst();
    }

    public int peekFirst() {
        int val = -1;
        if(f == -1 && r == -1) {
            System.out.println("Dequeue is empty!");
        } else {
            val = dequeue[f];
        }
        return val;
    }

    public int peekLast() {
        int val = -1;
        if(f == -1 && r == -1) {
            System.out.println("Dequeue is empty!");
        } else {
            val = dequeue[r];
        }
        return val;
    }

    public boolean isEmpty() {
        return f == -1 && r == -1;
    }

    public void display() {
        if(f == -1 && r == -1) {
            System.out.println("Dequeue is empty!");
        } else {
            for(int i=f; i<=r; i++) {
                System.out.print(dequeue[i] + " ");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        Dequeue dq = new Dequeue(3);

        System.out.println(dq.isEmpty());
        System.out.println(dq.remove());
        System.out.println(dq.removeLast());
        dq.add(1);
        dq.addFirst(2);
        dq.addLast(3);
        dq.add(4);
        dq.display();
        System.out.println(dq.peek());
        System.out.println(dq.peekLast());
        System.out.println(dq.remove());
        System.out.println(dq.removeFirst());
        System.out.println(dq.removeLast());
        System.out.println(dq.remove());
        System.out.println(dq.peek());
        System.out.println(dq.isEmpty());
    }
}
