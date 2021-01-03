import edu.princeton.cs.algs4.StdRandom;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    // initial capacity of underlying resizing array
    private static final int INIT_CAPACITY = 8;

    private int n; // Position to insert into
    private Item[] items; // Array of items

    // construct an empty randomized queue
    public RandomizedQueue() {
        this.items = (Item[]) new Object[INIT_CAPACITY];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();

        // Array is full, so double it; repeated doubling
        if (n >= items.length) {
            this.items = Arrays.copyOf(this.items, this.items.length * 2);
        }
        this.items[n] = item;
        n++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();

        int randomIndex = StdRandom.uniform(n);
        Item randomItem = this.items[randomIndex];

        // Swap the last element in the current position
        this.items[randomIndex] = this.items[n - 1];
        this.items[n - 1] = null;
        this.n--;

        // Shrink array if necessary
        if (n > 0 && n < this.items.length / 4) {
            this.items = Arrays.copyOf(this.items, this.items.length / 2);
        }

        return randomItem;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();

        int randomIndex = StdRandom.uniform(n);
        return this.items[randomIndex];
    }

    // return an iterator over items in order from front to back
    @Override
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int i = n;
        private int[] order;

        public RandomizedQueueIterator() {
            order = new int[i];
            for (int j = 0; j < i; ++j) {
                order[j] = j;
            }
            StdRandom.shuffle(order);
        }

        public boolean hasNext() {
            return i > 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return items[order[--i]];
        }
    }

    // unit testing (required)
    public static void main(String[] args) {

        RandomizedQueue<Character> randomizedQueue = new RandomizedQueue<>();

        randomizedQueue.enqueue('a');
        randomizedQueue.enqueue('b');
        randomizedQueue.enqueue('c');
        System.out.println(randomizedQueue.size());
        System.out.println(randomizedQueue.isEmpty());

        System.out.println("-----Sampling-----");
        System.out.println(randomizedQueue.sample());
        System.out.println(randomizedQueue.sample());
        System.out.println(randomizedQueue.sample());
        System.out.println(randomizedQueue.sample());
        System.out.println(randomizedQueue.sample());
        System.out.println("-----End Sampling-----");

        System.out.println(randomizedQueue.dequeue());
        System.out.println(randomizedQueue.dequeue());
        System.out.println(randomizedQueue.dequeue());
        System.out.println(randomizedQueue.isEmpty());

    }

}
