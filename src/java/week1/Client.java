package week1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by ipototsky on 12/30/16.
 */
public class Client {

    public static void main(String[] args) {
        int N = StdIn.readInt();
        IUnionFind unionFind = new WeightedQuickUnionPathCompression(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (!unionFind.isConnected(p, q)) {
                unionFind.union(p, q);
                StdOut.println(p + " " + q);
            }
        }
    }
}
