package com.wangjia.base.reflect;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

/**
 * @author : wangjia
 * @time : 2018/1/18 14:08
 */

public class ClassA<T> {
    private T obj;

    public void setObject(T obj) {
        this.obj = obj;
    }

    public T getObject() {
        return obj;
    }

    public List<String> getList() {
        return Collections.EMPTY_LIST;
    }

    /**
     * 获取T的实际类型
     */
    public void testClassA() throws NoSuchFieldException, SecurityException {
        System.out.print("getSuperclass:");
        System.out.println(this.getClass().getSuperclass().getName());
        System.out.print("getGenericSuperclass:");
        Type t = this.getClass().getGenericSuperclass();
        System.out.println(t);
        if (ParameterizedType.class.isAssignableFrom(t.getClass())) {
            System.out.print("getActualTypeArguments:");
            for (Type t1 : ((ParameterizedType) t).getActualTypeArguments()) {
                System.out.print(t1.getTypeName() + ",");
            }
            System.out.println();
        }
    }
}