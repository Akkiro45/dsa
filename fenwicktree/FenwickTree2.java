package fenwicktree;

public class FenwickTree2 {
    int[] currentTree;
    int[] originalTree;
    int size;

    public FenwickTree2(int n) {
        this.size = n;
        this.currentTree = new int[n+1];
    }

    public FenwickTree2(int[] nums) {
        this.size = nums.length - 1;
        this.currentTree = new int[nums.length];
        
        currentTree = nums.clone();

        int i = 1;
        int j = 0;
        while(i <= this.size) {
            j = i + movementSize(i);
            if(j <= this.size) {
                currentTree[j] += currentTree[i];
            }
            i++;
        }
        originalTree = currentTree.clone();
    }

    private int movementSize(int i) {
        return (i & -i);
    }

    public int get(int i) {
        return getSum(i, currentTree) - getSum(i-1, originalTree);
    }

    public int getSum(int i, int[] tree) {
        int sum = 0;
        while(i > 0) {
            sum += tree[i];
            i -= movementSize(i);
        }
        return sum;
    }

    public void rangeUpdate(int i, int j, int val) {
        add(i, val);
        add(j+1, -val);
    }

    public void add(int i, int val) {
        while(i <= size) {
            currentTree[i] += val;
            i += movementSize(i);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 3, 5, 7, 9, 11};
        FenwickTree2 obj = new FenwickTree2(nums);
        System.out.println(obj.get(1));
        System.out.println(obj.get(3));
        System.out.println(obj.get(5));
        obj.rangeUpdate(1, 3, 2);
        System.out.println(obj.get(1));
        System.out.println(obj.get(3));
        System.out.println(obj.get(5));
    }
}
