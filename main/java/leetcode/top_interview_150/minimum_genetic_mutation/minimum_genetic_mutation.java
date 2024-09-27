package leetcode.top_interview_150.minimum_genetic_mutation;

import code_generation.utils.IoUtil;

/**
 * 433. 最小基因变化
 * <p>
 * 基因序列可以表示为一条由 8 个字符组成的字符串，其中每个字符都是 'A'、'C'、'G' 和 'T' 之一。
 * 假设我们需要调查从基因序列start 变为 end 所发生的基因变化。一次基因变化就意味着这个基因序列中的一个字符发生了变化。
 * 例如，"AACCGGTT" --> "AACCGGTA" 就是一次基因变化。
 * 另有一个基因库 bank 记录了所有有效的基因变化，只有基因库中的基因才是有效的基因序列。（变化后的基因必须位于基因库 bank 中）
 * 给你两个基因序列 start 和 end ，以及一个基因库 bank ，请你找出并返回能够使start 变化为 end 所需的最少变化次数。如果无法完成此基因变化，返回 -1 。
 * 注意：起始基因序列start 默认是有效的，但是它并不一定会出现在基因库中。
 * <p>
 * 示例 1：
 * 输入：start = "AACCGGTT", end = "AACCGGTA", bank = ["AACCGGTA"]
 * 输出：1
 * <p>
 * 示例 2：
 * 输入：start = "AACCGGTT", end = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]
 * 输出：2
 * <p>
 * 示例 3：
 * 输入：start = "AAAAACCC", end = "AACCCCCC", bank = ["AAAACCCC","AAACCCCC","AACCCCCC"]
 * 输出：3
 * <p>
 * 提示：
 * start.length == 8
 * end.length == 8
 * 0 <= bank.length <= 10
 * bank[i].length == 8
 * start、end 和 bank[i] 仅由字符 ['A', 'C', 'G', 'T'] 组成
 *
 * @author: wuxin0011
 * @Description: DFS 从一个一起始点出发 找到一个终点
 * @title: 最小基因变化
 * @url https://leetcode.cn/problems/minimum-genetic-mutation
 */
public class minimum_genetic_mutation {
    public static void main(String[] args) {
        IoUtil.testUtil(minimum_genetic_mutation.class, "minMutation");
    }

    public static int inf = 0x3f;

    public int minMutation(String startGene, String endGene, String[] bank) {
        int mask = 0;
        int ans = inf;
        for (int i = 0; i < bank.length; i++) {
            if (check(startGene, bank[i])) {
                ans = Math.min(ans, dfs(bank[i], mask | 1 << i, endGene, bank) + 1);
            }
        }
        return ans >= inf ? -1 : ans;
    }

    public int dfs(String a, int mask, String endGene, String[] bank) {

        if (a.equals(endGene)) {
            return 0;
        }
        int res = inf;

        for (int i = 0; i < bank.length; i++) {
            if ((mask >> i & 1) == 0) {
                if (check(a, bank[i])) {
                    res = Math.min(dfs(bank[i], mask | 1 << i, endGene, bank) + 1,res);
                }
            }
        }
        return res;
    }

    public boolean check(String a, String b) {
        int n = a.length();
        int u = 0;
        for (int i = 0; i < n; i++) {
            if (a.charAt(i) != b.charAt(i)) u++;
            if (u > 1) return false;
        }
        return u == 1;
    }


}
