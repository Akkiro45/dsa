package problems.graph;

import java.util.ArrayList;
import java.util.List;

import graph.GraphUsingMatrix;

public class WaysToChooseFriends {

    private static int dfs(int[][] adjMat, int curr, int v, boolean[] visited) {
        int size = 1;
        visited[curr] = true;

        for(int i=0; i<v; i++) {
            if(adjMat[curr][i] != 0 && !visited[i]) {
                size += dfs(adjMat, i, v, visited);
            }
        }

        return size;
    }
    
    public static void main(String[] args) {
        int v = 5;
        GraphUsingMatrix graph = new GraphUsingMatrix(v);
        graph.addEdge(0, 1);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);

        List<Integer> groups = new ArrayList<>();
        boolean[] visited = new boolean[v];

        for(int i=0; i<v; i++) {
            if(!visited[i]) {
                groups.add(dfs(graph.adjMat, i, v, visited));
            }
        }

        int ways = 0;
        for(int group : groups) {
            ways += (group * (v - group));
        }
        ways /= 2;
        System.out.println("Ways we choose 2 friends from diffrent groups : " + ways);
    }
}
