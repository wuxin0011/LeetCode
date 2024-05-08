package leetcode.ox3if.data_struct.heap.base.Solution_0012;

import code_generation.utils.IoUtil;

import java.util.PriorityQueue;

/**
 * 2462. 雇佣 K 位工人的总代价
 * <p>
 * 给你一个下标从 0开始的整数数组costs，其中costs[i]是雇佣第 i位工人的代价。
 * 同时给你两个整数k 和candidates。我们想根据以下规则恰好雇佣k位工人：
 * 总共进行k轮雇佣，且每一轮恰好雇佣一位工人。
 * 在每一轮雇佣中，从最前面 candidates和最后面 candidates人中选出代价最小的一位工人，如果有多位代价相同且最小的工人，选择下标更小的一位工人。
 * <p>
 * 比方说，costs = [3,2,7,7,1,2] 且candidates = 2，第一轮雇佣中，我们选择第4位工人，因为他的代价最小[3,2,7,7,1,2]。
 * 第二轮雇佣，我们选择第1位工人，因为他们的代价与第4位工人一样都是最小代价，而且下标更小，[3,2,7,7,2]。注意每一轮雇佣后，剩余工人的下标可能会发生变化。
 * <p>
 * <p>
 * 如果剩余员工数目不足 candidates人，那么下一轮雇佣他们中代价最小的一人，如果有多位代价相同且最小的工人，选择下标更小的一位工人。
 * 一位工人只能被选择一次。
 * 返回雇佣恰好k位工人的总代价。
 * <p>
 * 示例 1：
 * 输入：costs = [17,12,10,2,7,2,11,20,8], k = 3, candidates = 4
 * 输出：11
 * 解释：我们总共雇佣 3 位工人。总代价一开始为 0 。
 * - 第一轮雇佣，我们从 [17,12,10,2,7,2,11,20,8] 中选择。最小代价是 2 ，有两位工人，我们选择下标更小的一位工人，即第 3 位工人。总代价是 0 + 2 = 2 。
 * - 第二轮雇佣，我们从 [17,12,10,7,2,11,20,8] 中选择。最小代价是 2 ，下标为 4 ，总代价是 2 + 2 = 4 。
 * - 第三轮雇佣，我们从 [17,12,10,7,11,20,8] 中选择，最小代价是 7 ，下标为 3 ，总代价是 4 + 7 = 11 。注意下标为 3 的工人同时在最前面和最后面 4 位工人中。
 * 总雇佣代价是 11 。
 * <p>
 * 示例 2：
 * 输入：costs = [1,2,4,1], k = 3, candidates = 3
 * 输出：4
 * 解释：我们总共雇佣 3 位工人。总代价一开始为 0 。
 * - 第一轮雇佣，我们从 [1,2,4,1] 中选择。最小代价为 1 ，有两位工人，我们选择下标更小的一位工人，即第 0 位工人，总代价是 0 + 1 = 1 。注意，下标为 1 和 2 的工人同时在最前面和最后面 3 位工人中。
 * - 第二轮雇佣，我们从 [2,4,1] 中选择。最小代价为 1 ，下标为 2 ，总代价是 1 + 1 = 2 。
 * - 第三轮雇佣，少于 3 位工人，我们从剩余工人 [2,4] 中选择。最小代价是 2 ，下标为 0 。总代价为 2 + 2 = 4 。
 * 总雇佣代价是 4 。
 * <p>
 * 提示：
 * 1 <= costs.length <= 10^5
 * 1 <= costs[i] <= 10^5
 * 1 <= k, candidates <= costs.length
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/total-cost-to-hire-k-workers
 * @title: total-cost-to-hire-k-workers
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "totalCost", "in.txt");
        IoUtil.testUtil(Solution.class, "totalCost2", "in.txt");
    }


    public long totalCost(int[] costs, int k, int candidates) {

        int n = costs.length;

        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        long ans = 0;
        int tot = n;
        int i = 0, j = n - 1;
        int cnt1 = 0, cnt2 = 0;
        boolean[] vis = new boolean[n];
        while (i + cnt1 < candidates && j - cnt2 >= 0) {
            q.add(new int[]{costs[i + cnt1], i + cnt1});
            q.add(new int[]{costs[j - cnt2], j - cnt2});
            cnt1++;
            cnt2++;
        }
        i += cnt1;
        j -= cnt2;

        while (k > 0 && !q.isEmpty() && tot > 0) {
            int[] p = q.poll();
            int id = p[1];
            if (vis[id]) continue;
            ans += p[0];
            vis[id] = true;
            if (id < i && i < n) {
                q.add(new int[]{costs[i], i});
                i++;
            } else if (id > j && j >= 0) {
                q.add(new int[]{costs[j], j});
                j--;
            }
            tot--;
            k--;
        }


        return ans;

    }


    public static int max = 1000001;
    public static int[][] arr = new int[max][2];
    public static Heap heap = new Heap(arr, 0, false);
    static boolean[] vis = new boolean[max];

    public static void clear(int n) {
        heap.size = 0;
        for (int i = 0; i < n; i++) {
            vis[i] = false;
        }
    }

    public long totalCost2(int[] costs, int k, int candidates) {

        int n = costs.length;
        clear(n);
        long ans = 0;
        int tot = n;
        int i = 0, j = n - 1;
        int cnt = 0;
        boolean[] vis = new boolean[n];
        while ( i < n && j >= 0 && cnt < candidates) {
            heap.add(new int[]{costs[i], i});
            heap.add(new int[]{costs[j], j});
            cnt++;
            i++;
            j--;
        }
        while (k > 0 && !heap.isEmpty() && tot > 0) {
            int[] p = heap.poll();
            int id = p[1];
            if (vis[id]) continue;
            ans += p[0];
            vis[id] = true;
            if (id < i && i < n) {
                heap.add(new int[]{costs[i], i});
                i++;
            } else if (id > j && j >= 0) {
                heap.add(new int[]{costs[j], j});
                j--;
            }
            tot--;
            k--;
        }


        return ans;

    }


    static class Heap {
        private boolean isBigHeap = true; // 最大堆
        public int size;
        int[][] arr;


        public Heap(int[][] arr) {
            this(arr, arr.length, true);
        }

        public Heap(int[][] arr, int size, boolean isBigHeap) {
            this.arr = arr;
            this.size = size;
            this.isBigHeap = isBigHeap;
            this.buildHeap(size);
        }


        /**
         * 针对不同情况请自定义实现堆排序比较器
         *
         * @param a 强大的
         * @param b 弱小的
         * @return int
         */
        public int cmp(int[] a, int[] b) {
            // a compare b
            int c = a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]; // default compare
            if (c == 0) {
                return 0;
            } else if (c > 0) {
                return isBigHeap ? 1 : -1;
            } else {
                return isBigHeap ? -1 : 1;
            }

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
            if (isEmpty()) {
                throw new IndexOutOfBoundsException();
            }
            int[] temp = arr[0]; // 取出堆顶元素
            size--;
            swap(0, size);
            heapify(0, size); // 下沉
            return temp;
        }

        public void add(int[] newData) {
            if (size >= arr.length) {
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
            while (cur > 0) {
                cur--;
                swap(0, cur);
                heapify(0, cur);
            }
        }
    }


}