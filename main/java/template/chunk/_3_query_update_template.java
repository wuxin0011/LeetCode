package template.chunk;

/**
 * 分块模板
 * @author: wuxin0011
 * @Description:
 */
public class _3_query_update_template {

    public static void main(String[] args) {
        // 这部分为演示部分
        int n = 100,q = 100;
        int[] a = new int[n + 1];
        Block block = new Block(a);
        while(q-->0) {
            int op = 0;
            int l = 0;
            int r = 0;
            if(op==1) {
                block.update(l,r);
            }else{
                block.query(l,r);
            }
        }
        // 这部分为演示部分
    }


    static class Block {


        int[] bi,bl,br,a;

        int n,blen,bcount;
        Block(int[] arr) {
            this.n = arr.length;
            a = new int[n];
            for(int i = 0;i<n;i++){
                a[i] = arr[i];
            }
            bcount = Math.max(1,(int)(Math.sqrt(n)) + 1);
            blen = (n + bcount - 1)  / bcount;
            for(int i = 0;i < n;i++) {
                bi[i] = i / blen;
            }

            for(int b = 0;b < bcount;b++) {
                bl[b] = b * blen;
                br[b] = Math.min(n - 1,(b + 1) * blen - 1);
                if(br[b]==n-1)break;
            }
            // init .. more
        }

        // 查询一个块
        int queryBlock(int b) {
            return 0;
        }


        // 更新一个块
        void updateBlock(int b) {
            // ...
            for(int i = bl[b];i<=br[b];i++) {

            }
        }



        void update(int l,int r) {
            if(bi[l]==bi[r]) {
                for(int i = l;i<=r;i++) {
                    a[i] /= i - l + 1;
                }
                updateBlock(bi[r]);
            }else {
                for(int i = l;i<=br[bi[l]];i++){
                    a[i] /= i - l + 1;
                }
                updateBlock(bi[l]);
                for(int b = bi[l] + 1;b < bi[r];b++) {
                    for(int i = bl[b];i<=br[b];i++) {
                        a[i] /= i - l + 1;
                    }
                    updateBlock(b);
                }
                if(bi[r] > bi[l]) {
                    for(int i = bl[bi[r]];i<=r;i++) {
                        a[i] /= i - l + 1;
                    }
                    updateBlock(bi[r]);
                }
            }

        }




        int query(int l,int r) {
            int ans = 0;
            if(bi[l]==bi[r]) {
                for(int i = l;i<=r;i++) {
                    ans ^= a[i];
                }
            }else {
                for(int i = l;i<=br[bi[l]];i++) {
                    ans ^= a[i];
                }
                for(int b = bi[l] + 1;b < bi[r];b++) {
                    ans ^= queryBlock(b);
                }
                if(bi[r] > bi[l]) {
                    for(int i = bl[bi[r]];i<=r;i++) {
                        ans ^= a[i];
                    }
                }
            }
            return ans;
        }

    }
}
