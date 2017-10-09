package com.micro.mysegmentdefault.middleimpl.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.adapter.BaseRecyclerAdapter;
import com.micro.mysegmentdefault.entity.TagDetailUserEntity;
import com.micro.mysegmentdefault.ui.user.UserZoneActivity;
import com.micro.mysegmentdefault.utils.ImageUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/9 - 18:06 <p>
 * interface :
 */

@Deprecated
public class TagDetailUserAdapter extends BaseRecyclerAdapter<TagDetailUserEntity.Item> {

    public TagDetailUserAdapter(Context ctx) {
        super(ctx);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new UserViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.tag_user_item_layout, parent, false));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder,  final TagDetailUserEntity.Item item, int position) {
        UserViewHolder userHolder = (UserViewHolder) holder;
        userHolder.mTvTitle.setText(item.name);

        ImageUtils.showUserCircleImageUrl(item.avatarUrl, userHolder.mUserIcon);
        userHolder.mTvScore.setText("+" + item.incr);

        if(position == 0) {
            userHolder.mTvRank.setTextColor(Color.parseColor("#EAD97F"));
        }else if(position == 2) {
            userHolder.mTvRank.setTextColor(Color.parseColor("#D09848"));
        }else {
            userHolder.mTvRank.setTextColor(Color.parseColor("#A8A7A8"));
        }

        userHolder.mTvRank.setText("#" + (1+position));

        userHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserZoneActivity.start(mContext,item.slug);
            }
        });
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.id_tv_id)
        TextView mTvRank;

        @Bind(R.id.id_tv_username)
        TextView mTvTitle;

        @Bind(R.id.id_iv_user_icon)
        ImageView mUserIcon;

        @Bind(R.id.id_tv_score)
        TextView mTvScore;

        public UserViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }

}
