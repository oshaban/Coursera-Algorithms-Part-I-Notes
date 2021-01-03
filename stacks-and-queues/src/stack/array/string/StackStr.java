package stack.array.string;

import org.junit.Assert;

import java.util.Arrays;

/**
 * A stack of String implementation using re-sizing arrays.
 */
public class StackStr {

    String[] items = new String[1];
    int N = 0;

    public void push(String str) {

        // Array is full, double it
        if (N == items.length) {
            String[] temp = Arrays.copyOf(items, items.length * 2);
            items = temp;
        }
        items[N] = str;
        N++;
    }

    public String pop() {
        String temp = items[N-1];
        items[N-1] = null; // Clear out the spot in the array
        N--;

        // Array is a quarter empty, half it
        if (items.length / 4 >= N) {
            String[] itemsTemp = Arrays.copyOf(items, items.length/2);
            items = itemsTemp;
        }

        return temp;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    // Driver
    public static void main(String[] args) {
        StackStr stack = new StackStr();
        stack.push("to");
        stack.push("be");
        stack.push("or");
        stack.push("not");
        stack.push("to");
        Assert.assertFalse(stack.isEmpty());
        Assert.assertEquals(stack.pop(), "to");
        stack.push("be");
        Assert.assertEquals(stack.pop(), "be");
        Assert.assertEquals(stack.pop(), "not");
        stack.push("that");
        Assert.assertEquals(stack.pop(), "that");
        Assert.assertEquals(stack.pop(), "or");
        Assert.assertEquals(stack.pop(), "be");
        Assert.assertEquals(stack.pop(), "to");
        Assert.assertTrue(stack.isEmpty());
    }

}
