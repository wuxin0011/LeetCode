package code_generation.parse;

import code_generation.Parse;
import code_generation.utils.ReflectUtils;

import java.util.IllegalFormatConversionException;

/**
 * @author: wuxin0011
 * @Description:
 */
public abstract class AbstractParse implements Parse {

    @Override
    public Object parse(String input) throws IllegalFormatConversionException {
        return ReflectUtils.toString(input);
    }
}
