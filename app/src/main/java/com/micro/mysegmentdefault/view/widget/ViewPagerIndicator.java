package com.micro.mysegmentdefault.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.view.View;

/**
 * author : micro_hx <p>
 * desc : viewpager下的小圆点轮播<p>
 * email: javainstalling@163.com <p>
 * date : 2017/7/7 - 14:13 <p>
 * interface :
 */

public class ViewPagerIndicator extends View {

    private PagerAdapter mPagerAdapter;

    //画点的Paint
    private Paint mDotPaint;

    //点的半径
    private int mDotRadius = 10;

    //点之间的额间距
    private int mDotInterval = 20;

    //目标点的位置
    private int mRunningDotX , mRunningDoxY ;


    public ViewPagerIndicator(Context context) {
        this(context, null);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    private void initViews() {
        mDotPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDotPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mDotPaint.setColor(Color.BLUE);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mPagerAdapter == null || mPagerAdapter.getCount() == 0) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }

        int count = mPagerAdapter.getCount();
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int width;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            width = getPaddingLeft() + getPaddingRight() + count * mDotRadius * 2 + (count - 1) * mDotInterval;
            width = Math.min(width,widthSize);
        }

        int height;
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            height = getPaddingBottom() + getPaddingTop() + mDotRadius * 2;
            height = Math.min(height,heightSize);
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mPagerAdapter == null || mPagerAdapter.getCount() == 0) return;

        //画小球
        int count = mPagerAdapter.getCount();
        mDotPaint.setColor(Color.GRAY);
        for (int i = 0; i < count; i++) {
            int x = getPaddingLeft() + i * mDotInterval + (2 * i + 1) * mDotRadius;
            int y = getPaddingTop() + mDotRadius;
            canvas.drawCircle(x,y,mDotRadius,mDotPaint);
        }

        //画滚动的View
        mDotPaint.setColor(Color.WHITE);
        canvas.drawCircle(mRunningDotX,mRunningDoxY,mDotRadius,mDotPaint);
    }

    public void setPagerAdapter(PagerAdapter pagerAdapter) {
        this.mPagerAdapter = pagerAdapter;
        mRunningDotX = getPaddingLeft() +  mDotRadius;
        mRunningDoxY = getPaddingTop() + mDotRadius;

        requestLayout();
    }

    public void setCurrentItem(int position) {
        if(position >= mPagerAdapter.getCount()-1) {
            position = mPagerAdapter.getCount() -1 ;
        }

        mRunningDotX = getPaddingLeft() + position * mDotInterval + (2 * position + 1) * mDotRadius;
        mRunningDoxY = getPaddingTop() + mDotRadius;
        invalidate();
    }


    public void onPageScrolled(int startPosition, int endPosition , float positionOffset,boolean isRight) {
        int startL = getPaddingLeft() + startPosition * mDotInterval + (2 * startPosition + 1) * mDotRadius;
        int endL = getPaddingLeft() + endPosition * mDotInterval + (2 * endPosition + 1) * mDotRadius;

        int targetL ;
        if(isRight){
            targetL = (int) (startL + (endL - startL) * positionOffset);
        }else{
            targetL = (int) (startL + (endL - startL) *(1.0f- positionOffset));
        }

        mRunningDotX = targetL;
        invalidate();
    }



}
