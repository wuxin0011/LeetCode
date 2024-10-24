package leetcode.contest.weekly.w_400.w_420.d;

import code_generation.utils.IoUtil;
import template.str.Manacher.ManacherTemplate.Manacher;

import java.util.*;
/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/weekly-contest-420/problems/check-if-dfs-strings-are-palindromes">判断 DFS 字符串是否是回文串</a>
 * @title: 判断 DFS 字符串是否是回文串
 */
public class D {

    public static void main(String[] args) {
        IoUtil.testUtil(D.class,"findAnswer","D.txt");
    }


    List<Integer>[] g;
    int time,nodes[][];

    char[] dfsStr,a;

    void dfs(int x) {
        nodes[x][0] = time;
        for(int y : g[x]) {
            dfs(y);
        }
        dfsStr[time] = a[x];
        time++;
        nodes[x][1] = time;
    }
    void init(int n,String s) {
        time = 0;
        g = new List[n];
        Arrays.setAll(g,i->new ArrayList<>());
        nodes = new int[n][2];
        a = s.toCharArray();
        dfsStr = new char[n];
    }

    public boolean[] findAnswer(int[] parent, String s) {
        int n = parent.length;
        init(n,s);
        for(int i = 1;i < n;i++) {
             g[parent[i]].add(i);
        }
        dfs(0);
        Manacher manacher = new Manacher(dfsStr);
        boolean[] ans = new boolean[n];
        for(int i = 0;i < n;i++) {
            ans[i] = manacher.query(nodes[i][0],nodes[i][1]);
        }
        return ans;
	}

  

}