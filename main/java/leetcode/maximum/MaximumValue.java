package leetcode.maximum;

/**
 * @author: wuxin0011
 * @Description:
 */
public class MaximumValue {


    public static void main(String[] args) {

        // String[] strings = {"1", "01", "001", "0001"};
        // String[] strings = {"alic3","bob","3","4","00000"};
        String[] strings = {"0392", "c2bs", "yz5"};
        // String[] strings = {"88146", "z5", "4jj0zv37", "42", "1", "13428", "4841", "988", "2x85ssoau", "12706558", "cd5", "jy5uhg", "l1yd", "0cjic9g", "369466", "657059", "wjkzgyepz", "7", "0419961", "38589819", "kvx", "53439644", "ss1c9b8m", "n", "75ow", "445485083", "8pgg", "78758125", "febk0", "4l", "76", "fiadsf8y", "78258", "54132344", "59816956", "6lmqdeq", "6mb38", "46143461", "8353", "7844", "l3lj4", "16", "52197632", "sgabo", "712668", "355488", "lz9aanoe", "8443541", "2759387", "rpavtw627", "t5opxpco", "4sg6", "pdh", "b8v0", "1rz", "3", "013", "39863", "okq9xi2r", "qpvu", "hxr5a", "81920", "4bgysj26c", "986", "r", "lif1hj", "94881", "6287733", "cfanahk0k", "d", "8c", "730", "x9", "8", "6454949", "7c", "772990380", "262", "424110059", "3nyxiuk4", "ba9timos", "6", "60", "8757782"};
        MaximumValue maximumValue = new MaximumValue();
        System.out.println("max = " + (maximumValue.maximumValue(strings)));
    }

    public int maximumValue(String[] strs) {
        int max = 0;
        for (String str : strs) {
            max = Math.max(max, getStrNum(str));
        }
        return max;
    }


    public static int getStrNum( String str) {
        if (isOnlyNum(str)) {
            int result = 0;
            int m = 1;
            for (int i = 0; i < str.length(); i++) {
                if (getCharNum(str.charAt(i)) != 0) {
                    for (int j = str.length() - 1; j >= i; j--) {
                        result = result + getCharNum(str.charAt(j)) * m;
                        m = m * 10;
                    }
                    return result;
                }
            }
            return 0;

        } else {
            return str.length();
        }
    }

    public static boolean isOnlyNum(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z')) {
                return false;
            }
        }
        return true;
    }

    public static int getCharNum(char c) {
        return c - '0';
    }


}
