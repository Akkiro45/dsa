package problems.hashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BinaryTreeVerticalOrder {

    private int left;
    private int right;
    private Map<Integer, List<Integer>> map;

    private void traverseTree(int[] tree, int i, int n, int currVerticalOrder) {
        if(i >= n) {
            return;
        }
        List<Integer> list = map.getOrDefault(currVerticalOrder, new ArrayList<>());
        list.add(tree[i]);
        map.put(currVerticalOrder, list);
        left = Math.min(left, currVerticalOrder);
        right = Math.max(right, currVerticalOrder);
        traverseTree(tree, ((2*i) + 1), n, currVerticalOrder - 1);
        traverseTree(tree, ((2*i) + 2), n, currVerticalOrder + 1);
    }
    
    public void displayVerticalOrder(int[] tree, int n) {
        left = 0;
        right = 0;
        map = new HashMap<>(n);
        
        traverseTree(tree, 0, n, 0);

        System.out.println("Vertical order : ");
        for(int i=left; i<=right; i++) {
            System.out.println(map.get(i));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] tree = new int[]{10, 7, 4, 3, 11, 14, 6};
        int n = tree.length;
        BinaryTreeVerticalOrder obj = new BinaryTreeVerticalOrder();
        obj.displayVerticalOrder(tree, n);
    }
}
