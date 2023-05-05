package problems.recursion;

public class FirstAndLastOccu {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 3, 4, 3, 2, 2};
        int[] res = new int[2];
        res = firstAndLast(nums, 0, res, 2);
        System.out.println(res[0] + " " + res[1]);
    }
    
    private static int[] firstAndLast(int[] nums, int i, int[] res, int num) {

        if(nums[i] == num) {
            res[1] = i;
        }
        if(nums[nums.length - i - 1] == num) {
            res[0] = nums.length - i - 1;
        }

        if(i == nums.length - 1) {
            return res;
        }

        i++;
        return firstAndLast(nums, i, res, num);
    }
}
