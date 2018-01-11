package com.wj.threadlocal.test;

import com.wj.proxy.targets.Human;
import com.wj.proxy.targets.RobotWorker;
import com.wj.proxy.targets.Teacher;
import com.wj.proxy.targets.Worker;
import com.wj.proxy.jdkdynamic.DyWorkerProxy;
import org.junit.Test;

/**
 * @author : wangjia
 * @time : 2018/1/11 22:27
 */
public class ProxyTest {
    @Test
    public void testDynamicProxy() throws Exception {
        Worker worker = new DyWorkerProxy().bind(new RobotWorker(), Worker.class);
        worker.workPrepare();
    }

    /**
     * jdk 动态代理的
     * ** [DyWorkerProxy 中的代码复用]**
     */
    @Test
    public void testDynamicProxy2() throws Exception {
        Human human = new DyWorkerProxy().bind(new Teacher(), Human.class);
        human.eat();
    }

    /**
     * 静态代理
     */
    @Test
    public void tessStatic() throws Exception {
        Worker worker = new DyWorkerProxy().bind(new RobotWorker(), Worker.class);
        worker.workPrepare();
    }
}
