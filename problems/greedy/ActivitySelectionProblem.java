package problems.greedy;

import java.util.Arrays;

public class ActivitySelectionProblem {
    
    public static int findMaxActivity(int[][] activities, int n) {
        int activitiesCount = 0;
        int currTime = 0;

        Arrays.sort(activities, (a, b) -> a[1] - b[1]);

        for(int i=0; i<n; i++) {
            if(activities[i][0] >= currTime) {
                activitiesCount++;
                currTime = activities[i][1];
            }
        }

        return activitiesCount;
    }

    public static void main(String[] args) {
        int[][] activities = new int[][]{
            {5, 9},
            {1, 2},
            {3, 4},
            {0, 6},
            {5, 7},
            {8, 9}
        };
        int n = activities.length;
        System.out.println("Max can be performed : " + findMaxActivity(activities, n));
    }
}
