
webpackJsonp([3], [function (t, i, o) {
    t.exports = o(4)
}, , , , function (t, i, o) {
    (function (t) {
        "use strict";
        o(2), o(7), o(3), t(document).ready(function () {
            hljs.initHighlighting();
            var i = 0;

            t("pre code").each(function (t, i) {
                hljs.highlightBlock(i)
            });


            var o = function (i, o, n, e) {
                var c = t(this), a = c;
                n ?
                a.addClass("article-detail__comment-item-zan--active") :
                a.removeClass("article-detail__comment-item-zan--active"), a.find("span").text(o)
            };

            t(".comments-more").click(function () {
                window.sf.articleAction("comment", "0")
            }),


           t(".comment-action").click(function () {
                var n = t(this).data("id") + "", e = t(this).data("action");
                if ("vote" == e) {
                    var c = "comment-" + n + i++;
                    t(document).one(c, o.bind(this)), window.sf.commentAction(e, n, c)
                } else window.sf.commentAction(e, n, null)
            }),

           t(".article-more").click(function () {
                var i = t(this).offset();
                window.sf.articleMore(i.left, i.top)
            }),

           t(".comment-more").click(function () {
                var i = t(this).data("id") + "", o = t(this).offset();
                window.sf.commentMore(i, o.left, o.top)
            }),

           t("#main-container").on("click", ".fmt img", function () {
                var i = t(this).attr("src");
                window.sf.viewImage(i)
            }),

           t("#main-container").on("click", ".fmt pre", function () {
                var i = t(this).html();
                window.sf.viewCode(i)
            }),

           window.triggerCommentEvent = function () {
                var i = Array.prototype.slice.call(arguments), o = i[0], n = i.slice(1);
                t(document).trigger(o, n)
            };


            var n = function (i, o) {
                o ? (t(this).addClass("followed"), t(this).text("已关注")) : (t(this).removeClass("followed"), t(this).text("关注"))
            };

            t(".article-detail__author").on("click", ".article-detail__author-btn", function () {
                var i = t(this).data("id"), o = "follow-author" + i;
                t(document).one(o, n.bind(this));

                i = "x"+i;
                window.sf.followAuthor(i || -1, o) ;

            });

            var e = function (i, o) {
                o ? (t(this).addClass("followed"), t(this).text("已关注")) : (t(this).removeClass("followed"), t(this).text("关注专栏"))
            };

            t(".article-detail__author-blog").on("click", ".article-detail__author-btn", function () {
                var i = t(this).data("id"), o = "follow-blog" + i;
                t(document).one(o, e.bind(this)), window.sf.followBlog(i || -1, o)
            }),

            t(".article-reward").on("click", ".reward-icon", function () {
                window.sf.articleAction("reward")
            }),

            t(".article-reward").on("click", ".reward-user", function () {
                window.sf.articleAction("rewardUsers")
            }),

            t(".fmt img").each(function () {
                var i = t(this).attr("src");
                0 === i.indexOf("/") && t(this).attr("src", "https://segmentfault.com" + i);
                var o = t(this).attr("width"), n = t(this).attr("height");
                if (o) {
                    var e = t(this).width(), c = e * n / o;
                    t(this).attr("height", c)
                }
            }),

            t(".fmt img").lazyload()
        })
    }).call(i, o(1))
}, , , function (t, i) {
}]);