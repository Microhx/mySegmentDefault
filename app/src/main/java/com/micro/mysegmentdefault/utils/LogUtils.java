package com.micro.mysegmentdefault.utils;

import android.util.Log;

import java.util.Collection;
import java.util.Iterator;

/**
 * author : micro_hx <p>
 * desc : Log工具类<p>
 * email: javainstalling@163.com <p>
 * date : 2017/3/23 - 15:52 <p>
 * interface :
 */

public class LogUtils {

    public static final String TAG = "_mySegmentDefault" ;

    private static final boolean DEBUG = true ;


    public static void d(String msg) {
        if(DEBUG) {
            Log.d(TAG,msg) ;
        }
    }

    public static void collection(Collection<?> collection) {
        if(DEBUG) {
            Log.d(TAG,"==============log start========================") ;
            Iterator<?> iterator = collection.iterator();
            while (iterator.hasNext()) {
                d(iterator.next().toString());
            }
            Log.d(TAG,"=============log  end========================") ;

        }
    }



}
