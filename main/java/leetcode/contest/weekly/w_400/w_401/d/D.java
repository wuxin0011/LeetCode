package leetcode.contest.weekly.w_400.w_401.d;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * @author: agitated-curranfnd
 * @Description: BitSet DP
 * @url: https://leetcode.cn/contest/weekly-contest-401/problems/maximum-total-reward-using-operations-ii
 * @title: 执行操作可获得的最大总奖励 II
 */
public class D {

    public static void main(String[] args) {
        IoUtil.testUtil(D.class, "maxTotalReward", "D.txt");
    }


    public static class BitSet {
        private  int[] arr;

        BitSet(int len) {
            arr = new int[(len + 31) / 32 + 1];
        }

        public void add(int v) {
            if (v / 32 >= arr.length || v < 0) {
                throw new IndexOutOfBoundsException();
            }
            arr[v / 32] |= 1 << (v % 32);
        }

        public boolean contains(int v) {
            if (v / 32 >= arr.length || v < 0) return false;
            return ((arr[v / 32] >>> (v % 32)) & 1) != 0;
        }

        public void merge(int v) {
            int i = v / 32;
            int m = (v % 32) + 1;
            if (m == 32) {
                for (int j = 0; j <= i; j++) {
                    arr[i + j + 1] |= arr[j];
                }
                return;
            }
            int[] tmp = new int[arr.length];
            if (i >= 0) System.arraycopy(arr, 0, tmp, 0, i);
            tmp[i] = arr[i] & ((1 << m) - 1);
            for (int j = i + 1; j > 0; j--) {
                tmp[j] <<= m;
                tmp[j] |= (tmp[j - 1] ^ (tmp[j - 1] << m >>> m)) >>> (32 - m);
            }
            tmp[0] <<= m;
            for (int j = 0; j <= i + 1; j++) {
                arr[j + i] |= tmp[j];
            }
        }

        public int bitLength() {
            for (int i = arr.length * Integer.SIZE; i >= 0; i--) {
                if (contains(i)) {
                    return i;
                }
            }
            return 0;
        }
    }


    // https://leetcode.cn/problems/maximum-total-reward-using-operations-ii/submissions/577075865/
    // https://leetcode.cn/problems/maximum-total-reward-using-operations-ii/submissions/577079568/
    // python 代码 https://leetcode.cn/problems/maximum-total-reward-using-operations-ii/submissions/575591597/
    public int maxTotalReward(int[] rewardValues) {
        Arrays.sort(rewardValues);
        int max = rewardValues[rewardValues.length - 1] * 2 + 1;
        BitSet dp = new BitSet(max);
        dp.add(0);
        for (int i = 0; i < rewardValues.length; i++) {
            if (i > 0 && rewardValues[i] == rewardValues[i - 1]) continue;
            int v = rewardValues[i];
            dp.merge(v - 1);
        }
        return dp.bitLength();
    }


}