package com.micro.mysegmentdefault.middleimpl.subfragment;

import com.micro.mysegmentdefault.base.adapter.BaseRecyclerAdapter;
import com.micro.mysegmentdefault.entity.TopUserEntity;
import com.micro.mysegmentdefault.middle.BaseRefreshFragment;
import com.micro.mysegmentdefault.middleimpl.adapter.UserTopRecyclerAdapter;
import com.micro.mysegmentdefault.utils.LogUtils;

import java.util.List;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/16 - 22:43 <p>
 * interface :
 */

public class UserTopListFragment extends BaseRefreshFragment {

    private int mPosition ;


    @Override
    protected void initOnCreateMethod() {
        super.initOnCreateMethod();
        mPosition = getArguments().getInt("position",0);
    }

    public int getCurrentPosition() {
        return mPosition;
    }

    @Override
    protected BaseRecyclerAdapter getRecyclerAdapter() {
        return new UserTopRecyclerAdapter(getActivity());
    }


    public void loadAllDatas(List<TopUserEntity.UserItem> datas) {
        mEmptyLayout.dismiss();

        mBaseRecyclerAdapter.addAll(datas);
        //加载完成
        mBaseRecyclerAdapter.setState(BaseRecyclerAdapter.STATE_HIDE, true);
        mRefreshLayout.onComplete();
        mRefreshLayout.setCanLoadMore(false);
    }

}
