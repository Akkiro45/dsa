package tree;

public class SearchInSortedAndRotatedArray {
    
    public static int search(int[] nums, int key) {
        int beg = 0;
        int end = nums.length - 1;
        int mid;

        while(beg <= end) {
            mid = (beg + end) / 2;

            if(nums[mid] == key) {
                return mid;
            }

            if(nums[beg] < nums[mid]) {
                // Left is sorted
                if(key >= nums[beg] && key < nums[mid]) {
                    end = mid - 1;
                } else {
                    beg = mid + 1;
                }
            } else {
                // Right is sorted
                if(key <= nums[end] && key > nums[mid]) {
                    beg = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }

        return -1;
    }

    public static int search(int[] nums, int key, int beg, int end) {
        if(beg > end) {
            return -1;
        }

        int mid = (beg + end) / 2;

        if(nums[mid] == key) {
            return mid;
        }

        if(nums[beg] < nums[mid]) {
            // Left is sorted
            if(key >= nums[beg] && key < nums[mid]) {
                return search(nums, key, beg, mid - 1);
            } else {
                return search(nums, key, mid + 1, end);
            }
        } else {
            // Right is sorted
            if(key <= nums[end] && key > nums[mid]) {
                return search(nums, key, mid + 1, end);
            } else {
                return search(nums, key, beg, mid - 1);
            }
        }
    }
    
    public static void main(String[] args) {
        int[] nums = new int[]{30, 40, 50, 10, 20};
        int key = 40;
        System.out.println("Found at index : " + search(nums, key));
        System.out.println("Found at index : " + search(nums, key, 0, nums.length - 1));
    }
}
