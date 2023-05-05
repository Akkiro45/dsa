package tree;

public class FindthePeakElement {

    public static int find(int[] nums, int beg, int end) {
        if(beg > end) {
            return -1;
        }

        int mid = (beg + end) / 2;
        if((mid == 0 || nums[mid-1] <= nums[mid]) && (mid == end || nums[mid+1] <= nums[mid])) {
            return mid;
        }

        if(mid != 0 && nums[mid - 1] > nums[mid]) {
            return find(nums, beg, mid - 1);
        } else {
            return find(nums, mid + 1, end);
        }
    }

    public static int findMaxPeak(int[] nums) {
        int n = nums.length;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        int ind = -1;

        for(int left = 0, right = n-1; left<n && right >= 0; left++, right--) {
            if(left == 0) {
                leftMax[left] = nums[left];
            } else {
                leftMax[left] = Math.max(leftMax[left-1], nums[left]);
            }
            if(right == n-1) {
                rightMax[right] = nums[right];
            } else {
                rightMax[right] = Math.max(rightMax[right+1], nums[right]);
            }
        }

        for(int i=0; i<n; i++) {
            if(leftMax[i] == rightMax[i]) {
                if(ind == -1 || leftMax[i] > nums[ind]) {
                    ind = i;
                }
            }
        }

        return ind;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{10, 20, 15, 2, 23, 90, 67};
        System.out.println("Peak element's index : " + find(nums, 0, nums.length - 1));
        System.out.println("Max Peak element's index : " + findMaxPeak(nums));
    }
}
