package problems.twopointers;

public class ConsecutiveOnes {

    // private static int maxConsecutiveOnes(int[] arr, int k) {
    //     int i = 0;
    //     int j = 0;

    //     for(i=0; i<arr.length; i++) {
    //         if(arr[i] == 0) {
    //             k--;
    //         }
    //         if(k < 0) {
    //             if(arr[j] == 0) {
    //                 k++;
    //             }
    //             j++;
    //         }
    //     }

    //     return i - j;
    // }

    private static int maxConsecutiveOnes(int[] arr, int k) {
        int i = 0;
        int j = 0;
        int max = 0;
        int count = 0;

        for(i=0; i<arr.length; i++) {
            if(arr[i] == 0) {
                count++;
            }
            while(count > k) {
                if(arr[j] == 0) {
                    count--;
                }
                j++;
            }
            max = Math.max(max, i - j + 1);
        }

        return max;
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        int k1 = 2;
        System.out.println(maxConsecutiveOnes(arr1, k1)); // 6

        int[] arr2 = new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1};
        int k2 = 3;
        System.out.println(maxConsecutiveOnes(arr2, k2)); // 10
    }
}
