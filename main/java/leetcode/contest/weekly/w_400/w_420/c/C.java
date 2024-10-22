package leetcode.contest.weekly.w_400.w_420.c;

import code_generation.utils.IoUtil;

import java.util.HashMap;
import java.util.Map;
/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   https://leetcode.cn/contest/weekly-contest-420/problems/minimum-division-operations-to-make-array-non-decreasing
 * @title: 使数组非递减的最少除法操作次数
 */
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(C.class,"minOperations","C.txt");
    }


    static Map<Integer,Integer> map = new HashMap<>();

    static {
        for(int x = 2;x < (int)1e6 + 1;x++) {
            for(int i = 2;i * i <= x; i++) {
                if(x % i != 0) {
                    continue;
                }
                int k0 = i;
                int k1 = x / i;
                if(k0 == x) k0 = -1;
                if(k1 == x) k1 = -1;
                int k = Math.max(k0,k1);
                if(k == -1)continue;
                map.put(x,k);
                break;
            }
        }
    }



    public int minOperations(int[] nums) {
//        handler(nums);
        int n = nums.length;
        int cnt = 0;
        for(int i = n - 2;i >= 0;i--) {
            if(nums[i] > nums[i+1] ) {
                if(!map.containsKey(nums[i])) {
                    return -1;
                }
                nums[i] /= map.get(nums[i]);
                cnt++;
            }
            if(nums[i] > nums[i+1]) {
                return -1;
            }
        }
        return cnt;
    }




}