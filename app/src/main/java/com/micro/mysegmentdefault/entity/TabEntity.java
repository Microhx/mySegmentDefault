package com.micro.mysegmentdefault.entity;

import com.flyco.tablayout.listener.CustomTabEntity;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/6 - 12:12 <p>
 * interface :
 */

public class TabEntity implements CustomTabEntity {

    private String mTitle ;
    private int mSelectIcon ;
    private int mUnSelectedIcon ;

    public TabEntity(String mTitle, int mSelectIcon, int mUnSelectedIcon) {
        this.mTitle = mTitle;
        this.mSelectIcon = mSelectIcon;
        this.mUnSelectedIcon = mUnSelectedIcon;
    }

    @Override
    public String getTabTitle() {
        return this.mTitle;
    }

    @Override
    public int getTabSelectedIcon() {
        return this.mSelectIcon;
    }

    @Override
    public int getTabUnselectedIcon() {
        return this.mUnSelectedIcon;
    }
}
