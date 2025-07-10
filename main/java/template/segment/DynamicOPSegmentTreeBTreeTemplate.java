package template.segment;

/*
 *
 *   https://www.luogu.com.cn/record/197482966
 *
 *
 *
 */

/**
 * 动态开点线段树 二叉树版 维护更多信息
 * 注意：如果需要效率更高需要使用数组维护的开点线段树
 *
 * @see template.segment.DynamicOPSegmentTreeBTreeArrayTemplate
 */
public class DynamicOPSegmentTreeBTreeTemplate {



    /***************************************线段树模板开始**************************************/
    @FunctionalInterface
    public interface Operation {
        Node op(Node a, Node b);
    }

    public static class Node {
        /***************不动变量****************/
        Node left;
        Node right;
        boolean vis; // 懒更新tag
        int add; // 懒添加
        int change; // 更新值
        /*******************维护信息，如果维护区间合并更多信自行添加******************/
        long val; //

        public Node(long initial) {
            val = initial;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "left=" + left +
                    ", right=" + right +
                    ", vis=" + vis +
                    ", add=" + add +
                    ", change=" + change +
                    ", val=" + val +
                    '}';
        }
    }

    public static class LazySegment {
        Node root;
        int n;
        long initial;
        Operation operation;

        boolean isOffset = true; // 是否以[1,n] 作为区间查询

        /**
         * @param n         查询范围 [1,n]
         * @param initial   默认初始值
         * @param operation 操作
         */
        public LazySegment(int n, long initial, Operation operation) {
            this.n = n;
            this.initial = initial;
            this.operation = operation;
            this.root = new Node(initial);
        }

        private void updateLazy(Node node, int l, int r, int v) {
            node.change = v;
            node.add = 0;
            node.vis = true;
            // 最值等操作 和范围size无关
            // node.val = v;
            // 求和使用下面
            node.val = v * 1L * (r - l + 1);
        }

        private void addLazy(Node node, int l, int r, int v) {
            node.add += v;
            // 最值等操作 和范围size无关
            // node.val += v;
            // 求和使用下面 注释上面
            node.val += v * 1L * (r - l + 1);
        }

        private void up(Node node, int l, int r) {
            Node newNode = new Node(initial);
            if (node.left != null) {
                newNode = operation.op(newNode, node.left);
            }
            if (node.right != null) {
                newNode = operation.op(newNode, node.right);
            }
            node.val = newNode.val;
        }


        private void down(Node node, int l, int r) {
            int mid = l + ((r - l) >> 1);
            if (node.vis) {
                if (mid - l + 1 > 0) {
                    addLeftSon(node);
                    updateLazy(node.left, l, mid, node.change);
                }
                if (r - mid > 0) {
                    addRightSon(node);
                    updateLazy(node.right, mid + 1, r, node.change);
                }
                node.vis = false;
            }
            if (node.add != 0) {
                if (mid - l + 1 > 0) {
                    addLeftSon(node);
                    addLazy(node.left, l, mid, node.add);
                }
                if (r - mid > 0) {
                    addRightSon(node);
                    addLazy(node.right, mid + 1, r, node.add);
                }
                node.add = 0;
            }
        }


        private void update(int ql, int qr, int v, int l, int r, Node node) {
            if (ql <= l && r <= qr) {
                updateLazy(node, l, r, v);
            } else {
                int mid = l + ((r - l) >> 1);
                down(node, l, r);
                if (ql <= mid) {
                    addLeftSon(node);
                    update(ql, qr, v, l, mid, node.left);
                }
                if (qr > mid) {
                    addRightSon(node);
                    update(ql, qr, v, mid + 1, r, node.right);
                }
                up(node, l, r);
            }
        }


        private void add(int ql, int qr, int v, int l, int r, Node node) {
            if (ql <= l && r <= qr) {
                addLazy(node, l, r, v);
            } else {
                int mid = l + ((r - l) >> 1);
                down(node, l, r);
                if (ql <= mid) {
                    addLeftSon(node);
                    add(ql, qr, v, l, mid, node.left);
                }
                if (qr > mid) {
                    addRightSon(node);
                    add(ql, qr, v, mid + 1, r, node.right);
                }
                up(node, l, r);
            }
        }


        private Node query(int ql, int qr, int l, int r, Node node) {
            if (ql <= l && r <= qr) {
                return node;
            } else {
                int mid = l + ((r - l) >> 1);
                Node temp = new Node(initial);
                down(node, l, r);
                if (ql <= mid) {
                    if (node.left != null) {
                        temp = operation.op(temp, query(ql, qr, l, mid, node.left));
                    }
                }
                if (qr > mid) {
                    if (node.right != null) {
                        temp = operation.op(query(ql, qr, mid + 1, r, node.right), temp);
                    }
                }
                return temp;
            }
        }

        private void addLeftSon(Node node) {
            if (node.left == null) {
                node.left = new Node(initial);
            }
        }

        private void addRightSon(Node node) {
            if (node.right == null) {
                node.right = new Node(initial);
            }
        }


        public void build(int l, int r, Node node, int[] array) {
            if (l == r) {
                node.val = array[l];
            } else {
                int mid = l + ((r - l) >> 1);
                addLeftSon(node);
                build(l, mid, node.left, array);
                addRightSon(node);
                build(mid + 1, r, node.right, array);
                up(node, l, r);
            }
            node.vis = false;
            node.add = 0;
        }

        public Node query(int ql, int qr) {
            return query(ql, qr, isOffset ? 1 : 0, isOffset ? n : n - 1, this.root);
        }

        public void add(int ql, int qr, int v) {
            add(ql, qr, v, isOffset ? 1 : 0, isOffset ? n : n - 1, this.root);
        }

        public void update(int ql, int qr, int v) {
            update(ql, qr, v, isOffset ? 1 : 0, isOffset ? n : n - 1, this.root);
        }


        // 线段树二分 查询第一个
        // 查询区间 [L,R] 符合条件的第一个
        public int findFirst(int L,int R,int val, int l, int r, Node node) {
            if (r < L || l > R || node == null|| node.val < val)
                return -1;
            if (l == r) {
                return l;
            }
            int mid = l + ((r - l) >> 1);
            if(L <= mid) {
                int p = findFirst(L,R,val,l,mid,node.left);
                if(p >= 0)  return p;
            }
            return findFirst(L,R,val,mid + 1,r,node.right);
        }

        // 线段树二分 查询最后一个
        // 查询区间 [L,R] 符合条件的最后一个
        public int findLast(int L,int R,int val, int l, int r, Node node) {
            if (r < L || l > R || node == null|| node.val < val)
                return -1;
            if (l == r) {
                return l;
            }
            int mid = l + ((r - l) >> 1);
            if(L <= mid) {
                int p =  findLast(L,R,val,mid + 1,r,node.right);
                if(p >= 0)  return p;
            }
            return findLast(L,R,val,l,mid,node.left);
        }
    }

    /***************************************线段树模板结束**************************************/


    public static void main(String[] args) {
        int T = 10; // 测试次数
        boolean ok = true;
        long result = 0, expect = 0;
        long initial = 0;


        Operation operation = (nodeX, nodeY) -> {
            Node node = new Node(initial);
            node.val = nodeX.val + nodeY.val;
            return node;
        };


        while (--T > 0 && ok) {

            int n = 100;

            int[] nums = new int[n + 1];

            LazySegment seg = new LazySegment(n, initial, operation);

            for (int i = 1; i <= n; i++) {
                boolean isNeg = Math.random() > 0.5;
                int v = (int) (Math.random() * 12);
                nums[i] = isNeg ? -v : v;
            }

            seg.build(1, n, seg.root, nums);

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
                    Node aNode = seg.query(L, R);
                    Node bNode = query(L, R, nums, initial, operation);
                    if (aNode.val != bNode.val) {
                        ok = false;
                        System.out.println("result = " + aNode + "\nexpect = " + bNode);
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