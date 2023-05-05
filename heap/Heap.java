package heap;
/**
 * Max heap
 */
public class Heap {

    int n = 0;
    int arrSize = 0;
    int[] arr;

    public Heap() {
        this.n = 0;
        this.arrSize = 0;
        this.arr = new int[arrSize];
    }

    public Heap(int n) {
        this.n = 0;
        this.arrSize = n;
        this.arr = new int[arrSize];
    }

    public Heap(int[] arr) {
        this.n = arr.length;
        this.arrSize = n;
        this.arr = arr;
        construct();
    }

    private void increaseSize() {
        int newSize = 1;
        if(arrSize != 0) {
            newSize = arrSize * 2;
        }
        int[] newArr = new int[newSize];
        int i = 0;
        for(int el : arr) {
            newArr[i++] = el;
        }
        this.arr = newArr;
        this.arrSize = newSize;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public void clear() {
        this.n = 0;
        this.arrSize = 0;
        this.arr = new int[arrSize];
    }

    public int peek() {
        if(isEmpty()) {
            return -1;
        }
        return arr[0];
    }

    public int pool() {
        if(isEmpty()) {
            return -1;
        }
        return delete(peek());
    }

    private int indexOf(int el) {
        for(int i=0; i<n; i++) {
            if(el == arr[i]) {
                return i;
            }
        }
        return -1;
    }

    public boolean contains(int el) {
        return indexOf(el) != -1;
    }

    // O(n)
    public void construct() {
        for(int i=((n/2) - 1); i>=0; i--) {
            sink(i);
        }
    }
    
    public void insert(int el) {
        if(n+1 > arrSize) {
            increaseSize();
        }
        arr[n++] = el;
        swim(n-1);
    }

    public int delete(int el) {
        int ind = indexOf(el);
        if(ind < 0) {
            return -1;
        }
        swap(n-1, ind);
        arr[--n] = 0;
        sink(ind);
        return el;
    }

    private int getParentIndex(int childInd) {
        return ((childInd - 1) / 2);
    }

    private int getLeftChildIndex(int parentIndex) {
        return ((parentIndex * 2) + 1);
    }

    private int getRightChildIndex(int parentIndex) {
        return ((parentIndex * 2) + 2);
    }

    private void swim(int ind) {
        int parentInd = getParentIndex(ind);
        while(ind > 0 && arr[parentInd] < arr[ind]) {
            swap(parentInd, ind);
            ind = parentInd;
            parentInd = getParentIndex(ind);
        }
    }

    private void sink(int ind) {
        if(ind < 0) {
            return;
        }
        while(true) {
            int left = getLeftChildIndex(ind);
            int right = getRightChildIndex(ind);
            int largest = ind;
            if(left < n && arr[ind] < arr[left]) {
                largest = left;
            }
            if(right < n && arr[ind] < arr[right]) {
                largest = right;
            }
            if(largest == ind) {
                break;
            }
            swap(ind, largest);
            ind = largest;
        }
    }

    private void swap(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public boolean isValidMaxHeap() {
        int left;
        int right;
        for(int i=0; i<n; i++) {
            left = getLeftChildIndex(i);
            right = getRightChildIndex(i);
            if(left < n && arr[i] < arr[left]) {
                return false;
            }
            if(right < n && arr[i] < arr[right]) {
                return false;
            }
        }
        return true;
    }

    public void display() {
        System.out.println("Heap : ");
        for(int i=0; i<n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public int[] heapSort(int[] arr) {
        Heap heap = new Heap(arr);
        for(int i=arr.length - 1; i>=0; i--) {
            heap.swap(0, i);
            heap.n--;
            heap.sink(0);
        }
        return heap.arr;
    }

    public static void main(String[] args) {
        Heap hp = new Heap();
        hp.insert(4);
        hp.insert(2);
        hp.insert(3);
        hp.insert(1);
        hp.display();
        System.out.println("Deleted : " + hp.delete(10));
        System.out.println("Deleted : " + hp.delete(2));
        // hp.n++;
        // hp.arr[3] = 10;
        hp.display();
        System.out.println("Is Valid : " + hp.isValidMaxHeap());

        int[] arr = new int[]{1, 2, 3, 4, 5, 6};
        hp = new Heap(arr);
        hp.display();
        System.out.println("Is Valid : " + hp.isValidMaxHeap());

        arr = new int[]{9, 2, 6, 4, 8, 1};
        arr = hp.heapSort(arr);
        System.out.println("Sorted : ");
        for(int el : arr) {
            System.out.print(el + " ");
        }
    }
}
