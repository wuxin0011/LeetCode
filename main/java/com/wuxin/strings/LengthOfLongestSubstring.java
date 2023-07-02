package com.wuxin.strings;

import com.wuxin.annotation.Description;
import com.wuxin.utils.Bean.Difficulty;
import com.wuxin.utils.Bean.Tag;
import com.wuxin.utils.InvocationHandlerMethodTime;
import com.wuxin.utils.LogarithmicDevice;

/**
 * @author: wuxin0011
 * @Description:
 */
@Description(value = "无重复字符的最长子串", url = "https://leetcode.cn/problems/longest-substring-without-repeating-characters/", diff = Difficulty.MEDIUM, tag = Tag.STRING)
public class LengthOfLongestSubstring implements LogarithmicDevice {

    public static void main(String[] args) {
        InvocationHandlerMethodTime.getRunTime(LengthOfLongestSubstring.class);
    }

    @Override
    public void logarithmicDevice() {

    }
}
