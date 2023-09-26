import edu.princeton.cs.algs4.StdRandom;
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
                while(!percolation.percolates()){
                    int x = StdRandom.uiform(1, number+1);
                    percolation.open(x,x);
                }
                int os =percolation.numberOfOpenSites();
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
        return StdStats.mean(trial);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(trial);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        int a=1.96 * stddev();
        return mean() - (a/ Math.sqrt(trial));

    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        int a=1.96 * stddev();
        return mean() + (a / Math.sqrt(trial));
    }

   // test client (see below)
   public static void main(String[] args){

    String.out.println("mean                    = " + mean());
    String.out.println("stddev                  = " + stddev());
    String.out.println("95% confidence interval = " + confidenceLo()+", "+confidenceHi());
   }
}