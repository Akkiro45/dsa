package heap;

public class IndexedPriorityQueue {
    int size;
    int elements;
    int[] val;
    int[] pm;
    int[] im;

    public IndexedPriorityQueue() {
        elements = 0;
        size = 2;
        val = new int[size];
        pm = new int[size];
        im = new int[size];
        for(int i=0; i<elements; i++) {
            val[i] = -1;
            pm[i] = i;
            im[i] = i;
        }
    }

    public IndexedPriorityQueue(int nums[]) {
        elements = nums.length;
        size = nums.length;
        val = new int[size];
        pm = new int[size];
        im = new int[size];
        for(int i=0; i<elements; i++) {
            val[i] = nums[i];
            pm[i] = i;
            im[i] = i;
        }
        construct();
    }

    private void construct() {
        for(int i=(elements/2) - 1; i>=0; i--) {
            sink(i);
        }
    }

    private void swim(int i) {
        int parent = (i - 1) / 2;
        while(parent >= 0 && shouldSwap(i, parent)) {
            swap(i, parent);
            i = parent;
            parent = (i - 1) / 2;
        }
    }

    private void sink(int i) {
        int leftChild;
        int rightChild;
        int child;
        while(true) {
            leftChild = (i + 1) * 2;
            rightChild = (i + 2) * 2;
            child = i;
            if(leftChild < elements && shouldSwap(leftChild, child)) {
                child = leftChild;
            }
            if(rightChild < elements && shouldSwap(rightChild, child)) {
                child = rightChild;
            }
            if(child == i) {
                return;
            }
            swap(i, child);
            i = child;
        }
    }

    private boolean shouldSwap(int child, int parent) {
        // Min heap
        return val[im[child]] < val[im[parent]];
    }

    private void swap(int i, int j) {
        int temp = pm[im[i]];
        pm[im[i]] = pm[im[j]];
        pm[im[j]] = temp;

        temp = im[j];
        im[j] = im[i];
        im[i] = temp;
    }

    public void display() {
        System.out.println("PM");
        for(int i=0; i<elements; i++) {
            System.out.print(pm[i] + " ");
        }
        System.out.println();
    }

    public void insert(int ki, int value) {
        if(ki >= size) {
            size *= 2;
            int[] tempVal = new int[size];
            int[] tempPM = new int[size];
            int[] tempIM = new int[size];
            for(int i=0; i<elements; i++) {
                tempVal[i] = val[i];
                tempPM[i] = pm[i];
                tempIM[i] = im[i];
            } 
            val = tempVal;
            pm = tempPM;
            im = tempIM;
        }
        val[ki] = value;
        pm[ki] = ki;
        im[ki] = ki;
        elements++;
        swim(ki);
    }

    public int size() {
        return elements;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(int ki) {
        if(ki >= elements) {
            return false;
        }
        return pm[ki] != -1; 
    }

    public int peekMinKeyIndex() {
        if(isEmpty()) {
            return -1;
        }
        return im[0];
    }

    public int pollMinKeyIndex() {
        if(isEmpty()) {
            return -1;
        }
        return delete(im[0]);
    }

    public int delete(int ki) {
        if(!contains(ki)) {
            return -1;
        }
        int value = val[ki];
        int i = pm[ki];
        elements--;
        swap(i, size());
        sink(i);
        swim(i);
        val[ki] = -1;
        pm[elements] = -1;
        im[elements] = -1;
        return value;
    }

    public int update(int ki, int value) {
        if(!contains(ki)) {
            return -1;
        }
        int oldValue = val[ki];
        val[ki] = value;
        int i = pm[ki];
        sink(i);
        swim(i);
        return oldValue;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 15, 11, 17, 7, 9, 2, 1, 6, 5, 16, 4};
        IndexedPriorityQueue q = new IndexedPriorityQueue();
        for(int i=0; i<nums.length; i++) {
            q.insert(i, nums[i]);
        }
        q.insert(12, 2);
        q.pollMinKeyIndex();
        q.display(); 
    }
}
