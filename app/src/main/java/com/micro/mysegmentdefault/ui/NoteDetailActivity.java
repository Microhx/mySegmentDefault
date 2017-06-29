package com.micro.mysegmentdefault.ui;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.entity.CollectionMessageEvent;
import com.micro.mysegmentdefault.entity.NoteDetailDataEntity;
import com.micro.mysegmentdefault.ui.comment.CommonWebActivity;
import com.micro.mysegmentdefault.middle.NoteDetailContract;
import com.micro.mysegmentdefault.middleimpl.mvp.model.NoteDetailModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.NoteDetailPresenter;
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
 * desc : 笔记详细页面<p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/16 - 17:48 <p>
 * interface :
 */

public class NoteDetailActivity extends CommonWebActivity<NoteDetailPresenter,NoteDetailModel> implements NoteDetailContract.NoteDetailView {

    private NoteDetailDataEntity.DataEntity mDataEntity;

    @Override
    protected void initHeadLayouts() {
        super.initHeadLayouts();
        mTagTitle.setText(R.string.str_note);

        //mTvZan
        mTvZan.setText(R.string.str_create_branch);
        mTvCollect.setText(R.string.str_collect);
        mTvComment.setText(R.string.str_comment);

        Drawable leftDrawable = getResources().getDrawable(R.drawable.ic_fork_selector);
        leftDrawable.setBounds(0,0,leftDrawable.getMinimumWidth(),leftDrawable.getMinimumHeight());
        mTvZan.setCompoundDrawables(leftDrawable,null,null,null);

        EventBus.getDefault().register(this);
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this,mModel);
        mPresenter.loadingNoteDetail(mNewsId);
    }

    @Override
    public void loadingNoteDetail(NoteDetailDataEntity entity) {
        mDataEntity = entity.getData();

        if (!TextUtils.isEmpty(mDataEntity.getParsedText())) {
            showBottomData();

            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("note", entity.getData());

            //检查文件夹是否存在
            FileUtils.getDirData(this, "themes/note-detail.chtml", "note-detail.chtml").getAbsolutePath();
            //替换html模版
            String content = NewsDetailParser.getInstance(this.getExternalCacheDir().getAbsolutePath()).render("note-detail.chtml", tempMap);
            //替换图片文件
            content = FileUtils.replaceAllImagePath(content);

            //加载本地数据
            mWebView.loadDataWithBaseURL("file:///android_asset/", content, "text/html", "utf-8", null);
            return;
        }

        mWebView.loadUrl(Api.BASE_URL + entity.getData().getUrl());
    }

    private void showBottomData() {
        if(CommonUtils.safeParseInt(mDataEntity.getForks()) > 0) {
            mTvZan.setText(mDataEntity.getForks());
            mTvZan.setSelected(mDataEntity.isIsForked());
        }

        if(CommonUtils.safeParseInt(mDataEntity.getBookmarks()) > 0) {
            mTvCollect.setText(mDataEntity.getBookmarks());
            mTvCollect.setSelected(mDataEntity.isIsBookmarked());
        }

        if(CommonUtils.safeParseInt(mDataEntity.getComments()) > 0) {
            mTvComment.setText(mDataEntity.getComments());
        }
    }

    /**
     * 添加zan
     * @param v
     *
     */
    @OnClick(R.id.id_layout_zan)
    public void addZan(View v) {
        if(mDataEntity.isIsForked()) return;
        mPresenter.createOrCancelBranch(mDataEntity.getId());
    }

    @Override
    public void createOrCancelBranch(boolean result) {
        if(result) {
            String fork = mDataEntity.getForks();
            int target = CommonUtils.safeParseInt(fork) + 1;
            mTvZan.setText(String.valueOf(target));
            mTvZan.setSelected(true);
        }else{
            showToast(R.string.str_operation_error);
        }
    }

    /**
     * 添加到自己的markbook
     * @param v
     */
    @OnClick(R.id.id_layout_collect)
    public void addCollection(View v) {
        UserAddCollectionActivity.start(this,3,mNewsId);
    }


    /**
     * 查看&添加评论
     * @param v
     */
    @OnClick(R.id.id_layout_comment)
    public void addComment(View v) {
        showToast("添加评论啊");
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCollectionEvent(CollectionMessageEvent event) {
        LogUtils.d("--->event--->>" + event.type + "----->>" + event.number);

        if(null != event && event.type == 1) {
            mTvCollect.setText(CommonUtils.safeParseInt(event.number) <= 0 ? getString(R.string.str_collect) : event.number);
            mTvCollect.setSelected(event.isBookMarked);
            showToast(R.string.str_operation_success);
        }
    }


    @Override
    public void showErrorLoading() {
        LogUtils.d("loading error " );
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);

        super.onDestroy();
    }
}
