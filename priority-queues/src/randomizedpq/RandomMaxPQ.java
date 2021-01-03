package randomizedpq;

import priorityqueues.MaxPQ;
import java.util.Random;

/**
 * Randomized Priority Queue with sample() and delRandom()
 */
public class RandomMaxPQ extends MaxPQ {

    private Random random = new Random();

    public RandomMaxPQ(int N) {
        super(N);
    }

    // Returns a key that is chosen uniformly at random
    public int sample() {
        int r = random.nextInt(this.N) + 1; // Generate random number from 1 to N
        return pq[r];
    }

    // Deletes a key that is chosen uniformly at random
    public int delRandom() {
        int r = random.nextInt(this.N) + 1; // Generate random number from 1 to N
        int randomNumber = pq[r];
        swap(r, N); // Swap random number to last position in heap
        pq[N] = 0; // Clear out last position
        N--;
        sink(r); // Restore heap order
        return randomNumber;
    }

    public static void main(String[] args) {
        RandomMaxPQ pq = new RandomMaxPQ(10);
        pq.insert(1);
        pq.insert(5);
        pq.insert(10);
        pq.insert(12);
        pq.insert(2);

        System.out.println("Sampling 5 times:");
        for (int i = 0; i < 5; i++) {
            System.out.println(pq.sample());
        }

        System.out.println("Deleting random");
        while (!pq.isEmpty()) {
            System.out.println(pq.delRandom());
        }

    }

}
