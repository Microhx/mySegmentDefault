package com.micro.mysegmentdefault.ui.write;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListPopupWindow;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.module.BaseActivity;
import com.micro.mysegmentdefault.entity.BestTag;
import com.micro.mysegmentdefault.entity.OnlyData;
import com.micro.mysegmentdefault.entity.UserBlogDataEntity;
import com.micro.mysegmentdefault.logic.UserLogic;
import com.micro.mysegmentdefault.middle.WriteArticleContract;
import com.micro.mysegmentdefault.middleimpl.adapter.listbase.ArticleColumnAdapter;
import com.micro.mysegmentdefault.middleimpl.mvp.model.WriteArticleModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.WriteArticlePresenter;
import com.micro.mysegmentdefault.utils.CommonUtils;
import com.micro.mysegmentdefault.view.widget.TagDynamicDrawable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

import static android.view.WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE;
import static android.widget.ListPopupWindow.INPUT_METHOD_NEEDED;

/**
 * 写文章
 */
public class WriteArticleActivity extends BaseActivity<WriteArticlePresenter, WriteArticleModel> implements WriteArticleContract.AbsWriteArticleView, AdapterView.OnItemClickListener {

    @Bind(R.id.id_et_title)
    EditText mEtTitle;

    @Bind(R.id.id_et_tag)
    EditText mEtTag;

    @Bind(R.id.id_et_content)
    EditText mEtContent;

    @Bind(R.id.id_layout)
    LinearLayout mItemLayout;

    @Bind(R.id.id_tv_column)
    TextView mTvColumn;

    //专栏集合
    List<UserBlogDataEntity.BlogItem> mBlogItemList;

    ListPopupWindow mListPopupWindow;

    ArticleColumnAdapter mArticleColumnAdapter;

    private String mBlogColumnId;

    private ArrayList<BestTag> mBestTags;

    @Override
    protected int getContentViewId() {
        return R.layout.content_write_article;
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this, mModel);
        mPresenter.loadUserBlogInfo();
    }

    @Override
    protected void initViews() {
        mListPopupWindow = new ListPopupWindow(this);
        mListPopupWindow.setSoftInputMode(SOFT_INPUT_ADJUST_RESIZE);
        mListPopupWindow.setInputMethodMode(INPUT_METHOD_NEEDED);
        mListPopupWindow.setAnchorView(mItemLayout);
        mListPopupWindow.setVerticalOffset(-100);

        //mListPopupWindow.setAdapter(mAskTagAdapter);
        mListPopupWindow.setBackgroundDrawable(new ColorDrawable(-1));
        mListPopupWindow.setOnItemClickListener(this);
    }

    @OnClick(R.id.id_layout)
    public void showColumnView(View v) {
        mListPopupWindow.show();
    }


    @OnClick(R.id.id_iv_back)
    public void onFinish(View v) {
        this.finish();
    }

    @Override
    public void showUserBlogInfo(UserBlogDataEntity dataEntity) {
        if (null != dataEntity) {
            mBlogItemList = dataEntity.data;
        } else {
            showToast(R.string.str_get_data_error);
        }

        mArticleColumnAdapter = new ArticleColumnAdapter(mBlogItemList);
        mListPopupWindow.setAdapter(mArticleColumnAdapter);
    }

    @Override
    public void showUserCommitBlog(OnlyData data) {
        if (null != data) {
            if (data.status == 1) {
                showToast(R.string.str_note_publish_success);
            } else {
                showToast(data.message);
            }

            finish();
        } else {
            showToast(R.string.str_operation_error);
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @OnClick(R.id.id_et_tag)
    public void onChooseTag(View v) {
        goForResult(new Intent(this, AskTagActivity.class).putExtra("bestTags", mBestTags), 200);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mListPopupWindow.dismiss();

        if (CommonUtils.collectionIsNull(mBlogItemList) || position >= mBlogItemList.size()) {
            //TODO 未知开通专栏的数据结构
            goForResult(CreateSpecialColumnActivity.class, 100);
        } else {
            UserBlogDataEntity.BlogItem item = mArticleColumnAdapter.getItem(position);
            mTvColumn.setText(item.name);
            mBlogColumnId = item.id;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 200 && resultCode == RESULT_OK) {
            mBestTags = data.getParcelableArrayListExtra("bestTags");
            resetTagList();
        }
    }


    private void resetTagList() {
        if (CommonUtils.collectionIsNull(mBestTags)) return;
        SpannableStringBuilder sb = new SpannableStringBuilder();
        int index = 0;
        for (BestTag tag : mBestTags) {
            String name = tag.name;
            int length = name.length();

            sb.append(name);
            sb.setSpan(new TagDynamicDrawable(tag), index, index + length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            index += length;
        }
        mEtTag.getText().clear();
        mEtTag.getText().append(sb);
    }


    @OnClick(R.id.id_tv_right)
    public void onCommit(View v) {
        if (TextUtils.isEmpty(mBlogColumnId)) {
            showToast(R.string.str_choose_special_column);
            return;
        }

        String title = mEtTitle.getText().toString().trim();
        if (TextUtils.isEmpty(title)) {
            showToast(R.string.str_title_not_allow_null);
            return;
        }

        if (CommonUtils.collectionIsNull(mBestTags)) {
            showToast(R.string.str_add_acticle_tag);
            return;
        }

        String content = mEtContent.getText().toString().trim();
        if (TextUtils.isEmpty(content) || content.length() < 10) {
            showToast(R.string.str_article_length_too_short);
            return;
        }

        Map<String, String> optionMap = new HashMap<>();
        optionMap.put("title", title);
        optionMap.put("text", content);
        optionMap.put("blogId", mBlogColumnId);
        optionMap.put("token", UserLogic.getUserToken());
        int index = 0;
        for (BestTag tag : mBestTags) {
            optionMap.put("tags[" + index++ + "]", tag.id);
        }
        mPresenter.commitUserBlog(optionMap);
    }


}
