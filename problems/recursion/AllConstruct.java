package problems.recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllConstruct {
    public static void main(String[] args) {
        // Test Case 1
        System.out.println("\nTest Case 1");
        String target1 = "abcdef";
        String[] wordBank1 = new String[]{"ab", "abc", "cd", "def", "abcd"};
        System.out.println(allConstruct(target1, wordBank1, null));

        // Test Case 2
        System.out.println("\nTest Case 2");
        String target2 = "skateboard";
        String[] wordBank2 = new String[]{"bo", "rd", "ate", "t", "ska", "sk", "boar"};
        System.out.println(allConstruct(target2, wordBank2, null));

        // Test Case 3
        System.out.println("\nTest Case 3");
        String target3 = "purple";
        String[] wordBank3 = new String[]{"purp", "p", "ur", "le", "purpl"};
        System.out.println(allConstruct(target3, wordBank3, null));

        // Test Case 4
        System.out.println("\nTest Case 4");
        String target4 = "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeed";
        String[] wordBank4 = new String[]{"e", "ee", "eee", "eeee", "eeeee", "eeeee"};
        System.out.println(allConstruct(target4, wordBank4, null));
    }

    private static List<List<String>> allConstruct(String target, String[] wordBank, Map<String, List<List<String>>> memo) {
        if(memo == null) {
            memo = new HashMap<>();
        } else if(memo.containsKey(target)) {
            return memo.get(target);
        }
        
        List<List<String>> res = null;
        if(target.length() == 0) {
            res = new ArrayList<>();
            res.add(new ArrayList<>());
            return res;
        }

        for(String word : wordBank) {
            if(target.indexOf(word) == 0) {
                String remainderTarget = target.substring(word.length());
                List<List<String>> remainderRes = allConstruct(remainderTarget, wordBank, memo);
                if(remainderRes != null) {
                    if(res == null) {
                        res = new ArrayList<>();
                    }
                    for(List<String> list : remainderRes) {
                        list.add(0, word);
                        res.add(list);
                    }
                }
            }
        }

        memo.put(target, res);
        return res;
    }
}
