package com.micro.mysegmentdefault.middleimpl.subfragment;

import com.micro.mysegmentdefault.base.adapter.BaseRecyclerAdapter;
import com.micro.mysegmentdefault.entity.MessageEvent;
import com.micro.mysegmentdefault.entity.PageEntity;
import com.micro.mysegmentdefault.entity.TagDataEntity;
import com.micro.mysegmentdefault.middle.BaseRefreshFragment;
import com.micro.mysegmentdefault.middleimpl.adapter.UserTagRecyclerAdapter;
import com.micro.mysegmentdefault.middleimpl.mvp.model.UserTagModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.UserTagPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/8 - 17:05 <p>
 * interface :
 */

public class  UserTagFragment extends BaseRefreshFragment<UserTagPresenter, UserTagModel, TagDataEntity.Item>
                             implements UserTagRecyclerAdapter.OnTagSelectListener {

    private int mPosition;

    private UserTagRecyclerAdapter mTagRecyclerAdapter;

    @Override
    public void getCommonListDatas(int startPages, List<TagDataEntity.Item> mDataList, PageEntity entity) {
        super.getCommonListDatas(startPages, mDataList, entity);

        mBaseRecyclerAdapter.setState(BaseRecyclerAdapter.STATE_HIDE, true);
        mRefreshLayout.setCanLoadMore(false);
    }

    /**
     * 不存在刷新
     */
    @Override
    public void onRefreshing() {
        mRefreshLayout.onComplete();
    }

    @Override
    protected void initOnCreateMethod() {
        if (null != getArguments()) {
            mPosition = getArguments().getInt("position", 0);
        }
        EventBus.getDefault().register(this);
    }

    @Override
    protected BaseRecyclerAdapter getRecyclerAdapter() {
        mTagRecyclerAdapter = new UserTagRecyclerAdapter(getContext(), this, mPosition);
        return mTagRecyclerAdapter;
    }

    @Override
    public int getCommonType() {
        return mPosition;
    }

    @Override
    public void onSelect(TagDataEntity.Item item) {
        if (mPosition > 0) {
            mTagRecyclerAdapter.followTagName(item, true);
            EventBus.getDefault().post(getDefaultMessageEvent(item, 2));
        }
    }

    @Override
    public void onUnSelect(TagDataEntity.Item item,boolean fromSource) {
        if (mPosition == 0) {
            mTagRecyclerAdapter.removeTagName(item);
            if(!fromSource) {
                EventBus.getDefault().post(getDefaultMessageEvent(item, 1));
            }
        } else {
            mTagRecyclerAdapter.followTagName(item, false);
            if(fromSource) {
                EventBus.getDefault().post(getDefaultMessageEvent(item, 3));
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        //LogUtils.d(mPosition + "----" + "---event----" + event.type);
        if (mPosition == 0) {   //已关注页面
            if (event.type == 2 || event.type == 6) {
                event.item.isFollowed = true;
                mTagRecyclerAdapter.addItem(event.item);
                checkLayoutVisible();
            } else if (event.type == 3 || event.type == 7) {
                mTagRecyclerAdapter.removeTagName(event.item);
            }

        } else {                //热门标签页面
            if (event.type == 1 || event.type == 7) {
                mTagRecyclerAdapter.followTagName(event.item, false);
            }else if(event.type == 6) {
                mTagRecyclerAdapter.followTagName(event.item, true);
            }
        }
    }

    private MessageEvent getDefaultMessageEvent(TagDataEntity.Item item, int i) {
        MessageEvent event = new MessageEvent();
        event.item = item;
        event.type = i;
        return event;
    }


    public List<TagDataEntity.Item> getTagItems() {
        return mTagRecyclerAdapter.getItems() ;
    }
}
