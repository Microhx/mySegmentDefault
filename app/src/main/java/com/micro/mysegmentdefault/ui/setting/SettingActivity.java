package com.micro.mysegmentdefault.ui.setting;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.module.BaseActivity;
import com.micro.mysegmentdefault.entity.MessageEvent;
import com.micro.mysegmentdefault.logic.UserLogic;
import com.micro.mysegmentdefault.utils.DialogUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
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

    @OnClick(R.id.id_login_out)
    public void userLogout(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("您确定退出吗？").
                setPositiveButton(R.string.str_sure, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        UserLogic.clearAll();
                        userhasLogout();
                        dialog.dismiss();

                        finish();
                    }
                }).setNegativeButton(R.string.str_cancel,null).
                show();
    }


    private void userhasLogout() {
        MessageEvent event = new MessageEvent();
        event.type = 4 ;
        EventBus.getDefault().post(event);
    }


}
