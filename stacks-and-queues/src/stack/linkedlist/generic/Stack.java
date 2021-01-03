package stack.linkedlist.generic;

import org.junit.Assert;

/**
 * Generic Stack Implementation using Linked Lists.
 */
public class Stack<Item> {

    ListNode<Item> head = null;

    public void push(Item item) {
        ListNode<Item> node = new ListNode<>();
        node.value = item;

        node.next = head;
        head = node;
    }

    public Item pop() {
        if (this.isEmpty()) throw new RuntimeException();

        ListNode<Item> oldHead = head;
        head = head.next;
        return oldHead.value;
    }

    public boolean isEmpty() {
        return head == null;
    }

    private static class ListNode<Item> {
        Item value;
        ListNode<Item> next = null;
    }

    // Driver
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.push("Hello");
        stack.push("World");

        Assert.assertFalse(stack.isEmpty());
        Assert.assertEquals(stack.pop(), "World");
        Assert.assertEquals(stack.pop(), "Hello");
        Assert.assertTrue(stack.isEmpty());

    }

}
