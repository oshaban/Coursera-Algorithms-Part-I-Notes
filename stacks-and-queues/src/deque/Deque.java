package deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A Simple Deque implementation using Doubly Linked Lists
 */
public class Deque<Item> implements Iterable<Item> {

    private int size;
    private ListNode<Item> head;
    private ListNode<Item> tail;

    // construct an empty deque
    public Deque() {
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // return the number of items on the deque
    public int size() {
        return this.size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        ListNode<Item> newHead = new ListNode<>(item);
        if (this.isEmpty()) {
            this.head = newHead;
            this.tail = newHead;
        } else {
            newHead.next = head;
            this.head.prev = newHead;
            this.head = newHead;
        }
        this.size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        ListNode<Item> newTail = new ListNode<>(item);
        if (this.isEmpty()) {
            this.tail = newTail;
            this.head = newTail;
        } else {
            this.tail.next = newTail;
            newTail.prev = tail;
            this.tail = this.tail.next;
        }
        this.size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (this.isEmpty()) throw new NoSuchElementException();

        ListNode<Item> oldHead = head;
        if (this.size() == 1) {
            this.head = null;
            this.tail = null;
            this.size--;
            return oldHead.val;
        } else {
            this.head = this.head.next;
            this.head.prev = null;
            this.size--;
            return oldHead.val;
        }
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (this.isEmpty()) throw new NoSuchElementException();

        ListNode<Item> oldTail = this.tail;
        if (this.size() == 1) {
            this.head = null;
            this.tail = null;
            this.size--;
            return oldTail.val;
        } else {
            this.tail = tail.prev;
            this.tail.next = null;
            this.size--;
            return oldTail.val;
        }
    }

    // return an iterator over items in order from front to back
    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {

            private ListNode<Item> cur = head;

            @Override
            public boolean hasNext() {
                return cur != null;
            }

            @Override
            public Item next() {
                if (!hasNext()) throw new NoSuchElementException();
                Item item = cur.val;
                cur = cur.next;
                return item;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

        };
    }

    private static class ListNode<Item> {
        Item val;
        ListNode<Item> next;
        ListNode<Item> prev;

        private ListNode(Item val) {
            this.val = val;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {

        Deque<Integer> deque = new Deque<>();
        System.out.println(deque.isEmpty());
        deque.addFirst(3);
        deque.addFirst(2);
        deque.addFirst(1);
        deque.addLast(4);
        // ----->
        // 1 2 3 4

        System.out.println("-----");
        for (Integer num : deque) {
            System.out.print(num + " ");
        }
        System.out.println("-----");

        System.out.println(deque.removeFirst()); // 1
        System.out.println(deque.removeLast()); // 4
        System.out.println(deque.isEmpty()); // false
        System.out.println(deque.removeLast()); // 3
        System.out.println(deque.removeFirst()); // 2
        System.out.println(deque.isEmpty()); // true

    }

}