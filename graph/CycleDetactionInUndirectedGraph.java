package graph;

import java.util.List;

public class CycleDetactionInUndirectedGraph {

    public static boolean dfs(List<List<Integer>> adjList, boolean[] visited, int curr, int parent) {
        visited[curr] = true;

        for(int neighbor : adjList.get(curr)) {
            if(!visited[neighbor]) {
                if(dfs(adjList, visited, neighbor, curr)) {
                    return true;
                }
            } else if(neighbor != parent) {
                return true;
            }
        }

        return false;
    }
    
    public static void main(String[] args) {
        GraphUsingList graph = new GraphUsingList(6);
        graph.generatGraph();

        boolean[] visited = new boolean[graph.v + 1];
        for(int i=1; i<=graph.v; i++) {
            if(!visited[i]) {
                if(dfs(graph.adjList, visited, i, -1)) {
                    System.out.println("Cycle present!");
                    break;
                }
            }
        }
    }
}
