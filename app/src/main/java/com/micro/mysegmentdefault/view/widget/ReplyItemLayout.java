package com.micro.mysegmentdefault.view.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.entity.NewsCommentDataEntity;
import com.micro.mysegmentdefault.utils.CommonUtils;

import java.util.List;

/**
 * author : micro_hx <p>
 * desc : 用于用户回复item <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/28 - 16:39 <p>
 * interface :
 */

public class ReplyItemLayout extends LinearLayout {

    public ReplyItemLayout(Context context) {
        this(context,null);
    }

    public ReplyItemLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ReplyItemLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void addDatas(List<NewsCommentDataEntity.RepliedItem> mItems) {
        removeAllViews();
        if(CommonUtils.collectionIsNull(mItems)) {
            setVisibility(GONE);
            return;
        }

        int len = mItems.size();
        for(int i =0 ; i < len ; i++) {
            NewsCommentDataEntity.RepliedItem item = mItems.get(i);
            View rootView = addReplyItems(item);
            addView(rootView);
            if(i < len - 1) {
                addDecorationItem();
            }
        }
    }

    @NonNull
    private View addReplyItems(NewsCommentDataEntity.RepliedItem item) {
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.reply_item_layout,this,false);
        TextView tvName = (TextView) rootView.findViewById(R.id.id_tv_username);
        TextView tvPublishTime = (TextView) rootView.findViewById(R.id.id_tv_publish_time);
        TextView tvComment = (TextView) rootView.findViewById(R.id.id_tv_comment);
        TextView tvLike = (TextView) rootView.findViewById(R.id.id_tv_like);
        ImageView ivReply = (ImageView) rootView.findViewById(R.id.id_iv_reply);

        tvName.setText(item.user.name);
        tvPublishTime.setText(item.createdDate);
        tvComment.setText(item.originalText);
        tvLike.setText(item.votes);
        return rootView;
    }

    private void addDecorationItem() {
        View view = new View(getContext());
        view.setBackgroundResource(R.color.text_second_color);
        addView(view,getDefaultLayoutParams());
    }

    private LinearLayout.LayoutParams mLayoutParams;
    private LinearLayout.LayoutParams getDefaultLayoutParams() {
        if(null == mLayoutParams) {
            mLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,1);
        }
        return mLayoutParams;
    }


}
