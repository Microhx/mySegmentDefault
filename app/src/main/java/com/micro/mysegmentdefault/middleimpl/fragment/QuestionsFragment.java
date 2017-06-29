package com.micro.mysegmentdefault.middleimpl.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.adapter.BasePagerAdapter;
import com.micro.mysegmentdefault.base.module.BaseFragment;
import com.micro.mysegmentdefault.entity.MessageEvent;
import com.micro.mysegmentdefault.entity.TagDataEntity;
import com.micro.mysegmentdefault.middleimpl.adapter.QuestionFragmentPagerAdapter;
import com.micro.mysegmentdefault.ui.UserTagManageActivity;
import com.micro.mysegmentdefault.utils.FileUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * author : micro_hx <p>
 * desc : 问答fragment<p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/6 - 12:23 <p>
 * interface :
 */

public class QuestionsFragment extends BaseFragment {

    @Bind(R.id.id_tv_tab_layout)
    TabLayout mTableLayout;

    @Bind(R.id.id_view_pager)
    ViewPager mViewPager;

    @Bind(R.id.id_iv_right)
    ImageView mRightImage;

    private List<TagDataEntity.Item> mTitleEntityList;

    private BasePagerAdapter mBasePageAdapter;

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews() {
        mRightImage.setVisibility(View.VISIBLE);
        mTitleEntityList = FileUtils.getNewsTitleEntityList(1);

        mBasePageAdapter = new QuestionFragmentPagerAdapter(mTitleEntityList, getChildFragmentManager());
        mViewPager.setAdapter(mBasePageAdapter);
        mTableLayout.setupWithViewPager(mViewPager);
        mTableLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    @OnClick(R.id.id_iv_right)
    public void onCall(View e) {
        goWithActivity(UserTagManageActivity.class);
    }


    @Override
    protected void initOnCreateMethod() {
        super.initOnCreateMethod();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        if (null != event && event.type == -1) {
            mTitleEntityList = FileUtils.getNewsTitleEntityList(1);
            mBasePageAdapter.updateDataList(mTitleEntityList);
            mViewPager.setCurrentItem(mTitleEntityList.size() - 1);
        }
    }

}
