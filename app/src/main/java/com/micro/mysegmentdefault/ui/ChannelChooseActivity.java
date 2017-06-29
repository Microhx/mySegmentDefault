package com.micro.mysegmentdefault.ui;

import android.content.Intent;
import android.widget.FrameLayout;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.adapter.BaseRecyclerAdapter;
import com.micro.mysegmentdefault.entity.ChannelDataEntity;
import com.micro.mysegmentdefault.middle.BaseRefreshActivity;
import com.micro.mysegmentdefault.middleimpl.adapter.ChannelChooseAdapter;
import com.micro.mysegmentdefault.middleimpl.mvp.model.ChannelChooseModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.ChannelChoosePresenter;

/**
 * 频道选择
 */
public class ChannelChooseActivity extends BaseRefreshActivity<ChannelChoosePresenter,ChannelChooseModel,ChannelDataEntity.Item> implements ChannelChooseAdapter.onItemChooseListener {

    @Override
    protected void initTitleSetting(FrameLayout mTitleContent) {
        mTvTitle.setText(R.string.str_choose_channel);
    }

    @Override
    protected BaseRecyclerAdapter<ChannelDataEntity.Item> getRecyclerAdapter() {
        return new ChannelChooseAdapter(getApplicationContext(), this);
    }


    @Override
    public void onChoose(String item,String itemCode) {
        setResult(RESULT_OK,new Intent().putExtra("channelName" , item).putExtra("channelCode",itemCode));
        finish();
    }
}
