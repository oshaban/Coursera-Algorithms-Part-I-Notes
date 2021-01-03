package problems.shufflelinkedlist;

import java.util.Random;

public class ShuffleLinkedList {

    private static Random random = new Random();

    /**
     * Shuffles a Linked List
     *
     * @param head Head of Linked List
     * @return Shuffled Linked List
     */
    public static ListNode sort(ListNode head) {

        // Empty list or list of size 1 is shuffled
        if (head == null || head.next == null) return head;

        // Find middle using slow and fast pointers
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = head;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        // slow will land on the middle, split into two halves
        prev.next = null;
        ListNode first = head;
        ListNode second = slow;

        first = sort(first);
        second = sort(second);

        return merge(first, second);

    }

    public static ListNode merge(ListNode first, ListNode second) {

        // Sentinel node to simplify merging
        ListNode dummyHead = new ListNode(-1);
        ListNode cur = dummyHead;

        while (first != null && second != null) {
            // Flip a coin to choose which node to pick for the merge
            int flip = random.nextInt(2);

            if (flip == 0) {
                cur.next = first;
                cur = cur.next;
                first = first.next;
            } else {
                cur.next = second;
                cur = cur.next;
                second = second.next;
            }

        }

        // Merge remaining, if any
        if (first != null) {
            cur.next = first;
        } else if (second != null) {
            cur.next = second;
        }

        return dummyHead.next;
    }


    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        ListNode dummyHead = new ListNode(-1);
        ListNode cur = dummyHead;
        for (int i = 0; i <= 6; i++) {
            cur.next = new ListNode(i);
            cur = cur.next;
        }
        ListNode head = dummyHead.next;


        // Print list
        cur = sort(head);
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }

    }

}
