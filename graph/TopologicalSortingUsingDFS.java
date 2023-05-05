package graph;

import java.util.List;
import java.util.Stack;

public class TopologicalSortingUsingDFS {

    public static void dfs(List<List<Integer>> adjList, boolean[] visited, Stack<Integer> s, int curr) {
        visited[curr] = true;

        for(int neighbor : adjList.get(curr)) {
            if(!visited[neighbor]) {
                dfs(adjList, visited, s, neighbor);
            }
        }

        s.push(curr);
    }

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

        Stack<Integer> s = new Stack<>();
        boolean[] visited = new boolean[graph.v];
        for(int i=0; i<graph.v; i++) {
            if(!visited[i]) {
                dfs(graph.adjList, visited, s, i);
            }
        }

        System.out.println("Topological order : ");
        while(!s.isEmpty()) {
            System.out.print(s.pop() + " ");
        }
    }
}
