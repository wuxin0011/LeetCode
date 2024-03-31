package leetcode.contest.biweekly.bi_100.bi_127.b;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description:
 * @url
 * @title
 */
public class B {
    public static void main(String[] args) {


        IoUtil.testUtil(B.class, IoUtil.DEFAULT_METHOD_NAME, "B.txt");
    }

    public int minimumLevels(int[] possible) {
        int n = possible.length, res = 1000000;
        int[] pre = new int[n + 1];
        for(int i = 1;i <= n;i++){
            pre[i] = pre[i-1] + (possible[i-1] == 0?-1:1);
        }
        for(int i = 1;i < n;i++){
            if(pre[i] > pre[n] - pre[i]){
                return i;
            }
        }
        return -1;
    }
}
