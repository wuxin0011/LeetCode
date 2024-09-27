package template.str.zfunction;

/**
 * @author: wuxin0011
 * @Description: z函数
 */
public class ZFunction_Template {

    public static int[] zFunction(char[] s) {
        int n = s.length;
        int[] z = new int[n];
        for (int i = 0, l = 0, r = 0; i < n; i++) {
            if (i <= r && z[i - l] < r - i + 1) {
                z[i] = z[i - l];
            } else {
                z[i] = Math.max(0, r - i + 1);
                while (z[i] + i < n && s[z[i]] == s[z[i] + i]) z[i]++;
            }
            if (z[i] + i - 1 > r) {
                l = i;
                r = z[i] + i - 1;
            }
        }
        return z;
    }
}
