package test.application.maze;

import application.maze.MazePath;

/**
 * @author Winter Yuan
 * @version 1.0
 */
public class MazePathTest {
    public static void main(String[] args) {
        int[][] maze = new int[][]{
                {1,1,1,1,1,1,1,1,1,1},
                {0,0,1,1,1,0,1,1,1,1},
                {1,0,0,0,1,0,0,0,1,1},
                {1,1,1,0,0,0,1,0,1,1},
                {1,1,1,1,1,1,0,0,1,1},
                {1,1,0,1,0,0,1,0,1,1},
                {1,1,0,0,1,0,0,0,0,1},
                {1,1,0,1,1,0,1,1,1,1},
                {1,1,0,0,0,0,0,0,0,1},
                {1,1,1,1,1,1,1,1,0,1},
        };

        MazePath path1 = new MazePath(maze);

        System.out.println("=====================");
        path1.SeekPathByStack(1,1, 9,8);
        System.out.println(path1.times);
    }
}
