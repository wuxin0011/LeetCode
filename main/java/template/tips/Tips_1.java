package template.tips;

import java.util.Arrays;
import java.util.List;

/**
 * @author: wuxin0011
 * @Description: 离散化 常见于比较大小关系 （值域过大）
 */
public class Tips_1 {


    static class T0 {
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


    // 离散化基本模板
    // 当值域过大或者map常数过大或许可以考虑试试离散化当map
    static class LB {
        int[] a;
        LB(int[] array) {
            this.init(Arrays.copyOf(array,array.length));
        }
        LB(List<Integer> lists) {
            this.init(lists.stream().mapToInt(xxx-> xxx).toArray());
        }
        void init(int[] temp) {
            Arrays.sort(temp);
            int size = 1;
            for(int i = 1;i<temp.length;i++) {
                if(temp[i]!=temp[i-1]){
                    temp[size++]=temp[i];
                }
            }
            this.a = new int[size];
            System.arraycopy(temp, 0, a, 0, size);
        }
        public int getId(int x) {
            int l = 0,r = a.length - 1;
            while(l<=r){
                int mid = l + ((r - l)>>1);
                if(a[mid]>=x){
                    r = mid - 1;
                }else{
                    l = mid + 1;
                }
            }
            return l;
        }
    }


    static class LB2 {
        long[] a;
        LB2(long[] array) {
            this.init(Arrays.copyOf(array,array.length));
        }
        LB2(List<Long> lists) {
            this.init(lists.stream().mapToLong(xxx-> xxx).toArray());
        }
        void init(long[] temp) {
            Arrays.sort(temp);
            int size = 1;
            for(int i = 1;i<temp.length;i++) {
                if(temp[i]!=temp[i-1]){
                    temp[size++]=temp[i];
                }
            }
            this.a = new long[size];
            System.arraycopy(temp, 0, a, 0, size);
        }



        public int getId(long x) {
            int l = 0,r = a.length - 1;
            while(l<=r){
                int mid = l + ((r - l)>>1);
                if(a[mid]>=x){
                    r = mid - 1;
                }else{
                    l = mid + 1;
                }
            }
            return l;
        }
    }
}
