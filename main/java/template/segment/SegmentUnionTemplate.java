package template.segment;

import java.io.*;
import java.util.*;

/**
 * @author: wuxin0011
 * @Description: 线段树区间合并
 * @link <a href="https://www.luogu.com.cn/problem/P2572">线段树区间合并</a>
 */
public class SegmentUnionTemplate {
    static int MAXN = (int) 1e6 + 1;
    static int MOD7 = (int) 1e9 + 7, MOD8 = 80112002, MOD9 = 998244353, inf = Integer.MAX_VALUE;
    static int MOD = (int) MOD9;

    public static class Info {
        public int n, pre, suf;

        public Info(int n, int pre, int suf) {
            this.n = n;
            this.pre = pre;
            this.suf = suf;
        }

        public void set(int n, int pre, int suf) {
            this.n = n;
            this.pre = pre;
            this.suf = suf;
        }
    }

    public static class LazySegment {

        Info[] info0, info1;
        int[] sums;
        int[] arr;
        int[] change;
        boolean[] vis;
        boolean[] rev;

        public LazySegment(int n) {
            this.sums = new int[n << 2];
            this.arr = new int[n << 2];
            this.change = new int[n << 2];
            this.vis = new boolean[n << 2];
            this.rev = new boolean[n << 2];
            this.info0 = new Info[n << 2];
            this.info1 = new Info[n << 2];
        }

        void down(int i, int ln, int rn) {
            if (vis[i]) {
                updateLazy(i << 1, ln, change[i]);
                updateLazy(i << 1 | 1, rn, change[i]);
                vis[i] = false;
            }
            if (rev[i]) {
                reverseLazy(i << 1, ln);
                reverseLazy(i << 1 | 1, rn);
                rev[i] = false;
            }
        }

        void updateLazy(int i, int size, int v) {
            vis[i] = true;
            sums[i] = size * v;
            change[i] = v;
            rev[i] = false;
            int v0 = v == 0 ? size : 0;
            int v1 = v == 1 ? size : 0;
            setInfo(info0, i, v0, v0, v0);
            setInfo(info1, i, v1, v1, v1);
        }

        void reverseLazy(int i, int size) {
            sums[i] = size - sums[i];
            int n0 = info0[i].n, pre0 = info0[i].pre, suf0 = info0[i].suf;
            int n1 = info1[i].n, pre1 = info1[i].pre, suf1 = info1[i].suf;
            setInfo(info0, i, n1, pre1, suf1);
            setInfo(info1, i, n0, pre0, suf0);
            rev[i] = !rev[i];
        }

        void setInfoUP(int i, int ln, int rn, Info[] infos) {
            int l = i << 1, r = i << 1 | 1;
            int size = Math.max(Math.max(infos[l].n, infos[r].n), infos[l].suf + infos[r].pre);
            int pre = infos[l].pre < ln ? infos[l].pre : (infos[l].pre + infos[r].pre);
            int suf = infos[r].suf < rn ? infos[r].suf : (infos[l].suf + infos[r].suf);
            setInfo(infos, i, size, pre, suf);
        }

        // 信息汇总
        void up(int i, int ln, int rn) {
            int l = i << 1, r = i << 1 | 1;
            sums[i] = sums[l] + sums[r];
            setInfoUP(i, ln, rn, info0);
            setInfoUP(i, ln, rn, info1);
        }

        // 初始化信息
        void setInfo(Info[] infos, int i, int n, int pre, int suf) {
            if (infos[i] == null) {
                infos[i] = new Info(n, pre, suf);
            } else {
                infos[i].set(n, pre, suf);
            }
        }

        void build(int l, int r, int i) {
            if (l == r) {
                sums[i] = arr[l];
                int v0 = arr[l] == 0 ? 1 : 0;
                int v1 = arr[l] == 1 ? 1 : 0;
                setInfo(info0, i, v0, v0, v0);
                setInfo(info1, i, v1, v1, v1);
            } else {
                int mid = l + ((r - l) >> 1);
                build(l, mid, i << 1);
                build(mid + 1, r, i << 1 | 1);
                up(i, mid - l + 1, r - mid);
            }
            vis[i] = false;
            rev[i] = false;

        }

        // 查询有多少个1
        int query(int ql, int qr, int l, int r, int i) {
            if (ql <= l && r <= qr) {
                return sums[i];
            }
            int mid = l + ((r - l) >> 1);
            down(i, mid - l + 1, r - mid);
            int ans = 0;
            if (ql <= mid) {
                ans += query(ql, qr, l, mid, i << 1);
            }
            if (qr > mid) {
                ans += query(ql, qr, mid + 1, r, i << 1 | 1);
            }
            up(i, mid - l + 1, r - mid);
            return ans;
        }

        // 反转 0->1 1->0
        void reverse(int ql, int qr, int l, int r, int i) {
            if (ql <= l && r <= qr) {
                reverseLazy(i, r - l + 1);
                return;
            }
            int mid = l + ((r - l) >> 1);
            down(i, mid - l + 1, r - mid);
            if (ql <= mid) {
                reverse(ql, qr, l, mid, i << 1);
            }
            if (qr > mid) {
                reverse(ql, qr, mid + 1, r, i << 1 | 1);
            }
            up(i, mid - l + 1, r - mid);
        }

        // update
        void update(int ql, int qr, int v, int l, int r, int i) {
            if (ql <= l && r <= qr) {
                updateLazy(i, r - l + 1, v);
                return;
            }
            int mid = l + ((r - l) >> 1);
            down(i, mid - l + 1, r - mid);
            if (ql <= mid) {
                update(ql, qr, v, l, mid, i << 1);
            }
            if (qr > mid) {
                update(ql, qr, v, mid + 1, r, i << 1 | 1);
            }
            up(i, mid - l + 1, r - mid);
        }

        // 信息汇总
        public Info queryInfo(int ql, int qr, int l, int r, int i) {
            if (ql <= l && r <= qr) {
                return info1[i];
            } else {
                int mid = l + ((r - l) >> 1);
                down(i, mid - l + 1, r - mid);
                if (qr <= mid) {
                    return queryInfo(ql, qr, l, mid, i << 1);
                }
                if (ql > mid) {
                    return queryInfo(ql, qr, mid + 1, r, i << 1 | 1);
                }
                Info lInfo = queryInfo(ql, qr, l, mid, i << 1);
                Info rInfo = queryInfo(ql, qr, mid + 1, r, i << 1 | 1);
                // 连续1 最长为 max(左，右，左后缀 + 右前缀）
                int size = Math.max(Math.max(rInfo.n, lInfo.n), lInfo.suf + rInfo.pre);
                int pre = lInfo.n < mid - Math.max(ql, l) + 1 ? lInfo.pre : (lInfo.pre + rInfo.pre);
                int suf = rInfo.n < Math.min(r, qr) - mid ? rInfo.suf : (lInfo.suf + rInfo.suf);
                return new Info(size, pre, suf);
            }

        }

    }

    static LazySegment seg = new LazySegment((int) 1e5 + 10);

    public static class MainClass {

        void solve() {
            int n = io.read();
            int m = io.read();
            for (int i = 1; i <= n; i++) {
                seg.arr[i] = io.read();
            }
            seg.build(1, n, 1);

            for (; m > 0; m--) {
                int op = io.read();
                int l = io.read();
                int r = io.read();
                l++;
                r++;
                if (op == 0 || op == 1) {
                    seg.update(l, r, op, 1, n, 1);
                } else if (op == 2) {
                    seg.reverse(l, r, 1, n, 1);
                } else if (op == 3) {
                    io.println(seg.query(l, r, 1, n, 1));
                } else if (op == 4) {
                    io.println(seg.queryInfo(l, r, 1, n, 1).n);
                }
            }

        }

    }

    static IO io;

    public static void main(String[] args) {
        io = new IO();
        int t = 1;
        // t = io.read();
        for (; t > 0; t--) {
            new MainClass().solve();
        }
        io.close();
    }

    static boolean isDebug = true;

    public static class IO {
        public static final String DEFAULT_FILE_NAME = "in.txt"; // default read file
        public static final boolean DELETE_CLOSE = false; // auto close ?
        public BufferedInputStream br = null;
        public PrintWriter ptr = null;
        public boolean splitDOT = true; // if string contains ',' ,need split ?,default true

        {
            try {
                File file = new File(DEFAULT_FILE_NAME);
                br = file.exists() ? new BufferedInputStream(new FileInputStream(file)) : br;
            } catch (Exception ignored) {

            }
            initIO(false);
        }

        public void initIO(boolean flushIO) {
            br = flushIO || br == null ? new BufferedInputStream(System.in) : br;
            ptr = flushIO || ptr == null ? new PrintWriter(new BufferedOutputStream(System.out)) : ptr;
        }

        public int read() {
            long x = readLong();
            if (x > Integer.MAX_VALUE || x < Integer.MIN_VALUE) {
                throw new RuntimeException("overflow int type");
            }
            return (int) x;
        }

        public long readLong() {
            try {
                int c = br.read();
                int f = 1;
                long x = 0;
                while (c < '0' || c > '9') {
                    if (c == '-') {
                        f = -1;
                    }
                    c = br.read();
                }
                while (c >= '0' && c <= '9') {
                    x = x * 10 + (c - '0');
                    c = br.read();
                }
                return x * f;
            } catch (IOException e) {
                System.err.println("read Error,place check your input is number !");
                return -1;
            }
        }

        public void dbg(Object... args) {
            if (!isDebug) {
                return;
            }
            String argsStringIndex = "";
            // argsStringIndex += "{ ";
            // for(int i = 0;i < args.length;i++) {
            // argsStringIndex += i;
            // if(i != args.length-1) {
            // argsStringIndex +=",";
            // }
            // }
            // argsStringIndex += " }";
            String str = "";
            str += "{ ";
            for (int i = 0; i < args.length; i++) {
                str += String.valueOf(args[i]);
                if (i < args.length - 1) {
                    str += " ,";
                }
            }
            str += " }";
            println(argsStringIndex + (argsStringIndex.length() != 0 ? " = " : "") + str);
        }

        public void println(Object... objs) {
            for (int i = 0; i < objs.length; i++) {
                ptr.print(objs[i]);
            }
            ptr.print("\n");
        }

        public void print(Object... objs) {
            for (int i = 0; i < objs.length; i++) {
                ptr.print(objs[i]);
            }
        }

        public void printf(String format, Object... obj) {
            ptr.printf(format, obj);
        }

        public void close() {
            try {
                if (br != null)
                    br.close();

                if (ptr != null) {
                    ptr.flush();
                    ptr.close();
                }
                if (DELETE_CLOSE) {
                    br = null;
                    ptr = null;
                }
            } catch (Exception ignore) {

            }
        }

    }

}
