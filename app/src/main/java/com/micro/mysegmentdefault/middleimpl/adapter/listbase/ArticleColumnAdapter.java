package com.micro.mysegmentdefault.middleimpl.adapter.listbase;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.entity.UserBlogDataEntity;
import com.micro.mysegmentdefault.utils.CommonUtils;

import java.util.List;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/7/6 - 15:12 <p>
 * interface :
 */

public class ArticleColumnAdapter extends BaseAdapter {

    private List<UserBlogDataEntity.BlogItem> mBlogItemList;

    public ArticleColumnAdapter(List<UserBlogDataEntity.BlogItem> mBlogItemList) {
        this.mBlogItemList = mBlogItemList;
    }

    @Override
    public int getCount() {
        return CommonUtils.collectionIsNull(mBlogItemList) ? 1 : mBlogItemList.size() + 1;
    }

    @Override
    public UserBlogDataEntity.BlogItem getItem(int position) {
        return mBlogItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private boolean isLastOne(int position){
        return position == getCount()-1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder ;
        if(null == convertView) {
            convertView = View.inflate(parent.getContext(), R.layout.item_article_add_column,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

            if(isLastOne(position)){
                viewHolder.mIvImage.setVisibility(View.VISIBLE);
                viewHolder.mIvImage.setImageResource(R.drawable.ic_add_blog);
                viewHolder.mTvTitle.setTextColor(parent.getResources().getColor(R.color.app_theme_color));
                viewHolder.mTvTitle.setText(R.string.str_create_new_column);
            }else {
                viewHolder.mTvTitle.setTextColor(parent.getResources().getColor(R.color.text_first_color));
                viewHolder.mTvTitle.setText(getItem(position).name);
                viewHolder.mIvImage.setVisibility(View.GONE);
            }

        return convertView;
    }

    public class ViewHolder {
        public TextView mTvTitle;
        public ImageView mIvImage ;

        public ViewHolder(View rootView) {
            mTvTitle = (TextView) rootView.findViewById(R.id.id_tv_column);
            mIvImage = (ImageView) rootView.findViewById(R.id.id_iv_add);
        }
    }

}
