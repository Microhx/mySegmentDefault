package com.micro.mysegmentdefault.entity;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/21 - 9:24 <p>
 * interface :
 */

public class UserDataEntity {

    public int status ;
    public String message ;
    public UserItem data ;


    public static class UserItem {
        public String id ;
        public String name ;
        public String avatarUrl ;
        public String rank ;
        public String followedUsers;
        public String followingUsers;
        public boolean isFollowed ;
        public boolean isEachFollowed ;
        public SummaryBadges summaryBadges;
    }

    public static class SummaryBadges {
        public String gold;
        public String silver;
        public String bronze;
    }

}
