package template.math.combine;

/**
 *
 * 测试题目 https://leetcode.cn/problems/find-nth-smallest-integer-with-k-one-bits/submissions/694013228/
 * @author: wuxin0011
 * @Description: 组合
 */
public class Comb {


   // 计算小范围的comb
   // 从n中选择m有多少种
   static long comb(int n, int m) {
        long ans = 1;
        if (m > n || m < 0)
            return 0;
        for (int i = 1; i <= m; i++) {
            ans = 1L * ans * (n - i + 1) / i;
        }
        return ans;
    }

    static int N = 51;
    static long[][] cb = new long[N][N];
    static {
        for(int i = 0;i<=N;i++){
            for (int j = 0; j <= N; j++) {
                cb[i][j] = comb(i,j);
            }
        }
    }
    static long co(int n,int m){
        if(n<0||m<0||m>n||n>N||m>N)return 0;
        return cb[n][m];
    }
}