package com.micro.mysegmentdefault.base.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.micro.mysegmentdefault.entity.TitleEntity;
import com.micro.mysegmentdefault.utils.CommonUtils;

import java.util.List;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/6 - 16:20 <p>
 * interface :
 */

public abstract class BasePagerAdapter<T> extends FragmentPagerAdapter {

    protected List<T> mTitleEntityList ;


    public BasePagerAdapter(List<T> titleEntityList , FragmentManager fm) {
        super(fm);
        this.mTitleEntityList = titleEntityList ;
    }

    public void updateDataList(List<T> titleEntityList) {
        this.mTitleEntityList = titleEntityList;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return CommonUtils.collectionIsNull(mTitleEntityList) ? 0 : mTitleEntityList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return getPageTitle(position,mTitleEntityList.get(position)) ;
    }

    public abstract CharSequence getPageTitle(int position , T t) ;

}
