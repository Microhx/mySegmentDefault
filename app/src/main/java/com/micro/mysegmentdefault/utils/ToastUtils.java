package com.micro.mysegmentdefault.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * author : micro_hx <p>
 * desc : Toast帮助类<p>
 * email: javainstalling@163.com <p>
 * date : 2017/3/22 - 13:49 <p>
 * interface :
 */

public class ToastUtils {

    public static void showMessage(Context ctx , String msg) {
        Toast.makeText(ctx,msg,Toast.LENGTH_SHORT).show();
    }

    public static void showMessage(Context ctx , int msg) {
        Toast.makeText(ctx,msg,Toast.LENGTH_SHORT).show();
    }



}
