package leetcode.contest.weekly.w_300.w_388.c;

import leetcode.utils.IoUtil;

import java.util.*;

/**
 * @author: wuxin0011
 * @Description:
 * @url https://leetcode.cn/problems/shortest-uncommon-substring-in-an-array/
 */
@SuppressWarnings("all")
public class C {
    public static void main(String[] args) {
        IoUtil.testUtil(C.class, "shortestSubstrings", "in.txt");
    }

    public static class StringHash {
        private static final int MAX = 1000001;
        private static final int BASE = 499;
        private int max, base, right;
        private long[] pow, hash;
        private char[] cs;

        public StringHash() {
            this(MAX, BASE);
        }

        public StringHash(int max) {
            this(max, BASE);
        }

        public StringHash(int max, String s) {
            this(max, BASE, s);
        }

        public StringHash(int max, char[] cs) {
            this(max, BASE, cs);
        }

        public StringHash(String s) {
            this(-1, BASE, s);
        }

        public StringHash(char[] cs) {
            this(-1, BASE, cs);
        }

        public StringHash(int max, int base) {
            this(max, base, (char[]) null);
        }

        public StringHash(int max, int base, String s) {
            this(max, base, s == null ? (char[]) null : s.toCharArray());
        }

        public StringHash(int max, int base, char[] cs) {
            this.max = max <= 0 ? MAX : max;
            this.base = base;
            this.hash = new long[max];
            this.pow = new long[max];
            if (cs != null) {
                this.build(cs);
            }
        }

        public StringHash build(String s) {
            if (s == null || "".equals(s)) {
                return this;
            }
            this.build(s.toCharArray());
            return this;
        }


        public StringHash build(char[] ss) {
            if (ss == null || ss.length == 0) {
                return this;
            }

            if (ss.length > max) {
                throw new RuntimeException(" out ! ss.length =  " + ss.length + ",max = " + max);
            }
            int n = ss.length;
            this.right = n;
            this.cs = ss;
            pow[0] = 1;
            hash[0] = ss[0] - 'a' + 1;
            for (int i = 1; i < n; i++) {
                pow[i] = pow[i - 1] * base;
                hash[i] = hash[i - 1] * base + ss[i] - 'a' + 1;
            }
            return this;
        }

        public long getHash() {
            return getHash(0, this.right);
        }


        public long getHash(int r) {
            return getHash(0, r);
        }

        // [l,r)
        public long getHash(int l, int r) {
            if (r <= 0) {
                return 0;
            }
            if (r > max) {
                throw new RuntimeException("  right > max ! place check max");
            }
            long ans = hash[r-1];
            if (l > 0) {
                ans -= hash[l - 1] * pow[r - l];
            }
            return ans;
        }
    }


    public String[] shortestSubstrings(String[] arr) {
        Map<Long, Integer> map = new HashMap<>();
        StringHash[] hashs = new StringHash[arr.length];
        for (int i = 0; i < hashs.length; i++) {
            String s = arr[i];
            hashs[i] = new StringHash(s.length(), s);
            for (int j = 0; j < s.length(); j++) {
                for (int k = j+1; k <= s.length(); k++) {
                    long h = hashs[i].getHash(j, k);
                    map.put(h, map.getOrDefault(h, 0) + 1);
                }
            }
        }
        String[] ans = new String[arr.length];
        for(int i = 0;i<hashs.length;i++){
            String s = arr[i];
            String ss = "";
            Map<Long,Integer> thisMap = new HashMap<>();

            for (int j = 0; j < s.length(); j++) {
                for (int k = j+1; k <= s.length(); k++) {
                    long h = hashs[i].getHash(j, k);
                    thisMap.put(h, thisMap.getOrDefault(h, 0) + 1);
                }
            }


            for (int j = 0; j < s.length(); j++) {
                for (int k = j+1; k <=s.length(); k++) {
                    long h = hashs[i].getHash(j, k);
                    if(map.getOrDefault(h,0).equals(thisMap.getOrDefault(h,0)) ){
                        String os = s.substring(j,k);
                        if( ss.length() == 0 ) {
                            ss = os;
                            continue;
                        }
                        if(ss.length()>os.length()){
                            ss = os;
                            continue;
                        }
                        if(is(ss,os)){
                            ss = os;
                            continue;
                        }
                    }
                }
            }
            ans[i] = ss;
        }

        return ans;
    }

    public static boolean is(String a,String b){
        if(a.length() != b.length()) return false;
        for(int i = 0;i<a.length();i++){
            if(a.charAt(i)!=b.charAt(i)){
                return a.charAt(i) > b.charAt(i);
            }
        }
        return true;
    }
}
