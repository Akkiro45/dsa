package problems.dptabulation;

import java.util.Arrays;

public class MaximumSumSubMatrix {

    public static int findMaxSubMatSum(int[][] mat, int n, int m) {
        int maxSum = Integer.MIN_VALUE;
        int[] sum = new int[n];

        for(int colStart=0; colStart<m; colStart++) {
            Arrays.fill(sum, 0);
            for(int colEnd=colStart; colEnd<m; colEnd++) {
                for(int row=0; row<n; row++) {
                    sum[row] += mat[row][colEnd];
                }
                maxSum = Math.max(maxSum, kadans(sum));
            }
        }

        return maxSum;
    }

    public static int kadans(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int currSum = 0;

        for(int i=0; i<nums.length; i++) {
            currSum += nums[i];
            maxSum = Math.max(maxSum, currSum);
            if(currSum < 0) {
                currSum = 0;
            }
        }

        return maxSum;
    }

    public static void main(String[] args) {
        int[][] mat = new int[][]{
            { 0, -2, -7, 0 },
            { 9, 2, -6, 2 },
            { -4, 1, -4, 1 },
            { -1, 8, 0, -2 }
        };
        System.out.println(findMaxSubMatSum(mat, mat.length, mat[0].length));
    }
}
