package com.micro.mysegmentdefault.view.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;

import com.micro.mysegmentdefault.utils.LogUtils;

/**
 * author : micro_hx <p>
 * desc : 监听viewpager向左还是向右滑动 <p>
 * email: javainstalling@163.com <p>
 * date : 2017/7/5 - 20:59 <p>
 * interface :
 */

public class CustomViewPager extends ViewPager {

    private onLeftOrRightListener mLeftOrRightListener ;

    private boolean mIsRight ;

    //滑动的状态
    private int mScrollState ;

    //开始的position位置
    private int mStartPosition ;
    //结束的position位置
    private int mEndPosition ;

    public CustomViewPager(Context context) {
        this(context,null);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        addOnPageChangeListener(mOnPageChangeListener);
    }

    private OnPageChangeListener mOnPageChangeListener = new OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if(positionOffset == 0.0f) return;
            if(mScrollState == ViewPager.SCROLL_STATE_DRAGGING) {
                mScrollState = -1 ;
                mIsRight = positionOffset < 0.5f ;

                mStartPosition = getCurrentItem();
                mEndPosition = mIsRight ? mStartPosition + 1 : mStartPosition -1 ;
                if(mEndPosition < 0 || mEndPosition > getAdapter().getCount()) {
                    mStartPosition = mEndPosition;
                }
            }

            if(null != mLeftOrRightListener){
                mLeftOrRightListener.onPageScrolled(mStartPosition,mEndPosition,positionOffset,mIsRight);
            }

        }

        @Override
        public void onPageSelected(int position) {
            if(null != mLeftOrRightListener) mLeftOrRightListener.onPageSelected(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            mScrollState = state;
            if(null != mLeftOrRightListener) mLeftOrRightListener.onPageScrollStateChanged(state);
        }
    } ;


    public void setLeftOrRightListener(onLeftOrRightListener mLeftOrRightListener) { //listener
        this.mLeftOrRightListener = mLeftOrRightListener;
    }


    public interface onLeftOrRightListener{
        void onPageScrolled(int startPosition, int endPosition, float positionOffset, boolean isRight) ;
        void onPageSelected(int position);
        void onPageScrollStateChanged(int state);
    }



}
