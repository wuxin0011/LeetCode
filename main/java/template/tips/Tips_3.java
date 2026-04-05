package template.tips;

import java.util.Objects;

/**
 * pair 模板
 * @author: wuxin0011
 */
public class Tips_3 {
    public static class Pair<K,V> {
        K key;
        V val;
        Pair(K key,V val) {
            this.key = key;
            this.val = val;
        }
        public String toString() {
            return String.format("[%s,%s]\n",key,val);
        }
        public int hashCode() {
            return Objects.hash(key,val);
        }
        public boolean equals(Object o) {
            if(o instanceof Pair) {
                Pair<K,V> new_pair = (Pair<K,V>)o;
                if(!new_pair.key.equals(key)) return false;
                if(!new_pair.val.equals(val)) return false;
                return true;
            }
            return false;
        }
    }
}
