package com.micro.mysegmentdefault.middleimpl.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.micro.mysegmentdefault.base.adapter.BasePagerAdapter;
import com.micro.mysegmentdefault.entity.TitleEntity;
import com.micro.mysegmentdefault.middleimpl.subfragment.UserTagFragment;

import java.util.List;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/8 - 17:00 <p>
 * interface :
 */

public class UserTagManageFragmentPagerAdapter extends BasePagerAdapter<String> {

    public UserTagManageFragmentPagerAdapter(List<String> titleEntityList, FragmentManager fm) {
        super(titleEntityList, fm);
    }

    @Override
    public CharSequence getPageTitle(int position, String s) {
        return s;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new UserTagFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        fragment.setArguments(bundle);

        return fragment;
    }
}
