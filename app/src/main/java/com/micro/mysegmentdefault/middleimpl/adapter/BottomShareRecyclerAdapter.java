package com.micro.mysegmentdefault.middleimpl.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.SegmentApplication;
import com.micro.mysegmentdefault.base.adapter.BaseRecyclerAdapter;
import com.micro.mysegmentdefault.entity.ShareItemEntity;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/22 - 22:59 <p>
 * interface :
 */

public class BottomShareRecyclerAdapter extends BaseRecyclerAdapter<ShareItemEntity> {

    public BottomShareRecyclerAdapter(Context ctx) {
        super(ctx);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new BottomShareViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.share_item_layout,parent,false));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, final ShareItemEntity item, int position) {
        BottomShareViewHolder bottomHolder = (BottomShareViewHolder) holder;
        bottomHolder.mTvTitle.setText(item.mTitle);
        bottomHolder.mIvImage.setImageDrawable(item.mDrawable);
        bottomHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                SegmentApplication.getApplication().startActivity(item.mIntent);
            }
        });
    }

    public class BottomShareViewHolder extends RecyclerView.ViewHolder {
        ImageView mIvImage ;
        TextView mTvTitle ;

        public BottomShareViewHolder(View itemView) {
            super(itemView);
            mTvTitle = (TextView) itemView.findViewById(R.id.id_tv_title);
            mIvImage = (ImageView) itemView.findViewById(R.id.id_iv_image);
        }
    }



}
