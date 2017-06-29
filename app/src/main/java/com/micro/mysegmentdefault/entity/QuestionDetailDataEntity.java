package com.micro.mysegmentdefault.entity;

import java.util.List;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/5/16 - 15:53 <p>
 * interface :
 */

public class QuestionDetailDataEntity {

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
        private String url;
        private String title;
        private String currentStatus;
        private String votes;
        private String created;
        private String comments;
        private String createdDate;
        private String siteId;
        private String excerpt;
        private String originalText;
        private String parsedText;
        private boolean isAccepted;
        private String bookmarks;
        private String viewsWord;
        private String answers;
        private boolean isClosed;
        private boolean isLiked;
        private boolean isHated;
        private boolean isFollowed;
        private String followers;
        private boolean isBookmarked;
        private boolean canLike;
        private boolean canHate;
        private boolean canEdit;

        private LastAnswerEntity lastAnswer;

        private UserEntity user;
        private Object existsAnswer;

        private List<TagsEntity> tags;

        private List<RelateEntity> relate;

        private List<AnswerEntity> answer;

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

        public String getCurrentStatus() {
            return currentStatus;
        }

        public void setCurrentStatus(String currentStatus) {
            this.currentStatus = currentStatus;
        }

        public String getVotes() {
            return votes;
        }

        public void setVotes(String votes) {
            this.votes = votes;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
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

        public String getSiteId() {
            return siteId;
        }

        public void setSiteId(String siteId) {
            this.siteId = siteId;
        }

        public String getExcerpt() {
            return excerpt;
        }

        public void setExcerpt(String excerpt) {
            this.excerpt = excerpt;
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

        public boolean isIsAccepted() {
            return isAccepted;
        }

        public void setIsAccepted(boolean isAccepted) {
            this.isAccepted = isAccepted;
        }

        public String getBookmarks() {
            return bookmarks;
        }

        public void setBookmarks(String bookmarks) {
            this.bookmarks = bookmarks;
        }

        public String getViewsWord() {
            return viewsWord;
        }

        public void setViewsWord(String viewsWord) {
            this.viewsWord = viewsWord;
        }

        public String getAnswers() {
            return answers;
        }

        public void setAnswers(String answers) {
            this.answers = answers;
        }

        public boolean isIsClosed() {
            return isClosed;
        }

        public void setIsClosed(boolean isClosed) {
            this.isClosed = isClosed;
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

        public boolean isIsFollowed() {
            return isFollowed;
        }

        public void setIsFollowed(boolean isFollowed) {
            this.isFollowed = isFollowed;
        }

        public String getFollowers() {
            return followers;
        }

        public void setFollowers(String followers) {
            this.followers = followers;
        }

        public boolean isIsBookmarked() {
            return isBookmarked;
        }

        public void setIsBookmarked(boolean isBookmarked) {
            this.isBookmarked = isBookmarked;
        }

        public boolean isCanLike() {
            return canLike;
        }

        public void setCanLike(boolean canLike) {
            this.canLike = canLike;
        }

        public boolean isCanHate() {
            return canHate;
        }

        public void setCanHate(boolean canHate) {
            this.canHate = canHate;
        }

        public boolean isCanEdit() {
            return canEdit;
        }

        public void setCanEdit(boolean canEdit) {
            this.canEdit = canEdit;
        }

        public LastAnswerEntity getLastAnswer() {
            return lastAnswer;
        }

        public void setLastAnswer(LastAnswerEntity lastAnswer) {
            this.lastAnswer = lastAnswer;
        }

        public UserEntity getUser() {
            return user;
        }

        public void setUser(UserEntity user) {
            this.user = user;
        }

        public Object getExistsAnswer() {
            return existsAnswer;
        }

        public void setExistsAnswer(Object existsAnswer) {
            this.existsAnswer = existsAnswer;
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

        public List<AnswerEntity> getAnswer() {
            return answer;
        }

        public void setAnswer(List<AnswerEntity> answer) {
            this.answer = answer;
        }

        public static class LastAnswerEntity {
            /**
             * name : CRIMX
             * url : /u/crimx
             * id : 1030000002640240
             */

            private UserEntity user;
            private String createdDate;
            private String url;

            public UserEntity getUser() {
                return user;
            }

            public void setUser(UserEntity user) {
                this.user = user;
            }

            public String getCreatedDate() {
                return createdDate;
            }

            public void setCreatedDate(String createdDate) {
                this.createdDate = createdDate;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public static class UserEntity {
                private String name;
                private String url;
                private String id;

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
            }
        }

        public static class UserEntity {
            private String id;
            private String name;
            private String rank;
            private String avatarUrl;
            private String url;
            private String slug;

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

            public String getRank() {
                return rank;
            }

            public void setRank(String rank) {
                this.rank = rank;
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

            public String getSlug() {
                return slug;
            }

            public void setSlug(String slug) {
                this.slug = slug;
            }
        }

        public static class TagsEntity {
            private String name;
            private String url;
            private String id;
            private String thumbnailUrl;
            private String iconUrl;
            /**
             * id : 1040000000089486
             * name : js
             */

            private List<SimilarTagsEntity> similarTags;

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

            public List<SimilarTagsEntity> getSimilarTags() {
                return similarTags;
            }

            public void setSimilarTags(List<SimilarTagsEntity> similarTags) {
                this.similarTags = similarTags;
            }

            public static class SimilarTagsEntity {
                private String id;
                private String name;

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
            }
        }

        public static class RelateEntity {
            private String id;
            private String url;
            private String title;
            private String answers;
            private boolean isAccepted;

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

            public String getAnswers() {
                return answers;
            }

            public void setAnswers(String answers) {
                this.answers = answers;
            }

            public boolean isIsAccepted() {
                return isAccepted;
            }

            public void setIsAccepted(boolean isAccepted) {
                this.isAccepted = isAccepted;
            }
        }

        public static class AnswerEntity {
            private String id;
            private String url;
            private String originalText;
            private String parsedText;
            private String shortUrl;
            private String isEdited;
            private String editUrl;
            private String currentStatus;
            private String type;
            private boolean canLike;
            private boolean canHate;
            private boolean canEdit;
            private boolean canAccept;
            private boolean canIgnore;
            private boolean canDelete;
            /**
             * id : 1030000002640240
             * name : CRIMX
             * rank : 825
             * rankWord : 825
             * avatarUrl : https://sfault-avatar.b0.upaiyun.com/391/576/3915768937-58ff3e2f0e2c7_big64
             * url : /u/crimx
             * slug : crimx
             * followedUsers : 4
             */

            private UserEntity user;
            private Object revision;
            private String comments;
            private boolean isLiked;
            private boolean isHated;
            private String createdDate;
            private String modifiedDate;
            private boolean isModified;
            private String votes;
            private boolean isAuthor;

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

            public String getShortUrl() {
                return shortUrl;
            }

            public void setShortUrl(String shortUrl) {
                this.shortUrl = shortUrl;
            }

            public String getIsEdited() {
                return isEdited;
            }

            public void setIsEdited(String isEdited) {
                this.isEdited = isEdited;
            }

            public String getEditUrl() {
                return editUrl;
            }

            public void setEditUrl(String editUrl) {
                this.editUrl = editUrl;
            }

            public String getCurrentStatus() {
                return currentStatus;
            }

            public void setCurrentStatus(String currentStatus) {
                this.currentStatus = currentStatus;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public boolean isCanLike() {
                return canLike;
            }

            public void setCanLike(boolean canLike) {
                this.canLike = canLike;
            }

            public boolean isCanHate() {
                return canHate;
            }

            public void setCanHate(boolean canHate) {
                this.canHate = canHate;
            }

            public boolean isCanEdit() {
                return canEdit;
            }

            public void setCanEdit(boolean canEdit) {
                this.canEdit = canEdit;
            }

            public boolean isCanAccept() {
                return canAccept;
            }

            public void setCanAccept(boolean canAccept) {
                this.canAccept = canAccept;
            }

            public boolean isCanIgnore() {
                return canIgnore;
            }

            public void setCanIgnore(boolean canIgnore) {
                this.canIgnore = canIgnore;
            }

            public boolean isCanDelete() {
                return canDelete;
            }

            public void setCanDelete(boolean canDelete) {
                this.canDelete = canDelete;
            }

            public UserEntity getUser() {
                return user;
            }

            public void setUser(UserEntity user) {
                this.user = user;
            }

            public Object getRevision() {
                return revision;
            }

            public void setRevision(Object revision) {
                this.revision = revision;
            }

            public String getComments() {
                return comments;
            }

            public void setComments(String comments) {
                this.comments = comments;
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

            public String getCreatedDate() {
                return createdDate;
            }

            public void setCreatedDate(String createdDate) {
                this.createdDate = createdDate;
            }

            public String getModifiedDate() {
                return modifiedDate;
            }

            public void setModifiedDate(String modifiedDate) {
                this.modifiedDate = modifiedDate;
            }

            public boolean isIsModified() {
                return isModified;
            }

            public void setIsModified(boolean isModified) {
                this.isModified = isModified;
            }

            public String getVotes() {
                return votes;
            }

            public void setVotes(String votes) {
                this.votes = votes;
            }

            public boolean isIsAuthor() {
                return isAuthor;
            }

            public void setIsAuthor(boolean isAuthor) {
                this.isAuthor = isAuthor;
            }

            public static class UserEntity {
                private String id;
                private String name;
                private String rank;
                private String rankWord;
                private String avatarUrl;
                private String url;
                private String slug;
                private int followedUsers;

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

                public String getSlug() {
                    return slug;
                }

                public void setSlug(String slug) {
                    this.slug = slug;
                }

                public int getFollowedUsers() {
                    return followedUsers;
                }

                public void setFollowedUsers(int followedUsers) {
                    this.followedUsers = followedUsers;
                }
            }
        }
    }
}
