package com.micro.mysegmentdefault.middleimpl.subfragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.module.BaseFragment;
import com.micro.mysegmentdefault.entity.UserRecordEntity;
import com.micro.mysegmentdefault.middle.UserRecordContract;
import com.micro.mysegmentdefault.middleimpl.mvp.model.UserRecordModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.UserRecordPresenter;
import com.micro.mysegmentdefault.utils.CommonUtils;
import com.micro.mysegmentdefault.utils.ToastUtils;
import com.micro.mysegmentdefault.view.widget.TagContainerLayout;

import java.util.List;

import butterknife.Bind;

/**
 * author : micro_hx <p>
 * desc : 用户档案主页 <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/19 - 15:18 <p>
 * interface :
 */

public class UserRecordFragment extends BaseFragment<UserRecordPresenter, UserRecordModel> implements UserRecordContract.AbsUserRecordView {

    private String mUserName;

    @Bind(R.id.id_tv_city)
    TextView mTvCity;

    @Bind(R.id.id_tv_edu_background)
    TextView mEduBackground;

    @Bind(R.id.id_tv_jobs)
    TextView mTvJobs;

    @Bind(R.id.id_tv_skill)
    TextView mTvSkill;

    @Bind(R.id.id_layout_skill)
    TagContainerLayout mLayoutSkill;

    @Bind(R.id.id_layout_project)
    LinearLayout mLayoutProject;

    @Bind(R.id.id_layout_study)
    LinearLayout mLayoutStudy;

    @Bind(R.id.id_layout_work)
    LinearLayout mLayoutWork;

    @Bind(R.id.id_layout_social)
    LinearLayout mLayoutSocial;

    @Override
    protected void initOnCreateMethod() {
        mUserName = getArguments().getString("userName");
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this, mModel);
        mPresenter.loadingUserRecordInfo(mUserName);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_user_record_layout;
    }


    @Override
    public void loadUserRecordInfo(UserRecordEntity.UserRecordItem data) {
        if (!TextUtils.isEmpty(data.cityName)) {
            mTvCity.setText(data.cityName);
        } else {
            mTvCity.setText(R.string.str_user_no_msg_record);
        }

        if (CommonUtils.collectionIsNull(data.latestSchool)) {
            mEduBackground.setText(R.string.str_user_no_msg_record);
        } else {
            mEduBackground.setText(CommonUtils.convertList2String(data.latestSchool));
        }

        if (CommonUtils.collectionIsNull(data.latestCompany)) {
            mTvJobs.setText(R.string.str_user_no_msg_record);
        } else {
            mTvJobs.setText(CommonUtils.convertList2String(data.latestCompany));
        }

        if (CommonUtils.collectionIsNull(data.bestTags)) {
            mTvSkill.setVisibility(View.VISIBLE);
            mLayoutSkill.setVisibility(View.GONE);
        } else {
            mTvSkill.setVisibility(View.GONE);
            mLayoutSkill.setVisibility(View.VISIBLE);
            mLayoutSkill.setUserBestTags(data.bestTags);
        }

        if (!CommonUtils.collectionIsNull(data.profile.projects)) {
            addProjectData(mLayoutProject, data.profile.projects);
        }

        if (!CommonUtils.collectionIsNull(data.profile.schools)) {
            addSchoolData(mLayoutStudy, data.profile.schools);
        }

        if (!CommonUtils.collectionIsNull(data.profile.companies)) {
            addCompanyData(mLayoutWork, data.profile.companies);
        }

        if(!CommonUtils.collectionIsNull(data.publicBindings)) {
            addPublicBinds(mLayoutSocial,data.publicBindings);
        }

    }

    private void addPublicBinds(LinearLayout mLayoutSocial, List<UserRecordEntity.PublicBinding> publicBindings) {
        mLayoutSocial.removeAllViews();
        ImageView iv ;
        for(UserRecordEntity.PublicBinding pb : publicBindings) {
            iv = new ImageView(getContext());
            iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
            setImages(iv,pb.type);
            //TODO add goto the url... 05/21
            mLayoutSocial.addView(iv,getSocialParamLayout());
        }
    }

    private void addCompanyData(LinearLayout mLayoutWork, List<UserRecordEntity.Company> companies) {
        mLayoutWork.removeAllViews();
        for (int i = 0; i < companies.size(); i++) {
            UserRecordEntity.Company company = companies.get(i);
            addViews(mLayoutWork, company.name, company.role, i == companies.size() - 1);
        }
    }


    @Override
    public void loadUserRecordInfoError() {
        ToastUtils.showMessage(getActivity(), R.string.data_request_error);
    }

    private void addSchoolData(LinearLayout mLayoutStudy, List<UserRecordEntity.School> schools) {
        mLayoutStudy.removeAllViews();
        for (int i = 0; i < schools.size(); i++) {
            UserRecordEntity.School school = schools.get(i);
            addViews(mLayoutStudy, school.name, school.department,  i == schools.size() - 1);
        }
    }

    private void addProjectData(LinearLayout mLayoutProject, List<UserRecordEntity.Project> projects) {
        mLayoutProject.removeAllViews();
        for (int i = 0; i < projects.size(); i++) {
            UserRecordEntity.Project p = projects.get(i);
            addViews(mLayoutProject, p.name, p.role, i == projects.size() - 1);
        }
    }


    private void addViews(ViewGroup rootView, String mainTitleStr, String subTitleStr, boolean isLastOne) {
        View targetView = LayoutInflater.from(getContext()).inflate(R.layout.user_record_item_layout, mLayoutProject, false);
        TextView mainTitle = (TextView) targetView.findViewById(R.id.id_tv_main_title);
        TextView subTitle = (TextView) targetView.findViewById(R.id.id_tv_sub_title);

        mainTitle.setText(mainTitleStr);
        subTitle.setText(subTitleStr);
        rootView.addView(targetView);

        if (!isLastOne) {
            View backView = new View(getContext());
            backView.setBackgroundResource(R.color.text_first_color);
            rootView.addView(backView, getBackLineParamLayout());
        }
    }


    private LinearLayout.LayoutParams getBackLineParamLayout() {
        return new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1);
    }


    private LinearLayout.LayoutParams mSocialParams ;
    private LinearLayout.LayoutParams getSocialParamLayout() {
        if(null == mSocialParams) {
            int size = CommonUtils.dip2px(getContext(),24);
            mSocialParams = new LinearLayout.LayoutParams(size,size);
            mSocialParams.setMargins(10,5,10,5);
        }
        return mSocialParams;
    }

    private void setImages(ImageView iv, String type) {
        if("github".equals(type)){
            iv.setImageResource(R.drawable.sns_github);
        }else if("facebook".equals(type)) {
            iv.setImageResource(R.drawable.sns_facebook);
        }else if("google".equals(type)){
            iv.setImageResource(R.drawable.sns_google);
        }else if("qq".equals(type)) {
            iv.setImageResource(R.drawable.sns_qq);
        }else if("wechat".equals(type)){
            iv.setImageResource(R.drawable.sns_wechat);
        }else if("douban".equals(type)) {
            iv.setImageResource(R.drawable.sns_douban);
        }else if("weibo".equals(type)) {
            iv.setImageResource(R.drawable.sns_weibo);
        }
    }


}
