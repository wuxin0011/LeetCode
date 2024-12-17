package template.str.hash;

import code_generation.utils.RandomArrayUtils;
import template.str.Manacher.ManacherTemplate;
import template.str.kmp.KMP_Template;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 双模hash 常数大于普通 hash 板子，但是极大不会被卡（除非是常数过大 卡tl)
 */
public class StringHash2Template {


    // 检查回文 如果需要copy请直接附带
    static class StringHashPalindrome {
        int mod1, mod2, base1, base2;
        StringHash stringHash1, stringHash2;
        int n;
        int[] a;

        StringHashPalindrome() {
            this(StringHash.BASE1, StringHash.BASE2, StringHash.MOD1, StringHash.MOD2);
        }

        StringHashPalindrome(int base1, int base2, int mod1, int mod2) {
            this.base1 = base1;
            this.base2 = base2;
            this.mod1 = mod1;
            this.mod2 = mod2;
        }

        void build(char[] chars) {
            int[] array = new int[chars.length];
            for (int i = 0; i < chars.length; i++) {
                array[i] = chars[i];
            }
            this.build(array);
        }

        void build(int[] array) {
            this.a = array;
            this.n = array.length;
            this.stringHash1 = new StringHash(base1, base2, mod1, mod2);
            this.stringHash2 = new StringHash(base1, base2, mod1, mod2);
            int[] b = new int[n];
            for (int i = 0; i < n; i++) {
                b[i] = a[n - i - 1];
            }
            this.stringHash1.build(a);
            this.stringHash2.build(b);
        }


        // query [l,r]  isPalindrome
        boolean isPalindromeString(int l, int r) {
            return stringHash1.get(l, r) == stringHash2.get(n - r - 1, n - l - 1);
        }
    }


    static class StringHash {
        private static final int BASE1 = (int) 8e8 + new Random().nextInt((int) 1e8);
        private static final int BASE2 = (int) 8e8 + new Random().nextInt((int) 1e8);
        private static final int MOD1 = 1_070_777_777;
        private static final int MOD2 = 1_000_000_007;

        int mod1, mod2, base1, base2;
        int[] powBase1, powBase2, preHash1, preHash2;


        public StringHash() {
            this(BASE1, BASE2, MOD1, MOD2);
        }


        public StringHash(int b1, int b2, int m1, int m2) {
            this.base1 = b1;
            this.base2 = b2;
            this.mod1 = m1;
            this.mod2 = m2;
        }


        public void build(char[] chars) {
            int[] a = new int[chars.length];
            for (int i = 0; i < chars.length; i++) {
                a[i] = chars[i];
            }
            this.build(a);
        }

        public void build(int[] nums) {
            int n = nums.length;
            powBase1 = new int[n];
            powBase2 = new int[n];
            preHash1 = new int[n];
            preHash2 = new int[n];
            powBase1[0] = powBase2[0] = 1;
            preHash1[0] = preHash2[0] = nums[0] + 1;
            for (int i = 1; i < n; i++) {
                powBase1[i] = (int) (powBase1[i - 1] * 1L * base1 % mod1);
                powBase2[i] = (int) (powBase2[i - 1] * 1L * base2 % mod2);
                preHash1[i] = (int) ((preHash1[i - 1] * 1L * base1 + nums[i] + 1) % mod1);
                preHash2[i] = (int) ((preHash2[i - 1] * 1L * base2 + nums[i] + 1) % mod2);
            }
        }

        // [l,r]
        // len = r - l + 1
        public long get(int l, int r) {
            long subHash1 = ((preHash1[r] - (l > 0 ? (preHash1[l - 1] * 1L * powBase1[r - l + 1]) : 0)) % mod1 + mod1) % mod1;
            long subHash2 = ((preHash2[r] - (l > 0 ? (preHash2[l - 1] * 1L * powBase2[r - l + 1]) : 0)) % mod2 + mod2) % mod2;
            long subHash = subHash1 << 32 | subHash2;
            return subHash;
        }
    }



    public static void main(String[] args) {
        // test();
//        test02();
        test03();
    }


//    测试KMP

    static void test() {


        boolean ok = true;

        for (int t = 0; t < 1; t++) {
            char[] chars = RandomArrayUtils.randomCharArray(100, 1000);
//            chars = "admin hello world fdasfasfdsafdsadadminadminpp aadmin admin xxxx admin".toCharArray();
            char[] p = RandomArrayUtils.randomCharArray(chars.length);
//            p = "a".toCharArray();
            List<Integer> ans = KMP_Template.kmpList(chars, p);
//            System.out.println(ans);
            List<Integer> res = new ArrayList<>();


            StringHash hash1 = new StringHash();
            StringHash hash2 = new StringHash();


            hash1.build(chars);
            hash2.build(p);

            int n = p.length;
            long v1 = hash2.get(0, n - 1);
//        System.out.println(v1);
            for (int i = 0; i < chars.length - n + 1; i++) {
                if (hash1.get(i, i + n - 1) == v1) {
                    res.add(i);
                }
            }
//        System.out.println("res = " + res);
            if (!res.equals(ans)) {
                System.out.println("expect = " + ans + ",result = " + res);
                ok = false;
                break;
            }
        }
        System.out.println(ok ? "ok" : "error");
    }

    //测试马拉车
    static void test02() {
        System.out.println("测试双模hash 和 马拉车");
        boolean ok = true;
        long t1 = 0, t2 = 0, time = 0;
        String errorStr = "";
        StringHashPalindrome hash = new StringHashPalindrome();
        next:
        for (int T = 10; T > 0; T--) {

            String s = new String(RandomArrayUtils.randomCharArray(100, (int) 1e4));
            long c1 = 0, c2 = 0;
            time++;

            hash.build(s.toCharArray());


            // 马拉车
            ManacherTemplate.Manacher manacher = new ManacherTemplate.Manacher(s);

            for (int i = 0; i < s.length(); i++) {
                for (int j = i + 1; j < s.length(); j++) {
                    long st = System.currentTimeMillis();
                    boolean checkResult = hash.isPalindromeString(i, j);
                    long ed = System.currentTimeMillis();
                    boolean manacherQueryResult = manacher.query(i, j + 1);
                    long e2 = System.currentTimeMillis();
                    if (checkResult != manacherQueryResult) {
                        ok = false;
                        errorStr = s;
                        System.out.println("String = " + s.substring(i, j + 1) + " query = {  " + i + " " + j + " }" + " result = " + checkResult + ",manacher = " + manacherQueryResult);

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
        if (ok) {
            System.out.println("string hash       : " + (t1 * 1.0 / 100) + ":ms");
            System.out.println("manacher          : " + (t2 * 1.0 / 100) + ":ms");
        } else {
            System.out.println(time);
        }
    }


    static void test03() {
        System.out.println("测试双模hash 和 普通hash ");
        boolean ok = true;
        long t1 = 0, t2 = 0, time = 0;
        String errorStr = "";

        next:
        for (int T = 100; T > 0; T--) {


            String s = new String(RandomArrayUtils.randomCharArray(100, (int) 1e5));
            long c1 = 0, c2 = 0;
            time++;
            StringHashPalindrome hash = new StringHashPalindrome();

            StringHashMod.StringHash3.calcHash(s.toCharArray(),true);

            hash.build(s.toCharArray());

            for (int i = 0; i < s.length(); i++) {
                for (int j = i + 1; j < s.length(); j++) {
                    long st = System.currentTimeMillis();
                    boolean checkResult = hash.isPalindromeString(i, j);
                    long ed = System.currentTimeMillis();
                    boolean stringhash1Result = StringHashMod.StringHash3.isPalindromeString(i,j);
                    long e2 = System.currentTimeMillis();
                    if (checkResult != stringhash1Result) {
                        ok = false;
                        errorStr = s;
                        System.out.println("String = " + s.substring(i, j + 1) + " query = {  " + i + " " + j + " }" + " result = " + checkResult + ",stringhash1Result = " + stringhash1Result);

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
        if (ok) {
            System.out.println("双模hash      : " + (t1 * 1.0 / 100) + ":ms");
            System.out.println("单hash        : " + (t2 * 1.0 / 100) + ":ms");
        } else {
            System.out.println(time);
        }
    }


}
