package com.micro.mysegmentdefault.middleimpl.fragment;

import android.content.DialogInterface;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.adapter.BasePagerAdapter;
import com.micro.mysegmentdefault.base.module.BaseFragment;
import com.micro.mysegmentdefault.entity.MessageEvent;
import com.micro.mysegmentdefault.entity.TagDataEntity;
import com.micro.mysegmentdefault.middleimpl.adapter.NewsFragmentPagerAdapter;
import com.micro.mysegmentdefault.ui.MultipleSearchActivity;
import com.micro.mysegmentdefault.ui.UserTagManageActivity;
import com.micro.mysegmentdefault.ui.write.AskQuestionActivity;
import com.micro.mysegmentdefault.ui.write.WriteArticleActivity;
import com.micro.mysegmentdefault.utils.DialogUtils;
import com.micro.mysegmentdefault.utils.FileUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * author : micro_hx <p>
 * desc : 专栏【文章】fragment<p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/6 - 12:22 <p>
 * interface :
 */

public class ArticleFragment extends BaseFragment implements DialogInterface.OnClickListener {

    @Bind(R.id.id_toolbar_layout)
    Toolbar mToolBar;

    @Bind(R.id.id_tv_tab_layout)
    TabLayout mTableLayout;

    @Bind(R.id.id_view_pager)
    ViewPager mViewPager;

    @Bind(R.id.id_iv_right)
    ImageView mRightImage;

    private List<TagDataEntity.Item> mTitleEntityList;

    private BasePagerAdapter mBasePageAdapter;

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initViews() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(this.mToolBar);
        //the fragment has menu items to contribute.
        setHasOptionsMenu(true);

        mRightImage.setVisibility(View.VISIBLE);
        mTitleEntityList = FileUtils.getArticlesTitleEntityList(0);

        mBasePageAdapter = new NewsFragmentPagerAdapter(mTitleEntityList, getChildFragmentManager());
        mViewPager.setAdapter(mBasePageAdapter);
        mTableLayout.setupWithViewPager(mViewPager);
        mTableLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    @OnClick(R.id.id_iv_right)
    public void onCall(View e) {
        if(checkUserLogin()) {
            goWithActivity(UserTagManageActivity.class);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_news_title, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.id_menu_write) {
            String[] items = getResources().getStringArray(R.array.write_question_article);
            DialogUtils.showAlertItemDialog(getActivity(),items,this);
        }

        return true;
    }


    @OnClick(R.id.id_layout_search)
    public void goToMultipleSearch(View v) {
        goWithActivity(MultipleSearchActivity.class);
    }


    @Override
    protected void initOnCreateMethod() {
        super.initOnCreateMethod();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        if (null != event && event.type == -1) {
            mTitleEntityList = FileUtils.getArticlesTitleEntityList(0);
            mBasePageAdapter.updateDataList(mTitleEntityList);
            mViewPager.setCurrentItem(mTitleEntityList.size() - 1);
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if(which == 0) { //提问题
            DialogUtils.showAlertDialog(getActivity(),
                    getString(R.string.str_ask_question_title),
                    getString(R.string.str_ask_question_content),
                    getString(R.string.str_ask_question_i_kown),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            goWithActivity(AskQuestionActivity.class);
                        }
                    });
        }else { //写文章
                goWithActivity(WriteArticleActivity.class);
        }
    }
}
