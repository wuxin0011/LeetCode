package template.bit;

import code_generation.utils.RandomArrayUtils;

import java.util.*;

/**
 *  参考文章 ： 《01字典树 详解（模板，应用，进阶）》 https://blog.csdn.net/w20221013/article/details/131953807
 * @author: wuxin0011
 * @Description: 01 字典树
 */
public class _01_bit_tree {


    /**
     * 完整的01字典树，支持第K大异或值查询
     */
    static class BinaryTrie {
        static class TrieNode {
            TrieNode[] children;
            int count;      // 经过该节点的数字个数
            int endCount;   // 以该节点结尾的数字个数（可选）

            public TrieNode() {
                children = new TrieNode[2];  // 0 和 1 分支
                count = 0;
                endCount = 0;
            }
        }

        private TrieNode root;
        private int totalNumbers;  // 树中数字总数

        public BinaryTrie() {
            root = new TrieNode();
            totalNumbers = 0;
        }

        /**
         * 插入数字
         */
        public void insert(int num) {
            TrieNode node = root;
            for (int i = 31; i >= 0; i--) {
                int bit = (num >> i) & 1;
                if (node.children[bit] == null) {
                    node.children[bit] = new TrieNode();
                }
                node = node.children[bit];
                node.count++;
            }
            node.endCount++;
            totalNumbers++;
        }

        /**
         * 删除数字
         */
        public void remove(int num) {
            if (!contains(num)) return;

            TrieNode node = root;
            for (int i = 31; i >= 0; i--) {
                int bit = (num >> i) & 1;
                node = node.children[bit];
                node.count--;
            }
            node.endCount--;
            totalNumbers--;
        }

        /**
         * 检查数字是否存在
         */
        public boolean contains(int num) {
            TrieNode node = root;
            for (int i = 31; i >= 0; i--) {
                int bit = (num >> i) & 1;
                if (node.children[bit] == null || node.children[bit].count == 0) {
                    return false;
                }
                node = node.children[bit];
            }
            return node.endCount > 0;
        }

        /**
         * 获取树中数字总数
         */
        public int size() {
            return totalNumbers;
        }

        /**
         * 查询与num异或的最大值
         */
        public int queryMaxXor(int num) {
            TrieNode node = root;
            int result = 0;
            for (int i = 31; i >= 0; i--) {
                int bit = (num >> i) & 1;
                int desiredBit = 1 - bit;

                if (node.children[desiredBit] != null &&
                        node.children[desiredBit].count > 0) {
                    result |= (1 << i);
                    node = node.children[desiredBit];
                } else {
                    node = node.children[bit];
                }
            }
            return result;
        }

        /**
         * 查询与num异或的最小值
         */
        public int queryMinXor(int num) {
            TrieNode node = root;
            int result = 0;
            for (int i = 31; i >= 0; i--) {
                int bit = (num >> i) & 1;

                // 优先走相同位
                if (node.children[bit] != null && node.children[bit].count > 0) {
                    node = node.children[bit];
                } else {
                    result |= (1 << i);
                    node = node.children[1 - bit];
                }
            }
            return result;
        }

        /**
         * 查询与num异或的第K小值（k从1开始）
         */
        public int queryKthSmallestXor(int num, int k) {
            if (k < 1 || k > totalNumbers) {
                throw new IllegalArgumentException("k must be between 1 and " + totalNumbers);
            }

            TrieNode node = root;
            int result = 0;

            for (int i = 31; i >= 0; i--) {
                int bit = (num >> i) & 1;

                // 相同位产生更小的异或值
                if (node.children[bit] != null) {
                    int sameCount = node.children[bit].count;

                    if (k <= sameCount) {
                        // 走相同位，当前位异或结果为0
                        node = node.children[bit];
                    } else {
                        // 走相反位，当前位异或结果为1
                        k -= sameCount;
                        result |= (1 << i);
                        node = node.children[1 - bit];
                    }
                } else {
                    // 只能走相反位
                    result |= (1 << i);
                    node = node.children[1 - bit];
                }
            }
            return result;
        }

        /**
         * 查询与num异或的第K大值（k从1开始）
         */
        public int queryKthLargestXor(int num, int k) {
            if (k < 1 || k > totalNumbers) {
                throw new IllegalArgumentException("k must be between 1 and " + totalNumbers);
            }

            // 第k大 = 第(totalNumbers - k + 1)小
            return queryKthSmallestXor(num, totalNumbers - k + 1);
        }

        /**
         * 统计与num异或值小于target的数字数量
         */
        public int countXorLessThan(int num, int target) {
            TrieNode node = root;
            int count = 0;

            for (int i = 31; i >= 0 && node != null; i--) {
                int numBit = (num >> i) & 1;
                int targetBit = (target >> i) & 1;

                if (targetBit == 1) {
                    // 目标位为1，走相同位的所有数字异或值当前位为0，都小于target
                    if (node.children[numBit] != null) {
                        count += node.children[numBit].count;
                    }
                    // 继续检查相反位
                    node = node.children[1 - numBit];
                } else {
                    // 目标位为0，只能走相同位
                    node = node.children[numBit];
                }
            }
            return count;
        }

        /**
         * 统计与num异或值小于等于target的数字数量
         */
        public int countXorLessEqual(int num, int target) {
            return countXorLessThan(num, target + 1);
        }

        /**
         * 清空字典树
         */
        public void clear() {
            root = new TrieNode();
            totalNumbers = 0;
        }
    }

    /**
     * 求解数组中数对的第K大异或值
     */
    static class KthXorPairSolver {

        /**
         * 方法1：二分答案 + Trie计数（最优解）
         * 时间复杂度：O(32 * n * logMAX)
         */
        public static int findKthLargestXorPair(int[] nums, int k) {
            if (nums == null || nums.length < 2 || k < 1 ||
                    k > (long) nums.length * (nums.length - 1) / 2) {
                throw new IllegalArgumentException("Invalid input");
            }

            BinaryTrie trie = new BinaryTrie();
            int left = 0, right = Integer.MAX_VALUE;
            int answer = 0;

            while (left <= right) {
                int mid = left + (right - left) / 2;

                // 计算异或值 > mid 的数对数量
                long count = countXorPairsGreaterThan(nums, trie, mid);

                if (count >= k) {
                    // 有足够多的数对异或值 > mid，说明答案在右侧
                    answer = mid;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return answer;
        }

        /**
         * 统计异或值大于target的数对数量
         */
        private static long countXorPairsGreaterThan(int[] nums, BinaryTrie trie, int target) {
            trie.clear();
            long count = 0;

            for (int num : nums) {
                // 与当前num异或值 > target 的数量 = 总数 - 异或值 <= target的数量
                count += trie.size() - trie.countXorLessEqual(num, target);
                trie.insert(num);
            }
            return count;
        }

        /**
         * 方法2：最小堆（适用于k较小的情况）
         * 时间复杂度：O(n^2 * logk)
         */
        public static int findKthLargestXorPairHeap(int[] nums, int k) {
            if (k == 1) {
                return findMaximumXorPair(nums);
            }

            // 使用最小堆维护前k大的异或值
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();

            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    int xor = nums[i] ^ nums[j];

                    if (minHeap.size() < k) {
                        minHeap.offer(xor);
                    } else if (xor > minHeap.peek()) {
                        minHeap.poll();
                        minHeap.offer(xor);
                    }
                }
            }

            return minHeap.peek();
        }

        /**
         * 方法3：排序所有异或值（适用于n较小的情况）
         * 时间复杂度：O(n^2 * logn)
         */
        public static int findKthLargestXorPairSort(int[] nums, int k) {
            List<Integer> xors = new ArrayList<>();

            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    xors.add(nums[i] ^ nums[j]);
                }
            }

            Collections.sort(xors, Collections.reverseOrder());
            return xors.get(k - 1);
        }

        /**
         * 辅助方法：求最大异或对
         */
        private static int findMaximumXorPair(int[] nums) {
            BinaryTrie trie = new BinaryTrie();
            int maxXor = 0;

            for (int num : nums) {
                maxXor = Math.max(maxXor, trie.queryMaxXor(num));
                trie.insert(num);
            }
            return maxXor;
        }

        /**
         * 验证方法：暴力求解（用于测试）
         */
        public static int findKthLargestXorPairBruteForce(int[] nums, int k) {
            List<Integer> xors = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    xors.add(nums[i] ^ nums[j]);
                }
            }
            Collections.sort(xors, Collections.reverseOrder());
            return xors.get(k - 1);
        }
    }


    public static void main(String[] args) {
        // 测试数据
        int[] nums = RandomArrayUtils.randomIntArray(3,10,1,1000);
        int k = 3;

        System.out.println("数组: " + Arrays.toString(nums));
        System.out.println("数对总数: " + (nums.length * (nums.length - 1) / 2));

        // 计算所有异或值用于验证
        List<Integer> allXors = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                allXors.add(nums[i] ^ nums[j]);
            }
        }
        Collections.sort(allXors, Collections.reverseOrder());
        System.out.println("所有异或值排序: " + allXors);

        // 测试各种方法
        System.out.println("\n第 " + k + " 大异或值:");
        System.out.println("暴力法: " + KthXorPairSolver.findKthLargestXorPairBruteForce(nums, k));
        System.out.println("二分+Trie法: " + KthXorPairSolver.findKthLargestXorPair(nums, k));
        System.out.println("堆方法: " + KthXorPairSolver.findKthLargestXorPairHeap(nums, k));
        System.out.println("排序法: " + KthXorPairSolver.findKthLargestXorPairSort(nums, k));

        // 测试单个数字的第K大异或值
        System.out.println("\n=== 测试单个数字的第K大异或值 ===");
        BinaryTrie trie = new BinaryTrie();
        int[] numbers = {1, 2, 3, 4, 5};
        for (int num : numbers) {
            trie.insert(num);
        }

        int testNum = 3;
        for (int i = 1; i <= numbers.length; i++) {
            int kthLargest = trie.queryKthLargestXor(testNum, i);
            int kthSmallest = trie.queryKthSmallestXor(testNum, i);
            System.out.printf("与 %d 异或的第%d大值: %d, 第%d小值: %d%n",
                    testNum, i, kthLargest, i, kthSmallest);
        }

        // 性能测试
        System.out.println("\n=== 性能测试 ===");
        int[] largeNums = generateTestData(1000);
        long start = System.currentTimeMillis();
        int result = KthXorPairSolver.findKthLargestXorPair(largeNums, 100);
        long end = System.currentTimeMillis();
        System.out.println("1000个数字中第100大异或值: " + result);
        System.out.println("二分+Trie法耗时: " + (end - start) + "ms");
    }

    // 生成测试数据
    private static int[] generateTestData(int size) {
        Random random = new Random();
        int[] data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = random.nextInt(100000);
        }
        return data;
    }
}
