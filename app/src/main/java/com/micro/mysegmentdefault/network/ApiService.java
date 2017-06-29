package com.micro.mysegmentdefault.network;

import com.micro.mysegmentdefault.entity.AccountDataEntity;
import com.micro.mysegmentdefault.entity.ActionDataEntity;
import com.micro.mysegmentdefault.entity.ArticleDetailEntity;
import com.micro.mysegmentdefault.entity.BaseDataEntity;
import com.micro.mysegmentdefault.entity.CommonResult;
import com.micro.mysegmentdefault.entity.DiscoverDataEntity;
import com.micro.mysegmentdefault.entity.FollowDataEntity;
import com.micro.mysegmentdefault.entity.HomeDataEntity;
import com.micro.mysegmentdefault.entity.LocationDataEntity;
import com.micro.mysegmentdefault.entity.MessageDataEntity;
import com.micro.mysegmentdefault.entity.NewCollectionDataEntity;
import com.micro.mysegmentdefault.entity.NewsCommentDataEntity;
import com.micro.mysegmentdefault.entity.NewsDataEntity;
import com.micro.mysegmentdefault.entity.NewsDetailDataEntity;
import com.micro.mysegmentdefault.entity.NoteDataEntity;
import com.micro.mysegmentdefault.entity.NoteDetailDataEntity;
import com.micro.mysegmentdefault.entity.OnlyData;
import com.micro.mysegmentdefault.entity.PushMessageDataEntity;
import com.micro.mysegmentdefault.entity.QuestionDetailDataEntity;
import com.micro.mysegmentdefault.entity.SocialAccountBindDataEntity;
import com.micro.mysegmentdefault.entity.TagDataEntity;
import com.micro.mysegmentdefault.entity.TagDetailArticleEntity;
import com.micro.mysegmentdefault.entity.TagDetailDataEntity;
import com.micro.mysegmentdefault.entity.TagDetailQuestionEntity;
import com.micro.mysegmentdefault.entity.TagDetailUserEntity;
import com.micro.mysegmentdefault.entity.TagUploadDataEntity;
import com.micro.mysegmentdefault.entity.TagUploadOtherDataEntity;
import com.micro.mysegmentdefault.entity.ThirdPlatformDataEntity;
import com.micro.mysegmentdefault.entity.TopUserEntity;
import com.micro.mysegmentdefault.entity.UpdatePasswordEntity;
import com.micro.mysegmentdefault.entity.UserAttentionPersonDataEntity;
import com.micro.mysegmentdefault.entity.UserAttentionTagDataEntity;
import com.micro.mysegmentdefault.entity.UserCollectEntity;
import com.micro.mysegmentdefault.entity.UserCollectionDetailDataEntity;
import com.micro.mysegmentdefault.entity.UserDataEntity;
import com.micro.mysegmentdefault.entity.UserDetailDataEntity;
import com.micro.mysegmentdefault.entity.UserPageEntity;
import com.micro.mysegmentdefault.entity.UserPrivateEventDataEntity;
import com.micro.mysegmentdefault.entity.UserPrivateEventDetailDataEntity;
import com.micro.mysegmentdefault.entity.UserRecordEntity;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/6 - 16:59 <p>
 * interface :
 */

public interface ApiService {

    /**
     * https://api.segmentfault.com/news/rank?token=d9e5ecb7b0aa6a77fc2ccf7eaf7f04f3_b32715522c272e2093e9ca34f34ce8c5&page=1&channel=frontend
     *
     * @param token
     * @param channel
     * @param page
     * @return
     */

    @GET("news/{path}")
    Observable<HomeDataEntity> getHomeDataEntityList(
                                                     @Path(("path")) String path,
                                                     @Query("token") String token,
                                                     @Query("channel") String channel,
                                                     @Query("page") int page);

    //https://api.segmentfault.com/article/newest?page=1
    @GET("article/{path}")
    Observable<NewsDataEntity> getNewsBaseDataEntityList(@Path("path") String path, @Query("page") String page);

    //https://api.segmentfault.com/article/tagged/1040000000089449?page=1
    @GET("article/tagged/{path}")
    Observable<NewsDataEntity> getNewsExtendsDataEntityList(@Path("path") String path, @Query("page") String page);

    //获取热门tag标签路径
    @GET("tag/hottest")
    Observable<TagDataEntity> getHotsTagDataEntityList();

    //https://api.segmentfault.com/search?q=java&page=1&type=tag

    @GET("search")
    Observable<TagDataEntity> getSearchTagDataEntityList(@Query("q") String q,
                                                         @Query("page") String page,
                                                         @Query("type") String type);

    //https://api.segmentfault.com/t/java
    @GET("t/{path}")
    Observable<TagDetailDataEntity> getTagDetailDataEntity(@Path("path") String path);

    //标签详情中问题
    @GET("/question/tagged/{path}")
    Observable<TagDetailQuestionEntity> getTagDetailQuestionEntity(@Path("path") String tagId,
                                                                   @Query("page") String page);


    //标签详情中问题
    @GET("/question/{path}")
    Observable<TagDetailQuestionEntity> getTagRecommendEntity(@Path("path") String tagId,
                                                              @Query("page") String page);  //recomment


    @GET("/article/tagged/{path}")
    Observable<TagDetailArticleEntity> getTagDetailArticleEntity(@Path("path") String tagId,
                                                                 @Query("page") String page);

    @GET("tag/{path}/topuser")
    Observable<TagDetailUserEntity> getTagDetailUserEntity(@Path("path") String tagId);


    @POST("tag/{tagId}/follow")
    Observable<CommonResult> getFollowTagResult(@Path("tagId") String tagId, @Query("token") String token);


    @POST("tag/{tagId}/follow/cancel")
    Observable<CommonResult> getCancelFollowTagResult(@Path("tagId") String tagId, @Query("token") String token);


    @GET("activity/recommendable")
    Observable<DiscoverDataEntity> getActivityDiscoverDataEntity() ;

    //头条详细内容
    @GET("news/{newsId}")
    Observable<NewsDetailDataEntity> getNewsDetailData(@Path("newsId") String newsId ,@Query("token") String token) ;

    //专栏
    @GET("article/{articleId}")
    Observable<ArticleDetailEntity> getArticleDetailData(@Path("articleId") String articleId , @Query("token") String token);

    //问答
    @GET("question/{questionId}")
    Observable<QuestionDetailDataEntity> getQuestionDetailData(@Path("questionId") String questionId , @Query("token") String token);

    //获取最新的Note
    @GET("note/newest")
    Observable<NoteDataEntity> getNoteListEntityData(@Query("page") String page);

    @GET("notes/me")
    Observable<NoteDataEntity> getNoteListEntityData(@Query("page") String page , @Query("token") String token);


    @GET("note/{noteId}")
    Observable<NoteDetailDataEntity> getNoteDetailData(@Path("noteId") String noteId ,@Query("token") String token) ;

    @POST("note/{noteId}/fork")
    Observable<NoteDetailDataEntity> userForkNoteData(@Path("noteId") String noteId , @Query("token") String token);


    @GET("user/timeline")
    Observable<ActionDataEntity> getActionDataEntity(@Query("token") String token , @Query("before") String before);

    //获取用户List列表
    @GET("user/list")
    Observable<TopUserEntity> getTopUserEntity() ;

    //u/qiukeren?type=base
    @GET("u/{username}")
    Observable<UserDataEntity> getUserZoneDataEntity(@Path("username") String username ,
                                                     @Query("type") String type,
                                                     @Query("token") String token);

    @GET("u/{username}")
    Observable<UserPageEntity> getUserPageDataEntity(@Path("username") String username ,
                                                     @Query("type") String type,
                                                     @Query("token") String token);

    //用户档案
    @GET("u/{username}")
    Observable<UserRecordEntity> getUserRecordDataEntity(@Path("username") String username ,
                                                         @Query("type") String type,
                                                         @Query("token") String token);

    //news/1210000009528444/like
    @POST("news/{newsId}/like")
    Observable<BaseDataEntity> userLikeNewsDataEntity(@Path("newsId") String newsId , @Query("token") String token);

    @POST("news/{newsId}/like/cancel")
    Observable<BaseDataEntity> userDisLikeNewsDataEntity(@Path("newsId") String newsId , @Query("token") String token);


    @GET("user/bookmarkArchives")
    Observable<UserCollectEntity> getUserCollectDataEntity(@Query("objectId") String objectId ,
                                                           @Query("page") String page , @Query("token") String token);

    @POST("{type}/{newsId}/bookmark")
    Observable<BaseDataEntity> addUserCollectionDataEntity(@Path("type") String tagType, @Path("newsId") String newsId ,
                                                           @QueryMap Map<String,String> map , @Query("token") String token);

    //https://api.segmentfault.com/bookmarkArchives/add
    @POST("bookmarkArchives/add")
    Observable<NewCollectionDataEntity> addUserNewCollectionDataEntity(@Query("name") String name , @Query("description") String desc,
                                                                       @Query("isPrivate") String isPrivate, @Query("token") String token);

    @GET("/news/{newsId}/comments")
    Observable<NewsCommentDataEntity> getUserCommentDataEntity(@Path("newsId") String newsId , @Query("page") String page , @Query("token") String token);


    //news/1210000009528444/like
    @POST("user/{userId}/follow")
    Observable<FollowDataEntity> userFollowDataEntity(@Path("userId") String userId , @Query("token") String token);

    @POST("user/{userId}/follow/cancel")
    Observable<FollowDataEntity> userCancelFollowDataEntity(@Path("userId") String userId , @Query("token") String token);


    @POST("question/{questionId}/follow")
    Observable<BaseDataEntity> userFollowQuestionDataEntity(@Path("questionId") String questionId , @Query("token") String token);

    @POST("question/{questionId}/follow/cancel")
    Observable<BaseDataEntity> userCancelFollowQuestionDataEntity(@Path("questionId") String questionId , @Query("token") String token);

    @POST("notes/add")
    Observable<NoteDetailDataEntity> addUserNewNoteDataEntity(@Query("title") String title ,      @Query("text") String content ,
                                                              @Query("language") String language , @Query("isPrivate") String isPrivate ,
                                                              @Query("token") String token);


    /**
     * 获取用户详细信息
     * @param userName
     * @param token
     * @return
     */
    @GET("/u/{userName}")
    Observable<UserDetailDataEntity> loadUserDetailDataEntity(@Path("userName") String userName ,
                                                              @Query("token") String token , @Query("type") String type);

    @GET("address/province")
    Observable<LocationDataEntity> loadAllProvincesDataEntity(@Query("token") String token);

    @GET("address/province/{cityCode}")
    Observable<LocationDataEntity> loadCityDataEntity(@Path("cityCode") String cityCode, @Query("token") String token);


    @POST("user/avatar/upload")
    @Multipart
    Observable<BaseDataEntity> uploadUserIconDataEntity(@PartMap Map<String,RequestBody> map);


    @POST("user/profile/edit")
    Observable<BaseDataEntity> uploadUserNameDataEntity(@Query("name") String name , @Query("token") String token);

    @POST("user/profile/edit")
    Observable<BaseDataEntity> uploadUserGenderDataEntity(@Query("gender") String gender , @Query("token") String token);

    @POST("user/profile/edit")
    Observable<BaseDataEntity> uploadUserHomePageDataEntity(@Query("homepage") String homepage , @Query("token") String token);

    @POST("user/profile/edit")
    Observable<BaseDataEntity> uploadUserDescDataEntity(@Query("description") String description , @Query("token") String token);

    @POST("user/profile/edit")
    Observable<BaseDataEntity> uploadUserCityDataEntity(@Query("city") String cityId , @Query("token") String token);

    @POST("user/profile/tag/set")
    Observable<TagUploadDataEntity> uploadUserTagDataEntity(@QueryMap Map<String,String> tagMap);

    @POST("user/profile/{tagType}/{actionType}")
    Observable<TagUploadOtherDataEntity> uploadOtherUserTagDataEntity(@Path("tagType") String tagType , @Path("actionType") String actionType, @QueryMap Map<String,String> paramMap);

    @POST("user/profile/{tagType}/remove")
    Observable<BaseDataEntity> removeOtherUserInfoDataEntity(@Path("tagType") String tagType , @Query("sort") String sort , @Query("token") String token);

    @GET("user/events")
    Observable<MessageDataEntity> getUserMessageEventDataEntity(@Query("type") String strType , @Query("token") String token, @Query("page") String page);

    @GET("user/events")
    Observable<UserPrivateEventDataEntity> getUserPrivateEventDataEntity(@Query("type") String strType , @Query("token") String token , @Query("page") String page);

    @GET("inbox/{eventId}")
    Observable<UserPrivateEventDetailDataEntity> getUserPrivateEventDetailDataEntity(@Path("eventId") String eventId , @Query("before") long beforeTime , @Query("token") String token);

    @POST("inbox/delete/{eventId}")
    Observable<BaseDataEntity> userDeletePrivateEventData(@Path("eventId") String eventId , @Query("token") String token);

    @GET("user/{uid}/users/following")
    Observable<UserAttentionPersonDataEntity> userAttentionDataEntity(@Path("uid") String uid , @Query("token") String token , @Query("page") int page);

    @GET("tag/following")
    Observable<UserAttentionTagDataEntity> userAttentionTagDataEntity(@Query("token") String token , @Query("page") int page);

    @GET("question/following")
    Observable<TagDetailQuestionEntity> userAttentionQuestionDataEntity(@Query("token") String token , @Query("page") int page);

    @GET("bookmarkArchive/{dataId}")
    Observable<UserCollectionDetailDataEntity> userCollectionDetailDataEntity(@Path("dataId") String dataId , @Query("token") String token , @Query("page") int page);

    @GET("settings/notify")
    Observable<PushMessageDataEntity> userPushMessageDataEntity(@Query("token") String token);

    @POST("settings/notify")
    Observable<PushMessageDataEntity> messagePushSetting(@QueryMap Map<String,String> params , @Query("token") String token);

    @GET("u/{userUrl}")
    Observable<AccountDataEntity> getAccountDataEntity(@Path("userUrl") String userUrl , @Query("token") String token , @Query("type") String type);

    @GET("settings/oauth")
    Observable<ThirdPlatformDataEntity> getThirdPlatformDataEntity(@Query("token") String token);

    @POST("settings/password")
    Observable<UpdatePasswordEntity> updateUserPassword(@Query("password") String password,
                                                        @Query("newPassword") String newPassword ,
                                                        @Query("confirmPassword") String confirmPassword,
                                                        @Query("token") String token);

    @GET("settings/binding")
    Observable<SocialAccountBindDataEntity> getSocialAccountBindDataEntity(@Query("token") String token);


    @POST("settings/oauth/{type}/{hideOrShow}")
    Observable<OnlyData> updateSocialAccountInfo(@Path("type") String type ,
                                                 @Path("hideOrShow") String hideOrShow,
                                                 @Query("token") String token);

    @POST("news/add")
    Observable<OnlyData> updateToutiao(@Query("url") String url,
                                       @Query("title") String title,
                                       @Query("type") String type ,
                                       @Query("description") String description,
                                       @Query("token") String token);
}
