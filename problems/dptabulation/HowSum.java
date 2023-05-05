package problems.dptabulation;

import java.util.ArrayList;
import java.util.List;

public class HowSum {
    public static void main(String[] args) {
        // Test Case 1
        int[] nums1 = new int[]{2, 3};
        System.out.println(howSum(7, nums1));

        // Test Case 2
        int[] nums2 = new int[]{7, 14};
        System.out.println(howSum(300, nums2));
    }

    private static List<Integer> howSum(int target, int[] nums) {
        List<List<Integer>> table = new ArrayList<>();

        for(int i=0; i<=target; i++) {
            table.add(null);
        }
        
        table.set(0, new ArrayList<>());

        for(int i=0; i<=target; i++) {
            List<Integer> curr = table.get(i);
            if(curr != null) {
                for(int num : nums) {
                    if(i+num <= target) {
                        List<Integer> temp = new ArrayList<>();
                        temp.addAll(curr);
                        temp.add(num);
                        table.set(i+num, temp);
                    }
                }
            }
        }

        return table.get(target);
    }
}
