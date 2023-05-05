package graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class GraphUsingMatrix {
    
    public int v;
    public int[][] adjMat;

    public GraphUsingMatrix(int v) {
        this.v = v;
        adjMat = new int[v+1][v+1];
    }

    public void addEdge(int source, int dest) {
        adjMat[source][dest] = 1;
        adjMat[dest][source] = 1;
    }

    public void addWeightedEdge(int source, int dest, int weight) {
        adjMat[source][dest] = weight;
        adjMat[dest][source] = weight;
    }

    public void addDirectedWeightedEdge(int source, int dest, int weight) {
        adjMat[source][dest] = weight;
    }

    public void display() {
        System.out.println("Adjacency matrix");
        for(int i=1; i<=v; i++) {
            for(int j=1; j<=v; j++) {
                System.out.print(adjMat[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void printBFS() {
        System.out.println("Breadth First Search");
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[v+1];

        q.add(1);
        visited[1] = true;
        System.out.print(1 + " ");
        while(!q.isEmpty()) {
            for(int i=1; i<=v; i++) {
                if(adjMat[q.peek()][i] == 1 && visited[i] == false) {
                    System.out.print(i + " ");
                    visited[i] = true;
                    q.add(i);
                }
            }
            q.poll();
        }
    }

    public void generatGraph() {
        this.addEdge(1, 2);
        this.addEdge(1, 3);
        this.addEdge(1, 5);
        this.addEdge(2, 5);
        this.addEdge(3, 4);
        this.addEdge(4, 5);
        this.addEdge(4, 6);
        this.addEdge(5, 6);
    }

    public void printDFS(int v) {
        boolean[] visited = new boolean[this.v+1];
        System.out.println("\nDepth First Search : ");
        printDFS(v, visited);
    }

    public void printDFS(int v, boolean[] visited) {
        visited[v] = true;
        System.out.print(v + " ");

        for(int i=1; i<=this.v; i++) {
            if(adjMat[v][i] == 1 && visited[i] != true) {
                printDFS(i, visited);
            }
        }
    }

    public void printDFSUsingStack(int v) {
        System.out.println("\nDepth First Search : ");
        boolean[] visited = new boolean[this.v+1];
        Stack<Integer> s = new Stack<>();

        visited[v] = true;
        s.push(v);
        
        while(!s.isEmpty()) {
            int curr = s.pop();
            System.out.print(curr + " ");
            for(int i=1; i<=this.v; i++) {
                if(adjMat[curr][i] == 1 && visited[i] != true) {
                    visited[i] = true;
                    s.push(i);
                }
            }
        }
    }

    public static void main(String[] args) {
        GraphUsingMatrix graph = new GraphUsingMatrix(6);
        graph.generatGraph();
        graph.display();
        graph.printBFS();
        graph.printDFS(1);
        graph.printDFSUsingStack(1);
    }
}
