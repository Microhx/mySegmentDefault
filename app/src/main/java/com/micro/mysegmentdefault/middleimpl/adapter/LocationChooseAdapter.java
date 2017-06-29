package com.micro.mysegmentdefault.middleimpl.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.adapter.BaseRecyclerAdapter;
import com.micro.mysegmentdefault.entity.LocationDataEntity;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/2 - 18:04 <p>
 * interface :
 */

public class LocationChooseAdapter extends BaseRecyclerAdapter<LocationDataEntity.Item> {

    private onItemSelectListener mOnItemSelelctListener ;

    public void setOnItemSelelctListener(onItemSelectListener listener){
        this.mOnItemSelelctListener = listener;
    }


    public LocationChooseAdapter(Context ctx) {
        super(ctx);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new LocationViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.location_item_layout,parent,false));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, final LocationDataEntity.Item item, int position) {
        LocationViewHolder locationHolder = (LocationViewHolder) holder;
        locationHolder.mTVContent.setText(item.name);
        locationHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != mOnItemSelelctListener) {
                    mOnItemSelelctListener.onItemSelect(item.name,item.id);
                }
            }
        });
    }

    class LocationViewHolder extends RecyclerView.ViewHolder {

        TextView mTVContent;

        public LocationViewHolder(View itemView) {
            super(itemView);
            mTVContent = (TextView) itemView.findViewById(R.id.id_tv_location);
        }
    }

    public interface onItemSelectListener {
        void onItemSelect(String name , String cityId);
    }


}
