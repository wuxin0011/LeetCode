package template.str.suffixArray_template;

import code_generation.utils.RandomArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 参考文章
 * https://www.cnblogs.com/xiwen-ydys/p/18299719#%E5%90%8E%E7%BC%80%E6%95%B0%E7%BB%84%E7%AE%80%E4%BB%8B
 * <p>
 * 后缀数组可以用于什么场景
 * 单纯的后缀数组
 * 1、寻找最小的循环移动位置
 * 2、在字符串中寻找子串
 * 3、给你一个字符串，每次从首或尾取一个字符组成字符串，问所有能够组成的字符串中字典序最小的一个
 * 最长公共前缀
 * <p>
 * 1、两子串最长公共前缀
 * 2、比较一个字符串两个子串的大小关系
 * 3、不同子串的数目
 * 4、出现至少 k 次的子串的最大长度
 * 5、是否有某字符串在文本串中至少不重叠地出现了两次
 * 6、连续的若干相同子串
 *
 * @author: wuxin0011
 * @Description:
 */
@SuppressWarnings("all")
public class SuffixArrayTemplate {


    public static class SuffixArray {
        public int n;
        public int[] sa,rk,lc;

        public SuffixArray(char[] s) {
            n = s.length;
            sa = new int[n];
            lc = new int[n - 1];
            rk = new int[n];

            Integer[] saWrapper = new Integer[n];
            for (int i = 0; i < n; i++) {
                saWrapper[i] = i;
            }

            Arrays.sort(saWrapper, (a, b) -> Character.compare(s[a], s[b]));

            for (int i = 0; i < n; i++) {
                sa[i] = saWrapper[i];
            }

            rk[sa[0]] = 0;
            for (int i = 1; i < n; ++i) {
                rk[sa[i]] = rk[sa[i - 1]] + (s[sa[i]] != s[sa[i - 1]] ? 1 : 0);
            }

            int k = 1;
            ArrayList<Integer> tmp = new ArrayList<>();
            int[] cnt = new int[n];

            while (rk[sa[n - 1]] < n - 1) {
                tmp = new ArrayList<>();
                for (int i = 0; i < k; ++i) {
                    tmp.add(n - k + i);
                }
                for (int i : sa) {
                    if (i >= k) {
                        tmp.add(i - k);
                    }
                }

                Arrays.fill(cnt, 0);
                for (int i = 0; i < n; ++i) {
                    ++cnt[rk[i]];
                }
                for (int i = 1; i < n; ++i) {
                    cnt[i] += cnt[i - 1];
                }

                int[] newSa = new int[n];
                for (int i = n - 1; i >= 0; --i) {
                    newSa[--cnt[rk[tmp.get(i)]]] = tmp.get(i);
                }
                sa = newSa;

                int[] newRk = rk.clone();
                rk = new int[n];
                rk[sa[0]] = 0;
                for (int i = 1; i < n; ++i) {
                    boolean less = (newRk[sa[i - 1]] < newRk[sa[i]]) ||
                            (sa[i - 1] + k == n) ||
                            (newRk[sa[i - 1] + k] < newRk[sa[i] + k]);
                    rk[sa[i]] = rk[sa[i - 1]] + (less ? 1 : 0);
                }
                k *= 2;
            }

            for (int i = 0, j = 0; i < n; ++i) {
                if (rk[i] == 0) {
                    j = 0;
                } else {
                    if (j > 0) j--;
                    int prevSuffix = sa[rk[i] - 1];
                    while (i + j < n && prevSuffix + j < n &&
                            s[i + j] == s[prevSuffix + j]) {
                        ++j;
                    }
                    lc[rk[i] - 1] = j;
                }
            }
        }
    }


    public static void main(String[] args) {
        char[] s = RandomArrayUtils.randomCharArray(10, 20);
        SuffixArray suffixArray = new SuffixArray(s);
        System.out.println(Arrays.toString(s));
        System.out.println("当前位置后缀数组的排名 " + Arrays.toString(suffixArray.sa));
        System.out.println("当前位置后缀数组的LCP(sa[i],sa[i+1]) " + Arrays.toString(suffixArray.lc));
        System.out.println("rk = " + Arrays.toString(suffixArray.rk));


        check(s, suffixArray.sa, suffixArray.lc);
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
