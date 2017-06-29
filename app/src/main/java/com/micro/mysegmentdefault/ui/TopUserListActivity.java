package com.micro.mysegmentdefault.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.module.BaseActivity;
import com.micro.mysegmentdefault.entity.TopUserEntity;
import com.micro.mysegmentdefault.middle.TopUserContract;
import com.micro.mysegmentdefault.middleimpl.adapter.UserTopListFragmentPagerAdapter;
import com.micro.mysegmentdefault.middleimpl.mvp.model.TopUserModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.TopUserPresenter;
import com.micro.mysegmentdefault.middleimpl.subfragment.UserTopListFragment;
import com.micro.mysegmentdefault.utils.CommonUtils;
import com.micro.mysegmentdefault.utils.LogUtils;
import com.micro.mysegmentdefault.view.widget.PublicHeadLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * author : micro_hx <p>
 * desc : 用户排行榜 <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/6 - 12:23 <p>
 * interface :
 */
public class TopUserListActivity extends BaseActivity<TopUserPresenter, TopUserModel> implements TopUserContract.TopUserView {

    @Bind(R.id.id_tv_tab_layout)
    TabLayout mTabLayout;

    @Bind(R.id.id_view_pager)
    ViewPager mViewPager;

    @Bind(R.id.id_widget_layout)
    PublicHeadLayout mTitleLayout;

    private UserTopListFragmentPagerAdapter mFragmentPagerAdapter;

    private List<String> mListTitle;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_user_tag_manage;
    }

    @Override
    protected void initViews() {
        mTitleLayout.setTitle(R.string.str_user_list);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        mListTitle = new ArrayList<>();
        mFragmentPagerAdapter = new UserTopListFragmentPagerAdapter(mListTitle, getSupportFragmentManager());

        mViewPager.setAdapter(mFragmentPagerAdapter);
        mViewPager.setOffscreenPageLimit(5);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this, mModel);
        mPresenter.loadTopUserInfo();
    }


    @Override
    public void showTopUserEntity(TopUserEntity entity) {
        mListTitle.add("今天");
        mListTitle.add("本周");
        mListTitle.add(entity.data.month);
        mListTitle.add(entity.data.quarter);
        mListTitle.add(entity.data.year);
        mListTitle.add("历史记录");

        mFragmentPagerAdapter.updateTitleInfo(mListTitle);

        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
        if (!CommonUtils.collectionIsNull(fragmentList)) {
            for (Fragment f : fragmentList) {
                if (f instanceof UserTopListFragment) {
                    UserTopListFragment topFragment = (UserTopListFragment) f;

                    if (topFragment.getCurrentPosition() == 0) {
                        topFragment.loadAllDatas(entity.data.dayTopUsers);
                    } else if (topFragment.getCurrentPosition() == 1) {
                        topFragment.loadAllDatas(entity.data.weekTopUsers);
                    } else if (topFragment.getCurrentPosition() == 2) {
                        topFragment.loadAllDatas(entity.data.monthTopUsers);
                    } else if (topFragment.getCurrentPosition() == 3) {
                        topFragment.loadAllDatas(entity.data.quarterTopUsers);
                    } else if (topFragment.getCurrentPosition() == 4) {
                        topFragment.loadAllDatas(entity.data.yearTopUsers);
                    } else if (topFragment.getCurrentPosition() == 5) {
                        topFragment.loadAllDatas(entity.data.topUsers);
                    }
                }
            }
        }
    }

    @Override
    public void onLoadingDataError() {
        LogUtils.d("onloading data error ----->>");
    }

    @OnClick(R.id.id_iv_back)
    public void onCallBack(View v){
        finish();
    }

}
