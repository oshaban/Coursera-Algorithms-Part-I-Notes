package problems.maxstack;

import java.util.Stack;


/**
 * Stack with max. Create a data structure that efficiently supports the stack operations (push and pop) and also a return-the-maximum operation. Assume the elements are real numbers so that you can compare them.
 */
public class MaxStack {

    Stack<Integer> stack = new Stack<>();
    Stack<Integer> maxSoFarStack = new Stack<>(); // Keeps track of max value so far for each position in the stack

    public void push(int x) {
        stack.push(x);
        if (maxSoFarStack.isEmpty()) {
            maxSoFarStack.push(x);
        } else {
            maxSoFarStack.push(Math.max(maxSoFarStack.peek(), x));
        }
    }

    public int pop() {
        int temp = stack.pop();
        maxSoFarStack.pop();
        return temp;
    }

    public int top() {
        return stack.peek();
    }

    public int peekMax() {
        return maxSoFarStack.peek();
    }

    public int popMax() {
        // We need to find where the max value is in the stack, remove it, then recalculate all
        // of the max values in maxSoFar
        int maxVal = maxSoFarStack.peek();

        // To remove we use a temp stack
        Stack<Integer> temp = new Stack<>();
        while (!stack.isEmpty() && stack.peek() != maxVal) {
            temp.push(stack.pop()); // Add to temp
            maxSoFarStack.pop(); // Remove corresponding max value
        }
        // Remove that value from the stack since we found where it is
        stack.pop();
        maxSoFarStack.pop();

        // Re add all elements to the stacks
        while (!temp.isEmpty()) {
            push(temp.pop());
        }

        return maxVal;
    }

    public static void main(String[] args) {

    }

}