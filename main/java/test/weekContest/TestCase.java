package test.weekContest;

import code_generation.crwal.leetcode.LCTestCase;
import code_generation.utils.StringUtils;

/**
 * @author: wuxin0011
 * @Description:
 */
public class TestCase {
//
//    static  {
//        System.out.println("static init ...");
//    }
//    {
//        System.out.println("obj init before");
//    }

    public static void main(String[] args) {
        int[][] classias = {{1, 2}, {1, 5}};
        int ext = 1;
//        System.out.println(avage(classias));
//        classias[0][0] += 1;
//        classias[0][1] += 1;
//        System.out.println(avage(classias));
//        classias[0][0] -= 1;
//        classias[0][1] -= 1;
//        classias[1][0] += 1;
//        classias[1][1] += 1;
//        System.out.println(avage(classias));
//        System.out.println(avage(classias,-1,0));
//        System.out.println(avage(classias,0,1));
//        System.out.println(avage(classias,1,1));
//        System.out.println(avage(classias,0,2));
//        System.out.println(avage(classias,1,2));

        testInput();
    }

    public static double avage(int[][] classias) {
        double tot = 0;
        int n = classias.length;
        for (int[] c : classias) {
            tot += c[0] * 1.0 / c[1];
        }
        return tot / n;
    }

    public static double avage(int[][] classias, int i, int total) {
        double tot = 0;
        int n = classias.length;
        for (int j = 0; j < classias.length; j++) {
            if (j == i) {
                classias[i][0] += total;
                classias[i][1] += total;
            }
            tot += 1.0 * classias[j][0] / classias[j][1];
            if (i == j) {
                classias[i][0] -= total;
                classias[i][1] -= total;
            }
        }
        return tot / n;
    }

    public static void testInput() {
        String input = " <span class=\"example-io\" style=\"\n" +
                "    font-family: Menlo,sans-serif;\n" +
                "    font-size: 0.85rem;\n" +
                "\">nums1 = [2,6,4], nums2 = [9,7,5]</span></p>\n" +
                "\n" +
                "<p>";
        String s = LCTestCase.matchExample(input);
        System.out.println(s);
        input = " <span class=\"example\" style=\"\n" +
                "    font-family: Menlo,sans-serif;\n" +
                "    font-size: 0.85rem;\n" +
                "\">nums1 = [2,6,4], nums2 = [9,7,5]</span></p>\n" +
                "\n" +
                "<p>";
        s = LCTestCase.matchExample(input);
        System.out.println(s);

        // s = "\\135\\\\n";
        // System.out.println();
        System.out.println("origin str : \n");
        System.out.println(input);
        System.out.println("update str : \n");
        System.out.println(StringUtils.removeTag(StringUtils.removeTag(input, "span"), "p"));

    }

}
