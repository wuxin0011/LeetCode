package code_generation.utils;

/**
 * @author: wuxin0011
 * @Description:
 */
public class StringUtils {

    public static boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }

    public static boolean strictIsEmpty(String s) {
        return s == null || s.length() == 0 || "null".equals(s) || "#".equals(s);
    }

    public static boolean strictIsEmpty(String s, String... nullstr) {
        if (isEmpty(s)) {
            return true;
        }
        for (String s1 : nullstr) {
            if (!isEmpty(s1) && s1.equals(s)) {
                return true;
            }
        }
        return false;
    }


    public static boolean isIgnore(char c) {
        return c == '\r' || c == '\n' || c == '\t' || c == '\b' || c == '\f' || c == '\0' || c == '\\' || c == ' ' || c == '\'' || c == '\"';
    }

    public static boolean isIgnoreStrict(char c) {
        return isIgnore(c) || isIgnoreStrict(c, '#');
    }

    public static boolean isIgnoreStrict(char c, char... chars) {
        if (isIgnore(c)) {
            return true;
        }
        for (char c1 : chars) {
            if (c1 == c) {
                return true;
            }
        }
        return false;
    }


    public static String replaceIgnoreContent(String s) {
        if (isEmpty(s)) {
            return s;
        }
        s = s.replace("<div>", "").replace("</div>", "");
        s = s.replace("<ul>", "").replace("</ul>", "");
        s = s.replace("<li>", "").replace("</li>", "");
        s = s.replace("<span>", "").replace("</span>", "");
        s = s.replace("<u>", "").replace("</u>", "");
        s = s.replace("<p>", "").replace("</p>", "");
        s = s.replace("<h>", "").replace("</h>", "");
        return s;
    }

}
