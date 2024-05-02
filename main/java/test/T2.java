package test;

/**
 * @author: wuxin0011
 * @Description:
 */
public class T2 {

    public static void main(String[] args) throws Exception {
        // Class<T1> t1Class = T1.class;

        for (int i = 0; i < 3; i++) {
            Class<?> a1 = Class.forName(T1.class.getName());
            System.out.println("T1.class.getName():" +T1.class.getName());
        }
        double a = 30.66667;
        double b = 30.666666666666664;
        System.out.println(Double.parseDouble(String.valueOf(a)) == Double.parseDouble(String.valueOf(b)));
        a = Double.parseDouble(String.format("%.5f",a));
        b = Double.parseDouble(String.format("%.5f",b));
        System.out.println(a == b);
        System.out.println(a -b);
    }
}
