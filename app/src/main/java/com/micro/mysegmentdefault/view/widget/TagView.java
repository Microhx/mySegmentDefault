package com.micro.mysegmentdefault.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/19 - 15:49 <p>
 * interface :
 */

public class TagView extends View {

    private static final String TAG = TagView.class.getSimpleName();

    //绘制的文字与标签值
    private String mText, mTextNumber;

    private Paint mTextPaint, mBgPaint;

    //text的padding值
    private float mTextPaddingLeft, mTextPaddingRight, mTextPaddingTop, mTextPaddingBottom;

    //Number的padding值
    private float mNumberPaddingLeft, mNumberPaddingRight, mNumberPaddingTop, mNumberPaddingBottom;

    //Text color/bgColor Number color/bgColor
    private int mTextBgColor, mTextColor, mNumberBgColor, mNumberColor;

    private int mWidth, mHeight, mTextHeight, mRadius;

    private Path mCirclePath;

    private RectF mTempRectF ;

    public TagView(Context context) {
        this(context, null);
    }

    public TagView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TagView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initViews();
    }

    private void initViews() {
        //Todo all Resources
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setTextSize(22);
        mTextHeight = (int) (mTextPaint.descent() - mTextPaint.ascent()) / 2;

        mBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBgPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        mTextPaddingLeft = mTextPaddingBottom = mTextPaddingTop = mTextPaddingRight = 20;
        mNumberPaddingLeft = mNumberPaddingRight = mNumberPaddingBottom = mNumberPaddingTop = 12;

        mTextColor = Color.parseColor("#77C8AB");
        mTextBgColor = Color.parseColor("#23039A63");

        mNumberBgColor = Color.parseColor("#77C8AB");
        mNumberColor = Color.WHITE;
        mRadius = 15;

        mCirclePath = new Path();
        mTempRectF = new RectF();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (TextUtils.isEmpty(mText) || TextUtils.isEmpty(mTextNumber)) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        } else {
            float mTextWidth = mTextPaint.measureText(mText);
            float mNumberWith = mTextPaint.measureText(mTextNumber);

            int totalWidth = (int) (mTextWidth + mTextPaddingLeft + mTextPaddingRight +
                    mNumberWith + mNumberPaddingLeft + mNumberPaddingRight);
            int totalHeight = (int) (mTextHeight + Math.max(mTextPaddingTop, mNumberPaddingTop) +
                    Math.max(mTextPaddingBottom, mNumberPaddingBottom));

            setMeasuredDimension(totalWidth, totalHeight);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mWidth = w;
        this.mHeight = h;

        Log.d(TAG, "the width is " + mWidth + " , the height is " + mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (TextUtils.isEmpty(mText) || TextUtils.isEmpty(mTextNumber)) return;

        drawBackground(canvas);
    }

    private void drawBackground(Canvas canvas) {
        mBgPaint.setColor(mTextBgColor);
        float mTextWidth = mTextPaint.measureText(mText) + mTextPaddingLeft + mTextPaddingRight;
        mCirclePath.reset();
        mCirclePath.moveTo(mRadius, 0);
        mCirclePath.lineTo(mTextWidth, 0);
        mCirclePath.lineTo(mTextWidth, mHeight);
        mCirclePath.lineTo(mRadius , mHeight);

        mTempRectF.set(0, mHeight - mRadius * 2, mRadius * 2, mHeight);
        mCirclePath.arcTo(mTempRectF, 90, 90, false);
        mCirclePath.lineTo(0, mHeight - mRadius );
        mCirclePath.lineTo(0,mRadius);

        mTempRectF.set(0,0,mRadius*2,mRadius*2);
        mCirclePath.arcTo(mTempRectF,180,90,false);
        mCirclePath.lineTo(mRadius,0);
        mCirclePath.close();
        canvas.drawPath(mCirclePath, mBgPaint);

        mTextPaint.setColor(mTextColor);
        canvas.drawText(mText, mTextWidth / 2, (mHeight + mTextHeight) / 2, mTextPaint);

        mBgPaint.setColor(mNumberBgColor);
        mCirclePath.reset();
        mCirclePath.moveTo(mTextWidth, 0);
        mCirclePath.lineTo(mWidth-mRadius,0);

        mTempRectF.set(mWidth - mRadius * 2 , 0 , mWidth , mRadius * 2);
        mCirclePath.arcTo(mTempRectF,-90,90,false);
        mCirclePath.lineTo(mWidth,mRadius);
        mCirclePath.lineTo(mWidth,mHeight-mRadius);

        mTempRectF.set(mWidth - mRadius * 2 , mHeight - mRadius *2 , mWidth , mHeight);
        mCirclePath.arcTo(mTempRectF,0,90,false);
        mCirclePath.lineTo(mWidth - mRadius , mHeight);
        mCirclePath.lineTo(mTextWidth,mHeight);
        mCirclePath.close();
        canvas.drawPath(mCirclePath,mBgPaint);

        mTextPaint.setColor(mNumberColor);
        canvas.drawText(mTextNumber, (mWidth + mTextWidth) / 2, (mHeight + mTextHeight) / 2, mTextPaint);
    }

    public void setData(String text, String number) {
        this.mText = text;
        this.mTextNumber = number;
        invalidate();
    }



}
