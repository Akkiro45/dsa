package problems.greedy;

import java.util.Arrays;

public class MaximumAndMinimumDifference {
    
    public static int getMaximum(int nums[], int n) {
        Arrays.sort(nums);
        int res = 0;

        for(int i=0; i<n; i++) {
            if(i < n/2) {
                res -= nums[i];
            } else {
                res += nums[i];
            }
        }

        return res;
    }

    public static int getMinimum(int nums[], int n) {
        Arrays.sort(nums);
        int res = 0;

        for(int i=0; i<n; i++) {
            if(i % 2 == 0) {
                res -= nums[i];
            } else {
                res += nums[i];
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{12, -3, 10, 0};
        int n = nums.length;
        System.out.println("Maximum : " + getMaximum(nums, n));
        System.out.println("Minimum : " + getMinimum(nums, n));
    }
}
