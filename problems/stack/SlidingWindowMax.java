package problems.stack;
import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowMax {
    public static void main(String[] args) {
        // Ans : 3, 3, 5, 5, 6, 7
        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        for(int num : findSlidingWindowMaximum(nums, k)) {
            System.out.print(num + " ");
        }
    }

    // Brute force O(n*k)
    // private static int[] findSlidingWindowMaximum(int[] nums, int k) {
    //     int n = nums.length;
    //     int[] res = new int[n-k+1];
    //     int max = 0;
    //     int p = 0;

    //     for(int i=0; i<n-k+1; i++) {
    //         max = 0;
    //         for(int j=i; j<i+k; j++) {
    //             max = Math.max(max, nums[j]);
    //         }
    //         res[p++] = max;
    //     }

    //     return res;
    // }

    // Using Deque O(n)
    // private static int[] findSlidingWindowMaximum(int[] nums, int k) {
    //     int n = nums.length;
    //     int m = n-k+1;
    //     int[] res = new int[m];
    //     Deque<Integer> dq = new LinkedList<>();
    //     int p=0;

    //     for(int i=0; i<k; i++) {
    //         while(!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) {
    //             dq.removeLast();
    //         }
    //         dq.addLast(i);
    //     }

    //     res[p++] = nums[dq.peek()];

    //     for(int i=k; i<n; i++) {
    //         while(!dq.isEmpty() && dq.peekFirst() < i-k+1) {
    //             dq.removeFirst();
    //         }
    //         while(!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) {
    //             dq.removeLast();
    //         }
    //         dq.addLast(i);
    //         res[p++] = nums[dq.peekFirst()];
    //     }

    //     return res;
    // }

    // Using DP O(n)
    private static int[] findSlidingWindowMaximum(int[] nums, int k) {
        int n = nums.length;
        int m = n-k+1;
        int[] res = new int[m];
        int[] left = new int[n];
        int[] right = new int[n];
        
        for(int i=0; i<n; i++) {
            if(i%k == 0) {
                left[i] = nums[i];
            } else {
                left[i] = Math.max(left[i-1], nums[i]);
            }
        }

        for(int i=n-1; i>=0; i--) {
            if(i == n-1 || (i % k) == k - 1) {
                right[i] = nums[i];
            } else {
                right[i] = Math.max(right[i+1], nums[i]);
            }
        }

        for(int i=0, j=k-1; i<m; i++, j++) {
            res[i] = Math.max(right[i], left[j]);
        }

        return res;
    }
}
