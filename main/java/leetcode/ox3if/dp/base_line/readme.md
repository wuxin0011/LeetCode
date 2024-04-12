
## 四、经典线性 DP

### §4.1 最长公共子序列（LCS）

讲解：[最长公共子序列 编辑距离](https://leetcode.cn/link/?target=https://www.bilibili.com/video/BV1TM4y1o7ug/)

一般定义 <math><semantics><mrow><mi>f</mi><mo>\[</mo><mi>i</mi><mo>\]</mo><mo>\[</mo><mi>j</mi><mo>\]</mo></mrow><annotation>f\[i\]\[j\]</annotation></semantics></math>f\[i\]\[j\] 表示对 <math><semantics><mrow><mo>(</mo><mi>s</mi><mo>\[</mo><mo>:</mo><mi>i</mi><mo>\]</mo><mo>,</mo><mi>t</mi><mo>\[</mo><mo>:</mo><mi>j</mi><mo>\]</mo><mo>)</mo></mrow><annotation>(s\[:i\],t\[:j\])</annotation></semantics></math>(s\[:i\],t\[:j\]) 的求解结果。

* [1143\. 最长公共子序列](https://leetcode.cn/problems/longest-common-subsequence/)
* [583\. 两个字符串的删除操作](https://leetcode.cn/problems/delete-operation-for-two-strings/)
* [712\. 两个字符串的最小 ASCII 删除和](https://leetcode.cn/problems/minimum-ascii-delete-sum-for-two-strings/)
* [72\. 编辑距离](https://leetcode.cn/problems/edit-distance/)
* [97\. 交错字符串](https://leetcode.cn/problems/interleaving-string/)
* [115\. 不同的子序列](https://leetcode.cn/problems/distinct-subsequences/)
* [1035\. 不相交的线](https://leetcode.cn/problems/uncrossed-lines/) 1806
* [1458\. 两个子序列的最大点积](https://leetcode.cn/problems/max-dot-product-of-two-subsequences/) 1824
* [1092\. 最短公共超序列](https://leetcode.cn/problems/shortest-common-supersequence/) 1977
* [1639\. 通过给定词典构造目标字符串的方案数](https://leetcode.cn/problems/number-of-ways-to-form-a-target-string-given-a-dictionary/) 2082
* [44\. 通配符匹配](https://leetcode.cn/problems/wildcard-matching/)
* [10\. 正则表达式匹配](https://leetcode.cn/problems/regular-expression-matching/)

### §4.2 最长递增子序列（LIS）

讲解：[最长递增子序列](https://leetcode.cn/link/?target=https://www.bilibili.com/video/BV1ub411Q7sB/)

做法有很多：

1. 枚举选哪个（见讲解）。
2. 二分贪心（见讲解）。
3. 计算 <math><semantics><mrow><mi>a</mi></mrow><annotation>a</annotation></semantics></math>a 和把 <math><semantics><mrow><mi>a</mi></mrow><annotation>a</annotation></semantics></math>a 排序后的数组 <math><semantics><mrow><mtext>sortedA</mtext></mrow><annotation>\\textit{sortedA}</annotation></semantics></math>sortedA 的最长公共子序列。
4. 数据结构优化（见 2407 题）。

* [300\. 最长递增子序列](https://leetcode.cn/problems/longest-increasing-subsequence/)
* [673\. 最长递增子序列的个数](https://leetcode.cn/problems/number-of-longest-increasing-subsequence/)
* [2826\. 将三个组排序](https://leetcode.cn/problems/sorting-three-groups/) 1721
* [1671\. 得到山形数组的最少删除次数](https://leetcode.cn/problems/minimum-number-of-removals-to-make-mountain-array/) 1913
* [1964\. 找出到每个位置为止最长的有效障碍赛跑路线](https://leetcode.cn/problems/find-the-longest-valid-obstacle-course-at-each-position/) 1933
* [1626\. 无矛盾的最佳球队](https://leetcode.cn/problems/best-team-with-no-conflicts/) 2027
* [354\. 俄罗斯套娃信封问题](https://leetcode.cn/problems/russian-doll-envelopes/)（二维 LIS）
* [1691\. 堆叠长方体的最大高度](https://leetcode.cn/problems/maximum-height-by-stacking-cuboids/) 2172
* [960\. 删列造序 III](https://leetcode.cn/problems/delete-columns-to-make-sorted-iii/) 2247
* [2407\. 最长递增子序列 II](https://leetcode.cn/problems/longest-increasing-subsequence-ii/) 2280
* [1187\. 使数组严格递增](https://leetcode.cn/problems/make-array-strictly-increasing/) 2316
* [1713\. 得到子序列的最少操作次数](https://leetcode.cn/problems/minimum-operations-to-make-a-subsequence/) 2351

**思维扩展**：

* [368\. 最大整除子集](https://leetcode.cn/problems/largest-divisible-subset/)

**思考题**：

给定整数 <math><semantics><mrow><mi>k</mi></mrow><annotation>k</annotation></semantics></math>k，构造一个数组 <math><semantics><mrow><mi>a</mi></mrow><annotation>a</annotation></semantics></math>a，使得 <math><semantics><mrow><mi>a</mi></mrow><annotation>a</annotation></semantics></math>a 恰好有 <math><semantics><mrow><mi>k</mi></mrow><annotation>k</annotation></semantics></math>k 个最长递增子序列。

[解答（评论）](https://leetcode.cn/problems/number-of-longest-increasing-subsequence/description/comments/2218054)
