package priorityqueues;

/**
 * A Priority Queue implementation, with max-heap ordering.
 */
public class MaxPQ {

    protected int N = 0; // Note: Heap inserting starts at index 1, pq[0] is unused
    protected int[] pq;

    public MaxPQ(int N) {
        this.pq = new int[N + 1];
    }

    // Builds a Max Heap using a bottom-up approach in O(N) time
    public MaxPQ(int[] nums) {
        this.pq = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            pq[i + 1] = nums[i];
        }
        this.N = this.pq.length - 1;

        for (int k = N / 2; k >= 1; k--) {
            sink(k);
        }
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void swim(int k) {
        while (k > 1 && pq[k] > pq[k / 2]) {
            swap(k, k / 2);
            k = k / 2;
        }
    }

    public void insert(int k) {
        pq[++N] = k;
        swim(N);
    }

    /**
     * Sinks the node k item
     */
    public void sink(int k) {
        // Check if node k has a child in bounds
        while (2 * k <= N) {

            // Pick the greater of the two children
            int j = 2 * k;
            if (j + 1 <= N && pq[j] < pq[j + 1]) j = 2 * k + 1;

            // Check if the child is greater than its parent
            if (pq[k] > pq[j]) break;

            // Swap if the child is greater than is parent
            swap(k, j);

            // Move up the node
            k = j;
        }
    }

    public int delMax() {
        int max = pq[1]; // Retrieve max from top
        swap(1, N); // Exchange with last item
        pq[N] = 0; // Avoid loitering
        sink(1); // Restore heap property
        N--;
        return max;
    }

    public void swap(int i, int j) {
        int temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    public static void main(String[] args) {

        System.out.println("Building a heap by inserting each element");

        MaxPQ pq = new MaxPQ(10);
        pq.insert(1);
        pq.insert(5);
        pq.insert(10);
        pq.insert(12);
        pq.insert(2);

        System.out.println("Removing items from heap");
        while (!pq.isEmpty()) {
            System.out.println(pq.delMax());
        }

        System.out.println("Building a heap by using bottom up heapify");
        MaxPQ maxPQ = new MaxPQ(new int[]{1, 5, 10, 12, 2});
        System.out.println("Removing items from heap");
        while (!maxPQ.isEmpty()) {
            System.out.println(maxPQ.delMax());
        }

    }


}
