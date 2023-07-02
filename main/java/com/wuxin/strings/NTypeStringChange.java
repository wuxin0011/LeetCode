package com.wuxin.strings;

import com.wuxin.annotation.Description;
import com.wuxin.utils.Bean.Difficulty;
import com.wuxin.utils.Bean.Tag;
import com.wuxin.utils.InvocationHandlerMethodTime;
import com.wuxin.utils.LogarithmicDevice;
import com.wuxin.utils.TestUtils;

/**
 * @author: wuxin0011
 * @Description:
 */
@Description(value = "N字符变换", url = "https://leetcode.cn/problems/zigzag-conversion/", diff = Difficulty.MEDIUM, tag = Tag.STRING)
public class NTypeStringChange implements LogarithmicDevice {


    public static void main(String[] args) {
        InvocationHandlerMethodTime.getRunTime(NTypeStringChange.class);
    }

    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        // result 拼接
        StringBuilder ans = new StringBuilder();
        int group = 2 * numRows - 2;
        for (int i = 1; i <= numRows; i++) {
            int interval = i == numRows ? group : 2 * numRows - 2 * i;
            int idx = i - 1;
            while (idx < s.length()) {
                ans.append(s.charAt(idx));
                idx += interval;
                interval = group - interval;
                if (interval == 0) {
                    interval = group;
                }
            }
        }
        return ans.toString();
    }


    @Override
    public void logarithmicDevice() {
        TestUtils.testBoolean("PAHNAPLSIIGYIR",convert("PAYPALISHIRING",3));
        TestUtils.testBoolean("PINALSIGYAHRPI",convert("PAYPALISHIRING",4));
    }
}
