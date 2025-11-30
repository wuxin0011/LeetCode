# 模板整理与灵神数据结构题单

[原文链接](https://leetcode.cn/discuss/post/3583665/fen-xiang-gun-ti-dan-chang-yong-shu-ju-j-bvmv/)

## 懒删除堆


```java
// 模板来源 https://leetcode.cn/circle/discuss/mOr1u6/

// 最小堆 LazyHeap minPQ = new LazyHeap(Integer::compare);

// 最大堆 LazyHeap maxPQ = new LazyHeap((a, b) -> Integer.compare(b, a));

class LazyHeap extends PriorityQueue<Integer> {
    private final Map<Integer, Integer> removeCnt = new HashMap<>(); // 每个元素剩余需要删除的次数
    private int size = 0; // 堆的实际大小
    public LazyHeap(Comparator<Integer> comparator) {
        super(comparator);
    }
    // 删除堆中所有应该删除的元素后，堆的实际大小
    public int size() {
        return size;
    }
    // 删除
    public void remove(int x) {
        removeCnt.merge(x, 1, Integer::sum); // 懒删除
        size--;
    }
    // 正式执行删除操作
    private void applyRemove() {
        while (removeCnt.getOrDefault(peek(), 0) > 0) {
            removeCnt.merge(poll(), -1, Integer::sum);
        }
    }
    // 查看堆顶
    public int top() {
        applyRemove();
        return peek(); // 真正的堆顶
    }
    // 出堆
    public int pop() {
        applyRemove();
        size--;
        return poll();
    }
    // 入堆
    public void push(int x) {
        int c = removeCnt.getOrDefault(x, 0);
        if (c > 0) {
            removeCnt.put(x, c - 1); // 抵消之前的删除
        } else {
            offer(x);
        }
        size++;
    }

}

//作者：灵茶山艾府
//链接：https://leetcode.cn/discuss/post/3583665/fen-xiang-gun-ti-dan-chang-yong-shu-ju-j-bvmv/
//来源：力扣（LeetCode）
//著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
```


## 并查集

```java
// 模板来源 https://leetcode.cn/circle/discuss/mOr1u6/

class UnionFind {
    private final int[] fa; // 代表元
    private final int[] size; // 集合大小
    public int cc; // 连通块个数
    UnionFind(int n) {
        fa = new int[n];
        for (int i = 0; i < n; i++) {
            fa[i] = i;
        }
        size = new int[n];
        Arrays.fill(size, 1);
        cc = n;
    }
    public int find(int x) {
        if (fa[x] != x) {
            fa[x] = find(fa[x]); // fa 改成代表元
        }
        return fa[x];
    }
    public boolean isSame(int x, int y) {
        return find(x) == find(y);
    }
    public boolean merge(int from, int to) {
        int x = find(from);
        int y = find(to);
        if (x == y) { // from 和 to 在同一个集合，不做合并
            return false;
        }
        fa[x] = y; // 合并集合。修改后就可以认为 from 和 to 在同一个集合了
        size[y] += size[x]; // 更新集合大小（注意集合大小保存在代表元上）
        // 无需更新 size[x]，因为我们不用 size[x] 而是用 size[find(x)] 获取集合大小，但 find(x) == y，我们不会再访问 size[x]
        cc--; // 成功合并，连通块个数减一
        return true;
    }
    // 返回 x 所在集合的大小
    public int getSize(int x) {
        return size[find(x)]; // 集合大小保存在代表元上
    }
}
//作者：灵茶山艾府
//链接：https://leetcode.cn/discuss/post/3583665/fen-xiang-gun-ti-dan-chang-yong-shu-ju-j-bvmv/
//来源：力扣（LeetCode）
//著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
```


### 带权并查集

```java
// 模板来源 https://leetcode.cn/circle/discuss/mOr1u6/
class UnionFind {
    private final int[] fa; // 代表元
    private final int[] dis; // dis[x] 表示 x 到（x 所在集合的）代表元的距离
    // 注意数据范围，必要时使用 long[] dis
    public UnionFind(int n) {
        // 一开始有 n 个集合 {0}, {1}, ..., {n-1}
        // 集合 i 的代表元是自己，自己到自己的距离是 0
        fa = new int[n];
        dis = new int[n];
        for (int i = 0; i < n; i++) {
            fa[i] = i;
        }
    }
    // 返回 x 所在集合的代表元
    // 同时做路径压缩
    public int find(int x) {
        if (fa[x] != x) {
            int root = find(fa[x]);
            dis[x] += dis[fa[x]]; // 递归更新 x 到其代表元的距离
            fa[x] = root;
        }
        return fa[x];
    }
    // 判断 x 和 y 是否在同一个集合（同普通并查集）
    public boolean same(int x, int y) {
        return find(x) == find(y);
    }
    // 计算从 from 到 to 的相对距离
    // 调用时需保证 from 和 to 在同一个集合中，否则返回值无意义
    public int getRelativeDistance(int from, int to) {
        find(from);
        find(to);
        // to-from = (x-from) - (x-to) = dis[from] - dis[to]
        return dis[from] - dis[to];
    }
    // 合并 from 和 to，新增信息 to - from = value
    // 其中 to 和 from 表示未知量，下文的 x 和 y 也表示未知量
    // 如果 from 和 to 不在同一个集合，返回 true，否则返回是否与已知信息矛盾
    public boolean merge(int from, int to, int value) {
        int x = find(from), y = find(to);
        if (x == y) { // from 和 to 在同一个集合，不做合并
            // to-from = (x-from) - (x-to) = dis[from] - dis[to] = value
            return dis[from] - dis[to] == value;
        }
        //    x --------- y
        //   /           /
        // from ------- to
        // 已知 x-from = dis[from] 和 y-to = dis[to]，现在合并 from 和 to，新增信息 to-from = value
        // 由于 y-from = (y-x) + (x-from) = (y-to) + (to-from)
        // 所以 y-x = (to-from) + (y-to) - (x-from) = value + dis[to] - dis[from]
        dis[x] = value + dis[to] - dis[from]; // 计算 x 到其代表元 y 的距离
        fa[x] = y;
        return true;
    }
}

//作者：灵茶山艾府
//链接：https://leetcode.cn/discuss/post/3583665/fen-xiang-gun-ti-dan-chang-yong-shu-ju-j-bvmv/
//来源：力扣（LeetCode）
//著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
```


## 树状数组


```java
// 模板来源 https://leetcode.cn/circle/discuss/mOr1u6/

class FenwickTree {
    private final long[] tree; // 如果计算结果没有超出 int 范围，可以改成 int
    public FenwickTree(int n) {
        tree = new long[n + 1]; // 使用下标 1 到 n
    }
    // a[i] 增加 val
    // 1 <= i <= n
    // 时间复杂度 O(log n)
    public void update(int i, long val) {
        for (; i < tree.length; i += i & -i) {
            tree[i] += val;
        }
    }
    // 求前缀和 a[1] + ... + a[i]
    // 1 <= i <= n
    // 时间复杂度 O(log n)
    public long pre(int i) {
        long res = 0;
        for (; i > 0; i &= i - 1) {
            res += tree[i];
        }
        return res;
    }
    // 求区间和 a[l] + ... + a[r]
    // 1 <= l <= r <= n
    // 时间复杂度 O(log n)
    public long query(int l, int r) {
        if (r < l) {
            return 0;
        }
        return pre(r) - pre(l - 1);
    }
}
//作者：灵茶山艾府
//链接：https://leetcode.cn/discuss/post/3583665/fen-xiang-gun-ti-dan-chang-yong-shu-ju-j-bvmv/
//来源：力扣（LeetCode）
//著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
```


## 线段树


### 段树（无区间更新）
```java
// 模板来源 https://leetcode.cn/circle/discuss/mOr1u6/
// 线段树有两个下标，一个是线段树节点的下标，另一个是线段树维护的区间的下标
// 节点的下标：从 1 开始，如果你想改成从 0 开始，需要把左右儿子下标分别改成 node*2+1 和 node*2+2
// 区间的下标：从 0 开始
class SegmentTree {
    private final int n;
    private final long[] tree; // 如果计算结果没有超出 int 范围，可以改成 int
    // 合并两个 val
    private long mergeVal(long a, long b) {
        return Math.max(a, b); // **根据题目修改**
    }
    // 线段树维护一个长为 n 的数组（下标从 0 到 n-1），元素初始值为 initVal
    public SegmentTree(int n, long initVal) {
        this.n = n;
        long[] a = new long[n];
        Arrays.fill(a, initVal);
        tree = new long[2 << (32 - Integer.numberOfLeadingZeros(n - 1))];
        build(a, 1, 0, n - 1);
    }
    // 线段树维护数组 a
    public SegmentTree(long[] a) {
        n = a.length;
        tree = new long[2 << (32 - Integer.numberOfLeadingZeros(n - 1))];
        build(a, 1, 0, n - 1);
    }
    // 更新 a[i] 为 mergeVal(a[i], val)
    // 时间复杂度 O(log n)
    public void update(int i, long val) {
        update(1, 0, n - 1, i, val);
    }
    // 返回用 mergeVal 合并所有 a[i] 的计算结果，其中 i 在闭区间 [ql, qr] 中
    // 时间复杂度 O(log n)
    public long query(int ql, int qr) {
        return query(1, 0, n - 1, ql, qr);
    }
    // 获取 a[i] 的值
    // 时间复杂度 O(log n)
    public long get(int i) {
        return query(1, 0, n - 1, i, i);
    }
    // 合并左右儿子的 val 到当前节点的 val
    private void maintain(int node) {
        tree[node] = mergeVal(tree[node * 2], tree[node * 2 + 1]);
    }
    // 用 a 初始化线段树
    // 时间复杂度 O(n)
    private void build(long[] a, int node, int l, int r) {
        if (l == r) { // 叶子
            tree[node] = a[l]; // 初始化叶节点的值
            return;
        }
        int m = (l + r) / 2;
        build(a, node * 2, l, m); // 初始化左子树
        build(a, node * 2 + 1, m + 1, r); // 初始化右子树
        maintain(node);
    }
    private void update(int node, int l, int r, int i, long val) {
        if (l == r) { // 叶子（到达目标）
            // 如果想直接替换的话，可以写 tree[node] = val
            tree[node] = mergeVal(tree[node], val);
            return;
        }
        int m = (l + r) / 2;
        if (i <= m) { // i 在左子树
            update(node * 2, l, m, i, val);
        } else { // i 在右子树
            update(node * 2 + 1, m + 1, r, i, val);
        }
        maintain(node);
    }
    private long query(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) { // 当前子树完全在 [ql, qr] 内
            return tree[node];
        }
        int m = (l + r) / 2;
        if (qr <= m) { // [ql, qr] 在左子树
            return query(node * 2, l, m, ql, qr);
        }
        if (ql > m) { // [ql, qr] 在右子树
            return query(node * 2 + 1, m + 1, r, ql, qr);
        }
        long lRes = query(node * 2, l, m, ql, qr);
        long rRes = query(node * 2 + 1, m + 1, r, ql, qr);
        return mergeVal(lRes, rRes);
    }
}
//作者：灵茶山艾府
//链接：https://leetcode.cn/discuss/post/3583665/fen-xiang-gun-ti-dan-chang-yong-shu-ju-j-bvmv/
//来源：力扣（LeetCode）
//著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
```


###  Lazy 线段树（有区间更新）

```java
// 模板来源 https://leetcode.cn/circle/discuss/mOr1u6/

class LazySegmentTree {
    // 懒标记初始值
    private static final long TODO_INIT = 0; // **根据题目修改**
    private static final class Node {
        long val; // **根据题目修改**
        long todo;
    }
    // 合并两个 val
    private long mergeVal(long a, long b) {
        return a + b; // **根据题目修改**
    }
    // 合并两个懒标记
    private long mergeTodo(long a, long b) {
        return a + b; // **根据题目修改**
    }
    // 把懒标记作用到 node 子树（本例为区间加）
    private void apply(int node, int l, int r, long todo) {
        Node cur = tree[node];
        // 计算 tree[node] 区间的整体变化
        cur.val += todo * (r - l + 1); // **根据题目修改**
        cur.todo = mergeTodo(todo, cur.todo);
    }
    private final int n;
    private final Node[] tree;
    // 线段树维护一个长为 n 的数组（下标从 0 到 n-1），元素初始值为 initVal
    public LazySegmentTree(int n, long initVal) {
        this.n = n;
        long[] a = new long[n];
        Arrays.fill(a, initVal);
        tree = new Node[2 << (32 - Integer.numberOfLeadingZeros(n - 1))];
        build(a, 1, 0, n - 1);
    }
    // 线段树维护数组 a
    public LazySegmentTree(long[] a) {
        n = a.length;
        tree = new Node[2 << (32 - Integer.numberOfLeadingZeros(n - 1))];
        build(a, 1, 0, n - 1);
    }
    // 用 f 更新 [ql, qr] 中的每个 a[i]
    // 0 <= ql <= qr <= n-1
    // 时间复杂度 O(log n)
    public void update(int ql, int qr, long f) {
        update(1, 0, n - 1, ql, qr, f);
    }
    // 返回用 mergeVal 合并所有 a[i] 的计算结果，其中 i 在闭区间 [ql, qr] 中
    // 0 <= ql <= qr <= n-1
    // 时间复杂度 O(log n)
    public long query(int ql, int qr) {
        return query(1, 0, n - 1, ql, qr);
    }
    // 把当前节点的懒标记下传给左右儿子
    private void spread(int node, int l, int r) {
        long todo = tree[node].todo;
        if (todo == TODO_INIT) { // 没有需要下传的信息
            return;
        }
        int m = (l + r) / 2;
        apply(node * 2, l, m, todo);
        apply(node * 2 + 1, m + 1, r, todo);
        tree[node].todo = TODO_INIT; // 下传完毕
    }
    // 合并左右儿子的 val 到当前节点的 val
    private void maintain(int node) {
        tree[node].val = mergeVal(tree[node * 2].val, tree[node * 2 + 1].val);
    }
    // 用 a 初始化线段树
    // 时间复杂度 O(n)
    private void build(long[] a, int node, int l, int r) {
        tree[node] = new Node();
        tree[node].todo = TODO_INIT;
        if (l == r) { // 叶子
            tree[node].val = a[l]; // 初始化叶节点的值
            return;
        }
        int m = (l + r) / 2;
        build(a, node * 2, l, m); // 初始化左子树
        build(a, node * 2 + 1, m + 1, r); // 初始化右子树
        maintain(node);
    }
    private void update(int node, int l, int r, int ql, int qr, long f) {
        if (ql <= l && r <= qr) { // 当前子树完全在 [ql, qr] 内
            apply(node, l, r, f);
            return;
        }
        spread(node, l, r);
        int m = (l + r) / 2;
        if (ql <= m) { // 更新左子树
            update(node * 2, l, m, ql, qr, f);
        }
        if (qr > m) { // 更新右子树
            update(node * 2 + 1, m + 1, r, ql, qr, f);
        }
        maintain(node);
    }

    private long query(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) { // 当前子树完全在 [ql, qr] 内
            return tree[node].val;
        }
        spread(node, l, r);
        int m = (l + r) / 2;
        if (qr <= m) { // [ql, qr] 在左子树
            return query(node * 2, l, m, ql, qr);
        }
        if (ql > m) { // [ql, qr] 在右子树
            return query(node * 2 + 1, m + 1, r, ql, qr);
        }
        long lRes = query(node * 2, l, m, ql, qr);
        long rRes = query(node * 2 + 1, m + 1, r, ql, qr);
        return mergeVal(lRes, rRes);
    }
}
//作者：灵茶山艾府
//链接：https://leetcode.cn/discuss/post/3583665/fen-xiang-gun-ti-dan-chang-yong-shu-ju-j-bvmv/
//来源：力扣（LeetCode）
//著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
```



## st 表

这部分为了方便，将板子修改了一部分 使用时候根据题目意思修改 op 函数就行

```java
class SparseTable {
    @FunctionalInterface
    public interface OP {
        int op(int x, int y);
    }
    private final int[][] infos;
    OP op;
    // 时间复杂度 O(n * log n)
    public SparseTable(int[] nums,OP op) {
        int n = nums.length;
        this.op = op;
        int w = 32 - Integer.numberOfLeadingZeros(n);
        infos = new int[w][n];
        for (int j = 0; j < n; j++) {
            infos[0][j] = nums[j];
        }
        for (int i = 1; i < w; i++) {
            for (int j = 0; j + (1 << i) <= n; j++) {
                infos[i][j] = op.op(infos[i - 1][j], infos[i - 1][j + (1 << (i - 1))]);
            }
        }
    }
    // [l, r) 左闭右开，下标从 0 开始
    // 必须保证 l < r
    // 时间复杂度 O(1)
    public int query(int l, int r) {
        int k = 31 - Integer.numberOfLeadingZeros(r - l);
        return op.op(infos[k][l], infos[k][r - (1 << k)]);
    }
}

//作者：灵茶山艾府
//链接：https://leetcode.cn/discuss/post/3583665/fen-xiang-gun-ti-dan-chang-yong-shu-ju-j-bvmv/
//来源：力扣（LeetCode）
//著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
```