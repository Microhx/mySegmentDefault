package com.micro.mysegmentdefault.middleimpl.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.entity.OnlyData;
import com.micro.mysegmentdefault.entity.UserLoginDataEntity;
import com.micro.mysegmentdefault.logic.UserLogic;
import com.micro.mysegmentdefault.middle.UserLoginContract;
import com.micro.mysegmentdefault.middleimpl.mvp.model.UserLoginModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.UserLoginPresenter;
import com.micro.mysegmentdefault.utils.LogUtils;
import com.micro.mysegmentdefault.utils.ToastUtils;
import com.micro.mysegmentdefault.view.widget.MatchParentDialog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author : micro_hx <p>
 * desc : 用户登录Fragment <p>
 * email: javainstalling@163.com <p>
 * date : 2017/7/7 - 15:53 <p>
 * interface :
 */

public class UserLoginFragment extends AppCompatDialogFragment implements UserLoginContract.AbsLoginView, RegisterNextStepFragment.OnCheckPhoneListener {
    private static final int TYPE_LOGIN = 1 ;
    private static final int TYPE_REGISTER = 2 ;

    private int mCurrentType = TYPE_LOGIN;

    @Bind(R.id.id_text_input_name)
    TextInputLayout mInputName;

    @Bind(R.id.id_text_input_phone)
    TextInputLayout mInputPhone;

    @Bind(R.id.id_text_input_password)
    TextInputLayout mInputPassword;

    @Bind(R.id.id_et_name)
    TextInputEditText mEtName;

    @Bind(R.id.id_et_phone)
    TextInputEditText mEtPhone ;

    @Bind(R.id.id_et_password)
    TextInputEditText mEtPassword;

    @Bind(R.id.id_btn_login)
    Button mBtnType;

    @Bind(R.id.id_tv_change)
    TextView mTvChange;

    RegisterNextStepFragment mRegisterFragment ;


    private UserLoginPresenter mLoginPresenter;
    private UserLoginModel mLoginModel;


    @OnClick(R.id.id_iv_back)
    public void onFinish(View v) {
        this.dismiss();
    }

    @OnClick(R.id.id_tv_change)
    public void onChange(View v) {
        Bundle bundle = new Bundle();
        bundle.putInt("enter_type",mCurrentType == TYPE_LOGIN ? TYPE_REGISTER : TYPE_LOGIN );
        UserLoginFragment fragment = new UserLoginFragment();
        fragment.setArguments(bundle);
        fragment.show(getFragmentManager(),"register");

        //本fragment关闭
        dismiss();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_Fullscreen);

        if(getArguments() != null) {
            mCurrentType = getArguments().getInt("enter_type",TYPE_LOGIN);
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login_layout,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this,view);

        if(mCurrentType == TYPE_LOGIN) { //登录模式
            mEtName.setVisibility(View.GONE);
            mBtnType.setText(R.string.str_login);
            mTvChange.setText(R.string.str_register);

        }else if(mCurrentType == TYPE_REGISTER) {  //注册模式
            mBtnType.setText(R.string.str_register);
            mTvChange.setText(R.string.str_login);
        }

        mLoginPresenter = new UserLoginPresenter();
        mLoginModel = new UserLoginModel();
        mLoginPresenter.setVM(this,mLoginModel);
    }

    @OnClick(R.id.id_btn_login)
    public void onSubmit(View v) {
        String userName = mEtName.getText().toString().trim();

        String phone  = mEtPhone.getText().toString().trim();

        String password = mEtPassword.getText().toString().trim();

        if(mCurrentType == TYPE_LOGIN) { //登录
            if(TextUtils.isEmpty(phone)) {
                mInputName.setError(getString(R.string.str_input_phone_or_email));
                return ;
            }

            if(TextUtils.isEmpty(password)){
                mInputPassword.setError(getString(R.string.str_pass_not_null));
                return;
            }
            mLoginPresenter.login(phone,password);
        }else if(mCurrentType == TYPE_REGISTER) {
            if(TextUtils.isEmpty(userName)){
                mInputName.setError(getString(R.string.str_input_name));
                return;
            }

            if(TextUtils.isEmpty(phone)) {
                mInputPhone.setError(getString(R.string.str_input_phone_or_email));
                return;
            }

            if(!phone.matches("^1\\d{10}$")) {
                mInputPhone.setError(getString(R.string.str_phone_format_error));
                return;
            }

            if(TextUtils.isEmpty(password)){
                mInputPassword.setError(getString(R.string.str_pass_not_null));
                return;
            }
            mLoginPresenter.register(userName,phone,password);
        }
    }


    @Override
    public void showLoginResult(UserLoginDataEntity entity) {
        if(null != entity) {
            if(entity.status != 0){
                ToastUtils.showMessage(getContext(),entity.message);
            }else {
                //save user info
                UserLogic.saveUserLoginInfo(entity.data);
                dismiss();

                //notify success
                ToastUtils.showMessage(getActivity(),R.string.str_finish_login);
            }
        }else{
            ToastUtils.showMessage(getContext(),R.string.str_operation_error);
        }
    }

    //用户注册
    @Override
    public void showUserRegisterCheck(UserLoginDataEntity entity) {
        if(null != entity) {
            if(entity.status != 0) {
                mRegisterFragment.showErrorLog(entity.message);
            }else{
                UserLogic.saveUserLoginInfo(entity.data);
                //关闭验证对话框Fragment
                mRegisterFragment.dismiss();
                //关闭注册对话框Fragment
                this.dismiss();
                //告知发布
                ToastUtils.showMessage(getActivity(),R.string.str_finish_register);
            }
        }else {
            ToastUtils.showMessage(getActivity(),R.string.str_operation_error);
        }
    }

    @Override
    public void showRegisterResult(OnlyData onlyData) {
        if(null != onlyData){
            if(onlyData.status != 0){
                ToastUtils.showMessage(getActivity(),onlyData.message);
            }else{
                showRegisterNextStep();
            }
        }else{
            ToastUtils.showMessage(getActivity(),R.string.str_operation_error);
        }
    }

    private void showRegisterNextStep() {
        Bundle bundle = new Bundle();
        bundle.putString("phone",mEtPhone.getText().toString().trim());

        if(null ==mRegisterFragment) {
            mRegisterFragment = new RegisterNextStepFragment();
            mRegisterFragment.setOnCheckPhoneListener(this);
        }

        mRegisterFragment.setArguments(bundle);
        mRegisterFragment.show(getActivity().getFragmentManager(),"register");
        mRegisterFragment.startCount();
    }

    @Override
    public Context getShowingActivity() {
        return getActivity();
    }

    @Override
    public void onCheckUserCode(String code) {
        String name = mEtName.getText().toString().trim();
        String phone = mEtPhone.getText().toString().trim();
        String password = mEtPassword.getText().toString().trim();

        mLoginPresenter.checkUserPhone(name,phone,code,password);
    }
}
