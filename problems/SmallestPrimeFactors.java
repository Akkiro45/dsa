package problems;
public class SmallestPrimeFactors {
    public static void main(String[] args) {
        int n = 20;
        int[] nums = new int[n + 1];
        int j;
        
        for(int i=2; i<=n; i++) {
            if(nums[i] == 0) {
                nums[i] = i;
                j = i*i;
                while(j <= n) {
                    nums[j] = i;
                    j += i;
                }
            }
        }
        
        while(n > 1) {
            System.out.println(nums[n]);
            n = n / nums[n];
        }
    }
}