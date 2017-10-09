package com.micro.mysegmentdefault.ui.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.module.BaseActivity;
import com.micro.mysegmentdefault.entity.MessageEvent;
import com.micro.mysegmentdefault.entity.UserDataEntity;
import com.micro.mysegmentdefault.logic.UserLogic;
import com.micro.mysegmentdefault.middle.UserZoneContract;
import com.micro.mysegmentdefault.middleimpl.adapter.UserZonePagerAdapter;
import com.micro.mysegmentdefault.middleimpl.mvp.model.UserZoneModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.UserZonePresenter;
import com.micro.mysegmentdefault.utils.ImageUtils;
import com.micro.mysegmentdefault.utils.LogUtils;
import com.micro.mysegmentdefault.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;

/**
 * author : micro_hx <p>
 * desc : 用户空间Activity<p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/19 - 11:36 <p>
 * interface :
 */

public class UserZoneActivity extends BaseActivity<UserZonePresenter,UserZoneModel> implements UserZoneContract.UserZoneView, Toolbar.OnMenuItemClickListener {

    @Bind(R.id.id_toolbar_layout)
    CollapsingToolbarLayout mToolBarLayout ;

    @Bind(R.id.id_tv_tab_layout)
    TabLayout mTabLayout ;

    @Bind(R.id.id_vp_content)
    ViewPager mViewPager ;

    @Bind(R.id.id_iv_user_bg)
    ImageView mImageViewBg ;

    @Bind(R.id.id_iv_user_icon)
    ImageView mImageUserIcon;

    @Bind(R.id.id_tv_gold)
    TextView mTvGold;

    @Bind(R.id.id_tv_silver)
    TextView mTvSilver;

    @Bind(R.id.id_tv_copper)
    TextView mTvCopper;

    @Bind(R.id.id_tv_reputation)
    TextView mTvReputation;

    @Bind(R.id.id_tv_attention)
    TextView mTvAttention;

    @Bind(R.id.id_tv_fans)
    TextView mTvFans;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    private UserZonePagerAdapter  mZonePageAdapter ;

    private String mUserName ;


    public static void start(Context ctx , String userName){
        Intent _intent = new Intent(ctx,UserZoneActivity.class);
        _intent.putExtra("userName",userName);
        ctx.startActivity(_intent);
    }

    @Override
    protected void initBeforeView(Bundle savedInstanceState) {
        mUserName = getIntent().getStringExtra("userName");
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this,mModel);
        mPresenter.loadUserZoneBaseInfo(mUserName);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_user_zone;
    }

    @Override
    protected void initViews() {
        setSupportActionBar(mToolbar);
        mToolbar.setOnMenuItemClickListener(this);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initEventBus();

        List<String> userList = Arrays.asList(getResources().getStringArray(R.array.user_zone_list));
        mZonePageAdapter = new UserZonePagerAdapter(mUserName,userList,getSupportFragmentManager());

        mViewPager.setAdapter(mZonePageAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initEventBus() {
        if(mUserName.equals(UserLogic.getUserSlug())) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void loadUserZoneData(UserDataEntity entity) {
        ImageUtils.showUrlImageFixXY(entity.data.avatarUrl,mImageViewBg);
        ImageUtils.showUserCircleImageUrl(entity.data.avatarUrl,mImageUserIcon);

        mToolBarLayout.setTitle(entity.data.name);
        mTvGold.setText(entity.data.summaryBadges.gold);
        mTvSilver.setText(entity.data.summaryBadges.silver);
        mTvCopper.setText(entity.data.summaryBadges.bronze);

        mTvReputation.setText(entity.data.rank);
        mTvAttention.setText(entity.data.followingUsers);
        mTvFans.setText(entity.data.followedUsers);
    }

    @Override
    public void loadUserZoneError() {
        ToastUtils.showMessage(this,R.string.data_request_error);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(mUserName.equals(UserLogic.getUserSlug())) {
            getMenuInflater().inflate(R.menu.user_title_menu,menu);
        }
        return mUserName.equals(UserLogic.getUserSlug());
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if(item.getItemId() == R.id.id_menu_edit) {
            go(UserEditInfoActivity.class);
        }

        return true;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        if(null != event && event.type == 4) {
            ImageUtils.showUrlImageFixXY(UserLogic.getUserPhoto(),mImageViewBg);
            ImageUtils.showUrlImage(UserLogic.getUserPhoto(),mImageUserIcon);
            mToolBarLayout.setTitle(UserLogic.getUserName());
        }
    }

    @Override
    protected void onDestroy() {
        if(mUserName.equals(UserLogic.getUserSlug())) {
            EventBus.getDefault().unregister(this);
        }

        super.onDestroy();
    }
}
