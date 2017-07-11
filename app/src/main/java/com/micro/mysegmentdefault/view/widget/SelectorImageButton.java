package com.micro.mysegmentdefault.view.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.AppCompatImageButton;
import android.util.AttributeSet;

import com.micro.mysegmentdefault.utils.LogUtils;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * author : micro_hx <p>
 * desc : 可供换Selector的颜色 ImageButton<p>
 * email: javainstalling@163.com <p>
 * date : 2017/7/11 - 16:42 <p>
 * interface :
 */

public class SelectorImageButton extends AppCompatImageButton {

    private ColorStateList mColorStateList;



    public SelectorImageButton(Context context) {
        this(context,null);
    }

    public SelectorImageButton(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SelectorImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setTintDrawableList(int imageSource, int color) {
        final Drawable wrappedDrawable = DrawableCompat.wrap(getResources().getDrawable(imageSource).mutate());
        DrawableCompat.setTintList(wrappedDrawable,ColorStateList.valueOf(getResources().getColor(color)));
        setImageDrawable(wrappedDrawable);
    }

    public void setTintDrawable(int imageSource, int color) {
        final Drawable wrappedDrawable = DrawableCompat.wrap(getResources().getDrawable(imageSource));
        DrawableCompat.setTint(wrappedDrawable,color);
        setImageDrawable(wrappedDrawable);
    }

    public void setColorStateList(ColorStateList list) {
        this.mColorStateList = list ;
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();

        if(null != mColorStateList && mColorStateList.isStateful()) {
            int color = mColorStateList.getColorForState(getDrawableState(),0);
            super.setColorFilter(color);
        }
    }

}
