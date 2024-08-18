package leetcode.contest.weekly.w_400.w_410.a;

import code_generation.utils.IoUtil;

import java.util.List;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-410/problems/snake-in-matrix
 * @title: 矩阵中的蛇
 */
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class, "finalPositionOfSnake", "A.txt");
    }


    public int finalPositionOfSnake(int n, List<String> commands) {
        int i = 0, j = 0;
        for (String command : commands) {
            int[] dir = dirs(command);
            i = dir[0] + i;
            j = dir[1] + j;
        }
        return i * n + j;
    }

    public static int[] dirs(String command) {
        if (command.equals("UP")) {
            return new int[]{-1, 0};
        }
        if (command.equals("RIGHT")) {
            return new int[]{0, 1};
        }
        if (command.equals("DOWN")) {
            return new int[]{1, 0};
        }
        if (command.equals("LEFT")) {
            return new int[]{0,-1};
        }
        return new int[]{-1, -1};
    }


}