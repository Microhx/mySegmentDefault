package com.micro.mysegmentdefault.ui.user.message;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.SegmentApplication;
import com.micro.mysegmentdefault.base.adapter.BaseRecyclerAdapter;
import com.micro.mysegmentdefault.entity.UserPrivateEventDetailDataEntity;
import com.micro.mysegmentdefault.middle.BaseRefreshActivity;
import com.micro.mysegmentdefault.middle.view.AbsUserPrivateEventDetailView;
import com.micro.mysegmentdefault.middleimpl.adapter.UserPrivateEventDetailRecyclerAdapter;
import com.micro.mysegmentdefault.middleimpl.mvp.model.UserPrivateEventDetailModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.UserPrivateEventDetailPresenter;
import com.micro.mysegmentdefault.utils.LogUtils;

import butterknife.OnClick;

/**
 * author : micro_hx <p>
 * desc : 私信具体内容 <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/12 - 21:59 <p>
 * interface :
 */

public class UserPrivateEventDetailActivity extends
        BaseRefreshActivity<UserPrivateEventDetailPresenter,
                            UserPrivateEventDetailModel,
                            UserPrivateEventDetailDataEntity.DataItem> implements AbsUserPrivateEventDetailView<UserPrivateEventDetailDataEntity.DataItem> {

    private String mEventId ;
    private String mUserPhoto;

    @Override
    protected void initTitleSetting(FrameLayout mTitleContent) {
        mTvTitle.setText(R.string.str_private_event);
        mIvRightButton.setVisibility(View.VISIBLE);
        mIvRightButton.setImageResource(R.drawable.ic_chat_delete);
    }


    @Override
    protected void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @OnClick(R.id.id_iv_right)
    public void onDeletePrivateEvent(View v) {
        if(TextUtils.isEmpty(mEventId)) return;
        mPresenter.deletePrivateEvent(mEventId);
    }

    public static void start(String eventId,String userPhoto) {
        Intent _intent = new Intent(SegmentApplication.getApplication(),UserPrivateEventDetailActivity.class);
        _intent.putExtra("eventId",eventId).
                putExtra("userPhoto" , userPhoto).
                setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        SegmentApplication.getApplication().startActivity(_intent);
    }

    protected void defaultSettingLayoutManager() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setReverseLayout(true);
        mRecyclerView.setLayoutManager(manager);
    }


    @Override
    protected void initBeforeView(Bundle savedInstanceState) {
        mEventId = getIntent().getStringExtra("eventId");
        mUserPhoto = getIntent().getStringExtra("userPhoto");

        LogUtils.d("eventId : " + mEventId + " , userPhoto : " + mUserPhoto);
    }

    @Override
    protected String getDefaultChannel() {
        return mEventId;
    }

    @Override
    protected BaseRecyclerAdapter getRecyclerAdapter() {
        return new UserPrivateEventDetailRecyclerAdapter(this,mUserPhoto);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onDeletePrivateEventResult(boolean result) {
        if(result) {
            showToast(R.string.str_delete_success);
            finish();
        }else {
            showToast(R.string.str_operation_error);
        }
    }
}
