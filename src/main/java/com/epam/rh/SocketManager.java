package com.epam.rh;

import java.net.Socket;
import java.util.concurrent.ExecutorService;

class SocketManager {
    private final ExecutorService tp;

    SocketManager(ExecutorService tp) {
        this.tp = tp;
    }
    void closeSocket(Socket socket){
        tp.submit(new SocketCloser(socket));
    }
    void submitSocketConnection() {
        tp.submit(new SocketRequester());
    }
    @SuppressWarnings("unused")
    void submitHttpConnection(){
        tp.submit(new UrlRequester());
    }
}
