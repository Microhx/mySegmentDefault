package com.micro.mysegmentdefault.base.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.micro.mysegmentdefault.utils.CommonUtils;

import java.util.List;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/10 - 14:37 <p>
 * interface :
 */

public abstract class BaseStatePageAdapter<T> extends FragmentStatePagerAdapter {

    protected List<T> mTitleEntityList ;

    public BaseStatePageAdapter(List<T> titleEntityList , FragmentManager fm) {
        super(fm);
        this.mTitleEntityList = titleEntityList ;
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
