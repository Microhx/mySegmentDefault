package com.micro.mysegmentdefault.middleimpl.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.adapter.BaseRecyclerAdapter;
import com.micro.mysegmentdefault.entity.TagDataEntity;
import com.micro.mysegmentdefault.utils.LogUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/8 - 17:41 <p>
 * interface :
 */

public class UserTagRecyclerAdapter extends BaseRecyclerAdapter<TagDataEntity.Item> {

    private OnTagSelectListener mSelectListener;

    private int mPosition;

    public UserTagRecyclerAdapter(Context context, OnTagSelectListener mSelectListener,int position) {
        super(context,NEITHER);
        this.mSelectListener = mSelectListener;
        this.mPosition = position;
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new UserTagViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_tag_item_layout, parent, false));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, final TagDataEntity.Item item, final int position) {
        UserTagViewHolder tagHolder = (UserTagViewHolder) holder;
        tagHolder.mCheckBox.setChecked(item.isFollowed);
        tagHolder.mTvContent.setText(item.name);

        tagHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mSelectListener) {
                    if(mPosition == 0) {
                        mSelectListener.onUnSelect(item,false);
                    }else {
                        if (item.isFollowed) {
                            mSelectListener.onUnSelect(item,true);
                        } else {
                            mSelectListener.onSelect(item);
                        }
                    }
                }
            }
        });
    }

    public void removeTagName(TagDataEntity.Item item) {
        int position = -1;
        for (int i = 0; i < getCount(); i++) {
            if (item.id.equals(mItems.get(i).id)) {
                position = i;
                mItems.remove(position);
                break;
            }
        }

        if(position >= 0) {
            notifyItemRemoved(position);
        }
    }

    public void followTagName(TagDataEntity.Item item, boolean isCheck) {
        int position = -1;
        for (int i = 0; i < getCount(); i++) {
            if (item.id.equals(mItems.get(i).id)) {
                position = i;
                mItems.get(position).isFollowed = isCheck;
                break;
            }
        }

        if(position >= 0) {
            notifyItemChanged(position);
        }
    }


    class UserTagViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.id_check_box)
        CheckBox mCheckBox;

        @Bind(R.id.id_tv_content)
        TextView mTvContent;

        public UserTagViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

    }


    public interface OnTagSelectListener {
        /**
         * 选中的某标签
         *
         * @param item
         */
        void onSelect(TagDataEntity.Item item);

        /**
         * 未选中的标签
         *
         * @param item
         * @param fromSource  是否来自热门标签 需要区分到底是哪个发出的确定
         */
        void onUnSelect(TagDataEntity.Item item, boolean fromSource);


    }

}
