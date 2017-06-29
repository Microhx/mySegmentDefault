package com.micro.mysegmentdefault.view.behavior;

import android.view.View;
import android.view.animation.TranslateAnimation;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/3/21 - 23:09 <p>
 * interface :
 */

public class TranslateAnimateHelper implements AnimateHelper {

    //目标View
    private View mTarget ;

    //当前显示状态
    private int mCurrentState = STATE_SHOW ;

    private float mStartY ;

    public static final int MODEL_TITLE = 0x03 ;
    public static final int MODEL_BOTTOM = 0x04 ;
    private int mCurrentModel = MODEL_TITLE ;

    private TranslateAnimateHelper(View target) {
        this.mTarget = target ;
    }

    public static TranslateAnimateHelper getAnimateHelper(View target) {
        return new TranslateAnimateHelper(target);
    }


    @Override
    public void hide() {
        if(mCurrentModel == MODEL_TITLE) {
            hideTitle() ;
        }else if(mCurrentModel == MODEL_BOTTOM) {
            hideBottom();
        }
    }


    @Override
    public void show() {
        if(mCurrentModel == MODEL_TITLE) {
            showTitle();
        }else if(mCurrentModel == MODEL_BOTTOM) {
            showBottom();
        }

    }


    private void hideTitle() {
        mTarget.setY(-mStartY);
        TranslateAnimation ta = new TranslateAnimation(0f,0f,mTarget.getMeasuredHeight(),0);
        ta.setDuration(400);
        mTarget.startAnimation(ta);
        mCurrentState = STATE_HIDE ;
    }


    private void showTitle() {
        mTarget.setY(mStartY);
        TranslateAnimation ta = new TranslateAnimation(0f,0f,-mTarget.getMeasuredHeight() , 0f) ;
        ta.setDuration(400);
        mTarget.startAnimation(ta);
        mCurrentState = STATE_SHOW ;
    }


    private void hideBottom() {
        mTarget.setY(-mStartY);
        TranslateAnimation ta = new TranslateAnimation(0f,0f,-mTarget.getMeasuredHeight(),0f) ;
        ta.setDuration(400);
        mTarget.startAnimation(ta);
        mCurrentState = STATE_HIDE ;
    }


    private void showBottom() {
        mTarget.setY(mStartY);
        TranslateAnimation ta = new TranslateAnimation(0f,0f,mTarget.getMeasuredHeight(),0f) ;
        ta.setDuration(400);
        mTarget.startAnimation(ta);
        mCurrentState = STATE_SHOW ;
    }

    public void setStartY(float startY) {
        this.mStartY = startY ;
    }


    public void setModel(int modelBottom) {
        this.mCurrentModel = modelBottom ;

    }

    public int getState() {
        return mCurrentState ;

    }
}
