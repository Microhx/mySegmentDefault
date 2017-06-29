package com.micro.mysegmentdefault.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.adapter.BaseRecyclerAdapter;
import com.micro.mysegmentdefault.entity.LocationDataEntity;
import com.micro.mysegmentdefault.middle.BaseRefreshActivity;
import com.micro.mysegmentdefault.middleimpl.adapter.LocationChooseAdapter;
import com.micro.mysegmentdefault.middleimpl.mvp.model.LocationChooseModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.LocalChoosePresenter;
import com.micro.mysegmentdefault.utils.CommonUtils;

/**
 * author : micro_hx <p>
 * desc : 用户居住地 activity<p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/2 - 17:40 <p>
 * interface :
 */

public class LocationChooseActivity extends BaseRefreshActivity<LocalChoosePresenter,LocationChooseModel,LocationDataEntity.Item> implements LocationChooseAdapter.onItemSelectListener {

    private LocationChooseAdapter mLocationAdapter;
    private int mType;
    private String mCityCode;
    private String mCityName ;

    public static void start(Activity context , int type , String cityCode , String cityName) {
        start(context,type,cityCode,cityName,0);
    }

    public static void start(Activity context, int type , String cityCode , String cityName, int requestCode) {
        Intent _intent = new Intent(context,LocationChooseActivity.class);
        _intent.putExtra("type",type);
        _intent.putExtra("cityCode",cityCode);
        _intent.putExtra("cityName" , cityName);
        context.startActivityForResult(_intent,requestCode);
    }

    @Override
    protected void initTopRecyclerLayout(RelativeLayout layout) {
        if(mType == 0) {
            mTvTitle.setText(R.string.str_living_place);
            layout.setVisibility(View.VISIBLE);

            TextView tv = new TextView(getApplicationContext());
            int pxValue = CommonUtils.dip2px(getApplicationContext(),12);

            tv.setPadding(pxValue,pxValue,pxValue,pxValue);
            tv.setBackgroundColor(getResources().getColor(R.color.page_bg_color));
            tv.setText(R.string.str_all_city);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,16);
            tv.setTextColor(getResources().getColor(R.color.text_second_color));
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.CENTER_VERTICAL);
            layout.addView(tv,params);
        }else {
            mTvTitle.setText(mCityName);
        }
    }

    @Override
    protected void initBeforeView(Bundle savedInstanceState) {
        mType = getIntent().getIntExtra("type",0);
        mCityCode = getIntent().getStringExtra("cityCode");
        mCityName = getIntent().getStringExtra("cityName");
    }

    @Override
    protected int getCommonType() {
        return mType;
    }

    @Override
    protected String getDefaultChannel() {
        return mCityCode;
    }

    @Override
    protected BaseRecyclerAdapter getRecyclerAdapter() {
        mLocationAdapter = new LocationChooseAdapter(getApplication());
        mLocationAdapter.setOnItemSelelctListener(this);
        return mLocationAdapter;
    }

    @Override
    public void onItemSelect(String name, String cityId) {
        if(mType == 0) {
            LocationChooseActivity.start(LocationChooseActivity.this,1,cityId,name,100);
        }else {
            Intent _intent = new Intent().
                    putExtra("cityId" ,cityId).
                    putExtra("cityName",name);

            setResult(RESULT_OK,_intent);
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(null != data && resultCode == RESULT_OK && requestCode == 100) {
            setResult(RESULT_OK,data);
            finish();
        }
    }
}
