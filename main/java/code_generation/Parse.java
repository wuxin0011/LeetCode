package code_generation;

import java.util.IllegalFormatConversionException;

/**
 * @author: wuxin0011
 * @Description: 根据输入的内容进行解析不同平台对输入输出内容要求和格式不同！
 */
public interface Parse {

    Object parse(String input) throws IllegalFormatConversionException;

}
