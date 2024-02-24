public class UnionFind {
    private int[] id;
    private int[] sz;

    public UnionFind(int N) {
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1; // Initialize the size of each component to 1
        }
    }

    private int root(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]]; // Path compression by halving
            i = id[i];
        }
        return i;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (i == j)
            return; // Already connected
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }
    }
}