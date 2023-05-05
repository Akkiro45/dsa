package problems.recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HowSum {
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>(); 
        // nums.add(5);
        // nums.add(3);
        // nums.add(4);
        // nums.add(7);
        // List<Integer> res = howSum(7, nums);

        nums.add(7);
        nums.add(14);
        List<Integer> res = howSum(300, nums, null);

        if(res != null) {
            for(int num : res) {
                System.out.println(num);
            }
        }
    }

    private static List<Integer> howSum(int sum, List<Integer> nums, Map<Integer, List<Integer>> memo) {
        if(sum == 0) {
            return new ArrayList<>();
        }
        if(sum < 0) {
            return null;
        }
        if(memo == null) {
            memo = new HashMap<>();
        } else if(memo.containsKey(sum)) {
            return memo.get(sum);
        }

        for(int num : nums) {
            List<Integer> res = howSum(sum - num, nums, memo);
            if(res != null) {
                res.add(num);
                memo.put(sum - num, res);
                return memo.get(sum - num);
            }
        }

        memo.put(sum, null);
        return memo.get(sum);
    }
}
