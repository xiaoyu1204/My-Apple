package com.example.myhttp.app;

import android.app.Application;
import android.content.Context;

import com.live.MyApplication;

import java.util.HashMap;

public class MyApp extends Application {

    private static String[] modules = {"com.live.MyApplication"};
    public static MyApp app;
    public static HashMap<String,Object> map;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        map=new HashMap<>();
        initMoudles();
    }

    public static MyApp getInstance() {
        return app;
    }

    private static Context mAppContext = null;

    public static HashMap<String, Object> getMap() {
        return map;
    }

    private void initMoudles() {
        for (String moduleImpl : modules){
            try {
                Class<?> clazz = Class.forName(moduleImpl);
                Object obj = clazz.newInstance();
                if (obj instanceof MyApplication){
                    ((MyApplication) obj).initApp(app);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

}
