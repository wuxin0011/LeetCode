## 对拍格式


参数说明

核心实现方法请查看 **[testUtil](./IoUtil.java)** 方法

```java
public static <T> void testUtil(Class<T> c, String methodName, String fileName, boolean openLongContent,boolean isStrict)
```

 - 类名
   - 必填
 - 测试方法名
   - 普通类对拍 如果测试方法只有除`main`方法一个方法，可不填,解析过程中会找到该方法
   - 构造类对拍 当调用方法名为`__ConstructorClass__` 表示调用构造类对拍
 - 获取输入的文件名
   - 默认`in.txt`
 - 是否开启 `#content#` 格式解析
   - 默认为`false`
 - 是否严格比较 ，默认 true ，如果不注重元素顺序，希望使用 false

**数组或者列表**

> PS: **2024/6/13 更新: 如果后续补充其他功能 不会在添加参数了 将会慢慢调整为注解**

### 普通对拍

> 举个例子 下面方法是获取数组的最大值

```java
import code_generation.utils.IoUtil;

class Main {
    public static void main(String[] args) {
        IoUtil.testUtil(Main.class, "getMax", "in.txt");
    }

    public int getMax(int[] arr) {
        int mx = Integer.MIN_VALUE;
        for (int num : arr) {
            if (mx < num) mx = num;
        }
        return mx;
    }
}
```

**in.txt**
```txt
["100","200","-100","300"]
300

[100,200,-100,500]
500
```
每组数据最后一行都是**期望结果**

对于数组类型按照上面格式输入就行，按不按找空格都可以（空格主要是为了自己方便看清楚）

**对于没有开启`##`解析的数据的文件，请必须一行对应一个参数的数据！！！**

> 如何支持多行解析 ？

如果数据长度过长无法一行存放或者不美观可以开启 ## 解析，对于每一组数据必须都用`##`包裹

举个例子，岛屿的数量

```java
public class number_of_islands {

    public static void main(String[] args) {
        IoUtil.testUtil(number_of_islands.class, "numIslands", "./txt_file/in.txt", true);
    }


    public int numIslands(char[][] nums) {
        int cnt = 0;
        int m = nums.length, n = nums[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (nums[i][j] == '1') {
                    cnt++;
                    f(nums, m, n, i, j);
                }
            }
        }
        return cnt;
    }

    public void f(char[][] nums, int m, int n, int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n || nums[i][j] != '1') {
            return;
        }
        nums[i][j] = '0';

        f(nums, m, n, i + 1, j);
        f(nums, m, n, i - 1, j);
        f(nums, m, n, i, j - 1);
        f(nums, m, n, i, j + 1);
    }
}

```


```txt
#
[
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
#
#
1
#




#
[
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
#
# 3 #
```

对于是否在## 包裹的数据是否在一样，这个没有严格要求


> 支持那些输入？

目前支持一维数组，二维数组，三维数组，`TreeNode`,`ListNode` 以及其他基本数据类型

```txt

# 基本数据类型
1
true
"Java"
Java



# 一维数组 或者 list
[a,b,c,d,e,f,g]
[100，200，-100,-1000]

# 二维数组 或者 list list
[[a,b],[c,d],[e,f]]
[[1,1],[2,100],[3,100]]

# 三维数组 或者 list list list
[[[a,c],[d,f],[e,f]],[[a,c],[d,f],[e,f]]]


// TODO 补充其他格式

```


### 构造类对拍


由于构造类类名不是 Solution 为了方便 采用 静态内部类 

- [类文件参考](../../leetcode/everyday/day_000/Code_0044_705.java)
- [对拍文件格式](../../leetcode/everyday/day_000/txt_file/Code_0044_705.txt)


### AMC 模式对拍

[参见ACM模式对拍说明](./ACM/readme.md)


## 其他

- [@Description](../annotation/Description.java)   题目其他信息说明 这个注解是写着玩的不是很重要
- [@TestCaseGroup](../annotation/TestCaseGroup.java) 测试那些组数据 根据自己需求测试那一组数组😂 根据个人经验来看这个还是十分有必要的🤨

## 注意

> 如果使用的跟目录请修改 [点这里](./IoUtil.java)


我这里是`main/java/`作为根路径

```java
public static final String[] DEFAULT_ROOTS = {"main", "java"};
```

如果是`src`目录作为根路径
```java
public static final String[] DEFAULT_ROOTS = {"src"};
```


为了方便，将对拍部分重写到一个文件中，将文件名，功能和这个一样，同时新增了在 `java` 文件中标记了测试案例符号

[这个文件](./lcutil.txt)
