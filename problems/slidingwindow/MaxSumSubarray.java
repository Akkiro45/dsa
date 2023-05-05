package problems.slidingwindow;

public class MaxSumSubarray {

    public static int find(int[] nums, int n, int x, int k) {
        int sum = 0;
        int start = 0;
        int end = 0;
        int maxSum = 0;

        for(int i=0; i<n; i++) {
            sum += nums[i];
            if(sum > x) {
                sum -= nums[start];
                start++;
            }
            if((end - start + 1) == k) {
                maxSum = Math.max(maxSum, sum);
                sum -= nums[start];
                start++;
            }
            end++;
        }

        return maxSum;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{7, 5, 4, 6, 8, 9};
        int n = nums.length;
        int x = 20;
        int k = 3;
        System.out.println("Maxium sum of subarray of size k : " + 
            find(nums, n, x, k));
    }
}
