package leetcode.contest.biweekly.bi_100.bi_167;

import code_generation.contest.ParseCodeInfo;
import code_generation.utils.IoUtil;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: <a href="https://leetcode.cn/contest/biweekly-contest-167/problems/design-exam-scores-tracker">设计考试分数记录器</a>
 * @title: 设计考试分数记录器
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(ExamTracker.class, ParseCodeInfo.ConstructorClass, "C.txt");
    }

    private static final int MOD = (int) 1e9 + 7;

    public static class ExamTracker {
        static int N=(int)1e6;
        static long[] sums = new long[N];
        static int[] a = new int[N];
        static int sz = 0;
        public ExamTracker() {
            sz = 0;
        }

        public void record(int t, int s) {
            a[sz] = t;
            sums[sz] = (sz > 0 ? sums[sz - 1] : 0) + s;
            sz++;
        }


        static int lowerbound(int x){
            int l = 0,r = sz-1;
            while(l<=r){
                int mid = l + ((r - l)>>1);
                if(a[mid]>=x)r=mid-1;
                else l = mid + 1;
            }
            return l;
        }

        public long totalScore(int st, int ed) {
            int l = lowerbound(st);
            if(l>=sz||l<0)return 0;
            int r = lowerbound(ed);
            if(r>=sz||r<0)return 0;
            long ans = 0;
            // System.out.println(st + " " +ed + " ，query1 = " + l + " " + r );
            if(a[r] != ed)r--;
            // if(a[l] != st)l++;
            if(l>r)return 0;
            // System.out.println(st + " " +ed + " ，query2 = " + l + " " + r );
            // System.out.println(st + " " +ed + " ，query = " + a[l] + " " + a[r] );
            // if(a[l]==st)l--;
            // if(a[r]==ed)r++;
            // long ans = sums[r] - (l >= 0 ? sums[l] : 0);
            // if(l>=0){
            //     System.out.println(st + " " +ed + " " + a[l] + " " + a[r] );
            // }
            return sums[r] - (l >0 ? sums[l - 1]:0);
            // return 0;
        }
    }

}