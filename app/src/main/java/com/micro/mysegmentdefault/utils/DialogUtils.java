package com.micro.mysegmentdefault.utils;

import android.content.Context;
import android.content.DialogInterface;
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

    public static void showAlertDialog(Context ctx, String title , String msg , String positiveText , DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setTitle(title).
                setMessage(msg).
                setPositiveButton(positiveText,listener).
                show();
    }


    public static void showAlertItemDialog(Context ctx, String[] items , DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setItems(items,listener).show();
    }



}
