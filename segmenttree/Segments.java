package segmenttree;

import java.util.Arrays;

public class Segments {

    int size;
    int n;
    int[] tree;

    public Segments(int[] nums) {
        this.n = nums.length;
        this.size = (int) (Math.pow(2, Math.ceil(Math.log(n) / Math.log(2)) + 1) - 1);
        tree = new int[size];
    }

    public int query(int curr, int start, int end, int i, int j) {
        if(start > j || end < i) {
            return 0;
        }
        if(i <= start && end <=j) {
            return tree[curr];
        }

        int mid = (start + end) / 2;
        return query((2*curr)+1, start, mid, i, j) + query((2*curr)+2, mid + 1, end, i, j);
    }

    public void update(int curr, int start, int end, int i) {
        tree[curr]++;
        if(start == end) {
            return;
        }

        int mid = (start + end) / 2;
        if(i <= mid) {
            update((2*curr)+1, start, mid, i);
        } else {
            update((2*curr)+2, mid + 1, end, i);
        }
    }

    public static void findNestedSegments(int[] nums) {
        System.out.println("\nNested segments");
        Segments obj = new Segments(nums);
        int n = nums.length;
        Integer[][] pairs = new Integer[(n/2) + 1][3];

        pairs[0][0] = 0;
        pairs[0][1] = 0;
        pairs[0][2] = 0;
        
        for(int i=0; i<n; i++) {
            pairs[nums[i]][2] = nums[i];
            if(pairs[nums[i]][0] == null) {
                pairs[nums[i]][0] = i;
            } else {
                pairs[nums[i]][1] = i;
            }
        }

        Arrays.sort(pairs, (a, b) -> a[1] - b[1]);
        
        for(int i=1; i<=n/2; i++) {
            System.out.println();
            System.out.print(pairs[i][2] + " [" + pairs[i][0] + ", " + pairs[i][1] + "]");
            int ans = obj.query(0, 0, obj.n-1, pairs[i][0], pairs[i][1]);
            System.out.print(" -> " + ans);
            obj.update(0, 0, obj.n-1, pairs[i][0]);
        }
    }

    public static void findOverlappingSegments(int[] nums) {
        System.out.println("\nOverlapping segments");
        Segments obj = new Segments(nums);
        int n = nums.length;
        Integer[][] pairs = new Integer[(n/2) + 1][3];

        pairs[0][0] = 0;
        pairs[0][1] = 0;
        pairs[0][2] = 0;
        
        for(int i=0; i<n; i++) {
            pairs[nums[i]][2] = nums[i];
            if(pairs[nums[i]][0] == null) {
                pairs[nums[i]][0] = i;
            } else {
                pairs[nums[i]][1] = i;
            }
        }

        Arrays.sort(pairs, (a, b) -> a[1] - b[1]);
        
        for(int i=1; i<=n/2; i++) {
            System.out.println();
            System.out.print(pairs[i][2] + " [" + pairs[i][0] + ", " + pairs[i][1] + "]");
            int ans = obj.query(0, 0, obj.n-1, pairs[i][0], pairs[i][1]);
            System.out.print(" -> " + (pairs[i][1] - pairs[i][0] - 1 - (2 *ans)));
            obj.update(0, 0, obj.n-1, pairs[i][0]);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[] {5, 1, 2, 2, 3, 1, 3, 4, 5, 4};
        findNestedSegments(nums);
        findOverlappingSegments(nums);
    }
}
