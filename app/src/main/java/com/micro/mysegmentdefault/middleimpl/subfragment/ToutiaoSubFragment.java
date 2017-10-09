package com.micro.mysegmentdefault.middleimpl.subfragment;

import com.micro.mysegmentdefault.base.adapter.BaseRecyclerAdapter;
import com.micro.mysegmentdefault.entity.NewToutiaoListData;
import com.micro.mysegmentdefault.middle.BaseRefreshFragment;
import com.micro.mysegmentdefault.middleimpl.adapter.HomeRecyclerAdapter;
import com.micro.mysegmentdefault.middleimpl.mvp.model.HomeModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.ToutiaoPresenter;
import com.micro.mysegmentdefault.view.widget.EmptyLayout;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/6 - 16:44 <p>
 * interface :
 */

public class ToutiaoSubFragment extends BaseRefreshFragment<ToutiaoPresenter, HomeModel, NewToutiaoListData> {

    private String mChannel = "";

    //最新的是 newest
    //最热的是 rank
    public static int MSORTVALUE = 0;

    @Override
    protected void initOnCreateMethod() {
        if (getArguments() != null) {
            mChannel = getArguments().getString("title");
        }
    }

    @Override
    protected BaseRecyclerAdapter getRecyclerAdapter() {
        return new HomeRecyclerAdapter(getActivity());
    }

    @Override
    protected String getDefaultChannel() {
        return MSORTVALUE + "___" + mChannel;
    }

    //重新加载数据
    public void reloadData() {
        if(null == mEmptyLayout || null == mPresenter) return;

        mEmptyLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
        mPresenter.getCommonListDatas(getCommonType(), getDefaultChannel(), PAGE_STEP);
    }

}
