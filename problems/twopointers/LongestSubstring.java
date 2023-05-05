package problems.twopointers;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstring {

    // private static int longestSubstring(String s) {
    //     int max = 0;
    //     Map<Character, Boolean> map = new HashMap<>();
    //     int j = 0;
    //     int n = s.length();
    //     Character ch;

    //     for(int i=0; i<n; i++) {
    //         ch = s.charAt(i);
    //         if(map.getOrDefault(ch, false) == true) {
    //             while(map.get(ch) != false) {
    //                 map.put(s.charAt(j++), false);
    //             }
    //         }
    //         map.put(ch, true);
    //         max = Math.max(max, i - j + 1);
    //     }

    //     return max;
    // }

    private static int longestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int j = -1;
        int i = 0;
        int n = s.length();
        Character ch;
        int max = 0;

        for(i=0; i<n; i++) {
            ch = s.charAt(i);

            if(map.getOrDefault(ch, -1) > j) {
                j = map.get(ch);
            }
            map.put(ch, i);
            max = Math.max(max, i - j);
        }

        return max;
    }

    public static void main(String[] args) {
        System.out.println(longestSubstring("abcabcbb"));
        System.out.println(longestSubstring("bbbbb"));
        System.out.println(longestSubstring("pwwkew"));
        System.out.println(longestSubstring(" "));
        System.out.println(longestSubstring(" & &&"));
    }
}
