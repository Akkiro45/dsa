package problems.twopointers;

import java.util.Arrays;

public class ThreeSum {

    private static boolean canSum(int[] nums, int target) {
        int l, r;
        int n = nums.length;
        int tempSum;

        Arrays.sort(nums);
        
        for(int i=0; i<n-2; i++) {
            l = i+1;
            r = n-1;
            tempSum = nums[i];
            while(l < r) {
                tempSum += nums[l] + nums[r];
                if(tempSum == target) {
                    return true;
                } else if(tempSum < target) {
                    l++;
                } else {
                    r--;
                }
                tempSum = nums[i];
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{12, 3, 7, 1, 6, 9};
        int target1 = 16;
        System.out.println(canSum(nums1, target1));

        int[] nums2 = new int[]{12, 3, 7, 1, 6, 9};
        int target2 = 5;
        System.out.println(canSum(nums2, target2));
    }
}
