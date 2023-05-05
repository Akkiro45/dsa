package string;

import java.util.Arrays;

public class CountDistinctStr {

    static long[] power;

    public static void setPower(int p, int n) {
        power = new long[n];
        power[0] = 1;
        for(int i=1; i<n; i++) {
            power[i] = power[i-1] * p;
        }
    }

    public static int hashCode(String str, int mod) {
        long hash = 0;
        for(int i=0; i<str.length(); i++) {
            hash += ((str.charAt(i) - 'a' + 1) * power[i]) % mod;
        }
        return (int)(hash);
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"ak", "jk", "jk", "ak", "u"};
        int n = strs.length;
        setPower(37, 5);
        int[] strsHash = new int[n];
        for(int i=0; i<n; i++) {
            strsHash[i] = hashCode(strs[i], n*n);
        }
        Arrays.sort(strsHash);
        int distinct = 0;
        for(int i=0; i<n; i++) {
            if(distinct == 0 || strsHash[i] != strsHash[i-1]) {
                distinct++;
            }
        }
        System.out.println(distinct);
    }
}
