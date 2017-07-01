package com.micro.mysegmentdefault.base.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.test.suitebuilder.annotation.Suppress;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.utils.LogUtils;
import com.micro.mysegmentdefault.view.recyclerview.ViewHolderHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * the base adapter for RecyclerView
 * Created by huanghaibin on 16-5-3.
 */
public abstract class BaseListRecyclerAdapter<T> extends BaseRecyclerAdapter<T> {

    private int mItemLayoutId ;

    public BaseListRecyclerAdapter(Context ctx , int itemLayoutId, int type) {
        super(ctx,type);
        this.mItemLayoutId = itemLayoutId;
    }

    public BaseListRecyclerAdapter(Context ctx,int itemLayoutId) {
        super(ctx);
        this.mItemLayoutId = itemLayoutId;

    }

    protected ViewHolderHelper onCreateDefaultViewHolder(ViewGroup parent, int type) {
        LogUtils.d("-------mContext------->>" + mContext + "---------->" + mItemLayoutId + "-----------" + type);

        ViewHolderHelper helper = ViewHolderHelper.get(mContext, null, parent, mItemLayoutId, -1);
        return helper;
    }

    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, T item, int position) {
        ViewHolderHelper helper = (ViewHolderHelper) holder;
        helper.updatePosition(position);

        //start the animation
        convertData(helper, getItem(position), position);
    }

    protected abstract void convertData(ViewHolderHelper holder, T item, int position);

}
