package com.micro.mysegmentdefault.ui.userzone;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.entity.ArticleDataEntity;
import com.micro.mysegmentdefault.middleimpl.mvp.model.ArticleModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.ArticlePresenter;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.ui.SchemeActivity;
import com.micro.mysegmentdefault.ui.user.attention.AbBaseAttentionActivity;
import com.micro.mysegmentdefault.utils.ToastUtils;
import com.micro.mysegmentdefault.view.recyclerview.ViewHolderHelper;

/**
 * author : micro_hx <p>
 * desc : 用户空间 --->TA的文章<p>
 * email: javainstalling@163.com <p>
 * date : 2017/9/2 - 20:07 <p>
 * interface :
 */

public class UserZoneArticleActivity extends AbBaseAttentionActivity<ArticlePresenter,ArticleModel,ArticleDataEntity.Item> {

    private String uid;

    public static void start(Context ctx, String uid) {
        ctx.startActivity(new Intent(ctx,UserZoneArticleActivity.class).putExtra("uid",uid));
    }

    @Override
    protected int getCommonType() {
        return Integer.MIN_VALUE;
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.news_item_layout;
    }

    @Override
    protected void initBeforeView(Bundle savedInstanceState) {
        uid = getIntent().getStringExtra("uid");
    }

    @Override
    protected void initTitleSetting(FrameLayout mTitleContent) {
        mTvTitle.setText(R.string.str_user_article);
    }

    @Override
    protected String getDefaultChannel() {
        return uid;
    }

    @Override
    protected void convertData(ViewHolderHelper holder, final ArticleDataEntity.Item item, int position) {
        holder.setTextView(R.id.id_tv_title,item.title).
                setTextView(R.id.id_tv_content,item.excerpt).
                setTextView(R.id.id_tv_vote_comment,item.votes + "人点赞  " + item.bookmarks + "人收藏").
                setTextView(R.id.id_tv_author,item.user.name).
                setItemViewOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(item.url)) {
                    Intent _intent = new Intent(mContext, SchemeActivity.class);
                    _intent.setData(Uri.parse(Api.WEB_URL + item.url));

                    mContext.startActivity(_intent);
                }else{
                    ToastUtils.showMessage(mContext,"url无效");
                }
            }
        });
    }
}
