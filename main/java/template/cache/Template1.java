package template.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author: wuxin0011
 * @Description:
 */
public class Template1 {

    static class NodeCache {
        int[] args;

        public NodeCache(int... args) {
            this.args = args;
        }

        @Override
        public int hashCode() {
            int val = 0;
            for (int x : this.args) {
                val += x;
            }
            return val;
            // return Objects.hashCode(args);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof NodeCache) {
                NodeCache cur = (NodeCache) obj;
                if (this.args.length != cur.args.length) {
                    return false;
                }
                for (int i = 0; i < this.args.length; i++) {
                    if (this.args[i] != cur.args[i])
                        return false;
                }
                return true;
            }
            return false;
//            return Objects.equals(this,obj);
        }
    }

    Map<NodeCache, Integer> cacheMap = new HashMap<>();


    int n;

    public int dfs(int i,int j,int k ){
        // todo 递归边界 example
        if(i >= n) {
            return 0;
        }

        NodeCache nodeCache = new NodeCache(i,j,k);
        Integer val = cacheMap.get(nodeCache);
        if(val != null) {
            return val;
        }

        int ans = 0;
        // TODO 计算逻辑
        // example
        ans = Math.max(dfs(i + 1,k,j),dfs(i + 1,k - 1,j)) + 1;

        // 换成计算结果
        cacheMap.put(nodeCache,ans);
        return ans;

    }
}
