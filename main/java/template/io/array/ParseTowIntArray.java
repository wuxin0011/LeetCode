package template.io.array;

/**
 * @author: wuxin0011
 * @Description:
 */
public class ParseTowIntArray {
    public static int MAX_ROW = 100;
    public static int MAX_COL = 100;
    private static int[][] arr = new int[MAX_ROW][MAX_COL];
    private static int row = 0;
    private static int col = 0;
    private static int max_col_row = 0;

    public static void parseTwoArray(String s) {
        row = 0;
        col = 0;
        if (s == null || s.length() == 0) {
            return;
        }

        int deep = 0, neg = 0, base = 0, n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == ' ' || c == '\n' || c == '\r' || c == '\f' || c == '\b') {
                continue;
            }
            switch (c) {
                case '[':
                    deep++;
                    base = 0;
                    col = 0;
                    break;
                case ']':
                    deep--;
                    if( deep == 0) break;
                    if (neg > 0) {
                        base *= -1;
                        neg--;
                    }
                    arr[row++][col++] = base;
                    max_col_row = Math.max(max_col_row,col);
                    base = 0;
                    break;
                case '-':
                    neg++;
                    break;
                case ',':
                    if( deep != 2 ) {
                        break;
                    }
                    if (neg > 0) {
                        base *= -1;
                        neg--;
                    }
                    arr[row][col++] = base;
                    base = 0;
                    break;

                default:
                    if (c >= '0' && c <= '9') {
                        base = base * 10 + c - '0';
                    }
                    break;
            }
        }

    }

    static void printArray() {
        System.out.println("**********************************");
        if( max_col_row != col ) {
            System.out.println("parse error ! max_col_row length != col ");
            return;
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col ; j++) {
                if (j == 0) {
                    System.out.print("[ ");
                }
                System.out.print(arr[i][j]);
                if (j != col-1) {
                    System.out.print(" , ");
                }

                if (j == col-1) {
                    System.out.print(" ]");
                }
            }
            System.out.println();
        }
        System.out.println("**********************************");
    }

    public static void main(String[] args) {
        String string = "["
                + "[1,2,3],"
                + "[1,2,-100],"
                + "[1,-2,-3],"
                + "[-1,2,-3],"
                + "[-1,-2,-3]"
                + ",[1,-2,3],"
                + "[100,200,300]"
                + "]";
        parseTwoArray(string);
        printArray();
    }

}
