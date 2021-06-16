package com.wangjia.concurrent.pool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String args[]) throws Exception {
        test2();
    }

    public static void test2() throws Exception {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(1);
        executor.setMaxPoolSize(3);
        executor.setKeepAliveSeconds(5);
        executor.setQueueCapacity(5);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        executor.initialize();
        ThreadPoolExecutor threadPoolExecutor = executor.getThreadPoolExecutor();
        for (int i = 0; i < 10; i++) {
            try {
                executor.execute(new MyTask(i));
                System.out.println("线程池中线程数目：" + executor.getPoolSize() + "，队列中等待执行的任务数目：" +
                        threadPoolExecutor.getQueue().size() + "，已执行玩别的任务数目：" + threadPoolExecutor.getCompletedTaskCount());
            } catch (TaskRejectedException e) {
                System.out.println("reject");
            }
        }
        while (true) {
            Thread.sleep(1000);
            System.out.println("线程池中线程数目：" + executor.getPoolSize() + "，队列中等待执行的任务数目：" +
                    threadPoolExecutor.getQueue().size() + "，已执行玩别的任务数目：" + threadPoolExecutor.getCompletedTaskCount());
        }
    }

    public static void test() throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 4, 200, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(10));
        for (int i = 0; i < 15; i++) {
            executor.execute(new MyTask(i));
            System.out.println("线程池中线程数目：" + executor.getPoolSize() + "，队列中等待执行的任务数目：" +
                    executor.getQueue().size() + "，已执行玩别的任务数目：" + executor.getCompletedTaskCount());
        }
        System.out.println("fin");
        while (true) {
            Thread.sleep(1000);
            System.out.println("线程池中线程数目：" + executor.getPoolSize() + "，队列中等待执行的任务数目：" +
                    executor.getQueue().size() + "，已执行玩别的任务数目：" + executor.getCompletedTaskCount());
        }
    }
}