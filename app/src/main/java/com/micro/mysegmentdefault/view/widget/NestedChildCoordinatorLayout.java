package com.micro.mysegmentdefault.view.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.util.AttributeSet;
import android.view.View;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/28 - 14:33 <p>
 * interface :
 */

public class NestedChildCoordinatorLayout extends CoordinatorLayout implements NestedScrollingChild {

    private NestedScrollingChildHelper mHelper = new NestedScrollingChildHelper(this);

    public NestedChildCoordinatorLayout(Context context) {
        this(context, null);
    }

    public NestedChildCoordinatorLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NestedChildCoordinatorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setNestedScrollingEnabled(true);
    }


    public boolean dispatchNestedFling(float paramFloat1, float paramFloat2, boolean paramBoolean) {
        return this.mHelper.dispatchNestedFling(paramFloat1, paramFloat2, paramBoolean);
    }

    public boolean dispatchNestedPreFling(float paramFloat1, float paramFloat2) {
        return this.mHelper.dispatchNestedPreFling(paramFloat1, paramFloat2);
    }

    public boolean dispatchNestedPreScroll(int paramInt1, int paramInt2, int[] paramArrayOfInt1, int[] paramArrayOfInt2) {
        return this.mHelper.dispatchNestedPreScroll(paramInt1, paramInt2, paramArrayOfInt1, paramArrayOfInt2);
    }

    public boolean dispatchNestedScroll(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt) {
        return this.mHelper.dispatchNestedScroll(paramInt1, paramInt2, paramInt3, paramInt4, paramArrayOfInt);
    }

    public boolean hasNestedScrollingParent() {
        return this.mHelper.hasNestedScrollingParent();
    }

    public boolean isNestedScrollingEnabled() {
        return this.mHelper.isNestedScrollingEnabled();
    }

    public boolean onNestedFling(View paramView, float paramFloat1, float paramFloat2, boolean paramBoolean) {
        dispatchNestedFling(paramFloat1, paramFloat2, paramBoolean);
        return super.onNestedFling(paramView, paramFloat1, paramFloat2, paramBoolean);
    }

    public boolean onNestedPreFling(View paramView, float paramFloat1, float paramFloat2) {
        dispatchNestedPreFling(paramFloat1, paramFloat2);
        return super.onNestedPreFling(paramView, paramFloat1, paramFloat2);
    }

    public void onNestedPreScroll(View paramView, int paramInt1, int paramInt2, int[] paramArrayOfInt) {
        super.onNestedPreScroll(paramView, paramInt1, paramInt2, paramArrayOfInt);
        dispatchNestedPreScroll(paramInt1, paramInt2, new int[2], null);
    }

    public void onNestedScroll(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        super.onNestedScroll(paramView, paramInt1, paramInt2, paramInt3, paramInt4);
        dispatchNestedScroll(0, 0, paramInt3, paramInt4, null);
    }

    public void onNestedScrollAccepted(View paramView1, View paramView2, int paramInt) {
        super.onNestedScrollAccepted(paramView1, paramView2, paramInt);
        startNestedScroll(paramInt);
    }

    public boolean onStartNestedScroll(View paramView1, View paramView2, int paramInt) {
        this.mHelper.startNestedScroll(paramInt);
        return super.onStartNestedScroll(paramView1, paramView2, paramInt);
    }

    public void onStopNestedScroll(View paramView) {
        this.mHelper.stopNestedScroll();
        super.onStopNestedScroll(paramView);
    }

    public void setNestedScrollingEnabled(boolean paramBoolean) {
        this.mHelper.setNestedScrollingEnabled(paramBoolean);
    }

    public boolean startNestedScroll(int paramInt) {
        return this.mHelper.startNestedScroll(paramInt);
    }

    public void stopNestedScroll() {
        this.mHelper.stopNestedScroll();
    }
}
