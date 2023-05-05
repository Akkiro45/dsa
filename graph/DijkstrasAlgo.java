package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

class Path implements Comparable<Path> {
    int parent;
    int dest;
    int w;

    public Path(int parent, int dest, int w) {
        this.parent = parent;
        this.dest = dest;
        this.w = w;
    }

    @Override
    public int compareTo(Path path) {
        return this.w - path.w;
    }
}

class DijkstrasUsingTS {
    boolean[] visited;
    TreeSet<Path> q;
    Path[] table;
    WeightedGraph graph;
    List<Path> res;
    int source;

    public DijkstrasUsingTS(WeightedGraph graph, int source) {
        this.graph = graph;
        visited = new boolean[graph.v];
        q = new TreeSet<>();
        table = new Path[graph.v];
        res = new ArrayList<>(graph.v);
        this.source = source;
        for(int i=0; i<graph.v; i++) {
            if(i == source) {
                table[source] = new Path(-1, source, 0);
            } else {
                table[i] = new Path(-1, i, Integer.MAX_VALUE);
            }
            q.add(table[i]);
        }
    }

    public void generatePath() {
        while(!q.isEmpty()) {
            Path min = q.pollFirst();

            if(visited[min.dest]) {
                continue;
            }
            visited[min.dest] = true;
            res.add(min);

            for(V neighbour : graph.graph.get(min.dest)) {
                if(!visited[neighbour.dest] && (neighbour.w + min.w) < table[neighbour.dest].w) {
                    q.remove(table[neighbour.dest]);
                    table[neighbour.dest].w = neighbour.w + min.w;
                    table[neighbour.dest].parent = min.dest;
                    q.add(table[neighbour.dest]);
                }
            }
        }
    }

    public void displayPath() {
        System.out.println("Path");
        for(Path path : res) {
            System.out.println(path.parent + " -> " + path.dest + "(" + path.w + ")");
        }
    }
}

public class DijkstrasAlgo {
    
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

        DijkstrasUsingTS dijkstrasUsingTS = new DijkstrasUsingTS(graph, 0);
        dijkstrasUsingTS.generatePath();
        dijkstrasUsingTS.displayPath();
    }
}
