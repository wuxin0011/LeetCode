## 分块

### 原理

块状数组其实是类似于暴力算法，利用分块将每个区间分为各自的块,利用区间操作`[L,R]` 当 区间过大可用分块加速更新
对于一个区间可以采用暴力


### 应用

对于不可维护的区间合并信息，同时存在区间修改 可以考虑试试分块


### 模板

#### [~~模板1~~](./_1_query_interval_val.java) 区间更新
- 求区间最值 最大值最小值 这个线段树能做
- 区间求和 这个线段树能做
- 区间 `[L,R]` 求满足 `>= val` 的个数,说明一下
  -  `>val` 可以通过 查询 `val + 1` 来转换
  -  `<val` 可以通过 查询`(R - L + 1) - >=val` 来转换
  -  `==val` 可以通过 查询 `(>=val) - (>= val + 1)` 来转换
  -  `<= val` 可以通过 查询 `(R - L + 1) - (>=val + 1)` 来转换

**注意当前模板存在问题 分块比暴力还慢**

暂时提供一个查询 [``[L,R] >= val`` 的分块](./_2_query_interval_val.java)


基本模板
```java
class Block {

    int bl[],br[],sz,blockNum;

    Block(int[] a) {
        int n = a.length;
        this.initBlockInfo(n);
        // 初始化其他部分信息 ...

    }


    //初始化 块的左右边界 大小等信息
    void initBlockInfo(int n) {
        sz = (int) Math.sqrt(n);
        blockNum = (n + sz - 1) / sz;

        bl = new int[blockNum];
        br = new int[blockNum];
        Arrays.fill(bl, Integer.MAX_VALUE);
        Arrays.fill(br, Integer.MIN_VALUE);


        for (int i = 0; i < n; i++) {
            int blockIndex = i / sz;
            bl[blockIndex] = Math.min(bl[blockIndex], i);
            br[blockIndex] = Math.max(br[blockIndex], i);
        }
    }


    // 分块整体模板
    // 可以更新题目需要的信息进行修改
    // 不是完整块需要暴力 更新or查询
    // 利用块某些性质（如有序等）快速[查询|更新] todo...
    private void handler(int l, int r) {
        int blockL = l / sz;
        int blockR = r / sz;

        if (blockL == blockR) {
            
            if (bl[blockL] == l && r == br[blockR]) {
                // 整块 todo...
            } else {
                for (int i = l; i <= r; i++) {
                   // 暴力 [查询更新] todo...
                }
            }
        } else {
            for (int i = l; i <= br[blockL]; i++) {
                // 暴力 [查询更新] todo...
            }

            
            for (int i = blockL + 1; i <= blockR - 1; i++) {
                // 整块 todo...
            }

            
            
            if (br[blockR] == r) {
                // 右边整块 ...
            } else {
                
                for (int i = bl[blockR]; i <= r; i++) {
                   // 暴力[查询 | 更新] ...
                }
            }
        }
    }

}
```