![数学题单数学题目力扣数学题单leetcode数学数论组合博弈几何随机 灵茶山艾府 灵神](https://pic.leetcode.cn/1715418671-whQQdB-meme-math-bf-math.png)

> 图：暴力？NO！数学做法，降维打击！

## 前言

本文整理了力扣上的数学相关题目，主要以数论和组合数学为主。

部分题目（尤其是组合数学）会涉及到取模，我写了一篇教程，请看 [模运算的世界：当加减乘除遇上取模](https://leetcode.cn/circle/discuss/mDfnkW/)。

## 一、数论

### §1.1 判断质数

* [3115\. 质数的最大距离](https://leetcode.cn/problems/maximum-prime-difference/) 1294
* [2614\. 对角线上的质数](https://leetcode.cn/problems/prime-in-diagonal/) 1375
* [762\. 二进制表示中质数个计算置位](https://leetcode.cn/problems/prime-number-of-set-bits-in-binary-representation/) 1383
* [3044\. 出现频率最高的质数](https://leetcode.cn/problems/most-frequent-prime/) 1737
* [866\. 回文质数](https://leetcode.cn/problems/prime-palindrome/) 1938

### §1.2 预处理质数（筛质数）

[埃氏筛和欧拉筛的讲解](https://leetcode.cn/link/?target=https://www.bilibili.com/video/BV1H8411E7hn/)

* [204\. 计数质数](https://leetcode.cn/problems/count-primes/)
* [2761\. 和等于目标值的质数对](https://leetcode.cn/problems/prime-pairs-with-target-sum/) 1505
* [2523\. 范围内最接近的两个质数](https://leetcode.cn/problems/closest-prime-numbers-in-range/) 1650
* [2601\. 质数减法运算](https://leetcode.cn/problems/prime-subtraction-operation/) 1779

### §1.3 质因数分解

* [2521\. 数组乘积中的不同质因数数目](https://leetcode.cn/problems/distinct-prime-factors-of-product-of-array/) 1413
* [2507\. 使用质因数之和替换后可以取到的最小值](https://leetcode.cn/problems/smallest-value-after-replacing-with-sum-of-prime-factors/) 1500
* [2584\. 分割数组使乘积互质](https://leetcode.cn/problems/split-the-array-to-make-coprime-products/) 2159
* [2709\. 最大公约数遍历](https://leetcode.cn/problems/greatest-common-divisor-traversal/) 2172
* [2862\. 完全子集的最大元素和](https://leetcode.cn/problems/maximum-element-sum-of-a-complete-subset-of-indices/) 2292
* [2818\. 操作使得分最大](https://leetcode.cn/problems/apply-operations-to-maximize-score/) 2397
* [1998\. 数组的最大公因数排序](https://leetcode.cn/problems/gcd-sort-of-an-array/) 2429
* [1735\. 生成乘积数组的方案数](https://leetcode.cn/problems/count-ways-to-make-array-with-product/) 2500
* [2338\. 统计理想数组的数目](https://leetcode.cn/problems/count-the-number-of-ideal-arrays/) 2615

### §1.4 阶乘分解

* [172\. 阶乘后的零](https://leetcode.cn/problems/factorial-trailing-zeroes/)
* [793\. 阶乘函数后 K 个零](https://leetcode.cn/problems/preimage-size-of-factorial-zeroes-function/) 2100

### §1.5 因子

* [2427\. 公因子的数目](https://leetcode.cn/problems/number-of-common-factors/) 1172
* [1952\. 三除数](https://leetcode.cn/problems/three-divisors/) 1204
* [1492\. n 的第 k 个因子](https://leetcode.cn/problems/the-kth-factor-of-n/) 1232
* [507\. 完美数](https://leetcode.cn/problems/perfect-number/)
* [1390\. 四因数](https://leetcode.cn/problems/four-divisors/) 1478
* [1362\. 最接近的因数](https://leetcode.cn/problems/closest-divisors/) 1534
* [829\. 连续整数求和](https://leetcode.cn/problems/consecutive-numbers-sum/) 1694
* [952\. 按公因数计算最大组件大小](https://leetcode.cn/problems/largest-component-size-by-common-factor/) 2272
* [1627\. 带阈值的图连通性](https://leetcode.cn/problems/graph-connectivity-with-threshold/) 2221
* [2183\. 统计可以被 K 整除的下标对数目](https://leetcode.cn/problems/count-array-pairs-divisible-by-k/) 2246
* [2198\. 单因数三元组](https://leetcode.cn/problems/number-of-single-divisor-triplets/)（会员题）
* [625\. 最小因式分解](https://leetcode.cn/problems/minimum-factorization/)（会员题）
* [2847\. 给定数字乘积的最小数字](https://leetcode.cn/problems/smallest-number-with-given-digit-product/)（会员题）

### §1.6 最大公约数（GCD）

* [1979\. 找出数组的最大公约数](https://leetcode.cn/problems/find-greatest-common-divisor-of-array/) 1184
* [2807\. 在链表中插入最大公约数](https://leetcode.cn/problems/insert-greatest-common-divisors-in-linked-list/) 1279
* [914\. 卡牌分组](https://leetcode.cn/problems/x-of-a-kind-in-a-deck-of-cards/) 1371
* [1071\. 字符串的最大公因子](https://leetcode.cn/problems/greatest-common-divisor-of-strings/) 1398
* [2001\. 可互换矩形的组数](https://leetcode.cn/problems/number-of-pairs-of-interchangeable-rectangles/) 1436
* [2344\. 使数组可以被整除的最少删除次数](https://leetcode.cn/problems/minimum-deletions-to-make-array-divisible/) 1641
* [365\. 水壶问题](https://leetcode.cn/problems/water-and-jug-problem/)
* [858\. 镜面反射](https://leetcode.cn/problems/mirror-reflection/) 1881
* [1250\. 检查「好数组」](https://leetcode.cn/problems/check-if-it-is-a-good-array/) 1983
* [149\. 直线上最多的点数](https://leetcode.cn/problems/max-points-on-a-line/)
* [2607\. 使子数组元素和相等](https://leetcode.cn/problems/make-k-subarray-sums-equal/) 2071
* [2543\. 判断一个点是否可以到达](https://leetcode.cn/problems/check-if-point-is-reachable/) 2221
* [2436\. 使子数组最大公约数大于一的最小分割数](https://leetcode.cn/problems/minimum-split-into-subarrays-with-gcd-greater-than-one/)（会员题）

### §1.7 GCD 性质

* [2447\. 最大公因数等于 K 的子数组数目](https://leetcode.cn/problems/number-of-subarrays-with-gcd-equal-to-k/) 1603
* [2654\. 使数组所有元素变成 1 的最少操作次数](https://leetcode.cn/problems/minimum-number-of-operations-to-make-all-array-elements-equal-to-1/) 1929
* [2941\. 子数组的最大 GCD-Sum](https://leetcode.cn/problems/maximum-gcd-sum-of-a-subarray/)（会员题）

### §1.8 GCD 与因子

* [1819\. 序列中不同最大公约数的数目](https://leetcode.cn/problems/number-of-different-subsequences-gcds/) 2540
* [LCP 14. 切分数组](https://leetcode.cn/problems/qie-fen-shu-zu/)
* [2464\. 有效分割中的最少子数组数目](https://leetcode.cn/problems/minimum-subarrays-in-a-valid-split/)（会员题）

### §1.9 最小公倍数（LCM）

* [2413\. 最小偶倍数](https://leetcode.cn/problems/smallest-even-multiple/) 1145
* [2470\. 最小公倍数为 K 的子数组数目](https://leetcode.cn/problems/number-of-subarrays-with-lcm-equal-to-k/) 1560
* [2197\. 替换数组中的非互质数](https://leetcode.cn/problems/replace-non-coprime-numbers-in-array/) 2057

### §1.10 互质

* [2748\. 美丽下标对的数目](https://leetcode.cn/problems/number-of-beautiful-pairs/) 1301
* [1447\. 最简分数](https://leetcode.cn/problems/simplified-fractions/) ~1400
* [1766\. 互质树](https://leetcode.cn/problems/tree-of-coprimes/) 2232

### §1.11 同余

* [2453\. 摧毁一系列目标](https://leetcode.cn/problems/destroy-sequential-targets/) 1762
* [2598\. 执行操作后的最大 MEX](https://leetcode.cn/problems/smallest-missing-non-negative-integer-after-operations/) 1846
* [1590\. 使数组和能被 P 整除](https://leetcode.cn/problems/make-sum-divisible-by-p/) 2039

### §1.12 其它

* [326\. 3 的幂](https://leetcode.cn/problems/power-of-three/)
* [633\. 平方数之和](https://leetcode.cn/problems/sum-of-square-numbers/) 费马平方和定理
* [279\. 完全平方数](https://leetcode.cn/problems/perfect-squares/) 四平方和定理
* [1015\. 可被 K 整除的最小整数](https://leetcode.cn/problems/smallest-integer-divisible-by-k/) 欧拉定理、欧拉函数
* [2240\. 买钢笔和铅笔的方案数](https://leetcode.cn/problems/number-of-ways-to-buy-pens-and-pencils/) 类欧几里得算法
* [2221\. 数组的三角和](https://leetcode.cn/problems/find-triangular-sum-of-an-array/) 扩展卢卡斯

## 二、组合数学

### §2.1 乘法原理

* [2125\. 银行中的激光束数量](https://leetcode.cn/problems/number-of-laser-beams-in-a-bank/) 1280
* [3128\. 直角三角形](https://leetcode.cn/problems/right-triangles/) 1541
* [1573\. 分割字符串的方案数](https://leetcode.cn/problems/number-of-ways-to-split-a-string/) 1591
* [2750\. 将数组划分成若干好子数组的方式](https://leetcode.cn/problems/ways-to-split-array-into-good-subarrays/) 1598
* [2063\. 所有子字符串中的元音](https://leetcode.cn/problems/vowels-of-all-substrings/) 1663 贡献法
* [1922\. 统计好数字的数目](https://leetcode.cn/problems/count-good-numbers/) 1675
* [2147\. 分隔长廊的方案数](https://leetcode.cn/problems/number-of-ways-to-divide-a-long-corridor/) 1915
* [2963\. 统计好分割方案的数目](https://leetcode.cn/problems/count-the-number-of-good-partitions/) 1985
* [2867\. 统计树中的合法路径数目](https://leetcode.cn/problems/count-valid-paths-in-a-tree/) 2428
* [2450\. 应用操作后不同二进制字符串的数量](https://leetcode.cn/problems/number-of-distinct-binary-strings-after-applying-operations/)（会员题）

### §2.2 组合计数

* [62\. 不同路径](https://leetcode.cn/problems/unique-paths/)
* [1175\. 质数排列](https://leetcode.cn/problems/prime-arrangements/) 1489
* [1359\. 有效的快递序列数目](https://leetcode.cn/problems/count-all-valid-pickup-and-delivery-options/) 1723
* [2400\. 恰好移动 k 步到达某一位置的方法数目](https://leetcode.cn/problems/number-of-ways-to-reach-a-position-after-exactly-k-steps/) 1751
* [2514\. 统计同位异构字符串数目](https://leetcode.cn/problems/count-anagrams/) 2070
* [1643\. 第 K 条最小指令](https://leetcode.cn/problems/kth-smallest-instructions/) 2080
* [2842\. 统计一个字符串的 k 子序列美丽值最大的数目](https://leetcode.cn/problems/count-k-subsequences-of-a-string-with-maximum-beauty/) 2092
* [3154\. 到达第 K 级台阶的方案数](https://leetcode.cn/problems/find-number-of-ways-to-reach-the-k-th-stair/) ~2100
* [1569\. 将子数组重新排序得到同一个二叉搜索树的方案数](https://leetcode.cn/problems/number-of-ways-to-reorder-array-to-get-same-bst/) 2288
* [1866\. 恰有 K 根木棍可以看到的排列数目](https://leetcode.cn/problems/number-of-ways-to-rearrange-sticks-with-k-sticks-visible/) 2333
* [1467\. 两个盒子中球的颜色数相同的概率](https://leetcode.cn/problems/probability-of-a-two-boxes-having-the-same-number-of-distinct-balls/) 2357
* [1916\. 统计为蚁群构筑房间的不同顺序](https://leetcode.cn/problems/count-ways-to-build-rooms-in-an-ant-colony/) 2486
* [1830\. 使字符串有序的最少操作次数](https://leetcode.cn/problems/minimum-number-of-operations-to-make-string-sorted/) 2620
* [2954\. 统计感冒序列的数目](https://leetcode.cn/problems/count-the-number-of-infection-sequences/) 2645
* [1575\. 统计所有可行路径](https://leetcode.cn/problems/count-all-possible-routes/)
* [LCP 25. 古董键盘](https://leetcode.cn/problems/Uh984O/)
* [2539\. 好子序列的个数](https://leetcode.cn/problems/count-the-number-of-good-subsequences/)（会员题）
* [634\. 寻找数组的错位排列](https://leetcode.cn/problems/find-the-derangement-of-an-array/)（会员题）

### §2.3 放球问题

* [1641\. 统计字典序元音字符串的数目](https://leetcode.cn/problems/count-sorted-vowel-strings/) 1519
* [1621\. 大小为 K 的不重叠线段的数目](https://leetcode.cn/problems/number-of-sets-of-k-non-overlapping-line-segments/) 2198
* [920\. 播放列表的数量](https://leetcode.cn/problems/number-of-music-playlists/) 2400
* [1735\. 生成乘积数组的方案数](https://leetcode.cn/problems/count-ways-to-make-array-with-product/) 2500
* [2338\. 统计理想数组的数目](https://leetcode.cn/problems/count-the-number-of-ideal-arrays/) 2615

### §2.4 容斥原理

* [2652\. 倍数求和](https://leetcode.cn/problems/sum-multiples/)
* [878\. 第 N 个神奇数字](https://leetcode.cn/problems/nth-magical-number/) 1897
* [1201\. 丑数 III](https://leetcode.cn/problems/ugly-number-iii/) 2039
* [2929\. 给小朋友们分糖果 II](https://leetcode.cn/problems/distribute-candies-among-children-ii/)
* [2930\. 重新排列后包含指定子字符串的字符串数目](https://leetcode.cn/problems/number-of-strings-which-can-be-rearranged-to-contain-substring/)
* [2513\. 最小化两个数组中的最大值](https://leetcode.cn/problems/minimize-the-maximum-of-two-arrays/) 2302
* [3116\. 单面值组合的第 K 小金额](https://leetcode.cn/problems/kth-smallest-amount-with-single-denomination-combination/) 2387
* [3130\. 找出所有稳定的二进制数组 II](https://leetcode.cn/problems/find-all-possible-stable-binary-arrays-ii/) 2825
* [2927\. 给小朋友们分糖果 III](https://leetcode.cn/problems/distribute-candies-among-children-iii/)（会员题）

## 三、概率期望

* [1227\. 飞机座位分配概率](https://leetcode.cn/problems/airplane-seat-assignment-probability/)
* [688\. 骑士在棋盘上的概率](https://leetcode.cn/problems/knight-probability-in-chessboard/)
* [837\. 新 21 点](https://leetcode.cn/problems/new-21-game/) 2350
* [1467\. 两个盒子中球的颜色数相同的概率](https://leetcode.cn/problems/probability-of-a-two-boxes-having-the-same-number-of-distinct-balls/) 2357
* [808\. 分汤](https://leetcode.cn/problems/soup-servings/) 2397
* [LCR 185. 统计结果概率](https://leetcode.cn/problems/nge-tou-zi-de-dian-shu-lcof/)
* [LCP 11. 期望个数统计](https://leetcode.cn/problems/qi-wang-ge-shu-tong-ji/)
* [九坤-04. 筹码游戏](https://leetcode.cn/contest/ubiquant2022/problems/I3Gm2h/)
* [1230\. 抛掷硬币](https://leetcode.cn/problems/toss-strange-coins/)（会员题）

## 四、博弈论

* [292\. Nim 游戏](https://leetcode.cn/problems/nim-game/) 巴什博弈
* [1561\. 你可以获得的最大硬币数目](https://leetcode.cn/problems/maximum-number-of-coins-you-can-get/) 1406
* [1025\. 除数博弈](https://leetcode.cn/problems/divisor-game/) 1435
* [2038\. 如果相邻两个颜色均相同则删除当前颜色](https://leetcode.cn/problems/remove-colored-pieces-if-both-neighbors-are-the-same-color/) 1468
* [877\. 石子游戏](https://leetcode.cn/problems/stone-game/) 1590
* [1510\. 石子游戏 IV](https://leetcode.cn/problems/stone-game-iv/) 1787
* [486\. 预测赢家](https://leetcode.cn/problems/predict-the-winner/)
* [1690\. 石子游戏 VII](https://leetcode.cn/problems/stone-game-vii/) 1951
* [1686\. 石子游戏 VI](https://leetcode.cn/problems/stone-game-vi/) 2001
* [1927\. 求和游戏](https://leetcode.cn/problems/sum-game/) 2005
* [1406\. 石子游戏 III](https://leetcode.cn/problems/stone-game-iii/) 2027
* [1140\. 石子游戏 II](https://leetcode.cn/problems/stone-game-ii/) 2035
* [1563\. 石子游戏 V](https://leetcode.cn/problems/stone-game-v/) 2087
* [464\. 我能赢吗](https://leetcode.cn/problems/can-i-win/)
* [2029\. 石子游戏 IX](https://leetcode.cn/problems/stone-game-ix/) 2277
* [810\. 黑板异或游戏](https://leetcode.cn/problems/chalkboard-xor-game/) 2341
* [1872\. 石子游戏 VIII](https://leetcode.cn/problems/stone-game-viii/) 2440
* [913\. 猫和老鼠](https://leetcode.cn/problems/cat-and-mouse/) 2567
* [1728\. 猫和老鼠 II](https://leetcode.cn/problems/cat-and-mouse-ii/) 2849
* [294\. 翻转游戏 II](https://leetcode.cn/problems/flip-game-ii/)（会员题）
* [1908\. Nim 游戏 II](https://leetcode.cn/problems/game-of-nim/)（会员题）
* [2005\. 斐波那契树的移除子树游戏](https://leetcode.cn/problems/subtree-removal-game-with-fibonacci-tree/)（会员题）
* [2868\. 单词游戏](https://leetcode.cn/problems/the-wording-game/)（会员题）

## 五、计算几何

### §5.1 点、线

* [1232\. 缀点成线](https://leetcode.cn/problems/check-if-it-is-a-straight-line/) 1247
* [2280\. 表示一个折线图的最少线段数](https://leetcode.cn/problems/minimum-lines-to-represent-a-line-chart/) 1681
* [1610\. 可见点的最大数目](https://leetcode.cn/problems/maximum-number-of-visible-points/) 2147
* [面试题 16.03. 交点](https://leetcode.cn/problems/intersection-lcci/)
* [面试题 16.13. 平分正方形](https://leetcode.cn/problems/bisect-squares-lcci/)
* [面试题 16.14. 最佳直线](https://leetcode.cn/problems/best-line-lcci/)
* [LCP 37. 最小矩形面积](https://leetcode.cn/problems/zui-xiao-ju-xing-mian-ji/)
* [2152\. 穿过所有点的所需最少直线数量](https://leetcode.cn/problems/minimum-number-of-lines-to-cover-points/)（会员题）

### §5.2 圆

* [1401\. 圆和矩形是否有重叠](https://leetcode.cn/problems/circle-and-rectangle-overlapping/) 1709
* [1453\. 圆形靶内的最大飞镖数量](https://leetcode.cn/problems/maximum-number-of-darts-inside-of-a-circular-dartboard/) 2202
* [LCP 42. 玩具套圈](https://leetcode.cn/problems/vFjcfV/)
* [1924\. 安装栅栏 II](https://leetcode.cn/problems/erect-the-fence-ii/)（会员题）最小圆覆盖 Welzl 算法

### §5.3 矩形、多边形

* [836\. 矩形重叠](https://leetcode.cn/problems/rectangle-overlap/) 1443
* [223\. 矩形面积](https://leetcode.cn/problems/rectangle-area/)
* [593\. 有效的正方形](https://leetcode.cn/problems/valid-square/)
* [939\. 最小面积矩形](https://leetcode.cn/problems/minimum-area-rectangle/) 1752
* [963\. 最小面积矩形 II](https://leetcode.cn/problems/minimum-area-rectangle-ii/) 1991
* [469\. 凸多边形](https://leetcode.cn/problems/convex-polygon/)（会员题）

### §5.4 凸包

* [587\. 安装栅栏](https://leetcode.cn/problems/erect-the-fence/)
* [LCP 15. 游乐园的迷宫](https://leetcode.cn/problems/you-le-yuan-de-mi-gong/)

## 六、随机算法

* [398\. 随机数索引](https://leetcode.cn/problems/random-pick-index/)
* [382\. 链表随机节点](https://leetcode.cn/problems/linked-list-random-node/)
* [384\. 打乱数组](https://leetcode.cn/problems/shuffle-an-array/)
* [470\. 用 Rand7() 实现 Rand10()](https://leetcode.cn/problems/implement-rand10-using-rand7/)
* [528\. 按权重随机选择](https://leetcode.cn/problems/random-pick-with-weight/)
* [710\. 黑名单中的随机数](https://leetcode.cn/problems/random-pick-with-blacklist/)
* [478\. 在圆内随机生成点](https://leetcode.cn/problems/generate-random-point-in-a-circle/)
* [497\. 非重叠矩形中的随机点](https://leetcode.cn/problems/random-point-in-non-overlapping-rectangles/)
* [519\. 随机翻转矩阵](https://leetcode.cn/problems/random-flip-matrix/)
* [380\. O(1) 时间插入、删除和获取随机元素](https://leetcode.cn/problems/insert-delete-getrandom-o1/)
* [381\. O(1) 时间插入、删除和获取随机元素 - 允许重复](https://leetcode.cn/problems/insert-delete-getrandom-o1-duplicates-allowed/)
* [1515\. 服务中心的最佳位置](https://leetcode.cn/problems/best-position-for-a-service-centre/) 梯度下降法

## 七、杂项

### §7.1 回文数

* [9\. 回文数](https://leetcode.cn/problems/palindrome-number/)
* [2396\. 严格回文的数字](https://leetcode.cn/problems/strictly-palindromic-number/) 1329
* [2217\. 找到指定长度的回文数](https://leetcode.cn/problems/find-palindrome-with-fixed-length/) 1822
* [866\. 回文质数](https://leetcode.cn/problems/prime-palindrome/) 1938
* [2967\. 使数组成为等数数组的最小代价](https://leetcode.cn/problems/minimum-cost-to-make-array-equalindromic/) 2116
* [906\. 超级回文数](https://leetcode.cn/problems/super-palindromes/) 2140
* [2081\. k 镜像数字的和](https://leetcode.cn/problems/sum-of-k-mirror-numbers/) 2210
* [564\. 寻找最近的回文数](https://leetcode.cn/problems/find-the-closest-palindrome/)
* [479\. 最大回文数乘积](https://leetcode.cn/problems/largest-palindrome-product/)

### §7.2 整数拆分

* [343\. 整数拆分](https://leetcode.cn/problems/integer-break/)
* [1808\. 好因子的最大数目](https://leetcode.cn/problems/maximize-number-of-nice-divisors/) 2070

### §7.3 曼哈顿距离

* [1131\. 绝对值表达式的最大值](https://leetcode.cn/problems/maximum-of-absolute-value-expression/) 2059
* [3102\. 最小化曼哈顿距离](https://leetcode.cn/problems/minimize-manhattan-distances/) 2216
* [1330\. 翻转子数组得到最大的数组值](https://leetcode.cn/problems/reverse-subarray-to-maximize-array-value/) 2482
* [1956\. 感染 K 种病毒所需的最短时间](https://leetcode.cn/problems/minimum-time-for-k-virus-variants-to-spread/)（会员题）
* [2613\. 美数对](https://leetcode.cn/problems/beautiful-pairs/)（会员题）

### §7.4 其它

* [2579\. 统计染色格子数](https://leetcode.cn/problems/count-total-number-of-colored-cells/) 1356
* [2834\. 找出美丽数组的最小和](https://leetcode.cn/problems/find-the-minimum-possible-sum-of-a-beautiful-array/) 1409
* [1414\. 和为 K 的最少斐波那契数字数目](https://leetcode.cn/problems/find-the-minimum-number-of-fibonacci-numbers-whose-sum-is-k/) 1466
* [1780\. 判断一个数字是否可以表示成三的幂的和](https://leetcode.cn/problems/check-if-number-is-a-sum-of-powers-of-three/) 1506
* [3091\. 执行操作使数据元素之和大于等于 K](https://leetcode.cn/problems/apply-operations-to-make-sum-of-array-greater-than-or-equal-to-k/) 1522
* [2541\. 使数组中所有元素相等的最小操作数 II](https://leetcode.cn/problems/minimum-operations-to-make-array-equal-ii/) 1620
* [2195\. 向数组中追加 K 个整数](https://leetcode.cn/problems/append-k-integers-with-minimal-sum/) 1659
* [2457\. 美丽整数的最小增量](https://leetcode.cn/problems/minimum-addition-to-make-integer-beautiful/) 1680
* [1017\. 负二进制转换](https://leetcode.cn/problems/convert-to-base-2/) 1698
* [1954\. 收集足够苹果的最小花园周长](https://leetcode.cn/problems/minimum-garden-perimeter-to-collect-enough-apples/) 1759
* [1073\. 负二进制数相加](https://leetcode.cn/problems/adding-two-negabinary-numbers/) 1807
* [1823\. 找出游戏的获胜者](https://leetcode.cn/problems/find-the-winner-of-the-circular-game/) 约瑟夫环
* [166\. 分数到小数](https://leetcode.cn/problems/fraction-to-recurring-decimal/) 有数学做法，可以不用哈希表
* [3012\. 通过操作使数组长度最小](https://leetcode.cn/problems/minimize-length-of-array-using-operations/) 1833
* [483\. 最小好进制](https://leetcode.cn/problems/smallest-good-base/)
* [972\. 相等的有理数](https://leetcode.cn/problems/equal-rational-numbers/) 2121
* [1862\. 向下取整数对和](https://leetcode.cn/problems/sum-of-floored-pairs/) 2170 调和级数枚举
* [1739\. 放置盒子](https://leetcode.cn/problems/building-boxes/) 2198
* [2443\. 反转之后的数字和](https://leetcode.cn/problems/sum-of-number-and-its-reverse/) 非暴力做法
* [1806\. 还原排列的最少操作步数](https://leetcode.cn/problems/minimum-number-of-operations-to-reinitialize-a-permutation/) 非暴力做法
* [458\. 可怜的小猪](https://leetcode.cn/problems/poor-pigs/)
* [60\. 排列序列](https://leetcode.cn/problems/permutation-sequence/)
* [2117\. 一个区间内所有数乘积的缩写](https://leetcode.cn/problems/abbreviating-the-product-of-a-range/) 2477
* [LCP 02. 分式化简](https://leetcode.cn/problems/deep-dark-fraction/) 连分数
* [LCP 29. 乐团站位](https://leetcode.cn/problems/SNJvJP/)
* [LCP 70. 沙地治理](https://leetcode.cn/problems/XxZZjK/)
* [660\. 移除 9](https://leetcode.cn/problems/remove-9/)（会员题）
* [2979\. 最贵的无法购买的商品](https://leetcode.cn/problems/most-expensive-item-that-can-not-be-bought/)（会员题）
* [2647\. 把三角形染成红色](https://leetcode.cn/problems/color-the-triangle-red/)（会员题）
