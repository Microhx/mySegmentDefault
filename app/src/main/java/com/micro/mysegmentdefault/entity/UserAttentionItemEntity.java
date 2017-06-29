package com.micro.mysegmentdefault.entity;

import com.micro.mysegmentdefault.base.data.BaseDataInterface;

/**
 * author : micro_hx <p>
 * desc : 用户关注Item <p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/14 - 21:54 <p>
 * interface :
 */

@Deprecated
public class UserAttentionItemEntity implements BaseDataInterface {

    public String name ;
    public String slug ;
    public String id;
    public String url;
    public String rank;
    public String avatarUrl;
    public String likedVotes;
    public String folloedUsers;
    public String isEachFollowed;


}
