package separateChaining;

import org.junit.Assert;

/**
 * An implementation of a hash-table using separate chaining for collision resolution.
 */
public class SeparateChainingHashTable {

    private int M;
    private ListNode[] st;

    public SeparateChainingHashTable(int M) {
        this.M = M;
        this.st = new ListNode[M];
    }


    public void put(int key, int value) {
        int index = Math.abs(key % M);

        // Check if key already exists
        ListNode cur = st[index];
        while (cur != null) {
            if (cur.key == key) {
                cur.val = value;
                return;
            }
            cur = cur.next;
        }
        // Create a new key
        st[index] = new ListNode(key, value, st[index]);
    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
     */
    public int get(int key) {
        int index = Math.abs(key % M);

        ListNode cur = st[index];
        while (cur != null) {
            if (cur.key == key) {
                return cur.val;
            }
            cur = cur.next;
        }
        return -1;
    }

    /**
     * Removes the mapping of the specified value key if this map contains a mapping for the key
     */
    public void remove(int key) {
        int index = Math.abs(key % M);

        ListNode cur = st[index];
        ListNode prev = st[index];

        // Edge cases:
        // Check if nothing is in bin; list is empty
        if (cur == null) return;

        // Deleting head
        if (cur.key == key) {
            st[index] = cur.next;
            return;
        }

        // Find the key to delete
        while (cur != null) {
            if (cur.key == key) {
                prev.next = cur.next;
                return;
            }
            prev = cur;
            cur = cur.next;
        }
    }

    private static class ListNode {
        private int key;
        private int val;
        private ListNode next;

        public ListNode(int key, int val, ListNode next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        SeparateChainingHashTable map = new SeparateChainingHashTable(10);
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