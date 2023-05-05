package sorting;

public class CountSort {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 2, 3, 4, 1, 6, 4, 3};
        sort(nums);
        for(int num : nums) {
            System.out.print(num + " ");
        }
    }

    private static void sort(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        int max = 0;
        for(int num : nums) {
            if(num > max) {
                max = num;
            }
        }

        int[] count = new int[max+1];
        for(int num : nums) {
            count[num]++;
        }

        for(int i=1; i<=max; i++) {
            count[i] += count[i-1];
        }

        for(int i=0; i<n; i++) {
            res[--count[nums[i]]] = nums[i];
        }

        for(int i=0; i<n; i++) {
            nums[i] = res[i];
        }
    }
}
