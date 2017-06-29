package com.micro.mysegmentdefault.middleimpl.adapter.multiple;

/**
 * Created by guoli on 2016/10/10.
 */
public interface MultiViewSupport<T> {
        int getLayoutId(int itemType) ;

        int getItemViewType(int position, T t) ;
}
