package com.micro.mysegmentdefault.middleimpl.subfragment;

import com.micro.mysegmentdefault.base.adapter.BaseRecyclerAdapter;
import com.micro.mysegmentdefault.entity.NewsDataEntity;
import com.micro.mysegmentdefault.middle.BaseRefreshFragment;
import com.micro.mysegmentdefault.middleimpl.adapter.NewsRecyclerAdapter;
import com.micro.mysegmentdefault.middleimpl.mvp.model.NewsModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.NewsPresenter;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/8 - 10:33 <p>
 * interface :
 */

public class ArticleSubFragment extends BaseRefreshFragment<NewsPresenter, NewsModel, NewsDataEntity.Item> {

    private int mPosition;
    private String mChannel;

    @Override
    protected void initOnCreateMethod() {
        if (null != getArguments()) {
            mPosition = getArguments().getInt("position");
            mChannel = getArguments().getString("channel");
        }
    }

    @Override
    protected BaseRecyclerAdapter<NewsDataEntity.Item> getRecyclerAdapter() {
        return new NewsRecyclerAdapter(getActivity());
    }

    @Override
    protected int getCommonType() {
        return mPosition <= 2 ? 0 : mPosition;
    }

    @Override
    protected String getDefaultChannel() {
        return mChannel;
    }
}
