package problems.recursion;

import java.util.HashMap;
import java.util.Map;

public class NoPathStartToEndInGameIn2D {
    public static void main(String[] args) {
        System.out.println(ways(2, 3, null));
    }

    private static int ways(int n, int m, Map<String, Integer> memo) {
        if(n == 0 || m == 0) {
            return 0;
        }
        if(n == 1 && m == 1) {
            return 1;
        }
        String key = n + "," + m;
        if(memo == null) {
            memo = new HashMap<>();
        } else if(memo.containsKey(key)) {
            return memo.get(key);
        }
        memo.put(key, ways(n - 1, m, memo) + ways(n, m - 1, memo));
        return memo.get(key);
    }
}
