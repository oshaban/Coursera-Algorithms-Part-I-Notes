package quickfind;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;

/**
 * A UnionFind Implementation.
 */
public class UnionFind {

    private int[] id;

    public UnionFind(int N) {
        // Create an array to represent the nodes and their components
        // each "i" is a node
        // id[i] represents the "component-number" for that node
        // nodes that have the same component-number are part of the same component
        this.id = new int[N];
        for (int node = 0; node < this.id.length; node++) {
            // Initially each node is split into its own component
            this.id[node] = node;
        }
    }

    // This checks if two nodes are part of the same component
    public boolean connected(int p, int q) {
        if (id[p] == id[q]) {
            return true;
        }
        return false;
    }

    // This will 'union' or connect two components together
    public void union(int p, int q) {
        // See which component the first node is part of
        // Change every node that is part of the second component to be part of the first component
        int firstComponentNumber = id[p];
        int secondComponentNumber = id[q];
        for (int node = 0; node < id.length; node++) {
            if (id[node] == firstComponentNumber) {
                id[node] = secondComponentNumber;
            }
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
