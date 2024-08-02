package leetcode._0x3f_.bitwise_operations.base.bitwise_0016;

import code_generation.contest.ParseCodeInfo;
import code_generation.utils.IoUtil;

/**
 * 2166. 设计位集
 *
 * 位集 Bitset 是一种能以紧凑形式存储位的数据结构。
 * 请你实现 Bitset 类。
 * 	Bitset(int size) 用 size 个位初始化 Bitset ，所有位都是 0 。
 * 	void fix(int idx) 将下标为 idx 的位上的值更新为 1 。如果值已经是 1 ，则不会发生任何改变。
 * 	void unfix(int idx) 将下标为 idx 的位上的值更新为 0 。如果值已经是 0 ，则不会发生任何改变。
 * 	void flip() 翻转 Bitset 中每一位上的值。换句话说，所有值为 0 的位将会变成 1 ，反之亦然。
 * 	boolean all() 检查Bitset 中 每一位 的值是否都是 1 。如果满足此条件，返回 true ；否则，返回 false 。
 * 	boolean one() 检查Bitset 中 是否至少一位 的值是 1 。如果满足此条件，返回 true ；否则，返回 false 。
 * 	int count() 返回 Bitset 中值为 1 的位的 总数 。
 * 	String toString() 返回 Bitset 的当前组成情况。注意，在结果字符串中，第 i 个下标处的字符应该与 Bitset 中的第 i 位一致。
 *
 * 示例：
 * 输入
 * ["Bitset", "fix", "fix", "flip", "all", "unfix", "flip", "one", "unfix", "count", "toString"]
 * [[5], [3], [1], [], [], [0], [], [], [0], [], []]
 * 输出
 * [null, null, null, null, false, null, null, true, null, 2, "010^10"]
 * 解释
 * Bitset bs = new Bitset(5); // bitset = "00000".
 * bs.fix(3);     // 将 idx = 3 处的值更新为 1 ，此时 bitset = "00010" 。
 * bs.fix(1);     // 将 idx = 1 处的值更新为 1 ，此时 bitset = "010^10" 。
 * bs.flip();     // 翻转每一位上的值，此时 bitset = "10^101" 。
 * bs.all();      // 返回 False ，bitset 中的值不全为 1 。
 * bs.unfix(0);   // 将 idx = 0 处的值更新为 0 ，此时 bitset = "0010^1" 。
 * bs.flip();     // 翻转每一位上的值，此时 bitset = "110^10" 。
 * bs.one();      // 返回 True ，至少存在一位的值为 1 。
 * bs.unfix(0);   // 将 idx = 0 处的值更新为 0 ，此时 bitset = "010^10" 。
 * bs.count();    // 返回 2 ，当前有 2 位的值为 1 。
 * bs.toString(); // 返回 "010^10" ，即 bitset 的当前组成情况。
 *
 * 提示：
 * 	1 <= size <= 10^5
 * 	0 <= idx <= size - 1
 * 	至多调用fix、unfix、flip、all、one、count 和 toString 方法 总共 10^5 次
 * 	至少调用 all、one、count 或 toString 方法一次
 * 	至多调用toString 方法 5 次
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/design-bitset
 * @title: 设计位集
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Bitset.class, ParseCodeInfo.ConstructorClass, "in.txt");
    }


    public static class Bitset {

        int size;
        int one;
        boolean reverse;
        int[] set;

        public Bitset(int size) {
            this.set = new int[(size + 31) / 32];
            this.size = size;
            this.reverse = false;
            this.one = 0;
        }

        public void fix(int idx) {
            int index = idx / 32;
            int val = idx % 32;
            if (!this.reverse) {
                // 0 - > 1
                if ((set[index] >> val & 1) == 0) {
                    one++;
                    set[index] |= 1 << val;
                }
            } else {
                // 1 - > 0
                if ((set[index] >> val & 1) == 1) {
                    one++;
                    set[index] ^= 1 << val;
                }
            }
        }


        // 1 - > 0
        public void unfix(int idx) {
            int index = idx / 32;
            int val = idx % 32;
            if (!reverse) {
                // 1 - > 0
                if ((set[index] >> val & 1) == 1) {
                    one--;
                    set[index] ^= 1 << val;
                }
            } else {
                // 0 - > 1
                if ((set[index] >> val & 1) == 0) {
                    one--;
                    set[index] |= 1 << val;
                }
            }


        }

        public void flip() {
            reverse = !reverse;
            one = size - one;
        }

        public boolean all() {

            return one == size;
        }

        public boolean one() {

            return one > 0;
        }

        public int count() {

            return one;
        }

        public String toString() {
            char[] cs = new char[size];
            for (int idx = 0; idx < size; idx++) {
                int index = idx / 32;
                int val = idx % 32;
                int v = (set[index] >> val & 1);
                v = !reverse ? v : v == 0 ? 1 : 0;
                cs[idx] = (char) (v + '0');
            }
            // System.out.println("string = " + new String(cs));
            return new String(cs);
        }


        // ======================================================下面方法为拓展===============================


        public boolean contains(int idx) {
            int index = idx / 32;
            int val = idx % 32;
            return (set[index] >> val & 1) == 1;
        }


        // 这个方法存在意义是如果 对应位置存在0 将 0 变成 1
        //
        public void reverseZero(int idx) {
            int index = idx / 32;
            int val = idx % 32;
            set[index] ^= (1 << val);
        }

        public void remove(int idx) {
            int index = idx / 32;
            int val = idx % 32;
            if (contains(idx)) {
                set[index] &= ~(1 << val);
            }
        }

        public void add(int idx) {
            int index = idx / 32;
            int val = idx % 32;
            set[index] |= (1 << val);
        }


    }

}