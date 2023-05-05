package problems.twopointers;

import java.util.Arrays;

public class TwoSum {

    // Time complexity O(nlogn) as nlogn for sort and n for search
    private static boolean canSum(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        int tempSum;

        Arrays.sort(nums);

        while(l < r) {
            tempSum = nums[l] + nums[r];
            if(tempSum == target) {
                return true;
            } else if(tempSum < target) {
                l++;
            } else {
                r--;
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
