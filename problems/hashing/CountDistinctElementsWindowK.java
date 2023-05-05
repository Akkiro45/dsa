package problems.hashing;

import java.util.HashMap;
import java.util.Map;

public class CountDistinctElementsWindowK {
    
    public static void displayCount(int[] nums, int n, int k) {
        Map<Integer, Integer> map = new HashMap<>(k);
        System.out.println("Distinct elements : ");
        for(int i=0; i<n; i++) {
            if(i >= k) {
                int count = map.get(nums[i-k]);
                if(count == 1) {
                    map.remove(nums[i-k]);
                } else {
                    map.put(nums[i-k], count - 1);
                }
            }
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            if(i < k - 1) {
                continue;
            }
            System.out.print(map.size() + " "); 
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 1, 3, 4, 2, 3};
        int n = nums.length;
        int k = 4;
        displayCount(nums, n, k);

        nums = new int[]{1, 2, 4, 4};
        n = nums.length;
        k = 2;
        displayCount(nums, n, k);
    }
}
