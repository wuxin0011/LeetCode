package leetcode.everyday.day_000;

import code_generation.utils.IoUtil;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 2007. 从双倍数组中还原原数组
 * <p>
 * 一个整数数组original可以转变成一个 双倍数组changed，转变方式为将 original中每个元素 值乘以 2 加入数组中，然后将所有元素 随机打乱。
 * 给你一个数组changed，如果change是双倍数组，那么请你返回original数组，否则请返回空数组。original的元素可以以任意顺序返回。
 * <p>
 * 示例 1：
 * 输入：changed = [1,3,4,2,6,8]
 * 输出：[1,3,4]
 * 解释：一个可能的 original 数组为 [1,3,4] :
 * - 将 1 乘以 2 ，得到 1 * 2 = 2 。
 * - 将 3 乘以 2 ，得到 3 * 2 = 6 。
 * - 将 4 乘以 2 ，得到 4 * 2 = 8 。
 * 其他可能的原数组方案为 [4,3,1] 或者 [3,1,4] 。
 * <p>
 * 示例 2：
 * 输入：changed = [6,3,0,1]
 * 输出：[]
 * 解释：changed 不是一个双倍数组。
 * <p>
 * 示例 3：
 * 输入：changed = [1]
 * 输出：[]
 * 解释：changed 不是一个双倍数组。
 * <p>
 * 提示：
 * 1 <= changed.length <= 10^5
 * 0 <= changed[i] <= 10^5
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/find-original-array-from-doubled-array
 * @title: 从双倍数组中还原原数组
 */
public class Code_0047_2007 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0047_2007.class, "findOriginalArray", "txt_file\\Code_0047_2007.txt", false, false);
        IoUtil.testUtil(Code_0047_2007.class, "findOriginalArray1", "txt_file\\Code_0047_2007.txt", false, false);
        IoUtil.testUtil(Code_0047_2007.class, "findOriginalArray2", "txt_file\\Code_0047_2007.txt", false, false);
    }

    static final int[] empty = new int[]{};


    public int[] findOriginalArray(int[] changed) {
        int n = changed.length;
        if ((n & 1) == 1) {
            return empty;
        }
        int zero = 0;
        int mx = 0;
        for (int v : changed) {
            if (v == 0) {
                zero++;
            }
            mx = Math.max(mx, v);
        }
        mx = (mx << 1) + 1;
        if (zero == n) {
            return new int[n >> 1];
        }
        if ((zero & 1) == 1) {
            return empty;
        }
        int[] counts = new int[mx];
        int j = zero >> 1;
        for (int v : changed) {
            if (v == 0) {
                continue;
            }
            counts[v]++;
        }
        int[] ans = new int[n >> 1];
        for (int i = 1; i < counts.length; i++) {
            int two = i << 1;
            if (two >= mx) break;
            while (counts[i] > 0 && counts[two] > 0) {
                ans[j++] = i;
                counts[i]--;
                counts[two]--;
            }

        }
        return j != (n >> 1) ? empty : ans;
    }


    public int[] findOriginalArray2(int[] changed) {
        int n = changed.length;
        if ((n & 1) == 1) {
            return empty;
        }
        int zero = 0;
        int mx = 0;
        for (int v : changed) {
            if (v == 0) {
                zero++;
            }
            mx = Math.max(mx, v);
        }
        mx = (mx << 1) + 1;
        if (zero == n) {
            return new int[n >> 1];
        }
        if ((zero & 1) == 1) {
            return empty;
        }
        Arrays.sort(changed);
        int[] map = new int[mx];
        int other = 0;
        int[] temps = new int[n];
        int j = zero >> 1;
        for (int v : changed) {
            if (v == 0) continue; // 0 不计入 0 是自己本身的 2 倍数
            if (map[v] >= 1) {
                temps[j++] = v >> 1;
                map[v]--;
                other++;
            } else {
                map[v << 1]++;
            }

        }
        if (other * 2 + zero != n) {
            return empty;
        }
        int[] ans = new int[j];
        System.arraycopy(temps, 0, ans, 0, ans.length);
        return ans;
    }


    public int[] findOriginalArray1(int[] changed) {
        int n = changed.length;
        if ((n & 1) == 1) {
            return empty;
        }
        int zero = 0;
        for (int v : changed) {
            if (v == 0) {
                zero++;
            }
        }
        if (zero == n) {
            return new int[n >> 1];
        }
        if ((zero & 1) == 1) {
            return empty;
        }
        Arrays.sort(changed);
        HashMap<Integer, Integer> map = new HashMap<>();
        int other = 0;
        int[] temps = new int[n];
        int j = zero >> 1;
        for (int v : changed) {
            if (v == 0) continue; // 0 不计入 0 是自己本身的 2 倍数
            if (map.getOrDefault(v, 0) >= 1) {
                temps[j++] = v >> 1;
                map.put(v, map.get(v) - 1);
                other++;
            } else {
                map.put(v << 1, map.getOrDefault(v << 1, 0) + 1);
            }

        }
        if (other * 2 + zero != n) {
            return empty;
        }
        int[] ans = new int[j];
        System.arraycopy(temps, 0, ans, 0, ans.length);
        return ans;
    }


}