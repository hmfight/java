package com.wangjia.proxy.targets;

/**
 * @author : wangjia
 * @time : 2018/1/11 19:38
 */
public class Seller12306 implements TicketSeller {

    @Override
    public void query() {
        System.out.println("您好，您回家的票还有……1张");
    }

    @Override
    public void sell() {
        System.out.println("您已购买回家的票！");
    }

}
