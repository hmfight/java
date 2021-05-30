package com.wangjia.proxy.utils;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ProxyUtils {

    public static void generateClassFile(Class clazz, String proxyName) {
        byte[] classFile = ProxyGenerator.generateProxyClass(proxyName, clazz.getInterfaces());
        String paths = "/Users/wangjia/";
        System.out.println(paths);
//        OutputStream out = null;
        try (OutputStream out = new FileOutputStream(paths + proxyName + ".class");) {
            //保留到硬盘中
            out.write(classFile);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
