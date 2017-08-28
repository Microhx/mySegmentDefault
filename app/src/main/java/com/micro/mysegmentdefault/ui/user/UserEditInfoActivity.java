package com.micro.mysegmentdefault.ui.user;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.micro.mysegmentdefault.R;
import com.micro.mysegmentdefault.base.module.BaseActivity;
import com.micro.mysegmentdefault.entity.BestTag;
import com.micro.mysegmentdefault.entity.TagUploadOtherDataEntity;
import com.micro.mysegmentdefault.entity.UserDetailDataEntity;
import com.micro.mysegmentdefault.logic.UserLogic;
import com.micro.mysegmentdefault.middle.UserEditContract;
import com.micro.mysegmentdefault.middleimpl.fragment.PictureChooseDialog;
import com.micro.mysegmentdefault.middleimpl.fragment.SexChooseDialog;
import com.micro.mysegmentdefault.middleimpl.mvp.model.UserEditModel;
import com.micro.mysegmentdefault.middleimpl.mvp.presenter.UserEditPresenter;
import com.micro.mysegmentdefault.ui.LocationChooseActivity;
import com.micro.mysegmentdefault.utils.CommonUtils;
import com.micro.mysegmentdefault.utils.FileUtils;
import com.micro.mysegmentdefault.utils.ImageUtils;
import com.micro.mysegmentdefault.utils.LogUtils;
import com.micro.mysegmentdefault.utils.PermissionUtils;
import com.micro.mysegmentdefault.view.widget.CircleImageView;
import com.micro.mysegmentdefault.view.widget.TagContainerLayout;
import com.micro.mysegmentdefault.view.widget.TagListLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class UserEditInfoActivity extends BaseActivity<UserEditPresenter, UserEditModel> implements PictureChooseDialog.PictureChooseInterface, UserEditContract.AbsUserEditView, SexChooseDialog.OnSexChooseInterface, TagListLayout.onItemClickListener {

    @Bind(R.id.id_iv_user_icon)
    CircleImageView mUserIcon;

    @Bind(R.id.id_tv_username)
    TextView mTvUserName;

    @Bind(R.id.id_tv_website)
    TextView mTvWebSite;

    @Bind(R.id.id_tv_sex)
    TextView mTvSex;

    @Bind(R.id.id_tv_city)
    TextView mTvCity;

    @Bind(R.id.id_tv_intro)
    TextView mTvIntro;

    @Bind(R.id.id_tag_container)
    TagContainerLayout mTagContainer;

    @Bind(R.id.id_layout_project)
    TagListLayout mTagProjectLayout;

    @Bind(R.id.id_layout_study)
    TagListLayout mTagStudyLayout;

    @Bind(R.id.id_layout_work)
    TagListLayout mTagWorkLayout;

    private static final int CODE_CHOOSE_IMAGE_FROM_ALBUM = 1;
    private static final int CODE_CHOOSE_IMAGE_FROM_CAMERA = 2;
    private static final int CODE_CHOOSE_IMAGE_CROP = 3;

    //选择城市
    private static final int CODE_CHOOSE_CITY = 5;

    //添加技能
    private static final int CODE_FOR_ADD_SKILL = 4;

    private static final int PERMISSION_READ_EXTERNAL = 1;
    private static final int PERMISSION_OTHER_EXTERNAL = 2;

    private PictureChooseDialog mPictureDialog;
    private SexChooseDialog mSexChooseDialog;

    //用户裁剪图片地址
    private String mCropImagePath;
    private File mImageSourceFile;

    //选择的城市名称
    private String mCityName;

    private UserDetailDataEntity.Data mUserData;

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this, mModel);
        mPresenter.loadUserDetailInfo(UserLogic.getUserName());

        mTagProjectLayout.setOnItemClickListener(this);
        mTagStudyLayout.setOnItemClickListener(this);
        mTagWorkLayout.setOnItemClickListener(this);
    }


    @OnClick(R.id.id_layout_user_icon)
    public void onChangeUserIcon(View c) {
        if (null == mPictureDialog) {
            mPictureDialog = new PictureChooseDialog();
            mPictureDialog.setInterface(this);
        }
        mPictureDialog.show(getSupportFragmentManager(), "picture");
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_user_edit_info;
    }

    @Override
    public void onPictureChoose(int position) {
        if (position == 0) { //从相册选取
            //1.检查权限
            if (PermissionUtils.checkPermissionAuthorized(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                pickPicture();
                return;
            }
            PermissionUtils.requestPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE, PERMISSION_READ_EXTERNAL, null);
        } else if (position == 1) {
            if (PermissionUtils.checkPermissionAuthorized(this, Manifest.permission.READ_EXTERNAL_STORAGE) &&
                    PermissionUtils.checkPermissionAuthorized(this, Manifest.permission.CAMERA)) {
                takePicture();
                return;
            }

            PermissionUtils.requestPermission(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                    PERMISSION_OTHER_EXTERNAL, null);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_READ_EXTERNAL && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            pickPicture();
        } else if (requestCode == PERMISSION_OTHER_EXTERNAL && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            takePicture();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        LogUtils.d("requestCode--->>" + requestCode + "---resultCode---->>" + resultCode + "---" );

        if (resultCode != RESULT_OK) return;

        switch (requestCode) {
            case CODE_CHOOSE_IMAGE_FROM_ALBUM:
                if (null != data) {
                    Uri inputFileUri = data.getData();
                    File tempFile = FileUtils.createTempFile();
                    if (null == tempFile) {
                        showToast(R.string.str_temp_file_error);
                        return;
                    }

                    mCropImagePath = tempFile.getAbsolutePath();
                    cropImage(inputFileUri, Uri.fromFile(tempFile));
                }


                break;

            case CODE_CHOOSE_IMAGE_FROM_CAMERA:
                File tempFile = FileUtils.createTempFile();
                if (null == tempFile) {
                    showToast(R.string.str_temp_file_error);
                    return;
                }

                mCropImagePath = tempFile.getAbsolutePath();
                cropImage(Uri.fromFile(mImageSourceFile), Uri.fromFile(tempFile));

                break;


            case CODE_CHOOSE_IMAGE_CROP:
                LogUtils.d("the cropImage is : " + mCropImagePath);
                mPresenter.uploadUserIconInfo(mCropImagePath);

                break;

            case CODE_FOR_ADD_SKILL:
                List<BestTag> _tempTag = data.getParcelableArrayListExtra("tags");
                mPresenter.uploadUserTags(_tempTag);
                break;


            case UserBaseInfoEditActivity.TYPE_NAME:
                String content = data.getStringExtra("content");
                if (!TextUtils.isEmpty(content)) {
                    if (!mTvUserName.getText().toString().equals(content)) {
                        //upload load
                        mPresenter.uploadUserProfile("name", content);
                    }
                } else {
                    showToast(R.string.str_name_must);
                }
                break;

            case UserBaseInfoEditActivity.TYPE_WEBSITE:
                String website = data.getStringExtra("content");
                mPresenter.uploadUserProfile("homepage", website);
                break;

            case UserBaseInfoEditActivity.TYPE_INTRODUCE:
                String description = data.getStringExtra("content");
                mPresenter.uploadUserProfile("description", description);
                break;

            case CODE_CHOOSE_CITY:
                String cityId = data.getStringExtra("cityId");
                mCityName = data.getStringExtra("cityName");
                mPresenter.uploadUserProfile("city", cityId);
                break;

            case UserEditMultipleActivity.TYPE_PROJECT:
                int sort1 = data.getIntExtra("_sort", -1);
                boolean isDelete = data.getBooleanExtra("_isDelete",false);

                if (isDelete) {
                    mPresenter.removeUserOtherInfo("project", sort1);
                } else {
                    String name1 = data.getStringExtra("_title");
                    String description1 = data.getStringExtra("_content");
                    boolean isUpdate = data.getBooleanExtra("_isUpdate", false);

                    if (!TextUtils.isEmpty(name1) && !TextUtils.isEmpty(description1)) {
                        mPresenter.uploadUserOtherInfo("project", "name", name1, "description", description1, isUpdate, sort1);
                    }
                }

                break;

            case UserEditMultipleActivity.TYPE_STUDY:
                int sort2 = data.getIntExtra("_sort", -1);
                boolean isDelete2 = data.getBooleanExtra("_isDelete" , false);

                if (isDelete2) {
                    mPresenter.removeUserOtherInfo("school", sort2);
                } else {
                    boolean isUpdate2 = data.getBooleanExtra("_isUpdate", false);
                    String name2 = data.getStringExtra("_title");
                    String department = data.getStringExtra("_content");
                    if (!TextUtils.isEmpty(name2) && !TextUtils.isEmpty(department)) {
                        mPresenter.uploadUserOtherInfo("school", "name", name2, "department", department, isUpdate2, sort2);
                    }
                }

                break;

            case UserEditMultipleActivity.TYPE_WORK:
                int sort3 = data.getIntExtra("_sort", -1);
                boolean isDelete3 = data.getBooleanExtra("_isDelete" , false);

                if (isDelete3) {
                    mPresenter.removeUserOtherInfo("company", sort3);
                } else {
                    boolean isUpdate3 = data.getBooleanExtra("_isUpdate", false);
                    String name3 = data.getStringExtra("_title");
                    String role = data.getStringExtra("_content");

                    if (!TextUtils.isEmpty(name3) && !TextUtils.isEmpty(role)) {
                        mPresenter.uploadUserOtherInfo("company", "name", name3, "role", role, isUpdate3, sort3);
                    }
                }

                break;
        }

    }

    /**
     * 裁剪图片
     *
     * @param inputUri
     * @param outputUri
     */
    private void cropImage(Uri inputUri, Uri outputUri) {
        //the server has limited the size of the picture...
        //so I have to limit the picture , add the params outputX and outputY

        Intent localIntent = new Intent("com.android.camera.action.CROP");
        localIntent.setDataAndType(inputUri, "image/*");
        localIntent.putExtra("crop", "true");
        localIntent.putExtra("aspectX", 1);
        localIntent.putExtra("aspectY", 1);
        localIntent.putExtra("outputX", 200);
        localIntent.putExtra("outputY", 200);
        localIntent.putExtra("scale", true);
        localIntent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        localIntent.putExtra("return-data", false);
        localIntent.putExtra("noFaceDetection", false);
        localIntent.putExtra("output", outputUri);
        startActivityForResult(localIntent, CODE_CHOOSE_IMAGE_CROP);
    }


    private void pickPicture() {
        try {
            startActivityForResult(new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI), CODE_CHOOSE_IMAGE_FROM_ALBUM);
            return;
        } catch (ActivityNotFoundException localActivityNotFoundException) {
            LogUtils.d("ActivityNotFoundException");
            showToast(R.string.str_can_not_find_file);
            return;
        }
    }


    private void takePicture() {
        mImageSourceFile = FileUtils.createTempFile();
        if (null == mImageSourceFile) {
            showToast(R.string.str_temp_file_error);
            return;
        }

        Intent localIntent = new Intent("android.media.action.IMAGE_CAPTURE");
        localIntent.putExtra("output", Uri.fromFile(mImageSourceFile));
        startActivityForResult(localIntent, CODE_CHOOSE_IMAGE_FROM_CAMERA);
    }


    @OnClick({R.id.id_layout_name, R.id.id_layout_website, R.id.id_layout_intro,
            R.id.id_layout_sex, R.id.id_iv_back, R.id.id_layout_location,
            R.id.id_add_user_skill, R.id.id_tv_add_project, R.id.id_tv_add_study, R.id.id_tv_add_work})
    public void onEnterEditPage(View v) {
        switch (v.getId()) {
            case R.id.id_layout_name:
                UserBaseInfoEditActivity.start(this, UserBaseInfoEditActivity.TYPE_NAME, mTvUserName.getText().toString());
                break;

            case R.id.id_layout_website:
                UserBaseInfoEditActivity.start(this, UserBaseInfoEditActivity.TYPE_WEBSITE, mTvWebSite.getText().toString());

                break;

            case R.id.id_layout_intro:
                UserBaseInfoEditActivity.start(this, UserBaseInfoEditActivity.TYPE_INTRODUCE, mTvIntro.getText().toString());
                break;

            case R.id.id_layout_sex:
                showUserSex();
                break;

            case R.id.id_iv_back:
                finish();
                break;

            case R.id.id_layout_location:
                LocationChooseActivity.start(this, 0, "", "", CODE_CHOOSE_CITY);
                break;

            case R.id.id_add_user_skill:
                UserChooseTagActivity.start(this, mUserData.bestTags, CODE_FOR_ADD_SKILL);
                break;

            case R.id.id_tv_add_project:
                UserEditMultipleActivity.start(this, UserEditMultipleActivity.TYPE_PROJECT, "", "", false);
                break;

            case R.id.id_tv_add_study:
                UserEditMultipleActivity.start(this, UserEditMultipleActivity.TYPE_STUDY, "", "", false);
                break;

            case R.id.id_tv_add_work:
                UserEditMultipleActivity.start(this, UserEditMultipleActivity.TYPE_WORK, "", "", false);
                break;

        }
    }

    private void showUserSex() {
        if (null == mSexChooseDialog) {
            mSexChooseDialog = new SexChooseDialog();
            mSexChooseDialog.setSexChooseListener(this);
        }

        int sexValue = CommonUtils.safeParseInt(mTvSex.getTag() + "");
        Bundle bundle = new Bundle();
        bundle.putInt("sex", sexValue);
        mSexChooseDialog.setArguments(bundle);
        mSexChooseDialog.show(getSupportFragmentManager(), "sex");
    }

    @Override
    public void onSexChoose(int position) {
        int oldSexValue = CommonUtils.safeParseInt(String.valueOf(mTvSex.getTag()));
        if (oldSexValue == position) return;

        mTvSex.setText(getSexValue(position));
        mTvSex.setTag(String.valueOf(position));

        mPresenter.uploadUserProfile("gender", String.valueOf(position));
    }

    @Override
    public void loadUserDetailInfo(UserDetailDataEntity entity) {
        mUserData = entity.data;
        ImageUtils.showUrlImage(mUserData.avatarUrl, mUserIcon);
        mTvUserName.setText(mUserData.name);
        mTvSex.setText(getSexValue(CommonUtils.safeParseInt(mUserData.profileGender)));
        mTvSex.setTag(mUserData.profileGender);

        mTvWebSite.setText(mUserData.site);
        mTvIntro.setText(mUserData.description);
        mTvCity.setText(mUserData.cityName);

        mTagContainer.setUserBestTags(mUserData.bestTags);

        mTagProjectLayout.addDataList(mUserData.projects, 0);
        mTagStudyLayout.addDataList(mUserData.schools, 1);
        mTagWorkLayout.addDataList(mUserData.companies, 2);

    }

    @Override
    public void loadUserDetailError() {
        showToast(R.string.str_operation_error);
    }

    @Override
    public Context getContext() {
        return this;
    }

    private int getSexValue(int gender) {
        if (gender == 0) return R.string.sex_secret;
        if (gender == 1) return R.string.sex_male;
        return R.string.sex_female;
    }


    @Override
    public void uploadUserIconInfo(boolean result, String imagePath) {
        if (result) {
            ImageUtils.showUrlImage(imagePath, mUserIcon);
        } else {
            showToast(R.string.str_operation_error);
        }
    }

    @Override
    public void uploadUserProfile(boolean result, String profileName, String profileContent, String message) {
        if (result) {
            switch (profileName) {
                case "name":
                    mTvUserName.setText(profileContent);
                    break;

                case "gender":
                    mTvSex.setText(getSexValue(CommonUtils.safeParseInt(profileContent)));
                    break;

                case "homepage":
                    mTvWebSite.setText(profileContent);
                    break;

                case "description":
                    mTvIntro.setText(profileContent);
                    break;

                case "city":
                    mTvCity.setText(mCityName);
                    break;
            }

        } else {
            if (!TextUtils.isEmpty(message)) {
                showToast(message);
                return;
            }

            showToast(R.string.str_operation_error);
        }
    }

    @Override
    public void uploadUserTags(boolean result, List<BestTag> tagList) {
        if (result) {
            mUserData.bestTags = (ArrayList<BestTag>) tagList;
            mTagContainer.setUserBestTags(tagList);
        } else {
            showToast(R.string.str_operation_error);
        }
    }


    @Override
    public void uploadUserOtherInfo(boolean result, String tagType, TagUploadOtherDataEntity datas, boolean isUpdate, int sort) {
        if (result) {
            List<TagUploadOtherDataEntity.Item> tempData = datas.data;
            switch (tagType) {
                    case "project":
                        mTagProjectLayout.addDataList2(tempData, 0);
                        break;

                    case "school":
                        mTagStudyLayout.addDataList2(tempData, 1);
                        break;

                    case "company":
                        mTagWorkLayout.addDataList2(tempData, 2);
                        break;
                }
        } else {

            if(null != datas && !TextUtils.isEmpty(datas.message)){
                showToast(datas.message);
                return;
            }

            showToast(R.string.str_operation_error);
        }
    }

    //-------------------------------------------------------------------->>

    @Override
    public void removeUserOtherInfo(boolean result, String tagType, int sort) {
        LogUtils.d(result + "--------------->>" + tagType + "-----------" + sort);

        if (result) {
            switch (tagType) {
                case "project":
                    mTagProjectLayout.removeViewAt(sort);
                    break;

                case "school":
                    mTagStudyLayout.removeViewAt(sort);
                    break;

                case "company":
                    mTagWorkLayout.removeViewAt(sort);
                    break;
            }
        } else {
            showToast(R.string.str_operation_error);
        }


    }

    @Override
    public void itemClick(int type, int sort, String title, String content) {
        LogUtils.d(type + "----" + sort + "---" + title + "-----" + content + "------>>>" + sort);
        int targetType = getTargetType(type);
        UserEditMultipleActivity.start(this, targetType, title, content, true, sort);
    }

    private int getTargetType(int type) {
        if (type == 0) return UserEditMultipleActivity.TYPE_PROJECT;
        if (type == 1) return UserEditMultipleActivity.TYPE_STUDY;
        return UserEditMultipleActivity.TYPE_WORK;
    }
}

