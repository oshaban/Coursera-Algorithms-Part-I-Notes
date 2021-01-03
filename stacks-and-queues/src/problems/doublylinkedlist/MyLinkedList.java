package problems.doublylinkedlist;

/**
 * Doubly Linked List Implementation that stores Integers
 */
public class MyLinkedList {

    int size = 0;
    ListNode pre = new ListNode(-1); // Sentinel node before the first item
    ListNode post = new ListNode(-1); // Sentinel node after the last item

    /**
     * Initialize your data structure here.
     */
    public MyLinkedList() {
        // Link up the sentinel nodes
        pre.next = post;
        post.prev = pre;
    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        if (index >= size) return -1; // Out of bounds

        ListNode cur = pre.next; // Start at the head of the list

        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.val;
    }

    public ListNode getNode(int index) {
        if (index >= size) return null; // Out of bounds

        ListNode cur = pre.next; // Start at head of list

        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {

        ListNode newNode = new ListNode(val);
        ListNode oldHead = pre.next;

        // Link the new node to be the head
        pre.next = newNode;
        newNode.prev = pre;

        // Point new node to the old head
        newNode.next = oldHead;
        oldHead.prev = newNode;

        size++;
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {

        ListNode newNode = new ListNode(val);
        ListNode oldTail = post.prev;

        // Point new node to be the new tail
        oldTail.next = newNode;
        newNode.prev = oldTail;

        // Link new node to the post node
        newNode.next = post;
        post.prev = newNode;

        size++;
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {

        if (index > size) {
            return;
        } else if (index == size) {
            addAtTail(val);
        } else {
            ListNode curNode = getNode(index); // Get node at that index
            ListNode prev = curNode.prev;

            ListNode newNode = new ListNode(val);

            prev.next = newNode;
            newNode.prev = prev;

            newNode.next = curNode;
            curNode.prev = newNode;

            size++;
        }
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {

        if (index >= size) return; // invalid index

        ListNode nodeToDelete = getNode(index);

        ListNode prev = nodeToDelete.prev;
        ListNode next = nodeToDelete.next;

        prev.next = next;
        next.prev = prev;

        size--;
    }

    public static class ListNode {

        public ListNode(int val) {
            this.val = val;
        }

        int val;
        ListNode prev;
        ListNode next;
    }

}
