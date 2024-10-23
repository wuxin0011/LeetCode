package leetcode.everyday.day_000;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 * @author: wuxin0011
 * @Description:
 * @url:   <a href="https://leetcode.cn/problems/count-pairs-that-form-a-complete-day-ii">count-pairs-that-form-a-complete-day-ii</a>
 * @title: count-pairs-that-form-a-complete-day-ii
 */
public class Code_0074_3185 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0074_3185.class,"countCompleteDayPairs","txt_file\\Code_0074_3185.txt");
    }
     

    public long countCompleteDayPairs(int[] hours) {
        int[] cnts = new int[24];
        for(int i = 0;i < hours.length;i++) {
            cnts[hours[i]%24]++;
        }
        long ans = 0;
        for(int i = 0;i <=12;i++) {
            if(i == 0 || i ==12) {
                ans += cnts[i] * 1L * (cnts[i] - 1) / 2;
            }
            else{
                ans += cnts[i] * 1L * cnts[24-i];
            }
        }
        return ans;
	}

  

}