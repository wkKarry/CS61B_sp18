package hw2;

import edu.princeton.cs.algs4.*;

public class PercolationStats {
    private double[] percolationProbabilities;
    private int trials;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) throw new IllegalArgumentException();
        percolationProbabilities = new double[T];
        for (int k = 0; k < T; k++) {
            Percolation matrix = pf.make(N);
            while (!matrix.percolates()) {
                int i = StdRandom.uniform(N);
                int j = StdRandom.uniform(N);
                matrix.open(i, j);
            }
            percolationProbabilities[k]+= matrix.numberOfOpenSites()/(N*N);
        }
    }   // perform T independent experiments on an N-by-N grid

    public double mean(){
        return StdStats.mean(percolationProbabilities);
    }                                           // sample mean of percolation threshold

    public double stddev(){
        return StdStats.stddev(percolationProbabilities);
    }                                         // sample standard deviation of percolation threshold

    public double confidenceLow(){
        return mean()-(1.96*stddev())/Math.sqrt(trials);
    }                                  // low endpoint of 95% confidence interval

    public double confidenceHigh(){
        return mean()+(1.96*stddev())/Math.sqrt(trials);
    }                                 // high endpoint of 95% confidence interval
}
