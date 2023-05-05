package problems.recursion;

import java.util.HashMap;
import java.util.Map;

public class TilingProblem {
    public static void main(String[] args) {
        System.out.println(ways(40, null));
    }

    private static int ways(int col, Map<Integer, Integer> memo) {
        if(col == 0) {
            return 1;
        }
        if(col < 0) {
            return 0;
        }
        if(memo == null) {
            memo = new HashMap<>();
        } else if(memo.containsKey(col)) {
            return memo.get(col);
        }
        memo.put(col, ways(col - 1, memo) + ways(col - 2, memo));
        return memo.get(col);
    }
}
