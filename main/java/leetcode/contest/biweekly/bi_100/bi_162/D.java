package leetcode.contest.biweekly.bi_100.bi_162;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * @author: qitongwei
 * @Description: 分块
 * @url: <a href="https://leetcode.cn/contest/biweekly-contest-162/problems/threshold-majority-queries">查询超过阈值频率最高元素</a>
 * @title: 查询超过阈值频率最高元素
 * <p>
 * <p>
 * * https://leetcode.cn/problems/threshold-majority-queries/submissions/685063297/
 * * https://leetcode.cn/submissions/detail/685063297/
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class D {

    public static void main(String[] args) {
        IoUtil.testUtil(D.class, "subarrayMajority", "D.txt");
    }

    private static final int MOD = (int) 1e9 + 7;

    static int N = (int) 1e4 + 10;
    static int max_block_width = 100;

    static int freq[][] = new int[max_block_width][N], mode[][] = new int[max_block_width][max_block_width], cnt[] = new int[N], a[] = new int[N], b[] = new int[N];

    static int n, m, sz, blen, bcount, sv[] = new int[N], bi[] = new int[N], bl[] = new int[N], br[] = new int[N];

    public int[] subarrayMajority(int[] nums, int[][] queries) {
        n = nums.length;
        m = queries.length;

        // 离散化处理
        rnq(nums);

        // 初始化块信息
        initBlockInfo();

        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int l = queries[i][0], r = queries[i][1], mi = queries[i][2];
            if (mi > r - l + 1) {
                ans[i] = -1;
            } else {
                int[] info = query(l, r);
                if (info[1] < mi) {
                    ans[i] = -1;
                } else {
                    ans[i] = b[info[0]];
                }
            }
        }
        return ans;
    }


    public static int get_cnt(int idl, int idr, int x) {
        if (idl > idr || x < 0) {
            return 0;
        }
        return freq[idr][x] - (idl > 0 ? freq[idl - 1][x] : 0);
    }

    static void clear(int l, int r) {
        for (int i = l; i <= r; i++) {
            cnt[sv[i]] = 0;
        }
    }


    public static void rnq(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            a[i] = b[i] = sv[i] = nums[i];
        }

        Arrays.sort(b, 0, n);
        sz = 1;
        for (int i = 0; i < n; i++) {
            if (b[sz - 1] != b[i]) {
                b[sz++] = b[i];
            }
        }

        for (int i = 0; i < n; i++) {
            sv[i] = lower_bound(nums[i]);
        }
    }

    public static int lower_bound(int x) {
        int l = 0, r = sz - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (b[mid] == x) return mid;
            if (b[mid] > x) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }


    public static void initBlockInfo() {
        blen = Math.min(max_block_width, (int) (Math.sqrt(n)) + 20);
        // 动态块大小
        while (true) {
            bcount = (n + blen - 1) / blen;
            if (bcount >= max_block_width) {
                blen++;
            } else {
                break;
            }
        }
        for (int i = 0; i < n; i++) {
            bi[i] = i / blen;
            cnt[i] = 0;
        }

        for (int i = 0; i < bcount; i++) {
            bl[i] = Math.min(n - 1, i * blen);
            br[i] = Math.min(n - 1, (i + 1) * blen - 1);
            Arrays.fill(freq[i], 0,sz + 1,0);
            if (bi[i] == n - 1 || br[i] == n - 1) break;
        }


        for (int id = 0; id < bcount; id++) {
            for (int i = bl[id]; i <= br[id]; i++) {
                freq[id][sv[i]]++;
            }
            if (id == 0) continue;
            for (int x = 0; x < sz; x++) {
                freq[id][x] += freq[id - 1][x];
            }
        }

        Arrays.fill(cnt, 0, sz + 1, 0);
        for (int id = 0; id < bcount; id++) {
            int most = 0;
            int most_cnt = 0;
            clear(bl[id], br[id]);
            for (int i = bl[id]; i <= br[id]; i++) {
                cnt[sv[i]]++;
                if (cnt[sv[i]] > most_cnt || cnt[sv[i]] == most_cnt && sv[i] < most) {
                    most = sv[i];
                    most_cnt = cnt[sv[i]];
                }
            }

            mode[id][id] = most;
        }

        Arrays.fill(cnt, 0, sz + 1, 0);

        for (int idl = 0; idl < bcount; idl++) {
            for (int idr = idl + 1; idr < bcount; idr++) {
                int most = mode[idl][idr - 1];
                int most_cnt = get_cnt(idl, idr, most);
                for (int i = bl[idr]; i <= br[idr]; i++) {
                    int cur_cnt = get_cnt(idl, idr, sv[i]);
                    if (most_cnt < cur_cnt || cur_cnt == most_cnt && sv[i] < most) {
                        most = sv[i];
                        most_cnt = cur_cnt;
                    }
                }
                mode[idl][idr] = most;
            }
        }
    }


    public static int[] query(int l, int r) {
        int most = 0;
        int most_cnt = 0;
        if (sz == 1) {
            return new int[]{0, r - l + 1};
        }
        if (bi[l] == bi[r]) {
            if (l == bl[bi[l]] && r == br[bi[r]]) {
                int id = bi[l];
                most = mode[id][id];
                most_cnt = get_cnt(id, id, most);
            } else {
                clear(l, r);
                for (int i = l; i <= r; i++) {
                    cnt[sv[i]]++;
                    if (cnt[sv[i]] > most_cnt || cnt[sv[i]] == most_cnt && sv[i] < most) {
                        most = sv[i];
                        most_cnt = cnt[sv[i]];
                    }
                }
                clear(l, r);
            }
        } else {
            most = (bi[l] + 1 <= bi[r] - 1) ? mode[bi[l] + 1][bi[r] - 1] : -1;
            most_cnt = get_cnt(bi[l] + 1, bi[r] - 1, most);
            for (int i = l; i <= br[bi[l]]; i++) {
                cnt[sv[i]]++;
                int x = sv[i];
                int cur_cnt = cnt[sv[i]] + get_cnt(bi[l] + 1, bi[r] - 1, x);
                if (cur_cnt > most_cnt || cur_cnt == most_cnt && x < most) {
                    most = x;
                    most_cnt = cur_cnt;
                }
            }
            for (int i = bl[bi[r]]; i <= r; i++) {
                cnt[sv[i]]++;
                int x = sv[i];
                int cur_cnt = cnt[sv[i]] + get_cnt(bi[l] + 1, bi[r] - 1, x);
                if (cur_cnt > most_cnt || cur_cnt == most_cnt && x < most) {
                    most = x;
                    most_cnt = cur_cnt;
                }
            }
            clear(l, br[bi[l]]);
            clear(bl[bi[r]], r);
        }
        return new int[]{most, most_cnt};
    }


}