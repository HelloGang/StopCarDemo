package com.qly.stopcardemo.app;

import android.app.Application;
import android.content.Context;

import com.qly.stopcardemo.utils.ImageLoaderUtils;

/**
 * 1.类的用途：
 * 2.@author
 * 3.@date
 */
public class MyApplication extends Application{
    public static int LEFT_POSITION = 0;
    private static  Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
        //初始化ImageLOaderUtils
        ImageLoaderUtils.initConfiguration(context);
    }

    //返回一个全局的上下文
    public static Context getContext(){
        return context;
    }
}
