package com.wuxin.function;

import java.util.Objects;

/**
 * @author: wuxin0011
 * @Description:
 */
@FunctionalInterface
public interface Expect<V, M> {

    void expect(V src, V dist, M message);


    default Expect<V, M> isExpect(Expect<V, M> expect) {
        Objects.requireNonNull(expect);
        return (V v1, V v2, M message) -> {
            if (v1 != null && v1.equals(v2)) {
                System.out.println(message);
            } else {
                System.out.println("test error");
            }
        };
    }
}
