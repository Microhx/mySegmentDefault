package com.micro.mysegmentdefault.base.module;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.micro.mysegmentdefault.base.mvp.model.BaseModel;
import com.micro.mysegmentdefault.base.mvp.presenter.BasePresenter;
import com.micro.mysegmentdefault.logic.UserLogic;
import com.micro.mysegmentdefault.middleimpl.fragment.UserLoginFragment;
import com.micro.mysegmentdefault.utils.ClassUtils;
import com.trello.rxlifecycle.components.support.RxFragment;

import butterknife.ButterKnife;
/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/3/22 - 14:02 <p>
 * interface :
 */

public abstract class BaseFragment<T extends BasePresenter, E extends BaseModel> extends RxFragment {

    protected Context mCtx ;
    protected View mContentView ;

    protected T mPresenter ;
    protected E mModel ;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initOnCreateMethod();
    }

    protected void initOnCreateMethod() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mCtx = getActivity() ;

        mContentView = inflater.inflate(getContentLayoutId() , null,false);
        ButterKnife.bind(this,mContentView);

        mPresenter = ClassUtils.getT(this,0);
        mModel = ClassUtils.getT(this,1);
        if(null != mPresenter) {
            mPresenter.mContext = this.getActivity() ;
        }

        initPresenter() ;
        initViews() ;
        return mContentView;
    }

    protected void initViews() {
    }

    protected void initPresenter() {
    }

    protected abstract int getContentLayoutId() ;

    @Override
    public void onDestroy() {
        ButterKnife.unbind(this);

        super.onDestroy();
    }

    protected void goWidthIntent(Intent intent) {
        startActivity(intent);
    }

    protected void goWithActivity(Class<? extends Activity> clazz) {
        startActivity(new Intent(getActivity(),clazz));
    }



    /**
     * 用户登录界面
     */
    protected boolean checkUserLogin(){
        if(!UserLogic.checkUserLogin()) {
            UserLoginFragment loginFragment = new UserLoginFragment();
            loginFragment.show(getFragmentManager(),"login");
            return false;
        }
        return true;

    }



}
