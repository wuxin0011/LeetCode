package leetcode.other.medium;

import code_generation.annotation.Description;
import code_generation.enums.Difficulty;
import code_generation.enums.Type;
import code_generation.proxy.InvocationHandlerMethodTime;
import code_generation.proxy.LogarithmicDevice;
import code_generation.utils.TestUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: wuxin0011
 * @Description:
 */
@Description
        (
                value = "N皇后",
                diff = Difficulty.HARD,
                customTag = "八皇后",
                types = {Type.MATH, Type.BACKTRACk},
                url = "https://leetcode.cn/problems/n-queens/"
        )
public class NQueens implements LogarithmicDevice {

        public static void main(String[] args) {
                InvocationHandlerMethodTime.getRunTime(NightQueens.class);
        }

        public List<List<String>> solveNQueens(int n) {
                List<List<String>> res = new ArrayList<>();
                String[][] g = new String[n][n];
                for (int i = 0; i < n; ++i) {
                        String[] t = new String[n];
                        Arrays.fill(t, ".");
                        g[i] = t;
                }
                // 列是否已经有值
                boolean[] col = new boolean[n];
                // 斜线是否已经有值
                boolean[] dg = new boolean[2 * n];
                // 反斜线是否已经有值
                boolean[] udg = new boolean[2 * n];
                // 从第一行开始搜索
                dfs(0, n, col, dg, udg, g, res);
                return res;
        }

        private void dfs(int u, int n, boolean[] col, boolean[] dg, boolean[] udg, String[][] g, List<List<String>> res) {
                if (u == n) {
                        List<String> t = new ArrayList<>();
                        for (String[] e : g) {
                                t.add(String.join("", e));
                        }
                        res.add(t);
                        return;
                }
                for (int i = 0; i < n; ++i) {
                        if (!col[i] && !dg[u + i] && !udg[n - u + i]) {
                                g[u][i] = "Q";
                                col[i] = dg[u + i] = udg[n - u + i] = true;
                                dfs(u + 1, n, col, dg, udg, g, res);
                                g[u][i] = ".";
                                col[i] = dg[u + i] = udg[n - u + i] = false;
                        }
                }
        }

        @Override
        public void logarithmicDevice() {
                // 8 皇后共有92中解法 此处作为判断依据
                int size = this.solveNQueens(8).size();
                System.out.println("size"+size);
                TestUtils.testBoolean(size == 92, true, "ok");
        }
}
