package com.micro.mysegmentdefault.ui.user.collection;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.SegmentApplication;
import com.micro.mysegmentdefault.entity.UserCollectionDetailDataEntity;
import com.micro.mysegmentdefault.middle.view.AbsUserCollectView;
import com.micro.mysegmentdefault.middleimpl.mvp.model.UserCollectionDetailModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.UserCollectionDetailPresenter;
import com.micro.mysegmentdefault.ui.user.attention.AbBaseAttentionActivity;
import com.micro.mysegmentdefault.utils.CommonUtils;
import com.micro.mysegmentdefault.utils.ImageUtils;
import com.micro.mysegmentdefault.view.recyclerview.ViewHolderHelper;

/**
 * author : micro_hx <p>
 * desc : 收藏详细内容展示页 header + Item List<p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/16 - 15:25 <p>
 * interface :
 */

public class UserCollectionDetailActivity extends AbBaseAttentionActivity<
                                            UserCollectionDetailPresenter,
                                            UserCollectionDetailModel,
                                            UserCollectionDetailDataEntity.Item>
                                         implements
                                            AbsUserCollectView<UserCollectionDetailDataEntity.Item> {
    private String mId;
    private String mCountNumber ;

    //是否为私密 私密则不能分享
    private String mIsPrivate ;

    //条目名称
    private String mCollectionTitle ;
    //条目关注数
    private String mCollectionNumber ;
    //发布人昵称
    private String mCollectUserName ;
    //发布人图像
    private String mCollectUserIcon ;

    private TextView mHeaderTitle ;
    private TextView mHeaderUserName ;
    private TextView mHeaderAttentionCount ;
    private ImageView mHeaderUserIcon;

    private Toolbar mToolbar ;

    public static void  start(String id,String countNumber,String isPrivate) {
        Intent _intent = new Intent(SegmentApplication.getApplication(),UserCollectionDetailActivity.class);
        _intent.putExtra("id", id).
                putExtra("countNumber",countNumber).
                putExtra("isPrivate",isPrivate).
                setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        SegmentApplication.getApplication().startActivity(_intent);
    }

    @Override
    protected void initBeforeView(Bundle savedInstanceState) {
        super.initBeforeView(savedInstanceState);
        mId = getIntent().getStringExtra("id");
        mCountNumber= getIntent().getStringExtra("countNumber");
        mIsPrivate = getIntent().getStringExtra("isPrivate");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_collection_operation,menu);
        return true;
    }

    @Override
    protected String getDefaultChannel() {
        return mId;
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    protected void initTitleSetting(FrameLayout mTitleContent) {
        /*mTvTitle.setText("共" +mCountNumber + "个条目");
        mIvRightButton.setVisibility(View.VISIBLE);
        mIvRightButton.setImageResource(R.drawable.ic_more);*/
        mTitleContent.removeAllViews();

        mToolbar = new Toolbar(getApplicationContext());
        mToolbar.setTitle(String.format("共%s个条目",mCountNumber));
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserCollectionDetailActivity.this.finish();
            }
        });
        //设置我们需要的Toolbar
        //setSupportActionBar(mToolbar);

        mTitleContent.addView(mToolbar,getDefaultLayoutParams(true));
    }


    /**
     * RecyclerView顶端Layout
     * @param layout
     */
    protected void initTopRecyclerLayout(RelativeLayout layout) {
        View rootView = LayoutInflater.from(this).inflate(R.layout.collection_detail_header_item,layout,false);
        mHeaderTitle = (TextView) rootView.findViewById(R.id.id_tv_title);
        mHeaderUserName = (TextView) rootView.findViewById(R.id.id_tv_user_name);
        mHeaderAttentionCount = (TextView) rootView.findViewById(R.id.id_tv_attention_number);
        mHeaderUserIcon = (ImageView) rootView.findViewById(R.id.id_iv_user_icon);
        setHeaderData();

        layout.setVisibility(View.VISIBLE);
        layout.removeAllViews();
        layout.addView(rootView,getDefaultLayoutParams(false));

    }

    private void setHeaderData() {
        if(TextUtils.isEmpty(mCollectionTitle)) return;

        mHeaderTitle.setText(mCollectionTitle);
        mHeaderUserName.setText(mCollectUserName);
        mHeaderAttentionCount.setText("共" + mCollectionNumber + "人关注");
        ImageUtils.showUrlImage(mCollectUserIcon,mHeaderUserIcon);
    }


    @Override
    protected int getItemLayoutId() {
        return R.layout.collection_detail_item;
    }

    @Override
    protected void convertData(ViewHolderHelper holder, UserCollectionDetailDataEntity.Item item, int position) {
        holder.setTextView(R.id.id_tv_title,item.title);
        holder.setTextView(R.id.id_tv_time, item.createdDate);
        holder.setTextView(R.id.id_tv_info , item.user.name + " " + item.viewsWord+"人收藏") ;
    }

    @Override
    public void updateUserOtherCollectInfo(String userName, String userPhoto, String collectTitle, String collectCount) {
        mCollectionTitle = collectTitle;
        mCollectionNumber = collectCount;
        mCollectUserName = userName;
        mCollectUserIcon = userPhoto;

        setHeaderData();
    }



    private RelativeLayout.LayoutParams getDefaultLayoutParams(boolean heightIsMatchParent) {
        return new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, heightIsMatchParent?RelativeLayout.LayoutParams.MATCH_PARENT: RelativeLayout.LayoutParams.WRAP_CONTENT);
    }

}
