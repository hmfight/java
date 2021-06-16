package com.wangjia.async.async;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class AsyncDemo {

    public void exec() throws InterruptedException, ExecutionException {
        //进行异步任务列表
        List<FutureTask<Integer>> futureTasks = new ArrayList<>();
        //线程池 初始化十个线程 和JDBC连接池是一个意思 实现重用
        ExecutorService executorService = Executors.newCachedThreadPool();
        ThreadPoolExecutor executorService1 = (ThreadPoolExecutor) executorService;
        executorService1.getActiveCount();
        long start = System.currentTimeMillis();
        //类似与run方法的实现 Callable是一个接口，在call中手写逻辑代码
        Callable<Integer> randomInt = () -> {
            Integer res = new Random().nextInt(100);
//            Thread.sleep(1000);
            System.out.println("任务执行:获取到结果 :" + res);
            long id = Thread.currentThread().getId();
            System.out.println("线程ID : " + id);
            return res;
        };

        for (int i = 0; i < 10; i++) {
            //创建一个异步任务
            FutureTask<Integer> futureTask = new FutureTask<>(randomInt);
            futureTasks.add(futureTask);
            //提交异步任务到线程池，让线程池管理任务 特爽把。
            //由于是异步并行任务，所以这里并不会阻塞
            executorService.submit(futureTask);
        }

        int count = 0;
        for (FutureTask<Integer> futureTask : futureTasks) {
            //futureTask.get() 得到我们想要的结果
            //该方法有一个重载get(long timeout, TimeUnit unit) 第一个参数为最大等待时间，第二个为时间的单位
            count += futureTask.get();
        }
        long end = System.currentTimeMillis();
        System.out.println("线程池的任务全部完成:结果为:" + count + "，main线程关闭，进行线程的清理");
        System.out.println("使用时间：" + (end - start) + "ms");
        //清理线程池
        executorService.shutdown();
    }

    public void exec2() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        long start = System.currentTimeMillis();
        Runnable runnable = () -> System.out.println("thread id :" + Thread.currentThread().getId());
        for (int i = 0; i < 100; i++) {
            executorService.submit(runnable);
        }
        long end = System.currentTimeMillis();
//        System.out.println("线程池的任务全部完成:结果为:" + count + "，main线程关闭，进行线程的清理");
        System.out.println("使用时间：" + (end - start) + "ms");
        //清理线程池
        executorService.shutdown();
    }
}

class NewThread extends Thread {

    @Override
    public void run() {
//        System.out.println(" new :" + value);
        System.out.println("thread id :" + Thread.currentThread().getId());
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("finish");
    }
}
