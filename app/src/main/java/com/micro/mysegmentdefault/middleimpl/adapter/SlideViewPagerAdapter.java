package com.micro.mysegmentdefault.middleimpl.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.entity.DiscoverDataEntity;
import com.micro.mysegmentdefault.utils.CommonUtils;
import com.micro.mysegmentdefault.utils.ImageUtils;
import com.micro.mysegmentdefault.utils.LogUtils;

import java.util.List;

/**
 * author : micro_hx <p>
 * desc : 轮播图 adapter<p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/10 - 22:55 <p>
 * interface :
 */

public class SlideViewPagerAdapter extends PagerAdapter {

    private List<DiscoverDataEntity.DiscoverItem> mListItems;
    private Context mContext;
    private onImageViewClickListener mViewClickListener ;

    public SlideViewPagerAdapter(Context mContext, List<DiscoverDataEntity.DiscoverItem> mListItems , onImageViewClickListener listener) {
        this.mContext = mContext;
        this.mListItems = mListItems;
        this.mViewClickListener = listener;
    }

    @Override
    public int getCount() {
        return CommonUtils.collectionIsNull(mListItems) ? 0 : mListItems.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView iv = new ImageView(mContext);

        final DiscoverDataEntity.DiscoverItem item = mListItems.get(position % mListItems.size());
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageUtils.showUrlImageFixXY(item.bannerUrl, iv);
        LogUtils.d("bannerUrl:" + item.bannerUrl);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != mViewClickListener) {
                    mViewClickListener.onClick(item);
                }
            }
        });
        
        container.addView(iv,getDefaultLayoutParams());

        return iv;
    }


    private ViewGroup.LayoutParams mLayoutParams;
    private ViewGroup.LayoutParams getDefaultLayoutParams() {
        if(null == mLayoutParams) {
            mLayoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
        return mLayoutParams;
    }

    public void updateData(List<DiscoverDataEntity.DiscoverItem> listItems) {
        mListItems.clear();
        mListItems.addAll(listItems);
        notifyDataSetChanged();
    }


    public interface onImageViewClickListener{
        void onClick( DiscoverDataEntity.DiscoverItem item) ;
    }


}
