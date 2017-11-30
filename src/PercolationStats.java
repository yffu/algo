import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
	
	private int[] ops;
	private Percolation perc;
	private int n;
	private int trs;
	private double op_mean;
	private double op_stddev;
	private double[] op_conf;

	public PercolationStats(int num, int trials) {
		// perform trials independent experiments on an n-by-n grid
		
		this.n=num;
		this.trs = trials;
		
		if (n < 1) throw new java.lang.IllegalArgumentException("n out of bounds");
		if (trs < 1) throw new java.lang.IllegalArgumentException("T out of bounds");
		
		ops = new int[trs];
		for(int t = 0; t < trs; t++) {
			// Initialize all sites to be blocked.
			perc = new Percolation(n);
			// Repeat the following until the system percolates:
			while (!perc.percolates()){
				// Choose a site uniformly at random among all blocked sites.
				// Open the site.
				perc.open(StdRandom.uniform(n) + 1, StdRandom.uniform(n) + 1);
			}
			ops[t]=perc.numberOfOpenSites();
		}
		
		op_mean = StdStats.mean(ops)/(n*n);
		op_stddev = StdStats.stddev(ops)/(n*n);
		op_conf = new double[2];
		if (Double.isNaN(op_stddev)) {
			op_conf[0]=Double.NaN;
			op_conf[1]=Double.NaN;
		}
		else {
			op_conf[0]=op_mean - (1.96 * op_stddev)/Math.sqrt(trs);
			op_conf[1]=op_mean + (1.96 * op_stddev)/Math.sqrt(trs);
		}
	}
	public double mean() {
		// sample mean of percolation threshold
		return op_mean;
	}
	public double stddev() {
		// sample standard deviation of percolation threshold
		// if trials equals 1? The sample standard deviation is undefined. We recommend returning Double.NaN. - covered in StdStats.stddev
		return op_stddev;
	}
	public double confidenceLo() {
		// low  endpoint of 95% confidence interval
		return op_conf[0];
	}
	public double confidenceHi() {
		// high endpoint of 95% confidence interval
		return op_conf[1];
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			PercolationStats perc_st = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
			StdOut.println("mean\t\t\t = " + perc_st.mean());
			StdOut.println("stddev\t\t\t = " + perc_st.stddev());
			StdOut.println("95% confidence interval\t = " + "[" + perc_st.confidenceLo() + ", " + perc_st.confidenceHi() + "]");
		}
		catch (NumberFormatException nfe) {
			System.out.println("invalid inputs " + nfe.getMessage());
			System.exit(1);
		}
		
	}

}
