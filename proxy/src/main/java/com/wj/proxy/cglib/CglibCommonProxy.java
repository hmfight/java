package com.wj.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author : wangjia
 * @time : 2018/1/11 19:35
 * JDK 动态代理
 */
public class CglibCommonProxy implements MethodInterceptor {

    /**
     * 被代理对象，供代理方法中进行真正的业务方法调用
     */
    private Object target;

    //相当于JDK动态代理中的绑定
    @SuppressWarnings("unchecked")
    public Object getInstance(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        //设置回调：对于代理类上所有方法的调用，都会调用CallBack，而Callback则需要实现intercept()方法进行拦
        enhancer.setCallback(this);
        // 创建动态代理类对象并返回
        return enhancer.create();
    }

    // 实现回调方法
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        doBefore();
        //调用业务类（父类中）的方法
        Object result = proxy.invokeSuper(obj, args);
        doAfter();
        return result;
    }

    private void doAfter() {
        System.out.println("**********************");
    }

    private void doBefore() {
        System.out.println("**********************");
    }

}
