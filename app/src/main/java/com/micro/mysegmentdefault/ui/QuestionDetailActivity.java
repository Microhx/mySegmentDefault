package com.micro.mysegmentdefault.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.entity.CollectionMessageEvent;
import com.micro.mysegmentdefault.entity.QuestionDetailDataEntity;
import com.micro.mysegmentdefault.middleimpl.fragment.BottomShareFragment;
import com.micro.mysegmentdefault.ui.comment.CommonWebActivity;
import com.micro.mysegmentdefault.middle.QuestionContract;
import com.micro.mysegmentdefault.middleimpl.mvp.model.QuestionDetailModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.QuestionDetailPresenter;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.ui.user.UserAddCollectionActivity;
import com.micro.mysegmentdefault.utils.CommonUtils;
import com.micro.mysegmentdefault.utils.FileUtils;
import com.micro.mysegmentdefault.utils.LogUtils;
import com.micro.mysegmentdefault.viewparser.NewsDetailParser;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.OnClick;

/**
 * author : micro_hx <p>
 * desc : 问答详细内容<p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/16 - 15:55 <p>
 * interface :
 */
public class QuestionDetailActivity extends CommonWebActivity<QuestionDetailPresenter,QuestionDetailModel> implements QuestionContract.QuestionDetailView {

    private QuestionDetailDataEntity.DataEntity mDataEntity;

    @Override
    protected void initViews() {
        super.initViews();

        EventBus.getDefault().register(this);

        mZanLayout.setVisibility(View.GONE);
        Drawable leftDrawable = getResources().getDrawable(R.drawable.ic_action_follow_selector);
        leftDrawable.setBounds(0, 0, leftDrawable.getMinimumWidth(), leftDrawable.getMinimumHeight());
        mTvComment.setCompoundDrawables(leftDrawable,null,null,null);

        mTvCollect.setText(R.string.str_collect);
        mTvComment.setText(R.string.str_attention);
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this,mModel);
        mPresenter.loadQuestionAnswer(mNewsId);
    }

    @Override
    public void showQuestionDetailEntity(QuestionDetailDataEntity entity) {
        mDataEntity = entity.getData();
        if(null == mDataEntity) return;

        mTagTitle.setText(R.string.str_question);
        setBottomStatus();

        if (!TextUtils.isEmpty(entity.getData().getParsedText()) ) {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("question", entity.getData());

            //检查文件夹是否存在
            FileUtils.getDirData(this, "themes/question-detail.chtml", "question-detail.chtml").getAbsolutePath();
            //替换html模版
            String content = NewsDetailParser.getInstance(this.getExternalCacheDir().getAbsolutePath()).render("question-detail.chtml", tempMap);
            //替换图片文件
            content = FileUtils.replaceAllImagePath(content);

            //加载本地数据
            mWebView.loadDataWithBaseURL("file:///android_asset/", content, "text/html", "utf-8", null);
            return;
        }

        mWebView.loadUrl(Api.BASE_URL + entity.getData().getUrl());

    }

    private void setBottomStatus() {
        if(CommonUtils.safeParseInt(mDataEntity.getBookmarks()) > 0) {
            mTvCollect.setText(mDataEntity.getBookmarks());
        }else{
            mTvCollect.setText(R.string.str_collect);
        }

        if(CommonUtils.safeParseInt(mDataEntity.getFollowers()) > 0) {
            mTvComment.setText(mDataEntity.getFollowers());
        }else{
            mTvComment.setText(R.string.str_attention);
        }

        mTvCollect.setSelected(mDataEntity.isIsBookmarked());
        mTvCollect.setSelected(mDataEntity.isIsFollowed());
    }


    @Override
    public void showError() {
        LogUtils.d("loading error");
    }


    @OnClick(R.id.id_iv_right)
    public void loadBottomShare(View view) {
        if(null == mDataEntity) return;

        BottomShareFragment fragment = new BottomShareFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title",mDataEntity.getTitle());
        bundle.putString("desc",mDataEntity.getTitle());
        bundle.putString("url",mDataEntity.getUrl());
        fragment.setArguments(bundle);
        fragment.show(getSupportFragmentManager(),"share");
    }

    /**
     * 添加收藏
     * @param v
     */
    @OnClick(R.id.id_layout_collect)
    public void addCollection(View v) {

        if(checkUserLogin()) {
            UserAddCollectionActivity.start(this, 2, mNewsId);
        }
    }


    @OnClick(R.id.id_layout_comment)
    public void addComment(View v) {
        if(null == mDataEntity) return;

        if(checkUserLogin()){
            mPresenter.loadQuestionFollow(mDataEntity.isIsFollowed(),mDataEntity.getId());
        }
    }

    @Override
    public void showQuestionDetailFollow(boolean result, String number) {
        if(result) {
            mDataEntity.setIsFollowed(!mDataEntity.isIsFollowed());
            mTvComment.setSelected(mDataEntity.isIsFollowed());
            mTvComment.setText(number);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCollectionEvent(CollectionMessageEvent event) {

        if(null != event && event.type == 1) {
            mTvCollect.setText(event.number);
            mTvCollect.setSelected(event.isBookMarked);
            showToast(R.string.str_operation_success);
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);

        super.onDestroy();
    }
}
