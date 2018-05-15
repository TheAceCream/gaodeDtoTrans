package com.gof.reactor.reactor;

import java.io.IOException;
import java.nio.channels.SocketChannel;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: HYC
 * @description:
 * @time: 2018年05月08日
 * @modifytime:
 */
public class Acceptor implements Runnable{
    private Reactor reactor;
    public Acceptor(Reactor reactor){
        this.reactor=reactor;
    }
    @Override
    public void run() {
        try {
            SocketChannel socketChannel=reactor.serverSocketChannel.accept();
            //调用Handler来处理channel
            if(socketChannel!=null) {
                new SocketReadHandler(reactor.selector, socketChannel);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
