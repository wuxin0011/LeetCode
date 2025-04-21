package template.tips;

import java.util.Arrays;

/**
 * @author: wuxin0011
 * @Description: 离散化 常见于比较大小关系 （值域过大）
 */
public class Tips_1 {


    public static void f0(int[] a) {
        int[] b = Arrays.copyOf(a,a.length);
        Arrays.sort(b);
        int size = 1;
        for(int i = 1;i < b.length;i++) {
            if(b[i] != b[size-1]) b[size++] = b[i];
        }
        // 通过k表示原始数值a[i]
        for(int i = 0;i < a.length;i++) {
            int k = lowerbound(b,0,size - 1,a[i]);
            // todo...
        }
    }

    public static int lowerbound(int[] a,int l,int r,int x) {
        while(l <= r) {
            int mid = l + ((r - l)>>1);
            if(a[mid]>=x)r = mid - 1;
            else l = mid + 1;
        }
        return l;
    }
}
