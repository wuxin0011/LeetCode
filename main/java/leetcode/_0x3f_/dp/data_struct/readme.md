## 十一、数据结构优化 DP


### §11.1 前缀和优化 DP

* [2327\. 知道秘密的人数](https://leetcode.cn/problems/number-of-people-aware-of-a-secret/) 1894
* [1997\. 访问完所有房间的第一天](https://leetcode.cn/problems/first-day-where-you-have-been-in-all-the-rooms/) 2260
* [2478\. 完美分割的方案数](https://leetcode.cn/problems/number-of-beautiful-partitions/) 2344
* [837\. 新 21 点](https://leetcode.cn/problems/new-21-game/) 2350
* [2463\. 最小移动总距离](https://leetcode.cn/problems/minimum-total-distance-traveled/) 2454
* [629\. K 个逆序对数组](https://leetcode.cn/problems/k-inverse-pairs-array/)
* [1977\. 划分数字的方案数](https://leetcode.cn/problems/number-of-ways-to-separate-numbers/) 2817

### §11.2 单调栈优化 DP

前置题单：[单调栈（矩形系列/字典序最小/贡献法）](https://leetcode.cn/circle/discuss/9oZFK9/)

* [1335\. 工作计划的最低难度](https://leetcode.cn/problems/minimum-difficulty-of-a-job-schedule/) 2035
* [2866\. 美丽塔 II](https://leetcode.cn/problems/beautiful-towers-ii/) 2072
* [2617\. 网格图中最少访问的格子数](https://leetcode.cn/problems/minimum-number-of-visited-cells-in-a-grid/) 2582
* [2355\. 你能拿走的最大图书数量](https://leetcode.cn/problems/maximum-number-of-books-you-can-take/)（会员题）

### §11.3 单调队列优化 DP

一般用来维护一段转移来源的最值。

1. 前提：区间右端点变大时，左端点也在变大（同滑动窗口）。
2. 转移前，去掉队首无用数据。
3. 计算转移（直接从队首转移）。
4. 把数据（一般是 <math><semantics><mrow><mi>f</mi><mo>\[</mo><mi>i</mi><mo>\]</mo></mrow><annotation>f\[i\]</annotation></semantics></math>f\[i\]）插入队尾前，去掉队尾无用数据。

* [2944\. 购买水果需要的最少金币数](https://leetcode.cn/problems/minimum-number-of-coins-for-fruits/) 1709 可以用单调队列优化到 <math><semantics><mrow><mi>O</mi><mo>(</mo><mi>n</mi><mo>)</mo></mrow><annotation>\\mathcal{O}(n)</annotation></semantics></math>O(n)
* [1696\. 跳跃游戏 VI](https://leetcode.cn/problems/jump-game-vi/) 1954
* [1425\. 带限制的子序列和](https://leetcode.cn/problems/constrained-subsequence-sum/) 2032
* [375\. 猜数字大小 II](https://leetcode.cn/problems/guess-number-higher-or-lower-ii/) 可以用单调队列优化到 <math><semantics><mrow><mi>O</mi><mo>(</mo><msup><mi>n</mi><mn>2</mn></msup><mo>)</mo></mrow><annotation>\\mathcal{O}(n^2)</annotation></semantics></math>O(n2)
* [1687\. 从仓库到码头运输箱子](https://leetcode.cn/problems/delivering-boxes-from-storage-to-ports/) 2610
* [2945\. 找到最大非递减数组的长度](https://leetcode.cn/problems/find-maximum-non-decreasing-array-length/) 2943
* [2969\. 购买水果需要的最少金币数 II](https://leetcode.cn/problems/minimum-number-of-coins-for-fruits-ii/)（会员题）

### §11.4 树状数组/线段树优化 DP

* [1626\. 无矛盾的最佳球队](https://leetcode.cn/problems/best-team-with-no-conflicts/) 2027
* [2407\. 最长递增子序列 II](https://leetcode.cn/problems/longest-increasing-subsequence-ii/) 2280
* [2770\. 达到末尾下标所需的最大跳跃次数](https://leetcode.cn/problems/maximum-number-of-jumps-to-reach-the-last-index/) 见我题解下的 [评论](https://leetcode.cn/problems/maximum-number-of-jumps-to-reach-the-last-index/solutions/2336752/dong-tai-gui-hua-cong-ji-yi-hua-sou-suo-2ptkg/comments/2060234)
* [2926\. 平衡子序列的最大和](https://leetcode.cn/problems/maximum-balanced-subsequence-sum/) 2448
* [2916\. 子数组不同元素数目的平方和 II](https://leetcode.cn/problems/subarrays-distinct-element-sum-of-squares-ii/) 2816

### §11.5 其它优化 DP

* [2713\. 矩阵中严格递增的单元格数](https://leetcode.cn/problems/maximum-strictly-increasing-cells-in-a-matrix/) 2387
* [LCP 59. 搭桥过河](https://leetcode.cn/problems/NfY1m5/)
* [2263\. 数组变为有序的最小操作次数](https://leetcode.cn/problems/make-array-non-decreasing-or-non-increasing/)（会员题）
