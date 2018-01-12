package com.wj.proxy.jdkdynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author : wangjia
 * @time : 2018/1/11 19:35
 * JDK 动态代理
 */
public class JdkCommonProxy implements InvocationHandler {
    private Object target;

    @SuppressWarnings("unchecked")
    public <T> T bind(Object target, Class<T> interfaces) {
        this.target = target;
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                new Class[]{interfaces}, this);
    }

    public Object bind(Object target) {
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        doBefore();
        Object invoke = method.invoke(target, args);
        doAfter();
        return invoke;
    }

    private void doAfter() {
        System.out.println("common after");
    }

    private void doBefore() {
        System.out.println("common before");
    }


}
