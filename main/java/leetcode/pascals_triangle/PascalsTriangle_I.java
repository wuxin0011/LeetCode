package leetcode.pascals_triangle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wuxin0011
 * @Description: 杨辉三角二 利用懒加载方式缓存题目要求的最大结果 后续取值直接通过下标取值就ok了
 */
public class PascalsTriangle_I {

    // 0 <= rowIndex <= 33
    public static void main(String[] args) {
        System.out.println(new PascalsTriangle_I().getRow(3));

        for (List<Integer> row : rows) {
            System.out.println(row);
        }
    }

    private static final List<List<Integer>> rows = new ArrayList<>();


    public List<Integer> getRow(int rowIndex) {
        if (rowIndex >= 34) {
            return new ArrayList<>();
        }

        if (rows.size() == 0) {
            int result = 0;
            // 从 1 开始是因为第零 行为0
            for (int row = 1; row <= 35; row++) {
                List<Integer> integers = new ArrayList<>();
                for (int column = 0; column < row - 1; column++) {
                    // 左右边界 为1
                    //  因为 row是从一开始的 所以 右边边界是 row - 2
                    if (column == 0 || column == row - 2) {
                        result = 1;
                    } else {
                        //
                        result = rows.get(row - 2).get(column - 1) + rows.get(row - 2).get(column);
                    }
                    integers.add(result);
                }
                rows.add(integers);
            }
            rows.remove(0);
        }
        return rows.get(rowIndex);
    }
}
