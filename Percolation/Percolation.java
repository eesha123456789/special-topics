import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int number;
    private int os;
    private int top;
    private int bottom;
    private boolean[][] grid;
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n){
        number=n;
        os=0;
        top=n*n +1;
        bottom=n*n;
        grid=new boolean[n][n];
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        verify(row,col);
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

    }
    // returns the number of open sites
    public int numberOfOpenSites(){

    }

    // does the system percolate?
    public boolean percolates()

    public boolean verify(int row, int column){
        if(number>column||number>row||0>=column||0>=row){
            throw new IllegalArgumentException("Out of bounds");  
        }
    }

    // test client (optional)
    public static void main(String[] args)
}