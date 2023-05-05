package problems.recursion;

import java.util.HashMap;
import java.util.Map;

public class CountConstruct {
    public static void main(String[] args) {
        // Test Case 1
        System.out.println("\nTest Case 1");
        String target1 = "abcdef";
        String[] wordBank1 = new String[]{"ab", "abc", "cd", "def", "abcd"};
        System.out.println(countConstruct(target1, wordBank1, null));

        // Test Case 2
        System.out.println("\nTest Case 2");
        String target2 = "skateboard";
        String[] wordBank2 = new String[]{"bo", "rd", "ate", "t", "ska", "sk", "boar"};
        System.out.println(countConstruct(target2, wordBank2, null));

        // Test Case 3
        System.out.println("\nTest Case 3");
        String target3 = "purple";
        String[] wordBank3 = new String[]{"purp", "p", "ur", "le", "purpl"};
        System.out.println(countConstruct(target3, wordBank3, null));

        // Test Case 4
        System.out.println("\nTest Case 4");
        String target4 = "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeed";
        String[] wordBank4 = new String[]{"e", "ee", "eee", "eeee", "eeeee", "eeeee"};
        System.out.println(countConstruct(target4, wordBank4, null));
    }

    private static int countConstruct(String target, String[] wordBank, Map<String, Integer> memo) {
        if(memo == null) {
            memo = new HashMap<>();
        } else if(memo.containsKey(target)) {
            return memo.get(target);
        }
        
        if(target.length() == 0) {
            return 1;
        }

        int count = 0;
        for(String word : wordBank) {
            if(target.indexOf(word) == 0) {
                String remainderTarget = target.substring(word.length());
                count += countConstruct(remainderTarget, wordBank, memo);
            }
        }

        memo.put(target, count);
        return count;
    }
}
