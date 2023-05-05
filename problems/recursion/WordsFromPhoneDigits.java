package problems.recursion;

public class WordsFromPhoneDigits {
    public static void main(String[] args) {
        phoneDigits("23", "");
    }

    private static void phoneDigits(String digits, String txt) {
        if(digits.length() == 0) {
            System.out.println(txt);
            return;
        }
        String[] phoneChars = new String[]{"", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        
        String chars = phoneChars[digits.charAt(0) - '1'];
        for(int i=0; i<chars.length(); i++) {
            phoneDigits(digits.substring(1), txt + chars.substring(i, i+1));
        }
    }
}
