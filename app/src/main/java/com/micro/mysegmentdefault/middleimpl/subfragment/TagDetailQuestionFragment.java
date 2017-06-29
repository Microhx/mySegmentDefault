package com.micro.mysegmentdefault.middleimpl.subfragment;

import com.micro.mysegmentdefault.base.adapter.BaseRecyclerAdapter;
import com.micro.mysegmentdefault.entity.TagDetailQuestionEntity;
import com.micro.mysegmentdefault.middle.BaseRefreshFragment;
import com.micro.mysegmentdefault.middleimpl.adapter.TagDetailQuestionRecyclerAdapter;
import com.micro.mysegmentdefault.middleimpl.mvp.model.TagDetailQuestionModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.TagDetailQuestionPresenter;
import com.micro.mysegmentdefault.utils.LogUtils;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/9 - 16:50 <p>
 * interface :
 */

public class TagDetailQuestionFragment extends BaseRefreshFragment<TagDetailQuestionPresenter,
        TagDetailQuestionModel,
        TagDetailQuestionEntity.Item> {

    private String mTagId;

    @Override
    protected void initOnCreateMethod() {
        mTagId = getArguments().getString("tagId", "");
    }

    @Override
    protected BaseRecyclerAdapter getRecyclerAdapter() {
        return new TagDetailQuestionRecyclerAdapter(getActivity());
    }

    @Override
    protected String getDefaultChannel() {
        return mTagId;
    }
}
