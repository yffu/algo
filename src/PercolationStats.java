import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
	
	private final double opMean;
	private final double opStddev;
	private final double[] opConf;

	public PercolationStats(int n, int trs) {
		// perform trials independent experiments on an n-by-n grid
		
		if (n < 1) throw new java.lang.IllegalArgumentException("n out of bounds");
		if (trs < 1) throw new java.lang.IllegalArgumentException("T out of bounds");
		
		int[] ops=new int[trs];
		for(int t=0; t < trs; t++) {
			// Initialize all sites to be blocked.
			Percolation perc = new Percolation(n);
			// Repeat the following until the system percolates:
			while(!perc.percolates()){
				// Choose a site uniformly at random among all blocked sites.
				// Open the site.
				perc.open(StdRandom.uniform(n) + 1, StdRandom.uniform(n) + 1);
			}
			ops[t]=perc.numberOfOpenSites();
		}
		
		opMean=StdStats.mean(ops)/(n*n);
		opStddev=StdStats.stddev(ops)/(n*n);
		opConf=new double[2];
		double CONFIDENCE_95=1.96;
		if (Double.isNaN(opStddev)) {
			opConf[0]=Double.NaN;
			opConf[1]=Double.NaN;
		}
		else {
			opConf[0]=opMean - (CONFIDENCE_95 * opStddev)/Math.sqrt(trs);
			opConf[1]=opMean + (CONFIDENCE_95 * opStddev)/Math.sqrt(trs);
		}
	}
	public double mean() {
		// sample mean of percolation threshold
		return opMean;
	}
	public double stddev() {
		// sample standard deviation of percolation threshold
		// if trials equals 1? The sample standard deviation is undefined. We recommend returning Double.NaN. - covered in StdStats.stddev
		return opStddev;
	}
	public double confidenceLo() {
		// low  endpoint of 95% confidence interval
		return opConf[0];
	}
	public double confidenceHi() {
		// high endpoint of 95% confidence interval
		return opConf[1];
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			PercolationStats percSt = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
			StdOut.println("mean\t\t\t = " + percSt.mean());
			StdOut.println("stddev\t\t\t = " + percSt.stddev());
			StdOut.println("95% confidence interval\t = " + "[" + percSt.confidenceLo() + ", " + percSt.confidenceHi() + "]");
		}
		catch(NumberFormatException nfe){
			System.out.println("invalid inputs " + nfe.getMessage());
		}
		
	}

}
