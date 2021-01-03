package queue.generic;

import org.junit.Assert;

/**
 * A Generic Queue Implementation using Linked Lists
 */
public class Queue<Item> {

    ListNode<Item> head = null;
    ListNode<Item> end = null;

    public boolean isEmpty() {
        return head == null;
    }

    public Item remove() {
        // Remove from the front of the list
        ListNode<Item> oldHead = head;
        head = head.next;
        return oldHead.val;
    }

    public void add(Item item) {
        // Add onto the back of the list
        ListNode<Item> node = new ListNode<>();
        node.val = item;
        if (isEmpty()) {
            head = node;
            end = node;
            return;
        }
        end.next = node;
        end = end.next;
    }

    private static class ListNode<Item> {
        Item val;
        ListNode<Item> next = null;
    }

    // Driver
    public static void main(String[] args) {
        // to be or not to - be - - that - - - is
        Queue<String> queue = new Queue<>();
        queue.add("to");
        queue.add("be");
        queue.add("or");
        queue.add("not");
        queue.add("to");
        Assert.assertFalse(queue.isEmpty());
        Assert.assertEquals(queue.remove(), "to");
        queue.add("be");
        Assert.assertEquals(queue.remove(), "be");
        Assert.assertEquals(queue.remove(), "or");
        queue.add("that");
        Assert.assertEquals(queue.remove(), "not");
        Assert.assertEquals(queue.remove(), "to");
        Assert.assertEquals(queue.remove(), "be");
        Assert.assertEquals(queue.remove(), "that");
        Assert.assertTrue(queue.isEmpty());
    }

}
