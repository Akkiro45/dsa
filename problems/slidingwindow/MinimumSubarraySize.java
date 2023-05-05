package problems.slidingwindow;

public class MinimumSubarraySize {

    public static int find(int[] nums, int n, int x) {
        int minSize = Integer.MAX_VALUE;
        int start = 0;
        int end = 0;
        int sum = 0;

        for(int i=0; i<n; i++) {
            sum += nums[i];
            if(sum <= x) {
                end++;
                continue;
            }

            do {
                minSize = Math.min(minSize, (end-start+1));
                sum -= nums[start];
                start++;
            } while(start <= end && sum > x);
            end++;
        }

        return minSize;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 4, 45, 6, 10, 19};
        int n = nums.length;
        int x = 51;

        System.out.println("Minimum size of subarray having sum more than X : " + 
            find(nums, n, x));
    }
}
