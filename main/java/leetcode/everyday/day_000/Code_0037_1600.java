package leetcode.everyday.day_000;

import code_generation.utils.IoUtil;
import java.util.*;

import code_generation.contest.ParseCodeInfo;
/**
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/throne-inheritance
 * @title: throne-inheritance
 */
public class Code_0037_1600 {

    public static void main(String[] args) {
        IoUtil.testUtil(ThroneInheritance.class,ParseCodeInfo.ConstructorClass,"txt_file\\Code_0037_1600.txt");
    }
	  

	 public static class ThroneInheritance {
         private String king;
         private Set<String> dead = new HashSet<>();
         private Map<String, List<String>> g = new HashMap<>();
         private List<String> ans = new ArrayList<>();

         public ThroneInheritance(String kingName) {
             king = kingName;
         }

         public void birth(String parentName, String childName) {
             g.computeIfAbsent(parentName, k -> new ArrayList<>()).add(childName);
         }

         public void death(String name) {
             dead.add(name);
         }

         public List<String> getInheritanceOrder() {
             ans.clear();
             dfs(king);
             return ans;
         }

         private void dfs(String x) {
             if (!dead.contains(x)) {
                 ans.add(x);
             }
             for (String y : g.getOrDefault(x, new ArrayList<String>())) {
                 dfs(y);
             }
         }
     }


}