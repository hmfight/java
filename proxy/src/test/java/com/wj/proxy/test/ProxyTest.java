package com.wj.proxy.test;

import com.wj.proxy.cglib.CglibCommonProxy;
import com.wj.proxy.jdkdynamic.JdkCommonProxy;
import com.wj.proxy.targets.Human;
import com.wj.proxy.targets.RobotWorker;
import com.wj.proxy.targets.Teacher;
import com.wj.proxy.targets.Worker;
import com.wj.proxy.utils.ProxyUtils;
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
        Worker worker = (Worker) new JdkCommonProxy().bind(new RobotWorker());
        worker.workPrepare();
        ProxyUtils.generateClassFile(worker.getClass(), "ReadWorkerProxy");
    }

    @Test
    public void testDynamicProxy() throws Exception {
        Worker worker = (Worker) new JdkCommonProxy().bind(new RobotWorker());
        worker.workPrepare();

        Worker worker2 = (Worker) new JdkCommonProxy().bind(new RobotWorker());
        worker2.workPrepare();
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
    @Test
    public void testCglib() throws Exception {
        CglibCommonProxy cglib = new CglibCommonProxy();
        RobotWorker worker = (RobotWorker) cglib.getInstance(new RobotWorker());
        worker.workPrepare();
    }

    /**
     * cglib
     */
    @Test
    public void testCglib2() throws Exception {
        CglibCommonProxy cglib = new CglibCommonProxy();
        Teacher teacher = (Teacher) cglib.getInstance(new Teacher());
        teacher.eat();
    }
}
