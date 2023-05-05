package graph;

import java.util.List;

public class CycleDetactionInDirectedGraph {

    public static boolean dfs(List<List<Integer>> adjList, boolean[] visited, boolean[] recS, int curr) {
        visited[curr] = true;
        recS[curr] = true;

        for(int neighbor : adjList.get(curr)) {
            if(!visited[neighbor]) {
                if(dfs(adjList, visited, recS, neighbor)) {
                    return true;
                }
            } else if(recS[neighbor]) {
                return true;
            }
        }

        recS[curr] = false;
        return false;
    }
    
    public static void main(String[] args) {
        GraphUsingList graph = new GraphUsingList(5);
        graph.addDirectedEdge(0, 1);
        graph.addDirectedEdge(2, 1);
        graph.addDirectedEdge(2, 3);
        graph.addDirectedEdge(3, 4);
        graph.addDirectedEdge(4, 2);

        boolean[] visited = new boolean[graph.v];
        boolean[] recS = new boolean[graph.v];
        for(int i=0; i<graph.v; i++) {
            if(!visited[i]) {
                if(dfs(graph.adjList, visited, recS, i)) {
                    System.out.println("Cycle present!");
                    break;
                }
            }
        }
    }
}
