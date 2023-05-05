package problems.dptabulation;

public class GridTraveller {
    public static void main(String[] args) {
        System.out.println(gridTraveller(3, 3));
    }

    private static long gridTraveller(int m, int n) {
        long[][] table = new long[m+1][n+1];

        table[1][1] = 1;

        for(int i=0; i<=m; i++) {
            for(int j=0; j<=n; j++) {
                if(i < m) {
                    table[i + 1][j] += table[i][j];
                }
                if(j < n) {
                    table[i][j + 1] += table[i][j];
                }
            }
        }

        return table[m][n];
    }
}
