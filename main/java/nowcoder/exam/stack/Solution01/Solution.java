package nowcoder.exam.stack.Solution01;

import code_generation.utils.ACM.IO;

import java.io.IOException;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://www.nowcoder.com/practice/2a2c00e7a88a498693568cef63a4b7bb
 */
public class Solution {

    static int MAXN = (int) 1e6 + 1;
    static int N = 0;
    static int size = 0;
    static int[] sk = new int[MAXN];
    static int[][] ans = new int[MAXN][2];
    static int[] arr = new int[MAXN];
    static IO io = null;

    public static void main(String[] args) throws IOException {
        io = new IO();
        N = io.readInt();
        for (int i = 0; i < N; i++) {
            arr[i] = io.readInt();
        }
        solve();
        for (int i = 0; i < N; i++) {
            io.println(ans[i][0] + " " + ans[i][1]);
        }
        io.close();
    }

    public static void solve() {
        size = 0;
        for (int i = 0; i < N; i++) {

            // 是否符合严格大压小
            // 对于大于或者等于当前 val 出栈
            while (size > 0 && arr[i] <= arr[sk[size - 1]]) {
                size--;
                int pos = sk[size];

                // 栈是否为空 ？如果不为空 说明 当前为值栈数据 小于或者等于 val
                ans[pos][0] = size - 1 >= 0 ? sk[size - 1] : -1;
                ans[pos][1] = i;
            }

            // 压栈
            sk[size++] = i;
        }

        // 栈不为空 逻辑同上
        while (size > 0) {
            size--;
            int pos = sk[size];
            ans[pos][0] = size - 1 >= 0 ? sk[size - 1] : -1;
            ans[pos][1] = -1;
        }


        // 更新数据
        // 因为是严格小与 这里是等于也处出栈 对于相同位置 val 一样的数据 最右边一顶是ok的
        // 因此从右边往左更新
        for (int i = N - 2; i >= 0; i--) {
            int pos = ans[i][1];
            if (pos != -1 && arr[i] == arr[pos]) {
                ans[i][1] = ans[pos][1];
            }
        }


    }


}
