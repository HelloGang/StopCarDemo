package com.qly.stopcardemo.utils;

import android.util.Log;

/**
 * Created by Administrator on 2017/1/11.
 */

public class LogUtils {
    //在之前debug运行的时候值为true 代表可以打印出Log
    private static final boolean DEBUG = true;
    //log.i
    public static void i(String TAG,String info){
        if(DEBUG){
            Log.i(TAG,info);
        }
    }
    //log.d
    public static void d(String TAG,String info){
        if(DEBUG){
            Log.d(TAG,info);
        }
    }
    //log.w
    public static void w(String TAG,String info){
        if(DEBUG){
            Log.w(TAG,info);
        }
    }
    //log.e
    public static void e(String TAG,String info){
        if(DEBUG){
            Log.e(TAG,info);
        }
    }
}
