package template.segment;

/**
 * @author: wuxin0011
 * @Description: 单点和区间修改的线段树 没有懒更新标记
 */
public class SegmentTemplate2 {


    /***************************************线段树模板开始**************************************/
    @FunctionalInterface
    public interface Operation {
        Node op(Node a, Node b);
    }

    public static class Node {
        /***************不动变量****************/
        Node left;
        Node right;

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
                    ", val=" + val +
                    '}';
        }
    }

    public static class Segment {
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
        public Segment(int n, long initial, Operation operation) {
            this.n = n;
            this.initial = initial;
            this.operation = operation;
            this.root = new Node(initial);
        }

        private void updateLazy(Node node, int l, int r, int v) {
            // 最值等操作 和范围size无关
            // node.val = v;
            // 求和使用下面
            node.val = v * 1L * (r - l + 1);
        }

        private void addLazy(Node node, int l, int r, int v) {
            // 最值等操作 和范围size无关
            node.val += v;
            // 求和使用下面 注释上面
//            node.val += v * 1L * (r - l + 1);
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


        private void update(int ql, int qr, int v, int l, int r, Node node) {
            if (ql <= l && r <= qr) {
                updateLazy(node, l, r, v);
            } else {
                int mid = l + ((r - l) >> 1);
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
        public int findFirst(int L, int R, int val, int l, int r, Node node) {
            if (r < L || l > R || node == null || node.val < val)
                return -1;
            if (l == r) {
                return l;
            }
            int mid = l + ((r - l) >> 1);
            if (L <= mid) {
                int p = findFirst(L, R, val, l, mid, node.left);
                if (p >= 0) return p;
            }
            return findFirst(L, R, val, mid + 1, r, node.right);
        }

        // 线段树二分 查询最后一个
        // 查询区间 [L,R] 符合条件的最后一个
        public int findLast(int L, int R, int val, int l, int r, Node node) {
            if (r < L || l > R || node == null || node.val < val)
                return -1;
            if (l == r) {
                return l;
            }
            int mid = l + ((r - l) >> 1);
            if (L <= mid) {
                int p = findLast(L, R, val, mid + 1, r, node.right);
                if (p >= 0) return p;
            }
            return findLast(L, R, val, l, mid, node.left);
        }
    }

    /***************************************线段树模板结束**************************************/

}
