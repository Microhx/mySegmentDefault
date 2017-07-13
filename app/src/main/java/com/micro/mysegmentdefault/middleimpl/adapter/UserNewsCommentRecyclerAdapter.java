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
import com.micro.mysegmentdefault.utils.CLICK_TYPE;
import com.micro.mysegmentdefault.utils.ImageUtils;
import com.micro.mysegmentdefault.view.widget.ReplyItemLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author : micro_hx <p>
 * desc : 用户评论的adapter <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/27 - 10:57 <p>
 * interface :
 */

public class UserNewsCommentRecyclerAdapter extends BaseRecyclerAdapter<NewsCommentDataEntity.CommentItem> {

    private onItemViewClickLister mItemViewClickListener;


    public UserNewsCommentRecyclerAdapter(Context context) {
        super(context, ONLY_HEADER);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new NewsCommentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.news_comment_item_layout, parent, false));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, final NewsCommentDataEntity.CommentItem item, int position) {
        NewsCommentViewHolder commentHolder = (NewsCommentViewHolder) holder;
        //因为存在头部 所以需要减1
        initDataSetting(item, commentHolder, position - 1);

        //因为存在头部 所以需要减1
        initDataListener(item, commentHolder, position - 1);
    }

    //初始化点击方法
    private void initDataListener(final NewsCommentDataEntity.CommentItem item, NewsCommentViewHolder holder, final int position) {

        //用户头像
        holder.mIvUserIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mItemViewClickListener)
                    mItemViewClickListener.onClick(CLICK_TYPE.USER_CENTER, position, 0, item);
            }
        });

        //用户姓名
        holder.mTvUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mItemViewClickListener)
                    mItemViewClickListener.onClick(CLICK_TYPE.USER_CENTER, position, 0, item);
            }
        });

        //用户点赞
        holder.mTvLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mItemViewClickListener)
                    mItemViewClickListener.onClick(CLICK_TYPE.ZAN, position, 0, item);
            }
        });

        //用户评论
        holder.mIvReplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mItemViewClickListener)
                    mItemViewClickListener.onClick(CLICK_TYPE.REPLY, position, 0, item);
            }
        });

    }

    private void initDataSetting(NewsCommentDataEntity.CommentItem item, NewsCommentViewHolder commentHolder, int position) {
        //用户评论头像
        ImageUtils.showUrlImage(item.user.avatarUrl, commentHolder.mIvUserIcon);
        commentHolder.mTvUserName.setText(item.user.name);
        commentHolder.mTvPublishTime.setText(item.createdDate);
        commentHolder.mTvComment.setText(item.originalText);
        commentHolder.mTvLike.setText(item.votes);

        //用户回复
        commentHolder.mReplayComment.addDatas(position, mItemViewClickListener, item);
    }


    public void setItemViewClickListener(onItemViewClickLister mItemViewClickListener) {
        this.mItemViewClickListener = mItemViewClickListener;
    }


    class NewsCommentViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.id_iv_user_icon)
        ImageView mIvUserIcon;

        @Bind(R.id.id_tv_username)
        TextView mTvUserName;

        @Bind(R.id.id_tv_publish_time)
        TextView mTvPublishTime;

        @Bind(R.id.id_tv_comment)
        TextView mTvComment;

        @Bind(R.id.id_tv_like)
        TextView mTvLike;

        @Bind(R.id.id_iv_reply)
        ImageView mIvReplay;

        @Bind(R.id.id_layout_comment_reply)
        ReplyItemLayout mReplayComment;

        public NewsCommentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * 针对每个View上的多个点击事件
     */
    public interface onItemViewClickLister {

        //type 类型值
        //type 1 进入用户中心
        //type 2 点赞
        //type 3 回复

        //position  当前Item所在的位置 也包含数据所在的位置

        //subPosition  回复列表 所在位置

        //当前item值

        void onClick(CLICK_TYPE type, int position, int subPosition, NewsCommentDataEntity.CommentItem item);
    }


}
