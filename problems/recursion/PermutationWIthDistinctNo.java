package problems.recursion;

import java.util.ArrayList;
import java.util.List;

public class PermutationWIthDistinctNo {
    public static void main(String[] args) {
        int n = 3;
        int[] nums = new int[n];
        for(int i=1; i<=n; i++) {
            nums[i-1] = i;
        }
        List<List<Integer>> res = permutation(nums, 0);
        System.out.println(res != null);
        for(List<Integer> list : res) {
            for(int num : list) {
                System.out.print(" " + num);
            }
            System.out.println();
        }
    }

    private static List<List<Integer>> permutation(int[] nums, int i) {
        int temp;
        List<List<Integer>> res = new ArrayList<>();
        if(i == nums.length) {
            List<Integer> list = new ArrayList<>();
            for(int num : nums) {
                System.out.print(" " + num);
                list.add(num);
            }
            res.add(list);
            System.out.println();
            return res;
        }

        for(int j=i; j<nums.length; j++) {
            temp = nums[j];
            nums[j] = nums[i];
            nums[i] = temp;
            for(List<Integer> list : permutation(nums, i+1)) {
                res.add(list);
            }
            temp = nums[j];
            nums[j] = nums[i];
            nums[i] = temp;
        }
        return res; 
    }

    // Without retuning but saving in passed list
    // private static void permutation(int[] nums, int i, List<List<Integer>> res, Map<String, List<List<Integer>>> memo) {
    //     int temp;
    //     if(i == nums.length) {
    //         List<Integer> list = new ArrayList<>();
    //         for(int num : nums) {
    //             System.out.print(" " + num);
    //             list.add(num);
    //         }
    //         res.add(list);
    //         System.out.println();
    //     }

    //     for(int j=i; j<nums.length; j++) {
    //         temp = nums[j];
    //         nums[j] = nums[i];
    //         nums[i] = temp;
    //         permutation(nums, i+1, res, null);
    //         temp = nums[j];
    //         nums[j] = nums[i];
    //         nums[i] = temp;
    //     }
    // }
}
