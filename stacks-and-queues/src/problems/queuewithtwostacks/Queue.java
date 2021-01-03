package problems.queuewithtwostacks;

import org.junit.Assert;

import java.util.Stack;

/**
 * Queue with two stacks. Implement a queue with two stacks so that each queue operations takes a constant amortized number of stack operations.
 */
public class Queue {

    Stack<Integer> input = new Stack<>();
    Stack<Integer> output = new Stack<>(); // This will store the items in reverse order from the main stack, acting like a queue

    /** Push element x to the back of queue. */
    public void push(int x) {
        input.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (!output.isEmpty()) return output.pop();

        // Have to move everything over to the reverse stack, so it acts like a queue
        while (!input.isEmpty()) output.push(input.pop());
        return output.pop();
    }

    /** Get the front element. */
    public int peek() {
        if (!output.isEmpty()) return output.peek();

        // Have to move everything over to the reverse stack, so it acts like a queue
        while (!input.isEmpty()) output.push(input.pop());
        return output.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return input.isEmpty() && output.isEmpty();
    }

    public static void main(String[] args) {
        Queue queue = new Queue();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        Assert.assertEquals(queue.pop(), 1);
        Assert.assertEquals(queue.pop(), 2);
        Assert.assertEquals(queue.pop(), 3);
    }

}