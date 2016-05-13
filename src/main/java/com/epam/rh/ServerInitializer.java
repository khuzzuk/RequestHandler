package com.epam.rh;


import com.epam.rh.db.Bus;
import com.epam.rh.db.DAOHandler;
import com.epam.rh.db.DAORequests;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelFactory;
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

    private void initialize() {
        ChannelFactory channelFactory = new NioServerSocketChannelFactory(
                Executors.newCachedThreadPool(), Executors.newCachedThreadPool()
        );
        ServerBootstrap bootstrap = new ServerBootstrap(channelFactory);
        CounterConnectionHandler handler = createHandler();
        bootstrap.setPipelineFactory(() -> Channels.pipeline(handler));
        bootstrap.setOption("child.tcpNoDelay", true);
        bootstrap.setOption("child.keepAlive", true);
        bootstrap.bind(new InetSocketAddress(port));
    }

    private CounterConnectionHandler createHandler(){
        DAOHandler handler = new DAOHandler();
        handler.initializeConnection();
        Bus bus = new Bus(Executors.newFixedThreadPool(5), new DAORequests(handler));
        return new CounterConnectionHandler(bus);
    }

    public static void main(String[] args) {
        ServerInitializer initializer = new ServerInitializer(60001);
        initializer.initialize();
        //TODO handle logging levels.
        org.jboss.logging.Logger.getLogger("org.hibernate").info("info");
    }
}
