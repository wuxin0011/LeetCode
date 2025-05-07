package template.math.number;

/**
 * 枚举因子统计这类题目有时候需要配合容斥原理
 * @author: wuxin0011
 * @Description: 枚举因子
 */
public class QualityFactor {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            Qualityfactor(i);
            System.out.println();
        }
    }


    // 基本枚举
    public static void Qualityfactor(int t) {
        for (int k = 1; k <= t / k; k++) {
            if (t % k != 0) {
                continue;
            }
            System.out.printf("%s * %s = %s\n", k, t / k, t);
        }
    }



    // 预处理 [1,N] 访问数字那些因子或者统计因子个数


    // 枚举因子配合gcd经典题目
    // https://leetcode.cn/problems/count-array-pairs-divisible-by-k/ 2183. 统计可以被 K 整除的下标对数目


}
