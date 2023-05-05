package problems.dptabulation;

import java.util.ArrayList;
import java.util.List;

public class AllConstruct {
    public static void main(String[] args) {
        // Test Case 1
        System.out.println("\nTest Case 1");
        String target1 = "abcdef";
        String[] wordBank1 = new String[]{"ab", "abc", "cd", "def", "abcd"};
        System.out.println(allConstruct(target1, wordBank1));

        // Test Case 2
        System.out.println("\nTest Case 2");
        String target2 = "skateboard";
        String[] wordBank2 = new String[]{"bo", "rd", "ate", "t", "ska", "sk", "boar"};
        System.out.println(allConstruct(target2, wordBank2));

        // Test Case 3
        System.out.println("\nTest Case 3");
        String target3 = "purple";
        String[] wordBank3 = new String[]{"purp", "p", "ur", "le", "e", "purpl"};
        System.out.println(allConstruct(target3, wordBank3));

        // Test Case 4
        System.out.println("\nTest Case 4");
        String target4 = "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeed";
        String[] wordBank4 = new String[]{"e", "ee", "eee", "eeee", "eeeee", "eeeee"};
        System.out.println(allConstruct(target4, wordBank4));
    }

    private static List<List<String>> allConstruct(String target, String[] wordBank) {
        int n = target.length();
        List<List<List<String>>> table = new ArrayList<>(n + 1);

        for(int i=0; i<=n; i++) {
            table.add(null);
        }
        List<List<String>> baseCase = new ArrayList<>();
        baseCase.add(new ArrayList<>());
        table.set(0, baseCase);

        for(int i=0; i<=n; i++) {
            List<List<String>> currtList = table.get(i);
            if(currtList != null) {
                for(String word : wordBank) {
                    if(target.indexOf(word) == i) {
                        List<List<String>> targetList = table.get(i + word.length());
                        if(targetList == null) {
                            targetList = new ArrayList<>();
                        }
                        for(List<String> targetWay : currtList) {
                            List<String> temp = new ArrayList<>(); 
                            temp.addAll(targetWay);
                            temp.add(word);
                            targetList.add(temp);
                        }
                        table.set(i + word.length(), targetList);
                    }
                }
            }
        }

        return table.get(n);
    }
}
