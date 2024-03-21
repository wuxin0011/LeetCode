package code_generation.parse;

import code_generation.utils.ReflectUtils;

/**
 * @author: wuxin0011
 * @Description:
 */
public interface ParseList<T> extends Parse<T> {

    @Override
    default T parse(String input) {
        return convert(input);
    }

    default T convert(String input) {
        return convert(ReflectUtils.toString(input), '[', ']', ',');
    }


    /**
     * 例如数组或者List格式如下
     * [1,2,3,4,4] => start = [  end = ] flag = ,
     * 规定了将输入内容按照指定格式转换
     *
     * @param input 输入的内容
     * @param start 开始标志
     * @param end   结束标志
     * @param flag  中间判断结果的标志符
     * @return T
     */
    default T convert(String input, char start, char end, char flag) {
        return null;
    }
}
