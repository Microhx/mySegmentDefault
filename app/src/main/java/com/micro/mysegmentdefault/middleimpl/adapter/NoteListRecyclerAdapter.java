package com.micro.mysegmentdefault.middleimpl.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.adapter.BaseRecyclerAdapter;
import com.micro.mysegmentdefault.entity.NoteDataEntity;
import com.micro.mysegmentdefault.ui.NoteDetailActivity;
import com.micro.mysegmentdefault.ui.comment.CommonWebActivity;
import com.micro.mysegmentdefault.utils.ImageUtils;
import com.micro.mysegmentdefault.view.widget.CircleImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/16 - 17:06 <p>
 * interface :
 */

public class NoteListRecyclerAdapter extends BaseRecyclerAdapter<NoteDataEntity.Item> {

    public NoteListRecyclerAdapter(Context ctx) {
        super(ctx);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new NoteListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item_layout, parent, false));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, final NoteDataEntity.Item item, int position) {
        NoteListViewHolder noteHolder = (NoteListViewHolder) holder;
        noteHolder.mQuesTitle.setText(item.title);
        noteHolder.mQuesContent.setText(item.originalAbstract);
        noteHolder.mQuesTime.setText(item.createdDate);

        if(null == item.user) {
            noteHolder.mUserHead.setVisibility(View.GONE);
            noteHolder.mUserName.setVisibility(View.GONE);
            noteHolder.mUserComment.setText(item.bookmarks + "人收藏");
        }else {
            noteHolder.mUserHead.setVisibility(View.VISIBLE);
            noteHolder.mUserName.setVisibility(View.VISIBLE);

            ImageUtils.showUrlImage(item.user.avatarUrl, noteHolder.mUserHead);
            noteHolder.mUserName.setText(item.user.name);
            noteHolder.mUserComment.setText(item.forks + "人建分支、" + item.bookmarks + "人收藏");
        }

        noteHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonWebActivity.start(item.id, NoteDetailActivity.class);
            }
        });

    }

    class NoteListViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.id_iv_user_icon)
        CircleImageView mUserHead;

        @Bind(R.id.id_tv_username)
        TextView mUserName;

        @Bind(R.id.id_tv_qus_title)
        TextView mQuesTitle;

        @Bind(R.id.id_tv_qus_content)
        TextView mQuesContent;

        @Bind(R.id.id_tv_qus_comment)
        TextView mUserComment;

        @Bind(R.id.id_tv_qus_time)
        TextView mQuesTime;

        public NoteListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
