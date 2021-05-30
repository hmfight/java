package com.wangjia.proxy.test;

import com.wangjia.proxy.cglib.CglibProxy;
import com.wangjia.proxy.jdkdynamic.JdkCommonProxy;
import com.wangjia.proxy.targets.Human;
import com.wangjia.proxy.targets.Seller12306;
import com.wangjia.proxy.targets.Teacher;
import com.wangjia.proxy.targets.TicketSeller;
import com.wangjia.proxy.utils.ProxyUtils;
import org.junit.Test;

/**
 * @author : wangjia
 * @time : 2018/1/11 22:27
 */
public class ProxyTest {
    /**
     * 静态代理
     */
    @Test
    public void tessStatic() throws Exception {
        TicketSeller seller = (TicketSeller) new JdkCommonProxy().bind(new Seller12306() {
        });
        seller.query();
        ProxyUtils.generateClassFile(seller.getClass(), "ReadWorkerProxy");
    }

    @Test
    public void testDynamicProxy() throws Exception {
        TicketSeller worker = (TicketSeller) new JdkCommonProxy().bind(new Seller12306());
        worker.query();

        TicketSeller worker2 = (TicketSeller) new JdkCommonProxy().bind(new Seller12306());
        worker2.query();
    }

    /**
     * jdk 动态代理的
     * ** [JdkCommonProxy 中的代码复用]**
     */
    @Test
    public void testDynamicProxy2() throws Exception {
        Human human = (Human) new JdkCommonProxy().bind(new Teacher());
        human.eat();
//        ProxyUtils.generateClassFile(human.getClass(), "ReadHumanProxy");
    }

    /**
     * cglib
     */
//    @Test
//    public void testCglib() throws Exception {
//        CglibProxy cglib = new CglibProxy();
//        RobotWorker worker = (RobotWorker) cglib.getInstance(new Ti());
//        worker.work();
//    }

    /**
     * cglib
     */
    @Test
    public void testCglib2() throws Exception {
        CglibProxy cglib = new CglibProxy();
        Teacher teacher = (Teacher) cglib.getInstance(new Teacher());
        teacher.eat();
    }
}
