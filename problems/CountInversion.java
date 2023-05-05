package problems;
public class CountInversion {
    public static void main(String[] args) {
        int[] nums = new int[]{6, 3, 9, 5, 2, 8, 7, 1};
        int n = nums.length;
        System.out.println(countInversions(nums, 0, n-1));
    }

    private static int countInversions(int[] nums, int beg, int end) {
        int count = 0;
        if(beg < end) {
            int mid = (beg + end) / 2;
            count = countInversions(nums, beg, mid) + countInversions(nums, mid + 1, end);

            count += merge(nums, beg, mid, end);
        }
        return count;
    }

    private static int merge(int[] nums, int beg, int mid, int end) {
        int invCount = 0;
        int[] a1 = new int[mid - beg + 1];
        int[] a2 = new int[end - mid];
        
        for(int i=0; i<a1.length; i++) {
            a1[i] = nums[beg + i];
        }

        for(int i=0; i<a2.length; i++) {
            a2[i] = nums[mid + 1 + i];
        }

        int i = 0;
        int j = 0;
        int k = beg;
        while(i < a1.length && j < a2.length) {
            if(a1[i] <= a2[j]) {
                nums[k] = a1[i++];
            } else {
                invCount += a1.length - i;
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

        return invCount;
    }
}
