package problems.slidingwindow;

public class FormNumberDivisibleByX {

    // Approach 1
    public static boolean check1(int[] nums, int n, int k, int x) {
        StringBuilder formedNum = new StringBuilder();
        int start = 0;
        int end = 0;

        for(int i=0; i<n; i++) {
            formedNum.append(nums[i]);
            if((end - start + 1) != k) {
                end++;
                continue;
            }

            if(Integer.parseInt(formedNum.toString()) % x == 0) {
                return true;
            }
            formedNum.delete(0, formedNum.indexOf(String.valueOf(nums[start+1])));
            start++;
            end++;
        }
        
        return false;
    }

    // Approach 1
    public static boolean check2(int[] nums, int n, int k, int x) {
        int sum = 0;
        int start = 0;
        int end = 0;

        for(int i=0; i<n; i++) {
            sum += nums[i];
            if((end - start + 1) != k) {
                end++;
                continue;
            }

            if(sum % x == 0) {
                System.out.println("Formed number");
                for(int j=start; j<=end; j++) {
                    System.out.print(nums[j]);
                }
                return true;
            }
            sum -= nums[start];
            start++;
            end++;
        }
        
        return false;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{8, 23, 45, 12, 56, 4};
        int n = nums.length;
        int k = 3;
        int x = 3;

        System.out.println("Exist : " + check1(nums, n, k, x));
        System.out.println("\nExist : " + check2(nums, n, k, x));
    }
}
