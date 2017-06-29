package com.micro.mysegmentdefault.ui.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.adapter.BaseRecyclerAdapter;
import com.micro.mysegmentdefault.base.mvp.view.AbsUserNewsCommentView;
import com.micro.mysegmentdefault.entity.CollectionMessageEvent;
import com.micro.mysegmentdefault.entity.NewsCommentDataEntity;
import com.micro.mysegmentdefault.middle.BaseRefreshActivity;
import com.micro.mysegmentdefault.middleimpl.adapter.UserNewsCommentRecyclerAdapter;
import com.micro.mysegmentdefault.middleimpl.mvp.model.UserNewsCommentModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.UserNewsCommentPresenter;
import com.micro.mysegmentdefault.utils.ImageUtils;
import com.micro.mysegmentdefault.utils.LogUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author : micro_hx <p>
 * desc : 文章评论区 <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/27 - 10:31 <p>
 * interface :
 */

public class UserNewsCommentActivity
        extends BaseRefreshActivity<UserNewsCommentPresenter,UserNewsCommentModel,NewsCommentDataEntity.CommentItem>
        implements BaseRecyclerAdapter.OnLoadingHeaderCallBack, AbsUserNewsCommentView<NewsCommentDataEntity.CommentItem> {

    private UserNewsCommentRecyclerAdapter mUserNewsCommentAdapter;

    private String mNewsId ;
    private String mAuthor;
    private String mNewsType;
    private String mNewsTime;
    private String mNewsDesc;
    private String mNewsTitle;
    private String mNewsImage;
    private String mNewsLikeCount;
    private String mNewsCollectCount;

    private boolean mIsLike;
    private boolean mIsCollect;

    @Override
    protected void initViews() {
        super.initViews();
        EventBus.getDefault().register(this);
    }


    @Override
    protected void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    public static void start(Context ctx ,String newsId, String author , String newsType , String publishTime ,
                             String newsDesc , String newsTitle , String newsImage ,
                             String likeCount , boolean isLike ,
                             String collectCount , boolean isCollect ) {

        Intent intent = new Intent(ctx,UserNewsCommentActivity.class);
        intent.putExtra("m_newsId",newsId);
        intent.putExtra("m_author",author);
        intent.putExtra("m_newsType",newsType);
        intent.putExtra("m_newsTime",publishTime);
        intent.putExtra("m_newsDesc" , newsDesc);
        intent.putExtra("m_newsTitle",newsTitle);
        intent.putExtra("m_newsImage",newsImage);
        intent.putExtra("m_newsLikeCount",likeCount);
        intent.putExtra("m_newsCollectCount",collectCount);
        intent.putExtra("m_isLike" , isLike);
        intent.putExtra("m_isCollect" , isCollect);

        ctx.startActivity(intent);
    }

    @Override
    protected String getDefaultChannel() {
        return mNewsId;
    }

    @Override
    protected void initTitleSetting(FrameLayout mTitleContent) {
        mTvTitle.setText(R.string.str_comment);
    }

    @Override
    protected void initBeforeView(Bundle savedInstanceState) {
        Intent _intent = getIntent();
        mNewsId = _intent.getStringExtra("m_newsId");
        mAuthor = _intent.getStringExtra("m_author");
        mNewsType = _intent.getStringExtra("m_newsType");
        mNewsTime = _intent.getStringExtra("m_newsTime");
        mNewsDesc = _intent.getStringExtra("m_newsDesc");
        mNewsTitle = _intent.getStringExtra("m_newsTitle");
        mNewsImage = _intent.getStringExtra("m_newsImage");
        mNewsLikeCount = _intent.getStringExtra("m_newsLikeCount");
        mNewsCollectCount = _intent.getStringExtra("m_newsCollectCount");
        mIsLike = _intent.getBooleanExtra("m_isLike" , false);
        mIsCollect = _intent.getBooleanExtra("m_isCollect" , false);
    }

    @Override
    protected BaseRecyclerAdapter getRecyclerAdapter() {
        mUserNewsCommentAdapter = new UserNewsCommentRecyclerAdapter(this);
        mUserNewsCommentAdapter.setOnLoadingHeaderCallBack(this);
        return mUserNewsCommentAdapter;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCollectionEvent(CollectionMessageEvent event) {
        if(null != event && event.type == 1) {
            mNewsCollectCount = event.number;
            mIsCollect = event.isBookMarked;
            mBaseRecyclerAdapter.notifyItemChanged(0);
        }
    }


    /**
     * 请求完成之后，此时将不再请求数据 目前来看 分页数据后台返回的数据存在错误
     * 那么就算作一次请求吧
     */
    @Override
    protected void afterRequestFinish() {
        mBaseRecyclerAdapter.setState(BaseRecyclerAdapter.STATE_NO_MORE,true);
        mRefreshLayout.setCanLoadMore(false);
    }


    @Override
    public void zanOperationFinish(String number) {
        mNewsLikeCount = number;
        mIsLike = !mIsLike;
        mBaseRecyclerAdapter.notifyItemChanged(0);

        CollectionMessageEvent event = new CollectionMessageEvent();
        event.type = 2 ;
        event.number = number;
        event.isBookMarked = mIsLike;
        EventBus.getDefault().post(event);
    }



    @Override
    public void zanOperationError() {
        showToast(R.string.data_request_error);
    }

    /**
     * this is the recyclerView headerView
     *
     * @param parent
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateHeaderHolder(ViewGroup parent) {
        return new NewsHeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_news_comment_header_layout,parent,false));
    }

    @Override
    public void onBindHeaderHolder(RecyclerView.ViewHolder holder, int position) {
        NewsHeaderViewHolder newsHolder = (NewsHeaderViewHolder) holder;
        newsHolder.mTitle.setText(mAuthor + "发表于 #" + mNewsType + " " + mNewsTime);
        newsHolder.mDesc.setText(mNewsDesc);
        //newsHolder.mivIcon.
        ImageUtils.showUrlImage(mNewsImage,newsHolder.mivIcon);

        newsHolder.mNewsTitle.setText(mNewsTitle);
        newsHolder.mTvZan.setText(mNewsLikeCount);
        newsHolder.mTvCollection.setText(mNewsCollectCount);

        newsHolder.mTvZan.setSelected(mIsLike);
        newsHolder.mTvCollection.setSelected(mIsCollect);

        newsHolder.mLayoutZan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.zanOperation(mIsLike,mNewsId);
            }
        });

        newsHolder.mLayoutCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserAddCollectionActivity.start(UserNewsCommentActivity.this,0,mNewsId);
            }
        });

        newsHolder.mLayoutComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mRecyclerView.getChildCount() >= 2) {
                    int top = mRecyclerView.getChildAt(1).getTop();
                    LogUtils.d("the top is " + top);
                    mRecyclerView.scrollBy(0,top);
                }
            }
        });
    }


    class NewsHeaderViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.id_tv_title)
        TextView mTitle;

        @Bind(R.id.id_tv_desc)
        TextView mDesc ;

        @Bind(R.id.id_iv_icon)
        ImageView mivIcon ;

        @Bind(R.id.id_tv_news_title)
        TextView mNewsTitle;

        @Bind(R.id.id_tv_zan)
        TextView mTvZan;

        @Bind(R.id.id_tv_collection)
        TextView mTvCollection;

        @Bind(R.id.id_layout_zan)
        LinearLayout mLayoutZan;

        @Bind(R.id.id_layout_collect)
        LinearLayout mLayoutCollection;

        @Bind(R.id.id_layout_comment)
        LinearLayout mLayoutComment;


        public NewsHeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


}
