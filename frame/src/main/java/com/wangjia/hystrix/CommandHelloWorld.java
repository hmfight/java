package com.wangjia.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;

public class CommandHelloWorld extends HystrixCommand<String> {

    private static int i = 0;
    private final String name;

    public CommandHelloWorld(String name) {
//        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("orderService"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("queryByOrderId"))
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter()
                                .withCircuitBreakerRequestVolumeThreshold(10)
                                //熔断器中断请求5秒后会进入半打开状态,放部分流量过去重试
                                .withCircuitBreakerSleepWindowInMilliseconds(5000)
                                .withExecutionTimeoutEnabled(true)
                                .withExecutionTimeoutInMilliseconds(1)
                                //错误率达到50开启熔断保护
                                .withCircuitBreakerErrorThresholdPercentage(50)
                                .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)
                                //最大并发请求量
                                .withExecutionIsolationSemaphoreMaxConcurrentRequests(10)));

        this.name = name;
    }

    @Override
    protected String run() throws InterruptedException {
        if (i > 100 && i < 120) {
            Thread.sleep(100);
            i++;
        } else {
            i++;
            return "Hello " + i + "!" + System.currentTimeMillis() / 1000;
        }
        return "Hello " + i + "!" + System.currentTimeMillis() / 1000;
    }

    @Override
    protected String getFallback() {
        i++;
        return "fallback" + i + "" + System.currentTimeMillis() / 1000;
    }
}