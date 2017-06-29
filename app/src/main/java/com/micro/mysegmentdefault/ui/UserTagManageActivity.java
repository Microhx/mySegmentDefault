package com.micro.mysegmentdefault.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.module.BaseActivity;
import com.micro.mysegmentdefault.entity.MessageEvent;
import com.micro.mysegmentdefault.entity.TagDataEntity;
import com.micro.mysegmentdefault.middleimpl.adapter.UserTagManageFragmentPagerAdapter;
import com.micro.mysegmentdefault.middleimpl.subfragment.UserTagFragment;
import com.micro.mysegmentdefault.utils.FileUtils;
import com.micro.mysegmentdefault.view.widget.PublicHeadLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * author : micro_hx <p>
 * desc : 标签管理<p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/8 - 13:58 <p>
 * interface :
 */

public class UserTagManageActivity extends BaseActivity {

    @Bind(R.id.id_tv_tab_layout)
    TabLayout mTabLayout;

    @Bind(R.id.id_view_pager)
    ViewPager mViewPager;

    @Bind(R.id.id_widget_layout)
    PublicHeadLayout mTitleLayout;

    private UserTagManageFragmentPagerAdapter mFragmentPagerAdapter;

    private List<String> mListTitle;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_user_tag_manage;
    }

    @Override
    protected void initViews() {
        mTitleLayout.setTitle(R.string.str_manage_tags);
        mTitleLayout.setRightSource(R.drawable.ic_search);

        mListTitle = Arrays.asList(getResources().getStringArray(R.array.user_tag));
        mFragmentPagerAdapter = new UserTagManageFragmentPagerAdapter(mListTitle, getSupportFragmentManager());

        mViewPager.setAdapter(mFragmentPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onBackPressed() {
        onFinishEvent();
    }

    private void onFinishEvent() {
        saveHasFollowedTags();
        postEventBusEvent();
        finish();
    }

    private void postEventBusEvent() {
        MessageEvent event = new MessageEvent();
        event.type = -1;
        EventBus.getDefault().post(event);
    }

    private void saveHasFollowedTags() {
        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
        if (null != fragmentList && !fragmentList.isEmpty()) {
            for (Fragment f : fragmentList) {
                if (f instanceof UserTagFragment) {
                    UserTagFragment userFragment = (UserTagFragment) f;
                    if (userFragment.getCommonType() == 0) {
                        saveItemData(userFragment.getTagItems());
                        break;
                    }
                }
            }
        }
    }

    private void saveItemData(List<TagDataEntity.Item> tagItems) {
        FileUtils.saveTagDataEntity(this, tagItems);
    }

    @OnClick(R.id.id_iv_right)
    public void onCallEvent2(View v) {
        go(UserSearchTagActivity.class);
    }

    @OnClick(R.id.id_iv_back)
    public void onCallEvent1(View v) {
        onFinishEvent();
    }

}
