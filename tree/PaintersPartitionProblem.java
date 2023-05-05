package tree;

/**
 * We have to paint n boards of length {A1, A2…An}. There are k painters available and each takes 1 unit of time to paint 1 unit of the board. The problem is to find the minimum time to get 
this job was done under the constraints that any painter will only paint continuous sections of boards, say board {2, 3, 4} or only board {1} or nothing but not board {2, 4, 5}.
 */
public class PaintersPartitionProblem {

    public static boolean isPossible(int[] boards, int m, int target) {
        int allocatedPainters = 1;
        int sum = 0;

        for(int board : boards) {
            if(board > target) {
                return false;
            }
            sum += board;
            if(sum > target) {
                allocatedPainters++;
                sum = board;
            }
        }

        return allocatedPainters <= m;
    }

    public static int findMinimumTime(int[] boards, int n, int m) {
        int res = Integer.MAX_VALUE;
        int beg = Integer.MAX_VALUE; // can be 0 as well
        int end = 0;
        int mid;
        for(int board : boards) {
            beg = Math.min(beg, board);
            end += board;
        }

        while(beg < end) {
            mid = (beg + end) / 2;
            if(isPossible(boards, m, mid)) {
                res = Math.min(res, mid);
                end = mid;
            } else {
                beg = mid + 1;
            }
        }

        return res;
    }
    
    public static void main(String[] args) {
        int[] boards = new int[]{10, 10, 10, 10};
        int n = boards.length;
        int m = 2;
        // Output = 20
        System.out.println("Minimum time taken to paint all boards : " 
            + findMinimumTime(boards, n, m));

        boards = new int[]{10, 20, 30, 40};
        n = boards.length;
        m = 2;
        // Output = 60
        System.out.println("Minimum time taken to paint all boards : " 
            + findMinimumTime(boards, n, m));
    }
}
