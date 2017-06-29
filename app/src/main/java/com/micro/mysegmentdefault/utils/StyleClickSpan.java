package com.micro.mysegmentdefault.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcel;
import android.text.TextPaint;
import android.text.style.URLSpan;
import android.view.View;

import com.micro.mysegmentdefault.ui.SchemeActivity;

/**
 * author : micro_hx <p>
 * desc : 可点击的<p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/7 - 17:56 <p>
 * interface :
 */

public class StyleClickSpan extends URLSpan {

    public StyleClickSpan(String params) {
        super(params);
    }

    @Override
    public void onClick(View view) {
        Uri localUri = Uri.parse(getURL());
        Context localContext = view.getContext();
        Intent _intent = new Intent(localContext, SchemeActivity.class);
        _intent.setData(localUri);
        _intent.putExtra("inner",true);
        localContext.startActivity(_intent);
    }

    public void updateDrawState(TextPaint paramTextPaint) {
        super.updateDrawState(paramTextPaint);
        paramTextPaint.setUnderlineText(false);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    protected StyleClickSpan(Parcel in) {
        super(in);
    }

    public static final Creator<StyleClickSpan> CREATOR = new Creator<StyleClickSpan>() {
        @Override
        public StyleClickSpan createFromParcel(Parcel source) {
            return new StyleClickSpan(source);
        }

        @Override
        public StyleClickSpan[] newArray(int size) {
            return new StyleClickSpan[size];
        }
    };
}
