package com.micro.mysegmentdefault.view.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewConfiguration;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/3/21 - 23:02 <p>
 * interface :
 */

public class ByeBurgerBehavior extends CoordinatorLayout.Behavior<View> {

    protected final int mTouchSlop ;

    protected boolean isFirstMove = true ;

    public ByeBurgerBehavior(Context ctx , AttributeSet attr) {
        super(ctx,attr);
        mTouchSlop = ViewConfiguration.get(ctx).getScaledTouchSlop();
    }


    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0 ;
    }
}
