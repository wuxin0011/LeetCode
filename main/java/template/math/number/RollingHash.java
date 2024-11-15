package template.math.number;

import java.util.LinkedList;

/**
 * @author: wuxin0011
 * @Description:
 */
public class RollingHash {
    private int base;
    private long mod;
    private LinkedList<Integer> xs;
    private LinkedList<Long> bs;
    private long hash;
    public RollingHash (int base, long mod) {
        this.base = base;
        this.mod = mod;
        xs = new LinkedList<>();
        bs = new LinkedList<>();
        hash = 0;
    }
    public long getHash() {
        return hash;
    }
    public void addFirst(int x) {
        xs.addFirst(x);
        bs.addFirst(bs.isEmpty() ? 1 : bs.getFirst() * base % mod);
        hash = (hash + x * bs.getFirst() % mod) % mod;
    }
    public void addLast(int x) {
        xs.addLast(x);
        bs.addFirst(bs.isEmpty() ? 1 : bs.getFirst() * base % mod);
        hash = (hash * base % mod + x) % mod;
    }
    public void removeFirst() {
        hash = (hash + mod - xs.removeFirst() * bs.removeFirst() % mod) % mod;
    }
}
