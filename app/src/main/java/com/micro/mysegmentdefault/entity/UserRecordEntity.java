package com.micro.mysegmentdefault.entity;

import java.util.List;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/21 - 11:12 <p>
 * interface :
 */

public class UserRecordEntity {

    public int status ;
    public String message ;
    public UserRecordItem data;


    public static class UserRecordItem {

        public String cityName ;

        public List<PublicBinding> publicBindings;

        public List<String> latestCompany;

        public List<String> latestSchool;

        public String lastUpdateSlugDays;

        public List<BestTag> bestTags;

        public Profile profile;
    }


    public static class Profile {
        public List<Company> companies;

        public List<School> schools;

        public List<Project> projects;
    }

    public static class PublicBinding {
        public String id;
        public String type;
        public String user_id ;
        public String url ;
        public String token;
        public String is_hide;
    }


    public static class Company {
        public String role;
        public String name;
    }


    public static class School {
        public String id ;
        public String department;
        public String name;
    }

    public static class Project {
        public String name ;
        public String role ;
    }

}
