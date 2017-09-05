package com.micro.mysegmentdefault.ui.user.collection;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.SegmentApplication;
import com.micro.mysegmentdefault.entity.BaseDataEntity;
import com.micro.mysegmentdefault.entity.UserCollectionDetailDataEntity;
import com.micro.mysegmentdefault.middle.view.AbsUserCollectView;
import com.micro.mysegmentdefault.middleimpl.fragment.BottomReportFragment;
import com.micro.mysegmentdefault.middleimpl.mvp.model.UserCollectionDetailModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.UserCollectionDetailPresenter;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.ui.user.UserNewCollectionTagActivity;
import com.micro.mysegmentdefault.ui.user.attention.AbBaseAttentionActivity;
import com.micro.mysegmentdefault.utils.ImageUtils;
import com.micro.mysegmentdefault.utils.LogUtils;
import com.micro.mysegmentdefault.view.recyclerview.ViewHolderHelper;

import java.util.Iterator;
import java.util.List;

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
    // private  1
    // public   0
    private String mIsPrivate ;

    //条目名称
    private String mCollectionTitle ;
    //条目关注数
    private String mCollectionNumber ;
    //发布人昵称
    private String mCollectUserName ;
    //发布人图像
    private String mCollectUserIcon ;

    //条目简介
    private String mCollectDesc ;
    //条目Id
    private String mCollectId ;
    //是否为作者
    private boolean isAuthor ;


    private TextView mHeaderTitle ;
    private TextView mHeaderUserName ;
    private TextView mHeaderAttentionCount ;
    private ImageView mHeaderUserIcon;

    private Toolbar mToolbar ;

    public static void  start(Activity act , String id, String isAuthor, String countNumber, String isPrivate, int requestCode) {
        Intent _intent = new Intent(act,UserCollectionDetailActivity.class);
        _intent.putExtra("id", id).
                putExtra("countNumber",countNumber).
                putExtra("isAuthor",isAuthor).
                putExtra("isPrivate",isPrivate);
        act.startActivityForResult(_intent,requestCode);
    }

    @Override
    protected void initBeforeView(Bundle savedInstanceState) {
        super.initBeforeView(savedInstanceState);
        mId = getIntent().getStringExtra("id");
        mCountNumber= getIntent().getStringExtra("countNumber");
        mIsPrivate = getIntent().getStringExtra("isPrivate");

        //True true
        isAuthor = "true".equalsIgnoreCase(getIntent().getStringExtra("isAuthor"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(null != menu) menu.clear();
         getMenuInflater().inflate(R.menu.menu_collection_operation,menu);
         addMoreMenu(menu);

         if(!isAuthor) {
             //非作者时 移除删除和编辑
             menu.removeItem(R.id.delete);
             menu.removeItem(R.id.edit);
         }

        return true;
    }

    private void addMoreMenu(Menu menu) {
        Intent localIntent = new Intent();
        localIntent.setAction(Intent.ACTION_SEND);
        localIntent.setType("text/plain");
        List localList = getPackageManager().queryIntentActivities(localIntent,0);
        PackageManager localPackageManager = getPackageManager();

        SubMenu localSubMenu = menu.findItem(R.id.menu_share).getSubMenu();
        SubMenu secondSubMenu = localSubMenu.addSubMenu("查看更多");

        Iterator iterator = localList.iterator();
        while (iterator.hasNext()) {
            ResolveInfo resolveInfo = (ResolveInfo) iterator.next();
            CharSequence charsequence = resolveInfo.loadLabel(localPackageManager);
            Drawable localDrawable = resolveInfo.loadIcon(localPackageManager);

            String localPackageName = resolveInfo.activityInfo.packageName;
            String localName = resolveInfo.activityInfo.name;

            String msg = "【"+mCollectionTitle+"】" + "分享来自@mySegmentFault,传送门:" + Api.WEB_URL + "/bookmark/" + mId;

            if(!"com.sina.weibo".equals(localPackageName) && !"com.tencent.mm".equals(localPackageName)) {
                MenuItem menuItem = secondSubMenu.add(charsequence);
                menuItem.setIcon(localDrawable);
                menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
                Intent _intent = new Intent().setAction(Intent.ACTION_SEND);
                _intent.setType("text/plain");
                _intent.putExtra("android.intent.extra.TEXT",msg);
                _intent.setClassName(localPackageName,localName);
                menuItem.setIntent(_intent);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.delete:
                goToDelete();
                break;

            case R.id.edit:
                updateBookMark();
                break;

            case R.id.report:
                reportSomeThing();
                break;

            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    private void reportSomeThing() {
        BottomReportFragment reportFragment = new BottomReportFragment();
        reportFragment.show(getSupportFragmentManager(),"report");
    }


    /**
     * 更新bookmark
     */
    private void updateBookMark() {
        UserNewCollectionTagActivity.
                start(this,mCollectionTitle,mCollectDesc,mCollectId,"1".equals(mIsPrivate),CODE_UPDATE_BOOK_MARK);
    }

    private void goToDelete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this) ;
        builder.setTitle(R.string.str_confirm_to_delete).
                setMessage(R.string.str_ask_to_delete).
                setPositiveButton(R.string.str_sure, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                mPresenter.deleteBookmark(mId);
            }
        }) .setNegativeButton(R.string.str_cancel,null).show();
    }

    public static final int CODE_UPDATE_BOOK_MARK = 100;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CODE_UPDATE_BOOK_MARK && resultCode == RESULT_OK){
            //刷新
            onRefreshing();
        }
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
        mTitleContent.removeAllViews();

        mToolbar = new Toolbar(this);
        mToolbar.setTitle(String.format("共%s个条目",mCountNumber));
        mToolbar.setTitleTextAppearance(getApplicationContext(),R.style.CoordinatorLayoutAppearance);
        mToolbar.setOverflowIcon(getResources().getDrawable(R.drawable.ic_white_more));

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserCollectionDetailActivity.this.finish();
            }
        });

        mTitleContent.addView(mToolbar,getDefaultFrameLayoutParams());

        //设置我们需要的Toolbar
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
    public void updateUserCollectionInfo(UserCollectionDetailDataEntity.Parent parent) {
        if(null != parent){
            mCollectionTitle = parent.title;
            mCollectionNumber = parent.num;
            mCollectUserName = parent.user.name;
            mCollectUserIcon = parent.user.avatarUrl;

            mCollectDesc = parent.description;
            mCollectId = parent.id;
            mIsPrivate = parent.isPrivate;

            setHeaderData();
        }
    }

    @Override
    public void deleteUserBookmark(BaseDataEntity entity) {
        if(null != entity) {
            if(entity.status == 0) {
                showToast(R.string.str_operation_success);
                setResult(RESULT_OK,new Intent().putExtra("collectId",mCollectId));
                finish();


            }else{
                showToast(entity.message);
            }
        }else{
            showToast(R.string.str_operation_error);
        }

    }

    @Override
    public Context getContext() {
        return this;
    }

    private FrameLayout.LayoutParams getDefaultFrameLayoutParams() {
        return new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    private RelativeLayout.LayoutParams getDefaultLayoutParams(boolean heightIsMatchParent) {
        return new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, heightIsMatchParent?RelativeLayout.LayoutParams.MATCH_PARENT: RelativeLayout.LayoutParams.WRAP_CONTENT);
    }

}
