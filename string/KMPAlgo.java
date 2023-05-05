package string;

public class KMPAlgo {

    public static boolean checkPattern(String str, int n, String srch, int m, int[] lps) {
        int j = -1;
        int i = 0;

        while(i < n) {
            if(str.charAt(i) == srch.charAt(j+1)) {
                i++;
                j++;
            } else if(j != -1) {
                j = lps[j]-1;
            } else {
                i++;
            }

            if((j+1) == m) {
                return true;
            }
        }

        return false;
    }

    // O(n^3)
    public static int[] longestPrefixSufffix1(String str, int n) {
        int[] lps = new int[n];
        int len;
        boolean flag;

        for(int i=0; i<n; i++) {
            flag = true;
            len = i + 1;
            
            for(int j=len-1; j>0; j--) {
                flag = true;

                for(int k=0; k<j; k++) {
                    if(str.charAt(k) != str.charAt(len - j + k)) {
                        flag = false;
                        break;
                    }
                }

                if(flag == true) {
                    lps[i] = j;
                    break;
                }
            }

            if(flag == false) {
                lps[i] = 0;
            }
        }
        
        return lps;
    }

    // O(n)
    public static int[] longestPrefixSufffix2(String str, int n) {
        int[] lps = new int[n];
        int len = 0;
        lps[0] = 0;
        int i = 1;

        while(i < n) {
            if(str.charAt(i) == str.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else if(len == 0) {
                lps[i] = 0;
                i++;
            } else {
                len = lps[len - 1];
            }
        }

        return lps;
    }

    public static void main(String[] args) {
        String str = "abacsabadasassasassabacabad";
        String srch = "abacabad";
        
        int[] lps = longestPrefixSufffix1(srch, srch.length());
        System.out.println(checkPattern(str, str.length(), srch, srch.length(), lps));
        
        lps = longestPrefixSufffix2(srch, srch.length());
        System.out.println(checkPattern(str, str.length(), srch, srch.length(), lps));
    }
}
