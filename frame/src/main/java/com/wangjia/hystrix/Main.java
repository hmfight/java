package com.wangjia.hystrix;

/**
 * @author : wj
 * @date : 2021/6/16 23:51
 */
public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            String s = new CommandHelloWorld("Bob").execute();
            System.out.println(s);
        }
//        Future<String> s = new CommandHelloWorld("Bob").queue();
//        Observable<String> s = new CommandHelloWorld("Bob").observe();
    }
}
