package segmentTrees;

import org.junit.Assert;

import java.util.Random;

/**
 * Segment Trees are useful for performing range queries, and supporting updates on the items in the range.
 * Segment trees take O(N) time to construct, but take O(log(N)) time to perform range queries, and O(log(N)) to update.
 * The below is an example of a segment tree that supports a range sum. The segment tree can easily be modified to
 * support other range queries, such as range minimum query or range maximum query.
 */
public class SegmentTree {

    // Test runner
    public static void main(String[] args) {

        int bound = 50;
        int N = 500;
        Random random = new Random();

        int[] arr = new int[N];
        for (int i = 0; i < arr.length; i++) arr[i] = random.nextInt(bound);

        int lower = random.nextInt(arr.length - 1);
        int upper = lower + random.nextInt(arr.length - lower);

        Node segmentTree = new Node(0, arr.length - 1, arr);
        int segSum = segmentTree.rangeSum(lower, upper);

        int actualSum = 0;
        for (int i = lower; i <= upper; i++) actualSum += arr[i];

        System.out.println("Segment Tree Sum: " + segSum);
        System.out.println("Actual Sum: " + actualSum);
        Assert.assertEquals(segSum, actualSum);


        // Testing update method
        for (int i = lower; i <= upper; i++) {
            int update = random.nextInt(bound);
            arr[i] = update;
            segmentTree.update(i, update);
        }

        int segSum2 = segmentTree.rangeSum(lower, upper);

        int actualSum2 = 0;
        for (int i = lower; i <= upper; i++) actualSum2 += arr[i];

        System.out.println("Segment Tree Sum2: " + segSum2);
        System.out.println("Actual Sum2: " + actualSum2);
        Assert.assertEquals(segSum2, actualSum2);


    }

    public static class Node {
        int low, high; // Array indexes of left and right bounds
        Node left, right;
        int sum; // Stores range sum

        public Node(int low, int high, int[] a) {
            this.low = low;
            this.high = high;
            if (low == high) {
                // Leaf node
                sum = a[low];
            } else {
                // Two children
                // Split range in half, compute sum of each half, then merge back together; merge-sort
                int mid = low + (high - low) / 2;
                left = new Node(low, mid, a);
                right = new Node(mid + 1, high, a);
                merge(); // Recalculates the sum
            }
        }

        // Merges two halves and recalculates sum
        public void merge() {
            if (low == high) return; // No children

            // Two children, need to recalculate
            sum = left.sum + right.sum;
        }

        // Updates a point within the underlying array of the range query
        public void update(int index, int newVal) {
            if (low == high) {
                // Leaf node
                sum = newVal;
                return;
            }
            // Update children
            if (index <= left.high) left.update(index, newVal);
            else right.update(index, newVal);

            // Merge halves and recalculate sum
            merge();
        }


        // Gets the sum in the range [l ,r]
        public int rangeSum(int l, int r) {
            // No overlap
            if (r < low || l > high) return 0;

            // Complete overlap
            if (l <= low && high <= r) return sum;

            // Partial overlap
            return left.rangeSum(l, r) + right.rangeSum(l, r);
        }

    }

}
