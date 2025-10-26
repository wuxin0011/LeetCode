# 模板整理与灵神数学题单

[原文链接](https://leetcode.cn/discuss/post/3584388/fen-xiang-gun-ti-dan-shu-xue-suan-fa-shu-gcai/)

## 判断质数

### 1、写法一
```java
private boolean isPrime(int n) {
    for (int i = 2; i * i <= n; i++) {
        if (n % i == 0) {
            return false;
        }
    }
    return n >= 2; // 1 不是质数
}
```

### 2、写法二 更快
```java
class Solution {
        private static final int MX = 1_000_001;
        private static final boolean[] isPrime = new boolean[MX];
        private static final List<Integer> primes = new ArrayList<>();
        private static boolean initialized = false;
        private void init() {
            if (initialized) {
                return;
            }
            initialized = true;
            Arrays.fill(isPrime, true);
            isPrime[0] = isPrime[1] = false; // 0 和 1 不是质数
            for (int i = 2; i < MX; i++) {
                if (isPrime[i]) {
                    primes.add(i);
                    for (long j = (long) i * i; j < MX; j += i) {
                        isPrime[(int) j] = false; // j 是质数 i 的倍数
                    }
                }
            }
        }
        public int solve(int[] nums) {
            init(); // 记得初始化
            return 0;
        }

}
//作者：灵茶山艾府
//链接：https://leetcode.cn/discuss/post/3584388/fen-xiang-gun-ti-dan-shu-xue-suan-fa-shu-gcai/
//来源：力扣（LeetCode）
//著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
```


## 质因数分解

### 模板一

预处理每个数的所有不同质因子。原理同埃氏筛

```java
class Solution {
    private static final int MX = 1_000_001;
    private static final List<Integer>[] primeFactors = new ArrayList[MX];
    private static boolean initialized = false;
    private void init() {
        if (initialized) {
            return;
        }
        initialized = true;
        Arrays.setAll(primeFactors, _ -> new ArrayList<>());
        for (int i = 2; i < MX; i++) {
            if (primeFactors[i].isEmpty()) { // i 是质数
                for (int j = i; j < MX; j += i) { // i 的倍数 j 有质因子 i
                    primeFactors[j].add(i);
                }
            }
        }
    }
    public int solve(int[] nums) {
        init(); // 记得初始化
    }

}
//作者：灵茶山艾府
//链接：https://leetcode.cn/discuss/post/3584388/fen-xiang-gun-ti-dan-shu-xue-suan-fa-shu-gcai/
//来源：力扣（LeetCode）
//著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
```

### 模板二

预处理 x 的最小质因子 LPF(x)，从而做到 O(logx) 分解 x。可以求出 x 的每个质因子的个数。

```java
class Solution {
    private static final int MX = 1_000_001;
    private static final int[] lpf = new int[MX];
    private static boolean initialized = false;
    // 这样写比 static block 更快
    private void init() {
        if (initialized) {
            return;
        }
        initialized = true;
        for (int i = 2; i < MX; i++) {
            if (lpf[i] == 0) { // i 是质数
                for (int j = i; j < MX; j += i) {
                    if (lpf[j] == 0) { // 首次访问 j
                        lpf[j] = i;
                    }
                }
            }
        }
    }
    // 质因数分解
    // 例如 primeFactorization(45) = [[3, 2], [5, 1]]，表示 45 = 3^2 * 5^1
    // 时间复杂度 O(log x)
    private List<int[]> primeFactorization(int x) {
        List<int[]> res = new ArrayList<>();
        while (x > 1) {
            int p = lpf[x];
            int e = 1;
            for (x /= p; x % p == 0; x /= p) {
                e++;
            }
            res.add(new int[]{p, e});
        }
        return res;
    }
    public int solve(int[] nums) {
        init(); // 记得初始化
    }
}
//作者：灵茶山艾府
//链接：https://leetcode.cn/discuss/post/3584388/fen-xiang-gun-ti-dan-shu-xue-suan-fa-shu-gcai/
//来源：力扣（LeetCode）
//著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
```


### 回文数

```java
class Solution {
    private static final long MX = 10_000_000_000L; // 根据题目调整
    private static final List<Long> palindromes = new ArrayList<>();
    private static boolean initialized = false;
    private void init() {
        if (initialized) {
            return;
        }
        initialized = true;
        for (int base = 1; ; base *= 10) {
            // 生成奇数长度回文数，例如 base = 10，生成的范围是 101 ~ 999
            for (int i = base; i < base * 10; i++) {
                long x = i;
                for (int t = i / 10; t > 0; t /= 10) {
                    x = x * 10 + t % 10;
                }
                if (x > MX) {
                    return;
                }
                palindromes.add(x);
            }
            // 生成偶数长度回文数，例如 base = 10，生成的范围是 1001 ~ 9999
            for (int i = base; i < base * 10; i++) {
                long x = i;
                for (int t = i; t > 0; t /= 10) {
                    x = x * 10 + t % 10;
                }
                if (x > MX) {
                    return;
                }
                palindromes.add(x);
            }
        }
    }

    public int solve(int n) {
        init(); // 记得调用
    }

}

//作者：灵茶山艾府
//链接：https://leetcode.cn/discuss/post/3584388/fen-xiang-gun-ti-dan-shu-xue-suan-fa-shu-gcai/
//来源：力扣（LeetCode）
//著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
```



### 组合数学


```java
class Solution {
    private static final int MOD = 1_000_000_007;
    private static final int MX = 100_001; // 根据题目数据范围修改
    private static final long[] F = new long[MX]; // F[i] = i!
    private static final long[] INV_F = new long[MX]; // INV_F[i] = i!^-1 = pow(i!, MOD-2)
    static {
        F[0] = 1;
        for (int i = 1; i < MX; i++) {
            F[i] = F[i - 1] * i % MOD;
        }
        INV_F[MX - 1] = pow(F[MX - 1], MOD - 2);
        for (int i = MX - 1; i > 0; i--) {
            INV_F[i - 1] = INV_F[i] * i % MOD;
        }
    }
    private static long pow(long x, int n) {
        long res = 1;
        for (; n > 0; n /= 2) {
            if (n % 2 > 0) {
                res = res * x % MOD;
            }
            x = x * x % MOD;
        }
        return res;
    }
    // 从 n 个数中选 m 个数的方案数
    private long comb(int n, int m) {
        return m < 0 || m > n ? 0 : F[n] * INV_F[m] % MOD * INV_F[n - m] % MOD;
    }
    public int solve(int[] nums) {
        // 预处理的逻辑写在 static 块中，这样只会初始化一次
    }
}

//作者：灵茶山艾府
//链接：https://leetcode.cn/discuss/post/mDfnkW/
//来源：力扣（LeetCode）
//著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
```