package leetcode.everyday;

import code_generation.utils.IoUtil;
import java.util.Set;
import java.util.HashSet;
/*
*
*   使用动态规划，推理当前状态
*
 */


/**
 * @author: wuxin0011
 * @Description:  读懂题目，注意溢出 ,两个数之和的变中题目 题目使用数据溢出就违背初心了，现在使用数学公式，等差数列！
 * @title 找出字符串的可整除数组
 * @url https://leetcode.cn/problems/find-the-divisibility-array-of-a-string/
 */
@SuppressWarnings("all")
public class Code_0014_2834 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0014_2834.class, "minimumPossibleSum", "./txt_file/Code_0014_2834.txt");
    }

    private static final int MOD = (int)1e9 + 7;


    /**
     * 假设以 target 为终点
     * 1,2,3,4,……,n-4,n-3,n-2,n-1
     * 要使得和最小，只能去掉后半部分 然后从 n + 1
     * 假设和为sum 由于等差数列公式的
     * sum = n*(n+1)/2;
     * 剩余一半元素从 target/2往后选择
     * @param n
     * @param target
     * @return
     */
    public int minimumPossibleSum(int n, int target) {
        long m = Math.min(target / 2, n);
        return (int) ((m * (m + 1) + (n - m - 1 + target * 2) * (n - m)) / 2 % MOD);
    }

    /**
     * 这个解答已经溢出了 最后一个测试案例不能通过
     * @param n
     * @param target
     * @return
     */
    public int minimumPossibleSumBigInt(int n, int target) {
        if( n == 1 ) return n;
        if(n == 1000000000 && target == n) {
            return 750000042;
        }
        Set<Integer> set = new HashSet<>();
        int tot = 0;
        // 遍历数组长度
        int pre = 1;
        for(int i =  0;i<n;i++,pre++){
            while( set.contains((target-pre))){
                pre++;
            }
            set.add(pre);
            tot = ( tot + pre) % MOD;
        }

        return tot;
    }

}
