package com.micro.mysegmentdefault.middleimpl.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.micro.mysegmentdefault.base.adapter.BasePagerAdapter;
import com.micro.mysegmentdefault.middleimpl.subfragment.UserPageFragment;
import com.micro.mysegmentdefault.middleimpl.subfragment.UserRecordFragment;

import java.util.List;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/21 - 7:58 <p>
 * interface :
 */

public class UserZonePagerAdapter extends BasePagerAdapter<String> {

    private String mUserName;

    public UserZonePagerAdapter(String userName, List<String> titleEntityList, FragmentManager fm) {
        super(titleEntityList, fm);
        this.mUserName = userName;
    }

    @Override
    public CharSequence getPageTitle(int position, String s) {
        return s;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment tempFragment = (position == 0 ? new UserPageFragment() : new UserRecordFragment());
        Bundle bundle = new Bundle();
        bundle.putString("userName" , mUserName);
        tempFragment.setArguments(bundle);

        return tempFragment;
    }
}
