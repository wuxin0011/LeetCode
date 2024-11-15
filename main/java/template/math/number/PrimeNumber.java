package template.math.number;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

/**
 * 质数  ： 除了1和本身外 不能被其他数整除
 *
 * @author: wuxin0011
 * @Description: 质数常用模板
 */
public class PrimeNumber {


    // 常用模板
    // https://leetcode.cn/problems/prime-subtraction-operation/
    static class DefaultPrimeNumberTemplate {
        static int N = (int) 1e6 + 1;
        static int[] vis = new int[N];
        static int[] sums = new int[N];
        static int[] sk;
        static int size;

        static {
            Arrays.fill(vis, 1);
            size = N;
            for (int i = 0; i < N; i++) {
                if (i < 2) {
                    vis[i] = 0;
                    size--;
                } else {
                    if (vis[i] == 0) {
                        continue;
                    }
                    if (i * 1L * i >= N) {
                        break;
                    }
                    for (int j = i * i; j < N; j += i) {
                        if (vis[j] != 0) {
                            size--;
                        }
                        vis[j] = 0;
                    }
                }
            }
            sk = new int[size];

            for (int i = 0, j = 0; i < N; i++) {
                if (vis[i] == 1) {
                    sk[j++] = i;
                }
                // if(i > 2) {
                //     sums[i] = sums[i-1] + a[i];
                // }
            }
        }

        // 配合二分使用
        static int lowerBound(int t) {
            int l = 0, r = size;
            while (l <= r) {
                int mid = l + ((r - l) >> 1);
                if (mid >= size) {
                    break;
                }
                if (sk[mid] >= t) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            return l;
        }


        public static boolean isPrime(int n) {
            return vis[n] == 1;
        }

    }


    static class baseValid {

        /**
         * 普通版本 一般是数据量不能太大
         * 判断一个数是否是质数（素数）
         *
         * @param n num
         * @return ok
         */
        public static boolean isPrime(int n) {
            if (n < 2) {
                return false;
            }
            for (int i = 2; i <= n / i; i++) {
                if (n % i == 0) {
                    return false;
                }
            }
            return true;
        }


        /**
         * 统计范围内质数 数量
         * 5 * 10 ^ 6
         * 埃氏筛
         *
         * @param n
         * @return
         * @url: https://leetcode.cn/problems/count-primes/description/
         * @url: https://leetcode.cn/problems/count-primes/submissions/540161552/ 可以预处理
         */
        public static int erCountPrimes(int n) {
            int[] isPrime = new int[n];
            Arrays.fill(isPrime, 1);
            int ans = 0;
            for (int i = 2; i < n; ++i) {
                if (isPrime[i] == 1) {
                    ans += 1;
                    // 防止数据量打越界
                    if (i * 1L * i >= n) {
                        break;
                    }
                    if (i * i < n) {
                        for (int j = i * i; j < n; j += i) {
                            isPrime[j] = 0;
                        }
                    }
                }
            }
            return ans;
        }


        // 预处理 + 前缀和     直接统计 0 - N 范围内质数
        static int N = (int) 1e6 * 5 + 2;
        static int[] sum = new int[N];

        static {
            Arrays.fill(sum, 1);

            // 0 和 1 不是质数
            // 除了1和本身外没有任何其他质因子
            sum[0] = 0;
            sum[1] = 0;

            int n = sum.length;
            for (int i = 2; i < n; i++) {
                if (sum[i] == 0) {
                    continue;
                }
                // 防止越界
                if (i * 1L * i >= n) {
                    break;
                }
                for (int j = i * i; j < n; j += i) {
                    sum[j] = 0;
                }
            }
            // 如果不统计这个范围质数数量而是判断这个数是不是质数
            // 就不需要这一步了 输入一个num => sum[num] == 1 就是质数
            // 但是当数据范围过大 超过 1e6 这个就不是适合了 见下方
            for (int i = 2; i < n; i++) {
                sum[i] += sum[i - 1];
            }
        }

        public int preCountPrimes(int n) {
            if (n == 0) {
                return 0;
            }
            return sum[n - 1];
        }


        /**
         * bitset 优化
         *
         * @param n
         * @return
         */

        public int bitSetCountPrimes(int n) {
            BitSet bs = new BitSet();
            int total = 0;
            int i = bs.nextClearBit(2);
            int count = Math.min((int) (Math.sqrt(1.0 * n)) + 1, n - 1);
            while (i <= count) {
                if (!bs.get(i)) {
                    total += 1;
                    int j = i * i;
                    for (int k = j; k < n; k += i) {
                        bs.set(k);
                    }
                }
                i = bs.nextClearBit(i + 1);
            }
            while (i > 0 && i < n) {
                total += 1;
                i = bs.nextClearBit(i + 1);
            }
            return total;
        }

        /**
         * 统计范围内质数 数量
         * 5 * 10 ^ 6
         * 线性筛 又称为欧拉筛
         *
         * @param n
         * @return
         * @url: https://leetcode.cn/problems/count-primes/description/
         */
        public int OLCountPrimes(int n) {
            List<Integer> primes = new ArrayList<Integer>();
            int[] isPrime = new int[n];
            Arrays.fill(isPrime, 1);
            for (int i = 2; i < n; ++i) {
                if (isPrime[i] == 1) {
                    primes.add(i);
                }
                for (int j = 0; j < primes.size() && i * primes.get(j) < n; ++j) {
                    isPrime[i * primes.get(j)] = 0;
                    if (i % primes.get(j) == 0) {
                        break;
                    }
                }
            }
            return primes.size();
        }
    }


    /**
     * 判断 测试 1e10 数字是不是质数
     * 使用方法 millerRabin
     */
    static class millerRabinValidPrimeNumber {
        // 质数的个数代表测试次数
        // 如果想增加测试次数就继续增加更大的质数
        public static long[] p = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37};

        public static boolean millerRabin(long n) {
            if (n <= 2) {
                return n == 2;
            }
            if ((n & 1) == 0) {
                return false;
            }
            for (int i = 0; i < p.length && p[i] < n; i++) {
                if (witness(p[i], n)) {
                    return false;
                }
            }
            return true;
        }

        // 返回n是不是合数
        public static boolean witness(long a, long n) {
            long u = n - 1;
            int t = 0;
            while ((u & 1) == 0) {
                t++;
                u >>= 1;
            }
            long x1 = power(a, u, n), x2;
            for (int i = 1; i <= t; i++) {
                x2 = power(x1, 2, n);
                if (x2 == 1 && x1 != 1 && x1 != n - 1) {
                    return true;
                }
                x1 = x2;
            }
            if (x1 != 1) {
                return true;
            }
            return false;
        }


        public static long power(long n, long p, long mod) {
            long ans = 1;
            while (p > 0) {
                if ((p & 1) == 1) {
                    ans = (ans * n) % mod;
                }
                n = (n * n) % mod;
                p >>= 1;
            }
            return ans;
        }

    }


    /**
     * 判断 任意范围 数字是不是质数
     * <p>
     * 使用方法 自带库
     */
    static class millerRabinValidPrimeNumber2 {

        /**
         * @param stringNumber 数字范围特别大 导致 long  都越界了 用 字符串表示
         * @param testTimes            测试次数 次数越大精度越好 耗时越高 底层还是 millerRabin 方法测试
         * @return true
         */
        public static boolean isProbablePrime(String stringNumber, int testTimes) {
            BigInteger b = new BigInteger(stringNumber);
            return b.isProbablePrime(testTimes);
        }

    }


}
