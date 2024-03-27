package code_generation.contest;

/**
 * @author: wuxin0011
 * @Description:
 */
public interface DefaultProblem {

    default void start(Class<?> c) {
        start(c, false);
    }

    public void start(Class<?> c, boolean input);
}
