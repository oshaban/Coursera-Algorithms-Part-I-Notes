package problems.minstack;

import org.junit.Assert;

import java.util.Stack;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 */
public class MinStack {

    Stack<Integer> stack = new Stack<>();
    Stack<Integer> minSoFar = new Stack<>();

    public void push(int x) {
        stack.push(x);

        // Stores the min so far for the element in the main stack
        if (minSoFar.isEmpty()) {
            minSoFar.push(x);
        } else {
            minSoFar.push(Math.min(x, minSoFar.peek()));
        }
    }

    public void pop() {
        stack.pop();
        minSoFar.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minSoFar.peek();
    }

    public static void main(String[] args) {
        // -2, 0, -3
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        Assert.assertEquals(minStack.getMin(), -2);
        minStack.push(-3);
        Assert.assertEquals(minStack.getMin(), -3);
        minStack.pop();
        Assert.assertEquals(minStack.getMin(), -2);
        Assert.assertEquals(minStack.top(), 0);
    }

}
