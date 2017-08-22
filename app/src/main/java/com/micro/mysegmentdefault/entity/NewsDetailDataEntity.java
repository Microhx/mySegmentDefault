package com.micro.mysegmentdefault.entity;

import android.net.Uri;

import java.util.List;

/**
 * author : micro_hx <p>
 * desc : 头条详细内容实体<p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/15 - 21:12 <p>
 * interface :
 */

public class NewsDetailDataEntity {

    private int status;
    private DataEntity data;
    private String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataEntity {
        private String id;
        private String title;
        private String host;
        private String url;
        private String description;
        private String currentStatus;
        private String realViews;
        private String votesWord;
        private String comments;
        private String createdDate;
        private boolean isLiked;
        private String readParsedText;
        private String bookmarksWord;
        private boolean isBookmarked;
        private String newsUrl;
        private String originPath;

        private String cateType;
        private String original_text;
        private String readFirstImg;
        private String parsedText;

        private UserEntity user;

        private List<NewsTypesEntity> newsTypes;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCurrentStatus() {
            return currentStatus;
        }

        public void setCurrentStatus(String currentStatus) {
            this.currentStatus = currentStatus;
        }

        public String getRealViews() {
            return realViews;
        }

        public void setRealViews(String realViews) {
            this.realViews = realViews;
        }

        public String getVotesWord() {
            return votesWord;
        }

        public void setVotesWord(String votesWord) {
            this.votesWord = votesWord;
        }

        public String getComments() {
            return comments;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public boolean isIsLiked() {
            return isLiked;
        }

        public void setIsLiked(boolean isLiked) {
            this.isLiked = isLiked;
        }

        public String getReadParsedText() {
            return readParsedText;
        }

        public void setReadParsedText(String readParsedText) {
            this.readParsedText = readParsedText;
        }

        public String getBookmarksWord() {
            return bookmarksWord;
        }

        public void setBookmarksWord(String bookmarksWord) {
            this.bookmarksWord = bookmarksWord;
        }

        public boolean isIsBookmarked() {
            return isBookmarked;
        }

        public void setIsBookmarked(boolean isBookmarked) {
            this.isBookmarked = isBookmarked;
        }

        public String getNewsUrl() {
            return newsUrl;
        }

        public void setNewsUrl(String newsUrl) {
            this.newsUrl = newsUrl;
        }

        public String getOriginPath() {
            return originPath;
        }

        public void setOriginPath(String originPath) {
            this.originPath = originPath;
        }

        public String getCateType() {
            return cateType;
        }

        public void setCateType(String cateType) {
            this.cateType = cateType;
        }

        public String getOriginal_text() {
            return original_text;
        }

        public void setOriginal_text(String original_text) {
            this.original_text = original_text;
        }

        public String getReadFirstImg() {
            return readFirstImg;
        }

        public void setReadFirstImg(String readFirstImg) {
            this.readFirstImg = readFirstImg;
        }

        public UserEntity getUser() {
            return user;
        }

        public void setUser(UserEntity user) {
            this.user = user;
        }

        public List<NewsTypesEntity> getNewsTypes() {
            return newsTypes;
        }

        public void setNewsTypes(List<NewsTypesEntity> newsTypes) {
            this.newsTypes = newsTypes;
        }

        public String getShortPath() {
            return Uri.parse(originPath).getHost();
        }

        public String getParsedText() {
            return parsedText;
        }

        public void setParsedText(String parsedText) {
            this.parsedText = parsedText;
        }

        public static class UserEntity {
            private String id;
            private String name;
            private String url;
            private String avatarUrl;
            private boolean isFollowed;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getAvatarUrl() {
                return avatarUrl;
            }

            public void setAvatarUrl(String avatarUrl) {
                this.avatarUrl = avatarUrl;
            }

            public boolean isIsFollowed() {
                return isFollowed;
            }

            public void setIsFollowed(boolean isFollowed) {
                this.isFollowed = isFollowed;
            }
        }

        public static class NewsTypesEntity {
            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
