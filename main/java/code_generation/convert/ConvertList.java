package code_generation.convert;

import java.util.List;

/**
 * @author: wuxin0011
 * @Description:
 */
public interface ConvertList<S extends List<?>, T> extends Convert<T> {


    default S parseList(String input) {
        return parseList(input, '[', ']', ',');
    }


    /**
     * 将输入内容按照指定格式解析
     *
     * @param input         输入的字符串
     * @param startFlag     开始的标志
     * @param endFlag       结束的标志
     * @param interruptFlag 中断的标志
     * @return T
     */
    S parseList(String input, char startFlag, char endFlag, char interruptFlag);

}
