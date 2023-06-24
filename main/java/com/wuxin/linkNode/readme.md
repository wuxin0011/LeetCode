## 两个链表对应位置相加

### 思路分析

虚拟头结点，链表遍历，遍历过程中记录进位数。



### 代码

```java
 public static SingleNode twoNodeAddSum(SingleNode p1, SingleNode p2) {
        if (p1 == null && p2 == null) {
             return null;
        }
        SingleNode listNode = new SingleNode(-1);
        SingleNode cur = listNode;

        int c = 0;
        while (p1 != null || p2 != null) {
           // 记录上次进位数
           int val = c;
   
           if (p1 != null) {
              val += p1.val;
              p1 = p1.next;
           }
           if (p2 != null) {
              val += p2.val;
              p2 = p2.next;
           }
           // 进位数
           c = (int) Math.floor((double) val / 10);
           val = val % 10;
   
           // 当前指针位置后移动一位
           cur.next = new SingleNode(val);
           cur = cur.next;
        }


        return listNode.next;
     }
```

## 如何判断链表是有环  如果存在，返回第一个节点

### 思路分析

1. 使用 `HashSet` 也就是 哈希映射 遍历时候将当前节点存入 `HashSet` 中，每次存入前比较是否存在，存在就说明存在环
    - 优点: 容易想到
    - 缺点: 使用了额外空间结构
2. 快慢指针 使用两个指针 快指针 每次走慢指针的 n(n>=2) 倍 ，如果存在环某时刻**一定出现快慢指针相等**。
    - 优点: 没有使用额外空间 仅使用有限变就能获取结果
    - 缺点: 不容易想到

### 代码

方案一

```java
  public static SingleNode hasRingNode2(SingleNode node){
        // 两个节点的链表不能成环
        if(node==null||node.next==null){
            return null;
        }
        // hash 映射
        HashSet<SingleNode> set=new HashSet<>();
        // 临时变量 代替 头结点遍历
        SingleNode temp=node;
        
        while(temp!=null){
           if(set.contains(temp)){
              return temp;
           }
           set.add(temp);
           temp=temp.next;
        }

        return null;
     }
```

方案二

``` java 
       public static SingleNode hasRingNode(SingleNode node) {
        // 两个节点的链表不能成环
        if (node == null || node.next == null || node.next.next == null) {
            return null;
        }
        // 创建一个款
        SingleNode slow = node.next;
        SingleNode fast = node.next.next;
        // 如果存在环形  快慢节点指针一定会相遇
        while (slow.next != null && fast.next.next != null) {
            // 快慢指针相遇
            if (slow == fast) {
                break;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = node;
        while (slow != null && fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
    
 ```

## 判断两个无环链表相交第一个节点 如果存在，返回第一个节点

## 思路分析

1. 使用 `HashSet` 也就是 哈希映射
    - 优点: 容易想到
    - 缺点: 使用了额外空间结构
2. 长短比较 ，同步移动 ，第一遍遍历出长短链表，与两个链表差值 ，长链表先移动差值，然后短链表在同时移动，如果两个链相交
   一定在某点相等。

### 代码

方案一：

```java
public static SingleNode getIntersectNode(SingleNode l1, SingleNode l2) {
        if (l1 == null || l2 == null) {
            return null;
        }
        SingleNode h1 = l1;
        HashSet<SingleNode> set1 = new HashSet<>();
        while (h1 != null) {
            set1.add(h1);
            h1 = h1.next;
        }
        h1 = l2;
        while (h1 != null) {
            h1 = h1.next;
            if (set1.contains(h1)) {
                return h1;
            }
        }
        return null;
    }

```

方案二

```java
 public static SingleNode getIntersectNode(SingleNode l1, SingleNode l2) {
        if (l1 == null || l2 == null) {
            return null;
        }
        // 如果头节点是公共的
        if (l1 == l2) {
            return l1;
        }
        int n = 0;
        SingleNode c1 = l1;
        SingleNode c2 = l2;
        while (c1.next != null) {
            n++;
            c1 = c1.next;
        }
        while (c2.next != null) {
            n--;
            c2 = c2.next;
        }
        // 最后一个节点如果不相等 一定不会相交
        // 如果相交的话最后一个节点一定相等
        if (c1 != c2) {
            return null;
        }
        // 获取较长一个链表
        c1 = n > 0 ? l1 : l2;
        // 获取较短一个链表
        c2 = c1 == l1 ? l2 : l1;
        // 获取长度的结绝对值
        n = Math.abs(n);
        // 长的链表先走 n 步
        while (n != 0) {
            c1 = c1.next;
            n--;
        }
        // 短链表 开始遍历
        while (c1 != null && c2 != null && c1 != c2) {
            c1 = c1.next;
            c2 = c2.next;
        }
        return c1;
    }
```

### 判断两个两个可能存在存在环的单链表相交的第一节点