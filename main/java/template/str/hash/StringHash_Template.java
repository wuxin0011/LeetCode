package template.str.hash;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wuxin0011
 * @Description: 自然溢出版本 也是最容易被卡的版本 但是是字符串hash中效率最佳版本
 */
public class StringHash_Template {

    public static final StringHash sh = new StringHash();

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
            long ans = hash[r - 1];
            if (l > 0) {
                ans -= hash[l - 1] * pow[r - l];
            }
            return ans;
        }

        public int getFirst(long h, int len) {
            return getIdx(h, len);
        }

        public int getIdx(long h, int len) {
            for (int i = 0; i < this.right - len + 1; i++) {
                if (h == getHash(i, i + len)) {
                    return i;
                }
            }
            return -1;
        }

        public int getLast(long h, int len) {
            for (int i = this.right; i - len >= 0; i--) {
                if (h == getHash(i - len, i)) {
                    return i - len;
                }
            }
            return -1;
        }

        public List<Integer> getIds(long h, int len) {
            List<Integer> ans = new ArrayList<>();
            for (int i = 0; i < this.right - len + 1; i++) {
                if (h == getHash(i, i + len)) {
                    ans.add(i);
                }
            }
            return ans;
        }
    }


    //********************************************************StringHashSimple*****************************************************
    public static class StringHashSimple {
        private static final int MAX = 1000001;
        private static final int BASE = 499;
        private static long[] pow = new long[MAX];
        private static long[] hash = new long[MAX];

        private static int right;

        public static  void build(String s) {
            if (s == null || "".equals(s)) {
                return;
            }
            build(s.toCharArray());
        }


        public static void build(char[] ss) {
            if (ss == null || ss.length == 0) {
                return;
            }

            if (ss.length > MAX) {
                throw new RuntimeException(" out ! ss.length =  " + ss.length + ",max = " + MAX);
            }
            int n = ss.length;
            pow[0] = 1;
            right = ss.length;
            hash[0] = ss[0] - 'a' + 1;
            for (int i = 1; i < n; i++) {
                pow[i] = pow[i - 1] * BASE;
                hash[i] = hash[i - 1] * BASE + ss[i] - 'a' + 1;
            }
        }

        public static long getHash() {
            return getHash(0, right);
        }


        public static long getHash(int r) {
            return getHash(0, r);
        }

        // [l,r)
        public static long getHash(int l, int r) {
            if (r <= 0) {
                return 0;
            }
            if (r > MAX) {
                throw new RuntimeException("  right > max ! place check max");
            }
            long ans = hash[r - 1];
            if (l > 0) {
                ans -= hash[l - 1] * pow[r - l];
            }
            return ans;
        }

        public static int getFirst(long h, int len) {
            return getIdx(h, len);
        }

        public static int getIdx(long h, int len) {
            for (int i = 0; i < right - len + 1; i++) {
                if (h == getHash(i, i + len)) {
                    return i;
                }
            }
            return -1;
        }

        public static int getLast(long h, int len) {
            for (int i = right; i - len >= 0; i--) {
                if (h == getHash(i - len, i)) {
                    return i - len;
                }
            }
            return -1;
        }

        public static List<Integer> getIds(long h, int len) {
            List<Integer> ans = new ArrayList<>();
            for (int i = 0; i < right - len + 1; i++) {
                if (h == getHash(i, i + len)) {
                    ans.add(i);
                }
            }
            return ans;
        }
    }

}