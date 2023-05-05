package graph;

public class BellmanFordAlgo {
    
    public static void main(String[] args) {
        WeightedGraph graph = new WeightedGraph(5);
        graph.addDirectedEdge(0, 1, -1);
        graph.addDirectedEdge(0, 2, 4);
        graph.addDirectedEdge(1, 2, 3);
        graph.addDirectedEdge(1, 3, 2);
        graph.addDirectedEdge(1, 4, 2);
        graph.addDirectedEdge(3, 2, 5);
        graph.addDirectedEdge(3, 1, 1);
        graph.addDirectedEdge(4, 3, -3);
        graph.displayGraph();

        int[] dist = new int[graph.v];

        for(int i=0; i<graph.v; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        dist[0] = 0;

        for(int i=1; i<graph.v; i++) {
            for(int j=0; j<graph.v; j++) {
                for(V neighbour : graph.graph.get(j)) {
                    if(dist[j] != Integer.MAX_VALUE && (dist[j] + neighbour.w) < dist[neighbour.dest]) {
                        dist[neighbour.dest] = dist[j] + neighbour.w;
                    }
                }
            }
        }
        // To check if negative cycle is present or not
        boolean negativeCycle = false;
        for(int j=0; j<graph.v; j++) {
            for(V neighbour : graph.graph.get(j)) {
                if(dist[j] != Integer.MAX_VALUE && (dist[j] + neighbour.w) < dist[neighbour.dest]) {
                    negativeCycle = true;
                    break;
                }
            }
            if(negativeCycle) {
                break;
            }
        }
        if(negativeCycle) {
            System.out.println("Negative cycle is present!");
        } else {
            System.out.println("Vertex\tDistance from Source");
            for(int i=0; i<graph.v; i++) {
                System.out.println(i + "\t" + dist[i]);
            }
        }
    }
}
