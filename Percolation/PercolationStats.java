import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    private final double[] results;
    private int trial;

    /**
     * Perform trials independent experiments on an n-by-n grid.
     */
    public PercolationStats(int n, int trials) {
        if(n<=0){
            throw new IllegalArgumentException("Out of Bounds");
        }
        trial=trials;
        results=new double[n];
        for(int i=1;i<=trials;i++){
            Percolation a=new Percolation(n);
            while(!a.percolates()){
                int row = StdRandom.uniformInt(1, n + 1);
                int col = StdRandom.uniformInt(1, n + 1);
                a.open(row, col);
            }
            double z = (double) a.numberOfOpenSites() / (n * n);
            results[i] = z;

            }
        
       //Check input parameters
       //Initialize class variables
       //Run trials - for each trial create a new percolation object and randomly open a site until it percolates
       //set results to percentage of sites that have to be opened before it percolates
    }
    public double mean() {
        return StdStats.mean(results);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(results);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - ((CONFIDENCE_95*stddev())/ Math.sqrt(trial));

    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + ((CONFIDENCE_95*stddev())/ Math.sqrt(trial));
    }
    /**
     * test client (described below)
     */
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