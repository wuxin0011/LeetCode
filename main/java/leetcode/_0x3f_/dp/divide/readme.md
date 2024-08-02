
## 六、划分型 DP

### §6.1 判定能否划分

一般定义 <math><semantics><mrow><mi>f</mi><mo>\[</mo><mi>i</mi><mo>\]</mo></mrow><annotation>f\[i\]</annotation></semantics></math>f\[i\] 表示长为 <math><semantics><mrow><mi>i</mi></mrow><annotation>i</annotation></semantics></math>i 的前缀 <math><semantics><mrow><mi>a</mi><mo>\[</mo><mo>:</mo><mi>i</mi><mo>\]</mo></mrow><annotation>a\[:i\]</annotation></semantics></math>a\[:i\] 能否划分。

枚举最后一个子数组的左端点 <math><semantics><mrow><mi>L</mi></mrow><annotation>L</annotation></semantics></math>L，从 <math><semantics><mrow><mi>f</mi><mo>\[</mo><mi>L</mi><mo>\]</mo></mrow><annotation>f\[L\]</annotation></semantics></math>f\[L\] 转移到 <math><semantics><mrow><mi>f</mi><mo>\[</mo><mi>i</mi><mo>\]</mo></mrow><annotation>f\[i\]</annotation></semantics></math>f\[i\]，并考虑 <math><semantics><mrow><mi>a</mi><mo>\[</mo><mi>L</mi><mo>:</mo><mi>j</mi><mo>\]</mo></mrow><annotation>a\[L:j\]</annotation></semantics></math>a\[L:j\] 是否满足要求。

* [2369\. 检查数组是否存在有效划分](https://leetcode.cn/problems/check-if-there-is-a-valid-partition-for-the-array/) 1780
* [139\. 单词拆分](https://leetcode.cn/problems/word-break/)

### §6.2 计算划分个数

计算最少（最多）可以划分出的子数组个数、划分方案数等。

一般定义 <math><semantics><mrow><mi>f</mi><mo>\[</mo><mi>i</mi><mo>\]</mo></mrow><annotation>f\[i\]</annotation></semantics></math>f\[i\] 表示长为 <math><semantics><mrow><mi>i</mi></mrow><annotation>i</annotation></semantics></math>i 的前缀 <math><semantics><mrow><mi>a</mi><mo>\[</mo><mo>:</mo><mi>i</mi><mo>\]</mo></mrow><annotation>a\[:i\]</annotation></semantics></math>a\[:i\] 在题目约束下，分割出的最少（最多）子数组个数（或者定义成分割方案数）。

枚举最后一个子数组的左端点 <math><semantics><mrow><mi>L</mi></mrow><annotation>L</annotation></semantics></math>L，从 <math><semantics><mrow><mi>f</mi><mo>\[</mo><mi>L</mi><mo>\]</mo></mrow><annotation>f\[L\]</annotation></semantics></math>f\[L\] 转移到 <math><semantics><mrow><mi>f</mi><mo>\[</mo><mi>i</mi><mo>\]</mo></mrow><annotation>f\[i\]</annotation></semantics></math>f\[i\]，并考虑 <math><semantics><mrow><mi>a</mi><mo>\[</mo><mi>L</mi><mo>:</mo><mi>j</mi><mo>\]</mo></mrow><annotation>a\[L:j\]</annotation></semantics></math>a\[L:j\] 对最优解的影响。

* [132\. 分割回文串 II](https://leetcode.cn/problems/palindrome-partitioning-ii/)
* [2707\. 字符串中的额外字符](https://leetcode.cn/problems/extra-characters-in-a-string/) 1736
* [2767\. 将字符串分割为最少的美丽子字符串](https://leetcode.cn/problems/partition-string-into-minimum-beautiful-substrings/) 1865
* [91\. 解码方法](https://leetcode.cn/problems/decode-ways/)
* [639\. 解码方法 II](https://leetcode.cn/problems/decode-ways-ii/)
* [LCR 165. 解密数字](https://leetcode.cn/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/)
* [1416\. 恢复数组](https://leetcode.cn/problems/restore-the-array/) 1920
* [2472\. 不重叠回文子字符串的最大数目](https://leetcode.cn/problems/maximum-number-of-non-overlapping-palindrome-substrings/) 2013
* [1105\. 填充书架](https://leetcode.cn/problems/filling-bookcase-shelves/) 2014
* [2547\. 拆分数组的最小代价](https://leetcode.cn/problems/minimum-cost-to-split-an-array/) 2020
* [2430\. 对字母串可执行的最大删除数](https://leetcode.cn/problems/maximum-deletions-on-a-string/) 2102
* [2463\. 最小移动总距离](https://leetcode.cn/problems/minimum-total-distance-traveled/) 2454
* [2977\. 转换字符串的最小成本 II](https://leetcode.cn/problems/minimum-cost-to-convert-string-ii/) 2696
* [2052\. 将句子分隔成行的最低成本](https://leetcode.cn/problems/minimum-cost-to-separate-sentence-into-rows/)（会员题）
* [2464\. 有效分割中的最少子数组数目](https://leetcode.cn/problems/minimum-subarrays-in-a-valid-split/)（会员题）

### §6.3 约束划分个数

将数组分成（恰好/至多）<math><semantics><mrow><mi>k</mi></mrow><annotation>k</annotation></semantics></math>k 个连续子数组，计算与这些子数组有关的最优值。

一般定义 <math><semantics><mrow><mi>f</mi><mo>\[</mo><mi>i</mi><mo>\]</mo><mo>\[</mo><mi>j</mi><mo>\]</mo></mrow><annotation>f\[i\]\[j\]</annotation></semantics></math>f\[i\]\[j\] 表示将长为 <math><semantics><mrow><mi>j</mi></mrow><annotation>j</annotation></semantics></math>j 的前缀 <math><semantics><mrow><mi>a</mi><mo>\[</mo><mo>:</mo><mi>j</mi><mo>\]</mo></mrow><annotation>a\[:j\]</annotation></semantics></math>a\[:j\] 分成 <math><semantics><mrow><mi>i</mi></mrow><annotation>i</annotation></semantics></math>i 个连续子数组所得到的最优解。

枚举最后一个子数组的左端点 <math><semantics><mrow><mi>L</mi></mrow><annotation>L</annotation></semantics></math>L，从 <math><semantics><mrow><mi>f</mi><mo>\[</mo><mi>i</mi><mo>−</mo><mn>1</mn><mo>\]</mo><mo>\[</mo><mi>L</mi><mo>\]</mo></mrow><annotation>f\[i-1\]\[L\]</annotation></semantics></math>f\[i−1\]\[L\] 转移到 <math><semantics><mrow><mi>f</mi><mo>\[</mo><mi>i</mi><mo>\]</mo><mo>\[</mo><mi>j</mi><mo>\]</mo></mrow><annotation>f\[i\]\[j\]</annotation></semantics></math>f\[i\]\[j\]，并考虑 <math><semantics><mrow><mi>a</mi><mo>\[</mo><mi>L</mi><mo>:</mo><mi>j</mi><mo>\]</mo></mrow><annotation>a\[L:j\]</annotation></semantics></math>a\[L:j\] 对最优解的影响。

* [410\. 分割数组的最大值](https://leetcode.cn/problems/split-array-largest-sum/)
* [1043\. 分隔数组以得到最大和](https://leetcode.cn/problems/partition-array-for-maximum-sum/) 1916
* [1745\. 分割回文串 IV](https://leetcode.cn/problems/palindrome-partitioning-iv/) 1925
* [813\. 最大平均值和的分组](https://leetcode.cn/problems/largest-sum-of-averages/) 1937
* [1278\. 分割回文串 III](https://leetcode.cn/problems/palindrome-partitioning-iii/) 1979
* [1335\. 工作计划的最低难度](https://leetcode.cn/problems/minimum-difficulty-of-a-job-schedule/) 2035
* [1473\. 粉刷房子 III](https://leetcode.cn/problems/paint-house-iii/) 2056
* [1478\. 安排邮筒](https://leetcode.cn/problems/allocate-mailboxes/) 2190
* [1959\. K 次调整数组大小浪费的最小总空间](https://leetcode.cn/problems/minimum-total-space-wasted-with-k-resizing-operations/) 2310 \*转换
* [2478\. 完美分割的方案数](https://leetcode.cn/problems/number-of-beautiful-partitions/) 2344
* [3077\. K 个不相交子数组的最大能量值](https://leetcode.cn/problems/maximum-strength-of-k-disjoint-subarrays/) 2557
* [2911\. 得到 K 个半回文串的最少修改次数](https://leetcode.cn/problems/minimum-changes-to-make-k-semi-palindromes/) 2608

### §6.4 不相交区间

* [2830\. 销售利润最大化](https://leetcode.cn/problems/maximize-the-profit-as-the-salesman/) 1851
* [2008\. 出租车的最大盈利](https://leetcode.cn/problems/maximum-earnings-from-taxi/) 1872
* [1235\. 规划兼职工作](https://leetcode.cn/problems/maximum-profit-in-job-scheduling/) 2023
* [1751\. 最多可以参加的会议数目 II](https://leetcode.cn/problems/maximum-number-of-events-that-can-be-attended-ii/) 2041
