package com.micro.mysegmentdefault.entity;

import java.util.List;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/16 - 13:51 <p>
 * interface :
 */

public class ArticleDetailEntity {

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
        private String editUrl;
        private String title;
        private String currentStatus;
        private String originalText;
        private String parsedText;
        private String votes;
        private String bookmarks;
        private String comments;

        //2.3k
        private String viewsWord;
        private String createdDate;
        private boolean isLiked;
        private boolean isHated;
        private boolean isAuthor;
        private boolean isRecommend;
        private boolean isHidden;
        private boolean isBookmarked;
        private boolean isDeleted;
        private String excerpt;
        private String url;
        private String showLicense;
        private String rewardCount;
        private boolean canLike;
        private boolean canEdit;


        private BlogEntity blog;

        private UserEntity user;
        private Object commentDetail;

        private List<TagsEntity> tags;

        private List<RelateEntity> relate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getEditUrl() {
            return editUrl;
        }

        public void setEditUrl(String editUrl) {
            this.editUrl = editUrl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCurrentStatus() {
            return currentStatus;
        }

        public void setCurrentStatus(String currentStatus) {
            this.currentStatus = currentStatus;
        }

        public String getOriginalText() {
            return originalText;
        }

        public void setOriginalText(String originalText) {
            this.originalText = originalText;
        }

        public String getParsedText() {
            return parsedText;
        }

        public void setParsedText(String parsedText) {
            this.parsedText = parsedText;
        }

        public String getVotes() {
            return votes;
        }

        public void setVotes(String votes) {
            this.votes = votes;
        }

        public String getBookmarks() {
            return bookmarks;
        }

        public void setBookmarks(String bookmarks) {
            this.bookmarks = bookmarks;
        }

        public String getComments() {
            return comments;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }

        public String getViewsWord() {
            return viewsWord;
        }

        public void setViewsWord(String viewsWord) {
            this.viewsWord = viewsWord;
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

        public boolean isIsHated() {
            return isHated;
        }

        public void setIsHated(boolean isHated) {
            this.isHated = isHated;
        }

        public boolean isIsAuthor() {
            return isAuthor;
        }

        public void setIsAuthor(boolean isAuthor) {
            this.isAuthor = isAuthor;
        }

        public boolean isIsRecommend() {
            return isRecommend;
        }

        public void setIsRecommend(boolean isRecommend) {
            this.isRecommend = isRecommend;
        }

        public boolean isIsHidden() {
            return isHidden;
        }

        public void setIsHidden(boolean isHidden) {
            this.isHidden = isHidden;
        }

        public boolean isIsBookmarked() {
            return isBookmarked;
        }

        public void setIsBookmarked(boolean isBookmarked) {
            this.isBookmarked = isBookmarked;
        }

        public boolean isIsDeleted() {
            return isDeleted;
        }

        public void setIsDeleted(boolean isDeleted) {
            this.isDeleted = isDeleted;
        }

        public String getExcerpt() {
            return excerpt;
        }

        public void setExcerpt(String excerpt) {
            this.excerpt = excerpt;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getShowLicense() {
            return showLicense;
        }

        public void setShowLicense(String showLicense) {
            this.showLicense = showLicense;
        }

        public String getRewardCount() {
            return rewardCount;
        }

        public void setRewardCount(String rewardCount) {
            this.rewardCount = rewardCount;
        }

        public boolean isCanLike() {
            return canLike;
        }

        public void setCanLike(boolean canLike) {
            this.canLike = canLike;
        }

        public boolean isCanEdit() {
            return canEdit;
        }

        public void setCanEdit(boolean canEdit) {
            this.canEdit = canEdit;
        }

        public BlogEntity getBlog() {
            return blog;
        }

        public void setBlog(BlogEntity blog) {
            this.blog = blog;
        }

        public UserEntity getUser() {
            return user;
        }

        public void setUser(UserEntity user) {
            this.user = user;
        }

        public Object getCommentDetail() {
            return commentDetail;
        }

        public void setCommentDetail(Object commentDetail) {
            this.commentDetail = commentDetail;
        }

        public List<TagsEntity> getTags() {
            return tags;
        }

        public void setTags(List<TagsEntity> tags) {
            this.tags = tags;
        }

        public List<RelateEntity> getRelate() {
            return relate;
        }

        public void setRelate(List<RelateEntity> relate) {
            this.relate = relate;
        }

        public static class BlogEntity {
            private String name;
            private String url;
            private String id;
            private String parsedText;
            private boolean isFollowed;
            private String description;
            private String reward;

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

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getParsedText() {
                return parsedText;
            }

            public void setParsedText(String parsedText) {
                this.parsedText = parsedText;
            }

            public boolean isIsFollowed() {
                return isFollowed;
            }

            public void setIsFollowed(boolean isFollowed) {
                this.isFollowed = isFollowed;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getReward() {
                return reward;
            }

            public void setReward(String reward) {
                this.reward = reward;
            }
        }

        public static class UserEntity {
            private String id;
            private String slug;
            private String name;
            private String avatarUrl;
            private String rank;
            private String rankWord;
            private String url;
            private String articles;
            private boolean isFollowed;
            private String followersWord;

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

            public String getRank() {
                return rank;
            }

            public void setRank(String rank) {
                this.rank = rank;
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

            public String getArticles() {
                return articles;
            }

            public void setArticles(String articles) {
                this.articles = articles;
            }

            public boolean isIsFollowed() {
                return isFollowed;
            }

            public void setIsFollowed(boolean isFollowed) {
                this.isFollowed = isFollowed;
            }

            public String getFollowersWord() {
                return followersWord;
            }

            public void setFollowersWord(String followersWord) {
                this.followersWord = followersWord;
            }
        }

        public static class TagsEntity {
            private String name;
            private String url;
            private String id;
            private String thumbnailUrl;
            private String iconUrl;
            private Object similarTags;

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

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getThumbnailUrl() {
                return thumbnailUrl;
            }

            public void setThumbnailUrl(String thumbnailUrl) {
                this.thumbnailUrl = thumbnailUrl;
            }

            public String getIconUrl() {
                return iconUrl;
            }

            public void setIconUrl(String iconUrl) {
                this.iconUrl = iconUrl;
            }

            public Object getSimilarTags() {
                return similarTags;
            }

            public void setSimilarTags(Object similarTags) {
                this.similarTags = similarTags;
            }
        }

        public static class RelateEntity {
            private String id;
            private String url;
            private String title;
            private String viewsWord;
            private String bookmarks;
            private String excerpt;
            /**
             * name : 彼得潘
             * avatarUrl : https://sfault-avatar.b0.upaiyun.com/865/737/865737806-565bc108bdf8a_big64
             * url : /u/panlining
             */

            private UserEntity user;
            /**
             * name : php
             * url : /t/php
             * id : 1040000000089387
             * thumbnailUrl : https://sfault-avatar.b0.upaiyun.com/188/805/188805810-1040000000089387_huge100
             */

            private List<TagsEntity> tags;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getViewsWord() {
                return viewsWord;
            }

            public void setViewsWord(String viewsWord) {
                this.viewsWord = viewsWord;
            }

            public String getBookmarks() {
                return bookmarks;
            }

            public void setBookmarks(String bookmarks) {
                this.bookmarks = bookmarks;
            }

            public String getExcerpt() {
                return excerpt;
            }

            public void setExcerpt(String excerpt) {
                this.excerpt = excerpt;
            }

            public UserEntity getUser() {
                return user;
            }

            public void setUser(UserEntity user) {
                this.user = user;
            }

            public List<TagsEntity> getTags() {
                return tags;
            }

            public void setTags(List<TagsEntity> tags) {
                this.tags = tags;
            }

            public static class UserEntity {
                private String name;
                private String avatarUrl;
                private String url;

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

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }

            public static class TagsEntity {
                private String name;
                private String url;
                private String id;
                private String thumbnailUrl;

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

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getThumbnailUrl() {
                    return thumbnailUrl;
                }

                public void setThumbnailUrl(String thumbnailUrl) {
                    this.thumbnailUrl = thumbnailUrl;
                }
            }
        }
    }
}
