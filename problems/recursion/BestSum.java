package problems.recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BestSum {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 4, 5};
        int sum = 8;
        List<Integer> res = bestSum(sum, nums, null);
        System.out.println(res.size());
        for(Integer num : res) {
            System.out.print(num + " ");
        }
    }

    private static List<Integer> bestSum(int sum, int[] nums, Map<Integer, List<Integer>> memo) {
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

        List<Integer> bestList = null;
        for(int num : nums) {
            int res = sum - num;
            List<Integer> currList = bestSum(res, nums, memo);
            System.out.println(currList);
            if(currList != null) {
                currList.add(num);
                if(bestList == null || currList.size() < bestList.size()) {
                    bestList = currList;
                }
            }
        }

        memo.put(sum, bestList);
        return bestList;
    }
}
