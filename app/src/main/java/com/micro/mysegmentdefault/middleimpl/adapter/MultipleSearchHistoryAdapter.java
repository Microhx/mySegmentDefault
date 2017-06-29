package com.micro.mysegmentdefault.middleimpl.adapter;

import android.content.Context;

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

    public MultipleSearchHistoryAdapter(Context ctx, int itemLayoutId, int type) {
        super(ctx, itemLayoutId, type);
    }

    @Override
    protected void convertData(ViewHolderHelper holder, String item, int position) {
        holder.setTextView(R.id.id_tv_history_title,item);
    }
}
