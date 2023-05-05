package problems.priorityqueue;

import java.util.Collections;
import java.util.PriorityQueue;

public class SmallestSequenceWithSumK {
    
    public static int getSmallestSequenceSize(int[] nums, int n, int k) {
        int res = 0;
        PriorityQueue<Integer> q = new PriorityQueue<>(n, Collections.reverseOrder());
        int sum = 0;

        for(int num : nums) {
            q.add(num);
        }

        while(!q.isEmpty() && sum < k) {
            res++;
            sum += q.poll();
        }

        return sum < k ? -1 : res;
    }
    
    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 1, 5, 6, 3, 7, 9, 14, 10, 2, 5};
        int n = nums.length;
        int k = 35;
        System.out.println("Smallest Sequence Size : " + getSmallestSequenceSize(nums, n, k));

        nums = new int[]{1, 2, 2, 2, 3, 4, 5, 4, 7, 6, 5, 12};
        n = nums.length;
        k = 70;
        System.out.println("Smallest Sequence Size : " + getSmallestSequenceSize(nums, n, k));
    }
}
