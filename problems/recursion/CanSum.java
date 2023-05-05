package problems.recursion;

import java.util.HashMap;
import java.util.Map;

public class CanSum {
    public static void main(String[] args) {
        System.out.println(canSum(300, new int[]{7, 14}, null));
    }

    private static boolean canSum(int target, int[] nums, Map<Integer, Boolean> memo) {
        if(target == 0) {
            return true;
        }
        if(target < 0) {
            return false;
        }
        
        if(memo == null) {
            memo = new HashMap<>();
        }

        if(memo.containsKey(target)) {
            return memo.get(target);
        }
        
        for(int num : nums) {
            int res = target - num;
            memo.put(res, canSum(res, nums, memo));
            if (memo.get(res) == true) {
                return true;
            }
        }

        return false;
    }
}
