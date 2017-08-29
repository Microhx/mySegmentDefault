package com.micro.mysegmentdefault.middleimpl.subfragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.module.BaseFragment;
import com.micro.mysegmentdefault.entity.UserPageEntity;
import com.micro.mysegmentdefault.middle.UserPagerContract;
import com.micro.mysegmentdefault.middleimpl.mvp.model.UserPageModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.UserPagePresenter;
import com.micro.mysegmentdefault.ui.user.TimeLineActivity;
import com.micro.mysegmentdefault.utils.ToastUtils;
import com.micro.mysegmentdefault.view.widget.TagContainerLayout;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * author : micro_hx <p>
 * desc : 用户个人主页 <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/19 - 15:18 <p>
 * interface :
 */

public class UserPageFragment extends BaseFragment<UserPagePresenter,UserPageModel> implements UserPagerContract.AbsUserPagerView {

    private String mUserName ;

    @Bind(R.id.id_tv_website)
    TextView mUserWebsite;

    @Bind(R.id.id_tv_intro)
    TextView mUserIntro;

    @Bind(R.id.id_tv_question)
    TextView mQuestion ;

    @Bind(R.id.id_tv_answer)
    TextView mAnswer ;

    @Bind(R.id.id_tv_article)
    TextView mArticle ;

    @Bind(R.id.id_tv_share)
    TextView mSharing ;

    @Bind(R.id.id_tv_collection)
    TextView mCollection;

    @Bind(R.id.id_layout_tags)
    TagContainerLayout mTagLayout;

    private String mUserId ;


    @Override
    protected void initOnCreateMethod() {
        super.initOnCreateMethod();
        mUserName = getArguments().getString("userName");
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this,mModel);
        mPresenter.loadUserPagersInfo(mUserName);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_user_page_layout;
    }

    @Override
    public void loadUserPagerInfo(UserPageEntity.UserPageItem item) {
        if(!TextUtils.isEmpty(item.homepage)) {
            mUserWebsite.setText(item.homepage);
        }

        if(!TextUtils.isEmpty(item.description)) {
            mUserIntro.setText(item.description);
        }

        mUserId = item.id;
        mQuestion.setText(item.questions);
        mAnswer.setText(item.answers);
        mArticle.setText(item.articles);
        mSharing.setText(item.notes);
        mCollection.setText(item.bookmarkCount);
        mTagLayout.setUserPagerData(item.activeTags);
    }


    @OnClick({R.id.id_card_dynamic})
    public void onCall(View v) {
        switch (v.getId()) {
            case R.id.id_card_dynamic:
                if(TextUtils.isEmpty(mUserId)) {
                    ToastUtils.showMessage(getContext(),R.string.data_request_error);
                    return;
                }

                TimeLineActivity.start(getActivity(),mUserId);

                break;




        }






    }


    @Override
    public void loadUserPagerError() {
        ToastUtils.showMessage(getActivity(),R.string.data_request_error);
    }
}
