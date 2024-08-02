package leetcode._0x3f_.slide_window.slide_0023;

import code_generation.utils.IoUtil;

/**
 *
 * 2024. 考试的最大困扰度
 *
 * 一位老师正在出一场由 n道判断题构成的考试，每道题的答案为 true （用 'T' 表示）或者 false （用 'F'表示）。老师想增加学生对自己做出答案的不确定性，方法是最大化有 连续相同结果的题数。（也就是连续出现 true 或者连续出现 false）。给你一个字符串answerKey，其中answerKey[i]是第 i个问题的正确结果。除此以外，还给你一个整数 k，表示你能进行以下操作的最多次数：每次操作中，将问题的正确答案改为'T' 或者'F'（也就是将 answerKey[i] 改为'T'或者'F'）。请你返回在不超过 k次操作的情况下，最大连续 'T'或者 'F'的数目。
 *
 * 示例 1：输入：answerKey = "TTFF", k = 2输出：4解释：我们可以将两个 'F' 都变为 'T' ，得到 answerKey = "TTTT" 。总共有四个连续的 'T' 。
 *
 * 示例 2：输入：answerKey = "TFFT", k = 1输出：3解释：我们可以将最前面的 'T' 换成 'F' ，得到 answerKey = "FFFT" 。或者，我们可以将第二个 'T' 换成 'F' ，得到 answerKey = "TFFF" 。两种情况下，都有三个连续的 'F' 。
 *
 * 示例 3：输入：answerKey = "TTFTTFTT", k = 1输出：5解释：我们可以将第一个 'F' 换成 'T' ，得到 answerKey = "TTTTTFTT" 。或者我们可以将第二个 'F' 换成 'T' ，得到 answerKey = "TTFTTTTT" 。两种情况下，都有五个连续的 'T' 。
 *
 * 提示：
 *
 *
 * 	n == answerKey.length
 * 	1 <= n <= 5 * 104
 * 	answerKey[i]要么是'T' ，要么是'F'
 * 	1 <= k <= n
 *
 *
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/maximize-the-confusion-of-an-exam
 * @title: maximize-the-confusion-of-an-exam
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"maxConsecutiveAnswers","in.txt");
    }
     

    public int maxConsecutiveAnswers(String answerKey, int k) {
        char[] ch = answerKey.toCharArray();
        return Math.max(f(ch,k,'F'),f(ch,k,'T'));
	}

    public static  int f(char[] ch,int k,char O){
        int r = 0,l = 0;
        int n = ch.length;
        int ans = 0;
        while(r<n){
            if(ch[r]==O)k--;
            while(k<0){
                if(ch[l]==O)k++;
                l++;
            }
            ans = Math.max(ans,r - l+1);
            r++;
        }
        return ans;
    }



  

}