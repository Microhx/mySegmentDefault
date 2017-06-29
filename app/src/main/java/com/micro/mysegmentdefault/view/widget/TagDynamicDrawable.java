package com.micro.mysegmentdefault.view.widget;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.text.style.DynamicDrawableSpan;
import android.util.Log;

import com.micro.mysegmentdefault.base.SegmentApplication;
import com.micro.mysegmentdefault.entity.BestTag;
import com.micro.mysegmentdefault.utils.CommonUtils;
import com.micro.mysegmentdefault.utils.LogUtils;

/**
 * author : micro_hx <p>
 * desc : tag动态Drawable<p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/3 - 21:05 <p>
 * interface :
 */

public class TagDynamicDrawable extends DynamicDrawableSpan {

    private static int DEFAULT_PADDING_LEFT = 15 ;
    private static int DEFAULT_PADDING_TOP = 10 ;

    private static final int TEXT_COLOR = Color.parseColor("#017E66");
    private static final int BACKGROUND_COLOR = Color.parseColor("#E7F2ED");

    private Paint mDrawablePaint ;
    private TextPaint mTextPaint;

    private BestTag mBestTag ;

    private Rect mTextRect = new Rect();
    private Rect mBitmapRect = new Rect();

    private Drawable mTargetDrawable;

    private int mPaddingLeft , mMiniHeight, mPaddingTop , mPadding;


    public BestTag getBestTag(){
        return mBestTag;
    }


    public TagDynamicDrawable(BestTag tag) {
        this.mBestTag = tag;
        mDrawablePaint = new Paint();
        mTextPaint = new TextPaint();
        mTextPaint.setTextSize(CommonUtils.dip2px(SegmentApplication.getApplication(),14));
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(TEXT_COLOR);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mDrawablePaint.setColor(BACKGROUND_COLOR);

        mPaddingLeft = CommonUtils.dip2px(SegmentApplication.getApplication(),DEFAULT_PADDING_LEFT);
        mPaddingTop = CommonUtils.dip2px(SegmentApplication.getApplication(),DEFAULT_PADDING_TOP);
        mMiniHeight = CommonUtils.dip2px(SegmentApplication.getApplication(),30);
        mPadding = CommonUtils.dip2px(SegmentApplication.getApplication(),2);
    }


    @Override
    public Drawable getDrawable() {
        if(null == mTargetDrawable) {
            mTargetDrawable = getDefaultDrawable();
        }
        return mTargetDrawable;
    }

    private Drawable getDefaultDrawable() {
        String str = this.mBestTag.name;
        mTextPaint.getTextBounds(str,0,str.length(),mTextRect);

        int width = mTextRect.width() + mPaddingLeft + 2 * mPadding;
        int height = mTextRect.height() + mPaddingTop;
        if(height < mMiniHeight) {
            height = mMiniHeight;
        }

        Bitmap localBitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        Canvas localCanvas = new Canvas(localBitmap);
        mBitmapRect.set(mPadding,0,width - 2 * mPadding, height);

        localCanvas.drawRect(mBitmapRect,mDrawablePaint);
        localCanvas.drawText(str,mBitmapRect.centerX(),mBitmapRect.centerY() - (mTextPaint.descent() + mTextPaint.ascent())/2f , mTextPaint);
        BitmapDrawable localBitmapDrawable = new BitmapDrawable(Resources.getSystem(),localBitmap);
        localBitmapDrawable.setBounds(0,0,localBitmapDrawable.getIntrinsicWidth(),localBitmapDrawable.getIntrinsicHeight());

        return localBitmapDrawable;
    }


    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        Rect localRect = getDrawable().getBounds();

        int top , bottom ;

        if(null != fm) {
            if(localRect.bottom - (fm.descent - fm.ascent) < 0) {
                top = bottom = 0 ;
            }else {
                top = fm.descent;
                bottom = localRect.bottom - (fm.descent - fm.ascent);
            }

            fm.descent = (top + bottom /2 );
            fm.bottom = fm.descent;

            fm.ascent = (-localRect.bottom + fm.descent);
            fm.top = fm.ascent;

            LogUtils.d("descent:" + fm.descent);
            LogUtils.d("ascent:" + fm.ascent);
        }

        return localRect.right;
    }
}
