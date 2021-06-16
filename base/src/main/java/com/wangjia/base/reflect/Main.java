package com.wangjia.base.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author : wangjia
 * @time : 2018/1/18 14:24
 */
public class Main {
    public static void main(String[] args) throws NoSuchMethodException {
        ClassA<Integer> integerClassA = new ClassA<>();
        Method method = integerClassA.getClass().getMethod("getList");
        Class<?> returnType = method.getReturnType();
        if (returnType.getName().equals(List.class.getName())) {
            System.out.println("equal");
        }
        Type genericReturnType = method.getGenericReturnType();
        if (genericReturnType.equals(List.class)) {
            System.out.println("equal");
        }
        System.out.println(genericReturnType.getTypeName());

        //获取返回值的泛型参数

        if (genericReturnType instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) genericReturnType).getActualTypeArguments();
            for (Type type : actualTypeArguments) {
                System.out.println(type);
            }
        }
    }

}
