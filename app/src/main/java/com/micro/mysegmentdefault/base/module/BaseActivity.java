package com.micro.mysegmentdefault.base.module;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.micro.mysegmentdefault.base.mvp.model.BaseModel;
import com.micro.mysegmentdefault.base.mvp.presenter.BasePresenter;
import com.micro.mysegmentdefault.utils.ClassUtils;
import com.micro.mysegmentdefault.utils.LogUtils;
import com.micro.mysegmentdefault.utils.ToastUtils;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.io.File;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;

import butterknife.ButterKnife;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/3/19 - 22:48 <p>
 * interface :
 */

public abstract class BaseActivity<T extends BasePresenter, E extends BaseModel> extends RxAppCompatActivity {

    public T mPresenter;
    public E mModel;
    public Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBeforeView(savedInstanceState);

        initTempMethod();

        setContentView(getContentViewId());

        ButterKnife.bind(this);

        initSelfSetting();

        initPresenter();

        initViews();
    }

    private void initTempMethod() {
        try {
            Field field =  ButterKnife.class.getDeclaredField("debug");
            field.setAccessible(true);
            field.set(ButterKnife.class,true);

            Field field1 =  ButterKnife.class.getDeclaredField("BINDERS");
            field1.setAccessible(true);
            field1.set(ButterKnife.class,new LinkedHashMap<Class<?>, ButterKnife.ViewBinder<Object>>());

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    //初始化View之间所做的操作
    protected void initBeforeView(Bundle savedInstanceState) {}

    protected abstract int getContentViewId();

    protected void initSelfSetting() {
        mContext = this;
        mPresenter = ClassUtils.getT(this, 0);
        mModel = ClassUtils.getT(this, 1);
        if (null != mPresenter) {
            mPresenter.mContext = this;
        }
    }

    protected void initPresenter() {

    }


    protected void initViews() {

    }


    //基础方法：
    protected void go(Class<? extends Activity> activity) {
        startActivity(new Intent(this, activity));
    }


    protected void goForResult(Class<? extends Activity> activity , int requestCode) {
        startActivityForResult(new Intent(this,activity),requestCode);
    }

    protected void showToast(String msg) {
        ToastUtils.showMessage(this,msg);
    }

    protected void showToast(int msg) {
        ToastUtils.showMessage(this,msg);
    }

    /*@Override
    protected void onDestroy() {
        super.onDestroy();

        ButterKnife.unbind(this);
    }*/
}
