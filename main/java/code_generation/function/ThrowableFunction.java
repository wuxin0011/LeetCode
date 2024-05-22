package code_generation.function;

/**
 * @author: wuxin0011
 * @Description:
 */
@FunctionalInterface
public
interface ThrowableFunction{
    void apply() throws Exception;
}