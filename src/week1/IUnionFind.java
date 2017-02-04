package week1;

/**
 * Created by ipototsky on 12/30/16.
 */
public interface IUnionFind {

    void union(int q, int p);

    boolean isConnected(int q, int p);
}
