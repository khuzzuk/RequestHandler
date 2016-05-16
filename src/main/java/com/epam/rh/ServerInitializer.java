package com.epam.rh;


import lombok.NoArgsConstructor;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

@Service
@NoArgsConstructor
public class ServerInitializer {
    private final int port = 60001;
    @Autowired
    private CounterConnectionHandler handler;

    @PostConstruct
    private void initialize() {
        ChannelFactory channelFactory = new NioServerSocketChannelFactory(
                Executors.newCachedThreadPool(), Executors.newCachedThreadPool()
        );
        ServerBootstrap bootstrap = new ServerBootstrap(channelFactory);
        System.out.println(handler);
        bootstrap.setPipelineFactory(() -> Channels.pipeline(handler));
        bootstrap.setOption("child.tcpNoDelay", true);
        bootstrap.setOption("child.keepAlive", true);
        bootstrap.bind(new InetSocketAddress(port));
    }

}
