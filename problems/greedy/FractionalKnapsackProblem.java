package problems.greedy;

import java.util.Arrays;

public class FractionalKnapsackProblem {
    
    public static double findMaxValue(int[][] iteams, int n, int weight) {
        double currVal = 0;
        int currWeight = 0;

        Arrays.sort(iteams, (iteam1, iteam2) -> {
            if(((double)iteam2[0] / (double)iteam2[1]) > ((double)iteam1[0] / (double)iteam1[1])) {
                return 1;
            } else {
                return -1;
            }
            // return ((iteam2[0] / iteam2[1]) - (iteam1[0] / iteam1[1]));
        });

        for(int i=0; i<n; i++) {
            if((currWeight + iteams[i][1]) <= weight) {
                currVal += iteams[i][0];
                currWeight += iteams[i][1];
            } else {
                currVal += (((double)iteams[i][0] / (double)iteams[i][1]) * (weight - currWeight));
                break;
            }
        }

        return currVal;
    }

    public static void main(String[] args) {
        // Items as (value, weight) pairs 
        int[][] iteams = new int[][]{
            {60, 10}, {100, 20}, {120, 30}
        };
        int n = iteams.length;
        int weight = 50;
        System.out.println("Maximum possible value = " + findMaxValue(iteams, n, weight));

        iteams = new int[][]{
            {500, 30}
        };
        n = iteams.length;
        weight = 10;
        System.out.println("Maximum possible value = " + findMaxValue(iteams, n, weight));
    }
}
