package leetcode.everyday.day_000;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/verify-preorder-serialization-of-a-binary-tree
 * @title: verify-preorder-serialization-of-a-binary-tree
 */
public class Code_0030_331 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0030_331.class,"isValidSerialization","txt_file\\Code_0030_331.txt");
    }


    public boolean isValidSerialization(String preorder) {
        List<String> stk = new ArrayList<>();
        for (String s : preorder.split(",")) {
            stk.add(s);
            while (stk.size() >= 3 && stk.get(stk.size() - 1).equals("#")
                    && stk.get(stk.size() - 2).equals("#") && !stk.get(stk.size() - 3).equals("#")) {
                stk.remove(stk.size() - 1);
                stk.remove(stk.size() - 1);
                stk.remove(stk.size() - 1);
                stk.add("#");
            }
        }
        return stk.size() == 1 && stk.get(0).equals("#");
    }
}