package com.micro.mysegmentdefault.ui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.adapter.BaseRecyclerAdapter;
import com.micro.mysegmentdefault.entity.NoteDataEntity;
import com.micro.mysegmentdefault.middle.BaseRefreshActivity;
import com.micro.mysegmentdefault.middleimpl.adapter.NoteListRecyclerAdapter;
import com.micro.mysegmentdefault.middleimpl.mvp.model.NoteListModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.NoteListPresenter;
import com.micro.mysegmentdefault.ui.user.UserNewNoteActivity;
import com.micro.mysegmentdefault.utils.CommonUtils;
import com.micro.mysegmentdefault.utils.LogUtils;
import com.micro.mysegmentdefault.view.widget.EmptyLayout;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * author : micro_hx <p>
 * desc : 代码笔记<p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/16 - 16:50 <p>
 * interface :
 */

public class NoteListActivity extends BaseRefreshActivity<NoteListPresenter,NoteListModel,NoteDataEntity.Item> implements AdapterView.OnItemSelectedListener {

    //当前获取为推荐笔记/个人笔记
    private int mCurrentPosition = 0;

    @Override
    protected void initTitleSetting(FrameLayout mTitleContent) {
        mTitleLayout.removeAllViews();
        mActionButton.setVisibility(View.VISIBLE);

        Spinner spinner = new Spinner(getApplicationContext(),Spinner.MODE_DROPDOWN);
        spinner.setAdapter(getSimpleAdapter());
        spinner.setGravity(Gravity.CENTER);
        spinner.setPopupBackgroundDrawable(new ColorDrawable(Color.parseColor("#A8A7A8")));
        spinner.setDropDownHorizontalOffset(CommonUtils.dip2px(this,40));
        spinner.setDropDownVerticalOffset(CommonUtils.dip2px(this,40));
        spinner.setOnItemSelectedListener(this);

        mTitleLayout.addView(spinner);

    }

    @Override
    protected BaseRecyclerAdapter getRecyclerAdapter() {
        return new NoteListRecyclerAdapter(this);
    }


    private SpinnerAdapter getSimpleAdapter() {
        return new ArrayAdapter(getApplication(),R.layout.spinner_item_text_layout,
                                R.id.id_tv_content, getResources().getStringArray(R.array.user_note));
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        LogUtils.d("----result position--->>" + position + "-----currentPosition---->>" + mCurrentPosition);

        if(mCurrentPosition != position){
            mCurrentPosition = position;

            mEmptyLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
            mPresenter.getCommonListDatas(mCurrentPosition,"",1);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}


    @OnClick(R.id.id_float_button)
    public void onEnterNewNoteActivity(View v) {
        go(UserNewNoteActivity.class);
    }

}
