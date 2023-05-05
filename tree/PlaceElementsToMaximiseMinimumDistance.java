package tree;

import java.util.Arrays;

/**
 * Given an array representing n positions along a straight line. Find k (where k <= n) elements from the array such that the minimum distance between any two (consecutive points among the k points) is maximized.
 */
public class PlaceElementsToMaximiseMinimumDistance {

    public static boolean isFeasible(int[] nums, int k, int target) {
        int found = 1;
        int last = nums[0];

        for(int num : nums) {
            if((num - last) >= target) {
                last = num;
                found++;
                if(found == k) {
                    return true;
                }
            }
        }
        
        return false;
    }

    public static int findMaximiseMinimumDistance(int[] nums, int k) {
        Arrays.sort(nums);
        int res = -1;
        int n = nums.length;
        int beg = nums[0];
        int end = nums[n-1];
        int mid;


        while(beg < end) {
            mid = (beg + end) / 2;
            if(isFeasible(nums, k, mid)) {
                res = Math.max(res, mid);
                beg = mid + 1;
            } else {
                end = mid - 1;
            }
        }        

        return res;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 8, 4, 9};
        int k1 = 3;
        System.out.println("Res1 : " + findMaximiseMinimumDistance(nums1, k1));
    }
}
