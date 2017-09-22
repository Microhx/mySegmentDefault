package com.micro.mysegmentdefault.middleimpl.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.adapter.BaseRecyclerAdapter;
import com.micro.mysegmentdefault.entity.TopUserEntity;
import com.micro.mysegmentdefault.ui.user.UserZoneActivity;
import com.micro.mysegmentdefault.utils.ImageUtils;
import com.micro.mysegmentdefault.utils.LogUtils;
import com.micro.mysegmentdefault.view.widget.CircleImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/18 - 23:07 <p>
 * interface :
 */

public class UserTopRecyclerAdapter extends BaseRecyclerAdapter<TopUserEntity.UserItem> {

    public UserTopRecyclerAdapter(Context ctx) {
        super(ctx,NEITHER);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new UserTopViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.top_user_list_item_layout,parent,false));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, final TopUserEntity.UserItem item, int position) {
        UserTopViewHolder userHolder = (UserTopViewHolder) holder;
        userHolder.mTvUserName.setText(item.name);
        userHolder.mTvUserScore.setText("+"+item.incr);
        ImageUtils.showUrlImage(item.avatarUrl,userHolder.mUserIcon);

        if(position <= 2 ) {
            userHolder.mRankTv.setVisibility(View.GONE);
            userHolder.mRankImageView.setVisibility(View.VISIBLE);

            if(position == 0) {
                userHolder.mRankImageView.setImageResource(R.drawable.ic_rank_1);
            }else if(position == 1) {
                userHolder.mRankImageView.setImageResource(R.drawable.ic_rank_2);
            }else {
                userHolder.mRankImageView.setImageResource(R.drawable.ic_rank_3);
            }
        }else {
            userHolder.mRankTv.setVisibility(View.VISIBLE);
            userHolder.mRankImageView.setVisibility(View.GONE);
            userHolder.mRankTv.setText(String.valueOf(1+position));
        }

        userHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(item.url) && item.url.length() > 3) {
                    UserZoneActivity.start(mContext,item.url.substring(3));
                }
            }
        });
    }

    class UserTopViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.id_iv_rank)
        ImageView mRankImageView ;

        @Bind(R.id.id_tv_rank)
        TextView mRankTv ;

        @Bind(R.id.id_iv_icon)
        CircleImageView mUserIcon;

        @Bind(R.id.id_iv_username)
        TextView mTvUserName ;

        @Bind(R.id.id_iv_score)
        TextView mTvUserScore ;

        public UserTopViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
