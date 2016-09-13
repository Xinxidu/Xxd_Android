package com.xinxidu.xxd.base;

import com.rogers.kit.base.BaseApp;
import com.xinxidu.xxd.event.Engine;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 *
 */
public class App extends BaseApp {
    private static App sInstance;
    private Engine mEngine;

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;
        mEngine = new Retrofit.Builder()
                .baseUrl("http://www.tooopen.com/view/712724.html")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(Engine.class);

      //  Fresco.initialize(this);
    }


    public static App getInstance() {
        return sInstance;
    }

    public Engine getEngine() {
        return mEngine;
    }
}