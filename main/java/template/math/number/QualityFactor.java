package template.math.number;

/**
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

    public static void Qualityfactor(int t) {
        for (int k = 1; k <= t / k; k++) {
            if (t % k != 0) {
                continue;
            }
            System.out.printf("%s * %s = %s\n", k, t / k, t);
        }
    }

}
