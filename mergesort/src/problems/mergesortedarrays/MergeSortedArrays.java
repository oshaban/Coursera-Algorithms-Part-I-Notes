package problems.mergesortedarrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class MergeSortedArrays {

    public static List<Integer> mergeSortedList(List<List<Integer>> lists) {

        // Use a minheap to extract the next min value during the merge step of merge sort
        // Each List will have an ArrayNode as a pointer to the value that is being considered for the merge
        PriorityQueue<ArrayNode> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));


        // Convert the ArrayList into a Linked List representation
        for (List<Integer> list : lists) {
            if (!list.isEmpty()) {
                ArrayNode head = new ArrayNode(list.get(0));
                ArrayNode cur = head;
                for (int i = 1; i < list.size(); i++) {
                    ArrayNode next = new ArrayNode(list.get(i));
                    cur.next = next;
                    cur = cur.next;
                }
                minHeap.add(head);
            }
        }

        List<Integer> res = new ArrayList<>();

        while (!minHeap.isEmpty()) {

            // Gets the smallest value out of all lists during the merge
            ArrayNode node = minHeap.poll();
            res.add(node.val);

            // Move the pointer for that list up
            if (node.next != null) {
                minHeap.add(node.next);
            }
        }

        return res;
    }

    public static class ArrayNode {
        int val;
        ArrayNode next;

        public ArrayNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 4, 5));
        List<Integer> list2 = new ArrayList<>(Arrays.asList(1, 3, 4));
        List<Integer> list3 = new ArrayList<>(Arrays.asList(2, 6));
        List<List<Integer>> lists = new ArrayList<>();
        lists.add(list1);
        lists.add(list2);
        lists.add(list3);

        List<Integer> res = mergeSortedList(lists);
        System.out.println(res);
    }

}
