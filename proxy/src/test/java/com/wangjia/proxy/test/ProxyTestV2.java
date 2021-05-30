package com.wangjia.proxy.test;

import com.wangjia.proxy.cglib.CglibProxy;
import com.wangjia.proxy.proxyer.JdkBasedProxy;
import com.wangjia.proxy.proxyer.MeiTuanSeller;
import com.wangjia.proxy.targets.Seller12306;
import com.wangjia.proxy.targets.TicketSeller;
import com.wangjia.proxy.utils.ProxyUtils;
import org.junit.Test;

/**
 * @author : wangjia
 * @time : 2018/1/11 22:27
 */
public class ProxyTestV2 {
    /**
     * 静态代理
     */
    @Test
    public void testStatic() throws Exception {
        TicketSeller seller = new MeiTuanSeller(new Seller12306());
        seller.query();
        seller.sell();
    }

    /**
     * 动态代理
     */
    @Test
    public void testJdkBasedProxy() throws Exception {
        TicketSeller seller = (TicketSeller) new JdkBasedProxy().bind(new Seller12306());
        seller.query();
        seller.sell();
//        ProxyUtils.generateClassFile(Seller12306.class, "test");
    }

    @Test
    public void testGeneClassFile() throws Exception {
        ProxyUtils.generateClassFile(Seller12306.class, "testddd");
    }

    /**
     * cglib 动态代理
     */
    @Test
    public void cglibProxy() throws Exception {
        CglibProxy cglib = new CglibProxy();
        Seller12306 seller = (Seller12306) cglib.getInstance(new Seller12306());
        seller.query();
        seller.sell();
    }

}
