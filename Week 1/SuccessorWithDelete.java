public class SuccessorWithDelete {
    private int[] parent;
    private int[] size;
    private int[] successor;
    private boolean[] removed;

    public SuccessorWithDelete(int n) {
        parent = new int[n];
        size = new int[n];
        successor = new int[n];
        removed = new boolean[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
            successor[i] = (i + 1 < n) ? i + 1 : i; // Initially, point to the next element or itself if last
            removed[i] = false;
        }
    }

    private int root(int i) {
        while (i != parent[i]) {
            parent[i] = parent[parent[i]];
            i = parent[i];
        }
        return i;
    }

    public void remove(int x) {
        if (removed[x])
            return;
        removed[x] = true;

        // Union the removed element with its successor
        if (x < removed.length - 1) {
            union(x, x + 1);
        }

        if (x > 0) { // ensure the path is correctly updated to skip over all removed elements.
            union(x, x - 1);
        }
    }

    private void union(int p, int q) {
        int rootP = root(p);
        int rootQ = root(q);

        if (rootP == rootQ)
            return;

        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        } else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }

        // After union, update successor of the root of the smaller set to be successor
        // of the larger set
        successor[rootP] = successor[rootQ];
    }

    public int findSuccessor(int x) {
        if (!removed[x]) {
            return x;
        }
        return successor[root(x)];
    }
}
