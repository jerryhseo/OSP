! function(e, o) {
    if ("object" == typeof exports && "object" == typeof module) module.exports = o();
    else if ("function" == typeof define && define.amd) define([], o);
    else {
        var t = o();
        for (var n in t)("object" == typeof exports ? exports : e)[n] = t[n]
    }
}(window, function() {
    return function(e) {
        var o = {};

        function t(n) {
            if (o[n]) return o[n].exports;
            var r = o[n] = {
                i: n,
                l: !1,
                exports: {}
            };
            return e[n].call(r.exports, r, r.exports, t), r.l = !0, r.exports
        }
        return t.m = e, t.c = o, t.d = function(e, o, n) {
            t.o(e, o) || Object.defineProperty(e, o, {
                configurable: !1,
                enumerable: !0,
                get: n
            })
        }, t.r = function(e) {
            Object.defineProperty(e, "__esModule", {
                value: !0
            })
        }, t.n = function(e) {
            var o = e && e.__esModule ? function() {
                return e.default
            } : function() {
                return e
            };
            return t.d(o, "a", o), o
        }, t.o = function(e, o) {
            return Object.prototype.hasOwnProperty.call(e, o)
        }, t.p = "", t(t.s = 392)
    }({
        23: function(e, o) {
            var t;
            t = function() {
                return this
            }();
            try {
                t = t || Function("return this")() || (0, eval)("this")
            } catch (e) {
                "object" == typeof window && (t = window)
            }
            e.exports = t
        },
        391: function(e, o, t) {
            "use strict";
            "serviceWorker" in navigator && "http:" !== document.location.protocol && window.addEventListener("load", function() {
                navigator.serviceWorker.register("./serviceWorker.js").then(function(e) {
                    console.log("Workbox service worker successful with scope:", e.scope)
                }).catch(function(e) {
                    console.error("Workbox service worker failed to register", e)
                })
            })
        },
        392: function(e, o, t) {
            (function(o) {
                o.Glance || (o.Glance = {}), o.Glance.externals || (o.Glance.externals = {}), e.exports = o.Glance.externals.Workbox = t(391)
            }).call(this, t(23))
        }
    })
});