package template.str.suffixArray_template;

import code_generation.utils.RandomArrayUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 基于SA_IS 实现的后缀数组
 * @author: wuxin0011
 * @Description:
 */
@SuppressWarnings("all")
public class SuffixArray_SA_IS_Template {


    public static class SAIS {

        // TODO 待实现
        private static final int ALPHABET_SIZE = 256;

        public static int[] buildSuffixArray(String s) {
          return new int[]{};
        }

    }
    public static void main(String[] args) {
        char[] s = RandomArrayUtils.randomCharArray(10, 20);
        int[] sa = SAIS.buildSuffixArray(new String(s));
        System.out.println(Arrays.toString(s));
        System.out.println("当前位置后缀数组的排名 " + Arrays.toString(sa));
//        System.out.println("当前位置后缀数组的LCP(sa[i],sa[i+1]) " + Arrays.toString(suffixArray.lc));
//        System.out.println("rk = " + Arrays.toString(suffixArray.rk));


        check(s, sa,new int[]{});
    }


    // 暴力方法
    public static void check(char[] s, int[] sa, int[] lcp) {
        int n = s.length;
        int[] sa1 = new int[n];
        Integer[] ids = new Integer[n];
        Arrays.setAll(ids, i -> i);

        for (int i = 0; i < n; i++) {
            String cur = new String(s, i, n - i);
//            System.out.println(cur);
        }
        Arrays.sort(ids, (i, j) -> new String(s, i, n - i).compareTo(new String(s, j, n - j)));
//        System.out.println(Arrays.toString(ids));
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            sa1[i] = ids[i];
            map.put(ids[i], i);
        }

        System.out.println("\n\n排名对比" + (Arrays.equals(sa1, sa) ? "ok" : "error"));
        if(!Arrays.equals(sa1,sa)){
            System.out.println("sa1 = " + Arrays.toString(sa1));
            System.out.println("sa = " + Arrays.toString(sa));
        }

        int[] lcp1 = new int[n - 1];


        // 暴力求LCP
        for (int i = 0; i < n - 1; i++){
            int currentLcp = 0;
            while (sa[i] + currentLcp < n && sa[i + 1] + currentLcp < n && s[sa[i] + currentLcp] == s[sa[i+1] + currentLcp]) {
                currentLcp++;
            }

            lcp1[i] = currentLcp;
        }

        System.out.println("LCP对比：" + (Arrays.equals(lcp1, lcp) ? "ok" : "error"));
        if(!Arrays.equals(lcp1, lcp)){
            System.out.println("lcp1 = " + Arrays.toString(lcp1));
            System.out.println("lcp = " + Arrays.toString(lcp));
        }
    }
}
