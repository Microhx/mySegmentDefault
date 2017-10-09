package com.micro.mysegmentdefault.middleimpl.subfragment;

import com.micro.mysegmentdefault.base.adapter.BaseRecyclerAdapter;
import com.micro.mysegmentdefault.entity.NewQuestionListData;
import com.micro.mysegmentdefault.middle.BaseRefreshFragment;
import com.micro.mysegmentdefault.middleimpl.adapter.TagDetailQuestionRecyclerAdapter;
import com.micro.mysegmentdefault.middleimpl.mvp.model.TagDetailQuestionModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.TagDetailQuestionPresenter;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/10 - 18:37 <p>
 * interface :
 */

public class QuestionSubFragment extends BaseRefreshFragment<TagDetailQuestionPresenter,TagDetailQuestionModel,NewQuestionListData> {

    private String mTagId;
    private int mPosition ;

    @Override
    protected void initOnCreateMethod() {
        mTagId = getArguments().getString("channel", "");
        mPosition = getArguments().getInt("position",0) ;
    }

    @Override
    protected BaseRecyclerAdapter<NewQuestionListData> getRecyclerAdapter() {
        return new TagDetailQuestionRecyclerAdapter(getActivity());
    }

    @Override
    protected String getDefaultChannel() {
        return mTagId;
    }

    @Override
    protected int getCommonType() {
        return mPosition < 2 ? Integer.MIN_VALUE : 0 ;
    }
}
