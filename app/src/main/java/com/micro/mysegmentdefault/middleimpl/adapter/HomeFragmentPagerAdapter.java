package com.micro.mysegmentdefault.middleimpl.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.micro.mysegmentdefault.base.adapter.BasePagerAdapter;
import com.micro.mysegmentdefault.entity.TitleEntity;
import com.micro.mysegmentdefault.middleimpl.subfragment.ToutiaoSubFragment;

import java.util.List;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/6 - 16:43 <p>
 * interface :
 */

public class HomeFragmentPagerAdapter extends BasePagerAdapter<TitleEntity> {

    public HomeFragmentPagerAdapter(List<TitleEntity> titleEntityList, FragmentManager fm) {
        super(titleEntityList, fm);
    }

    @Override
    public CharSequence getPageTitle(int position, TitleEntity item) {
        return item.name;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new ToutiaoSubFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        bundle.putString("title", mTitleEntityList.get(position).channel);
        fragment.setArguments(bundle);

        return fragment;
    }


}
