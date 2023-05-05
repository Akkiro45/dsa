package problems.priorityqueue;

import java.util.PriorityQueue;

public class ConnectNRopesWithMinimumCost {
    
    public static int getMinCost(int[] ropes, int n) {
        int cost = 0;
        int currCost = 0;

        PriorityQueue<Integer> q = new PriorityQueue<>(n);

        for(int rope : ropes) {
            q.add(rope);
        }

        while(!q.isEmpty()) {
            currCost = q.poll();
            if(!q.isEmpty()) {
                currCost += q.poll();
            }
            cost += currCost;
            if(!q.isEmpty()) {
                q.add(currCost);
            }
        }

        return cost;
    }

    public static void main(String[] args) {
        int[] ropes = new int[]{4, 3, 2, 6};
        int n = ropes.length;
        System.out.println("Min cost : " + getMinCost(ropes, n));

        ropes = new int[]{1, 2, 3};
        n = ropes.length;
        System.out.println("Min cost : " + getMinCost(ropes, n));
    }
}
