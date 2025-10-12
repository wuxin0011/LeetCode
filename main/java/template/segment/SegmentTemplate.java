package template.segment;

/**
 * @author: wuxin0011
 * @Description: 单点和区间修改的线段树 没有懒更新标记
 */
public class SegmentTemplate {


    /***************************************线段树模板开始**************************************/

    // 自定义实现操作
    @FunctionalInterface
    public interface Operation {
        Node op(Node x, Node y);
    }

    // 维护的信息
    public static class Node {
        /*维护值*/
        long val;
        Node(long initial) {
            this.val = initial;
        }
    }

    // 如果是求和注意 updateLazy 和 addLazy 两处逻辑
    // 注意下标和原始数组对应关系 + 1
    public static class Segment {

        int n;
        long initial;
        Operation operation;
        int[] left, right;
        Node[] nodes;
        int cnt;

        boolean isOffset = true; // 是否以[1,n] 作为区间查询

        /**
         * @param n         查询范围 开辟的空间大小[4*n] 与二叉树版的n不同，这里n是数组大小
         * @param initial   默认初始值
         * @param operation 操作
         */
        public Segment(int n, long initial, Operation operation) {
            this.n = n;
            this.initial = initial;
            this.operation = operation;
            this.left = new int[n << 1];
            this.right = new int[n << 1];
            this.nodes = new Node[n << 2];
            // 如果不调用 build 需要初始化
            // Arrays.setAll(nodes, i -> new Node(initial));
            this.cnt = 1;
        }

        // 如果使用静态方式需要clear
        public void clear() {
            for (int i = 0; i <= cnt; i++) {
                left[i] = right[i] = 0;
                if (nodes[i] != null) {
                    nodes[i].val = initial;
                } else {
                    nodes[i] = new Node(initial);
                }
            }
            cnt = 1;
        }


        private void updateLazy(int i, int l, int r, int v) {
            // max|min
            nodes[i].val = v;
            // sums
            // nodes[i].val = (long) v * (r - l + 1);
        }

        private void addLazy(int i, int l, int r, int v) {
            // max|min
            nodes[i].val += v;
            // sums
            // nodes[i].val += (long) v * (r - l + 1);
        }

        private void up(int i, int l, int r) {
            nodes[i] = operation.op(nodes[left[i]], nodes[right[i]]);
        }

        private void addLeftSon(int i) {
            if (left[i] == 0) {
                left[i] = ++cnt;
                nodes[left[i]] = new Node(initial);
            }
        }

        private void addRightSon(int i) {
            if (right[i] == 0) {
                right[i] = ++cnt;
                nodes[right[i]] = new Node(initial);
            }
        }


        private void update(int ql, int qr, int v, int l, int r, int i) {
            if (ql <= l && r <= qr) {
                 updateLazy(i, l, r, v);
            } else {
                int mid = l + ((r - l) >> 1);
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


        private void add(int ql, int qr, int v, int l, int r, int i) {
            if (ql <= l && r <= qr) {
                addLazy(i, l, r, v);
            } else {
                int mid = l + ((r - l) >> 1);
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


        private Node query(int ql, int qr, int l, int r, int i) {
            if (ql <= l && r <= qr) {
                return nodes[i];
            } else {
                int mid = l + ((r - l) >> 1);
                Node temp = new Node(initial);
                if (ql <= mid) {
                    if (left[i] != 0) {
                        temp = operation.op(temp, query(ql, qr, l, mid, left[i]));
                    }
                }
                if (qr > mid) {
                    if (right[i] != 0) {
                        temp = operation.op(temp, query(ql, qr, mid + 1, r, right[i]));
                    }
                }
                return temp;
            }
        }


        public void build(int l, int r, int i, int[] array) {
            if (l == r) {
                // 如果传入数组不是从1开始,需要-1 new Node(array[l - 1])
                nodes[i] = new Node(array[l]);
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

        public Node query(int ql, int qr) {
            return query(ql, qr, isOffset ? 1 : 0, isOffset ? n : n - 1, 1);
        }

        public void add(int ql, int qr, int v) {
            add(ql, qr, v, isOffset ? 1 : 0, isOffset ? n : n - 1, 1);
        }

        public void update(int ql, int qr, int v) {
            update(ql, qr, v, isOffset ? 1 : 0, isOffset ? n : n - 1, 1);
        }

        // 线段树二分 查询第一个
        // 查询区间 [L,R] 符合条件的第一个
        public int findFirst(int L,int R, int val, int l, int r, int i) {
            if (r < L || l > R || nodes[i] == null || nodes[i].val < val)
                return -1;
            if (l == r) {
                return l;
            }
            int mid = l + ((r - l) >> 1);
            if (L <= mid) {
                int p = findFirst(L, R,val, l, mid, left[i]);
                if (p >= 0) return p;
            }
            return findFirst(L,R, val, mid + 1, r, right[i]);
        }

        // 线段树二分 查询最后一个
        // 查询区间 [L,R] 符合条件的最后一个
        public int findLast(int L,int R, int val, int l, int r, int i) {
            if (r < L || l > R || nodes[i] == null || nodes[i].val < val)
                return -1;
            if (l == r) {
                return l;
            }
            int mid = l + ((r - l) >> 1);
            if (L <= mid) {
                int p = findLast(L,R, val, mid + 1, r, right[i]) ;
                if (p >= 0) return p;
            }
            return findLast(L, R,val, l, mid, left[i]);
        }
    }

    /***************************************线段树模板结束**************************************/
}
