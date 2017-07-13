package com.micro.mysegmentdefault.ui;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.JavascriptInterface;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.entity.CollectionMessageEvent;
import com.micro.mysegmentdefault.entity.NewsDetailDataEntity;
import com.micro.mysegmentdefault.ui.comment.CommonWebActivity;
import com.micro.mysegmentdefault.middle.NewsDetailContract;
import com.micro.mysegmentdefault.middleimpl.fragment.BottomShareFragment;
import com.micro.mysegmentdefault.middleimpl.mvp.model.NewsDetailModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.NewsDetailPresenter;
import com.micro.mysegmentdefault.ui.user.UserAddCollectionActivity;
import com.micro.mysegmentdefault.ui.user.UserNewsCommentActivity;
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
 * desc : 头条详细内容<p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/14 - 23:15 <p>
 * interface :
 */

public class HomeDataDetailActivity extends CommonWebActivity<NewsDetailPresenter, NewsDetailModel> implements NewsDetailContract.DetailView {

    private String mTitle ;
    private String mDesc ;
    private String mUrl ;

    private NewsDetailDataEntity.DataEntity mDataEntity;

    @Override
    protected void initViews() {
        super.initViews();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this, mModel);
        mPresenter.loadArticleDetailInfo(mNewsId);
    }

    @Override
    protected void initHeadLayouts() {
        mIvRightImage.setImageResource(R.drawable.ic_share_mtrl_alpha);
    }

    @Override
    public void showNewsDataDetailEntity(NewsDetailDataEntity entity) {
        mDataEntity = entity.getData();

        mTitle = mDataEntity.getTitle();
        mDesc = mDataEntity.getDescription();
        mUrl = mDataEntity.getUrl();

        mTagTitle.setText(mTitle);
        mTvZan.setText(mDataEntity.getVotesWord());
        mTvComment.setText(mDataEntity.getComments());
        mTvCollect.setText(mDataEntity.getBookmarksWord());

        mTvCollect.setSelected(mDataEntity.isIsBookmarked());
        mTvZan.setSelected(mDataEntity.isIsLiked());


        if (!TextUtils.isEmpty(mDataEntity.getReadParsedText()) || checkHost(mDataEntity.getOriginPath())) {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("news", entity.getData());

            //检查文件夹是否存在
            FileUtils.getDirData(this, "themes/news-detail.chtml", "news-detail.chtml").getAbsolutePath();
            //替换html模版
            String content = NewsDetailParser.getInstance(this.getExternalCacheDir().getAbsolutePath()).render("news-detail.chtml", tempMap);
            //替换图片文件
            content = FileUtils.replaceAllImagePath(content);

            //加载本地数据
            mWebView.loadDataWithBaseURL("file:///android_asset/", content, "text/html", "utf-8", null);

            //加载本地javaBean对象
            mWebView.addJavascriptInterface(new Sf(),"sf");

            return;
        }

        //这里直接加载网页 手机宽度过小时，底部会出现bottomView
        mWebView.loadUrl(mDataEntity.getOriginPath());
    }

    @Override
    public void loadDataError() {
        showToast(R.string.str_operation_error);
        LogUtils.d("-------error------>>");
        // mWebView.loadDataWithBaseURL();
    }


    private boolean checkHost(String path) {
        return Uri.parse(path).getHost().equals("mp.weixin.qq.com");
    }

    @OnClick(R.id.id_iv_right)
    public void loadBottomShare(View view) {
        BottomShareFragment fragment = new BottomShareFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title",mTitle);
        bundle.putString("desc",mDesc);
        bundle.putString("url",mUrl);
        fragment.setArguments(bundle);
        fragment.show(getSupportFragmentManager(),"share");
    }

    /**
     * 添加zan
     * @param v
     *
     */
    @OnClick(R.id.id_layout_zan)
    public void addZan(View v) {
        mPresenter.zanOperation(mTvZan.isSelected(),mNewsId);
    }

    /**
     * 添加到自己的markbook
     * @param v
     */
    @OnClick(R.id.id_layout_collect)
    public void addCollection(View v) {
        UserAddCollectionActivity.start(this,0,mNewsId);
    }

    /**
     * 查看&添加评论
     * @param v
     */
    @OnClick(R.id.id_layout_comment)
    public void addComment(View v) {
        UserNewsCommentActivity.start(this,mDataEntity.getId(),mDataEntity.getUser().getName(),mDataEntity.getNewsTypes().get(0).getName(),
                                    mDataEntity.getCreatedDate(),mDataEntity.getOriginal_text(),mDataEntity.getTitle(),
                                    mDataEntity.getReadFirstImg(),

                                    mTvZan.getText().toString(), mTvZan.isSelected(),
                                    mTvCollect.getText().toString(), mTvCollect.isSelected());}

    @Override
    public void zanOperationFinish(String number) {
        mTvZan.setText(number);
        mTvZan.setSelected(!mTvZan.isSelected());
    }

    @Override
    public void zanOperationError() {
        showToast(R.string.str_operation_error);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCollectionEvent(CollectionMessageEvent event) {
        if(null != event && event.type == 1) {
            mTvCollect.setText(event.number);
            mTvCollect.setSelected(event.isBookMarked);
            showToast(R.string.str_operation_success);
        }else if(null != event && event.type == 2) {
            mTvZan.setText(event.number);
            mTvZan.setSelected(event.isBookMarked);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void addUserFollow(String followerId , String otherInfo) {
        if(mDataEntity == null) return;
        mPresenter.followOrCancelUser(mDataEntity.getUser().isIsFollowed() , followerId);
    }

    @Override
    public void followUserResult(boolean result, String userId) {
        if(result) {
            LogUtils.d("----result------>>" + result + "----------userId---------->>" + userId);
            mDataEntity.getUser().setIsFollowed(!mDataEntity.getUser().isIsFollowed());

            //TODO 成功之后 需要reloadCss
        }
    }

    class Sf {
        @JavascriptInterface
        public void followAuthor(final String followerId , final String info){
            LogUtils.d("---------followerId----------->>" + followerId + "---->>info---->>"+info);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    addUserFollow(followerId,info);
                }
            });
        }
    }

}
