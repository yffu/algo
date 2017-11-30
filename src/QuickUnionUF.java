

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickUnionUF {
	
	private int[] id;

	public QuickUnionUF(int n) {
		// TODO Auto-generated constructor stub
		id = new int[n];
		for (int i = 0; i < id.length; i++) 
			id[i]=i;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int N = StdIn.readInt();
		QuickUnionUF uf = new QuickUnionUF(N);
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
				for (int i = 0; i < N; i++) {
					str_id += "  " + i;
					str_sep += "---";
					str_val += "  " + uf.find(i);
				}
				StdOut.println(str_id);
				StdOut.println(str_sep);
				StdOut.println(str_val);
			}
		}
		
	}

	private boolean connected(int p, int q) {
		// TODO Auto-generated method stub
		return find_root(p) == find_root(q);
		
	}
	
	private int find_root(int i) {
		if ( i == id[i]) return i;
		else return find_root(id[i]);
	}

	private void union(int p, int q) {
		// TODO Auto-generated method stub
		int rt_p = find_root(p);
		int rt_q = find_root(q);
		// the if else is unnecessary, since if they have the same root, id[x]=x which is true anyways for root
		//		if (rt_p == rt_q) return;
		//		else id[rt_p] = rt_q;
		
		id[rt_p] = rt_q;
	}

	int find(int p) {
		return id[p];
	}
}
