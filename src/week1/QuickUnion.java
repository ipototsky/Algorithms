package week1;

/**
 * Created by ipototsky on 12/30/16.
 */
public class QuickUnion implements IUnionFind {
    private int[] array;

    public QuickUnion(int n) {
        array = new int[n];
        for(int i = 0; i < n; i++){
           array[i] = i;
        }
    }

    @Override
    public void union(int p, int q) {
        int pId = root(p);
        int qId = root(q);

        array[pId] = qId;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return root(q) == root(p);
    }

    //root of q
    private int root(int i) {
        while(i != array[i]) {
            i = array[i];
        }
        return i;
    }
}
