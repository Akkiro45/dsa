package problems.recursion;

import java.util.ArrayList;
import java.util.List;

public class PermutationWIthDuplicatNo {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 2};
        List<List<Integer>> res = permutation(nums, 0);
        for(List<Integer> list : res) {
            for(int num : list) {
                System.out.print(" " + num);
            }
            System.out.println();
        }
    }

    private static List<List<Integer>> permutation(int nums[], int i) {
        List<List<Integer>> res = new ArrayList<>();
        int temp;

        if(i == nums.length) {
            List<Integer> list = new ArrayList<>();
            for(int num : nums) {
                list.add(num);
            }
            res.add(list);
            return res;
        }

        for(int j=i; j<nums.length; j++) {
            if(!(i != j && nums[i] == nums[j])) {
                temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
                for(List<Integer> list : permutation(nums, i + 1)) {
                    res.add(list);
                }
                temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
            }
        }

        return res;
    }
}
