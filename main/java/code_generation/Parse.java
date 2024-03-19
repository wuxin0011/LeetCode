package code_generation;

import java.util.IllegalFormatConversionException;

/**
 * @author: wuxin0011
 * @Description: 根据输入的内容进行解析不同平台对输入输出内容要求和格式不同！
 */
public interface Parse {


    /**
     * 根据输入的内容，不同数据按照不同解析解析
     * 例如有的平台数组 [1,2,3] 有的却是 {1,2,3}
     *
     * @param input 输入的内容
     * @return 返回解析后的结果
     * @throws IllegalFormatConversionException 解析格式转换异常
     */
    Object parse(String input) throws IllegalFormatConversionException;

}
