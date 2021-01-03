package problems.mergesortedqueues;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MergeSortedQueues {

    public static void main(String[] args) {

    /*
        q1: [1,2,3]
        q2: [2,5,6]
     */

        Queue<Integer> q1 = new LinkedList<>(Arrays.asList(1,2,3));
        Queue<Integer> q2 = new LinkedList<>(Arrays.asList(2,5,6));

        Queue<Integer> res = merge(q1, q2);

        System.out.println(res);

    }

    public static Queue<Integer> merge(Queue<Integer> q1, Queue<Integer> q2) {
        Queue<Integer> q1Temp = new LinkedList<>(q1);
        Queue<Integer> q2Temp = new LinkedList<>(q2);
        Queue<Integer> res = new LinkedList<>();

        while (!q1Temp.isEmpty() && !q2Temp.isEmpty()) {
            if (q1Temp.peek() <= q2Temp.peek()) {
                res.add(q1Temp.poll());
            } else {
                res.add(q2Temp.poll());
            }
        }

        while (!q1Temp.isEmpty()) res.add(q1Temp.poll());
        while (!q2Temp.isEmpty()) res.add(q2Temp.poll());

        return res;
    }

}
