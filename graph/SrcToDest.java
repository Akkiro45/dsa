package graph;

import java.util.LinkedList;
import java.util.Queue;

public class SrcToDest {
    
    public static void main(String[] args) {
        GraphUsingList graph = new GraphUsingList(6);
        graph.generatGraph();

        boolean[] visited = new boolean[graph.v + 1];
        int[] dist = new int[graph.v + 1];
        int[] pred = new int[graph.v + 1];

        int src = 6;
        int dest = 1;

        for(int i=1; i<=graph.v; i++) {
            visited[i] = false;
            dist[i] = Integer.MAX_VALUE;
            pred[i] = -1;
        }

        Queue<Integer> q = new LinkedList<>();
        boolean flag = false;

        q.add(src);
        visited[src] = true;
        dist[src] = 0;

        while(!q.isEmpty()) {
            for(int vertice : graph.adjList.get(q.peek())) {
                if(visited[vertice] == false) {
                    visited[vertice] = true;
                    dist[vertice] = dist[q.peek()] + 1;
                    pred[vertice] = q.peek();
                    if(vertice == dest) {
                        flag = true;
                        break;
                    }
                    q.add(vertice);
                }
            }
            q.poll();
            if(flag == true) {
                break;
            }
        }

        if(flag == true) {
            System.out.println("Found!");
            System.out.println("Distance from src = " + dist[dest]);
            System.out.print("Path : ");
            int i = dest;
            System.out.print(i);
            while(i > 0) {
                i = pred[i];
                if(i == -1) {
                    break;
                }
                System.out.print(" <- " + i);
            }
        } else {
            System.out.println("Not found!");
        }
    }
}
