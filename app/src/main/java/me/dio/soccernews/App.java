package me.dio.soccernews;

import android.app.Application;

// FIXME futuramente alterar essa solução para uma forma de ingeção de dependencia
public class App extends Application {
    public static App instance;

    public static App getInstance() {return instance;}

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

}
