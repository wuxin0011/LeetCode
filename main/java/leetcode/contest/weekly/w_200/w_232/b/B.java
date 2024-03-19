package leetcode.contest.weekly.w_200.w_232.b;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description:
 * @url https://leetcode.cn/problems/find-center-of-star-graph/description/
 * @title
 */
public class B {
    public static void main(String[] args) {
        IoUtil.testUtil(B.class,IoUtil.DEFAULT_METHOD_NAME,"B.txt");
    }

    public int findCenter(int[][] edges) {
        int n = edges.length + 1;
        int[] in = new int[n];
        for(int[] e : edges){
            in[e[0]-1]++;
            in[e[1]-1]++;
        }
        for(int i = 0;i<n;i++){
            if(in[i] == n - 1){
                return i + 1;
            }
        }
        return 0;
    }
}
