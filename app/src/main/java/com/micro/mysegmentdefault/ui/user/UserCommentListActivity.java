package com.micro.mysegmentdefault.ui.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.micro.mysegmentdefault.entity.BaseDataEntity;
import com.micro.mysegmentdefault.entity.CollectionMessageEvent;
import com.micro.mysegmentdefault.entity.NewsCommentDataEntity;
import com.micro.mysegmentdefault.middle.BaseRefreshActivity;
import com.micro.mysegmentdefault.middleimpl.adapter.UserNewsCommentRecyclerAdapter;
import com.micro.mysegmentdefault.middleimpl.mvp.model.UserNewsCommentModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.UserNewsCommentPresenter;
import com.micro.mysegmentdefault.utils.CLICK_TYPE;
import com.micro.mysegmentdefault.utils.CommonUtils;
import com.micro.mysegmentdefault.utils.ImageUtils;
import com.micro.mysegmentdefault.utils.LogUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author : micro_hx <p>
 * desc : 文章评论区【只针对文章 还存在一种评论 只是针对用户评论列表而来】 <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/27 - 10:31 <p>
 * interface :
 */

public class UserCommentListActivity
        extends BaseRefreshActivity<UserNewsCommentPresenter,UserNewsCommentModel,NewsCommentDataEntity.CommentItem>
        implements BaseRecyclerAdapter.OnLoadingHeaderCallBack, AbsUserNewsCommentView<NewsCommentDataEntity.CommentItem>,UserNewsCommentRecyclerAdapter.onItemViewClickLister {

    private String mNewsId ;
    private String mAuthor;
    private String mNewsType;
    private String mNewsTime;
    private String mNewsDesc;
    private String mNewsTitle;
    private String mNewsImage;
    private String mNewsLikeCount;
    private String mNewsCollectCount;

    //是否为文章评论
    //文章评论存在文章头
    private boolean mIsNewsComment;

    private boolean mIsLike;
    private boolean mIsCollect;

    @Deprecated
    private NewsCommentDataEntity.CommentItem mCommentItem;

    private UserNewsCommentRecyclerAdapter mUserNewsCommentAdapter;

    private String mCommentId ;

    private int mCommentPosition ;
    private int mCommentSubPosition;

    @Override
    protected void initViews() {
        super.initViews();
        EventBus.getDefault().register(this);
    }


    @Override
    protected void initBottomLayoutView() {
        mBottomLayout.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    //新闻评论 news
    public static void start(Context ctx ,String newsId, String author , String newsType , String publishTime ,
                             String newsDesc , String newsTitle , String newsImage ,
                             String likeCount , boolean isLike ,
                             String collectCount , boolean isCollect ) {

        Intent intent = new Intent(ctx,UserCommentListActivity.class);
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

    //文章评论
    public static void start(Context ctx,String newsId) {
        Intent intent = new Intent(ctx,UserCommentListActivity.class).putExtra("hasHead",false).putExtra("m_newsId",newsId);
        ctx.startActivity(intent);
    }


    @Override
    protected String getDefaultChannel() {
        return mNewsId;
    }

    @Override
    protected int getCommonType() {
        return mIsNewsComment ? super.getCommonType() : 1;
    }

    //当数据为空，或者数据不存在时，是否显示empty页面
    @Override
    protected boolean showEmptyPageWhenDataIsNull() {
        return true ;
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

        mIsNewsComment = _intent.getBooleanExtra("hasHead",true);
    }

    @Override
    protected BaseRecyclerAdapter getRecyclerAdapter() {
        mUserNewsCommentAdapter = new UserNewsCommentRecyclerAdapter(this, mIsNewsComment);
        mUserNewsCommentAdapter.setItemViewClickListener(this);
        if(mIsNewsComment) {
            mUserNewsCommentAdapter.setOnLoadingHeaderCallBack(this);
        }

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

    @OnClick({R.id.id_bottom_layout})
    public void addSomeComment(View v) {
        mCommentPosition = -1 ;
        UserNewsCommentReplyActivity.start(this,mNewsId,false,null,COMMENT_REQUEST_CODE);
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
    public void zanOperationFinish(String type , String number) {

        if("news".equals(type)) {
            mNewsLikeCount = number;
            mIsLike = !mIsLike;
            mBaseRecyclerAdapter.notifyItemChanged(0);

            CollectionMessageEvent event = new CollectionMessageEvent();
            event.type = 2 ;
            event.number = number;
            event.isBookMarked = mIsLike;
            EventBus.getDefault().post(event);

        }else if("comment".equals(type)) {  //评论
           // mCommentItem.votes = number ;
           // mCommentItem.isLiked = !mCommentItem.isLiked;
           // mUserNewsCommentAdapter.updateCommentItem(mCommentItem);
            mUserNewsCommentAdapter.updateCommentItem2(number,mCommentId);
        }
    }


    @Override
    public void zanOperationError(BaseDataEntity entity) {
        if(null != entity) {
            showToast(entity.message);
        }else {
            showToast(R.string.data_request_error);
        }
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
                mPresenter.zanOperation("news",mIsLike,mNewsId);
            }
        });

        newsHolder.mLayoutCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserAddCollectionActivity.start(UserCommentListActivity.this,0,mNewsId);
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

    @Override
    public void onClick(CLICK_TYPE type,
                        int position,
                        int subPosition,
                        NewsCommentDataEntity.CommentItem item) {
        mCommentPosition = position;
        mCommentSubPosition = subPosition;

        LogUtils.d("----------" + type + "---------->>" + position + "---->>>" + subPosition + "---->>" + item);

        if(type == CLICK_TYPE.USER_CENTER) {
            UserZoneActivity.start(this, item.user.slug);

        }else if(type == CLICK_TYPE.ZAN){

            if(subPosition < 0) {
                mCommentId = item.id;
                mPresenter.zanOperation("comment",item.isLiked,item.id);

            }else {
                if(!CommonUtils.collectionIsNull(item.repliedComments)) {
                    NewsCommentDataEntity.RepliedItem replyItem = item.repliedComments.get(subPosition);
                    mCommentId = replyItem.id;
                    mPresenter.zanOperation("comment",replyItem.isLiked,item.id);
                }
            }

        }else if(type == CLICK_TYPE.REPLY) {  //不能回复嵌套的评论
            //TODO
            if(subPosition < 0) {
                UserNewsCommentReplyActivity.start(this,item.id,true,item.user.name,COMMENT_REQUEST_CODE);
            }else {
                NewsCommentDataEntity.RepliedItem repliedItem = item.repliedComments.get(subPosition);
                UserNewsCommentReplyActivity.start(this,item.id,true,repliedItem.user.name,COMMENT_REQUEST_CODE);
            }
        }
    }

    public static final int COMMENT_REQUEST_CODE = 0x01;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == COMMENT_REQUEST_CODE && resultCode == RESULT_OK && null != data) {
            //reload the commentList
            LogUtils.d("--------comment success-----------------");

            mUserNewsCommentAdapter.updateUserComment(mCommentPosition,mCommentSubPosition,data);
            checkLayoutVisible();
        }
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

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();

    }
}
