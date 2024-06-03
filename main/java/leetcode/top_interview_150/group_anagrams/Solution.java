package leetcode.top_interview_150.group_anagrams;

import code_generation.utils.IoUtil;

import java.util.*;

/**
 * 49. 字母异位词分组
 * <p>
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。
 * 可以按任意顺序返回结果列表。
 * 字母异位词 是由重新排列源单词的所有字母得到的一个新单词。
 * <p>
 * 示例 1:
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * <p>
 * 示例 2:
 * 输入: strs = [""]
 * 输出: [[""]]
 * <p>
 * 示例 3:
 * 输入: strs = ["a"]
 * 输出: [["a"]]
 * <p>
 * 提示：
 * 1 <= strs.length <= 10^4
 * 0 <= strs[i].length <= 100
 * strs[i]仅包含小写字母
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/group-anagrams
 * @title: 字母异位词分组
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "groupAnagrams", "in.txt", false, false);
    }


    public List<List<String>> groupAnagrams(String[] strs) {

        List<List<String>> ans = new ArrayList<>();
        // 一个单词展开
        Map<String, Integer> map = new HashMap<>();
        for (String str : strs) {
            char[] chs = str.toCharArray();
            Arrays.sort(chs);
            String t = new String(chs);
            Integer idx = map.get(t);
            if (idx != null) {
                ans.get(idx).add(str);
            } else {
                List<String> temp = new ArrayList<>();
                temp.add(str);
                map.put(t, ans.size());
                ans.add(temp);
            }
        }
        return ans;
    }


}