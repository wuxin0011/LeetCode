package leetcode.everyday.day_000;

import code_generation.utils.IoUtil;
import java.util.*;

import code_generation.contest.ParseCodeInfo;
/**
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/kth-ancestor-of-a-tree-node
 * @title: kth-ancestor-of-a-tree-node
 */
public class Code_0036_1483 {

    public static void main(String[] args) {
        IoUtil.testUtil(TreeAncestor.class,ParseCodeInfo.ConstructorClass,"txt_file\\Code_0036_1483.txt");
    }
	  

	 public static class TreeAncestor {
         private int[][] pa;

         public TreeAncestor(int n, int[] parent) {
             int m = 32 - Integer.numberOfLeadingZeros(n); // n 的二进制长度
             pa = new int[n][m];
             for (int i = 0; i < n; i++)
                 pa[i][0] = parent[i];
             for (int i = 0; i < m - 1; i++) {
                 for (int x = 0; x < n; x++) {
                     int p = pa[x][i];
                     pa[x][i + 1] = p < 0 ? -1 : pa[p][i];
                 }
             }
         }

         public int getKthAncestor(int node, int k) {
             int m = 32 - Integer.numberOfLeadingZeros(k); // k 的二进制长度
             for (int i = 0; i < m; i++) {
                 if (((k >> i) & 1) > 0) { // k 的二进制从低到高第 i 位是 1
                     node = pa[node][i];
                     if (node < 0) break;
                 }
             }
             return node;
         }

         // 另一种写法，不断去掉 k 的最低位的 1
         public int getKthAncestor2(int node, int k) {
             for (; k > 0 && node != -1; k &= k - 1) {
                 node = pa[node][Integer.numberOfTrailingZeros(k)];
             }
             return node;
         }
     }
}