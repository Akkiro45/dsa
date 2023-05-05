package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class VEdge implements Comparable<VEdge> {
    int src;
    int dest;
    int w;

    public VEdge(int src, int dest, int w) {
        this.src = src;
        this.dest = dest;
        this.w = w;
    }

    @Override
    public int compareTo(VEdge edge) {
        return this.w - edge.w;
    }
}

public class KruskalsAlgo {

    private int v;
    private WeightedGraph graph;
    private List<VEdge> edges;
    private List<VEdge> res;
    private UnionFind uf;
    private Map<String, Boolean> map;

    public KruskalsAlgo(WeightedGraph graph) {
        this.graph = graph;
        this.v = graph.v;
        this.edges = new ArrayList<>();
        this.res = new ArrayList<>(v-1);
        this.uf = new UnionFind(v);
        this.map = new HashMap<>();
    }

    public void generateMST() {
        for(int i=0; i<v; i++) {
            for(V neighbour : graph.graph.get(i)) {
                // edges.add(new VEdge(i, neighbour.dest, neighbour.w));
                String key1 = i + "-" + neighbour.dest;
                String key2 = neighbour.dest + "-" + i;
                if(!map.getOrDefault(key1, false) || !map.getOrDefault(key2, false)) {
                    edges.add(new VEdge(i, neighbour.dest, neighbour.w));
                    map.put(key1, true);
                    map.put(key2, true);
                }
            }
        }
        
        Collections.sort(edges);

        int includedEdge = 0;
        int i=0;
        while(includedEdge != v-1 && i < edges.size()) {
            VEdge edge = edges.get(i);
            if(!uf.connected(edge.src, edge.dest)) {
                res.add(edge);
                uf.union(edge.src, edge.dest);
                includedEdge++;
            }
            i++;
        }
        
    }

    public void displayMst() {
        System.out.println("MST");
        for(VEdge edge : res) {
            System.out.println(edge.src + " -> " + edge.dest + "(" + edge.w + ")");
        }
    }

    public static void main(String[] args) {
        WeightedGraph graph = new WeightedGraph(5);
        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 3, 6);
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 3, 8);
        graph.addEdge(1, 4, 5);
        graph.addEdge(2, 4, 7);
        graph.addEdge(3, 4, 9);
        graph.displayGraph();

        KruskalsAlgo kruskalsAlgo = new KruskalsAlgo(graph);
        kruskalsAlgo.generateMST();
        kruskalsAlgo.displayMst();
    }
}
