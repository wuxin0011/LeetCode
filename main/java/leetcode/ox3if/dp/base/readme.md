


## 前言

掌握动态规划（DP）是没有捷径的，咱们唯一能做的，就是投入时间猛猛刷题。好比学数学，只看书看视频而不做习题，是不能说学会的。

我能做的，是帮你节省找题的时间，并把这些题分类整理好。有着相同套路的题，一起做效率会更高，也更能领悟到 DP 的精髓。所以推荐按照专题刷。

题目已按照难度分排序（右侧数字为难度分）。如果遇到难度很大，题解都看不懂的题目，建议直接跳过，二刷的时候再来尝试。

## 一、入门 DP

![dp-2.jpg](https://pic.leetcode.cn/1710769845-JRnIfA-dp-2.jpg)

**记忆化搜索**是新手村神器（甚至可以用到游戏后期），推荐先看 [动态规划入门：从记忆化搜索到递推](https://leetcode.cn/link/?target=https://www.bilibili.com/video/BV1Xj411K7oF/)。

但记忆化搜索并不是万能的，某些题目只有写成递推，才能结合数据结构等来优化时间复杂度，多数题目还可以优化空间复杂度。所以尽量在写完记忆化搜索后，把递推的代码也写一下。熟练之后直接写递推也可以。

### §1.1 爬楼梯

* [70\. 爬楼梯](https://leetcode.cn/problems/climbing-stairs/)（[题解](https://leetcode.cn/problems/climbing-stairs/solution/jiao-ni-yi-bu-bu-si-kao-dong-tai-gui-hua-7zm1/)）
* [746\. 使用最小花费爬楼梯](https://leetcode.cn/problems/min-cost-climbing-stairs/)（[题解](https://leetcode.cn/problems/min-cost-climbing-stairs/solution/jiao-ni-yi-bu-bu-si-kao-dong-tai-gui-hua-j99e/)）
* [377\. 组合总和 Ⅳ](https://leetcode.cn/problems/combination-sum-iv/) 本质是爬楼梯，相当于每次往上爬 <math><semantics><mrow><mtext>nums</mtext><mo>\[</mo><mi>i</mi><mo>\]</mo></mrow><annotation>\\textit{nums}\[i\]</annotation></semantics></math>nums\[i\] 步
* [2466\. 统计构造好字符串的方案数](https://leetcode.cn/problems/count-ways-to-build-good-strings/) 1694
* [2266\. 统计打字方案数](https://leetcode.cn/problems/count-number-of-texts/) 1857
* [2533\. 好二进制字符串的数量](https://leetcode.cn/problems/number-of-good-binary-strings/)（会员题）

### §1.2 打家劫舍

* [198\. 打家劫舍](https://leetcode.cn/problems/house-robber/)
* [740\. 删除并获得点数](https://leetcode.cn/problems/delete-and-earn/)
* [2320\. 统计放置房子的方式数](https://leetcode.cn/problems/count-number-of-ways-to-place-houses/) 1608
* [213\. 打家劫舍 II](https://leetcode.cn/problems/house-robber-ii/)

### §1.3 最大子数组和（最大子段和）

有两种做法：

1. 定义状态 <math><semantics><mrow><mi>f</mi><mo>\[</mo><mi>i</mi><mo>\]</mo></mrow><annotation>f\[i\]</annotation></semantics></math>f\[i\] 表示以 <math><semantics><mrow><mi>a</mi><mo>\[</mo><mi>i</mi><mo>\]</mo></mrow><annotation>a\[i\]</annotation></semantics></math>a\[i\] 结尾的最大子数组和，不和 <math><semantics><mrow><mi>i</mi></mrow><annotation>i</annotation></semantics></math>i 左边拼起来就是 <math><semantics><mrow><mi>f</mi><mo>\[</mo><mi>i</mi><mo>\]</mo><mo>\=</mo><mi>a</mi><mo>\[</mo><mi>i</mi><mo>\]</mo></mrow><annotation>f\[i\]=a\[i\]</annotation></semantics></math>f\[i\]\=a\[i\]，和 <math><semantics><mrow><mi>i</mi></mrow><annotation>i</annotation></semantics></math>i 左边拼起来就是 <math><semantics><mrow><mi>f</mi><mo>\[</mo><mi>i</mi><mo>\]</mo><mo>\=</mo><mi>f</mi><mo>\[</mo><mi>i</mi><mo>−</mo><mn>1</mn><mo>\]</mo><mo>+</mo><mi>a</mi><mo>\[</mo><mi>i</mi><mo>\]</mo></mrow><annotation>f\[i\]=f\[i-1\]+a\[i\]</annotation></semantics></math>f\[i\]\=f\[i−1\]+a\[i\]，取最大值就得到了状态转移方程 <math><semantics><mrow><mi>f</mi><mo>\[</mo><mi>i</mi><mo>\]</mo><mo>\=</mo><mi>max</mi><mo>⁡</mo><mo>(</mo><mi>f</mi><mo>\[</mo><mi>i</mi><mtext>−</mtext><mn>1</mn><mo>\]</mo><mo>,</mo><mn>0</mn><mo>)</mo><mo>+</mo><mi>a</mi><mo>\[</mo><mi>i</mi><mo>\]</mo></mrow><annotation>f\[i\]=\\max(f\[i−1\],0)+a\[i\]</annotation></semantics></math>f\[i\]\=max(f\[i−1\],0)+a\[i\]，答案为 <math><semantics><mrow><mi>max</mi><mo>⁡</mo><mo>(</mo><mi>f</mi><mo>)</mo></mrow><annotation>\\max(f)</annotation></semantics></math>max(f)。这个做法也叫做 Kadane 算法。
2. 用前缀和解决，具体见 [题解](https://leetcode.cn/problems/maximum-subarray/solution/qian-zhui-he-zuo-fa-ben-zhi-shi-mai-mai-abu71/)。

* [53\. 最大子数组和](https://leetcode.cn/problems/maximum-subarray/)
* [2606\. 找到最大开销的子字符串](https://leetcode.cn/problems/find-the-substring-with-maximum-cost/) 1422
* [1749\. 任意子数组和的绝对值的最大值](https://leetcode.cn/problems/maximum-absolute-sum-of-any-subarray/) 1542
* [1191\. K 次串联后最大子数组之和](https://leetcode.cn/problems/k-concatenation-maximum-sum/) 1748
* [918\. 环形子数组的最大和](https://leetcode.cn/problems/maximum-sum-circular-subarray/) 1777
* [2321\. 拼接数组的最大分数](https://leetcode.cn/problems/maximum-score-of-spliced-array/) 1791
* [363\. 矩形区域不超过 K 的最大数值和](https://leetcode.cn/problems/max-sum-of-rectangle-no-larger-than-k/)

