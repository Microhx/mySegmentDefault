package com.micro.mysegmentdefault.middleimpl.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.adapter.BaseRecyclerAdapter;
import com.micro.mysegmentdefault.entity.ChannelDataEntity;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/28 - 18:52 <p>
 * interface :
 */

public class ChannelChooseAdapter extends BaseRecyclerAdapter<ChannelDataEntity.Item> {

    private onItemChooseListener mItemListener ;


    public ChannelChooseAdapter(Context ctx, onItemChooseListener mItemListener) {
        super(ctx);
        this.mItemListener = mItemListener;
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new ChannelViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_channel_layout,parent,false));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, final ChannelDataEntity.Item item, int position) {
        ChannelViewHolder channelHolder = (ChannelViewHolder) holder;

        channelHolder.mTv.setText(item.channelName);
        channelHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != mItemListener) mItemListener.onChoose(item.channelName, item.channelCode);
            }
        });
    }

    class ChannelViewHolder extends RecyclerView.ViewHolder {

        TextView mTv ;

        public ChannelViewHolder(View itemView) {
            super(itemView);
            mTv = (TextView) itemView.findViewById(R.id.id_tv_title);

        }
    }

    public interface onItemChooseListener {
        void onChoose(String item,String code);
    }
}
