package problems.slidingwindow;

public class SubarrayWithPalindromicConcatenation {

    public static int getNumberOfDigits(int num) {
        int count = 0;

        while(num > 0) {
            count++;
            num /= 10;
        }

        return count;
    }
    
    public static boolean findUsingStr(int[] nums, int n, int k) {
        StringBuilder inOrder = new StringBuilder();
        int start = 0;
        int end = 0;

        for(int i=0; i<n; i++) {
            inOrder.append(nums[i]);

            if((end - start + 1) != k) {
                end++;
                continue;
            }

            if(inOrder.toString().equals(inOrder.reverse().toString())) {
                System.out.println("Palindrom : ");
                for(int j=start; j<=end; j++) {
                    System.out.print(nums[j]);
                }
                return true;
            }
            inOrder.reverse();

            inOrder.delete(0, getNumberOfDigits(nums[start]));
            start++;
            end++;
        }

        return false;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 5, 3, 2, 3, 5, 4};
        int n = nums.length;
        int k = 5;

        System.out.println("\nExist : " + findUsingStr(nums, n, k));

        nums = new int[]{2, 3, 5, 1, 3};
        n = nums.length;
        k = 4;
        System.out.println("\nExist : " + findUsingStr(nums, n, k));
    }
}
