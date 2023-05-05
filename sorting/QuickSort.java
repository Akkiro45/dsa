package sorting;

public class QuickSort {
    public static void main(String[] args) {
        int[] nums = new int[]{6, 3, 9, 5, 2, 8, 7};
        sort(nums, 0, nums.length - 1);
        for(int num : nums) {
            System.out.print(num + " ");
        }
    }

    private static void sort(int[] nums, int l, int r) {
        if(l < r) {
            int pivot = partision(nums, l, r);

            sort(nums, l, pivot - 1);
            sort(nums, pivot + 1, r);
        }
    }

    private static int partision(int[] nums, int l, int r) {
        int pivotEle = nums[r];
        int i = l - 1;
        int j = l;
        while(j < r) {
            if(nums[j] < pivotEle) {
                i++;
                swap(nums, i, j); 
            }
            j++;
        }

        i++;
        swap(nums, i, r);
        return i;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
