package template.array;

import java.util.Random;

/**
 * @author: wuxin0011
 * @Description: 网格图方向
 */
public class GridDirect {
    // 八个方向
    static class A {


        static void test() {
            // 一般变量定义
            int i = 0, j = 0;
            int m = 100, n = 100;


            int[][] dirs = {
                    {-1, -1}, {-1, 0}, {-1, 1},
                    {0, -1}, {0, 1},
                    {1, -1}, {1, 0}, {1, 1},
            };

            for (int[] dir : dirs) {
                int x = dir[0] + i;
                int y = dir[1] + j;
                if (x >= 0 && x < m && y >= 0 && y < n) {
                    // todo
                }
            }
        }

    }

    // 四个方向
    static class B {

        // 写法1
        static int[][] dirs2 = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};


        // 写法二


        static void test() {
            int i = 0, j = 0;
            int m = 100, n = 100;


            int[] dirs = {-1, 0, 1, 0, -1};
            for (int k = 0; k <= 3; k++) {
                int x = dirs[k] + i;
                int y = dirs[k + 1] + j;
                if (x >= 0 && x < m && y >= 0 && y < n) {
                    // todo
                }
            }
        }
    }



    // 四个方向 顺时针 逆时针
    // https://leetcode.cn/problems/EXvqDp/description/
    static class C {

        static int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        static void test(int direct) {
            int i = 0, j = 0;
            int m = 100, n = 100;

            // 假设v是改变方向的值
            int v = new Random().nextInt(4);

            if(v == 0){
                // 不变
            }else if(v == 1){
                // 逆时针
                direct = (direct + 3) % 4;
            }else if(v == 2){
                // 顺时针
                direct = (direct + 1) % 4;
            }

            // 改变方向
            i += dirs[direct][0];
            j += dirs[direct][1];


        }
    }


}
