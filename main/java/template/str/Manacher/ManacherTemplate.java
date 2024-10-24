package template.str.Manacher;

import code_generation.utils.RandomArrayUtils;

/**
 * @link <a href="https://leetcode.cn/problems/check-if-dfs-strings-are-palindromes/solutions/2957704/mo-ban-dfs-shi-jian-chuo-manacher-suan-f-ttu6/">参考链接</a>
 * @author: wuxin0011
 * @Description: Manacher又称 "马拉车" 算法 是一个 o(1) 判断 字符串区间是不是回文的
 */
public class ManacherTemplate {


    public static class Manacher {
        char[] origin, chars;
        int[] halfLen; // 回文半径


        public Manacher(String s) {
           this(s.toCharArray());
        }

        public Manacher(char[] origin) {
            this.origin = origin;
            this.init();
        }

        public void init() {
            int n = this.origin.length;
            chars = new char[n * 2 + 3];


//            Arrays.fill(chars,'#');
//            chars[0] = '^';
//            for(int i = 0;i < n;i++) {
//                chars[i * 2 + 2] = this.origin[i];
//            }
//            chars[chars.length - 1] = '$';


            for (int i = 0; i < chars.length; i++) {
                if (i == 0) {
                    chars[i] = '^';
                } else if (i == chars.length - 1) {
                    chars[i] = '$';
                } else {
                    chars[i] = (i - 1) % 2 == 0 ? '#' : this.origin[(i - 1) / 2];
                }
            }
            // System.out.println(Arrays.toString(chars));


            halfLen = new int[chars.length - 2];
            halfLen[1] = 1;
            int boxMid = 0, boxR = 0;
            for (int i = 2; i < halfLen.length; i++) { // 循环的起止位置对应着原串的首尾字符
                int hl = 1;
                if (i < boxR) {
                    hl = Math.min(halfLen[boxMid * 2 - i], boxR - i);
                }
                while (chars[i - hl] == chars[i + hl]) {
                    hl++;
                    boxMid = i;
                    boxR = i + hl;
                }
                halfLen[i] = hl;
            }

            // System.out.println(Arrays.toString(halfLen));
        }


        // 询问[l，r) 区间是否回文串
        public boolean query(int l, int r) {
            return halfLen[l + r + 1] > r - l;
        }
    }


    public static void main(String[] args) {
        boolean ok = true;
        long t1 = 0, t2 = 0;
        next:
        for (int T = 100; T > 0; T--) {
            String s = new String(RandomArrayUtils.randomCharArray(100,(int) 1e4));
            Manacher manacher = new Manacher(s.toCharArray());
            long c1 = 0,c2 = 0;
            for (int i = 0; i < s.length(); i++) {
                for (int j = i + 1; j < s.length(); j++) {
                    long st = System.currentTimeMillis();
                    boolean checkResult = check(i, j, s);
                    long ed = System.currentTimeMillis();
                    boolean manacherQueryResult = manacher.query(i, j + 1);
                    long e2 = System.currentTimeMillis();
                    if (checkResult != manacherQueryResult) {
                        ok = false;
                        break next;
                    }

                    c1 += (ed - st);
                    c2 += (e2 - ed);
                }
            }
            t1 += c1;
            t2 += c2;
        }
        System.out.println(ok ? "ok" : "fail");
        if(ok) {
            System.out.println("暴力耗时  : " + (t1 * 1.0 / 100) + ":ms");
            System.out.println("manacher : " + (t2 * 1.0 / 100) + ":ms");
        }
    }

    public static boolean check(int l, int r, String s) {
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
}
