! function(e, o) {
    if ("object" == typeof exports && "object" == typeof module) module.exports = o();
    else if ("function" == typeof define && define.amd) define([], o);
    else {
        var r = o();
        for (var t in r)("object" == typeof exports ? exports : e)[t] = r[t]
    }
}(window, function() {
    return (window.webpackJsonp = window.webpackJsonp || []).push([
        [2], {
            321: function(e, o, r) {
                "use strict";
                "serviceWorker" in navigator && "http:" !== document.location.protocol && window.addEventListener("load", function() {
                    navigator.serviceWorker.register("./serviceWorker.js").then(function(e) {
                        console.log("Workbox service worker successful with scope:", e.scope)
                    }).catch(function(e) {
                        console.error("Workbox service worker failed to register", e)
                    })
                })
            }
        },
        [
            [321, 0]
        ]
    ])
});
