package leetcode.base.strings;

import code_generation.annotation.Description;
import code_generation.enums.Difficulty;
import code_generation.enums.Tag;
import code_generation.proxy.InvocationHandlerMethodTime;
import code_generation.LogarithmicDevice;
import code_generation.utils.TestUtils;

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
