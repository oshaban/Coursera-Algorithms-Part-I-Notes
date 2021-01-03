package linearProbing;

import org.junit.Assert;

/**
 * An implementation of a hash-table using open addressing with linear probing as a collision resolution method.
 * For optimal performance, load factor should be kept to 0.5
 */
public class LinearProbingHashTable {

    private int M;
    private Integer[] keys;
    private Integer[] values;

    public LinearProbingHashTable(int M) {
        this.M = M;
        this.keys = new Integer[M];
        this.values = new Integer[M];
    }


    public void put(int key, int value) {
        int index = Math.abs(Integer.hashCode(key) % M);

        int i;
        for (i = index; keys[i] != null; i = (i + 1) % M) {
            if (keys[i] == key) {
                values[i] = value;
                return;
            }
        }
        keys[i] = key;
        values[i] = value;
    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
     */
    public int get(int key) {
        int index = Math.abs(Integer.hashCode(key) % M);
        for (int i = index; keys[i] != null; i = (i + 1) % M) {
            if (keys[i] == key) return values[i];
        }
        return -1;
    }

    /**
     * Removes the mapping of the specified value key if this map contains a mapping for the key
     */
    public void remove(int key) {
        int index = Math.abs(Integer.hashCode(key) % M);

        for (int i = index; keys[i] != null; i = (i + 1) % M) {
            // Found the key to delete
            if (keys[i] == key) {

                // Clear out key
                keys[i] = null;
                values[i] = null;

                // Refill the rest of the keys after the key just deleted
                i = (i + 1) % M;
                while (keys[i] != null) {
                    int keyToRedo = keys[i];
                    int valToRedo = values[i];

                    keys[i] = null;
                    values[i] = null;

                    put(keyToRedo, valToRedo);
                    i = (i + 1) % M;
                }
            }
        }

    }

    public static void main(String[] args) {
        LinearProbingHashTable map = new LinearProbingHashTable(10);
        Assert.assertEquals(map.get(1), -1);
        map.put(1, 1);
        map.put(2, 2);
        Assert.assertEquals(map.get(1), 1);
        Assert.assertEquals(map.get(2), 2);
        Assert.assertEquals(map.get(3), -1);
        map.put(2, 1);
        Assert.assertEquals(map.get(2), 1);
        map.remove(2);
        Assert.assertEquals(map.get(2), -1);
    }

}
