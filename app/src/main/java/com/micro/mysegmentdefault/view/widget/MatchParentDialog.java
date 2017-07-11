package com.micro.mysegmentdefault.view.widget;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.view.ViewGroup;

/**
 * author : micro_hx <p>
 * desc : 宽度为match parent的Dialog<p>
 * email: javainstalling@163.com <p>
 * date : 2017/7/10 - 14:37 <p>
 * interface :
 */

@Deprecated
public class MatchParentDialog extends AppCompatDialog {

    public MatchParentDialog(Context context,int theme) {
        super(context,theme);
        supportRequestWindowFeature(1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
    }
}
