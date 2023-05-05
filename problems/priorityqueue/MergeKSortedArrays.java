package problems.priorityqueue;

import java.util.PriorityQueue;

public class MergeKSortedArrays {

    static class HeapNode {
        int num;
        int k;
        int ind;
        int n;

        public HeapNode(int num, int k, int ind, int n) {
            this.num = num;
            this.ind = ind;
            this.k = k;
            this.n = n;
        }
    }

    public static int[] merge(int[][] nums, int k) {
        int i = 0;
        PriorityQueue<HeapNode> q = new PriorityQueue<>(k, (a, b) -> a.num - b.num);
        HeapNode poped = null;
        int n = 0;
        int[] res;

        for(i=0; i<k; i++) {
            q.add(new HeapNode(nums[i][0], i, 0, nums[i].length));
            n += nums[i].length;
        }
        
        res = new int[n];
        i=0;
        while(!q.isEmpty()) {
            poped = q.poll();
            res[i++] = poped.num;
            poped.ind++;
            if(poped.ind < poped.n) {
                poped.num = nums[poped.k][poped.ind];
                q.add(poped);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] nums = new int[][]{ {1, 3, 5, 7}, {2, 4, 6, 8}, {0, 9, 10, 11} };
        int k = nums.length;
        System.out.println("Sorted Array");
        for(int num : merge(nums, k)) {
            System.out.print(num + " ");
        }

        nums = new int[][]{ {1, 3, 5, 7, 32}, {2, 4, 6}, {0, 9, 10, 11} };
        k = nums.length;
        System.out.println("\n\nSorted Array");
        for(int num : merge(nums, k)) {
            System.out.print(num + " ");
        }
    }
}
