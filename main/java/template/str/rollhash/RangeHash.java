package template.str.rollhash;
// Rolling Hash模版
/**
 * @author: wuxin0011
 * @Description:
 */
public  class RangeHash {
    private static final int mod = 1_000_000_007;
    private static final int inv = 129032259;
    public long[] prefix, div;

    public RangeHash(char[] word, boolean dir) {
        prefix = new long[word.length + 1];
        div = new long[word.length + 1];
        long mul = 1;
        div[0] = 1;
        int i = !dir ? 0 : word.length - 1;
        int pos = !dir ? 1 : -1;
        for (int j = 0; i < word.length && i >= 0; i += pos, ++j) {
            int t = word[i] - 'a';
            prefix[j + 1] = (prefix[j] + t * mul) % mod;
            mul = (mul * 31) % mod;
            div[j + 1] = div[j] * inv % mod;
        }
    }

    public long getRangeHash(int st, int ed) {
        long diff = (prefix[ed + 1] - prefix[st] + mod) % mod;
        return diff * div[st] % mod;
    }

    public int getLen() {
        return prefix.length - 1;
    }
}