package com.wj.proxy.pstatic;

import com.wj.proxy.targets.RobotWorker;
import com.wj.proxy.targets.Worker;

/**
 * @author : wangjia
 * @time : 2018/1/11 21:06
 * 静态代理
 */
public class WorkerProxy implements Worker {
    private RobotWorker robotWorker;

    public WorkerProxy(RobotWorker robotWorker) {
        this.robotWorker = robotWorker;
    }

    @Override
    public void workPrepare() {
        System.out.println("before");
        robotWorker.workPrepare();
        System.out.println("after");
    }

}
