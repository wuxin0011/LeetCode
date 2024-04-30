package leetcode.everyday.day_000;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 * 1017. 负二进制转换给你一个整数 n ，以二进制字符串的形式返回该整数的 负二进制（base -2）表示。
 * 注意，除非字符串就是"0"，否则返回的字符串中不能含有前导零。
 *
 * 示例 1：
 * 输入：n = 2
 * 输出："110"
 * 解释：(-2)2 + (-2)1 = 2
 *
 * 示例 2：
 * 输入：n = 3
 * 输出："111"
 * 解释：(-2)2 + (-2)1 + (-2)0 = 3
 *
 * 示例 3：
 * 输入：n = 4
 * 输出："100"
 * 解释：(-2)2 = 4
 *
 * 提示：
 * 	0 <= n <= 10^9
 *
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/convert-to-base-2
 * @title: 负二进制转换
 */
public class Code_0057_1017 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0057_1017.class,"baseNeg2","txt_file\\Code_0057_1017.txt");
    }
     

    public String baseNeg2(int n) {
        StringBuilder ans=new StringBuilder();
        if(n==0){
            ans.append("0");
        }
        while(n!=0){
            int res=0;
            if(n<0){
                res=-n%2;
            }else{
                res=n%2;
            }
            ans.append(res);
            n=(n-res)/(-2);
        }
        return ans.reverse().toString();
	}




}