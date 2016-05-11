package com.epam.rh;


import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

class ServerInitializer {
    private final int port;

    @SuppressWarnings({"SameParameterValue", "WeakerAccess"})
    public ServerInitializer(int port) {
        this.port = port;
    }

    @SuppressWarnings("WeakerAccess")
    public void initialize() {
        ChannelFactory channelFactory = new NioServerSocketChannelFactory(
                Executors.newCachedThreadPool(), Executors.newCachedThreadPool()
        );
        ServerBootstrap bootstrap = new ServerBootstrap(channelFactory);
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                return Channels.pipeline(new CounterConnectionHandler());
            }
        });
        bootstrap.setOption("child.tcpNoDelay", true);
        bootstrap.setOption("child.keepAlive", true);
        bootstrap.bind(new InetSocketAddress(port));
    }

    public static void main(String[] args) {
        ServerInitializer initializer = new ServerInitializer(60000);
        initializer.initialize();
    }
}
