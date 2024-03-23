package code_generation.function;

/**
 * @author: wuxin0011
 * @Description:
 */
@FunctionalInterface
public interface Expect<V, M> {

    void expect(V src, V dist, M message);

}
