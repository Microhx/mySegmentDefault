package com.micro.mysegmentdefault.middleimpl.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.micro.mysegmentdefault.base.adapter.BasePagerAdapter;
import com.micro.mysegmentdefault.entity.TagDataEntity;
import com.micro.mysegmentdefault.middleimpl.subfragment.QuestionSubFragment;

import java.util.List;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/10 - 18:35 <p>
 * interface :
 */

public class QuestionFragmentPagerAdapter extends BasePagerAdapter<TagDataEntity.Item> {


    public QuestionFragmentPagerAdapter(List<TagDataEntity.Item> titleEntityList, FragmentManager fm) {
        super(titleEntityList, fm);
    }


    @Override
    public CharSequence getPageTitle(int position, TagDataEntity.Item titleEntity) {
        return titleEntity.name;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new QuestionSubFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        bundle.putString("channel", mTitleEntityList.get(position).id);
        fragment.setArguments(bundle);
        return fragment;
    }
}
