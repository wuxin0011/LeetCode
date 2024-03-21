package code_generation.proxy;

import code_generation.utils.ReflectUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: wuxin0011
 * @Description:
 */
public class InvocationHandlerMethodTime implements InvocationHandler {

    public LogarithmicDevice target;

    public InvocationHandlerMethodTime(LogarithmicDevice target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        Object result = null;
        String info = null;
        String methodInfo = ReflectUtils.getMethodInfo(method);
        if (methodInfo == null || "".equals(methodInfo)) {
            info = ReflectUtils.getClassInfo(target.getClass());
        }
        try {
            System.out.println(info);
            long l1 = System.currentTimeMillis();
            result = method.invoke(target, args);
            long l2 = System.currentTimeMillis();
            System.out.println("\n本次测试耗时 :" + (l2 - l1) + "ms");
        } catch (Exception e) {
            System.err.println("测试失败！");
            e.printStackTrace();
        }
        return result;
    }

    public static void getRunTime(LogarithmicDevice logarithmicDevice) {
        // 创建 InvocationHandler 实例
        InvocationHandler invocationHandler = new InvocationHandlerMethodTime(logarithmicDevice);
        // 创建代理对象
        LogarithmicDevice proxy = (LogarithmicDevice) Proxy.newProxyInstance(
                logarithmicDevice.getClass().getClassLoader(),
                logarithmicDevice.getClass().getInterfaces(),
                invocationHandler
        );
        // 执行代理方法
        proxy.logarithmicDevice();
    }

    public static <T extends LogarithmicDevice> void getRunTime(Class<T> c) {
        try {
            getRunTime(c.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


}
