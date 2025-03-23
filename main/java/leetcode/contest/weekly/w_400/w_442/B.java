package leetcode.contest.weekly.w_400.w_442;

import code_generation.utils.IoUtil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/weekly-contest-442/problems/properties-graph">属性图</a>
 * @title: 属性图
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class,"numberOfComponents","B.txt");
    }
     

    public int numberOfComponents(int[][] properties, int k) {
        int n = properties.length;
        Set<Integer>[] sets = new Set[n];
        Arrays.setAll(sets,i->new HashSet<Integer>());
        for (int i = 0; i < n; i++) {
            int[] array = properties[i];
            for (int x : array) {
                sets[i].add(x);
            }
        }
        size = n;
        fa = new int[n];
        Arrays.setAll(fa,i->i);
        for (int i = 0; i < n; i++) {
            for(int j = i + 1;j < n;j++) {
                int sz = 0;
                for(int x : sets[i]) {
                    if(sets[j].contains(x)) {
                        sz++;
                    }
                    if(sz>=k) break;
                }
                if(sz>=k) {
                    merge(i,j);
                }
            }

        }
        return size;
    }

    int[] fa;
    int size;

    int find(int x) {
        while(x != fa[x]){
            fa[x] = fa[fa[x]];
            x = fa[x];
        }
        return x;
    }

    void merge(int x,int y){
        x = find(x);
        y = find(y);
        if(x == y) return;
        fa[x] = y;
        size--;
    }



  

}