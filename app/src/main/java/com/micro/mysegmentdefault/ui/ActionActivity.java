package com.micro.mysegmentdefault.ui;

import android.widget.FrameLayout;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.adapter.BaseRecyclerAdapter;
import com.micro.mysegmentdefault.entity.ActionDataEntity;
import com.micro.mysegmentdefault.middle.BaseRefreshActivity;
import com.micro.mysegmentdefault.middleimpl.adapter.ActionRecyclerAdapter;
import com.micro.mysegmentdefault.middleimpl.mvp.model.ActionModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.ActionPresenter;

/**
 * author : micro_hx <p>
 * desc : 最新动态 <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/16 - 21:17 <p>
 * interface :
 */

public class ActionActivity extends BaseRefreshActivity<ActionPresenter, ActionModel, ActionDataEntity.Item> {

    //TODO 需要一个锚点 06/02

    @Override
    protected void initTitleSetting(FrameLayout mTitleContent) {
        mTvTitle.setText(R.string.str_tweet);
    }

    @Override
    protected BaseRecyclerAdapter getRecyclerAdapter() {
        return new ActionRecyclerAdapter(this);
    }

    @Override
    protected String getDefaultChannel() {
        //加上mPageEntity==null 因为还有可能刷新
        if (mBaseRecyclerAdapter.getCount() == 0 || mPageEntity == null) return "0";
        return mBaseRecyclerAdapter.getItem(mBaseRecyclerAdapter.getCount() - 1).mtime;
    }
}
