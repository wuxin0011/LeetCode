package leetcode.ox3if.data_struct.heap.base.Solution_0010;

import code_generation.utils.IoUtil;
/**
 *
 * 1801. 积压订单中的订单总数
 *
 * 给你一个二维整数数组 orders ，其中每个 orders[i] = [pricei, amounti, orderTypei] 表示有 amounti 笔类型为orderTypei 、价格为pricei 的订单。
 * 订单类型 orderTypei 可以分为两种：
 * 	0 表示这是一批采购订单 buy
 * 	1 表示这是一批销售订单 sell
 * 注意，orders[i] 表示一批共计 amounti 笔的独立订单，这些订单的价格和类型相同。对于所有有效的 i ，由 orders[i] 表示的所有订单提交时间均早于 orders[i+1] 表示的所有订单。
 * 存在由未执行订单组成的 积压订单 。积压订单最初是空的。提交订单时，会发生以下情况：
 * 	如果该订单是一笔采购订单 buy ，则可以查看积压订单中价格 最低 的销售订单 sell 。如果该销售订单 sell 的价格 低于或等于 当前采购订单 buy 的价格，则匹配并执行这两笔订单，并将销售订单 sell 从积压订单中删除。否则，采购订单 buy 将会添加到积压订单中。
 * 	反之亦然，如果该订单是一笔销售订单 sell ，则可以查看积压订单中价格 最高 的采购订单 buy 。如果该采购订单 buy 的价格 高于或等于 当前销售订单 sell 的价格，则匹配并执行这两笔订单，并将采购订单 buy 从积压订单中删除。否则，销售订单 sell 将会添加到积压订单中。
 * 输入所有订单后，返回积压订单中的 订单总数 。由于数字可能很大，所以需要返回对 10^9 + 7 取余的结果。
 *
 * 示例 1：
 * 输入：orders = [[10,5,0],[15,2,1],[25,1,1],[30,4,0]]
 * 输出：6
 * 解释：输入订单后会发生下述情况：
 * - 提交 5 笔采购订单，价格为 10 。没有销售订单，所以这 5 笔订单添加到积压订单中。
 * - 提交 2 笔销售订单，价格为 15 。没有采购订单的价格大于或等于 15 ，所以这 2 笔订单添加到积压订单中。
 * - 提交 1 笔销售订单，价格为 25 。没有采购订单的价格大于或等于 25 ，所以这 1 笔订单添加到积压订单中。
 * - 提交 4 笔采购订单，价格为 30 。前 2 笔采购订单与价格最低（价格为 15）的 2 笔销售订单匹配，从积压订单中删除这 2 笔销售订单。第 3 笔采购订单与价格最低的 1 笔销售订单匹配，销售订单价格为 25 ，从积压订单中删除这 1 笔销售订单。积压订单中不存在更多销售订单，所以第 4 笔采购订单需要添加到积压订单中。
 * 最终，积压订单中有 5 笔价格为 10 的采购订单，和 1 笔价格为 30 的采购订单。所以积压订单中的订单总数为 6 。
 *
 * 示例 2：
 * 输入：orders = [[7,1000000000,1],[15,3,0],[5,999999995,0],[5,1,1]]
 * 输出：999999984
 * 解释：输入订单后会发生下述情况：
 * - 提交 10^9 笔销售订单，价格为 7 。没有采购订单，所以这 10^9 笔订单添加到积压订单中。
 * - 提交 3 笔采购订单，价格为 15 。这些采购订单与价格最低（价格为 7 ）的 3 笔销售订单匹配，从积压订单中删除这 3 笔销售订单。
 * - 提交 999999995 笔采购订单，价格为 5 。销售订单的最低价为 7 ，所以这 999999995 笔订单添加到积压订单中。
 * - 提交 1 笔销售订单，价格为 5 。这笔销售订单与价格最高（价格为 5 ）的 1 笔采购订单匹配，从积压订单中删除这 1 笔采购订单。
 * 最终，积压订单中有 (1000000000-3) 笔价格为 7 的销售订单，和 (999999995-1) 笔价格为 5 的采购订单。所以积压订单中的订单总数为 1999999991 ，等于 999999984 % (10^9 + 7) 。
 *
 * 提示：
 * 	1 <= orders.length <= 10^5
 * 	orders[i].length == 3
 * 	1 <= pricei, amounti <= 10^9
 * 	orderTypei 为 0 或 1
 *
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/number-of-orders-in-the-backlog
 * @title: 积压订单中的订单总数
 */
public class Solution {

    public static void main(String[] args) {
        // IoUtil.testUtil(Solution.class, "getNumberOfBacklogOrders", "in.txt");
        IoUtil.testUtil(Solution.class, "getNumberOfBacklogOrders2", "in.txt");
    }

    private static final int MOD = (int) 1e9 + 7;
//
//    public int getNumberOfBacklogOrders(int[][] orders) {
//
//        PriorityQueue<int[]> p1 = new PriorityQueue<>(Comparator.comparingInt(a -> a[0])); // 销售订单 积压订单
//        PriorityQueue<int[]> p2 = new PriorityQueue<>((a, b) -> b[0] - a[0]); // 采购订单 积压订单
//        for (int[] o : orders) {
//            if (o[2] == 0) {
//                // 采购
//                while (!p1.isEmpty() && o[1] > 0 && p1.peek()[0] <= o[0]) {
//                    if (o[1] == p1.peek()[1]) {
//                        o[1] = 0;
//                        p1.poll();
//                    } else if (o[1] > p1.peek()[1]) {
//                        o[1] -= p1.poll()[1];
//                    } else {
//                        p1.peek()[1] -= o[1];
//                        o[1] = 0;
//                    }
//                }
//
//                if (o[1] > 0) {
//                    p2.add(o);
//                }
//            } else {
//                // 销售
//                while (!p2.isEmpty() && o[1] > 0 && p2.peek()[0] >= o[0]) {
//                    if (o[1] == p2.peek()[1]) {
//                        o[1] = 0;
//                        p2.poll();
//                    } else if (o[1] > p2.peek()[1]) {
//                        o[1] -= p2.poll()[1];
//                    } else {
//                        p2.peek()[1] -= o[1];
//                        o[1] = 0;
//                    }
//                }
//
//                if (o[1] > 0) {
//                    p1.add(o);
//                }
//            }
//
//
//        }
//        long cnt = 0;
//        while (!p1.isEmpty()) cnt = (cnt + p1.poll()[1]) % MOD;
//        while (!p2.isEmpty()) cnt = (cnt + p2.poll()[1]) % MOD;
//        return (int) cnt;
//    }


    static int MAXL = 100001;
    static int MAXN = 3;
    static int[][] a = new int[MAXL][MAXN];
    static int[][] b = new int[MAXL][MAXN];
    static Heap p1 = new Heap(a, 0, false); //
    static Heap p2 = new Heap(b, 0, true);

    public int getNumberOfBacklogOrders2(int[][] orders) {
        for (int[] o : orders) {
            if (o[2] == 0) {
                // 采购
                while (!p1.isEmpty() && o[1] > 0 && p1.peek()[0] <= o[0]) {
                    if (o[1] == p1.peek()[1]) {
                        o[1] = 0;
                        p1.poll();
                    } else if (o[1] > p1.peek()[1]) {
                        o[1] -= p1.poll()[1];
                    } else {
                        p1.peek()[1] -= o[1];
                        o[1] = 0;
                    }
                }

                if (o[1] > 0) {
                    p2.add(o);
                }
            } else {
                // 销售
                while (!p2.isEmpty() && o[1] > 0 && p2.peek()[0] >= o[0]) {
                    if (o[1] == p2.peek()[1]) {
                        o[1] = 0;
                        p2.poll();
                    } else if (o[1] > p2.peek()[1]) {
                        o[1] -= p2.poll()[1];
                    } else {
                        p2.peek()[1] -= o[1];
                        o[1] = 0;
                    }
                }

                if (o[1] > 0) {
                    p1.add(o);
                }
            }


        }
        long cnt = 0;
        while (!p1.isEmpty()) cnt = (cnt + p1.poll()[1]) % MOD;
        while (!p2.isEmpty()) cnt = (cnt + p2.poll()[1]) % MOD;
        return (int) cnt;
    }

    static class Heap {
        private boolean isBigHeap = true; // 最大堆
        public int size;
        int[][] arr;



        public Heap(int[][] arr) {
            this(arr,arr.length, true);
        }

        public Heap(int[][] arr, int size, boolean isBigHeap) {
            this.arr = arr;
            this.size = size;
            this.isBigHeap = isBigHeap;
            buildHeap(size);
        }

        // 构建堆
        public void buildHeap(int n) {
            for (int i = 0; i < n; i++) {
                heapInsert(i);
            }
        }


        public void heapInsert(int i) {
            while (i > 0 && cmp(arr[i], arr[(i - 1) / 2]) > 0) {
                swap(i, (i - 1) / 2);
                i = (i - 1) / 2;
            }

        }


        /**
         * 针对不同情况请自定义实现堆排序比较器
         * @param a 强大的
         * @param b 弱小的
         * @return int
         */
        public int cmp(int[] a, int[] b) {
            // a compare b
            int c = a[0] - b[0];
            if (c == 0) {
                return 0;
            } else if (c > 0) {
                return isBigHeap ? 1 : -1;
            } else {
                return isBigHeap ? -1 : 1;
            }

        }

        public void heapify() {
            heapify(0, arr.length);
        }

        public void heapify(int i, int size) {
            int l = i * 2 + 1;
            while (l < size) {
                int best = 0;
                if (l + 1 < size && cmp(arr[l + 1], arr[l]) > 0) {
                    best = l + 1;
                } else {
                    best = l;
                }
                if (cmp(arr[i], arr[best]) >= 0) {
                    break;
                }
                swap(i, best);
                i = best;
                l = 2 * i + 1;
            }
        }

        public void swap(int i, int j) {
            int[] temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        public int[] peek() {
            return arr[0];
        }

        public int[] poll() {
            if(isEmpty()) {
                throw new IndexOutOfBoundsException();
            }
            int[] temp = arr[0]; // 取出堆顶元素
            size--;
            swap(0, size);
            heapify(0, size); // 下沉
            return temp;
        }

        public void add(int[] newData) {
            if(size>=arr.length) {
                throw new IndexOutOfBoundsException();
            }
            arr[size] = newData;
            heapInsert(size);
            size++;
        }

        public int size() {
            return this.size;
        }

        public boolean isEmpty() {
            return this.size == 0;
        }

        /**
         * 堆排序
         */
        public void heapSort() {
            buildHeap(size);
            int cur = size;
            while(cur>0) {
                cur--;
                swap(0,cur);
                heapify(0,cur);
            }
        }
    }

}