package template.array.lis;

/**
 * @author: wuxin0011
 * @Description: 最长递增子序列 (贪心 + 二分）
 */
@SuppressWarnings("warning")
public class LIS {

    public static int[] lis(int[] a) {
        int n = a.length;
        int[] g = new int[n];
        int[] f = new int[n];
        int size = 0;
        for(int i = 0;i < n;i++) {
            int j = lowerbound(f,a[i]);
            if(j == size) {
                f[size++] = a[i];
            }else{
                f[j] = a[i];
            }
            g[i] = j + 1;
        }
        return g;
    }

    public static int lowerbound(int[] a,int t){
        int l = 0,r = a.length - 1;
        while(l<=r){
            int mid = l + ((r - l)>>1);
            if(a[mid] >=t) r = mid - 1;
            else l = mid + 1;
        }
        return l;
    }
}
