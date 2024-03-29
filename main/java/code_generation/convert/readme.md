
## 解析类

预设下面这些格式


### List

先枚举List<String>类型吧，后面的都可以通过这个转换得到，比如 ``String[]`` 可以通过 `List<String>` 转换得到


- ``String``
- ``List<String>``
- ``List<List<String>>``
- ``List<List<List<String>>>``


通过上面三个可以得到各种类型的数据【ps:数据输入的格式🆗】




> 常见格式如下 (按照常见顺序排序，可以将 int 看作 `int`、`int[][]`、`int[][]` 以及 对应的 `List`)

- int
- String
- char
- long
- boolean
- TreeNode (二叉树)
- ListNode (单链表)
- double
- float
- // ... 其他未知类型



> 转换流程


以 ``int`` 为例子，转换格式如下

- `String` -> `int`
- `List<String>` -> `List<Integer>` -> `int[]`
- `List<List<String>>` -> `List<List<Integer>>` -> `int[][]`
- `List<List<List<String>>>` -> `List<List<List<String>>>` -> `int[][][]`




**目前最多到三维数组,其他转换类似，通过实现List，能够省去不少重复代码**


目前实现上面解析有下面这些工具
- [IoUtil ](../utils/IoUtil.java)
  - 找到文件，解析输入内容
- [ReflectUtils](../utils/ReflectUtils.java)
  - 解析成对应格式
- [TestUtils](../utils/TestUtils.java)
  - 将结果和代码运行结果对比

不少转换格式没有实现，但是目前没有遇到对应格式题目，当不符合这些格式，自动提出警告信息