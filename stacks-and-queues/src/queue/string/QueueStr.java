package queue.string;

import org.junit.Assert;

/**
 * A Queue Implementation using LinkedLists, that stores Strings.
 */
public class QueueStr {

    ListNode head = null;
    ListNode end = null;

    public boolean isEmpty() {
        return head == null;
    }

    public String remove() {
        // Remove from the front of the list
        ListNode oldHead = head;
        head = head.next;
        return oldHead.val;
    }

    public void add(String str) {
        // Add onto the back of the list
        ListNode node = new ListNode();
        node.val = str;
        if (isEmpty()) {
            head = node;
            end = node;
            return;
        }
        end.next = node;
        end = end.next;
    }

    private static class ListNode {
        String val;
        ListNode next = null;
    }

    // Driver
    public static void main(String[] args) {
        // to be or not to - be - - that - - - is
        QueueStr queue = new QueueStr();
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
