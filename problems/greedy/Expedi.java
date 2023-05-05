package problems.greedy;

import java.util.Arrays;

public class Expedi {

    private static boolean isBetter(int currFuel, int prevFuel, int prevDist, int nextFuel, int nextDist) {
        int fuel = (prevFuel - (nextDist - prevDist)) + nextFuel;
        return (currFuel > fuel);
    }
    
    public static int findMinStopToReach(int[][] stations, int n, int initialFuel, int dest) {
        int flag = 0;
        int stops = 0;
        int currPos = -1, currFuel = initialFuel, currDist = 0;
        int prevPos, prevFuel, prevDist;
        int i = 0;
        int[][] fuelStations = new int[n][2];

        for(i=0; i<n; i++) {
            fuelStations[i][0] = dest - stations[i][0];
            fuelStations[i][1] = stations[i][1];
        }

        Arrays.sort(fuelStations, (a, b) -> a[0] - b[0]);

        do {
            prevPos = currPos;
            prevFuel = currFuel;
            prevDist = currDist;
            i = prevPos + 1;
            stops++;
            while(i < n && (prevFuel - (fuelStations[i][0] - prevDist)) >= 0) {
                if((i == (prevPos + 1)) || (isBetter(currFuel, prevFuel, prevDist, fuelStations[i][1], fuelStations[i][0]))) {
                    currPos = i;
                    currDist = prevDist + fuelStations[i][0];
                    currFuel = (prevFuel - (fuelStations[i][0] - prevDist)) + fuelStations[i][1]; 
                }
                if((currDist + currFuel) >= dest) {
                    flag = 1;
                    break;
                }
                i++;
            }
            if(flag == 1) {
                break;
            }
            if(i == n) {
                break;
            }
        } while(currPos < n);
        
        return flag == 1 ? stops : -1;
    }

    public static void main(String[] args) {
        int[][] stations = new int[][]{
            {4, 4}, {5, 2}, {11, 5}, {15, 10}
        };
        int n = stations.length;
        int initialFuel = 10;
        int dest = 25;
        System.out.println("Minimum stop required : " + findMinStopToReach(stations, n, initialFuel, dest));
    }
}
