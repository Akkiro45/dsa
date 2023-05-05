package problems.ibm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayGenerator {
    public static List<Integer> getSmallestArray(List<Integer> arr, int l, int r) {
        int n = arr.size();
        int prevCRR;
        int i = 1;
        List<Integer> brr = new ArrayList<>(n);

        brr.add(l = Math.max(Math.min(arr.get(0), r), l));
        prevCRR = brr.get(0) - arr.get(0);
        
        while(l <= r && i < n) {
            l = Math.max(Math.min(arr.get(i), r), l);
            while(l <= r && prevCRR >= (l-arr.get(i))) {
                l++;
            }
            if(l > r) {
                return List.of(-1);
            }
            brr.add(l);
            prevCRR = brr.get(i) - arr.get(i);
            i++;
        }
    
        return brr;
    }
    
    public static void main(String[] args) {
        List<Integer> arr;
        arr = Arrays.asList(1, 2, 1, 2);
        System.out.println(getSmallestArray(arr, 1, 10));
        arr = Arrays.asList(1, 2, 1, 3);
        System.out.println(getSmallestArray(arr, 1, 10));
        arr = Arrays.asList(1, 2, 1, 3);
        System.out.println(getSmallestArray(arr, 1, 5));
    }
}
