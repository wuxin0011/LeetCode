package leetcode.top_interview_150.monotonic_stacks;

import leetcode.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description:
 * @url https://leetcode.cn/problems/daily-temperatures/
 */
public class daily_temperatures {

    public static void main(String[] args) {
        IoUtil.testUtil(daily_temperatures.class,"dailyTemperatures","./txt_file/daily_temperatures.txt");
    }
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] ans = new int[n];

        // 这里用数组作为一个栈 是常用的技巧
        int[] sk = new int[n];
        int size = 0;

        for(int i = n-1;i>=0;i--){
            // 单调栈维护 当前温度大于等于后面（栈）中对应的温度时 会移出栈顶日期
            while(size>0&&temperatures[i] >= temperatures[sk[size-1]]){
                size--;
            }
            // 当然栈不为空 需要添加栈顶
            ans[i] = size == 0 ? 0 : sk[size-1] - i;
            sk[size++] = i; // 添加到栈顶 后面如果小于会被移除
        }

        return ans;
    }
}
