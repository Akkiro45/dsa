package problems.recursion;

import java.util.HashMap;
import java.util.Map;

public class FriendsPair {
    public static void main(String[] args) {
        System.out.println(ways(50, null));
    }

    private static long ways(long n, Map<Long, Long> memo) {
        if(n == 0 || n == 1 || n == 2) {
            return n;
        }
        if(memo == null) {
            memo = new HashMap<>();
        } else if(memo.containsKey(n)) {
            return memo.get(n);
        }
        memo.put(n, ways(n - 1, memo) + ways(n - 2, memo) * (n - 1));
        return memo.get(n);
    }
}
