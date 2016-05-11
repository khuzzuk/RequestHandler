package com.epam.rh;

import com.epam.rh.db.Bus;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

import java.nio.charset.Charset;


class CounterConnectionHandler extends SimpleChannelHandler {
    private static volatile int counter = 0;
    private static long startTime=0;
    private Bus bus;
    private Charset charset;

    public CounterConnectionHandler(Bus bus) {
        this.bus = bus;
        charset = Charset.forName("UTF-8");
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        increase();
        if (counter%10000==0) reportCounting();
        if (startTime==0) startTime=System.currentTimeMillis();
        ChannelBuffer buffer = (ChannelBuffer) e.getMessage();
        bus.reportRequest(buffer.toString(charset));
        ctx.getChannel().close();
    }

    private synchronized void increase(){
        counter++;
    }

    private void reportCounting() {
        double rate = counter*1000/(System.currentTimeMillis()-startTime);
        System.out.println("Server is handling "+rate+" requests per second");
        counter=0;
        startTime=System.currentTimeMillis();
    }

    @SuppressWarnings("ThrowableResultOfMethodCallIgnored")
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        e.getCause().printStackTrace();
        e.getChannel().close();
    }
}
