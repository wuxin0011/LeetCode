推荐先阅读：[从集合论到位运算，常见位运算技巧分类总结！](https://leetcode.cn/circle/discuss/CaOJ45/)

### 基础题

* [1486\. 数组异或操作](https://leetcode.cn/problems/xor-operation-in-an-array/) 1181
* [2595\. 奇偶位数](https://leetcode.cn/problems/number-of-even-and-odd-bits/) 1207
* [231\. 2 的幂](https://leetcode.cn/problems/power-of-two/)
* [342\. 4 的幂](https://leetcode.cn/problems/power-of-four/)
* [476\. 数字的补数](https://leetcode.cn/problems/number-complement/) 1235
* [191\. 位 1 的个数](https://leetcode.cn/problems/number-of-1-bits/)
* [338\. 比特位计数](https://leetcode.cn/problems/counting-bits/) 也可以 DP
* [1356\. 根据数字二进制下 1 的数目排序](https://leetcode.cn/problems/sort-integers-by-the-number-of-1-bits/) 1258
* [461\. 汉明距离](https://leetcode.cn/problems/hamming-distance/)
* [2220\. 转换数字的最少位翻转次数](https://leetcode.cn/problems/minimum-bit-flips-to-convert-number/) 1282
* [868\. 二进制间距](https://leetcode.cn/problems/binary-gap/) 1307
* [2917\. 找出数组中的 K-or 值](https://leetcode.cn/problems/find-the-k-or-of-an-array/) 1389
* [693\. 交替位二进制数](https://leetcode.cn/problems/binary-number-with-alternating-bits/)

### 与或（AND/OR）的性质

* [2980\. 检查按位或是否存在尾随零](https://leetcode.cn/problems/check-if-bitwise-or-has-trailing-zeros/) 1234
* [1318\. 或运算的最小翻转次数](https://leetcode.cn/problems/minimum-flips-to-make-a-or-b-equal-to-c/) 1383
* [2419\. 按位与最大的最长子数组](https://leetcode.cn/problems/longest-subarray-with-maximum-bitwise-and/) 1496
* [2871\. 将数组分割成最多数目的子数组](https://leetcode.cn/problems/split-array-into-maximum-number-of-subarrays/) 1750
* [2401\. 最长优雅子数组](https://leetcode.cn/problems/longest-nice-subarray/) 1750
* [2680\. 最大或值](https://leetcode.cn/problems/maximum-or/) 1912 可以做到 <math><semantics><mrow><mi>O</mi><mo>(</mo><mn>1</mn><mo>)</mo></mrow><annotation>\\mathcal{O}(1)</annotation></semantics></math>O(1) 额外空间
* [2411\. 按位或最大的最小子数组长度](https://leetcode.cn/problems/smallest-subarrays-with-maximum-bitwise-or/) 1938
* [898\. 子数组按位或操作](https://leetcode.cn/problems/bitwise-ors-of-subarrays/) 2133
* [1521\. 找到最接近目标值的函数值](https://leetcode.cn/problems/find-a-value-of-a-mysterious-function-closest-to-target/) 2384

### 异或（XOR）的性质

* [1720\. 解码异或后的数组](https://leetcode.cn/problems/decode-xored-array/) 1284
* [2433\. 找出前缀异或的原始数组](https://leetcode.cn/problems/find-the-original-array-of-prefix-xor/) 1367
* [1310\. 子数组异或查询](https://leetcode.cn/problems/xor-queries-of-a-subarray/) 1460
* [2683\. 相邻值的按位异或](https://leetcode.cn/problems/neighboring-bitwise-xor/) 1518
* [1829\. 每个查询的最大异或值](https://leetcode.cn/problems/maximum-xor-for-each-query/) 1523
* [2997\. 使数组异或和等于 K 的最少操作次数](https://leetcode.cn/problems/minimum-number-of-operations-to-make-array-xor-equal-to-k/) 1525
* [1442\. 形成两个异或相等数组的三元组数目](https://leetcode.cn/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/) 1525
* [2429\. 最小异或](https://leetcode.cn/problems/minimize-xor/) 1532
* [2527\. 查询数组异或美丽值](https://leetcode.cn/problems/find-xor-beauty-of-array/) 1550
* [2317\. 操作后的最大异或和](https://leetcode.cn/problems/maximum-xor-after-operations/) 1679
* [2588\. 统计美丽子数组数目](https://leetcode.cn/problems/count-the-number-of-beautiful-subarrays/) 1697
* [2564\. 子字符串异或查询](https://leetcode.cn/problems/substring-xor-queries/) 1959
* [1734\. 解码异或后的排列](https://leetcode.cn/problems/decode-xored-permutation/) 2024
* [2857\. 统计距离为 k 的点对](https://leetcode.cn/problems/count-pairs-of-points-with-distance-k/) 2082

### 拆位 / 贡献法

* [477\. 汉明距离总和](https://leetcode.cn/problems/total-hamming-distance/)
* [1863\. 找出所有子集的异或总和再求和](https://leetcode.cn/problems/sum-of-all-subset-xor-totals/) 可以做到 <math><semantics><mrow><mi>O</mi><mo>(</mo><mi>n</mi><mo>)</mo></mrow><annotation>\\mathcal{O}(n)</annotation></semantics></math>O(n) 时间
* [2425\. 所有数对的异或和](https://leetcode.cn/problems/bitwise-xor-of-all-pairings/) 1622 可以做到 <math><semantics><mrow><mi>O</mi><mo>(</mo><mi>n</mi><mo>+</mo><mi>m</mi><mo>)</mo></mrow><annotation>\\mathcal{O}(n+m)</annotation></semantics></math>O(n+m) 时间
* [2275\. 按位与结果大于零的最长组合](https://leetcode.cn/problems/largest-combination-with-bitwise-and-greater-than-zero/) 1642
* [1835\. 所有数对按位与结果的异或和](https://leetcode.cn/problems/find-xor-sum-of-all-pairs-bitwise-and/) 1825 也有恒等式做法
* [2505\. 所有子序列和的按位或](https://leetcode.cn/problems/bitwise-or-of-all-subsequence-sums/)（会员题）

### 试填法

* [3007\. 价值和小于等于 K 的最大数字](https://leetcode.cn/problems/maximum-number-that-sum-of-the-prices-is-less-than-or-equal-to-k/) 2258
* [421\. 数组中两个数的最大异或值](https://leetcode.cn/problems/maximum-xor-of-two-numbers-in-an-array/)，[试填法题解](https://leetcode.cn/problems/maximum-xor-of-two-numbers-in-an-array/solution/tu-jie-jian-ji-gao-xiao-yi-tu-miao-dong-1427d/)
* [2935\. 找出强数对的最大异或值 II](https://leetcode.cn/problems/maximum-strong-pair-xor-ii/) 2349
* [3022\. 给定操作次数内使剩余元素的或值最小](https://leetcode.cn/problems/minimize-or-of-remaining-elements-using-operations/) 2918

### 恒等式

* [1835\. 所有数对按位与结果的异或和](https://leetcode.cn/problems/find-xor-sum-of-all-pairs-bitwise-and/) 1825
* [2354\. 优质数对的数目](https://leetcode.cn/problems/number-of-excellent-pairs/) 2076

### 思维题（贪心、脑筋急转弯等）

* [2546\. 执行逐位运算使字符串相等](https://leetcode.cn/problems/apply-bitwise-operations-to-make-strings-equal/) 1605
* [1558\. 得到目标数组的最少函数调用次数](https://leetcode.cn/problems/minimum-numbers-of-function-calls-to-make-target-array/) 1637
* [2571\. 将整数减少到零需要的最少操作数](https://leetcode.cn/problems/minimum-operations-to-reduce-an-integer-to-0/) 1649 巧妙结论
* [2568\. 最小无法得到的或值](https://leetcode.cn/problems/minimum-impossible-or/) 1754
* [2939\. 最大异或乘积](https://leetcode.cn/problems/maximum-xor-product/) 2128
* [2749\. 得到整数零需要执行的最少操作数](https://leetcode.cn/problems/minimum-operations-to-make-the-integer-zero/) 2132
* [2835\. 使子序列的和等于目标的最少操作次数](https://leetcode.cn/problems/minimum-operations-to-form-subsequence-with-target-sum/) 2207
* [2897\. 对数组执行操作使平方和最大](https://leetcode.cn/problems/apply-operations-on-array-to-maximize-sum-of-squares/) 2301
* [810\. 黑板异或游戏](https://leetcode.cn/problems/chalkboard-xor-game/) 2341

### 其它

* [136\. 只出现一次的数字](https://leetcode.cn/problems/single-number/)
* [287\. 寻找重复数](https://leetcode.cn/problems/find-the-duplicate-number/)
* [260\. 只出现一次的数字 III](https://leetcode.cn/problems/single-number-iii/)
* [137\. 只出现一次的数字 II](https://leetcode.cn/problems/single-number-ii/)
* [645\. 错误的集合](https://leetcode.cn/problems/set-mismatch/)
* [190\. 颠倒二进制位](https://leetcode.cn/problems/reverse-bits/)
* [371\. 两整数之和](https://leetcode.cn/problems/sum-of-two-integers/)
* [201\. 数字范围按位与](https://leetcode.cn/problems/bitwise-and-of-numbers-range/)
* [2154\. 将找到的值乘以 2](https://leetcode.cn/problems/keep-multiplying-found-values-by-two/) 可以做到 <math><semantics><mrow><mi>O</mi><mo>(</mo><mi>n</mi><mo>)</mo></mrow><annotation>\\mathcal{O}(n)</annotation></semantics></math>O(n) 时间
* [2044\. 统计按位或能得到最大值的子集数目](https://leetcode.cn/problems/count-number-of-maximum-bitwise-or-subsets/) 1568
* [2438\. 二的幂数组中查询范围内的乘积](https://leetcode.cn/problems/range-product-queries-of-powers/) 1610
* [1680\. 连接连续二进制数字](https://leetcode.cn/problems/concatenation-of-consecutive-binary-numbers/) 1630
* [982\. 按位与为零的三元组](https://leetcode.cn/problems/triples-with-bitwise-and-equal-to-zero/) 2085
* [1611\. 使整数变为 0 的最少操作次数](https://leetcode.cn/problems/minimum-one-bit-operations-to-make-integers-zero/) 2345
