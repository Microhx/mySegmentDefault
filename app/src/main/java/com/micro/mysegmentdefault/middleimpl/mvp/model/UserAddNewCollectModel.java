package com.micro.mysegmentdefault.middleimpl.mvp.model;

import com.micro.mysegmentdefault.entity.BaseDataEntity;
import com.micro.mysegmentdefault.entity.NewCollectionDataEntity;
import com.micro.mysegmentdefault.middle.UserAddNewCollectContract;
import com.micro.mysegmentdefault.network.Api;
import com.micro.mysegmentdefault.network.RxSchedulers;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.functions.Func1;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/26 - 23:08 <p>
 * interface :
 */

public class UserAddNewCollectModel implements UserAddNewCollectContract.AbsNewCollectModel {

    @Override
    public Observable<NewCollectionDataEntity> addNewCollect(String title, String desc, boolean isPrivate, String token) {
        return Api.
                getApiService(0).
                addUserNewCollectionDataEntity(title,desc,isPrivate?"1":"0",token).
                compose(RxSchedulers.<NewCollectionDataEntity>io_main());
    }

    @Override
    public Observable<BaseDataEntity> updateNewCollect(String collectId, String title, String desc, boolean isPrivate, String token) {
        return Api.
                getApiService(0).
                updateUserCollect(collectId,title,desc,isPrivate?"1":"0",token).
                map(new Func1<ResponseBody, BaseDataEntity>() {
                    @Override
                    public BaseDataEntity call(ResponseBody s) {
                        String str = "" ;
                        try {
                             str = s.string();
                             s.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        BaseDataEntity entity = new BaseDataEntity();
                        try {
                            JSONObject object = new JSONObject(str);
                            entity.status = object.getInt("status");
                            entity.message = object.getString("message");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return entity;
                    }
                }).compose(RxSchedulers.<BaseDataEntity>io_main());
    }

}
