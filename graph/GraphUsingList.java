package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class GraphUsingList {
    
    int v;
    List<List<Integer>> adjList;

    public GraphUsingList(int v) {
        this.v = v;
        adjList = new ArrayList<>(v+1);
        for(int i=0; i<=v; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int src, int dest) {
        adjList.get(src).add(dest);
        adjList.get(dest).add(src);
    }

    public void addDirectedEdge(int src, int dest) {
        adjList.get(src).add(dest);
    }

    public void display() {
        System.out.println("Adjacency List");
        for(int i=1; i<=v; i++) {
            for(Integer vertice : adjList.get(i)) {
                System.out.print(vertice + " ");
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
        while(!q.isEmpty()) {
            System.out.print(q.peek() + " ");
            for(int vertice : adjList.get(q.peek())) {
                if(visited[vertice] == false) {
                    visited[vertice] = true;
                    q.add(vertice);
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

        for(int neighbour : adjList.get(v)) {
            if(visited[neighbour] != true) {
                printDFS(neighbour, visited);
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
            for(int neighbour : adjList.get(curr)) {
                if(visited[neighbour] != true) {
                    visited[neighbour] = true;
                    s.push(neighbour);
                }
            }
        }
    }

    public static void main(String[] args) {
        GraphUsingList graph = new GraphUsingList(6);
        graph.generatGraph();
        graph.display();
        graph.printBFS();
        graph.printDFS(1);
        graph.printDFSUsingStack(1);
    }
}
