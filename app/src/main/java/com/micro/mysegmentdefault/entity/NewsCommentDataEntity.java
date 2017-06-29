package com.micro.mysegmentdefault.entity;

import com.micro.mysegmentdefault.base.data.BaseDataInterface;

import java.util.List;

/**
 * author : micro_hx <p>
 * desc : 用户评论<p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/27 - 10:58 <p>
 * interface :
 */

public class NewsCommentDataEntity {

    public int status;
    public String message ;
    public Data data;


    public static class Data {
        public PageEntity page;
        public List<CommentItem> rows;
    }


    public static class CommentItem implements BaseDataInterface{

        public boolean canDelete;
        public boolean canEdit ;
        public String createdDate ;
        public String id ;
        public String originalText;
        public String repliedCommentCount;

        public List<RepliedItem> repliedComments;

        public CommentUser user;

        public String votes;

    }

    public static class RepliedItem {
        public boolean canDelete;
        public boolean canEdit;
        public String createdDate;
        public String id ;
        public boolean isLike;
        public String originalText;
        public String votes ;

        public ReplyUser user;
    }

    public static class ReplyUser {
        public String name;
        public String slug ;
        public String url;
    }

    public static class CommentUser {
        public String avatarUrl;
        public String id;
        public String name;
        public String slug;
        public String url;

    }



}
