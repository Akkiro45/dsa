package problems.recursion;

import java.util.HashMap;
import java.util.Map;

public class NoPathStartToEndInGame {
    public static void main(String[] args) {
        int start = 0;
        int end = 100;
        System.out.println(countWays(end - start, null));
    }

    private static int countWays(int dest, Map<Integer, Integer> memo) {
        if(dest == 0) {
            return 1;
        }
        if(dest < 0) {
            return 0;
        }
        if(memo == null) {
            memo = new HashMap<>();
        } else if(memo.containsKey(dest)) {
            return memo.get(dest);
        }

        int ways = 0;
        for(int i=1; i<=6; i++) {
            memo.put(dest - i, countWays(dest - i, memo));
            ways += memo.get(dest - i);
        }
        return ways;
    }
}
