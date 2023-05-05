package problems.graph;

public class SnakesAndLadders {

    static int best = 0;

    public static int getMinDiceThrows(int curr, int dice, int[] moves, int N) {
        if(best == N-1) {
            return 1;
        }
        
        int nextMove = curr + dice;
        if(moves[nextMove] == -1) {
            best = Math.max(best, nextMove);
        } else if(moves[nextMove] > curr) {
            // ladder
            best = Math.max(best, moves[nextMove]);
        }

        if(dice == 6) {
            return 1 + getMinDiceThrows(best, 1, moves, N);
        } else {
            return getMinDiceThrows(curr, dice + 1, moves, N);
        }
    }

    public static void main(String[] args) {
        int N = 30;
        int moves[] = new int[N];
        for (int i = 0; i < N; i++)
            moves[i] = -1;
 
        // Ladders
        moves[2] = 21;
        moves[4] = 7;
        moves[10] = 25;
        moves[19] = 28;
 
        // Snakes
        moves[26] = 0;
        moves[20] = 8;
        moves[16] = 3;
        moves[18] = 6;
 
        System.out.println("Min Dice throws required is "
                           + getMinDiceThrows(-1, 1, moves, N));
    }
}
