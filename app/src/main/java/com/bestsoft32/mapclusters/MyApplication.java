package com.bestsoft32.mapclusters;

import android.app.Application;
import android.content.Context;

import com.bestsoft32.mapclusters.server.NetworkManager;
import com.bestsoft32.ttvolleylib.TTVolleyRX;

public class MyApplication extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();

        //Volley and TTVolleyRX
        NetworkManager.getInstance(context);
        TTVolleyRX.init(context);
    }

    public static Context getMyAppContext(){
        return context;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
