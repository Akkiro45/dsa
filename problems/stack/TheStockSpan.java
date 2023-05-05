package problems.stack;
import java.util.Stack;

public class TheStockSpan {

    // O(n) space and time
    // private static int[] getStockSpan(int[] values) {
    //     int n = values.length;
    //     int[] stockSpan = new int[n];
    //     Stack<Integer> s = new Stack<>();
    //     int prevDays = 0;

    //     for(int i=0; i<n; i++) {
    //         prevDays = 0;
    //         while(!s.isEmpty() && values[s.peek()] <= values[i]) {
    //             s.pop();
    //         }
    //         if(i == 0) {
    //             prevDays = 0;
    //         } else if(s.isEmpty()) {
    //             prevDays = i;
    //         } else {
    //             prevDays = i - s.peek() - 1;
    //         }
    //         stockSpan[i] = prevDays + 1;
    //         s.push(i);
    //     }

    //     return stockSpan;
    // }

    // O(n) space and time
    private static int[] getStockSpan(int[] values) {
        int n = values.length;
        int[] stockSpan = new int[n];
        Stack<int[]> s = new Stack<>();
        int span = 0;

        for(int i=0; i<n; i++) {
            span = 1;
            while(!s.isEmpty() && s.peek()[0] <= values[i]) {
                span += s.peek()[1];
                s.pop();
            }
            s.push(new int[]{values[i], span});      
            stockSpan[i] = span;
        }

        return stockSpan;
    }

    public static void main(String[] args) {
        int[] values1 = new int[]{100, 80, 60, 70, 60, 75, 85};
        int[] res1 = getStockSpan(values1);
        for(int val :  res1) {
            System.out.println(val + " ");
        }
    }
}
