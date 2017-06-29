package com.micro.mysegmentdefault.ui.user.message;

import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.adapter.BaseRecyclerAdapter;
import com.micro.mysegmentdefault.entity.UserPrivateEventDataEntity;
import com.micro.mysegmentdefault.middle.BaseRefreshActivity;
import com.micro.mysegmentdefault.middleimpl.adapter.UserPrivateEventRecyclerAdapter;
import com.micro.mysegmentdefault.middleimpl.mvp.model.UserPrivateEventModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.UserPrivateEventPresenter;

import butterknife.OnClick;

/**
 * author : micro_hx <p>
 * desc : 用户私信<p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/12 - 17:53 <p>
 * interface :
 */

public class UserPrivateEventActivity extends BaseRefreshActivity<UserPrivateEventPresenter,UserPrivateEventModel,UserPrivateEventDataEntity.DataItem> {


    @Override
    protected void initTitleSetting(FrameLayout mTitleContent) {
        mTvTitle.setText(R.string.str_private_event);
        mTvRightMessage.setVisibility(View.VISIBLE);
        mTvRightMessage.setText(R.string.str_neglect_event);
    }



    @Override
    protected String getDefaultChannel() {
        return  "inbox" ;
    }

    @Override
    protected BaseRecyclerAdapter getRecyclerAdapter() {
        return new UserPrivateEventRecyclerAdapter(this);
    }
}
