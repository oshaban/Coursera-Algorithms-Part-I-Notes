package problems.lrucache;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * An LRU Cache Implementation that stores Integer:Integer key:value pairs
 */
public class LRUCache {

    // To access all nodes in the queue instantly, we use a map that points to them
    // This will even allow us to access nodes in the middle of the queue
    Map<Integer, ListNode> map = new HashMap<>();

    // To handle edge cases of inserting/deleting from empty/small queues, we use sentinel/dummy nodes
    ListNode pre = new ListNode(-1, -1); // Sentinel before first item
    ListNode post = new ListNode(-1, -1); // Sentinel after last item
    int size = 0;
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;

        // Link up sentinels
        pre.next = post;
        post.prev = pre;
    }

    public int get(int key) {
        // Cache doesn't contain key
        if (!map.containsKey(key) || size == 0) return -1;

        // Remove the node from the queue
        ListNode node = removeNode(key);
        int nodeVal = node.val;

        // Add it to the end of the queue
        addToEnd(node);
        return nodeVal;
    }

    public void put(int key, int value) {

        ListNode node;

        // Cache already contains the key, update the value
        if (map.containsKey(key)) {
            node = map.get(key);
            node.val = value;
            removeNode(node.key);
        } else {
            // Cache doesn't contain the key, we are adding a new key:value
            node = new ListNode(key, value);
        }

        // cache is full, remove item from the front of the queue, which contains the LRU item
        if (size >= capacity) {
            removeFront();
        }

        addToEnd(node);
    }

    public ListNode removeNode(int key) {

        ListNode node = map.get(key);
        ListNode prev = node.prev;
        ListNode next = node.next;

        // Re-wire list to skip current node
        prev.next = next;
        next.prev = prev;

        // Remove reference from map
        map.remove(node.key);

        size--;
        return node;
    }

    public void removeFront() {
        ListNode front = pre.next;
        removeNode(front.key);
    }

    public void addToEnd(ListNode node) {
        ListNode tail = post.prev;

        // node becomes new last item in list
        tail.next = node;
        node.prev = tail;

        // Link the post sentinel node to the new last item in list
        node.next = post;
        post.prev = node;

        // Add the reference to the node in the map
        map.put(node.key, node);

        size++;
    }

    public class ListNode {
        int key;
        int val;
        ListNode prev;
        ListNode next;

        public ListNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    public static void main(String[] args) {

        /*
        ["LRUCache",    "put",  "put",  "get",  "put",  "get",  "put",  "get",      "get",  "get"]
        [ [2],          [1,1],  [2,2],  [1],    [3,3],  [2],    [4,4],      [1],    [3],    [4]]

        Expected
        [null,          null,   null,   1,      null,   -1,     null,   -1,         3,      4]

        */

        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        Assert.assertEquals(1, lruCache.get(1));
        lruCache.put(3, 3);
        Assert.assertEquals(-1, lruCache.get(2));
        lruCache.put(4, 4);
        Assert.assertEquals(-1, lruCache.get(1));
        Assert.assertEquals(3, lruCache.get(3));
        Assert.assertEquals(4, lruCache.get(4));
    }
}