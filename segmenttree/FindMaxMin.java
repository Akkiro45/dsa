package segmenttree;

class MaxMin {
    int max;
    int min;
    public MaxMin(int min, int max) {
        this.min = min;
        this.max = max;
    }
}

public class FindMaxMin {
    MaxMin[] tree;
    int size;
    int n;

    public FindMaxMin(int[] nums) {
        this.n = nums.length;
        this.size = (int)Math.pow(2, (int) (Math.ceil(Math.log(this.n) / Math.log(2)) + 1)) - 1;
        tree = new MaxMin[this.size];
        construct(0, 0, this.n - 1, nums);
    }

    public MaxMin construct(int curr, int start, int end, int[] nums) {
        if(start == end) {
            tree[curr] = new MaxMin(nums[start], nums[start]);
            return tree[curr];
        }

        int mid = (start + end) / 2;
        MaxMin left = construct((2*curr) + 1, start, mid, nums);
        MaxMin right = construct((2*curr) + 2, mid + 1, end, nums);
        tree[curr] = new MaxMin(Math.min(left.min, right.min), Math.max(left.max, right.max));
        return tree[curr];
    }

    public MaxMin query(int i, int j) {
        if(i < 0 || i >= n || j < 0 || j >= n) {
            throw new IllegalArgumentException("Invalid query!");
        }
        return query(0, 0, n-1, i, j);
    }

    public MaxMin query(int curr, int start, int end, int i, int j) {
        if(start > j || end < i) {
            return new MaxMin(Integer.MAX_VALUE, Integer.MIN_VALUE);
        }
        if(start >= i && end <= j) {
            return tree[curr];
        }
        int mid = (start + end) / 2;
        MaxMin left = query((2*curr) + 1, start, mid, i, j);
        MaxMin right = query((2*curr) + 2, mid + 1, end, i, j);
        return new MaxMin(Math.min(left.min, right.min), Math.max(left.max, right.max));
    }

    public void update(int i, int val) {
        if(i < 0 || i >= n) {
            throw new IllegalArgumentException("Invalid index!");
        }
        update(0, 0, n-1, i, val);
    }

    public MaxMin update(int curr, int start, int end, int i, int val) {
        if(i < start && i > end) {
            return tree[curr];
        }
        if(start == end && start == i) {
            tree[curr].min = val;
            tree[curr].max = val;
            return tree[curr];
        }
        if(start == end) {
            return tree[curr];
        }

        int mid = (start + end) / 2;
        MaxMin left = update((2*curr) + 1, start, mid, i, val);
        MaxMin right = update((2*curr) + 2, mid + 1, end, i, val);

        tree[curr].min = Math.min(left.min, right.min);
        tree[curr].max = Math.max(left.max, right.max);

        return tree[curr];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{12, 6, -3, 9, 0, 1, 3, 7};
        FindMaxMin obj = new FindMaxMin(nums);
        MaxMin ans = obj.query(0, 5);
        System.out.println(ans.min + " " + ans.max);
        obj.update(2, 50);
        ans = obj.query(0, 5);
        System.out.println(ans.min + " " + ans.max);
    }
}
