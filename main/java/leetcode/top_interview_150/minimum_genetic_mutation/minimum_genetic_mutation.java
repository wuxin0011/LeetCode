package leetcode.top_interview_150.minimum_genetic_mutation;

import leetcode.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description: 最小基因突变
 * @url https://leetcode.cn/problems/minimum-genetic-mutation
 */
public class minimum_genetic_mutation {
    public static void main(String[] args) {
        IoUtil.testUtil(minimum_genetic_mutation.class, "minMutation");
    }

    String endGene;
    int n;
    boolean[] vis;
    String[] bank;
    int cnt;

    public int minMutation(String startGene, String endGene, String[] bank) {
        this.n = bank.length;
        this.vis = new boolean[n];
        this.bank = bank;
        this.cnt = Integer.MAX_VALUE;
        this.endGene = endGene;
        for(int i = 0;i<n;i++){
            boolean f = check(startGene,bank[i]);
            //System.out.println("find = " + f + ", idx = " + i);
            if(f){
                vis[i] = true;
                dfs(bank[i],1);
                vis[i] = false;
            }
        }
        return cnt == Integer.MAX_VALUE ? -1 : cnt;
    }

    public void dfs(String src,int t){
        System.out.println("cnt => " + t);
        if(src.equals(endGene)){
            //System.out.println("find======>");
            cnt = Math.min(cnt,t);
            return;
        }
        for(int i = 0;i<n;i++){
            if(vis[i]) continue;
            boolean f = check(src,bank[i]);
            if(f){
                //System.out.println("find dfs = " + f + ", idx = " + i);
                vis[i] = true;
                dfs(bank[i],t+1);
                vis[i] = false;
            }
        }
        
    }

    public static boolean check(String src,String target){
        if(src.length() != target.length()){
            return false;
        }
        boolean use = false;
        for(int i = 0;i<src.length();i++){
            if( src.charAt(i) != target.charAt(i)) {
                if( use ){
                    return false;
                }
                use = true;
            }
        }
        return true;

    }
}
