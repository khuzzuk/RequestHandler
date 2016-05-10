package com.epam.rh;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;


public class CounterConnectionHandler extends SimpleChannelHandler {
    private static volatile int counter = 0;
    private long startTime;

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        counter++;
        /*if (counter%5000==0) */System.out.println(counter);
        if (startTime==0) startTime=System.currentTimeMillis();
        if (counter==300000){
            reportCounting();
        }
        ctx.getChannel().close();
    }

    private void reportCounting() {
        double rate = counter*1000/(System.currentTimeMillis()-startTime);
        System.out.println("Server is handling "+rate+" requests per second");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        e.getCause().printStackTrace();
        e.getChannel().close();
    }
}
