package com.micro.mysegmentdefault.ui.user.attention;

import android.graphics.Color;

import com.micro.mysegmentdefault.base.adapter.BaseListRecyclerAdapter;
import com.micro.mysegmentdefault.base.adapter.BaseRecyclerAdapter;
import com.micro.mysegmentdefault.base.data.BaseDataInterface;
import com.micro.mysegmentdefault.base.mvp.model.BaseRefreshModel;
import com.micro.mysegmentdefault.base.mvp.presenter.BaseRefreshPresenter;
import com.micro.mysegmentdefault.middle.BaseRefreshActivity;
import com.micro.mysegmentdefault.view.recyclerview.ViewHolderHelper;

/**
 * author : micro_hx <p>
 * desc : BaseAttention Presenter的抽象<p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/14 - 18:42 <p>
 * interface :
 */

public abstract class AbBaseAttentionActivity<T extends BaseRefreshPresenter,
                                              M extends BaseRefreshModel,
                                              D extends BaseDataInterface>
            extends BaseRefreshActivity<T,M,D> {


    @Override
    protected BaseRecyclerAdapter<D> getRecyclerAdapter() {
        if(recycleNeedHeaderView()) {
            BaseRecyclerAdapter<D> thatAdapter =  new BaseListRecyclerAdapter<D>(this,getItemLayoutId(),BaseListRecyclerAdapter.BOTH_HEADER_FOOTER) {
                @Override
                protected void convertData(ViewHolderHelper holder, D item, int position) {
                    AbBaseAttentionActivity.this.convertData(holder,item,position);
                }
            };
            thatAdapter.setOnLoadingHeaderCallBack(getOnLoadingHeader());
            return thatAdapter;
        }

        return new BaseListRecyclerAdapter<D>(this,getItemLayoutId()) {
            @Override
            protected void convertData(ViewHolderHelper holder, D item, int position) {
                AbBaseAttentionActivity.this.convertData(holder,item,position);
            }

        };
    }

    protected abstract int getItemLayoutId();

    protected abstract void convertData(ViewHolderHelper holder, D item, int position);

    /**
     * 是否需要headerView
     * @return
     */
    protected boolean recycleNeedHeaderView() {
        return false;
    }

    /**
     * 需要的头部回调
     * @return
     */
    protected  BaseRecyclerAdapter.OnLoadingHeaderCallBack getOnLoadingHeader() {
        return null ;
    }


    public static final int GREEN_BACKGROUND_COLOR = Color.parseColor("#EFF9F6");
    public static final int GRAY_BACKGROUND_COLOR = Color.parseColor("#EFEEF0");

    public static final int GREEN_TEXT_COLOR = Color.parseColor("#0C9F69");
    public static final int GRAY_TEXT_COLOR = Color.parseColor("#9D9C9D");

}
