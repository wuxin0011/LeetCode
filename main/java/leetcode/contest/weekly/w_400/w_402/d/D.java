package leetcode.contest.weekly.w_400.w_402.d;

import code_generation.annotation.Description;
import code_generation.enums.Difficulty;
import code_generation.enums.Tag;
import code_generation.enums.Type;
import code_generation.utils.IoUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-402/problems/peaks-in-array
 * @title: 数组中的峰值
 */
public class D {


    @Description(
            url = "https://leetcode.cn/contest/weekly-contest-402/problems/peaks-in-array",
            types = {Type.TREE_ARRAY},
            diff = Difficulty.HARD,
            tag = Tag.ARRAY
    )
    static class S1 {
        public static void main(String[] args) {
            IoUtil.testUtil(S1.class, "countOfPeaks", "D.txt");
        }

        public static class Fenwick {
            int n;
            int[] data;

            Fenwick(int n) {
                this.n = n;
                this.data = new int[n];
            }

            int lowbit(int i) {
                return i & -i;
            }

            void add(int i, int val) {
                i += 1;
                while (i < n) {
                    data[i] += val;
                    i += lowbit(i);
                }
            }

            private int _sum(int i) {
                int s = 0;
                while (i > 0) {
                    s += data[i];
                    i -= lowbit(i);
                }
                return s;
            }

            int query(int l, int r) {
                if (l < 0 || r > n || l >= r) {
                    return 0;
                }
                return _sum(r) - _sum(l);
            }
        }


        public List<Integer> countOfPeaks(int[] nums, int[][] queries) {
            int n = nums.length;

            Fenwick t = new Fenwick(n);

            List<Integer> ans = new ArrayList<>();

            // 用于标记该位置是否改变了
            int[] l = new int[n];

            // 初始化树状数组
            // 本题类型与前缀和
            for (int i = 1; i < n - 1; i++) {
                if (nums[i] > nums[i + 1] && nums[i] > nums[i - 1]) {
                    l[i] = 1;
                    t.add(i, 1);
                }
            }

            for (int[] q : queries) {
                int x = q[0], y = q[1], z = q[2];
                if (x == 1) {
                    ans.add(t.query(y + 1, z));
                } else {
                    // update val
                    nums[y] = z;
                    for (int i = y - 1; i <= y + 1; i++) {
                        // 题目要求去掉最后和第一个位置
                        if (i > 0 && i < n - 1) {
                            if (nums[i] > nums[i + 1] && nums[i] > nums[i - 1]) {
                                if (l[i] == 0) {
                                    l[i] = 1;
                                    t.add(i, 1);
                                }
                            } else {
                                if (l[i] == 1) {
                                    l[i] = 0;
                                    t.add(i, -1);
                                }
                            }
                        }
                    }
                }
            }
            return ans;
        }


    }
}