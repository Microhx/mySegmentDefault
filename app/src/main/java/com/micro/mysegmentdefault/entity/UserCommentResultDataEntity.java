package com.micro.mysegmentdefault.entity;

/**
 * author : micro_hx <p>
 * desc : 用户评论之后返回的数据<p>
 * email: javainstalling@163.com <p>
 * date : 2017/8/21 - 17:13 <p>
 * interface :
 */

public class UserCommentResultDataEntity {

    public int status ;
    public String message ;
    public Data data ;

    public static class Data {
        public int total;
        public Comment comment;
    }

    public static class Comment {
        public String createdDate ;
        public String id ;
        public String isLiked ;
        public String objecteId ;
        public String originalText ;
        public String parsedText;

        public ReplyUser replyUser;

        public User user ;

        public String userId;
    }

    public static class ReplyUser {
        public String name;
        public String url;
    }

    public static class User {
        public String avatarUrl;
        public String id;
        public String name;
        public String url;
    }







}
