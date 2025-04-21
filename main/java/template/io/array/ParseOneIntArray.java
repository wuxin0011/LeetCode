package template.io.array;

/**
 * @author: wuxin0011
 * @Description:
 */
public class ParseOneIntArray {

    public static int MAX = 101;
    private static int[] arr = new int[MAX];
    private static int size;


    public static void parseIntArray(String s) {
        size = 0;
        int base = 0;
        int deep = 0;
        int neg = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '\n' || c == '\t' || c == '\f' || c == '\r' || c == ' ')
                continue;
            switch (c) {
                case '[':
                    base = 0; // 初始化
                    deep++;
                    break;
                case ']':
                    deep--;
                    if (deep == 0) {
                        if (neg > 0) {
                            base *= -1;
                            neg--;
                        }
                        arr[size++] = base;
                    }

                    break;
                case '-':
                    neg++;
                    break;
                case ',':
                    if (neg > 0) {
                        base *= -1;
                        neg--;
                    }
                    arr[size++] = base;

                    base = 0;
                    break;
                default:
                    if( c >= '0' && c <= '9') {
                        base = base * 10 + c - '0';
                    }

                    break;
            }
        }
    }


    public static void printOneIntArray() {
        System.out.print("[ ");
        for(int i = 0;i<size;i++) {
            System.out.print(arr[i]);
            if( i != size -1 ) {
                System.out.print(" ,");
            }
        }
        System.out.print(" ]\n");
    }

    public static void main(String[] args) {
        String tartetString = "[1,2,31000,40,-115]";
        parseIntArray(tartetString);
        printOneIntArray();
    }





    static void readInfo() {

    }
}
