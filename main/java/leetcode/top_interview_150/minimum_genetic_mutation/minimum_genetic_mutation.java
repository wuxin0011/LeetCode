package leetcode.top_interview_150.minimum_genetic_mutation;

import leetcode.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description: 最小基因突变
 */
public class minimum_genetic_mutation {
    public static void main(String[] args) {
        IoUtil.testUtil(minimum_genetic_mutation.class, "minMutation");
    }

    public int minMutation(String startGene, String endGene, String[] bank) {
//        Map<String, Integer> map = new HashMap<>();
//        for (int i = 0; i < bank.length; i++) {
//            map.put(bank[i], i);
//        }
        boolean f = false;
        for (int i = 0; i < bank.length; i++) {
            if (bank[i].equals(endGene)) {
                f = true;
                break;
            }
        }
        if (!f) return -1;
        // 变化后的基因必须位于基因库 bank 中）
        int cnt = 0;
        int n = endGene.length();
        char[] s1 = startGene.toCharArray();
        char[] s2 = endGene.toCharArray();

        for (int i = 0; i < n; i++) {
            if (s1[i] == s2[i]) {
                continue;
            }
            if (s2[i] == 'T' && (s1[i] == 'C' || s1[i] == 'G')) {
                return -1;
            }
            if (s2[i] == 'A' && (s1[i] == 'C' || s1[i] == 'G')) {
                return -1;
            }
            if (s2[i] == 'C' && s1[i] != 'G' || s2[i] == 'G' && s1[i] != 'C') {
                return -1;
            }
            s1[i] = s2[i];
            cnt++;

        }
        // System.out.println("cnt = " + cnt);
        return cnt;
    }


    public static int getIndex(char c) {
        switch (c) {
            case 'A':
                return 0;
            case 'C':
                return 1;
            case 'G':
                return 2;
            case 'T':
                return 3;
            default:
                return -1;
        }
    }
}
