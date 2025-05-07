package template.bit;

/**
 * @author: wuxin0011
 * @Description:
 */
public class Base {

    // 这个函数是方便打表
    // 以对齐方式打印int[] 的中二进制数据
    public static String printBinaryToString(int...args) {
        int mx = 0;
        for(int x : args) if(mx < x) mx = x;
        // List<String> list = new ArrayList<>();
        int n = 0;
        for(int i = 0,v = 0;i < 32 && v < mx;i++) {
            v |= (1 << i);
            n++;
        }
        String S = "";
        for(int x : args) {
            String s = "";
            for(int i = 0;i < n;i++) {
                s = (x >> i & 1) + s;
            }
            S += s;
            S += "\n";
        }
        System.out.println(S);
        return S;
    }


}
