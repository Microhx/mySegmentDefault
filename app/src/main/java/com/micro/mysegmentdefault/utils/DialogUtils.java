package com.micro.mysegmentdefault.utils;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.micro.mysegmentdefault.R;

/**
 * author : micro_hx <p>
 * desc : Dialog帮助类 <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/27 - 18:13 <p>
 * interface :
 */

public class DialogUtils {

    public static void showAlertDialog(Context ctx,String title , String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setTitle(title).
                setMessage(msg).
                setPositiveButton(R.string.str_i_know,null).
                show();
    }


}
