package com.micro.mysegmentdefault.middleimpl.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.SegmentApplication;
import com.micro.mysegmentdefault.base.adapter.BaseRecyclerAdapter;
import com.micro.mysegmentdefault.entity.NewsCommentDataEntity;
import com.micro.mysegmentdefault.ui.user.UserAddCommentActivity;
import com.micro.mysegmentdefault.utils.ImageUtils;
import com.micro.mysegmentdefault.view.widget.ReplyItemLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/27 - 10:57 <p>
 * interface :
 */

public class UserNewsCommentRecyclerAdapter extends BaseRecyclerAdapter<NewsCommentDataEntity.CommentItem> {

    public UserNewsCommentRecyclerAdapter(Context context) {
        super(context, ONLY_HEADER);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new NewsCommentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.news_comment_item_layout,parent,false));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, final NewsCommentDataEntity.CommentItem item, int position) {
        NewsCommentViewHolder commentHolder = (NewsCommentViewHolder) holder;

        //用户评论头像
        ImageUtils.showUrlImage(item.user.avatarUrl,commentHolder.mIvUserIcon);
        commentHolder.mTvUserName.setText(item.user.name);
        commentHolder.mTvPublishTime.setText(item.createdDate);
        commentHolder.mTvComment.setText(item.originalText);
        commentHolder.mTvLike.setText(item.votes);

        //用户回复
        commentHolder.mReplayComment.addDatas(item.repliedComments);

        //reply
        commentHolder.mIvReplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserAddCommentActivity.start(SegmentApplication.getApplication(),item.id,item.user.name);
            }
        });
    }


    class NewsCommentViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.id_iv_user_icon)
        ImageView mIvUserIcon;

        @Bind(R.id.id_tv_username)
        TextView mTvUserName;

        @Bind(R.id.id_tv_publish_time)
        TextView mTvPublishTime;

        @Bind(R.id.id_tv_comment)
        TextView  mTvComment ;

        @Bind(R.id.id_tv_like)
        TextView mTvLike;

        @Bind(R.id.id_iv_reply)
        ImageView mIvReplay;

        @Bind(R.id.id_layout_comment_reply)
        ReplyItemLayout mReplayComment;

        public NewsCommentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
