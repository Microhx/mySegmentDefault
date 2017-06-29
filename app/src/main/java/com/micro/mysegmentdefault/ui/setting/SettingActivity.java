package com.micro.mysegmentdefault.ui.setting;

import android.view.View;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.module.BaseActivity;

import butterknife.OnClick;

/**
 * 设置开始页面
 */
public class SettingActivity extends BaseActivity {

    @Override
    protected int getContentViewId() {
        return R.layout.activity_setting;
    }

    @OnClick(R.id.id_iv_back)
    public void onFinishCall(View v){
        finish();
    }

    @OnClick(R.id.tv_push_msg_setting)
    public void pushMessageSetting(View v) {
        go(MessagePushSettingActivity.class);
    }

    @OnClick(R.id.id_account_password)
    public void accountAndPassword(View v) {
        go(AccountSettingActivity.class);
    }


}
