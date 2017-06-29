package com.micro.mysegmentdefault.ui;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.module.BaseActivity;
import com.micro.mysegmentdefault.entity.OnlyData;
import com.micro.mysegmentdefault.middle.UserPublishToutiaoContract;
import com.micro.mysegmentdefault.middleimpl.mvp.model.UserPublishToutiaoModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.UserPublishToutiaoPresenter;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * author : micro_hx <p>
 * desc : 用户发布头条 <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/28 - 16:02 <p>
 * interface :
 */

public class UserPublishToutiaoActivity extends BaseActivity<UserPublishToutiaoPresenter,UserPublishToutiaoModel> implements UserPublishToutiaoContract.AbsUserPublishToutiaoView {

    @Bind(R.id.id_et_website)
    EditText mWebSite;

    @Bind(R.id.id_et_title)
    EditText mTitle ;

    @Bind(R.id.id_et_type)
    EditText mChannel;

    @Bind(R.id.id_et_content)
    EditText mContent ;

    private String mChannelCode;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_publish_tou_tiao;
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @OnClick(R.id.id_iv_back)
    public void onFinish(View v) {
        finish();
    }

    @OnClick(R.id.id_et_type)
    public void goChannelType(View V) {
        goForResult(ChannelChooseActivity.class,100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 100 && resultCode == RESULT_OK) {
            mChannel.setText(data.getStringExtra("channelName"));
            mChannelCode = data.getStringExtra("channelCode");
        }
    }

    @OnClick(R.id.id_iv_right)
    public void onSubmit(View v) {
        String webSite = mWebSite.getText().toString().trim();
        String title = mTitle.getText().toString().trim();
        String channel = mChannel.getText().toString().trim();
        String content = mContent.getText().toString().trim();

        if(TextUtils.isEmpty(webSite)) {
            showToast(R.string.str_input_website);
            return;
        }

        if(TextUtils.isEmpty(title)) {
            showToast(R.string.str_input_link_title);
            return;
        }

        if(TextUtils.isEmpty(channel) || TextUtils.isEmpty(mChannelCode)) {
            showToast(R.string.str_input_channel);
            return;
        }

        if(TextUtils.isEmpty(content)) {
            showToast(R.string.str_input_content);
            return;
        }

        mPresenter.updateToutiao(webSite,title,mChannelCode,content);
    }

    @Override
    public void updateToutiaoResult(OnlyData data) {
        if(null != data){
            if(data.status == 0){
                showToast(R.string.str_input_channel_success);
                finish();
            }else{
                showToast(data.message);
            }
        }else{
            showToast(R.string.str_operation_error);
        }
    }


    @Override
    public Context getContext() {
        return this;
    }
}
