package com.micro.mysegmentdefault.middleimpl.adapter.listbase;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.entity.SearchDataEntity;
import com.micro.mysegmentdefault.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/7/1 - 11:44 <p>
 * interface :
 */

public class AskTagAdapter extends BaseAdapter {

    private List<SearchDataEntity.SearchItem> mDatas = new ArrayList<>();

    private String mKeyWords ;

    private boolean mDataIsNull = false ;

    @Override
    public int getCount() {
        return CommonUtils.collectionIsNull(mDatas) ? 0 : mDatas.size();
    }

    @Override
    public SearchDataEntity.SearchItem getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.item_choose_new_tag, null);
            viewHolder = new ViewHolder();
            ButterKnife.bind(viewHolder, convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.mIVLeftImage.setVisibility(mDataIsNull ? View.GONE : View.VISIBLE);
        viewHolder.mTvCreate.setVisibility(mDataIsNull ? View.VISIBLE : View.GONE);
        if(mDataIsNull) {
            viewHolder.mTvTag.setText(mKeyWords);
        }else {
            viewHolder.mTvTag.setText(getItem(position).name);
        }

        return convertView;
    }

    public void clear() {
        mDatas.clear();
        notifyDataSetChanged();
    }

    public String getTagName(){
        return mKeyWords;
    }


    public void setDatas(List<SearchDataEntity.SearchItem> mDatas, boolean dataIsNull) {
        this.mDatas = mDatas;
        this.mDataIsNull = dataIsNull;
        notifyDataSetChanged();
    }

    public void setSingleData(String mSearchKeyWords, boolean dataIsNull) {
        this.mKeyWords = mSearchKeyWords;
        this.mDataIsNull = dataIsNull;

        this.mDatas.clear();
        this.mDatas.add(new SearchDataEntity.SearchItem());
        notifyDataSetChanged();
    }

    public class ViewHolder {

        @Bind(R.id.id_tv_tag)
        TextView mTvTag;

        @Bind(R.id.id_create_tag)
        TextView mTvCreate;

        @Bind(R.id.id_iv_image)
        ImageView mIVLeftImage;

        public ViewHolder() {}

    }


}

