package problems.hashing;

import java.util.HashMap;
import java.util.Map;

public class SubarrayWithGivenSumK {
    
    public static void display(int[] nums, int n, int k) {
        Map<Integer, Integer> map = new HashMap<>(n);
        int sum = 0;

        map.put(0, -1);

        for(int i=0; i<n; i++) {
            sum += nums[i];

            if(map.containsKey(sum - k)) {
                System.out.println("Found : " + (map.get(sum - k) + 1) + ", " + i);
                return;
            }
            map.put(sum, i);
        }

        System.out.println("Not found!");
    }
    
    public static void main(String[] args) {
        int[] nums = new int[]{1, 4, 20, 3, 10, 5};
        int n = nums.length;
        int k = 33;
        display(nums, n, k);

        nums = new int[]{10, 2, -2, -20, 10};
        n = nums.length;
        k = -10;
        display(nums, n, k);

        nums = new int[]{-10, 0, 2, -2, -20, 10};
        n = nums.length;
        k = 20;
        display(nums, n, k);
    }
}
