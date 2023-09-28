
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Percolation {
    private final WeightedQuickUnionUF wquuf;
    private final WeightedQuickUnionUF normalQU;
    private final int dimension;
    private final int headIndex;
    private final int tailIndex;
    private final boolean[][] openStatus;
    private int numOpen;
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if(n<=0){
            throw new IllegalArgumentException("Out of bounds");
        }
        dimension=n;
        headIndex=0;
        tailIndex=n+1;
        openStatus=new boolean[n][n];
        numOpen=0;
        wquuf=new WeightedQuickUnionUF(n*n +1);
        normalQU=new WeightedQuickUnionUF (n*n +2);
        // Check boundary conditions and initialize class variables
    }
    private int getIndex(int row, int col) {
        //Mapping from row column to single index
        int index = (row - 1) * dimension + col;
        return index;
    }
    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        verify(row, col);
        openStatus[row][col]=true;
        numOpen++;
        if(row==1){
            wquuf.union(headIndex,getIndex(row, col));
            normalQU.union(headIndex,getIndex(row, col));
        }
        if(row==dimension){
            wquuf.union(getIndex(row,col),tailIndex);
            normalQU.union(getIndex(row,col),tailIndex);
        }
        if(isOpen(row-1,col)){
            wquuf.union(getIndex(row-1,col),getIndex(row,col));
            normalQU.union(getIndex(row-1,col),getIndex(row,col));
        }
        if(isOpen(row,col-1)){
            wquuf.union(getIndex(row-1,col),getIndex(row,col));
            normalQU.union(getIndex(row-1,col),getIndex(row,col));
        }
        if(isOpen(row+1,col)){
            wquuf.union(getIndex(row-1,col),getIndex(row,col));
            normalQU.union(getIndex(row-1,col),getIndex(row,col));
        }
        if(isOpen(row,col+1)){
            wquuf.union(getIndex(row-1,col),getIndex(row,col));
            normalQU.union(getIndex(row-1,col),getIndex(row,col));
        }
        //See the slide in the Union-Find Applications about how to do open - slide 59/60 about 7:10 in.
        //Check the top and bottom
        //Check boundary conditions and edge cases
        //Up down left right
    
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        verify(row, col);
        return openStatus[row][col];
        // Your code should maintain this state in a data structure of boolean values
        // One liner returns the state
    }
    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        verify(row,col);
        return wquuf.find(getIndex(row,col))==wquuf.find(headIndex);

        // Is there a path from the top to the specified Index?
    }
    // returns the number of open sites
    public int numberOfOpenSites() {
        return numOpen;
    }
    // does the system percolate?
    public boolean percolates() {
       //Is there a path between top and bottom? 
        return wquuf.find(tailIndex)=wquuf.find(headIndex);
    }
    public void verify(int row, int col){
        if(row>dimension || row<=0 || col>dimension || col<=0){
            throw new IllegalArgumentException("Out of Bounds");
        }
    }
    public static void main(String[] args) {
    }
}

