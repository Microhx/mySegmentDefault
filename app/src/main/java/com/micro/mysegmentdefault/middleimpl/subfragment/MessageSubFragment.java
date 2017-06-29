package com.micro.mysegmentdefault.middleimpl.subfragment;

import com.micro.mysegmentdefault.base.adapter.BaseRecyclerAdapter;
import com.micro.mysegmentdefault.entity.MessageDataEntity;
import com.micro.mysegmentdefault.middle.BaseRefreshFragment;
import com.micro.mysegmentdefault.middleimpl.adapter.MessageRecyclerAdapter;
import com.micro.mysegmentdefault.middleimpl.mvp.model.MessageModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.MessagePresenter;

/**
 * author : micro_hx <p>
 * desc : 消息fragment<p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/6 - 15:31 <p>
 * interface :
 */

public class MessageSubFragment extends BaseRefreshFragment<MessagePresenter,MessageModel,MessageDataEntity.Item> {

    private int mPosition ;

    @Override
    protected void initOnCreateMethod() {
        mPosition = getArguments().getInt("position",0);
    }

    @Override
    protected int getCommonType() {
        return mPosition;
    }

    @Override
    protected BaseRecyclerAdapter getRecyclerAdapter() {
        return new MessageRecyclerAdapter(getActivity());
    }
}
