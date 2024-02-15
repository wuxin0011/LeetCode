package leetcode.pascals_triangle;

import java.util.ArrayList;
import java.util.List;

/**
 * 杨辉三角
 *
 * @author: wuxin0011
 * @Description: 观察排列规律
 * @see https://leetcode.cn/problems/pascals-triangle/
 */
public class PascalsTriangle {

    public static void main(String[] args) {

        System.out.println(new PascalsTriangle().generate(4));
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> arrayLists = new ArrayList<>();
        if (numRows == 0) {
            return arrayLists;
        }
        ArrayList<Integer> caches = new ArrayList<>();
        for (int row = 1; row < numRows + 1; row++) {
            ArrayList<Integer> arr = new ArrayList<>();

            for (int column = 0; column < row; column++) {
                int result = 0;
                // 当前行 i
                if (column == 0) {
                    result = 1;
                } else if (column == row - 1) {
                    result = 1;
                } else {
                    // 上一行 当前行 为 i -1
                    int left = caches.get(column-1);
                    int right = caches.get(column);
                    result = left + right;
                }
                arr.add(result);
            }
            arrayLists.add(arr);
            caches = arr;

        }
        return arrayLists;
    }

}
