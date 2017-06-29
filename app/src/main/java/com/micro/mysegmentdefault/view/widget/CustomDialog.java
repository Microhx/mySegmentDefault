package com.micro.mysegmentdefault.view.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.micro.mysegmentdefault.R;

/**
 * author : micro_hx <p>
 * desc : 基础Dialog<p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/24 - 22:07 <p>
 * interface :
 */

public class CustomDialog extends Dialog {

    public CustomDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_item_layout);
    }
}
