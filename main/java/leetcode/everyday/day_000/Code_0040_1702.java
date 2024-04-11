package leetcode.everyday.day_000;

import code_generation.utils.IoUtil;
/**
 * 1702. 修改后的最大二进制字符串
 * <p>
 * 给你一个二进制字符串binary，它仅有0或者1组成。你可以使用下面的操作任意次对它进行修改：操作 1 ：如果二进制串包含子字符串"00"，你可以用"10"将其替换。比方说，"00010" -> "10010"操作 2 ：如果二进制串包含子字符串"10"，你可以用"01"将其替换。比方说，"00010" -> "00001"请你返回执行上述操作任意次以后能得到的 最大二进制字符串。如果二进制字符串 x对应的十进制数字大于二进制字符串 y对应的十进制数字，那么我们称二进制字符串x大于二进制字符串y。
 * <p>
 * 示例 1：输入：binary = "000110"输出："111011"解释：一个可行的转换为："000110" -> "000101" "000101" -> "100101" "100101" -> "110101" "110101" -> "110011" "110011" -> "111011"
 * <p>
 * 示例 2：输入：binary = "01"输出："01"解释："01" 没办法进行任何转换。
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= binary.length <= 105
 * binary 仅包含'0' 和'1' 。
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/maximum-binary-string-after-change
 * @title: 修改后的最大二进制字符串
 */
public class Code_0040_1702 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0040_1702.class, "maximumBinaryString", "txt_file\\Code_0040_1702.txt");
    }


    public String maximumBinaryString(String binary) {
        char[] cs = binary.toCharArray();
        int pos = -1;
        for (int i = 0; i < cs.length; i++) {
            if(cs[i] == '0'){
                pos = i;
                break;

            }
        }
        if(pos==-1){
            return binary;
        }
        int cnt = 0;
        int n = cs.length;
        for(;pos<n;pos++){
            if(cs[pos]=='1'){
                cnt++;
            }
        }
        StringBuilder sb = new StringBuilder();
        int l = n - cnt - 1;
        while(l>0){
            sb.append(1);
            l--;
        }
        sb.append(0);
        while(cnt>0){
            sb.append(1);
            cnt--;
        }
        return sb.toString();
    }



}