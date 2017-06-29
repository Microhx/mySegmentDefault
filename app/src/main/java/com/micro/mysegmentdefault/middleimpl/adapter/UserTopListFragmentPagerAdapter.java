package com.micro.mysegmentdefault.middleimpl.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.micro.mysegmentdefault.base.adapter.BaseStatePageAdapter;
import com.micro.mysegmentdefault.middleimpl.subfragment.UserTopListFragment;

import java.util.List;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/16 - 22:45 <p>
 * interface :
 */

public class UserTopListFragmentPagerAdapter extends BaseStatePageAdapter<String> {

    public UserTopListFragmentPagerAdapter(List<String> titleEntityList, FragmentManager fm) {
        super(titleEntityList, fm);
    }

    @Override
    public CharSequence getPageTitle(int position, String s) {
        return s;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new UserTopListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        fragment.setArguments(bundle);
        return fragment;
    }

    public void updateTitleInfo(List<String> mListTitle) {
        mTitleEntityList = mListTitle;
        notifyDataSetChanged();
    }
}
