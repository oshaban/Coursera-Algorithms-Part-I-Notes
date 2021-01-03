package stack.linkedlist.string;

import org.junit.Assert;

/**
 * A stack of String implementation using Linked Lists.
 */
public class StackStr {

    ListNode head = null;

    public void push(String str) {
        ListNode node = new ListNode();
        node.value = str;

        node.next = head;
        head = node;
    }

    public String pop() {
        if (this.isEmpty()) throw new RuntimeException();

        ListNode oldHead = head;
        head = head.next;
        return oldHead.value;
    }

    public boolean isEmpty() {
        return head == null;
    }

    private static class ListNode {
        String value;
        ListNode next = null;
    }

    // Driver
    public static void main(String[] args) {
        StackStr stack = new StackStr();
        stack.push("Hello");
        stack.push("World");

        Assert.assertFalse(stack.isEmpty());
        Assert.assertEquals(stack.pop(), "World");
        Assert.assertEquals(stack.pop(), "Hello");
        Assert.assertTrue(stack.isEmpty());

    }

}
