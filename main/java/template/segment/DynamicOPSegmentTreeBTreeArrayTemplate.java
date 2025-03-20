package template.segment;


/*
*
*
*    https://www.luogu.com.cn/record/197536628
*
*
*/

/**
 * 动态开点线段树 数组版 维护更多信息
 * 注意：如果不想维护范围大小请使用二叉树版本的开点线段树
 * @see template.segment.DynamicOPSegmentTreeBTreeTemplate
 *  //
 */
public class DynamicOPSegmentTreeBTreeArrayTemplate {

    // 自定义实现操作
    @FunctionalInterface
    public interface Operation {
        Node op(Node x, Node y);
    }

    // 维护的信息
    public static class Node {
        /*维护值*/
        long val;
        /*基本上无需修改值*/
        int add, change;
        boolean vis;

        Node(long initial) {
            this.val = initial;
        }
    }

    // 如果是求和注意 updateLazy 和 addLazy 两处逻辑
    // 注意下标和原始数组对应关系 + 1
    public static class LazySegment {

        int n;
        long initial;
        Operation operation;
        int[] left, right;
        Node[] nodes;
        int cnt;

        /**
         * @param n         查询范围 [1,n]
         * @param initial   默认初始值
         * @param operation 操作
         */
        public LazySegment(int n, long initial, Operation operation) {
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
                    nodes[i].vis = false;
                    nodes[i].add = 0;
                } else {
                    nodes[i] = new Node(initial);
                }
            }
            cnt = 1;
        }


        private void updateLazy(int i, int l, int r, int v) {
            nodes[i].add = 0;
            nodes[i].vis = true;
            nodes[i].change = v;
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
            nodes[i].add += v;
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


        private void down(int i, int l, int r) {
            int mid = l + ((r - l) >> 1);
            if (nodes[i].vis) {
                if (mid - l + 1 > 0) {
                    addLeftSon(i);
                    updateLazy(left[i], l, mid, nodes[i].change);
                }
                if (r - mid > 0) {
                    addRightSon(i);
                    updateLazy(right[i], mid + 1, r, nodes[i].change);
                }
                nodes[i].vis = false;
            }
            if (nodes[i].add != 0) {
                if (mid - l + 1 > 0) {
                    addLeftSon(i);
                    addLazy(left[i], l, mid, nodes[i].add);
                }
                if (r - mid > 0) {
                    addRightSon(i);
                    addLazy(right[i], mid + 1, r, nodes[i].add);
                }
                nodes[i].add = 0;
            }
        }


        private void update(int ql, int qr, int v, int l, int r, int i) {
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


        private void add(int ql, int qr, int v, int l, int r, int i) {
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


        private Node query(int ql, int qr, int l, int r, int i) {
            if (ql <= l && r <= qr) {
                return nodes[i];
            } else {
                int mid = l + ((r - l) >> 1);
                down(i, l, r);
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
            return query(ql, qr, 1, n, 1);
        }

        public void add(int ql, int qr, int v) {
            add(ql, qr, v, 1, n, 1);
        }

        public void update(int ql, int qr, int v) {
            update(ql, qr, v, 1, n, 1);
        }

        // 线段树二分 查询第一个
        // 注意 L 传入需要 + 1 因为 + 1 才和线段树下标对应
        public int findFirst(int L, int val, int l, int r, int i) {
            if (i == 0 || nodes[i] == null || nodes[i].val < val)
                return -1;
            if (l == r) {
                return l;
            }
            int mid = l + ((r - l) >> 1);
            if (L <= mid) {
                int p = findFirst(L, val, l, mid, left[i]);
                if (p >= 0) return p;
            }
            return findFirst(L, val, mid + 1, r, right[i]);
        }

        // 线段树二分 查询第一个
        // 注意 L 传入需要 + 1 因为 + 1 才和线段树下标对应
        public int findLast(int R, int val, int l, int r, int i) {
            if (i == 0 || nodes[i] == null || nodes[i].val < val)
                return -1;
            if (l == r) {
                return l;
            }
            int mid = l + ((r - l) >> 1);
            if (R >= mid) {
                int p = findLast(R, val, mid + 1, r, right[i]);
                if (p >= 0) return p;
            }
            return findLast(R, val, l, mid, left[i]);
        }

    }


    public static void main(String[] args) {
        int T = 10; // 测试次数
        boolean ok = true;

        long initial = Long.MAX_VALUE;
        Operation operation = (Node x, Node y) -> new Node(Math.min(x == null ? initial : x.val, y == null ? initial : y.val));


        while (--T > 0 && ok) {

            int n = 100;

            int[] nums = new int[n + 1];

            LazySegment seg = new LazySegment(n, initial, operation);

            for (int i = 1; i <= n; i++) {
                boolean isNeg = Math.random() > 0.5;
                int v = (int) (Math.random() * 12);
                nums[i] = isNeg ? -v : v;
            }

            seg.build(1, n, 1, nums);

            int k = 100;

            while (--k > 0) {

                boolean isNeg = Math.random() > 0.5;
                int v = (int) (Math.random() * 1000);
                if (isNeg) v = -v;

                // 注意要 + 1
                int a = (int) ((Math.random() * n) + 1);
                int b = (int) ((Math.random() * n) + 1);


                // 设置左右区间
                int L = Math.min(a, b);
                int R = Math.max(a, b);
//                System.out.printf("{%s %s} n = %s\n",L,R,n);


                double op = Math.random();


                if (op <= 0.2) {
                    checkAdd(L, R, v, nums);
                    seg.add(L, R, v);
                } else if (op <= 0.5) {
                    checkChange(L, R, v, nums);
                    seg.update(L, R, v);
                } else {
                    Node x = seg.query(L, R);
                    Node y = query(L, R, nums, initial, operation);
                    if (x.val != y.val) {
                        ok = false;
                        System.out.println("result = " + x.val + "\nexpect = " + y.val);
                        break;
                    }
                }


            }
        }
        System.out.println(ok ? "ok" : "fail");
    }


    // 暴力测试部分
    public static void checkAdd(int l, int r, long V, int[] array) {
        for (int i = l; i <= r; i++) {
            array[i] += V;
        }
    }

    public static void checkChange(int l, int r, int V, int[] array) {
        for (int i = l; i <= r; i++) {
            array[i] = V;
        }
    }


    public static Node query(int l, int r, int[] array, long initial, Operation operation) {
        Node temp = new Node(initial);
        for (int i = l; i <= r; i++) {
            temp = operation.op(temp, new Node(array[i]));
        }
        return temp;
    }


}