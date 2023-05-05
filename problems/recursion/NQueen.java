package problems.recursion;

public class NQueen {
    public static void main(String[] args) {
        int n = 4;
        int[] res = new int[n];
        int[] cols = new int[n];
        placeNQueens(n, 0, res, cols);
        for(int pos : res) {
            for(int i=0; i<n; i++) {
                System.out.print(" " + (pos - 1 == i ? 1 : 0));
            }
            System.out.println();
        }
    }

    private static boolean placeNQueens(int n, int row, int[] places, int[] cols) {
        if(row == n) {
            return true;
        }

        for(int i=1; i<=n; i++) {
            if(cols[i-1] == 0) {
                if(row != 0) {
                    int prevCol = places[row - 1];
                    if(prevCol + 1 == i || prevCol - 1 == i) {
                        continue;
                    }
                }

                places[row] = i;
                cols[i-1] = 1;
                if(placeNQueens(n, row+1, places, cols)) {
                    return true;
                }
                places[row] = 0;
                cols[i-1] = 0;
            }
        }

        return false;
    }
}
