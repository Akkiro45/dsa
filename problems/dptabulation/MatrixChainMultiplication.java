package problems.dptabulation;

import java.util.Arrays;

public class MatrixChainMultiplication {

    public static int topdown(int[] arr, int i, int j, int[][] dp) {
        if((j - i) <= 1) {
            return 0;
        }
        if(dp[i][j] != -1) {
            return dp[i][j];
        }

        int min = Integer.MAX_VALUE;

        for(int k=i+1; k<j; k++) {
            min = Math.min(min, (
                topdown(arr, i, k, dp) + topdown(arr, k, j, dp) + (arr[i] * arr[k] * arr[j])
            ));
        }

        dp[i][j] = min;
        return min;
    }

    public static int bottomup(int[] arr, int n, int[][] dp) {
        for(int m=2; m<n; m++) {
            for(int i=0, j = m; i<n-m; i++, j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for(int k=i+1; k<j; k++) {
                    dp[i][j] = Math.min(dp[i][j], (
                        dp[i][k] + dp[k][j] + (arr[i] * arr[k] * arr[j])
                    ));
                }
            }
        }

        return dp[0][n-1];
    }

    public static void main(String[] args) {
        int[] arr = new int[]{40, 20, 30, 10, 30};
        int n = arr.length;
        int[][] dp = new int[n][n];
        for(int[] row : dp) {
            Arrays.fill(row, -1);
        }
        System.out.println(topdown(arr, 0, n-1, dp));
        dp = new int[n][n];
        for(int[] row : dp) {
            Arrays.fill(row, 0);
        }
        System.out.println(bottomup(arr, n, dp));
    }
}
