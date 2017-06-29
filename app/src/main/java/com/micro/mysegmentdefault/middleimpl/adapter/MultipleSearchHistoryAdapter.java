package com.micro.mysegmentdefault.middleimpl.adapter;

import android.content.Context;
import android.view.View;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.adapter.BaseListRecyclerAdapter;
import com.micro.mysegmentdefault.view.recyclerview.ViewHolderHelper;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/29 - 11:45 <p>
 * interface :
 */

public class MultipleSearchHistoryAdapter extends BaseListRecyclerAdapter<String> {

    private OnItemSelectListener mListener;

    public MultipleSearchHistoryAdapter(Context ctx, int itemLayoutId, int type) {
        super(ctx, itemLayoutId, type);
    }

    @Override
    protected void convertData(ViewHolderHelper holder, final String item, int position) {
        holder.setTextView(R.id.id_tv_history_title,item);
        holder.setItemViewOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != mListener) mListener.onItemSelect(item);
            }
        }) ;
    }

    public void setOnItemSelectListener(OnItemSelectListener listener) {
        this.mListener = listener;
    }

    public interface OnItemSelectListener{
        void onItemSelect(String item);
    }

}
