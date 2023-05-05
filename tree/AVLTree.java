package tree;

class AVLNode {
    int height;
    int bf;
    AVLNode left;
    AVLNode right;
    int val;

    public AVLNode(int val) {
        this.height = 0;
        this.bf = 0;
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

public class AVLTree {
    public AVLNode root = null;
    public int nodeCount = 0;

    public int size() {
        return nodeCount;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int getHeight() {
        if(root == null) {
            return 0;
        }
        return root.height;
    }

    public boolean contains(int val) {
        return contains(root, val);
    }

    private boolean contains(AVLNode node, int val) {
        if(node == null) {
            return false;
        }
        if(node.val == val) {
            return true;
        } else if(node.val < val) {
            return contains(node.right, val);
        } else {
            return contains(node.left, val);
        }
    }

    public boolean insert(int val) {
        if(contains(val)) {
            return false;
        }

        root = insert(root, val);
        nodeCount++;
        return true;
    }

    public AVLNode insert(AVLNode node, int val) {
        if(node == null) {
            return new AVLNode(val);
        }

        if(node.val < val) {
            node.right = insert(node.right, val);
        } else {
            node.left = insert(node.left, val);
        }

        update(node);
        return rebalance(node);
    }

    public boolean delete(int val) {
        if(!contains(val)) {
            return false;
        }

        root = delete(root, val);
        nodeCount--;
        return true;
    }

    private AVLNode delete(AVLNode node, int val) {
        if(node == null) {
            return null;
        }

        if(node.val < val) {
            node.right = delete(node.right, val);
        } else if(node.val > val) {
            node.left = delete(node.left, val);
        } else {
            if(node.left == null) {
                return node.right;
            } else if(node.right == null) {
                return node.left;
            } else {
                int successor = findMin(node.right);
                node.val = successor;
                node.right = delete(node.right, successor);
            }
        }

        update(node);
        return rebalance(node);
    }

    private int findMin(AVLNode node) {
        if(node.left == null) {
            return node.val;
        }

        return findMin(node.left);
    }

    private void update(AVLNode node) {
        int leftHeight = node.left == null ? -1 : node.left.height;
        int rightHeight = node.right == null ? -1 : node.right.height;
        node.height = 1 + Math.max(leftHeight, rightHeight);

        leftHeight = node.left == null ? 0 : node.left.height;
        rightHeight = node.right == null ? 0 : node.right.height;
        node.bf = rightHeight - leftHeight;
    }

    private AVLNode rebalance(AVLNode node) {
        if(node.bf == -2) {
            if(node.left.bf <= 0) {
                // LeftLeft
                return rotateRight(node);
            } else {
                // LeftRight
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }
        } else if(node.bf == 2) {
            if(node.right.bf <= 0) {
                // RightLeft
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            } else {
                // RightRight
                return rotateLeft(node);
            }
        }
        return node;
    }

    private AVLNode rotateRight(AVLNode node) {
        AVLNode nodeLeft = node.left;
        AVLNode nodeLeftRight = node.left.right;

        nodeLeft.right = node;
        node.left = nodeLeftRight;

        update(node);
        update(nodeLeft);

        return nodeLeft;
    }

    private AVLNode rotateLeft(AVLNode node) {
        AVLNode nodeRight = node.right;
        AVLNode nodeRightLeft = node.right.left;

        nodeRight.left = node;
        node.right = nodeRightLeft;

        update(node);
        update(nodeRight);

        return nodeRight;
    }

    public void preOrder() {
        System.out.println("Pre Order");
        preOrder(root);
        System.out.println("\n----------");
    }

    private void preOrder(AVLNode node) {
        if(node == null) {
            return;
        }

        System.out.print(node.val + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(AVLNode node) {
        if(node == null) {
            return true;
        }

        if(node.bf < -1 || node.bf > 1) {
            return false;
        }
        return isBalanced(node.left) && isBalanced(node.right);
    }

    public boolean isBST() {
        return isBST(root);
    }

    private boolean isBST(AVLNode node) {
        if(node == null) {
            return true;
        }

        if(node.left != null && node.left.val > node.val) {
            return false;
        }
        if(node.right != null && node.right.val < node.val) {
            return false;
        }

        return isBST(node.left) && isBST(node.right);
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        tree.insert(5);
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(7);
        tree.delete(3);
        tree.delete(1);
        tree.insert(3);
        System.out.println(tree.isBST());
        System.out.println(tree.isBalanced());
        tree.preOrder();
    }
}
