package com.micro.mysegmentdefault.entity;

import com.micro.mysegmentdefault.base.data.BaseDataInterface;

import java.util.List;

/**
 * author : micro_hx <p>
 * desc : 用户收藏Entity <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/24 - 15:04 <p>
 * interface :
 */

public class UserCollectEntity {
    public int status ;
    public Data data ;

    public static class Data {
        public PageEntity page;
        public List<CollectItem> rows;
    }

    public static class CollectItem implements BaseDataInterface {
        public String description;
        public String id;
        public String isBookmarked ;
        public String isPrivate;
        public String num;
        public String title ;
        public User user ;
        //是否为作者
        public String isAuthor;

    }

    public static class User {
        public String avatarUrl;
        public String name ;

    }

}
