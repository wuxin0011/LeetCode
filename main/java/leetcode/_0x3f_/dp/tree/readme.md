
## 十二、树形 DP

注：可能有同学觉得树形 DP 没有重复访问同一个状态（重叠子问题），并不能算作 DP，而是算作普通的递归。这么说也有一定道理，不过考虑到思维方式和 DP 是一样的自底向上，所以仍然叫做树形 DP。此外，如果是自顶向下的递归做法，是存在重叠子问题的，一般要结合记忆化搜索实现。

### §12.1 树的直径

讲解：[树形 DP：树的直径](https://leetcode.cn/link/?target=https://www.bilibili.com/video/BV17o4y187h1/)

* [543\. 二叉树的直径](https://leetcode.cn/problems/diameter-of-binary-tree/)
* [124\. 二叉树中的最大路径和](https://leetcode.cn/problems/binary-tree-maximum-path-sum/)
* [687\. 最长同值路径](https://leetcode.cn/problems/longest-univalue-path/)
* [2246\. 相邻字符不同的最长路径](https://leetcode.cn/problems/longest-path-with-different-adjacent-characters/) 2126
* [1617\. 统计子树中城市之间最大距离](https://leetcode.cn/problems/count-subtrees-with-max-distance-between-cities/) 2309
* [2538\. 最大价值和与最小价值和的差值](https://leetcode.cn/problems/difference-between-maximum-and-minimum-price-sum/) 2398
* [1245\. 树的直径](https://leetcode-cn.com/problems/tree-diameter/)（会员题）

> 注：求直径也有两次 DFS 的做法。

### §12.2 树上最大独立集

讲解：[树形 DP：打家劫舍III](https://leetcode.cn/link/?target=https://www.bilibili.com/video/BV1vu4y1f7dn/)

* [337\. 打家劫舍 III](https://leetcode.cn/problems/house-robber-iii/)（没有上司的舞会）
* [2646\. 最小化旅行的价格总和](https://leetcode.cn/problems/minimize-the-total-price-of-the-trips/) 2238
* [2378\. 选择边来最大化树的得分](https://leetcode.cn/problems/choose-edges-to-maximize-score-in-a-tree/)（会员题）

### §12.3 树上最小支配集

讲解：[树形 DP：监控二叉树](https://leetcode.cn/link/?target=https://www.bilibili.com/video/BV1oF411U7qL/)，包含 968 的变形题。

* [968\. 监控二叉树](https://leetcode.cn/problems/binary-tree-cameras/) 2124

### §12.4 换根 DP

也叫二次扫描法。

[【图解】一张图秒懂换根 DP！](https://leetcode.cn/problems/sum-of-distances-in-tree/solution/tu-jie-yi-zhang-tu-miao-dong-huan-gen-dp-6bgb/)

* [834\. 树中距离之和](https://leetcode.cn/problems/sum-of-distances-in-tree/) 2197
* [2581\. 统计可能的树根数目](https://leetcode.cn/problems/count-number-of-possible-root-nodes/) 2228
* [2858\. 可以到达每一个节点的最少边反转次数](https://leetcode.cn/problems/minimum-edge-reversals-so-every-node-is-reachable/) 2295
* [310\. 最小高度树](https://leetcode.cn/problems/minimum-height-trees/) 也可以用拓扑排序做

### §12.5 其它树形 DP

* [2925\. 在树上执行操作以后得到的最大分数](https://leetcode.cn/problems/maximum-score-after-applying-operations-on-a-tree/) 1940
* [3068\. 最大节点价值之和](https://leetcode.cn/problems/find-the-maximum-sum-of-node-values/) 2268
* [2920\. 收集所有金币可获得的最大积分](https://leetcode.cn/problems/maximum-points-after-collecting-coins-from-all-nodes/) 2351
* [2867\. 统计树中的合法路径数目](https://leetcode.cn/problems/count-valid-paths-in-a-tree/) 2428
* [1916\. 统计为蚁群构筑房间的不同顺序](https://leetcode.cn/problems/count-ways-to-build-rooms-in-an-ant-colony/) 2486
* [LCP 10. 二叉树任务调度](https://leetcode.cn/problems/er-cha-shu-ren-wu-diao-du/)
* [LCP 34. 二叉树染色](https://leetcode.cn/problems/er-cha-shu-ran-se-UGC/)
* [LCP 64. 二叉树灯饰](https://leetcode.cn/problems/U7WvvU/)
* [2313\. 二叉树中得到结果所需的最少翻转次数](https://leetcode.cn/problems/minimum-flips-in-binary-tree-to-get-result/)（会员题）
