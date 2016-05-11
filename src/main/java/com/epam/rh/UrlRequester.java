package com.epam.rh;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@SuppressWarnings("unused")
class UrlRequester implements Runnable {
    @Override
    public void run() {
        try {
            URL url = new URL("http://localhost:60000");
            ((HttpURLConnection) url.openConnection()).getResponseCode();
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }
}
