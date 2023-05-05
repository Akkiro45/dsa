package problems.stack;
import java.util.ArrayDeque;
import java.util.Deque;

public class LargestRectangleinHistogram {
    public static void main(String[] args) {
        // Test case 1
        int[] heights1 = new int[]{2, 1, 5, 6, 2, 3};
        System.out.println("Case 1: " + findLargestRectangleinHistogram(heights1));

        // Test case 2
        int[] heights2 = new int[]{2, 4};
        System.out.println("Case 1: " + findLargestRectangleinHistogram(heights2));
    }

    // Bruteforce O(!n) | O(n^2)
    // private static int findLargestRectangleinHistogram(int[] heights) {
    //     int res = 0;
    //     int currArea = 0;
    //     int currHeight = 0;
    //     int currWidth = 0;
    //     int n = heights.length;

    //     for(int i=0; i<n; i++) {
    //         currArea = 0;
    //         currHeight = heights[i];
    //         currWidth = 0;
    //         for(int j=i; j<n; j++) {
    //             currWidth = j - i + 1;
    //             currHeight = Math.min(currHeight, heights[j]);
    //             currArea = currHeight * currWidth;
    //             res = Math.max(currArea, res);
    //         }
    //     }

    //     return res;
    // } 

    //  Optimized O(n)
    private static int findLargestRectangleinHistogram(int[] heights) {
        int res = 0;
        int n = heights.length;
        int i = 0;
        int lastPoped;
        Deque<Integer> s = new ArrayDeque<>(n+1);

        s.push(-1);

        while(i < n) {
            if(s.peek() == -1 || heights[s.peek()] <= heights[i]) {
                s.push(i++);
            } else {
                lastPoped = s.pop();
                res = Math.max(heights[lastPoped] * (i - s.peek() - 1), res);
            }
        }

        while(s.peek() != -1) {
            lastPoped = s.pop();
            res = Math.max(heights[lastPoped] * (i - s.peek() - 1), res);
        }

        return res;
    }
}
