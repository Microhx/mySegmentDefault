package com.micro.mysegmentdefault;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.micro.mysegmentdefault.base.module.BaseMainActivity;
import com.micro.mysegmentdefault.entity.TabEntity;
import com.micro.mysegmentdefault.middleimpl.fragment.FragmentUtils;

import java.util.ArrayList;

import butterknife.Bind;

public class MainActivity extends BaseMainActivity implements OnTabSelectListener {

    @Bind(R.id.tab_layout)
    CommonTabLayout mTabLayout ;

    private FragmentManager mFragmentManager;
    private Fragment mCurrentFragment;
    private int mCurrentIndex = -1;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        initTabLayout();

        initFragments();

        changeFragment(0);
    }

    private void initFragments() {
        mFragmentManager = getSupportFragmentManager();
    }

    private void changeFragment(int index) {
        if (index == mCurrentIndex) return;

        Fragment tempFragment = mFragmentManager.findFragmentByTag(String.valueOf(index));
        if (null == tempFragment) {
            tempFragment = FragmentUtils.getBaseFragmentByIndex(index);
        }

        if (tempFragment.isAdded()) {
            if (null == mCurrentFragment) return;

            mFragmentManager.beginTransaction().hide(mCurrentFragment).show(tempFragment).commit();
        } else {
            if (null != mCurrentFragment) {
                mFragmentManager.beginTransaction().hide(mCurrentFragment).add(R.id.id_layout_content, tempFragment, String.valueOf(index)).commit();
            } else {
                mFragmentManager.beginTransaction().add(R.id.id_layout_content, tempFragment, String.valueOf(index)).commit();
            }
        }

        mCurrentFragment = tempFragment;
        mCurrentIndex = index;
    }

    private void initTabLayout() {
        String[] listData = getResources().getStringArray(R.array.main_item);
        int[] dataSource = {R.drawable.ic_tab_news,R.drawable.ic_tab_article,R.drawable.ic_tab_question,
                            R.drawable.ic_tab_discover,R.drawable.ic_tab_mine};
        int[] dataSourcePress = {R.drawable.ic_tab_news_press,R.drawable.ic_tab_article_press,R.drawable.ic_tab_question_press,
                            R.drawable.ic_tab_discover_press,R.drawable.ic_tab_mine_press};

        ArrayList<CustomTabEntity> innerList = new ArrayList<>();
        CustomTabEntity tabEntity ;
        for(int i = 0 ; i < listData.length; i++) {
            tabEntity = new TabEntity(listData[i],dataSourcePress[i],dataSource[i]);
            innerList.add(tabEntity);
        }

        mTabLayout.setTabData(innerList);
        mTabLayout.setOnTabSelectListener(this);
    }

    @Override
    public void onTabSelect(int position) {
        changeFragment(position);
    }

    @Override
    public void onTabReselect(int position) {}

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //super.onSaveInstanceState(outState);
    }
}
