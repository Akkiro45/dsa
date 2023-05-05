package segmenttree;

class Node {
    int count;
    int left;
    int right;

    public Node(int count, int left, int right) {
        this.count = count;
        this.left = left;
        this.right = right;
    }
}

public class KthOne {
    Node[] tree;
    int size;
    int n;

    public KthOne(int[] nums) {
        this.n = nums.length;
        this.size = (int)Math.pow(2, (int) (Math.ceil(Math.log(this.n) / Math.log(2)) + 1)) - 1;
        tree = new Node[this.size];
        construct(0, 0, this.n - 1, nums);
    }

    public Node construct(int curr, int start, int end, int[] nums) {
        if(start == end) {
            tree[curr] = new Node(nums[start], 0, 0);;
            return tree[curr];
        }

        int mid = (start + end) / 2;
        Node left = construct((2*curr) + 1, start, mid, nums);
        Node right = construct((2*curr) + 2, mid + 1, end, nums);

        tree[curr] = new Node(left.count + right.count, left.count, right.count);
        return tree[curr];
    }

    public int query(int k) {
        if(k < 0 || k >= n) {
            throw new IllegalArgumentException("Invalid query!");
        }
        return query(0, 0, n-1, k);
    }

    public int query(int curr, int start, int end, int k) {
        if(start == end) {
            return start;
        }
        int mid = (start + end) / 2;
        if(k > tree[curr].left - 1) {
            return query((2*curr) + 2, mid + 1, end, k - tree[curr].left);
        } else {
            return query((2*curr) + 1, start, mid, k);
        }
    }

    public void update(int i) {
        if(i < 0 || i >= n) {
            throw new IllegalArgumentException("Invalid index!");
        }
        update(0, 0, n-1, i);
    }

    public Node update(int curr, int start, int end, int i) {
        if(i < start && i > end) {
            return tree[curr];
        }
        if(start == end && start == i) {
            tree[curr].count = tree[curr].count == 0 ? 1 : 0;
            return tree[curr];
        }
        if(start == end) {
            return tree[curr];
        }

        int mid = (start + end) / 2;
        Node left = update((2*curr) + 1, start, mid, i);
        Node right = update((2*curr) + 2, mid + 1, end, i);

        tree[curr] = new Node(left.count + right.count, left.count, right.count);
        return tree[curr];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 0, 1, 0};
        KthOne obj = new KthOne(nums);
        System.out.println(obj.query(0));
        System.out.println(obj.query(1));
        System.out.println(obj.query(2));
        obj.update(2);
        System.out.println(obj.query(3));
        obj.update(0);
        System.out.println(obj.query(0));
    }
}