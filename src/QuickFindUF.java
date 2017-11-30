

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickFindUF {
	
	private int[] id;

	public QuickFindUF(int n) {
		// TODO Auto-generated constructor stub
		id = new int[n];
		for (int i = 0; i < id.length; i++) 
			id[i]=i;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int N = StdIn.readInt();
		QuickFindUF uf = new QuickFindUF(N);
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

	private void union(int p, int q) {
		// TODO Auto-generated method stub
		if (connected(p, q)) return;
		else {
			int id_p = id[p];
			int id_q = id[q];
			for (int i = 0; i < id.length; i++) {
				if (id[i] == id_p) id[i] = id_q;
			}
		}
	}

	private boolean connected(int p, int q) {
		// TODO Auto-generated method stub
		return id[p] == id[q];
	}
	
	int find(int p) {
		return id[p];
	}
	
	int count() {
		return id.length;
	}

}
