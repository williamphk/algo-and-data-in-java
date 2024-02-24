public class LargestElement {
    private int[] id;
    private int[] size;
    private int[] largest;

    public LargestElement(int n) {
        id = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
            size[i] = 1;
            largest[i] = i;
        }
    }

    private int root(int i) {
        while (i != id[i]) {
            i = id[i];
        }
        return i;
    }

    private int find(int i) {
        return largest[root(i)];
    }

    public void union(int p, int q) {
        int rootP = root(p);
        int rootQ = root(q);
        if (rootP == rootQ)
            return;

        if (size[rootP] < size[rootQ]) {
            id[rootP] = rootQ;
            size[rootQ] += size[rootP];
            largest[rootQ] = Math.max(largest[rootP], largest[rootQ]);
        } else {
            id[rootQ] = rootP;
            size[rootP] += size[rootQ];
            largest[rootP] = Math.max(largest[rootP], largest[rootQ]);
        }
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }
}
