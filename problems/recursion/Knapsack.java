package problems.recursion;

import java.util.HashMap;
import java.util.Map;

public class Knapsack {
    public static void main(String[] args) {
        long[] values = new long[]{100, 50, 150};
        long[] weights = new long[]{10, 20, 30};
        long capacity = 50;
        System.out.println(maxValue(values, weights, capacity, 0, 0, 0, null));
    }

    private static long maxValue(long[] values, long[] weights, long capacity, int i, long value, long weight, Map<String, Long> memo) {
        if(i == values.length) {
            if(capacity >= weight) {
                return value;
            } else {
                return 0;
            }
        }
        if(weight > capacity) {
            return 0;
        }
        String key = weight + "," + i;
        if(memo == null) {
            memo = new HashMap<>();
        } else if(memo.containsKey(key)) {
            return memo.get(key);
        }

        memo.put(key, Math.max(
            maxValue(values, weights, capacity, i + 1, value + values[i], weight + weights[i], memo), 
            maxValue(values, weights, capacity, i + 1, value, weight, memo)));
        return memo.get(key);
    }
}
