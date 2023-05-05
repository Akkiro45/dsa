package problems.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {

    private static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        long tempSum = 0;
        int l, r;
        
        for(int i=0; i<n-3; i++) {
            if(i == 0 || nums[i] != nums[i-1]) {
                for(int j=i+1; j<n-2; j++) {
                    if(j == i + 1 || nums[j] != nums[j-1]) {
                        tempSum = nums[i] + nums[j];
                        l = j+1;
                        r = n-1;
                        while(l < r) {
                            tempSum += nums[l] + nums[r];
                            if(tempSum == target) {
                                res.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
                                while(l < r && nums[l] == nums[l+1]) {
                                    l++;
                                }
                                while(l < r && nums[r] == nums[r-1]) {
                                    r--;
                                }
                                l++;
                                r--;
                            } else if(tempSum < target) {
                                l++;
                            } else {
                                r--;
                            }
                            tempSum = nums[i] + nums[j];
                        }
                    }
                }   
            }
        }
        
        return res;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 0, -1, 0, -2, 2};
        int target1 = 0;
        System.out.println(fourSum(nums1, target1));

        int[] nums2 = new int[]{2, 2, 2, 2, 2};
        int target2 = 8;
        System.out.println(fourSum(nums2, target2));
    }
}
