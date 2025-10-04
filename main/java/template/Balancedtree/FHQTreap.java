package template.Balancedtree;

/**
 * @author: wuxin0011
 * @Description: FHQTreap 的实现
 */
public class FHQTreap {
    public int[] key, count, left, right, size;
    public int head, cnt;
    public double[] priority;

    FHQTreap(int n) {
        key = new int[n];
        count = new int[n];
        left = new int[n];
        right = new int[n];
        size = new int[n];
        priority = new double[n];
    }

    public void up(int i) {
        size[i] = size[left[i]] + size[right[i]] + count[i];
    }

    public void split(int l, int r, int i, int num) {
        if (i == 0) {
            right[l] = left[r] = 0;
        } else {
            if (key[i] <= num) {
                right[l] = i;
                split(i, r, right[i], num);
            } else {
                left[r] = i;
                split(l, i, left[i], num);
            }
            up(i);
        }
    }

    public int merge(int l, int r) {
        if (l == 0 || r == 0) {
            return l + r;
        }
        if (priority[l] >= priority[r]) {
            right[l] = merge(right[l], r);
            up(l);
            return l;
        } else {
            left[r] = merge(l, left[r]);
            up(r);
            return r;
        }
    }

    public int find(int i, int num) {
        if (i == 0) {
            return 0;
        }
        if (key[i] == num) {
            return i;
        } else if (key[i] > num) {
            return find(left[i], num);
        } else {
            return find(right[i], num);
        }
    }

    public void changeCount(int i, int num, int change) {
        if (key[i] == num) {
            count[i] += change;
        } else if (key[i] > num) {
            changeCount(left[i], num, change);
        } else {
            changeCount(right[i], num, change);
        }
        up(i);
    }

    public void add(int num) {
        if (find(head, num) != 0) {
            changeCount(head, num, 1);
        } else {
            split(0, 0, head, num);
            key[++cnt] = num;
            count[cnt] = size[cnt] = 1;
            priority[cnt] = Math.random();
            head = merge(merge(right[0], cnt), left[0]);
        }
    }

    public void remove(int num) {
        int i = find(head, num);
        if (i != 0) {
            if (count[i] > 1) {
                changeCount(head, num, -1);
            } else {
                split(0, 0, head, num);
                int lm = right[0];
                int r = left[0];
                split(0, 0, lm, num - 1);
                int l = right[0];
                head = merge(l, r);
            }
        }
    }

    public int small(int i, int num) {
        if (i == 0) {
            return 0;
        }
        if (key[i] >= num) {
            return small(left[i], num);
        } else {
            return size[left[i]] + count[i] + small(right[i], num);
        }
    }

    public int rank(int num) {
        return small(head, num) + 1;
    }

    public int index(int i, int x) {
        if (size[left[i]] >= x) {
            return index(left[i], x);
        } else if (size[left[i]] + count[i] < x) {
            return index(right[i], x - size[left[i]] - count[i]);
        }
        return key[i];
    }

    public int index(int x) {
        return index(head, x);
    }

    public int floor(int i, int num) {
        if (i == 0) {
            return Integer.MIN_VALUE;
        }
        if (key[i] >= num) {
            return floor(left[i], num);
        } else {
            return Math.max(key[i], floor(right[i], num));
        }
    }

    public int floor(int num) {
        return floor(head, num);
    }

    public int ceil(int i, int num) {
        if (i == 0) {
            return Integer.MAX_VALUE;
        }
        if (key[i] <= num) {
            return ceil(right[i], num);
        } else {
            return Math.min(key[i], ceil(left[i], num));
        }
    }

    public int ceil(int num) {
        return ceil(head, num);
    }

    public void clear() {
        for (int i = 0; i <= cnt; i++) {
            key[i] = left[i] = right[i] = count[i] = size[i] = 0;
            priority[i] = 0;
        }
        cnt = 0;
        head = 0;
    }

}
