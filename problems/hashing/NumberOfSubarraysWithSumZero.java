package problems.hashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberOfSubarraysWithSumZero {

    public static void displayAubAaraysWithSumK(int[] nums, int n, int k) {
        int sum = 0;
        Map<Integer, List<Integer>> map = new HashMap<>();

        List<Integer> list = new ArrayList<>();
        list.add(-1);
        map.put(0, list);

        for(int i=0; i<n; i++) {
            sum += nums[i];
            if(map.containsKey(sum - k)) {
                for(Integer ind : map.get(sum - k)) {
                    System.out.println((ind + 1) + " : " + i);
                }
            }
            list = map.getOrDefault(sum, new ArrayList<>());
            list.add(i);
            map.put(sum, list);
        }
    }

    public static int countSubAaraysWithSumK(int[] nums, int n, int k) {
        int res = 0;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();

        map.put(0, 1);

        for(int i=0; i<n; i++) {
            sum += nums[i];

            if(map.containsKey(sum - k)) {
                res += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{6, 3, -1, -3, 4, -2, 2, 4, 6, -12, -7};
        int n = nums.length;
        int k = 0;
        displayAubAaraysWithSumK(nums, n, k);
        System.out.println("Subbarray count : " + countSubAaraysWithSumK(nums, n, k));
    }
}
