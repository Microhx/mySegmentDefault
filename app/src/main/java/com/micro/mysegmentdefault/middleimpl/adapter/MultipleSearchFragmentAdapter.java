package com.micro.mysegmentdefault.middleimpl.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.micro.mysegmentdefault.base.adapter.BasePagerAdapter;
import com.micro.mysegmentdefault.middleimpl.subfragment.SearchSubFragment;

import java.util.List;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/29 - 14:27 <p>
 * interface :
 */

public class MultipleSearchFragmentAdapter extends BasePagerAdapter<String> {

    public MultipleSearchFragmentAdapter(List<String> list, FragmentManager fm) {
        super(list, fm);
    }

    @Override
    public CharSequence getPageTitle(int position, String s) {
        return s;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new SearchSubFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        fragment.setArguments(bundle);
        return fragment;
    }
}
