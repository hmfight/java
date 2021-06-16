package com.wangjia.spring.lifecycle;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author : wj
 * @date : 2021/5/30 23:59
 */
@Data
public class PetUser implements InitializingBean, BeanNameAware, DisposableBean, ApplicationContextAware {

    private Address address;
    @Autowired
    private Sex sex;

    public PetUser() {
        System.out.println("construct PetUser(name)");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("BeanNameAware invoked");

    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean.destroy() invoked");

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean.afterPropertiesSet() invoked");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("ApplicationContextAware.setApplicationContext invoked");
    }

    @Override
    public String toString() {
        return "PetUser{" +
                "address=" + address +
                ", sex=" + sex +
                '}';
    }
}
