
## 八、区间 DP

讲解：[区间 DP](https://leetcode.cn/link/?target=https://www.bilibili.com/video/BV1Gs4y1E7EU/)

从数组的左右两端不断缩短，求解关于某段下标区间的最优值。

一般定义 <math><semantics><mrow><mi>f</mi><mo>\[</mo><mi>i</mi><mo>\]</mo><mo>\[</mo><mi>j</mi><mo>\]</mo></mrow><annotation>f\[i\]\[j\]</annotation></semantics></math>f\[i\]\[j\] 表示下标区间 <math><semantics><mrow><mo>\[</mo><mi>i</mi><mo>,</mo><mi>j</mi><mo>\]</mo></mrow><annotation>\[i,j\]</annotation></semantics></math>\[i,j\] 的最优值。

### §8.1 最长回文子序列

* [516\. 最长回文子序列](https://leetcode.cn/problems/longest-palindromic-subsequence/)
* [730\. 统计不同回文子序列](https://leetcode.cn/problems/count-different-palindromic-subsequences/)
* [1312\. 让字符串成为回文串的最少插入次数](https://leetcode.cn/problems/minimum-insertion-steps-to-make-a-string-palindrome/) 1787
* [1771\. 由子序列构造的最长回文串的长度](https://leetcode.cn/problems/maximize-palindrome-length-from-subsequences/) 2182
* [1682\. 最长回文子序列 II](https://leetcode.cn/problems/longest-palindromic-subsequence-ii/)（会员题）
* [1216\. 验证回文串 III](https://leetcode.cn/problems/valid-palindrome-iii/)（会员题）
* [1246\. 删除回文子数组](https://leetcode.cn/problems/palindrome-removal/)（会员题）

### §8.2 其它区间 DP

* [5\. 最长回文子串](https://leetcode.cn/problems/longest-palindromic-substring/)
* [3040\. 相同分数的最大操作数目 II](https://leetcode.cn/problems/maximum-number-of-operations-with-the-same-score-ii/) 1709
* [375\. 猜数字大小 II](https://leetcode.cn/problems/guess-number-higher-or-lower-ii/)
* [1130\. 叶值的最小代价生成树](https://leetcode.cn/problems/minimum-cost-tree-from-leaf-values/) 1919
* [96\. 不同的二叉搜索树](https://leetcode.cn/problems/unique-binary-search-trees/)
* [1770\. 执行乘法运算的最大分数](https://leetcode.cn/problems/maximum-score-from-performing-multiplication-operations/) 2068
* [1547\. 切棍子的最小成本](https://leetcode.cn/problems/minimum-cost-to-cut-a-stick/) 2116
* [1039\. 多边形三角剖分的最低得分](https://leetcode.cn/problems/minimum-score-triangulation-of-polygon/solution/shi-pin-jiao-ni-yi-bu-bu-si-kao-dong-tai-aty6/) 2130
* [1000\. 合并石头的最低成本](https://leetcode.cn/problems/minimum-cost-to-merge-stones/) 2423
* [2019\. 解出数学表达式的学生分数](https://leetcode.cn/problems/the-score-of-students-solving-math-expression/) 2584
* [87\. 扰乱字符串](https://leetcode.cn/problems/scramble-string/)
* [312\. 戳气球](https://leetcode.cn/problems/burst-balloons/)
* [664\. 奇怪的打印机](https://leetcode.cn/problems/strange-printer/)
* [546\. 移除盒子](https://leetcode.cn/problems/remove-boxes/) 同 CF1107E，可能是力扣上最难的 DP
* [471\. 编码最短长度的字符串](https://leetcode.cn/problems/encode-string-with-shortest-length/)（会员题）
* [3018\. 可处理的最大删除操作数 I](https://leetcode.cn/problems/maximum-number-of-removal-queries-that-can-be-processed-i/)（会员题）
