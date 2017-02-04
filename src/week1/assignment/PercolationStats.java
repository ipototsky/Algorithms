package week1.assignment;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Created by ipototsky on 1/3/17.
 */
public class PercolationStats {
    private double[] thresholds;

    public PercolationStats(int n, int trials) {
        thresholds = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(n) + 1;
                int col = StdRandom.uniform(n) + 1;
                if (!percolation.isOpen(row, col)) {
                    percolation.open(row, col);
                }
            }
            thresholds[i] = percolation.numberOfOpenSites();
        }
    }

    public double mean() {
        return StdStats.mean(thresholds);
    }

    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    public double confidenceLo() {
        return mean() - 1.96 * Math.sqrt(stddev())/Math.sqrt(thresholds.length);
    }

    public double confidenceHi() {
        return mean() + 1.96 * Math.sqrt(stddev())/Math.sqrt(thresholds.length);
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        int trials = StdIn.readInt();
        new PercolationStats(n, trials);
    }
}