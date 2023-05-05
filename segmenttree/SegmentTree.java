package segmenttree;

public class SegmentTree {
    private int[] tree;
    private int size;
    private int n;

    public SegmentTree(int[] nums) {
        // size (2 ^ (log(n) + 1)) - 1
        this.n = nums.length;
        this.size = (int) Math.pow(2, (int)(Math.ceil(Math.log(n) / Math.log(2))) + 1) - 1;
        this.tree = new int[this.size];
        construct(nums, 0, 0, n - 1);
    }

    private int construct(int[] nums, int curr, int start, int end) {
        if(start == end) {
            tree[curr] = nums[start];
            return nums[start];
        }
        int mid = (start + end) / 2;
        tree[curr] = construct(nums, (2*curr) + 1, start, mid) + 
                        construct(nums, (2*curr) + 2, mid + 1, end);

        return tree[curr];
    }

    public void display() {
        System.out.println("Segment tree");
        for(int node : tree) {
            System.out.print(node + " ");
        }
        System.out.println();
    }

    public int getRangeSum(int i, int j) {
        if(i < 0  || i >= n || j < 0 || j >= n) {
            throw new IllegalArgumentException("Invalid range!");
        }
        return  getRangeSum(0, 0, n - 1, i, j);
    }

    public int getRangeSum(int curr, int start, int end, int i, int j) {
        if(j < start || i > end) {
            return 0;
        }
        if(i <= start && j >= end) {
            return tree[curr];
        }
        int mid = (start + end) / 2;
        return getRangeSum((curr*2) + 1, start, mid, i, j) + getRangeSum((curr*2) + 2, mid + 1, end, i, j);
    }

    public void update(int i, int oldVal, int newVal) {
        if(i < 0 || i >= n) {
            throw new IllegalArgumentException("Invalid index!");
        }
        update(0, 0, n-1, i, newVal - oldVal);
    }

    public void update(int curr, int start, int end, int i, int diff) {
        if(start > i || i > end) {
            return;
        }
        tree[curr] += diff;
        if(start != end) {
            int mid = (start + end) / 2;
            update((curr*2) + 1, start, mid, i, diff);
            update((curr*2) + 2, mid + 1, end, i, diff);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 5, 7, 9, 11};
        SegmentTree tree = new SegmentTree(nums);
        tree.display();
        System.out.println(tree.getRangeSum(1, 3));
        tree.update(1, 3, 10);
        System.out.println(tree.getRangeSum(1, 3));
    }
}
