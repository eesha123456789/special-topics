import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int number;
    private int os;
    private int top;
    private int bottom;
    private boolean[][] grid;
    private int virtualTop;
    private int virtualBottom;
    private WeightedQuickUnionUF ufgrid;
    private WeightedQuickUnionUF uffull;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n){
        verify(n,n);
        number=n;
        os=0;
        top=n*n +1;
        bottom=n*n;
        grid=new boolean[n][n];
        //adjust for top and bottom (indices)
        virtualBottom= number+3;
        virtualTop=number+2;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        verify(row,col);
        grid[row][col]=true;
        os++;
        
        if (row-1 >= 0 && col-1 >= 0 && row-1 < number && col-1 < number && isOpen(row - 1, col)) {
            ufgrid.union(getIndex(row, col)-1, getIndex(row - 1, col) - 1);
            uffull.union(getIndex(row, col)-1, getIndex(row - 1, col) - 1);
        }

        if (row-1 >= 0 && col-1 >= 0 && row-1 < number && col-1 < number && isOpen(row, col - 1)) {
            ufgrid.union(getIndex(row, col)-1, getIndex(row, col - 1) - 1);
            uffull.union(getIndex(row, col)-1, getIndex(row, col - 1) - 1);
        }
        
        if (row-1 >= 0 && col-1 >= 0 && row-1 < number && col-1 < number && isOpen(row + 1, col)) {
            ufgrid.union(getIndex(row, col)-1, getIndex(row + 1, col) - 1);
            uffull.union(getIndex(row, col)-1, getIndex(row + 1, col) - 1);
        }

        if (row-1 >= 0 && col-1 >= 0 && row-1 < number && col-1 < number  && isOpen(row, col + 1)) {
            ufgrid.union(getIndex(row, col)-1, getIndex(row, col + 1) - 1);
            uffull.union(getIndex(row, col)-1, getIndex(row, col + 1) - 1);
        }



    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        verify(row, col);
        if(grid[row-1][col-1]){
            return true; 
        }
        else{
            return false;
        }
        
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        verify(row, col);
        return uffull.connected(virtualTop,getIndex(row,col));
    }
    // returns the number of open sites
    public int numberOfOpenSites(){
        return os;
    }

    // does the system percolate?
    public boolean percolates(){
        return ufgrid.connected(virtualTop,virtualBottom);
    }

    public boolean verify(int row, int column){
        if(number>column||number>row||0>=column||0>=row){
            throw new IllegalArgumentException("Out of bounds");  
        }
    }
    private int getIndex(int row, int col) {
        //Mapping from row column to single index
        int index = (row - 1) * number + col;
        return index;
    }

    /* test client (optional)
    public static void main(String[] args){

    }*/
}