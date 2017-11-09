/**
 * ParamQuery Pro v4.0.1
 * 
 * Copyright (c) 2012-2017 Paramvir Dhindsa (http://paramquery.com)
 * Released under Commercial license
 * http://paramquery.com/pro/license
 * 
 */
! function() {
    var t = window.pq = window.pq || {},
        e = t.mixin = {};
    e.render = {
        getRenderVal: function(t, e, r) {
            var n = t.column,
                i = n.exportRender;
            return (e && i !== !1 || i) && (n.render || n._render || n.format) ? r.renderCell(t) : [t.rowData[t.dataIndx], ""]
        }
    }
}(),
function(t) {
    "use strict";
    var e = t.ui.autocomplete.prototype,
        r = e._renderMenu,
        n = e._renderItem;
    e._renderMenu = function(e, n) {
        r.call(this, e, n);
        var i = this.options,
            o = i.selectItem;
        if (o && o.on) {
            var a = o.cls,
                a = void 0 === a ? "ui-state-highlight" : a,
                l = this.element.val();
            l && a && t("a", e).filter(function() {
                return t(this).text() === l
            }).addClass(a)
        }
    }, e._renderItem = function(t, e) {
        var r = n.call(this, t, e),
            i = this.options,
            o = i.highlightText;
        if (o && o.on) {
            var a = this.element.val();
            if (a) {
                var l = new RegExp("(" + a + ")", "i"),
                    s = e.label;
                if (l.test(s)) {
                    var d = o.style,
                        d = void 0 === d ? "font-weight:bold;" : d,
                        c = o.cls,
                        c = void 0 === c ? "" : c;
                    s = s.replace(l, "<span style='" + d + "' class='" + c + "'>$1</span>"), r.find("a").html(s)
                }
            }
        }
        return r
    };
    var i = t.paramquery = t.paramquery || {},
        o = function(t, e, r, n) {
            for (var i, o = e.slice(), a = 0, l = o.length, s = []; l > a; a++) {
                var d = o[a],
                    c = d.cb,
                    h = d.one;
                if (h) {
                    if (d._oncerun) continue;
                    d._oncerun = !0
                }
                if (i = c.call(t, r, n), i === !1 && (r.preventDefault(), r.stopPropagation()), h && s.push(a), r.isImmediatePropagationStopped()) break
            }
            if (l = s.length)
                for (a = l - 1; a >= 0; a--) o.splice(s[a], 1)
        };
    i._trigger = function(e, r, n) {
        var i, a, l = this,
            s = l.listeners,
            d = s[e],
            c = l.options,
            h = c.allEvents,
            u = c.bubble,
            f = l.element,
            p = c[e];
        if (n = n || {}, r = t.Event(r), r.type = l.widgetName + ":" + e, r.target = f[0], a = r.originalEvent)
            for (i in a) i in r || (r[i] = a[i]);
        if (h && "function" == typeof h && h.call(l, r, n), d && d.length && (o(l, d, r, n), r.isImmediatePropagationStopped())) return !r.isDefaultPrevented();
        if (c.trigger && (f[u ? "trigger" : "triggerHandler"](r, n), r.isImmediatePropagationStopped())) return !r.isDefaultPrevented();
        if (p) {
            var g = p.call(l, r, n);
            g === !1 && (r.preventDefault(), r.stopPropagation())
        }
        return d = s[e + "Done"], d && d.length && o(l, d, r, n), !r.isDefaultPrevented()
    };
    var a = function(t, e, r, n, i) {
        var o = t.listeners[e];
        o || (o = t.listeners[e] = []), o[i ? "unshift" : "push"]({
            cb: r,
            one: n
        })
    };
    i.on = function() {
        var t = arguments;
        if ("boolean" == typeof t[0]) var e = t[0],
            r = t[1],
            n = t[2],
            i = t[3];
        else var r = t[0],
            n = t[1],
            i = t[2];
        for (var o = r.split(" "), l = 0; l < o.length; l++) {
            var s = o[l];
            s && a(this, s, n, i, e)
        }
        return this
    }, i.one = function() {
        for (var t = arguments.length, e = [], r = 0; t > r; r++) e[r] = arguments[r];
        return e[t] = !0, this.on.apply(this, e)
    };
    var l = function(t, e, r) {
        if (r) {
            var n = t.listeners[e];
            if (n) {
                for (var i = [], o = 0, a = n.length; a > o; o++) {
                    var l = n[o],
                        s = l.cb;
                    r == s && i.push(o)
                }
                if (i.length)
                    for (var o = i.length - 1; o >= 0; o--) n.splice(i[o], 1)
            }
        } else delete t.listeners[e]
    };
    i.off = function(t, e) {
        for (var r = t.split(" "), n = 0; n < r.length; n++) {
            var i = r[n];
            i && l(this, i, e)
        }
        return this
    };
    var s = {
        options: {
            items: "td.pq-has-tooltip,td[title]",
            position: {
                my: "center top",
                at: "center bottom"
            },
            content: function() {
                var e = t(this),
                    r = e.closest(".pq-grid"),
                    n = r.pqGrid("instance"),
                    i = n.getCellIndices({
                        $td: e
                    }),
                    o = i.rowIndx,
                    a = i.dataIndx,
                    l = n.data({
                        rowIndx: o,
                        dataIndx: a,
                        data: "pq_valid"
                    }).data;
                if (l) {
                    var s = l.icon,
                        d = l.msg;
                    d = null != d ? d : "";
                    var c = "" == s ? "" : "<span class='ui-icon " + s + " pq-tooltip-icon'></span>";
                    return c + d
                }
                return e.attr("title")
            }
        }
    };
    s._create = function() {
        this._super();
        var e = this.element,
            r = this.eventNamespace;
        e.on("pqtooltipopen" + r, function(e, r) {
            var n = t(e.target),
                i = t(e.originalEvent.target);
            if (i.on("remove.pqtt", function(t) {
                    n.pqTooltip("close", t, !0)
                }), n.is(".pq-grid")) {
                var o, a = n.pqGrid("instance"),
                    l = a.getCellIndices({
                        $td: i
                    }),
                    s = l.rowIndx,
                    d = l.dataIndx,
                    c = a.getRowData({
                        rowIndx: s
                    });
                if ((o = c) && (o = o.pq_celldata) && (o = o[d]) && (o = o.pq_valid)) {
                    var h = o,
                        u = h.style,
                        f = h.cls;
                    r.tooltip.addClass(f);
                    var p = r.tooltip.attr("style");
                    r.tooltip.attr("style", p + ";" + u)
                }
                n.find(".pq-sb-horiz,.pq-sb-vert").on("pqscrollbardrag.pqtt", function(t, e) {
                    t.currentTarget = i[0], n.pqTooltip("close", t, !0)
                })
            }
        }), e.on("pqtooltipclose" + r, function(e, r) {
            var n = t(e.target),
                i = t(e.originalEvent.target);
            i.off(".pqtt"), n.is(".pq-grid") && n.find(".pq-sb-horiz,.pq-sb-vert").off(".pqtt")
        })
    }, t.widget("paramquery.pqTooltip", t.ui.tooltip, s)
}(jQuery),
function(t) {
    t.paramquery.onResizeDiv = function(e, r) {
        function n() {
            l = o, r()
        }

        function i() {
            c.children().scrollLeft(1e4).scrollTop(1e4)
        }
        var o, a, l = e.offsetHeight,
            s = 'style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;visibility:hidden;z-index:-1;"',
            d = ["<div ", s, " >", "<div ", s, " >", "<div style='width:10000px;height:10000px;'></div>", "</div>", "<div ", s, " >", "<div style='width:999%;height:999%;'></div>", "</div>", "</div>"].join(""),
            c = t(d);
        t(e).append(c), d = c[0], i(), c.children().on("scroll", function() {
            cancelAnimationFrame(a), o = e.offsetHeight, o != l && (a = requestAnimationFrame(n), i())
        })
    }
}(jQuery),
function(t) {
    "use strict";

    function e(t) {
        return t.charCodeAt(0) - 64
    }
    var r = t.paramquery,
        n = Array.prototype;
    !n.find && (n.find = function(t, e) {
        for (var r, n = 0, i = this.length; i > n; n++)
            if (r = this[n], t.call(e, r, n, this)) return r
    }), !n.findIndex && (n.findIndex = function(t, e) {
        for (var r, n = 0, i = this.length; i > n; n++)
            if (r = this[n], t.call(e, r, n, this)) return n;
        return -1
    });
    var i = t.extend(window.pq, {
        arrayUnique: function(t, e) {
            var r, n, i, o = [],
                a = t.length,
                l = {};
            for (r = 0; a > r; r++) n = t[r], i = e ? n[e] : n, l.hasOwnProperty(i) || (l[i] = 1, o.push(n));
            return o
        },
        escapeHtml: function(t) {
            return t.replace(/&/g, "&amp;").replace(/</g, "&lt;")
        },
        escapeXml: function(t) {
            return t.replace(/&/g, "&amp;").replace(/</g, "&lt;")
        },
        excelToJui: function() {
            var t = {};
            return function(e) {
                var r = t[e];
                return r || (r = e.replace(/yy/g, "y").replace(/dddd/g, "DD").replace(/ddd/g, "D").replace(/mmmm/g, "MM").replace(/mmm/g, "M"), t[e] = r), r
            }
        }(),
        excelToNum: function() {
            var t = {};
            return function(e) {
                var r = t[e];
                return r || (r = e.replace(/\\/g, ""), t[e] = r), r
            }
        }(),
        flatten: function(t, e) {
            var r, n = 0,
                o = t.length;
            for (e = e || []; o > n; n++) r = t[n], null != r && (r.push ? i.flatten(r, e) : e.push(r));
            return e
        },
        getFn: function() {
            var t = {};
            return function(e) {
                var r = e;
                return "string" == typeof e && ((r = t[e]) || (r = window, e.split(".").forEach(function(t) {
                    r = r[t]
                }), t[e] = r)), r
            }
        }(),
        isDateFormat: function() {
            var t = {};
            return function(e) {
                var r = t[e];
                return null == r && (r = t[e] = /^[mdy\s-\/]*$/i.test(e)), r
            }
        }(),
        isEmpty: function(t) {
            for (var e in t) return !1;
            return !0
        },
        juiToExcel: function() {
            var t = {};
            return function(e) {
                var r = t[e];
                return r || (r = e.replace(/y/g, "yy").replace(/DD/g, "dddd").replace(/D/g, "ddd").replace(/MM/g, "mmmm").replace(/M/g, "mmm"), t[e] = r), r
            }
        }(),
        numToExcel: function() {
            var t = {};
            return function(e) {
                var r = t[e];
                return r || (r = e.replace(/[^#0,.]/g, function(t) {
                    return "\\" + t
                }), t[e] = r), r
            }
        }(),
        searchSeqArray: function(t, e) {
            for (var r, n = t.length, i = e, o = e, a = 0, o = o > n - 1 ? n - 1 : o;;) {
                if (r = t[o], a++, e > r) {
                    if (o == n - 1) {
                        o = -1;
                        break
                    }
                    o = Math.ceil((o + i) / 2), o = o > n - 1 ? n - 1 : o
                } else {
                    if (!(r > e)) break;
                    i = o, o = Math.floor(o / 2)
                }
                if (a > 100) throw "too many iterations"
            }
            return o
        },
        unescapeXml: function() {
            var t = {
                amp: "&",
                lt: "<",
                gt: ">",
                quot: '"',
                apos: "'"
            };
            return function(e) {
                return e.replace(/&(amp|lt|gt|quot|apos);/g, function(e, r) {
                    return t[r]
                })
            }
        }()
    });
    r.select = function(t) {
        var e, r, n, i = t.attr,
            o = t.options,
            a = t.groupIndx,
            l = t.labelIndx,
            s = t.valueIndx,
            d = null != l && null != s,
            c = null != a,
            h = t.prepend,
            u = t.dataMap,
            f = function() {
                for (var t = {}, e = 0; e < u.length; e++) {
                    var r = u[e];
                    t[r] = w[r]
                }
                return "data-map='" + JSON.stringify(t) + "'"
            },
            p = ["<select ", i, " >"];
        if (h)
            for (var g in h) p.push('<option value="', g, '">', h[g], "</option>");
        if (o && o.length) {
            for (var v = 0, m = o.length; m > v; v++) {
                var w = o[v];
                if (d) {
                    var x = w[s],
                        y = w.pq_disabled ? 'disabled="disabled" ' : "",
                        _ = w.pq_selected ? 'selected="selected" ' : "";
                    if (null == x) continue;
                    if (n = u ? f() : "", c) {
                        var b = w.pq_disabled_group ? 'disabled="disabled" ' : "";
                        e = w[a], r != e && (null != r && p.push("</optgroup>"), p.push('<optgroup label="', e, '" ', b, " >"), r = e)
                    }
                    if (l == s) p.push("<option ", _, y, n, ">", x, "</option>");
                    else {
                        var I = w[l];
                        p.push("<option ", _, y, n, ' value="', x, '">', I, "</option>")
                    }
                } else if ("object" == typeof w)
                    for (var g in w) p.push('<option value="', g, '">', w[g], "</option>");
                else p.push("<option>", w, "</option>")
            }
            c && p.push("</optgroup>")
        }
        return p.push("</select>"), p.join("")
    }, t.fn.pqval = function(t) {
        if (t) {
            if (t.incr) {
                var e = this.data("pq_value");
                return this.prop("indeterminate", !1), e ? (e = !1, this.prop("checked", !1)) : e === !1 ? (e = null, this.prop("indeterminate", !0), this.prop("checked", !1)) : (e = !0, this.prop("checked", !0)), this.data("pq_value", e), e
            }
            var e = t.val;
            return this.data("pq_value", e), this.prop("indeterminate", !1), null == e ? (this.prop("indeterminate", !0), this.prop("checked", !1)) : e ? this.prop("checked", !0) : this.prop("checked", !1), this
        }
        return this.data("pq_value")
    }, r.xmlToArray = function(e, r) {
        var n = r.itemParent,
            i = r.itemNames,
            o = [],
            a = t(e).find(n);
        return a.each(function(e, r) {
            var n = t(r),
                a = [];
            t(i).each(function(t, e) {
                a.push(n.find(e).text().replace(/\r|\n|\t/g, ""))
            }), o.push(a)
        }), o
    }, r.xmlToJson = function(e, r) {
        var n = r.itemParent,
            i = r.itemNames,
            o = [],
            a = t(e).find(n);
        return a.each(function(e, r) {
            for (var n = t(r), a = {}, l = 0, s = i.length; s > l; l++) {
                var d = i[l];
                a[d] = n.find(d).text().replace(/\r|\n|\t/g, "")
            }
            o.push(a)
        }), o
    }, r.tableToArray = function(e) {
        var r = t(e),
            n = [],
            i = [],
            o = r.children("tbody").children("tr"),
            a = o.length ? t(o[0]) : t(),
            l = o.length > 1 ? t(o[1]) : t();
        return a.children("th,td").each(function(e, r) {
            var i = t(r),
                o = i.html(),
                a = i.width(),
                s = "left",
                d = "string";
            if (l.length) var c = l.find("td:eq(" + e + ")"),
                h = c.attr("align"),
                s = h ? h : s;
            var u = {
                title: o,
                width: a,
                dataType: d,
                align: s,
                dataIndx: e
            };
            n.push(u)
        }), o.each(function(e, r) {
            if (0 != e) {
                var n = t(r),
                    o = [];
                n.children("td").each(function(e, r) {
                    o.push(t.trim(t(r).html()))
                }), i.push(o)
            }
        }), {
            data: i,
            colModel: n
        }
    };
    var o = function(t) {
        return function(e, r) {
            var n, i, o, a;
            if (e) {
                if (i = e.split(":"), e = r && i.length > 1 ? i[1] : i[0], n = t[e]) return n;
                o = /^([^#]*|&#[^#]*)?[\,\.#0]*?([\,\s\.]?)([#0]*)([\,\s\.]?)([0]*?)(\s*[^#^0]*|&#[^#]*)?$/, a = e.match(o), a && a.length && (n = {
                    symbol: a[1] || "",
                    thouSep: a[2],
                    thousand: a[3].length,
                    decSep: a[4],
                    decimal: a[5].length,
                    symbolEnd: a[6] || ""
                }, t[e] = n)
            }
            return n = n || {
                symbol: "",
                symbolEnd: "",
                thouSep: ",",
                thousand: 3,
                decSep: ".",
                decimal: 2
            }
        }
    }({});
    r.formatCurrency = function(t, e) {
        var r = parseFloat(t);
        if (!isNaN(r)) {
            var n = 0 > r,
                i = o(e, n),
                a = i.symbol,
                l = i.symbolEnd,
                s = i.thousand,
                d = i.thouSep,
                c = i.decSep,
                h = i.decimal;
            r = r.toFixed(h);
            for (var u = r.length, f = h + c.length, p = r.substring(0, u - f), g = r.substring(u - f + c.length, u), v = p.match(/\d/g).reverse(), m = [], w = 0; w < v.length; w++) w > 0 && w % s == 0 && m.push(d), m.push(v[w]);
            return m = m.reverse(), p = m.join(""), (n ? "-" : "") + a + p + c + g + l
        }
    }, i.formatNumber = r.formatCurrency, i.validation = {
        is: function(t, e) {
            return "string" != t && t ? (t = t.substring(0, 1).toUpperCase() + t.substring(1, t.length), this["is" + t](e)) : !0
        },
        isFloat: function(t) {
            var e = parseFloat(t);
            return !isNaN(e) && e == t
        },
        isInteger: function(t) {
            var e = parseInt(t);
            return !isNaN(e) && e == t
        },
        isDate: function(t) {
            var e = Date.parse(t);
            return !isNaN(e)
        }
    };
    var a = [],
        l = {},
        s = i.toLetter = function(t) {
            var e = a[t];
            if (!e) {
                t++;
                var r = t % 26,
                    n = t / 26 | 0,
                    i = r ? String.fromCharCode(64 + r) : (--n, "Z");
                e = n ? s(n - 1) + i : i, t--, a[t] = e, l[e] = t
            }
            return e
        };
    i.toNumber = function(t) {
        var r, n, i, o, s, d = l[t];
        if (null == d) {
            for (r = t.length, d = -1, n = 0; r > n; n++) i = t[n], o = e(i), s = r - n - 1, d += o * Math.pow(26, s);
            a[d] = t, l[t] = d
        }
        return d
    }, i.generateData = function(t, e) {
        for (var r = [], n = 0; e > n; n++) r[n] = s(n);
        for (var i = [], n = 0; t > n; n++)
            for (var o = i[n] = [], a = 0; e > a; a++) o[a] = r[a] + (n + 1);
        return i
    }
}(jQuery),
function(t) {
    pq.validations = {
        minLen: function(t, e, r) {
            return t = r(t), e = r(e), t.length >= e ? !0 : void 0
        },
        nonEmpty: function(t) {
            return null != t && "" !== t ? !0 : void 0
        },
        maxLen: function(t, e, r) {
            return t = r(t), e = r(e), t.length <= e ? !0 : void 0
        },
        gt: function(t, e, r) {
            return t = r(t), e = r(e), t > e ? !0 : void 0
        },
        gte: function(t, e, r) {
            return t = r(t), e = r(e), t >= e ? !0 : void 0
        },
        lt: function(t, e, r) {
            return t = r(t), e = r(e), e > t ? !0 : void 0
        },
        lte: function(t, e, r) {
            return t = r(t), e = r(e), e >= t ? !0 : void 0
        },
        neq: function(t, e, r) {
            return t = r(t), e = r(e), t !== e ? !0 : void 0
        },
        regexp: function(t, e) {
            return new RegExp(e).test(t) ? !0 : void 0
        }
    };
    var e = t.paramquery;
    e.cValid = function(t) {
        this.that = t
    };
    var r = e.cValid.prototype;
    r._isValidCell = function(t) {
        var e = this.that,
            r = t.column,
            n = r.validations;
        if (!n || !n.length) return {
            valid: !0
        };
        var i, o = t.value,
            a = r.dataType,
            l = function(t) {
                return e.getValueFromDataType(t, a, !0)
            },
            s = t.rowData;
        if (!s) throw "rowData required.";
        for (var d = 0; d < n.length; d++) {
            var c = n[d],
                h = c.on,
                u = c.type,
                f = !1,
                p = c.msg,
                g = c.value;
            if (h !== !1) {
                if (i = pq.validations[u]) f = null == o ? !1 : i(o, g, l);
                else if (u) {
                    var v = {
                        column: r,
                        value: o,
                        rowData: s,
                        msg: p
                    };
                    e.callFn(u, v) === !1 ? (f = !1, p = v.msg) : f = !0
                } else f = !0;
                if (!f) return {
                    valid: !1,
                    msg: p,
                    column: r,
                    warn: c.warn,
                    dataIndx: r.dataIndx,
                    validation: c
                }
            }
        }
        return {
            valid: !0
        }
    }, r.isValidCell = function(e) {
        var r = this.that,
            n = e.rowData,
            i = e.rowIndx,
            o = e.value,
            a = e.valueDef,
            l = e.column,
            s = e.focusInvalid,
            d = r.options,
            c = d.bootstrap,
            h = e.allowInvalid,
            u = l.dataIndx,
            f = d.validation,
            p = d.warning,
            g = d.editModel,
            v = g.invalidClass,
            m = g.warnClass,
            w = document.activeElement;
        if (e.checkEditable && 0 == r.isEditableCell({
                rowIndx: i,
                dataIndx: u
            })) return {
            valid: !0
        };
        var x = this._isValidCell({
                column: l,
                value: o,
                rowData: n
            }),
            y = x.valid,
            _ = x.warn,
            b = x.msg;
        if (y) r.data({
            rowData: n,
            dataIndx: u,
            data: "pq_valid"
        }) && (r.removeClass({
            rowData: n,
            rowIndx: i,
            dataIndx: u,
            cls: m + " " + v
        }), r.removeData({
            rowData: n,
            dataIndx: u,
            data: "pq_valid"
        }));
        else var I = t.extend({}, _ ? p : f, x.validation),
            C = I.css,
            q = I.cls,
            R = I.icon,
            D = I.style;
        if (h || _) return y ? {
            valid: !0
        } : (r.addClass({
            rowData: n,
            rowIndx: i,
            dataIndx: u,
            cls: _ ? m : v
        }), r.data({
            rowData: n,
            dataIndx: u,
            data: {
                pq_valid: {
                    css: C,
                    icon: R,
                    style: D,
                    msg: b,
                    cls: q
                }
            }
        }), x);
        if (!y) {
            if (null == i) {
                var M = r.getRowIndx({
                        rowData: n,
                        dataUF: !0
                    }),
                    i = M.rowIndx;
                if (null == i || M.uf) return x.uf = M.uf, x
            }
            if (s) {
                var T;
                if (a) {
                    if (t(w).hasClass("pq-editor-focus")) {
                        var P = d.editModel.indices;
                        if (P) {
                            var E = P.rowIndx,
                                S = P.dataIndx;
                            if (null != i && i != E) throw "incorrect usage of isValid rowIndx: " + i;
                            if (u != S) throw "incorrect usage of isValid dataIndx: " + u;
                            r.editCell({
                                rowIndx: E,
                                dataIndx: u
                            })
                        }
                    }
                } else {
                    r.goToPage({
                        rowIndx: i
                    });
                    var k = {
                            rowIndx: i,
                            dataIndx: u
                        },
                        k = r.normalize(k);
                    T = r.getCell(k), r["cell" == d.selectionModel.type ? "setSelection" : "scrollCell"](k), r.focus(k)
                }
                var H;
                if (T || (H = r.getEditCell()) && H.$cell) {
                    var F = T || H.$cell;
                    F.attr("title", b);
                    var $ = "tooltip",
                        A = "open";
                    c.on && c.tooltip && ($ = c.tooltip, A = "show");
                    try {
                        F[$]("destroy")
                    } catch (V) {}
                    F[$]({
                        trigger: "manual",
                        position: {
                            my: "left center+5",
                            at: "right center"
                        },
                        content: function() {
                            var t = "" == R ? "" : "<span class='ui-icon " + R + " pq-tooltip-icon'></span>";
                            return t + b
                        },
                        open: function(t, e) {
                            var r = e.tooltip;
                            if (q && r.addClass(q), D) {
                                var n = r.attr("style");
                                r.attr("style", n + ";" + D)
                            }
                            C && r.tooltip.css(C)
                        }
                    })[$](A)
                }
            }
            return x
        }
        if (a) {
            var H = r.getEditCell();
            if (H && H.$cell) {
                var F = H.$cell;
                F.removeAttr("title");
                try {
                    F.tooltip("destroy")
                } catch (V) {}
            }
        }
        return {
            valid: !0
        }
    }, r.isValid = function(t) {
        t = t || {};
        var e = this.that,
            r = t.allowInvalid,
            n = t.focusInvalid,
            i = t.checkEditable,
            r = null == r ? !1 : r,
            o = t.dataIndx;
        if (null != o) {
            var a = e.columns[o],
                l = t.rowData || e.getRowData(t),
                s = t.hasOwnProperty("value"),
                d = s ? t.value : l[o],
                c = this.isValidCell({
                    rowData: l,
                    checkEditable: i,
                    rowIndx: t.rowIndx,
                    value: d,
                    valueDef: s,
                    column: a,
                    allowInvalid: r,
                    focusInvalid: n
                });
            return c.valid || c.warn ? {
                valid: !0
            } : c
        }
        if (null != t.rowIndx || null != t.rowIndxPage || null != t.rowData) {
            for (var l = t.rowData || e.getRowData(t), h = e.colModel, u = [], f = 0, p = h.length; p > f; f++) {
                var a = h[f],
                    g = a.hidden;
                if (!g) {
                    var o = a.dataIndx,
                        d = l[o],
                        c = this.isValidCell({
                            rowData: l,
                            value: d,
                            column: a,
                            rowIndx: t.rowIndx,
                            checkEditable: i,
                            allowInvalid: r,
                            focusInvalid: n
                        });
                    if (!c.valid && !c.warn) {
                        if (!r) return c;
                        u.push({
                            rowData: l,
                            dataIndx: o,
                            column: a
                        })
                    }
                }
            }
            return r && u.length ? {
                cells: u,
                valid: !1
            } : {
                valid: !0
            }
        }
        var v = t.data ? t.data : e.options.dataModel.data,
            u = [];
        if (!v) return null;
        for (var f = 0, p = v.length; p > f; f++) {
            var m, l = v[f];
            if (!i || (m = this.getRowIndx({
                    rowData: l
                }).rowIndx, null != m && 0 != e.isEditableRow({
                    rowData: l,
                    rowIndx: m
                }))) {
                var w = this.isValid({
                        rowData: l,
                        rowIndx: m,
                        checkEditable: i,
                        allowInvalid: r,
                        focusInvalid: n
                    }),
                    x = w.cells;
                if (r === !1) {
                    if (!w.valid) return w
                } else x && x.length && (u = u.concat(x))
            }
        }
        return r && u.length ? {
            cells: u,
            valid: !1
        } : {
            valid: !0
        }
    }
}(jQuery),
function(t) {
    "use strict";

    function e(e, r, n) {
        return t(e ? "<span tabindex='0' rel='tooltip' data-placement='top' title='" + r + "' class='btn btn-xs " + n + "'></span>" : "<span class='pq-ui-button ui-widget-header' tabindex='0' rel='tooltip' title='" + r + "'><span class='ui-icon ui-icon-" + n + "'></span></span>")
    }

    function r(e, r) {
        e.bind("click keydown", function(e) {
            return "keydown" != e.type || e.keyCode == t.ui.keyCode.ENTER ? r.call(this, e) : void 0
        })
    }

    function n(t, e, r) {
        e[r ? "addClass" : "removeClass"]("disabled").css("pointer-events", r ? "none" : "").attr("tabindex", r ? "" : "0")
    }
    var i = {};
    i.options = {
        bootstrap: {
            on: !1,
            pager: "",
            nextIcon: "glyphicon glyphicon-forward",
            prevIcon: "glyphicon glyphicon-backward",
            firstIcon: "glyphicon glyphicon-step-backward",
            lastIcon: "glyphicon glyphicon-step-forward",
            refreshIcon: "glyphicon glyphicon-refresh"
        },
        curPage: 0,
        totalPages: 0,
        totalRecords: 0,
        msg: "",
        rPPOptions: [10, 20, 30, 40, 50, 100],
        rPP: 20
    }, i._regional = {
        strDisplay: "Displaying {0} to {1} of {2} items.",
        strFirstPage: "First Page",
        strLastPage: "Last Page",
        strNextPage: "Next Page",
        strPage: "Page {0} of {1}",
        strPrevPage: "Previous Page",
        strRefresh: "Refresh",
        strRpp: "Records per page:{0}"
    }, t.extend(i.options, i._regional), i._create = function() {
        var n = this,
            i = this.options,
            o = this.element,
            a = i.bootstrap,
            l = a.on;
        this.listeners = {}, o.addClass("pq-pager " + (l ? a.pager : "")), this.first = e(l, i.strFirstPage, l ? a.firstIcon : "seek-first").appendTo(o), r(this.first, function(t) {
            i.curPage > 1 && n._onChange(t, 1)
        }), this.prev = e(l, i.strPrevPage, l ? a.prevIcon : "seek-prev").appendTo(o), r(this.prev, function(t) {
            if (i.curPage > 1) {
                var e = i.curPage - 1;
                n._onChange(t, e)
            }
        }), t("<span class='pq-separator'></span>").appendTo(o), this.pageHolder = t("<span class='pq-page-placeholder'></span>").appendTo(o), t("<span class='pq-separator'></span>").appendTo(o), this.next = e(l, i.strNextPage, l ? a.nextIcon : "seek-next").appendTo(o), r(this.next, function(t) {
            if (i.curPage < i.totalPages) {
                var e = i.curPage + 1;
                n._onChange(t, e)
            }
        }), this.last = e(l, i.strLastPage, l ? a.lastIcon : "seek-end").appendTo(o), r(this.last, function(t) {
            if (i.curPage !== i.totalPages) {
                var e = i.totalPages;
                n._onChange(t, e)
            }
        }), t("<span class='pq-separator'></span>").appendTo(o), this.rPPHolder = t("<span class='pq-page-placeholder'></span>").appendTo(o), this.$refresh = e(l, i.strRefresh, l ? a.refreshIcon : "refresh").appendTo(o), r(this.$refresh, function(t) {
            return n._trigger("beforeRefresh", t) === !1 ? !1 : void n._trigger("refresh", t)
        }), t("<span class='pq-separator'></span>").appendTo(o), this.$msg = t("<span class='pq-pager-msg'></span>").appendTo(o), this._refresh()
    }, i._destroy = function() {
        this.element.empty().removeClass("pq-pager").enableSelection()
    }, i._setOption = function(t, e) {
        "curPage" != t && "totalPages" != t || (e = 1 * e), this._super(t, e)
    }, i._setOptions = function(e) {
        var r, n = !1,
            i = this.options;
        for (r in e) {
            var o = e[r],
                a = typeof o;
            "string" == a || "number" == a ? o != i[r] && (this._setOption(r, o), n = !0) : "function" == typeof o.splice || t.isPlainObject(o) ? JSON.stringify(o) != JSON.stringify(i[r]) && (this._setOption(r, o), n = !0) : o != i[r] && (this._setOption(r, o), n = !0)
        }
        return n && this._refresh(), this
    }, t.widget("paramquery.pqPager", i), pq.pager = function(e, r) {
        var n = t(e).pqPager(r),
            i = n.data("paramqueryPqPager") || n.data("paramquery-pqPager");
        return i
    };
    var o = t.paramquery;
    o.pqPager.regional = {}, o.pqPager.regional.en = i._regional, i = o.pqPager.prototype, o.pqPager.defaults = i.options, i._refreshPage = function() {
        var e = this;
        this.pageHolder.empty();
        for (var r = this.options, n = r.bootstrap, i = r.strPage, o = i.split(" "), a = [], l = 0, s = o.length; s > l; l++) {
            var d = o[l];
            "{0}" == d ? a.push("<input type='text' tabindex='0' class='pq-pager-input ", n.on ? "" : "ui-corner-all", "' />") : "{1}" == d ? a.push("<span class='total'></span>") : a.push("<span>", d, "</span>")
        }
        var c = a.join(""),
            h = t(c).appendTo(this.pageHolder);
        this.page = h.filter("input").bind("keydown", function(e) {
            e.keyCode === t.ui.keyCode.ENTER && t(this).trigger("change")
        }).bind("change", function(n) {
            var i = t(this),
                o = i.val();
            return isNaN(o) || 1 > o ? (i.val(r.curPage), !1) : (o = parseInt(o), o !== r.curPage ? o > r.totalPages ? (i.val(r.curPage), !1) : e._onChange(n, o) === !1 ? (i.val(r.curPage), !1) : void 0 : void 0)
        }), this.$total = h.filter("span.total")
    }, i._onChange = function(t, e) {
        return this._trigger("beforeChange", t, {
            curPage: e
        }) === !1 ? !1 : this._trigger("change", t, {
            curPage: e
        }) === !1 ? !1 : void this.option({
            curPage: e
        })
    }, i._refresh = function() {
        this._refreshPage();
        var e = (this.$rPP, this),
            r = this.options,
            i = r.bootstrap;
        if (this.rPPHolder.empty(), r.strRpp) {
            var o = r.rPPOptions,
                a = r.strRpp;
            if (-1 != a.indexOf("{0}")) {
                for (var l = ["<select class='", i.on ? "" : "ui-corner-all", "'>"], s = 0, d = o.length; d > s; s++) {
                    var c = o[s];
                    l.push('<option value="', c, '">', c, "</option>")
                }
                l.push("</select>");
                var h = l.join("");
                a = a.replace("{0}", "</span>" + h), a = "<span>" + a + "<span class='pq-separator'></span>"
            } else a = "<span>" + a + "</span><span class='pq-separator'></span>";
            this.$rPP = t(a).appendTo(this.rPPHolder).filter("select").val(r.rPP).change(function(r) {
                var n = t(this),
                    i = n.val();
                return e._trigger("beforeChange", r, {
                    rPP: i
                }) === !1 ? (n.val(e.options.rPP), !1) : void(e._trigger("change", r, {
                    rPP: i
                }) !== !1 && (e.options.rPP = i))
            })
        }
        var u = r.bootstrap.on,
            f = r.curPage >= r.totalPages;
        n(u, this.next, f), n(u, this.last, f);
        var f = r.curPage <= 1;
        if (n(u, this.first, f), n(u, this.prev, f), this.page.val(r.curPage), this.$total.text(r.totalPages), this.options.totalRecords > 0) {
            var p = r.rPP,
                g = r.curPage,
                v = r.totalRecords,
                m = (g - 1) * p,
                w = g * p;
            w > v && (w = v);
            var x = r.strDisplay;
            x = x.replace("{0}", m + 1), x = x.replace("{1}", w), x = x.replace("{2}", v), this.$msg.html(x)
        } else this.$msg.html("")
    }, i.getInstance = function() {
        return {
            pager: this
        }
    }, i._trigger = o._trigger, i.on = o.on, i.one = o.one, i.off = o.off
}(jQuery),
function(t) {
    "use strict";

    function e(t, e) {
        return t ? "<div class='" + e + "'></div>" : "<div class='ui-icon ui-icon-" + e + "'></div>"
    }
    var r = t.paramquery,
        n = {};
    n.options = {
        length: 200,
        num_eles: 3,
        trigger: !1,
        cur_pos: 0,
        ratio: 0,
        timeout: 350,
        pace: "optimum",
        direction: "vertical",
        bootstrap: {
            on: !1,
            slider: "btn btn-default",
            up: "glyphicon glyphicon-chevron-up",
            down: "glyphicon glyphicon-chevron-down",
            left: "glyphicon glyphicon-chevron-left",
            right: "glyphicon glyphicon-chevron-right"
        },
        theme: !1
    }, n._destroy = function() {
        t(document).off("." + this.eventNamespace), this.element.removeClass("pq-sb pq-sb-vert pq-sb-vert-t pq-sb-vert-wt").enableSelection().removeClass("pq-sb-horiz pq-sb-horiz-t pq-sb-horiz-wt").unbind("click.pq-scrollbar").empty(), this.element.removeData()
    }, n._create = function() {
        this.listeners = {}, this._createLayout()
    }, n._setOption = function(t, e) {
        var r = this.options;
        "disabled" == t ? (this._super(t, e), 1 == e ? this.$slider.draggable("disable") : this.$slider.draggable("enable")) : "theme" == t ? (r[t] = e, this._createLayout()) : "ratio" == t ? e >= 0 && 1 >= e && (r[t] = e) : r[t] = e
    }, n._setOptions = function() {
        this._super.apply(this, arguments), this.refresh()
    }, t.widget("paramquery.pqScrollBar", n), pq.scrollbar = function(e, r) {
        var n = t(e).pqScrollBar(r),
            i = n.data("paramqueryPqScrollBar") || n.data("paramquery-pqScrollBar");
        return i
    };
    var n = r.pqScrollBar.prototype;
    r.pqScrollBar.defaults = n.options, n._createLayout = function() {
        var r = this,
            n = this.options,
            i = n.bootstrap,
            o = i.on,
            a = n.direction,
            l = this.eventNamespace,
            s = n.theme,
            d = this.element.empty();
        "vertical" == a ? (d.removeClass("pq-sb-vert-t pq-sb-vert-wt").addClass("pq-sb pq-sb-vert"), s ? (d.addClass("pq-sb-vert-t"), d[0].innerHTML = ["<div class='top-btn pq-sb-btn ", o ? "" : "ui-state-default ui-corner-top", "'>", e(o, o ? i.up : "triangle-1-n"), "</div>", "<div class='pq-sb-slider ", o ? i.slider : "ui-corner-all ui-state-default", "'>", "</div>", "<div class='bottom-btn pq-sb-btn ", o ? "" : "ui-state-default ui-corner-bottom", "'>", e(o, o ? i.down : "triangle-1-s"), "</div>"].join("")) : (d.addClass("pq-sb-vert-wt"), d[0].innerHTML = ["<div class='top-btn pq-sb-btn'></div>", "<div class='pq-sb-slider'>", "<div class='vert-slider-top'></div>", "<div class='vert-slider-bg'></div>", "<div class='vert-slider-center'></div>", "<div class='vert-slider-bg'></div>", "<div class='vert-slider-bottom'></div>", "</div>", "<div class='bottom-btn pq-sb-btn'></div>"].join(""))) : (d.removeClass("pq-sb-horiz-t pq-sb-horiz-wt").addClass("pq-sb pq-sb-horiz"), s ? (d.addClass("pq-sb-horiz-t"), d[0].innerHTML = ["<div class='left-btn pq-sb-btn ", o ? "" : "ui-state-default ui-corner-left", "'>", e(o, o ? i.left : "triangle-1-w"), "</div>", "<div class='pq-sb-slider pq-sb-slider-h ", o ? i.slider : "ui-state-default ui-corner-all", "'>", "</div>", "<div class='right-btn pq-sb-btn ", o ? "" : "ui-state-default ui-corner-right", "'>", e(o, o ? i.right : "triangle-1-e"), "</div>"].join("")) : (d.addClass("pq-sb-horiz-wt"), d.width(this.width), d[0].innerHTML = ["<div class='left-btn pq-sb-btn'></div>", "<div class='pq-sb-slider pq-sb-slider-h'>", "<span class='horiz-slider-left'></span>", "<span class='horiz-slider-bg'></span>", "<span class='horiz-slider-center'></span>", "<span class='horiz-slider-bg'></span>", "<span class='horiz-slider-right'></span>", "</div>", "<div class='right-btn pq-sb-btn'></div>"].join(""))), d.disableSelection().unbind(".pq-scrollbar").on("mousedown.pq-scrollbar", function(e) {
            if (!n.disabled && !r.$slider.is(":hidden"))
                if (t(document).off("." + l).on("mouseup." + l, function(t) {
                        r._mouseup(t)
                    }), "vertical" == a) {
                    var i = e.pageY,
                        o = r.element.offset().top,
                        s = o + n.length,
                        d = r.$slider,
                        c = d.offset().top,
                        h = d.height(),
                        u = c + h;
                    c > i && i > o + 17 ? r._setTimerPageLeft(i, h, e, 0) : i > u && s - 17 > i && r._setTimerPageRight(i, h, e, 0)
                } else {
                    var f = e.pageX,
                        o = r.element.offset().left,
                        s = o + n.length,
                        c = r.$slider.offset().left,
                        u = c + r.$slider.width();
                    c > f && f > o + 17 ? (r.$slider.css("left", f - r.element.offset().left), r._updateCurPosAndTrigger(e)) : f > u && s - 17 > f && (r.$slider.css("left", f - r.element.offset().left - r.$slider.width()), r._updateCurPosAndTrigger(e))
                }
        });
        var c = this.$slider = t("div.pq-sb-slider", this.element);
        this._bindSliderEvents(c), this.$top_btn = t("div.top-btn,div.left-btn", this.element).click(function(t) {
            return r.options.disabled ? void 0 : (r.decr_cur_pos(t), !1)
        }).mousedown(function(t) {
            return r.options.disabled ? void 0 : (r.mousedownTimeout = setTimeout(function() {
                r.mousedownInterval = setInterval(function() {
                    r.decr_cur_pos(t)
                }, 0)
            }, r.options.timeout), !1)
        }).bind("mouseup mouseout", function(t) {
            r._mouseup(t)
        }), this.$bottom_btn = t("div.bottom-btn,div.right-btn", this.element).click(function(t) {
            return r.options.disabled ? void 0 : (r.incr_cur_pos(t), !1)
        }).mousedown(function(t) {
            return r.options.disabled ? void 0 : (r.mousedownTimeout = setTimeout(function() {
                r.mousedownInterval = setInterval(function() {
                    r.incr_cur_pos(t)
                }, 0)
            }, r.options.timeout), !1)
        }).bind("mouseup mouseout", function(t) {
            r._mouseup(t)
        }), this.refresh()
    };
    var i = 0;
    n._setTimerPageLeft = function(t, e, r, n) {
        var o = this,
            a = o.options;
        this.mousedownTimeout = window.setTimeout(function() {
            if (t >= o.$slider.offset().top) o._mouseup();
            else {
                o._pageLeft(r);
                var n = i ? 0 : a.timeout;
                i++, o._setTimerPageLeft(t, e, r, n)
            }
        }, n)
    }, n._setTimerPageRight = function(t, e, r, n) {
        var o = this;
        this.mousedownTimeout = window.setTimeout(function() {
            if (t <= o.$slider.offset().top + e) o._mouseup();
            else {
                o._pageRight(r);
                var n = i ? 0 : o.options.timeout;
                i++, o._setTimerPageRight(t, e, r, n)
            }
        }, n)
    }, n._bindSliderEvents = function(e) {
        var r = this,
            n = this.options.direction,
            i = "x";
        "vertical" == n && (i = "y"), e.draggable({
            axis: i,
            helper: function(t, e) {
                return r._setDragLimits(), this
            },
            start: function(t) {
                r.topWhileDrag = null, r.dragging = !0
            },
            drag: function(t) {
                r.dragging = !0;
                var e = r.options.pace;
                "optimum" == e ? r._setNormalPace(t) : "fast" == e && r._updateCurPosAndTrigger(t)
            },
            stop: function(t) {
                "fast" != r.options.pace && r._updateCurPosAndTrigger(t), r.dragging = !1, r.refresh()
            }
        }).on("keydown.pq-scrollbar", function(e) {
            var n = e.keyCode,
                i = r.options,
                o = i.cur_pos,
                a = i.ratio,
                l = i.num_eles,
                s = t.ui.keyCode;
            return null == r.keydownTimeout && (r.keydownTimeout = window.setTimeout(function() {
                n == s.DOWN || n == s.RIGHT ? r.incr_cur_pos(e) : n == s.UP || n == s.LEFT ? r.decr_cur_pos(e) : n == s.HOME ? i.steps ? o > 0 && (i.cur_pos = 0, r.updateSliderPos(), r.scroll(e)) : a > 0 && (i.ratio = 0, r.updateSliderPos(), r.drag(e)) : n == s.END ? i.steps ? l > o && (i.cur_pos = l, r.updateSliderPos(), r.scroll(e)) : 1 > a && (i.ratio = 1, r.updateSliderPos(), r.drag(e)) : "vertical" == i.direction && (n == s.PAGE_DOWN ? r._pageRight(e) : n == s.PAGE_UP && r._pageLeft(e)), r.keydownTimeout = null
            }, 0)), n == s.DOWN || n == s.RIGHT || n == s.UP || n == s.LEFT || n == s.PAGE_DOWN || n == s.PAGE_UP || n == s.HOME || n == s.END ? (e.preventDefault(), !1) : void 0
        })
    }, n.decr_cur_pos = function(t) {
        var e = this,
            r = e.options,
            n = r.steps;
        if (n) r.cur_pos > 0 && (r.cur_pos--, e.updateSliderPos(), e.scroll(t));
        else if (r.ratio > 0) {
            var i = r.ratio - 1 / (r.num_eles - 1);
            0 > i && (i = 0), r.ratio = i, e.updateSliderPos(), e.drag(t)
        }
    }, n.incr_cur_pos = function(t) {
        var e = this,
            r = e.options,
            n = r.steps;
        if (n) r.cur_pos < r.num_eles - 1 && r.cur_pos++, e.updateSliderPos(), e.scroll(t);
        else {
            if (r.ratio < 1) {
                var i = r.ratio + 1 / (r.num_eles - 1);
                i > 1 && (i = 1), r.ratio = i
            }
            e.updateSliderPos(), e.drag(t)
        }
    }, n._mouseup = function(t) {
        if (!this.options.disabled) {
            i = 0;
            var e = this;
            window.clearTimeout(e.mousedownTimeout), e.mousedownTimeout = null, window.clearInterval(e.mousedownInterval), e.mousedownInterval = null
        }
    }, n._setDragLimits = function() {
        var t = this.options;
        if ("vertical" == t.direction) {
            var e = this.element.offset().top + 17,
                r = e + t.length - 34 - this.slider_length;
            this.$slider.draggable("option", "containment", [0, e, 0, r])
        } else e = this.element.offset().left + 17, r = e + t.length - 34 - this.slider_length, this.$slider.draggable("option", "containment", [e, 0, r, 0])
    }, n._validateCurPos = function() {
        var t = this.options;
        t.cur_pos >= t.num_eles && (t.cur_pos = t.num_eles - 1), t.cur_pos < 0 && (t.cur_pos = 0)
    }, n._updateSliderPosRatio = function() {
        var t = this,
            e = this.options,
            r = e.direction,
            n = e.ratio,
            i = t.$slider[0],
            o = e.length - 34 - this.slider_length;
        if (null == n) throw "updateSliderPosRatio ratio N/A";
        var a = n * o + 17;
        "vertical" == r ? i.style.top = a + "px" : i.style.left = a + "px"
    }, n._updateSliderPosCurPos = function() {
        var t = this.options,
            e = this.$slider[0],
            r = this.scroll_space * t.cur_pos / (t.num_eles - 1);
        "vertical" == t.direction ? e.style.top = 17 + r + "px" : e.style.left = 17 + r + "px"
    }, n.updateSliderPos = function() {
        var t = this.options;
        if (void 0 === t.steps) throw "updateSliderPos. steps N/A";
        t.steps ? this._updateSliderPosCurPos() : this._updateSliderPosRatio()
    }, n.scroll = function(t) {
        var e = this.options;
        this._trigger("scroll", t, {
            cur_pos: e.cur_pos,
            num_eles: e.num_eles
        })
    }, n.drag = function(t) {
        var e = this,
            r = e.options;
        this._trigger("drag", t, {
            ratio: r.ratio
        })
    }, n._pageRight = function(t) {
        this._trigger("pageRight", t, null)
    }, n._pageLeft = function(t) {
        this._trigger("pageLeft", t, null)
    }, n._setSliderBgLength = function() {
        var e = this.options,
            r = e.theme,
            n = this.$slider,
            i = e.length,
            o = 40 * e.num_eles + i,
            a = i - 34,
            l = a * i / o,
            s = Math.round((l - 14) / 2);
        1 > s && (s = 1);
        var d = 14 + 2 * s;
        if (this.scroll_space = e.length - 34 - d, d !== this.slider_length) {
            this.slider_length = d;
            var c = "vertical" === e.direction ? {
                dim: "height",
                cls: ".vert-slider-bg"
            } : {
                dim: "width",
                cls: ".horiz-slider-bg"
            };
            r ? n[c.dim](d - 2) : (t(c.cls, this.element)[c.dim](s), n[c.dim](d))
        }
    }, n._updateCurPosAndTrigger = function(t, e) {
        var r = this,
            n = this.options,
            i = n.direction,
            o = r.$slider;
        null == e && (e = parseInt(o[0].style["vertical" === i ? "top" : "left"]));
        var a = n.length - 34 - this.slider_length,
            l = (e - 17) / a;
        if (n.steps) {
            var s = l * (n.num_eles - 1);
            if (s = Math.round(s), n.cur_pos != s) {
                if (this.dragging) {
                    if (null != this.topWhileDrag) {
                        if (this.topWhileDrag < e && n.cur_pos > s) return;
                        if (this.topWhileDrag > e && n.cur_pos < s) return
                    }
                    this.topWhileDrag = e
                }
                r.options.cur_pos = s, this.scroll(t)
            }
        } else n.ratio = l, this.drag(t)
    }, n._setNormalPace = function(t) {
        this.timer && (window.clearInterval(this.timer), this.timer = null);
        var e = this,
            r = this.options,
            n = r.direction;
        e.timer = window.setInterval(function() {
            var r = e.$slider,
                i = parseInt(r[0].style["vertical" === n ? "top" : "left"]);
            e.prev_top === i && (e._updateCurPosAndTrigger(t, i), window.clearInterval(e.timer), e.timer = null), e.prev_top = i
        }, 20)
    }, n.refresh = function() {
        var t = this.options,
            e = this.$slider[0],
            r = this.element;
        return t.num_eles <= 1 ? void(r[0].style.display = "none") : (r[0].style.display = "", this._validateCurPos(), this.dragging || (r["vertical" === t.direction ? "height" : "width"](t.length), this._setSliderBgLength(), this.scroll_space < 4 || t.num_eles <= 1 ? e.style.display = "none" : e.style.display = ""), void this.updateSliderPos())
    }, n._trigger = r._trigger, n.on = r.on, n.one = r.one, n.off = r.off
}(jQuery),
function(t) {
    "use strict";

    function e(t) {
        return "<span class='btn btn-xs glyphicon glyphicon-" + t + "' ></span>"
    }

    function r(t) {
        return "<span class='ui-widget-header pq-ui-button'><span class='ui-icon ui-icon-" + t + "'></span></span>"
    }
    var n = function() {};
    n.prototype = {
        belongs: function(t) {
            return t.target == this.that.element[0] ? !0 : void 0
        },
        setTimer: function(t, e) {
            var r = this;
            clearTimeout(r._timeID), r._timeID = setTimeout(function() {
                t()
            }, e)
        }
    };
    var i = t.paramquery;
    i.cClass = n;
    var o = {
        widgetEventPrefix: "pqgrid"
    };
    o._create = function() {
        var e = this,
            r = this.options,
            n = this.element,
            o = r.dataModel,
            a = r.bootstrap,
            l = a.on,
            s = r.roundCorners && !l,
            d = r.ui,
            c = r.sortModel;
        if (t(document).triggerHandler("pqGrid:bootup", {
                instance: this
            }), this.BS_on = l, r.collapsible || (r.collapsible = {
                on: !1,
                collapsed: !1
            }), r.flexHeight && (r.height = "flex"), r.flexWidth && (r.width = "flex"), o.sortIndx) {
            c.on = r.sortable, c.type = o.sorting;
            var h = [],
                u = o.sortIndx,
                f = o.sortDir;
            if (t.isArray(u)) {
                for (var p = 0; p < u.length; p++) {
                    var g = f && f[p] ? f[p] : "up";
                    h.push({
                        dataIndx: u[p],
                        dir: g
                    })
                }
                c.single = !1
            } else {
                var g = f ? f : "up";
                h.push({
                    dataIndx: u,
                    dir: g
                }), c.single = !0
            }
            c.sorter = h
        }
        r.virtualXHeader === !1 && (r.virtualXHeader = null),
            this.iGenerateView = new i.cGenerateView(this), this.iRefresh = new i.cRefresh(this), this.iKeyNav = new i.cKeyNav(this), this.iValid = new i.cValid(this), this.tables = [], this.$tbl = null, this.iColModel = new i.cColModel(this), this.iSort = new i.cSort(this), this.iHeader = new i.cHeader(this), this._initTypeColumns(), n.on("scroll" + this.eventNamespace, function() {
                this.scrollLeft = 0, this.scrollTop = 0
            });
        var v = l ? a.grid : d.grid,
            m = l ? "" : d.header_o,
            w = l ? "" : d.bottom,
            x = l ? a.top : d.top;
        n.empty().addClass("pq-grid " + v + " " + (s ? " ui-corner-all" : "")).html(["<div class='pq-grid-top ", x, " ", s ? " ui-corner-top" : "", "'>", "<div class='pq-grid-title", s ? " ui-corner-top" : "", "'>&nbsp;</div>", "</div>", "<div class='pq-grid-center' >", "<div class='pq-header-outer ", m, "'>", "</div>", "<div class='pq-grid-cont-outer' >", "<div class='pq-grid-cont'></div>", "</div>", "</div>", "<div class='pq-grid-bottom ", w, " ", s ? " ui-corner-bottom" : "", "'>", "<div class='pq-grid-footer'></div>", "</div>"].join("")), this.$bottom = t("div.pq-grid-bottom", n), this._trigger("render", null, {
            dataModel: this.options.dataModel,
            colModel: this.colModel
        }), this.$top = t("div.pq-grid-top", n), r.showTop || this.$top.css("display", "none"), this.$title = t("div.pq-grid-title", n), r.showTitle || this.$title.css("display", "none");
        var y = this.$grid_center = t("div.pq-grid-center", n).on("scroll", function() {
            this.scrollTop = 0
        });
        this.$header_o = t("div.pq-header-outer", this.$grid_center).on("scroll", function() {
            this.scrollTop = 0, this.scrollLeft = 0
        }), this.$footer = t("div.pq-grid-footer", n), this.$cont_o = t(".pq-grid-cont-outer", y);
        var _ = this.$cont = t("div.pq-grid-cont", y);
        t(window).bind("resize" + e.eventNamespace + " orientationchange" + e.eventNamespace, function(t, r) {
            e.onWindowResize(t, r)
        }), _.on("click", ".pq-grid-cell,.pq-grid-number-cell", function(r) {
            return t.data(r.target, e.widgetName + ".preventClickEvent") !== !0 && e.evtBelongs(r) ? e._onClickCell(r) : void 0
        }), _.on("click", "tr.pq-grid-row", function(r) {
            return t.data(r.target, e.widgetName + ".preventClickEvent") !== !0 && e.evtBelongs(r) ? e._onClickRow(r) : void 0
        }).on("contextmenu", "td.pq-grid-cell", function(t) {
            return e.evtBelongs(t) ? e._onRightClickCell(t) : void 0
        }).on("contextmenu", "tr.pq-grid-row", function(t) {
            return e.evtBelongs(t) ? e._onRightClickRow(t) : void 0
        }).on("dblclick", "td.pq-grid-cell", function(t) {
            return e.evtBelongs(t) ? e._onDblClickCell(t) : void 0
        }).on("dblclick", "tr.pq-grid-row", function(t) {
            return e.evtBelongs(t) ? e._onDblClickRow(t) : void 0
        }), _.on("blur", function() {
            e.onblur()
        }).on("focus", function(t) {
            e.onfocus(t)
        }).on("focusout", function(t) {}).on("focusin", function(t) {}).on("mousedown", e._onMouseDown(e)).on("change", e._onChange(e)), _.on("mouseenter", "td.pq-grid-cell", e._onCellMouseEnter(e)).on("mouseenter", "tr.pq-grid-row", e._onRowMouseEnter(e)).on("mouseleave", "td.pq-grid-cell", e._onCellMouseLeave(e)).on("mouseleave", "tr.pq-grid-row", e._onRowMouseLeave(e)).on("keyup", e._onKeyUp(e)), y.bind("mousewheel DOMMouseScroll", e._onMouseWheel(e));
        this.$hvscroll = t("<div class='pq-hvscroll-square ui-widget-content'></div>").appendTo(y);
        var b = t("<div class='pq-vscroll'></div>").appendTo(y);
        this.prevVScroll = 0;
        var I = r.scrollModel;
        void 0 === I.lastColumn && r.virtualX && (I.lastColumn = "auto"), this.vscroll = pq.scrollbar(b, {
            pace: I.pace,
            theme: I.theme,
            bootstrap: r.bootstrap,
            steps: r.virtualY,
            direction: "vertical",
            cur_pos: 0,
            pageRight: function() {
                e.iKeyNav.pageDown()
            },
            pageLeft: function() {
                e.iKeyNav.pageUp()
            },
            drag: function(t, r) {
                e.iMouseSelection.syncViewWithScrollBarVert(r.ratio)
            },
            scroll: function(t, r) {
                e.iRefresh.refreshVscroll(r)
            }
        });
        var C = t("<div class='pq-hscroll'></div>").appendTo(y);
        "flex" === r.height && C.css("position", "relative"), this.hscroll = pq.scrollbar(C, {
            direction: "horizontal",
            pace: I.pace,
            bootstrap: r.bootstrap,
            theme: I.theme,
            steps: r.virtualX,
            cur_pos: 0,
            drag: function(t, r) {
                e.iMouseSelection.syncViewWithScrollBarHor(r.ratio)
            },
            scroll: function() {
                r.virtualX && e.refresh({
                    skipColWidths: !0
                })
            }
        }), r.selectionModel["native"] || this.disableSelection(), y.bind("keydown.pq-grid", e._onKeyPressDown(e)), this._refreshTitle(), this.iRows = new i.cRows(this), this.generateLoading(), this._initPager(), this._refreshResizable(), this._refreshDraggable(), this.iResizeColumns = new i.cResizeColumns(this), this._mouseInit()
    }, o._mouseDown = function(e) {
        var r = this;
        return t(e.target).closest(".pq-editor-focus").length ? (this._blurEditMode = !0, void window.setTimeout(function() {
            r._blurEditMode = !1
        }, 0)) : (this._saveDims(), this._mousePQUpDelegate = function(t) {
            return r._mousePQUp(t)
        }, t(document).bind("mouseup" + this.eventNamespace, this._mousePQUpDelegate), this._super(e))
    }, o.destroy = function() {
        this._trigger("destroy"), this._super(), window.clearInterval(this._refreshEditorPosTimer), this.autoResizeTimeout && clearTimeout(this.autoResizeTimeout);
        for (var e in this) delete this[e];
        this.options = void 0, t.fragments = {}
    }, o._setOption = function(t, e) {
        var r = this.options,
            n = r.dataModel;
        if ("height" === t) {
            if ("flex" === e) {
                var i = this.vscroll;
                e && i && i.widget().hasClass("pq-sb-vert") && (r.virtualY ? i.option("cur_pos", 0) : i.option("ratio", 0)), this.hscroll.widget().css("position", "relative")
            } else "flex" === r.height && this.hscroll.widget().css("position", "");
            r[t] = e
        } else if ("width" === t && "flex" == e) {
            r[t] = e;
            var o = this.hscroll;
            e && o && o.widget().hasClass("pq-sb-horiz") && (r.virtualX ? o.option("cur_pos", 0) : o.option("ratio", 0))
        } else if ("title" == t) r[t] = e, this._refreshTitle();
        else if ("roundCorners" == t) r[t] = e, e ? (this.element.addClass("ui-corner-all"), this.$top.addClass("ui-corner-top"), this.$bottom.addClass("ui-corner-bottom")) : (this.element.removeClass("ui-corner-all"), this.$top.removeClass("ui-corner-top"), this.$bottom.removeClass("ui-corner-bottom"));
        else if ("virtualX" == t) r[t] = e, this.hscroll.option("steps", e);
        else if ("virtualY" == t) r[t] = e, this.vscroll.option("steps", e);
        else if ("freezeCols" == t) e = parseInt(e), !isNaN(e) && e >= 0 && e <= this.colModel.length - 2 && (r[t] = e);
        else if ("freezeRows" == t) e = parseInt(e), !isNaN(e) && e >= 0 && (r[t] = e);
        else if ("resizable" == t) r[t] = e, this._refreshResizable();
        else if ("draggable" == t) r[t] = e, this._refreshDraggable();
        else if ("scrollModel" == t) r[t] = e;
        else if ("dataModel" == t) e.data !== n.data && n.dataUF && (n.dataUF.length = 0), r[t] = e;
        else {
            if ("groupModel" == t) throw "use groupOption() to set groupModel options.";
            if ("treeModel" == t) throw "use treeOption() to set treeModel options.";
            if ("pageModel" == t) r[t] = e;
            else if ("selectionModel" === t) r[t] = e;
            else if ("colModel" === t || "columnTemplate" == t) r[t] = e, this.iColModel.init();
            else if ("disabled" === t) this._super(t, e), e === !0 ? this._disable() : this._enable();
            else if ("numberCell" === t) r[t] = e, this.iRefresh.decidePanes();
            else if ("strLoading" === t) r[t] = e, this._refreshLoadingString();
            else if ("showTop" === t) r[t] = e, this.$top.css("display", e ? "" : "none");
            else if ("showTitle" === t) r[t] = e, this.$title.css("display", e ? "" : "none");
            else if ("showToolbar" === t) {
                r[t] = e;
                var a = this._toolbar.widget();
                a.css("display", e ? "" : "none")
            } else "toolbar" == t ? r[t] = e : "collapsible" === t ? (r[t] = e, this._createCollapse()) : "showBottom" === t ? (r[t] = e, this.$bottom.css("display", e ? "" : "none")) : r[t] = e
        }
        return this
    }, o.options = {
        cancel: "input,textarea,button,select,option,.pq-no-capture,.ui-resizable-handle",
        trigger: !1,
        bootstrap: {
            on: !1,
            thead: "table table-striped table-condensed table-bordered",
            tbody: "table table-condensed",
            grid: "panel panel-default",
            top: "",
            btn: "btn btn-default",
            groupModel: {
                icon: ["glyphicon-triangle-bottom", "glyphicon-triangle-right"]
            },
            header_active: "active"
        },
        ui: {
            on: !0,
            grid: "ui-widget ui-widget-content",
            top: "ui-widget-header",
            bottom: "ui-widget-header",
            header_o: "ui-widget-header",
            header: "ui-state-default",
            header_active: "ui-state-active"
        },
        cls: {
            cont_inner: "pq-grid-cont-inner",
            cont_inner_b: "pq-grid-cont-inner-b"
        },
        distance: 3,
        collapsible: {
            on: !0,
            toggle: !0,
            collapsed: !1,
            _collapsed: !1,
            refreshAfterExpand: !0,
            css: {
                zIndex: 1e3
            }
        },
        colModel: null,
        columnBorders: !0,
        dataModel: {
            data: [],
            dataUF: [],
            cache: !1,
            dataType: "JSON",
            location: "local",
            sorting: "local",
            sortDir: "up",
            method: "GET"
        },
        direction: "",
        draggable: !1,
        editable: !0,
        editModel: {
            cellBorderWidth: 0,
            pressToEdit: !0,
            clicksToEdit: 2,
            filterKeys: !0,
            keyUpDown: !0,
            reInt: /^([\-]?[1-9][0-9]*|[\-]?[0-9]?)$/,
            reFloat: /^[\-]?[0-9]*\.?[0-9]*$/,
            onBlur: "validate",
            saveKey: t.ui.keyCode.ENTER,
            onSave: "nextFocus",
            onTab: "nextFocus",
            allowInvalid: !1,
            invalidClass: "pq-cell-red-tr pq-has-tooltip",
            warnClass: "pq-cell-blue-tr pq-has-tooltip",
            validate: !0
        },
        editor: {
            select: !1,
            type: "textbox"
        },
        summaryOptions: {
            number: "avg,max,min,stdev,stdevp,sum",
            date: "count,max,min",
            string: "count"
        },
        summaryTitle: {
            avg: "Avg: {0}",
            count: "Count: {0}",
            max: "Max: {0}",
            min: "Min: {0}",
            stdev: "Stdev: {0}",
            stdevp: "Stdevp: {0}",
            sum: "Sum: {0}"
        },
        validation: {
            icon: "ui-icon-alert",
            cls: "ui-state-error",
            style: "padding:3px 10px;"
        },
        warning: {
            icon: "ui-icon-info",
            cls: "",
            style: "padding:3px 10px;"
        },
        freezeCols: 0,
        freezeRows: 0,
        freezeBorders: !0,
        calcDataIndxFromColIndx: !0,
        height: 400,
        hoverMode: "null",
        _maxColWidth: "99%",
        _minColWidth: 50,
        minWidth: 100,
        numberCell: {
            width: 30,
            title: "",
            resizable: !0,
            minWidth: 30,
            maxWidth: 100,
            show: !0
        },
        pageModel: {
            curPage: 1,
            totalPages: 0,
            rPP: 10,
            rPPOptions: [10, 20, 50, 100]
        },
        resizable: !1,
        roundCorners: !0,
        rowBorders: !0,
        rowHeight: 25,
        scrollModel: {
            pace: "fast",
            smooth: !1,
            horizontal: !0,
            lastColumn: "auto",
            autoFit: !1,
            theme: !0
        },
        selectionModel: {
            type: "cell",
            onTab: "nextFocus",
            row: !0,
            mode: "block"
        },
        swipeModel: {
            on: "touch",
            speed: 20,
            ratio: .15,
            repeat: 20
        },
        showBottom: !0,
        showHeader: !0,
        showTitle: !0,
        showToolbar: !0,
        showTop: !0,
        sortable: !0,
        sql: !1,
        stripeRows: !0,
        title: "&nbsp;",
        treeModel: null,
        virtualX: !1,
        virtualY: !1,
        width: "auto",
        wrap: !0,
        hwrap: !0
    };
    var a = {
        strAdd: "Add",
        strDelete: "Delete",
        strEdit: "Edit",
        strGroup_header: "Drag a column here to group by that column",
        strGroup_merge: "Merge cells",
        strGroup_fixCols: "Fix columns",
        strGroup_grandSummary: "Grand summary",
        strLoading: "Loading",
        strNoRows: "No rows to display."
    };
    t.extend(!0, o.options, a), t.widget("paramquery._pqGrid", t.ui.mouse, o);
    var l = i._pqGrid.prototype;
    l.refreshCM = function(t) {
        t ? this.option("colModel", t) : this.iColModel.init()
    }, l.evtBelongs = function(e) {
        return t(e.target).closest(".pq-grid")[0] == this.element[0]
    }, l.readCell = function(t, e, r, n, i) {
        return r && r.isRootCell(n, i, "o") === !1 ? void 0 : t[e.dataIndx]
    }, l.saveCell = function(t, e, r) {
        var n = e.dataIndx;
        t[n] = r
    }, l._destroyResizable = function() {
        var t = this.element,
            e = t.data();
        (e.resizable || e.uiResizable || e["ui-resizable"]) && t.resizable("destroy")
    }, l._disable = function() {
        null == this.$disable && (this.$disable = t("<div class='pq-grid-disable'></div>").css("opacity", .2).appendTo(this.element))
    }, l._enable = function() {
        this.$disable && (this.element[0].removeChild(this.$disable[0]), this.$disable = null)
    }, l._destroy = function() {
        this.loading && this.xhr.abort(), this._destroyResizable(), this._destroyDraggable(), this._mouseDestroy(), this.element.off(this.eventNamespace), t(window).unbind(this.eventNamespace), t(document).unbind(this.eventNamespace), this.element.empty().css("height", "").css("width", "").removeClass("pq-grid ui-widget ui-widget-content ui-corner-all").removeData()
    }, l.addColumn = function(t) {
        var e = t.columns || [t.column],
            r = this.options,
            n = r.colModel,
            i = n.concat(e);
        this.refreshCM(i), this._trigger("addColumn"), t.refresh !== !1 && this.refresh()
    }, l.deleteColumn = function(t) {
        for (var e = t.colList || [{
                colIndx: t.colIndx
            }], r = t.history !== !1, n = this.options, i = n.colModel, o = e.length - 1; o >= 0; o--) {
            var a = e[o],
                l = a.colIndx,
                s = i.splice(l, 1)[0];
            a.column = s
        }
        this.iColModel.init(), r && (this.iHistory.increment(), e.type = "delete", this.iHistory.push({
            colList: e
        })), this._trigger("deleteColumn", null, {
            colList: e
        }), t.refresh !== !1 && this.refreshView()
    }, l._onKeyUp = function(t) {
        return function(e) {
            t.evtBelongs(e) && t._trigger("keyUp", e, null)
        }
    }, l.onKeyPressDown = function(e) {
        var r = this,
            n = t(e.target).closest(".pq-grid-header");
        return n.length ? r._trigger("headerKeyDown", e, null) : void(r.iKeyNav.bodyKeyPressDown(e) !== !1 && 0 == r._trigger("keyDown", e, null))
    }, l._onKeyPressDown = function(t) {
        return function(e) {
            t.evtBelongs(e) && t.onKeyPressDown(e, t)
        }
    }, l.collapse = function(t) {
        var e = this,
            r = this.element,
            n = this.options,
            i = n.collapsible,
            o = i.$collapse.children("span"),
            a = function() {
                r.css("overflow", "hidden"), o.addClass("ui-icon-circle-triangle-s").removeClass("ui-icon-circle-triangle-n"), r.hasClass("ui-resizable") && r.resizable("destroy"), e._toolbar && e._toolbar.disable(), i.collapsed = !0, i._collapsed = !0, i.animating = !1, e._trigger("collapse")
            };
        return t = t ? t : {}, i._collapsed ? !1 : (i.htCapture = r.height(), void(t.animate === !1 ? (r.height(23), a()) : (i.animating = !0, r.animate({
            height: "23px"
        }, function() {
            a()
        }))))
    }, l.expand = function(t) {
        var e = this,
            r = this.element,
            n = this.options,
            i = n.collapsible,
            o = i.htCapture,
            a = i.$collapse.children("span"),
            l = function() {
                r.css("overflow", ""), i._collapsed = !1, i.collapsed = !1, e._refreshResizable(), i.refreshAfterExpand && e.refresh(), a.addClass("ui-icon-circle-triangle-n").removeClass("ui-icon-circle-triangle-s"), e._toolbar && e._toolbar.enable(), i.animating = !1, e._trigger("expand")
            };
        return t = t ? t : {}, i._collapsed === !1 ? !1 : void(t.animate === !1 ? (r.height(o), l()) : (i.animating = !0, r.animate({
            height: o
        }, function() {
            l()
        })))
    }, l._createCollapse = function() {
        var n = this,
            i = this.$top,
            o = this.options,
            a = this.BS_on,
            l = o.collapsible;
        if (!l.$stripe) {
            var s = t(["<div class='pq-slider-icon pq-no-capture'  >", "</div>"].join("")).appendTo(i);
            l.$stripe = s
        }
        l.on ? l.$collapse || (l.$collapse = t(a ? e("collapse-down") : r("circle-triangle-n")).appendTo(l.$stripe).click(function(t) {
            l.collapsed ? n.expand() : n.collapse()
        })) : l.$collapse && (l.$collapse.remove(), delete l.$collapse), l.collapsed && !l._collapsed ? n.collapse({
            animate: !1
        }) : !l.collapsed && l._collapsed && n.expand({
            animate: !1
        }), l.toggle ? l.$toggle || (l.$toggle = t(a ? e("fullscreen") : r("arrow-4-diag")).prependTo(l.$stripe).click(function(t) {
            n.toggle()
        })) : l.$toggle && (l.$toggle.remove(), delete l.$toggle)
    }, l.toggle = function() {
        var e, r = this.options,
            n = r.collapsible,
            i = this.element,
            o = this._maxim,
            e = o ? "min" : "max",
            a = t(document.body);
        if (this._trigger("beforeToggle", null, {
                state: e
            }) === !1) return !1;
        if ("min" == e) {
            var l = o.eleObj,
                s = o.docObj;
            this.option({
                height: l.height,
                width: l.width,
                maxHeight: l.maxHeight,
                maxWidth: l.maxWidth
            }), i[0].style.cssText = l.cssText, a[0].style.cssText = s.cssText, t("html").css({
                overflow: "visible"
            }), window.scrollTo(s.scrollLeft, s.scrollTop), this._maxim = null
        } else {
            var l = {
                height: r.height,
                width: r.width,
                cssText: i[0].style.cssText,
                maxHeight: r.maxHeight,
                maxWidth: r.maxWidth
            };
            this.option({
                height: "100%",
                width: "100%",
                maxHeight: null,
                maxWidth: null
            }), i.css(t.extend({
                position: "fixed",
                left: 0,
                top: 0,
                margin: 0
            }, n.css));
            var s = {
                scrollLeft: t(window).scrollLeft(),
                scrollTop: t(window).scrollTop(),
                cssText: a[0].style.cssText
            };
            a.css({
                height: 0,
                width: 0,
                overflow: "hidden",
                position: "static"
            }), t("html").css({
                overflow: "hidden"
            }), window.scrollTo(0, 0), this._maxim = {
                eleObj: l,
                docObj: s
            }
        }
        this._trigger("toggle", null, {
            state: e
        }), this._refreshResizable(), this.refresh(), t(window).trigger("resize", {
            $grid: i,
            state: e
        })
    }, l._mouseCapture = function(e) {
        var r = this.options;
        if (!e.target) return !1;
        if (this.evtBelongs(e)) {
            var n = r.swipeModel;
            return 0 != n.on && ("touch" != n.on || t.support.touch)
        }
        return !1
    }, l._saveDims = function() {
        var e = (this.$cont, this.$tbl),
            r = this.$tbl_header;
        if (e)
            for (var n = 0; n < e.length; n++) {
                var i = e[n],
                    o = t(i);
                o.data("offsetHeight", Math.round(i.offsetHeight) - 1), o.data("scrollWidth", Math.round(i.scrollWidth))
            }
        if (r)
            for (var n = 0; n < r.length; n++) {
                var i = r[n],
                    a = t(i).parent();
                a.data("offsetHeight", Math.round(i.offsetHeight)), a.data("scrollWidth", Math.round(i.scrollWidth))
            }
    }, l._mousePQUp = function(e) {
        t(document).unbind("mouseup" + this.eventNamespace, this._mousePQUpDelegate), this._trigger("mousePQUp", e, null)
    }, l._mouseStart = function(t) {
        return this.blurEditor({
            force: !0
        }), !0
    }, l._mouseDrag = function(t) {
        return 0 != this._trigger("mouseDrag", t, null)
    }, l._mouseStop = function(t) {
        return 0 != this._trigger("mouseStop", t, null)
    }, l.onWindowResize = function(e, r) {
        var n, i, o = this,
            a = o.options,
            l = o.element,
            s = l.parent(),
            d = "_lastParentHt",
            c = "_lastParentWd",
            h = "autoResizeTimeout";
        if (r) {
            var u = r.$grid;
            if (u && (u == l || 0 == l.closest(u).length)) return
        }
        s.length && (s[0] == document.body || "fixed" == l.css("position") ? (n = window.innerHeight ? window.innerHeight : t(window).height(), i = t(window).width()) : (n = s.height(), i = s.width()), null != o[d] && n == o[d] && i == o[c] || (o[d] = n, o[c] = i, t.support.touch && a.editModel.indices && t(document.activeElement).is(".pq-editor-focus") || (o[h] && clearTimeout(o[h]), o[h] = window.setTimeout(function() {
            o._refreshAfterResize(), delete o[h]
        }, a.autoSizeInterval || 0))))
    }, l._onMouseWheel = function(t) {
        return function(e) {
            t._saveDims();
            var r = t.options,
                n = 0,
                i = !1,
                o = e.originalEvent,
                a = o.wheelDeltaX,
                l = o.wheelDeltaY,
                s = o.wheelDelta;
            if (a && Math.abs(a) > Math.abs(l)) {
                if ("flex" == r.width) return !0;
                i = !0, n = a / 120
            } else if (s) {
                if (!t.iRefresh.vscroll) return !0;
                n = s / 120
            } else if (o.detail) {
                if (!t.iRefresh.vscroll) return !0;
                n = -1 * o.detail / 3
            }
            n *= 3;
            var d = i ? t.hscroll : t.vscroll,
                c = parseInt(d.option("cur_pos")),
                h = parseInt(d.option("num_eles"));
            if (!r.scrollModel.smooth && (i && r.virtualX || !i && r.virtualY)) {
                n = n > 0 ? Math[1 > n ? "ceil" : "floor"](n) : Math[-1 > n ? "ceil" : "floor"](n);
                var u = c - n;
                if (0 > u ? u = 0 : u > h - 1 && (u = h - 1), u == c) return !0;
                d.option("cur_pos", c - n), d.scroll()
            } else {
                var f = d.option("ratio"),
                    p = f - n / (h - 1);
                if (p > 1 ? p = 1 : 0 > p && (p = 0), f == p) return !0;
                d.option("ratio", p), d.drag()
            }
            return !1
        }
    }, l._onDblClickCell = function(e) {
        var r = this,
            n = t(e.currentTarget),
            i = r.getCellIndices({
                $td: n
            }),
            o = i.rowIndxPage,
            a = r.riOffset,
            l = o + a,
            s = i.colIndx;
        if (null != s) return 0 == r._trigger("cellDblClick", e, {
            $td: n,
            rowIndxPage: o,
            rowIndx: l,
            colIndx: s,
            column: r.colModel[s],
            rowData: r.pdata[o]
        }) ? !1 : void(r.options.editModel.clicksToEdit > 1 && this.isEditableRow({
            rowIndx: l
        }) && this.isEditableCell({
            colIndx: s,
            rowIndx: l
        }) && r.editCell({
            rowIndxPage: o,
            colIndx: s
        }))
    }, l._onClickCont = function(t) {}, l._onClickRow = function(e) {
        var r = this,
            n = t(e.currentTarget),
            i = parseInt(n.attr("pq-row-indx")),
            o = r.riOffset,
            a = i + o;
        if (!isNaN(i)) {
            this.options;
            return 0 == r._trigger("rowClick", e, {
                $tr: n,
                rowIndxPage: i,
                rowIndx: a,
                rowData: r.pdata[i]
            }) ? !1 : void 0
        }
    }, l._onRightClickRow = function(e) {
        var r = this,
            n = t(e.currentTarget),
            i = parseInt(n.attr("pq-row-indx")),
            o = r.riOffset,
            a = i + o;
        if (!isNaN(i)) {
            this.options;
            return 0 == r._trigger("rowRightClick", e, {
                $tr: n,
                rowIndxPage: i,
                rowIndx: a,
                rowData: r.pdata[i]
            }) ? !1 : void 0
        }
    }, l._onDblClickRow = function(e) {
        var r = this,
            n = t(e.currentTarget),
            i = parseInt(n.attr("pq-row-indx")),
            o = r.riOffset,
            a = i + o;
        return 0 == r._trigger("rowDblClick", e, {
            $tr: n,
            rowIndxPage: i,
            rowIndx: a,
            rowData: r.pdata[i]
        }) ? !1 : void 0
    }, l.getValueFromDataType = function(e, r, n) {
        if ("=" == (e + "")[0]) return e;
        var i;
        if ("date" == r) return i = Date.parse(e), isNaN(i) ? "" : n ? i : e;
        if ("object" == r) return e;
        if ("integer" == r) i = parseInt(e);
        else {
            if ("float" != r) return "bool" == r ? (i = t.trim(e).toLowerCase(), 0 == i.length ? null : "true" == i || "yes" == i || "1" == i ? !0 : "false" == i || "no" == i || "0" == i ? !1 : Boolean(i)) : null == e ? e : t.trim(e);
            i = parseFloat(e)
        }
        return isNaN(i) || null == i ? null == e ? e : null : i
    }, l.isValid = function(t) {
        return this.iValid.isValid(t)
    }, l.isValidChange = function(t) {
        t = t || {};
        var e = this.getChanges(),
            r = e.addList,
            n = e.updateList,
            i = n.concat(r);
        return t.data = i, this.isValid(t)
    }, l.isEditableRow = function(t) {
        var e = this.options.editable;
        return null != e ? "function" == typeof e ? e.call(this, this.normalize(t)) : e : !0
    }, l.isEditableCell = function(t) {
        var e, r, n = t.column;
        return n || (e = this.normalize(t), n = e.column), r = n.editable, t.checkVisible && n.hidden ? !1 : null != r ? "function" == typeof r ? (e = e || this.normalize(t), this.callFn(r, e)) : r : !0
    }, l._onMouseDownCont = function(t) {
        this.blurEditor({
            blurIfFocus: !0
        });
        var e, r, n = this;
        return n._trigger("contMouseDown", t, null) === !1 ? !1 : (e = n.pdata, e && e.length || (r = n.$cont[0], r.setAttribute("tabindex", 0), r.focus()), !0)
    }, l._onMouseDown = function(e) {
        return function(r) {
            if (1 == r.which && e.evtBelongs(r)) {
                var n, i = t(r.target),
                    o = i.closest(".pq-grid-cell,.pq-grid-number-cell:not(.pq-detail-child)");
                if (o.length && (r.currentTarget = o[0], n = e._onMouseDownCell(r), n === !1)) return !1;
                if (r.isPropagationStopped()) return;
                var a = i.closest(".pq-grid-row");
                if (a.length && (r.currentTarget = a[0], n = e._onMouseDownRow(r), n === !1)) return !1;
                if (r.isPropagationStopped()) return;
                return e._onMouseDownCont(r)
            }
        }
    }, l._onMouseDownCell = function(e) {
        var r, n = this,
            i = t(e.currentTarget),
            o = n.getCellIndices({
                $td: i
            });
        return null != o.rowIndx ? (o = this.iMerge.getRootCell(o.rowIndx, o.colIndx, "o"), r = n.normalize(o), r.$td = i, 0 != n._trigger("cellMouseDown", e, r)) : void 0
    }, l._onMouseDownRow = function(e) {
        var r = this,
            n = t(e.currentTarget),
            i = r.getRowIndx({
                $tr: n
            });
        return i.$tr = n, 0 != r._trigger("rowMouseDown", e, i)
    }, l._onCellMouseEnter = function(e) {
        return function(r) {
            if (e.evtBelongs(r)) {
                var n = t(this),
                    i = e.options,
                    o = e.getCellIndices({
                        $td: n
                    });
                if (null == o.rowIndx || null == o.colIndx) return;
                return e._trigger("cellMouseEnter", r, o) === !1 ? !1 : ("cell" == i.hoverMode && e.highlightCell(n), !0)
            }
        }
    }, l._onChange = function(e) {
        function r() {
            if (n && i && i.target == n.target) {
                var t, r = {
                    ctrlKey: 0,
                    metaKey: 0,
                    shiftKey: 0,
                    altKey: 0
                };
                for (t in r) i[t] = n[t];
                e._trigger("valChange", i, o), i = n = void 0
            }
        }
        var n, i, o;
        return e.on("cellClickDone", function(t) {
                n = t.originalEvent, r()
            }),
            function(n) {
                if (e.evtBelongs(n)) {
                    var a = t(n.target),
                        l = a.closest(".pq-grid-cell");
                    l.length && (o = e.getCellIndices({
                        $td: l
                    }), o = e.normalize(o), o.input = a[0], i = n, r())
                }
            }
    }, l._onRowMouseEnter = function(e) {
        return function(r) {
            if (e.evtBelongs(r)) {
                var n = t(this),
                    i = e.options,
                    o = e.getRowIndx({
                        $tr: n
                    }),
                    a = o.rowIndxPage;
                return e._trigger("rowMouseEnter", r, o) === !1 ? !1 : ("row" == i.hoverMode && e.highlightRow(a), !0)
            }
        }
    }, l._onCellMouseLeave = function(e) {
        return function(r) {
            if (e.evtBelongs(r)) {
                var n = t(this);
                return "cell" == e.options.hoverMode && e.unHighlightCell(n), !0
            }
        }
    }, l._onRowMouseLeave = function(e) {
        return function(r) {
            if (e.evtBelongs(r)) {
                var n = t(this),
                    i = e.getRowIndx({
                        $tr: n
                    }),
                    o = i.rowIndxPage;
                return e._trigger("rowMouseLeave", r, {
                    $tr: n,
                    rowIndx: i.rowIndx,
                    rowIndxPage: o
                }) === !1 ? !1 : ("row" == e.options.hoverMode && e.unHighlightRow(o), !0)
            }
        }
    }, l.enableSelection = function() {
        this.element.removeClass("pq-disable-select").off("selectstart" + this.eventNamespace)
    }, l.disableSelection = function() {
        this.element.addClass("pq-disable-select").on("selectstart" + this.eventNamespace, function(e) {
            var r = e.target;
            if (r) {
                var n = t(e.target);
                return n.is("input,textarea,select") ? !0 : n.closest(".pq-native-select").length ? !0 : void e.preventDefault()
            }
        })
    }, l._onClickCell = function(e) {
        var r = this,
            n = this.options,
            i = n.editModel,
            o = t(e.currentTarget),
            a = r.getCellIndices({
                $td: o
            }),
            l = this.iMerge.getRootCell(a.rowIndx, a.colIndx, "o"),
            s = this.normalize(l),
            d = s.rowIndx,
            c = s.colIndx;
        return s.$td = o, s.evt = e, 0 == r._trigger("beforeCellClick", e, s) ? !1 : (r._trigger("cellClick", e, s), void(null == c || 0 > c || 1 == i.clicksToEdit && this.isEditableRow({
            rowIndx: d
        }) && this.isEditableCell({
            colIndx: c,
            rowIndx: d
        }) && r.editCell(s)))
    }, l._onRightClickCell = function(e) {
        var r = t(e.currentTarget),
            n = this.getCellIndices({
                $td: r
            }),
            i = this,
            o = n.rowIndxPage,
            a = this.riOffset,
            l = o + a,
            s = n.colIndx,
            d = this.colModel,
            c = this.options;
        c.DM;
        if (null != s) {
            var h = d[s],
                u = h.dataIndx;
            return 0 == this._trigger("cellRightClick", e, {
                $td: r,
                rowIndxPage: o,
                rowIndx: l,
                colIndx: s,
                dataIndx: u,
                column: h,
                rowData: i.pdata[o]
            }) ? !1 : void 0
        }
    }, l.highlightCell = function(t) {
        t.addClass("pq-grid-cell-hover ui-state-hover")
    }, l.unHighlightCell = function(t) {
        t.removeClass("pq-grid-cell-hover ui-state-hover")
    }, l.highlightRow = function(t) {
        if (isNaN(t));
        else {
            var e = this.getRow({
                rowIndxPage: t
            });
            e && e.addClass("pq-grid-row-hover ui-state-hover")
        }
    }, l.unHighlightRow = function(t) {
        if (isNaN(t));
        else {
            var e = this.getRow({
                rowIndxPage: t
            });
            e && e.removeClass("pq-grid-row-hover ui-state-hover")
        }
    }, l._getCreateEventData = function() {
        return {
            dataModel: this.options.dataModel,
            data: this.pdata,
            colModel: this.options.colModel
        }
    }, l._findCellFromEvt = function(e) {
        var r = t(e.target),
            n = r.closest(".pq-grid-cell");
        if (null == n || 0 == n.length) return {
            rowIndxPage: null,
            colIndx: null,
            $td: null
        };
        var i = this.getCellIndices({
            $td: n
        });
        return i.$td = n, i
    }, l._initPager = function() {
        var e = this,
            r = e.options,
            n = r.pageModel;
        if (n.type) {
            var i = {
                bootstrap: r.bootstrap,
                change: function(t, r) {
                    e.blurEditor({
                        force: !0
                    });
                    var n = e.options.pageModel;
                    void 0 != r.curPage && (n.prevPage = n.curPage, n.curPage = r.curPage), void 0 != r.rPP && (n.rPP = r.rPP), "remote" == n.type ? e.remoteRequest({
                        callback: function() {
                            e._onDataAvailable({
                                apply: !0,
                                header: !1
                            })
                        }
                    }) : e.refreshView({
                        header: !1,
                        source: "pager"
                    })
                },
                refresh: function(t) {
                    e.refreshDataAndView()
                }
            };
            i = t.extend(i, n), this.pagerW = pq.pager(n.appendTo ? n.appendTo : this.$footer, i)
        }
    }, l.generateLoading = function() {
        this.$loading && this.$loading.remove(), this.$loading = t("<div class='pq-loading'></div>").appendTo(this.element), t(["<div class='pq-loading-bg'></div><div class='pq-loading-mask ui-state-highlight'><div>", this.options.strLoading, "...</div></div>"].join("")).appendTo(this.$loading), this.$loading.find("div.pq-loading-bg").css("opacity", .2)
    }, l._refreshLoadingString = function() {
        this.$loading.find("div.pq-loading-mask").children("div").html(this.options.strLoading)
    }, l.showLoading = function() {
        null == this.showLoadingCounter && (this.showLoadingCounter = 0), this.showLoadingCounter++, this.$loading.show()
    }, l.hideLoading = function() {
        this.showLoadingCounter > 0 && this.showLoadingCounter--, this.showLoadingCounter || this.$loading.hide()
    }, l.getTotalRows = function() {
        var t = this.options,
            e = t.dataModel,
            r = e.data || [],
            n = e.dataUF || [],
            i = t.pageModel;
        return "remote" == i.location ? i.totalRecords : r.length + n.length
    }, l.refreshDataFromDataModel = function(t) {
        t = t || {};
        var e, r, n, i, o, a = this,
            l = a.options,
            s = l.dataModel,
            d = l.pageModel,
            c = s.data,
            h = d.type,
            u = a._queueATriggers;
        for (var f in u) {
            var p = u[f];
            delete u[f], a._trigger(f, p.evt, p.ui)
        }
        if (a._trigger("beforeRefreshData", null, {}), "local" == h) i = d.totalRecords = c.length, d.totalPages = n = Math.ceil(i / d.rPP), d.curPage > n && (d.curPage = n), n && !d.curPage && (d.curPage = 1), e = (d.curPage - 1) * d.rPP, e = e >= 0 ? e : 0, r = d.curPage * d.rPP, r > c.length && (r = c.length), a.pdata = c.slice(e, r), o = e;
        else if ("remote" == h) {
            d.totalPages = n = Math.ceil(d.totalRecords / d.rPP), d.curPage > n && (d.curPage = n), n && !d.curPage && (d.curPage = 1);
            var r = d.rPP;
            r > c.length && (r = c.length), a.pdata = c.slice(0, r), o = d.rPP * (d.curPage - 1)
        } else l.backwardCompat ? a.pdata = c.slice(0) : a.pdata = c;
        a.riOffset = o >= 0 ? o : 0, a._trigger("dataReady", null, {
            source: t.source
        })
    }, l.getQueryStringCRUD = function() {
        return ""
    }, l.remoteRequest = function(e) {
        this.loading && this.xhr.abort(), e = e || {};
        var r = this,
            n = "",
            i = "",
            o = this.options,
            a = !1,
            l = this.colModel,
            s = o.dataModel,
            d = o.sortModel,
            c = o.filterModel,
            h = o.pageModel;
        if ("function" == typeof s.getUrl) {
            var u = {
                    colModel: l,
                    dataModel: s,
                    sortModel: d,
                    groupModel: o.groupModel,
                    pageModel: h,
                    filterModel: c
                },
                f = s.getUrl.call(this, u);
            f && f.url && (n = f.url), f && f.data && (i = f.data)
        } else if ("string" == typeof s.url) {
            n = s.url;
            var p = {},
                g = {},
                v = {};
            if ("remote" == d.type) {
                e.initBySort || this.sort({
                    initByRemote: !0
                });
                var m = this.iSort.getQueryStringSort();
                m && (p = {
                    pq_sort: m
                })
            }
            "remote" == h.type && (v = {
                pq_curpage: h.curPage,
                pq_rpp: h.rPP
            });
            var w;
            "local" != c.type && (w = this.iFilterData.getQueryStringFilter(), w && (a = !0, g = {
                pq_filter: w
            }));
            var x = s.postData,
                y = s.postDataOnce;
            x && "function" == typeof x && (x = x.call(this, {
                colModel: l,
                dataModel: s
            })), i = t.extend({
                pq_datatype: s.dataType
            }, g, v, p, x, y)
        }
        n && (this.loading = !0, this.showLoading(), this.xhr = t.ajax({
            url: n,
            dataType: s.dataType,
            async: null == s.async ? !0 : s.async,
            cache: s.cache,
            contentType: s.contentType,
            type: s.method,
            data: i,
            beforeSend: function(t, e) {
                return "function" == typeof s.beforeSend ? s.beforeSend.call(r, t, e) : void 0
            },
            success: function(t, n, i) {
                r.onRemoteSuccess(t, n, i, a, e)
            },
            error: function(t, e, n) {
                if (r.hideLoading(), r.loading = !1, "function" == typeof s.error) s.error.call(r, t, e, n);
                else if ("abort" != n) throw "Error : " + n
            }
        }))
    }, l.onRemoteSuccess = function(t, e, r, n, i) {
        var o, a = this,
            l = a.options,
            s = a.colModel,
            d = l.pageModel,
            c = l.dataModel;
        o = "function" == typeof c.getData ? c.getData.call(a, t, e, r) : t, c.data = o.data, "remote" == d.type && (null != o.curPage && (d.curPage = o.curPage), null != o.totalRecords && (d.totalRecords = o.totalRecords)), a.hideLoading(), a.loading = !1, a._trigger("load", null, {
            dataModel: c,
            colModel: s
        }), n && (a._queueATriggers.filter = {
            ui: {}
        }), i.callback && i.callback()
    }, l._refreshTitle = function() {
        this.$title.html(this.options.title)
    }, l._destroyDraggable = function() {
        var t = this.element,
            e = t.parent(".pq-wrapper");
        e.length && e.data("draggable") && (e.draggable("destroy"), this.$title.removeClass("pq-draggable pq-no-capture"), t.unwrap(".pq-wrapper"))
    }, l._refreshDraggable = function() {
        var t = this.options,
            e = this.element,
            r = this.$title;
        if (t.draggable) {
            r.addClass("pq-draggable pq-no-capture");
            var n = e.parent(".pq-wrapper");
            n.length || e.wrap("<div class='pq-wrapper' />"), e.parent(".pq-wrapper").draggable({
                handle: r
            })
        } else this._destroyDraggable()
    }, l._refreshResizable = function() {
        var e = this,
            r = this.element,
            n = this.options,
            i = (n.width + "").indexOf("%") > -1,
            o = (n.height + "").indexOf("%") > -1,
            a = "auto" == n.width,
            l = "flex" == n.width,
            s = "flex" == n.height;
        if (!n.resizable || (s || o) && (l || i || a)) this._destroyResizable();
        else {
            var d = "e,s,se";
            s || o ? d = "e" : (l || i || a) && (d = "s");
            var c = !0;
            if (r.hasClass("ui-resizable")) {
                var h = r.resizable("option", "handles");
                d == h ? c = !1 : this._destroyResizable()
            }
            c && r.resizable({
                helper: "ui-state-default",
                handles: d,
                minWidth: n.minWidth,
                minHeight: n.minHeight ? n.minHeight : 100,
                delay: 0,
                start: function(e, r) {
                    t(r.helper).css({
                        opacity: .5,
                        background: "#ccc",
                        border: "1px solid steelblue"
                    })
                },
                resize: function(t, e) {},
                stop: function(r, i) {
                    var o = e.element,
                        a = o[0],
                        l = n.width,
                        s = n.height,
                        d = (l + "").indexOf("%") > -1,
                        c = (s + "").indexOf("%") > -1,
                        h = "auto" == l,
                        u = "flex" == l,
                        f = "flex" == s,
                        p = !1;
                    a.style.width = a.offsetWidth + 3 + "px", a.style.height = a.offsetHeight + 3 + "px", c || f || (p = !0, n.height = a.offsetHeight), d || h || u || (p = !0, n.width = a.offsetWidth), e.refresh(), o.css("position", "relative"), p && t(window).trigger("resize")
                }
            })
        }
    }, l._refreshAfterResize = function() {
        var t = this.options,
            e = t.width,
            r = t.height,
            n = -1 != (e + "").indexOf("%"),
            i = "auto" === e,
            o = -1 != (r + "").indexOf("%");
        (n || i || o) && this.refresh()
    }, l.refresh = function(t) {
        this.iRefresh.refresh(t)
    }, l.refreshView = function(t) {
        null != this.options.editModel.indices && this.blurEditor({
            force: !0
        }), this.refreshDataFromDataModel(t), this.refresh(t)
    }, l._refreshPager = function() {
        var t = this.options,
            e = t.pageModel,
            r = !!e.type,
            n = e.rPP,
            i = e.totalRecords;
        if (r) {
            var o = t.pageModel;
            this.pagerW || this._initPager(), this.pagerW.option(o), i > n ? this.$bottom.css("display", "") : t.showBottom || this.$bottom.css("display", "none")
        } else this.pagerW && (this.pagerW.destroy(), this.pagerW = null), t.showBottom ? this.$bottom.css("display", "") : this.$bottom.css("display", "none")
    }, l.getInstance = function() {
        return {
            grid: this
        }
    }, l.refreshDataAndView = function(t) {
        var e = this.options.dataModel;
        if ("remote" == e.location) {
            var r = this;
            this.remoteRequest({
                callback: function() {
                    r._onDataAvailable(t)
                }
            })
        } else this._onDataAvailable(t)
    }, l.getColIndx = function(t) {
        var e, r, n, i = t.dataIndx,
            o = t.column;
        if (o) r = !0;
        else {
            if (void 0 === i) throw "dataIndx / column NA";
            n = !0
        }
        var a = this.colModel,
            l = a.length;
        if (r) {
            for (var s = 0; l > s; s++)
                if (a[s] == o) return s
        } else if (e = this.colIndxs[i], null != e) return e;
        return -1
    }, l.getColumn = function(t) {
        if (null == t.dataIndx) throw "dataIndx N/A";
        return this.columns[t.dataIndx]
    }, l._generateCellRowOutline = function() {
        var e = this.options,
            r = e.editModel;
        if (this.$div_focus) {
            if (e.debug) throw "this.$div_focus already present assert failed"
        } else {
            var n = this.element;
            r.inline && (n = this.getCell(r.indices), n.css("padding", 0).empty()), this.$div_focus = t(["<div class='pq-editor-outer'>", "<div class='pq-editor-inner'>", "</div>", "</div>"].join("")).appendTo(n);
            var i = t.extend({
                    all: !0
                }, r.indices),
                o = this.getCell(i);
            o.css("height", o[0].offsetHeight), o.empty(), this.refreshEditorPos()
        }
    }, l._removeEditOutline = function(e) {
        function r(t) {
            t.hasClass("hasDatepicker") && t.datepicker("hide").datepicker("destroy")
        }
        if (this.$div_focus) {
            var n = this.$div_focus.find(".pq-editor-focus");
            if (r(n), n[0] == document.activeElement) {
                var i = this._blurEditMode;
                this._blurEditMode = !0, n.blur(), this._blurEditMode = i
            }
            this.$div_focus.remove(), delete this.$div_focus;
            var o = this.options.editModel,
                a = t.extend({}, o.indices);
            o.indices = null, a.rowData = void 0, this.refreshCell(a)
        }
    }, l.refreshEditorPos = function() {}, l.get$Tbl = function(e, r) {
        var n = this.$tbl,
            i = [];
        if (n && n.length) {
            var o = this.pqpanes,
                a = this.options,
                l = a.freezeRows,
                s = a.freezeCols;
            return o.h && o.v ? null == r ? e >= l ? i.push(n[2], n[3]) : i.push(n[0], n[1]) : i = r >= s && e >= l ? n[3] : s > r && e >= l ? n[2] : r >= s && l > e ? n[1] : n[0] : i = o.v ? null == r ? n : r >= s ? n[1] : n[0] : o.h && e >= l ? n[1] : n[0], i ? t(i) : void 0
        }
    }, l.scrollCell = function(t) {
        this.scrollRow(t), this.scrollColumn(t)
    }, l.scrollY = function(t) {
        this.vscroll.option("cur_pos", t), this.vscroll.scroll()
    }, l.scrollRow = function(t) {
        var e = this.options,
            t = this.normalize(t),
            r = t.rowIndxPage,
            n = t.rowData;
        return !this.pdata || r >= this.pdata.length ? !1 : !n || n.pq_hidden ? !1 : void(e.virtualY ? this._scrollRowVirtual(t) : this.iMouseSelection.scrollRowNonVirtual(t))
    }, l._scrollRowVirtual = function(t) {
        var e = this.options,
            r = t.rowIndxPage,
            n = !!this.iHierarchy,
            i = t.rowIndx,
            o = this.vscroll,
            a = this.scrollCurPos,
            r = null == r ? i - this.riOffset : r,
            l = parseInt(e.freezeRows);
        if (!(l > r)) {
            var s = this._calcCurPosFromRowIndxPage(r);
            if (null != s) {
                a > s && (o.option("cur_pos", s), o.scroll());
                var d = this.get$Tbl(r);
                if (!d || !d.length) return null;
                var c = d.children("tbody").children("tr[pq-row-indx=" + r + "]"),
                    h = c.last(),
                    u = h;
                c.length > 1 && (u = c.first());
                var f = h[0],
                    p = parseInt(d.css("marginTop"));
                if (void 0 == f) o.option("cur_pos", s), o.scroll();
                else {
                    var g = f.offsetTop + f.offsetHeight,
                        v = this.iRefresh.getEContHt(),
                        m = p,
                        w = this._getSBHeight(),
                        x = u.prev("tr");
                    if (x.hasClass("pq-row-hidden") || x.hasClass("pq-last-frozen-row")) return;
                    if (g > v - m) {
                        var y, _ = g - (v - w - m),
                            c = d.children().children("tr"),
                            b = 0,
                            I = 0;
                        for (l ? (y = c.filter("tr.pq-last-frozen-row").last().next(), 0 == y.length && (y = c.filter("tr.pq-row-hidden").next())) : y = c.filter("tr.pq-row-hidden").next();;) {
                            if (!y.length) break;
                            if (b += y[0].offsetHeight, y[0] == h[0]) break;
                            if (n && 0 != y.hasClass("pq-detail-child")) {
                                if (b >= _) break
                            } else if (I++, b >= _) break;
                            y = y.next()
                        }
                        var C = a + I;
                        C > s && (C = s);
                        var q = o.option("num_eles");
                        C + 1 > q && (q = C + 1), o.option({
                            num_eles: q,
                            cur_pos: C
                        }), o.scroll()
                    }
                }
            }
        }
    }, l.blurEditor = function(t) {
        if (this.$div_focus) {
            var e = this.$div_focus.find(".pq-editor-focus");
            if (!t || !t.blurIfFocus) return e.triggerHandler("blur", t);
            document.activeElement == e[0] && e.blur()
        }
    }, l._scrollColumnVirtual = function(t) {
        var e = t.colIndx,
            r = this.hscroll,
            e = null == e ? this.colIndxs[t.dataIndx] : e,
            n = this.options.freezeCols,
            i = this._calcRightEdgeCol(e).width,
            o = this.iRefresh.getEContWd();
        if (i > o) {
            for (var a = this.calcWidthCols(-1, e + 1) - o, l = this.colModel, s = l.length, d = 0, c = 0, h = n; s > h; h++) {
                var u = l[h];
                if (u.hidden || (d += u.outerWidth), h == e) {
                    c = h - n - this._calcNumHiddenUnFrozens(h);
                    break
                }
                if (d >= a) {
                    c = h - n - this._calcNumHiddenUnFrozens(h) + 1;
                    break
                }
            }
            return r.option("cur_pos", c), r.scroll(), !0
        }
        if (e >= n && e < this.initH) {
            var f = e - n - this._calcNumHiddenUnFrozens(e);
            return r.option("cur_pos", f), r.scroll(), !0
        }
        return !1
    }, l.scrollColumn = function(t) {
        var e = this.options,
            r = e.virtualX;
        return "flex" !== e.width || e.maxWidth ? r ? this._scrollColumnVirtual(t) : this.iMouseSelection.scrollColumnNonVirtual(t) : !1
    }, l.Selection = function() {
        return this.iSelection
    }, l.goToPage = function(t) {
        var e = this.options.pageModel;
        if ("local" == e.type || "remote" == e.type) {
            var r = t.rowIndx,
                n = e.rPP,
                i = null == t.page ? Math.ceil((r + 1) / n) : t.page,
                o = e.curPage;
            i != o && (e.curPage = i, "local" == e.type ? this.refreshView() : this.refreshDataAndView())
        }
    }, l.setSelection = function(t) {
        if (null == t) return this.iSelection.removeAll(), this.iRows.removeAll({
            all: !0
        }), !0;
        var e = this.pdata;
        if (!e || !e.length) return !1;
        t = this.normalize(t);
        var r = t.rowIndx,
            n = t.rowIndxPage,
            i = t.colIndx;
        if (t.rowData && null == r) {
            var o = this.getRowIndx(t);
            t.rowIndx = r = o.rowIndx, t.rowIndxPage = n = o.rowIndxPage
        }
        return null == r || 0 > r || 0 > i || i >= this.colModel.length ? !1 : (this.goToPage(t), n = r - this.riOffset, this.scrollRow({
            rowIndxPage: n
        }), null == i ? this.iRows.add({
            rowIndx: r
        }) : (this.scrollColumn({
            colIndx: i
        }), this.Range({
            r1: r,
            c1: i
        }).select()), void(t.focus !== !1 && this.focus({
            rowIndxPage: n,
            colIndx: null == i ? this.getFirstVisibleCI() : i
        })))
    }, l.getColModel = function() {
        return this.colModel
    }, l.saveEditCell = function(e) {
        var r = this.options,
            n = r.editModel;
        if (!n.indices) return null;
        var i, o = t.extend({}, n.indices),
            a = e ? e.evt : null,
            l = this.riOffset,
            s = o.colIndx,
            d = o.rowIndxPage,
            c = d + l,
            h = this.colModel,
            u = h[s],
            f = u.dataIndx,
            p = this.pdata,
            g = p[d],
            v = r.dataModel;
        if (null == g) return null;
        if (null != d) {
            var m = this.getEditCellData();
            if (t.isPlainObject(m)) {
                i = {};
                for (var w in m) i[w] = g[w]
            } else i = this.readCell(g, u);
            "<br>" == m && (m = ""), null == i && "" === m && (m = null);
            var x = {
                rowIndx: c,
                rowIndxPage: d,
                dataIndx: f,
                column: u,
                newVal: m,
                value: m,
                oldVal: i,
                rowData: g,
                dataModel: v
            };
            if (this._trigger("cellBeforeSave", a, x) === !1) return !1;
            var y = {},
                _ = !1;
            t.isPlainObject(m) ? (y = m, _ = !0) : y[f] = m;
            var b = this.updateRow({
                row: y,
                rowIndx: c,
                refresh: _,
                silent: !0,
                source: "edit",
                checkEditable: !1
            });
            return b === !1 ? !1 : (this._trigger("cellSave", a, x), !0)
        }
    }, l._addInvalid = function(t) {}, l._digestNewRow = function(t, e, r, n, i, o, a, l, s) {
        var d, c, h, u = this,
            f = u.getValueFromDataType,
            p = u.columns,
            g = u.colIndxs;
        for (d in t)
            if (c = p[d], h = g[d], c) {
                if (o && null != c.editable && u.isEditableCell({
                        rowIndx: r,
                        colIndx: h,
                        dataIndx: d
                    }) === !1) {
                    delete t[d], e && delete e[d];
                    continue
                }
                var v = c.dataType,
                    m = f(t[d], v),
                    w = e ? e[d] : void 0,
                    w = void 0 !== w ? f(w, v) : void 0;
                if (t[d] = m, a && c.validations)
                    if ("edit" == s && l === !1) {
                        var x = this.isValid({
                            focusInvalid: !0,
                            dataIndx: d,
                            rowIndx: r,
                            value: m
                        });
                        if (0 == x.valid && !x.warn) return !1
                    } else {
                        var y = "add" == i ? t : n,
                            x = this.iValid.isValidCell({
                                column: c,
                                rowData: y,
                                allowInvalid: l,
                                value: m
                            });
                        x.valid === !1 && (l !== !1 || x.warn || delete t[d])
                    }
                if ("update" == i && m === w) {
                    delete t[d], delete e[d];
                    continue
                }
            }
        return "update" != i ? !0 : pq.isEmpty(t) ? void 0 : !0
    }, l._digestData = function(t) {
        if (t.rowList) throw "not supported";
        if (C = t.addList = t.addList || [], t.updateList = t.updateList || [], t.deleteList = t.deleteList || [], C.length && C[0].rowData) throw "rd in addList";
        if (this._trigger("beforeValidate", null, t) === !1) return !1;
        var e, r, n = this,
            i = n.options,
            o = i.editModel,
            a = i.dataModel,
            l = a.data,
            s = i.colModel,
            d = i.pageModel,
            c = i.historyModel,
            h = null == t.validate ? o.validate : t.validate,
            u = "remote" == d.type,
            f = null == t.allowInvalid ? o.allowInvalid : t.allowInvalid,
            p = i.trackModel,
            g = t.track,
            g = null == g ? null == i.track ? p.on : i.track : g,
            v = null == t.history ? c.on : t.history,
            m = this.iHistory,
            w = this.iUCData,
            x = null == t.checkEditable ? !0 : t.checkEditable,
            y = null == t.checkEditableAdd ? x : t.checkEditableAdd,
            _ = t.source,
            b = n.iRefresh,
            I = this.riOffset,
            C = t.addList,
            q = t.updateList,
            R = t.deleteList,
            D = [],
            M = [];
        for (!l && (l = a.data = []), e = 0, r = q.length; r > e; e++) {
            var T, P = q[e],
                E = P.newRow,
                S = P.rowData,
                k = P.checkEditable,
                H = P.rowIndx,
                F = P.oldRow;
            if (null == k && (k = x), !F) throw "oldRow required while update";
            if (!k || i.editable === !0 || n.isEditableRow({
                    rowIndx: H,
                    rowData: S
                }) !== !1) {
                if (T = this._digestNewRow(E, F, H, S, "update", k, h, f, _), T === !1) return !1;
                T && M.push(P)
            }
        }
        for (e = 0, r = C.length; r > e; e++) {
            var S, F, P = C[e],
                E = P.newRow,
                k = P.checkEditable,
                H = P.rowIndx;
            if (null == k && (k = y), s.forEach(function(t) {
                    var e = t.dataIndx;
                    E[e] = E[e]
                }), T = this._digestNewRow(E, F, H, S, "add", k, h, f, _), T === !1) return !1;
            T && D.push(P)
        }
        return C = t.addList = D, q = t.updateList = M, C.length || q.length || R.length ? (v && (m.increment(), m.push(t)), n._digestUpdate(q, w, g), C.length && (n._digestAdd(C, w, g, l, d, u, I), b.addRowIndx()), R.length && (n._digestDelete(R, w, g, l, d, u, I), b.addRowIndx()), n._trigger("change", null, t), !0) : "edit" == _ ? null : !1
    }, l._digestUpdate = function(t, e, r) {
        for (var n, i, o, a = 0, l = t.length, s = this.columns, d = this.saveCell; l > a; a++) {
            var c = t[a],
                h = c.newRow,
                u = c.rowData;
            r && e.update({
                rowData: u,
                row: h,
                refresh: !1
            });
            for (o in h) n = s[o], i = h[o], d(u, n, i)
        }
    }, l._digestAdd = function(t, e, r, n, i, o, a) {
        for (var l, s, d = 0, c = t.length; c > d; d++) {
            var h = t[d],
                u = h.newRow,
                f = h.rowIndx;
            r && e.add({
                rowData: u
            }), null == f ? n.push(u) : (s = f - a, l = o ? s : f, n.splice(l, 0, u)), h.rowData = u, o && i.totalRecords++
        }
    }, l._digestDelete = function(t, e, r, n, i, o, a) {
        for (var l = 0, s = t.length; s > l; l++) {
            var d = t[l],
                c = d.rowData,
                h = this.getRowIndx({
                    rowData: c,
                    dataUF: !0
                }),
                u = h.uf,
                f = h.rowIndx;
            d.uf = u, d.rowIndx = f
        }
        for (t.sort(function(t, e) {
                return e.rowIndx - t.rowIndx
            }), l = 0; s > l; l++) {
            var d = t[l],
                c = d.rowData,
                u = d.uf,
                f = d.rowIndx;
            r && e["delete"]({
                rowIndx: f,
                rowData: c
            });
            var p = f - a,
                g = o ? p : f;
            if (u) DM.dataUF.splice(f, 1);
            else {
                var v = n.splice(g, 1);
                v && v.length && o && i.totalRecords--
            }
        }
    }, l.cacheRIs = function() {
        var t = this.options.dataModel;
        t.data.forEach(function(t, e) {
            t.pq_ri = e
        }), t.dataUF.forEach(function(t, e) {
            t.pq_ri_uf = e
        })
    }, l.getRI = function(t) {
        return null != t.pq_ri ? t.pq_ri : t.pq_ri_uf
    }, l.refreshColumn = function(t) {
        var e = this.normalize(t),
            r = this.initV,
            n = this.finalV,
            i = this.options.freezeRows,
            o = e.colIndx,
            a = e.dataIndx,
            l = e.column;
        e.skip = !0;
        for (var s = 0; n >= s; s++) r > s && s >= i && (s = r), e.rowIndxPage = s, this.refreshCell(e);
        this._trigger("refreshColumn", null, {
            column: l,
            colIndx: o,
            dataIndx: a
        }), this.iRefresh.softRefresh()
    }, l.refreshCell = function(t) {
        var e = this.normalize(t);
        if (this.pdata) {
            var r = e.skip,
                n = e.rowIndx,
                i = e.rowIndxPage,
                o = e.colIndx,
                a = this.iMerge,
                l = e.rowData;
            if (l) {
                var s = this.getCell({
                    all: !0,
                    rowIndxPage: i,
                    colIndx: o
                });
                if (s && s.length > 0) {
                    var d = e;
                    a.ismergedCell(n, o) && (d = a.getRootCell(n, o, "a"));
                    var c, h = this.iGenerateView.renderCell(d);
                    if (!h) return;
                    s.replaceWith(h), (c = this._focusEle) && c.rowIndxPage == i && this.focus(), r || (this._trigger("refreshCell", null, e), this.iRefresh.softRefresh())
                }
            }
        }
    }, l.refreshRow = function(e) {
        var r = this.normalize(e);
        if (this.pdata) {
            var n = this,
                i = r.rowIndx,
                o = r.rowIndxPage,
                a = n.options,
                l = a.freezeRows,
                s = r.rowData;
            if (!s || s.pq_hidden || o > n.finalV || o < n.initV && o >= l) return null;
            var d, c = this.getRow({
                    all: !0,
                    rowIndxPage: o
                }),
                h = [];
            n.iGenerateView.refreshRow(o, h);
            var u = h.join("");
            if (c && c.length) c.replaceWith(u);
            else {
                if (!a.virtualY) return !1;
                if (o == n.finalV) n.$tbl.append(u);
                else {
                    if (o != n.initV) throw "refreshRow > rip not found";
                    for (var f = n.$tbl, p = 0; p < f.length; p++) t(f[p]).children("tbody").children(l ? ".pq-last-frozen-row" : "tr:first").after(u)
                }
            }
            return (d = this._focusEle) && d.rowIndxPage == o && n.focus(), this._trigger("refreshRow", null, {
                rowData: s,
                rowIndx: i,
                rowIndxPage: o
            }), r.refresh !== !1 && this.iRefresh.softRefresh(), !0
        }
    }, l.quitEditMode = function(t) {
        if (!this._quitEditMode) {
            var e = this,
                r = !1,
                n = !1,
                i = !1,
                o = this.options,
                a = o.editModel,
                l = a.indices,
                s = void 0;
            e._quitEditMode = !0, t && (r = t.old, n = t.silent, i = t.fireOnly, s = t.evt), l && (n || r || this._trigger("editorEnd", s, l), i || (this._removeEditOutline(t), a.indices = null)), e._quitEditMode = null
        }
    }, l.getViewPortRowsIndx = function() {
        return {
            beginIndx: this.initV,
            endIndx: this.finalV
        }
    }, l.getViewPortIndx = function() {
        return {
            initV: this.initV,
            finalV: this.finalV,
            initH: this.initH,
            finalH: this.finalH
        }
    }, l.getRIOffset = function() {
        return this.riOffset
    }, l.getEditCell = function() {
        var t = this.options.editModel;
        if (t.indices) {
            var e = this.getCell(t.indices),
                r = this.$div_focus.children(".pq-editor-inner"),
                n = r.find(".pq-editor-focus");
            return {
                $td: e,
                $cell: r,
                $editor: n
            }
        }
        return {}
    }, l.editCell = function(t) {
        var e = this.normalize(t),
            r = this.iMerge,
            n = e.rowIndx,
            i = e.colIndx;
        if (r.ismergedCell(n, i)) {
            var o = r.getRootCell(n, i, "o");
            if (o.rowIndx != e.rowIndx || o.colIndx != e.colIndx) return !1
        }
        this.scrollRow(e), this.scrollColumn(e);
        var a = this.getCell(e);
        return a && a.length ? this._editCell(e) : void 0
    }, l.getFirstEditableColIndx = function(t) {
        if (null == t.rowIndx) throw "rowIndx NA";
        if (!this.isEditableRow(t)) return -1;
        for (var e = this.colModel, r = 0; r < e.length; r++)
            if (t.colIndx = r, this.isEditableCell(t) && !e[r].hidden) return r;
        return -1
    }, l.editFirstCellInRow = function(t) {
        var e = this.normalize(t),
            r = e.rowIndx,
            n = this.getFirstEditableColIndx({
                rowIndx: r
            }); - 1 != n && this.editCell({
            rowIndx: r,
            colIndx: n
        })
    }, l._editCell = function(e) {
        var r = this.normalize(e),
            n = this,
            o = r.evt,
            a = r.rowIndxPage,
            l = r.colIndx,
            s = n.pdata;
        if (!s || a >= s.length) return !1;
        var n = this,
            d = this.options,
            c = d.editModel,
            h = s[a],
            u = r.rowIndx,
            f = this.colModel,
            p = f[l],
            g = p.dataIndx,
            v = n.readCell(h, p),
            m = {
                rowIndx: u,
                rowIndxPage: a,
                cellData: v,
                rowData: h,
                dataIndx: g,
                colIndx: l,
                column: p
            },
            w = p.editor,
            x = this,
            y = typeof w,
            w = "function" == y || "string" == y ? x.callFn(w, m) : w;
        if (void 0 === w && "function" == typeof d.geditor && (w = d.geditor.call(x, m)), w !== !1) {
            w && w.getData && (c._getData = w.getData);
            var _ = d.editor,
                b = w ? t.extend({}, _, w) : _,
                I = !1;
            if (c.indices) {
                var C = c.indices;
                if (C.rowIndxPage == a && C.colIndx == l) {
                    this.refreshEditorPos();
                    var q = this.$div_focus.find(".pq-editor-focus");
                    return q[0].focus(), document.activeElement != q[0] && window.setTimeout(function() {
                        q.focus()
                    }, 0), !1
                }
                if (this.blurEditor({
                        evt: o
                    }) === !1) return !1;
                this.quitEditMode({
                    evt: o
                })
            }
            c.indices = {
                rowIndxPage: a,
                rowIndx: u,
                colIndx: l,
                column: p,
                dataIndx: g
            }, this._generateCellRowOutline();
            var R = this.$div_focus,
                D = R.children(".pq-editor-inner");
            D.addClass("pq-align-" + (p.align || "left")), m.$cell = D;
            var M, T = b.type,
                P = null == r.select ? b.select : r.select,
                E = b.init,
                S = b.valueIndx,
                k = b.dataMap,
                H = b.mapIndices,
                H = H ? H : {},
                F = b.cls || "",
                F = "function" == typeof F ? F.call(x, m) : F,
                $ = "pq-editor-focus " + F,
                A = $ + " pq-cell-editor ",
                V = b.attr || "",
                V = "function" == typeof V ? V.call(x, m) : V,
                L = b.style || "",
                L = "function" == typeof L ? L.call(x, m) : L,
                z = L ? "style='" + L + "'" : "",
                N = z,
                O = z;
            if (m.cls = $, m.attr = V, "function" == typeof T && (M = T.call(x, m), M && (T = M)), _._type = T, "checkbox" == T) {
                var W = b.subtype,
                    U = v ? "checked='checked'" : "";
                M = "<input " + U + " class='" + A + "' " + V + " " + O + " type=checkbox name='" + g + "' />", D.html(M);
                var j = D.children("input");
                "triple" == W && (j.pqval({
                    val: v
                }), D.click(function(e) {
                    t(this).children("input").pqval({
                        incr: !0
                    })
                }))
            } else if ("textarea" == T || "select" == T || "textbox" == T) {
                if ("textarea" == T) M = "<textarea class='" + A + "' " + V + " " + N + " name='" + g + "' ></textarea>";
                else if ("select" == T) {
                    var B = b.options || [];
                    B.constructor !== Array && (B = n.callFn(B, m));
                    var G = [V, " class='", A, "' ", N, " name='", g, "'"].join("");
                    M = i.select({
                        options: B,
                        attr: G,
                        prepend: b.prepend,
                        labelIndx: b.labelIndx,
                        valueIndx: S,
                        groupIndx: b.groupIndx,
                        dataMap: k
                    })
                } else M = "<input class='" + A + "' " + V + " " + N + " type=text name='" + g + "' />";
                t(M).appendTo(D).val("select" == T && null != S && (H[S] || this.columns[S]) ? H[S] ? h[H[S]] : h[S] : v)
            } else T && "contenteditable" != T || (M = "<div contenteditable='true' tabindx='0' " + z + " " + V + " class='" + A + "'></div>", D.html(M), D.children().html(v), I = !0);
            E && (m.$editor = D.children(".pq-editor-focus"), this.callFn(E, m));
            var q = D.children(".pq-editor-focus"),
                K = c.filterKeys,
                X = p.editModel;
            X && void 0 !== X.filterKeys && (K = X.filterKeys);
            var Q = {
                $cell: D,
                $editor: q,
                $td: n.getCell(c.indices),
                dataIndx: g,
                column: p,
                colIndx: l,
                rowIndx: u,
                rowIndxPage: a,
                rowData: h
            };
            if (c.indices = Q, q.data({
                    FK: K
                }).on("click", function(e) {
                    t(this).focus(), n._trigger("editorClick", null, Q)
                }).on("keydown", function(t) {
                    n.iKeyNav.keyDownInEdit(t)
                }).on("keypress", function(t) {
                    return n.iKeyNav.keyPressInEdit(t, {
                        FK: K
                    })
                }).on("keyup", function(t) {
                    return n.iKeyNav.keyUpInEdit(t, {
                        FK: K
                    })
                }).on("blur", function(e, r) {
                    var i = n.options,
                        o = i.editModel,
                        a = o.onBlur,
                        l = "save" == a,
                        s = "validate" == a,
                        d = o.cancelBlurCls,
                        c = r ? r.force : !1;
                    if (!n._quitEditMode && !n._blurEditMode && o.indices) {
                        var h = t(e.target);
                        if (!c) {
                            if (n._trigger("editorBlur", e, Q) === !1) return;
                            if (!a) return;
                            if (d && h.hasClass(d)) return;
                            if (h.hasClass("hasDatepicker")) {
                                var u = h.datepicker("widget");
                                if (u.is(":visible")) return !1
                            } else if (h.hasClass("ui-autocomplete-input")) {
                                if (h.autocomplete("widget").is(":visible")) return
                            } else if (h.hasClass("ui-multiselect")) {
                                if (t(".ui-multiselect-menu").is(":visible") || t(document.activeElement).closest(".ui-multiselect-menu").length) return
                            } else if (h.hasClass("pq-select-button") && (t(".pq-select-menu").is(":visible") || t(document.activeElement).closest(".pq-select-menu").length)) return
                        }
                        n._blurEditMode = !0;
                        var f = c || l || !s;
                        if (!n.saveEditCell({
                                evt: e,
                                silent: f
                            }) && !c && s) return n._deleteBlurEditMode(), !1;
                        n.quitEditMode({
                            evt: e
                        }), n._deleteBlurEditMode()
                    }
                }).on("focus", function(t) {
                    n._trigger("editorFocus", t, Q)
                }), n._trigger("editorBegin", o, Q), q.focus(), window.setTimeout(function() {
                    var e = t(document.activeElement);
                    if (e.hasClass("pq-editor-focus") === !1) {
                        var r = n.element ? n.element.find(".pq-editor-focus") : t();
                        r.focus()
                    }
                }), P)
                if (I) try {
                    var Y = q[0],
                        J = document.createRange();
                    J.selectNodeContents(Y);
                    var Z = window.getSelection();
                    Z.removeAllRanges(), Z.addRange(J)
                } catch (tt) {} else q.select()
        }
    }, l._deleteBlurEditMode = function(t) {
        var e = this,
            t = t ? t : {};
        e._blurEditMode && (t.timer ? window.setTimeout(function() {
            delete e._blurEditMode
        }, 0) : delete e._blurEditMode)
    }, l.getRow = function(e) {
        var r = this.normalize(e),
            n = r.rowIndxPage,
            i = r.all ? this.$tbl : this.get$Tbl(n),
            o = t();
        if (i && i.length) {
            var a = i.children("tbody");
            null != n && (o = a.children("tr[pq-row-indx=" + n + "]"), o.length > i.length && (o = o.filter(".pq-detail-master")))
        }
        return o
    }, l.getCell = function(e) {
        var r = e.all,
            n = this.options,
            i = this.normalize(e),
            o = i.rowIndxPage,
            a = i.rowIndx,
            l = i.colIndx,
            s = this.iMerge,
            d = s.ismergedCell(a, l);
        if (d) {
            var c = s.getRootCell(a, l, "a"),
                h = s.getRootCell(a, l, "o");
            if (!(o === h.rowIndxPage && l === h.colIndx || o === c.rowIndxPage && l === c.colIndx)) return t();
            o = c.rowIndxPage, l = h.colIndx
        } else {
            if (o >= n.freezeRows && (o < this.initV || o > this.finalV) || l >= n.freezeCols && (l < this.initH || l > this.finalH)) return t();
            if (!i.rowData || !i.column || i.rowData.pq_hidden || i.column.hidden) return t()
        }
        var u, f = r ? this.$tbl : this.get$Tbl(o, l);
        return u = f && f.length ? f.children().children("tr[pq-row-indx=" + o + "]").children("[pq-col-indx=" + l + "]") : t()
    }, l.getCellHeader = function(e) {
        var r, n = e.colIndx,
            i = e.dataIndx,
            n = null == n ? this.colIndxs[i] : n,
            o = this.$tbl_header,
            a = this.options,
            l = a.freezeCols;
        if (o) {
            o.length > 1 && (o = t(n >= l ? o[1] : o[0]));
            var r = o.find("[pq-col-indx=" + n + "].pq-grid-col-leaf");
            return r
        }
        return t()
    }, l.getEditorIndices = function() {
        var e = this.options.editModel.indices;
        return e ? t.extend({}, e) : null
    }, l.getEditCellData = function() {
        var e = this.options,
            r = e.editModel,
            n = r.indices;
        if (!n) return null;
        var i, o = n.colIndx,
            a = n.rowIndxPage,
            l = n.rowIndx,
            s = this.colModel[o],
            d = s.editor,
            c = e.editor,
            h = d ? t.extend({}, c, d) : c,
            u = h.valueIndx,
            f = h.labelIndx,
            p = h.mapIndices,
            p = p ? p : {},
            g = s.dataIndx,
            v = this.$div_focus,
            m = v.children(".pq-editor-inner"),
            w = r._getData || h.getData;
        if (r._getData = void 0, w) i = this.callFn(w, {
            $cell: m,
            rowData: n.rowData,
            dataIndx: g,
            rowIndx: l,
            rowIndxPage: a,
            column: s,
            colIndx: o
        });
        else {
            var x = c._type;
            if ("checkbox" == x) {
                var y = m.children();
                i = "triple" == h.subtype ? y.pqval() : !!y.is(":checked")
            } else if ("contenteditable" == x) i = m.children().html();
            else {
                var _ = m.find('*[name="' + g + '"]');
                if (_ && _.length)
                    if ("select" == x && null != u)
                        if (p[u] || this.columns[u]) {
                            i = {}, i[p[u] ? p[u] : u] = _.val(), i[p[f] ? p[f] : f] = _.find("option:selected").text();
                            var b = h.dataMap;
                            if (b) {
                                var I = _.find("option:selected").data("map");
                                if (I)
                                    for (var C = 0; C < b.length; C++) {
                                        var q = b[C];
                                        i[p[q] ? p[q] : q] = I[q]
                                    }
                            }
                        } else i = _.val();
                else i = _.val();
                else {
                    var _ = m.find(".pq-editor-focus");
                    _ && _.length && (i = _.val())
                }
            }
        }
        return i
    }, l.getCellIndices = function(t) {
        var e = t.$td;
        if (null == e || 0 == e.length || e.closest(".pq-grid")[0] != this.element[0]) return {};
        var r, n = e.parent("tr"),
            i = n.attr("pq-row-indx");
        null != i && (i = parseInt(i), r = i + this.riOffset);
        var o, a = e.attr("pq-col-indx");
        return null != a && (a = parseInt(a), a >= 0 && (o = this.colModel[a].dataIndx)), this.iMerge.getRootCell(r, a, "o")
    }, l.getRowsByClass = function(t) {
        var e = this.options,
            r = e.dataModel,
            n = e.pageModel,
            i = "remote" == n.type,
            o = this.riOffset,
            a = r.data,
            l = [];
        if (null == a) return l;
        for (var s = 0, d = a.length; d > s; s++) {
            var c = a[s];
            if (c.pq_rowcls && (t.rowData = c, this.hasClass(t))) {
                var h = {
                        rowData: c
                    },
                    u = i ? s + o : s,
                    f = u - o;
                h.rowIndx = u, h.rowIndxPage = f, l.push(h)
            }
        }
        return l
    }, l.getCellsByClass = function(t) {
        var e = this,
            r = this.options,
            n = r.dataModel,
            i = r.pageModel,
            o = "remote" == i.type,
            a = this.riOffset,
            l = n.data,
            s = [];
        if (null == l) return s;
        for (var d = 0, c = l.length; c > d; d++) {
            var h = l[d],
                u = o ? d + a : d,
                f = h.pq_cellcls;
            if (f)
                for (var p in f) {
                    var g = {
                        rowData: h,
                        rowIndx: u,
                        dataIndx: p,
                        cls: t.cls
                    };
                    if (e.hasClass(g)) {
                        var v = e.normalize(g);
                        s.push(v)
                    }
                }
        }
        return s
    }, l.data = function(e) {
        var r = e.dataIndx,
            n = e.colIndx,
            r = null != n ? this.colModel[n].dataIndx : r,
            i = e.data,
            o = null == i || "string" == typeof i,
            a = e.rowData || this.getRowData(e);
        if (!a) return {
            data: null
        };
        if (null == r) {
            var l = a.pq_rowdata;
            if (o) {
                var s;
                return null != l && (s = null == i ? l : l[i]), {
                    data: s
                }
            }
            var d = t.extend(!0, a.pq_rowdata, i);
            a.pq_rowdata = d
        } else {
            var c = a.pq_celldata;
            if (o) {
                var s;
                if (null != c) {
                    var h = c[r];
                    s = null == i || null == h ? h : h[i]
                }
                return {
                    data: s
                }
            }
            c || (a.pq_celldata = {});
            var d = t.extend(!0, a.pq_celldata[r], i);
            a.pq_celldata[r] = d
        }
    }, l.attr = function(e) {
        var r = e.rowIndx,
            n = e.dataIndx,
            i = e.colIndx,
            n = null != i ? this.colModel[i].dataIndx : n,
            o = e.attr,
            a = null == o || "string" == typeof o,
            l = this.riOffset,
            s = e.refresh,
            d = e.rowData || this.getRowData(e);
        if (!d) return {
            attr: null
        };
        if (a || s === !1 || null != r || (r = this.getRowIndx({
                rowData: d
            }).rowIndx), null == n) {
            var c = d.pq_rowattr;
            if (a) {
                var h;
                return null != c && (h = null == o ? c : c[o]), {
                    attr: h
                }
            }
            var u = t.extend(!0, d.pq_rowattr, o);
            if (d.pq_rowattr = u, s !== !1 && null != r) {
                var f = this.getRow({
                    rowIndxPage: r - l
                });
                if (f) {
                    var p = this.stringifyAttr(u);
                    f.attr(p)
                }
            }
        } else {
            var g = d.pq_cellattr;
            if (a) {
                var h;
                if (null != g) {
                    var v = g[n];
                    h = null == o || null == v ? v : v[o]
                }
                return {
                    attr: h
                }
            }
            g || (d.pq_cellattr = {});
            var u = t.extend(!0, d.pq_cellattr[n], o);
            if (d.pq_cellattr[n] = u, s !== !1 && null != r) {
                var m = this.getCell({
                    rowIndxPage: r - l,
                    dataIndx: n
                });
                if (m) {
                    var p = this.stringifyAttr(u);
                    m.attr(p)
                }
            }
        }
    }, l.stringifyAttr = function(t) {
        var e = {};
        for (var r in t) {
            var n = t[r];
            if (n)
                if ("title" == r) n = n.replace(/\"/g, "&quot;"), e[r] = n;
                else if ("style" == r && "object" == typeof n) {
                var i, o = [];
                for (var a in n) i = n[a], i && o.push(a + ":" + i);
                n = o.join(";") + (o.length ? ";" : ""), n && (e[r] = n)
            } else "object" == typeof n && (n = JSON.stringify(n)), e[r] = n
        }
        return e
    }, l.removeData = function(e) {
        var r = e.dataIndx,
            n = e.colIndx,
            r = null != n ? this.colModel[n].dataIndx : r,
            i = e.data,
            i = null == i ? [] : i,
            o = "string" == typeof i ? i.split(" ") : i,
            a = o.length,
            l = e.rowData || this.getRowData(e);
        if (l)
            if (null == r) {
                var s = l.pq_rowdata;
                if (s) {
                    if (a)
                        for (var d = 0; a > d; d++) {
                            var c = o[d];
                            delete s[c]
                        }
                    a && !t.isEmptyObject(s) || delete l.pq_rowdata
                }
            } else {
                var h = l.pq_celldata;
                if (h && h[r]) {
                    var u = h[r];
                    if (a)
                        for (var d = 0; a > d; d++) {
                            var c = o[d];
                            delete u[c]
                        }
                    a && !t.isEmptyObject(u) || delete h[r]
                }
            }
    }, l.removeAttr = function(e) {
        var r = e.rowIndx,
            n = e.dataIndx,
            i = e.colIndx,
            n = null != i ? this.colModel[i].dataIndx : n,
            o = e.attr,
            o = null == o ? [] : o,
            a = "string" == typeof o ? o.split(" ") : o,
            l = a.length,
            s = r - this.riOffset,
            d = e.refresh,
            c = e.rowData || this.getRowData(e);
        if (c)
            if (d !== !1 && null == r && (r = this.getRowIndx({
                    rowData: c
                }).rowIndx), null == n) {
                var h = c.pq_rowattr;
                if (h) {
                    if (l)
                        for (var u = 0; l > u; u++) {
                            var f = a[u];
                            delete h[f]
                        } else
                            for (var f in h) a.push(f);
                    l && !t.isEmptyObject(h) || delete c.pq_rowattr
                }
                if (d !== !1 && null != r && a.length) {
                    o = a.join(" ");
                    var p = this.getRow({
                        rowIndxPage: s
                    });
                    p && p.removeAttr(o)
                }
            } else {
                var g = c.pq_cellattr;
                if (g && g[n]) {
                    var v = g[n];
                    if (l)
                        for (var u = 0; l > u; u++) {
                            var f = a[u];
                            delete v[f]
                        } else
                            for (var f in v) a.push(f);
                    l && !t.isEmptyObject(v) || delete g[n]
                }
                if (d !== !1 && null != r && a.length) {
                    o = a.join(" ");
                    var m = this.getCell({
                        rowIndxPage: s,
                        dataIndx: n
                    });
                    m && m.removeAttr(o)
                }
            }
    }, l.normalize = function(t, e) {
        var r, n, i, o = {};
        for (i in t) o[i] = t[i];
        var a = o.rowIndx,
            l = o.rowIndxPage,
            s = o.dataIndx,
            d = o.colIndx;
        return null == l && null == a || (r = this.riOffset, a = null == a ? 1 * l + r : a, l = null == l ? 1 * a - r : l, o.rowIndx = a, o.rowIndxPage = l, o.rowData = o.rowData || e && e[a] || this.getRowData(o)), null == d && null == s || (n = this.colModel, s = null == s ? n[d] ? n[d].dataIndx : void 0 : s, d = null == d ? this.colIndxs[s] : d, o.column = n[d], o.colIndx = d, o.dataIndx = s), o
    }, l.normalizeList = function(t) {
        var e = this,
            r = e.get_p_data();
        return t.map(function(t) {
            return e.normalize(t, r)
        })
    }, l.addClass = function(t) {
        var e, r = this.normalize(t),
            n = r.rowIndxPage,
            i = r.dataIndx,
            o = pq.arrayUnique,
            a = r.cls,
            l = r.refresh,
            s = r.rowData;
        if (s)
            if (l !== !1 && null == n && (n = this.getRowIndx({
                    rowData: s
                }).rowIndxPage), null == i) {
                var d = s.pq_rowcls;
                if (e = d ? d + " " + a : a, e = o(e.split(/\s+/)).join(" "), s.pq_rowcls = e, l !== !1 && null != n) {
                    var c = this.getRow({
                        rowIndxPage: n
                    });
                    c && c.addClass(a)
                }
            } else {
                var h = [];
                "function" != typeof i.push ? h.push(i) : h = i;
                var u = s.pq_cellcls;
                u || (u = s.pq_cellcls = {});
                for (var f = 0, p = h.length; p > f; f++) {
                    i = h[f];
                    var g = u[i];
                    if (e = g ? g + " " + a : a, e = o(e.split(/\s+/)).join(" "), u[i] = e, l !== !1 && null != n) {
                        var v = this.getCell({
                            rowIndxPage: n,
                            dataIndx: i
                        });
                        v && v.addClass(a)
                    }
                }
            }
    }, l.removeClass = function(t) {
        var e = this.normalize(t),
            r = e.rowIndx,
            n = e.rowData,
            i = e.dataIndx,
            o = e.cls,
            a = e.refresh;
        if (n) {
            var l = n.pq_cellcls,
                s = n.pq_rowcls;
            if (a !== !1 && null == r && (r = this.getRowIndx({
                    rowData: n
                }).rowIndx), null == i) {
                if (s && (n.pq_rowcls = this._removeClass(s, o), null != r && a !== !1)) {
                    var d = this.getRow({
                        rowIndx: r
                    });
                    d && d.removeClass(o)
                }
            } else if (l) {
                var c = [];
                "function" != typeof i.push ? c.push(i) : c = i;
                for (var h = 0, u = c.length; u > h; h++) {
                    var i = c[h],
                        f = l[i];
                    if (f && (n.pq_cellcls[i] = this._removeClass(f, o), null != r && a !== !1)) {
                        var p = this.getCell({
                            rowIndx: r,
                            dataIndx: i
                        });
                        p && p.removeClass(o)
                    }
                }
            }
        }
    }, l.hasClass = function(t) {
        var e, r = t.dataIndx,
            n = t.cls,
            i = this.getRowData(t),
            o = new RegExp("\\b" + n + "\\b");
        if (i) {
            if (null == r) return e = i.pq_rowcls, !(!e || !o.test(e));
            var a = i.pq_cellcls;
            return !!(a && a[r] && o.test(a[r]))
        }
        return null
    }, l._removeClass = function(t, e) {
        if (t && e) {
            for (var r = t.split(/\s+/), n = e.split(/\s+/), i = [], o = 0, a = r.length; a > o; o++) {
                for (var l = r[o], s = !1, d = 0, c = n.length; c > d; d++) {
                    var h = n[d];
                    if (l === h) {
                        s = !0;
                        break
                    }
                }
                s || i.push(l)
            }
            return i.length > 1 ? i.join(" ") : 1 === i.length ? i[0] : null
        }
    }, l.getRowIndx = function(t) {
        var e, r, n, i = t.$tr,
            o = t.rowData,
            a = this.riOffset;
        if (o) {
            if (null != (n = o.pq_ri)) return {
                rowData: o,
                rowIndx: n,
                rowIndxPage: n - a
            };
            var l = this.get_p_data(),
                s = !1,
                d = t.dataUF ? this.options.dataModel.dataUF : null,
                c = !1;
            if (l)
                for (var h = 0, u = l.length; u > h; h++)
                    if (l[h] == o) {
                        c = !0;
                        break
                    }
            if (!c && d) {
                s = !0;
                for (var h = 0, u = d.length; u > h; h++)
                    if (d[h] == o) {
                        c = !0;
                        break
                    }
            }
            return c ? (e = h - a, r = h, {
                rowIndxPage: s ? void 0 : e,
                uf: s,
                rowIndx: r,
                rowData: o
            }) : {}
        }
        return null == i || 0 == i.length ? {} : (e = i.attr("pq-row-indx"), null == e ? {} : (e = parseInt(e), {
            rowIndxPage: e,
            rowIndx: e + a
        }))
    }, l.search = function(t) {
        for (var e = this.options, r = t.row, n = t.first, i = e.dataModel, o = e.pageModel, a = o.type, l = [], s = this.riOffset, d = "remote" == a, c = i.data, h = 0, u = c.length; u > h; h++) {
            var f = c[h],
                p = !0;
            for (var g in r) r[g] !== f[g] && (p = !1);
            if (p) {
                var v = d ? h + s : h,
                    m = this.normalize({
                        rowIndx: v
                    });
                if (l.push(m), n) break
            }
        }
        return l
    }, l._calcNumHiddenFrozens = function() {
        for (var t = 0, e = this.options.freezeCols, r = 0; e > r; r++) this.colModel[r].hidden && t++;
        return t
    }, l._calcNumHiddenUnFrozens = function(t) {
        for (var e = 0, r = this.options.freezeCols, n = null != t ? t : this.colModel.length, i = r; n > i; i++) this.colModel[i].hidden && e++;
        return e
    }, l._getSBHeight = function() {
        return this.iRefresh.getSBHeight()
    }, l._getSBWidth = function() {
        return this.iRefresh.getSBWidth()
    }, l.getFirstVisibleRIP = function(t) {
        for (var e = this.pdata, r = t ? this.initV : 0, n = e.length; n > r; r++)
            if (!e[r].pq_hidden) return r
    }, l.getLastVisibleRIP = function() {
        for (var t = this.pdata, e = t.length - 1; e >= 0; e--)
            if (!t[e].pq_hidden) return e;
        return null
    }, l.getFirstVisibleCI = function(t) {
        for (var e = this.colModel, r = e.length, n = t ? this.initH : 0; r > n; n++) {
            var i = e[n].hidden;
            if (!i) return n
        }
        return null
    }, l.getLastVisibleCI = function() {
        for (var t = this.colModel, e = t.length, r = e - 1; r >= 0; r--) {
            var n = t[r].hidden;
            if (!n) return r
        }
        return null
    }, l.getTotalVisibleColumns = function() {
        for (var t = this.colModel, e = t.length, r = 0, n = 0; e > n; n++) {
            var i = t[n],
                o = i.hidden;
            o || r++
        }
        return r
    }, l._calcCurPosFromRowIndxPage = function(t) {
        return t < this.options.freezeRows ? 0 : pq.searchSeqArray(this.iRefresh.vrows, t)
    }, l._calcCurPosFromColIndx = function(t) {
        return t < this.options.freezeCols ? 0 : pq.searchSeqArray(this.iRefresh.vcols, t)
    }, l.calcWidthCols = function(t, e, r) {
        var n = 0,
            i = this.options,
            o = 0,
            a = i.numberCell,
            l = this.colModel;
        if (-1 == t && (a.show && (n += r ? 1 * a.width : a.outerWidth), t = 0), r)
            for (var s = t; e > s; s++) {
                var d = l[s];
                if (d && !d.hidden) {
                    if (!d._width) throw "assert failed";
                    n += d._width + o
                }
            } else
                for (var s = t; e > s; s++) {
                    var d = l[s];
                    d && !d.hidden && (n += d.outerWidth)
                }
        return n
    }, l.calcHeightFrozenRows = function() {
        var e = this.$tbl,
            r = 0;
        if (e && e.length) {
            var n = t(e[0]).find("tr.pq-last-frozen-row");
            if (n && n.length) {
                var i = n[0];
                r = i.offsetTop + i.offsetHeight
            }
        }
        return r
    }, l._calcRightEdgeCol = function(t) {
        var e = 0,
            r = 0,
            n = this.colModel,
            i = this.initH,
            o = this.options,
            a = o.freezeCols,
            l = o.numberCell;
        l.show && (e += l.outerWidth, r++);
        for (var s = 0; t >= s; s++) {
            i > s && s >= a && (s = i);
            var d = n[s];
            d.hidden || (e += d.outerWidth, r++)
        }
        return {
            width: e,
            cols: r
        }
    }, l._createHeader = function() {
        this.iHeader.createHeader(), this.options.showHeader && this._trigger("createHeader")
    }, l.createTable = function(t) {
        t.other = !0;
        var e = this.iGenerateView;
        e.generateView(t), e.scrollView()
    }
}(jQuery),
function(t) {
    "use strict";
    var e = t.paramquery.cKeyNav = function(t) {
        this.that = t
    };
    e.prototype = {
        bodyKeyPressDown: function(e) {
            var r, n, i, o, a = this.that,
                l = a.riOffset,
                s = a.options,
                d = s.formulasModel,
                c = a.iMerge,
                h = a._focusEle,
                u = s.selectionModel,
                f = s.editModel,
                p = document.activeElement,
                g = e.ctrlKey || e.metaKey,
                v = t.ui.keyCode,
                m = e.keyCode;
            if (f.indices) return void a.$div_focus.find(".pq-cell-focus").focus();
            if (o = t(e.target), o.hasClass("pq-grid-cell")) h = a.getCellIndices({
                $td: o
            });
            else if ("pq-grid-excel" != p.id && "pq-grid-cont" != p.className) return;
            var w, x, y = a.normalize(h),
                n = y.rowIndxPage,
                r = y.rowIndx,
                i = y.colIndx,
                _ = a.pdata,
                b = y,
                I = !0;
            if (null != r && null != i && null != y.rowData) {
                if (c.ismergedCell(r, i) && (b = c.getRootCell(r, i, "o"), y = b, n = y.rowIndxPage, r = y.rowIndx, i = y.colIndx, m != v.PAGE_UP && m != v.PAGE_DOWN && m != v.HOME && m != v.END || (w = c.getData(r, i, "proxy_cell")) && (x = w.rowIndx - l, _[x].pq_hidden || (n = x, r = n + l))), 0 == a._trigger("beforeCellKeyDown", e, b)) return !1;
                if (a._trigger("cellKeyDown", e, b), m == v.LEFT || m == v.RIGHT || m == v.UP || m == v.DOWN || u.onTab && m == v.TAB) {
                    var C = null;
                    m == v.LEFT || m == v.TAB && e.shiftKey ? C = this.incrIndx(n, i, !1) : m == v.RIGHT || m == v.TAB && !e.shiftKey ? C = this.incrIndx(n, i, !0) : m == v.UP ? C = this.decrRowIndx2(n, i) : m == v.DOWN && (C = this.incrRowIndx2(n, i)), C && (r = C.rowIndxPage + l, this.select({
                        rowIndx: r,
                        colIndx: C.colIndx,
                        evt: e
                    }))
                } else if (m == v.PAGE_DOWN || m == v.PAGE_UP) {
                    var q = m == v.PAGE_UP ? "pageUp" : "pageDown",
                        R = this[q](n);
                    R && (n = R.rowIndxPage, null != n && (r = n + l, this.select({
                        rowIndx: r,
                        colIndx: i,
                        evt: e
                    })))
                } else if (m == v.HOME) g ? r = a.getFirstVisibleRIP() + l : i = a.getFirstVisibleCI(), this.select({
                    rowIndx: r,
                    colIndx: i,
                    evt: e
                });
                else if (m == v.END) g ? r = a.getLastVisibleRIP() + l : i = a.getLastVisibleCI(), this.select({
                    rowIndx: r,
                    colIndx: i,
                    evt: e
                });
                else if (m == v.ENTER) {
                    n = b.rowIndxPage, i = b.colIndx;
                    var D = a.getCell({
                        rowIndxPage: n,
                        colIndx: i
                    });
                    if (D && D.length > 0) {
                        var r = n + l,
                            M = a.isEditableRow({
                                rowIndx: r
                            }),
                            T = a.isEditableCell({
                                rowIndx: r,
                                colIndx: i
                            });
                        if (M && T) a.editCell({
                            rowIndxPage: n,
                            colIndx: i
                        });
                        else {
                            var P = D.find("button");
                            P.length && t(P[0]).click()
                        }
                    }
                } else if (g && "65" == m) {
                    var E = a.iSelection;
                    "row" == u.type && "single" != u.mode ? a.iRows.toggleAll({
                        all: u.all
                    }) : "cell" == u.type && "single" != u.mode && E.selectAll({
                        type: "cell",
                        all: u.all
                    })
                } else f.pressToEdit && (this.isEditKey(m) || d.on && 187 == m) && !g ? 46 == m ? a.clear() : (n = b.rowIndxPage, i = b.colIndx, D = a.getCell({
                    rowIndxPage: n,
                    colIndx: i
                }), D && D.length && (r = n + l, M = a.isEditableRow({
                    rowIndx: r
                }), T = a.isEditableCell({
                    rowIndx: r,
                    colIndx: i
                }), M && T && a.editCell({
                    rowIndxPage: n,
                    colIndx: i,
                    select: !0
                })), I = !1) : I = !1;
                I && e.preventDefault()
            }
        },
        decrPageSize: function() {
            var e, r = this.that,
                n = r.$tbl,
                i = n.children("tbody").children(".pq-grid-row"),
                o = r.options.freezeRows,
                a = r.pdata,
                l = 0;
            if (i.length) {
                var s;
                if (o ? (s = i.filter("tr.pq-last-frozen-row"), s.length && (s = s.next())) : i.length >= 2 && (s = t(i[1])), s && s.length)
                    for (var l = r.getRowIndx({
                            $tr: s
                        }).rowIndxPage, e = l, d = 0, c = r.pageSize - 3, h = l; h >= 0; h--) {
                        var u = a[h];
                        if (!u.pq_hidden && (d++, e = h, d >= c)) break
                    }
            }
            return {
                rowIndxPage: e
            }
        },
        decrRowIndx: function(t, e) {
            for (var r = this.that, n = t, i = r.pdata, e = 1, o = 0, a = t - 1; a >= 0; a--) {
                var l = i[a].pq_hidden;
                if (!l && (o++, n = a, o == e)) return n
            }
            return n
        },
        decrRowIndx2: function(t, e) {
            var r, n, i = this.that,
                o = i.riOffset,
                a = t + o,
                l = i.iMerge,
                s = i.pdata;
            if (r = l.ismergedCell(a, e)) {
                var d = l.getRootCell(a, e, "a"),
                    n = l.getData(a, e, "proxy_cell"),
                    e = d.colIndx;
                e = n ? n.colIndx : e
            }
            for (var c = t - 1; c >= 0; c--) {
                var h = s[c].pq_hidden;
                if (!h) {
                    t = c;
                    break
                }
            }
            return {
                rowIndxPage: t,
                colIndx: e
            }
        },
        getMergeCell: function(t, e) {
            var r, n, i = this.that,
                o = i.options,
                a = i.iMerge;
            return a.ismergedCell(t, e) && (n = a.getRootCell(t, e, "o"), a.setData(n.rowIndx, n.colIndx, {
                proxy_cell: {
                    rowIndx: t,
                    colIndx: e
                }
            }), o.virtualY && (r = a.getRootCell(t, e, "a"))), r || (t = this.getVisibleRowIndx(t), e = this.getVisibleColIndx(e), r = i.normalize({
                rowIndx: t,
                colIndx: e
            })), r
        },
        getValText: function(e) {
            var r = e[0].nodeName.toLowerCase(),
                n = ["input", "textarea", "select"],
                i = "text";
            return -1 != t.inArray(r, n) && (i = "val"), i
        },
        getVisibleRowIndx: function(t) {
            for (var e = this.that, r = e.pdata, n = r.length, i = e.riOffset, o = t - i, a = e.getRowData({
                    rowIndx: t
                }); a.pq_hidden && n - 1 > o;) o++, t++, a = e.getRowData({
                rowIndx: t
            });
            return t
        },
        getVisibleColIndx: function(t) {
            for (var e = this.that, r = e.colModel, n = r.length, i = r[t]; i.hidden && n - 1 > t;) t++, i = r[t];
            return t
        },
        incrEditIndx: function(t, e, r) {
            var n, i = this.that,
                o = i.colModel,
                a = o.length,
                l = i.iMerge,
                s = i.riOffset,
                d = i[r ? "getLastVisibleRIP" : "getFirstVisibleRIP"]();
            do {
                var c = t + s,
                    h = l.ismergedCell(c, e);
                if (h) {
                    var u = l.getData(c, e, "proxy_edit_cell");
                    u && (c = u.rowIndx, t = c - s), e = r ? e + h.colspan : e - 1
                } else e = r ? e + 1 : e - 1;
                if (r && e >= a || !r && 0 > e) {
                    if (t == d) return null;
                    do {
                        t = this[r ? "incrRowIndx" : "decrRowIndx"](t);
                        var f = i.isEditableRow({
                            rowIndxPage: t
                        });
                        if (t == d && 0 == f) return null
                    } while (0 == f);
                    e = r ? 0 : a - 1
                }
                if (c = t + s, h = l.ismergedCell(c, e)) {
                    var p = l.getRootCell(c, e, "o");
                    l.setData(p.rowIndx, p.colIndx, {
                        proxy_edit_cell: {
                            rowIndx: c,
                            colIndx: e
                        }
                    }), c = p.rowIndx, e = p.colIndx
                }
                n = o[e];
                var g = i.isEditableCell({
                        rowIndx: c,
                        colIndx: e,
                        checkVisible: !0
                    }),
                    v = n.editor,
                    v = "function" == typeof v ? v.call(i, i.normalize({
                        rowIndx: c,
                        colIndx: e
                    })) : v;
                t = c - s
            } while (n && (n.hidden || 0 == g || v === !1));
            return {
                rowIndxPage: t,
                colIndx: e
            }
        },
        incrIndx: function(t, e, r) {
            var n, i, o, a, l, s = this.that,
                d = s.iMerge,
                c = s.pdata,
                h = s.riOffset,
                u = s[r ? "getLastVisibleRIP" : "getFirstVisibleRIP"](),
                f = s.colModel,
                p = f.length;
            if (null == e) return t == u ? null : (t = this[r ? "incrRowIndx" : "decrRowIndx"](t), {
                rowIndxPage: t
            });
            do {
                if (o = t + h, (n = d.ismergedCell(o, e)) && (!l && (i = d.getData(o, e, "proxy_cell")) && (a = i.rowIndx - h, c[a].pq_hidden || (t = a)), c[t].pq_hidden && (t = d.getRootCell(o, e).rowIndxPage), !l && r && (e += n && n.colspan ? n.colspan - 1 : 0)), e = r ? e + 1 : e - 1, r && e >= p || !r && 0 > e) {
                    if (t == u) return null;
                    t = this[r ? "incrRowIndx" : "decrRowIndx"](t), e = r ? 0 : p - 1
                }
                l = f[e]
            } while (l && l.hidden);
            return {
                rowIndxPage: t,
                colIndx: e
            }
        },
        incrPageSize: function() {
            for (var e = this.that, r = e.$tbl, n = r.children("tbody").children(".pq-grid-row"), i = parseInt(r.css("marginTop")), o = e.iRefresh.getEContHt() - i, a = n.length - 1; a >= 0; a--) {
                var l = n[a];
                if (l.offsetTop < o) break
            }
            var s = e.getRowIndx({
                $tr: t(l)
            }).rowIndxPage;
            return {
                rowIndxPage: s
            }
        },
        incrRowIndx: function(t, e) {
            for (var r = this.that, n = t, e = 1, i = r.pdata, o = 0, a = t + 1, l = i.length; l > a; a++) {
                var s = i[a].pq_hidden;
                if (!s && (o++, n = a, o == e)) return n
            }
            return n
        },
        incrRowIndx2: function(t, e) {
            var r, n, i = this.that,
                o = i.riOffset,
                a = t + o,
                l = i.iMerge,
                s = i.pdata;
            if (r = l.ismergedCell(a, e)) {
                var d = l.getRootCell(a, e, "a"),
                    n = l.getData(a, e, "proxy_cell"),
                    e = d.colIndx;
                t = r.rowspan ? t + r.rowspan - 1 : 0, e = n ? n.colIndx : e
            }
            for (var c = t + 1, h = s.length; h > c; c++) {
                var u = s[c].pq_hidden;
                if (!u) {
                    t = c;
                    break
                }
            }
            return {
                rowIndxPage: t,
                colIndx: e
            }
        },
        isEditKey: function(t) {
            return t >= 32 && 127 >= t || 189 == t
        },
        keyDownInEdit: function(e) {
            var r = this.that,
                n = r.options,
                i = n.editModel.indices;
            if (i) {
                var o = t(e.target),
                    a = t.ui.keyCode,
                    l = n.editModel,
                    s = t.extend({}, i),
                    d = s.rowIndxPage,
                    c = s.colIndx,
                    h = s.column,
                    u = h.editModel,
                    f = u ? t.extend({}, l, u) : l,
                    p = this.getValText(o);
                if (o.data("oldVal", o[p]()), 0 == r._trigger("editorKeyDown", e, s)) return !1;
                if (e.keyCode == a.TAB || e.keyCode == f.saveKey) {
                    var g = e.keyCode == a.TAB ? f.onTab : f.onSave,
                        s = {
                            rowIndxPage: d,
                            colIndx: c,
                            incr: !!g,
                            edit: "nextEdit" == g
                        };
                    return this.saveAndMove(s, e)
                }
                if (e.keyCode == a.ESCAPE) return r.quitEditMode({
                    evt: e
                }), r.focus({
                    rowIndxPage: d,
                    colIndx: c
                }), e.preventDefault(), !1;
                if (e.keyCode == a.PAGE_UP || e.keyCode == a.PAGE_DOWN) return e.preventDefault(), !1;
                if (f.keyUpDown && !e.altKey) {
                    if (e.keyCode == a.DOWN) {
                        var s = this.incrRowIndx2(d, c);
                        return this.saveAndMove(s, e)
                    }
                    if (e.keyCode == a.UP) {
                        var s = this.decrRowIndx2(d, c);
                        return this.saveAndMove(s, e)
                    }
                }
            }
        },
        keyPressInEdit: function(e, r) {
            var n = this.that,
                i = n.options,
                o = i.editModel.indices,
                a = r || {},
                l = a.FK,
                s = o.column,
                d = t.ui.keyCode,
                c = ["BACKSPACE", "LEFT", "RIGHT", "UP", "DOWN", "DELETE", "HOME", "END"].map(function(t) {
                    return d[t]
                }),
                h = s.dataType;
            if (t.inArray(e.keyCode, c) >= 0) return !0;
            if (n._trigger("editorKeyPress", e, t.extend({}, o)) === !1) return !1;
            if (l && ("float" == h || "integer" == h)) {
                var u = o.$editor.val(),
                    f = "float" == h ? "0123456789.-=" : "0123456789-=",
                    p = e.charCode || e.keyCode,
                    g = String.fromCharCode(p);
                if ("=" !== u[0] && g && -1 == f.indexOf(g)) return !1
            }
            return !0
        },
        keyUpInEdit: function(e, r) {
            var n = this.that,
                i = n.options,
                o = r || {},
                a = o.FK,
                l = i.editModel,
                s = l.indices;
            n._trigger("editorKeyUp", e, t.extend({}, s));
            var d = s.column,
                c = d.dataType;
            if (a && ("float" == c || "integer" == c)) {
                var h = t(e.target),
                    u = "integer" == c ? l.reInt : l.reFloat,
                    f = this.getValText(h),
                    p = h.data("oldVal"),
                    g = h[f]();
                if (0 == u.test(g) && "=" !== g[0])
                    if (u.test(p)) h[f](p);
                    else {
                        var v = "float" == c ? parseFloat(p) : parseInt(p);
                        isNaN(v) ? h[f](0) : h[f](v)
                    }
            }
        },
        pageNonVirtual: function(e, r) {
            var n, i = this.that,
                o = i.$cont[0].offsetHeight - i._getSBHeight(),
                a = i.getRow({
                    rowIndxPage: e
                }),
                l = 0,
                s = 0,
                d = t(a[0])[r]("tr.pq-grid-row"),
                c = d.length;
            if (c > 0) {
                do {
                    n = d[s];
                    var h = n.offsetHeight;
                    if (l += h, l >= o) break;
                    s++
                } while (c > s);
                s = s > 0 ? s - 1 : s;
                do {
                    var a = t(d[s]);
                    if (e = i.getRowIndx({
                            $tr: a
                        }).rowIndxPage, null != e) break;
                    s--
                } while (s >= 0)
            }
            return e
        },
        pageDown: function(t) {
            var e = this.that,
                r = e.options,
                n = e.vscroll,
                i = n.option("cur_pos"),
                o = n.option("num_eles"),
                a = n.option("ratio");
            if (r.virtualY) {
                if (o - 1 > i) {
                    var t = this.incrPageSize().rowIndxPage,
                        l = e._calcCurPosFromRowIndxPage(t);
                    if (null == l) return;
                    n.option("cur_pos", l), n.scroll()
                }
            } else if (null != t) t = this.pageNonVirtual(t, "nextAll");
            else if (1 > a) {
                var s = e.iRefresh.getEContHt(),
                    d = e.iMouseSelection;
                d.updateTableY(-1 * s), d.syncScrollBarVert()
            }
            return {
                rowIndxPage: t,
                curPos: l
            }
        },
        pageUp: function(t) {
            var e = this.that,
                r = e.options,
                n = e.vscroll;
            if (r.virtualY) {
                var i = n.option("cur_pos");
                if (i > 0) {
                    var t = this.decrPageSize().rowIndxPage,
                        o = e._calcCurPosFromRowIndxPage(t);
                    if (null == o) return;
                    n.option("cur_pos", o), n.scroll()
                }
            } else {
                var a = n.option("ratio");
                if (null != t) t = this.pageNonVirtual(t, "prevAll");
                else if (a > 0) {
                    var l = e.iRefresh.getEContHt(),
                        s = e.iMouseSelection;
                    s.updateTableY(l), s.syncScrollBarVert()
                }
            }
            return {
                rowIndxPage: t,
                curPos: o
            }
        },
        saveAndMove: function(t, e) {
            if (null == t) return e.preventDefault(), !1;
            var r = this.that,
                n = t.rowIndxPage,
                i = t.colIndx;
            if (r._blurEditMode = !0, r.saveEditCell({
                    evt: e
                }) === !1 || !r.pdata) return r.pdata || r.quitEditMode(e), r._deleteBlurEditMode({
                timer: !0,
                msg: "saveAndMove saveEditCell"
            }), e.preventDefault(), !1;
            if (r.quitEditMode(e), t.incr) {
                var o = this[t.edit ? "incrEditIndx" : "incrIndx"](n, i, !e.shiftKey);
                n = o ? o.rowIndxPage : n, i = o ? o.colIndx : i
            }
            r.scrollRow({
                rowIndxPage: n
            }), r.scrollColumn({
                colIndx: i
            });
            var a = n + r.riOffset;
            return this.select({
                rowIndx: a,
                colIndx: i,
                evt: e
            }), t.edit && r._editCell({
                rowIndxPage: n,
                colIndx: i
            }), r._deleteBlurEditMode({
                timer: !0,
                msg: "saveAndMove"
            }), e.preventDefault(), !1
        },
        select: function(e) {
            var r = this.that,
                n = this,
                i = e.rowIndx,
                o = e.colIndx,
                a = e.evt,
                e = this.getMergeCell(i, o),
                i = e.rowIndx,
                o = e.colIndx,
                l = e.rowIndxPage,
                s = r.options,
                d = r.iSelection,
                c = s.selectionModel,
                h = c.type,
                u = "row" == h,
                f = "cell" == h,
                p = s.realFocus ? function(t) {
                    clearTimeout(n.timeoutID), n.timeoutID = setTimeout(function() {
                        r.options && t()
                    }, 0)
                } : function(t) {
                    t()
                };
            p(function() {
                r.scrollCell({
                    rowIndx: i,
                    colIndx: o
                });
                var e = d.address();
                if (a.shiftKey && a.keyCode !== t.ui.keyCode.TAB && c.type && "single" != c.mode && (e.length || u))
                    if (u) r.iRows.extend({
                        rowIndx: i,
                        evt: a
                    });
                    else {
                        var n = e[e.length - 1],
                            s = n.firstR,
                            h = n.firstC,
                            p = n.type,
                            g = !1;
                        "column" == p ? (n.c1 = h, n.c2 = o, n.r1 = n.r2 = n.type = void 0) : (e = {
                            _type: "block",
                            r1: s,
                            r2: i,
                            c1: h,
                            c2: o,
                            firstR: s,
                            firstC: h
                        }, g = !0), r.Range(e, g).select()
                    }
                else u || f && r.Range({
                    r1: i,
                    c1: o,
                    firstR: i,
                    firstC: o
                }).select();
                r.focus({
                    rowIndxPage: l,
                    colIndx: o
                })
            })
        }
    }
}(jQuery),
function(t) {
    "use strict";
    var e = t.paramquery,
        r = e.cGenerateView = function(t) {
            this.that = t;
            var e = this.options = t.options,
                r = this;
            null != e.postRenderInterval && t.on("refresh", function() {
                r.prColDef.length && r.postRenderAll()
            }).on("refreshRow", function(t, e) {
                r.prColDef.length && r.postRenderRow(e)
            }).on("refreshCell", function(t, e) {
                e.column.postRender && r.postRenderRow(e)
            }).on("refreshColumn", function(t, e) {
                e.column.postRender && r.setTimerPostRender(0, e.colIndx, e.column)
            }), e.mergeModel.flex || t.on("refresh refreshCell refreshRow refreshColumn", r.onRefreshMerge(t))
        };
    r.prototype = {
        appendRow: function(t) {
            var e = this.that,
                r = e.pdata,
                n = e.colModel,
                i = e.finalV;
            if (i + t > r.length && (t = r.length - i), t) {
                if (t > 1) throw "noRows > 1";
                e._trigger("beforeTableView", null, {
                    pageData: r,
                    initV: i,
                    finalV: i,
                    initH: e.initH,
                    finalH: e.finalH,
                    colModel: n
                }), e.refreshRow({
                    rowIndxPage: i,
                    refresh: !1
                })
            }
            return t
        },
        createColDefs: function() {
            for (var t = this.that, e = t.colModel, r = t.initH, n = t.finalH, i = t.options, o = i.freezeCols, a = [], l = [], s = 0; n >= s; s++) {
                if (r > s && s >= o && (s = r, s > n)) throw "initH>finalH";
                var d = e[s];
                d.hidden || (a.push({
                    column: d,
                    colIndx: s
                }), d.postRender && l.push({
                    column: d,
                    colIndx: s
                }))
            }
            this.colDef = a, this.prColDef = l
        },
        format: function() {
            var e = t.datepicker,
                r = pq.formatNumber;
            return function(t, n) {
                if (pq.isDateFormat(n)) {
                    if (t == parseInt(t)) return pq.formulas.TEXT(t, pq.juiToExcel(n));
                    if (isNaN(Date.parse(t))) return;
                    return e.formatDate(n, new Date(t))
                }
                return r(t, n)
            }
        }(),
        generateTables: function(t, e, r) {
            r = r || {};
            var n, i = this.that,
                o = i.options,
                a = o.bootstrap,
                l = o.numberCell,
                s = (i.iRefresh, 1 * o.freezeRows),
                d = 0,
                c = r.other,
                h = c ? r.data.length - 1 : e;
            if (c ? n = r.data : (n = i.pdata, this.setFrozenRip(n, s)), c || null != t && null != h) {
                var u = ["pq-grid-table"];
                a.on && u.push(a.tbody), o.columnBorders && u.push("pq-td-border-right"), o.rowBorders && u.push("pq-td-border-top"), u.push(o.wrap ? "pq-wrap" : "pq-no-wrap");
                var f = ["<table class='" + u.join(" ") + "' >"];
                if (f.push("<tr class='pq-row-hidden'>"), l.show) {
                    var p = l.width;
                    f.push("<td style='width:" + p + "px;' ></td>")
                }
                for (var g = this.colDef, v = 0, m = g.length; m > v; v++) {
                    var w = g[v],
                        x = w.colIndx,
                        y = w.column,
                        p = y.outerWidth;
                    f.push("<td style='width:", p, "px;' pq-col-indx='", x, "'></td>")
                }
                f.push("</tr>");
                for (var d = 0; h >= d; d++) {
                    t > d && d >= s && (d = t);
                    var _ = n[d],
                        b = d,
                        I = _ ? _.pq_hidden : !1;
                    if (!I) {
                        var C = _.pq_detail && _.pq_detail.show;
                        this.generateRow(_, b, f, c, C), C && this.generateDetailRow(_, b, f)
                    }
                }
                return f.push("</table>"), f.join("")
            }
        },
        generateView: function(t) {
            t = t || {};
            var r, n, i = this,
                o = this.that,
                a = o.options,
                l = "flex" === a.width,
                s = "flex" === a.height,
                d = a.cls,
                c = d.cont_inner,
                h = d.cont_inner_b,
                u = o.initH,
                f = o.finalH,
                p = o.pqpanes;
            if (t.$cont) {
                var g = this.generateTables(null, null, t),
                    v = t.$cont;
                v.empty(), p.v ? v[0].innerHTML = ["<div class='", c, "'>", g, "</div>", "<div class='", c, "'>", g, "</div>"].join("") : v[0].innerHTML = ["<div class='", c, "'>", g, "</div>"].join("");
                var m = v.children("div"),
                    w = m.children("table");
                o.tables[0] = {
                    $tbl: w,
                    $cont: v
                }
            } else {
                r = o.initV, n = o.finalV;
                var x = o.pdata,
                    v = o.$cont;
                null != a.editModel.indices && o.blurEditor({
                    force: !0
                });
                var y = {
                    pageData: x,
                    initV: r,
                    finalV: n,
                    initH: u,
                    finalH: f,
                    source: t.source
                };
                o._trigger("beforeTableView", null, y);
                var g = i.generateTables(r, n, t);
                if (v.empty(), 0 === o.totalVisibleRows) v.append("<div class='" + c + " pq-grid-norows' >" + a.strNoRows + "</div>");
                else {
                    var _ = s || l ? "style='position:relative;'" : "";
                    p.h && p.v ? v[0].innerHTML = ["<div class='", c, "'>", g, "</div>", "<div class='", c, "'>", g, "</div>", "<div class='", c, " ", h, "'>", g, "</div>", "<div class='", c, " ", h, "'>", g, "</div>"].join("") : p.v ? v[0].innerHTML = ["<div class='", c, "' ", _, " >", g, "</div>", "<div class='", c, "' >", g, "</div>"].join("") : p.h ? v[0].innerHTML = ["<div class='", c, "' style='position:relative;' >", g, "</div>", "<div class='", c, " ", h, "' style='position:relative;' >", g, "</div>"].join("") : v[0].innerHTML = ["<div class='", c, "' ", _, " >", g, "</div>"].join("")
                }
                o.$tbl = v.children("div").children("table"), a.scrollModel.flexContent && o.$tbl[0] && e.onResizeDiv(o.$tbl[0], function() {
                    o._trigger("resizeTable")
                }), this.setPaneEvents(), o._trigger("refresh", null, y)
            }
            this.setPanes()
        },
        generateRow: function(t, e, r, n, i) {
            var o = ["pq-grid-row"];
            this.frozenRip === e && o.push("pq-last-frozen-row"), i && o.push("pq-detail-master");
            var a = this.that,
                l = this.options,
                s = l.rowInit,
                d = l.numberCell,
                c = "",
                h = "",
                u = a.riOffset,
                f = e + u;
            if (s) {
                var p = s.call(a, {
                    rowData: t,
                    rowIndxPage: e,
                    rowIndx: f
                });
                p && (p.cls && o.push(p.cls), c += p.attr ? " " + p.attr : "", h += p.style ? p.style : "")
            }
            l.stripeRows && o.push("pq-striped"), t.pq_rowselect && o.push(a.iRows.hclass);
            var g = t.pq_rowcls;
            null != g && o.push(g);
            var v = t.pq_rowattr;
            if (v) {
                var m = a.stringifyAttr(v);
                for (var w in m) {
                    var x = m[w];
                    c += " " + w + '="' + x + '"'
                }
            }
            h = h ? "style='" + h + "'" : "", r.push("<tr pq-row-indx='", e, "' class='", o.join(" "), "' ", c, " ", h, " >"), d.show && r.push(["<td pq-col-indx='-1' class='pq-grid-number-cell'>", n ? "&nbsp;" : f + 1, "</td>"].join(""));
            for (var y = {
                    rowIndx: e + u,
                    rowIndxPage: e,
                    other: n,
                    rowData: t
                }, _ = this.colDef, b = 0, I = _.length; I > b; b++) {
                var C = _[b],
                    q = C.colIndx,
                    R = C.column;
                y.column = R, y.colIndx = q, r.push(this.renderCell(y))
            }
            return r.push("</tr>"), r
        },
        generateDetailRow: function(t, e, r) {
            var n = ["pq-grid-row pq-detail-child"],
                i = this.that,
                o = i.options,
                a = t.pq_rowcls,
                l = o.numberCell,
                s = "pq-grid-cell";
            return o.stripeRows && n.push("pq-striped"), t.pq_rowselect && n.push(i.iRows.hclass), a && n.push(a), r.push("<tr pq-row-indx='" + e + "' class='" + n.join(" ") + "' >"), l.show && r.push(["<td class='pq-grid-number-cell'>", "&nbsp;</td>"].join("")), r.push("<td class='" + s + " pq-detail-child' colSpan='20'></td>"), r.push("</tr>"), r
        },
        onRefreshMerge: function(e) {
            return function() {
                for (var r, n, i, o, a, l, s, d = e.$cont.find(".pq-merge-cell-div"), c = d.length, h = [], u = []; c--;) r = d[c], n = r.parentNode, i || (i = 2 * parseInt(t(n).css("paddingTop")), o = 2 * parseInt(t(n).css("paddingLeft"))), a = Math.max(n.offsetHeight, 28), l = a - i, s = n.offsetWidth - o, h[c] = l - 1 + "px", u[c] = s + "px";
                for (c = d.length; c--;) r = d[c], r.style.height = h[c], r.style.width = u[c]
            }
        },
        postRenderAll: function() {
            var t = this.that,
                e = this,
                r = t.pdata,
                n = t.options.postRenderInterval,
                i = function(t) {
                    return -1 == n ? t() : setTimeout(t, n)
                };
            r && r.length && (clearTimeout(e.postRenderTimeoutID), e.postRenderTimeoutID = i(function() {
                e.setTimerPostRender(0)
            }))
        },
        postRenderRow: function(t) {
            var e, r = this.that,
                n = this.prColDef,
                i = r.iMerge,
                o = t.rowIndx,
                a = r;
            null != t.colIndx && (n = [{
                colIndx: t.colIndx,
                column: t.column
            }]);
            for (var l = 0, s = n.length; s > l; l++) {
                var d = n[l],
                    e = d.column.postRender,
                    c = d.colIndx;
                i.ismergedCell(o, c) ? i.isRootCell(o, c, "a") && (t = i.getRootCell(o, c), a.callFn(e, t)) : (t = r.normalize({
                    rowIndx: o,
                    colIndx: c
                }), a.callFn(e, t))
            }
        },
        prependRow: function(t) {
            var e = this.that,
                r = e.options.freezeRows,
                n = e.calcVisibleRows(e.pdata, 0, r),
                i = e.pdata,
                o = e.initV,
                a = e.colModel;
            if (e._mergeCells) {
                if (n)
                    for (var l = 0; r > l; l++) e.refreshRow({
                        rowIndxPage: l,
                        refresh: !1
                    });
                e.refreshRow({
                    rowIndxPage: o + 1,
                    refresh: !1
                })
            }
            return e._trigger("beforeTableView", null, {
                pageData: i,
                initV: o,
                finalV: o,
                initH: e.initH,
                finalH: e.finalH,
                colModel: a
            }), e.refreshRow({
                rowIndxPage: o,
                refresh: !1
            }), t
        },
        removeTopRow: function(e) {
            for (var r = this.that, n = r.options.freezeRows, i = r.calcVisibleRows(r.pdata, 0, n), o = r.$tbl, a = t([]), l = 0; l < o.length; l++) {
                var s = t(o[l]),
                    d = s.children("tbody"),
                    c = d.children("tr:gt(0)").slice(i, e + i);
                a = a.add(c)
            }
            if (a.remove(), r._mergeCells) {
                if (i)
                    for (var l = 0; n > l; l++) r.refreshRow({
                        rowIndxPage: l,
                        refresh: !1
                    });
                r.refreshRow({
                    rowIndxPage: r.initV,
                    refresh: !1
                })
            }
        },
        removeBottomRow: function(e) {
            var r = this.that,
                n = r.$tbl;
            if (e)
                for (var i = 0; i < n.length; i++) {
                    var o = t(n[i]),
                        a = o.children("tbody"),
                        l = a.children("tr:gt(0)").slice(-e);
                    l.remove()
                }
        },
        renderCell: function(t) {
            var e, r, n, i = this.that,
                o = [],
                a = [],
                l = t.Export,
                s = !1,
                d = this.options,
                c = ["pq-grid-cell"];
            if (!t.other && !l && i._mergeCells) {
                if (t = i.iMerge.renderCell(t), null == t) return "";
                t.rowspan && (o.push("rowspan='" + t.rowspan + "'", "colspan='" + t.colspan + "'"), d.mergeModel.flex || (s = !0, c.push("pq-merge-cell"))), t.style && a.push(t.style), t.cls && c.push(t.cls)
            }
            var h, u = t.rowData,
                f = t.column,
                p = f.dataType,
                g = t.colIndx,
                v = f.dataIndx,
                m = d.freezeCols,
                w = d.columnBorders;
            if (u) {
                l || (o.push("pq-col-indx='" + g + "'"), f.align && c.push("pq-align-" + f.align), g == m - 1 && w && c.push("pq-last-frozen-col"), f.cls && c.push(f.cls));
                var x, y, _ = u[v],
                    _ = "string" == typeof _ && "html" != p ? pq.escapeHtml(_) : _,
                    b = f.format || (y = u.pq_format) && (y = y[v]),
                    I = b ? this.format(_, b, p) : _;
                if (t.dataIndx = v, t.cellData = _, t.formatVal = I, (h = f.render) && (x = i.callFn(h, t), x && "string" != typeof x && ((e = x.attr) && o.push(e), (n = x.cls) && c.push(n), (r = x.style) && a.push(r), x = x.text)), null == x && (h = f._render) && (x = h.call(i, t)), x && "string" != typeof x && ((e = x.attr) && o.push(e), (n = x.cls) && c.push(n), (r = x.style) && a.push(r), x = x.text), null == x && (x = I || _), l) return [x, r];
                var C = u.pq_cellcls;
                if (C) {
                    var q = C[v];
                    q && c.push(q)
                }
                var R = u.pq_cellattr;
                if (R) {
                    var D = R[v];
                    if (D) {
                        var M = i.stringifyAttr(D);
                        for (var T in M) {
                            var P = M[T];
                            "style" == T ? a.push(P) : o.push(T + '="' + P + '"')
                        }
                    }
                }
                a = a.length ? " style='" + a.join("") + "' " : "", "" !== x && void 0 != x || (x = "&nbsp;");
                var E = ["<td class='", c.join(" "), "' ", o.join(" "), a, " >", s ? "<div class='pq-merge-cell-div'><div><div class='pq-merge-inner'>" : "", x, s ? "</div></div></div>" : "", "</td>"].join("");
                return E
            }
        },
        refreshRow: function(t, e) {
            var r = this.that,
                n = r.pdata[t],
                i = n.pq_detail && n.pq_detail.show;
            this.generateRow(n, t, e, null, i)
        },
        setTimerPostRender: function(t, e, r) {
            var n = this.that,
                i = this,
                o = n.pdata,
                a = function(t) {
                    return -1 == n.options.postRenderInterval ? t() : setTimeout(t, 0)
                };
            o && o.length && (i.postRenderTimeoutID = a(function() {
                var a = n.initV,
                    l = n.options.freezeRows;
                a > t && t >= l && (t = a);
                var s = o[t];
                s.pq_hidden || i.postRenderRow({
                    column: r,
                    colIndx: e,
                    rowIndx: t + n.riOffset
                }), t++, t <= n.finalV && i.setTimerPostRender(t)
            }))
        },
        scrollView: function() {
            var t = this.that,
                e = this.options,
                r = e.virtualX,
                n = e.virtualY;
            r || t.hscroll.drag(), n || t.vscroll.drag()
        },
        setFrozenRip: function(t, e) {
            if (t) {
                for (var r; e-- && (r = t[e]) && r.pq_hidden;);
                this.frozenRip = e
            }
        },
        setPaneEvents: function() {
            var e = this.that,
                r = e.$cont,
                n = e.pqpanes,
                i = r.children("div"),
                o = e.iMouseSelection,
                a = e.$tbl;
            if (a && a.length)
                if (n.h && n.v) {
                    var l = t(i[0]),
                        s = t(i[1]),
                        d = t(i[2]),
                        c = t(i[3]);
                    l.on("scroll", function(t) {
                        this.scrollTop = 0, this.scrollLeft = 0
                    }), s.on("scroll", function(t) {
                        this.scrollTop = 0, this.scrollLeft = o.getScrollLeft(!0)
                    }), d.on("scroll", function(t) {
                        this.scrollTop = o.getScrollTop(!0), this.scrollLeft = 0
                    }), c.on("scroll", function(t) {
                        this.scrollTop = o.getScrollTop(!0), this.scrollLeft = o.getScrollLeft(!0)
                    })
                } else if (n.v) {
                var h = t(i[0]),
                    u = t(i[1]);
                h.on("scroll", function(t) {
                    this.scrollTop = o.getScrollTop(!0), this.scrollLeft = 0
                }), u.on("scroll", function(t) {
                    this.scrollTop = o.getScrollTop(!0), this.scrollLeft = o.getScrollLeft(!0)
                })
            } else if (n.h) {
                var f = t(i[0]),
                    p = t(i[1]);
                f.on("scroll", function(t) {
                    this.scrollTop = 0, this.scrollLeft = o.getScrollLeft(!0)
                }), p.on("scroll", function(t) {
                    this.scrollTop = o.getScrollTop(!0), this.scrollLeft = o.getScrollLeft(!0)
                })
            } else i.on("scroll", function(t) {
                this.scrollTop = o.getScrollTop(!0), this.scrollLeft = o.getScrollLeft(!0)
            })
        },
        setPanes: function() {
            var e, r = this.that,
                n = r.$cont,
                i = r.pqpanes,
                o = n.children("div"),
                a = r.iRefresh,
                l = r.$tbl,
                s = r.options,
                d = 1 * s.freezeCols,
                c = (r.initH, 1),
                h = r.calcWidthCols(-1, d) + c,
                u = "flex" === s.width,
                f = u && !s.maxWidth ? "" : a.getEContWd();
            if (r.tables.length) {
                var p = r.tables[0].$tbl,
                    g = r.tables[0].$cont,
                    v = p.parent("div");
                i.v ? (t(v[0]).css({
                    width: h
                }), t(v[1]).css({
                    left: h,
                    width: f - h
                }), t(p[1]).css({
                    left: -1 * h
                })) : t(v[0]).css({
                    width: f
                }), g.height(p[0].scrollHeight - 1), a.setContHeight()
            }
            if (l && l.length) {
                var m = "flex" === s.height,
                    w = m && !s.maxHeight ? "" : a.getEContHt();
                if (i.h && i.v) {
                    var x = t(o[0]),
                        y = t(o[1]),
                        _ = t(l[1]),
                        b = t(o[2]),
                        I = t(l[2]),
                        C = t(o[3]),
                        q = t(l[3]),
                        e = r.calcHeightFrozenRows(),
                        R = e;
                    x.css({
                        width: h,
                        height: R
                    }), y.css({
                        left: h,
                        width: f - h,
                        height: R
                    }), _.css({
                        left: -1 * h
                    }), b.css({
                        width: h,
                        top: R,
                        height: w - R
                    }), I.css({
                        top: -(R + 1)
                    }), C.css({
                        left: h,
                        width: f - h,
                        top: R,
                        height: w - R
                    }), q.css({
                        top: -(R + 1),
                        left: -1 * h
                    })
                } else if (i.v) {
                    var D = t(o[0]),
                        M = t(o[1]),
                        T = t(l[1]);
                    D.css({
                        width: h,
                        height: w
                    }), M.css({
                        left: h,
                        width: f - h,
                        height: w
                    }), T.css({
                        left: -1 * h
                    })
                } else if (i.h) {
                    var P = t(o[0]),
                        E = t(o[1]),
                        S = t(l[1]),
                        e = r.calcHeightFrozenRows(),
                        R = e;
                    P.css({
                        height: R,
                        width: f
                    }), E.css({
                        width: f,
                        height: w - R
                    }), S.css({
                        top: -(R + 1)
                    })
                } else o.css({
                    width: f,
                    height: w
                })
            }
            s.showHeader && (i.v ? (r.$header_left.css({
                width: h
            }), r.$header_right.css({
                left: h,
                width: f - h
            }), r.$header_right_inner.css({
                left: -h
            })) : r.$header_left.css({
                width: f
            }))
        }
    }
}(jQuery),
function(t) {
    "use strict";

    function e(t, e, r) {
        for (var n = t[e], i = 0; i < n.length; i++)
            if (n[i] == r) return i
    }

    function r(t, e, r) {
        for (var n = 0, i = e; r > i; i++) {
            var o = t[i];
            o.hidden !== !0 && n++
        }
        return n
    }
    var n = t.paramquery,
        i = n._pqGrid.prototype;
    i.getHeadCell = function(t) {
        var e, r, n, i, o = t.attr("pq-col-indx"),
            a = t.attr("pq-row-indx");
        return null != o && null != a && (o = 1 * o, a = 1 * a, r = this.headerCells[a][o], r && (i = r.colModel, n = r.leftPos)), i && i.length && (e = !0), {
            col: r,
            ci: o,
            o_ci: n,
            ri: a,
            isParent: e
        }
    }, i.flex = function(t) {
        this.iResizeColumns.flex(t)
    }, n.cHeader = function(t) {
        var e = this;
        e.that = t, e.td_cls = "pq-grid-col", t.on("headerKeyDown", e.onKeyDown(e, t)).on("beforeRefreshHeader", e.onBeforeRefreshH(e, t)).on("refreshHeader", e.onRefreshH(e, t))
    }, n.cHeader.prototype = t.extend({
        onBeforeRefreshH: function(e, r) {
            return function() {
                var n = document.activeElement,
                    i = n ? n.className : "",
                    o = e.focusUI,
                    a = t(n);
                o && (o.nofocus = -1 == i.indexOf("pq-grid-col-leaf") || !a.closest(r.element).length)
            }
        },
        onRefreshH: function(t) {
            return function(e) {
                t.setTimer(function() {
                    t.that.options && t.focus()
                }, 100)
            }
        },
        colCollapse: function(t, e) {
            var r = this.that,
                n = {
                    column: t
                },
                i = t.collapsible;
            r._trigger("beforeColumnCollapse", e, n) !== !1 && (i.on = !i.on, r._trigger("columnCollapse", e, n) !== !1 && r.refresh({
                colModel: !0
            }))
        },
        onKeyDown: function(e, r) {
            var n = t.ui.keyCode;
            return function(i) {
                var o, a, l, s, d = i.originalEvent.target,
                    c = t(d).closest(".pq-grid-col-leaf"),
                    h = i.keyCode;
                c.length && (a = r.getHeadCell(c), l = a.ci, h == n.RIGHT ? s = e.getNextVisibleCI(l) : h == n.LEFT ? s = e.getPrevVisibleCI(l) : h == n.ENTER && e._onHeaderCellClick(a.col, l, i), null != s && s != l && (c.removeAttr("tabindex"), o = {
                    colIndx: s
                }, r.scrollColumn(o), e.focus(o)))
            }
        },
        getNextVisibleCI: function(t) {
            for (var e = this.that.colModel, r = e.length, n = t + 1; r > n; n++)
                if (!e[n].hidden) return n;
            return t
        },
        getPrevVisibleCI: function(t) {
            for (var e = this.that.colModel, r = t - 1; r >= 0; r--)
                if (!e[r].hidden) return r;
            return t
        },
        focus: function(t) {
            var e, r = t || this.focusUI,
                n = this.that;
            r && null != r.colIndx && (this.focusUI = r, e = n.getCellHeader(r), e.attr("tabindex", 0), r.nofocus || e.focus())
        },
        createHeader: function() {
            var e = this.that,
                r = this,
                n = e.options,
                i = n.bootstrap,
                o = (i.on ? i.thead : "") + " pq-grid-header-table ",
                a = n.ui,
                l = i.on ? "" : a.header,
                s = n.hwrap,
                d = e.pqpanes,
                c = parseInt(n.freezeCols),
                h = n.numberCell,
                u = e.colModel,
                f = n.sortModel,
                p = r.getSortSpaceSpans(f),
                g = e.depth,
                v = n.virtualX,
                m = e.iGenerateView.colDef,
                w = e.initH,
                x = e.finalH,
                y = e.headerCells,
                _ = e.$header_o;
            if (e._trigger("beforeRefreshHeader"), _.empty(), n.showHeader === !1) return void _.css("display", "none");
            _.css("display", ""), o += s ? "pq-wrap " : "pq-no-wrap ";
            var b = ["<table class='" + o + "' >"];
            if (g >= 1) {
                b.push("<tr class='pq-row-hidden'>"), h.show && b.push("<td style='width:" + h.width + "px;' ></td>");
                for (var I = 0, C = m.length; C > I; I++) {
                    var q = m[I],
                        R = q.colIndx,
                        D = q.column,
                        M = D.outerWidth;
                    b.push("<td style='width:" + M + "px;' pq-col-indx=" + R + "></td>")
                }
                b.push("</tr>")
            }
            for (var T = 0; g > T; T++) {
                b.push("<tr class='pq-grid-title-row'>"), 0 == T && h.show && b.push(["<th pq-col-indx='-1' class='pq-grid-number-col' rowspan='", g, "'>", "<div class='pq-td-div'>", h.title ? h.title : "&nbsp;", "</div></th>"].join(""));
                for (var R = 0; x >= R; R++) {
                    if (w > R && R >= c && v && (R = w, R > x)) throw "initH>finalH";
                    r.createHeaderCell(T, R, y, b, c, w, g, p)
                }
                b.push("</tr>")
            }
            e.ovCreateHeader(b), b.push("</table>");
            var P = b.join("");
            d.v ? _[0].innerHTML = ["<span class='pq-grid-header pq-grid-header-left ", l, "'>", "<div class='pq-grid-header-inner'>", P, "</div>", "</span>", "<span class='pq-grid-header ", l, "'>", "<div class='pq-grid-header-inner'>", P, "</div>", "</span>"].join("") : _[0].innerHTML = ["<span class='pq-grid-header ", l, "'>", "<div class='pq-grid-header-inner'>", P, "</div>", "</span>"].join("");
            var E = e.$header = _.children(".pq-grid-header"),
                S = E.children(".pq-grid-header-inner");
            e.$tbl_header = S.children("table"), e.$header_left = t(E[0]), e.$header_left_inner = t(S[0]), d.v && (e.$header_right = t(E[1]), e.$header_right_inner = t(S[1])), E.click(function(t) {
                return r._onHeaderClick(t)
            }), r._refreshResizeColumn(w, x, u), e._trigger("refreshHeader", null, null)
        },
        _onHeaderClick: function(e) {
            var r, n, i, o, a = this,
                l = this.that,
                s = l.iDragColumns;
            if (!s || "stop" == s.status) {
                if (o = t(e.target), o.is("input,label")) return !0;
                if (r = o.closest(".pq-grid-col"), r.length && (i = l.getHeadCell(r), n = i.col))
                    if (o.hasClass("pq-col-collapse")) a.colCollapse(n, e);
                    else if (!i.isParent) return a._onHeaderCellClick(n, i.ci, e)
            }
        },
        createHeaderCell: function(t, i, o, a, l, s, d, c) {
            var h, u, f, p, g = this.that,
                v = g.options.sheet,
                m = this.td_cls,
                w = o[t][i],
                x = g.colModel,
                y = w.collapsible,
                _ = "",
                b = w.colSpan;
            if (!(w.hidden || 0 === b || t > 0 && w == o[t - 1][i])) {
                if (i > 0 && w == o[t][i - 1]) {
                    if (i > s) return;
                    if (h = e(o, t, w), l > h) return;
                    b -= r(x, h, i)
                } else if (l && l > i && i + b > l) {
                    var I = b - r(x, l, s),
                        C = r(x, i, l);
                    b = Math.max(I, C)
                }
                var q = w.halign || w.align,
                    R = w.cls,
                    D = [m],
                    M = w.title,
                    T = w.type,
                    M = "function" == typeof M ? M.call(g, {
                        column: w,
                        colIndx: i,
                        dataIndx: w.dataIndx
                    }) : M,
                    M = v ? n.toLetter(i) : M,
                    M = null != M ? M : "checkbox" == T && w.cb.header ? "<input type='checkbox'/>" : w.dataIndx;
                w.pqtitle = M, q && D.push("pq-align-" + q), i == l - 1 && 1 == d && D.push("pq-last-frozen-col"), l - 1 >= i ? D.push("pq-left-col") : i >= s && D.push("pq-right-col"), R && D.push(R), null == w.colModel || 0 == w.colModel.length ? (D.push("pq-grid-col-leaf"), _ = i == s ? "tabindex='0' " : "") : (c = "", y && (D.push("pq-collapsible-th"), u = ["<div class='pq-col-collapse'>", y.on ? "+" : "-", "</div>"].join(""))), f = "pq-row-indx=" + t, p = "pq-col-indx=" + i, a.push(["<th ", p, " ", f, " ", _, " class='", D.join(" "), "' rowspan=", w.rowSpan, " colspan=", b, ">", "<div class='pq-td-div'>", M, c, "</div>", u, "</th>"].join(""))
            }
        },
        getSortSpaceSpans: function(t) {
            var e = t.space ? " pq-space" : "";
            return ["<span class='pq-col-sort-icon", e, "'></span>", t.number ? "<span class='pq-col-sort-count" + e + "'></span>" : ""].join("")
        },
        _onHeaderCellClick: function(t, e, r) {
            var n = this.that,
                i = n.options,
                o = i.sortModel,
                a = t.dataIndx;
            if (n._trigger("headerCellClick", r, {
                    column: t,
                    colIndx: e,
                    dataIndx: a
                }) !== !1)
                if (i.selectionModel.column && -1 == r.target.className.indexOf("pq-td-div")) {
                    var l = {
                            c1: e,
                            firstC: e
                        },
                        s = n.iSelection.address();
                    if (r.shiftKey) {
                        var d = s.length;
                        if (d && "column" == s[d - 1].type) {
                            var c = s[d - 1];
                            c.c1 = c.firstC, c.c2 = e, c.r1 = c.r2 = c.type = void 0
                        }
                        l = s
                    }
                    n.Range(l, !1).select(), n.focus({
                        rowIndxPage: n.getFirstVisibleRIP(!0),
                        colIndx: e
                    })
                } else if (o.on) {
                if (0 == t.sortable) return;
                n.sort({
                    sorter: [{
                        dataIndx: a
                    }],
                    addon: !0,
                    tempMultiple: o.multiKey && r[o.multiKey],
                    evt: r
                }), this.focus({
                    colIndx: e
                })
            }
        },
        _refreshResizeColumn: function(t, e, r) {
            var n = this.that,
                i = n.options,
                o = !!i.filterModel.ficon,
                a = i.numberCell,
                l = parseInt(i.freezeCols),
                s = [],
                d = [],
                c = n.pqpanes.v,
                h = 0,
                u = 0;
            a.show && (h = a.outerWidth, a.resizable && (u = h - 5, s.push("<div pq-col-indx='-1' style='left:", u, "px;'", " class='pq-grid-col-resize-handle'>&nbsp;</div>")));
            for (var f = n.iGenerateView.colDef, p = 0, g = f.length; g > p; p++) {
                var v = f[p],
                    m = v.colIndx,
                    w = v.column,
                    x = w.ficon,
                    y = x || null == x && o,
                    _ = s;
                h += w.outerWidth, (w.resizable !== !1 || y) && (c && m >= l && (_ = d), u = h - 5, _.push("<div pq-col-indx='", m, "' style='left:", u, "px;'", " class='pq-grid-col-resize-handle'>&nbsp;</div>"))
            }
            d.length && n.$header_right_inner.append(d.join("")), n.$header_left_inner.append(s.join(""))
        },
        refreshHeaderSortIcons: function() {
            var t = this.that,
                e = t.options,
                r = e.bootstrap,
                n = e.ui,
                i = t.$header;
            if (i) {
                var o = t.iSort.getSorter(),
                    a = o.length,
                    l = !1,
                    s = t.options.sortModel;
                s.number && a > 1 && (l = !0);
                for (var d = 0; a > d; d++) {
                    var c = o[d],
                        h = c.dataIndx,
                        u = t.getColIndx({
                            dataIndx: h
                        }),
                        f = c.dir,
                        p = r.on ? r.header_active : n.header_active + " pq-col-sort-" + ("up" == f ? "asc" : "desc"),
                        g = r.on ? " glyphicon glyphicon-arrow-" + f : "ui-icon ui-icon-triangle-1-" + ("up" == f ? "n" : "s"),
                        v = i.find(".pq-grid-col-leaf[pq-col-indx=" + u + "]");
                    v.addClass(p), v.find(".pq-col-sort-icon").addClass(g), l && v.find(".pq-col-sort-count").html(d + 1)
                }
            }
        }
    }, new n.cClass), n.cResizeColumns = function(e) {
        this.that = e;
        var r = this;
        e.$header_o.on({
            mousedown: function(e) {
                if (!e.pq_composed) {
                    var n = t(e.target);
                    r.setDraggables(e), e.pq_composed = !0;
                    var i = t.Event("mousedown", e);
                    n.trigger(i)
                }
            },
            dblclick: function(t) {
                r.doubleClick(t)
            }
        }, ".pq-grid-col-resize-handle");
        var n = e.options,
            i = n.flex;
        i.on && i.one && e.one("ready", function() {
            r.flex()
        })
    }, n.cResizeColumns.prototype = {
        doubleClick: function(e) {
            var r = this.that,
                n = r.options,
                i = n.flex,
                o = t(e.target),
                a = parseInt(o.attr("pq-col-indx"));
            isNaN(a) || i.on && this.flex(i.all && !n.scrollModel.autoFit ? {} : {
                colIndx: [a]
            })
        },
        _initFlexTable: function(e, r) {
            if (e.length) {
                e.find(".pq-grid-cell").css("white-space", "nowrap");
                var n, i = e.css({
                        tableLayout: "auto",
                        width: ""
                    }).addClass("pq-no-wrap").removeClass("pq-wrap").children("tbody").children(".pq-row-hidden"),
                    o = i.children("td").css("width", "");
                if (r) {
                    var a, l = i.next().children("td"),
                        s = 0,
                        d = [],
                        c = [];
                    l.each(function(t, e) {
                        if (a = 1 * e.getAttribute("colspan"), a > 1 && (s += a - 1, d[s] = e.offsetWidth, n = o[s], !n || !n.offsetWidth))
                            for (var r = s - (a - 1); s > r; r++) c[r] = !0;
                        s++
                    })
                }
            }
            return [o || t(), c || [], d]
        },
        flex: function(e) {
            e = e || {};
            var r, n = this.that,
                i = n.options,
                o = n.element,
                a = i.numberCell,
                l = e.colIndx,
                s = e.dataIndx,
                d = null == e.refresh ? !0 : e.refresh,
                c = !1,
                h = 0,
                u = n.$tbl,
                f = n.$tbl_header,
                p = u && u.length ? t(u[0]).clone() : t(),
                g = n.tables && n.tables.length ? n.tables[0].$tbl : null,
                g = g ? t(g[0]).clone() : t(),
                v = f && f.length ? t(f[0]).clone() : t(),
                m = p.find(".pq-merge-cell");
            if (m.each(function(e, r) {
                    r.innerHTML = t(r).find(".pq-merge-inner")[0].innerHTML, r.style.whiteSpace = "noWrap"
                }), null != s) {
                l = [];
                for (var w = 0, x = s.length; x > w; w++) {
                    var y = n.colIndxs[s[w]];
                    null != y && l.push(y)
                }
            }
            null != l && (l.sort(function(t, e) {
                return t - e
            }), r = l.length), v.find("tr.pq-grid-header-search-row").remove();
            var _ = t("<div class='pq-grid' style='width:1px;height:1px;position:absolute;left:0px;top:0px;'>").append(v).append(g).append(p);
            _.addClass(o.attr("class")), o.parent().append(_);
            for (var b = this._initFlexTable(p, !0), I = b[0], C = b[1], q = b[2], R = 0, D = this._initFlexTable(g)[0], M = this._initFlexTable(v)[0], T = a.show ? 0 : -1, w = 0, P = n.iGenerateView.colDef, x = P.length; x > w; w++) {
                var E = P[w],
                    S = E.colIndx,
                    k = E.column;
                if (T++, l) {
                    if (!l.length) break;
                    if (l[0] !== S) continue;
                    l.splice(0, 1)
                }
                var H = I[T],
                    F = M[T],
                    $ = D[T],
                    A = C[T],
                    V = H ? H.offsetWidth : 0,
                    L = $ ? $.offsetWidth : 0,
                    z = F ? F.offsetWidth : 0;
                A ? V = 0 : R && (V = q[T] - R, R = 0);
                var N = Math.max(V, L, z) - h + 1;
                A && (R += N), k._width !== N && (c = !0, k.width = N, 1 === r && (k._resized = !0))
            }
            _.remove(), c && d && n.refresh({
                source: "flex",
                colModel: !0
            })
        },
        setDraggables: function(e) {
            var r, n, i, o = t(e.target),
                a = this;
            o.draggable({
                axis: "x",
                helper: function(e, r) {
                    var n = t(e.target),
                        i = parseInt(n.attr("pq-col-indx"));
                    return a._setDragLimits(i), a._getDragHelper(e, r), n
                },
                start: function(t, e) {
                    r = e.position.left, i = parseInt(a.$cl[0].style.left)
                },
                drag: function(t, e) {
                    n = e.position.left;
                    var o = n - r;
                    a.$cl[0].style.left = i + o + "px"
                },
                stop: function(t, e) {
                    return a.resizeStop(t, e, r)
                }
            })
        },
        _getDragHelper: function(e) {
            var r = this.that,
                n = r.options,
                i = parseInt(n.freezeCols),
                o = t(e.target),
                a = r.$grid_center,
                l = parseInt(o.attr("pq-col-indx")),
                s = a.outerHeight();
            this.$cl = t("<div class='pq-grid-drag-bar'></div>").appendTo(a), this.$clleft = t("<div class='pq-grid-drag-bar'></div>").appendTo(a), this.$cl.height(s), this.$clleft.height(s);
            var d = t("[pq-col-indx=" + l + "]", r.$header)[0],
                c = d.offsetLeft;
            r.pqpanes.v ? l >= i && (c -= r.$header[1].scrollLeft) : (c += r.$header[0].offsetLeft, c -= r.$header[0].scrollLeft), this.$clleft.css({
                left: c
            }), c += d.offsetWidth, this.$cl.css({
                left: c
            })
        },
        _setDragLimits: function(t) {
            if (!(0 > t)) {
                var e = this.that,
                    r = e.colModel,
                    n = r[t],
                    i = e.options,
                    o = e.$header_left;
                t >= i.freezeCols && e.pqpanes.v && (o = e.$header_right);
                var a = o.find("th[pq-col-indx='" + t + "']"),
                    l = a.offset().left + n._minWidth,
                    s = l + n._maxWidth - n._minWidth,
                    d = o.find("div.pq-grid-col-resize-handle[pq-col-indx=" + t + "]");
                d.draggable("instance") && d.draggable("option", "containment", [l, 0, s, 0])
            }
        },
        resizeStop: function(e, r, n) {
            var i = this.that,
                o = i.colModel,
                a = i.options,
                l = this,
                s = a.numberCell;
            l.$clleft.remove(), l.$cl.remove();
            var d, c = r.position.left,
                h = c - n,
                u = t(r.helper),
                f = parseInt(u.attr("pq-col-indx"));
            if (-1 == f) {
                d = null;
                var p = parseInt(s.width),
                    g = p + h;
                s.width = g
            } else {
                d = o[f];
                var p = parseInt(d.width),
                    g = p + h;
                d.width = g, d._resized = !0
            }
            i._trigger("columnResize", e, {
                colIndx: f,
                column: d,
                dataIndx: d ? d.dataIndx : null,
                oldWidth: p,
                newWidth: d ? d.width : s.width
            }), i.refresh()
        }
    }, n.cDragColumns = function(e) {
        var r = this;
        r.that = e, r.$drag_helper = null;
        var n = e.options.dragColumns,
            i = n.topIcon,
            o = n.bottomIcon;
        r.status = "stop", r.$arrowTop = t("<div class='pq-arrow-down ui-icon " + i + "'></div>").appendTo(e.element), r.$arrowBottom = t("<div class='pq-arrow-up ui-icon " + o + "' ></div>").appendTo(e.element), r.hideArrows(), n && n.enabled && e.$header_o.on("mousedown", ".pq-grid-col", r.onColMouseDown(r, e))
    }, n.cDragColumns.prototype = {
        onColMouseDown: function(e, r) {
            return function(n) {
                var i, o, a, l, s = t(this);
                if (!n.pq_composed) {
                    if (t(n.target).is("input,select,textarea")) return;
                    if (i = r.getHeadCell(s), o = i.col, a = o ? o.parent : null, !o || o.nodrag || o._nodrag || a && 1 == a.colSpan) return;
                    e.setDraggable(n, o, i) && (n.pq_composed = !0, l = t.Event("mousedown", n), t(n.target).trigger(l))
                }
            }
        },
        showFeedback: function(t, e) {
            var r = this.that,
                n = t[0],
                i = n.offsetParent.offsetParent,
                o = r.$grid_center[0].offsetTop,
                a = n.offsetLeft - i.offsetParent.scrollLeft + (e ? 0 : n.offsetWidth) - 8,
                l = o + n.offsetTop - 16,
                s = o + r.$header[0].offsetHeight;
            this.$arrowTop.css({
                left: a,
                top: l,
                display: ""
            }), this.$arrowBottom.css({
                left: a,
                top: s,
                display: ""
            })
        },
        showArrows: function() {
            this.$arrowTop.show(), this.$arrowBottom.show()
        },
        hideArrows: function() {
            this.$arrowTop.hide(), this.$arrowBottom.hide()
        },
        updateDragHelper: function(t) {
            var e = this.that,
                r = e.options.dragColumns,
                n = r.acceptIcon,
                i = r.rejectIcon,
                o = this.$drag_helper;
            o && (t ? (o.children("span.pq-drag-icon").addClass(n).removeClass(i), o.removeClass("ui-state-error")) : (o.children("span.pq-drag-icon").removeClass(n).addClass(i), o.addClass("ui-state-error")))
        },
        setDraggable: function(e, r, n) {
            var i = t(e.currentTarget),
                o = this,
                a = o.that;
            return i.hasClass("ui-draggable") ? void 0 : (i.draggable({
                distance: 10,
                cursorAt: {
                    top: -18,
                    left: -10
                },
                zIndex: "1000",
                appendTo: a.element,
                revert: "invalid",
                helper: o.dragHelper(o, a, r),
                start: o.onStart(o, a, r, n),
                drag: o.onDrag(o, a),
                stop: function() {
                    a.element && (o.status = "stop", a.$header.find(".pq-grid-col-resize-handle").show(), o.hideArrows())
                }
            }), !0)
        },
        onStart: function(t, e, r, n) {
            return function(i) {
                return e._trigger("columnDrag", i.originalEvent, {
                    column: r
                }) === !1 ? !1 : void t.setDroppables(n)
            }
        },
        onDrag: function(e, r) {
            return function(n, i) {
                e.status = "drag";
                var o = t(".pq-drop-hover", r.$header);
                if (o.length > 0) {
                    e.showArrows(), e.updateDragHelper(!0);
                    var a = o.width(),
                        l = n.clientX - o.offset().left + t(document).scrollLeft();
                    a / 2 > l ? (e.leftDrop = !0, e.showFeedback(o, !0)) : (e.leftDrop = !1, e.showFeedback(o, !1))
                } else {
                    e.hideArrows();
                    var s = t(".pq-drop-hover", r.$top);
                    s.length ? e.updateDragHelper(!0) : e.updateDragHelper()
                }
            }
        },
        dragHelper: function(e, r, n) {
            var i = r.options.dragColumns.rejectIcon;
            return function() {
                e.status = "helper", r.$header.find(".pq-grid-col-resize-handle").hide();
                var o = t("<div class='pq-col-drag-helper ui-widget-content ui-corner-all panel panel-default' ><span class='pq-drag-icon ui-icon " + i + " glyphicon glyphicon-remove'></span>" + n.pqtitle + "</div>");
                return e.$drag_helper = o, o[0]
            }
        },
        _columnIndexOf: function(t, e) {
            for (var r = 0, n = t.length; n > r; r++)
                if (t[r] == e) return r;
            return -1
        },
        setDroppables: function(e) {
            var r, n, i, o, a, l, s, d = this,
                c = d.that,
                h = e.col,
                u = e.ri,
                f = e.o_ci,
                p = f + h.o_colspan,
                g = c.$header_left,
                v = d.onDrop(),
                m = {
                    hoverClass: "pq-drop-hover ui-state-highlight",
                    accept: ".pq-grid-col",
                    tolerance: "pointer",
                    drop: v
                },
                w = g.find(".pq-left-col"),
                x = c.pqpanes.v || c.pqpanes.vH ? c.$header_right.find(".pq-right-col") : g.find(".pq-right-col");
            for (w = w.add(x), s = w.length; s--;) a = t(w[s]), l = a.hasClass("ui-droppable"), r = c.getHeadCell(a), o = r.col, n = r.ri, i = r.ci, o == h || o.nodrop || o._nodrop || n > u && i >= f && p > i ? l && a.droppable("destroy") : l || a.droppable(m);
        },
        onDrop: function() {
            var e = this,
                r = this.that;
            return function(n, i) {
                if (!e.dropPending) {
                    var o = 1 * i.draggable.attr("pq-col-indx"),
                        a = 1 * i.draggable.attr("pq-row-indx"),
                        l = t(this),
                        s = 1 * l.attr("pq-col-indx"),
                        d = 1 * l.attr("pq-row-indx"),
                        c = e.leftDrop,
                        h = e.moveColumn(o, s, c, a, d);
                    e.dropPending = !0, window.setTimeout(function() {
                        r.iColModel.init();
                        var t = r._trigger("columnOrder", null, {
                            dataIndx: h.dataIndx,
                            column: h,
                            oldcolIndx: o,
                            colIndx: r.getColIndx({
                                column: h
                            })
                        });
                        t !== !1 && r.refresh(), e.dropPending = !1
                    }, 0)
                }
            }
        },
        getRowIndx: function(t, e, r) {
            for (var n, i; r && (n = t[r][e], i = t[r - 1][e], n == i);) r--;
            return r
        },
        moveColumn: function(t, e, r, n, i) {
            var o = this.that,
                a = this,
                l = o.options.colModel,
                s = o.headerCells,
                d = o.depth - 1,
                n = null == n ? a.getRowIndx(s, t, d) : n,
                i = null == i ? a.getRowIndx(s, e, d) : i,
                c = s[n][t],
                h = s[i][e],
                u = n ? s[n - 1][t].colModel : l,
                f = i ? s[i - 1][e].colModel : l,
                p = a._columnIndexOf(u, c),
                g = r ? 1 : 0,
                v = u.splice(p, 1)[0],
                m = a._columnIndexOf(f, h) + 1 - g;
            return f.splice(m, 0, v), v
        }
    }, n.cHeaderSearch = function(t) {
        var e = this;
        e.that = t, e.td_cls = "pq-grid-col", e.dataHS = {}, t.on("headerKeyDown", function(t, r) {
            return e.onHeaderKeyDown(t, r)
        }).on("createHeader", function(t, r) {
            return e.onCreateHeader(t, r)
        })
    }, n.cHeaderSearch.prototype = {
        get$Ele: function(e, r) {
            var n, i = this.that,
                o = i.options.freezeCols,
                a = t(i.$tbl_header[0]),
                l = ".pq-grid-hd-search-field[name='" + r + "']",
                s = t(i.$tbl_header[2 == i.$tbl_header.length ? 1 : 0]);
            return n = e >= o ? s.find(l) : a.find(l)
        },
        _onKeyDown: function(e, r, n) {
            var i = this.that,
                o = e.keyCode,
                a = t.ui.keyCode;
            if (o !== a.TAB) return !0;
            var l, s = n.attr("name"),
                d = i.getColIndx({
                    dataIndx: s
                }),
                c = i.colModel,
                h = e.shiftKey,
                u = c[d];
            if ("between" == u.filter.condition) {
                i.scrollColumn({
                    colIndx: d
                });
                var f = this.get$Ele(d, s);
                if (f[0] == n[0] ? h || (l = f[1]) : h && (l = f[0]), l) return l.focus(), e.preventDefault(), !1
            }
            for (;;) {
                if (h ? d-- : d++, 0 > d || d >= c.length) break;
                var u = c[d],
                    p = u.filter;
                if (!u.hidden && p) {
                    i.scrollColumn({
                        colIndx: d
                    });
                    var l, s = u.dataIndx,
                        l = this.get$Ele(d, s);
                    if ("between" == p.condition && (l = t(h ? l[1] : l[0])), l) return l.focus(), e.preventDefault(), !1;
                    break
                }
            }
        },
        onHeaderKeyDown: function(e, r) {
            var n = t(e.originalEvent.target);
            return n.hasClass("pq-grid-hd-search-field") ? this._onKeyDown(e, r, n) : !0
        },
        _bindFocus: function() {
            function e(e) {
                var i = t(e.target),
                    o = i.closest(".pq-grid-hd-search-field"),
                    a = o.attr("name");
                if (r.scrollColumn({
                        dataIndx: a
                    })) {
                    var l = r.getColIndx({
                            dataIndx: a
                        }),
                        s = n.get$Ele(l, a);
                    s.focus()
                }
            }
            for (var r = this.that, n = this, i = r.$header.find(".pq-grid-header-search-row"), o = 0; o < i.length; o++) t(i[o]).on("focusin", e)
        },
        createListener: function(t) {
            var e = {},
                r = this.that;
            return e[t] = function(t, e) {
                r.filter({
                    rules: [{
                        dataIndx: e.dataIndx,
                        value: e.value,
                        value2: e.value2
                    }]
                })
            }, e
        },
        onCreateHeader: function() {
            var e = this,
                r = this.that,
                n = r.options,
                i = n.filterModel;
            if (i.header) {
                this._bindFocus();
                var o = r.colModel,
                    a = n.freezeCols,
                    l = r.$tbl_header,
                    s = t(l[0]),
                    d = t(l[1]),
                    c = "input,select";
                if (l.length > 1) {
                    s.find(c).css("visibility", "hidden");
                    for (var h = 0; a > h; h++) {
                        var u = o[h],
                            f = u.dataIndx,
                            c = "*[name='" + f + "']";
                        s.find(c).css("visibility", "visible"), d.find(c).css("visibility", "hidden")
                    }
                }
                for (var p = r.iGenerateView.colDef, h = 0, g = p.length; g > h; h++) {
                    var v = p[h],
                        m = v.colIndx,
                        u = v.column,
                        w = u.filter;
                    w && e.postRenderCell(u, m)
                }
            }
        },
        postRenderCell: function(e, r) {
            var n = e.dataIndx,
                i = e.filter,
                o = this,
                a = o.that,
                l = a.options.freezeCols,
                s = a.$tbl_header,
                d = t(s[0]),
                c = t(s[1]),
                h = {
                    button: "click",
                    select: "change",
                    checkbox: "change",
                    textbox: "timeout"
                },
                u = r >= l && s.length > 1 ? c : d,
                f = u.find(".pq-col-" + r),
                p = f.find("*[name='" + n + "']");
            if (0 != p.length) {
                var g = i.type,
                    v = i.value;
                "checkbox" == g && "triple" == i.subtype ? p.pqval({
                    val: v
                }) : "select" == g && null != v && p.val(v);
                var m = i.init,
                    w = i.listener,
                    x = i.listeners || [w ? w : h[g]];
                m && a.callFn(m, {
                    dataIndx: n,
                    column: e,
                    $cell: f,
                    $editor: p
                });
                for (var y = 0; y < x.length; y++) {
                    var _ = x[y],
                        b = typeof _,
                        I = {};
                    "string" == b ? _ = o.createListener(_) : "function" == b && (I[h[g]] = _, _ = I);
                    for (var C in _) o.bindListener(p, C, _[C], e)
                }
            }
        },
        fakeEvent: function(t, e) {
            if ("timeout" == e) {
                var r, n = this.that.options.filterModel.timeout;
                t.bind("keyup change", function() {
                    clearTimeout(r), r = setTimeout(function() {
                        t.triggerHandler("timeout")
                    }, n)
                })
            }
        },
        bindListener: function(e, r, n, i) {
            var o = this.that,
                a = i.filter.value,
                l = i.filter.value2;
            this.fakeEvent(e, r), e.on(r, function(r) {
                var s, d, c = i.filter;
                return "checkbox" == c.type ? s = "triple" == c.subtype ? e.pqval({
                    incr: !0
                }) : !!e.is(":checked") : "between" == c.condition ? (s = t(e[0]).val(), d = t(e[1]).val()) : s = e.val(), s = "" === s ? void 0 : s, d = "" === d ? void 0 : d, a !== s || l !== d ? (a = s, l = d, n = pq.getFn(n), n.call(o, r, {
                    column: i,
                    dataIndx: i.dataIndx,
                    value: s,
                    value2: d
                })) : void 0
            })
        },
        betweenTmpl: function(t, e) {
            var r = ["<div class='pq-from-div'>", t, "</div>", "<span class='pq-from-to-center'>-</span>", "<div class='pq-to-div'>", e, "</div>"].join("");
            return r
        },
        createDOM: function(t) {
            var e = this.that,
                r = this,
                n = e.options,
                i = (this.dataHS, n.numberCell);
            t.push("<tr class='pq-grid-header-search-row'>"), i.show && t.push(["<td pq-col-indx='-1' class='pq-grid-number-col' rowspan='1'>", "<div class='pq-td-div'>&nbsp;</div></td>"].join(""));
            for (var o = e.iGenerateView.colDef, a = 0, l = o.length; l > a; a++) {
                var s = o[a],
                    d = s.colIndx,
                    c = s.column;
                t.push(r.renderCell(c, d))
            }
            t.push("</tr>")
        },
        renderCell: function(t, e) {
            var r, i = [this.td_cls],
                o = this,
                a = o.that,
                l = t.filter,
                s = a.options.filterModel,
                d = t.cls,
                c = " ui-corner-all",
                h = t.halign || t.align;
            if (h && i.push("pq-align-" + h), d && i.push(d), l) {
                var u, f = t.dataIndx,
                    p = l.type,
                    g = l.value,
                    v = l.condition,
                    m = "pq-grid-hd-search-field " + (l.cls || ""),
                    w = l.style || "",
                    x = l.attr || "",
                    y = "";
                if ("between" == v && (u = l.value2, u = null != u ? u : ""), "textbox" === p) g = g ? g : "", m = m + " pq-search-txt" + c, y = "between" == v ? this.betweenTmpl(this._input(f, g, m + " pq-from", w, x, s), this._input(f, u, m + " pq-to", w, x, s)) : this._input(f, g, m, w, x, s);
                else if ("textarea" === p) g = g ? g : "", m = m + " pq-search-txt" + c, y = "between" == v ? this.betweenTmpl(this._textarea(f, g, m + " pq-from", w, x), this._textarea(f, u, m + " pq-to", w, x)) : this._textarea(f, g, m, w, x);
                else if ("select" === p)
                    if (l.cache) y = l.cache;
                    else {
                        var _ = l.options,
                            b = typeof _;
                        "string" != b && "function" != b || (_ = a.callFn(_, {
                            column: t,
                            value: g,
                            dataIndx: f,
                            cls: m,
                            style: w,
                            attr: x
                        })), m += c;
                        var I = ["name='", f, "' class='", m, "' style='", w, "' ", x].join("");
                        y = n.select({
                            options: _,
                            attr: I,
                            prepend: l.prepend,
                            valueIndx: l.valueIndx,
                            labelIndx: l.labelIndx,
                            groupIndx: l.groupIndx
                        }), l.cache = y
                    }
                else if ("checkbox" == p) {
                    var C = null == g || 0 == g ? "" : "checked=checked";
                    y = ["<input ", C, " name='", f, "' type=checkbox class='" + m + "' style='" + w + "' " + x + "/>"].join("")
                } else "string" == typeof p ? y = p : "function" == typeof p && (y = p.call(a, {
                    width: t.outerWidth,
                    value: g,
                    value2: u,
                    column: t,
                    dataIndx: f,
                    cls: m,
                    attr: x,
                    style: w
                }));
                y && i.push("pq-col-" + e), r = ["<td class='", i.join(" "), "'><div class='pq-td-div' >", "", y, "</div></td>"].join("")
            } else r = ["<td class='", i.join(" "), "'><div class='pq-td-div' >", "&nbsp;", "</div></td>"].join("");
            return r
        },
        _input: function(t, e, r, n, i, o) {
            return ["<input ", , , ' value="', e, "\" name='", t, "' type=text style='", n, "' class='", r, "' ", i, " />"].join("")
        },
        _textarea: function(t, e, r, n, i) {
            return ["<textarea name='", t, "' style='" + n + "' class='" + r + "' " + i + " >", e, "</textarea>"].join("")
        }
    }
}(jQuery),
function(t) {
    "use strict";

    function e(e, r) {
        this.that = e;
        var n = this,
            i = e.options;
        this.type = "detail", this.refreshComplete = !0, this.detachView = !1, e.on("cellClick", function(t, e) {
            return n.toggle(t, e)
        }).on("cellKeyDown", function(e, r) {
            return e.keyCode == t.ui.keyCode.ENTER ? n.toggle(e, r) : void 0
        }).on("refresh", function(t, e) {
            return n.aftertable()
        }).on("beforeTableView", function(t, e) {
            return n.beforeTableView(t, e)
        }).on("tableWidthChange", function(t, e) {
            return n.tableWidthChange(t, e)
        }), r._render = function(t) {
            var e, r = i.detailModel,
                n = t.cellData,
                o = t.rowData;
            if (!o.pq_gsummary && !o.pq_gtitle) return e = n && n.show ? r.expandIcon : r.collapseIcon, "<div class='ui-icon " + e + "'></div>"
        }
    }
    t.paramquery.cHierarchy = e;
    var r = e.prototype;
    r.tableWidthChange = function() {
        if (this.refreshComplete) {
            this.refreshComplete = !1;
            for (var e = this.that, r = e.$tbl.children("tbody").children("tr.pq-detail-child").children("td.pq-detail-child"), n = 0, i = r.length; i > n; n++)
                for (var o = r[n], a = t(o), l = a.find(".pq-grid"), s = 0, d = l.length; d > s; s++) {
                    var c = t(l[s]);
                    c.is(":visible") && c.pqGrid("onWindowResize")
                }
            this.refreshComplete = !0
        }
    }, r.aftertable = function(e) {
        var r = this.that,
            n = r.options.detailModel.init,
            i = r.pdata;
        if (this.refreshComplete) {
            this.refreshComplete = !1, e = e ? e : r.$tbl.children("tbody").children("tr.pq-detail-child");
            for (var o = 0, a = e.length; a > o; o++) {
                var l = e[o],
                    s = t(l),
                    d = s.attr("pq-row-indx"),
                    c = i[d],
                    h = !1,
                    u = c.pq_detail.child;
                u || "function" == typeof n && (h = !0, u = n.call(r, {
                    rowData: c
                }), c.pq_detail.child = u, c.pq_detail.height = 25);
                var f = s.children("td.pq-detail-child");
                f.append(u);
                for (var p = f.find(".pq-grid"), g = 0, v = p.length; v > g; g++) {
                    var m = t(p[g]);
                    h ? m.hasClass("pq-pending-refresh") && m.is(":visible") && (m.removeClass("pq-pending-refresh"), m.pqGrid("refresh")) : m.is(":visible") && m.pqGrid("onWindowResize")
                }
            }
            this.refreshComplete = !0, this.detachView = !1
        }
    }, r.beforeTableView = function(t, e) {
        this.detachView || (this.detachInitView(), this.detachView = !0)
    }, r.detachInitView = function(e) {
        var r = this.that,
            n = r.$tbl;
        if (n && n.length) {
            e = e ? e : n.children("tbody").children("tr.pq-detail-child");
            for (var i = 0; i < e.length; i++) {
                var o = e[i],
                    a = t(o),
                    l = a.children("td.pq-detail-child").children();
                l.detach()
            }
        }
    }, r.toggle = function(t, e) {
        var r = this.that,
            n = e.column,
            i = e.rowData,
            o = e.rowIndx,
            a = this.type;
        if (!i.pq_gtitle && !i.pq_gsummary && n && n.type === a) {
            var l = "pq_detail",
                s = {
                    rowIndx: o,
                    focus: !0
                };
            null == i[l] ? r.rowExpand(s) : i[l].show === !1 ? r.rowExpand(s) : this.rowCollapse(s)
        }
    }, r.rowExpand = function(t) {
        this.normalize(t);
        var e = this.that,
            r = e.options,
            n = t.rowData,
            i = t.rowIndx,
            o = t.rowIndxPage,
            a = r.detailModel,
            l = "pq_detail";
        if (null != n) {
            if (e._trigger("beforeRowExpand", null, t) === !1) return !1;
            null == n[l] ? n[l] = {
                show: !0
            } : n[l].show === !1 && (n[l].show = !0), a.cache || this.rowInvalidate(t), e.refreshRow({
                rowIndx: i
            });
            var s = [];
            e.iGenerateView.generateDetailRow(n, o, s);
            var d = e.getRow({
                rowIndxPage: o
            });
            d.after(s.join("")), this.aftertable(d.next()), t.focus && e.getCell({
                rowIndx: i,
                dataIndx: l
            }).attr("tabindex", "0").focus(), t.scrollRow && this.scrollRow({
                rowIndx: i
            })
        }
    }, r.rowInvalidate = function(t) {
        var e = this.that,
            r = e.getRowData(t),
            n = "pq_detail",
            i = r[n],
            o = i ? i.child : null;
        o && (o.remove(), r[n].child = null, r[n].height = 0)
    }, r.normalize = function(t) {
        var e = this.that,
            r = t.rowIndx,
            n = t.rowIndxPage,
            i = e.riOffset;
        t.rowIndx = null == r ? n + i : r, t.rowIndxPage = null == n ? r - i : n, t.rowData = e.getRowData(t)
    }, r.rowCollapse = function(t) {
        this.normalize(t);
        var e = this.that,
            r = e.options,
            n = t.rowData,
            i = t.rowIndx,
            o = t.rowIndxPage,
            a = r.detailModel,
            l = "pq_detail";
        if (null != n && null != n[l] && n[l].show === !0) {
            if (a.cache || this.rowInvalidate(t), n[l].show = !1, r.virtualY) e.refresh();
            else {
                var s = e.getRow({
                    rowIndxPage: o
                }).next("tr.pq-detail-child");
                s.length && (this.detachInitView(s), s.remove(), e.refreshRow({
                    rowIndx: i
                })), t.focus && e.getCell({
                    rowIndx: i,
                    dataIndx: l
                }).attr("tabindex", "0").focus()
            }
            if (t.scrollRow) {
                var i = t.rowIndx;
                this.scrollRow({
                    rowIndx: i
                })
            }
        }
    }
}(jQuery),
function(t) {
    "use strict";

    function e(t, e) {
        if (window.getComputedStyle) return getComputedStyle(t)[e];
        var r = t.currentStyle[e];
        return "auto" == r ? 0 : r
    }
    var r = function(t) {
        var e = this;
        e.vrows = [], e.that = t, t.on("dataReadyDone", function() {
            e.calcVisibleV(), e.addRowIndx()
        }).on("CMInit", function() {
            e.calcVisibleH()
        }).on("refresh", function() {
            e.summaryTable()
        }).on("resizeTable", function() {
            e.ignoreTResize || e.softRefresh()
        })
    };
    t.paramquery.cRefresh = r, r.prototype = {
        _computeOuterWidths: function() {
            for (var t = this.that, e = t.options, r = 0, n = e.numberCell, i = t.colModel, o = i.length, a = 0; o > a; a++) {
                var l = i[a];
                l.outerWidth = l._width + r
            }
            n.show && (n.outerWidth = n.width)
        },
        _refreshFrozenLine: function() {
            var e = this.that,
                r = e.options,
                n = r.numberCell,
                i = e.$cont_o,
                o = r.freezeBorders,
                a = r.freezeCols,
                l = r.freezeRows;
            if (this.$freezeLine && this.$freezeLine.remove(), this.$freezeLineH && this.$freezeLineH.remove(), o) {
                if (a) {
                    var s = e.calcWidthCols(-1, a);
                    isNaN(s) || 0 === s || s > 0 && n.show && s === n.width || (this.$freezeLine = t(["<div class='pqg-vert-frozen-line' ", " style = 'left:", s - 1, "px;' >", "</div>"].join("")).appendTo(i))
                }
                if (l) {
                    var d = e.$tbl;
                    if (d) {
                        var c = d.children("tbody").children(".pq-last-frozen-row")[0];
                        if (c) {
                            var h = c.offsetTop + c.offsetHeight - 1;
                            this.$freezeLineH = t("<div class='pqg-horiz-frozen-line' style='top:" + (h - 1) + "px;' ></div>").appendTo(i)
                        }
                    }
                }
            }
        },
        _setScrollVLength: function(t) {
            t = t || {};
            var e = this.that,
                r = e.options;
            if ("flex" !== r.height || r.maxHeight) {
                var n = this.getSBHeight(),
                    i = this.contHt - n + this.headerHt - 2;
                e.vscroll.widget().css("bottom", n), e.vscroll.option("length", i)
            }
        },
        addRowIndx: function() {
            for (var t, e = this.that, r = e.get_p_data(), n = r.length; n--;) t = r[n], t && (t.pq_ri = n)
        },
        autoFit: function() {
            var t = this.that,
                e = t.colModel,
                r = e.length,
                n = t.calcWidthCols(-1, r, !0),
                i = this.contWd - this.getSBWidth();
            if (n !== i) {
                for (var o, a = n - i, l = [], s = 0; r > s; s++) {
                    var d = e[s],
                        c = d._percent,
                        h = (d.resizable !== !1, d._resized),
                        u = d.hidden;
                    if (!u && !c && !h) {
                        var f;
                        0 > a ? (f = d._maxWidth - d._width, f && l.push({
                            availWd: -1 * f,
                            colIndx: s
                        })) : (f = d._width - d._minWidth, f && l.push({
                            availWd: f,
                            colIndx: s
                        }))
                    }
                    h && (o = d, delete d._resized)
                }
                l.sort(function(t, e) {
                    return t.availWd > e.availWd ? 1 : t.availWd < e.availWd ? -1 : 0
                });
                for (var s = 0, p = l.length; p > s; s++) {
                    var g, v = l[s],
                        f = v.availWd,
                        m = v.colIndx,
                        w = Math.round(a / (p - s)),
                        d = e[m],
                        x = d._width;
                    Math.abs(f) > Math.abs(w) ? (g = x - w, a -= w) : (g = x - f, a -= f), d.width = d._width = g
                }
                if (0 != a && o) {
                    var g = o._width - a;
                    g > o._maxWidth ? g = o._maxWidth : g < o._minWidth && (g = o._minWidth), o.width = o._width = g
                }
            }
        },
        autoLastColumn: function() {
            var t, e = this.that,
                r = e.options,
                n = "flex" === r.width,
                i = 0,
                o = r.scrollModel;
            if (!n && "auto" === o.lastColumn && r.virtualX && (t = this.contWd - this.getSBWidth(), !isNaN(t))) {
                var a = r.freezeCols,
                    l = e.colModel,
                    s = l.length,
                    d = e.calcWidthCols(-1, a, !0),
                    c = t - d,
                    h = !1,
                    u = e.getLastVisibleCI();
                if (null != u) {
                    var f = l[u];
                    if (!f._percent) {
                        for (var p, g = f._width, v = f._minWidth, m = f._maxWidth, w = s - 1; w >= a; w--) {
                            var x = l[w];
                            if (!x.hidden) {
                                var y = x._width + i;
                                if (c -= y, 0 > c) {
                                    h = !0, p = g + c >= v ? g + c : g + y + c;
                                    break
                                }
                            }
                        }
                        h || (p = g + c), p > m ? p = m : v > p && (p = v), f.width = f._width = p, f.outerWidth = f._width + i
                    }
                }
            }
        },
        numericVal: function(t, e) {
            var r;
            return r = (t + "").indexOf("%") > -1 ? parseInt(t) * e / 100 : parseInt(t), Math.round(r)
        },
        refreshColumnWidths: function(t) {
            t = t || {};
            var e = this.that,
                r = e.options,
                n = r.numberCell,
                i = "flex" === r.width,
                o = 0,
                a = e.colModel,
                l = r.scrollModel,
                s = l.autoFit,
                d = this.contWd,
                c = a.length,
                h = this.getSBWidth(),
                u = r._minColWidth,
                f = r._maxColWidth,
                p = 0;
            n.show && (n.width < n.minWidth && (n.width = n.minWidth), p = n.outerWidth = n.width);
            var g = i ? null : d - h - p,
                u = Math.floor(this.numericVal(u, g)),
                f = Math.ceil(this.numericVal(f, g)),
                v = 0;
            if (!i && 5 > g || isNaN(g)) {
                if (r.debug) throw "availWidth N/A"
            } else {
                delete e.percentColumn;
                for (var m = 0; c > m; m++) {
                    var w = a[m],
                        x = w.hidden;
                    if (!x) {
                        var y = w.width,
                            _ = (y + "").indexOf("%") > -1 ? !0 : null,
                            b = w.minWidth,
                            I = w.maxWidth,
                            b = b ? this.numericVal(b, g) : u,
                            I = I ? this.numericVal(I, g) : f;
                        if (b > I && (I = b), void 0 != y) {
                            var C, q = 0;
                            !i && _ ? (e.percentColumn = !0, w.resizable = !1, w._percent = !0, C = this.numericVal(y, g) - o, q = Math.floor(C), v += C - q, v >= 1 && (q += 1, v -= 1)) : y && (q = 1 * y), b > q ? q = b : !i && q > I && (q = I), w._width = q
                        } else w._width = b;
                        _ || (w.width = w._width), w._minWidth = b, w._maxWidth = i ? 1e3 : I
                    }
                }
                i === !1 && t.refreshWidth !== !1 && (s && this.autoFit(), this.autoLastColumn()), this._computeOuterWidths()
            }
        },
        estRowsInViewPort: function() {
            var t = Math.ceil(this.contHt / this.rowHt);
            return this.that.pageSize = t, t
        },
        setScrollVNumEles: function(e) {
            e = e || {};
            var r = this.that,
                n = r.vscroll,
                i = r.options;
            if (!i.maxHeight && "flex" === i.height) return n.option("num_eles", 0), 0;
            var o, a, l = !!r.iHierarchy,
                s = parseInt(n.option("num_eles")),
                d = parseInt(n.option("cur_pos")),
                c = this.getEContHt(),
                h = r.pdata,
                u = h ? r.totalVisibleRows : 0,
                f = 0;
            if (r.$tbl && r.$tbl.length > 0 && (o = r.$tbl[r.$tbl.length - 1], f = o.scrollHeight, a = t(o)), f > 0) {
                for (var p = a.children().children("tr"), g = 0, v = 0, m = 1; m < p.length; m++) {
                    var w = p[m];
                    if (g += w.offsetHeight, g >= c) {
                        l && t(w).hasClass("pq-detail-child") && (v = v > 1 ? v - 1 : 1);
                        break
                    }
                    l ? t(w).hasClass("pq-detail-child") === !1 && v++ : v++
                }
                s = u - v + 1
            } else s = d + 1;
            return s > u && (s = u), g > c && l && s++, n.option("num_eles", s), s
        },
        setHeaderHeight: function() {
            var e, r, n, i = this.that,
                o = i.$header,
                a = "scrollHeight",
                l = ".pq-grid-header-search-row";
            if (o && o.length) {
                if (o.length > 1) {
                    if (e = o[0][a], r = o[1][a], n = Math.max(e, r), e !== r) {
                        var s = t(o[0]).find(l),
                            d = t(o[1]).find(l);
                        s.length && (s.css("height", ""), d.css("height", ""), e = o[0][a], r = o[1][a], n = Math.max(e, r), n > e ? s.height(d[0][a]) : d.height(s[0][a]))
                    }
                } else e = o[0][a], n = e;
                i.$header_o.height(n - 3), this.headerHt = n - 1
            } else i.$header_o.height(0), this.headerHt = 0
        },
        initContHeight: function() {
            var t = this.that,
                e = t.options,
                r = "flex" == e.height;
            r && !e.maxHeight || (this.contHt = this.height - (e.showHeader ? this.rowHt : 0) - (e.showTop ? t.$top[0].offsetHeight : 0) - (e.showBottom ? t.$bottom[0].offsetHeight : 0))
        },
        initContWidth: function() {
            var t = this.that;
            t.options;
            this.contWd = this.width, t._trigger("contWd")
        },
        setContHeight: function(t) {
            t = t || {};
            var r = this.that,
                n = r.$top,
                i = r.options,
                o = this.height - r.$header_o[0].offsetHeight - (i.showTop ? n[0].offsetHeight + parseInt(e(n[0], "marginTop")) : 0) - r.$bottom[0].offsetHeight + 1;
            o = o >= 0 ? o : "", r.$cont.height(o), this.contHt = o
        },
        setContAndGridHeightForFlex: function() {
            var t = this.that,
                e = t.hscroll.widget();
            if (this.vscroll) e.css("position", "");
            else {
                e.css("position", "relative");
                var r = t.$cont,
                    n = t.options.cls,
                    i = r.children("." + n.cont_inner_b);
                r.height(""), i.length || (i = r.children("." + n.cont_inner)), i.height(""), t.element.height(""), t.$grid_center.height("")
            }
        },
        setContAndGridWidthForFlex: function() {
            var t = this.that,
                e = t.options,
                r = e.maxWidth,
                n = this.maxWidthPixel,
                i = t.calcWidthCols(-1, t.colModel.length),
                o = t.element,
                a = this.getSBWidth(),
                l = i + a;
            r && l >= n && (l = n), this.contWd = l, t._trigger("contWd"), o.width(l + "px")
        },
        getTotalVisibleRows: function(t, e, r) {
            var n = this.that,
                i = this.vrows,
                o = this.estRowsInViewPort(),
                a = 0,
                l = r ? r.length : 0,
                s = e,
                d = 0,
                c = 0,
                h = null,
                u = !!n.iHierarchy,
                f = n.options,
                p = f.detailModel.offset,
                g = 0,
                v = this.rowHt,
                m = u ? this.contHt : void 0;
            if (null == r || 0 == l) return {
                initV: null,
                finalV: null,
                tvRows: a,
                lastFrozenRow: null
            };
            for (var w = 0, x = l > e ? e : l; x > w; w++) {
                var y = r[w],
                    _ = y.pq_hidden;
                if (!_ && (h = w, a++, u)) {
                    var b = y.pq_detail;
                    if (b && b.show) {
                        var I = b.height || 0;
                        I > p && (I = p), g += I + v
                    } else g += v
                }
            }
            if (e > l) return {
                initV: h,
                finalV: h,
                tvRows: a,
                lastFrozenRow: h
            };
            o -= a, s = d = i[t], c = 0;
            for (var w = s, x = l; x > w; w++) {
                var y = r[w],
                    _ = y.pq_hidden;
                if (_) d++;
                else {
                    if (c === o) break;
                    d++, c++
                }
                if (u && !_) {
                    var b = y.pq_detail;
                    if (b && b.show) {
                        var I = b.height || 0;
                        I > p && (I = p), g += I + v
                    } else g += v;
                    if (g > m) break
                }
            }
            return a += i.length, s = s >= l ? l - 1 : s, d = d >= l ? l - 1 : d, d = s > d ? s : d, {
                initV: s,
                finalV: d,
                tvRows: a,
                lastFrozenRow: h
            }
        },
        setInitH: function(t, e) {
            var r = this.that;
            r.initH = t, r.finalH = e
        },
        setInitV: function(t, e) {
            var r, n = this.that;
            null != t && null != e || (r = this.vrows, r && r.length && (t = e = r[r.length - 1])), n.initV = t, n.finalV = e
        },
        calcVisibleV: function() {
            var t, e = this.that,
                r = e.options,
                n = r.freezeRows,
                i = e.pdata || [],
                o = i.length,
                a = [],
                l = 0;
            for (n = n > o ? o : n, t = 0; n > t; t++) i[t].pq_hidden || (a[l++] = t);
            for (this.vfrows = a, l = 0, a = [], t = n; o > t; t++) i[t].pq_hidden || (a[l++] = t);
            this.vrows = a
        },
        calcVisibleH: function() {
            for (var t, e = this.that, r = e.options, n = r.freezeCols, i = [], o = 0, a = e.colModel, t = n, l = a.length; l > t; t++) a[t].hidden || (i[o++] = t);
            this.vcols = i
        },
        calcInitFinal: function() {
            var t, e, r = this.that,
                n = r.options,
                i = n.virtualY,
                o = n.freezeRows,
                a = "flex" === n.height,
                l = r.pdata;
            if (null == l || 0 === l.length) {
                var s = this.getTotalVisibleRows(d, o, l);
                r.totalVisibleRows = s.tvRows, t = s.initV, e = s.finalV, r.lastFrozenRow = s.lastFrozenRow
            } else if (i) {
                var d = parseInt(r.vscroll.option("cur_pos"));
                if (isNaN(d) || 0 > d) throw "cur_pos NA";
                r.scrollCurPos = d;
                var s = this.getTotalVisibleRows(d, o, l);
                r.totalVisibleRows = s.tvRows, t = s.initV, r.lastFrozenRow = s.lastFrozenRow, e = a && !n.maxHeight ? l.length - 1 : s.finalV
            } else {
                var s = this.getTotalVisibleRows(0, o, l);
                r.lastFrozenRow = s.lastFrozenRow, r.totalVisibleRows = s.tvRows, t = 0, e = l.length - 1
            }
            this.setInitV(t, e)
        },
        calcInitFinalH: function() {
            var t, e, r = this.that,
                n = r.options,
                i = n.virtualX,
                o = r.colModel,
                a = o.length;
            if (i) {
                var t, l = parseInt(r.hscroll.option("cur_pos")),
                    s = parseInt(n.freezeCols),
                    d = "flex" === n.width,
                    c = this.vcols;
                if (c || (this.calcVisibleH(), c = this.vcols), l >= c.length && (l = c.length - 1), t = c[l], t > a - 1 && (t = a - 1), (0 > t || null == t) && (t = 0), d && !n.maxWidth) e = a - 1;
                else {
                    for (var h = r.calcWidthCols(-1, s), u = this.getEContWd(), f = t; a > f; f++) {
                        var p = o[f];
                        if (!p.hidden) {
                            var g = p.outerWidth;
                            if (!g && n.debug) throw "outerwidth N/A";
                            if (h += g, h > u) break
                        }
                    }
                    e = f, e > a - 1 && (e = a - 1), s - 1 > e && (e = s - 1)
                }
            } else t = 0, e = a - 1;
            this.setInitH(t, e)
        },
        _calcOffset: function(t) {
            var e = /(-|\+)([0-9]+)/,
                r = e.exec(t);
            return r && 3 === r.length ? parseInt(r[1] + r[2]) : 0
        },
        setMax: function(t) {
            var e = this.that,
                r = e.element,
                n = e.options,
                i = n[t];
            i ? (i == parseInt(i) && (i += "px"), r.css(t, i)) : r.css(t, "")
        },
        refreshGridWidthAndHeight: function() {
            var e, r, n = this.that,
                i = n.options,
                o = (i.width + "").indexOf("%") > -1,
                a = (i.height + "").indexOf("%") > -1,
                l = (i.maxHeight + "").indexOf("%") > -1,
                s = "flex" == i.height,
                d = l && s,
                c = (i.maxWidth + "").indexOf("%") > -1,
                h = "flex" == i.width,
                u = c && h,
                f = n.element;
            if (o || a || d || u) {
                var p = f.parent();
                if (!p.length) return;
                var g, v;
                p[0] == document.body || "fixed" == f.css("position") ? (g = t(window).width(), v = window.innerHeight ? window.innerHeight : t(window).height()) : (g = p.width(), v = p.height());
                var m = this._calcOffset,
                    w = o ? m(i.width) : 0,
                    x = a ? m(i.height) : 0;
                u ? e = parseInt(i.maxWidth) * g / 100 : o && (e = parseInt(i.width) * g / 100 + w), d ? r = parseInt(i.maxHeight) * v / 100 : a && (r = parseInt(i.height) * v / 100 + x)
            }
            e || (h && i.maxWidth ? c || (e = i.maxWidth) : o || (e = i.width)), i.maxWidth && (this.maxWidthPixel = e), r || (s && i.maxHeight ? l || (r = i.maxHeight) : a || (r = i.height)), parseFloat(e) == e ? (e = e < i.minWidth ? i.minWidth : e, f.css("width", e)) : "auto" === e && f.width(e), parseFloat(r) == r && (r = r < i.minHeight ? i.minHeight : r, f.css("height", r)), this.width = Math.round(f.width()), this.height = Math.round(f.height())
        },
        decidePanes: function() {
            var t = this.that,
                e = t.pqpanes = {
                    v: !1,
                    h: !1
                },
                r = t.options,
                n = r.virtualX,
                i = r.virtualY,
                o = "flex" == r.height && !r.maxHeight,
                a = "flex" == r.width && !r.maxWidth,
                l = r.numberCell,
                s = r.freezeRows,
                d = r.freezeCols;
            !s || o || !d && !l.show || a ? s && !o ? i || (e.h = !0) : !d && !l.show || a || n || (e.v = !0) : (i || (e.h = !0), n || (e.v = !0))
        },
        _storeColumnWidths: function(t) {
            for (var e = this.that, r = e.options, n = e.colModel, i = r.virtualX, o = r.freezeCols, a = e.initH, l = t ? n.length - 1 : e.finalH, s = [], d = 0; l >= d; d++) !t && i && a > d && d >= o && (d = a), s[d] = {
                outerWidth: n[d].outerWidth
            };
            return s
        },
        _isColumnWidthChanged: function(t) {
            for (var e = this.that, r = e.colModel, n = e.iGenerateView.colDef, i = 0, o = n.length; o > i; i++) {
                var a = n[i],
                    l = a.colIndx;
                if (r[l].outerWidth !== t[l].outerWidth) return !0
            }
            return !1
        },
        softRefresh: function() {
            var t = this.that,
                e = t.options;
            this.refreshScrollbars(), t.iGenerateView.setPanes(), t._saveDims(), t.iMouseSelection.syncScrollBarVert(), "flex" == e.height && this.setContAndGridHeightForFlex(), "flex" == e.width && this.setContAndGridWidthForFlex(), this._refreshFrozenLine()
        },
        refreshScrollbars: function(t) {
            t = t || {};
            var e, r, n, i, o = this,
                a = o.that,
                l = a.options,
                s = a.iGenerateView,
                d = "flex" === l.height,
                c = "flex" === l.width;
            (d || o.contHt) && (c || o.contWd) && null !== a.totalVisibleRows && (r = o.setScrollVNumEles(t), i = r > 1, d && !l.maxHeight || i === o.vscroll || (o.vscroll = i, l.scrollModel.autoFit || l.virtualX || c ? (e = o._storeColumnWidths(), o.refreshColumnWidths(), (o._isColumnWidthChanged(e) || c) && (o.ignoreTResize = !0, o._refreshTableWidths(e, {
                table: !0,
                header: !0
            }), delete o.ignoreTResize, o.setHeaderHeight(), o.setContHeight(), s.setPanes(), r = o.setScrollVNumEles(!0), i = r > 1, o.vscroll = i), e = null) : s.setPanes()), r = o.setScrollHNumEles(), n = r > 1, o.hscroll != n && (o.hscroll = n, s.setPanes()), o._setScrollHLength(), o._setScrollVLength(t), o._setScrollHVLength())
        },
        _setScrollHVLength: function() {
            var t = this.that;
            this.vscroll && this.hscroll || t.$hvscroll.css("visibility", "hidden")
        },
        _setScrollHLength: function() {
            var t = this.that,
                e = t.hscroll.widget(),
                r = t.$hvscroll,
                n = t.options;
            if (!n.scrollModel.horizontal) return e.css("visibility", "hidden"), void r.css("visibility", "hidden");
            e.css("visibility", ""), r.css("visibility", "");
            var i = this.contWd,
                o = this.getSBWidth();
            e.css("right", 0 === o ? 0 : ""), t.hscroll.option("length", i - o)
        },
        estVscroll: function() {
            var t = this.that,
                e = !0;
            null == t.totalVisibleRows || null == this.contHt ? e = !1 : t.totalVisibleRows * this.rowHt < this.contHt && (e = !1), this.vscroll = e
        },
        getSBWidth: function() {
            return null == this.vscroll && this.estVscroll(), this.vscroll ? 17 : 0
        },
        estHscroll: function() {
            var t = this.that;
            if (null == this.contWd) throw "failed";
            var e = !1,
                r = this.calcColsOutsideCont(t.colModel) + 1;
            r > 1 && (e = !0), this.hscroll = e
        },
        getSBHeight: function() {
            return null == this.hscroll && this.estHscroll(), this.hscroll ? 17 : 0
        },
        getEContHt: function() {
            if (null == this.contHt) throw "contHt N/A";
            return this.contHt - this.getSBHeight()
        },
        getEContWd: function() {
            if (null == this.contWd) throw "contWd N/A";
            return this.contWd - this.getSBWidth()
        },
        calcColsOutsideCont: function(t) {
            var e, r = this.that,
                n = r.options,
                i = n.numberCell,
                o = t.length,
                a = n.freezeCols,
                l = this.contWd - this.getSBWidth(),
                s = 0;
            i.show && (s += i.outerWidth);
            for (var d = 0; o > d; d++) e = t[d], e.hidden || (s += e.outerWidth);
            var c = 0,
                h = 0,
                u = Math.round(s);
            for (u > l && h++, d = a; o > d; d++)
                if (e = t[d], !e.hidden) {
                    if (c += e.outerWidth, u = s - c, !(u > l)) break;
                    h++
                }
            return h
        },
        setScrollHNumEles: function() {
            var t = this.that,
                e = t.options,
                r = t.colModel,
                n = e.scrollModel,
                i = t.hscroll,
                o = 1 * i.option("cur_pos"),
                a = 0;
            return ("flex" !== e.width || e.maxWidth) && (a = "fullScroll" === n.lastColumn ? r.length - e.freezeCols - t._calcNumHiddenUnFrozens() : this.calcColsOutsideCont(r) + 1), o && o >= a && (a = o + 1), i.option("num_eles", a), a
        },
        init: function() {
            var t = this.that,
                e = t.options;
            this.hscroll = this.vscroll = this.contHt = this.contWd = null, t.initH = t.initV = t.finalH = t.finalV = null, t.totalVisibleRows = t.lastFrozenRow = null, this.rowHt = e.rowHeight, this.headerHt = 0, this.height = null
        },
        refresh: function(t) {
            t = t || {};
            var e, r = this,
                n = r.that,
                i = t.header,
                o = t.table,
                a = t.pager,
                l = n.iGenerateView,
                s = n.element;
            if (t.colModel && n.refreshCM(), !s[0].offsetWidth) return void s.addClass("pq-pending-refresh");
            if (t.toolbar && n.refreshToolbar(), n.iMouseSelection.resetMargins(), r.init(), e = n.options, r.decidePanes(), e.collapsible._collapsed = !1, r.setMax("maxHeight"), r.setMax("maxWidth"), r.refreshGridWidthAndHeight(), r.initContHeight(), r.initContWidth(), r.calcInitFinal(), i === !1 || o === !1) var d = r._storeColumnWidths(!0);
            t.skipColWidths || r.refreshColumnWidths(), r.autoLastColumn(), r.calcInitFinalH(), l.createColDefs(), i !== !1 ? n._createHeader() : r._isColumnWidthChanged(d) && r._refreshTableWidths(d, {
                header: !0
            }), n._refreshHeaderSortIcons(), a !== !1 && n._refreshPager(), r.setHeaderHeight(), r.setContHeight(), o !== !1 ? l.generateView({
                source: t.source
            }) : (r._refreshTableWidths(d, {
                table: !0
            }), l.setPanes()), n._saveDims(), l.scrollView(), r.refreshScrollbars(), "flex" == e.height && r.setContAndGridHeightForFlex(), "flex" == e.width && r.setContAndGridWidthForFlex(), r._refreshFrozenLine(), n._createCollapse(), e.dataModel.postDataOnce = void 0
        },
        summaryTable: function() {
            var e, r = this,
                n = r.$summary,
                i = r.that,
                o = i.options.summaryData;
            o ? (n || (n = r.$summary = t("<div class='pq-grid-summary'></div>").prependTo(i.$bottom)), e = {
                data: o,
                $cont: n
            }, i.createTable(e)) : n && n[0].innerHTML && n.empty()
        },
        refreshVscroll: function(t) {
            var e, r = this.that,
                n = r.iGenerateView,
                i = r.options;
            if (i.virtualY) {
                var o = r.initV,
                    a = r.finalV;
                this.calcInitFinal();
                var l = o - r.initV,
                    s = a - r.finalV;
                i.fullrefreshOnScroll || i.detailModel.init || r._mergeCells || 1 != Math.abs(l) || 1 != Math.abs(s) ? o == r.initV && a == r.finalV || n.generateView() : -1 == l ? (n.removeTopRow(1), n.appendRow(r.finalV - a)) : 1 == l && (n.prependRow(), n.removeBottomRow(a - r.finalV)), r._saveDims(), n.scrollView(), e = this.setScrollVNumEles(), 1 >= e && this.refreshScrollbars()
            }
        },
        _refreshTableWidths: function(t, e) {
            var r, n, i = this.that,
                o = i.$tbl_header,
                a = e.header && o,
                l = i.$tbl,
                s = e.table && l,
                d = a ? o.children().children(".pq-row-hidden") : null,
                c = a ? i.$header.find(".pq-grid-col-resize-handle") : null,
                h = s && l ? l.children().children(".pq-row-hidden") : null,
                u = !1,
                f = 0;
            if (s && i.tables.length) {
                var p = i.tables[0].$tbl.children().children(".pq-row-hidden");
                h = h ? h.add(p) : p
            }
            for (var g = i.iGenerateView.colDef, v = 0, m = g.length; m > v; v++) {
                var w = g[v],
                    x = w.colIndx,
                    y = w.column,
                    _ = t[x],
                    b = _.outerWidth,
                    I = y.outerWidth;
                if (I !== b && (a && (r = d.find("td[pq-col-indx=" + x + "]"), r.width(I)), h && (n = h.find("td[pq-col-indx=" + x + "]"), n.length && (u = !0, n.width(I)))), f += I - b, a && 0 !== f) {
                    var C = c.filter("[pq-col-indx=" + x + "]"),
                        q = parseInt(C.css("left"));
                    C.css("left", q + f)
                }
            }
            u && i._trigger("tableWidthChange"), i._saveDims()
        }
    }
}(jQuery),
function(t) {
    "use strict";
    var e = !0;
    t(function() {
        var r = t("<input type='checkbox' style='position:fixed;left:-50px;top:-50px;'/>").appendTo(document.body);
        r[0].indeterminate = !0, r.on("change", function() {
            e = !1
        }), r.click(), r.remove()
    });
    var r = t.paramquery.cCheckBoxColumn = function(e, r) {
        var n = this;
        this.that = e, this.options = e.options, this.column = r;
        var i = {
                all: !1,
                header: !1,
                select: !1,
                check: !0,
                uncheck: !1
            },
            o = r.cb = t.extend({}, i, r.cb),
            a = this.dataIndx = r.dataIndx;
        r._render = n.cellRender(r), e.on("dataAvailable", function() {
            e.one("dataReady", function() {
                return n.onDataReady()
            })
        }).on("dataReady", function() {
            n.setValCBox()
        }).on("valChange", n.onCheckBoxChange(n, e)).on("cellKeyDown", function(t, e) {
            return n.onCellKeyDown(t, e)
        }).on("refreshHeader", function(t, e) {
            return n.refreshHeader(t, e)
        }), r.cb.select && e.on("rowSelect", n.onRowSelect(n, e)).on("beforeRowSelectDone", n.onBeforeRowSelect(n, e, a, o.check, o.uncheck)).on("change", n.onChange(n, e, a, o.check, o.uncheck))
    };
    r.prototype = {
        cellRender: function(t) {
            return function(e) {
                var r, n = e.rowData;
                if (!n.pq_gtitle && !n.pq_gsummary) return r = t.cb.check === e.cellData ? "checked" : "", "<input type='checkbox' " + r + " />"
            }
        },
        hasHeaderChkBox: function() {
            return this.column.cb.header
        },
        isEditableCell: function(t, e, r, n, i) {
            var o = this.that;
            return !o.isEditableRow({
                rowIndx: t,
                rowData: e
            }) || r && !o.isEditableCell({
                rowIndx: t,
                rowData: e,
                column: r,
                colIndx: n,
                dataIndx: i
            }) ? void 0 : !0
        },
        onBeforeRowSelect: function(t, e, r, n, i) {
            return function(o, a) {
                if ("checkbox" != a.source) {
                    var l = function(o) {
                        for (var a, l, s, d = o.length, c = e.columns[r], h = e.colIndxs[r]; d--;) s = o[d], a = s.rowIndx, l = s.rowData, t.isEditableCell(a, l, c, h, r) ? l[r] = l.pq_rowselect ? i : n : o.splice(d, 1)
                    };
                    l(a.addList), l(a.deleteList)
                }
            }
        },
        onCellKeyDown: function(e, r) {
            if (r.dataIndx == this.dataIndx && (13 == e.keyCode || 32 == e.keyCode)) {
                var n = t(e.originalEvent.target).find("input");
                return n.click(), !1
            }
        },
        onChange: function(t, e, r, n, i) {
            return function(t, i) {
                if ("checkbox" != i.source) {
                    var o = [],
                        a = [],
                        l = function(t) {
                            t.forEach(function(t) {
                                var e, i = t.newRow,
                                    l = t.oldRow;
                                i.hasOwnProperty(r) && (e = i[r], e === n ? o.push(t) : l && l[r] === n && a.push(t))
                            })
                        };
                    l(i.addList), l(i.updateList), e.SelectRow().update({
                        addList: o,
                        deleteList: a
                    })
                }
            }
        },
        onCheckBoxChange: function(t, e) {
            return function(r, n) {
                if (n.dataIndx == t.dataIndx) {
                    var i = t.column.cb,
                        o = r.originalEvent,
                        a = n.rowData,
                        l = n.rowIndx,
                        s = n.dataIndx,
                        d = n.input.checked,
                        c = {},
                        h = {};
                    c[s] = d ? i.check : i.uncheck, h[s] = a[s];
                    var u = [{
                        rowData: a,
                        rowIndx: l,
                        oldRow: h,
                        newRow: c
                    }];
                    if (n.check = d, n.rows = u, e._trigger("beforeCheck", o, n) === !1) return e.refreshCell({
                        rowIndx: l,
                        dataIndx: s
                    }), !1;
                    var f = {
                        source: "checkbox",
                        updateList: u
                    };
                    if (f.history = f.track = i.select ? !1 : null, e._digestData(f) === !1) return e.refreshCell({
                        rowIndx: l,
                        dataIndx: s
                    }), !1;
                    e.refreshRow({
                        rowIndx: l
                    }), u = n.rows = f.updateList, e._trigger("check", o, n), i.select && e.iRows[d ? "add" : "remove"]({
                        rows: u,
                        source: "checkbox"
                    }), t.setValCBox()
                }
            }
        },
        onDataReady: function() {
            var t, e = this.that,
                r = e.get_p_data(),
                n = 0,
                i = r.length,
                o = this.column,
                a = o.cb,
                l = o.dataIndx;
            if (null != l && r && a.select)
                for (; i > n; n++)(t = r[n]) && (t[l] === a.check ? t.pq_rowselect = !0 : t.pq_rowselect && (t[l] = a.check))
        },
        onHeaderChange: function(e) {
            for (var r = t(e.target), n = this.that, i = this.column, o = i.dataIndx, a = n.options, l = i.cb, s = l.all, d = s ? a.dataModel.data : n.pdata, c = "remote" == a.pageModel.type, h = c || !s ? n.riOffset : 0, u = [], f = {
                    column: i,
                    dataIndx: o,
                    source: "header"
                }, p = r[0].checked, g = 0, v = d.length; v > g; g++) {
                var m = g + h,
                    w = d[g],
                    x = {},
                    y = {};
                x[o] = p ? l.check : l.uncheck, y[o] = w[o], u.push({
                    rowIndx: m,
                    rowData: w,
                    newRow: x,
                    oldRow: y
                })
            }
            var _ = {
                updateList: u,
                source: "checkbox"
            };
            return _.history = _.track = l.select ? !1 : null, f.check = p, f.rows = u, n._trigger("beforeCheck", e, f) === !1 ? (n.refreshHeader(), !1) : n._digestData(_) === !1 ? (n.refreshHeader(), !1) : (n.refresh({
                header: !1
            }), u = f.rows = _.updateList, n._trigger("check", e, f), void(l.select && n.iRows[p ? "add" : "remove"]({
                rows: u,
                source: "checkbox"
            })))
        },
        onRowSelect: function(t, e) {
            return function(t, r) {
                "checkbox" != r.source && (r.addList.length || r.deleteList.length) && e.refresh()
            }
        },
        refreshHeader: function(t, r) {
            var n = this;
            if (this.hasHeaderChkBox()) {
                var i = this.that,
                    o = i.pdata;
                if (o) {
                    var a = i.getCellHeader({
                        dataIndx: this.dataIndx
                    });
                    if (a) {
                        var l = this.$inp = a.find("input");
                        this.setValCBox(), e && l.on("click", function(t) {
                            null == l.data("pq_value") && (l[0].checked = !0, l.data("pq_value", !0), n.onHeaderChange(t))
                        }), l.on("change", function(t) {
                            n.onHeaderChange(t)
                        })
                    }
                }
            }
        },
        setValCBox: function() {
            if (this.hasHeaderChkBox() && this.$inp) {
                var t, e, r = this.that,
                    n = this.options,
                    i = this.dataIndx,
                    o = this.column,
                    a = r.colIndxs[i],
                    l = o.cb,
                    s = l.all,
                    d = "remote" == n.pageModel.type,
                    c = d || !s ? r.riOffset : 0,
                    h = s ? n.dataModel.data : r.pdata,
                    u = null,
                    f = 0,
                    p = 0,
                    g = 0;
                if (h) {
                    for (var v = 0, m = h.length; m > v; v++) t = h[v], e = v + c, this.isEditableCell(e, t, o, a, i) && (p++, t[i] === l.check ? f++ : g++);
                    f == p && p ? u = !0 : g == p && (u = !1), this.$inp.pqval({
                        val: u
                    })
                }
            }
        }
    }
}(jQuery),
function(t) {
    "use strict";

    function e(t, e, r) {
        for (var n = 0, i = t.length; i > n; n++) {
            for (var o, a = t[n], l = {}, s = 0, d = e.length; d > s; s++) o = e[s], l[o] = a[o];
            r.push(l)
        }
    }
    var r = t.paramquery,
        n = {};
    n.options = {
        flex: {
            on: !0,
            one: !1,
            all: !0
        },
        detailModel: {
            cache: !0,
            offset: 100,
            expandIcon: "ui-icon-triangle-1-se glyphicon glyphicon-minus",
            collapseIcon: "ui-icon-triangle-1-e glyphicon glyphicon-plus"
        },
        dragColumns: {
            enabled: !0,
            acceptIcon: "ui-icon-check glyphicon-ok",
            rejectIcon: "ui-icon-closethick glyphicon-remove",
            topIcon: "ui-icon-circle-arrow-s glyphicon glyphicon-circle-arrow-down",
            bottomIcon: "ui-icon-circle-arrow-n glyphicon glyphicon-circle-arrow-up"
        },
        track: null,
        mergeModel: {
            flex: !1
        },
        realFocus: !0,
        sortModel: {
            on: !0,
            type: "local",
            multiKey: "shiftKey",
            number: !0,
            single: !0,
            cancel: !0,
            sorter: [],
            useCache: !0,
            ignoreCase: !1
        },
        filterModel: {
            on: !0,
            type: "local",
            mode: "AND",
            header: !1,
            timeout: 400
        }
    }, n._create = function() {
        var t = this,
            e = t.options;
        t.listeners = {}, t._queueATriggers = {}, t.iHistory = new r.cHistory(t), t.iGroup = new r.cGroup(t), t.iMerge = new r.cMerge(t), t.iFilterData = new r.cFilterData(t), t.iSelection = new i.Selection(t), t.iHeaderSearch = new r.cHeaderSearch(t), t.iUCData = new r.cUCData(t), t.iMouseSelection = new r.cMouseSelection(t), t._super(), new r.cFormula(t), t.iDragColumns = new r.cDragColumns(t), t.refreshToolbar(), "remote" === e.dataModel.location && t.refresh({
            table: !0
        }), t.on("dataAvailable", function() {
            t.one("refreshDone", function() {
                t._trigger("ready"), setTimeout(function() {
                    t.element && t._trigger("complete")
                }, 0)
            })
        }), t.refreshDataAndView({
            header: !0
        })
    }, t.widget("paramquery.pqGrid", r._pqGrid, n), t.widget.extend = function() {
        var e, r, n = Array.prototype.shift,
            i = t.isPlainObject,
            o = t.isArray,
            a = t.widget.extend,
            l = n.apply(arguments);
        "boolean" == typeof l && (e = l, l = n.apply(arguments));
        var s, d, c, h = arguments,
            u = 0,
            f = h.length;
        for (null == e && (e = f > 1); f > u; u++) {
            s = h[u];
            for (d in s) c = s[d], void 0 !== c && (r = !(u > 0), i(c) ? (l[d] = l[d] || {}, a(r, l[d], c)) : o(c) ? l[d] = e && r ? c.slice() : c : l[d] = c)
        }
        return l
    };
    var i = window.pq = window.pq || {};
    i.grid = function(e, r) {
        var n = t(e).pqGrid(r),
            i = n.data("paramqueryPqGrid") || n.data("paramquery-pqGrid");
        return i
    }, i.grid.render = {}, r.pqGrid.regional = {};
    var o = r.pqGrid.prototype;
    r.pqGrid.defaults = o.options, o.focus = function(t) {
        var e, r, n, i, o, a, l, s, d = t || {},
            c = this,
            h = c.options,
            u = d.$td,
            f = document.activeElement,
            p = c.$cont,
            g = p[0],
            v = d.rowIndxPage,
            m = d.colIndx;
        if (u) null != v && null != m || (n = this.getCellIndices({
            $td: u
        }), v = n.rowIndxPage, m = n.colIndx);
        else {
            if (null == v || null == m) {
                if (r = this._focusEle, f && f != document.body && "pq-grid-excel" != f.id && "pq-grid-cont" != f.className) return void(i = !0);
                r ? (v = r.rowIndxPage, m = r.colIndx) : i = !0
            }
            null != v && (l = c.iMerge, a = v + c.riOffset, l.ismergedCell(a, m) && (s = l.getRootCell(a, m, "o"), v = s.rowIndxPage, m = s.colIndx), u = c.getCell({
                rowIndxPage: v,
                colIndx: m
            }))
        }
        h.realFocus ? (g.removeAttribute("tabindex"), r = this._focusEle = this._focusEle || {}, u && (e = u[0]) && "TD" == e.nodeName.toUpperCase() && !e.edited ? (r.$ele && r.$ele[0].removeAttribute("tabindex"), r.$ele = u, r.rowIndxPage = v, r.colIndx = m, e.setAttribute("tabindex", 0), i || e.focus()) : (o = h.dataModel.data, o && o.length ? (u = p.find(".pq-grid-row:first > .pq-grid-cell"), u.length && u[0].setAttribute("tabindex", 0)) : g.setAttribute("tabindex", 0))) : (r = this._focusEle, r && (this.removeClass({
            rowIndxPage: r.rowIndxPage,
            colIndx: r.colIndx,
            cls: "pq-focus",
            refresh: !1
        }), this.element.find(".pq-focus").removeClass("pq-focus")), u && (this.addClass({
            rowIndxPage: v,
            colIndx: m,
            cls: "pq-focus"
        }), this._focusEle = {
            $ele: u,
            rowIndxPage: v,
            colIndx: m
        }))
    }, o.onfocus = function(t) {
        if (!this.options.realFocus) {
            var e = this._focusEle;
            if (e) {
                var r = e.rowIndxPage,
                    n = e.colIndx;
                this.addClass({
                    rowIndxPage: r,
                    colIndx: n,
                    cls: "pq-focus"
                })
            }
        }
    }, o.onblur = function() {
        if (!this.options.realFocus) {
            var t = this._focusEle;
            if (t) {
                var e = t.rowIndxPage,
                    r = t.colIndx;
                this.removeClass({
                    rowIndxPage: e,
                    colIndx: r,
                    cls: "pq-focus"
                })
            }
        }
    }, o.callFn = function(t, e) {
        return i.getFn(t).call(this, e)
    }, o.rowExpand = function(t) {
        this.iHierarchy.rowExpand(t)
    }, o.rowInvalidate = function(t) {
        this.iHierarchy.rowInvalidate(t)
    }, o.rowCollapse = function(t) {
        this.iHierarchy.rowCollapse(t)
    }, o.saveState = function(e) {
        e = e || {};
        for (var r, n, i, o = this, a = o.element, l = t.extend, s = o.options, d = s.sortModel, c = l(!0, {}, {
                sorter: d.sorter
            }), h = s.pageModel, u = {
                rPP: h.rPP,
                curPage: h.curPage
            }, f = o.colModel, p = [], g = 0, v = f.length, m = s.groupModel, w = l(!0, {}, {
                dataIndx: m.dataIndx,
                dir: m.dir,
                collapsed: m.collapsed,
                merge: m.merge,
                grandSummary: m.grandSummary
            }), x = a[0].id; v > g; g++) r = f[g], i = {
            width: r.width,
            dataIndx: r.dataIndx,
            hidden: r.hidden
        }, (n = r.filter) && (i.filter = {
            value: n.value,
            value2: n.value2,
            on: n.on
        }), p[g] = i;
        var y = {
            colModel: p,
            height: s.height,
            datestamp: Date.now(),
            width: s.width,
            groupModel: w,
            pageModel: u,
            sortModel: c,
            freezeRows: s.freezeRows,
            freezeCols: s.freezeCols
        };
        return e.stringify !== !1 && (y = JSON.stringify(y), e.save !== !1 && "undefined" != typeof Storage && localStorage.setItem("pq-grid" + (x || ""), y)), y
    }, o.loadState = function(e) {
        e = e || {};
        var r, n = this,
            i = n.element,
            o = t.widget.extend,
            a = t.extend,
            l = i[0].id,
            s = e.state || ("undefined" == typeof Storage ? void 0 : localStorage.getItem("pq-grid" + (l || "")));
        if (!s) return !1;
        "string" == typeof s && (s = JSON.parse(s));
        for (var d, c, h, u = s.colModel, f = [], p = [], g = [], v = [], m = [], w = n.options, x = n.depth > 1, y = x ? n.colModel : w.colModel, _ = 0, b = u.length; b > _; _++) d = u[_], h = d.dataIndx, p[h] = !0, g[h] = _, f[h] = d.width, v[h] = d.filter, m[h] = d.hidden;
        x || y.sort(function(t, e) {
            return g[t.dataIndx] - g[e.dataIndx]
        });
        for (var _ = 0, b = y.length; b > _; _++) c = y[_], h = c.dataIndx, p[h] && (c.width = f[h] || c.width, c.filter = a(c.filter, v[h]), c.hidden = m[h]);
        return n.iColModel.init(), o(w.sortModel, s.sortModel), o(w.pageModel, s.pageModel), n.Group().option(s.groupModel, !1), r = {
            freezeRows: s.freezeRows,
            freezeCols: s.freezeCols
        }, isNaN(1 * w.height) || isNaN(1 * s.height) || (r.height = s.height), isNaN(1 * w.width) || isNaN(1 * s.width) || (r.width = s.width), n.option(r), e.refresh !== !1 && n.refreshDataAndView(), !0
    }, o.refreshToolbar = function() {
        var e, r = this,
            n = r.options,
            o = n.toolbar;
        if (r._toolbar && (e = r._toolbar, e.destroy()), o) {
            var a = o.cls,
                a = a ? a : "",
                l = o.style,
                l = l ? l : "",
                s = o.attr,
                s = s ? s : "",
                d = o.items,
                c = t("<div class='" + a + "' style='" + l + "' " + s + " ></div>");
            e ? e.widget().replaceWith(c) : r.$top.append(c), e = i.toolbar(c, {
                items: d,
                gridInstance: r,
                bootstrap: n.bootstrap
            }), n.showToolbar || c.css("display", "none"), r._toolbar = e
        }
    }, o.isLeftOrRight = function(t) {
        var e = (this.options, this.freezeCols);
        return t > e ? "right" : "left"
    }, o.ovCreateHeader = function(t) {
        this.options.filterModel.header && this.iHeaderSearch.createDOM(t)
    }, o.filter = function(t) {
        return this.iFilterData.filter(t)
    }, o._initTypeColumns = function() {
        for (var t = this.colModel, e = 0, n = t.length; n > e; e++) {
            var i = t[e],
                o = i.type;
            "checkBoxSelection" === o || "checkbox" == o ? (i.type = "checkbox", new r.cCheckBoxColumn(this, i)) : "detail" === o && (i.dataIndx = "pq_detail", this.iHierarchy = new r.cHierarchy(this, i))
        }
    }, o.refreshHeader = function() {
        this._createHeader(), this.iGenerateView.setPanes(), this._refreshHeaderSortIcons()
    }, o.refreshHeaderFilter = function(t) {
        var e = this.normalize(t),
            r = e.colIndx,
            n = e.column,
            i = this.iHeaderSearch,
            o = this.$header.find(".pq-grid-header-search-row > .pq-col-" + r);
        o.replaceWith(i.renderCell(n, r)), i.postRenderCell(n, r)
    }, o._refreshHeaderSortIcons = function() {
        this.iHeader.refreshHeaderSortIcons()
    }, o.getLargestRowCol = function(t) {
        for (var e, r = 0; r < t.length; r++) {
            var n = t[r],
                i = n.rowIndx;
            null == e ? e = n.rowIndx : i > e && (e = i), e = n.rowIndx
        }
    }, o.bringCellToView = function(t) {
        this._bringCellToView(t)
    }, o._setUrl = function(t) {
        this.options.dataModel.getUrl = function() {
            return {
                url: this.url + (null != t ? t : "")
            }
        }
    }, o.pageData = function() {
        return this.pdata
    }, o.getData = function(t) {
        t = t || {};
        var r = t.dataIndx,
            n = r ? r.length : 0,
            i = t.data,
            o = this.options.dataModel,
            a = o.data || [],
            l = o.dataUF || [],
            s = [];
        if (!n) return l.length ? a.concat(l) : a;
        i ? e(i, r, s) : (e(a, r, s), e(l, r, s));
        for (var d = [], c = 0; n > c; c++) {
            var h = r[c],
                u = this.getColumn({
                    dataIndx: h
                });
            d.push({
                dataIndx: h,
                dir: "up",
                dataType: u.dataType,
                sortType: u.sortType
            })
        }
        s = this.iSort._sortLocalData(d, s);
        for (var f = [], p = void 0, g = 0, v = s.length; v > g; g++) {
            var m = s[g],
                w = JSON.stringify(m);
            w !== p && (f.push(m), p = w)
        }
        return f
    }, o.get_p_data = function() {
        var t, e, r, n, i = this.options,
            o = i.pageModel,
            a = o.type,
            l = i.dataModel.data,
            s = this.pdata,
            d = [];
        return a ? (e = o.rPP, r = this.riOffset, t = "remote" == a, d = t ? new Array(r) : l.slice(0, r), n = t ? [] : l.slice(r + e), d.concat(s, n)) : s || l
    }, o._onDataAvailable = function(t) {
        t = t || {};
        var e = this.options,
            r = !t.data,
            n = t.source,
            i = t.sort,
            o = [],
            a = e.filterModel,
            l = e.dataModel,
            s = e.sortModel;
        l.location;
        return r !== !1 && t.trigger !== !1 && this._trigger("dataAvailable", t.evt, {
            source: n
        }), o = a && a.on && "local" == a.type ? this.iFilterData.filterLocalData(t).data : l.data, "local" == s.type && i !== !1 && (r ? this.sort({
            refresh: !1
        }) : o = this.iSort.sortLocalData(o)), r === !1 ? o : void this.refreshView(t)
    }, o.reset = function(e) {
        e = e || {};
        var r, n, i = this,
            o = e.sort,
            a = i.options,
            l = e.refresh !== !1,
            s = t.extend,
            d = e.filter,
            c = e.group;
        (o || d || c) && (o && (r = o === !0 ? {
            sorter: []
        } : o, s(a.sortModel, r)), d && !l && this.iFilterData.clearFilters(i.colModel), c && (n = c === !0 ? {
            dataIndx: []
        } : c, i.groupOption(n, !1)), l && (d ? (i.filter({
            oper: "replace",
            rules: []
        }), i.refreshHeader()) : o ? i.sort() : i.refreshView()))
    }, o._trigger = r._trigger, o.on = r.on, o.one = r.one, o.off = r.off, o.pager = function() {
        return this.pagerW
    }, o.vscrollbar = function() {
        return this.vscroll
    }, o.hscrollbar = function() {
        return this.hscroll
    }, o.toolbar = function() {
        return this._toolbar.element
    }, o.Columns = function() {
        return this.iColModel
    }, r.cColModel = function(t) {
        this.that = t, this.init()
    }, r.cColModel.prototype = {
        alignColumns: function(t, e) {
            for (var r = 0; e > r; r++) {
                var n = t[r];
                if (!n.align) {
                    var i = n.dataType;
                    !i || "integer" != i && "float" != i || (n.align = "right")
                }
            }
        },
        alter: function(t) {
            var e = this.that;
            t.call(e), e.refreshCM(), e.refresh()
        },
        assignRowSpan: function() {
            for (var t = this.that, e = t.colModel.length, r = t.headerCells, n = t.depth, i = 0; e > i; i++)
                for (var o = 0; n > o; o++) {
                    var a = r[o][i];
                    if (!(i > 0 && a == r[o][i - 1] || o > 0 && a == r[o - 1][i])) {
                        for (var l = 1, s = o + 1; n > s; s++) {
                            var d = r[s][i];
                            a == d && l++
                        }
                        a.rowSpan = l
                    }
                }
            return r
        },
        autoGenColumns: function() {
            var e = this.that,
                r = e.options,
                n = r.columnTemplate || {},
                o = n.dataType,
                a = n.title,
                l = n.width,
                s = r.dataModel.data,
                d = i.validation,
                c = [];
            if (s && s.length) {
                var h = s[0];
                t.each(h, function(t, e) {
                    var r = "string";
                    d.isInteger(e) ? r = e + "".indexOf(".") > -1 ? "float" : "integer" : d.isDate(e) ? r = "date" : d.isFloat(e) && (r = "float"), c.push({
                        dataType: o ? o : r,
                        dataIndx: t,
                        title: a ? a : t,
                        width: l ? l : 100
                    })
                })
            }
            r.colModel = c
        },
        cacheIndices: function() {
            for (var t = this.that, e = "JSON" == this.getDataType(), r = {}, n = {}, i = {}, o = t.colModel, a = 0, l = o.length; l > a; a++) {
                var s = o[a],
                    d = s.dataIndx;
                null == d && (d = "detail" == s.type ? "pq_detail" : e ? "dataIndx_" + a : a, "pq_detail" == d && (s.dataType = "object"), s.dataIndx = d), r[d] = s, n[d] = a;
                var c = s.validations;
                c && (i[d] = i)
            }
            t.columns = r, t.colIndxs = n, t.validations = i
        },
        collapse: function(t, e) {
            var r = e.on,
                n = t.colModel || [],
                i = n.length,
                o = e.last ? i - 1 : 0;
            i && (this.each(function(t) {
                t.hidden = r
            }, n), this.each(function(t) {
                t.hidden = !1
            }, [n[o]]))
        },
        each: function(t, e) {
            var r = this.that;
            (e || r.options.colModel).forEach(function(e) {
                t.call(r, e), e.colModel && this.each(t, e.colModel)
            }, this)
        },
        extend: function(e, r) {
            for (var n, i, o = t.extend, a = e.length; a--;) {
                var l = e[a];
                for (n in r) void 0 === l[n] && (i = r[n], i && "object" == typeof i ? l[n] = o(!0, {}, i) : l[n] = i)
            }
        },
        find: function(t, e) {
            for (var r, n, i = this.that, o = e || i.options.colModel, a = 0, l = o.length; l > a; a++) {
                if (r = o[a], t.call(i, r)) return r;
                if (r.colModel && (n = this.find(t, r.colModel))) return n
            }
        },
        getHeadersCells: function() {
            for (var t = this.that, e = t.options.colModel, r = t.colModel.length, n = t.depth, i = [], o = 0; n > o; o++) {
                i[o] = [];
                for (var a = 0, l = 0, s = 0; r > s; s++) {
                    var d;
                    if (0 == o) d = e[a];
                    else {
                        var c = i[o - 1][s],
                            h = c.colModel;
                        if (h && 0 != h.length) {
                            for (var u = s - c.leftPos, f = 0, p = 0, g = 0; g < h.length; g++)
                                if (f += h[g].childCount > 0 ? h[g].childCount : 1, f > u) {
                                    p = g;
                                    break
                                }
                            d = h[p]
                        } else d = c
                    }
                    var v = d.childCount ? d.childCount : 1;
                    s == l ? (d.leftPos = s, i[o][s] = d, l += v, e[a + 1] && a++) : i[o][s] = i[o][s - 1]
                }
            }
            return t.headerCells = i, i
        },
        getDataType: function() {
            var t = this.colModel;
            if (t && t[0]) {
                var e = t[0].dataIndx;
                return "string" == typeof e ? "JSON" : "ARRAY"
            }
        },
        init: function() {
            var t, e, r, n = this.that,
                i = n.options,
                o = i.columnTemplate,
                a = i.colModel;
            a || (this.autoGenColumns(), a = i.colModel), t = this.nestedCols(a), n.depth = t.depth, e = n.colModel = t.colModel, r = e.length, o && this.extend(e, o), this.getHeadersCells(), this.alignColumns(e, r), this.assignRowSpan(), this.cacheIndices(), n._trigger("CMInit")
        },
        nestedCols: function(t, e, r, n) {
            var i = t.length,
                o = [];
            null == e && (e = 1);
            for (var a = e, l = 0, s = 0, d = 0, c = 0, h = 0; i > h; h++) {
                var u = t[h],
                    f = u.colModel,
                    p = u.collapsible;
                if (u.parent = n ? n : void 0, r === !0 && (u.hidden = r), f && f.length) {
                    p && this.collapse(u, p);
                    var g = this.nestedCols(f, e + 1, u.hidden, u);
                    o = o.concat(g.colModel), g.colSpan > 0 ? (g.depth > a && (a = g.depth), u.colSpan = g.colSpan, l += g.colSpan) : u.colSpan = 0, c += g.o_colspan, u.o_colspan = g.o_colspan, u.childCount = g.childCount, d += g.childCount
                } else u.hidden ? u.colSpan = 0 : (u.colSpan = 1, l++), c++, u.o_colspan = 1, u.childCount = 0, d++, o.push(u)
            }
            return {
                depth: a,
                colModel: o,
                colSpan: l,
                width: s,
                childCount: d,
                o_colspan: c
            }
        }
    }
}(jQuery),
function(t) {
    "use strict";
    var e = function(t) {
        var e = this;
        e.that = t, e["class"] = "pq-grid-select-overlay", e.ranges = [], t.on("refresh refreshRow resizeTable", e.onRefresh(e, t))
    };
    t.paramquery.cCells = e, e.prototype = {
        addBlock: function(e, r) {
            if (e && this.addUnique(this.ranges, e)) {
                var n = this.that,
                    i = e.r1,
                    o = e.c1,
                    a = e.r2,
                    l = e.c2,
                    s = this.serialize(i, o, a, l) + " " + e.type,
                    d = this.getCoord,
                    c = function(t, e) {
                        return n.getCell({
                            rowIndx: t,
                            colIndx: e
                        })
                    },
                    h = this.shiftRC(i, o, a, l);
                if (h) {
                    i = h[0], o = h[1], a = h[2], l = h[3];
                    var u, f, p, g, v, m, w, x, y, _, b, I, C, q = c(i, o),
                        R = q.closest("table")[0],
                        D = c(a, l),
                        M = D.closest("table")[0];
                    q[0] && (h = d(q), x = h[0], y = h[1], h = d(D), _ = h[2], b = h[3], I = b - y, C = _ - x, R == M ? this.addLayer(x, y, I, C, s, R) : (u = c(i, l), f = u.closest("table")[0], p = c(a, o), g = p.closest("table")[0], w = t(R).parent()[0], m = w.offsetWidth, v = w.offsetHeight, g == R ? (this.addLayer(x, y, I, m - x, s, R, "border-right:0;"), this.addLayer(0, y, I, _, s, M, "border-left:0;")) : R == f ? (this.addLayer(x, y, v - y, C, s, R, "border-bottom:0;"), this.addLayer(x, 0, b, C, s, M, "border-top:0;")) : (this.addLayer(x, y, v - y, m - x, s, R, "border-right:0;border-bottom:0"), this.addLayer(0, y, v - y, _, s, f, "border-left:0;border-bottom:0"), this.addLayer(x, 0, b, m - x, s, g, "border-right:0;border-top:0"), this.addLayer(0, 0, b, _, s, M, "border-left:0;border-top:0"))))
                }
            }
        },
        addLayer: function(e, r, n, i, o, a, l) {
            r -= 1;
            var s = "position:absolute;left:" + e + "px;top:" + r + "px;height:" + n + "px;width:" + i + "px;";
            s += "pointer-events:none;", -1 == o.indexOf("cell") && (s += "border:1px solid #999;" + (l || "")), t("<svg class='" + this["class"] + " " + o + "' style='" + s + "'></svg>").appendTo(t(a).parent())
        },
        addUnique: function(t, e) {
            var r = t.filter(function(t) {
                return e.r1 == t.r1 && e.c1 == t.c1 && e.r2 == t.r2 && e.c2 == t.c2
            })[0];
            return r ? void 0 : (t.push(e), !0)
        },
        getCoord: function(t) {
            var e = t.closest("table"),
                r = t[0],
                n = r.offsetHeight,
                i = r.offsetWidth,
                o = r.offsetLeft + parseInt(e.css("left")),
                a = r.offsetTop + parseInt(e.css("top"));
            return [o, a, o + i, a + n]
        },
        getLastVisibleFrozenCI: function() {
            for (var t = this.that, e = t.colModel, r = t.options.freezeCols - 1; r >= 0; r--)
                if (!e[r].hidden) return r
        },
        getLastVisibleFrozenRIP: function() {
            for (var t = this.that, e = t.get_p_data(), r = t.riOffset, n = t.options.freezeRows + r - 1; n >= r; n--)
                if (!e[n].pq_hidden) return n - r
        },
        getSelection: function() {
            var t = this.that,
                e = t.get_p_data(),
                r = t.colModel,
                n = [];
            return this.ranges.forEach(function(t) {
                var i, o, a, l = t.r1,
                    s = t.r2,
                    d = t.c1,
                    c = t.c2;
                for (o = l; s >= o; o++)
                    for (i = e[o], a = d; c >= a; a++) n.push({
                        dataIndx: r[a].dataIndx,
                        colIndx: a,
                        rowIndx: o,
                        rowData: i
                    })
            }), n
        },
        isSelected: function(t) {
            var e = this.that,
                r = e.normalize(t),
                n = r.rowIndx,
                i = r.colIndx;
            return null == i || null == n ? null : !!this.ranges.find(function(t) {
                var e = t.r1,
                    r = t.r2,
                    o = t.c1,
                    a = t.c2;
                return n >= e && r >= n && i >= o && a >= i ? !0 : void 0
            })
        },
        onRefresh: function(t, e) {
            var r;
            return function() {
                clearTimeout(r), r = setTimeout(function() {
                    e.element && (t.removeAll(), e.Selection().address().forEach(function(e) {
                        t.addBlock(e)
                    }))
                }, 50)
            }
        },
        removeAll: function() {
            var t = this.that.$cont;
            t && t.children().children("svg").remove(), this.ranges = []
        },
        removeBlock: function(t) {
            if (t) {
                var e = t.r1,
                    r = t.c1,
                    n = t.r2,
                    i = t.c2,
                    o = this.ranges.findIndex(function(t) {
                        return e == t.r1 && r == t.c1 && n == t.r2 && i == t.c2
                    });
                o >= 0 && (this.ranges.splice(o, 1), this.that.$cont.find("." + this["class"] + "." + this.serialize(e, r, n, i)).remove())
            }
        },
        serialize: function(t, e, r, n) {
            return "r1" + t + "c1" + e + "r2" + r + "c2" + n
        },
        shiftRC: function(t, e, r, n) {
            var i, o = this.that,
                a = o.iMerge,
                l = o.options,
                s = l.freezeCols,
                d = l.freezeRows,
                c = o.initH,
                h = o.initV,
                u = o.finalH,
                f = o.finalV,
                p = o.riOffset;
            return t -= p, r -= p, t = d > t ? Math.max(t, Math.min(0, r)) : Math.max(t, h), e = s > e ? e : Math.max(e, c), d && r >= d && h > r ? r = this.getLastVisibleFrozenRIP() : r >= h && (r = Math.min(r, f)), s && n >= s && c > n ? n = this.getLastVisibleFrozenCI() : n >= c && (n = Math.min(n, u)), e > n || t > r ? void 0 : (t += p, r += p, a.ismergedCell(t, e) && (i = a.getRootCell(t, e, "a"), t = i.rowIndx, e = i.colIndx), a.ismergedCell(r, n) && (i = a.getRootCell(r, n, "a"), r = i.rowIndx, n = i.colIndx), [t, e, r, n])
        }
    }
}(jQuery),
function(t) {
    "use strict";

    function e(t) {
        t.shiftKey && "pqGrid:mousePQUp" != t.type || (this._trigger("selectEnd", null, {
            selection: this.Selection()
        }), this.off("mousePQUp", e), this.off("keyUp", e))
    }
    t.paramquery.pqGrid.prototype.Range = function(t, e) {
        return new r.Range(this, t, "range", e)
    };
    var r = window.pq = window.pq || {};
    r.extend = function(t, e, r) {
        var n = function() {};
        n.prototype = t.prototype;
        var i = e.prototype = new n,
            o = t.prototype;
        for (var a in r) {
            var l = o[a],
                s = r[a];
            l ? i[a] = function(t, e) {
                return function() {
                    var r, n = this._super;
                    return this._super = function() {
                        return t.apply(this, arguments)
                    }, r = e.apply(this, arguments), this._super = n, r
                }
            }(l, s) : i[a] = s
        }
        i.constructor = e, i._base = t, i._bp = function(t) {
            var e = arguments;
            return Array.prototype.shift.call(e), o[t].apply(this, e)
        }
    };
    var n = r.Range = function(t, e, r, i) {
        if (null == t) throw "invalid param";
        return this.that = t, this._areas = [], this instanceof n == 0 ? new n(t, e, r, i) : (this._type = r || "range", void this.init(e, i))
    };
    n.prototype = t.extend({
        add: function(t) {
            this.init(t)
        },
        address: function() {
            return this._areas
        },
        addressLast: function() {
            var t = this.address();
            return t[t.length - 1]
        },
        clear: function() {
            return this.copy({
                copy: !1,
                cut: !0,
                source: "clear"
            })
        },
        clearOther: function(t) {
            var e, r = this._normal(t, !0),
                n = this.address();
            for (e = n.length - 1; e >= 0; e--) {
                var i = n[e];
                i.r1 == r.r1 && i.c1 == r.c1 && i.r2 == r.r2 && i.c2 == r.c2 || n.splice(e, 1)
            }
        },
        _copyArea: function(t, e, r, n, i, o, a, l, s, d, c) {
            var h, u, f, p, g, v = this.that,
                m = v.readCell,
                w = this.getRenderVal,
                x = v.iMerge,
                y = v.riOffset,
                _ = v.iGenerateView;
            for (p = t; e >= p; p++) {
                var b = [],
                    I = l[p],
                    C = {},
                    q = {},
                    R = {
                        rowIndx: p,
                        rowIndxPage: p - y,
                        rowData: I,
                        Export: !0,
                        exportClip: !0
                    };
                for (g = r; n >= g; g++) {
                    var D = i[g],
                        M = D.dataIndx;
                    D.copy !== !1 && (h = I[M], d && (u = m(I, D, x, p, g), u === h && (R.colIndx = g, R.column = D, R.dataIndx = M, u = w(R, c, _)[0]), b.push(u)), s && void 0 !== h && (C[M] = void 0, q[M] = h))
                }
                s && a.push({
                    rowIndx: p,
                    rowData: I,
                    oldRow: q,
                    newRow: C
                }), f = b.join("	"), b = [], o.push(f)
            }
        },
        copy: function(t) {
            t = t || {};
            var e, r, n, i, o, a = this.that,
                l = t.dest,
                s = !!t.cut,
                d = null == t.copy ? !0 : t.copy,
                c = t.source || (s ? "cut" : "copy"),
                h = t.history,
                u = t.allowInvalid,
                f = [],
                p = [],
                g = a.get_p_data(),
                v = a.colModel,
                m = t.render,
                w = this.address();
            if (h = null == h ? !0 : h, u = null == u ? !0 : u, m = null == m ? a.options.copyModel.render : m, w.length) {
                if (w.forEach(function(t) {
                        e = t.type, r = t.r1, n = t.c1, i = "cell" === e ? r : t.r2, o = "cell" === e ? n : t.c2, this._copyArea(r, i, n, o, v, p, f, g, s, d, m)
                    }, this), d) {
                    var x = p.join("\n");
                    if (t.clip) {
                        var y = t.clip;
                        y.val(x), y.select()
                    } else a._setGlobalStr(x)
                }
                if (l) a.paste({
                    dest: l,
                    rowList: f,
                    history: h,
                    allowInvalid: u
                });
                else if (s) {
                    var _ = a._digestData({
                        updateList: f,
                        source: c,
                        history: h,
                        allowInvalid: u
                    });
                    _ !== !1 && a.refresh({
                        source: "cut"
                    })
                }
            }
        },
        _countArea: function(t) {
            var e = t,
                r = t.type,
                n = e.r1,
                i = e.c1,
                o = e.r2,
                a = e.c2;
            return "cell" === r ? 1 : "row" === r ? 0 : (o - n + 1) * (a - i + 1)
        },
        count: function() {
            for (var t = "range" === this._type, e = this.address(), r = 0, n = e.length, i = 0; n > i; i++) r += t ? this._countArea(e[i]) : 1;
            return r
        },
        cut: function(t) {
            return t = t || {}, t.cut = !0, this.copy(t)
        },
        getIndx: function(t) {
            return null == t ? this._areas.length - 1 : t
        },
        getValue: function() {
            var t, e, r, n, i, o, a, l, s, d, c = this.address(),
                h = [],
                u = this.that;
            if (c.length) {
                for (t = c[0], n = t.r1, i = t.c1, o = t.r2, a = t.c2, d = u.get_p_data(), l = n; o >= l; l++)
                    for (e = d[l], s = i; a >= s; s++) r = e[u.colModel[s].dataIndx], h.push(r);
                return h
            }
        },
        hide: function(t) {
            t = t || {};
            var e, r = this.that,
                n = r.colModel,
                i = r.get_p_data(),
                o = this._areas;
            o.forEach(function(t) {
                var r = t.type,
                    o = t.r1,
                    a = t.r2,
                    l = t.c1,
                    s = t.c2;
                if ("column" === r)
                    for (e = l; s >= e; e++) n[e].hidden = !0;
                else if ("row" === r)
                    for (e = o; a >= e; e++) i[e].pq_hidden = !0
            }), t.refresh !== !1 && r.refreshView()
        },
        indexOf: function(t) {
            t = this._normal(t);
            for (var e, r = t.r1, n = t.c1, i = t.r2, o = t.c2, a = this.address(), l = 0, s = a.length; s > l; l++)
                if (e = a[l], "row" !== e.type && r >= e.r1 && i <= e.r2 && n >= e.c1 && o <= e.c2) return l;
            return -1
        },
        index: function(t) {
            t = this._normal(t);
            for (var e, r = t.type, n = t.r1, i = t.c1, o = t.r2, a = t.c2, l = this.address(), s = 0, d = l.length; d > s; s++)
                if (e = l[s], r === e.type && n === e.r1 && o === e.r2 && i === e.c1 && a === e.c2) return s;
            return -1
        },
        init: function(t, e) {
            if (e = e !== !1, t)
                if ("function" == typeof t.push)
                    for (var r = 0, n = t.length; n > r; r++) this.init(t[r], e);
                else {
                    var i = this._normal(t, e),
                        o = this._areas = this._areas || [];
                    i && o.push(i)
                }
        },
        merge: function(t) {
            t = t || {};
            var e, r, n = this.that,
                i = n.options,
                o = i.mergeCells,
                a = this._areas,
                l = a[0];
            l && (e = l.r2 - l.r1 + 1, r = l.c2 - l.c1 + 1, (e > 1 || r > 1) && (l.rc = e, l.cc = r, o.push(l), t.refresh !== !1 && n.refreshView()))
        },
        replace: function(t, e) {
            var r = this._normal(t),
                n = this._areas,
                i = this.getIndx(e);
            n.splice(i, 1, r)
        },
        remove: function(t) {
            var e = this._areas,
                r = this.indexOf(t);
            r >= 0 && e.splice(r, 1)
        },
        resize: function(t, e) {
            var r = this._normal(t),
                n = this._areas,
                i = this.getIndx(e),
                o = n[i];
            return ["r1", "c1", "r2", "c2", "rc", "cc", "type"].forEach(function(t) {
                o[t] = r[t]
            }), this
        },
        rows: function(t) {
            var e = this.that,
                n = [],
                i = this.addressLast();
            if (i)
                for (var o = i.r1, a = i.c1, l = i.r2, s = i.c2, d = i.type, c = null == t ? o : o + t, h = null == t ? l : o + t, u = c; h >= u; u++) n.push({
                    r1: u,
                    c1: a,
                    r2: u,
                    c2: s,
                    type: d
                });
            return r.Range(e, n, "row")
        },
        _normal: function(t, e) {
            if (t.type) return t;
            var r;
            if ("function" == typeof t.push) {
                r = [];
                for (var n = 0, i = t.length; i > n; n++) {
                    var o = this._normal(t[n], e);
                    o && r.push(o)
                }
                return r
            }
            var a, l, s = this.that,
                d = s.get_p_data(),
                c = d.length - 1,
                h = s.colModel,
                u = h.length - 1,
                f = t.r1,
                p = t.c1,
                f = f > c ? c : f,
                p = p > u ? u : p,
                g = t.rc,
                v = t.cc,
                m = t.r2,
                w = t.c2,
                m = m > c ? c : m,
                w = w > u ? u : w,
                m = g ? f + g - 1 : m,
                w = v ? p + v - 1 : w;
            return 0 > u || 0 > c ? null : (f > m && (a = f, f = m, m = a), p > w && (a = p, p = w, w = a), null != f || null != p ? (null == f ? (f = 0, m = c, w = null == w ? p : w, l = "column") : null == p ? (!t._type, p = 0, m = null == m ? f : m, w = u, l = t._type || "row") : null == m || f == m && p == w ? (l = "cell", m = f, w = p) : l = "block", e && (r = s.iMerge.inflateRange(f, p, m, w), f = r[0], p = r[1], m = r[2], w = r[3]), t.r1 = f, t.c1 = p, t.r2 = m, t.c2 = w, t.type = t.type || l, t) : void 0)
        },
        select: function() {
            var t = this.that,
                e = t.iSelection,
                r = this._areas;
            return r.length && (e.removeAll({
                trigger: !1
            }), r.forEach(function(t) {
                e.add(t, !1)
            }), e.trigger()), this
        },
        unhide: function(t) {
            t = t || {};
            var e, r = this.that,
                n = r.colModel,
                i = r.get_p_data(),
                o = this._areas;
            o.forEach(function(t) {
                var r = t.type,
                    o = t.r1,
                    a = t.r2,
                    l = t.c1,
                    s = t.c2;
                if ("column" === r)
                    for (e = l; s >= e; e++) n[e].hidden = !1;
                else if ("row" === r)
                    for (e = o; a >= e; e++) i[e].pq_hidden = !1
            }), t.refresh !== !1 && r.refreshView()
        },
        unmerge: function(t) {
            t = t || {};
            var e = this.that,
                r = e.options,
                n = r.mergeCells,
                i = this._areas,
                o = i[0];
            if (o) {
                for (var a = 0; a < n.length; a++) {
                    var l = n[a];
                    if (l.r1 === o.r1 && l.c1 === o.c1) {
                        n.splice(a, 1);
                        break
                    }
                }
                t.refresh !== !1 && e.refreshView()
            }
        },
        value: function(t) {
            var e, r, n, i, o, a = 0,
                l = this.that,
                s = l.colModel,
                d = [],
                c = this.address();
            if (void 0 === t) return this.getValue();
            for (var h = 0; h < c.length; h++) {
                e = c[h], r = e.r1, n = e.c1, i = e.r2, o = e.c2;
                for (var u = r; i >= u; u++) {
                    for (var f = l.normalize({
                            rowIndx: u
                        }), p = f.rowData, g = f.rowIndx, v = {}, m = {}, w = n; o >= w; w++) {
                        var x = s[w].dataIndx;
                        m[x] = t[a++], v[x] = p[x]
                    }
                    d.push({
                        rowData: p,
                        rowIndx: g,
                        newRow: m,
                        oldRow: v
                    })
                }
            }
            return d.length && (l._digestData({
                updateList: d,
                source: "range"
            }), l.refresh()), this
        }
    }, r.mixin.render);
    var i = r.Selection = function(e, r) {
        if (null == e) throw "invalid param";
        return this instanceof i == 0 ? new i(e, r) : (this._areas = [], this.that = e, this.iCells = new t.paramquery.cCells(e), void this._base(e, r))
    };
    r.extend(n, i, {
        add: function(t, e) {
            var r = this._normal(t, !0),
                n = this.iCells,
                i = this.indexOf(r);
            i >= 0 || (n.addBlock(r), this._super(r), e !== !1 && this.trigger())
        },
        clearOther: function(t) {
            var e = this.iCells,
                r = this._normal(t, !0);
            this.address().forEach(function(t) {
                t.r1 == r.r1 && t.c1 == r.c1 && t.r2 == r.r2 && t.c2 == r.c2 || e.removeBlock(t)
            }), this._super(r), this.trigger()
        },
        getSelection: function() {
            return this.iCells.getSelection()
        },
        isSelected: function(t) {
            return this.iCells.isSelected(t)
        },
        removeAll: function(t) {
            t = t || {}, this._areas.length && (this.iCells.removeAll(), this._areas = [], t.trigger !== !1 && this.trigger())
        },
        resizeOrReplace: function(t, e) {
            this.resize(t, e) || this.replace(t, e)
        },
        replace: function(t, e) {
            var r = this.iCells,
                n = this._normal(t),
                i = this._areas,
                o = this.getIndx(e),
                a = i[o];
            r.removeBlock(a), r.addBlock(n), this._super(n, o), this.trigger()
        },
        resize: function(t, e) {
            var r = this._normal(t, !0),
                n = r.r1,
                i = r.c1,
                o = r.r2,
                a = r.c2,
                l = this._areas || [];
            if (!l.length) return !1;
            var s = this.getIndx(e),
                d = l[s],
                c = d.r1,
                h = d.c1,
                u = d.r2,
                f = d.c2,
                p = c === n && h === i,
                g = c === n && f === a,
                v = u === o && h === i,
                m = u === o && f === a;
            return p && g && v && m ? !0 : void 0
        },
        selectAll: function(t) {
            t = t || {};
            var e, r = t.type,
                n = this.that,
                i = n.colModel,
                o = t.all,
                a = o ? 0 : n.riOffset,
                l = o ? n.get_p_data().length : n.pdata.length,
                s = i.length - 1,
                d = a + l - 1;
            return "row" === r ? (e = {
                r1: a,
                r2: d
            }, n.Range(e).select()) : (e = {
                r1: a,
                c1: 0
            }, e.r2 = d, e.c2 = s, n.Range(e).select()), this
        },
        trigger: function() {
            var t = this.that;
            t._trigger("selectChange", null, {
                selection: this
            }), t.off("mousePQUp", e), t.off("keyUp", e), t.on("mousePQUp", e), t.on("keyUp", e)
        }
    })
}(jQuery),
function(t) {
    "use strict";
    var e = t.paramquery,
        r = {};
    r.options = {
        items: [],
        gridInstance: null
    }, t.widget("paramquery.pqToolbar", r), r = e.pqToolbar.prototype, r.refresh = function() {
        this.element.empty(), this._create()
    }, r._create = function() {
        var r, n, i = this.options,
            o = i.gridInstance,
            a = {
                button: "click",
                select: "change",
                checkbox: "change",
                textbox: "change",
                file: "change"
            },
            l = i.bootstrap,
            s = l.on,
            d = o.colModel,
            c = i.items,
            h = this.element;
        h.addClass("pq-toolbar");
        for (var u = 0, f = c.length; f > u; u++) {
            var p, g, v = c[u],
                m = v.type,
                w = v.value,
                x = v.icon,
                y = v.options || {},
                _ = v.label,
                n = v.listener,
                b = n ? [n] : v.listeners,
                b = b || [function() {}],
                I = v.cls,
                C = I ? I : "",
                C = s && "button" == m ? l.btn + " " + C : C,
                C = C ? "class='" + C + "'" : "",
                q = v.style,
                R = q ? "style='" + q + "'" : "",
                D = v.attr,
                M = D ? D : "",
                T = _ && "button" != m && "file" != m ? [C, M] : [C, M, R],
                T = T.join(" ");
            if (v.options = y, "textbox" == m) g = t([_ ? "<label " + R + ">" + _ : "", "<input type='text' " + T + ">", _ ? "</label>" : ""].join(""));
            else if ("file" == m) g = t(["<label class='btn btn-default' " + T + ">", _ || "File", "<input type='file' style='display:none;'>", "</label>"].join(""));
            else if ("textarea" == m) g = t([_ ? "<label " + R + ">" + _ : "", "<textarea " + T + "></textarea>", _ ? "</label>" : ""].join(""));
            else if ("checkbox" == m) g = t([_ ? "<label " + R + ">" : "", "<input type='checkbox' ", w ? "checked='checked' " : "", T, ">", _ ? _ + "</label>" : ""].join(""));
            else if ("separator" == m) g = t("<span class='pq-separator' " + [M, R].join(" ") + "></span>");
            else if ("button" == m) {
                var P = "";
                s && (P = x ? "<span class='glyphicon " + x + "'></span>" : ""), g = t("<button type='button' " + T + ">" + P + _ + "</button>"), t.extend(y, {
                    label: _ ? _ : !1,
                    icons: {
                        primary: s ? "" : x
                    }
                }), g.button(y)
            } else "select" == m ? ("function" == typeof y && (y = y.call(o, {
                colModel: d
            })), y = y || [], p = e.select({
                options: y,
                attr: T,
                prepend: v.prepend,
                groupIndx: v.groupIndx,
                valueIndx: v.valueIndx,
                labelIndx: v.labelIndx
            }), g = t([_ ? "<label " + R + ">" + _ : "", p, _ ? "</label>" : ""].join(""))) : "string" == typeof m ? g = t(m) : "function" == typeof m && (p = m.call(o, {
                colModel: d,
                cls: C
            }), g = t(p));
            g.appendTo(h), "checkbox" !== m && void 0 !== w && (_ ? t(g[0].children[0]).val(w) : g.val(w));
            for (var E = 0, S = b.length; S > E; E++) {
                n = b[E];
                var k = {};
                "function" == typeof n ? k[a[m]] = n : k = n;
                for (r in k) g.on(r, this._onEvent(o, k[r], v))
            }
        }
    }, r._onEvent = function(e, r, n) {
        return function(i) {
            "checkbox" == n.type ? n.value = t(i.target).prop("checked") : n.value = t(i.target).val(), r.call(e, i)
        }
    }, r._destroy = function() {
        this.element.empty().removeClass("pq-toolbar").enableSelection()
    }, r._disable = function() {
        null == this.$disable && (this.$disable = t("<div class='pq-grid-disable'></div>").css("opacity", .2).appendTo(this.element))
    }, r._enable = function() {
        this.$disable && (this.element[0].removeChild(this.$disable[0]), this.$disable = null)
    }, r._setOption = function(t, e) {
        "disabled" == t && (1 == e ? this._disable() : this._enable())
    }, pq.toolbar = function(e, r) {
        var n = t(e).pqToolbar(r),
            i = n.data("paramqueryPqToolbar") || n.data("paramquery-pqToolbar");
        return i
    }
}(jQuery),
function(t) {
    "use strict";
    var e = t.paramquery,
        r = e.pqGrid.prototype;
    r.options.trackModel = {
        on: !1,
        dirtyClass: "pq-cell-dirty"
    }, e.cUCData = function(t) {
        this.that = t, this.udata = [], this.ddata = [], this.adata = [], this.options = t.options, t.on("dataAvailable", this.onDA(this))
    }, e.cUCData.prototype = {
        add: function(t) {
            for (var e = this.that, r = this.adata, n = this.ddata, i = t.rowData, o = this.options.trackModel, a = o.dirtyClass, l = e.getRecId({
                    rowData: i
                }), s = 0, d = r.length; d > s; s++) {
                var c = r[s];
                if (null != l && c.recId == l) throw "primary key violation";
                if (c.rowData == i) throw "same data can't be added twice."
            }
            for (var s = 0, d = n.length; d > s; s++)
                if (i == n[s].rowData) return void n.splice(s, 1);
            var h = [];
            for (var u in i) h.push(u);
            e.removeClass({
                rowData: i,
                dataIndx: h,
                cls: a
            });
            var t = {
                recId: l,
                rowData: i
            };
            r.push(t)
        },
        commit: function(t) {
            var e = this.that;
            if (null == t) this.commitAddAll(), this.commitUpdateAll(), this.commitDeleteAll();
            else {
                var r = t.history,
                    n = e.options.dataModel,
                    i = [],
                    o = n.recIndx,
                    a = t.type,
                    l = t.rows;
                r = null == r ? !1 : r, "add" == a ? l ? i = this.commitAdd(l, o) : this.commitAddAll() : "update" == a ? l ? this.commitUpdate(l, o) : this.commitUpdateAll() : "delete" == a && (l ? this.commitDelete(l, o) : this.commitDeleteAll()), i.length && (e._digestData({
                    source: "commit",
                    checkEditable: !1,
                    track: !1,
                    history: r,
                    updateList: i
                }), e.refreshView())
            }
        },
        commitAdd: function(e, r) {
            var n, i, o, a, l, s, d = this.that,
                c = d.colModel,
                h = c.length,
                u = this.adata,
                f = t.inArray,
                p = u.length,
                g = d.getValueFromDataType,
                v = [],
                m = e.length,
                w = [];
            for (i = 0; m > i; i++)
                for (l = e[i], n = 0; p > n; n++)
                    if (a = u[n].rowData, s = !0, -1 == f(a, w)) {
                        for (o = 0; h > o; o++) {
                            var x = c[o],
                                y = x.dataType,
                                _ = x.dataIndx;
                            if (!x.hidden && _ != r) {
                                var b = a[_],
                                    b = g(b, y),
                                    I = l[_],
                                    I = g(I, y);
                                if (b !== I) {
                                    s = !1;
                                    break
                                }
                            }
                        }
                        if (s) {
                            var C = {},
                                q = {};
                            C[r] = l[r], q[r] = a[r], v.push({
                                rowData: a,
                                oldRow: q,
                                newRow: C
                            }), w.push(a);
                            break
                        }
                    }
            var R = [];
            for (n = 0; p > n; n++) a = u[n].rowData, -1 == f(a, w) && R.push(u[n]);
            return this.adata = R, v
        },
        commitDelete: function(t, e) {
            for (var r, n, i, o, a = this.ddata, l = a.length, s = this.udata; l-- && (r = a[l].rowData, n = r[e], i = t.length);)
                for (; i--;)
                    if (n == t[i][e]) {
                        for (t.splice(i, 1), a.splice(l, 1), o = s.length; o--;) s[o].rowData == r && s.splice(o, 1);
                        break
                    }
        },
        commitUpdate: function(e, r) {
            var n, i, o = this.that,
                a = this.options.trackModel.dirtyClass,
                l = this.udata,
                s = l.length,
                d = e.length,
                c = [];
            for (n = 0; s > n; n++) {
                var h = l[n],
                    u = h.rowData,
                    f = h.oldRow;
                if (-1 == t.inArray(u, c))
                    for (i = 0; d > i; i++) {
                        var p = e[i];
                        if (u[r] == p[r]) {
                            c.push(u);
                            for (var g in f) o.removeClass({
                                rowData: u,
                                dataIndx: g,
                                cls: a
                            })
                        }
                    }
            }
            var v = [];
            for (n = 0; s > n; n++) u = l[n].rowData, -1 == t.inArray(u, c) && v.push(l[n]);
            this.udata = v
        },
        commitAddAll: function() {
            this.adata = []
        },
        commitDeleteAll: function() {
            for (var t, e = this.ddata, r = this.udata, n = r.length, i = e.length, o = 0; n > 0 && i > o; o++) {
                for (t = e[o].rowData; n--;) r[n].rowData == t && r.splice(n, 1);
                n = r.length
            }
            e.length = 0
        },
        commitUpdateAll: function() {
            for (var t = this.that, e = this.options.trackModel.dirtyClass, r = this.udata, n = 0, i = r.length; i > n; n++) {
                var o = r[n],
                    a = o.oldRow,
                    l = o.rowData;
                for (var s in a) t.removeClass({
                    rowData: l,
                    dataIndx: s,
                    cls: e
                })
            }
            this.udata = []
        },
        "delete": function(t) {
            for (var e = this.that, r = t.rowIndx, n = t.rowIndxPage, i = e.riOffset, r = null == r ? n + i : r, n = null == n ? r - i : n, o = e.options.pageModel.type, a = "remote" == o ? n : r, l = this.adata, s = this.ddata, d = e.getRowData(t), c = 0, h = l.length; h > c; c++)
                if (l[c].rowData == d) return void l.splice(c, 1);
            s.push({
                indx: a,
                rowData: d,
                rowIndx: r
            })
        },
        getChangesValue: function(e) {
            e = e || {};
            for (var r = this.that, n = e.all, i = this.udata, o = this.adata, a = this.ddata, l = [], s = [], d = [], c = [], h = [], u = [], f = 0, p = a.length; p > f; f++) {
                var g = a[f],
                    v = g.rowData,
                    m = {};
                h.push(v);
                for (var w in v) 0 != w.indexOf("pq_") && (m[w] = v[w]);
                u.push(m)
            }
            for (var f = 0, p = i.length; p > f; f++) {
                var g = i[f],
                    x = g.oldRow,
                    v = g.rowData;
                if (-1 == t.inArray(v, h) && -1 == t.inArray(v, l)) {
                    var m = {};
                    if (n !== !1)
                        for (var w in v) 0 != w.indexOf("pq_") && (m[w] = v[w]);
                    else {
                        for (var w in x) m[w] = v[w];
                        m[r.options.dataModel.recIndx] = g.recId
                    }
                    l.push(v), s.push(m), d.push(x)
                }
            }
            for (var f = 0, p = o.length; p > f; f++) {
                var g = o[f],
                    v = g.rowData,
                    m = {};
                for (var w in v) 0 != w.indexOf("pq_") && (m[w] = v[w]);
                c.push(m)
            }
            return {
                updateList: s,
                addList: c,
                deleteList: u,
                oldList: d
            }
        },
        getChanges: function() {
            for (var e = (this.that, this.udata), r = this.adata, n = this.ddata, i = t.inArray, o = [], a = [], l = [], s = [], d = 0, c = n.length; c > d; d++) {
                var h = n[d],
                    u = h.rowData;
                s.push(u)
            }
            for (var d = 0, c = e.length; c > d; d++) {
                var h = e[d],
                    f = h.oldRow,
                    u = h.rowData; - 1 == i(u, s) && -1 == i(u, o) && (o.push(u), a.push(f))
            }
            for (var d = 0, c = r.length; c > d; d++) {
                var h = r[d],
                    u = h.rowData;
                l.push(u)
            }
            return {
                updateList: o,
                addList: l,
                deleteList: s,
                oldList: a
            }
        },
        getChangesRaw: function() {
            var t = (this.that, this.udata),
                e = this.adata,
                r = this.ddata,
                n = {
                    updateList: [],
                    addList: [],
                    deleteList: []
                };
            return n.updateList = t, n.addList = e, n.deleteList = r, n
        },
        isDirty: function(t) {
            var e = this.that,
                r = this.udata,
                n = this.adata,
                i = this.ddata,
                o = !1,
                a = e.getRowData(t);
            if (a)
                for (var l = 0; l < r.length; l++) {
                    var s = r[l];
                    if (a == s.rowData) {
                        o = !0;
                        break
                    }
                } else(r.length || n.length || i.length) && (o = !0);
            return o
        },
        onDA: function(t) {
            return function(e, r) {
                "filter" != r.source && (t.udata = [], t.ddata = [], t.adata = [])
            }
        },
        rollbackAdd: function(t, e) {
            for (var r = this.adata, n = [], i = (t.type, 0), o = r.length; o > i; i++) {
                var a = r[i],
                    l = a.rowData;
                n.push({
                    type: "delete",
                    rowData: l
                })
            }
            return this.adata = [], n
        },
        rollbackDelete: function(t, e) {
            for (var r = this.ddata, n = [], i = (t.type, r.length - 1); i >= 0; i--) {
                var o = r[i],
                    a = (o.indx, o.rowIndx),
                    l = o.rowData;
                n.push({
                    type: "add",
                    rowIndx: a,
                    newRow: l
                })
            }
            return this.ddata = [], n
        },
        rollbackUpdate: function(t, e) {
            for (var r = this.that, n = this.options.trackModel.dirtyClass, i = this.udata, o = [], a = 0, l = i.length; l > a; a++) {
                var s = i[a],
                    d = s.recId,
                    c = s.rowData,
                    h = {},
                    u = s.oldRow;
                if (null != d) {
                    var f = [];
                    for (var p in u) h[p] = c[p], f.push(p);
                    r.removeClass({
                        rowData: c,
                        dataIndx: f,
                        cls: n,
                        refresh: !1
                    }), o.push({
                        type: "update",
                        rowData: c,
                        newRow: u,
                        oldRow: h
                    })
                }
            }
            return this.udata = [], o
        },
        rollback: function(t) {
            var e = this.that,
                r = e.options.dataModel,
                n = e.options.pageModel,
                i = t && null != t.refresh ? t.refresh : !0,
                o = t && null != t.type ? t.type : null,
                a = [],
                l = [],
                s = [],
                d = r.data;
            null != o && "update" != o || (l = this.rollbackUpdate(n, d)), null != o && "delete" != o || (a = this.rollbackDelete(n, d)), null != o && "add" != o || (s = this.rollbackAdd(n, d)), e._digestData({
                history: !1,
                allowInvalid: !0,
                checkEditable: !1,
                source: "rollback",
                track: !1,
                addList: a,
                updateList: l,
                deleteList: s
            }), i && e.refreshView()
        },
        update: function(e) {
            var r = this.that,
                n = this.options.trackModel,
                i = n.dirtyClass,
                o = e.rowData || r.getRowData(e),
                a = r.getRecId({
                    rowData: o
                }),
                l = e.dataIndx,
                s = e.refresh,
                d = r.columns,
                c = r.getValueFromDataType,
                h = e.row,
                u = this.udata,
                f = u.slice(0),
                p = !1;
            if (null != a) {
                for (var g = 0, v = u.length; v > g; g++) {
                    var m = u[g],
                        w = m.oldRow;
                    if (m.rowData == o) {
                        p = !0;
                        for (var l in h) {
                            var x = d[l],
                                y = x.dataType,
                                _ = h[l],
                                _ = c(_, y),
                                b = w[l],
                                b = c(b, y);
                            if (w.hasOwnProperty(l) && b === _) {
                                var I = {
                                    rowData: o,
                                    dataIndx: l,
                                    refresh: s,
                                    cls: i
                                };
                                r.removeClass(I), delete w[l]
                            } else {
                                var I = {
                                    rowData: o,
                                    dataIndx: l,
                                    refresh: s,
                                    cls: i
                                };
                                r.addClass(I), w.hasOwnProperty(l) || (w[l] = o[l])
                            }
                        }
                        t.isEmptyObject(w) && f.splice(g, 1);
                        break
                    }
                }
                if (!p) {
                    var w = {};
                    for (var l in h) {
                        w[l] = o[l];
                        var I = {
                            rowData: o,
                            dataIndx: l,
                            refresh: s,
                            cls: i
                        };
                        r.addClass(I)
                    }
                    var I = {
                        rowData: o,
                        recId: a,
                        oldRow: w
                    };
                    f.push(I)
                }
                this.udata = f
            }
        }
    }, r.getChanges = function(t) {
        if (this.blurEditor({
                force: !0
            }), t) {
            var e = t.format;
            if (e) {
                if ("byVal" == e) return this.iUCData.getChangesValue(t);
                if ("raw" == e) return this.iUCData.getChangesRaw()
            }
        }
        return this.iUCData.getChanges()
    }, r.rollback = function(t) {
        this.blurEditor({
            force: !0
        }), this.iUCData.rollback(t)
    }, r.isDirty = function(t) {
        return this.iUCData.isDirty(t)
    }, r.commit = function(t) {
        this.iUCData.commit(t)
    }, r.updateRow = function(t) {
        var e, r = this,
            n = t.rowList || [{
                rowIndx: t.rowIndx,
                newRow: t.newRow || t.row,
                rowData: t.rowData,
                rowIndxPage: t.rowIndxPage
            }],
            i = [];
        if (r.normalizeList(n).forEach(function(t) {
                var e, r = t.newRow,
                    n = t.rowData,
                    o = t.oldRow = {};
                if (n) {
                    for (e in r) o[e] = n[e];
                    i.push(t)
                }
            }), i.length) {
            var o = {
                    source: t.source || "update",
                    history: t.history,
                    checkEditable: t.checkEditable,
                    track: t.track,
                    allowInvalid: t.allowInvalid,
                    updateList: i
                },
                a = this._digestData(o);
            if (a === !1) return !1;
            t.refresh !== !1 && (i = o.updateList, e = i.length, e > 1 ? r.refresh() : 1 == e && r.refreshRow({
                rowIndx: i[0].rowIndx
            }))
        }
    }, r.getRecId = function(t) {
        var e = this,
            r = e.options.dataModel;
        t.dataIndx = r.recIndx;
        var n = e.getCellData(t);
        return null == n ? null : n
    }, r.getCellData = function(t) {
        var e = t.rowData || this.getRowData(t),
            r = t.dataIndx;
        return e ? e[r] : null
    }, r.getRowData = function(t) {
        if (!t) return null;
        var e, r = t.rowData;
        if (null != r) return r;
        if (e = t.recId, null == e) {
            var n = t.rowIndx,
                n = null != n ? n : t.rowIndxPage + this.riOffset,
                i = this.get_p_data(),
                o = i[n];
            return o
        }
        for (var a = this.options, l = a.dataModel, s = l.recIndx, d = l.data, c = 0, h = d.length; h > c; c++) {
            var o = d[c];
            if (o[s] == e) return o
        }
        return null
    }, r.deleteRow = function(t) {
        var e = this,
            r = e.normalizeList(t.rowList || [{
                rowIndx: t.rowIndx,
                rowIndxPage: t.rowIndxPage
            }]);
        return r.length ? (this._digestData({
            source: t.source || "delete",
            history: t.history,
            track: t.track,
            deleteList: r
        }), void(t.refresh !== !1 && e.refreshView())) : !1
    }, r.addRow = function(t) {
        var e, r, n = this,
            i = n.riOffset,
            o = n.options.dataModel,
            a = o.data = o.data || [];
        return t.rowData && (t.newRow = t.rowData), null != t.rowIndxPage && (t.rowIndx = t.rowIndxPage + i), r = t.rowList || [{
            rowIndx: t.rowIndx,
            newRow: t.newRow
        }], r.length && this._digestData({
            source: t.source || "add",
            history: t.history,
            track: t.track,
            checkEditable: t.checkEditable,
            addList: r
        }) !== !1 ? (t.refresh !== !1 && this.refreshView(), e = r[0].rowIndx, null == e ? a.length - 1 : e) : !1
    }
}(jQuery),
function() {
    "use strict";
    window.requestAnimationFrame = window.requestAnimationFrame || window.webkitRequestAnimationFrame || window.mozRequestAnimationFrame || function(t) {
        return setTimeout(t, 10)
    }, window.cancelAnimationFrame = window.cancelAnimationFrame || window.webkitCancelAnimationFrame || window.mozCancelAnimationFrame || function(t) {
        clearTimeout(t)
    }
}(),
function(t) {
    "use strict";

    function e(t) {
        this.that = t;
        var e = this;
        e.scrollTop = 0, e.scrollLeft = 0, e.borderRight = 0, e.borderRightExtra = 0, e.borderTop = 0, e.borderTopExtra = 0, e.borderLeft = 0, e.borderLeftExtra = 0, e.borderBottom = 0, e.borderBottomExtra = 0, e.maxBorder = 5e3, e.rowht = t.options.rowHeight, e.colwd = 60, t.on("contMouseDown", function(t, r) {
            return e._onContMouseDown(t, r)
        }).on("mouseDrag", function(t, r) {
            return e._onMouseDrag(t, r)
        }).on("mouseStop", function(t, r) {
            return e._onMouseStop(t, r)
        }).on("mousePQUp", function(t, r) {
            return e._onMousePQUp(t, r)
        }).on("cellClick", function(t, r) {
            return e._onCellClick(t, r)
        }).on("cellMouseDown", function(t, r) {
            return e._onCellMouseDown(t, r)
        }).on("cellMouseEnter", function(t, r) {
            return e._onCellMouseEnter(t, r)
        }).on("refresh refreshRow", function() {
            e.setTimer(function() {
                t.element && t.focus()
            }, 300)
        })
    }
    var r = t.paramquery;
    r.cMouseSelection = e;
    var n = e.prototype = new r.cClass;
    n.inViewPort = function(t) {
        var e = this.that,
            r = e.iRefresh,
            n = r.getEContHt(),
            i = r.getEContWd() + 1,
            o = t[0],
            a = this,
            l = a.marginTop,
            s = a.scrollLeft;
        if (n >= o.offsetTop + o.offsetHeight + l) {
            if ("TD" !== o.nodeName.toUpperCase()) return !0;
            if (i >= o.offsetLeft + o.offsetWidth + s) return !0
        }
    }, n._onCellMouseDown = function(t, e) {
        var r = this.that,
            n = e.rowIndx,
            i = r.iSelection,
            o = e.colIndx,
            a = r.options.selectionModel,
            l = a.type,
            s = a.mode,
            d = i.addressLast();
        if ("cell" !== l) return void r.focus(e);
        if (null != o) {
            if (-1 == o) {
                if (!a.row) return;
                o = void 0
            }
            if (t.shiftKey && "single" !== s && d && null != d.firstR) {
                var c = d.firstR,
                    h = d.firstC;
                i.resizeOrReplace({
                    r1: c,
                    c1: h,
                    r2: n,
                    c2: o,
                    firstR: c,
                    firstC: h
                })
            } else(t.ctrlKey || t.metaKey) && "single" !== s ? (this.mousedown = {
                r1: n,
                c1: o
            }, r.Selection().add({
                r1: n,
                c1: o,
                firstR: n,
                firstC: o
            })) : (this.mousedown = {
                r1: n,
                c1: o
            }, i.clearOther({
                r1: n,
                c1: o
            }), i.resizeOrReplace({
                r1: n,
                c1: o,
                firstR: n,
                firstC: o
            }));
            return r.focus(e), !0
        }
    }, n._onCellMouseEnter = function(t, e) {
        var r = this.that,
            n = r.options.selectionModel,
            i = n.type,
            o = this.mousedown,
            a = n.mode;
        if (o && "single" !== a) {
            if ("cell" === i) {
                var l = o.r1,
                    s = o.c1,
                    d = e.rowIndx,
                    c = e.colIndx,
                    h = r.Selection();
                r.scrollCell({
                    rowIndx: d,
                    colIndx: c
                }), h.resizeOrReplace({
                    r1: l,
                    c1: s,
                    r2: d,
                    c2: c
                })
            }
            r.focus(e)
        }
    }, n._onCellClick = function(t, e) {
        var r, n = this.that,
            i = n.options.selectionModel,
            o = "single" == i.mode,
            a = i.toggle,
            l = n.iRows;
        if ("row" == i.type) {
            if (!i.row && -1 == e.colIndx) return;
            r = l.isSelected(e), o && !r || a || !t.metaKey && !t.ctrlKey ? !o && t.shiftKey ? l.extend(e) : !o || r && a ? (e.isFirst = !0, l[a ? "toggle" : "add"](e)) : r || (l.removeAll(), l.add(e)) : (e.isFirst = !0, l.toggle(e))
        }
    }, n._onContMouseDown = function(t) {
        var e = this.that,
            r = e.options.swipeModel,
            n = r.on;
        return n && (this._stopSwipe(!0), this.swipedown = {
            x: t.pageX,
            y: t.pageY
        }), !0
    }, n._onMousePQUp = function() {
        this.mousedown = null
    }, n._stopSwipe = function(t) {
        var e = this;
        t && (e.swipedown = null, e.swipedownPrev = null), window.clearInterval(e.intID), window.cancelAnimationFrame(e.intID), e.intID = null
    }, n._onMouseStop = function(t) {
        var e = this,
            r = this.that;
        if (this.swipedownPrev) {
            var n = r.options.swipeModel,
                i = this.swipedownPrev,
                o = i.ts,
                a = (new Date).getTime(),
                l = a - o,
                s = i.x,
                d = i.y,
                c = t.pageX,
                h = t.pageY,
                u = c - s,
                f = h - d,
                p = Math.sqrt(u * u + f * f),
                g = p / l;
            if (g > n.ratio) {
                var v = 0,
                    m = n.repeat;
                e._stopSwipe();
                var w = function() {
                    v += n.speed, m--;
                    var t = c + v * u / l,
                        r = h + v * f / l;
                    e._onMouseDrag({
                        pageX: t,
                        pageY: r
                    }), m > 0 ? e.intID = window.requestAnimationFrame(w) : e._stopSwipe(!0)
                };
                w()
            } else e.swipedown = null, e.swipedownPrev = null
        }
    }, n._onMouseDrag = function(t) {
        var e = this.that,
            r = e.options;
        if (this.swipedown) {
            var n = this.swipedown,
                i = n.x,
                o = n.y,
                a = t.pageX,
                l = t.pageY;
            this.swipedownPrev = {
                x: i,
                y: o,
                ts: (new Date).getTime()
            }, r.virtualY || (this.scrollVertSmooth(o, l), this.syncScrollBarVert()), r.virtualX || (this.scrollHorSmooth(i, a), this.syncScrollBarHor()), n.x = a, n.y = l
        }
        return !0
    }, n.updateTableY = function(t) {
        if (0 === t) return !1;
        var e = this.that,
            r = this.getTableForVertScroll(),
            n = e.iRefresh.getEContHt();
        if (!r || !r.length) return !1;
        var i, o = r.data("offsetHeight") - 1,
            a = this.scrollTop - t;
        return i = 0 > a ? 0 : 0 > t && n - o + a > 0 ? o - n : a, this.setScrollTop(i, r, n), !0
    }, n.setScrollTop = function(t, e, r) {
        t >= 0 && (t = Math.round(t), this.scrollTop = t, e.parent("div").scrollTop(t))
    }, n.getScrollLeft = function() {
        return this.scrollLeft
    }, n.getScrollTop = function() {
        return this.scrollTop
    }, n.setScrollLeft = function(e, r, n, i) {
        if (e >= 0) {
            e = Math.round(e), this.scrollLeft = e;
            var o = n ? n.parent() : t();
            o = o.add(r ? r.parent("div") : t()), o.scrollLeft(e)
        }
    }, n.scrollVertSmooth = function(t, e) {
        t !== e && this.updateTableY(e - t)
    }, n.scrollHorSmooth = function(t, e) {
        if (t !== e) {
            var r = this.that,
                n = r.options,
                i = e - t,
                o = this.getTableForHorScroll(),
                a = this.getTableHeaderForHorScroll(),
                l = r.iRefresh.getEContWd();
            if (o || a) {
                var s, d = o ? o : a,
                    c = n.virtualX ? this.getScrollWidth(d) : d.data("scrollWidth"),
                    h = this.scrollLeft - i;
                s = 0 > h ? 0 : 0 > c - l - h ? c - l : h, this.setScrollLeft(s, o, a, l)
            }
        }
    }, n.syncViewWithScrollBarVert = function(t) {
        if (null != t) {
            var e = this.that,
                r = this.getTableForVertScroll();
            if (r && r.length) {
                var n = e.options;
                n.editModel.indices && e.blurEditor({
                    force: !0
                });
                var i = r.data("offsetHeight"),
                    o = e.iRefresh.getEContHt(),
                    a = i - o,
                    l = a * t;
                (l || 0 === l) && (0 > l && (l = 0), this.setScrollTop(l, r, o))
            }
        }
    }, n.syncViewWithScrollBarHor = function(t) {
        if (null != t) {
            var e = this.that,
                r = this.getTableForHorScroll(),
                n = this.getTableHeaderForHorScroll();
            if (r || n) {
                var i = e.options;
                i.editModel.indices && e.blurEditor({
                    force: !0
                });
                var o = r ? r : n,
                    a = i.virtualX ? this.getScrollWidth(o) : o.data("scrollWidth"),
                    l = e.iRefresh.getEContWd(),
                    s = a - l,
                    d = s * t;
                a && l && (0 > d && (d = 0), this.setScrollLeft(d, r, n, l))
            }
        }
    }, n.resetMargins = function() {
        this.scrollLeft = 0, this.scrollTop = 0
    }, n.syncHeaderViewWithScrollBarHor = function(t) {
        if (null != t) {
            var e = this.that,
                r = this.getTableHeaderForHorScroll();
            if (r) {
                var n = e.options,
                    i = n.freezeCols;
                n.editModel.indices && e.blurEditor({
                    force: !0
                });
                var o = r,
                    a = o.data("scrollWidth"),
                    l = e.iRefresh.getEContWd(),
                    s = e.calcWidthCols(i, t + i);
                a && l && (0 > s && (s = 0), r.css("marginLeft", -s))
            }
        }
    }, n.syncScrollBarVert = function() {
        var t = this.that,
            e = this.getTableForVertScroll();
        if (e && e.length) {
            var r = e.data("offsetHeight"),
                n = t.iRefresh.getEContHt(),
                i = r - n,
                o = Math.abs(this.scrollTop),
                a = i ? o / i : 0;
            a > 1 ? a = 1 : 0 > a && (a = 0), a >= 0 && 1 >= a && t.vscroll.widget().hasClass("pq-sb-vert") && t.vscroll.option("ratio", a)
        }
    }, n.syncScrollBarHor = function() {
        var t = this.that,
            e = t.options,
            r = this.getTableForHorScroll(),
            n = this.getTableHeaderForHorScroll();
        if (r || n) {
            var i = r ? r : n,
                o = e.virtualX ? this.getScrollWidth(i) : i.data("scrollWidth"),
                a = t.iRefresh.getEContWd(),
                l = o - a,
                s = this.scrollLeft,
                d = s / l;
            d >= 0 && 1 >= d && t.hscroll.widget().hasClass("pq-sb-horiz") && t.hscroll.option("ratio", d)
        }
    }, n.getTableForVertScroll = function() {
        var e = this.that,
            r = e.pqpanes,
            n = e.$tbl;
        if (n && n.length) return r.h && r.v ? n = t([n[2], n[3]]) : r.v ? n = t([n[0], n[1]]) : r.h && (n = t(n[1])), n
    }, n.getTableForHorScroll = function() {
        var e = this.that,
            r = e.pqpanes,
            n = [],
            i = e.$tbl;
        if (i && i.length) {
            if (r.h && r.v ? n.push(i[1], i[3]) : r.v ? n.push(i[1]) : r.h ? n.push(i[0], i[1]) : n.push(i[0]), e.tables.length) {
                var o = e.tables[0].$tbl;
                r.v ? n.push(o[1]) : n.push(o[0])
            }
            return t(n)
        }
    }, n.getTableHeaderForHorScroll = function() {
        var e = this.that,
            r = e.pqpanes,
            n = e.$tbl_header;
        if (n && n.length) return n = t(r.v ? n[1] : n[0]), n.parent()
    }, n.scrollRowNonVirtual = function(t) {
        var e = this.that,
            r = e.options,
            n = e.normalize(t),
            i = n.rowIndxPage,
            o = e.iRefresh.getEContHt(),
            a = 1 * r.freezeRows;
        if (!(a > i)) {
            var l = e.get$Tbl(i),
                s = e.getRow({
                    rowIndxPage: i
                }),
                d = s[0];
            if (d) {
                var c, h = l[0].offsetTop + 1,
                    u = d.offsetHeight,
                    f = this.getScrollTop(),
                    p = d.offsetTop - 1,
                    g = -1;
                0 > h + p - f ? (c = h + p + g, c = 0 > c ? 0 : c, this.setScrollTop(c, l, o), this.syncScrollBarVert()) : p + u - f > o && (c = u + p - o, this.setScrollTop(c, l, o), this.syncScrollBarVert())
            }
        }
    }, n.scrollColumnNonVirtual = function(t) {
        var e = this.that,
            r = t.colIndx,
            n = null == r ? e.getColIndx({
                dataIndx: t.dataIndx
            }) : r,
            i = e.options.freezeCols;
        if (!(i > n)) {
            var o, a = e._calcRightEdgeCol(n).width,
                l = e._calcRightEdgeCol(n - 1).width,
                s = e._calcRightEdgeCol(i - 1).width,
                d = this.getTableForHorScroll(),
                c = this.getTableHeaderForHorScroll(),
                h = e.iRefresh.getEContWd(),
                u = this.scrollLeft;
            a - u > h ? (o = a - h, this.setScrollLeft(o, d, c, h), this.syncScrollBarHor()) : u > l - s && (o = l - s, this.setScrollLeft(o, d, c, h), this.syncScrollBarHor())
        }
    }
}(jQuery),
function(t) {
    "use strict";
    var e = null,
        r = !1,
        n = "pq-grid-excel",
        i = t.paramquery,
        o = i.pqGrid.prototype;
    t.extend(o.options, {
        copyModel: {
            on: !0,
            render: !1,
            header: !0,
            zIndex: 1e4
        },
        cutModel: {
            on: !0
        },
        pasteModel: {
            on: !0,
            compare: "byVal",
            select: !0,
            validate: !0,
            allowInvalid: !0,
            type: "replace"
        }
    }), t.extend(o, {
        _setGlobalStr: function(t) {
            a.clip = t
        },
        copy: function() {
            return this.iSelection.copy()
        },
        cut: function() {
            return this.iSelection.copy({
                cut: !0,
                source: "cut"
            })
        },
        paste: function(t) {
            e = new a(this), e.paste(t), e = null
        },
        clear: function() {
            var t = this.iSelection;
            t.address().length ? t.clear() : this.iRows.toRange().clear()
        }
    });
    var a = i.cExcel = function(t, e) {
        this.that = t
    };
    a.clip = "", a.prototype = {
        createClipBoard: function() {
            var e = t("#pq-grid-excel-div"),
                r = this.that.options.copyModel,
                i = t("#" + n);
            0 == i.length && (e = t("<div id='pq-grid-excel-div'  style='position:fixed;top:20px;left:20px;height:1px;width:1px;overflow:hidden;z-index:" + r.zIndex + ";'/>").appendTo(document.body), i = t("<textarea id='" + n + "' autocomplete='off' spellcheck='false' style='overflow:hidden;height:10000px;width:10000px;opacity:0' />").appendTo(e), i.css({
                opacity: 0
            })), i.on("focusin", function(t) {
                t.stopPropagation()
            }), i.select()
        },
        destroyClipBoard: function() {
            this.clearClipBoard();
            var e = this.that,
                r = t(window).scrollTop(),
                n = t(window).scrollLeft();
            e.focus();
            var i = t(window).scrollTop(),
                o = t(window).scrollLeft();
            r == i && n == o || window.scrollTo(n, r)
        },
        clearClipBoard: function() {
            var e = t("#" + n);
            e.val("")
        },
        copy: function(t) {
            var e = this.that,
                r = e.iSelection;
            return r.address().length ? r.copy(t) : void e.iRows.toRange().copy(t)
        },
        paste: function(e) {
            e = e || {};
            var r = this.that,
                n = e.dest,
                i = e.clip,
                o = i ? i.length ? i.val() : "" : a.clip;
            o = o.replace(/\n$/, "");
            var l = o.split("\n"),
                s = l.length,
                d = r.colModel,
                c = r.options,
                h = r.readCell,
                u = c.pasteModel,
                f = "row",
                p = !1,
                g = d.length;
            if (u.on && 0 != o.length && 0 != s) {
                for (var v = 0; s > v; v++) l[v] = l[v].split("	");
                var m, w, x, y, _ = u.type,
                    b = n ? r.Range(n) : r.Selection(),
                    I = b.address(),
                    C = I.length ? I : r.iRows.toRange().address(),
                    q = C[0],
                    R = {
                        rows: l,
                        areas: [q]
                    };
                if (r._trigger("beforePaste", null, R) === !1) return !1;
                q && r.getRowData({
                    rowIndx: q.r1
                }) ? (f = "row" == q.type ? "row" : "cell", m = q.r1, x = q.r2, w = q.c1, y = q.c2) : (f = "cell", m = 0, x = 0, w = 0, y = 0);
                var D, M;
                "replace" == _ ? (D = m, M = s > x - m + 1 ? "extend" : "repeat") : "append" == _ ? (D = x + 1, M = "extend") : "prepend" == _ && (D = m, M = "extend");
                var T, P, E, S = "extend" == M ? s : x - m + 1,
                    k = 0,
                    H = [],
                    F = [],
                    $ = 0;
                for (v = 0; S > v; v++) {
                    var A = l[k],
                        V = v + D,
                        L = "replace" == _ ? r.getRowData({
                            rowIndx: V
                        }) : null,
                        z = L ? {} : null,
                        N = {};
                    void 0 === A && "repeat" === M && (k = 0, A = l[k]), k++;
                    var O = A,
                        W = O.length;
                    if (!P)
                        if ("cell" == f) {
                            if (T = W > y - w + 1 ? "extend" : "repeat", P = "extend" == T ? W : y - w + 1, isNaN(P)) throw "lenH NaN. assert failed.";
                            P + w > g && (P = g - w)
                        } else P = g, w = 0;
                    var U = 0,
                        j = 0,
                        B = 0;
                    for (E = P, j = 0; E > j; j++) {
                        U >= W && (U = 0);
                        var G = j + w,
                            K = d[G],
                            X = O[U],
                            Q = K.dataIndx;
                        K.copy !== !1 ? (U++, N[Q] = X, z && (z[Q] = h(L, K))) : (B++, "extend" == T && g > E + w && E++)
                    }
                    0 == t.isEmptyObject(N) && (null == L ? (p = !0, H.push({
                        newRow: N,
                        rowIndx: V
                    })) : F.push({
                        newRow: N,
                        rowIndx: V,
                        rowData: L,
                        oldRow: z
                    }), $++)
                }
                var Y = {
                    addList: H,
                    updateList: F,
                    source: "paste",
                    allowInvalid: u.allowInvalid,
                    validate: u.validate
                };
                r._digestData(Y), r[p ? "refreshView" : "refresh"](), u.select && r.Range({
                    r1: D,
                    c1: w,
                    r2: D + $ - 1,
                    c2: "extend" == T ? w + P - 1 + B : y
                }).select(), r._trigger("paste", null, R)
            }
        }
    }, t(document).unbind(".pqExcel").bind("keydown.pqExcel", function(i) {
        if (i.ctrlKey || i.metaKey) {
            var o = t(i.target);
            if (!(o.hasClass("pq-grid-row") || o.hasClass("pq-grid-cell") || o.is("#" + n) || o.hasClass("pq-grid-cont"))) return;
            var s, d = o.closest(".pq-grid");
            if (e || o.length && d.length) {
                if (!e) {
                    try {
                        if (s = d.pqGrid("instance"), s.option("selectionModel.native")) return !0
                    } catch (c) {
                        return !0
                    }
                    e = new a(s, o), e.createClipBoard()
                }
                if ("67" == i.keyCode || "99" == i.keyCode) e.copy({
                    clip: t("#" + n)
                });
                else if ("88" == i.keyCode) e.copy({
                    cut: !0,
                    clip: t("#" + n)
                });
                else if ("86" == i.keyCode || "118" == i.keyCode) r = !0, e.clearClipBoard(), window.setTimeout(function() {
                    e && (e.paste({
                        clip: t("#" + n)
                    }), e.destroyClipBoard(), e = null), r = !1
                }, 0);
                else {
                    var h = t("#" + n);
                    if (h.length) {
                        var u = document.activeElement;
                        u == h[0] && e.that.onKeyPressDown(i)
                    }
                }
            }
        } else {
            var f = i.keyCode,
                p = t.ui.keyCode,
                g = f == p.UP || f == p.DOWN || f == p.LEFT || f == p.RIGHT || f == p.PAGE_UP || f == p.PAGE_DOWN;
            if (g) {
                if (l) return !1;
                o = t(i.target), (o.hasClass("pq-grid-row") || o.hasClass("pq-grid-cell")) && (l = !0)
            }
        }
    }).bind("keyup.pqExcel", function(n) {
        var i = n.keyCode;
        if (r || !e || n.ctrlKey || n.metaKey || -1 == t.inArray(i, [17, 91, 93, 224]) || (e.destroyClipBoard(), e = null), l) {
            var o = t(n.target);
            o.hasClass("pq-grid-row") || o.hasClass("pq-grid-cell") || (l = !1)
        }
    });
    var l = !1
}(jQuery),
function(t) {
    "use strict";
    var e = t.paramquery,
        r = e.pqGrid.prototype.options,
        n = {
            on: !0,
            checkEditable: !0,
            checkEditableAdd: !1,
            allowInvalid: !0
        };
    r.historyModel = r.historyModel || n;
    var i = e.cHistory = function(t) {
        var e = this;
        this.that = t, this.options = t.options, this.records = [], this.counter = 0, this.id = 0, t.on("keyDown", function(t, r) {
            return e.onKeyDown(t, r)
        }).on("dataAvailable", function(t, r) {
            "filter" != r.source && e.reset()
        })
    };
    i.prototype = {
        onKeyDown: function(t, e) {
            var r = {
                    z: "90",
                    y: "89",
                    c: "67",
                    v: "86"
                },
                n = t.ctrlKey || t.metaKey;
            return n && t.keyCode == r.z ? (this.undo(), !1) : n && t.keyCode == r.y ? (this.redo(), !1) : void 0
        },
        resetUndo: function() {
            if (0 == this.counter) return !1;
            this.counter = 0;
            var t = this.that;
            t._trigger("history", null, {
                type: "resetUndo",
                num_undo: 0,
                num_redo: this.records.length - this.counter,
                canUndo: !1,
                canRedo: !0
            })
        },
        reset: function() {
            if (0 == this.counter && 0 == this.records.length) return !1;
            this.records = [], this.counter = 0, this.id = 0;
            var t = this.that;
            t._trigger("history", null, {
                num_undo: 0,
                num_redo: 0,
                type: "reset",
                canUndo: !1,
                canRedo: !1
            })
        },
        increment: function() {
            var t = this.records,
                e = t.length;
            if (e) {
                var r = t[e - 1].id;
                this.id = r + 1
            } else this.id = 0
        },
        push: function(e) {
            var r = this.canRedo(),
                n = this.records,
                i = this.counter;
            n.length > i && n.splice(i, n.length - i), n[i] = t.extend({
                id: this.id
            }, e), this.counter++;
            var o, a, l = this.that;
            1 == this.counter && (o = !0), r && this.counter == n.length && (a = !1), l._trigger("history", null, {
                type: "add",
                canUndo: o,
                canRedo: a,
                num_undo: this.counter,
                num_redo: 0
            })
        },
        canUndo: function() {
            return this.counter > 0
        },
        canRedo: function() {
            return this.counter < this.records.length
        },
        processCol: function(t, e) {
            var r = this.that;
            if (t.length) {
                var n = "add" == t.type,
                    n = e ? n : !n;
                r[n ? "addColumn" : "deleteColumn"]({
                    colList: t,
                    history: !1
                })
            }
        },
        undo: function() {
            var t = this.canRedo(),
                e = this.that,
                r = this.options.historyModel,
                n = this.records;
            if (!(this.counter > 0)) return !1;
            this.counter--;
            var i, o, a = this.counter,
                l = n[a],
                s = l.colList || [],
                d = (l.id, l.updateList.map(function(t) {
                    return {
                        rowIndx: e.getRowIndx({
                            rowData: t.rowData
                        }).rowIndx,
                        rowData: t.rowData,
                        oldRow: t.newRow,
                        newRow: t.oldRow
                    }
                })),
                c = l.addList.map(function(t) {
                    return {
                        rowData: t.newRow
                    }
                }),
                h = l.deleteList.map(function(t) {
                    return {
                        newRow: t.rowData,
                        rowIndx: t.rowIndx
                    }
                });
            if (s.length) this.processCol(s);
            else {
                e._digestData({
                    history: !1,
                    source: "undo",
                    checkEditable: r.checkEditable,
                    checkEditableAdd: r.checkEditableAdd,
                    allowInvalid: r.allowInvalid,
                    addList: h,
                    updateList: d,
                    deleteList: c
                });
                e[d.length || c.length ? "refreshView" : "refresh"]({
                    source: "undo"
                })
            }
            return t === !1 && (i = !0), 0 == this.counter && (o = !1), e._trigger("history", null, {
                canUndo: o,
                canRedo: i,
                type: "undo",
                num_undo: this.counter,
                num_redo: this.records.length - this.counter
            }), !0
        },
        redo: function() {
            var t = this.canUndo(),
                e = this.that,
                r = this.options.historyModel,
                n = this.counter,
                i = this.records;
            if (n == i.length) return !1;
            var o = i[n],
                a = o.colList || [],
                l = (o.id, o.updateList.map(function(t) {
                    return {
                        rowIndx: e.getRowIndx({
                            rowData: t.rowData
                        }).rowIndx,
                        rowData: t.rowData,
                        newRow: t.newRow,
                        oldRow: t.oldRow
                    }
                })),
                s = o.deleteList.map(function(t) {
                    return {
                        rowData: t.rowData
                    }
                }),
                d = o.addList.map(function(t) {
                    return {
                        newRow: t.newRow,
                        rowIndx: t.rowIndx
                    }
                });
            if (a.length) this.processCol(a, !0);
            else {
                e._digestData({
                    history: !1,
                    source: "redo",
                    checkEditable: r.checkEditable,
                    checkEditableAdd: r.checkEditableAdd,
                    allowInvalid: r.allowInvalid,
                    addList: d,
                    updateList: l,
                    deleteList: s
                });
                e[l.length || s.length ? "refreshView" : "refresh"]({
                    source: "redo"
                })
            }
            this.counter < i.length && this.counter++;
            var c, h;
            return 0 == t && (c = !0), this.counter == this.records.length && (h = !1), e._trigger("history", null, {
                canUndo: c,
                canRedo: h,
                type: "redo",
                num_undo: this.counter,
                num_redo: this.records.length - this.counter
            }), !0
        }
    };
    var o = e.pqGrid.prototype;
    o.history = function(t) {
        var e = t.method;
        return this.iHistory[e](t)
    }, o.History = function() {
        return this.iHistory
    }
}(jQuery),
function(t) {
    "use strict";
    var e = t.paramquery;
    e.filter = function() {
        var t = {
            begin: {
                text: "Begins With",
                TR: !0,
                string: !0
            },
            between: {
                text: "Between",
                TR: !0,
                string: !0,
                date: !0,
                number: !0
            },
            notbegin: {
                text: "Does not begin with",
                TR: !0,
                string: !0
            },
            contain: {
                text: "Contains",
                TR: !0,
                string: !0
            },
            notcontain: {
                text: "Does not contain",
                TR: !0,
                string: !0
            },
            equal: {
                text: "Equal To",
                TR: !0,
                string: !0,
                bool: !0
            },
            notequal: {
                text: "Not Equal To",
                TR: !0,
                string: !0
            },
            empty: {
                text: "Empty",
                TR: !1,
                string: !0,
                bool: !0
            },
            notempty: {
                text: "Not Empty",
                TR: !1,
                string: !0,
                bool: !0
            },
            end: {
                text: "Ends With",
                TR: !0,
                string: !0
            },
            notend: {
                text: "Does not end with",
                TR: !0,
                string: !0
            },
            less: {
                text: "Less Than",
                TR: !0,
                number: !0,
                date: !0
            },
            lte: {
                text: "Less than or equal",
                TR: !0,
                number: !0,
                date: !0
            },
            range: {
                TR: !0,
                string: !0,
                number: !0,
                date: !0
            },
            regexp: {
                TR: !0,
                string: !0,
                number: !0,
                date: !0
            },
            great: {
                text: "Great Than",
                TR: !0,
                number: !0,
                date: !0
            },
            gte: {
                text: "Greater than or equal",
                TR: !0,
                number: !0,
                date: !0
            }
        };
        return {
            conditions: t,
            getAllConditions: function() {
                var e = [];
                for (var r in t) e.push(r);
                return e
            }(),
            getConditions: function(e) {
                var r = [];
                for (var n in t) t[n][e] && r.push(n);
                return r
            },
            getTRConditions: function() {
                var e = [];
                for (var r in t) t[r].TR && e.push(r);
                return e
            }(),
            getWTRConditions: function() {
                var e = [];
                for (var r in t) t[r].TR || e.push(r);
                return e
            }()
        }
    }(), e.filter.rules = {}, e.filter.rules.en = {
        begin: "Begins With",
        between: "Between",
        notbegin: "Does not begin with",
        contain: "Contains",
        notcontain: "Does not contain",
        equal: "Equal To",
        notequal: "Not Equal To",
        empty: "Empty",
        notempty: "Not Empty",
        end: "Ends With",
        notend: "Does not end with",
        less: "Less Than",
        lte: "Less than or equal",
        great: "Great Than",
        gte: "Greater than or equal"
    };
    var r = function(t) {
        this.that = t, t.on("load", function() {
            var e = t.options.dataModel.dataUF;
            e && (e.length = 0)
        }), this.isMatchCell = this.isMatchCellSingle
    };
    e.cFilterData = r, r.conditions = {
        equal: function(t, e) {
            return t == e ? !0 : void 0
        },
        contain: function(t, e) {
            return -1 != t.indexOf(e) ? !0 : void 0
        },
        notcontain: function(t, e) {
            return -1 == t.indexOf(e) ? !0 : void 0
        },
        empty: function(t) {
            return 0 == t.length ? !0 : void 0
        },
        notempty: function(t) {
            return t.length > 0 ? !0 : void 0
        },
        begin: function(t, e) {
            return 0 == (t + "").indexOf(e) ? !0 : void 0
        },
        notbegin: function(t, e) {
            return 0 != t.indexOf(e) ? !0 : void 0
        },
        end: function(t, e) {
            var r = t.lastIndexOf(e);
            return -1 != r && r + e.length == t.length ? !0 : void 0
        },
        notend: function(t, e) {
            var r = t.lastIndexOf(e);
            return -1 == r || r + e.length != t.length ? !0 : void 0
        },
        regexp: function(t, e) {
            return e.test(t) ? (e.lastIndex = 0, !0) : void 0
        },
        notequal: function(t, e) {
            return t != e ? !0 : void 0
        },
        great: function(t, e) {
            return t > e ? !0 : void 0
        },
        gte: function(t, e) {
            return t >= e ? !0 : void 0
        },
        between: function(t, e, r) {
            return t >= e && r >= t ? !0 : void 0
        },
        range: function(e, r) {
            return -1 != t.inArray(e, r) ? !0 : void 0
        },
        less: function(t, e) {
            return e > t ? !0 : void 0
        },
        lte: function(t, e) {
            return e >= t ? !0 : void 0
        }
    }, r.convert = function(e, r) {
        return e = null == e ? "" : e, "string" == r ? e = t.trim(e).toUpperCase() : "date" == r ? e = Date.parse(e) : "integer" == r ? e = parseInt(e) : "float" == r ? e = parseFloat(e) : "bool" == r ? e = String(e).toLowerCase() : "html" == r && (e = t.trim(e).toUpperCase()), e
    }, r.prototype = {
        isMatchCellSingle: function(t, e) {
            var n = t.dataIndx,
                i = t.dataType,
                o = t.value,
                a = t.value2,
                l = t.condition,
                s = t.cbFn,
                d = e[n];
            d = "regexp" == l ? null == d ? "" : d : r.convert(d, i);
            var c = !!s(d, o, a);
            return c
        },
        isMatchRow: function(t, e, r) {
            if (0 == e.length) return !0;
            for (var n = 0; n < e.length; n++) {
                var i = e[n],
                    o = this.isMatchCell(i, t);
                if ("OR" == r && o) return !0;
                if ("AND" == r && !o) return !1
            }
            return "AND" == r ? !0 : "OR" == r ? !1 : void 0
        },
        getQueryStringFilter: function() {
            var t = this.that,
                e = t.options,
                r = e.stringify,
                n = e.filterModel,
                i = n.mode,
                o = t.colModel,
                a = this.getRulesFromCM({
                    CM: o,
                    location: "remote"
                }),
                l = "";
            if (n && n.on && a)
                if (a.length) {
                    var s = {
                        mode: i,
                        data: a
                    };
                    l = r === !1 ? s : JSON.stringify(s)
                } else l = "";
            return l
        },
        copyRuleToColumn: function(e, r) {
            var n = r.filter,
                i = e.condition,
                o = e.value;
            if (n ? n.on = !0 : n = r.filter = {
                    on: !0
                }, i && (n.condition = i), i = n.condition, n.value = o, "between" == i) n.value2 = e.value2;
            else if ("range" == i) {
                var a = [];
                if (o)
                    if ("string" == typeof o) {
                        var l = n.options,
                            s = o.indexOf('"'),
                            d = o.lastIndexOf('"');
                        if (o = o.substr(s, d + 1), o = JSON.parse("[" + o + "]"), l)
                            for (var c = 0, h = l.length; h > c; c++) {
                                var u = l[c]; - 1 != t.inArray(u, o) && a.push(u)
                            } else a = o.split(",s*")
                    } else "function" == typeof o.push && (a = o);
                n.value = a
            }
        },
        filterLocalData: function(t) {
            t = t || {};
            var e, r, n = this.that,
                i = t.data,
                o = !i,
                a = o ? n.colModel : t.CM,
                l = this.getRulesFromCM({
                    CM: a
                }),
                s = n.options,
                d = s.dataModel,
                c = n.iSort,
                h = i || d.data,
                u = d.dataUF = d.dataUF || [],
                f = [],
                p = [],
                g = s.filterModel,
                v = (g.multiple, t.mode || g.mode);
            if (o)
                if (u.length) {
                    r = !0;
                    for (var m = 0, w = u.length; w > m; m++) h.push(u[m]);
                    u = d.dataUF = []
                } else {
                    if (!l.length) return {
                        data: h,
                        dataUF: u
                    };
                    c.saveOrder()
                }
            if (g.on && v && l && l.length) {
                if (h.length)
                    if (e = {
                            filters: l,
                            mode: v,
                            data: h
                        }, n._trigger("customFilter", null, e) === !1) f = e.dataTmp, p = e.dataUF;
                    else
                        for (var m = 0, w = h.length; w > m; m++) {
                            var x = h[m];
                            this.isMatchRow(x, l, v) ? f.push(x) : p.push(x)
                        }
                    h = f, u = p, 0 == c.readSorter().length && (h = c.sortLocalData(h)), o && (d.data = h, d.dataUF = u)
            } else r && o && (e = {
                data: h
            }, n._trigger("clearFilter", null, e) === !1 && (h = e.data), 0 == c.readSorter().length && (h = c.sortLocalData(h)), d.data = h, n._queueATriggers.filter = {
                ui: {
                    type: "local"
                }
            });
            return o && (n._queueATriggers.filter = {
                ui: {
                    type: "local",
                    filter: l
                }
            }), {
                data: h,
                dataUF: u
            }
        },
        addMissingConditions: function(t) {
            var e = this.that;
            t.forEach(function(t) {
                t.condition = t.condition || e.getColumn({
                    dataIndx: t.dataIndx
                }).filter.condition
            })
        },
        getRulesFromCM: function(r) {
            var n = r.CM;
            if (!n) throw "CM N/A";
            for (var i = (this.that, n.length), o = 0, a = r.location, l = e.filter.getAllConditions, s = e.filter.getTRConditions, d = [], c = e.cFilterData, h = function(e, r, n) {
                    return "function" == typeof e ? !0 : "between" == e ? null != r && "" !== r || null != n && "" !== n : -1 != t.inArray(e, l) ? null != r && "" !== r || -1 == t.inArray(e, s) : !0
                }, u = function(t, e) {
                    return "remote" == a ? (t = null == t ? "" : t, t.toString()) : c.convert(t, e)
                }; i > o; o++) {
                var f = n[o],
                    p = f.filter;
                if (p && p.on) {
                    var g = f.dataIndx,
                        v = f.dataType,
                        v = v && "stringi" != v && "function" != typeof v ? v : "string",
                        m = p.value,
                        w = p.value2,
                        x = p.condition;
                    if (h(x, m, w)) {
                        if ("between" == x) "" === m || null == m ? (x = "lte", m = u(w, v)) : "" === w || null == w ? (x = "gte", m = u(m, v)) : (m = u(m, v), w = u(w, v));
                        else if ("regexp" == x) {
                            if ("remote" == a) m = m.toString();
                            else if ("string" == typeof m) try {
                                var y = p.modifiers || "gi";
                                m = new RegExp(m, y)
                            } catch (_) {
                                m = /.*/
                            }
                        } else if ("range" == x) {
                            if (null == m) continue;
                            if ("string" == typeof m) m = u(m, v), m = m.split(/\s*,\s*/);
                            else if (m && "function" == typeof m.push) {
                                if (0 == m.length) continue;
                                m = m.slice();
                                for (var b = 0, I = m.length; I > b; b++) m[b] = u(m[b], v)
                            }
                        } else m = u(m, v);
                        var C;
                        C = "remote" == a ? "" : "function" == typeof x ? x : c.conditions[x], d.push({
                            dataIndx: g,
                            value: m,
                            value2: w,
                            condition: x,
                            dataType: v,
                            cbFn: C
                        })
                    }
                }
            }
            return d
        },
        getCMFromRules: function(e) {
            var r = this.that;
            return e.map(function(e) {
                return t.extend(!0, {}, r.getColumn({
                    dataIndx: e.dataIndx
                }))
            })
        },
        clearFilters: function(t) {
            t.forEach(function(t) {
                var e = t.filter;
                e && (e.value = e.value2 = void 0)
            })
        },
        filter: function(t) {
            t = t || {}, this.compatibilityCheck(t);
            var e, r, n = this.that,
                i = n.options,
                o = !1,
                a = t.data,
                l = t.rules || [t.rule],
                s = !a,
                d = i.dataModel,
                c = i.filterModel,
                h = t.mode || c.mode,
                u = "replace" == t.oper,
                f = s ? n.colModel : this.getCMFromRules(l),
                p = 0,
                g = l.length;
            if (this.addMissingConditions(l), s) {
                if (n._trigger("beforeFilter", null, t) === !1) return;
                for (null != t.header && (o = t.header), u && this.clearFilters(f); g > p; p++) e = l[p], r = n.getColumn({
                    dataIndx: e.dataIndx
                }), this.copyRuleToColumn(e, r)
            } else
                for (; g > p; p++) e = l[p], r = f[p], this.copyRuleToColumn(e, r);
            var v = {
                header: o,
                CM: f,
                data: a,
                rules: l,
                mode: h
            };
            return "remote" != d.location || "local" == c.type ? (v.source = "filter", v.trigger = !1, n._onDataAvailable(v)) : void n.remoteRequest({
                apply: s,
                CM: f,
                callback: function() {
                    return n._onDataAvailable(v)
                }
            })
        },
        compatibilityCheck: function(t) {
            var e, r = t.data,
                n = "Incorrect filter parameters. Please check upgrade guide";
            if (r)
                if (e = r[0]) {
                    if (e.hasOwnProperty("dataIndx") && e.hasOwnProperty("value")) throw n
                } else if (!t.rules) throw n
        }
    }
}(jQuery),
function(t) {
    "use strict";
    var e = t.paramquery,
        r = e.cSort = function(t) {
            var e = this;
            e.that = t, e.sorters = [], e.tmpPrefix = "pq_tmp_", e.cancel = !1
        };
    e.pqGrid.prototype.sort = function(t) {
        t = t || {};
        var e = this,
            r = this.options,
            n = r.dataModel,
            i = n.data,
            o = r.sortModel,
            a = o.type;
        if (i && i.length || "local" != a) {
            var l, s = r.editModel,
                d = this.iSort,
                c = d.getSorter(),
                h = t.evt,
                u = null == t.single ? d.readSingle() : t.single,
                f = d.readCancel();
            if (t.sorter ? t.addon ? (t.single = u, t.cancel = f, l = d.addon(t)) : l = t.sorter : l = d.readSorter(), l.length || c.length) {
                s.indices && e.blurEditor({
                    force: !0
                });
                var p = {
                    dataIndx: l.length ? l[0].dataIndx : null,
                    oldSorter: c,
                    sorter: l,
                    source: t.source,
                    single: u
                };
                if (e._trigger("beforeSort", h, p) === !1) return void d.cancelSort();
                d.resumeSort(), "local" == a && d.saveOrder(), d.setSorter(l), d.setSingle(u), d.writeSorter(l), d.writeSingle(u), "local" == a ? (n.data = d.sortLocalData(i), this._queueATriggers.sort = {
                    evt: h,
                    ui: p
                }, t.refresh !== !1 && this.refreshView()) : "remote" == a && (this._queueATriggers.sort = {
                    evt: h,
                    ui: p
                }, t.initByRemote || this.remoteRequest({
                    initBySort: !0,
                    callback: function() {
                        e._onDataAvailable()
                    }
                }))
            }
        }
    };
    var n = r.prototype;
    n.cancelSort = function() {
        this.cancel = !0
    }, n.resumeSort = function() {
        this.cancel = !1
    }, n.readSorter = function() {
        var t, e = this.that,
            r = e.options,
            n = e.columns,
            i = [],
            o = r.sortModel,
            a = o.sorter;
        if (a && (t = a.length)) {
            for (; t--;) null == n[a[t].dataIndx] && a.splice(t, 1);
            i = i.concat(a)
        }
        return i = pq.arrayUnique(i, "dataIndx")
    }, n.setSingle = function(t) {
        this.single = t
    }, n.getSingle = function() {
        return this.single
    }, n.readSingle = function() {
        return this.that.options.sortModel.single
    }, n.writeSingle = function(t) {
        this.that.options.sortModel.single = t
    }, n.setCancel = function(t) {
        this.cancel = t
    }, n.getCancel = function() {
        return this.cancel
    }, n.readCancel = function() {
        return this.that.options.sortModel.cancel
    }, n.writeCancel = function(t) {
        this.that.options.sortModel.cancel = t
    }, n.writeSorter = function(t) {
        var e = this.that.options,
            r = e.sortModel;
        r.sorter = t
    }, n.addon = function(t) {
        t = t || {};
        var e = t.sorter,
            r = e[0].dataIndx,
            n = e[0].dir,
            i = t.single,
            o = t.cancel,
            a = 0,
            l = this.readSorter();
        if (null == i) throw "sort single N/A";
        if (null != r)
            if (i && !t.tempMultiple)
                if (l = l.length ? [l[0]] : [], l[a] && l[a].dataIndx == r) {
                    var s = l[a].dir,
                        d = "up" === s ? "down" : o && "down" === s ? "" : "up";
                    "" === d ? l.length-- : l[a].dir = d
                } else d = n ? n : "up", l[a] = {
                    dataIndx: r,
                    dir: d
                };
        else {
            var c = this.inSorters(l, r);
            c > -1 ? (s = l[c].dir, "up" == s ? l[c].dir = "down" : o && "down" == s ? l.splice(c, 1) : 1 == l.length ? l[c].dir = "up" : l.splice(c, 1)) : l.push({
                dataIndx: r,
                dir: "up"
            })
        }
        return l
    }, n.saveOrder = function(t) {
        var e = this.that,
            r = e.options.dataModel,
            t = r.data;
        if (t && t.length && !(r.dataUF && r.dataUF.length || this.getSorter().length && null != t[0].pq_order))
            for (var n = 0, i = t.length; i > n; n++) t[n].pq_order = n
    }, n.getQueryStringSort = function() {
        if (this.cancel) return "";
        var t = this.that,
            e = this.sorters,
            r = t.options,
            n = r.stringify;
        return e.length ? n === !1 ? e : JSON.stringify(e) : ""
    }, n.getSorter = function() {
        var t = this.sorters;
        return t
    }, n.setSorter = function(t) {
        this.sorters = t.slice(0)
    }, n.inSorters = function(t, e) {
        for (var r = 0; r < t.length; r++)
            if (t[r].dataIndx == e) return r;
        return -1
    }, n.sortLocalData = function(t) {
        var e = this.sorters;
        return e.length || (e = [{
            dataIndx: "pq_order",
            dir: "up",
            dataType: "integer"
        }]), this._sortLocalData(e, t)
    }, n.compileSorter = function(t, e) {
        var r = this,
            n = r.that,
            o = n.columns,
            a = n.options,
            l = [],
            s = [],
            d = [],
            c = r.tmpPrefix,
            h = a.sortModel,
            u = h.useCache,
            f = h.ignoreCase,
            p = t.length;
        e = e ? e : a.dataModel.data;
        for (var g = 0; p > g; g++) {
            var v = t[g],
                m = v.dataIndx,
                w = o[m] || {},
                x = v.dir = v.dir || "up",
                y = "up" == x ? 1 : -1,
                _ = w.sortType,
                _ = pq.getFn(_),
                b = w.dataType || v.dataType || "string",
                b = "string" == b && f ? "stringi" : b,
                I = u && "date" == b,
                C = I ? c + m : m;
            s[g] = C, d[g] = y, _ ? l[g] = function(t, e) {
                return function(r, n, i, o) {
                    return e(r, n, i, o, t)
                }
            }(_, i.sort_sortType) : "integer" == b ? l[g] = i.sort_number : "float" == b ? l[g] = i.sort_number : "function" == typeof b ? l[g] = function(t, e) {
                return function(r, n, i, o) {
                    return e(r, n, i, o, t)
                }
            }(b, i.sort_dataType) : "date" == b ? l[g] = i["sort_date" + (I ? "_fast" : "")] : "bool" == b ? l[g] = i.sort_bool : "stringi" == b ? l[g] = i.sort_locale : l[g] = i.sort_string, I && r.useCache(e, b, m, C)
        }
        return r._composite(l, s, d, p)
    }, n._composite = function(t, e, r, n) {
        return function(i, o) {
            for (var a = 0, l = 0; n > l && (a = t[l](i, o, e[l], r[l]),
                    0 == a); l++);
            return a
        }
    }, n._sortLocalData = function(t, e) {
        if (!e) return [];
        if (!e.length || !t || !t.length) return e;
        var r = this,
            n = r.that,
            i = n.options.sortModel,
            o = r.compileSorter(t),
            a = {
                sort_composite: o,
                data: e
            };
        return n._trigger("customSort", null, a) !== !1 ? e.sort(o) : e = a.data, i.useCache && setTimeout(r.removeCache(t, e), 0), e
    }, n.useCache = function(t, e, r, n) {
        for (var o = i["get_" + e], a = t.length; a--;) {
            var l = t[a];
            l[n] = o(l[r])
        }
    }, n.removeCache = function(t, e) {
        var r = this.tmpPrefix;
        return function() {
            for (var n = t.length; n--;) {
                var i = t[n],
                    o = r + i.dataIndx,
                    a = e.length;
                if (a && e[0].hasOwnProperty(o))
                    for (; a--;) delete e[a][o]
            }
        }
    };
    var i = {
        get_date: function(t) {
            var e;
            return t ? isNaN(e = Date.parse(t)) ? 0 : e : 0
        },
        sort_number: function(t, e, r, n) {
            var i = t[r],
                o = e[r];
            return i = i ? 1 * i : 0, o = o ? 1 * o : 0, (i - o) * n
        },
        sort_date: function(t, e, r, n) {
            var i = t[r],
                o = e[r];
            return i = i ? Date.parse(i) : 0, o = o ? Date.parse(o) : 0, (i - o) * n
        },
        sort_date_fast: function(t, e, r, n) {
            var i = t[r],
                o = e[r];
            return (i - o) * n
        },
        sort_dataType: function(t, e, r, n, i) {
            var o = t[r],
                a = e[r];
            return i(o, a) * n
        },
        sort_sortType: function(t, e, r, n, i) {
            return i(t, e, r) * n
        },
        sort_string: function(t, e, r, n) {
            var i = t[r] || "",
                o = e[r] || "",
                a = 0;
            return i > o ? a = 1 : o > i && (a = -1), a * n
        },
        sort_locale: function(t, e, r, n) {
            var i = t[r] || "",
                o = e[r] || "";
            return i.localeCompare(o) * n
        },
        sort_bool: function(t, e, r, n) {
            var i = t[r],
                o = e[r],
                a = 0;
            return i && !o || i === !1 && null === o ? a = 1 : (o && !i || o === !1 && null === i) && (a = -1), a * n
        }
    };
    pq.sortObj = i
}(jQuery),
function(t) {
    "use strict";

    function e(t) {
        this.that = t, this.mc = null;
        var e = this;
        t.on("dataReady columnOrder groupShowHide", function(r, n) {
            t.options.mergeCells && "pager" !== n.source && e.init()
        })
    }

    function r(t, e, r) {
        var n, i = 0,
            o = e,
            a = t.length;
        for (r = r > a ? a : r; r > o; o++) n = t[o], n.pq_hidden !== !0 && i++;
        return i
    }
    t.paramquery.cMerge = e;
    var n = e.prototype;
    n.findNextVisibleColumn = function(t, e, r) {
        for (var n, i = e; e + r > i; i++) {
            if (n = t[i], !n) return -1;
            if (!n.hidden) return i
        }
    }, n.findNextVisibleRow = function(t, e, r) {
        for (var n, i = e; e + r > i; i++) {
            if (n = t[i], !n) return -1;
            if (!n.pq_hidden) return i
        }
    }, n.init = function() {
        for (var t = this.that, e = this.findNextVisibleColumn, r = this.findNextVisibleRow, n = this.calcVisibleColumns, i = this.calcVisibleRows, o = t.colModel, a = t.options.mergeCells || [], l = t.get_p_data(), s = [], d = [], c = 0, h = a.length; h > c; c++) {
            var u, f, p = a[c],
                g = p.r1,
                v = g,
                m = l[g],
                w = p.c1,
                x = w,
                y = o[w],
                _ = p.rc,
                b = p.cc;
            if (y && m && (y.hidden && (x = e(o, w, b)), u = n(o, w, w + b), m.pq_hidden && (v = r(l, g, _)), f = i(l, g, g + _), !(1 > f || 1 > u))) {
                s.push({
                    r1: g,
                    c1: w,
                    rc: _,
                    cc: b,
                    e_rc: f,
                    e_cc: u
                }), d[v] = d[v] || [], d[v][x] = {
                    show: !0,
                    rowspan: f,
                    colspan: u,
                    o_rowspan: _,
                    o_colspan: b,
                    style: p.style,
                    cls: p.cls,
                    attr: p.attr,
                    r1: g,
                    c1: w,
                    v_r1: v,
                    v_c1: x
                };
                for (var I = {
                        show: !1,
                        r1: g,
                        c1: w,
                        v_r1: v,
                        v_c1: x
                    }, C = g; g + _ > C; C++) {
                    d[C] = d[C] || [];
                    for (var q = w; w + b > q; q++) C == v && q == x || (d[C][q] = I)
                }
            }
        }
        t._mergeCells = d.length > 0, this.mc = d, this.mc2 = s
    }, n.isHidden = function(t, e) {
        var r, n = (this.that, this.mc);
        return !(!(n && n[t] && (r = n[t][e])) || r.show)
    }, n.setData = function(t, e, r) {
        var n, i = (this.that, this.mc);
        i[t] && (n = i[t][e]) && (n.data = r)
    }, n.getData = function(t, e, r) {
        var n, i = (this.that, this.mc);
        if (i[t] && (n = i[t][e])) {
            var o = n.data;
            return o ? o[r] : null
        }
    }, n.removeData = function(t, e, r) {
        var n, i = (this.that, this.mc);
        if (i && i[t] && (n = i[t][e])) {
            var o = n.data;
            o && (o[r] = null)
        }
    }, n.ismergedCell = function(t, e) {
        var r, n = (this.that, this.mc);
        if (n && n[t] && (r = n[t][e])) {
            var i = r.v_r1,
                o = r.v_c1,
                a = n[i][o];
            return t == a.r1 && e == a.c1 ? {
                rowspan: a.o_rowspan,
                colspan: a.o_colspan
            } : !0
        }
        return !1
    }, n.isRootCell = function(t, e, r) {
        var n, i = (this.that, this.mc);
        if (i && i[t] && (n = i[t][e])) {
            if ("o" == r) return t == n.r1 && e == n.c1;
            var o = n.v_r1,
                a = n.v_c1;
            if ("a" == r) {
                var l = i[o][a];
                return l.a_r1 == t && l.a_c1 == e
            }
            if (o == t && a == e) {
                var l = i[o][a];
                return {
                    rowspan: l.rowspan,
                    colspan: l.colspan
                }
            }
        }
    }, n.getRootCell = function(t, e, r) {
        var n, i = this.that,
            o = this.mc;
        if (o && o[t] && (n = o[t][e])) {
            if ("a" == r) {
                t = n.v_r1, e = n.v_c1;
                var a = o[t][e];
                a.a_r1 && (t = a.a_r1, e = a.a_c1)
            } else "o" == r ? (t = n.r1, e = n.c1) : (t = n.v_r1, e = n.v_c1);
            var l = i.colModel,
                s = l[e],
                d = i.riOffset,
                c = t - d;
            return 0 > c && (c = 0, t = d), {
                rowIndxPage: c,
                colIndx: e,
                column: s,
                dataIndx: s.dataIndx,
                rowData: i.getRowData({
                    rowIndx: t
                }),
                rowIndx: t,
                rowspan: n.rowspan,
                colspan: n.colspan
            }
        }
        return i.normalize({
            rowIndx: t,
            colIndx: e
        })
    }, n.inflateRange = function(t, e, r, n) {
        var i = this.that,
            o = !1,
            a = i.options,
            l = a.groupModel,
            s = l.on ? i.riOffset + i.pdata.length - 1 : a.dataModel.data.length - 1,
            d = i.colModel.length - 1,
            c = this.mc2;
        if (!c) return [t, e, r, n];
        t: for (var h = 0, u = c.length; u > h; h++) {
            var f = c[h],
                p = f.r1,
                g = f.c1,
                v = p + f.rc - 1,
                m = g + f.cc - 1,
                v = v > s ? s : v,
                m = m > d ? d : m,
                w = t > p && v >= t,
                x = r >= p && v > r,
                y = e > g && m >= e,
                _ = n >= g && m > n;
            if ((w || x) && m >= e && n >= g || (y || _) && v >= t && r >= p) {
                o = !0, t = t > p ? p : t, e = e > g ? g : e, r = v > r ? v : r, n = m > n ? m : n;
                break t
            }
        }
        return o ? this.inflateRange(t, e, r, n) : [t, e, r, n]
    }, n.calcVisibleColumns = function(t, e, r) {
        var n = 0,
            i = t.length;
        for (r = r > i ? i : r; r > e; e++) t[e].hidden !== !0 && n++;
        return n
    }, n.calcVisibleRows = r;
    var i = t.paramquery.pqGrid.prototype;
    i.calcVisibleRows = r, n.renderCell = function(t) {
        var e, r, n = this.that,
            i = t.rowIndx,
            o = t.rowIndxPage,
            a = this.calcVisibleColumns,
            l = this.calcVisibleRows,
            s = t.colIndx,
            d = this.mc;
        if (d[i] && (r = d[i][s])) {
            var c = r.v_r1,
                h = r.v_c1,
                u = r.r1,
                f = r.c1,
                p = n.options,
                g = n.colModel,
                v = n.riOffset,
                m = p.freezeCols,
                w = p.freezeRows,
                x = w ? w + v : 0,
                y = n.initH,
                _ = n.initV,
                b = s == h && o == _ && c >= x,
                I = s == h && i == _ && c >= x,
                C = i == c && s == y && h >= m,
                q = i == _ && s == y && h >= m && c >= x;
            if (!(r.show || b || I || C || q)) return null;
            var R = u - v,
                D = n.pdata,
                M = n.get_p_data(),
                T = M[u],
                P = g[f];
            e = {
                rowData: T,
                rowIndx: u,
                colIndx: f,
                column: P,
                rowIndxPage: R
            };
            var E = d[c][h],
                S = E.colspan,
                k = E.rowspan,
                H = a(g, s, m),
                F = l(D, i - v, w);
            if (m && m > s && S > H) {
                var $ = S - a(g, m, y),
                    A = H;
                S = Math.max($, A)
            } else S -= a(g, h, s);
            if (w && i > v && x > i && k > F) {
                var V = k - l(D, w, _),
                    L = F;
                k = Math.max(V, L)
            } else k -= l(M, c, i);
            E.a_r1 = i, E.a_c1 = s, e.rowspan = k, e.colspan = S, e.style = E.style, e.attr = E.attr, e.cls = E.cls
        }
        return e ? e : t
    }, n.getMergeCells = function(t, e, r) {
        for (var n, i, o, a = this.that, l = a.options.mergeCells, s = a.riOffset, d = s + r, c = [], h = l ? l.length : 0, u = 0; h > u; u++) n = l[u], i = n.r1, o = n.c1, (!e || i >= s && d > i) && (e && (i -= s), i += t, c.push({
            r1: i,
            c1: o,
            r2: i + n.rc - 1,
            c2: o + n.cc - 1
        }));
        return c
    }
}(jQuery),
function(t) {
    "use strict";

    function e(t, e, r, n) {
        t.push("<li data-option='", r, "' class='pq-menu-item'>", "<label>", "<input type='checkbox' ", e[r] ? "checked" : "", "/>", n["strGroup_" + r], "</label></li>")
    }

    function r(t, e, r, n) {
        var i, o = t[e],
            a = n;
        if (o) {
            do i = o[a].rip, a++; while (r > i);
            return a - 1
        }
    }
    var n = t.paramquery;
    n.pqGrid.defaults.groupModel = {
        on: !1,
        title: [],
        titleDefault: "{0} ({1})",
        header: !0,
        headerMenu: !0,
        menuItems: ["merge", "fixCols", "grandSummary"],
        fixCols: !0,
        icon: ["ui-icon-triangle-1-se", "ui-icon-triangle-1-e"],
        dataIndx: [],
        collapsed: [],
        showSummary: [],
        calcSummary: [],
        summaryInTitleRow: "collapsed",
        summaryEdit: !0,
        refreshOnChange: !0
    }, pq.aggregate = {
        sum: function(t) {
            for (var e, r = 0, n = t.length; n--;) e = t[n], null != e && (r += e - 0);
            return r
        },
        avg: function(t, e) {
            try {
                var r = pq.formulas.AVERAGE(t)
            } catch (n) {
                r = n
            }
            return r
        },
        flatten: function(t) {
            return t.filter(function(t) {
                return null != t
            })
        },
        max: function(t, e) {
            var r, n = e.dataType;
            return t = this.flatten(t), "float" == n || "integer" == n ? r = Math.max.apply(Math, t) : "date" == n ? (t.sort(function(t, e) {
                return t = Date.parse(t), e = Date.parse(e), e - t
            }), r = t[0]) : (t.sort(), r = t[t.length - 1]), r
        },
        min: function(t, e) {
            var r, n, i, o, a = e.dataType;
            if (t = this.flatten(t), "integer" == a || "float" == a) r = Math.min.apply(Math, t);
            else if ("date" == a) {
                for (o = t.length, n = []; o--;) i = t[o], n.push({
                    dateO: i,
                    dateP: Date.parse(i)
                });
                n.sort(function(t, e) {
                    return t.dateP - e.dateP
                }), r = n.length ? n[0].dateO : void 0
            } else t.sort(), r = t[0];
            return r
        },
        count: function(t) {
            return this.flatten(t).length
        },
        stdev: function(t) {
            try {
                var e = pq.formulas.STDEV(t)
            } catch (r) {
                e = r
            }
            return e
        },
        stdevp: function(t) {
            try {
                var e = pq.formulas.STDEVP(t)
            } catch (r) {
                e = r
            }
            return e
        }
    };
    var i = n.cGroup = function(t) {
        var e = this;
        e.that = t, t.options.groupModel.on && e.init()
    };
    i.beforeTrigger = function(t, e) {
        return function(r) {
            return e._trigger("beforeGroupExpand", t, r) === !1
        }
    }, i.onGroupItemClick = function(e) {
        return function(r) {
            var n = t(r.target),
                i = t(this).data("indx");
            n.hasClass("pq-group-remove") ? e.removeGroup(i) : e.toggleLevel(i, r)
        }
    }, i.prototype = {
        addGroup: function(e, r) {
            var n = this.that,
                i = n.options.groupModel,
                o = i.dataIndx = i.dataIndx || [];
            null != e && -1 === t.inArray(e, o) && (null == r ? o.push(e) : o.splice(r, 0, e), this._triggerChange = !0, this.refreshFull())
        },
        createHeader: function() {
            for (var e = this, r = e.that, n = e.$header, o = r.options, a = o.bootstrap, l = r.columns, s = a.on, d = o.groupModel, c = d.dataIndx, h = c.length; h--;) null == l[c[h]] && c.splice(h, 1);
            if (h = c.length, d.header && d.on) {
                if (n ? n.empty() : (n = e.$header = t("<div class='pq-group-header ui-helper-clearfix' ></div>").appendTo(r.$top), n.on("click", ".pq-group-item", i.onGroupItemClick(e))), h) {
                    for (var u = [], f = 0; h > f; f++) {
                        var p = c[f],
                            g = l[p],
                            v = d.collapsed,
                            m = s ? a.groupModel.icon : d.icon,
                            w = v[f] ? m[1] : m[0];
                        u.push("<div tabindex='0' class='pq-group-item' data-indx='", p, "' >", "<span class='", e.toggleIcon, w, "' ></span>", g.pqtitle || ("string" == typeof g.title ? g.title : p), "<span class='", e.groupRemoveIcon, "' ></span></div>")
                    }
                    n[0].innerHTML = u.join("")
                }
                e.initHeader(o, d)
            } else n && (n.remove(), e.$header = null)
        },
        concat: function() {
            return function(t, e, r) {
                return e.forEach(function(e) {
                    t.push(e)
                }), r.pq_children = e, t
            }
        },
        collapseTo: function(t) {
            this.expandTo(t, !0)
        },
        editorSummary: function(e, r) {
            var n = e.summaryOptions,
                i = this;
            return function(e) {
                var o = e.rowData;
                if (o.pq_gsummary || o.pq_gtitle) {
                    var a, l, s = pq.aggregate,
                        d = e.column,
                        c = d.summary,
                        h = c ? c.edit : null,
                        u = d.dataType,
                        f = [""];
                    if (t.inArray(e.dataIndx, r.dataIndx) > -1) return !1;
                    if (!r.summaryEdit && !h || h === !1) return !1;
                    "integer" == u || "float" == u ? u = "number" : "date" !== u && (u = "string"), l = n[u].split(","), a = t.inArray;
                    for (var p in s) a(p, l) > -1 && f.push(p);
                    return 1 == f.length ? !1 : {
                        type: "select",
                        prepend: r.prepend,
                        options: r.options || f,
                        valueIndx: r.valueIndx,
                        labelIndx: r.labelIndx,
                        init: r.init || i.editorInit,
                        getData: r.getData || i.editorGetData
                    }
                }
            }
        },
        editorInit: function(t) {
            var e, r = t.column.summary;
            r || (r = t.column.summary = {}), e = r.type, t.$cell.find("select").val(e)
        },
        editorGetData: function(t) {
            var e = t.column,
                r = e.dataType,
                n = t.$cell.find("select").val();
            return e.summary.type = n, this.one("beforeValidate", function(t, n) {
                n.allowInvalid = !0, n.track = !1, n.history = !1, e.dataType = "string", this.one(!0, "change", function(t, n) {
                    e.dataType = r
                })
            }), n
        },
        expandTo: function(t, e) {
            var n, i, o, a, l, s = this.that,
                d = !!e,
                c = t.split(","),
                h = c.length,
                u = this.tree,
                f = 0,
                p = s.pdata;
            if (!(h > u.length)) {
                for (var g = 0; h > g; g++) {
                    if (l = 1 * c[g] + f, a = u[g][l], !a) {
                        if (0 == g) return;
                        break
                    }
                    n = a.rip, (!d || d && g == h - 1) && (i = p[n], i.pq_close != d && (o = i, i.pq_close = d)), f = r(u, g + 1, n, l)
                }
                o && s._trigger("group", null, {
                    indx: l,
                    close: d
                }) !== !1 && this.saveState(!0)
            }
        },
        collapseAll: function(t) {
            this.expandAll(t, !0)
        },
        expandAll: function(t, e) {
            this.trigger({
                all: !0,
                close: !!e,
                level: t || 0
            }) !== !1 && this.that.refreshView()
        },
        collapse: function(t) {
            this.expand(t, !0)
        },
        expand: function(t, e) {
            this.trigger({
                close: !!e,
                level: t || 0
            }) !== !1 && this.that.refreshView()
        },
        firstCol: function() {
            return this.that.colModel.find(function(t) {
                return !t.hidden
            })
        },
        flatten: function(t, e, r, n) {
            var i = r.dataIndx,
                o = r.titleInFirstCol,
                a = o ? this.firstCol().dataIndx : null,
                l = this.concat(),
                s = this.tree = [],
                d = i.length,
                c = [];
            return function h(u, f, p) {
                if (!d) return u;
                var g = f || 0,
                    v = i[g],
                    m = r.collapsed[g],
                    w = r.calcSummary[g] !== !1,
                    x = r.showSummary[g],
                    y = s[g] = s[g] || [],
                    _ = e(u, v, t[v]);
                return _.forEach(function(t) {
                    var e, r = t[1],
                        i = x ? {
                            pq_gsummary: !0,
                            pq_level: g,
                            pq_rowcls: "pq-summary-row"
                        } : 0,
                        s = r.length,
                        u = c.length;
                    e = {
                        pq_gtitle: !0,
                        pq_level: g,
                        pq_close: m,
                        pq_items: s,
                        pq_children: []
                    }, e[o ? a : v] = t[0], c.push(e), p && p.push(e), w && n(r, e, i), d > g + 1 ? h(r, g + 1, e.pq_children) : c = l(c, r, e), i && c.push(i), y.push({
                        rip: u,
                        rip2: c.length
                    })
                }), c
            }
        },
        getVal: function(e) {
            var r = t.trim;
            return function(t, n, i) {
                var o = t[n],
                    a = i.groupChange;
                return a ? (a = pq.getFn(a))(o) : (o = r(o), e ? o.toUpperCase() : o)
            }
        },
        getSumCols: function() {
            return this._sumCols
        },
        getSumDIs: function() {
            return this._sumDIs
        },
        group: function(t) {
            return function(e, r, n) {
                var i = {},
                    o = [];
                return e.forEach(function(e) {
                    e.pq_hidden = void 0;
                    var a = t(e, r, n),
                        l = i[a];
                    null == l && (i[a] = l = o.length, o[l] = [a, []]), o[l][1].push(e)
                }), o
            }
        },
        groupData: function() {
            var t = this,
                e = t.that,
                r = e.options,
                n = r.groupModel,
                i = t.getVal(n.ignoreCase),
                o = n.dataIndx,
                a = e.pdata,
                l = e.columns,
                s = this.setSumCols(o),
                d = this.summary(s[0], s[1]);
            if (n.grandSummary) {
                var c = {
                    pq_grandsummary: !0,
                    pq_gsummary: !0
                };
                d(a, c), t.summaryData = r.summaryData = [c]
            } else t.summaryData.length = 0;
            e.pdata = this.flatten(l, this.group(i), n, d)(a)
        },
        init: function() {
            var t, e, r, n, i, o, a = this;
            a._init || (a.mc = [], a.tree = [], a.summaryData = [], o = a.that, t = o.options, e = t.groupModel, r = t.bootstrap, n = r.on, i = n ? "glyphicon " : "ui-icon ", a.groupRemoveIcon = "pq-group-remove " + i + (n ? "glyphicon-remove" : "ui-icon-close"), a.toggleIcon = "pq-group-toggle " + i, o.on("cellClick", a.onCellClick(a)).on("cellKeyDown", a.onCellKeyDown(a, e)).on(!0, "cellMouseDown", a.onCellMouseDown()).on("change", a.onChange(a, e)).on("dataReady", a.onDataReady(a, o)).on("columnDragDone", a.onColumnDrag(a)).on("columnOrder", a.onColumnOrder(a, e)), a._init = !0)
        },
        initHeadSortable: function() {
            var t = this,
                e = t.that,
                r = t.$header,
                n = e.options;
            r.sortable({
                axis: "x",
                distance: 3,
                tolerance: "pointer",
                cancel: ".pq-group-menu",
                stop: t.onSortable(t, n)
            })
        },
        initHeadDroppable: function() {
            var t = this,
                e = t.that,
                r = t.$header;
            r && (r.droppable({
                accept: function(r) {
                    var n = 1 * r.attr("pq-col-indx");
                    if (!isNaN(n) && e.colModel[n]) return t.acceptDrop
                },
                tolerance: "pointer",
                hoverClass: "pq-drop-hover",
                drop: t.onDrop(e, t)
            }), t.acceptDrop = !0)
        },
        initHeader: function(t, e) {
            var r = this;
            if (r.$header) {
                var n = r.$header,
                    i = n.find(".pq-group-item");
                n.data("uiSortable") || r.initHeadSortable(), i.length || n.append("<span class='pq-group-placeholder'>" + t.strGroup_header + "</span>"), e.headerMenu && r.initHeaderMenu()
            }
        },
        initHeaderMenu: function() {
            for (var r, n = this, i = n.that, o = i.BS_on, a = i.options, l = n.$header, s = ["<ul class='pq-group-menu'><li>", o ? "<span class='glyphicon glyphicon-chevron-left'></span>" : "", "<ul>"], d = a.groupModel, c = d.menuItems, h = 0, u = c.length; u > h; h++) e(s, d, c[h], a);
            s.push("</ul></li></ul>"), r = t(s.join("")).appendTo(l), r.menu({
                icons: {
                    submenu: "ui-icon-carat-1-w"
                },
                position: {
                    my: "right top",
                    at: "left top"
                }
            }), r.change(function(e) {
                if ("INPUT" == e.target.nodeName) {
                    var r = t(e.target),
                        i = r.closest("li").data("option"),
                        o = {};
                    o[i] = !a.groupModel[i], n.option(o)
                }
            })
        },
        initmerge: function() {
            for (var t, e, r, n, i, o, a, l, s = this.that, d = s.options, c = d.groupModel, h = c.dataIndx, u = s.colIndxs, f = c.merge, p = c.summaryInTitleRow, g = c.titleInFirstCol, v = this.tree, m = s.riOffset, w = s.colModel.length, x = [], y = s.pdata, _ = 0; _ < v.length; _++) {
                t = v[_], r = u[h[_]];
                for (var b = 0, I = t.length; I > b && (e = t[b], n = e.rip, null != n); b++) f ? (i = e.rip2, o = i - n, a = n + m, x.push({
                    r1: a,
                    rc: o,
                    c1: r,
                    cc: 1
                })) : (a = n + m, l = y[n], p && (l.pq_close || "collapsed" !== p) || x.push({
                    r1: a,
                    rc: 1,
                    c1: g ? 0 : r,
                    cc: w
                }))
            }
            x.length ? (this.mc = d.mergeCells = x, s.iMerge.init()) : this.mc.length && (this.mc.length = 0, s.iMerge.init())
        },
        initcollapsed: function() {
            var t, e, r, n, i, o, a = this.that,
                l = a.options.groupModel,
                s = l.merge,
                d = this.pdata,
                c = a.pdata;
            if (c) {
                for (var h = 0, u = c.length; u > h; h++) t = c[h], e = t.pq_gtitle, void 0 !== e && (i = t.pq_level, o = null, d && (r = d[h], n = r ? r.pq_close : null, null != n && (o = t.pq_close = n)), null == o && (o = t.pq_close), o ? this.showHideRows(h + 1, i, l) : s && (t.pq_hidden = !0));
                delete this.pdata
            }
        },
        onCellClick: function(e) {
            return function(r, n) {
                n.rowData.pq_gtitle && t(r.originalEvent.target).hasClass("pq-group-icon") && e.toggleRow(n.rowIndxPage, r)
            }
        },
        onCellMouseDown: function() {
            return function(e, r) {
                r.rowData.pq_gtitle && t(e.originalEvent.target).hasClass("pq-group-icon") && e.stopImmediatePropagation()
            }
        },
        onCellKeyDown: function(e, r) {
            return function(n, i) {
                return i.rowData.pq_gtitle && t.inArray(i.dataIndx, r.dataIndx) >= 0 && n.keyCode == t.ui.keyCode.ENTER ? (e.toggleRow(i.rowIndxPage, n), !1) : void 0
            }
        },
        onChange: function(t, e) {
            return function() {
                t.saveState(e.refreshOnChange)
            }
        },
        onColumnDrag: function(t) {
            return function(e, r) {
                var n = r.column,
                    i = n.colModel;
                i && i.length || n.groupable === !1 ? t.acceptDrop = !1 : t.initHeadDroppable()
            }
        },
        onDrop: function(t, e) {
            return function(r, n) {
                var i = 1 * n.draggable.attr("pq-col-indx"),
                    o = t.colModel[i].dataIndx;
                e.addGroup(o), e.acceptDrop = !1
            }
        },
        onSortable: function(e, r) {
            return function() {
                for (var n, i, o, a = [], l = r.groupModel, s = l.dataIndx, d = t(this).find(".pq-group-item"), c = 0; c < d.length; c++) i = t(d[c]), o = i.data("indx"), s[c] !== o && (n = !0), a.push(o);
                n && (l.dataIndx = a, e._triggerChange = !0, e.refreshFull())
            }
        },
        onDataReady: function(t, e) {
            return function() {
                t.tree.length = 0;
                var r = e.options.groupModel,
                    n = r.dataIndx.length;
                r.on && (n || r.grandSummary) && (t.groupData(), t.refreshColumns(), n && (t.initcollapsed(), t.initmerge())), t.createHeader()
            }
        },
        onColumnOrder: function(t, e) {
            return function() {
                return e.titleInFirstCol ? (t.refreshFull(), !1) : void t.initmerge()
            }
        },
        option: function(e, r) {
            var n = e.dataIndx,
                i = this.that,
                o = n ? n.length : 0,
                a = this,
                l = i.options,
                s = l.groupModel,
                d = s.dataIndx,
                c = e.on || null == e.on && s.on;
            c && a.init(), s.on && d.length && (e.on === !1 || 0 === o) && a.showRows(), t.extend(s, e), a.setOption(), r !== !1 && i.refreshView()
        },
        showRows: function() {
            this.that.options.dataModel.data.forEach(function(t) {
                t.pq_hidden && (t.pq_hidden = void 0)
            })
        },
        renderCell: function(t, e) {
            var r = this.renderTitle(t, e),
                n = this.renderSummary(t);
            return function(t, e) {
                t._render = t._renderG = function(t) {
                    var i = t.rowData,
                        o = i.pq_gtitle;
                    return e && o ? r(t) : o || i.pq_gsummary ? n(t) : void 0
                }
            }
        },
        renderSummary: function(t) {
            var e = this.that;
            return function(r) {
                var n, i, o, a = (r.rowData, r.column),
                    l = a.summary;
                return l && (i = l.type) ? (o = t.summaryTitle[i], "function" == typeof o ? o.call(e, r) : (n = r.formatVal, null == n && (n = r.cellData, n = null == n ? "" : n), "number" != typeof n || a.format || parseInt(n) === n || (n = n.toFixed(2)), o ? o.replace("{0}", n) : n)) : void 0
            }
        },
        renderTitle: function(t, e) {
            var r = this.that,
                n = t.bootstrap,
                i = e.indent || 0,
                o = n.on,
                a = o ? n.groupModel.icon : e.icon,
                l = o ? ["glyphicon " + a[0], "glyphicon " + a[1]] : ["ui-icon " + a[0], "ui-icon " + a[1]];
            return function(t) {
                var n, o, a, s, d = t.rowData;
                return null != t.cellData ? (n = d.pq_close, o = d.pq_level, a = e.title, a = a[o] || e.titleDefault, a = "function" == typeof a ? a.call(r, t) : a.replace("{0}", t.cellData).replace("{1}", d.pq_items), s = n ? 1 : 0, {
                    text: (t.Export ? "" : "<span style='margin-left:" + i * o + "px;' class='pq-group-icon " + l[s] + "'></span>") + a,
                    cls: "pq-group-title-cell",
                    style: "text-align:left;"
                }) : void 0
            }
        },
        removeGroup: function(t) {
            for (var e = this, r = e.that, n = 0, i = r.options.groupModel, o = i.dataIndx; n < o.length; n++)
                if (t === o[n]) {
                    o.splice(n, 1);
                    break
                }
            o.length || (e.showRows(), e.mc.length = 0), e._triggerChange = !0, e.refreshFull()
        },
        refreshColumns: function() {
            for (var t, e, r, n = this.that, i = n.options, o = i.groupModel, a = o.on, l = o.fixCols, s = this.renderCell(i, o), d = o.dataIndx, c = d.length, h = n.colModel, u = h.length; u--;) t = h[u], t._renderG && (delete t._render, delete t._renderG), t._nodrag && (delete t._nodrag, delete t._nodrop), a && (e = t.summary) && e.type && s(t);
            if (i.geditor = a ? this.editorSummary(i, o) : void 0, a)
                if (o.titleInFirstCol) t = this.firstCol(), s(t, !0);
                else
                    for (u = c - 1; u >= 0; u--) t = n.getColumn({
                        dataIndx: d[u]
                    }), s(t, !0);
            if (l && a)
                for (u = 0; c > u; u++) r = n.getColIndx({
                    dataIndx: d[u]
                }), t = h[r], t._nodrag = t._nodrop = !0, r != u && (n.iDragColumns.moveColumn(r, u, !0), n.iColModel.init())
        },
        refreshFull: function() {
            var t = this.that;
            this._triggerChange && (t._trigger("groupChange"), this._triggerChange = !1), t.refreshView()
        },
        refreshView: function() {
            this.that.refreshView()
        },
        showHideRows: function(t, e, r) {
            for (var n, i = this.that, o = !0, a = i.pdata, l = t, s = a.length; s > l; l++)
                if (n = a[l], n.pq_gsummary) r.merge || r.summaryInTitleRow ? n.pq_level >= e && (n.pq_hidden = o) : n.pq_level > e && (n.pq_hidden = o);
                else if (n.pq_gtitle) {
                if (n.pq_level <= e) break;
                n.pq_hidden = o
            } else n.pq_hidden = o
        },
        saveState: function(t) {
            var e = this.that,
                r = e.options.groupModel;
            if (r.on && r.dataIndx.length) {
                for (var n = e.pdata, i = n.length, o = new Array(i), a = 0; i > a; a++) o[a] = n[a];
                this.pdata = o, t && e.refreshView()
            }
        },
        setSumCols: function(e) {
            var r = t.inArray,
                n = [],
                i = [];
            return this.that.colModel.forEach(function(t) {
                var o, a = t.summary;
                a && a.type && (o = t.dataIndx, -1 === r(o, e) && (n.push(t), i.push(o)))
            }), this._sumCols = n, this._sumDIs = i, [n, i]
        },
        summary: function(t, e) {
            var r = pq.aggregate,
                n = t.map(function(t) {
                    return t.summary.type
                });
            return function(i, o, a) {
                e.forEach(function(e, l) {
                    var s, d = [];
                    i.forEach(function(t, r) {
                        d[r] = t[e]
                    }), s = r[n[l]](d, t[l]), null == o[e] && (o[e] = s), a && (a[e] = s)
                })
            }
        },
        setOption: function() {
            var t = this;
            t._init && (t.refreshColumns(), t.summaryData.length = 0, t.tree.length = 0, t.initmerge())
        },
        toggleLevel: function(e, r) {
            var n = this.that.options.groupModel,
                i = n.collapsed,
                o = t.inArray(e, n.dataIndx),
                a = r.ctrlKey ? "All" : "",
                l = i[o];
            this[(l ? "expand" : "collapse") + a](o)
        },
        trigger: function(t) {
            var e, r, n, o, a = t.evt,
                l = t.rd,
                s = t.level,
                d = t.all,
                c = t.close,
                h = this.that,
                u = h.options.groupModel,
                f = u.dataIndx,
                p = u.collapsed,
                g = i.beforeTrigger(a, h),
                v = {};
            if (l) {
                if (e = l.pq_level, r = f[e], n = l[r], c = !l.pq_close, v = {
                        level: e,
                        close: c,
                        group: n
                    }, g(v)) return !1;
                l.pq_close = c
            } else if (d) {
                if (v = {
                        all: !0,
                        close: c,
                        level: s
                    }, g(v)) return !1;
                for (o = s; o < f.length; o++) p[o] = c
            } else if (null != s) {
                if (v = {
                        level: s,
                        close: c
                    }, g(v)) return !1;
                p[s] = c
            }
            return h._trigger("group", null, v)
        },
        toggleRow: function(t, e) {
            var r = this.that,
                n = r.pdata,
                i = n[t];
            this.trigger({
                evt: e,
                rd: i
            }) !== !1 && this.saveState(!0)
        }
    };
    var o = n.pqGrid.prototype;
    o.Group = function(t) {
        var e = this.iGroup;
        return null == t ? e : void e.expandTo(t.indx)
    }
}(jQuery),
function(t) {
    "use strict";
    var e = t.paramquery;
    t(document).on("pqGrid:bootup", function(t, e) {
        var n = e.instance;
        n.iFillHandle = new r(n)
    }), e.pqGrid.defaults.fillHandle = "all", e.pqGrid.defaults.autofill = !0;
    var r = e.cFillHandle = function(t) {
        var e = this;
        e.$wrap, e.locked, e.sel, e.that = t, t.on("selectChange", e.onSelectChange(e)).on("selectEnd", e.onSelectEnd(e)).on("refresh refreshRow resizeTable", e.onRefresh(e))
    };
    r.prototype = {
        onSelectChange: function(t) {
            return function() {
                this.options.fillHandle && t.create()
            }
        },
        onSelectEnd: function(t) {
            return function() {
                this.options.fillHandle && (t.setDraggable(), t.setDoubleClickable())
            }
        },
        onRefresh: function(t) {
            var e;
            return function() {
                this.options.fillHandle && (clearTimeout(e), e = setTimeout(function() {
                    t.that.element && (t.create(), t.setDraggable())
                }, 50))
            }
        },
        remove: function() {
            var t = this.$wrap;
            t && t.remove()
        },
        create: function() {
            var e = this;
            if (!e.locked) {
                e.remove();
                var r = e.that,
                    n = r.Selection().address();
                if (1 === n.length) {
                    var n = n[0],
                        i = n.r2,
                        o = n.c2,
                        a = {
                            rowIndx: i,
                            colIndx: o
                        },
                        l = r.iMerge,
                        s = l.ismergedCell(i, o),
                        d = s ? l.getRootCell(i, o, "a") : a,
                        c = r.getCell(d);
                    if (c.length && r._trigger("beforeFillHandle", null, r.normalize(d)) !== !1) {
                        var h = c[0],
                            u = c.closest(".pq-grid-cont-inner"),
                            f = u[0],
                            p = f.offsetTop,
                            g = f.offsetLeft,
                            v = h.offsetLeft + h.offsetWidth - 8 - g,
                            m = h.offsetTop + h.offsetHeight - 8 - p,
                            w = t("<div class='pq-fill-handle'></div>").appendTo(u);
                        w.css({
                            position: "absolute",
                            top: m,
                            left: v,
                            height: 10,
                            width: 10,
                            background: "#333",
                            cursor: "crosshair",
                            border: "2px solid #fff"
                        }), e.$wrap = w
                    }
                }
            }
        },
        setDoubleClickable: function() {
            var t = this,
                e = t.$wrap;
            e && e.on("dblclick", t.onDblClick(t.that, t))
        },
        setDraggable: function() {
            var t = this,
                e = t.$wrap,
                r = t.that.$cont;
            e && e.draggable({
                helper: function() {
                    return "<div style='height:10px;width:10px;cursor:crosshair;'></div>"
                },
                appendTo: r,
                start: function() {
                    t.onStart()
                },
                drag: function(e) {
                    t.onDrag(e)
                },
                stop: function() {
                    t.onStop()
                }
            })
        },
        patternDate: function(t) {
            var e = this;
            return function(r) {
                var n = new Date(t);
                return n.setDate(n.getDate() + (r - 1)), e.formatDate(n)
            }
        },
        formatDate: function(t) {
            return t.getMonth() + 1 + "/" + t.getDate() + "/" + t.getFullYear()
        },
        patternDate2: function(t, e) {
            var r, n = new Date(t),
                i = new Date(e),
                o = this,
                a = i.getDate() - n.getDate(),
                l = i.getMonth() - n.getMonth(),
                s = i.getFullYear() - n.getFullYear();
            return !l && !s || !a && !l || !s && !a ? function(e) {
                var r = new Date(t);
                return a ? r.setDate(r.getDate() + a * (e - 1)) : l ? r.setMonth(r.getMonth() + l * (e - 1)) : r.setFullYear(r.getFullYear() + s * (e - 1)), o.formatDate(r)
            } : (n = Date.parse(n), r = Date.parse(i) - n, function(t) {
                var e = new Date(n + r * (t - 1));
                return o.formatDate(e)
            })
        },
        pattern: function(t, e) {
            if ("date" == e || "integer" == e || "float" == e) {
                var r, n, i, o = t.length,
                    a = "date" === e;
                return 1 === o ? (r = t[0], a ? this.patternDate(r) : function(t) {
                    return r + (t - 1)
                }) : 2 === o ? a ? this.patternDate2(t[0], t[1]) : (r = t[1] - t[0], n = t[0] - r, function(t) {
                    return r * t + n
                }) : 3 === o ? (r = (t[2] - 2 * t[1] + t[0]) / 2, n = t[1] - t[0] - 3 * r, i = t[0] - r - n, function(t) {
                    return r * t * t + n * t + i
                }) : !1
            }
        },
        autofillVal: function(t, e, r, n) {
            var i, o, a, l, s, d = this.that,
                c = t.r1,
                h = t.c1,
                u = t.r2,
                f = t.c2,
                p = e.r1,
                g = e.c1,
                v = e.r2,
                m = e.c2,
                w = [];
            if (n) {
                for (l = {
                        r1: c,
                        r2: u
                    }, l.c1 = h > g ? g : f + 1, l.c2 = h > g ? h - 1 : m, s = g - h, o = g; m >= o; o++)
                    if (s++, h > o || o > f)
                        for (i = 0, a = c; u >= a; a++) w.push(r[i](s, o)), i++
            } else
                for (l = {
                        c1: h,
                        c2: f
                    }, l.r1 = c > p ? p : u + 1, l.r2 = c > p ? c - 1 : v, s = p - c, o = p; v >= o; o++)
                    if (s++, c > o || o > u)
                        for (i = 0, a = h; f >= a; a++) w.push(r[i](s, o)), i++; return d.Range(l).value(w), !0
        },
        autofill: function(t, e) {
            var r, n, i, o, a, l, s, d, c = this.that,
                h = c.colModel,
                u = c.get_p_data(),
                f = [],
                p = t.r1,
                g = t.c1,
                v = t.r2,
                m = t.c2,
                w = e.c1 != g || e.c2 != m;
            if (w) {
                for (a = p; v >= a; a++) {
                    if (s = {
                            sel: {
                                r: a,
                                c: g
                            },
                            x: !0
                        }, c._trigger("autofillSeries", null, s), !(d = s.series)) return;
                    f.push(d)
                }
                return this.autofillVal(t, e, f, w)
            }
            for (l = g; m >= l; l++) {
                for (r = h[l], n = r.dataType, o = r.dataIndx, i = [], a = p; v >= a; a++) i.push(u[a][o]);
                if (s = {
                        cells: i,
                        sel: {
                            r1: p,
                            c: l,
                            r2: v,
                            r: p
                        }
                    }, c._trigger("autofillSeries", null, s), !(d = s.series || this.pattern(i, n))) return;
                f.push(d)
            }
            return this.autofillVal(t, e, f)
        },
        onStop: function() {
            var t = this,
                e = t.that,
                r = e.options.autofill,
                n = t.sel,
                i = e.Selection().address()[0];
            n.r1 == i.r1 && n.c1 == i.c1 && n.r2 == i.r2 && n.c2 == i.c2 || (t.locked = !1, r && t.autofill(n, i) || e.Range(n).copy({
                dest: i
            }))
        },
        onStart: function() {
            this.locked = !0, this.sel = this.that.Selection().address()[0]
        },
        onDrag: function(e) {
            var r = this,
                n = r.that,
                i = n.options.fillHandle,
                o = "all" == i,
                a = o || "horizontal" == i,
                l = o || "vertical" == i,
                s = e.clientX - 10,
                d = e.clientY,
                c = document.elementFromPoint(s, d),
                h = t(c).closest(".pq-grid-cell");
            if (h.length) {
                var u = n.getCellIndices({
                        $td: h
                    }),
                    f = r.sel,
                    p = f.r1,
                    g = f.c1,
                    v = f.r2,
                    m = f.c2,
                    w = {
                        r1: p,
                        c1: g,
                        r2: v,
                        c2: m
                    },
                    x = function(t, e) {
                        w[t] = e, n.Range(w).select()
                    },
                    y = u.rowIndx,
                    _ = u.colIndx;
                o && v >= y && y >= p || a && !l ? _ > m ? x("c2", _) : g > _ && x("c1", _) : l && (y > v ? x("r2", y) : p > y && x("r1", y))
            }
        },
        onDblClick: function(t, e) {
            return function() {
                var r = t.options,
                    n = r.fillHandle;
                if ("all" == n || "vertical" == n) {
                    for (var i, o = t.Selection().address()[0], a = o.c2, l = o.r2 + 1, s = r.dataModel.data, d = t.getColModel()[a].dataIndx; i = s[l];) {
                        if (null != i[d] && "" !== i[d]) {
                            l--;
                            break
                        }
                        l++
                    }
                    e.onStart(), t.Range({
                        r1: o.r1,
                        c1: o.c1,
                        r2: l,
                        c2: a
                    }).select(), e.onStop()
                }
            }
        }
    }
}(jQuery),
function(t) {
    "use strict";
    var e = t.paramquery;
    t(document).on("pqGrid:bootup", function(t, e) {
        new r(e.instance)
    });
    var r = e.cScroll = function(e) {
        var r = this,
            n = t(document),
            i = ".pqgrid-csroll";
        r.that = e, e.one("refresh", r.oneRefresh(e, r, n, i))
    };
    e.cScroll = r;
    var n = r.prototype;
    n.oneRefresh = function(e, r, n, i) {
        return function() {
            e.$cont.on("mousedown", function(e) {
                t(e.target).closest(".pq-sb").length || (n.on("mousemove" + i, function(t) {
                    r.onMouseDrag(t)
                }), n.on("mouseup" + i, function() {
                    n.off(i)
                }))
            })
        }
    }, n.onMouseDrag = function(t) {
        var e = this,
            r = e.that,
            n = r.$cont,
            i = n[0].offsetHeight,
            o = n[0].offsetWidth,
            a = n.offset(),
            l = a.top,
            s = a.left,
            d = l + i,
            c = s + o,
            h = t.pageY,
            u = t.pageX,
            f = h - d,
            p = u - c,
            g = l - h,
            v = s - u;
        u > s && c > u && (f > 0 || g > 0) ? f > 0 ? e.scrollV(f, !0) : g > 0 && e.scrollV(g) : h > l && d > h && (p > 0 ? e.scrollH(p, !0) : v > 0 && e.scrollH(v))
    }, n.scrollH = function(t, e) {
        var r = this,
            n = r.that.options.virtualX;
        r[n ? "scrollVirtual" : "scrollNV"](t, e, !0)
    }, n.scrollV = function(t, e) {
        var r = this,
            n = r.that.options.virtualY;
        r[n ? "scrollVirtual" : "scrollNV"](t, e)
    }, n.scrollVirtual = function(t, e, r) {
        var n = this.that,
            i = r ? n.hscrollbar() : n.vscrollbar(),
            o = i.options,
            a = o.cur_pos,
            l = o.num_eles,
            s = Math.ceil(t / 10),
            d = Math.pow(5, s - 1) * (e ? 1 : -1),
            a = a + d;
        0 > a ? a = 0 : a >= l && (a = l - 1), i.option("cur_pos", a).scroll()
    }, n.scrollNV = function(t, e, r) {
        var n = this.that,
            i = n.$tbl[0],
            o = i[r ? "offsetWidth" : "offsetHeight"],
            a = r ? n.hscrollbar() : n.vscrollbar(),
            l = a.options,
            s = l.ratio,
            d = s * o,
            c = d + (e ? t : -t),
            h = c / o;
        h > 1 ? h = 1 : 0 > h && (h = 0), a.option("ratio", h).drag()
    }
}(jQuery),
function(t) {
    "use strict";
    var e = t.paramquery;
    e.cFormula = function(t) {
        var e = this;
        e.that = t, e.oldF = [], t.one("ready", function() {
            t.on("CMInit", e.onCMInit(e))
        }).on("dataAvailable", function() {
            e.onDA()
        }).on(!0, "change", function(t, r) {
            e.onChange(r)
        })
    }, e.cFormula.prototype = {
        onCMInit: function(t) {
            return function() {
                t.isFormulaChange(t.oldF, t.formulas()) && t.calcMainData()
            }
        },
        callRow: function(t, e, r) {
            var n = this.that,
                i = 0;
            if (t)
                for (i = 0; r > i; i++) {
                    var o = e[i],
                        a = o[0],
                        l = o[1];
                    t[a.dataIndx] = l.call(n, t, a)
                }
        },
        onDA: function() {
            this.calcMainData()
        },
        isFormulaChange: function(t, e) {
            var r = !1,
                n = 0,
                i = t.length,
                o = e.length;
            if (i == o) {
                for (; i > n; n++)
                    if (t[n][0] != e[n][0]) {
                        r = !0;
                        break
                    }
            } else r = !0;
            return r
        },
        calcMainData: function() {
            var t = this.formulaSave(),
                e = this.that,
                r = t.length;
            if (r) {
                for (var n = e.options, i = n.dataModel.data, o = i.length; o--;) this.callRow(i[o], t, r);
                e._trigger("formulaComputed")
            }
        },
        onChange: function(t) {
            var e = this.formulas(),
                r = e.length,
                n = this,
                i = function(t) {
                    n.callRow(t.rowData, e, r)
                };
            r && (t.addList.forEach(i), t.updateList.forEach(i))
        },
        formulas: function() {
            var t, e, r = this.that,
                n = [],
                i = r.options.formulas || [];
            return i.forEach(function(i) {
                t = r.getColumn({
                    dataIndx: i[0]
                }), t && (e = i[1], e && n.push([t, e]))
            }), n
        },
        formulaSave: function() {
            var t = this.formulas();
            return this.oldF = t, t
        }
    }
}(jQuery),
function(t) {
    "use strict";
    var e = t.paramquery;
    e.pqGrid.defaults.treeModel = {
        cbId: "pq_tree_cb",
        childstr: "children",
        iconCollapse: ["ui-icon-triangle-1-se", "ui-icon-triangle-1-e"],
        iconFolder: ["ui-icon-folder-open", "ui-icon-folder-collapsed"],
        iconFile: "ui-icon-document",
        id: "id",
        indent: 18,
        parentId: "parentId",
        refreshOnChange: !0
    }, e.pqGrid.prototype.Tree = function() {
        return this.iTree
    }, t(document).on("pqGrid:bootup", function(t, e) {
        var n = e.instance;
        n.iTree = new r(n)
    });
    var r = e.cTree = function(t) {
        this.that = t, this.fns = {}, this.init(), this.cache = {}, this.di_prev
    };
    r.prototype = {
        _cascadeNest: function(t, e) {
            for (var r, n, i, o = this, a = o.cbId, l = o.prop, s = o.parentId, d = o.childstr, c = t.length, h = 0; c > h; h++) n = t[h], n[l] && (r = !0, o.eachChild(n, o.chkEachChild(a, e, n[a], l)), delete n[l]), (i = n[d]) && i.length && o._cascadeNest(i, e);
            r && null != n[s] && o.eachParent(n, o.chkEachParent(a, e, l))
        },
        addNodes: function(t, e) {
            var r, n, i = this,
                o = i.that,
                a = o.options.dataModel,
                l = i.parentId,
                s = e ? e[i.id] : null,
                d = 0,
                c = [];
            if (t) {
                for (r = t.length; r > d; d++) n = t[d], null != s && (n[l] = s), c.push({
                    newRow: n
                });
                o._digestData({
                    addList: c,
                    history: !1
                }), a.data = i.groupById(a.data), i.buildCache(), o.refreshView()
            }
        },
        buildCache: function() {
            for (var t, e, r = this, n = r.that.options, i = n.dataModel.data, o = r.cache, a = r.id, l = 0, s = i.length; s > l; l++) {
                if (t = i[l], e = t[a], null == e) throw "unknown id of row";
                o[e] = t
            }
        },
        checkNodes: function(t, e, r) {
            for (var n, i, o, a = null == r ? !0 : r, l = 0, s = t.length, d = [], c = {}, h = this, u = h.that, f = u.riOffset, p = h.cbId, g = h.prop, v = u.options.treeModel, m = v.cascade, w = v.select; s > l; l++) n = t[l], i = n.pq_ri, d.push({
                rowData: n,
                rowIndx: i,
                rowIndxPage: i - f
            });
            if (c.rows = d, u._trigger("beforeCheck", e, c) !== !1) {
                for (d = c.rows, s = d.length, l = 0; s > l; l++) n = d[l].rowData, n[p] = a, w && (n.pq_rowselect = a), m && (n[g] = !0);
                o = h.getRoots(), m && h._cascadeNest(o, w), u._trigger("check", e, c), u.refresh()
            }
        },
        chkEachChild: function(t, e, r, n) {
            return function(i) {
                n && i[n] || (i[t] = r, e && (i.pq_rowselect = r))
            }
        },
        chkEachParent: function(t, e) {
            var r = this.childstr;
            return function(n) {
                for (var i, o, a = n[r], l = 0, s = 0, d = 0, c = a.length; c > d; d++) {
                    if (o = a[d][t]) l++;
                    else {
                        if (null === o) {
                            i = null;
                            break
                        }
                        s++
                    }
                    if (l && s) {
                        i = null;
                        break
                    }
                }
                void 0 === i && (i = !!l), n[t] = i, e && (n.pq_rowselect = i)
            }
        },
        collapseAll: function(t) {
            this[t ? "expandNodes" : "collapseNodes"](this.that.options.dataModel.data)
        },
        collapseNodes: function(t, e, r) {
            for (var n, i, o = 0, a = this.that, l = t.length, s = [], d = !r; l > o; o++) n = t[o], this.isFolder(n) && this.isCollapsed(n) !== d && s.push(n);
            if (s.length && (i = {
                    close: d,
                    nodes: s
                }, a._trigger("beforeTreeExpand", e, i) !== !1)) {
                for (l = s.length, o = 0; l > o; o++) n = s[o], n.pq_close = d;
                a._trigger("treeExpand", e, i), a.refreshView()
            }
        },
        eachParent: function(t, e) {
            for (; t = this.getParent(t);) e(t)
        },
        eachChild: function(t, e) {
            e(t);
            for (var r, n = this.childstr, i = t[n] || [], o = 0, a = i.length; a > o; o++) r = i[o], e(r), r[n] && this.eachChild(r, e)
        },
        expandAll: function() {
            this.collapseAll(!0)
        },
        expandNodes: function(t, e) {
            this.collapseNodes(t, e, !0)
        },
        expandTo: function(t) {
            var e = [];
            do t.pq_close && e.push(t); while (t = this.getParent(t));
            this.expandNodes(e)
        },
        exportCell: function(t, e) {
            for (var r = "", n = 0; e > n; n++) r += "- ";
            return r + (null == t ? "" : t)
        },
        filter: function(t, e, r, n, i, o) {
            for (var a, l, s, d, c = this.childstr, h = 0, u = t.length; u > h; h++) a = t[h], l = !1, (d = a[c]) && (l = this.filter(d, e, r, n, i, o), l && (s = !0, i.push(a))), l || (r.isMatchRow(a, e, n) ? (s = !0, i.push(a)) : o.push(a));
            return s
        },
        _flatten: function(t, e, r, n) {
            for (var i, o, a = this, l = t.length, s = a.id, d = a.parentId, c = 0, h = a.childstr; l > c; c++) i = t[c], i.pq_level = r, n.push(i), e && (i[d] = e[s]), o = i[h], o && a._flatten(o, i, r + 1, n)
        },
        flatten: function(t) {
            var e = [];
            return this._flatten(t, null, 0, e), e
        },
        getFormat: function() {
            for (var t, e, r = this, n = r.that.options.dataModel.data, i = "flat", o = 0, a = n.length, l = r.parentId, s = r.childstr; a > o && (t = n[o], null == t[l]); o++)
                if ((e = t[s]) && e.length) return r.getParent(e[0]) == t ? "flat" : "nested";
            return i
        },
        getAllChildren: function(t, e) {
            for (var r, n = this.childstr, i = t[n] || [], o = i.length, a = 0, l = e || []; o > a; a++) r = i[a], l.push(r), r[n] && this.getAllChildren(r, l);
            return l
        },
        getCheckedNodes: function() {
            for (var t, e = this.that.options.dataModel.data, r = e.length, n = 0, i = [], o = this.cbId; r > n; n++) t = e[n], t[o] && i.push(t);
            return i
        },
        getLevel: function(t) {
            return t.pq_level
        },
        getNode: function(t) {
            return this.cache[t]
        },
        getParent: function(t) {
            var e = t[this.parentId];
            return this.cache[e]
        },
        getRoots: function(t) {
            for (var e, r = this.that, n = t || r.options.dataModel.data, i = n.length, o = 0, a = []; i > o; o++) e = n[o],
                e.pq_level || a.push(e);
            return a
        },
        _groupById: function(t, e, r, n, i) {
            for (var o, a = this, l = a.childstr, s = 0, d = r.length; d > s; s++) {
                var c = r[s],
                    h = c[e];
                c.pq_level = i, t.push(c), (o = n[h]) ? (c[l] = o, a._groupById(t, e, o, n, i + 1)) : delete c[l]
            }
        },
        groupById: function(t) {
            for (var e, r, n, i = this, o = i.id, a = i.parentId, l = {}, s = [], d = 0, c = t.length; c > d; d++) n = t[d], e = n[a], null == e && (e = ""), (r = l[e]) || (r = l[e] = []), r.push(n);
            return i._groupById(s, o, l[""] || [], l, 0), s
        },
        init: function() {
            var t = this,
                e = t.that,
                r = e.options,
                n = r.treeModel,
                i = n.cbId,
                o = t.dataIndx = n.dataIndx;
            t.cbId = i, t.prop = "pq_tree_prop", t.id = n.id, t.parentId = n.parentId, t.childstr = n.childstr, o ? t._init || (t.on("CMInit", t.onColInit(t, e, n)).on("dataAvailable", t.onDataAvailable(t, e, n)).on("dataReady", t.onDataReady(t, e, n)).on("beforeCellKeyDown", t.onBeforeCellKeyDown(t, e)).on("customSort", t.onCustomSort(t, e)).on("customFilter", t.onCustomFilter(t, e)).on("clearFilter", t.onClearFilter(t)).on("change", t.onChange(t, e, n)).on("cellClick", t.onCellClick(t, e)).on("refresh refreshRow", t.onRefresh(t, n)).on("valChange", t.onCheckbox(t, n)), t._init = !0) : t._init && (this.off(), t._init = !1), t._init && (r.groupModel.on = n.summary)
        },
        initData: function() {
            var t = this,
                e = t.that,
                r = e.options,
                n = r.dataModel,
                i = n.data;
            i = "flat" == t.getFormat() ? t.groupById(i) : t.flatten(i), n.data = i, t.buildCache()
        },
        isFolder: function(t) {
            return null != t.pq_close || !!t[this.childstr]
        },
        isCollapsed: function(t) {
            return !!t.pq_close
        },
        off: function() {
            var t, e = this.fns,
                r = this.that;
            for (t in e) r.off(t, e[t]);
            this.fns = {}
        },
        on: function(t, e) {
            return this.fns[t] = e, this.that.on(t, e), this
        },
        onCustomSort: function(t) {
            return function(e, r) {
                var n = t.getRoots(r.data);
                return t.sort(n, r.sort_composite), r.data = t.flatten(n), !1
            }
        },
        onColInit: function(t) {
            return function() {
                t.setCellRender()
            }
        },
        onCellClick: function(e) {
            return function(r, n) {
                n.dataIndx == e.dataIndx && t(r.originalEvent.target).hasClass("pq-group-icon") && e.toggleNode(n.rowData, r)
            }
        },
        onBeforeCellKeyDown: function(e, r) {
            return function(n, i) {
                var o, a, l = i.rowData,
                    s = i.dataIndx,
                    d = n.keyCode,
                    c = t.ui.keyCode;
                if (s == e.dataIndx) {
                    if (e.isFolder(l) && (a = l.pq_close, d == c.ENTER && !r.isEditableCell({
                            rowData: l,
                            dataIndx: s
                        }) || !a && d == c.LEFT || a && d == c.RIGHT)) return e.toggleNode(l), !1;
                    if (d == c.SPACE && (o = r.getCell(i).find("input[type='checkbox']"), o.length)) return o.click(), !1
                }
            }
        },
        onChange: function(t, e, r) {
            return function() {
                r.summary && r.refreshOnChange && e.refreshView()
            }
        },
        onRefresh: function(t, e) {
            return function() {
                if (e.checkbox)
                    for (var t = this.$cont.find(".pq_indeter"), r = t.length; r--;) t[r].indeterminate = !0
            }
        },
        onClearFilter: function(t) {
            return function(e, r) {
                return r.data = t.groupById(r.data), !1
            }
        },
        onCustomFilter: function(t, e) {
            return function(r, n) {
                var i = t.groupById(n.data),
                    o = e.iFilterData,
                    a = n.filters,
                    l = [],
                    s = [],
                    d = n.mode;
                return t.filter(t.getRoots(i), a, o, d, l, s), n.dataTmp = t.groupById(l), n.dataUF = s, !1
            }
        },
        onCheckbox: function(t, e) {
            return function(r, n) {
                e.checkbox && n.dataIndx == e.dataIndx && t.checkNodes([n.rowData], r, n.input.checked)
            }
        },
        onDataAvailable: function(t) {
            return function() {
                t.initData()
            }
        },
        onDataReady: function(t, e, r) {
            return function() {
                r.summary && t.summary(t), t.showHideRows()
            }
        },
        option: function(e, r) {
            var n, i = this,
                o = i.that,
                a = o.options.treeModel,
                l = a.dataIndx;
            t.extend(a, e), n = a.dataIndx, i.setCellRender(), i.init(), !l && n && i.initData(), r !== !1 && o.refreshView()
        },
        _summary: function(t, e, r, n, i, o) {
            for (var a, l, s, d, c, h = this, u = h.childstr, f = 0, p = t.length, g = 0, v = {}, m = {}, w = h.id, x = h.parentId, y = r.length, _ = pq.aggregate; y > g; g++) c = r[g], v[c] = [];
            for (; p > f; f++)
                for (l = t[f], a = null, e.push(l), (s = l[u]) && (a = h._summary(s, e, r, n, i, l)), g = 0; y > g; g++) c = r[g], a && v[c].push(a[c]), v[c].push(l[c]);
            for (g = 0; y > g; g++) c = r[g], d = n[g], m[c] = _[d](v[c], i[g]);
            return l.pq_level && (m.pq_level = l.pq_level, m.pq_gsummary = !0, o && (m[x] = o[w]), e.push(m)), m
        },
        summary: function(t) {
            for (var e, r, n = t.that, i = t.getRoots(), o = [], a = [], l = [], s = [], d = 0, c = n.colModel, h = c.length; h > d; d++) e = c[d], r = e.summary, r && r.type && (l.push(e.dataIndx), s.push(e), a.push(r.type));
            t._summary(i, o, l, a, s), n.pdata = o
        },
        _iconCls: function(t, e, r) {
            if (r.icons) {
                var n;
                if (e && (n = r.iconFolder)) return t.pq_close ? n[1] : n[0];
                if (!t.pq_gsummary) return r.iconFile
            }
        },
        renderCB: function(t, e, r, n) {
            if (e.pq_gsummary) return "";
            var i = this.that,
                o = "",
                a = "";
            return "function" == typeof t && (t = t.call(i, e)), t ? (e[n] && (o = "checked"), null === e[n] && (a = "class='pq_indeter'"), "<input type='checkbox' " + r + " " + a + " " + o + "/>") : void 0
        },
        renderCell: function(t, e) {
            return function(r) {
                var n, i, o, a, l, s = r.rowData,
                    d = t.that,
                    c = e.indent,
                    h = e.render,
                    u = e.iconCollapse,
                    f = e.checkbox,
                    p = t.isFolder(s),
                    g = t._iconCls(s, p, e),
                    v = s.pq_level || 0,
                    m = v * c,
                    w = m + 1 * c,
                    x = ["pq-group-title-cell"],
                    y = ["text-indent:", p ? m : w, "px;"],
                    _ = "style='width:" + c + "px;'",
                    b = r.cellData;
                if (h) {
                    var I = d.callFn(h, r);
                    null != I && ("string" != typeof I ? (I.iconCls && (g = I.iconCls), null != I.text && (b = I.text), a = I.attr, x.push(I.cls), y.push(I.style)) : b = I)
                }
                return r.Export ? t.exportCell(b, v) : (f && (l = t.renderCB(f, s, _, e.cbId)), p && (i = s.pq_close ? u[1] : u[0], n = "<span " + _ + " class='pq-group-icon ui-icon " + i + "'></span>"), g && (o = "<span " + _ + " class='pq-tree-icon ui-icon " + g + "'></span>"), {
                    cls: x.join(" "),
                    attr: a,
                    style: y.join(""),
                    text: [n, o, l, b].join("")
                })
            }
        },
        setCellRender: function() {
            var t, e, r = this,
                n = r.that,
                i = n.options.treeModel,
                o = n.columns;
            i.summary && n.iGroup.refreshColumns(), (t = r.di_prev) && (e = o[t], e && (e._render = null), r.di_prev = null), (t = i.dataIndx) && (e = o[t], e._render = r.renderCell(r, i), r.di_prev = t)
        },
        _showHideRows: function(t, e, r) {
            for (var n, i, o, a = this, l = e || a.getRoots(), s = a.childstr, d = r || !1, c = l.length, h = 0; c > h; h++) n = l[h], n.pq_hidden = d, (o = n[s]) && (i = d || n.pq_close, a._showHideRows(t, o, i))
        },
        showHideRows: function() {
            var t, e, r = this,
                n = r.that,
                i = 0,
                o = n.get_p_data(),
                a = n.options.treeModel.summary;
            if (r._showHideRows(o), a)
                for (o = n.pdata, t = o.length; t > i; i++) e = o[i], e.pq_gsummary && (e.pq_hidden = r.getParent(e).pq_hidden)
        },
        sort: function(t, e) {
            var r = this.childstr;
            ! function n(t) {
                t.sort(e);
                for (var i, o = t.length, a = 0; o > a; a++)(i = t[a][r]) && n(i)
            }(t)
        },
        toggleNode: function(t, e) {
            this[t.pq_close ? "expandNodes" : "collapseNodes"]([t], e)
        },
        unCheckNodes: function(t, e) {
            this.checkNodes(t, e, !1)
        }
    }
}(jQuery),
function(t) {
    "use strict";
    var e = t.paramquery,
        r = e.pqGrid.prototype,
        n = function(t) {
            this.that = t;
            var e = t.options;
            this.options = e, this.selection = [], this.hclass = " pq-state-select " + (e.bootstrap.on ? "" : "ui-state-highlight")
        };
    e.cRows = n, r.SelectRow = function() {
        return this.iRows
    }, n.prototype = {
        _add: function(t, e) {
            var r, n = this.that,
                i = t.rowIndxPage,
                o = !e,
                a = t.rowData,
                l = this.inViewRow(i);
            return !a.pq_hidden && l && (r = n.getRow(t), r.length && (r[o ? "addClass" : "removeClass"](this.hclass), !o && r.removeAttr("tabindex"))), a.pq_rowselect = o, t
        },
        _data: function(t) {
            t = t || {};
            var e = this.that,
                r = t.all,
                n = e.riOffset,
                i = r ? 0 : n,
                o = e.get_p_data(),
                a = r ? o.length : e.pdata.length,
                l = i + a;
            return [o, i, l]
        },
        add: function(t) {
            var e = t.addList = t.rows || [{
                rowIndx: t.rowIndx
            }];
            t.isFirst && this.setFirst(e[0].rowIndx), this.update(t)
        },
        extend: function(t) {
            var e, r, n, i, o, a = t.rowIndx,
                l = [],
                s = this.getFirst();
            if (null != s) {
                if (o = this.isSelected({
                        rowIndx: s
                    }), null == o) return;
                for (s > a ? (s = [a, a = s][0], n = s, i = a - 1) : (n = s + 1, i = a), e = n; i >= e; e++) r = {
                    rowIndx: e
                }, l.push(r);
                this.update(o ? {
                    addList: l
                } : {
                    deleteList: l
                })
            }
        },
        getFirst: function() {
            return this._firstR
        },
        getSelection: function() {
            for (var t, e = this.that, r = e.get_p_data(), n = 0, i = r.length, o = []; i > n; n++) t = r[n], t.pq_rowselect && o.push({
                rowIndx: n,
                rowData: t
            });
            return o
        },
        inViewRow: function(t) {
            var e = this.that,
                r = e.options,
                n = r.freezeRows,
                i = e.finalV;
            return n > t ? !0 : t >= e.initV && i >= t
        },
        isSelected: function(t) {
            var e = t.rowData || this.that.getRowData(t);
            return e ? e.pq_rowselect === !0 : null
        },
        isSelectedAll: function(t) {
            for (var e, r = this._data(t), n = r[0], i = r[1], o = r[2]; o > i; i++)
                if (e = n[i], e && !e.pq_rowselect) return !1;
            return !0
        },
        removeAll: function(t) {
            this.selectAll(t, !0)
        },
        remove: function(t) {
            var e = t.deleteList = t.rows || [{
                rowIndx: t.rowIndx
            }];
            t.isFirst && this.setFirst(e[0].rowIndx), this.update(t)
        },
        selectAll: function(t, e) {
            for (var r, n = this.that, i = [], o = n.riOffset, a = this._data(t), l = a[0], s = a[1], d = a[2]; d > s; s++) r = l[s], r && i.push({
                rowIndx: s,
                rowIndxPage: s - o,
                rowData: r
            });
            this.update(e ? {
                deleteList: i
            } : {
                addList: i
            }, !0)
        },
        setFirst: function(t) {
            this._firstR = t
        },
        toRange: function() {
            for (var t, e, r, n = [], i = this.that, o = i.get_p_data(), a = 0, l = o.length; l > a; a++) t = o[a], t.pq_rowselect ? null != e ? r = a : e = r = a : null != e && (n.push({
                r1: e,
                r2: r
            }), e = r = null);
            return null != e && n.push({
                r1: e,
                r2: r
            }), i.Range(n)
        },
        toggle: function(t) {
            this[this.isSelected(t) ? "remove" : "add"](t)
        },
        toggleAll: function(t) {
            this[this.isSelectedAll(t) ? "removeAll" : "selectAll"](t)
        },
        update: function(t, e) {
            var r = this,
                n = r.that,
                i = {
                    source: t.source
                },
                o = function(t) {
                    return e ? t : n.normalizeList(t)
                },
                a = o(t.addList || []),
                l = o(t.deleteList || []);
            if (a = a.filter(function(t) {
                    return r.isSelected(t) === !1
                }), l = l.filter(function(t) {
                    return r.isSelected(t)
                }), a.length || l.length) {
                if (i.addList = a, i.deleteList = l, n._trigger("beforeRowSelect", null, i) === !1) return;
                i.addList.forEach(function(t) {
                    r._add(t)
                }), i.deleteList.forEach(function(t) {
                    r._add(t, !0)
                }), n._trigger("rowSelect", null, i)
            }
        }
    }
}(jQuery),
function(t) {
    "use strict";
    var e = t.paramquery;
    t(document).on("pqGrid:bootup", function(t, e) {
        var n = e.instance;
        n.iImport = new r(n)
    }), e.pqGrid.prototype.importWb = function(t) {
        return this.iImport.importWb(t)
    };
    var r = e.cImport = function(t) {
        this.that = t
    };
    r.prototype = {
        fillRows: function(t, e, r) {
            for (var n = t.length; e > n; n++) t.push(r ? {} : [])
        },
        generateCols: function(t, e, r) {
            var n, i, o = pq.toLetter,
                a = [],
                l = 0,
                s = pq.excel.colWidth,
                d = r ? r.cells : [],
                c = [];
            for (d.forEach(function(t, e) {
                    var r = t.indx || e;
                    c[r] = t.value
                }), e = e || [], e.forEach(function(t, e) {
                    var r = t.indx || e;
                    a[r] = {
                        hidden: t.hidden,
                        width: t.width,
                        title: c[r] || ""
                    }
                }), t = Math.max(t, e.length); t > l; l++) n = a[l] || {}, i = {
                title: n.title || o(l),
                width: n.width || s,
                halign: "center"
            }, n.hidden && (i.hidden = !0), a[l] = i;
            return a
        },
        toRC: function(t) {
            var e = t.match(/([A-Z]+)(\d+)/),
                r = pq.toNumber(e[1]),
                n = e[2] - 1;
            return [n, r]
        },
        getAddress: function(t) {
            var e = t.split(":"),
                r = this.toRC(e[0]),
                n = r[0],
                i = r[1],
                o = this.toRC(e[1]),
                a = o[0],
                l = o[1],
                s = a - n + 1,
                d = l - i + 1;
            return {
                r1: n,
                c1: i,
                rc: s,
                cc: d
            }
        },
        importS: function(t, e, r, n, i) {
            var o, a, l, s, d, c, h = t.mergeCells,
                u = this,
                f = [],
                p = this.that,
                g = 0,
                v = t.rows,
                m = t.frozenRows || 0,
                w = v.length,
                x = 0,
                y = p.options.colModel,
                _ = y && y.length;
            for (null != i && (c = v[i], v = v.slice(i + 1), m -= i + 1, m = m > 0 ? m : 0), x = 0, w = v.length; w > x; x++) o = v[x], a = o.indx || x, l = {}, a != x && this.fillRows(f, a, !0), o.cells.forEach(function(t, e) {
                s = t.indx || e, d = n && _ && y[s] ? y[s].dataIndx : s, l[d] = t.value, u.copyStyle(l, d, t), t.format && u.copyFormat(l, d, t.format), t.formula && u.copyFormula(l, d, t.formula), s >= g && (g = s + 1)
            }), o.hidden && (l.pq_hidden = !0), f[a] = l;
            t.name && p.option("title", t.name), e && this.fillRows(f, f.length + e, !0), p.option("dataModel.data", f), g += r || 0, p.refreshCM(this.generateCols(g, t.columns, c)), p.option("mergeCells", (h || []).map(function(t) {
                return u.getAddress(t)
            })), p.option({
                freezeRows: m,
                freezeCols: t.frozenCols
            }), p.refreshDataAndView(), p._trigger("importWb")
        },
        copyFormula: function(t, e, r) {
            var n = t.pq_fn = t.pq_fn || {};
            n[e] = r
        },
        copyFormat: function(t, e, r) {
            var n = t.pq_format = t.pq_format || {};
            r = pq.isDateFormat(r) ? pq.excelToJui(r) : pq.excelToNum(r), n[e] = r
        },
        copyStyle: function(t, e, r) {
            var n, i, o = [];
            (n = r.font) && o.push("font-family:" + n), (n = r.fontSize) && o.push("font-size:" + n + "px"), (n = r.color) && o.push("color:" + n), (n = r.bgColor) && o.push("background:" + n), r.bold && o.push("font-weight:bold"), r.italic && o.push("font-style:italic"), r.underline && o.push("text-decoration:underline"), (n = r.align) && o.push("text-align:" + n), (n = r.valign) && o.push("vertical-align:" + n), r.wrap && o.push("white-space:normal"), (o = o.join(";")) && (i = t.pq_cellattr = t.pq_cellattr || {}, i[e] = {
                style: o
            })
        },
        importWb: function(t) {
            var e = t.workbook,
                r = t.sheet || 0,
                n = e.sheets.filter(function(t, e) {
                    return r == e || r == t.name
                })[0];
            n && this.importS(n, t.extraRows, t.extraCols, t.keepCM, t.headerRowIndx)
        }
    }
}(jQuery),
function(t) {
    "use strict";
    pq.excelImport = {
        attr: function() {
            var t = new RegExp('([a-z]+)\\s*=\\s*"([^"]*)"', "gi");
            return function(e) {
                e = e || "", e = e.slice(0, e.indexOf(">"));
                var r = {};
                return e.replace(t, function(t, e, n) {
                    r[e] = n
                }), r
            }
        }(),
        cacheStyles: function() {
            var e, r, n, i = this,
                o = t(t.parseXML(i.getStyleText())),
                a = t.extend(!0, {}, i.preDefFormats),
                l = [],
                s = [""],
                d = ["", ""];
            o.find("numFmts>numFmt").each(function(e, r) {
                var n = t(r),
                    i = n.attr("formatCode");
                a[n.attr("numFmtId")] = i
            }), o.find("fills>fill>patternFill>fgColor[rgb]").each(function(e, r) {
                var n = i.getColor(t(r).attr("rgb"));
                d.push(n)
            }), o.find("fonts>font").each(function(n, o) {
                var a = t(o),
                    l = 1 * a.find("sz").attr("val"),
                    d = a.find("name").attr("val"),
                    c = a.find("color").attr("rgb"),
                    h = {};
                return 0 === n ? (e = l, void(r = d.toUpperCase())) : (a.find("b").length && (h.bold = !0), c && (h.color = i.getColor(c)), d && d.toUpperCase() != r && (h.font = d), l && l != e && (h.fontSize = l), a.find("u").length && (h.underline = !0), a.find("i").length && (h.italic = !0), void s.push(h))
            }), o.find("cellXfs>xf").each(function(e, r) {
                var i, o, c, h, u = t(r),
                    f = 1 * u.attr("numFmtId"),
                    p = 1 * u.attr("fillId"),
                    g = u.children("alignment"),
                    v = 1 * u.attr("fontId"),
                    m = v ? s[v] : {},
                    w = {};
                g.length && (i = g.attr("horizontal"), i && (w.align = i), o = g.attr("vertical"), o && (w.valign = o), c = g.attr("wrapText"), "1" == c && (w.wrap = !0)), f && (n = a[f], /(?=.*m.*)(?=.*d.*)(?=.*y.*)/i.test(n) && (n = n.replace(/(\[.*\]|[^mdy\/\-\s])/gi, "")), w.format = n), p && d[p] && (w.bgColor = d[p]);
                for (h in m) w[h] = m[h];
                l.push(w)
            }), i.getStyle = function(t) {
                return l[t]
            }, o = 0
        },
        getMergeCells: function(t) {
            var e = this,
                r = t.match(/<mergeCell\s+.*?(\/>|<\/mergeCell>)/g) || [];
            return r.map(function(t) {
                return e.attr(t).ref
            })
        },
        getFrozen: function(t) {
            var e = this.match(t, /<pane.*?(\/>|<\/pane>)/, 0),
                r = this.attr(e),
                n = 1 * r.xSplit,
                i = 1 * r.ySplit;
            return {
                r: i || 0,
                c: n || 0
            }
        },
        getFormula: function(e) {
            var r = {},
                n = t.paramquery.cFormulas.shiftRC();
            return function(t, i, o) {
                if ("<f" === t.substr(0, 2)) {
                    var a, l = e.match(t, /^<f.*?>(.*?)<\/f>/, 1),
                        s = e.attr(t);
                    return "shared" == s.t && (l ? r[s.si] = {
                        r: i,
                        c: o,
                        f: l
                    } : (a = r[s.si], l = n(a.f, o - a.c, i - a.r))), l
                }
            }
        },
        getCols: function(t) {
            var e = this,
                r = [],
                n = t.match(/<col\s.*?\/>/g) || [],
                i = pq.excel.colRatio;
            return n.forEach(function(t, n) {
                var o, a = e.attr(t),
                    l = 1 * a.min,
                    s = 1 * a.max,
                    d = 1 * a.hidden,
                    c = 1 * a.width;
                for (n = l; s >= n; n++) o = {}, d ? o.hidden = !0 : o.width = 1 * (c * i).toFixed(2), n !== r.length + 1 && (o.indx = n - 1), r.push(o)
            }), r
        },
        getColor: function(t) {
            return "#" + t.slice(2)
        },
        getPath: function(t) {
            return this.paths[t]
        },
        getPathSheets: function() {
            return this.pathSheets
        },
        getFileTextFromKey: function(t) {
            return this.getFileText(this.getPath(t))
        },
        getFileText: function(t) {
            return this.files[t.replace(/^\//, "")].asText()
        },
        getSheetText: function(t) {
            t = t || 0;
            var e = this.pathSheets.filter(function(e, r) {
                return e.name === t || r === t
            })[0].path;
            return this.getFileText(e)
        },
        getStyleText: function() {
            return this.getFileTextFromKey("st")
        },
        getSI: function(t) {
            var e, r = [],
                n = pq.unescapeXml,
                i = 1 * this.attr(this.match(t, /<sst.*?>.*?<\/sst>/, 0)).uniqueCount;
            if (t.replace(/<si>(.*?)<\/si>/g, function(t, i) {
                    e = [], i.replace(/<t.*?>(.*?)<\/t>/g, function(t, r) {
                        e.push(r)
                    }), r.push(n(e.join("")))
                }), i && i !== r.length) throw "si misatch";
            return r
        },
        getWorkBook: function(t, e, r) {
            var n = this,
                i = {};
            e ? i[e] = !0 : "string" == typeof t && (i.base64 = !0), n.files = new JSZip(t, i).files, this.readPaths(), this.cacheStyles();
            var o = this.getPath("ss"),
                a = [],
                l = o ? this.getSI(this.getFileText(o)) : [];
            return n.getPathSheets().forEach(function(t, e) {
                if (!r || r.indexOf(e) > -1 || r.indexOf(t.name) > -1) {
                    var i = n.getFileText(t.path),
                        o = n.match(i, /<sheetData.*?>(.*?)<\/sheetData>/, 1),
                        s = n.getWorkSheet(i, o, l, t.name);
                    a.push(s)
                }
            }), delete n.files, {
                sheets: a
            }
        },
        getWorkSheet: function(t, e, r, n) {
            for (var i, o, a, l, s, d, c, h, u, f, p, g, v, m, w, x, y, _ = this, b = [], I = 0, C = pq.toNumber, q = this.getFormula(_), R = pq.isEmpty, D = pq.formulas, M = pq.isDateFormat, T = _.getMergeCells(t), P = e.match(/<row.*?<\/row>/g) || [], E = 0, S = P.length; S > E; E++) {
                d = {
                    cells: []
                }, w = P[E], v = _.attr(w), y = v.r, x = y ? y - 1 : E, x !== E && (d.indx = x), v.hidden && (d.hidden = !0), c = w.match(/(<c[^<]*?\/>|<c.*?<\/c>)/g) || [];
                for (var k = 0, H = c.length; H > k; k++) {
                    if (o = c[k], m = _.attr(o), h = m.t, g = _.match(o, /<c.*?>(.*?)(<\/c>)?$/, 1), s = {}, "inlineStr" == h ? f = g.match(/<t><!\[CDATA\[(.*?)\]\]><\/t>/)[1] : (f = _.match(g, /<v>(.*?)<\/v>/, 1) || void 0, null != f && (f = "s" == h ? r[f] : "str" == h ? pq.unescapeXml(f) : "b" == h ? "1" == f : D.VALUE(f))), p = m.r, p ? (p = p.replace(/\d+/, ""), p = C(p)) : p = k, I = I > p ? I : p, void 0 !== f && (s.value = f), p !== k && (s.indx = p), a = q(g, x, p), a && (s.formula = pq.unescapeXml(a)), u = m.s, u && (u = this.getStyle(u))) {
                        for (i in u) s[i] = u[i];
                        l = s.format, !a && l && M(l) && (s.value = D.TEXT(f, "m/d/yyyy"))
                    }!R(s) && d.cells.push(s)
                }
                b.push(d)
            }
            var F = {
                    rows: b,
                    name: n
                },
                $ = _.getCols(t),
                A = _.getFrozen(t);
            return T.length && (F.mergeCells = T), $.length && (F.columns = $), A.r && (F.frozenRows = A.r), A.c && (F.frozenCols = A.c), F
        },
        Import: function(t, e) {
            var r, n, i, o = this,
                a = t.file,
                l = t.content,
                s = t.url,
                d = function(r, n) {
                    e(o.getWorkBook(r, t.type || n, t.sheets))
                };
            s ? (n = "?" + Math.random(), window.Uint8Array ? (i = new XMLHttpRequest, i.open("GET", s + n, !0), i.responseType = "arraybuffer", i.onload = function(t) {
                200 == this.status && d(i.response)
            }, i.send()) : JSZipUtils.getBinaryContent(s + n, function(t, e) {
                d(e, "binary")
            })) : a ? (r = new FileReader, r.onload = function(t) {
                d(t.target.result)
            }, r.readAsArrayBuffer(a)) : l && d(l)
        },
        match: function(t, e, r) {
            var n = t.match(e);
            return n ? n[r] : ""
        },
        preDefFormats: {
            1: "0",
            2: "0.00",
            3: "#,##0",
            4: "#,##0.00",
            5: "$#,##0_);($#,##0)",
            6: "$#,##0_);[Red]($#,##0)",
            7: "$#,##0.00_);($#,##0.00)",
            8: "$#,##0.00_);[Red]($#,##0.00)",
            9: "0%",
            10: "0.00%",
            11: "0.00E+00",
            12: "# ?/?",
            13: "# ??/??",
            14: "m/d/yyyy",
            15: "d-mmm-yy",
            16: "d-mmm",
            17: "mmm-yy",
            18: "h:mm AM/PM",
            19: "h:mm:ss AM/PM",
            20: "h:mm",
            21: "h:mm:ss",
            22: "m/d/yyyy h:mm",
            37: "#,##0_);(#,##0)",
            38: "#,##0_);[Red](#,##0)",
            39: "#,##0.00_);(#,##0.00)",
            40: "#,##0.00_);[Red](#,##0.00)",
            45: "mm:ss",
            46: "[h]:mm:ss",
            47: "mm:ss.0",
            48: "##0.0E+0",
            49: "@"
        },
        readPaths: function() {
            var e = this.files,
                r = t(t.parseXML(e["[Content_Types].xml"].asText())),
                n = this.paths = {
                    wb: "sheet.main",
                    ws: "worksheet",
                    st: "styles",
                    ss: "sharedStrings"
                };
            for (var i in n) n[i] = r.find('[ContentType$="' + n[i] + '+xml"]').attr("PartName");
            for (i in e)
                if (/workbook.xml.rels$/.test(i)) {
                    n.wbrels = i;
                    break
                }
            var o = t(this.getFileTextFromKey("wbrels")),
                a = t(this.getFileTextFromKey("wb")),
                l = this.pathSheets = [];
            a.find("sheet").each(function(e, n) {
                var i = t(n),
                    a = i.attr("r:id"),
                    s = i.attr("name"),
                    d = o.find('[Id="' + a + '"]').attr("Target"),
                    c = r.find('Override[PartName$="' + d + '"]').attr("PartName");
                l.push({
                    name: s,
                    rId: a,
                    path: c
                })
            })
        }
    }
}(jQuery),
function(t) {
    "use strict";
    var e = t.paramquery,
        r = e._pqGrid.prototype;
    r.exportExcel = function(t) {
        return t = t || {}, t.format = "xlsx", this.exportData(t)
    }, r.exportCsv = function(t) {
        return t = t || {}, t.format = "csv", this.exportData(t)
    }, r.exportData = function(t) {
        var e = new n(this, t);
        return e.Export(t)
    };
    var n = e.cExport = function(t, e) {
        this.that = t
    };
    n.prototype = t.extend({
        copyStyle: function(t, e) {
            var r, n, i, o, a, l, s;
            "string" == typeof e && (s = e.split(";"), e = {}, s.forEach(function(t) {
                t && (s = t.split(":"), s[0] && s[1] && (e[s[0].trim()] = s[1].trim()))
            })), (r = e.background) && (t.bgColor = r), (n = e["font-size"]) && (t.fontSize = parseFloat(n)), (o = e.color) && (t.color = o), "normal" == e["white-space"] && (t.wrap = !0), (a = e["text-align"]) && (t.align = a), (l = e["vertical-align"]) && (t.valign = l), "bold" == e["font-weight"] && (t.bold = !0), (i = e["font-family"]) && (t.font = i), "italic" == e["font-style"] && (t.italic = !0), "underline" == e["text-decoration"] && (t.underline = !0)
        },
        Export: function(t) {
            var e, r = this,
                n = r.that,
                i = n.options,
                o = i.groupModel,
                a = "remote" == i.pageModel.type,
                l = n.riOffset,
                s = n.iGenerateView,
                d = n.iMerge,
                c = n.colModel,
                h = c.length,
                u = n.headerCells,
                f = u.length,
                p = i.treeModel,
                g = o.on && o.dataIndx.length || a || p.dataIndx && p.summary,
                v = g ? n.pdata : i.dataModel.data,
                v = i.summaryData ? v.concat(i.summaryData) : v,
                m = v.length,
                w = t.render,
                x = !t.noheader,
                y = t.format;
            if (n._trigger("beforeExport", null, t) === !1) return !1;
            if ("xlsx" == y) {
                var _ = r.getWorkbook(c, h, u, f, v, m, a, l, d, w, s, x, t.sheetName);
                return n._trigger("workbookReady", null, {
                    workbook: _
                }) === !1 ? _ : t.workbook ? _ : (t.workbook = _, pq.excel.exportWb(t))
            }
            return "json" == y ? t.data = r.getJsonContent(t, v) : "csv" == y ? t.data = r.getCSVContent(t, c, h, u, f, v, m, a, l, d, w, s, x) : t.data = r.getHtmlContent(t, c, h, u, f, v, m, a, l, d, w, s, x), e = e || r.postRequest(t), n._trigger("exportData", null, t), e
        },
        getTitle: function(t, e) {
            var r = t.title;
            return r ? "function" == typeof r && (r = r.call(this.that, {
                colIndx: e,
                column: t,
                dataIndx: t.dataIndx,
                Export: !0
            })) : r = "", r
        },
        getXlsMergeCells: function(t, e, r, n) {
            t = t.concat(r.getMergeCells(e, this.curPage, n));
            for (var i = [], o = pq.toLetter, a = t.length, l = 0; a > l; l++) {
                var s = t[l];
                s = o(s.c1) + (s.r1 + 1) + ":" + o(s.c2) + (s.r2 + 1), i.push(s)
            }
            return i
        },
        getXlsCols: function(t, e) {
            for (var r, n, i, o = [], a = 0, l = pq.excel.colWidth; e > a; a++) n = t[a], n.copy !== !1 && (i = 1 * (n._width || l).toFixed(2), r = {}, i !== l && (r.width = i), n.hidden && (r.hidden = !0), pq.isEmpty(r) || (o.length !== a && (r.indx = a), o.push(r)));
            return o
        },
        getXlsHeader: function(t, e, r) {
            for (var n = this, i = [], o = 0; e > o; o++) {
                for (var a = t[o], l = [], s = 0, d = a.length; d > s; s++) {
                    var c = a[s];
                    if (c.copy !== !1) {
                        var h = c.o_colspan,
                            u = c.rowSpan,
                            f = n.getTitle(c, s);
                        o > 0 && c == t[o - 1][s] ? f = "" : s > 0 && c == t[o][s - 1] ? f = "" : (h > 1 || u > 1) && r.push({
                            r1: o,
                            c1: s,
                            r2: o + u - 1,
                            c2: s + h - 1
                        }), l.push({
                            value: f,
                            bgColor: "#eeeeee"
                        })
                    }
                }
                i.push({
                    cells: l
                })
            }
            return i
        },
        getXlsBody: function(e, r, n, i, o, a, l, s, d) {
            var c, h, u, f, p, g, v, m, w, x, y, _, b, I, C, q, R, D, M, T = this,
                P = T.that,
                E = [];
            for (h = 0; i > h; h++) {
                for (_ = n[h], y = [], b = o ? h + a : h, I = b - a, m = {
                        rowIndx: b,
                        rowIndxPage: I,
                        rowData: _,
                        Export: !0
                    }, u = 0; r > u; u++) v = e[u], C = v.dataIndx, D = _.pq_cellattr, g = _[C], f = g, v.copy !== !1 && (p = P.getFormula(_, C), c = !1, l.ismergedCell(b, u) && (l.isRootCell(b, u, "o") || (c = !0)), c || p || (m.colIndx = u, m.column = v, m.dataIndx = C, w = T.getRenderVal(m, s, d), f = w[0], x = w[1]), M = T.getCellFormat(_, C) || v.format, M && (pq.isDateFormat(M) ? (f !== g && t.datepicker.formatDate(M, new Date(g)) === f && (f = g), M = pq.juiToExcel(M)) : (f !== g && pq.formatNumber(g, M) === f && (f = g), M = pq.numToExcel(M))), R = {}, void 0 !== f && (R.value = f), D && (D = D[C]) && (D = D.style) && T.copyStyle(R, D), x && T.copyStyle(R, x), p && (R.formula = p), M && (R.format = M), pq.isEmpty(R) || (R.dataIndx = C, y.length !== u && (R.indx = u), y.push(R)));
                q = {}, y.length && (q.cells = y), _.pq_hidden && (q.hidden = !0), pq.isEmpty(q) || (E.length !== h && (q.indx = h), E.push(q))
            }
            return E
        },
        getCellFormat: function(t, e) {
            var r = t.pq_format;
            return r && r[e]
        },
        getWorkbook: function(t, e, r, n, i, o, a, l, s, d, c, h, u) {
            var f, p = this,
                g = p.getXlsCols(t, e),
                v = [],
                m = p.that.options,
                w = m.freezeCols,
                x = m.freezeRows || 0,
                x = h ? n + x : x,
                y = h ? p.getXlsHeader(r, n, v) : [],
                _ = p.getXlsMergeCells(v, h ? n : 0, s, o),
                b = p.getXlsBody(t, e, i, o, a, l, s, d, c),
                I = {
                    columns: g,
                    rows: y.concat(b)
                };
            return _.length && (I.mergeCells = _), (f = y.length) && (I.headerRows = f), x && (I.frozenRows = x), w && (I.frozenCols = w), (u || (u = m.title)) && (I.name = u), {
                sheets: [I]
            }
        },
        getHtmlHeader: function(t, e) {
            for (var r, n, i, o, a, l = this, s = [], d = 0; e > d; d++) {
                var c = t[d],
                    h = null;
                s.push("<tr>");
                for (var u = 0, f = c.length; f > u; u++) r = c[u], n = r.colSpan, !r.hidden && n && r.copy !== !1 && (i = r.rowSpan, d > 0 && r == t[d - 1][u] || h && u > 0 && r == h || (o = l.getTitle(r, u), h = r, a = r.halign || r.align, a = a ? "align=" + a : "", s.push("<th colspan=", n, " rowspan=", i, " ", a, ">", o, "</th>")));
                s.push("</tr>")
            }
            return s.join("")
        },
        getHtmlBody: function(t, e, r, n, i, o, a, l, s) {
            var d, c, h, u, f, p, g, v, m, w, x, y, _, b, I = this,
                C = [];
            for (d = 0; n > d; d++)
                if (m = r[d], !m.pq_hidden) {
                    for (w = i ? d + o : d, x = w - o, v = {
                            rowIndx: w,
                            rowIndxPage: x,
                            rowData: m,
                            Export: !0
                        }, C.push("<tr>"), c = 0; e > c; c++)
                        if (h = t[c], !h.hidden && h.copy !== !1) {
                            if (u = null, f = null, _ = "", a.ismergedCell(w, c)) {
                                if (!(f = a.isRootCell(w, c))) continue;
                                u = a.getRootCell(w, c, "o"), u.Export = !0, p = I.getRenderVal(u, l, s), y = p[0], g = p[1], _ = "rowspan=" + f.rowspan + " colspan=" + f.colspan + " "
                            } else v.colIndx = c, v.column = h, v.dataIndx = h.dataIndx, p = I.getRenderVal(v, l, s), y = p[0], g = p[1];
                            b = h.align, _ += b ? "align=" + b : "", y = null == y ? "" : y, C.push("<td ", _, g ? ' style="' + g + '"' : "", ">", y, "</td>")
                        }
                    C.push("</tr>")
                }
            return C.join("")
        },
        getHtmlContent: function(t, e, r, n, i, o, a, l, s, d, c, h, u) {
            var f = this,
                p = f.that,
                g = t.cssRules || "",
                v = p.element.find(".pq-grid-table"),
                m = v.css("font-family"),
                w = v.css("font-size"),
                x = "table{empty-cells:show;font-family:" + m + ";font-size:" + w + ";border-collapse:collapse;}",
                y = [];
            return y.push("<!DOCTYPE html><html><head>", '<meta charset="utf-8" />', "<title>", t.title ? t.title : "ParamQuery Pro", "</title>", "</head><body>", "<style>", x, "td,th{padding: 5px;border:1px solid #ccc;}", g, "</style>", "<table>"), y.push(u ? f.getHtmlHeader(n, i, e) : ""), y.push(f.getHtmlBody(e, r, o, a, l, s, d, c, h)), y.push("</table></body></html>"), y.join("")
        },
        getCsvHeader: function(t, e, r, n) {
            for (var i, o, a, l = this, s = [], d = [], c = 0; e > c; c++) {
                for (var h = t[c], u = null, f = 0, p = h.length; p > f; f++) i = r[f], i.hidden || i.copy === !1 || (o = h[f], c > 0 && o == t[c - 1][f] ? s.push("") : u && f > 0 && o == u ? s.push("") : (a = l.getTitle(o, f), a = a ? a.replace(/\"/g, '""') : "", u = o, s.push('"' + a + '"')));
                d.push(s.join(n)), s = []
            }
            return d
        },
        getCSVContent: function(t, e, r, n, i, o, a, l, s, d, c, h, u) {
            var f, p, g, v, m, w, x, y, _, b, I, C = this,
                q = t.separator || ",",
                R = [];
            for (I = u ? C.getCsvHeader(n, i, e, q) : [], v = 0; a > v; v++)
                if (x = o[v], !x.pq_hidden) {
                    y = l ? v + s : v, _ = y - s, w = {
                        rowIndx: y,
                        rowIndxPage: _,
                        rowData: x,
                        Export: !0
                    };
                    for (var m = 0; r > m; m++)
                        if (b = e[m], !b.hidden && b.copy !== !1) {
                            p = null, f = null, d.ismergedCell(y, m) ? (f = d.isRootCell(y, m)) ? (p = d.getRootCell(y, m, "o"), p.Export = !0, g = C.getRenderVal(p, c, h)[0]) : g = "" : (w.colIndx = m, w.column = b, w.dataIndx = b.dataIndx, g = C.getRenderVal(w, c, h)[0]);
                            var D = (null == g ? "" : g) + "";
                            D = D.replace(/\"/g, '""'), R.push('"' + D + '"')
                        }
                    I.push(R.join(q)), R = []
                }
            return "\ufeff" + I.join("\n")
        },
        getJsonContent: function(t, e) {
            function r(t, e) {
                return 0 !== (t + "").indexOf("pq_") ? e : void 0
            }
            return t.nostringify ? e : JSON.stringify(e, t.nopqdata ? r : null, t.nopretty ? null : 2)
        },
        postRequest: function(e) {
            var r, n, i = e.format,
                o = e.url,
                a = e.filename || "pqGrid";
            if (e.zip && "xlsx" != i) {
                var l = new JSZip;
                l.file(a + "." + e.format, e.data), r = l.generate({
                    type: "base64",
                    compression: "DEFLATE"
                }), n = !0, i = "zip"
            } else n = !!e.decodeBase, r = e.data;
            return o && t.ajax({
                url: o,
                type: "POST",
                cache: !1,
                data: {
                    pq_ext: i,
                    pq_data: r,
                    pq_decode: n,
                    pq_filename: a
                },
                success: function(e) {
                    o += (o.indexOf("?") > 0 ? "&" : "?") + "pq_filename=" + e, t(document.body).append("<iframe height='0' width='0' frameborder='0' src=\"" + o + '"></iframe>')
                }
            }), r
        }
    }, pq.mixin.render)
}(jQuery);
var objEx = pq.excel = {
    _tmpl: {
        rels: '<?xml version="1.0" encoding="UTF-8" standalone="yes"?><Relationships xmlns="http://schemas.openxmlformats.org/package/2006/relationships"><Relationship Id="rId1" Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/officeDocument" Target="workbook.xml"/></Relationships>'
    },
    eachRow: function(t, e) {
        for (var r = t.rows, n = 0, i = r.length; i > n; n++) e(r[n], n)
    },
    exportWb: function(t) {
        var e = t.workbook,
            r = this._tmpl,
            n = this,
            i = [],
            o = e.sheets,
            a = o.length,
            l = new JSZip;
        l.file("[Content_Types].xml", this.getContentTypes(a)), o.forEach(function(t, e) {
            var r = n.getCols(t.columns),
                o = n.getFrozen(t.frozenRows, t.frozenCols),
                a = n.getBody(t.rows),
                s = n.getMergeCells(t.mergeCells);
            i.push(t.name), l.file("worksheet" + (e + 1) + ".xml", n.getSheet(o, r, a, s))
        }), l.file("workbook.xml", this.getWBook(i)), l.file("styles.xml", n.getStyle());
        var s = l.folder("_rels");
        return s.file(".rels", r.rels), s.file("workbook.xml.rels", this.getWBookRels(a)), t.url ? (t.data = l.generate({
            type: "base64",
            compression: "DEFLATE"
        }), t.decodeBase = !0, pq.postRequest(t)) : l.generate({
            type: t.type || "blob",
            compression: "DEFLATE"
        })
    },
    eachCell: function(t, e) {
        t.forEach(function(t) {
            var r;
            (r = t.cells) ? r.forEach(e): (r = t.rows) && this.eachCell(r, e)
        }, this)
    },
    getArray: function(t) {
        var e = [],
            r = this;
        return this.eachRow(t, function(t) {
            var n = [];
            t.cells.forEach(function(t) {
                n.push(r.getCell(t))
            }), e.push(n)
        }), e
    },
    getBody: function(t) {
        var e, r, n, i, o, a, l, s, d, c, h, u, f, p, g, v, m, w, x, y, _, b, I, C, q, R, D = this,
            M = pq.formulas,
            T = [],
            P = t.length;
        for (e = 0; P > e; e++) {
            for (f = t[e], h = f.cells, C = h.length, q = f.hidden ? 'hidden="1"' : "", n = (f.indx || e) + 1, o = 'r="' + n + '"', T.push("<row " + q + " " + o + ">"), r = 0; C > r; r++) c = h[r], u = c.value, i = c.indx || r, a = "", l = "", o = i === r ? "" : 'r="' + pq.toLetter(i) + n + '"', R = c.format, p = c.bgColor, g = c.color, v = c.font, m = c.fontSize, _ = c.bold, b = c.italic, I = c.underline, w = c.align, x = c.wrap, y = c.valign, d = c.formula, d = d ? "<f>" + pq.escapeXml(d) + "</f>" : "", null == u ? s = "<v></v>" : u == parseFloat(u) ? s = "<v>" + u + "</v>" : R && M.isDate(u) ? s = "<v>" + M.VALUE(u) + "</v>" : "boolean" == typeof u ? (s = "<v>" + (u ? "1" : "0") + "</v>", a = 't="b"') : (a = 't="inlineStr"', s = "<is><t><![CDATA[" + u + "]]></t></is>"), (R || p || g || m || w || y || x || _ || b || I) && (l = 's="' + D.getStyleIndx(R, p, g, v, m, w, y, x, _, b, I) + '"'), T.push("<c " + a + " " + o + " " + l + ">" + d + s + "</c>");
            T.push("</row>")
        }
        return T.join("")
    },
    getCell: function(t) {
        var e = t.format,
            r = t.value;
        return e ? pq.formulas.TEXT(r, e) : r
    },
    getCSV: function(t) {
        var e = [],
            r = this;
        return this.eachRow(t, function(t) {
            var n = [];
            t.cells.forEach(function(t) {
                n.push(r.getCell(t))
            }), e.push(n.join(","))
        }), e.join("\r\n")
    },
    getColor: function() {
        var t = {},
            e = function(t) {
                return 1 === t.length ? "0" + t : t
            };
        return function(r) {
            var n, i, o = t[r];
            if (o || (/^#[0-9,a,b,c,d,e,f]{6}$/i.test(r) ? i = r.replace("#", "") : (n = r.match(/^rgb\((\d{1,3}),(\d{1,3}),(\d{1,3})\)$/i)) && (i = e((1 * n[1]).toString(16)) + e((1 * n[2]).toString(16)) + e((1 * n[3]).toString(16))), i && 6 === i.length && (o = t[r] = "ff" + i)), o) return o;
            throw "invalid color: " + r
        }
    }(),
    _getCol: function(t, e, r, n, i) {
        if (i) {
            if (i == this.colWidth && !n) return;
            i = 1 * (i / this.colRatio).toFixed(2), i = ' customWidth="1" width="' + i + '"'
        }
        t.push('<col min="', e, '" max="', r, '" hidden="', n, '"', i, "/>")
    },
    getCols: function(t) {
        if (!t || !t.length) return "";
        var e, r, n, i, o = [],
            a = 0,
            l = 0,
            s = 0,
            d = t.length;
        for (o.push("<cols>"); d > s; s++) {
            var c = t[s],
                h = c.hidden ? 1 : 0,
                u = c.width,
                f = c.indx;
            a = (f || a) + 1, n === u && i === h && a == l + 1 ? r = a : (null != n && (this._getCol(o, e, r, i, n), e = null), r = a, null == e && (e = a)), n = u, i = h, l = a
        }
        return this._getCol(o, e, r, i, n), o.push("</cols>"), o.join("")
    },
    getContentTypes: function(t) {
        for (var e = [], r = 1; t >= r; r++) e.push('<Override PartName="/worksheet' + r + '.xml" ContentType="application/vnd.openxmlformats-officedocument.spreadsheetml.worksheet+xml"/>');
        return ['<?xml version="1.0" encoding="UTF-8" standalone="yes"?>', '<Types xmlns="http://schemas.openxmlformats.org/package/2006/content-types">', '<Default Extension="rels" ContentType="application/vnd.openxmlformats-package.relationships+xml"/>', '<Override PartName="/workbook.xml" ContentType="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet.main+xml"/>', e.join(""), '<Override PartName="/styles.xml" ContentType="application/vnd.openxmlformats-officedocument.spreadsheetml.styles+xml"/>', "</Types>"].join("")
    },
    getFillIndx: function(t) {
        var e = this,
            r = e.fills = e.fills || {
                length: 2
            };
        return e.getIndx(r, t)
    },
    getFontIndx: function(t, e, r, n, i, o) {
        var a = this,
            l = a.fonts = a.fonts || {
                length: 1
            };
        return a.getIndx(l, (t || "") + "_" + (e || "") + "_" + (r || "") + "_" + (n || "") + "_" + (i || "") + "_" + (o || ""))
    },
    getFormatIndx: function(t) {
        var e = this,
            r = e.formats = e.formats || {
                length: 164
            };
        return e.getIndx(r, t)
    },
    getFrozen: function(t, e) {
        t = t || 0, e = e || 0;
        var r = pq.toLetter(e) + (t + 1);
        return ['<sheetViews><sheetView workbookViewId="0">', '<pane xSplit="', e, '" ySplit="', t, '" topLeftCell="', r, '" activePane="bottomLeft" state="frozen"/>', "</sheetView></sheetViews>"].join("")
    },
    getIndx: function(t, e) {
        var r = t[e];
        return null == r && (r = t[e] = t.length, t.length++), r
    },
    getMergeCells: function(t) {
        t = t || [];
        var e = [],
            r = 0,
            n = t.length;
        for (e.push('<mergeCells count="' + n + '">'); n > r; r++) e.push('<mergeCell ref="', t[r], '"/>');
        return e.push("</mergeCells>"), n ? e.join("") : ""
    },
    getWBook: function(t) {
        return ['<?xml version="1.0" encoding="UTF-8" standalone="yes"?><workbook xmlns="http://schemas.openxmlformats.org/spreadsheetml/2006/main" xmlns:r="http://schemas.openxmlformats.org/officeDocument/2006/relationships"><sheets>', t.map(function(t, e) {
            return e++, ['<sheet name="', t ? pq.escapeXml(t) : "sheet" + e, '" sheetId="', e, '" r:id="rId', e, '"/>'].join("")
        }).join(""), "</sheets></workbook>"].join("")
    },
    getWBookRels: function(t) {
        for (var e = [], r = 1; t >= r; r++) e.push('<Relationship Id="rId' + r + '" Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/worksheet" Target="/worksheet' + r + '.xml"/>');
        return ['<?xml version="1.0" encoding="UTF-8" standalone="yes"?>', '<Relationships xmlns="http://schemas.openxmlformats.org/package/2006/relationships">', e.join(""), '<Relationship Id="rId5" Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/styles" Target="/styles.xml"/>', "</Relationships>"].join("")
    },
    getSheet: function(t, e, r, n) {
        return ['<?xml version="1.0" encoding="UTF-8" standalone="yes"?><worksheet xmlns="http://schemas.openxmlformats.org/spreadsheetml/2006/main" xmlns:r="http://schemas.openxmlformats.org/officeDocument/2006/relationships">', t, e, "<sheetData>", r, "</sheetData>", n, "</worksheet>"].join("")
    },
    getStyleIndx: function(t, e, r, n, i, o, a, l, s, d, c) {
        var h = this,
            u = t ? h.getFormatIndx(t) : "",
            f = e ? h.getFillIndx(e) : "",
            p = r || n || i || s || d || c ? h.getFontIndx(r, n, i, s, d, c) : "",
            g = u + "_" + f + "_" + p + "_" + (o || "") + "_" + (a || "") + "_" + (l || ""),
            v = h.styles = h.styles || {
                length: 1
            };
        return h.getIndx(v, g)
    },
    getStyle: function() {
        var t, e, r, n, i, o, a, l, s, d, c, h, u, f, p, g, v, m, w = this.formats,
            x = this.fills,
            y = this.fonts,
            _ = this.styles,
            b = [],
            I = [],
            C = [],
            q = ['<xf numFmtId="0" applyNumberFormat="1"/>'];
        if (w) {
            delete w.length;
            for (m in w) b.push('<numFmt numFmtId="' + w[m] + '" formatCode="' + m + '"/>');
            delete this.formats
        }
        if (x) {
            delete x.length;
            for (m in x) I.push('<fill><patternFill patternType="solid"><fgColor rgb="' + this.getColor(m) + '"/></patternFill></fill>');
            delete this.fills
        }
        if (y) {
            delete y.length;
            for (m in y) a = m.split("_"), t = "<color " + (a[0] ? 'rgb="' + this.getColor(a[0]) + '"' : 'theme="1"') + " />",
                r = '<name val="' + (a[1] || "Calibri") + '"/>', e = '<sz val="' + (a[2] || 11) + '"/>', n = a[3] ? "<b/>" : "", i = a[4] ? "<i/>" : "", o = a[5] ? "<u/>" : "", C.push("<font>", n, i, o, e, t, r, '<family val="2"/></font>');
            delete this.fonts
        }
        if (_) {
            delete _.length;
            for (m in _) a = m.split("_"), l = a[0], s = a[1], d = a[2], c = a[3], h = a[4], u = a[5], f = s ? ' applyFill="1" fillId="' + s + '" ' : "", g = d ? ' applyFont="1" fontId="' + d + '" ' : "", p = l ? ' applyNumberFormat="1" numFmtId="' + l + '"' : "", c = c ? ' horizontal="' + c + '" ' : "", h = h ? ' vertical="' + h + '" ' : "", u = u ? ' wrapText="1" ' : "", v = c || h || u ? ' applyAlignment="1"><alignment ' + c + h + u + "/></xf>" : "/>", q.push("<xf " + p + f + g + v);
            delete this.styles
        }
        return b = b.join("\n"), q = q.join("\n"), I = I.join("\n"), C = C.join(""), ['<?xml version="1.0" encoding="UTF-8" standalone="yes"?>', '<styleSheet xmlns="http://schemas.openxmlformats.org/spreadsheetml/2006/main">', "<numFmts>", b, "</numFmts>", "<fonts>", '<font><sz val="11"/><color theme="1"/><name val="Calibri"/><family val="2"/><scheme val="minor"/></font>', C, "</fonts>", '<fills><fill><patternFill patternType="none"/></fill><fill><patternFill patternType="gray125"/></fill>', I, "</fills>", '<borders count="1"><border><left/><right/><top/><bottom/><diagonal/></border></borders>', '<cellStyleXfs count="1"><xf numFmtId="0" fontId="0" fillId="0" borderId="0"/>', "</cellStyleXfs>", "<cellXfs>", q, "</cellXfs>", '<cellStyles count="1"><cellStyle name="Normal" xfId="0" builtinId="0"/></cellStyles>', '<dxfs count="0"/><tableStyles count="0" defaultTableStyle="TableStyleMedium9" defaultPivotStyle="PivotStyleLight16"/>', "</styleSheet>"].join("")
    },
    importXl: function() {
        var t = pq.excelImport;
        return t.Import.apply(t, arguments)
    }
};
objEx.colRatio = 8, objEx.colWidth = 8.43 * objEx.colRatio, pq.postRequest = function(t) {
        var e, r, n = t.format,
            i = t.url,
            o = t.filename || "pqGrid";
        if (t.zip && "xlsx" != n) {
            var a = new JSZip;
            a.file(o + "." + t.format, t.data), e = a.generate({
                type: "base64",
                compression: "DEFLATE"
            }), r = !0, n = "zip"
        } else r = !!t.decodeBase, e = t.data;
        return i && $.ajax({
            url: i,
            type: "POST",
            cache: !1,
            data: {
                pq_ext: n,
                pq_data: e,
                pq_decode: r,
                pq_filename: o
            },
            success: function(t) {
                i += (i.indexOf("?") > 0 ? "&" : "?") + "pq_filename=" + t, $(document.body).append("<iframe height='0' width='0' frameborder='0' src=\"" + i + '"></iframe>')
            }
        }), e
    },
    function($) {
        var _pq = $.paramquery;
        _pq.pqGrid.defaults.formulasModel = {
            on: !0
        }, _pq.pqGrid.prototype.getFormula = function(t, e) {
            var r = this.iFormulas.getFnW(t, e);
            return r ? r.fn : void 0
        }, $(document).on("pqGrid:bootup", function(t, e) {
            var r = e.instance,
                n = r.options.formulasModel;
            n.on && (r.iFormulas = new cFormulas(r))
        });
        var cFormulas = _pq.cFormulas = function(t) {
            var e = this;
            e.that = t, e.fn = {}, t.on("dataReadyDone", function() {
                e.onDataReadyDone()
            }).on("columnOrder", function() {
                e.onColumnOrder()
            }).on("beforeValidateDone", function(t, r) {
                e.onBeforeValidateDone(r)
            }).on("autofillSeries", function(t, r) {
                e.onAutofill(r)
            }).on("editorBegin", function(t, r) {
                e.onEditorBegin(r)
            }).on("editorEnd", function() {
                e.onEditorEnd()
            }).on("editorKeyUp editorClick", function(t, r) {
                e.onEditorKeyUp(t, r)
            }).on(!0, "change", function(t, r) {
                e.onChange(r)
            })
        };
        $.extend(cFormulas, {
            deString: function(t, e, r) {
                var n = [];
                return t = t.replace(/"(([^"]|"")+)"/g, function(t, e) {
                    return n.push(e), "#" + (n.length - 1) + "#"
                }), t = e(t), n.forEach(function(e, n) {
                    r && (e = e.replace(/""/g, '\\"')), t = t.replace("#" + n + "#", '"' + e + '"')
                }), t
            },
            selectExp: function(t, e) {
                var r, n, i, o, a = t.slice(0, e).replace(/"[^"]*"/g, "");
                return !/"[^"]+$/.test(a) && (i = t.slice(e), (r = a.match(/.*?([a-z0-9:$]+)$/i)) && ("" === i && (n = []) || (n = i.match(/^([a-z0-9:$]+)?.*/i)))) ? o = (r[1] + (null == n[1] ? "" : n[1])).replace(/\$/g, "").toUpperCase() : void 0
            },
            shiftRC: function(t) {
                var e = cFormulas,
                    r = t ? t.get_p_data().length - 1 : 0,
                    n = t ? t.colModel.length - 1 : 0;
                return function(t, i, o) {
                    return i && (t = e.shiftC(t, i, n)), o && (t = e.shiftR(t, o, r)), t
                }
            },
            shiftR: function(t, e, r) {
                return cFormulas.deString(t, function(t) {
                    return t = t.replace(/(\$?)([A-Z]+)(\$?)([\d]+)/g, function(t, n, i, o, a) {
                        if (o) return t;
                        var l = 1 * a + e - 1;
                        return l = 0 > l ? 0 : r && l > r ? r : l, n + i + (l + 1)
                    }), t.replace(/(\$?)([0-9]+):(\$?)([0-9]+)/g, function(t, n, i, o, a) {
                        var l;
                        return n || (l = 1 * i + e - 1, l = 0 > l ? 0 : r && l > r ? r : l, i = l + 1), o || (l = 1 * a + e - 1, l = 0 > l ? 0 : r && l > r ? r : l, a = l + 1), n + i + ":" + o + a
                    })
                })
            },
            shiftC: function(t, e, r) {
                return cFormulas.deString(t, function(t) {
                    return t = t.replace(/(\$?)([A-Z]+)(\$?)([\d]+)/g, function(t, n, i, o, a) {
                        if (n) return t;
                        var l = pq.toNumber(i) + e;
                        return l = 0 > l ? 0 : r && l > r ? r : l, pq.toLetter(l) + o + a
                    }), t.replace(/(\$?)([A-Z]+):(\$?)([A-Z]+)/g, function(t, n, i, o, a) {
                        var l;
                        return n || (l = pq.toNumber(i) + e, l = 0 > l ? 0 : r && l > r ? r : l, i = pq.toLetter(l)), o || (l = pq.toNumber(a) + e, l = 0 > l ? 0 : r && l > r ? r : l, a = pq.toLetter(l)), n + i + ":" + o + a
                    })
                })
            }
        }), cFormulas.prototype = {
            addRowIndx: function(t) {
                t.forEach(function(t) {
                    var e, r, n = t.newRow,
                        i = n.pq_fn;
                    if (i)
                        for (r in i) e = i[r], e.ri = e.riO = n.pq_ri
                })
            },
            cell: function(t) {
                var e = this.toCell(t),
                    r = e.r,
                    n = e.c;
                return this.valueArr(r, n)[0]
            },
            check: function(t) {
                return cFormulas.deString(t, function(t) {
                    return t = t.split(" ").join(""), t.toUpperCase().replace(/([A-Z]+)([0-9]+)\:([A-Z]+)([0-9]+)/g, function(t, e, r, n, i) {
                        return e = pq.toNumber(e), n = pq.toNumber(n), e > n && (e = [n, n = e][0]), 1 * r > 1 * i && (r = [i, i = r][0]), pq.toLetter(e) + r + ":" + pq.toLetter(n) + i
                    })
                })
            },
            computeAll: function() {
                var t, e = this,
                    r = e.that;
                e.initObj(), e.eachFormula(function(e) {
                    e.clean = 0, t = !0
                }), t && e.eachFormula(function(t, n, i, o, a) {
                    n[i] = e.execIfDirty(t), a && r.isValid({
                        rowIndx: o,
                        rowData: n,
                        dataIndx: i,
                        allowInvalid: !0
                    })
                })
            },
            eachFormula: function(t) {
                var e = this,
                    r = !0,
                    n = e.that,
                    i = function(e, n, i) {
                        var o, a;
                        for (o in i) a = i[o], "string" != typeof a && t(a, e, o, n, r)
                    },
                    o = function(t) {
                        t = t || [];
                        for (var e, r, n = t.length; n--;)(e = t[n]) && (r = e.pq_fn) && i(e, n, r)
                    };
                o(n.get_p_data()), r = !1, o(n.options.summaryData)
            },
            execIfDirty: function(t) {
                if (t.clean) {
                    if (.5 == t.clean) return
                } else t.clean = .5, t.val = this.exec(t.fn, t.ri, t.ci), t.clean = 1;
                return t.val
            },
            exec: function(_fn, r, c) {
                var self = this,
                    obj = self.obj,
                    fn = cFormulas.deString(_fn, function(t) {
                        return t = t.replace(/(\$?([A-Z]+)?\$?([0-9]+)?\:\$?([A-Z]+)?\$?([0-9]+)?)/g, function(t, e) {
                            return obj[e] = obj[e] || self.range(e), "obj['" + e + "']"
                        }), t = t.replace(/(?:[^:]|^)(\$?[A-Z]+\$?[0-9]+)(?!:)/g, function(t, e) {
                            obj[e] = obj[e] || self.cell(e);
                            var r = t.charAt(0);
                            return (t === e ? "" : "$" == r ? "" : r) + e
                        }), t = t.replace(/{/g, "[").replace(/}/g, "]").replace(/(?:[^><])(=+)/g, function(t, e) {
                            return t + (1 === e.length ? "=" : "")
                        }).replace(/<>/g, "!=").replace(/&/g, "+")
                    }, !0);
                with(obj.getRange = function() {
                    return {
                        r1: r,
                        c1: c
                    }
                }, obj) {
                    try {
                        var v = eval(fn);
                        "function" == typeof v ? v = "#NAME?" : "string" == typeof v && cFormulas.deString(v, function(t) {
                            t.indexOf("function") >= 0 && (v = "#NAME?")
                        }), v !== v && (v = null)
                    } catch (ex) {
                        v = "string" == typeof ex ? ex : ex.message
                    }
                    return v
                }
            },
            initObj: function() {
                this.obj = $.extend({
                    iFormula: this
                }, pq.formulas)
            },
            onAutofill: function(t) {
                var e = t.sel,
                    r = this,
                    n = r.that,
                    i = e.r,
                    o = e.c,
                    a = t.x,
                    l = n.getRowData({
                        rowIndx: i
                    }),
                    s = n.colModel,
                    d = s.length - 1,
                    c = n.get_p_data().length - 1,
                    h = s[o].dataIndx,
                    u = r.getFnW(l, h);
                u && (t.series = function(t) {
                    return "=" + (a ? cFormulas.shiftC(u.fn, t - 1, d) : cFormulas.shiftR(u.fn, t - 1, c))
                })
            },
            onBeforeValidateDone: function(t) {
                var e = this,
                    r = this.that.colIndxs,
                    n = function(n) {
                        n.forEach(function(n) {
                            var i, o, a, l = n.newRow,
                                s = n.rowData;
                            for (o in l)
                                if (i = l[o], "string" == typeof i && "=" === i[0]) {
                                    t.allowInvalid = !0;
                                    var d = e.check(i),
                                        c = s ? e.getFnW(s, o) : null;
                                    c ? d !== c.fn && (n.oldRow[o] = "=" + c.fn, e.save(s, o, d, n.rowIndx, r[o])) : e.save(s || l, o, d, n.rowIndx, r[o])
                                } else s && (a = e.remove(s, o)) && (n.oldRow[o] = "=" + a.fn)
                        })
                    };
                n(t.addList), n(t.updateList)
            },
            onChange: function(t) {
                this.addRowIndx(t.addList), t.addList.length || t.deleteList.length || (this.computeAll(), "edit" === t.source && this.that.refresh())
            },
            onColumnOrder: function() {
                var t, e, r = this,
                    n = r.that,
                    i = cFormulas.shiftRC(n),
                    o = n.colIndxs;
                r.eachFormula(function(r, n, a) {
                    t = o[a], r.ci != t && (e = t - r.ciO, r.ci = t, r.fn = i(r.fnOrig, e, r.ri - r.riO))
                }), null != t && r.computeAll()
            },
            onEditorBegin: function(t) {
                var e = this.getFnW(t.rowData, t.dataIndx);
                e && t.$editor.val("=" + e.fn)
            },
            onEditorEnd: function() {
                pq.intel.hide()
            },
            onEditorKeyUp: function(t, e) {
                var r = e.$editor,
                    n = r[0],
                    i = n.value,
                    o = pq.intel,
                    a = n.selectionEnd;
                i && 0 === i.indexOf("=") && (o.popup(i, a, r), this.select(i, a))
            },
            onDataReadyDone: function() {
                var t, e = this,
                    r = e.that,
                    n = cFormulas.shiftRC(r),
                    i = r.colIndxs,
                    o = function(r, o, a) {
                        var l, s, d;
                        for (s in a) l = a[s], t = !0, "string" == typeof l ? e.save(r, s, e.check(l), o, i[s]) : l.ri != o && (d = o - l.riO, l.ri = o, l.fn = n(l.fnOrig, l.ci - l.ciO, d))
                    },
                    a = function(t) {
                        t = t || [];
                        for (var e, r, n = t.length; n--;)(e = t[n]) && (r = e.pq_fn) && o(e, n, r)
                    };
                a(r.get_p_data()), a(r.options.summaryData), e.initObj(), t && e.computeAll()
            },
            getFnW: function(t, e) {
                var r;
                return (r = t.pq_fn) ? r[e] : void 0
            },
            remove: function(t, e) {
                var r, n = t.pq_fn;
                return n && (r = n[e]) ? (delete n[e], pq.isEmpty(n) && delete t.pq_fn, r) : void 0
            },
            range: function(t) {
                var e = t.split(":"),
                    r = this.that,
                    n = this.toCell(e[0]),
                    i = n.r,
                    o = n.c,
                    a = this.toCell(e[1]),
                    l = a.r,
                    s = a.c;
                return this.valueArr(null == i ? 0 : i, null == o ? 0 : o, null == l ? r.get_p_data().length - 1 : l, null == s ? r.colModel.length - 1 : s)
            },
            save: function(t, e, r, n, i) {
                var o, a = r.replace(/^=/, ""),
                    l = {
                        clean: 0,
                        fn: a,
                        fnOrig: a,
                        riO: n,
                        ciO: i,
                        ri: n,
                        ci: i
                    };
                return o = t.pq_fn = t.pq_fn || {}, o[e] = l, l
            },
            selectRange: function(t, e) {
                var r, n, i, o, a = cFormulas.selectExp(t, e);
                return a ? (/^([a-z0-9]+):([a-z0-9]+)$/i.test(a) ? (r = a.split(":"), n = this.toCell(r[0]), i = this.toCell(r[1]), o = {
                    r1: n.r,
                    c1: n.c,
                    r2: i.r,
                    c2: i.c
                }) : /^[a-z]+[0-9]+$/i.test(a) && (n = this.toCell(a), o = {
                    r1: n.r,
                    c1: n.c
                }), o) : void 0
            },
            select: function(t, e) {
                var r = this.selectRange(t, e),
                    n = this.that;
                r ? n.Range(r).select() : n.Selection().removeAll()
            },
            toCell: function(t) {
                var e = t.match(/\$?([A-Z]+)?\$?(\d+)?/);
                return {
                    c: e[1] ? pq.toNumber(e[1]) : null,
                    r: e[2] ? e[2] - 1 : null
                }
            },
            valueArr: function(t, e, r, n) {
                var i, o, a, l, s, d, c = this.that,
                    h = c.colModel,
                    u = h.length,
                    f = [],
                    p = [],
                    g = [],
                    v = c.get_p_data(),
                    m = v.length;
                for (r = null == r ? t : r, n = null == n ? e : n, t = 0 > t ? 0 : t, e = 0 > e ? 0 : e, r = r >= m ? m - 1 : r, n = n >= u ? u - 1 : n, i = t; r >= i; i++) {
                    for (a = v[i], o = e; n >= o; o++) l = h[o].dataIndx, d = (s = this.getFnW(a, l)) ? this.execIfDirty(s) : a[l], f.push(d), g.push(d);
                    p.push(g), g = []
                }
                return f.get2Arr = function() {
                    return p
                }, f.getRange = function() {
                    return {
                        r1: t,
                        c1: e,
                        r2: r,
                        c2: n
                    }
                }, f
            }
        }
    }(jQuery),
    function(t) {
        "use strict";
        var e = window.pq = window.pq || {};
        e.intel = {
            removeFn: function(t) {
                var e, r = t.length;
                return t = t.replace(/[a-z]*\([^()]*\)/gi, ""), e = t.length, r === e ? t : this.removeFn(t)
            },
            removeStrings: function(t) {
                return t = t.replace(/"[^"]*"/g, ""), t.replace(/"[^"]*$/, "")
            },
            getMatch: function(t, r) {
                var n, i = e.formulas,
                    o = [];
                t = t.toUpperCase();
                for (n in i)
                    if (r) {
                        if (n === t) return [n]
                    } else 0 === n.indexOf(t) && o.push(n);
                return o
            },
            intel: function(t) {
                t = this.removeStrings(t), t = this.removeFn(t);
                var e, r, n, i = /^=(.*[,+\-&*\s(><=])?([a-z]+)((\()[^)]*)?$/i;
                return (e = t.match(i)) && (r = e[2], e[4] && (n = !0)), [r, n]
            },
            movepos: function(t) {
                var e;
                return (e = t.match(/([^a-z].*)/i)) ? t.indexOf(e[1]) + 1 : t.length
            },
            intel3: function(t, e) {
                e < t.length && /=(.*[,+\-&*\s(><=])?[a-z]+$/i.test(t.slice(0, e)) && (e += this.movepos(t.slice(e)));
                var r = t.substr(0, e),
                    n = this.intel(r);
                return n
            },
            item: function(t) {
                var e = this.that.options.strFormulas;
                return e = e ? e[t] : null, "<div>" + (e ? e[0] : t) + "</div>" + (e ? "<div style='font-size:0.9em;color:#888;margin-bottom:5px;'>" + e[1] + "</div>" : "")
            },
            popup: function(e, r, n) {
                var i, o, a, l = n.closest(".pq-grid"),
                    s = t(".pq-intel"),
                    d = l,
                    c = this.intel3(e, r);
                this.that = l.pqGrid("instance"), s.remove(), (i = c[0]) && (o = this.getMatch(i, c[1]), a = o.map(this.item, this).join(""), a && t("<div class='pq-intel' style='width:350px;max-height:300px;overflow:auto;background:#fff;border:1px solid gray;box-shadow: 4px 4px 2px #aaaaaa;padding:5px;'></div>").appendTo(d).html(a).position({
                    my: "center top",
                    at: "center bottom",
                    collision: "flipfit",
                    of: n,
                    within: d
                }))
            },
            hide: function() {
                t(".pq-intel").remove()
            }
        }
    }(jQuery),
    function(t) {
        "use strict";
        var e = t.paramquery;
        t(document).on("pqGrid:bootup", function(t, r) {
            var n = r.instance;
            new e.cEditor(n)
        }), e.cEditor = function(t) {
            var e = this;
            e.that = t, t.on("editorBeginDone", function(t, r) {
                r.$td[0].edited = !0, e.fixWidth(r)
            }).on("editorEnd", function(t, r) {
                r.$td[0].edited = !1, cancelAnimationFrame(e.id)
            }).on("editorKeyDown", function(t, r) {
                e.id = requestAnimationFrame(function() {
                    e.fixWidth(r)
                })
            })
        }, e.cEditor.prototype = {
            escape: function(t) {
                return t.replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/</g, "&lt;")
            },
            fixWidth: function(e) {
                var r = this,
                    n = r.that,
                    i = e.$td,
                    o = i[0].offsetWidth,
                    a = e.$editor;
                if ("text" == a[0].type) {
                    var l = r.escape(a.val()),
                        s = n.widget(),
                        d = s.width(),
                        c = t("<span style='position:absolute;top:0;left:0;visibilty:hidden;'><pre>" + l + "</pre></span>").appendTo(s),
                        h = parseInt(c.width()) + 25;
                    c.remove(), h = h > d ? d : h > o ? h : o
                } else h = o;
                a.css("width", h + "px"), r.position(a, s, i)
            },
            position: function(t, e, r) {
                t.closest(".pq-editor-outer").css("border-width", "0").position({
                    my: "left center",
                    at: "left center",
                    of: r,
                    collision: "fit",
                    within: e
                })
            }
        }
    }(jQuery),
    function($) {
        "use strict";
        var f = pq.formulas = {
            evalify: function(t, e) {
                var r, n, i = e.match(/([><=]{1,2})?(.*)/),
                    o = i[1] || "=",
                    a = i[2],
                    l = this;
                return /(\*|\?)/.test(a) ? r = a.replace(/\*/g, ".*").replace(/\?/g, "\\S").replace(/\(/g, "\\(").replace(/\)/g, "\\)") : (o = "=" === o ? "==" : "<>" === o ? "!=" : o, n = this.isNumber(a)), t.map(function(t) {
                    return r ? (t = null == t ? "" : t, t = ("<>" === o ? "!" : "") + "/^" + r + '$/i.test("' + t + '")') : n ? t = l.isNumber(t) ? t + o + a : "false" : (t = null == t ? "" : t, t = '"' + (t + "").toUpperCase() + '"' + o + '"' + (a + "").toUpperCase() + '"'), t
                })
            },
            get2Arr: function(t) {
                return t.get2Arr ? t.get2Arr() : t
            },
            isNumber: function(t) {
                return parseFloat(t) == t
            },
            _reduce: function(t, e) {
                var r = (t.length, []),
                    n = e.map(function(t) {
                        return []
                    });
                return t.forEach(function(t, i) {
                    null != t && (t = 1 * t, isNaN(t) || (r.push(t), n.forEach(function(t, r) {
                        t.push(e[r][i])
                    })))
                }), [r, n]
            },
            reduce: function(t) {
                t = this.toArray(t);
                var e = t.shift(),
                    r = t.filter(function(t, e) {
                        return e % 2 == 0
                    }),
                    n = this._reduce(e, r);
                return e = n[0], r = n[1], [e].concat(t.map(function(e, n) {
                    return n % 2 == 0 ? r[n / 2] : t[n]
                }))
            },
            strDate1: "(\\d{1,2})/(\\d{1,2})/(\\d{2,4})",
            strDate2: "(\\d{4})-(\\d{1,2})-(\\d{1,2})",
            strTime: "(\\d{1,2})(:(\\d{1,2}))?(:(\\d{1,2}))?(\\s(AM|PM))?",
            isDate: function(t) {
                return this.reDate.test(t) && Date.parse(t) || t && t.constructor == Date
            },
            toArray: function(t) {
                for (var e = [], r = 0, n = t.length; n > r; r++) e.push(t[r]);
                return e
            },
            valueToDate: function(t) {
                var e = new Date(Date.UTC(1900, 0, 1));
                return e.setUTCDate(e.getUTCDate() + t - 2), e
            },
            varToDate: function(t) {
                var e, r, n, i, o;
                if (this.isNumber(t)) e = this.valueToDate(t);
                else if (t.getTime) e = t;
                else if ("string" == typeof t) {
                    if ((r = t.match(this.reDateTime)) ? r[12] ? (o = 1 * r[13], i = 1 * r[15], n = 1 * r[14]) : (n = 1 * r[2], i = 1 * r[3], o = 1 * r[4]) : (r = t.match(this.reDate2)) ? (o = 1 * r[1], i = 1 * r[3], n = 1 * r[2]) : (r = t.match(this.reDate1)) && (n = 1 * r[1], i = 1 * r[2], o = 1 * r[3]), !r) throw "#N/A date";
                    t = Date.UTC(o, n - 1, i), e = new Date(t)
                }
                return e
            },
            _IFS: function(arg, fn) {
                for (var len = arg.length, i = 0, arr = [], a = 0; len > i; i += 2) arr.push(this.evalify(arg[i], arg[i + 1]));
                for (var condsIndx = arr[0].length, lenArr = len / 2, j; condsIndx--;) {
                    for (j = 0; lenArr > j && eval(arr[j][condsIndx]); j++);
                    a += j === lenArr ? fn(condsIndx) : 0
                }
                return a
            },
            ABS: function(t) {
                return Math.abs(t.map ? t[0] : t)
            },
            ACOS: function(t) {
                return Math.acos(t)
            },
            AND: function() {
                var arr = this.toArray(arguments);
                return eval(arr.join(" && "))
            },
            ASIN: function(t) {
                return Math.asin(t)
            },
            ATAN: function(t) {
                return Math.atan(t)
            },
            _AVERAGE: function(t) {
                var e = 0,
                    r = 0;
                if (t.forEach(function(t) {
                        parseFloat(t) == t && (r += 1 * t, e++)
                    }), e) return r / e;
                throw "#DIV/0!"
            },
            AVERAGE: function() {
                return this._AVERAGE(pq.flatten(arguments))
            },
            AVERAGEIF: function(t, e, r) {
                return this.AVERAGEIFS(r || t, t, e)
            },
            AVERAGEIFS: function() {
                var t = this.reduce(arguments),
                    e = 0,
                    r = t.shift(),
                    n = this._IFS(t, function(t) {
                        return e++, r[t]
                    });
                if (!e) throw "#DIV/0!";
                return n / e
            },
            TRUE: !0,
            FALSE: !1,
            CEILING: function(t) {
                return Math.ceil(t)
            },
            CHAR: function(t) {
                return String.fromCharCode(t)
            },
            CHOOSE: function() {
                var t = pq.flatten(arguments),
                    e = t[0];
                if (e > 0 && e < t.length) return t[e];
                throw "#VALUE!"
            },
            CODE: function(t) {
                return (t + "").charCodeAt(0)
            },
            COLUMN: function(t) {
                return (t || this).getRange().c1 + 1
            },
            COLUMNS: function(t) {
                var e = t.getRange();
                return e.c2 - e.c1 + 1
            },
            CONCATENATE: function() {
                var t = pq.flatten(arguments),
                    e = "";
                return t.forEach(function(t) {
                    e += t
                }), e
            },
            COS: function(t) {
                return Math.cos(t)
            },
            _COUNT: function(t) {
                var e = pq.flatten(t),
                    r = this,
                    n = 0,
                    i = 0,
                    o = 0;
                return e.forEach(function(t) {
                    null == t || "" === t ? n++ : (i++, r.isNumber(t) && o++)
                }), [n, i, o]
            },
            COUNT: function() {
                return this._COUNT(arguments)[2]
            },
            COUNTA: function() {
                return this._COUNT(arguments)[1]
            },
            COUNTBLANK: function() {
                return this._COUNT(arguments)[0]
            },
            COUNTIF: function(t, e) {
                return this.COUNTIFS(t, e)
            },
            COUNTIFS: function() {
                return this._IFS(arguments, function() {
                    return 1
                })
            },
            DATE: function(t, e, r) {
                if (0 > t || t > 9999) throw "#NUM!";
                return 1899 >= t && (t += 1900), this.VALUE(new Date(Date.UTC(t, e - 1, r)))
            },
            DATEVALUE: function(t) {
                return this.DATEDIF("1/1/1900", t, "D") + 2
            },
            DATEDIF: function(t, e, r) {
                var n, i = this.varToDate(e),
                    o = this.varToDate(t),
                    a = i.getTime(),
                    l = o.getTime(),
                    s = (a - l) / 864e5;
                if ("Y" === r) return parseInt(s / 365);
                if ("M" === r) return n = i.getUTCMonth() - o.getUTCMonth() + 12 * (i.getUTCFullYear() - o.getUTCFullYear()), o.getUTCDate() > i.getUTCDate() && n--, n;
                if ("D" === r) return s;
                throw "unit N/A"
            },
            DAY: function(t) {
                return this.varToDate(t).getUTCDate()
            },
            DAYS: function(t, e) {
                return this.DATEDIF(e, t, "D")
            },
            DEGREES: function(t) {
                return 180 / Math.PI * t
            },
            EOMONTH: function(t, e) {
                e = e || 0;
                var r = this.varToDate(t);
                return r.setUTCMonth(r.getUTCMonth() + e + 1), r.setUTCDate(0), this.VALUE(r)
            },
            EXP: function(t) {
                return Math.exp(t)
            },
            FIND: function(t, e, r) {
                return e.indexOf(t, r ? r - 1 : 0) + 1
            },
            FLOOR: function(t, e) {
                return 0 > t * e ? "#NUM!" : parseInt(t / e) * e
            },
            HLOOKUP: function(t, e, r, n) {
                null == n && (n = !0), e = this.get2Arr(e);
                var i = this.MATCH(t, e[0], n ? 1 : 0);
                return this.INDEX(e, r, i)
            },
            HOUR: function(t) {
                if (Date.parse(t)) {
                    var e = new Date(t);
                    return e.getHours()
                }
                return 24 * t
            },
            IF: function(t, e, r) {
                return t ? e : r
            },
            INDEX: function(t, e, r) {
                return t = this.get2Arr(t), e = e || 1, r = r || 1, "function" == typeof t[0].push ? t[e - 1][r - 1] : t[e > 1 ? e - 1 : r - 1]
            },
            INDIRECT: function(t) {
                var e = this.iFormula;
                return e.cell(t.toUpperCase())
            },
            LARGE: function(t, e) {
                return t.sort(), t[t.length - (e || 1)]
            },
            LEFT: function(t, e) {
                return t.substr(0, e || 1)
            },
            LEN: function(t) {
                return t = (t.map ? t : [t]).map(function(t) {
                    return t.length
                }), t.length > 1 ? t : t[0]
            },
            LOOKUP: function(t, e, r) {
                r = r || e;
                var n = this.MATCH(t, e, 1);
                return this.INDEX(r, 1, n)
            },
            LOWER: function(t) {
                return (t + "").toLocaleLowerCase()
            },
            _MAXMIN: function(t, e) {
                var r, n = this;
                return t.forEach(function(t) {
                    null != t && (t = n.VALUE(t), n.isNumber(t) && (t * e > r * e || null == r) && (r = t))
                }), null != r ? r : 0
            },
            MATCH: function(val, arr, type) {
                var isNumber = this.isNumber(val),
                    _isNumber, sign, indx, _val, i = 0,
                    len = arr.length;
                if (null == type && (type = 1), val = isNumber ? val : val.toUpperCase(), 0 === type) {
                    for (arr = this.evalify(arr, val + ""), i = 0; len > i; i++)
                        if (_val = arr[i], eval(_val)) {
                            indx = i + 1;
                            break
                        }
                } else {
                    for (i = 0; len > i; i++)
                        if (_val = arr[i], _isNumber = this.isNumber(_val), _val = arr[i] = _isNumber ? _val : _val ? _val.toUpperCase() : "", val == _val) {
                            indx = i + 1;
                            break
                        }
                    if (!indx) {
                        for (i = 0; len > i; i++)
                            if (_val = arr[i], _isNumber = this.isNumber(_val), type * (val > _val ? -1 : 1) === 1 && isNumber == _isNumber) {
                                indx = i;
                                break
                            }
                        indx = null == indx ? i : indx
                    }
                }
                if (indx) return indx;
                throw "#N/A"
            },
            MAX: function() {
                var t = pq.flatten(arguments);
                return this._MAXMIN(t, 1)
            },
            MEDIAN: function() {
                var t = pq.flatten(arguments).filter(function(t) {
                        return 1 * t == t
                    }).sort(function(t, e) {
                        return e - t
                    }),
                    e = t.length,
                    r = e / 2;
                return r === parseInt(r) ? (t[r - 1] + t[r]) / 2 : t[(e - 1) / 2]
            },
            MID: function(t, e, r) {
                if (1 > e || 0 > r) throw "#VALUE!";
                return t.substr(e - 1, r)
            },
            MIN: function() {
                var t = pq.flatten(arguments);
                return this._MAXMIN(t, -1)
            },
            MODE: function() {
                var t, e, r = pq.flatten(arguments),
                    n = {},
                    i = 0;
                if (r.forEach(function(r) {
                        t = n[r] = n[r] ? n[r] + 1 : 1, t > i && (i = t, e = r)
                    }), 2 > i) throw "#N/A";
                return e
            },
            MONTH: function(t) {
                return this.varToDate(t).getUTCMonth() + 1
            },
            OR: function() {
                var arr = this.toArray(arguments);
                return eval(arr.join(" || "))
            },
            PI: function() {
                return Math.PI
            },
            POWER: function(t, e) {
                return Math.pow(t, e)
            },
            PRODUCT: function() {
                var t = pq.flatten(arguments),
                    e = 1;
                return t.forEach(function(t) {
                    e *= t
                }), e
            },
            PROPER: function(t) {
                return t = t.replace(/(\S+)/g, function(t) {
                    return t.charAt(0).toUpperCase() + t.substr(1).toLowerCase()
                })
            },
            RADIANS: function(t) {
                return Math.PI / 180 * t
            },
            RAND: function() {
                return Math.random()
            },
            RANK: function(t, e, r) {
                var n = JSON.stringify(e.getRange()),
                    i = this,
                    o = n + "_range";
                e = this[o] || function() {
                    return i[o] = e, e.sort(function(t, e) {
                        return t - e
                    })
                }();
                for (var a = 0, l = e.length; l > a; a++)
                    if (t === e[a]) return r ? a + 1 : l - a
            },
            RATE: function() {},
            REPLACE: function(t, e, r, n) {
                return t += "", t.substr(0, e - 1) + n + t.substr(e + r - 1)
            },
            REPT: function(t, e) {
                for (var r = ""; e--;) r += t;
                return r
            },
            RIGHT: function(t, e) {
                return e = e || 1, t.substr(-1 * e, e)
            },
            _ROUND: function(t, e, r) {
                var n = Math.pow(10, e),
                    i = t * n,
                    o = parseInt(i),
                    a = i - o;
                return r(o, a) / n
            },
            ROUND: function(t, e) {
                return this._ROUND(t, e, function(t, e) {
                    var r = Math.abs(e);
                    return t + (r >= .5 ? r / e : 0)
                })
            },
            ROUNDDOWN: function(t, e) {
                return this._ROUND(t, e, function(t) {
                    return t
                })
            },
            ROUNDUP: function(t, e) {
                return this._ROUND(t, e, function(t, e) {
                    return t + (e ? Math.abs(e) / e : 0)
                })
            },
            ROW: function(t) {
                return (t || this).getRange().r1 + 1
            },
            ROWS: function(t) {
                var e = t.getRange();
                return e.r2 - e.r1 + 1
            },
            SEARCH: function(t, e, r) {
                return t = t.toUpperCase(), e = e.toUpperCase(), e.indexOf(t, r ? r - 1 : 0) + 1
            },
            SIN: function(t) {
                return Math.sin(t)
            },
            SMALL: function(t, e) {
                return t.sort(), t[(e || 1) - 1]
            },
            SQRT: function(t) {
                return Math.sqrt(t)
            },
            _STDEV: function(t) {
                t = pq.flatten(t);
                var e = t.length,
                    r = this._AVERAGE(t),
                    n = 0;
                return t.forEach(function(t) {
                    n += (t - r) * (t - r)
                }), [n, e]
            },
            STDEV: function() {
                var t = this._STDEV(arguments);
                if (1 === t[1]) throw "#DIV/0!";
                return Math.sqrt(t[0] / (t[1] - 1))
            },
            STDEVP: function() {
                var t = this._STDEV(arguments);
                return Math.sqrt(t[0] / t[1])
            },
            SUBSTITUTE: function(t, e, r, n) {
                var i = 0;
                return t.replace(new RegExp(e, "g"), function(t) {
                    return i++, n ? i === n ? r : e : r
                })
            },
            SUM: function() {
                var t = pq.flatten(arguments),
                    e = 0,
                    r = this;
                return t.forEach(function(t) {
                    t = r.VALUE(t), r.isNumber(t) && (e += parseFloat(t))
                }), e
            },
            SUMIF: function(t, e, r) {
                return this.SUMIFS(r || t, t, e)
            },
            SUMIFS: function() {
                var t = this.reduce(arguments),
                    e = t.shift();
                return this._IFS(t, function(t) {
                    return e[t]
                })
            },
            SUMPRODUCT: function() {
                var t = this.toArray(arguments);
                return t = t[0].map(function(e, r) {
                    var n = 1;
                    return t.forEach(function(t) {
                        var e = t[r];
                        n *= parseFloat(e) == e ? e : 0
                    }), n
                }), pq.aggregate.sum(t)
            },
            TAN: function(t) {
                return Math.tan(t)
            },
            TEXT: function(t, e) {
                return this.isNumber(t) && e.indexOf("#") >= 0 ? pq.formatNumber(t, e) : $.datepicker.formatDate(pq.excelToJui(e), this.varToDate(t))
            },
            TIME: function(t, e, r) {
                return (t + e / 60 + r / 3600) / 24
            },
            TIMEVALUE: function(t) {
                var e = t.match(this.reTime);
                if (e && null != e[1] && (null != e[3] || null != e[7])) var r = 1 * e[1],
                    n = 1 * (e[3] || 0),
                    i = 1 * (e[5] || 0),
                    o = (e[7] || "").toUpperCase(),
                    a = r + n / 60 + i / 3600;
                if (a >= 0 && (o && 13 > a || !o && 24 > a)) return "PM" == o && 12 > r ? a += 12 : "AM" == o && 12 == r && (a -= 12), a / 24;
                throw "#VALUE!"
            },
            TODAY: function() {
                var t = new Date;
                return this.VALUE(new Date(Date.UTC(t.getFullYear(), t.getMonth(), t.getDate())))
            },
            TRIM: function(t) {
                return t.replace(/^\s+|\s+$/gm, "")
            },
            TRUNC: function(t, e) {
                return e = Math.pow(10, e || 0), ~~(t * e) / e
            },
            UPPER: function(t) {
                return (t + "").toLocaleUpperCase()
            },
            VALUE: function(t) {
                var e, r;
                if (t)
                    if (parseFloat(t) == t) r = parseFloat(t);
                    else if (this.isDate(t)) r = this.DATEVALUE(t);
                else if (e = t.match(this.reDateTime)) {
                    var n = e[1] || e[12],
                        i = t.substr(n.length + 1);
                    r = this.DATEVALUE(n) + this.TIMEVALUE(i)
                } else(e = t.match(this.reTime)) ? r = this.TIMEVALUE(t) : (r = t.replace(/[^0-9\-.]/g, ""), r = r.replace(/(\.[1-9]*)0+$/, "$1").replace(/\.$/, ""));
                else r = 0;
                return r
            },
            VAR: function() {
                var t = this._STDEV(arguments);
                return t[0] / (t[1] - 1)
            },
            VARP: function() {
                var t = this._STDEV(arguments);
                return t[0] / t[1]
            },
            VLOOKUP: function(t, e, r, n) {
                null == n && (n = !0), e = this.get2Arr(e);
                var i = e.map(function(t) {
                        return t[0]
                    }),
                    o = this.MATCH(t, i, n ? 1 : 0);
                return this.INDEX(e, o, r)
            },
            YEAR: function(t) {
                return this.varToDate(t).getUTCFullYear()
            }
        };
        f.reDate1 = new RegExp("^" + f.strDate1 + "$"), f.reDate2 = new RegExp("^" + f.strDate2 + "$"), f.reDate = new RegExp("^" + f.strDate1 + "$|^" + f.strDate2 + "$"), f.reTime = new RegExp("^" + f.strTime + "$", "i"), f.reDateTime = new RegExp("^(" + f.strDate1 + ")\\s" + f.strTime + "$|^(" + f.strDate2 + ")\\s" + f.strTime + "$")
    }(jQuery);