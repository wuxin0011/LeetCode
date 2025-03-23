package leetcode.contest.weekly.w_400.w_442;

import code_generation.utils.IoUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: <a href="https://leetcode.cn/contest/weekly-contest-442/problems/minimum-operations-to-make-array-elements-zero">使数组元素都变为零的最少操作次数</a>
 * @title: 使数组元素都变为零的最少操作次数
 */
//@TestCaseGroup(start = 4,end = 0x3fff,use = true)
public class D {

    public static void main(String[] args) {
//        System.out.println(floor(9,4) );
//        System.out.println(ceil(8,4) );
//        System.out.println(ceil(6,4) );
//        System.out.println(floor(6,4) );
        IoUtil.testUtil(D.class, "minOperations", "D.txt");
//        int N = (int)1e9 + 1;
//        for(int i = 1;i <= 20;i++) {
//            int t = 1;
//            int v = 1;
//            while(t <= i) {
//                v *= 4;
//                t++;
//            }
//            System.out.println(v);
//            if(v > N) {
//                System.out.println("break");
//                break;
//            }
//        }
    }

    static List<Integer> list = new ArrayList<>();

    static {
        int N = (int) 1e9 + 1;
        for (int i = 1, v = 1; i <= 20; i++) {
            v *= 4;
            list.add(v - 1);
            if (v > N) {
                break;
            }
        }
    }


    public long minOperations(int[][] queries) {
        long ans = 0;
        for (int[] query : queries) {
            long l = query[0], r = query[1];
            long t = 0;
            int index = lowerbound(l);
            while (index < list.size()) {
                if (l > r) break;
                // 求区间长度
                t += (long) (index + 1) * (Math.min(r, list.get(index)) - l + 1);
                l = list.get(index) + 1;
                index++;
            }
            ans += (t + 1) / 2;
        }
        return ans;
    }


    static int lowerbound(long x) {
        int l = 0, r = list.size() - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (list.get(mid) >= x) r = mid - 1;
            else l = mid + 1;
        }
        return l;
    }


}