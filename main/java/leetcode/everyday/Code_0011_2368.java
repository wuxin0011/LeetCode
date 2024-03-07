package leetcode.everyday;

import leetcode.utils.IoUtil;
import java.util.*;

/*
*
*   使用动态规划，推理当前状态
*
 */


/**
 * @author: wuxin0011
 * @Description:  受限条件下可到达节点的数目
 * @url https://leetcode.cn/problems/reachable-nodes-with-restrictions/description/
 */
@SuppressWarnings("all")
public class Code_0011_2368 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0011_2368.class, "reachableNodes", "./txt_file/Code_0011_2368.txt");
    }


    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        List<Integer>[] g = new ArrayList[n];
        boolean[] vis = new boolean[n];
        for(int r : restricted){
            vis[r] = true;
        }
        for(int i = 0;i<n;i++){
            g[i]=new ArrayList<>();
        }
        for(int[] e : edges){
            int x = e[0];
            int y = e[1];
            if( !vis[x] && !vis[y]){
                // 由于是无向图 x 中加入 y
                // y 中加入 x
                g[x].add(y);
                g[y].add(x);
            }
        }
        return dfs(0,-1,g);
    }

    /**
     * 深度搜索
     * @param cur 当前节点
     * @param f 父节点
     * @param g 边集
     * @return cnt
     */
    public int dfs(int cur,int f,List<Integer>[] g){
        int cnt = 1;
        for(int idx : g[cur]){
            if( idx != f ){
                cnt += dfs(idx,cur,g);
            }
        }
        return cnt;
    }


}
