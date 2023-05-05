package problems.recursion;

import java.util.HashMap;
import java.util.Map;

public class CanConstruct {
    public static void main(String[] args) {
        // Test Case 1
        System.out.println("\nTest Case 1");
        String target1 = "abcdef";
        String[] wordBank1 = new String[]{"ab", "abc", "cd", "def", "abcd"};
        System.out.println(canConstruct(target1, wordBank1, null));

        // Test Case 2
        System.out.println("\nTest Case 2");
        String target2 = "skateboard";
        String[] wordBank2 = new String[]{"bo", "rd", "ate", "t", "ska", "sk", "boar"};
        System.out.println(canConstruct(target2, wordBank2, null));

        // Test Case 3
        System.out.println("\nTest Case 3");
        String target3 = "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeed";
        String[] wordBank3 = new String[]{"e", "ee", "eee", "eeee", "eeeee", "eeeee"};
        System.out.println(canConstruct(target3, wordBank3, null));
    }

    private static boolean canConstruct(String target, String[] wordBank, Map<String, Boolean> memo) {
        if(memo == null) {
            memo = new HashMap<>();
        } else if(memo.containsKey(target)) {
            return memo.get(target);
        }
        
        if(target.length() == 0) {
            return true;
        }

        for(String word : wordBank) {
            if(target.indexOf(word) == 0) {
                String remainderTarget = target.substring(word.length());
                if(canConstruct(remainderTarget, wordBank, memo)) {
                    memo.put(target, true);
                    return true;
                }
            }
        }
        memo.put(target, false);
        return false;
    }
}
