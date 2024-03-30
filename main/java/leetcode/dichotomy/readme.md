
### 二分答案具有单调性

假设区间为 `[l,r]`, 在符合 ans 的 某个点为 k , 那么一定有如下成立

如果是最大

- `[l,k]` 区间答案一定满足题目要求， 而且 l - > k 具有单调性

如果是最小

- `[k,r]` 区间答案一定满足题目要求 ，而且 k -> l 具有单调性


一般模板

假设最右一定满足题目要求，从 `[k,r]` 也是全部满足题目要求的 这个 **K** 就是要寻找的
```java
int r = 一定符合要求的最大值,l = 不符合要求的;

int ans = 0;


while(l<=r){
    int mid = l + ((r - l)>>1);
    if(check(mid)){
        ans = mid;
        r = mid - 1; // 继续往左寻找
    }else{
        l = mid + 1; // 不符合要求，往
    }
}

return ans;



public boolean check(int mid){
    
    // 检查区间的合理性
}


```




```java
int l = 一定符合要求的最小值,r = max;

int ans = 0;


while(l<=r){
    int mid = l + ((r - l)>>1);
    if(check(mid)){
        ans = mid;
        l = mid + 1; // 继续往右查找
    }else{
        r = mid - 1; // 不符合要求，往左边找
       
    }
}

return ans;



public boolean check(int mid){
    
    // 检查区间的合理性
}


```



### 二分题单

- [爱吃香蕉的狒狒](https://leetcode.cn/problems/nZZqjQ/)
- [分割数组的最大值](https://leetcode.cn/problems/split-array-largest-sum/)
- [找出第 K 小的数对距离](https://leetcode.cn/problems/find-k-th-smallest-pair-distance)

