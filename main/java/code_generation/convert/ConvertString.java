package code_generation.convert;

import java.util.IllegalFormatConversionException;

/**
 * @author: wuxin0011
 * @Description:
 */
@SuppressWarnings("all")
public class ConvertString extends HandlerIgnoreString implements Convert<String> {

    @Override
    public String convert(String input) throws IllegalFormatConversionException {
        return ignore(input);
    }
}
