package com.micro.mysegmentdefault.base;

import android.app.Application;
import android.content.Context;

import com.micro.mysegmentdefault.cache.CacheDBImpl;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/6 - 11:08 <p>
 * interface :
 */

public class SegmentApplication extends Application {

    private static Context mCtx ;

    //TODO somethings.......

    @Override
    public void onCreate() {
        super.onCreate();
        this.mCtx = this ;

        //init DB
        CacheDBImpl.getInstance() ;
    }

    public static Context getApplication() {
        return mCtx;
    }


}
