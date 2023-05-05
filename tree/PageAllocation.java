package tree;

/**
 * Given number of pages in n different books and m students. The books are arranged in ascending order of number of pages. Every student is assigned to read some consecutive books. The task is to assign books in such a way that the maximum number of pages assigned to a student is minimum. 
 */
public class PageAllocation {

    public static boolean isPossible(int[] pages, int n, int m, int targetMax) {
        int allocatedStudents = 1;
        int sum = 0;

        for(int page : pages) {
            if(page > targetMax) {
                return false;
            }
            sum += page;
            if(sum > targetMax) {
                allocatedStudents++;
                sum = page;
                if(allocatedStudents > m) {
                    return false;
                }
            }
        }

        return true;
    }

    public static int findMinimumMaxiumumPages(int[] pages, int n, int m) {
        int sum = 0;
        for(int page : pages) {
            sum += page;
        }
        int beg = 0;
        int end = sum;
        int mid;
        int res = Integer.MAX_VALUE;

        while(beg < end) {
            mid = (beg + end) / 2;
            if(isPossible(pages, n, m, mid)) {
                res = Math.min(res, mid);
                end = mid - 1;
            } else {
                beg = mid + 1;
            }
        }

        return res;
    }
    
    public static void main(String[] args) {
        int[] pages = new int[]{12, 34, 67, 90};
        int n = pages.length;
        int m = 2;
        System.out.println("Minimum possible maxium pages allocated to student : " 
            + findMinimumMaxiumumPages(pages, n, m));
    }
}
