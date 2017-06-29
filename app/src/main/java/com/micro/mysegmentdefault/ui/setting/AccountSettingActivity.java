package com.micro.mysegmentdefault.ui.setting;

import android.app.DialogFragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.module.BaseActivity;
import com.micro.mysegmentdefault.entity.AccountDataEntity;
import com.micro.mysegmentdefault.entity.ThirdPlatformDataEntity;
import com.micro.mysegmentdefault.middle.AccountSettingContract;
import com.micro.mysegmentdefault.middleimpl.fragment.UpdateEmailFragment;
import com.micro.mysegmentdefault.middleimpl.mvp.model.AccountModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.AccountPresenter;
import com.micro.mysegmentdefault.utils.CommonUtils;
import com.micro.mysegmentdefault.utils.DialogUtils;
import com.micro.mysegmentdefault.utils.LogUtils;
import com.micro.mysegmentdefault.view.widget.MessagePushItemLayout;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * author : micro_hx <p>
 * desc : 账户和密码 设置<p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/20 - 21:52 <p>
 * interface :
 */

public class AccountSettingActivity extends BaseActivity<AccountPresenter,AccountModel> implements AccountSettingContract.AbsAccountView, UpdateEmailFragment.OnEmailFinishListener {

    @Bind(R.id.id_tv_email)
    TextView mBindEmail;

    @Bind(R.id.id_tv_phone)
    TextView mBindPhone;

    @Bind(R.id.id_item_wechat)
    MessagePushItemLayout  mItemWeChat;

    @Bind(R.id.id_item_weibo)
    MessagePushItemLayout mItemWeiBo;

    @Bind(R.id.id_item_qq)
    MessagePushItemLayout mItemQQ;

    @Bind(R.id.id_item_google)
    MessagePushItemLayout mItemGoogle;

    @Bind(R.id.id_item_github)
    MessagePushItemLayout mItemGithub;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_account_setting;
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this,mModel);
        mPresenter.loadUserAccountInfo();
    }

    //--------------------------------------------------------------
    @Override
    public void showAccountDataEntity(AccountDataEntity entity) {
        String mail = entity.data.mail;
        String phone = entity.data.phone;

        if(!TextUtils.isEmpty(mail)) {
            mBindEmail.setText(CommonUtils.getSecretEmail(mail));
        }

        if(!TextUtils.isEmpty(phone)) {
            mBindPhone.setText(CommonUtils.getSecretPhone(phone));
        }
    }

    @Override
    public void showThirdPlatformDataEntity(ThirdPlatformDataEntity entity) {
        ThirdPlatformDataEntity.Account account = entity.data.bindings;
        mItemWeChat.setSwitchOpen(null != account.weixin);
        mItemWeiBo.setSwitchOpen(null != account.weibo);
        mItemQQ.setSwitchOpen(null != account.qq);
        mItemGoogle.setSwitchOpen(null != account.google);
        mItemGithub.setSwitchOpen(null != account.github);
    }

    @Override
    public void showDataError() {
        showToast(R.string.str_operation_error);
    }

    @OnClick(R.id.id_layout_email)
    public void onUpdateEmail(View v) {
        UpdateEmailFragment fragment = new UpdateEmailFragment();
        fragment.setListener(this);
        fragment.show(getFragmentManager(),"email");
    }

    @Override
    public void onFinish(String email) {
        LogUtils.d("email:" + email);
        //TODO
        DialogUtils.showAlertDialog(this,getString(R.string.str_email_has_sent),String.format(getString(R.string.str_email_sent_detail),email));
    }

    @OnClick(R.id.id_tv_update_password)
    public void onUpdatePassword(View v) {
        go(UpdatePasswordActivity.class);
    }

    @OnClick(R.id.id_tv_social_account_showing)
    public void onSocialAccountShowing(View v) {
        go(SocialAccountShowingActivity.class) ;
    }

}
