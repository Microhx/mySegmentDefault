package com.micro.mysegmentdefault.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * author : micro_hx <p>
 * desc : 权限检查帮助类 <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/2 - 13:42 <p>
 * interface :
 */

public class PermissionUtils {

    public static void requestPermission(Activity activity, String permissionStr , int permissionIndex , Runnable runnable) {
        if(ActivityCompat.shouldShowRequestPermissionRationale(activity,permissionStr)){
            if(null != runnable) {
                runnable.run();
                return;
            }
        }
        ActivityCompat.requestPermissions(activity,new String[]{permissionStr},permissionIndex);
    }

    public static void requestPermission(Activity activity , String[] permissionStr , int permissionIndex , Runnable runnable) {
         for(int i = 0 ; i < permissionStr.length ; i++) {
             if(ActivityCompat.shouldShowRequestPermissionRationale(activity,permissionStr[i])){
                 if(null != runnable) {
                     runnable.run();
                 }
             }
         }
        ActivityCompat.requestPermissions(activity,permissionStr,permissionIndex);
    }



    public static boolean checkPermissionAuthorized(Context ctx , String permission){
        return ContextCompat.checkSelfPermission(ctx,permission) == 0;
    }





}
