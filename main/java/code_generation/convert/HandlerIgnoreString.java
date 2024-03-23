package code_generation.convert;

import java.util.IllegalFormatConversionException;

/**
 * @author: wuxin0011
 * @Description:
 */
@SuppressWarnings("all")
public abstract class HandlerIgnoreString {

    public String ignore(String input) {
        return before(input);
    }

    private static String before(String input) throws IllegalFormatConversionException {
        if (input == null || input.length() == 0) {
            throw new NullPointerException("input content is null");
        }
        char[] charArray = input.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : charArray) {
            if (isIgnore(c)) continue;
            sb.append(c);
        }
        return sb.toString();
    }

    public static boolean isIgnore(char c) {
        return c == '\r' || c == '\n' || c == '\t' || c == '\b' || c == '\f' || c == '\0' || c == '\\' || c == ' ' || c == '\'' || c == '\"' || c == '#';
    }
}
