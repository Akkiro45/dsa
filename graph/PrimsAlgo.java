package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

class Edge implements Comparable<Edge> {
    int parent;
    int w;
    int dest;

    public Edge(int parent, int dest, int w) {
        this.parent = parent;
        this.dest = dest;
        this.w = w;
    }

    @Override
    public int compareTo(Edge edge) {
        return this.w - edge.w;
    }
}

class PrimsUsingPQ {
    boolean[] mst;
    PriorityQueue<Edge> q;
    List<Edge> result;
    WeightedGraph graph;
    
    public PrimsUsingPQ(WeightedGraph graph) {
        this.graph = graph;
        this.mst = new boolean[graph.v];
        q = new PriorityQueue<>();
        result = new ArrayList<>(graph.v - 1);
    }

    public void generateMST() {
        Edge edge = new Edge(-1, 0, 0);
        q.add(edge);

        while(!q.isEmpty()) {
            Edge min = q.poll();

            if(mst[min.dest]) {
                continue;
            }
            mst[min.dest] = true;
            result.add(min);

            for(V neighbour : graph.graph.get(min.dest)) {
                if(!mst[neighbour.dest]) {
                    Edge edge1 = new Edge(min.dest, neighbour.dest, neighbour.w);
                    q.add(edge1);
                }
            }

        }
    }

    public void displayMst() {
        System.out.println("MST");
        for(Edge edge : result) {
            System.out.println(edge.parent + " -> " + edge.dest + "(" + edge.w + ")");
        }
    }
}

class PrimsUsingTS {
    boolean[] mst;
    TreeSet<Edge> q;
    List<Edge> result;
    WeightedGraph graph;
    Edge[] key;
    
    public PrimsUsingTS(WeightedGraph graph) {
        this.graph = graph;
        this.mst = new boolean[graph.v];
        q = new TreeSet<>();
        result = new ArrayList<>(graph.v);
        key = new Edge[graph.v];
        for(int i=0; i<graph.v; i++) {
            if(i == 0) {
                key[i] = new Edge(-1, i, 0);
            } else {
                key[i] = new Edge(-1, i, Integer.MAX_VALUE);
            }
            q.add(key[i]);
        }
    }

    public void generateMST() {
        while(!q.isEmpty()) {
            Edge min = q.pollFirst();

            if(mst[min.dest]) {
                continue;
            }
            mst[min.dest] = true;
            result.add(min);

            for(V neighbour : graph.graph.get(min.dest)) {
                if(!mst[neighbour.dest] && neighbour.w < key[neighbour.dest].w) {
                    q.remove(key[neighbour.dest]);
                    key[neighbour.dest].parent = min.dest;
                    key[neighbour.dest].w = neighbour.w;
                    q.add(key[neighbour.dest]);
                }
            }

        }
    }

    public void displayMst() {
        System.out.println("MST");
        for(Edge edge : result) {
            System.out.println(edge.parent + " -> " + edge.dest + "(" + edge.w + ")");
        }
    }
}

public class PrimsAlgo {
    
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

        PrimsUsingPQ primsUsingPQ = new PrimsUsingPQ(graph);
        primsUsingPQ.generateMST();
        primsUsingPQ.displayMst();

        PrimsUsingTS primsUsingTS = new PrimsUsingTS(graph);
        primsUsingTS.generateMST();
        primsUsingTS.displayMst();
    }
}
