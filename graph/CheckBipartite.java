package graph;

import java.util.LinkedList;
import java.util.Queue;

public class CheckBipartite {

    public static void main(String[] args) {
        GraphUsingMatrix graph = new GraphUsingMatrix(4);
        graph.addEdge(0, 1);
        graph.addEdge(2, 1);
        graph.addEdge(2, 3);
        graph.addEdge(4, 3);

        Queue<Integer> q = new LinkedList<>();
        int[] color = new int[graph.v];
        boolean bipartite = true;

        q.add(0);
        color[0] = 1;

        while(!q.isEmpty()) {
            int curr = q.poll();
            for(int i=0; i<graph.v; i++) {
                if(graph.adjMat[curr][i] != 0) {
                    if(color[i] == 0) {
                        color[i] = color[curr] == 0 ? 1 : 0;
                        q.add(i);
                    } else if(color[i] == color[curr]) {
                        bipartite = false;
                        break;
                    }
                }
            }
            if(!bipartite) {
                break;
            }
        }

        if(bipartite) {
            System.out.println("Graph is bipartite!");
        } else {
            System.out.println("Graph is not bipartite!");
        }
    }
}
