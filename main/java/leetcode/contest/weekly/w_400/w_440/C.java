package leetcode.contest.weekly.w_400.w_440;

import code_generation.utils.IoUtil;
/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/weekly-contest-440/problems/fruits-into-baskets-iii">将水果装入篮子 III</a>
 * @title: 将水果装入篮子 III
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(C.class,"numOfUnplacedFruits","C.txt");
    }


    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int n = fruits.length;
        build(1, n, 1, baskets);
        int ans = 0;
        for (int x : fruits) {
            int id = find(1,x, 1, n, 1);
            if (id == -1) ans++;
            else update(id, id, -1, 1, n, 1);
        }
        return ans;
    }

    private static int N = (int) 1e5 + 10;
    private static int[] data = new int[N << 2];

    private static void build(int l, int r, int i, int[] array) {
        if (l == r) {
            data[i] = array[l - 1];
        } else {
            int mid = l + ((r - l) >> 1);
            build(l, mid, i << 1, array);
            build(mid + 1, r, i << 1 | 1, array);
            data[i] = Math.max(data[i << 1], data[i << 1 | 1]);
        }
    }

    private static void update(int ql, int qr, int val, int l, int r, int i) {
        if (l > r) return;
        if (l == r) {
            data[i] = val;
        } else {
            int mid = l + ((r - l) >> 1);
            if (ql <= mid) update(ql, qr, val, l, mid, i << 1);
            if (qr > mid) update(ql, qr, val, mid + 1, r, i << 1 | 1);
            data[i] = Math.max(data[i << 1], data[i << 1 | 1]);
        }
    }

    private static int query(int ql, int qr, int l, int r, int i) {
        if (l > r) return -1;
        if (ql <= l && r <= qr) {
            return data[i];
        } else {
            int mid = l + ((r - l) >> 1);
            int ans = -1;
            if (ql <= mid) ans = Math.max(query(ql, qr, l, mid, i << 1), ans);
            if (qr > mid) ans = Math.max(query(ql, qr, mid + 1, r, i << 1 | 1), ans);
            return ans;
        }
    }

//    private static int find(int val, int l, int r, int i) {
//        if (l > r) return -1;
//        if (l == r) return data[i] >= val ? l : -1;
//        int mid = l + ((r - l) >> 1);
//        if (query(l, mid, l, mid, i << 1) >= val) return find(val, l, mid, i << 1);
//        if (query(mid + 1, r, mid + 1, r, i << 1 | 1) >= val) return find(val, mid + 1, r, i << 1 | 1);
//        return -1;
//    }

    private static int find(int L,int val, int l, int r, int i) {
        if(data[i] < val || l > r) return -1;
        if(l == r) return l;
        int mid = l + ((r - l) >> 1);
        if(L<=mid) {
            int p = find(L,val,l,mid,i << 1);
            if(p >= 0) return p;
        }
        return find(L,val,mid + 1,r,i << 1 | 1);
    }


}