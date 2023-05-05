package problems;
public class SieveOfEratosthenes {
    public static void main(String[] args) {
        int n = 50;
        int[] nums = new int[n + 1];
        int j = 2;
        
        for(int i=2; i<=n; i++) {
            if(nums[i] != 1) {
                j = i*i;
                while(j <= n) {
                    nums[j] = 1;
                    j += i;
                }
            }
            if(nums[i] == 0) {
                System.out.println(i);
            }
        }
    }
}
