import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int top = 0;
    private int bottom;
    private int size;
    private boolean[][] opened;
    private int numberOfOpenSites;
    private WeightedQuickUnionUF uf;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than 0");
        }
        size = n;
        bottom = size * size + 1;
        uf = new WeightedQuickUnionUF(size * size + 2);
        opened = new boolean[size][size];
    }

    public void open(int row, int col) {
        if (!isOpen(row, col)) {
            opened[row - 1][col - 1] = true;
            numberOfOpenSites++;
        }
        if (row == 1) {
            uf.union(getQuickFindIndex(row, col), top);
        }
        if (row == size) {
            uf.union(getQuickFindIndex(row, col), bottom);
        }
        if (row > 1 && isOpen(row - 1, col)) {
            uf.union(getQuickFindIndex(row, col), getQuickFindIndex(row - 1, col)); // up
        }
        if (row < size && isOpen(row + 1, col)) {
            uf.union(getQuickFindIndex(row, col), getQuickFindIndex(row + 1, col)); // down
        }
        if (col > 1 && isOpen(row, col - 1)) {
            uf.union(getQuickFindIndex(row, col), getQuickFindIndex(row, col - 1)); // left
        }
        if (col < size && isOpen(row, col + 1)) {
            uf.union(getQuickFindIndex(row, col), getQuickFindIndex(row, col + 1)); // right
        }
    }

    public boolean isOpen(int row, int col) {
        return opened[row - 1][col - 1];
    }

    public boolean isFull(int row, int col) {
        return qf.find(top) == qf.find(getQuickFindIndex(row, col));
    }

    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    public boolean percolates() {
        return qf.find(top) == qf.find(bottom);
    }

    private int getQuickFindIndex(int row, int col) {
        return size * (row - 1) + col;
    }
}
