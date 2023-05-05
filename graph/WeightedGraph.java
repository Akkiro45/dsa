package graph;

import java.util.ArrayList;
import java.util.List;

class V {
    int src;
    int dest;
    int w;

    public V(int dest, int w) {
        this.dest = dest;
        this.w = w;
    }

    public V(int src, int dest, int w) {
        this.dest = dest;
        this.w = w;
    }
}

public class WeightedGraph {
    int v;
    List<List<V>> graph;

    public WeightedGraph(int v) {
        this.v = v;
        graph = new ArrayList<>(v);
        for(int i=0; i<v; i++) {
            graph.add(new ArrayList<>());
        }
    }

    public void addEdge(int src, int dest, int w) {
        V srcNode = new V(dest, w);
        graph.get(src).add(srcNode);
        V destNode = new V(src, w);
        graph.get(dest).add(destNode);
    }

    public void addDirectedEdge(int src, int dest, int w) {
        V srcNode = new V(dest, w);
        graph.get(src).add(srcNode);
    }

    public void displayGraph() {
        System.out.println("Graph");
        for(int i=0; i<v; i++) {
            for(V node : graph.get(i)) {
                System.out.print(node.dest + "(" + node.w + ") ");
            }
            System.out.println();
        }
    }
}
