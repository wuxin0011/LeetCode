package code_generation.parse.string;

import code_generation.parse.Parse;
import code_generation.utils.ReflectUtils;

import java.util.IllegalFormatConversionException;

/**
 * @author: wuxin0011
 * @Description:
 */
public class ParseString implements Parse<String> {

    @Override
    public String parse(String input) throws IllegalFormatConversionException {
        return ReflectUtils.toString(input);
    }
}
