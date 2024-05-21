package test.acmtest;

import code_generation.utils.ACM.IO;

/**
 * @author: wuxin0011
 * @Description:
 */
public class A {
    static IO io;

    public static void main(String[] args) {
        t();
    }

    public static void t() {
        io = new IO();
        int a = io.readInt();
        int b = io.readInt();
        io.println(a * b);
        io.close();
    }


}
