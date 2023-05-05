package problems.stack;
import java.util.ArrayDeque;
import java.util.Deque;

public class TrappingRainWater {

    public static void main(String[] args) {
        int[] height1 = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trap(height1));

        int[] height2 = new int[]{4, 2, 0, 3, 2, 5};
        System.out.println(trap(height2));
    }
    
    //Optimized O(n), space O(n)
    // private static int trap(int[] height) {
    //     int water = 0;
    //     int n = height.length;
    //     int[] leftMax = new int[n];
    //     int[] rightMax = new int[n];
        
    //     for(int i=0; i<n; i++) {
    //         if(i == 0 || leftMax[i-1] < height[i]) {
    //             leftMax[i] = height[i];
    //         } else {
    //             leftMax[i] = leftMax[i-1];
    //         }
    //         if(n-i == n || rightMax[n-i] < height[n-i-1]) {
    //             rightMax[n-i-1] = height[n-i-1];
    //         } else {
    //             rightMax[n-i-1] = rightMax[n-i];
    //         }
    //     }
        
    //     for(int i=0; i<n; i++) {
    //         water += Math.min(leftMax[i], rightMax[i]) - height[i];
    //     }
        
    //     return water;
    // }

    //Optimized O(n), space O(1)
    // private static int trap(int[] height) {
    //     int water = 0;
    //     int n = height.length;
    //     int left = 0;
    //     int right = n - 1;
    //     int leftMax = 0;
    //     int rightMax = 0;

    //     while(left < right) {
    //         if(height[left] <= height[right]) {
    //             if(height[left] >= leftMax) {
    //                 leftMax = height[left];
    //             } else {
    //                 water += leftMax - height[left];
    //             }
    //             left++;
    //         } else {
    //             if(height[right] >= rightMax) {
    //                 rightMax = height[right];
    //             } else {
    //                 water += rightMax - height[right];
    //             }
    //             right--;
    //         }
    //     }
        
    //     return water;
    // }

    //Optimized O(n), space O(n) using stack
    private static int trap(int[] height) {
        int water = 0;
        int n = height.length; 
        Deque<Integer> s = new ArrayDeque<>(n);
        int poped;

        for(int i=0; i<n; i++) {
            while(!s.isEmpty() && height[s.peek()] < height[i]) {
                poped = s.pop();
                if(s.isEmpty()) {
                    break;
                }
                water += (Math.min(height[s.peek()], height[i]) - height[poped]) * (i - s.peek() - 1);
            }
            s.push(i);
        }
        
        return water;
    }
}
