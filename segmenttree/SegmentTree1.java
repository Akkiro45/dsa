package segmenttree;

public class SegmentTree1 {
    
    private int[] tree;
    private int[] nums;
    private int size;
    private int n;

    public SegmentTree1(int[] nums) {
        n = nums.length;
        this.nums = nums;
        int x = (int) Math.ceil(Math.log(n) / Math.log(2));
        size = (int) (2 * Math.ceil(Math.pow(2, x)) - 1);
        tree = new int[size];
        construct(nums, 0, n-1, 0);
    }

    private int construct(int[] nums, int ss, int se, int si) {
        if(ss == se) {
            tree[si] = nums[ss];
            return tree[si];
        }

        int mid = (ss + se)>>1;

        tree[si] = construct(nums, ss, mid, (si*2) + 1) + 
                    construct(nums, mid + 1, se, (si*2) + 2);

        return tree[si];
    }

    public void display() {
        System.out.println("Segement tree\n");
        
        for(int val : tree) {
            System.out.print(val + " ");
        }

        System.out.println("\n------END------");
    }

    public int rangeQuery(int s, int e) {
        if(s < 0 || e >= n) {
            return -1;
        }
        
        return rangeQuery(0, n-1, 0, s, e);
    }

    private int rangeQuery(int ss, int se, int si, int s, int e) {
        if(ss >= s && se <= e) {
            return tree[si];
        }
        if(ss > e || se < s) {
            return 0;
        }
        int mid = (ss + se) >> 1;
        return rangeQuery(ss, mid, (si*2) + 1, s, e) + rangeQuery(mid + 1, se, (si*2) + 2, s, e);
    }

    public void pointUpdate(int i, int val) {
        int diff = val - nums[i];
        nums[i] = val;
        pointUpdate(0, n-1, 0, i, diff);
    }

    private void pointUpdate(int ss, int se, int si, int i, int diff) {
        if(ss == se && ss == i) {
            tree[si] += diff;
            return;
        }
        if(ss > i || se < i) {
            return;
        }
        tree[si] += diff;
        int mid = (ss + se) >> 1;
        pointUpdate(ss, mid, (si*2) + 1, i, diff);
        pointUpdate(mid + 1, se, (si*2) + 2, i, diff);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 5, 7, 9, 11};
        SegmentTree1 st = new SegmentTree1(nums);
        st.display();
        System.out.println(st.rangeQuery(4, 5));
        st.pointUpdate(4, 10);
        st.display();
        System.out.println(st.rangeQuery(4, 4));
        System.out.println(st.rangeQuery(4, 5));
    }
}
