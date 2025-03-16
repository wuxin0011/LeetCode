package leetcode.contest.weekly.w_400.w_441;

import code_generation.utils.IoUtil;
import code_generation.annotation.TestCaseGroup;

import javax.lang.model.element.VariableElement;
import java.util.*;
/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/weekly-contest-441/problems/closest-equal-element-queries">距离最小相等元素查询</a>
 * @title: 距离最小相等元素查询
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class,"solveQueries","B.txt");
    }


    static int inf = 0x3ffffff;
    public List<Integer> solveQueries(int[] a, int[] q) {
        Map<Integer,List<Integer>> map = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            List<Integer> ids = map.getOrDefault(a[i],new ArrayList<Integer>());
            ids.add(i);
            if(ids.size()==1){
                map.put(a[i],ids);
            }
        }
        List<Integer> ans = new ArrayList<>();
        int n = a.length;
        for (int i = 0; i < q.length; i++) {
            List<Integer> ids = map.getOrDefault(a[q[i]], new ArrayList<Integer>());
            if(ids.size() <= 1) {
                ans.add(-1);
            }else{
                int l = 0,r = ids.size() - 1;
                int x = q[i];
                for(;l <= r;) {
                    int mid = l + ((r - l )>> 1);
                    if(ids.get(mid) >= x){
                        r = mid - 1;
                    }else{
                        l = mid + 1;
                    }
                }
                int dis = inf;
                dis = Math.min(dis,get(n,ids,q[i],l - 1));
                dis = Math.min(dis,get(n,ids,q[i],l + 1));
                ans.add(dis);
            }
        }
        return ans;
    }


    public static int get(int n,List<Integer> ids,int cur,int pos) {
        if(!(0 <= pos && pos < ids.size())) {
            return inf;
        }
        int p = ids.get(pos);
        int first = ids.get(0);
        int last = ids.get(ids.size() - 1);
        int ans = inf;
        if(p != cur){
            ans = Math.min(ans,Math.min(n - Math.abs(cur - p),Math.abs(cur - p)));
        }
        if(first != cur && first != p){
            ans = Math.min(ans,Math.min(n - Math.abs(cur - first),Math.abs(cur - first)));
        }
        if(last != cur && last != p){
            ans = Math.min(ans,Math.min(n - Math.abs(cur - last),Math.abs(cur - last)));
        }
        return ans;
    }

  

}