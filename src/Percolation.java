import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private int[] op;
	private int sz;
	private int n;
	private WeightedQuickUnionUF uf;
	private int op_cnt = 0;
	
	private int xyTo1D(int row, int col) {
		if (col < 1 || col > n) throw new java.lang.IllegalArgumentException("col out of bounds");
		if (row < 1 || row > n) throw new java.lang.IllegalArgumentException("row out of bounds");
		return (row-1)*n + (col-1); 
	}
	// create n-by-n grid, with all sites blocked	
	public Percolation(int n)   {
		// default value is 0, corresponding to blocked.
		if (n <= 0) throw new java.lang.IllegalArgumentException("n out of bounds");
		this.n = n;
		sz = n * n;
		op = new int[sz];
		// extra 2 spots are for virtual top and bottom spots
		uf = new WeightedQuickUnionUF(sz + 2);
	}
	// open site (row, col) if it is not open already
	public    void open(int row, int col)    {
		// check if already open
		if (isOpen(row, col)) return;
		// union with positions on top, bottom, left and right. 
		int pos1D=xyTo1D(row, col); // precondition: 1 <= col <= n;  1<= row <= n otherwise exception is thrown.
		// precondition: spot is not open -> open the spot
		op[pos1D]=1;
		// increase open counter
		op_cnt += 1;
		// left 
		if (col > 1) {
			if (isOpen(row, col-1)) uf.union(pos1D, xyTo1D(row, col-1)); 
		}
		// right
		if (col < n) {
			if (isOpen(row, col+1)) uf.union(pos1D, xyTo1D(row, col+1));
		}
		// top 
		if (row > 1) {
			if (isOpen(row-1, col)) uf.union(pos1D, xyTo1D(row-1, col));
		}
		else uf.union(pos1D, n*n); // when row = 1, connect it to virtual top spot (assumed open always)
		// bottom
		if (row < n) {
			if(isOpen(row+1, col)) uf.union(pos1D, xyTo1D(row+1, col));
		}
		else uf.union(pos1D, n*n+1); // when row = n, connect it to virtual bottom spot (assumed open always)
	}
	// is site (row, col) open?
	public boolean isOpen(int row, int col)  {
		return op[xyTo1D(row, col)]==1;
	}
	// is site (row, col) full?
	public boolean isFull(int row, int col)  {
		//A full site is an open site that can be connected to an open site in the top row 
		int pos1D=xyTo1D(row, col);
		return uf.connected(pos1D, n*n);
	}
	// number of open sites
	public     int numberOfOpenSites()       {
		return op_cnt;
	}
	public boolean percolates() {
		return uf.connected(n*n, n*n+1);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
