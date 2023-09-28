import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationStats {
    private int trial;
    private int number;
    private double[] what;
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials){
        trial=trials;
        number=n;
        what=new double[trials];
        if (number > 0 && trial > 0) {
            for (int i=0;i<trial;i++){
                Percolation fin=new Percolation(number);
                while(!fin.percolates()){
                    int x = StdRandom.uniform(1, number+1);
                    fin.open(x,x);
                }
                int os =fin.numberOfOpenSites();
                what[trial]=(double)os/(Math.pow(2,number));
            }
        }
        else{
          throw new IllegalArgumentException("Out of bounds");  
        }

    }
    // sample mean of percolation threshold
     // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(what);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(what);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        double a=1.96 * stddev();
        return mean() - (a/ Math.sqrt(trial));

    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        double a=1.96 * stddev();
        return mean() + (a / Math.sqrt(trial));
    }

   // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, trials);
        StdOut.println("mean                    = " + stats.mean());
        StdOut.println("stddev                  = " + stats.stddev());
        StdOut.println("95% confidence interval = [" + stats.confidenceLo() + ", "
                + stats.confidenceHi() + "]");
    }
}