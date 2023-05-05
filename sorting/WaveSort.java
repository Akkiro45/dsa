package sorting;

public class WaveSort {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 4, 7, 5, 6, 2};
        int n = nums.length;
        sort(nums, n);
        for(int num : nums) {
            System.out.print(num + " ");
        }
    }

    private static void sort(int[] nums, int n) {
        for(int i=1; i<n-1; i+=2) {
            if(nums[i-1] < nums[i]) {
                swap(nums, i, i-1);
            }

            if(nums[i] > nums[i+1]) {
                swap(nums, i, i+1);
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
