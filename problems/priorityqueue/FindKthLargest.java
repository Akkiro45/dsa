package problems.priorityqueue;

import java.util.PriorityQueue;

public class FindKthLargest {

    public static int find(int[] nums, int n, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<>(k);

        for(int i=0; i<n; i++) {
            if(i < k) {
                q.add(nums[i]);
                continue;
            }
            if(q.peek() < nums[i]) {
                q.poll();
                q.add(nums[i]);
            }
        }

        return q.peek();
    }

    public static void main(String[] args) {
        int[] nums = new  int[]{7, 10, 4, 3, 20, 15};
        int n = nums.length;
        int k = 3;
        System.out.println("Kth Largest : " + find(nums, n, k));

        nums = new int[]{7, 10, 4, 3, 20, 15};
        n = nums.length;
        k = 4;
        System.out.println("Kth Largest : " + find(nums, n, k));
    }
}
