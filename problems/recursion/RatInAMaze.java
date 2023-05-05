package problems.recursion;

import java.util.HashMap;
import java.util.Map;

public class RatInAMaze {
    public static void main(String[] args) {
        int[][] maze = new int[][] {
            {1, 0, 1, 0, 1},
            {1, 1, 1, 1, 1},
            {0, 1, 0, 1, 0},
            {1, 0, 0, 1, 1},
            {1, 1, 1, 0, 1}
        };
        int[][] res = new int[maze.length][maze[0].length];
        
        if(findWay(maze, res, 0, 0, maze.length - 1, maze[0].length - 1, null)) {
            for(int i=0; i<maze.length; i++) {
                for(int j=0; j<maze[0].length; j++) {
                    System.out.print(res[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

    private static boolean findWay(int[][] maze, int[][] path, int i, int j, int iDest, int jDest, Map<String, Boolean> memo) {
        if(i == iDest && j == jDest) {
            path[i][j] = 1;
            return true;
        }
        if(i > iDest || j > jDest) {
            return false;
        }
        if(maze[i][j] == 0) {
            return false;
        }

        String key = i + "," + j;
        if(memo == null) {
            memo = new HashMap<>();
        } else if(memo.containsKey(key)) {
            return memo.get(key);
        }

        if(findWay(maze, path, i + 1, j, iDest, jDest, memo)) {
            path[i][j] = 1;
            memo.put(key, true);
            return true;
        } else if(findWay(maze, path, i, j + 1, iDest, jDest, memo)) {
            path[i][j] = 1;
            memo.put(key, true);
            return true;
        } else {
            memo.put(key, false);
            return false;
        }
    }
}
