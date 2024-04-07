package leetcode.contest.weekly.w_300.w_392.d;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   https://leetcode.cn/contest/weekly-contest-392/problems/minimum-cost-walk-in-weighted-graph
 * @title: 带权图里旅途的最小代价
 */
public class D {

    public static void main(String[] args) {
        IoUtil.testUtil(D.class,"minimumCost","D.txt");
    }


    public int[] minimumCost(int n, int[][] edges, int[][] query) {
        Union u = new Union(n);
        for(int[] edge : edges){
            u.union(edge[0], edge[1]);
        }

        int[] arr = new int[n];
        Arrays.fill(arr, (1 << 20) - 1);

        for(int[] edge : edges){
            arr[u.find(edge[0])] &= edge[2];
        }


        int m = query.length;
        int[] res = new int[m];
        int loc = 0;

        for(int[] q : query){
            if(q[0] == q[1]){
                res[loc++] = 0;
            }
            else if(u.find(q[0]) != u.find(q[1])){
                res[loc++] = -1;
            }
            else{
                res[loc++] = arr[u.find(q[0])];
            }
        }


        return res;
    }

    class Union{
        int size;
        int[] arr;

        public Union(int size){
            this.size = size;
            arr = new int[size];
            for(int i = 0; i < size; i++){
                arr[i] = i;
            }
        }

        public int find(int loc){
            if(arr[loc] != loc){
                arr[loc] = find(arr[loc]);
            }

            return arr[loc];
        }

        public void union(int a, int b){
            arr[find(a)] = find(b);
        }
    }

  

}