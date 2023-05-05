package sorting;

public class MergeSort {
    public static void main(String[] args) {
        int[] nums = new int[]{6, 5, 4, 3, 2, 1, 7};
        sort(nums, 0, nums.length - 1);
        for(int num : nums) {
            System.out.print(num + " ");
        }
    }

    private static void sort(int[] nums, int beg, int end) {
        if(beg < end) {
            int mid = (beg + end) / 2;
            sort(nums, beg, mid);
            sort(nums, mid+1, end);
            merge(nums, beg, mid, end);
        }
    }

    private static void merge(int[] nums, int beg, int mid, int end) {
        int[] a1 = new int[mid - beg + 1];
        int[] a2 = new int[end - mid];

        for(int i=0; i<a1.length; i++) {
            a1[i] = nums[beg + i];
        }
        for(int i=0; i<a2.length; i++) {
            a2[i] = nums[mid + 1 + i];
        }

        int k = beg;
        int i=0;
        int j=0;

        while(i < a1.length && j < a2.length) {
            if(a1[i] < a2[j]) {
                nums[k] = a1[i++];
            } else {
                nums[k] = a2[j++];
            }
            k++;
        }
        while(i < a1.length) {
            nums[k++] = a1[i++];
        }

        while(j < a2.length) {
            nums[k++] = a2[j++];
        }
    }
}
