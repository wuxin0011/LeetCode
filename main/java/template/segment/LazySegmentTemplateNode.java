package template.segment;

/**
 * @author: wuxin0011
 * @Description: 动态开点线段树
 */
public class LazySegmentTemplateNode {


    @FunctionalInterface
    public interface Operation {
        long operation(long x, long y);
    }

    public static class Node {
        Node left;
        Node right;
        boolean vis;
        int add, change;
        long val;

        public Node() {
        }
    }

    public static class LazySegment {
        Node root;
        int n;
        long initial;
        Operation operation;

        LazySegment(int n, long initial, Operation operation) {
            this.n = n;
            this.initial = initial;
            this.operation = operation;
            this.root = new Node();
        }

        private void up(Node node, int ln, int rn) {
            node.val = operation.operation(node.left.val, node.right.val);
        }


        private void down(Node node, int ln, int rn) {
            if (node.vis) {
                if (node.left == null) node.left = new Node();
                if (node.right == null) node.right = new Node();
                updateLazy(node.left, ln, node.change);
                updateLazy(node.right, rn, node.change);
                node.vis = false;
            }
            if (node.add != 0) {
                if (node.left == null) node.left = new Node();
                if (node.right == null) node.right = new Node();
                addLazy(node.left, ln, node.add);
                addLazy(node.right, rn, node.add);
                node.add = 0;
            }
        }


        private void updateLazy(Node node, int size, int v) {

            node.val = v;
            // sum
            // node.val = size * 1L * v;
            node.change = v;
            node.vis = true;
            node.add = 0;
        }

        private void addLazy(Node node, int size, int v) {
            node.val += v;
            // sum
            // node.val += v * 1L * size;
            node.add += v;
        }


        private void update(int ql, int qr, int v, int l, int r, Node node) {
            if (ql <= l && r <= qr) {
                updateLazy(node, r - l + 1, v);
            } else {
                int mid = l + ((r - l) >> 1);
                down(node, mid - l + 1, r - mid);
                if (ql <= mid) {
                    if (node.left == null) {
                        node.left = new Node();
                    }
                    update(ql, qr, v, l, mid, node.left);
                }
                if (qr > mid) {
                    if (node.right == null) {
                        node.right = new Node();
                    }
                    update(ql, qr, v, mid + 1, r, node.right);
                }
                up(node, mid - l + 1, r - mid);
            }
        }


        private void add(int ql, int qr, int v, int l, int r, Node node) {
            if (ql <= l && r <= qr) {
                addLazy(node, r - l + 1, v);
            } else {
                int mid = l + ((r - l) >> 1);
                down(node, mid - l + 1, r - mid);
                if (ql <= mid) {
                    if (node.left == null) {
                        node.left = new Node();
                    }
                    add(ql, qr, v, l, mid, node.left);
                }
                if (qr > mid) {
                    if (node.right == null) {
                        node.right = new Node();
                    }
                    add(ql, qr, v, mid + 1, r, node.right);
                }
                up(node, mid - l + 1, r - mid);
            }
        }


        private long query(int ql, int qr, int l, int r, Node node) {
            if (ql <= l && r <= qr) {
                return node.val;
            } else {
                int mid = l + ((r - l) >> 1);
                long ans = initial;
                down(node, mid - l + 1, r - mid);
                if (ql <= mid) {
                    if (node.left == null) {
                        node.left = new Node();
                    }
                    ans = operation.operation(ans, query(ql, qr, l, mid, node.left));
                }
                if (qr > mid) {
                    if (node.right == null) {
                        node.right = new Node();
                    }
                    ans = operation.operation(ans, query(ql, qr, mid + 1, r, node.right));
                }
                return ans;
            }
        }


        public void build(int l, int r, Node node, int[] array) {
            if (l == r) {
                node.val = array[l];
            } else {
                int mid = l + ((r - l) >> 1);
                if (node.left == null) {
                    node.left = new Node();
                }
                build(l, mid, node.left, array);
                if (node.right == null) {
                    node.right = new Node();
                }
                build(mid + 1, r, node.right, array);
                up(node, mid - l + 1, r - mid);
            }
            node.vis = false;
            node.add = 0;
        }

        public long query(int ql, int qr) {
            return query(ql, qr, 1, n, this.root);
        }

        public void add(int ql, int qr, int v) {
            add(ql, qr, v, 1, n, this.root);
        }

        public void update(int ql, int qr, int v) {
            update(ql, qr, v, 1, n, this.root);
        }


    }


    public static void main(String[] args) {


        int T = 10; // 测试次数
        boolean ok = true;
        Operation operation = (a, b) -> Math.max(a, b);
        long initial = Long.MIN_VALUE;
        while (--T > 0 && ok) {

            int n = 100;

            int[] nums = new int[n + 1];

            LazySegment seg = new LazySegment(n, initial, operation);

            for (int i = 1; i <= n; i++) {
                boolean isNeg = false;
                int v = (int) (Math.random() * 12);
                nums[i] = isNeg ? -v : v;
            }

            seg.build(1, n, seg.root, nums);

            int k = 100;

            while (--k > 0) {

                boolean isNeg = false;
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
                    long result = 0, expect = 0;

                    result = seg.query(L, R);
                    expect = query(L, R, nums);
                    if (result != expect) {
                        ok = false;
                        System.out.println("result = " + result + ",expect = " + expect);
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


    public static long query(int l, int r, int[] array) {
        long ans = Long.MIN_VALUE;
        for (int i = l; i <= r; i++) {
            ans = Math.max(ans, array[i]);
        }
        return ans;
    }


}