package week1;

/**
 * Created by ipototsky on 12/31/16.
 */
public class WeightedQuickUnionPathCompression implements IUnionFind {
    private int[] size;
    private int[] array;

    public WeightedQuickUnionPathCompression(int n) {
        array = new int[n];
        size = new int[n];
        for(int i = 0; i < n; i++) {
            array[i] = i;
        }
    }

    @Override
    public void union(int q, int p) {
        int pRoot = root(p);
        int qRoot = root(q);
        if (qRoot == pRoot) {
            return;
        }
        if (size[pRoot] > size[qRoot]) {
            array[qRoot] = array[pRoot];
            size[pRoot] += size[qRoot];
        } else {
            array[pRoot] = array[qRoot];
            size[qRoot] += size[pRoot];
        }
    }

    @Override
    public boolean isConnected(int q, int p) {
        return root(q) == root(p);
    }

    private int root(int i) {
        while (array[i] != i) {
            array[i] = array[array[i]];
            i = array[i];
        }
        return i;
    }
}