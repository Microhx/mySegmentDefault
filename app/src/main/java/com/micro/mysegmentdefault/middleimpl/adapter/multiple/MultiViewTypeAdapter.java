package com.micro.mysegmentdefault.middleimpl.adapter.multiple;

import android.content.Context;
import android.view.ViewGroup;

import com.micro.mysegmentdefault.base.adapter.BaseListRecyclerAdapter;
import com.micro.mysegmentdefault.utils.CommonUtils;
import com.micro.mysegmentdefault.view.recyclerview.ViewHolderHelper;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/29 - 16:02 <p>
 * interface :
 */

public abstract class MultiViewTypeAdapter<T> extends BaseListRecyclerAdapter<T> {

    private MultiViewSupport<T> mMultiViewSupport;

    public MultiViewTypeAdapter(Context ctx, MultiViewSupport<T> support) {
        super(ctx, -1);
        this.mMultiViewSupport = support;
        checkMultiViewType();
    }

    private void checkMultiViewType() {
        if (CommonUtils.objIsNull(mMultiViewSupport)) {
            throw new NullPointerException("the multiViewSupport must be not null!!");
        }
    }

    @Override
    public int getItemViewType(int position) {
        int type = super.getItemViewType(position);
        if(type == VIEW_TYPE_NORMAL && CommonUtils.objIsNotNull(mMultiViewSupport)) {
            return mMultiViewSupport.getItemViewType(position, getItem(position));
        }

        return super.getItemViewType(position);
    }

    @Override
    public ViewHolderHelper onCreateDefaultViewHolder(ViewGroup parent, int viewType) {
        if (CommonUtils.objIsNull(mMultiViewSupport) && viewType != VIEW_TYPE_NORMAL)
            return super.onCreateDefaultViewHolder(parent, viewType);

        int layoutId = mMultiViewSupport.getLayoutId(viewType);
        ViewHolderHelper helper = ViewHolderHelper.get(mContext, null, parent, layoutId, -1);

        return helper;
    }

}
