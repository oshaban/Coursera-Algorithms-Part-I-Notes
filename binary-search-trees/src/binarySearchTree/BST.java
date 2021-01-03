package binarySearchTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Binary Search Tree Implementation. Binary Search Tree is not height balanced.
 */
public class BST {

    private TreeNode root;

    /**
     * Searches for a target number in the BST, iteratively
     *
     * @param target Target Number
     * @return TreeNode containing target, or null
     */
    public TreeNode searchItr(int target) {
        TreeNode cur = root;
        while (cur != null) {
            if (cur.val > target) {
                cur = cur.left;
            } else if (cur.val < target) {
                cur = cur.right;
            } else {
                return cur;
            }
        }
        return null;
    }

    /**
     * Searches for a target number in the BST, recursively
     *
     * @param target Target Number
     * @return TreeNode containing target, or null
     */
    public TreeNode searchRec(int target) {
        return search(root, target);
    }

    private TreeNode search(TreeNode root, int target) {
        if (root == null) return null;
        else if (root.val > target) return search(root.left, target);
        else if (root.val < target) return search(root.right, target);
        else return root;
    }

    /**
     * Finds the node containing the minimum value in the BST
     *
     * @return TreeNode containing the minimum value
     */
    public TreeNode findMin() {
        TreeNode cur = root;
        while (cur.left != null) cur = cur.left;
        return cur;
    }

    /**
     * Finds the node containing the maximum value in the BST
     *
     * @return TreeNode containing the maximum value
     */
    public TreeNode findMax() {
        TreeNode cur = root;
        while (cur.right != null) cur = cur.right;
        return cur;
    }

    /**
     * Inorder traverses the BST and returns a list of values
     *
     * @return List of values in inorder
     */
    public List<Integer> inorder() {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return list;
    }

    private void inorder(TreeNode root, List<Integer> list) {
        if (root == null) return;
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }

    /**
     * Inorder traverse the BST iteratively, and returns a list of values
     *
     * @return List of values in inorder
     */
    public List<Integer> inorderItr() {

        List<Integer> res = new ArrayList<>();

        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<>();

        while (true) {

            while (cur.left != null) {
                stack.push(cur);
                cur = cur.left;
            }

            if (stack.isEmpty()) break;
            cur = stack.pop();

            res.add(cur.val);

            cur = cur.right;
        }

        return res;
    }

    /**
     * Inserts into the BST
     *
     * @param val Value to insert
     */
    public void insert(int val) {
        root = insert(root, val);
    }

    private TreeNode insert(TreeNode root, int val) {
        if (root == null) {
            // Just came off a null link, create a new node, and return it
            return new TreeNode(val);
        } else if (root.val > val) {
            // Update the left link of the tree to have the value inserted in its left subtree
            root.left = insert(root.left, val);
        } else {
            // Update the right link of the tree to have the value inserted in its right subtree
            root.right = insert(root.right, val);
        }
        // Return the tree with the new value inserted
        return root;
    }


    /**
     * Deletes a key from the BST using Hibbard Deletion
     *
     * @param key key to delete
     */
    public void deleteNode(int key) {
        root = delete(root, key);
    }

    public TreeNode delete(TreeNode root, int key) {
        // Item not found in tree
        if (root == null) return null;

        if (root.val > key) {
            root.left = delete(root.left, key);
        } else if (root.val < key) {
            root.right = delete(root.right, key);
        } else {
            // Landed on the node to delete

            // Case 0: The node has no children
            if (root.left == null && root.right == null) return null;

            // Case 1: The node has 1 child
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            // Case 2: The node has 2 children
            TreeNode minNode = findMin(root.right);
            root.val = minNode.val;
            root.right = deleteMin(root.right);

            return root;
        }

        return root;
    }

    public TreeNode findMin(TreeNode root) {
        TreeNode cur = root;
        while (cur.left != null) cur = cur.left;
        return cur;
    }

    public TreeNode deleteMin(TreeNode root) {
        if (root == null) return root;

        if (root.left == null) {
            return root.right;
        }
        root.left = deleteMin(root.left);
        return root;
    }

    private static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

}
