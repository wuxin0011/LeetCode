package com.wuxin.other.medium;

import com.wuxin.annotation.Description;
import com.wuxin.utils.InvocationHandlerMethodTime;
import com.wuxin.utils.LogarithmicDevice;

/**
 * @author: wuxin0011
 * @Description:
 */
@Description(value = "八皇后", views = {"https://zhuanlan.zhihu.com/p/99209213", "https://zhuanlan.zhihu.com/p/262001022"})
public class NightQueens implements LogarithmicDevice {

    public static void main(String[] args) {
        InvocationHandlerMethodTime.getRunTime(NightQueens.class);
    }

    private static final int N = 8;
    private int[] y; //
    int count = 0; //

    public void solve() {
        count = 0;
        y = new int[N + 1]; //
        int x = 1;
        while (x > 0) {
            // 当前的下一列
            y[x]++;
            // 检查位置是否合理 挡位置合理时候推出循环
            while ((y[x] <= N) && (!check(x))) {
                //找到合适的位置
                y[x]++;
            }

            // 边界合理性
            if (y[x] <= N) {
                if (x == N) {   // 如果找到了一个放置皇后的完整方案，解+1，打印结果
                    count++;
                    print();
                } else {
                    x++;// 还没找到完整方法，继续寻找下一个皇后位置。
                }
            } else {
                //  所有位置均不安全，回溯。
                y[x] = 0;
                x--;
            }
        }
    }


    /**
     * 检查当前棋子是否合法
     *
     * @param k 位置信息
     * @return boolean
     */
    private boolean check(int k) {
        for (int j = 1; j < k; j++) {
            if ((Math.abs(k - j) == Math.abs(y[j] - y[k])) || (y[j] == y[k])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 输出结果
     */
    private void print() {
        System.out.println("\t\t" + count);
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (j == y[i]) {
                    System.out.print(" x ");
                } else {
                    System.out.print(" o ");
                }
            }
            System.out.println();
        }
    }

    @Override
    public void logarithmicDevice() {
        this.solve();
    }
}
