package com.micro.mysegmentdefault.entity;

import java.util.ArrayList;

/**
 * author : micro_hx <p>
 * desc : 用户详细信息 entity<p>
 * email: javainstalling@163.com <p>
 * date : 2017/6/2 - 15:58 <p>
 * interface :
 */

public class UserDetailDataEntity {

    public int status;
    public String message;
    public Data data;


    public static class Data {

        public String avatarUrl;
        public String name;
        public String slug;

        //1为男 0为女
        public String profileGender;

        public String cityName;

        public String site;

        public String description;

        public ArrayList<ActiveTag> activeTags;

        public ArrayList<BestTag> bestTags;

        public ArrayList<BaseDataEntity> projects;

        public ArrayList<BaseDataEntity> schools;

        public ArrayList<BaseDataEntity> companies;

    }

    public static class BaseDataEntity {
        public String description;
        public String name ;
        public String department;
        public String stateName;
        public String role;
    }

}

/**
 *
 public static class Project {
 public String desciption;
 public String name;
 }


 public static class School {
 public String department;
 public String name;
 public String stateName;
 }


 public static class Company {
 public String name;
 public String role;
 }

 */


