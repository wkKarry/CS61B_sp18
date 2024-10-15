package hw2;

import edu.princeton.cs.algs4.NFA;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    boolean[][] grid;
    int max_index = 0;

    public Percolation(int N) {
        boolean[][] grid = new boolean[N][N];
        max_index = N-1;
    }             // create N-by-N grid, with all sites initially blocked

    public void open(int row, int col) {
        grid[row][col] = true;
    }       // open the site (row, col) if it is not open already

    public boolean isOpen(int row, int col) {
        return grid[row][col];
    }  // is the site (row, col) open?

    public boolean isFull(int row, int col) {
        if(col<0||col>max_index||isOpen(row, col)) {
            return false;
        }
        if (row == 0 && isOpen(row, col)) {
            return true;
        } else {
            return isFull(row - 1, col) || isFull(row, col + 1) || isFull(row, col - 1);
        }
    }  // is the site (row, col) full?

    public int numberOfOpenSites() {
        int count = 0;
        for (int i = 0; i < max_index; i++) {
            for (int j = 0; j < max_index; j++) {
                if (grid[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }           // number of open sites

    public boolean percolates() {
        for (int i = 0; i <= max_index; i++) {
            if (isFull(max_index,i)) {
                return true;
            }
        }
        return false;
    }              // does the system percolate?

    public static void main(String[] args) {
    }   // use for unit testing (not required)


}
