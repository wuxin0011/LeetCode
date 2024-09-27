package leetcode._0x3f_.dp.base.Solution_0004;

import code_generation.utils.IoUtil;

/**
 * 2266. 统计打字方案数
 * <p>
 * Alice 在给 Bob 用手机打字。
 * 数字到字母的 对应如下图所示。
 * 为了 打出一个字母，Alice 需要 按对应字母 i次，i是该字母在这个按键上所处的位置。
 * 比方说，为了按出字母's'，Alice 需要按'7'四次。类似的， Alice 需要按'5'两次得到字母'k'。
 * 注意，数字'0' 和'1'不映射到任何字母，所以Alice 不使用它们。
 * 但是，由于传输的错误，Bob 没有收到 Alice 打字的字母信息，反而收到了 按键的字符串信息。
 * 比方说，Alice 发出的信息为"bob"，Bob 将收到字符串"2266622"。
 * 给你一个字符串pressedKeys，表示 Bob 收到的字符串，请你返回 Alice 总共可能发出多少种文字信息。
 * 由于答案可能很大，将它对10^9 + 7取余 后返回。
 * <p>
 * 示例 1：
 * 输入：pressedKeys = "22233"
 * 输出：8
 * 解释：
 * Alice 可能发出的文字信息包括：
 * "aaadd", "abdd", "badd", "cdd", "aaae", "abe", "bae" 和 "ce" 。
 * 由于总共有 8 种可能的信息，所以我们返回 8 。
 * <p>
 * 示例 2：
 * 输入：pressedKeys = "222222222222222222222222222222222222"
 * 输出：82876089
 * 解释：
 * 总共有 208287610^3 种 Alice 可能发出的文字信息。
 * 由于我们需要将答案对 10^9 + 7 取余，所以我们返回 208287610^3 % (10^9 + 7) = 82876089 。
 * <p>
 * 提示：
 * 1 <= pressedKeys.length <= 10^5
 * pressedKeys 只包含数字'2'到'9'。
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/count-number-of-texts
 * @title: 统计打字方案数
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "countTexts", "in.txt");
    }

    private static final int MOD = (int) 1e9 + 7;
    private static final int max = (int) 1e5 + 1;


    private static final int[] f = new int[max];
    private static final int[] g = new int[max];

    static {
        f[0] = g[0] = 1;
        f[1] = g[1] = 1;
        f[2] = g[2] = 2;
        f[3] = g[3] = 4;
        for (int i = 4; i < f.length; i++) {
            f[i] = (int) (((long) f[i - 3] + f[i - 2] + f[i - 1]) % MOD);
            g[i] = (int) (((long) g[i - 4] + g[i - 3] + g[i - 2] + g[i - 1]) % MOD);
        }
    }

    public int countTexts(String pressedKeys) {
        int[] cnts = new int[10];
        char[] ch = pressedKeys.toCharArray();
        for (char c : ch) {
            cnts[c - '0']++;
        }

        long ans = 1;
        int cnt = 0;
        for (int i = 0; i < pressedKeys.length(); i++) {
            char c = pressedKeys.charAt(i);
            cnt++;
            if (i == pressedKeys.length() - 1 || c != pressedKeys.charAt(i + 1)) {
                if (c == '7' || c == '9') {
                    ans = (ans * g[cnt]) % MOD;
                } else {
                    ans = (ans * f[cnt]) % MOD;
                }
                cnt = 0;
            }
        }
        return (int) ans;
    }


}