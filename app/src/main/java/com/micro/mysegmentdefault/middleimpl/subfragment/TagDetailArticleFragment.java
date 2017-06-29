package com.micro.mysegmentdefault.middleimpl.subfragment;

import com.micro.mysegmentdefault.base.adapter.BaseRecyclerAdapter;
import com.micro.mysegmentdefault.entity.TagDetailArticleEntity;
import com.micro.mysegmentdefault.middle.BaseRefreshFragment;
import com.micro.mysegmentdefault.middleimpl.adapter.TagDetailArticleAdapter;
import com.micro.mysegmentdefault.middleimpl.mvp.model.TagDetailArticleModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.TagDetailArticlePresenter;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/9 - 16:50 <p>
 * interface :
 */

public class TagDetailArticleFragment extends
        BaseRefreshFragment<TagDetailArticlePresenter, TagDetailArticleModel, TagDetailArticleEntity.Item> {

    private String mTagId;

    @Override
    protected void initOnCreateMethod() {
        mTagId = getArguments().getString("tagId", "");
    }

    @Override
    protected BaseRecyclerAdapter getRecyclerAdapter() {
        return new TagDetailArticleAdapter(getActivity());
    }

    @Override
    protected String getDefaultChannel() {
        return mTagId;
    }
}
