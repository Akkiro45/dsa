package graph;

public class UnionFind {
    private int size;
    private int[] sz;
    private int[] p;
    private int numComponents;

    public UnionFind(int size) {
        this.size = size;
        this.sz = new int[size];
        this.p = new int[size];
        this.numComponents = size;
        for(int i=0; i<size; i++) {
            this.p[i] = i;
            this.sz[i] = 1;
        }
    }

    public int find(int x) {
        if(p[x] == x) {
            return x;
        }

        p[x] = find(p[x]);
        return p[x];
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if(rootX == rootY) {
            return;
        }

        if(sz[rootX] > sz[rootY]) {
            p[rootY] = rootX;
            sz[rootX] += sz[rootY];
            sz[rootY] = 0;
        } else {
            p[rootX] = rootY;
            sz[rootY] += sz[rootX];
            sz[rootX] = 0;
        }
        numComponents--;
    }

    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }

    public int componentSize(int x) {
        return sz[find(x)];
    }

    public int size() {
        return size;
    }

    public int components() {
        return numComponents;
    }

    public static void main(String[] args) {
        UnionFind q = new UnionFind(5);
        System.out.println(q.find(4));
        q.union(1, 4);
        System.out.println(q.size());
        System.out.println(q.componentSize(4));
        System.out.println(q.components());
        System.out.println(q.connected(1, 2));
    }
}
