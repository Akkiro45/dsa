package fenwicktree;

public class FenwickTree1 {
    int[] tree;
    int size;

    public FenwickTree1(int n) {
        this.size = n;
        this.tree = new int[n+1];
    }

    public FenwickTree1(int[] nums) {
        this.size = nums.length - 1;
        this.tree = new int[nums.length];
        
        tree = nums.clone();

        int i = 1;
        int j = 0;
        while(i <= this.size) {
            j = i + movementSize(i);
            if(j <= this.size) {
                tree[j] += tree[i];
            }
            i++;
        }
    }

    private int movementSize(int i) {
        return (i & -i);
    }

    private int getSum(int i) {
        int sum = 0;
        while(i > 0) {
            sum += tree[i];
            i -= movementSize(i);
        }
        return sum;
    }

    public int rangeQuery(int i, int j) {
        return (getSum(j) - getSum(i-1));
    }

    public int get(int i) {
        return rangeQuery(i, i);
    }

    public void pointUpdate(int i, int val) {
        while(i <= size) {
            tree[i] += val;
            i += movementSize(i);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 3, 5, 7, 9, 11};
        FenwickTree1 obj = new FenwickTree1(nums);
        System.out.println(obj.rangeQuery(1, 3));
        System.out.println(obj.get(1));
        obj.pointUpdate(1, 2);
        System.out.println(obj.rangeQuery(1, 3));
        System.out.println(obj.get(1));
    }
}
