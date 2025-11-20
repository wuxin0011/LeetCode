package template.bit;

import java.util.*;

/**
 * 参考文章 ： https://leetcode.cn/discuss/post/3571304/cong-ji-he-lun-dao-wei-yun-suan-chang-ji-enve/
 * @author: wuxin0011
 * @Description:
 */
public class Base {

    // 这个函数是方便打表
    // 以对齐方式打印int[] 的中二进制数据
    public static String printBinaryToString(int...args) {
        int mx = 0;
        for(int x : args) if(mx < x) mx = x;
        // List<String> list = new ArrayList<>();
        int n = 0;
        for(int i = 0,v = 0;i < 32 && v < mx;i++) {
            v |= (1 << i);
            n++;
        }
        String S = "";
        for(int x : args) {
            String s = "";
            for(int i = 0;i < n;i++) {
                s = (x >> i & 1) + s;
            }
            S += s;
            S += "\n";
        }
        System.out.println(S);
        return S;
    }



    // 常见相关技巧

    // 设置 i 位的值
    public static int set(int S,int i,int v) {
        return v == 0 ? (S & (~(1 << i))) : (S | 1 << i);
    }

    // 获取当前 i 位是不是 1
    public static boolean has(int S,int i) {
        return (S >> i & 1) == 1;
    }

    // 是否有相邻的 1
    public static boolean hasOneOne(int S) {
        return ((S & (S >> 1)) != 0);
    }


    // 统计二进制中1的个数
    public static int bitCount(int S) {
        return Integer.bitCount(S);
    }

    // 获取最右侧的1
    // Integer.lowestOneBit(S)
    public static int lowbit(int S) {
        return Integer.lowestOneBit(S);
    }


    // 获取最高位
    // Integer.highestOneBit(S)
    public static int high(int S) {
        return Integer.highestOneBit(S);
    }

    // 获取二进制长度 -> 100 = 3
    public static int bitLength(int S) {
        return 32 - Integer.numberOfLeadingZeros(S);
    }


    // 集合最大元素
    public static int numberOfLeadingZeros(int S) {
        return 31 - Integer.numberOfLeadingZeros(S);
    }

    // 集合最小元素
    public static int numberOfTrailingZeros(int S) {
        return Integer.numberOfTrailingZeros(S);
    }

    // 遍历集合中元素
    public static void exampleForS(int S) {
        for (int t = S; t > 0; t &= (t - 1)) {
            int i = Integer.numberOfTrailingZeros(t);
        }
    }


    // 枚举集合
    // 从 0 - (1<<n)
    public static void exampleAll(int n) {
        for(int S = 0;S<(1<<n);S++) {
            // ...
        }

        // 下面为测试部分
        List<Integer> it = new ArrayList<>();
        for(int S = 0;S<(1<<n);S++) {
            // ...
            it.add(S);
        }
        int[] array = it.stream().mapToInt(x -> x).toArray();
        System.out.println("枚举全集:" + (Integer.toBinaryString((1<<n)-1)));
        printBinaryToString(array);
    }

    // 枚举子集非空集合
    // S = 101
    // => 101 100 001
    public static void exampleSub(int S) {

        for (int sub = S; sub > 0; sub = (sub - 1) & S) {

        }

        // 下面为测试
        List<Integer> it = new ArrayList<>();
        for (int sub = S; sub > 0; sub = (sub - 1) & S) {
            it.add(sub);
        }
        int[] array = it.stream().mapToInt(x -> x).toArray();
        System.out.println("枚举子集非空");
        printBinaryToString(array);
    }


    // 枚举子集
    // S = 101
    // => 101 100 001 0
    public static void exampleSubEmpty(int S) {
        int sub = S;
        do {
            // ...
            sub = (sub - 1) & S;
        } while (sub != S);

        // 下面为测试部分---------------
        int X = S;
        List<Integer> it = new ArrayList<>();
        do {
            it.add(X);
            X = (X - 1) & S;
        } while (X != S);


        int[] array = it.stream().mapToInt(x -> x).toArray();
        System.out.println("枚举子集包含非空");
        printBinaryToString(array);

    }



    // 枚举超集
    public static void exampleFather(int n,int T) {
        for (int S = T; S < (1 << n); S = (S + 1) | T) {
            // ...
        }


        List<Integer> it = new ArrayList<>();
        for (int S = T; S < (1 << n); S = (S + 1) | T) {
            it.add(S);
        }

        int[] array = it.stream().mapToInt(x -> x).toArray();
        System.out.println("枚举超集");
        printBinaryToString(array);
    }


    // 亦可以用字典树解答
    // 求两个数最大异或和
    // 位运算 + 贪心 + 哈希表
    public static int findMaximumXOR(int[] nums) {
        int mx = nums[0];
        for (int x : nums) {
            mx = Math.max(x, mx);
        }
        int bit = 32 - Integer.numberOfLeadingZeros(mx);
        int ans = 0;
        for (int w = bit, m = 0; w >= 0; w--) {
            m |= 1 << w;
            int e = ans | 1 << w; // 贪心 当前位期望的最大结果
            Set<Integer> vis = new HashSet<>();
            for (int x : nums) {
                x &= m; // 去掉w后面的地位
                // 当前位置异或能知道符合当前位置最大值
                if (vis.contains(x ^ e)) {
                    ans = e;
                    break;
                }
                vis.add(x);
            }
        }
        return ans;
    }


    // 求两个异或和最小
    public static int findMinimumXOR(int[] nums) {
        int ans = Integer.MAX_VALUE;
        int[] b = Arrays.copyOf(nums,nums.length);
        Arrays.sort(b);
        for(int i = 0;i<nums.length-1;i++){
            ans=Math.min(ans,b[i]^b[i + 1]);
        }
        return ans;
    }



    public static void main(String[] args) {
        int[] a = {1, 20, 1111, 21, 11, 111};
        printBinaryToString(a);
        int S = a[1];
        System.out.println("当前集合的二进制: " + Integer.toBinaryString(S));
        System.out.println("获取二进制长度:" + bitLength(S));
        System.out.println("获取二进制1的个数:" + bitCount(S));
        System.out.println("获取当前位是否有1:" + has(S, 2));
        System.out.println("是否有相邻的1:" + hasOneOne(S));
        System.out.println("获取集合中最大元素:" + numberOfLeadingZeros(S));
        System.out.println("获取集合中最小元素:" + numberOfTrailingZeros(S));
        System.out.println("获取中两个数异或的最大值:" + findMaximumXOR(a));

        exampleSub(S);
        exampleSubEmpty(S);
        exampleAll(4);
    }


}
