package segmenttree;

class MinPair {
    int count;
    int min;
    public MinPair(int min, int count) {
        this.min = min;
        this.count = count;
    }
}

public class FindMinCount {
    MinPair[] tree;
    int size;
    int n;

    public FindMinCount(int[] nums) {
        this.n = nums.length;
        this.size = (int)Math.pow(2, (int) (Math.ceil(Math.log(this.n) / Math.log(2)) + 1)) - 1;
        tree = new MinPair[this.size];
        construct(0, 0, this.n - 1, nums);
    }

    public MinPair construct(int curr, int start, int end, int[] nums) {
        if(start == end) {
            tree[curr] = new MinPair(nums[start], 1);
            return tree[curr];
        }

        int mid = (start + end) / 2;
        MinPair left = construct((2*curr) + 1, start, mid, nums);
        MinPair right = construct((2*curr) + 2, mid + 1, end, nums);
        
        int min;
        int count;
        if(left.min < right.min) {
            min = left.min;
            count = left.count;
        } else if(left.min == right.min) {
            min = right.min;
            count = left.count + right.count;
        } else {
            min = right.min;
            count = right.count;
        }
        
        tree[curr] = new MinPair(min, count);
        return tree[curr];
    }

    public MinPair query(int i, int j) {
        if(i < 0 || i >= n || j < 0 || j >= n) {
            throw new IllegalArgumentException("Invalid query!");
        }
        return query(0, 0, n-1, i, j);
    }

    public MinPair query(int curr, int start, int end, int i, int j) {
        if(start > j || end < i) {
            return new MinPair(Integer.MAX_VALUE, Integer.MIN_VALUE);
        }
        if(start >= i && end <= j) {
            return tree[curr];
        }
        int mid = (start + end) / 2;
        MinPair left = query((2*curr) + 1, start, mid, i, j);
        MinPair right = query((2*curr) + 2, mid + 1, end, i, j);
        
        int min;
        int count;
        if(left.min < right.min) {
            min = left.min;
            count = left.count;
        } else {
            min = right.min;
            count = right.count;
        }
        return new MinPair(min, count);
    }

    public void update(int i, int val) {
        if(i < 0 || i >= n) {
            throw new IllegalArgumentException("Invalid index!");
        }
        update(0, 0, n-1, i, val);
    }

    public MinPair update(int curr, int start, int end, int i, int val) {
        if(i < start && i > end) {
            return tree[curr];
        }
        if(start == end && start == i) {
            tree[curr].min = val;
            tree[curr].count = 1;
            return tree[curr];
        }
        if(start == end) {
            return tree[curr];
        }

        int mid = (start + end) / 2;
        MinPair left = update((2*curr) + 1, start, mid, i, val);
        MinPair right = update((2*curr) + 2, mid + 1, end, i, val);

        int min;
        int count;
        if(left.min < right.min) {
            min = left.min;
            count = left.count;
        } else if(left.min == right.min) {
            min = right.min;
            count = left.count + right.count;
        } else {
            min = right.min;
            count = right.count;
        }

        tree[curr].min = min;
        tree[curr].count = count;

        return tree[curr];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{12, 6, -3, -3, 0, 1, 3, 7};
        FindMinCount obj = new FindMinCount(nums);
        MinPair ans = obj.query(0, 5);
        System.out.println(ans.min + " " + ans.count);
        obj.update(1, -3);
        ans = obj.query(0, 5);
        System.out.println(ans.min + " " + ans.count);
    }
}

