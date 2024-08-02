
## 九、状态压缩 DP（状压 DP）

推荐先阅读：[从集合论到位运算，常见位运算技巧分类总结！](https://leetcode.cn/circle/discuss/CaOJ45/)

### §9.1 排列型 ① 相邻无关

暴力做法是枚举数组 <math><semantics><mrow><mi>a</mi></mrow><annotation>a</annotation></semantics></math>a 的所有排列，对每个排列计算和题目有关的值，时间复杂度（通常来说）是 <math><semantics><mrow><mi>O</mi><mo>(</mo><mi>n</mi><mo>⋅</mo><mi>n</mi><mo>!</mo><mo>)</mo></mrow><annotation>\\mathcal{O}(n\\cdot n!)</annotation></semantics></math>O(n⋅n!)。可以解决 <math><semantics><mrow><mi>n</mi><mo>≤</mo><mn>10</mn></mrow><annotation>n\\le 10</annotation></semantics></math>n≤10 的问题。

状压 DP 可以把时间复杂度（通常来说）优化至 <math><semantics><mrow><mi>O</mi><mo>(</mo><mi>n</mi><mo>⋅</mo><msup><mn>2</mn><mi>n</mi></msup><mo>)</mo></mrow><annotation>\\mathcal{O}(n\\cdot 2^n)</annotation></semantics></math>O(n⋅2n)。可以解决 <math><semantics><mrow><mi>n</mi><mo>≤</mo><mn>20</mn></mrow><annotation>n\\le 20</annotation></semantics></math>n≤20 的问题。

一般定义 <math><semantics><mrow><mi>f</mi><mo>\[</mo><mi>S</mi><mo>\]</mo></mrow><annotation>f\[S\]</annotation></semantics></math>f\[S\] 表示已经排列好的元素（下标）集合为 <math><semantics><mrow><mi>S</mi></mrow><annotation>S</annotation></semantics></math>S 时，和题目有关的最优值。通过枚举当前位置要填的元素来转移。

注意不需要额外的状态表示当前要填第几个位置，因为集合 <math><semantics><mrow><mi>S</mi></mrow><annotation>S</annotation></semantics></math>S 的大小就是当前要填的位置。

> 注：部分题目可以爆搜，难度分仅供参考。

* [526\. 优美的排列](https://leetcode.cn/problems/beautiful-arrangement/)
* [1879\. 两个数组最小的异或值之和](https://leetcode.cn/problems/minimum-xor-sum-of-two-arrays/) 2145
* [2850\. 将石头分散到网格图的最少移动次数](https://leetcode.cn/problems/minimum-moves-to-spread-stones-over-grid/)
* [1947\. 最大兼容性评分和](https://leetcode.cn/problems/maximum-compatibility-score-sum/)
* [1799\. N 次操作后的最大分数和](https://leetcode.cn/problems/maximize-score-after-n-operations/)
* [2172\. 数组的最大与和](https://leetcode.cn/problems/maximum-and-sum-of-array/) 2392
* [1066\. 校园自行车分配 II](https://leetcode.cn/problems/campus-bikes-ii/)（会员题）
* [2992\. 自整除排列的数量](https://leetcode.cn/problems/number-of-self-divisible-permutations/)（会员题）
* [2403\. 杀死所有怪物的最短时间](https://leetcode.cn/problems/minimum-time-to-kill-all-monsters/)（会员题）

### §9.2 排列型 ② 相邻相关

一般定义 <math><semantics><mrow><mi>f</mi><mo>\[</mo><mi>S</mi><mo>\]</mo><mo>\[</mo><mi>i</mi><mo>\]</mo></mrow><annotation>f\[S\]\[i\]</annotation></semantics></math>f\[S\]\[i\] 表示已经排列好的元素（下标）集合为 <math><semantics><mrow><mi>S</mi></mrow><annotation>S</annotation></semantics></math>S，且上一个填的元素（下标）为 <math><semantics><mrow><mi>i</mi></mrow><annotation>i</annotation></semantics></math>i 时，和题目有关的最优值。通过枚举当前位置要填的元素来转移。

* [996\. 正方形数组的数目](https://leetcode.cn/problems/number-of-squareful-arrays/) 1932
* [2741\. 特别的排列](https://leetcode.cn/problems/special-permutations/) 2021
* [1681\. 最小不兼容性](https://leetcode.cn/problems/minimum-incompatibility/) 2390

### §9.3 旅行商问题（TSP）

本质上就是排列型 ②。

* [943\. 最短超级串](https://leetcode.cn/problems/find-the-shortest-superstring/) 2186
* [847\. 访问所有节点的最短路径](https://leetcode.cn/problems/shortest-path-visiting-all-nodes/) 2201
* [LCP 13. 寻宝](https://leetcode.cn/problems/xun-bao/)
* [2247\. K 条高速公路的最大旅行费用](https://leetcode.cn/problems/maximum-cost-of-trip-with-k-highways/)（会员题）

### §9.4 枚举子集的子集

一般定义 <math><semantics><mrow><mi>f</mi><mo>\[</mo><mi>S</mi><mo>\]</mo></mrow><annotation>f\[S\]</annotation></semantics></math>f\[S\] 表示已选（或者未选）的子集为 <math><semantics><mrow><mi>S</mi></mrow><annotation>S</annotation></semantics></math>S 时，和题目有关的最优值。通过枚举 <math><semantics><mrow><mi>S</mi></mrow><annotation>S</annotation></semantics></math>S（或者 <math><semantics><mrow><mi>S</mi></mrow><annotation>S</annotation></semantics></math>S 的补集）的子集来转移。

* [2305\. 公平分发饼干](https://leetcode.cn/problems/fair-distribution-of-cookies/) 1887
* [1986\. 完成任务的最少工作时间段](https://leetcode.cn/problems/minimum-number-of-work-sessions-to-finish-the-tasks/) 1995
* [1494\. 并行课程 II](https://leetcode.cn/problems/parallel-courses-ii/) 2082
* [1723\. 完成所有工作的最短时间](https://leetcode.cn/problems/find-minimum-time-to-finish-all-jobs/) 2284
* [1655\. 分配重复整数](https://leetcode.cn/problems/distribute-repeating-integers/) 2307
* [1349\. 参加考试的最大学生数](https://leetcode.cn/problems/maximum-students-taking-exam/) 2386
* [1681\. 最小不兼容性](https://leetcode.cn/problems/minimum-incompatibility/) 2390 有 <math><semantics><mrow><mi>O</mi><mo>(</mo><msup><mi>n</mi><mn>2</mn></msup><mo>⋅</mo><msup><mn>2</mn><mi>n</mi></msup><mo>)</mo></mrow><annotation>\\mathcal{O}(n^2\\cdot 2^n)</annotation></semantics></math>O(n2⋅2n) 做法
* [2572\. 无平方子集计数](https://leetcode.cn/problems/count-the-number-of-square-free-subsets/) 2420
* [1994\. 好子集的数目](https://leetcode.cn/problems/the-number-of-good-subsets/) 2465
* [LCP 04. 覆盖](https://leetcode.cn/problems/broken-board-dominoes/)
* [LCP 53. 守护太空城](https://leetcode.cn/problems/EJvmW4/)
* [465\. 最优账单平衡](https://leetcode.cn/problems/optimal-account-balancing/)（会员题）
* [2152\. 穿过所有点的所需最少直线数量](https://leetcode.cn/problems/minimum-number-of-lines-to-cover-points/)（会员题）

### §9.5 其它状压 DP

* [698\. 划分为k个相等的子集](https://leetcode.cn/problems/partition-to-k-equal-sum-subsets/)
* [1411\. 给 N x 3 网格图涂色的方案数](https://leetcode.cn/problems/number-of-ways-to-paint-n-3-grid/) 1845
* [2002\. 两个回文子序列长度的最大乘积](https://leetcode.cn/problems/maximum-product-of-the-length-of-two-palindromic-subsequences/) 1869
* [1931\. 用三种不同颜色为网格涂色](https://leetcode.cn/problems/painting-a-grid-with-three-different-colors/) 2170
* [1125\. 最小的必要团队](https://leetcode.cn/problems/smallest-sufficient-team/) 2251
* [1434\. 每个人戴不同帽子的方案数](https://leetcode.cn/problems/number-of-ways-to-wear-different-hats-to-each-other/) 2273
* [691\. 贴纸拼词](https://leetcode.cn/problems/stickers-to-spell-word/)
* [1595\. 连通两组点的最小成本](https://leetcode.cn/problems/minimum-cost-to-connect-two-groups-of-points/) 2538
* [1815\. 得到新鲜甜甜圈的最多组数](https://leetcode.cn/problems/maximum-number-of-groups-getting-fresh-donuts/) 2559
* [1659\. 最大化网格幸福感](https://leetcode.cn/problems/maximize-grid-happiness/) 2655
* [LCP 69. Hello LeetCode!](https://leetcode.cn/problems/rMeRt2/)
* [LCP 76. 魔法棋盘](https://leetcode.cn/problems/1ybDKD/)
* [LCP 82. 万灵之树](https://leetcode.cn/problems/cnHoX6/)
* [351\. 安卓系统手势解锁](https://leetcode.cn/problems/android-unlock-patterns/)（会员题）
* [2184\. 建造坚实的砖墙的方法数](https://leetcode.cn/problems/number-of-ways-to-build-sturdy-brick-wall/)（会员题）
