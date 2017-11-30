

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickUnionUFIPC {
	
	private int[] id;
	private int[] sz;

	public QuickUnionUFIPC(int n) {
		// TODO Auto-generated constructor stub
		id = new int[n];
		sz = new int[n];
		for (int i = 0; i < id.length; i++) {
			id[i]=i; 
			sz[i]=1;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int N = StdIn.readInt();
		QuickUnionUFIPC uf = new QuickUnionUFIPC(N);
		while(!StdIn.isEmpty())
		{
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			if(!uf.connected(p, q))
			{
				uf.union(p,q);
				StdOut.println(p + " " + q);
				String str_id = "    i   ";
				String str_sep = "--------";
				String str_val = " id[i]  "; 
				String str_sz = " sz[i]  ";
				for (int i = 0; i < N; i++) {
					str_id += "  " + i;
					str_sep += "---";
					str_val += "  " + uf.find(i);
					str_sz += "  " + uf.size(i);
				}
				StdOut.println(str_id);
				StdOut.println(str_sep);
				StdOut.println(str_val);
				StdOut.println(str_sz);
			}
		}
		
	}

	private boolean connected(int p, int q) {
		// TODO Auto-generated method stub
		return find_root(p) == find_root(q);
		
	}
	
	private int find_root(int i) {
		int rt_i;
		if ( i == id[i]) return i;
		else {  rt_i = find_root(id[i]); id[i] = rt_i; return rt_i; }
	}

	private void union(int p, int q) {
		// TODO Auto-generated method stub
		int rt_p = find_root(p);
		int rt_q = find_root(q);
		if (rt_p == rt_q) return;
		if (sz[rt_p] >= sz[rt_q]) { id[rt_q] = rt_p; sz[rt_p] += sz[rt_q]; }
		else { id[rt_p] = rt_q; sz[rt_q] += sz[rt_p]; }
	}

	int find(int p) {
		return id[p];
	}
	
	int size(int p) {
		return sz[p];
	}
}
