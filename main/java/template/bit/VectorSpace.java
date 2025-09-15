package template.bit;

/**
 * @author: wuxin0011
 * @Description:
 */

import java.util.ArrayList;
import java.util.List;


//板子来源 https://leetcode.cn/contest/biweekly-contest-165/ranking/?region=local_v2 草莓奶昔🍓

public class VectorSpace {
    private List<Integer> bases;

    public VectorSpace() {
        this.bases = new ArrayList<>();
    }

    public VectorSpace(List<Integer> nums) {
        this.bases = new ArrayList<>();
        if (nums != null) {
            for (int v : nums) {
                this.add(v);
            }
        }
    }

    public boolean add(int v) {
        for (int e : this.bases) {
            if (e == 0 || v == 0) {
                break;
            }
            v = Math.min(v, v ^ e);
        }
        if (v != 0) {
            this.bases.add(v);
            return true;
        }
        return false;
    }

    public int add2(int v) {
        for (int e : this.bases) {
            if (e == 0 || v == 0) {
                break;
            }
            v = Math.min(v, v ^ e);
        }
        if (v != 0) {
            this.bases.add(v);
        }
        return v;
    }

    public int getMax(int xorVal) {
        int res = xorVal;
        for (int e : this.bases) {
            res = Math.max(res, res ^ e);
        }
        return res;
    }

    public int getMax() {
        return this.getMax(0);
    }

    public int getMin(int xorVal) {
        int res = xorVal;
        for (int e : this.bases) {
            res = Math.min(res, res ^ e);
        }
        return res;
    }

    public int getMin() {
        return this.getMin(0);
    }

    public VectorSpace copy() {
        VectorSpace res = new VectorSpace();
        res.bases = new ArrayList<>(this.bases);
        return res;
    }

    public int size() {
        return this.bases.size();
    }

    public boolean contains(int v) {
        for (int e : this.bases) {
            if (v == 0) {
                break;
            }
            v = Math.min(v, v ^ e);
        }
        return v == 0;
    }

    public static VectorSpace merge(VectorSpace v1, VectorSpace v2) {
        if (v1.size() < v2.size()) {
            VectorSpace temp = v1;
            v1 = v2;
            v2 = temp;
        }
        VectorSpace res = v1.copy();
        for (int v : v2.bases) {
            res.add(v);
        }
        return res;
    }

    public static VectorSpace mergeDestructively(VectorSpace v1, VectorSpace v2) {
        if (v1.size() < v2.size()) {
            VectorSpace temp = v1;
            v1 = v2;
            v2 = temp;
        }
        VectorSpace res = v1;
        for (int v : v2.bases) {
            res.add(v);
        }
        return res;
    }

    public static List<Integer> f2Intersection(List<Integer> nums1, List<Integer> nums2, int maxLog) {
        VectorSpace tmp = new VectorSpace();
        int upper = 1 << maxLog;
        for (int a : nums1) {
            tmp.add((a << maxLog) + a);
        }
        List<Integer> res = new ArrayList<>();
        for (int b : nums2) {
            int v = b << maxLog;
            int u = tmp.add2(v);
            if (u < upper) {
                res.add(u);
            }
        }
        return res;
    }

    public static List<Integer> f2Intersection(List<Integer> nums1, List<Integer> nums2) {
        return f2Intersection(nums1, nums2, 32);
    }

    public VectorSpace or(VectorSpace other) {
        return VectorSpace.merge(this, other);
    }

    public VectorSpace ior(VectorSpace other) {
        for (int v : other.bases) {
            this.add(v);
        }
        return this;
    }

    @Override
    public String toString() {
        return this.bases.toString();
    }
}