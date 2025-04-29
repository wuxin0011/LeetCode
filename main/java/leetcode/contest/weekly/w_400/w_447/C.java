package leetcode.contest.weekly.w_400.w_447;

import code_generation.utils.IoUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/weekly-contest-447/problems/concatenated-divisibility">判断连接可整除性</a>
 * @title: 判断连接可整除性
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(C.class,"concatenatedDivisibility","C.txt");
    }



    String ss[];
    int mod,n;

    Integer[][] dp;

    public int[] concatenatedDivisibility(int[] nums, int k) {
        int n = nums.length;
        ss = new String[n];
        for(int i = 0;i < n;i++) ss[i] = Integer.toString(nums[i]);
        this.mod = k;
        this.n = n;
        Arrays.sort(ss, String::compareTo);
        List<Integer> ans = new ArrayList<>();
        List<String> res = new ArrayList<>();

        for(int j = 1;j < (1<<n);j++) {
            int v = 0;
            List<String> ids = new ArrayList<>();
            String sss = "";
            for(int i = 0;i < n;i++) {
                if((j >> i & 1) == 0) continue;
                String s = ss[i];
                sss += s;
                int ii = 0;
                while(ii < s.length()){
                    v = v * 10 + (s.charAt(ii)-'0');
                    v %= mod;
                    ii++;
                }
                ids.add(s);
            }
            if(v==0){
                ans.add(j);
                System.out.println(ids + ", mod = " + mod + " sss = " + sss);
                res.add(sss);
            }
        }
        if(res.size()==0)return new int[]{};
        Integer[] ids = new Integer[res.size()];
        Arrays.setAll(ids,v->v);
        Arrays.sort(ids, Comparator.comparing(res::get));
        int m = ans.get(ids[0]);
        List<Integer> vvv = new ArrayList<>();
        for(int i = 0;i < n;i++) {
            if((m>>i&1)==1){
                vvv.add(Integer.parseInt(ss[i]));
            }
        }
        return vvv.stream().mapToInt(Integer::intValue).toArray();
    }


  

}