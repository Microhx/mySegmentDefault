package com.micro.mysegmentdefault.middleimpl.fragment;

import android.view.View;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.module.BaseFragment;
import com.micro.mysegmentdefault.ui.setting.SettingActivity;
import com.micro.mysegmentdefault.ui.user.attention.UserAttentionActivity;
import com.micro.mysegmentdefault.ui.user.UserZoneActivity;
import com.micro.mysegmentdefault.ui.user.collection.UserCollectionActivity;
import com.micro.mysegmentdefault.ui.user.message.UserMessageActivity;
import com.micro.mysegmentdefault.ui.user.message.UserPrivateEventActivity;

import butterknife.OnClick;

/**
 * author : micro_hx <p>
 * desc : 个人栏目fragment<p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/6 - 12:24 <p>
 * interface :
 */

public class MineFragment extends BaseFragment {


    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_mine;
    }

    @OnClick(R.id.id_layout_userinfo)
    public void enterUserZone(View v) {
        //goWithActivity(UserEditInfoActivity.class);
        UserZoneActivity.start(getActivity(),"misaka_orange");
    }

    @OnClick(R.id.id_tv_message)
    public void enterUserMessage(View v) {
        goWithActivity(UserMessageActivity.class);
    }

    @OnClick(R.id.iv_tv_private_event)
    public void enterUserPrivateEvent(View v) {
        goWithActivity(UserPrivateEventActivity.class);
    }

    @OnClick(R.id.id_tv_attention)
    public void enterUserAttentionEvent(View v) {
        goWithActivity(UserAttentionActivity.class);
    }

    @OnClick(R.id.id_tv_collection)
    public void enterUserCollectionEvent(View v) { goWithActivity(UserCollectionActivity.class);}

    @OnClick(R.id.id_setting)
    public void enterUserSetting(View v) {goWithActivity(SettingActivity.class);}

}
