package graph;

import java.util.LinkedList;
import java.util.Queue;

// Kahn's Algorithm
public class TopologicalSortingUsingBFS {
    public static void main(String[] args) {
        GraphUsingList graph = new GraphUsingList(6);
        graph.addDirectedEdge(0, 2);
        graph.addDirectedEdge(0, 3);
        graph.addDirectedEdge(2, 3);
        graph.addDirectedEdge(2, 1);
        graph.addDirectedEdge(3, 1);
        graph.addDirectedEdge(1, 4);
        graph.addDirectedEdge(5, 1);
        graph.addDirectedEdge(5, 4);

        int[] indegree = new int[graph.v];
        Queue<Integer> q = new LinkedList<>();

        for(int i=0; i<graph.v; i++) {
            for(int neighbor : graph.adjList.get(i)) {
                indegree[neighbor]++;
            }
        }

        for(int i=0; i<graph.v; i++) {
            if(indegree[i] == 0) {
                q.add(i);
            }
        }
        
        System.out.println("Topological order : ");

        while(!q.isEmpty()) {
            System.out.print(q.peek() + " ");
            for(int neighbor : graph.adjList.get(q.peek())) {
                indegree[neighbor]--;
                if(indegree[neighbor] == 0) {
                    q.add(neighbor);
                }
            }
            q.poll();
        }

    }
}
