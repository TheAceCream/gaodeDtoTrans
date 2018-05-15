package com.gof.reactor.reactorPool;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: HYC
 * @description:
 * @time: 2018年05月09日
 * @modifytime:
 */
public class Main {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            TCPReactor reactor = new TCPReactor(6789);
            new Thread(reactor).start();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
