package com.micro.mysegmentdefault.middle;

import android.content.Context;

import com.micro.mysegmentdefault.base.mvp.model.BaseModel;
import com.micro.mysegmentdefault.base.mvp.presenter.BasePresenter;
import com.micro.mysegmentdefault.base.mvp.view.BaseView;
import com.micro.mysegmentdefault.entity.BaseDataEntity;
import com.micro.mysegmentdefault.entity.BestTag;
import com.micro.mysegmentdefault.entity.TagUploadDataEntity;
import com.micro.mysegmentdefault.entity.TagUploadOtherDataEntity;
import com.micro.mysegmentdefault.entity.UserDetailDataEntity;

import java.util.List;

import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/2 - 16:07 <p>
 * interface :
 */

public interface UserEditContract {

    interface AbsUserEditView extends BaseView{
      void loadUserDetailInfo(UserDetailDataEntity entity);
      void loadUserDetailError();
      Context getContext();

      void uploadUserIconInfo(boolean result,String imagePath);

      void uploadUserProfile(boolean result,String profileName , String profileContent , String message);

      void uploadUserTags(boolean result , List<BestTag> tagList);

      void uploadUserOtherInfo(boolean result ,String tagType, List<TagUploadOtherDataEntity.Item> datas,boolean isUpdate, int sort);

      void removeUserOtherInfo(boolean result , String tagType , int sort);
    }

    interface AbsUserEditModel extends BaseModel {

        Observable<UserDetailDataEntity> loadUserDetailInfo(String userName);

        Observable<BaseDataEntity> uploadUserIconInfo(String filePath);

        Observable<BaseDataEntity> uploadUserProfile(String profileName , String profileContent);

        Observable<TagUploadDataEntity> uploadUserTags(List<BestTag> tagList);

        Observable<TagUploadOtherDataEntity> uploadUserOtherInfo(String tagType , String title1 , String content1, String title2 , String content2,boolean isUpdate, int sort);

        Observable<BaseDataEntity> removeUserOtherInfo(String tagType,int sort);

    }


    abstract class AbsUserEditPresenter extends BasePresenter<AbsUserEditView,AbsUserEditModel> {

        public abstract void loadUserDetailInfo(String userName) ;

        public abstract void uploadUserIconInfo(String filePath) ;

        public abstract void uploadUserProfile(String profileName , String profileContent);

        public abstract void uploadUserTags(List<BestTag> tagsList);

        public abstract void uploadUserOtherInfo(String tagType , String title1 , String content1, String title2 , String content2,boolean isUpdate, int sort);

        public abstract void removeUserOtherInfo(String tagType, int sort);

    }


}
