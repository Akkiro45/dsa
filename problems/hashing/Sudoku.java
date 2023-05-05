package problems.hashing;

public class Sudoku {

    public boolean isSafe(int[][] grid, int row, int col, int key, int n, int m) {
        // For row
        for(int j=0; j<m; j++) {
            if(grid[row][j] == key) {
                return false;
            }
        }

        // For col
        for(int i=0; i<n; i++) {
            if(grid[i][col] == key) {
                return false;
            }
        }

        int innerGridRowStart = (row / 3) * 3;
        int innerGridColStart = (col / 3) * 3;

        for(int i=innerGridRowStart; i<innerGridRowStart+3; i++) {
            for(int j=innerGridColStart; j<innerGridColStart+3; j++) {
                if(grid[i][j] == key) {
                    return false;
                }
            }
        }

        return true;
    }
    
    public boolean solve(int[][] grid, int i, int j, int n, int m) {
        if(i >= n || j >= m) {
            return true;
        }
        int nextI = 0;
        int nextJ = 0;
        if(j == n-1) {
            nextI = i + 1;
            nextJ = 0;
        } else {
            nextI = i;
            nextJ = j + 1;
        }

        if(grid[i][j] != 0) {
            return solve(grid, nextI, nextJ, n, m);
        }

        for(int k=1; k<=9; k++) {
            if(isSafe(grid, i, j, k, n, m)) {
                grid[i][j] = k;
                if(solve(grid, nextI, nextJ, n, m)) {
                    return true;
                }
                grid[i][j] = 0;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{ 
            {3, 0, 6, 5, 0, 8, 4, 0, 0},
            {5, 2, 0, 0, 0, 0, 0, 0, 0}, 
            {0, 8, 7, 0, 0, 0, 0, 3, 1}, 
            {0, 0, 3, 0, 1, 0, 0, 8, 0}, 
            {9, 0, 0, 8, 6, 3, 0, 0, 5}, 
            {0, 5, 0, 0, 9, 0, 6, 0, 0}, 
            {1, 3, 0, 0, 0, 0, 2, 5, 0}, 
            {0, 0, 0, 0, 0, 0, 0, 7, 4}, 
            {0, 0, 5, 2, 0, 6, 3, 0, 0} 
        };
        int n = grid.length;
        int m = grid[0].length;
        Sudoku s = new Sudoku();
        s.solve(grid, 0, 0, n, m);
        System.out.println("Sudoku");
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("Solution");
        Solution so = new Solution();
        char[][] charGrid = new char[][]{
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };
        // charGrid = new char[][]{
        //     {'3', '.', '6', '5', '.', '8', '4', '.', '.'},
        //     {'5', '2', '.', '.', '.', '.', '.', '.', '.'}, 
        //     {'.', '8', '7', '.', '.', '.', '.', '3', '1'}, 
        //     {'.', '.', '3', '.', '1', '.', '.', '8', '.'}, 
        //     {'9', '.', '.', '8', '6', '3', '.', '.', '5'}, 
        //     {'.', '5', '.', '.', '9', '.', '6', '.', '.'}, 
        //     {'1', '3', '.', '.', '.', '.', '2', '5', '.'}, 
        //     {'.', '.', '.', '.', '.', '.', '.', '7', '4'}, 
        //     {'.', '.', '5', '2', '.', '6', '3', '.', '.'}
        // };
        so.solveSudoku(charGrid);
        for(int i=0; i<9; i++) {
            for(int j=0; j<9; j++) {
                System.out.print(charGrid[i][j] + " ");
            }
            System.out.println();
        }
    }


}

class Solution {
    
    static int n = 9;
    static int[] row = new int[9];
    static int[] col = new int[9];
    static int[] innerGrid = new int[9];
    static boolean set = false;
    
    public int innerGridInd(int i, int j) {
        return i / 3 * 3 + j / 3;
    }
    
    public boolean isSafe(char[][] board, int i, int j, int key) {
        return (
            ((row[i] & (1 << key)) == 0) &&
            ((col[j] & (1 << key)) == 0) &&
            ((innerGrid[innerGridInd(i, j)] & (1 << key)) == 0)
        );
    }
    
    public void addKey(int i, int j, int key) {
        row[i] |= (1 << key);
        col[j] |= (1 << key);
        innerGrid[innerGridInd(i, j)] |= (1 << key);
    }
    
    public void removeKey(int i, int j, int key) {
        row[i] &= ~(1 << key);
        col[j] &= ~(1 << key);
        innerGrid[innerGridInd(i, j)] &= ~(1 << key);
    }
    
    public boolean solve(char[][] board, int i, int j) {
        if(i >= n || j >= n) {
            return true;
        }
        if(set == false) {
            for(int m=0; m<n; m++) {
                for(int l=0; l<n; l++) {
                    if(board[m][l] != '.') {
                        addKey(m, l, (board[m][l] - '0'));
                    }
                }
            }
            set = true;
        }
        int nextI;
        int nextJ;
        if(j == n-1) {
            nextI = i + 1;
            nextJ = 0;
        } else {
            nextI = i;
            nextJ = j + 1;
        }
        
        if(board[i][j] != '.') {
            return solve(board, nextI, nextJ);
        }
        
        for(int k=1; k<=n; k++) {
            if(isSafe(board, i, j, k)) {
                board[i][j] = (char) (k + '0');
                addKey(i, j, k);
                if(solve(board, nextI, nextJ)) {
                    return true;
                }
                board[i][j] = '.';
                removeKey(i, j, k);
            }
        }
        
        return false;
    }
    
    public void solveSudoku(char[][] board) {
        solve(board, 0, 0);
    }
}
