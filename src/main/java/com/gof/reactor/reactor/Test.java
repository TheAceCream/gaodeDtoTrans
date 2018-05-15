package com.gof.reactor.reactor;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: HYC
 * @description:
 * @time: 2018年05月08日
 * @modifytime:
 */
public class Test {
    public static void main(String[] args) throws IOException {
        Reactor reactor = new Reactor(6789);
        Thread thread = new Thread(reactor);
        thread.start();
    }
}
