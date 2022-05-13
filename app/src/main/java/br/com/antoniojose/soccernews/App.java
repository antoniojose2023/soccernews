package br.com.antoniojose.soccernews;

import android.app.Application;

public class App extends Application {

    private static App instance;

    public static App getIntance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
