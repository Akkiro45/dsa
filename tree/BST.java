package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import tree.Tree.Node;

public class BST {

    public Node search(Node node, int data) {
        if(node == null) {
            return null;
        }

        if(node.data == data) {
            return node;
        } else if(node.data > data) {
            return search(node.left, data);
        } else {
            return search(node.right, data);
        }
    }

    public Node insert(Node node, int data) {
        if(node == null) {
            return new Node(data);
        }

        if(data < node.data) {
            node.left = insert(node.left, data);
        } else {
            node.right = insert(node.right, data);
        }
        return node;
    }

    public Node inorderSuccessor(Node node) {
        if(node == null) {
            return null;
        }

        Node succ = inorderSuccessor(node.left);
        if(succ == null) {
            return node;
        } else {
            return succ;
        }
    }

    public Node delete(Node node, int data) {
        if(node == null) {
            return null;
        }

        if(node.data > data) {
            node.left = delete(node.left, data);
        } else if(node.data < data) {
            node.right = delete(node.right, data);
        } else {
            if(node.left == null) {
                return node.right;
            } else if(node.right == null) {
                return node.left;
            } else {
                Node succ = inorderSuccessor(node.right);
                node.data = succ.data;
                succ.data = data;
                node.right = delete(node.right, data);
            }
        }
        return node;
    }

    public Node constructTreeFromPreOrder(int[] preOrder, int min, int max) {
        if(Tree.preInd >= preOrder.length) {
            return null;
        }

        Node node = null;
        int currVal = preOrder[Tree.preInd];
        
        if(currVal > min && currVal < max) {
            node = new Node(currVal);
            Tree.preInd++;
            node.left = constructTreeFromPreOrder(preOrder, min, currVal);
            node.right = constructTreeFromPreOrder(preOrder, currVal, max);
        }

        return node;
    }

    public boolean checkBST(Node node, Node min, Node max) {
        if(node == null) {
            return true;
        }

        if(min != null && node.data <= min.data) {
            return false;
        }

        if(max != null && node.data >= max.data) {
            return false;
        }

        return checkBST(node.left, min, node) && checkBST(node.right, node, max);
    }

    public Node constructBST(int[] sortedArray, int beg, int end) {
        if(beg > end) {
            return null;
        }

        int mid = (beg + end) / 2;
        Node node = new Node(sortedArray[mid]);

        node.left = constructBST(sortedArray, beg, mid - 1);
        node.right = constructBST(sortedArray, mid + 1, end);

        return node;
    }

    public int catalan(int n) {
        if(n <= 1) {
            return 1;
        }

        int res = 0;

        for(int i=0; i<=n-1; i++) {
            res += catalan(i) * catalan(n - i - 1);
        }

        return res;
    }

    public List<Node> constructBSTs(int start, int end) {
        List<Node> trees = new ArrayList<>();
        if(start > end) {
            trees.add(null);
            return trees;
        }

        for(int i=start; i<=end; i++) {
            List<Node> leftSubtrees = constructBSTs(start, i-1);
            List<Node> rightSubtrees = constructBSTs(i+1, end);

            for(Node left : leftSubtrees) {
                for(Node right : rightSubtrees) {
                    Node node = new Node(i);
                    node.left = left;
                    node.right = right;
                    trees.add(node);
                }
            }
        }

        return trees;
    }

    public void displayZigZag(Node root) {
        System.out.print("\nZig zag order : ");
        if(root == null) {
            return;
        }
        Stack<Node> right = new Stack<>();
        Stack<Node> left = new Stack<>();
        right.push(root);
        int level = 0;
        Node node;

        while(!left.isEmpty() || !right.isEmpty()) {
            if(level % 2 == 0) {
                while(!right.isEmpty()) {
                    node = right.pop();
                    System.out.print(node.data + " ");
                    if(node.left != null) {
                        left.push(node.left);
                    }
                    if(node.right != null) {
                        left.push(node.right);
                    }
                }
            } else {
                while(!left.isEmpty()) {
                    node = left.pop();
                    System.out.print(node.data + " ");
                    if(node.right != null) {
                        right.push(node.right);
                    }
                    if(node.left != null) {
                        right.push(node.left);
                    }
                }
            }
            level++;
        }
    }

    public boolean areIdenticalBST(Node node1, Node node2) {
        if(node1 == null && node2 == null) {
            return true;
        }

        if(node1 == null || node2 == null) {
            return false;
        }
        if(node1.data != node2.data) {
            return false;
        }

        return (areIdenticalBST(node1.left, node2.left) && areIdenticalBST(node1.right, node2.right));
    }

    public int[] maxBSTSizeInTree(Node node) {
        int[] res = new int[]{-1, -1, 0, 0, 1};
        // res[0] // min of subtree
        // res[1] // max of subtree
        // res[2] // max size of bst
        // res[3] // curr bst size
        // res[4] // is bst
        if(node == null) {
            return res;
        }

        int[] left = maxBSTSizeInTree(node.left);
        int[] right = maxBSTSizeInTree(node.right);

        if(left[4] == 0 || right[4] == 0) {
            if(left[3] > right[3]) {
                left[4] = 0;
                return left;
            } else {
                right[4] = 0;
                return right;
            }
        }
    
        // isBST
        if((left[1] == -1 || left[1] < node.data) && (right[0] == -1 || right[0] > node.data)) {
            res[4] = 1; 
            res[2] = left[2] + right[2] + 1; // Curr total 
            res[3] = Math.max(res[2], Math.max(left[3], right[3])); // Max
        } else {
            res[4] = 0;
            res[2] = 0; // Curr total 
            res[3] = Math.max(left[3], right[3]); // Max
        }
        res[0] = left[0] == -1 ? node.data : left[0]; // min 
        res[1] = right[1] == -1 ? node.data : right[1]; // max

        return res;
    }

    class Pointers {
        Node prev = null;
        Node first = null;
        Node mid = null;
        Node last = null;
    }

    public void caclPointers(Node node, Pointers pointers) {
        if(node == null) {
            return;
        }
        caclPointers(node.left, pointers);
        if(pointers.prev != null && pointers.prev.data >= node.data) {
            if(pointers.first == null) {
                pointers.first = pointers.prev;
                pointers.mid = node;
            } else {
                pointers.last = node;
            }
        }
        pointers.prev = node;
        caclPointers(node.right, pointers);
    }

    public void restoreBST(Node root) {
        int temp;
        Pointers pointers = new Pointers();

        caclPointers(root, pointers);
        
        if(pointers.last == null) {
            temp = pointers.first.data;
            pointers.first.data = pointers.mid.data;
            pointers.mid.data = temp;
        } else {
            temp = pointers.first.data;
            pointers.first.data = pointers.last.data;
            pointers.last.data = temp;
        }
    }

    public static void main(String[] args) {
        Tree tree1 = new Tree();
        BST bst1 = new BST();

        // Insertion
        tree1.root = bst1.insert(null, 5);
        bst1.insert(tree1.root, 4);
        bst1.insert(tree1.root, 6);
        bst1.insert(tree1.root, 1);
        bst1.insert(tree1.root, 3);
        bst1.insert(tree1.root, 2);
        bst1.insert(tree1.root, 9);
        bst1.insert(tree1.root, 7);
        bst1.insert(tree1.root, 0);
        tree1.displayInOrder(tree1.root);

        // Search
        Node searchRes = bst1.search(tree1.root, 2);
        System.out.println("\nSearch : " + (searchRes == null ? "Not found" : "Found"));

        // Delete
        tree1.root = bst1.delete(tree1.root, 5);
        tree1.displayInOrder(tree1.root);

        // Construct BST from preOrder
        /*
                    10
                  /     \
                2        13
                  \      /
                   1    11
        */            

        int[] preOrder = new int[]{10, 2, 1, 13, 11};
        Tree.preInd = 0;
        tree1.root = bst1.constructTreeFromPreOrder(preOrder, Integer.MIN_VALUE, Integer.MAX_VALUE);
        Tree.preInd = 0;
        System.out.println("\nConstructed tree : ");
        tree1.displayInOrder(tree1.root);

        // Check BST
        tree1.root.left.data = 0;
        System.out.println("\nIs valid BST : " + bst1.checkBST(tree1.root, null, null));
        tree1.root.left.data = 2;
        System.out.println("Is valid BST : " + bst1.checkBST(tree1.root, null, null));

        // Construct BST from sorted array
        int[] sortedArray = new int[]{1, 2, 3, 4, 5, 6, 7};
        tree1.root = bst1.constructBST(sortedArray, 0, sortedArray.length - 1);
        System.out.println("\nConstructed tree : ");
        tree1.displayPreOrder(tree1.root);

        // Catalan numbers
        System.out.println("\nCatalan numbers : ");
        for(int i=0; i<10; i++) {
            System.out.print(bst1.catalan(i) + " ");
        }

        // Constrcut BST's
        System.out.println("\nGenerated BST's");
        List<Node> trees = bst1.constructBSTs(1, 3);
        for(Node root : trees) {
            tree1.displayPreOrder(root);
            System.out.println();
        }

        // Zig zag traversal
        tree1.root = new Node(1);
        tree1.root.right = new Node(3);
        tree1.root.right.right = new Node(5);
        tree1.root.left = new Node(2);
        tree1.root.left.right = new Node(4);
        tree1.root.left.right.right = new Node(7);
        tree1.root.left.right.left = new Node(6);
        bst1.displayZigZag(tree1.root);

        // Check Identical BSTs
        int[] sortedArray1 = new int[]{1, 2, 3, 4, 5, 6};
        tree1.root = bst1.constructBST(sortedArray, 0, sortedArray.length - 1);
        Tree tree2 = new Tree();
        tree2.root = bst1.constructBST(sortedArray1, 0, sortedArray1.length - 1);
        System.out.println("\nAre identical trees : " + bst1.areIdenticalBST(tree1.root, tree2.root));

        // Max size of bst in binary tree
        tree2.root = new Node(5);
        tree2.root.right = new Node(4);
        tree2.root.left = new Node(2);
        tree2.root.left.left = new Node(1);
        tree2.root.left.right = new Node(3);
        System.out.println("Max BST size in binary tree : " + bst1.maxBSTSizeInTree(tree2.root)[3]);
    
        // restore bst
        tree1.root = new Node(4);
        tree1.root.left = new Node(2);
        tree1.root.right = new Node(6);
        tree1.root.left.left = new Node(1);
        tree1.root.left.right = new Node(5);
        tree1.root.right.left = new Node(3);
        tree1.root.right.right = new Node(7);
        System.out.println("Is BST : " + bst1.checkBST(tree1.root, null, null));
        bst1.restoreBST(tree1.root);
        System.out.println("Is BST : " + bst1.checkBST(tree1.root, null, null));
    }
}
