package com.micro.mysegmentdefault.middleimpl.subfragment;

import com.micro.mysegmentdefault.base.adapter.BaseRecyclerAdapter;
import com.micro.mysegmentdefault.entity.PageEntity;
import com.micro.mysegmentdefault.entity.TagDetailUserEntity;
import com.micro.mysegmentdefault.middle.BaseRefreshFragment;
import com.micro.mysegmentdefault.middleimpl.adapter.TagDetailUserAdapter;
import com.micro.mysegmentdefault.middleimpl.mvp.model.TagDetailUserModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.TagDetailUserPresenter;

import java.util.List;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/9 - 16:50 <p>
 * interface :
 */

public class TagDetailTopUserFragment extends BaseRefreshFragment<TagDetailUserPresenter, TagDetailUserModel, TagDetailUserEntity.Item> {

    private String mTagId;

    @Override
    protected void initOnCreateMethod() {
        mTagId = getArguments().getString("tagId", "");
    }

    @Override
    protected BaseRecyclerAdapter getRecyclerAdapter() {
        return new TagDetailUserAdapter(getActivity());
    }

    @Override
    public void getCommonListDatas(int startPages, List<TagDetailUserEntity.Item> mDataList, PageEntity entity) {
        super.getCommonListDatas(startPages, mDataList, entity);

        mBaseRecyclerAdapter.setState(BaseRecyclerAdapter.STATE_HIDE, true);
        mRefreshLayout.setCanLoadMore(false);

    }

    @Override
    protected String getDefaultChannel() {
        return mTagId;
    }
}
