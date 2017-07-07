package com.micro.mysegmentdefault.view.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.micro.mysegmentdefault.entity.DiscoverDataEntity;
import com.micro.mysegmentdefault.middleimpl.adapter.SlideViewPagerAdapter;
import com.micro.mysegmentdefault.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * author : micro_hx <p>
 * desc : 轮播图 <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/10 - 22:31 <p>
 * interface :
 */

public class SlideshowView extends RelativeLayout {

    private ViewPager mViewPager;

    private LinearLayout mDotLayout;

    private SlideViewPagerAdapter mSlideViewPagerAdapter;

    public SlideshowView(Context context) {
        this(context, null);
    }

    public SlideshowView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideshowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initViews();
    }

    private void initViews() {
        mViewPager = new ViewPager(getContext());
        addView(mViewPager, getMatchParentLayoutParams());

        mDotLayout = new LinearLayout(getContext());
        mDotLayout.setOrientation(LinearLayout.HORIZONTAL);
        RelativeLayout.LayoutParams dotLayoutParams = getWrapContentLayoutParams();
        dotLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        dotLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        dotLayoutParams.setMargins(0, 0, 0, 30);
        addView(mDotLayout, dotLayoutParams);
    }

    private LayoutParams getMatchParentLayoutParams() {
        return new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    private LayoutParams getWrapContentLayoutParams() {
        return new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }


    public void setDatas(List<DiscoverDataEntity.DiscoverItem> listItems) {
        mSlideViewPagerAdapter = new SlideViewPagerAdapter(getContext(), listItems);
        mViewPager.setAdapter(mSlideViewPagerAdapter);
    }


}
