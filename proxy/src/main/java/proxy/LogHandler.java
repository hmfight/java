package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * author  : wangjia
 * time    : 2016/12/2 17:08
 * contact : wangjia_yql@qq.com
 * desc    : 日志代理
 */
public class LogHandler implements InvocationHandler {
    private Object target;

    public Object newProxyInstance(Object proxy) throws Throwable {
        this.target = proxy;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("start-->>");
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                System.out.println(args[i]);
            }
        }
        Object ret = null;
        try {
            System.out.println("start-->>");
            ret = method.invoke(target, args);
            System.out.println("success-->>");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error-->>");
            throw e;
        }
        return ret;
    }
}
