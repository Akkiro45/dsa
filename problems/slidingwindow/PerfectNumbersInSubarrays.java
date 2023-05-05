package problems.slidingwindow;

public class PerfectNumbersInSubarrays {

    public static boolean isPerfectNumber(int num) {
        int sum = 1;

        for(int i=2; i<Math.sqrt(num); i++) {
            if(num % i == 0) {
                sum += i;
                if (i != num / i) {
                    sum += num / i;
                }
            }
        }

        return (num != 1 && num == sum);
    }

    public static int maxSubArray(int[] nums, int n, int k) {
        int maxSize = -1;
        boolean[] pNums = new boolean[n];
        int start = 0;
        int subArrayCount = 0;

        for(int i=0; i<n; i++) {
            pNums[i] = isPerfectNumber(nums[i]);
            subArrayCount += pNums[i] == true ? 1 : 0;

            if((i - start + 1) != k) {
                continue;
            }
            maxSize = Math.max(maxSize, subArrayCount);
            if(pNums[start] == true) {
                subArrayCount--;
            }
            start++;
        }

        return maxSize;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{28, 2, 3, 6, 496, 99, 8128, 24};
        int n = nums.length;
        int k = 4;
        System.out.println("Max size : " + maxSubArray(nums, n, k));

        nums = new int[]{1, 2, 3, 6};
        n = nums.length;
        k = 2;
        System.out.println("Max size : " + maxSubArray(nums, n, k));
    }
}
