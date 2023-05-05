package problems.graph;

public class ReplaceOwithX {

    public static void replaceOWithE(char[][] mat, int i, int j, int n, int m) {
        if(i >= n || j >= m || i < 0 || j < 0 || mat[i][j] != 'O') {
            return;
        }
        mat[i][j] = 'E';
        replaceOWithE(mat, i+1, j, n, m);
        replaceOWithE(mat, i-1, j, n, m);
        replaceOWithE(mat, i, j+1, n, m);
        replaceOWithE(mat, i, j-1, n, m);
    }

    public static void main(String[] args) {
        char[][] mat = new  char[][]{
            {'X', 'O', 'X', 'O', 'X', 'X'},
            {'X', 'O', 'X', 'X', 'O', 'X'},
            {'X', 'X', 'X', 'O', 'X', 'X'},
            {'O', 'X', 'X', 'X', 'X', 'X'},
            {'X', 'X', 'X', 'O', 'X', 'O'},
            {'O', 'O', 'X', 'O', 'O', 'O'}
        };
        int n = mat.length;
        int m = mat[0].length;
        
        for(int i=0; i<m; i++) {
            // Top border
            if(mat[0][i] == 'O') {
                replaceOWithE(mat, 0, i, n, m);
            }
            // Bottom border
            if(mat[n-1][i] == 'O') {
                replaceOWithE(mat, n-1, i, n, m);
            }
        }

        for(int i=0; i<n; i++) {
            // Left border
            if(mat[i][0] == 'O') {
                replaceOWithE(mat, i, 0, n, m);
            }
            // Right border
            if(mat[i][m-1] == 'O') {
                replaceOWithE(mat, i, m-1, n, m);
            }
        }

        // Replace O to X
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(mat[i][j] == 'O') {
                    mat[i][j] = 'X';
                }
            }
        }

        // Replace E to O
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(mat[i][j] == 'E') {
                    mat[i][j] = 'O';
                }
            }
        }

        // Display
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }
}
