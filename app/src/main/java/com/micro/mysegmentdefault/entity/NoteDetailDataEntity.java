package com.micro.mysegmentdefault.entity;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/16 - 17:52 <p>
 * interface :
 */

public class NoteDetailDataEntity {

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
        private String language;
        private String originalAbstract;
        private String currentStatus;
        private String parsedText;
        private String createdDate;
        private String isPrivate;
        private String viewsWord;
        private String editUrl;
        private String url;
        private String shortUrl;
        private boolean isBookmarked;
        private String bookmarks;
        private boolean isSelf;
        private boolean isForked;
        private String forks;
        private Object operator;


        private UserEntity user;
        private Object forkedNote;
        private String comments;
        private Object commentDetail;

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

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getOriginalAbstract() {
            return originalAbstract;
        }

        public void setOriginalAbstract(String originalAbstract) {
            this.originalAbstract = originalAbstract;
        }

        public String getCurrentStatus() {
            return currentStatus;
        }

        public void setCurrentStatus(String currentStatus) {
            this.currentStatus = currentStatus;
        }

        public String getParsedText() {
            return parsedText;
        }

        public void setParsedText(String parsedText) {
            this.parsedText = parsedText;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public String getIsPrivate() {
            return isPrivate;
        }

        public void setIsPrivate(String isPrivate) {
            this.isPrivate = isPrivate;
        }

        public String getViewsWord() {
            return viewsWord;
        }

        public void setViewsWord(String viewsWord) {
            this.viewsWord = viewsWord;
        }

        public String getEditUrl() {
            return editUrl;
        }

        public void setEditUrl(String editUrl) {
            this.editUrl = editUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getShortUrl() {
            return shortUrl;
        }

        public void setShortUrl(String shortUrl) {
            this.shortUrl = shortUrl;
        }

        public boolean isIsBookmarked() {
            return isBookmarked;
        }

        public void setIsBookmarked(boolean isBookmarked) {
            this.isBookmarked = isBookmarked;
        }

        public String getBookmarks() {
            return bookmarks;
        }

        public void setBookmarks(String bookmarks) {
            this.bookmarks = bookmarks;
        }

        public boolean isIsSelf() {
            return isSelf;
        }

        public void setIsSelf(boolean isSelf) {
            this.isSelf = isSelf;
        }

        public boolean isIsForked() {
            return isForked;
        }

        public void setIsForked(boolean isForked) {
            this.isForked = isForked;
        }

        public String getForks() {
            return forks;
        }

        public void setForks(String forks) {
            this.forks = forks;
        }

        public Object getOperator() {
            return operator;
        }

        public void setOperator(Object operator) {
            this.operator = operator;
        }

        public UserEntity getUser() {
            return user;
        }

        public void setUser(UserEntity user) {
            this.user = user;
        }

        public Object getForkedNote() {
            return forkedNote;
        }

        public void setForkedNote(Object forkedNote) {
            this.forkedNote = forkedNote;
        }

        public String getComments() {
            return comments;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }

        public Object getCommentDetail() {
            return commentDetail;
        }

        public void setCommentDetail(Object commentDetail) {
            this.commentDetail = commentDetail;
        }

        public static class UserEntity {
            private String name;
            private String avatarUrl;
            private String rankWord;
            private String url;
            private String followedUsers;
            private boolean isFollowed;
            private String id;
            private String slug;
            private String rank;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAvatarUrl() {
                return avatarUrl;
            }

            public void setAvatarUrl(String avatarUrl) {
                this.avatarUrl = avatarUrl;
            }

            public String getRankWord() {
                return rankWord;
            }

            public void setRankWord(String rankWord) {
                this.rankWord = rankWord;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getFollowedUsers() {
                return followedUsers;
            }

            public void setFollowedUsers(String followedUsers) {
                this.followedUsers = followedUsers;
            }

            public boolean isIsFollowed() {
                return isFollowed;
            }

            public void setIsFollowed(boolean isFollowed) {
                this.isFollowed = isFollowed;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getSlug() {
                return slug;
            }

            public void setSlug(String slug) {
                this.slug = slug;
            }

            public String getRank() {
                return rank;
            }

            public void setRank(String rank) {
                this.rank = rank;
            }
        }
    }
}
