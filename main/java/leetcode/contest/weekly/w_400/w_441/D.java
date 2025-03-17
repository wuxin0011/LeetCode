package leetcode.contest.weekly.w_400.w_441;

import code_generation.annotation.TestCaseGroup;
import code_generation.utils.IoUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: <a href="https://leetcode.cn/contest/weekly-contest-441/problems/count-beautiful-numbers">统计美丽整数的数目</a>
 * @title: 统计美丽整数的数目
 */
@TestCaseGroup(start = 1, end = 0x3fff, use = true)
public class D {



    static class S1 {
        public static void main(String[] args) {
            IoUtil.testUtil(S1.class, "beautifulNumbers", "D.txt");
        }

        public int beautifulNumbers(int l, int r) {
            return f(r) - f(l - 1);
        }

        public int f(int val) {
            if (val <= 0) return 0;
            this.cache = new HashMap<>();
            this.vals = String.valueOf(val).chars().map(x -> x - '0').toArray();
            return dfs(0, 1, 0, true, false);
        }


        int[] vals;

        Map<Long, Integer> cache = new HashMap<>();


        int dfs(int i, int mul, int sum, boolean isLimit, boolean isNum) {
            if (i == vals.length) {
                return sum > 0 && mul % sum == 0 ? 1 : 0;
            }
            long hash = (long) mul << 32L | i << 16L | sum;
            Integer result = cache.get(hash);
            if (!isLimit && isNum && result != null) {
                return result;
            }
            int ans = 0;
            if (!isNum) {
                ans += dfs(i + 1, 1, 0, false, false);
            }
            int hi = isLimit ? vals[i] : 9;
            int lo = isNum ? 0 : 1;
            for (int d = lo; d <= hi; d++) {
                ans += dfs(i + 1, mul * d, sum + d, isLimit && d == hi, true);
            }
            if(!isLimit && isNum) {
                cache.put(hash,ans);
            }
            return ans;
        }
    }




    static class S2 {
        public static void main(String[] args) {
            IoUtil.testUtil(S2.class, "beautifulNumbers", "D.txt");
        }

        public int beautifulNumbers(int l, int r) {
            this.cache = new HashMap<>();
            String s1 = String.valueOf(l);
            String s2 = String.valueOf(r);
            while(s1.length() < s2.length()) {
                s1 = "0" + s1;
            }
            low = s1.chars().map(x->x - '0').toArray();
            high = s2.chars().map(x->x - '0').toArray();
            return dfs(0, 1, 0,true, true, false);
        }


        int[] low,high;

        Map<Long, Integer> cache = new HashMap<>();


        int dfs(int i, int mul, int sum,boolean isLow, boolean isLimit, boolean isNum) {
            if (i == high.length) {
                return sum > 0 && mul % sum == 0 ? 1 : 0;
            }
            long hash = (long) mul << 32L | i << 16L | sum;
            Integer result = cache.get(hash);
            if (!isLow && !isLimit && isNum && result != null) {
                return result;
            }
            int ans = 0;
            if (!isNum && low[i] == 0) {
                ans += dfs(i + 1, 1, 0, true,false, false);
            }else{
                isNum = true;
            }
            int hi = isLimit ? high[i] : 9;
            int lo = isLow ? low[i] : 0;
            for (int d = isNum ? lo : 1; d <= hi; d++) {
                ans += dfs(i + 1, mul * d, sum + d, isLow && d == lo,isLimit && d == hi, true);
            }
            if(!isLow &&  !isLimit && isNum) {
                cache.put(hash,ans);
            }
            return ans;
        }
    }


}