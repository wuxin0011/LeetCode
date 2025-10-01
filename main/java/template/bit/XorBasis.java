package template.bit;

/**
 * 模板题 ： https://leetcode.cn/problems/maximum-xor-of-subsequences/description/
 * 1、判断一个数是否是子集的异或和
 * 2、判断子集的异或和的方案数
 * 3、判断子集的异或的和最大，最小，第K大，第k小
 * 4、判断一个数是否是子集的异或和 中的排名
 *
 * @author: wuxin0011
 * @Description:
 */
// 线性基模板
class XorBasis {
    private final int[] b;

    public XorBasis(int n) {
        b = new int[n];
    }

    public void insert(int x) {
        while (x > 0) {
            int i = 31 - Integer.numberOfLeadingZeros(x);
            if (b[i] == 0) {
                b[i] = x;
                return;
            }
            x ^= b[i];
        }
    }

    public int maxXor() {
        int res = 0;
        for (int i = b.length - 1; i >= 0; i--) {
            res = Math.max(res, res ^ b[i]);
        }
        return res;
    }
}

//作者：灵茶山艾府
//链接：https://leetcode.cn/problems/partition-array-for-maximum-xor-and-and/solutions/3734850/shi-zi-bian-xing-xian-xing-ji-pythonjava-3e80/
//来源：力扣（LeetCode）
//著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。