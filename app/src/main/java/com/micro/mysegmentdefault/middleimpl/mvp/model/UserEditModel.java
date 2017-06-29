package com.micro.mysegmentdefault.middleimpl.mvp.model;

import com.micro.mysegmentdefault.entity.BaseDataEntity;
import com.micro.mysegmentdefault.entity.BestTag;
import com.micro.mysegmentdefault.entity.TagUploadDataEntity;
import com.micro.mysegmentdefault.entity.TagUploadOtherDataEntity;
import com.micro.mysegmentdefault.entity.UserDetailDataEntity;
import com.micro.mysegmentdefault.logic.UserLogic;
import com.micro.mysegmentdefault.middle.UserEditContract;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.network.RxSchedulers;
import com.micro.mysegmentdefault.utils.CommonUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/2 - 16:13 <p>
 * interface :
 */

public class UserEditModel implements UserEditContract.AbsUserEditModel {


    @Override
    public Observable<UserDetailDataEntity> loadUserDetailInfo(String userName) {
        return Api.
                getApiService(0).
                loadUserDetailDataEntity(userName, UserLogic.getUserToken(),"all").
                compose(RxSchedulers.<UserDetailDataEntity>io_main());
    }


    @Override
    public Observable<BaseDataEntity> uploadUserIconInfo(String filePath) {
        Map<String, RequestBody> fileMap = new HashMap<>();
        File tempFile = new File(filePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), tempFile);
        fileMap.put("avatar"+ "\"; filename=\"" + tempFile.getName() + "", requestBody);
        fileMap.put("token",RequestBody.create(MediaType.parse("text/plain"), UserLogic.getUserToken()));

        return Api.getApiService(0).uploadUserIconDataEntity(fileMap).compose(RxSchedulers.<BaseDataEntity>io_main());
    }

    @Override
    public Observable<BaseDataEntity> uploadUserProfile(String profileName, String profileContent) {
        //name
        if("name".equals(profileName)) {
            return Api.getApiService(0).uploadUserNameDataEntity(profileContent,UserLogic.getUserToken()).compose(RxSchedulers.<BaseDataEntity>io_main());
        }else if("gender".equals(profileName)) {
            return Api.getApiService(0).uploadUserGenderDataEntity(profileContent,UserLogic.getUserToken()).compose(RxSchedulers.<BaseDataEntity>io_main());
        }else if("homepage".equals(profileName)) {
            return Api.getApiService(0).uploadUserHomePageDataEntity(profileContent,UserLogic.getUserToken()).compose(RxSchedulers.<BaseDataEntity>io_main());
        }else if("description".equals(profileName)) {
            return Api.getApiService(0).uploadUserDescDataEntity(profileContent,UserLogic.getUserToken()).compose(RxSchedulers.<BaseDataEntity>io_main());
        }else if("city".equals(profileName)) {
            return Api.getApiService(0).uploadUserCityDataEntity(profileContent,UserLogic.getUserToken()).compose(RxSchedulers.<BaseDataEntity>io_main());
        }

        return null ;
    }


    @Override
    public Observable<TagUploadDataEntity> uploadUserTags(List<BestTag> tagList) {
        Map<String,String> tempMap = new HashMap<>();
        tempMap.put("token",UserLogic.getUserToken());

        if(!CommonUtils.collectionIsNull(tagList)) {
            int index = 0 ;
            for(BestTag tag : tagList) {
                tempMap.put("tags["+String.valueOf(index++)+"]",tag.id);
            }
        }

        return Api.getApiService(0).uploadUserTagDataEntity(tempMap).compose(RxSchedulers.<TagUploadDataEntity>io_main());
    }


    @Override
    public Observable<TagUploadOtherDataEntity> uploadUserOtherInfo(String tagType, String title1, String content1, String title2, String content2,boolean isUpdate,int sort) {
        Map<String,String> tempMap = new HashMap<>();
        tempMap.put(title1,content1);
        tempMap.put(title2,content2);
        tempMap.put("sort",String.valueOf(sort));
        tempMap.put("token",UserLogic.getUserToken());

        return Api.
                getApiService(0).
                uploadOtherUserTagDataEntity(tagType, isUpdate? "set":"add" , tempMap).
                compose(RxSchedulers.<TagUploadOtherDataEntity>io_main());
    }

    @Override
    public Observable<BaseDataEntity> removeUserOtherInfo(String tagType, int sort) {
        return Api.
                getApiService(0).
                removeOtherUserInfoDataEntity(tagType,String.valueOf(sort),UserLogic.getUserToken()).
                compose(RxSchedulers.<BaseDataEntity>io_main());
    }


}
