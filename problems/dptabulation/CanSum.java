package problems.dptabulation;

public class CanSum {
    public static void main(String[] args) {
        // Test Case 1
        int[] nums1 = new int[]{5, 3, 7};
        System.out.println(canSum(7, nums1));
    }

    private static boolean canSum(int target, int[] nums) {
        boolean[] table = new boolean[target+1];

        table[0] = true;

        for(int i=0; i<=target; i++) {
            if(table[i]) {
                for(int num : nums) {
                    if(i+num <= target) {
                        table[i+num] = true;
                    }
                }
            }
        }

        return table[target];
    }
}
