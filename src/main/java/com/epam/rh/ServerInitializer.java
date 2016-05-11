package com.epam.rh;


import com.epam.rh.db.Bus;
import com.epam.rh.db.DAOHandler;
import com.epam.rh.db.DAORequests;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                return Channels.pipeline(createHandler());
            }
        });
        bootstrap.setOption("child.tcpNoDelay", true);
        bootstrap.setOption("child.keepAlive", true);
        bootstrap.bind(new InetSocketAddress(port));
    }

    private CounterConnectionHandler createHandler(){
        Bus bus = new Bus(Executors.newFixedThreadPool(5), new DAORequests(new DAOHandler()));
        return new CounterConnectionHandler(bus);
    }

    public static void main(String[] args) {
        ServerInitializer initializer = new ServerInitializer(60000);
        initializer.initialize();
        //TODO handle logging levels.
        org.jboss.logging.Logger.getLogger("org.hibernate").info("info");
    }
}
