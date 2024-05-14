package leetcode.ox3if.data_struct.tree_array.Solution_0001;

import code_generation.utils.IoUtil;

import java.util.*;

/**
 * 3072. 将元素分配到两个数组中 II
 * <p>
 * 给你一个下标从 1 开始、长度为 n 的整数数组 nums 。
 * 现定义函数 greaterCount ，使得 greaterCount(arr, val) 返回数组 arr 中 严格大于 val 的元素数量。
 * 你需要使用 n 次操作，将 nums 的所有元素分配到两个数组 arr1 和 arr2 中。在第一次操作中，将 nums[1] 追加到 arr1 。在第二次操作中，将 nums[2] 追加到 arr2 。之后，在第 i 次操作中：
 * 如果 greaterCount(arr1, nums[i]) > greaterCount(arr2, nums[i]) ，将 nums[i] 追加到 arr1 。
 * 如果 greaterCount(arr1, nums[i]) < greaterCount(arr2, nums[i]) ，将 nums[i] 追加到 arr2 。
 * 如果 greaterCount(arr1, nums[i]) == greaterCount(arr2, nums[i]) ，将 nums[i] 追加到元素数量较少的数组中。
 * 如果仍然相等，那么将 nums[i] 追加到 arr1 。
 * 连接数组 arr1 和 arr2 形成数组 result 。例如，如果 arr1 == [1,2,3] 且 arr2 == [4,5,6] ，那么 result = [1,2,3,4,5,6] 。
 * 返回整数数组 result 。
 * <p>
 * 示例 1：
 * 输入：nums = [2,1,3,3]
 * 输出：[2,3,1,3]
 * 解释：在前两次操作后，arr1 = [2] ，arr2 = [1] 。
 * 在第 3 次操作中，两个数组中大于 3 的元素数量都是零，并且长度相等，因此，将 nums[3] 追加到 arr1 。
 * 在第 4 次操作中，两个数组中大于 3 的元素数量都是零，但 arr2 的长度较小，因此，将 nums[4] 追加到 arr2 。
 * 在 4 次操作后，arr1 = [2,3] ，arr2 = [1,3] 。
 * 因此，连接形成的数组 result 是 [2,3,1,3] 。
 * <p>
 * 示例 2：
 * 输入：nums = [5,14,3,1,2]
 * 输出：[5,3,1,2,14]
 * 解释：在前两次操作后，arr1 = [5] ，arr2 = [14] 。
 * 在第 3 次操作中，两个数组中大于 3 的元素数量都是一，并且长度相等，因此，将 nums[3] 追加到 arr1 。
 * 在第 4 次操作中，arr1 中大于 1 的元素数量大于 arr2 中的数量（2 > 1），因此，将 nums[4] 追加到 arr1 。
 * 在第 5 次操作中，arr1 中大于 2 的元素数量大于 arr2 中的数量（2 > 1），因此，将 nums[5] 追加到 arr1 。
 * 在 5 次操作后，arr1 = [5,3,1,2] ，arr2 = [14] 。
 * 因此，连接形成的数组 result 是 [5,3,1,2,14] 。
 * <p>
 * 示例 3：
 * 输入：nums = [3,3,3,3]
 * 输出：[3,3,3,3]
 * 解释：在 4 次操作后，arr1 = [3,3] ，arr2 = [3,3] 。
 * 因此，连接形成的数组 result 是 [3,3,3,3] 。
 * <p>
 * 提示：
 * 3 <= n <= 10^5
 * 1 <= nums[i] <= 10^9
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/distribute-elements-into-two-arrays-ii
 * @title: distribute-elements-into-two-arrays-ii
 */
public class Solution {

    /**
     * 最优解
     */
    static class Solution1 {

        public static void main(String[] args) {
            IoUtil.testUtil(Solution1.class, "resultArray", "in.txt");
        }

        static class Fenwick {
            private final int[] tree;

            public Fenwick(int n) {
                tree = new int[n];
            }

            // 把下标为 i 的元素增加 1
            public void add(int i) {
                while (i < tree.length) {
                    tree[i]++;
                    i += i & -i;
                }
            }

            // 返回下标在 [1,i] 的元素之和
            public int pre(int i) {
                int res = 0;
                while (i > 0) {
                    res += tree[i];
                    i &= i - 1;
                }
                return res;
            }
        }

        public int[] resultArray(int[] nums) {
            int[] sorted = nums.clone();
            Arrays.sort(sorted); // 只排序不去重
            int n = nums.length;

            List<Integer> a = new ArrayList<>(n); // 预分配空间
            List<Integer> b = new ArrayList<>();
            a.add(nums[0]);
            b.add(nums[1]);

            Fenwick t1 = new Fenwick(n + 1);
            Fenwick t2 = new Fenwick(n + 1);
            t1.add(Arrays.binarySearch(sorted, nums[0]) + 1);
            t2.add(Arrays.binarySearch(sorted, nums[1]) + 1);

            for (int i = 2; i < nums.length; i++) {
                int x = nums[i];
                int v = Arrays.binarySearch(sorted, x) + 1;
                int gc1 = a.size() - t1.pre(v); // greaterCount(a, v)
                int gc2 = b.size() - t2.pre(v); // greaterCount(b, v)
                if (gc1 > gc2 || gc1 == gc2 && a.size() <= b.size()) {
                    a.add(x);
                    t1.add(v);
                } else {
                    b.add(x);
                    t2.add(v);
                }
            }
            a.addAll(b);
            for (int i = 0; i < n; i++) {
                nums[i] = a.get(i);
            }
            return nums;
        }
    }

    static class Solution2 {

        public static void main(String[] args) {
            IoUtil.testUtil(Solution2.class, "resultArray", "in.txt");
        }

        /**
         * 二分查找解法
         *
         * @param nums
         * @return
         * @test result https://leetcode.cn/problems/distribute-elements-into-two-arrays-ii/submissions/531979621/ 761ms
         */
        public int[] resultArray(int[] nums) {

            int n = nums.length;
            List<int[]> a1 = new ArrayList<>();
            List<int[]> a2 = new ArrayList<>();
            int size1 = 1, size2 = 1;
            a1.add(new int[]{nums[0], 0});
            a2.add(new int[]{nums[1], 1});
            for (int i = 2; i < nums.length; i++) {
                int val = nums[i];
                int l1 = lower_bound(a1, val + 1);
                int l2 = lower_bound(a2, val + 1);
                int cnt1 = l1 == -1 ? 0 : size1 - l1, cnt2 = l2 == -1 ? 0 : size2 - l2;
                if (cnt1 > cnt2 || size1 <= size2 && cnt1 == cnt2) {
                    insert(a1, l1, new int[]{val, i});
                    size1++;
                } else {
                    insert(a2, l2, new int[]{val, i});
                    size2++;
                }
            }


            int[] ans = new int[n];
            int i = 0;
            a1.sort((a, b) -> a[1] - b[1]);
            a2.sort((a, b) -> a[1] - b[1]);
            for (int[] a : a1) ans[i++] = a[0];
            for (int[] a : a2) ans[i++] = a[0];
            return ans;
        }

        public static void insert(List<int[]> arr, int pos, int[] vals) {
            //
            if (pos < 0) {
                pos = 0;
            }
            arr.add(pos, vals);
        }


        public static int lower_bound(List<int[]> arr, int val) {
            int r = arr.size() - 1;
            int l = 0;
            while (l <= r) {
                int mid = l + ((r - l) >> 1);
                if (arr.get(mid)[0] >= val) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            return l;
        }

    }

    static class Solution3 {

        public static void main(String[] args) {
            IoUtil.testUtil(Solution3.class, "resultArrayTimeout", "in.txt");
        }

        /**
         * Timeout 做法,过了840/844
         *
         * @param nums
         * @return
         */
        public int[] resultArrayTimeout(int[] nums) {
            boolean isOk = true;
            int pre = nums[0];
            for (int num : nums) {
                if (num != pre) {
                    isOk = false;
                    break;
                }
            }
            if (isOk) {
                return nums;
            }

            TreeMap<Integer, List<Integer>> arr1 = new TreeMap<>((a, b) -> a - b);
            int size1 = 1;
            TreeMap<Integer, List<Integer>> arr2 = new TreeMap<>((a, b) -> a - b);
            int size2 = 1;
            int n = nums.length;
            update(nums[0], arr1, 0);
            update(nums[1], arr2, 1);
            for (int i = 2; i < n; i++) {
                int val = nums[i];
                int val1 = greaterCount1(val, arr1);
                int val2 = greaterCount1(val, arr2);
                if (val1 > val2) {
                    update(val, arr1, i);
                    size1++;
                } else if (val1 < val2) {
                    update(val, arr2, i);
                    size2++;
                } else {
                    if (size1 <= size2) {
                        update(val, arr1, i);
                        size1++;
                    } else {
                        update(val, arr2, i);
                        size2++;
                    }
                }

            }

            int[] ans = new int[n];
            fill(ans, arr1, arr2);
            // System.out.println(Arrays.toString(ans));
            return ans;
        }

        public static int greaterCount1(int val, TreeMap<Integer, List<Integer>> treeMap) {
            Map<Integer, List<Integer>> map = treeMap.tailMap(val, false);
            int cnt = 0;
            for (Map.Entry<Integer, List<Integer>> item : map.entrySet()) {
                cnt += item.getValue().size();
            }
            return cnt;
        }

        public static void update(int val, TreeMap<Integer, List<Integer>> treeMap, int id) {
            List<Integer> orDefault = treeMap.getOrDefault(val, new ArrayList<Integer>());
            orDefault.add(id);
            treeMap.put(val, orDefault);
        }

        public static void fill(int[] arr, TreeMap<Integer, List<Integer>> treeMap, TreeMap<Integer, List<Integer>> treeMap2) {
            List<int[]> ids = new ArrayList<>();
            for (Map.Entry<Integer, List<Integer>> item : treeMap.entrySet()) {
                int k = item.getKey();
                for (Integer id : item.getValue()) {
                    ids.add(new int[]{k, id});
                }
            }
            ids.sort(Comparator.comparingInt(a -> a[1]));
            int j = 0;
            for (int[] ints : ids) {
                arr[j++] = ints[0];
            }
            ids.clear();
            for (Map.Entry<Integer, List<Integer>> item : treeMap2.entrySet()) {
                int k = item.getKey();
                for (Integer id : item.getValue()) {
                    ids.add(new int[]{k, id});
                }
            }
            ids.sort((a, b) -> a[1] - b[1]);
            for (int[] id : ids) {
                arr[j++] = id[0];
            }
        }

    }


}