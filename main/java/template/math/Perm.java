package template.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: wuxin0011
 * @Description: 排列组合
 */
public class Perm {

    //    排列

    /**
     * @link <a href="https://leetcode.cn/problems/permutations/submissions/578520466/">全排列</a>
     * @link <a href="https://leetcode.cn/problems/permutations-ii/submissions/578523264/">不重复的全排列</a>
     * isRepeat // 是否允许重复的排列
     */
    static class Permutations {
        public static List<List<Integer>> permutations(int n) {
            int[] ids = new int[n];
            for(int i = 0;i < n;i++) ids[i] = i;
            return permutations(ids);
        }
        public static List<List<Integer>> permutations(int... a) {
            return permutations(false, a);
        }

        /**
         * 返回全排列
         *
         * @param isRepeat 是否去重
         * @param a        数组
         * @return 返回排列
         */
        public static List<List<Integer>> permutations(boolean isRepeat, int... a) {
            List<List<Integer>> nextPerms = new ArrayList<>();
            ArrayList<Integer> path = new ArrayList<>();
            if (isRepeat) {
                Arrays.sort(a);
            }
            for (int i = 0; i < a.length; i++) path.add(0);
            nextPermsDfs(0, a, isRepeat, path, 0, nextPerms);
            return nextPerms;
        }

        public static void nextPermsDfs(int mask, int[] a, boolean isRepeat, List<Integer> path, int size, List<List<Integer>> ans) {
            if (mask == (1 << a.length) - 1) {
                ans.add(new ArrayList<>(path));
                return;
            }
            for (int i = 0; i < a.length; i++) {
                if (isRepeat && (i > 0 && a[i] == a[i - 1] && (mask >> (i - 1) & 1) == 1)) {
                    continue;
                }
                if ((mask >> i & 1) == 0) {
                    path.set(size, a[i]);
                    nextPermsDfs(mask | 1 << i, a, isRepeat, path, size + 1, ans);
                }
            }
        }

    }


    // 组合 注意  [0,1,2] [0,2,1] [1,2,0] 数字表示是索引 等视为一个重复组合
    // 返回 组合下标二维列表
    static class Combine {


        /**
         * 返回长度为n的数组 中选择 长度为k
         *
         * @param n 数组长度
         * @param k 选择数据量
         * @return 由下标组合而成的二维数组
         */
        public static List<List<Integer>> combine(int n, int k) {
            List<List<Integer>> combineIdx = new ArrayList<>();
            List<Integer> path = new ArrayList<>();
            combineIdxDfs(n, k, path, combineIdx);
            return combineIdx;
        }


        public static void combineIdxDfs(int i, int k, List<Integer> path, List<List<Integer>> ans) {
            if (path.size() > k) return;
            if (path.size() == k) {
                ans.add(new ArrayList<>(path));
                return;
            }
            int rest = k - path.size();
            for (int j = i; j >= rest; j--) {
                // 如果是存放下标
                path.add(j - 1);
                // 存放 val
//                path.add(j);
                combineIdxDfs(j - 1, k, path, ans);
                path.remove(path.size() - 1);
            }
        }


    }


    public static void main(String[] args) {
//        List<List<Integer>> nexts = Permutations.permutations(1,2,3,4);
//        for (List<Integer> next : nexts) {
//            System.out.println(next);
//        }
    }
}
