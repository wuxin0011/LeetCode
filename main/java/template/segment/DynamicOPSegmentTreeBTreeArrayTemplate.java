package template.segment;


/*
 *
 *
 *    https://www.luogu.com.cn/record/197536628
 *
 *
 * 板子如何使用 ？ 参考连接
 * <a href="https://leetcode.cn/problems/booking-concert-tickets-in-groups/submissions/613366603/>使用板子 音乐会门票 维护最大值 区间和 ，使用线段树树二分</a>
 */


/**
 * 动态开点线段树 数组版 维护更多信息
 * 注意：如果不想维护范围大小请使用二叉树版本的开点线段树
 *
 * @see template.segment.DynamicOPSegmentTreeBTreeTemplate
 * //
 */
public class DynamicOPSegmentTreeBTreeArrayTemplate {


    /***************************************线段树模板开始**************************************/


    static Info e() {
        return new Info();
    }


    static Info op(Info l, Info r) {
        Info node = e();
        return node;
    }

    static boolean check0(Info i, Info t) {
        return false;
    }


    // 维护的信息
    public static class Info {
        long val;

        Info() {
            val = 0;
        }
    }

    public static class LazySegment {
        int n;
        int[] left, right;
        Info[] infos, arr, updv, addv;
        int cnt;
        boolean[] visupd, visadd;


        public LazySegment(int n) {
            this.n = n;
            this.left = new int[n << 1];
            this.right = new int[n << 1];
            this.infos = new Info[n << 2];
            this.updv = new Info[n << 2];
            this.addv = new Info[n << 2];
            this.visupd = new boolean[n << 2];
            this.visadd = new boolean[n << 2];
            for (int i = 0; i < (n << 2); i++) {
                updv[i] = e();
                infos[i] = e();
                addv[i] = e();
            }
            this.cnt = 1;
        }

        // 如果使用静态方式需要clear
        public void clear() {
            for (int i = 0; i <= cnt; i++) {
                visupd[left[i]] = false;
                visupd[right[i]] = false;
                left[i] = right[i] = 0;
                updv[i] = e();
                infos[i] = e();
                addv[i] = e();
            }
            cnt = 1;
        }


        // 根据题目条件修改
        private void updateLazy(int i, int l, int r, Info v) {
            infos[i] = v;
            updv[i] = v;
            visupd[i] = true;
            addv[i] = null;
        }

        // 根据题目条件修改
        private void addLazy(int i, int l, int r, Info v) {
            infos[i] = op(infos[i], v);
            addv[i] = op(addv[i], v);
        }

        private void up(int i, int l, int r) {
            infos[i] = op(infos[left[i]], infos[right[i]]);
        }

        private void addLeftSon(int i) {
            if (left[i] == 0) {
                left[i] = ++cnt;
                infos[left[i]] = e();
            }
        }

        private void addRightSon(int i) {
            if (right[i] == 0) {
                right[i] = ++cnt;
                infos[right[i]] = e();
            }
        }


        private void down(int i, int l, int r) {
            int mid = l + ((r - l) >> 1);
            if (visupd[i]) {
                if (mid - l + 1 > 0) {
                    addLeftSon(i);
                    updateLazy(left[i], l, mid, updv[i]);
                }
                if (r - mid > 0) {
                    addRightSon(i);
                    updateLazy(right[i], mid + 1, r, updv[i]);
                }
                visupd[i] = false;
                visadd[i] = false;
            }
            if (visadd[i]) {
                if (mid - l + 1 > 0) {
                    addLeftSon(i);
                    addLazy(left[i], l, mid, addv[i]);
                }
                if (r - mid > 0) {
                    addRightSon(i);
                    addLazy(right[i], mid + 1, r, addv[i]);
                }
                visadd[i] = false;
            }
        }


        private void update(int ql, int qr, Info v, int l, int r, int i) {
            if (ql <= l && r <= qr) {
                updateLazy(i, l, r, v);
            } else {
                int mid = l + ((r - l) >> 1);
                down(i, l, r);
                if (ql <= mid) {
                    addLeftSon(i);
                    update(ql, qr, v, l, mid, left[i]);
                }
                if (qr > mid) {
                    addRightSon(i);
                    update(ql, qr, v, mid + 1, r, right[i]);
                }
                up(i, l, r);
            }
        }


        private void add(int ql, int qr, Info v, int l, int r, int i) {
            if (ql <= l && r <= qr) {
                addLazy(i, l, r, v);
            } else {
                int mid = l + ((r - l) >> 1);
                down(i, l, r);
                if (ql <= mid) {
                    addLeftSon(i);
                    add(ql, qr, v, l, mid, left[i]);
                }
                if (qr > mid) {
                    addRightSon(i);
                    add(ql, qr, v, mid + 1, r, right[i]);
                }
                up(i, l, r);
            }
        }


        private Info query(int ql, int qr, int l, int r, int i) {
            if (ql <= l && r <= qr) {
                return infos[i];
            } else {
                int mid = l + ((r - l) >> 1);
                down(i, l, r);
                Info temp = e();
                if (ql <= mid) {
                    if (left[i] != 0) {
                        temp = op(temp, query(ql, qr, l, mid, left[i]));
                    }
                }
                if (qr > mid) {
                    if (right[i] != 0) {
                        temp = op(temp, query(ql, qr, mid + 1, r, right[i]));
                    }
                }
                return temp;
            }
        }


        // 开点线段树一般不初始化
        public void build(int l, int r, int i, Info[] array) {
            if (l == r) {
                infos[i] = array[l];
            } else {
                int mid = l + ((r - l) >> 1);
                if (mid - l + 1 > 0) {
                    addLeftSon(i);
                    build(l, mid, left[i], array);
                }
                if (r - mid > 0) {
                    addRightSon(i);
                    build(mid + 1, r, right[i], array);
                }
                up(i, l, r);
            }
        }

        // 线段树二分 查询第一个
        // 查询区间 [L,R] 符合条件的第一个
        public int findFirst(int L, int R, Info t, int l, int r, int i) {
            if (r < L || l > R || infos[i] == null || !check0(infos[i], t))
                return -1;
            if (l == r) {
                return l;
            }
            int mid = l + ((r - l) >> 1);
            down(i, l, r);
            if (L <= mid) {
                int p = findFirst(L, R, t, l, mid, left[i]);
                if (p >= 0) return p;
            }
            return findFirst(L, R, t, mid + 1, r, right[i]);
        }

        // 线段树二分 查询最后一个
        // 查询区间 [L,R] 符合条件的最后一个
        public int findLast(int L, int R, Info t, int l, int r, int i) {
            if (r < L || l > R || infos[i] == null || !check0(infos[i], t))
                return -1;
            if (l == r) {
                return l;
            }
            int mid = l + ((r - l) >> 1);
            down(i, l, r);
            if (L <= mid) {
                int p = findLast(L, R, t, mid + 1, r, right[i]);
                if (p >= 0) return p;
            }
            return findLast(L, R, t, l, mid, left[i]);
        }
    }


}