package com.wj.proxy.proxyer;

import com.wj.proxy.targets.Seller12306;
import com.wj.proxy.targets.TicketSeller;

/**
 * @author : wangjia
 * @time : 2018/1/29 22:09
 */
public class MeiTuanSeller implements TicketSeller {
    private Seller12306 seller12306;

    public MeiTuanSeller(Seller12306 seller12306) {
        this.seller12306 = seller12306;
    }

    @Override
    public void query() {
        System.out.println("***********before***********");
        seller12306.query();
        System.out.println("***********after************");
    }

    @Override
    public void sell() {
        System.out.println("***********before***********");
        seller12306.sell();
        System.out.println("***********after************");
    }
}
