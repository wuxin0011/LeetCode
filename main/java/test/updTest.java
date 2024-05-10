package test;

import code_generation.utils.IoUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wuxin0011
 * @Description:
 */
public class updTest {

    public static void main(String[] args) {
        IoUtil.upd(updTest.class, "a", updTest.class, "b", 10, 10, null, 0);
    }

    public static int a(int i, ArrayList<int[]> arr) {
        return 0;
    }

    public static int b(int i, List<int[]> arr) {
        return 0;
    }
}
