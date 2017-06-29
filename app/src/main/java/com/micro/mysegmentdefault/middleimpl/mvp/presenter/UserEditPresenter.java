package com.micro.mysegmentdefault.middleimpl.mvp.presenter;

import com.micro.mysegmentdefault.entity.BaseDataEntity;
import com.micro.mysegmentdefault.entity.BestTag;
import com.micro.mysegmentdefault.entity.TagUploadDataEntity;
import com.micro.mysegmentdefault.entity.TagUploadOtherDataEntity;
import com.micro.mysegmentdefault.entity.UserDetailDataEntity;
import com.micro.mysegmentdefault.middle.UserEditContract;
import com.micro.mysegmentdefault.network.RxSubscriber;
import com.micro.mysegmentdefault.utils.LogUtils;

import java.util.List;

/**
 * author : micro_hx <p>
 * desc : 用户编辑信息 presenter <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/2 - 16:11 <p>
 * interface :
 */

public class UserEditPresenter extends UserEditContract.AbsUserEditPresenter {


    @Override
    public void loadUserDetailInfo(String userName) {
        mModel.loadUserDetailInfo(userName).subscribe(new RxSubscriber<UserDetailDataEntity>(mView.getContext(),"") {
            @Override
            public void _onNext(UserDetailDataEntity entity) {
                if(null != entity && entity.status == 0) {
                    mView.loadUserDetailInfo(entity);
                }else{
                    mView.loadUserDetailError();
                }
            }

            @Override
            public void _onError(Throwable t) {
                LogUtils.d("loadUserDetailInfo error : " + t);
                mView.loadUserDetailError();
            }
        }) ;


    }

    @Override
    public void uploadUserIconInfo(String filePath) {
        mModel.uploadUserIconInfo(filePath).
                subscribe(new RxSubscriber<BaseDataEntity>(mView.getContext(),"") {
            @Override
            public void _onNext(BaseDataEntity entity) {
                if(null != entity && entity.status == 0) {
                    mView.uploadUserIconInfo(true,entity.data);
                }else {
                    mView.uploadUserIconInfo(false,null);
                }
            }

            @Override
            public void _onError(Throwable t) {
                LogUtils.d("uploadUserIcon error:" + t);
                mView.uploadUserIconInfo(false,null);
            }
        });


    }

    @Override
    public void uploadUserProfile(final String profileName, final String profileContent) {
        mModel.uploadUserProfile(profileName,profileContent).subscribe(new RxSubscriber<BaseDataEntity>(mView.getContext(),"") {
            @Override
            public void _onNext(BaseDataEntity entity) {
                if(null != entity){
                    if(entity.status ==0) {
                        mView.uploadUserProfile(true,profileName,profileContent,null);
                    }else {
                        mView.uploadUserProfile(false,profileName,profileContent,entity.message);
                    }
                }else{
                    mView.uploadUserProfile(false,profileName,profileContent,null);
                }
            }

            @Override
            public void _onError(Throwable t) {
                LogUtils.d("uploadUserProfile error : " + t);
                mView.uploadUserProfile(false,profileName,profileContent,null);
            }
        }) ;
    }


    @Override
    public void uploadUserTags(List<BestTag> tagsList) {
        mModel.uploadUserTags(tagsList).subscribe(new RxSubscriber<TagUploadDataEntity>(mView.getContext(),"") {
            @Override
            public void _onNext(TagUploadDataEntity entity) {
                if(null != entity && entity.status == 0) {
                    mView.uploadUserTags(true,entity.data.tags);
                }else {
                    mView.uploadUserTags(false,null);
                }
            }

            @Override
            public void _onError(Throwable t) {
                LogUtils.d("uploadUserTag error : " + t );
                mView.uploadUserTags(false,null);
            }
        })    ;
    }

    @Override
    public void uploadUserOtherInfo(final String tagType, String title1, String content1, String title2, String content2,final boolean isUpdate,final int sort) {
        mModel.uploadUserOtherInfo(tagType,title1,content1,title2,content2,isUpdate,sort).
                subscribe(new RxSubscriber<TagUploadOtherDataEntity>(mView.getContext(),"") {
            @Override
            public void _onNext(TagUploadOtherDataEntity entity) {
                if(null != entity && entity.status == 0) {
                    mView.uploadUserOtherInfo(true,tagType,entity.data,isUpdate,sort);
                }else {
                    mView.uploadUserOtherInfo(false,tagType,null,isUpdate,sort);
                }
            }

            @Override
            public void _onError(Throwable t) {
                LogUtils.d("uploadUserOtherInfo error : " + t);
                mView.uploadUserOtherInfo(false,tagType,null,isUpdate,sort);
            }
        }) ;
    }

    @Override
    public void removeUserOtherInfo(final String tagType, final int sort) {
        mModel.removeUserOtherInfo(tagType,sort).
               subscribe(new RxSubscriber<BaseDataEntity>(mView.getContext(),"") {
            @Override
            public void _onNext(BaseDataEntity entity) {
                if(null != entity && entity.status == 0) {
                    mView.removeUserOtherInfo(true,tagType,sort);
                }else {
                    mView.removeUserOtherInfo(false,tagType,sort);
                }
            }

            @Override
            public void _onError(Throwable t) {
                LogUtils.d("removeUserOtherInfo error: " +t);
                mView.removeUserOtherInfo(false,tagType,sort);
            }
        }) ;
    }
}
