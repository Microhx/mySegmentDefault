package com.micro.mysegmentdefault.ui;

import android.text.TextUtils;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.middle.ActivityDetailContract;
import com.micro.mysegmentdefault.middleimpl.mvp.model.ActivityModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.ActivityPresenter;
import com.micro.mysegmentdefault.ui.comment.CommonWebActivity;

/**
 * author : micro_hx <p>
 * desc : 活动详情<p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/16 - 16:42 <p>
 * interface :
 */

public class ActivityDetailActivity extends CommonWebActivity<ActivityPresenter,ActivityModel> implements ActivityDetailContract.ActivityView {

    @Override
    protected void initViews() {
        super.initViews();
        mTagTitle.setText(R.string.str_activity);
        mPresenter.getActivityInfo(mNewsId);
    }

    @Override
    protected void initPresenter() {
      mPresenter.setVM(this,mModel);
    }


    @Override
    public void showActivityInfo(String webInfo) {
        if(TextUtils.isEmpty(webInfo)) {
            showToast(R.string.data_request_error);
        }

        mWebView.loadDataWithBaseURL(null, webInfo, "text/html", "utf-8", null);
    }
}
