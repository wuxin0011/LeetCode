package leetcode.contest.biweekly.bi_100.bi_183;

import code_generation.utils.IoUtil;
import code_generation.annotation.TestCaseGroup;
import java.util.*;
/**
 * @author: qitongwei
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/biweekly-contest-183/problems/count-non-adjacent-subsets-in-a-rooted-tree">统计有根树中不相邻子集的数目</a>
 * @title: 统计有根树中不相邻子集的数目
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class D {

    public static void main(String[] args) {
        IoUtil.testUtil(D.class,"countValidSubsets","D.txt");
    }
    private static final int MOD = (int)1e9 + 7; 

    List<Integer>[] g;
    int n,k;
    int[] a;
    public int countValidSubsets(int[] parent, int[] nums, int k) {
        this.n = nums.length;
        this.k = k;
        this.a = nums;
        g = new List[parent.length + 1];
        Arrays.setAll(g,xxxx->new ArrayList<>());
        for(int i = 1;i<parent.length;i++){
            g[parent[i]].add(i);
        }
        long[][] ans = dfs(0);
        return (int)((ans[0][0] + ans[1][0] - 1 + MOD) % MOD);
	}

    private long[][] dfs(int u) {
        long[] f0 = new long[k + 1];
        long[] f1 = new long[k + 1];
        f0[0] = 1;
        f1[a[u]%k]=1;
        for(int v : g[u]) {

            long[][] nxt = dfs(v);
            long[] vf0 = nxt[0],vf1 = nxt[1];


            long[] nf0 = new long[k + 1];
            // 不选u
            for(int v0 = 0;v0 < k;v0++){
                long cnt = vf0[v0] + vf1[v0];
                if(cnt==0)continue;
                for(int v1 = 0;v1<k;v1++){
                    int s = (v0 + v1) % k;
                    nf0[s] = (nf0[s] + cnt * f0[v1]) % MOD;
                }
            }

            long[] nf1 = new long[k + 1];
            // 选u
            for(int v0 = 0;v0 < k;v0++){
                long cnt = vf0[v0];
                if(cnt==0)continue;
                for(int v1 = 0;v1<k;v1++){
                    int s = (v0 + v1) % k;
                    nf1[s] = (nf1[s] + cnt * f1[v1]) % MOD;
                }
            }
            f0 = nf0;
            f1 = nf1;
        }
        return new long[][]{f0,f1};
    }



  

}