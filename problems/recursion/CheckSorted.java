package problems.recursion;

public class CheckSorted {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 4, 4, 5};
        System.out.println(isSorted(nums, 0));
    }

    private static boolean isSorted(int[] nums, int i) {
        if(i == nums.length - 1) {
            return true;
        }
        return nums[i] <= nums[i + 1] && isSorted(nums, i + 1);
    }
}
