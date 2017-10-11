package com.micro.mysegmentdefault.ui.user.message;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.module.BaseActivity;
import com.micro.mysegmentdefault.middleimpl.adapter.UserMessageAdapter;
import com.micro.mysegmentdefault.view.widget.PublicHeadLayout;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * author : micro_hx <p>
 * desc : 用户消息<p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/6 - 14:03 <p>
 * interface :
 */

public class UserMessageActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    @Bind(R.id.id_tv_tab_layout)
    TabLayout mTabLayout;

    @Bind(R.id.id_view_pager)
    ViewPager mViewPager;

    @Bind(R.id.id_widget_layout)
    PublicHeadLayout mTitleLayout;

    private Integer[] mSourceImage = {R.drawable.ic_notification_general_selector,
                                      R.drawable.ic_notification_rank_selector,
                                        R.drawable.ic_notification_follow_selector };
    //当前的viewPager位置
    private int mCurrentPosition = -1 ;

    @Override
    protected void initViews() {
        mTitleLayout.setTitle(R.string.str_user_message);

        mViewPager.setAdapter(new UserMessageAdapter(getSupportFragmentManager()));
        mViewPager.addOnPageChangeListener(this);
        mViewPager.setOffscreenPageLimit(3);

        mTabLayout.setupWithViewPager(mViewPager);
        for(int i = 0 ; i < mSourceImage.length ; i++) {
            mTabLayout.getTabAt(i).setIcon(mSourceImage[i]);
        }
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_user_tag_manage;
    }


    @Override
    public void onPageSelected(int position) {
        if(mTabLayout == null && mTabLayout.getChildCount() < 3) return;
        if(position == mCurrentPosition) return;

        mTabLayout.getTabAt(position).setIcon(mSourceImage[position]);
        mCurrentPosition = position ;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageScrollStateChanged(int state) {}


    @OnClick(R.id.id_iv_back)
    public void onCall(View v) {
        finish();
    }

}
