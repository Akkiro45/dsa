package problems.recursion;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci {
    public static void main(String[] args) {
        System.out.println(fib(100, null));
    }

    private static long fib(long n, Map<Long, Long> memo) {
        if(n == 0 || n == 1) {
            return (long) 0;
        }
        if(n == 2) {
            return (long) 1;
        }
        if(memo == null) {
            memo = new HashMap<>();
        }
        if(memo.containsKey(n)) {
            return memo.get(n);
        }
        memo.put(n, fib(n - 1, memo) + fib(n - 2, memo));
        return memo.get(n);
    }
}
