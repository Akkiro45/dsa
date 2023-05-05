package graph;

import java.util.LinkedList;
import java.util.Queue;

public class CountComponents {
    
    public static void main(String[] args) {
        GraphUsingList graph = new GraphUsingList(9);
        graph.generatGraph();
        graph.addEdge(7, 8);

        int components = 0;
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[graph.v + 1];

        for(int i=1; i<=graph.v; i++) {
            if(!visited[i]) {
                components++;
                
                // BFS
                visited[i] = true;
                q.add(i);
                while(!q.isEmpty()) {
                    for(int neighbour : graph.adjList.get(q.peek())) {
                        if(!visited[neighbour]) {
                            visited[neighbour] = true;
                            q.add(neighbour);
                        }
                    }
                    q.poll();
                }
            }
        }

        System.out.println("Number of components : " + components);
    }
}
