package hw2;

import edu.princeton.cs.algs4.NFA;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    public int range;
    public int num;
    private class Site{
        public int no;
        public boolean open;
        public Site(int i, int j){
            no = i*range +j + 1;
            open = false;
        }
    }
    private Site[][] map;
    private WeightedQuickUnionUF uf;
    public Percolation(int n) {
        if(n<=0) throw new IllegalArgumentException();
        range = n;
        map = new Site[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = new Site(i, j);
            }
        }
        uf = new WeightedQuickUnionUF(range * range + 2);
    }

    private boolean inValid(int index){
        return (index<0 ||index >=range);
    }


    public void open(int row, int col) {
        if(inValid(row) || inValid(col)) throw new IndexOutOfBoundsException();
        if(map[row][col].open) return;
        map[row][col].open = true;
        if (row == 0) {
            uf.union(map[row][col].no,0);
        }
        if (row-1>=0 && map[row-1][col].open) {
            uf.union(map[row-1][col].no,map[row][col].no);
        }
        if (row+1<range && map[row+1][col].open) {
            uf.union(map[row+1][col].no,map[row][col].no);
        }
        if (col-1>=0 && map[row][col-1].open) {
            uf.union(map[row][col-1].no,map[row][col].no);
        }
        if (col+1<range && map[row][col+1].open) {
            uf.union(map[row][col+1].no,map[row][col].no);
        }
        /*if (row == range - 1){
            uf.union(map[row][col].no,range * range + 1);
        }*/
        num++;
    }

    public boolean isOpen(int row, int col) {
        if(inValid(row) || inValid(col)) throw new IndexOutOfBoundsException();
        return map[row][col].open;
    }
    public boolean isFull(int row, int col) {
        if(inValid(row) || inValid(col)) throw new IndexOutOfBoundsException();
        return uf.connected(map[row][col].no,0);
    }
    public int numberOfOpenSites(){
        return num;
    }
    /*public boolean percolates(){
        return uf.connected(0,range * range + 1);
    }*/
    public boolean percolates() {
        for (int i = range*(range-1)+1; i < range*range + 1; i++) {
            if (uf.connected(i,0)){
                return true;
            }
        }
        return false;
    }
}
