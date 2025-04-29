package leetcode.contest.weekly.w_400.w_447;

import code_generation.utils.IoUtil;

import java.util.*;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/weekly-contest-447/problems/count-covered-buildings">统计被覆盖的建筑</a>
 * @title: 统计被覆盖的建筑
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class,"countCoveredBuildings","A.txt");
    }
     



    public int countCoveredBuildings(int n, int[][] buildings) {
        Map<Integer, TreeSet<Integer>> xmap = new HashMap<>();
        Map<Integer, TreeSet<Integer>> ymap = new HashMap<>();
        for (int[] building : buildings) {
            int x = building[0],y = building[1];
            TreeSet<Integer> xidx = xmap.getOrDefault(x,new TreeSet<>());
            TreeSet<Integer> yidx = ymap.getOrDefault(y,new TreeSet<>());
            xidx.add(y);
            yidx.add(x);
            xmap.put(x,xidx);
            ymap.put(y,yidx);
        }
        int ans = 0;
        for (int[] building : buildings) {
            int x = building[0],y = building[1];
            TreeSet<Integer> xidx = xmap.getOrDefault(x,new TreeSet<>());
            TreeSet<Integer> yidx = ymap.getOrDefault(y,new TreeSet<>());
            if(xidx.first() < y && y < xidx.last() &&  yidx.first() < x && x < yidx.last()) {
                ans++;
            }
        }
        return ans;
    }

}