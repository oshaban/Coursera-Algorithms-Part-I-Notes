package quckunion;

import org.junit.Assert;

/**
 * A Quick Union Implementation.
 */
public class UnionFind {

    // Each index i is going to represent a node
    // The actual value inside id[i] represents the parent of that node
    int[] id;

    // N nodes, from 0 .. N - 1
    public UnionFind(int N) {
        this.id = new int[N];

        // Initially the nodes have them selves as their parents
        for (int node = 0; node < this.id.length; node++) {
            this.id[node] = node;
        }
    }

    public int getRoot(int node) {
        int cur = node;
        while (id[cur] != cur) {
            cur = id[cur];
        }
        return cur;
    }

    public boolean connected(int p, int q) {
        // Check if both nodes have the same root for components
        return getRoot(p) == getRoot(q);
    }

    public void union(int p, int q) {
        int rootP = getRoot(p);
        int rootQ = getRoot(q);

        // Make the second components root, a child of the first components root
        id[rootQ] = rootP;
    }

    public static void main(String[] args) {
        // N components, 10 components from 0 ... 9
        UnionFind uf = new UnionFind(10);
        uf.union(4, 3);
        uf.union(3, 8);
        uf.union(6, 5);
        uf.union(9, 4);
        uf.union(2, 1);
        Assert.assertFalse(uf.connected(0, 7));
        Assert.assertTrue(uf.connected(8, 9));
        uf.union(5, 0);
        uf.union(7, 2);
        uf.union(6, 1);
        uf.union(1, 0);
        Assert.assertTrue(uf.connected(0, 7));
    }

}
