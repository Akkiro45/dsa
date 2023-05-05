package problems.dptabulation;

public class CountConstruct {
    public static void main(String[] args) {
        // Test Case 1
        System.out.println("\nTest Case 1");
        String target1 = "abcdef";
        String[] wordBank1 = new String[]{"ab", "abc", "cd", "def", "abcd"};
        System.out.println(countConstruct(target1, wordBank1));

        // Test Case 2
        System.out.println("\nTest Case 2");
        String target2 = "skateboard";
        String[] wordBank2 = new String[]{"bo", "rd", "ate", "t", "ska", "sk", "boar"};
        System.out.println(countConstruct(target2, wordBank2));

        // Test Case 3
        System.out.println("\nTest Case 3");
        String target3 = "purple";
        String[] wordBank3 = new String[]{"purp", "p", "ur", "le", "e", "purpl"};
        System.out.println(countConstruct(target3, wordBank3));

        // Test Case 4
        System.out.println("\nTest Case 4");
        String target4 = "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeed";
        String[] wordBank4 = new String[]{"e", "ee", "eee", "eeee", "eeeee", "eeeee"};
        System.out.println(countConstruct(target4, wordBank4));
    }

    private static int countConstruct(String target, String[] wordBank) {
        int n = target.length();
        int[] table = new int[n + 1];
        
        table[0] = 1;
        
        for(int i=0; i<=n; i++) {
            if(table[i] != 0) {
                for(String word : wordBank) {
                    if(target.indexOf(word) == i) {
                        table[i + word.length()] += table[i];
                    }
                }
            }
        }

        return table[n];
    }
}
