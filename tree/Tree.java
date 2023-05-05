package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Tree {

    Node root = null;
    static int preInd = 0;
    static int postInd = 0;
    static int maxLevel = -1;
    static int maxSum = 0;

    static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public void displayPreOrder(Node node) {
        if(node == null) {
            return;
        }
        System.out.print(node.data + " ");
        displayPreOrder(node.left);
        displayPreOrder(node.right);
    }

    public void displayInOrder(Node node) {
        if(node == null) {
            return;
        }

        displayInOrder(node.left);
        System.out.print(node.data + " ");
        displayInOrder(node.right);
    }

    public void displayPostOrder(Node node) {
        if(node == null) {
            return;
        }

        displayPostOrder(node.left);
        displayPostOrder(node.right);
        System.out.print(node.data + " ");
    }


    public Node buildTreeFromPreAndInOrder(int[] preOrder, int[] inOrder, int start, int end) {
        if(start > end) {
            return null;
        }
        
        Node node = new Node(preOrder[preInd]);

        for(int j=start; j<=end; j++) {
            if(inOrder[j] == node.data) {
                preInd++;
                node.left = buildTreeFromPreAndInOrder(preOrder, inOrder, start, j - 1);
                node.right = buildTreeFromPreAndInOrder(preOrder, inOrder, j + 1, end);
                return node;
            }
        }

        return null;
    }

    public Node buildTreeFromPostAndInOrder(int[] postOrder, int[] inOrder, int start, int end) {
        if(start > end) {
            return null;
        }

        Node node = new Node(postOrder[postInd]);

        for(int i=start; i<=end; i++) {
            if(inOrder[i] == node.data) {
                postInd--;
                node.right = buildTreeFromPostAndInOrder(postOrder, inOrder, i + 1, end);
                node.left = buildTreeFromPostAndInOrder(postOrder, inOrder, start, i - 1);
                return node;
            }
        }
        
        return null;
    }

    public void levelOrderTraversal() {
        if(root == null) {
            return;
        }
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        Node curr;
        System.out.println("Level order traversal : ");
        while(!q.isEmpty() || q.peek() != null) {
            curr = q.poll();
            System.out.print(curr.data + " ");
            if(curr.left != null) {
                q.add(curr.left);
            }
            if(curr.right != null) {
                q.add(curr.right);
            }
        }
        System.out.println();
    }

    public int sumOfNodesAtKthLevel(int k) {
        int sum = 0;
        if(root == null) {
            return -1;
        }
        int level = 0;
        Queue<Node> q = new LinkedList<>();
        Node curr;
        q.add(root);
        q.add(null);

        while(!q.isEmpty() && level != k) {
            curr = q.poll();
            if(q.isEmpty()) {
                break;
            }
            if(curr == null) {
                level++;
                q.add(null);
                continue;
            }
            if(curr.left != null) {
                q.add(curr.left);
            }
            if(curr.right != null) {
                q.add(curr.right);
            }
        }

        while(!q.isEmpty() && q.peek() != null) {
            sum += q.poll().data;
        }

        return level == k ? sum : -1;
    }

    public void displayLevels() {
        if(root == null) {
            return;
        }
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        q.add(null);
        Node curr;
        int level = 0;

        System.out.println("Level order traversal : ");
        System.out.println("Level " + level);
        while(!q.isEmpty()) {
            curr = q.poll();
            if(q.isEmpty()) {
                break;
            }
            if(curr == null) {
                System.out.println();
                System.out.println("Level " + ++level);
                q.add(null);
                continue;
            }
            System.out.print(curr.data + " ");
            if(curr.left != null) {
                q.add(curr.left);
            }
            if(curr.right != null) {
                q.add(curr.right);
            }
        }
        System.out.println();
    }

    public int getCount(Node node) {
        if(node == null) {
            return 0;
        }

        return (1 + getCount(node.left) + getCount(node.right));
    }

    public long getSum(Node node) {
        if(node == null) {
            return 0;
        }

        return (node.data + getSum(node.left) + getSum(node.right));
    }

    public int getHeight(Node node) {
        if(node == null) {
            return 0;
        }
        
        return (1 + (Math.max(getHeight(node.left), getHeight(node.right))));
    }

    // O(n^2)
    public int getDiameter(Node node) {
        if(node == null) {
            return 0;
        }

        int lH = getHeight(node.left);
        int rH = getHeight(node.right);

        int lD = getDiameter(node.left);
        int rD = getDiameter(node.right);
        
        int currD = lH + rH + 1;
        
        return Math.max(
            currD,
            Math.max(lD, rD)
        );
    }

    // O(n)
    public int[] getDiameterOptimized(Node node) {
        if(node == null) {
            return new int[]{0, 0};
        }

        int[] leftRes = getDiameterOptimized(node.left);
        int[] rightRes = getDiameterOptimized(node.right);
        int lD = leftRes[0];
        int rD = rightRes[0];
        int lH = leftRes[1];
        int rH = rightRes[1];
        int currD = lH + rH + 1;
        
        int height = Math.max(lH, rH) + 1;
        int diameter = Math.max(
            currD,
            Math.max(lD, rD)
        );
        return new int[]{diameter, height};
    }

    public void sumReplacment(Node node) {
        if(node == null) {
            return;
        }

        sumReplacment(node.left);
        sumReplacment(node.right);
        if(node.left != null) {
            node.data += node.left.data;
        }
        if(node.right != null) {
            node.data += node.right.data;
        }
    }

    // O(n^2)
    public boolean isBalancedBinaryTree(Node node) {
        if(node == null) {
            return true;
        }

        if(isBalancedBinaryTree(node.left) == false) {
            return false;
        }
        if(isBalancedBinaryTree(node.right) == false) {
            return false;
        }

        return Math.abs(
            getHeight(node.left) - getHeight(node.right)
        ) <= 1;
    }

    // O(n)
    public int[] isBalancedBinaryTreeOptimized(Node node) {
        if(node == null) {
            return new int[]{1, 0};
        }

        int[] leftRes = isBalancedBinaryTreeOptimized(node.left);
        if(leftRes[0] == 0) {
            return leftRes;
        }
        int[] rightRes = isBalancedBinaryTreeOptimized(node.right);
        if(rightRes[0] == 0) {
            return rightRes;
        }

        int[] res = new int[2];
        res[0] = Math.abs(
            leftRes[1] - rightRes[1]
        ) <= 1 ? 1 : 0;
        res[1] = Math.max(leftRes[1], rightRes[1]) + 1; 
        return res;
    }

    // // Time O(n), Space O(2^n)
    // public void displayLeftView(Node root) {
    //     Queue<Node> q = new LinkedList<>();
    //     System.out.println("Left View : ");
    //     Node prev, curr;

    //     if(root == null) {
    //         return;
    //     }
    //     q.add(root);
    //     q.add(null);
    //     prev = null;

    //     while(!q.isEmpty()) {
    //         curr = q.remove();
    //         if(q.isEmpty()) {
    //             break;
    //         }
    //         if(curr == null) {
    //             q.add(null);
    //             prev = curr;
    //             continue;
    //         }
    //         if(curr.left != null) {
    //             q.add(curr.left);
    //         }
    //         if(curr.right != null) {
    //             q.add(curr.right);
    //         }
    //         if(prev == null) {
    //             System.out.print(curr.data + " ");
    //         }
    //         prev = curr;
    //     }
    // }

    // Another approach Time O(n), Space O(2^n)
    public void displayLeftView(Node root) {
        System.out.println("Left View : ");
        if(root == null) {
            return;
        }

        Queue<Node> q = new LinkedList<>();
        int n;
        Node curr;
        
        q.add(root);

        while(!q.isEmpty()) {
            n = q.size();
            for(int i=0; i<n; i++) {
                curr = q.remove();
                if(i == 0) {
                    System.out.print(curr.data + " ");
                }
                
                if(curr.left != null) {
                    q.add(curr.left);
                }
                if(curr.right != null) {
                    q.add(curr.right);
                }
            }
        }
    }

    // Time O(n), Space O(m[height]) 
    public void displayLeftViewUsingRec(Node node, int level) {
        if(node == null) {
            return;
        }

        if(level > maxLevel) {
            maxLevel = level;
            System.out.print(node.data + " ");
        }

        displayLeftViewUsingRec(node.left, level + 1);
        displayLeftViewUsingRec(node.right, level + 1);
    }

    // Time O(n), Space O(2^n)
    public void displayRightView(Node root) {
        System.out.println("Right View : ");
        if(root == null) {
            return;
        }

        Queue<Node> q = new LinkedList<>();
        int n;
        Node curr;
        
        q.add(root);

        while(!q.isEmpty()) {
            n = q.size();
            for(int i=0; i<n; i++) {
                curr = q.remove();
                if(curr.left != null) {
                    q.add(curr.left);
                }
                if(curr.right != null) {
                    q.add(curr.right);
                }
                if(i == n-1) {
                    System.out.print(curr.data + " ");
                }
            }
        }
    }

    // Time O(n), Space O(m[height]) 
    public void displayRightViewUsingRec(Node node, int level) {
        if(node == null) {
            return;
        }

        if(level > maxLevel) {
            maxLevel = level;
            System.out.print(node.data + " ");
        }

        displayLeftViewUsingRec(node.right, level + 1);
        displayLeftViewUsingRec(node.left, level + 1);
    }

    public boolean getPath(Node node, int data, List<Node> path) {
        if(node == null) {
            return false;
        }
        path.add(node);
        if(node.data == data) {
            return true;
        }
        if(getPath(node.left, data, path) || getPath(node.right, data, path)) {
            return true;
        }
        path.remove(path.size() - 1);
        return false;
    }

    public int leastCommonAncestor(int n1, int n2) {
        List<Node> path1 = new ArrayList<>();
        List<Node> path2 = new ArrayList<>();
        if(!(getPath(root, n1, path1) && getPath(root, n2, path2))) {
            return -1;
        }

        int i = 0;
        for(i=0; i<Math.min(path1.size(), path2.size()); i++) {
            if(path1.get(i).data != path2.get(i).data) {
                break;
            }
        }
        return path1.get(i-1).data;
    }

    public Node leastCommonAncestorOptimized(Node node, int n1, int n2) {
        if(node == null) {
            return null;
        }

        if(node.data == n1 || node.data == n2) {
            return node;
        }

        Node leftLCS = leastCommonAncestorOptimized(node.left, n1, n2);
        Node rightLCS = leastCommonAncestorOptimized(node.right, n1, n2);

        if(leftLCS != null && rightLCS != null) {
            return node;
        }

        if(leftLCS != null) {
            return leftLCS;
        }
        if(rightLCS != null) {
            return rightLCS;
        }
        return null;
    }

    public int getDistance(Node node, int n) {
        if(node == null) {
            return -1;
        }

        if(node.data == n) {
            return 0;
        }

        int leftD = getDistance(node.left, n);
        if(leftD != -1) {
            return leftD + 1;
        }
        int rightD = getDistance(node.right, n);
        if(rightD != -1) {
            return rightD + 1;
        }

        return -1;
    }

    public int shortesDist(int n1, int n2) {
        Node lca = leastCommonAncestorOptimized(root, n1, n2);
        if(lca == null) {
            return -1;
        }

        return (getDistance(lca, n1) + getDistance(lca, n2));
    }

    public Node flattenTree(Node node) {
        if(node == null) {
            return null;
        }

        Node leftTail = flattenTree(node.left);
        Node rightTail = flattenTree(node.right);

        if(leftTail == null && rightTail == null) {
            return node;
        }

        if(node.left != null) {
            leftTail.right = node.right;
            node.right = node.left;
        }
        node.left = null;

        return rightTail == null ? leftTail : rightTail;
    }

    public void displayFlattenTree() {
        Node node = root;
        while(node != null) {
            System.out.print(node.data + " ");
            node = node.right;
        }
    }

    public void displayDescendants(Node node, int k) {
        if(node == null) {
            return;
        }
        if(k == 0) {
            System.out.print(node.data + " ");
            return;
        }
        displayDescendants(node.left, k - 1);
        displayDescendants(node.right, k - 1);
    }

    public int displayNodesAtDistK(Node node, Node target, int k) {
        if(node == null) {
            return -1;
        }
        if(node == target) {
            displayDescendants(node, k);
            return 0;
        }

        int dl = displayNodesAtDistK(node.left, target, k);
        if(dl != -1) {
            if((dl + 1) == k) {
                System.out.print(node.data + " ");
            } else if((dl + 1) < k) {
                displayDescendants(node.right, k - (dl + 2));
            }
            return (dl + 1);
        }
        int dr = displayNodesAtDistK(node.right, target, k);
        if(dr != -1) {
            if((dr + 1) == k) {
                System.out.print(node.data + " ");
            } else if((dr + 1) < k) {
                displayDescendants(node.left, k - (dr + 2));
            }
            return (dr + 1);
        }
        return -1;
    }

    public int maxPathSum(Node node) {
        if(node == null) {
            return 0;
        }

        int leftChildSum = maxPathSum(node.left);
        int rightChildSum = maxPathSum(node.right);

        int currMax = 0;
        currMax = Math.max(currMax, node.data);
        currMax = Math.max(currMax, leftChildSum + node.data);
        currMax = Math.max(currMax, rightChildSum + node.data);
        int singlePathSum = currMax;
        currMax = Math.max(currMax, (node.data + leftChildSum + rightChildSum));
        maxSum = Math.max(maxSum, currMax);
        return singlePathSum;
    }

    public static void main(String[] args) {
        Tree tree1 = new Tree();
        Node node = new Node(1);
        tree1.root = node;
        node = new Node(2);
        tree1.root.left = node;
        node = new Node(3);
        tree1.root.right = node;
        node = new Node(4);
        tree1.root.left.left = node;
        node = new Node(5);
        tree1.root.left.right = node;
        node = new Node(6);
        tree1.root.right.left = node;
        node = new Node(7);
        tree1.root.right.right = node;

        System.out.println("Pre Order");
        tree1.displayPreOrder(tree1.root);
        System.out.println();

        System.out.println("In Order");
        tree1.displayInOrder(tree1.root);
        System.out.println();

        System.out.println("Post Order");
        tree1.displayPostOrder(tree1.root);
        System.out.println();

        int[] preOrder = new int[]{1, 2, 4, 3, 5};
        int[] inOrder = new int[]{4, 2, 1, 5, 3};
        int[] postOrder = new int[]{4, 2, 5, 3, 1};

        Tree tree2 = new Tree();
        tree2.root = tree2.buildTreeFromPreAndInOrder(preOrder, inOrder, 0, inOrder.length - 1);
        System.out.println("In Order");
        tree2.displayInOrder(tree2.root);
        System.out.println();

        Tree tree3 = new Tree();
        Tree.postInd = inOrder.length - 1;
        tree3.root = tree3.buildTreeFromPostAndInOrder(postOrder, inOrder, 0, inOrder.length - 1);
        System.out.println("In Order");
        tree3.displayInOrder(tree3.root);
        System.out.println();

        tree3.levelOrderTraversal();
        tree3.displayLevels();
        System.out.println(tree3.sumOfNodesAtKthLevel(2));
        System.out.println("Count : " + tree3.getCount(tree3.root));
        System.out.println("Sum : " + tree3.getSum(tree3.root));
        System.out.println("Height : " + tree3.getHeight(tree3.root));
        System.out.println("Diameter : " + tree3.getDiameter(tree3.root));
        System.out.println("Diameter(Optimized) : " + tree3.getDiameterOptimized(tree3.root)[0]);

        tree3.levelOrderTraversal();
        tree3.sumReplacment(tree3.root);
        System.out.println("After sum replacement");
        tree3.levelOrderTraversal();
        System.out.println("Is Balanced : " + tree3.isBalancedBinaryTree(tree3.root));
        System.out.println("Is Balanced : " + tree3.isBalancedBinaryTreeOptimized(tree3.root)[0]);

        Tree tree4 = new Tree();
        node = new Node(1);
        tree4.root = node;
        tree4.root.left = new Node(2);
        tree4.root.left.left = new Node(3);
        System.out.println("Is Balanced : " + tree4.isBalancedBinaryTree(tree4.root));
        System.out.println("Is Balanced : " + tree4.isBalancedBinaryTreeOptimized(tree4.root)[0]);
    
        Tree tree5 = new Tree();
        node = new Node(1);
        tree5.root = node;
        tree5.root.left = new Node(2);
        tree5.root.right = new Node(3);
        tree5.root.right.left = new Node(4);

        tree5.displayLeftView(tree5.root);
        System.out.println("\nLeft View using Rec : ");
        Tree.maxLevel = -1;
        tree5.displayLeftViewUsingRec(tree5.root, 0);
        System.out.println();
        tree5.displayRightView(tree5.root);
        System.out.println("\nRight View using Rec : ");
        Tree.maxLevel = -1;
        tree5.displayRightViewUsingRec(tree5.root, 0);
        System.out.println();

        Tree tree6 = new Tree();
        node = new Node(1);
        tree6.root = node;
        node = new Node(2);
        tree6.root.left = node;
        node = new Node(3);
        tree6.root.right = node;
        node = new Node(4);
        tree6.root.right.left = node;
        node = new Node(5);
        tree6.root.right.left.left = node;
        node = new Node(6);
        tree6.root.right.right = node;

        /*
                    1
                  /   \
                2       3
                      /   \
                    4       6
                  /
                5
        */

        List<Node> path = new ArrayList<>();
        System.out.println("Path exist : " + tree6.getPath(tree6.root, 6, path));
        System.out.println("Path : ");
        for(Node nd : path) {
            System.out.print(nd.data + " ");
        }
        System.out.println();
        System.out.println("Least common anncestor : " + tree6.leastCommonAncestor(5, 6));
        System.out.println("Least common anncestor : " + tree6.leastCommonAncestorOptimized(tree6.root, 5, 6).data);
        System.out.println("Distance : " + tree6.getDistance(tree6.root, 5));
        System.out.println("Shortest distance : " + tree6.shortesDist(5, 2));
    
        tree5.flattenTree(tree5.root);
        System.out.println("Flatten tree : ");
        tree5.displayFlattenTree();
        
        System.out.println("\nDescendants : ");
        tree6.displayDescendants(tree6.root, 2);
        System.out.println("\nNodes at distance k : ");
        tree6.displayNodesAtDistK(tree6.root, tree6.root.right.left.left, 3);

        Tree tree7 = new Tree();
        tree7.root = new Node(1);
        tree7.root.left = new Node(-12);
        tree7.root.left.left = new Node(4);
        tree7.root.right = new Node(3);
        tree7.root.right.left = new Node(5);
        tree7.root.right.right = new Node(-6);
        System.out.println("\nMax path sum : " + tree7.maxPathSum(tree7.root));
    }
}
