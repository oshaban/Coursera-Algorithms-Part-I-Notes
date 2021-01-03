package redBlackBST;

/**
 * An implementation Balanced Binary Search Tree using Red-Black trees. Red-Black trees use balancing operations
 * to ensure the height of the tree remains log(N)
 */
public class RedBlackBST {

    private Node root = null;

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    public void insert(int val) {
        root = insert(root, val);
        root.color = BLACK;
    }

    private Node insert(Node root, int val) {
        if (root == null) {
            root = new Node(val);
        } else if (val < root.val) root.left = insert(root.left, val);
        else if (val > root.val) root.right = insert(root.right, val);

        if (!isRed(root.left) && isRed(root.right)) root = rotateLeft(root);
        if (isRed(root.left) && isRed(root.left.left)) root = rotateRight(root);
        if (isRed(root.left) && isRed(root.right)) colorFlip(root);

        return root;
    }

    private Node rotateLeft(Node h) {
        assert isRed(h.right);
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private Node rotateRight(Node h) {
        assert isRed(h.left);
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private void colorFlip(Node h) {
        assert !isRed(h);
        assert isRed(h.left);
        assert isRed(h.right);
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    private boolean isRed(Node h) {
        if (h == null) return false;
        return h.color == RED;
    }

    private static class Node {
        private int val;
        private Node left;
        private Node right;
        private boolean color = RED;

        public Node(int val) {
            this.val = val;
        }
    }

    // Helper method to inorder traverse
    public static void inorder(Node root) {
        if (root == null) return;
        inorder(root.left);
        System.out.println(root.val);
        inorder(root.right);
    }

    // Helper method to find tree height
    public static int height(Node root) {
        if (root == null) return 0;
        return 1 + Math.max(height(root.left), height(root.right));
    }

    // Example usage of Red-Black Tree
    public static void main(String[] args) {
        RedBlackBST bst = new RedBlackBST();
        for (int i = 0; i < 10; i++) {
            bst.insert(i);
        }

        inorder(bst.root);

        int h = height(bst.root);
        System.out.println("Height: " + h);
    }

}