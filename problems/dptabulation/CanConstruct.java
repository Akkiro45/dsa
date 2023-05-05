package problems.dptabulation;

public class CanConstruct {
    public static void main(String[] args) {
        // Test Case 1
        System.out.println("\nTest Case 1");
        String target1 = "abcdef";
        String[] wordBank1 = new String[]{"ab", "abc", "cd", "def", "abcd"};
        System.out.println(canConstruct(target1, wordBank1));

        // Test Case 2
        System.out.println("\nTest Case 2");
        String target2 = "skateboard";
        String[] wordBank2 = new String[]{"bo", "rd", "ate", "t", "ska", "sk", "boar"};
        System.out.println(canConstruct(target2, wordBank2));

        // Test Case 3
        System.out.println("\nTest Case 3");
        String target3 = "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeed";
        String[] wordBank3 = new String[]{"e", "ee", "eee", "eeee", "eeeee", "eeeee"};
        System.out.println(canConstruct(target3, wordBank3));
    }

    private static boolean canConstruct(String target, String[] wordBank) {
        int n = target.length();
        boolean[] table = new boolean[n + 1];
        table[0] = true;

        for(int i=0; i<=n; i++) {
            if(table[i]) {
                for(String word : wordBank) {
                    int index = target.indexOf(word);
                    if(index == i) {
                        table[i + word.length()] = true;
                    }
                }
            }
        }

        return table[n];
    }
}
