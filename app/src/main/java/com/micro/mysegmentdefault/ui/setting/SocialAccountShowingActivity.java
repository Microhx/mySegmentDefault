package com.micro.mysegmentdefault.ui.setting;

import android.util.Log;
import android.view.View;
import android.widget.Switch;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.module.BaseActivity;
import com.micro.mysegmentdefault.entity.SocialAccountBindDataEntity;
import com.micro.mysegmentdefault.middle.SocialAccountShowContract;
import com.micro.mysegmentdefault.middleimpl.mvp.model.SocialAccountModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.SocialAccountPresenter;
import com.micro.mysegmentdefault.utils.LogUtils;
import com.micro.mysegmentdefault.view.widget.MessagePushItemLayout;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 用户社交账号展示
 */
public class SocialAccountShowingActivity extends BaseActivity<SocialAccountPresenter, SocialAccountModel> implements SocialAccountShowContract.AbsSocialAccountView, MessagePushItemLayout.OnCheckBoxOnSelectListener {

    @Bind(R.id.id_item_weibo)
    MessagePushItemLayout mWeiBo;

    @Bind(R.id.id_item_google)
    MessagePushItemLayout mGoogle;

    @Bind(R.id.id_item_github)
    MessagePushItemLayout mGithub;

    //第一次获取数据 添加到switch中 也会触发onSelect函数
    private boolean mCheckEffective = false ;

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this, mModel);
        mPresenter.loadSocialAccountInfo();
    }

    @Override
    protected void initViews() {
        mWeiBo.setSelectListener(this);
        mGoogle.setSelectListener(this);
        mGithub.setSelectListener(this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_social_account_showing;
    }

    @Override
    public void showSocialAccountResult(SocialAccountBindDataEntity entity) {
        if (null != entity && entity.status == 0) {
            mWeiBo.setSwitchOpen(entity.data.weibo);
            mGoogle.setSwitchOpen(entity.data.google);
            mGithub.setSwitchOpen(entity.data.github);
        } else {
            showToast(R.string.str_get_data_error);
        }
        mCheckEffective = true ;
    }

    @Override
    public void onSelect(View view) {
        if(mCheckEffective) {
            mPresenter.updateSocialAccountInfo(view.getTag() + "", ((Switch) view).isChecked());
        }
    }

    @Override
    public void updateSocialAccountInfo(boolean result) {
        if (!result) {
            showToast(R.string.str_operation_error);
        }
    }

    @OnClick(R.id.id_iv_back)
    public void onFinish(View v) {
        finish();
    }
    


}
