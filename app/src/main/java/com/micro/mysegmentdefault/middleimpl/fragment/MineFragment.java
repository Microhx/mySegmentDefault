package com.micro.mysegmentdefault.middleimpl.fragment;

import android.view.View;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.module.BaseFragment;
import com.micro.mysegmentdefault.entity.MessageEvent;
import com.micro.mysegmentdefault.logic.UserLogic;
import com.micro.mysegmentdefault.ui.setting.SettingActivity;
import com.micro.mysegmentdefault.ui.user.attention.UserAttentionActivity;
import com.micro.mysegmentdefault.ui.user.UserZoneActivity;
import com.micro.mysegmentdefault.ui.user.collection.UserCollectionActivity;
import com.micro.mysegmentdefault.ui.user.message.UserMessageActivity;
import com.micro.mysegmentdefault.ui.user.message.UserPrivateEventActivity;
import com.micro.mysegmentdefault.utils.ImageUtils;
import com.micro.mysegmentdefault.view.widget.CircleImageView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * author : micro_hx <p>
 * desc : 个人栏目fragment<p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/6 - 12:24 <p>
 * interface :
 */

public class MineFragment extends BaseFragment {

    @Bind(R.id.id_iv_user_icon)
    CircleImageView mUserIcon;

    @Bind(R.id.id_tv_user_name)
    TextView mTvUserName;

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initViews() {
        initUserSetting();
        EventBus.getDefault().register(this);
    }

    @OnClick(R.id.id_layout_userinfo)
    public void enterUserZone(View v) {
        if (checkUserLogin()) {
            UserZoneActivity.start(getActivity(), UserLogic.getUserSlug());
        }
    }

    @OnClick(R.id.id_tv_message)
    public void enterUserMessage(View v) {
        if (checkUserLogin()) {
            goWithActivity(UserMessageActivity.class);
        }
    }

    @OnClick(R.id.iv_tv_private_event)
    public void enterUserPrivateEvent(View v) {
        if (checkUserLogin()) {
            goWithActivity(UserPrivateEventActivity.class);
        }
    }

    @OnClick(R.id.id_tv_attention)
    public void enterUserAttentionEvent(View v) {
        if(checkUserLogin()){
            goWithActivity(UserAttentionActivity.class);
        }
    }

    @OnClick(R.id.id_tv_collection)
    public void enterUserCollectionEvent(View v) {
        if(checkUserLogin()) {
            goWithActivity(UserCollectionActivity.class);
        }
    }

    @OnClick(R.id.id_setting)
    public void enterUserSetting(View v) {
        if(checkUserLogin()) {
            goWithActivity(SettingActivity.class);
        }
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        if(null != event && event.type == 4) {
            initUserSetting();
        }
    }

    private void initUserSetting() {
        if (UserLogic.checkUserLogin()) {
            ImageUtils.showUrlImage(UserLogic.getUserPhoto(), mUserIcon);
            mTvUserName.setText(UserLogic.getUserName());
        } else {
            mUserIcon.setImageResource(R.drawable.ic_tab_mine);
            mTvUserName.setText(R.string.str_login_register);
        }
    }

}
