package application.maze;

import adt.Stack;
import linked.stack.LinkedStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Winter Yuan
 * @version 1.0
 */
public class MazePath {

    // 执行次数
    public int times = 0;
    // 迷宫数组
    private final int[][] maze;
    /*
    标记数组,这个数组可用可不用。
    使用的话，会额外花费这个数组的空间。
    不使用而是直接在迷宫数组上打标记的话，会破坏迷宫数组的形状。
    自己选择吧
     */
    private final int[][] mark;

    // 两个公共数组，所有对象共用。
    // 方向数组
    private static final List<String> dirs = new ArrayList<>();
    // 表示下一个点位的数组
    private static final Map<String, int[]> move = new HashMap<>();

    // 公共数组赋值
    static {
        dirs.add("E");// 0 东
        dirs.add("S");// 1 南
        dirs.add("W");// 2 西
        dirs.add("N");// 3 北

        move.put("S",new int[]{1,0});
        move.put("W",new int[]{0,-1});
        move.put("N",new int[]{-1,0});
        move.put("E",new int[]{0,1});
    }

    /**
     * 初始化所需数组
     * @param maze 迷宫数组
     */
    public MazePath(int[][] maze){
        this.maze = maze;
        mark = new int[maze.length][maze[0].length];
        // 初始化标记数组
        for (int i = 0; i < maze.length; i++) {
            System.arraycopy(maze[i], 0, mark[i], 0, maze[0].length);
        }
    }

    /**
     * 算法实现
     * @param x 开始点位横坐标
     * @param y 开始点位纵坐标
     * @param m 出口点位横坐标
     * @param n 出口点位纵坐标
     * @return 下一点位是否可行
     */
    public boolean SeekPath(int x, int y, int m, int n) {
        times++;
        if (x == m && y == n)
            return true;
        if (maze[x][y] == 1)
            System.out.println("没有通路！");
        // 下一点位的坐标
        int g, h;
        // 四个方向轮流找下一个点位
        for (int i = 0; i < 4; i++) {
            g = x + move.get(dirs.get(i))[0];
            h = y + move.get(dirs.get(i))[1];
            if (g >= 0 && h >=0 && maze[g][h] == 0 && mark[g][h] == 0) {// 下一位置可通
                mark[g][h] = 1;// 标记此位置已访问
                // 开始递归
                if (SeekPath(g, h, m, n)) {
                    System.out.printf("下一点为:(%d,%d) ", g, h);
                    System.out.println("方向为：" + dirs.get(i));
                    return true;
                }
            }
            // 回溯,换方向
        }
        return false;
    }

    /**
     * 使用栈而不使用递归
     * @param x 开始点位横坐标
     * @param y 开始点位纵坐标
     * @param m 出口点位横坐标
     * @param n 出口点位纵坐标
     */
    public void SeekPathByStack(int x, int y, int m, int n){
        if (maze[x][y] == 1)
            System.out.println("没有通路！");
        mark[x][y] = 1; // 入口标记为走过
        // 非递归用到的栈
        Stack<int[]> stack = new LinkedStack<>();
        // 记录开始位置
        stack.push(new int[]{x,y,0});
        // (i,j),(g,h)轮流使用表示下一点位的坐标，d是其方向在方向表里的下标。
        int i, j, d, g, h;
        // 四个方向轮流找下一个点位
        while (!stack.isEmpty()){ // 栈不空就继续
            int[] index = stack.pop();
            i = index[0];
            j = index[1];
            d = index[2];
            // 0,东  1,南  2,西  3,北
            while (d < 4){ // 表示还有方向可遍历
                g = i + move.get(dirs.get(d))[0];
                h = j + move.get(dirs.get(d))[1];
                if (g == m && h == n){ // 抵达出口
                    System.out.printf("下一点为:(%d,%d) ", g, h);
                    System.out.println("方向为：" + dirs.get(d));
                    return;
                }
                // 新点位可通
                if (g >= 0 && h >= 0 && maze[g][h] == 0 && mark[g][h] == 0){
                    // 可以通行的点入栈，等待遍历
                    mark[g][h] = 1;// 标记走过的点位
                    stack.push(new int[]{g,h,0});
                    System.out.printf("下一点为:(%d,%d) ", g, h);
                    System.out.println("方向为：" + dirs.get(d));
                    // 走到下一个点位
                    i = g;
                    j = h;
                    d = -1; // if语句后有d++语句，不仅会使下一个点位从1方向开始寻找，而且会少1个方向。
                    times++;
                }
                d++;
            }
        }
        // 当栈空时还未找到通路，说明没有通路可走
        System.out.println("没有通路！");
    }
}
