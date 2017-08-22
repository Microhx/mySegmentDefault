package com.micro.mysegmentdefault.middleimpl.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.SegmentApplication;
import com.micro.mysegmentdefault.base.adapter.BasePagerAdapter;
import com.micro.mysegmentdefault.base.module.BaseFragment;
import com.micro.mysegmentdefault.entity.TitleEntity;
import com.micro.mysegmentdefault.middleimpl.adapter.HomeFragmentPagerAdapter;
import com.micro.mysegmentdefault.middleimpl.subfragment.ToutiaoSubFragment;
import com.micro.mysegmentdefault.ui.UserPublishToutiaoActivity;
import com.micro.mysegmentdefault.utils.CommonUtils;
import com.micro.mysegmentdefault.utils.FileUtils;

import java.util.List;

import butterknife.Bind;

/**
 * author : micro_hx <p>
 * desc : 头条fragment<p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/6 - 12:22 <p>
 * interface :
 */

public class ToutiaoFragment extends BaseFragment implements SexChooseDialog.OnSexChooseInterface {

    @Bind(R.id.id_tv_tab_layout)
    TabLayout mTableLayout;

    @Bind(R.id.id_view_pager)
    ViewPager mViewPager;

    @Bind(R.id.id_toolbar_layout)
    Toolbar mToolBar;

    private List<TitleEntity> mTitleEntityList;

    private BasePagerAdapter mBasePageAdapter;

    //0为最热的 1为最新的
    private int mToutiaoSortIndex = 0;

    //选择排列方式
    private SexChooseDialog mToutiaoSortDialog;

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(this.mToolBar);
        //the fragment has menu items to contribute.
        setHasOptionsMenu(true);

        mTitleEntityList = FileUtils.readHomeTitleEntity(SegmentApplication.getApplication());
        mBasePageAdapter = new HomeFragmentPagerAdapter(mTitleEntityList, getChildFragmentManager());
        mViewPager.setAdapter(mBasePageAdapter);
        mTableLayout.setupWithViewPager(mViewPager);
        mTableLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_home, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id_menu_sort: //列表排列方式
                chooseType();
                break;

            case R.id.id_menu_add: //添加新的头条
                goWithActivity(UserPublishToutiaoActivity.class);
                break;
        }

        return true;
    }

    private void chooseType() {
        if (null == mToutiaoSortDialog) {
            mToutiaoSortDialog = new SexChooseDialog();
            mToutiaoSortDialog.setSexChooseListener(this);
        }
        Bundle bundle = new Bundle();
        bundle.putInt("type", 1);
        bundle.putInt("index", mToutiaoSortIndex);
        mToutiaoSortDialog.setArguments(bundle);
        mToutiaoSortDialog.show(getFragmentManager(), "sort");
    }

    @Override
    public void onSexChoose(int position) {
        if (position != mToutiaoSortIndex) {
            mToutiaoSortIndex = position;
            //设置静态变量
            ToutiaoSubFragment.MSORTVALUE = mToutiaoSortIndex;

            //获取当前页面
            int currentIndex = mViewPager.getCurrentItem();

            //获取当前fragment
            List<Fragment> fragmentList = getChildFragmentManager().getFragments();

            //判断是否为null
            if(CommonUtils.collectionCheckIndex(fragmentList,currentIndex)) {
                Fragment targetFragment = fragmentList.get(currentIndex);
                //LogUtils.d("--------targetFragment-------->>" + targetFragment);
                if (targetFragment instanceof ToutiaoSubFragment) {
                    ((ToutiaoSubFragment) targetFragment).reloadData();
                }
            }
        }
    }
}
