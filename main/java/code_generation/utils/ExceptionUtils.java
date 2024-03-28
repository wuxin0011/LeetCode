package code_generation.utils;

import code_generation.function.ThrowableFunction;

import javax.sql.rowset.Predicate;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author: wuxin0011
 * @Description:
 */
public class ExceptionUtils {




    public static void executeWithExceptionHandling(ThrowableFunction function) {
        executeWithExceptionHandling(function, false);
    }

    public static void executeWithExceptionHandling(ThrowableFunction function, boolean isIgnore) {
        try {
            function.apply();
        } catch (Exception e) {
            if (!isIgnore) {
                e.printStackTrace();
            }

        }
    }


    public static void sleep(int s) {
        executeWithExceptionHandling(() -> Thread.sleep((long) (Math.random() * 1000 * s)));
    }
}
