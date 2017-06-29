package com.micro.mysegmentdefault.middleimpl.mvp.presenter;

import com.micro.mysegmentdefault.entity.TagDetailDataEntity;
import com.micro.mysegmentdefault.middle.TagDetailContract;
import com.micro.mysegmentdefault.utils.LogUtils;

import rx.functions.Action1;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/9 - 15:54 <p>
 * interface :
 */

public class TagDetailPresenter extends TagDetailContract.Presenter {

    @Override
    public void getTagDetailInfo(String tagName) {
        mModel.getTagDetailInfo(tagName).subscribe(new Action1<TagDetailDataEntity>() {
            @Override
            public void call(TagDetailDataEntity o) {
                mView.onDataSuccess(o);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LogUtils.d("TagDetail error : " + throwable);
                mView.onDataError();
            }
        });
    }


    @Override
    public void followTags(String tagId) {
        mModel.followTagStatus(tagId).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean result) {
                mView.onFollowTagStatus(result);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LogUtils.d("--->>followTags error--->>" + throwable);
                mView.onFollowTagStatus(false);
            }
        });
    }

    @Override
    public void cancelFollowTags(String tagId) {
        mModel.cancelFollowTagStatus(tagId).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean result) {
                mView.onCancelFollowTagStatus(result);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LogUtils.d("---cancelFollowTags error---" + throwable);
                mView.onCancelFollowTagStatus(false);
            }
        });

    }
}
