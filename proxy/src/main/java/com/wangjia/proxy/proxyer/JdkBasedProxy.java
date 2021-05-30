package com.wangjia.proxy.proxyer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author : wangjia
 * @time : 2018/1/11 19:35
 * JDK 动态代理
 */
public class JdkBasedProxy implements InvocationHandler {
    /**
     * 被代理对象
     */
    private Object target;

    public Object bind(Object target) {
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        doBefore();
        Object invokeRes = method.invoke(target, args);
        doAfter();
        return invokeRes;
    }

    private void doBefore() {
        System.out.println("***********before***********");
    }

    private void doAfter() {
        System.out.println("**********after************");
    }

}
