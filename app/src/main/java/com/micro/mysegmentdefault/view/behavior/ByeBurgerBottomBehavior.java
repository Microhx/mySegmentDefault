package com.micro.mysegmentdefault.view.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/3/21 - 23:06 <p>
 * interface :
 */

public class ByeBurgerBottomBehavior extends ByeBurgerBehavior {

    private TranslateAnimateHelper mAnimatorHelper ;

    public ByeBurgerBottomBehavior(Context ctx, AttributeSet attr) {
        super(ctx, attr);
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        if(isFirstMove) {
            isFirstMove = false;
            mAnimatorHelper = TranslateAnimateHelper.getAnimateHelper(child) ;
            mAnimatorHelper.setStartY(child.getY());
            mAnimatorHelper.setModel(TranslateAnimateHelper.MODEL_BOTTOM) ;
        }

        if(Math.abs(dy) > mTouchSlop) {
            if(dy < 0) {
                if(mAnimatorHelper.getState() == TranslateAnimateHelper.STATE_HIDE)
                    mAnimatorHelper.show();
            }else if(dy > 0) {
                if(mAnimatorHelper.getState() == TranslateAnimateHelper.STATE_SHOW)
                    mAnimatorHelper.hide();
            }
        }
    }
}
