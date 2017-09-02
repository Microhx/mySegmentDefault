package com.micro.mysegmentdefault.ui.userzone;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.adapter.BaseRecyclerAdapter;
import com.micro.mysegmentdefault.entity.UserTimeLineDataEntity;
import com.micro.mysegmentdefault.middle.BaseRefreshActivity;
import com.micro.mysegmentdefault.middleimpl.adapter.UserTimeLineAdapter;
import com.micro.mysegmentdefault.middleimpl.mvp.model.UserTimeLineModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.UserTimeLinePresenter;

/**
 * author : micro_hx <p>
 * desc : 用户动态<p>
 * email: javainstalling@163.com <p>
 * date : 2017/8/28 - 14:20 <p>
 * interface :
 */

public class UserTimeLineActivity extends BaseRefreshActivity<UserTimeLinePresenter,UserTimeLineModel,UserTimeLineDataEntity.DataItem>{

    private String mUserId ;

    public static void start(Context context,String userId) {
        context.startActivity(new Intent(context,UserTimeLineActivity.class).putExtra("userId",userId));
    }

    @Override
    protected void initBeforeView(Bundle savedInstanceState) {
        mUserId = getIntent().getStringExtra("userId");
    }

    @Override
    protected void initTitleSetting(FrameLayout mTitleContent) {
        mTvTitle.setText(R.string.str_simple_trend);
    }

    @Override
    protected String getDefaultChannel() {
        return  mUserId ;
    }


    @Override
    protected BaseRecyclerAdapter getRecyclerAdapter() {
        return new UserTimeLineAdapter(this);
    }
}
