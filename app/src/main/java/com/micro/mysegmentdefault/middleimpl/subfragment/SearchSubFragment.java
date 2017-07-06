package com.micro.mysegmentdefault.middleimpl.subfragment;

import android.text.TextUtils;

import com.micro.mysegmentdefault.base.adapter.BaseRecyclerAdapter;
import com.micro.mysegmentdefault.entity.SearchDataEntity;
import com.micro.mysegmentdefault.middle.BaseRefreshFragment;
import com.micro.mysegmentdefault.middle.view.AbsAksTagView;
import com.micro.mysegmentdefault.middleimpl.adapter.MultipleSearchAdapter;
import com.micro.mysegmentdefault.middleimpl.mvp.model.SearchSubModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.SearchSubPresenter;
import com.micro.mysegmentdefault.view.widget.EmptyLayout;

/**
 * author : micro_hx <p>
 * desc : 搜素基础类 <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/29 - 14:55 <p>
 * interface :
 */

public class SearchSubFragment extends BaseRefreshFragment<SearchSubPresenter,SearchSubModel,SearchDataEntity.SearchItem> implements AbsAksTagView<SearchDataEntity.SearchItem> {

    //当前position
    private int mPosition ;

    public static String mKeyWords ;

    private MultipleSearchAdapter mMultipleSearchAdapter;

    @Override
    protected void initOnCreateMethod() {
        if(null != getArguments()) {
            mPosition = getArguments().getInt("position",0);
        }
    }

    /**
     * 没有关键字 不开始请求
     * @return
     */
    @Override
    protected boolean shouldStartRequest() {
        return !TextUtils.isEmpty(mKeyWords);
    }

    public void reLoadNewData(){
        //开始刷新
        mMultipleSearchAdapter.setKeyWords(mKeyWords);

        mEmptyLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
        mPresenter.getCommonListDatas(getCommonType(), getDefaultChannel(), PAGE_STEP);
    }


    @Override
    protected int getCommonType() {
        return mPosition;
    }

    @Override
    protected String getDefaultChannel() {
        return mKeyWords;
    }


    //TODO 设置divider
    @Override
    protected BaseRecyclerAdapter<SearchDataEntity.SearchItem> getRecyclerAdapter() {
        mMultipleSearchAdapter = new MultipleSearchAdapter(getActivity(),mKeyWords);
        return mMultipleSearchAdapter;
    }

    @Override
    protected void initPresenter() {
        if (null == mPresenter) return;
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void showUserNewTag(boolean result) {

    }
}
