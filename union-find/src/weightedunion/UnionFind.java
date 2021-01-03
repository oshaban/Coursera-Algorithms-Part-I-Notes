package weightedunion;

import org.junit.Assert;

/**
 * A weighted union find implementation.
 */
public class UnionFind {

    private int[] id;
    private int[] size;

    public UnionFind(int N) {
        this.id = new int[N];
        this.size = new int[N];

        for (int node = 0; node < N; node++) {
            this.id[node] = node; // Initially make each node the root of that component
            this.size[node] = 1; // Initially each component has a size of 1
        }
    }

    private int getRoot(int node) {
        int cur = node;
        while (this.id[cur] != cur) {
            // Traverse up by making the cur pointer move up to the nodes parent
            cur = this.id[cur];
        }
        return cur;
    }

    public boolean connected(int p, int q) {
        // Do the nodes have the same root in their components?
        return getRoot(p) == getRoot(q);
    }

    public void union(int p, int q) {
        int pRoot = getRoot(p);
        int qRoot = getRoot(q);

        // The nodes are part of the same component, return
        if (pRoot == qRoot) return;

        // make the smaller component a child of the bigger component
        if (size[qRoot] < size[pRoot]) {
            // q component's size is smaller, and p component's size is bigger
            id[qRoot] = pRoot; // Parent is now p
            size[pRoot] += size[qRoot]; // Add to the size of component p
        } else {
            // p component's size is smaller, and q component's size is bigger
            id[pRoot] = qRoot;
            size[qRoot] += size[pRoot];
        }
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
