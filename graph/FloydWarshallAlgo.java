package graph;

public class FloydWarshallAlgo {
    public static void main(String[] args) {
        GraphUsingMatrix graph = new GraphUsingMatrix(4);
        graph.addDirectedWeightedEdge(0, 1, 5);
        graph.addDirectedWeightedEdge(0, 3, 10);
        graph.addDirectedWeightedEdge(1, 2, 3);
        graph.addDirectedWeightedEdge(2, 3, 1);

        int[][] sol = new int[graph.v][graph.v];
        for(int i=0; i<graph.v; i++) {
            for(int j=0; j<graph.v; j++) {
                if(i == j) {
                    sol[i][j] = 0;
                } else if(graph.adjMat[i][j] == 0) {
                    sol[i][j] = Integer.MAX_VALUE;
                } else {
                    sol[i][j] = graph.adjMat[i][j];
                }
            }
        }

        for(int k=0; k<graph.v; k++) {
            for(int i=0; i<graph.v; i++) {
                for(int j=0; j<graph.v; j++) {
                    if(
                        sol[i][k] != Integer.MAX_VALUE && 
                        sol[k][j] != Integer.MAX_VALUE && 
                        (sol[i][j] > (sol[i][k] + sol[k][j]))
                    ) {
                        sol[i][j] = sol[i][k] + sol[k][j];
                    }
                }
            }
        }

        for(int i=0; i<graph.v; i++) {
            for(int j=0; j<graph.v; j++) {
                System.out.print(sol[i][j] + " ");
            }
            System.out.println();
        }
    }
}
