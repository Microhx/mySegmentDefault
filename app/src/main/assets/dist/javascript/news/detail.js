/**
 * Created by guoli on 2017/5/15.
 */
webpackJsonp([2], {
    0: function (t, i, o) {
        t.exports = o(5)
    }, 5: function (t, i, o) {
        (function (t) {
            "use strict";
            o(2), o(8), o(3), t(function () {
                t(".news__desc--hide").on("click", function () {
                    t(this).removeClass("news__desc--hide")
                }), t(".fmt img").each(function () {
                    var i = t(this).attr("width"), o = t(this).attr("height");
                    if (i) {
                        var n = t(this).width(), e = n * o / i;
                        t(this).attr("height", e)
                    }
                }), t(".fmt img").lazyload();
                var i = function (i, o) {
                    o ? (t(this).addClass("followed"), t(this).text("已关注")) : (t(this).removeClass("followed"), t(this).text("关注"))
                };
                window.triggerEvent = function () {
                    var i = Array.prototype.slice.call(arguments), o = i[0], n = i.slice(1);
                    t(document).trigger(o, n)
                }, t(".news__author").on("click", ".news__author-follow", function () {
                    var o = t(this).data("id") + "", n = "follow-" + o;
                    t(document).one(n, i.bind(this)), window.sf.followAuthor(o || -1, n)
                })
            })
        }).call(i, o(1))
    }, 8: function (t, i) {
    }
});