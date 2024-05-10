package leetcode.everyday.day_000;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/count-tested-devices-after-test-operations
 * @title: 统计已测试设备
 */
public class Code_0063_2960 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0063_2960.class,"countTestedDevices","txt_file\\Code_0063_2960.txt");
    }
     

    public int countTestedDevices(int[] batteryPercentages) {
        int cnt = 0;
        for (int b : batteryPercentages) {
            if(b-cnt>0) cnt += 1;
        }
        return cnt;

    }

  

}