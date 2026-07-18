package template.math.number;

/**
 * https://leetcode.cn/contest/weekly-contest-510/problems/minimum-total-cost-to-process-all-elements/submissions/735476159/
 * @author: wuxin0011
 * @Description:
 */
public class Sums {

    // 等差求和
    public static long calcSumsByA1(long a1,long n,long diff){
        long an = a1 + 1L * (n - 1) * diff;
        return (1L * (a1 + an) * n) / 2;
    }
    public static long calcSumsByAn(long an,long n,long diff){
        long a1 = an - 1L * (n - 1) * diff;
        return (1L * (a1 + an) * n) / 2;
    }




    // https://leetcode.cn/discuss/post/3584387/fen-xiang-gun-mo-yun-suan-de-shi-jie-dan-7xgu/
    // 等差求和带取模版本
    public static long calcSumsByA1MOD(long a1, long n, long diff, long MOD) {
        long an = (a1 % MOD + ((n - 1) % MOD) * (diff % MOD)) % MOD;
        long sum = ((a1 % MOD + an) % MOD) * (n % MOD) % MOD;
        return sum * qpow(2, MOD - 2, MOD) % MOD;
    }
    public static long calcSumsByAnMOD(long an, long n, long diff, long MOD) {
        long anMod = an % MOD;
        long nMod = n % MOD;
        long diffMod = diff % MOD;
        long nMinus1Mod = ((n - 1) % MOD + MOD) % MOD;
        long a1 = (anMod - nMinus1Mod * diffMod % MOD + MOD) % MOD;
        long sum = ((a1 + anMod) % MOD) * nMod % MOD;
        return sum * qpow(2, MOD - 2, MOD) % MOD;
    }
    public static long qpow(long x,long n,long MOD){
        long ans=1;
        for(;n > 0;n /= 2){
            if((n%2)==1){
                ans = ans * x % MOD;
            }
            x = x * x % MOD;
        }
        return ans;
    }
}
