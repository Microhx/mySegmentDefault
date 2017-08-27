package com.micro.mysegmentdefault.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.entity.ArticleDetailEntity;
import com.micro.mysegmentdefault.entity.BaseDataEntity;
import com.micro.mysegmentdefault.entity.CollectionMessageEvent;
import com.micro.mysegmentdefault.middle.ArticleDetailContract;
import com.micro.mysegmentdefault.middleimpl.fragment.BottomShareFragment;
import com.micro.mysegmentdefault.middleimpl.mvp.model.ArticleDetailModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.ArticleDetailPresenter;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.ui.comment.CommonWebActivity;
import com.micro.mysegmentdefault.ui.user.UserAddCollectionActivity;
import com.micro.mysegmentdefault.ui.user.UserCommentListActivity;
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
 * desc : 专栏文章详细内容页面 <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/16 - 13:50 <p>
 * interface :
 */

public class ArticleDetailActivity extends CommonWebActivity<ArticleDetailPresenter, ArticleDetailModel> implements ArticleDetailContract.ArticleDetailView {

    private ArticleDetailEntity.DataEntity mDataEntity;

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this, mModel);
        mPresenter.loadArticleDetailInfo(mNewsId);
    }

    @Override
    protected void initHeadLayouts() {
        mIvRightImage.setImageResource(R.drawable.ic_share_mtrl_alpha);
    }


    @OnClick(R.id.id_iv_right)
    public void loadBottomShare(View view) {
        if (null == mDataEntity) return;

        BottomShareFragment fragment = new BottomShareFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", mDataEntity.getTitle());
        bundle.putString("desc", mDataEntity.getTitle());
        bundle.putString("url", mDataEntity.getUrl());
        fragment.setArguments(bundle);
        fragment.show(getSupportFragmentManager(), "share");
    }


    @Override
    protected void initViews() {
        super.initViews();

        //用户收藏夹
        EventBus.getDefault().register(this);

        mTvZan.setText(R.string.str_like);
        mTvCollect.setText(R.string.str_collect);
        mTvComment.setText(R.string.str_comment);
    }

    @Override
    public void showArticleDetailEntity(ArticleDetailEntity entity) {
        mTagTitle.setText(R.string.str_article);
        mDataEntity = entity.getData();
        setBottomText();

        if (!TextUtils.isEmpty(entity.getData().getParsedText())) {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("article", entity.getData());

            //检查文件夹是否存在
            FileUtils.getDirData(this, "themes/article-detail.chtml", "article-detail.chtml").getAbsolutePath();
            //替换html模版
            String content = NewsDetailParser.getInstance(this.getExternalCacheDir().getAbsolutePath()).render("article-detail.chtml", tempMap);
            //替换图片文件
            content = FileUtils.replaceAllImagePath(content);

            //加载本地数据
            mWebView.loadDataWithBaseURL("file:///android_asset/", content, "text/html", "utf-8", null);
            return;
        }

        mWebView.loadUrl(Api.BASE_URL + entity.getData().getUrl());
    }

    private void setBottomText() {
        if (CommonUtils.safeParseInt(mDataEntity.getVotes()) > 0) {
            mTvZan.setText(mDataEntity.getVotes());
        } else {
            mTvZan.setText(R.string.str_like);
        }

        if (CommonUtils.safeParseInt(mDataEntity.getBookmarks()) > 0) {
            mTvCollect.setText(mDataEntity.getBookmarks());
        } else {
            mTvCollect.setText(R.string.str_collect);
        }

        if (CommonUtils.safeParseInt(mDataEntity.getComments()) > 0) {
            mTvComment.setText(mDataEntity.getComments());
        } else {
            mTvComment.setText(R.string.str_comment);
        }

        mTvZan.setSelected(mDataEntity.isIsLiked());
        mTvCollect.setSelected(mDataEntity.isIsBookmarked());
    }


    /**
     * 添加zan
     *
     * @param v
     */
    @OnClick(R.id.id_layout_zan)
    public void addZan(View v) {
        if(null == mDataEntity) return;

         mPresenter.zanOperation(mTvZan.isSelected(),mNewsId);
    }

    @Override
    public void zanOperation(BaseDataEntity entity) {
        if(null != entity){
            if(entity.status != 0) {
                showToast(entity.message);
            }else{
                mTvZan.setText(entity.data);
                mTvZan.setSelected(!mTvZan.isSelected());
            }
        }else {
            showToast(R.string.str_operation_error);
        }
    }


    /**
     * 添加到自己的markbook
     *
     * @param v
     */
    @OnClick(R.id.id_layout_collect)
    public void addCollection(View v) {
        if(null == mDataEntity) return;
        UserAddCollectionActivity.start(this, 1,mNewsId);
    }


    /**
     * 查看&添加评论
     *
     * @param v
     */
    @OnClick(R.id.id_layout_comment)
    public void addComment(View v) {
        if(null == mDataEntity) return;
        UserCommentListActivity.start(this,mDataEntity.getId());
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCollectionEvent(CollectionMessageEvent event) {
        if (null != event && event.type == 1) {
            mTvCollect.setText(CommonUtils.safeParseInt(event.number) > 0 ? event.number : getString(R.string.str_collect));
            mTvCollect.setSelected(event.isBookMarked);
            showToast(R.string.str_operation_success);
        } else if (null != event && event.type == 2) {
            mTvZan.setText(CommonUtils.safeParseInt(event.number) > 0 ? event.number : getString(R.string.str_like));
            mTvZan.setSelected(event.isBookMarked);
        }
    }


    @Override
    public void loadDataError() {
        LogUtils.d("get Article detail error ");
    }


    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
