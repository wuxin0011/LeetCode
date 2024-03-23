package code_generation.convert;

import java.lang.reflect.Array;
import java.util.IllegalFormatConversionException;
import java.util.List;

public interface ConvertArray<S, T> extends ConvertList<List<S>, T[]> {

    @Override
    default T[] convert(String input) throws IllegalFormatConversionException {
        List<S> s = parseList(input);
        T[] ans = newArray(s.size());
        for (int i = 0; i < s.size(); i++) {
            ans[i] = (T) s.get(i);
        }
        return ans;
    }

    @Override
    default List<S> parseList(String input) {
        return ConvertList.super.parseList(input);
    }

    @Override
    default List<S> parseList(String input, char startFlag, char endFlag, char interruptFlag) {
        return null;
    }

    @SuppressWarnings("unchecked")
    default T[] newArray(int length) {
        return (T[]) Array.newInstance(Object.class, length); // 替换Object.class为实际类型的Class对象
    }
}
