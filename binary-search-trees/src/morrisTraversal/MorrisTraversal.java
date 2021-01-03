package morrisTraversal;

import java.util.ArrayList;
import java.util.List;

/**
 * Morris Traversal, an inorder traversal with O(1) space
 */
public class MorrisTraversal {

    public static void inorder(TreeNode root) {

        List<Integer> list = new ArrayList<>();

        TreeNode cur = root;
        while (cur != null) {

            if (cur.left == null) {
                list.add(cur.val);
                cur = cur.right;
            } else {
                TreeNode pred = findPred(cur);
                if (pred.right == null) {
                    pred.right = cur;
                    cur = cur.left;
                } else {
                    pred.right = null;
                    list.add(cur.val);
                    cur = cur.right;
                }
            }
        }

        System.out.println(list);
    }

    // Finds the predecessor of a node
    public static TreeNode findPred(TreeNode root) {
        TreeNode cur = root.left;
        while (cur.right != null && cur.right != root) cur = cur.right;
        return cur;
    }


    public static void main(String[] args) {
        /*
                   13
                /     \
               8      18
              / \    /  \
             6  11  14  20
         */
        int[] val = new int[] {0, 13, 8, 18, 6, 11, 14, 20};
        TreeNode root = buildTree(val, 1);
        inorder(root);
    }

    // Helper method to build the tree
    public static TreeNode buildTree(int[] nums, int i) {
        if (i >= nums.length) return null;
        TreeNode root = new TreeNode(nums[i]);
        root.left = buildTree(nums, 2*i);
        root.right = buildTree(nums, 2*i + 1);
        return root;
    }

    public static class TreeNode {
        public TreeNode(int val) {
            this.val = val;
        }
        private int val;
        private TreeNode right;
        private TreeNode left;
    }

}
