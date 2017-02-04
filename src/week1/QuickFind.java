package week1;

/**
 * Created by ipototsky on 12/30/16.
 */
public class QuickFind implements IUnionFind {
    private int array[];

    public QuickFind(int n) {
        this.array = new int[n];
        for(int i = 0; i < n; i++) {
            this.array[i] = i;
        }
    }


    public void union(int q, int p) {
        int pId = array[q];
        int qId = array[p];
        for (int i = 0; i < array.length; i++) {
            if (array[i] == pId) {
                array[i] = qId;
            }
        }
    }

    private int find(int q) {
        return array[q];
    }

    public boolean isConnected(int q, int p) {
        return find(q) == find(p);
    }
}
