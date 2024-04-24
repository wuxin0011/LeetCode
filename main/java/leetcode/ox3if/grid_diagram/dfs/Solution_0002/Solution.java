package leetcode.ox3if.grid_diagram.dfs.Solution_0002;

import code_generation.utils.IoUtil;
import com.sun.org.apache.bcel.internal.generic.IADD;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/pond-sizes-lcci
 * @title: 水域大小
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "pondSizes", "in.txt");
    }


    public static final int[] dirs = {};
    int cnt = 0;

    public int[] pondSizes(int[][] land) {

        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land[i].length; j++) {
                if (land[i][j] == 0) {
                    cnt = 0;
                    f(land, i, j);
                    ans.add(cnt);
                }
            }
        }
        int[] h = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            h[i] = ans.get(i);
        }
        Arrays.sort(h);
        return h;
    }

    public void f(int[][] land, int i, int j) {
        if (i < 0 || i >= land.length || j < 0 || j >= land[0].length || land[i][j] != 0) {
            return;
        }
        cnt++;
        land[i][j] = 1;
        // 左边
        f(land,i-1,j-1);
        f(land,i,j-1);
        f(land,i+1,j-1);

        // 中间
        f(land,i-1,j);
        // f(land,i,j);
        f(land,i+1,j);

        // 右边
        f(land,i-1,j+1);
        f(land,i,j+1);
        f(land,i+1,j+1);

    }


}