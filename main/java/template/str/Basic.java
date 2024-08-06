package template.str;

/**
 * @author: wuxin0011
 * @Description: 字符串中常见函数判断
 */
public class Basic {


    // 判断元音字符(含大写）
    public static boolean isVol(char c) {
        if ('A' <= c && c <= 'Z') {
            c = (char) (c - 'A' + 'a');
        }
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }


}
