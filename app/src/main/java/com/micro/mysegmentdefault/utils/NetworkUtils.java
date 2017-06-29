package com.micro.mysegmentdefault.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * author : micro_hx <p>
 * desc : 网络监测工具类<p>
 * email: javainstalling@163.com <p>
 * date : 2017/3/28 - 13:50 <p>
 * interface :
 */

public class NetworkUtils {

    public static boolean isNetConnected(Context ctx) {
        NetworkInfo localNetworkInfo = ((ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if ((localNetworkInfo != null) && (localNetworkInfo.isAvailable()))
            return true;
        return false ;
    }






}
