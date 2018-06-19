! function(t, e) {
    if ("object" == typeof exports && "object" == typeof module) module.exports = e();
    else if ("function" == typeof define && define.amd) define([], e);
    else {
        var n = e();
        for (var r in n)("object" == typeof exports ? exports : t)[r] = n[r]
    }
}(window, function() {
    return function(t) {
        var e = {};

        function n(r) {
            if (e[r]) return e[r].exports;
            var a = e[r] = {
                i: r,
                l: !1,
                exports: {}
            };
            return t[r].call(a.exports, a, a.exports, n), a.l = !0, a.exports
        }
        return n.m = t, n.c = e, n.d = function(t, e, r) {
            n.o(t, e) || Object.defineProperty(t, e, {
                configurable: !1,
                enumerable: !0,
                get: r
            })
        }, n.r = function(t) {
            Object.defineProperty(t, "__esModule", {
                value: !0
            })
        }, n.n = function(t) {
            var e = t && t.__esModule ? function() {
                return t.default
            } : function() {
                return t
            };
            return n.d(e, "a", e), e
        }, n.o = function(t, e) {
            return Object.prototype.hasOwnProperty.call(t, e)
        }, n.p = "", n(n.s = 397)
    }({
        112: function(t, e, n) {
            var r, a = n(28),
                o = {};
            o.create = function() {
                var t = new a.ARRAY_TYPE(4);
                return t[0] = 0, t[1] = 0, t[2] = 0, t[3] = 0, t
            }, o.clone = function(t) {
                var e = new a.ARRAY_TYPE(4);
                return e[0] = t[0], e[1] = t[1], e[2] = t[2], e[3] = t[3], e
            }, o.fromValues = function(t, e, n, r) {
                var o = new a.ARRAY_TYPE(4);
                return o[0] = t, o[1] = e, o[2] = n, o[3] = r, o
            }, o.copy = function(t, e) {
                return t[0] = e[0], t[1] = e[1], t[2] = e[2], t[3] = e[3], t
            }, o.set = function(t, e, n, r, a) {
                return t[0] = e, t[1] = n, t[2] = r, t[3] = a, t
            }, o.add = function(t, e, n) {
                return t[0] = e[0] + n[0], t[1] = e[1] + n[1], t[2] = e[2] + n[2], t[3] = e[3] + n[3], t
            }, o.subtract = function(t, e, n) {
                return t[0] = e[0] - n[0], t[1] = e[1] - n[1], t[2] = e[2] - n[2], t[3] = e[3] - n[3], t
            }, o.sub = o.subtract, o.multiply = function(t, e, n) {
                return t[0] = e[0] * n[0], t[1] = e[1] * n[1], t[2] = e[2] * n[2], t[3] = e[3] * n[3], t
            }, o.mul = o.multiply, o.divide = function(t, e, n) {
                return t[0] = e[0] / n[0], t[1] = e[1] / n[1], t[2] = e[2] / n[2], t[3] = e[3] / n[3], t
            }, o.div = o.divide, o.min = function(t, e, n) {
                return t[0] = Math.min(e[0], n[0]), t[1] = Math.min(e[1], n[1]), t[2] = Math.min(e[2], n[2]), t[3] = Math.min(e[3], n[3]), t
            }, o.max = function(t, e, n) {
                return t[0] = Math.max(e[0], n[0]), t[1] = Math.max(e[1], n[1]), t[2] = Math.max(e[2], n[2]), t[3] = Math.max(e[3], n[3]), t
            }, o.scale = function(t, e, n) {
                return t[0] = e[0] * n, t[1] = e[1] * n, t[2] = e[2] * n, t[3] = e[3] * n, t
            }, o.scaleAndAdd = function(t, e, n, r) {
                return t[0] = e[0] + n[0] * r, t[1] = e[1] + n[1] * r, t[2] = e[2] + n[2] * r, t[3] = e[3] + n[3] * r, t
            }, o.distance = function(t, e) {
                var n = e[0] - t[0],
                    r = e[1] - t[1],
                    a = e[2] - t[2],
                    o = e[3] - t[3];
                return Math.sqrt(n * n + r * r + a * a + o * o)
            }, o.dist = o.distance, o.squaredDistance = function(t, e) {
                var n = e[0] - t[0],
                    r = e[1] - t[1],
                    a = e[2] - t[2],
                    o = e[3] - t[3];
                return n * n + r * r + a * a + o * o
            }, o.sqrDist = o.squaredDistance, o.length = function(t) {
                var e = t[0],
                    n = t[1],
                    r = t[2],
                    a = t[3];
                return Math.sqrt(e * e + n * n + r * r + a * a)
            }, o.len = o.length, o.squaredLength = function(t) {
                var e = t[0],
                    n = t[1],
                    r = t[2],
                    a = t[3];
                return e * e + n * n + r * r + a * a
            }, o.sqrLen = o.squaredLength, o.negate = function(t, e) {
                return t[0] = -e[0], t[1] = -e[1], t[2] = -e[2], t[3] = -e[3], t
            }, o.inverse = function(t, e) {
                return t[0] = 1 / e[0], t[1] = 1 / e[1], t[2] = 1 / e[2], t[3] = 1 / e[3], t
            }, o.normalize = function(t, e) {
                var n = e[0],
                    r = e[1],
                    a = e[2],
                    o = e[3],
                    i = n * n + r * r + a * a + o * o;
                return i > 0 && (i = 1 / Math.sqrt(i), t[0] = n * i, t[1] = r * i, t[2] = a * i, t[3] = o * i), t
            }, o.dot = function(t, e) {
                return t[0] * e[0] + t[1] * e[1] + t[2] * e[2] + t[3] * e[3]
            }, o.lerp = function(t, e, n, r) {
                var a = e[0],
                    o = e[1],
                    i = e[2],
                    u = e[3];
                return t[0] = a + r * (n[0] - a), t[1] = o + r * (n[1] - o), t[2] = i + r * (n[2] - i), t[3] = u + r * (n[3] - u), t
            }, o.random = function(t, e) {
                return e = e || 1, t[0] = a.RANDOM(), t[1] = a.RANDOM(), t[2] = a.RANDOM(), t[3] = a.RANDOM(), o.normalize(t, t), o.scale(t, t, e), t
            }, o.transformMat4 = function(t, e, n) {
                var r = e[0],
                    a = e[1],
                    o = e[2],
                    i = e[3];
                return t[0] = n[0] * r + n[4] * a + n[8] * o + n[12] * i, t[1] = n[1] * r + n[5] * a + n[9] * o + n[13] * i, t[2] = n[2] * r + n[6] * a + n[10] * o + n[14] * i, t[3] = n[3] * r + n[7] * a + n[11] * o + n[15] * i, t
            }, o.transformQuat = function(t, e, n) {
                var r = e[0],
                    a = e[1],
                    o = e[2],
                    i = n[0],
                    u = n[1],
                    c = n[2],
                    f = n[3],
                    s = f * r + u * o - c * a,
                    l = f * a + c * r - i * o,
                    d = f * o + i * a - u * r,
                    v = -i * r - u * a - c * o;
                return t[0] = s * f + v * -i + l * -c - d * -u, t[1] = l * f + v * -u + d * -i - s * -c, t[2] = d * f + v * -c + s * -u - l * -i, t[3] = e[3], t
            }, o.forEach = (r = o.create(), function(t, e, n, a, o, i) {
                var u, c;
                for (e || (e = 4), n || (n = 0), c = a ? Math.min(a * e + n, t.length) : t.length, u = n; u < c; u += e) r[0] = t[u], r[1] = t[u + 1], r[2] = t[u + 2], r[3] = t[u + 3], o(r, r, i), t[u] = r[0], t[u + 1] = r[1], t[u + 2] = r[2], t[u + 3] = r[3];
                return t
            }), o.str = function(t) {
                return "vec4(" + t[0] + ", " + t[1] + ", " + t[2] + ", " + t[3] + ")"
            }, t.exports = o
        },
        113: function(t, e, n) {
            var r, a = n(28),
                o = {};
            o.create = function() {
                var t = new a.ARRAY_TYPE(3);
                return t[0] = 0, t[1] = 0, t[2] = 0, t
            }, o.clone = function(t) {
                var e = new a.ARRAY_TYPE(3);
                return e[0] = t[0], e[1] = t[1], e[2] = t[2], e
            }, o.fromValues = function(t, e, n) {
                var r = new a.ARRAY_TYPE(3);
                return r[0] = t, r[1] = e, r[2] = n, r
            }, o.copy = function(t, e) {
                return t[0] = e[0], t[1] = e[1], t[2] = e[2], t
            }, o.set = function(t, e, n, r) {
                return t[0] = e, t[1] = n, t[2] = r, t
            }, o.add = function(t, e, n) {
                return t[0] = e[0] + n[0], t[1] = e[1] + n[1], t[2] = e[2] + n[2], t
            }, o.subtract = function(t, e, n) {
                return t[0] = e[0] - n[0], t[1] = e[1] - n[1], t[2] = e[2] - n[2], t
            }, o.sub = o.subtract, o.multiply = function(t, e, n) {
                return t[0] = e[0] * n[0], t[1] = e[1] * n[1], t[2] = e[2] * n[2], t
            }, o.mul = o.multiply, o.divide = function(t, e, n) {
                return t[0] = e[0] / n[0], t[1] = e[1] / n[1], t[2] = e[2] / n[2], t
            }, o.div = o.divide, o.min = function(t, e, n) {
                return t[0] = Math.min(e[0], n[0]), t[1] = Math.min(e[1], n[1]), t[2] = Math.min(e[2], n[2]), t
            }, o.max = function(t, e, n) {
                return t[0] = Math.max(e[0], n[0]), t[1] = Math.max(e[1], n[1]), t[2] = Math.max(e[2], n[2]), t
            }, o.scale = function(t, e, n) {
                return t[0] = e[0] * n, t[1] = e[1] * n, t[2] = e[2] * n, t
            }, o.scaleAndAdd = function(t, e, n, r) {
                return t[0] = e[0] + n[0] * r, t[1] = e[1] + n[1] * r, t[2] = e[2] + n[2] * r, t
            }, o.distance = function(t, e) {
                var n = e[0] - t[0],
                    r = e[1] - t[1],
                    a = e[2] - t[2];
                return Math.sqrt(n * n + r * r + a * a)
            }, o.dist = o.distance, o.squaredDistance = function(t, e) {
                var n = e[0] - t[0],
                    r = e[1] - t[1],
                    a = e[2] - t[2];
                return n * n + r * r + a * a
            }, o.sqrDist = o.squaredDistance, o.length = function(t) {
                var e = t[0],
                    n = t[1],
                    r = t[2];
                return Math.sqrt(e * e + n * n + r * r)
            }, o.len = o.length, o.squaredLength = function(t) {
                var e = t[0],
                    n = t[1],
                    r = t[2];
                return e * e + n * n + r * r
            }, o.sqrLen = o.squaredLength, o.negate = function(t, e) {
                return t[0] = -e[0], t[1] = -e[1], t[2] = -e[2], t
            }, o.inverse = function(t, e) {
                return t[0] = 1 / e[0], t[1] = 1 / e[1], t[2] = 1 / e[2], t
            }, o.normalize = function(t, e) {
                var n = e[0],
                    r = e[1],
                    a = e[2],
                    o = n * n + r * r + a * a;
                return o > 0 && (o = 1 / Math.sqrt(o), t[0] = e[0] * o, t[1] = e[1] * o, t[2] = e[2] * o), t
            }, o.dot = function(t, e) {
                return t[0] * e[0] + t[1] * e[1] + t[2] * e[2]
            }, o.cross = function(t, e, n) {
                var r = e[0],
                    a = e[1],
                    o = e[2],
                    i = n[0],
                    u = n[1],
                    c = n[2];
                return t[0] = a * c - o * u, t[1] = o * i - r * c, t[2] = r * u - a * i, t
            }, o.lerp = function(t, e, n, r) {
                var a = e[0],
                    o = e[1],
                    i = e[2];
                return t[0] = a + r * (n[0] - a), t[1] = o + r * (n[1] - o), t[2] = i + r * (n[2] - i), t
            }, o.hermite = function(t, e, n, r, a, o) {
                var i = o * o,
                    u = i * (2 * o - 3) + 1,
                    c = i * (o - 2) + o,
                    f = i * (o - 1),
                    s = i * (3 - 2 * o);
                return t[0] = e[0] * u + n[0] * c + r[0] * f + a[0] * s, t[1] = e[1] * u + n[1] * c + r[1] * f + a[1] * s, t[2] = e[2] * u + n[2] * c + r[2] * f + a[2] * s, t
            }, o.bezier = function(t, e, n, r, a, o) {
                var i = 1 - o,
                    u = i * i,
                    c = o * o,
                    f = u * i,
                    s = 3 * o * u,
                    l = 3 * c * i,
                    d = c * o;
                return t[0] = e[0] * f + n[0] * s + r[0] * l + a[0] * d, t[1] = e[1] * f + n[1] * s + r[1] * l + a[1] * d, t[2] = e[2] * f + n[2] * s + r[2] * l + a[2] * d, t
            }, o.random = function(t, e) {
                e = e || 1;
                var n = 2 * a.RANDOM() * Math.PI,
                    r = 2 * a.RANDOM() - 1,
                    o = Math.sqrt(1 - r * r) * e;
                return t[0] = Math.cos(n) * o, t[1] = Math.sin(n) * o, t[2] = r * e, t
            }, o.transformMat4 = function(t, e, n) {
                var r = e[0],
                    a = e[1],
                    o = e[2],
                    i = n[3] * r + n[7] * a + n[11] * o + n[15];
                return i = i || 1, t[0] = (n[0] * r + n[4] * a + n[8] * o + n[12]) / i, t[1] = (n[1] * r + n[5] * a + n[9] * o + n[13]) / i, t[2] = (n[2] * r + n[6] * a + n[10] * o + n[14]) / i, t
            }, o.transformMat3 = function(t, e, n) {
                var r = e[0],
                    a = e[1],
                    o = e[2];
                return t[0] = r * n[0] + a * n[3] + o * n[6], t[1] = r * n[1] + a * n[4] + o * n[7], t[2] = r * n[2] + a * n[5] + o * n[8], t
            }, o.transformQuat = function(t, e, n) {
                var r = e[0],
                    a = e[1],
                    o = e[2],
                    i = n[0],
                    u = n[1],
                    c = n[2],
                    f = n[3],
                    s = f * r + u * o - c * a,
                    l = f * a + c * r - i * o,
                    d = f * o + i * a - u * r,
                    v = -i * r - u * a - c * o;
                return t[0] = s * f + v * -i + l * -c - d * -u, t[1] = l * f + v * -u + d * -i - s * -c, t[2] = d * f + v * -c + s * -u - l * -i, t
            }, o.rotateX = function(t, e, n, r) {
                var a = [],
                    o = [];
                return a[0] = e[0] - n[0], a[1] = e[1] - n[1], a[2] = e[2] - n[2], o[0] = a[0], o[1] = a[1] * Math.cos(r) - a[2] * Math.sin(r), o[2] = a[1] * Math.sin(r) + a[2] * Math.cos(r), t[0] = o[0] + n[0], t[1] = o[1] + n[1], t[2] = o[2] + n[2], t
            }, o.rotateY = function(t, e, n, r) {
                var a = [],
                    o = [];
                return a[0] = e[0] - n[0], a[1] = e[1] - n[1], a[2] = e[2] - n[2], o[0] = a[2] * Math.sin(r) + a[0] * Math.cos(r), o[1] = a[1], o[2] = a[2] * Math.cos(r) - a[0] * Math.sin(r), t[0] = o[0] + n[0], t[1] = o[1] + n[1], t[2] = o[2] + n[2], t
            }, o.rotateZ = function(t, e, n, r) {
                var a = [],
                    o = [];
                return a[0] = e[0] - n[0], a[1] = e[1] - n[1], a[2] = e[2] - n[2], o[0] = a[0] * Math.cos(r) - a[1] * Math.sin(r), o[1] = a[0] * Math.sin(r) + a[1] * Math.cos(r), o[2] = a[2], t[0] = o[0] + n[0], t[1] = o[1] + n[1], t[2] = o[2] + n[2], t
            }, o.forEach = (r = o.create(), function(t, e, n, a, o, i) {
                var u, c;
                for (e || (e = 3), n || (n = 0), c = a ? Math.min(a * e + n, t.length) : t.length, u = n; u < c; u += e) r[0] = t[u], r[1] = t[u + 1], r[2] = t[u + 2], o(r, r, i), t[u] = r[0], t[u + 1] = r[1], t[u + 2] = r[2];
                return t
            }), o.angle = function(t, e) {
                var n = o.fromValues(t[0], t[1], t[2]),
                    r = o.fromValues(e[0], e[1], e[2]);
                o.normalize(n, n), o.normalize(r, r);
                var a = o.dot(n, r);
                return a > 1 ? 0 : Math.acos(a)
            }, o.str = function(t) {
                return "vec3(" + t[0] + ", " + t[1] + ", " + t[2] + ")"
            }, t.exports = o
        },
        114: function(t, e, n) {
            var r = n(28),
                a = {
                    create: function() {
                        var t = new r.ARRAY_TYPE(9);
                        return t[0] = 1, t[1] = 0, t[2] = 0, t[3] = 0, t[4] = 1, t[5] = 0, t[6] = 0, t[7] = 0, t[8] = 1, t
                    },
                    fromMat4: function(t, e) {
                        return t[0] = e[0], t[1] = e[1], t[2] = e[2], t[3] = e[4], t[4] = e[5], t[5] = e[6], t[6] = e[8], t[7] = e[9], t[8] = e[10], t
                    },
                    clone: function(t) {
                        var e = new r.ARRAY_TYPE(9);
                        return e[0] = t[0], e[1] = t[1], e[2] = t[2], e[3] = t[3], e[4] = t[4], e[5] = t[5], e[6] = t[6], e[7] = t[7], e[8] = t[8], e
                    },
                    copy: function(t, e) {
                        return t[0] = e[0], t[1] = e[1], t[2] = e[2], t[3] = e[3], t[4] = e[4], t[5] = e[5], t[6] = e[6], t[7] = e[7], t[8] = e[8], t
                    },
                    identity: function(t) {
                        return t[0] = 1, t[1] = 0, t[2] = 0, t[3] = 0, t[4] = 1, t[5] = 0, t[6] = 0, t[7] = 0, t[8] = 1, t
                    },
                    transpose: function(t, e) {
                        if (t === e) {
                            var n = e[1],
                                r = e[2],
                                a = e[5];
                            t[1] = e[3], t[2] = e[6], t[3] = n, t[5] = e[7], t[6] = r, t[7] = a
                        } else t[0] = e[0], t[1] = e[3], t[2] = e[6], t[3] = e[1], t[4] = e[4], t[5] = e[7], t[6] = e[2], t[7] = e[5], t[8] = e[8];
                        return t
                    },
                    invert: function(t, e) {
                        var n = e[0],
                            r = e[1],
                            a = e[2],
                            o = e[3],
                            i = e[4],
                            u = e[5],
                            c = e[6],
                            f = e[7],
                            s = e[8],
                            l = s * i - u * f,
                            d = -s * o + u * c,
                            v = f * o - i * c,
                            p = n * l + r * d + a * v;
                        return p ? (p = 1 / p, t[0] = l * p, t[1] = (-s * r + a * f) * p, t[2] = (u * r - a * i) * p, t[3] = d * p, t[4] = (s * n - a * c) * p, t[5] = (-u * n + a * o) * p, t[6] = v * p, t[7] = (-f * n + r * c) * p, t[8] = (i * n - r * o) * p, t) : null
                    },
                    adjoint: function(t, e) {
                        var n = e[0],
                            r = e[1],
                            a = e[2],
                            o = e[3],
                            i = e[4],
                            u = e[5],
                            c = e[6],
                            f = e[7],
                            s = e[8];
                        return t[0] = i * s - u * f, t[1] = a * f - r * s, t[2] = r * u - a * i, t[3] = u * c - o * s, t[4] = n * s - a * c, t[5] = a * o - n * u, t[6] = o * f - i * c, t[7] = r * c - n * f, t[8] = n * i - r * o, t
                    },
                    determinant: function(t) {
                        var e = t[0],
                            n = t[1],
                            r = t[2],
                            a = t[3],
                            o = t[4],
                            i = t[5],
                            u = t[6],
                            c = t[7],
                            f = t[8];
                        return e * (f * o - i * c) + n * (-f * a + i * u) + r * (c * a - o * u)
                    },
                    multiply: function(t, e, n) {
                        var r = e[0],
                            a = e[1],
                            o = e[2],
                            i = e[3],
                            u = e[4],
                            c = e[5],
                            f = e[6],
                            s = e[7],
                            l = e[8],
                            d = n[0],
                            v = n[1],
                            p = n[2],
                            m = n[3],
                            h = n[4],
                            y = n[5],
                            g = n[6],
                            b = n[7],
                            M = n[8];
                        return t[0] = d * r + v * i + p * f, t[1] = d * a + v * u + p * s, t[2] = d * o + v * c + p * l, t[3] = m * r + h * i + y * f, t[4] = m * a + h * u + y * s, t[5] = m * o + h * c + y * l, t[6] = g * r + b * i + M * f, t[7] = g * a + b * u + M * s, t[8] = g * o + b * c + M * l, t
                    }
                };
            a.mul = a.multiply, a.translate = function(t, e, n) {
                var r = e[0],
                    a = e[1],
                    o = e[2],
                    i = e[3],
                    u = e[4],
                    c = e[5],
                    f = e[6],
                    s = e[7],
                    l = e[8],
                    d = n[0],
                    v = n[1];
                return t[0] = r, t[1] = a, t[2] = o, t[3] = i, t[4] = u, t[5] = c, t[6] = d * r + v * i + f, t[7] = d * a + v * u + s, t[8] = d * o + v * c + l, t
            }, a.rotate = function(t, e, n) {
                var r = e[0],
                    a = e[1],
                    o = e[2],
                    i = e[3],
                    u = e[4],
                    c = e[5],
                    f = e[6],
                    s = e[7],
                    l = e[8],
                    d = Math.sin(n),
                    v = Math.cos(n);
                return t[0] = v * r + d * i, t[1] = v * a + d * u, t[2] = v * o + d * c, t[3] = v * i - d * r, t[4] = v * u - d * a, t[5] = v * c - d * o, t[6] = f, t[7] = s, t[8] = l, t
            }, a.scale = function(t, e, n) {
                var r = n[0],
                    a = n[1];
                return t[0] = r * e[0], t[1] = r * e[1], t[2] = r * e[2], t[3] = a * e[3], t[4] = a * e[4], t[5] = a * e[5], t[6] = e[6], t[7] = e[7], t[8] = e[8], t
            }, a.fromTranslation = function(t, e) {
                return t[0] = 1, t[1] = 0, t[2] = 0, t[3] = 0, t[4] = 1, t[5] = 0, t[6] = e[0], t[7] = e[1], t[8] = 1, t
            }, a.fromRotation = function(t, e) {
                var n = Math.sin(e),
                    r = Math.cos(e);
                return t[0] = r, t[1] = n, t[2] = 0, t[3] = -n, t[4] = r, t[5] = 0, t[6] = 0, t[7] = 0, t[8] = 1, t
            }, a.fromScaling = function(t, e) {
                return t[0] = e[0], t[1] = 0, t[2] = 0, t[3] = 0, t[4] = e[1], t[5] = 0, t[6] = 0, t[7] = 0, t[8] = 1, t
            }, a.fromMat2d = function(t, e) {
                return t[0] = e[0], t[1] = e[1], t[2] = 0, t[3] = e[2], t[4] = e[3], t[5] = 0, t[6] = e[4], t[7] = e[5], t[8] = 1, t
            }, a.fromQuat = function(t, e) {
                var n = e[0],
                    r = e[1],
                    a = e[2],
                    o = e[3],
                    i = n + n,
                    u = r + r,
                    c = a + a,
                    f = n * i,
                    s = r * i,
                    l = r * u,
                    d = a * i,
                    v = a * u,
                    p = a * c,
                    m = o * i,
                    h = o * u,
                    y = o * c;
                return t[0] = 1 - l - p, t[3] = s - y, t[6] = d + h, t[1] = s + y, t[4] = 1 - f - p, t[7] = v - m, t[2] = d - h, t[5] = v + m, t[8] = 1 - f - l, t
            }, a.normalFromMat4 = function(t, e) {
                var n = e[0],
                    r = e[1],
                    a = e[2],
                    o = e[3],
                    i = e[4],
                    u = e[5],
                    c = e[6],
                    f = e[7],
                    s = e[8],
                    l = e[9],
                    d = e[10],
                    v = e[11],
                    p = e[12],
                    m = e[13],
                    h = e[14],
                    y = e[15],
                    g = n * u - r * i,
                    b = n * c - a * i,
                    M = n * f - o * i,
                    A = r * c - a * u,
                    I = r * f - o * u,
                    T = a * f - o * c,
                    O = s * m - l * p,
                    w = s * h - d * p,
                    x = s * y - v * p,
                    E = l * h - d * m,
                    k = l * y - v * m,
                    S = d * y - v * h,
                    N = g * S - b * k + M * E + A * x - I * w + T * O;
                return N ? (N = 1 / N, t[0] = (u * S - c * k + f * E) * N, t[1] = (c * x - i * S - f * w) * N, t[2] = (i * k - u * x + f * O) * N, t[3] = (a * k - r * S - o * E) * N, t[4] = (n * S - a * x + o * w) * N, t[5] = (r * x - n * k - o * O) * N, t[6] = (m * T - h * I + y * A) * N, t[7] = (h * M - p * T - y * b) * N, t[8] = (p * I - m * M + y * g) * N, t) : null
            }, a.str = function(t) {
                return "mat3(" + t[0] + ", " + t[1] + ", " + t[2] + ", " + t[3] + ", " + t[4] + ", " + t[5] + ", " + t[6] + ", " + t[7] + ", " + t[8] + ")"
            }, a.frob = function(t) {
                return Math.sqrt(Math.pow(t[0], 2) + Math.pow(t[1], 2) + Math.pow(t[2], 2) + Math.pow(t[3], 2) + Math.pow(t[4], 2) + Math.pow(t[5], 2) + Math.pow(t[6], 2) + Math.pow(t[7], 2) + Math.pow(t[8], 2))
            }, t.exports = a
        },
        115: function(t, e, n) {
            "use strict";
            Object.defineProperty(e, "__esModule", {
                value: !0
            });
            var r = e.StructuredType = {
                UNCHANGED: 0,
                SINGLE_POINT: 1,
                X_LINE: 2,
                Y_LINE: 3,
                Z_LINE: 4,
                XY_PLANE: 5,
                YZ_PLANE: 6,
                XZ_PLANE: 7,
                XYZ_GRID: 8,
                EMPTY: 9
            };
            e.default = {
                StructuredType: r
            }
        },
        116: function(t, e, n) {
            "use strict";
            Object.defineProperty(e, "__esModule", {
                value: !0
            }), e.newInstance = void 0, e.extend = s;
            var r = u(n(2)),
                a = u(n(49)),
                o = u(n(201)),
                i = u(n(198));

            function u(t) {
                return t && t.__esModule ? t : {
                    default: t
                }
            }
            var c = ["pointData", "cellData", "fieldData"];
            var f = {};

            function s(t, e) {
                var n = arguments.length > 2 && void 0 !== arguments[2] ? arguments[2] : {};
                Object.assign(e, f, n), r.default.obj(t, e), r.default.setGet(t, e, c),
                    function(t, e) {
                        e.classHierarchy.push("vtkDataSet"), c.forEach(function(t) {
                            e[t] ? e[t] = (0, a.default)(e[t]) : e[t] = o.default.newInstance()
                        })
                    }(0, e)
            }
            var l = e.newInstance = r.default.newInstance(s, "vtkDataSet");
            e.default = Object.assign({
                newInstance: l,
                extend: s
            }, i.default)
        },
        120: function(t, e, n) {
            (function(t) {
                var r = Function.prototype.apply;

                function a(t, e) {
                    this._id = t, this._clearFn = e
                }
                e.setTimeout = function() {
                    return new a(r.call(setTimeout, window, arguments), clearTimeout)
                }, e.setInterval = function() {
                    return new a(r.call(setInterval, window, arguments), clearInterval)
                }, e.clearTimeout = e.clearInterval = function(t) {
                    t && t.close()
                }, a.prototype.unref = a.prototype.ref = function() {}, a.prototype.close = function() {
                    this._clearFn.call(window, this._id)
                }, e.enroll = function(t, e) {
                    clearTimeout(t._idleTimeoutId), t._idleTimeout = e
                }, e.unenroll = function(t) {
                    clearTimeout(t._idleTimeoutId), t._idleTimeout = -1
                }, e._unrefActive = e.active = function(t) {
                    clearTimeout(t._idleTimeoutId);
                    var e = t._idleTimeout;
                    e >= 0 && (t._idleTimeoutId = setTimeout(function() {
                        t._onTimeout && t._onTimeout()
                    }, e))
                }, n(222), e.setImmediate = "undefined" != typeof self && self.setImmediate || void 0 !== t && t.setImmediate || this && this.setImmediate, e.clearImmediate = "undefined" != typeof self && self.clearImmediate || void 0 !== t && t.clearImmediate || this && this.clearImmediate
            }).call(this, n(23))
        },
        124: function(t, e, n) {
            "use strict";
            var r = function() {
                    return function(t, e) {
                        if (Array.isArray(t)) return t;
                        if (Symbol.iterator in Object(t)) return function(t, e) {
                            var n = [],
                                r = !0,
                                a = !1,
                                o = void 0;
                            try {
                                for (var i, u = t[Symbol.iterator](); !(r = (i = u.next()).done) && (n.push(i.value), !e || n.length !== e); r = !0);
                            } catch (t) {
                                a = !0, o = t
                            } finally {
                                try {
                                    !r && u.return && u.return()
                                } finally {
                                    if (a) throw o
                                }
                            }
                            return n
                        }(t, e);
                        throw new TypeError("Invalid attempt to destructure non-iterable instance")
                    }
                }(),
                a = function() {
                    function t(t, e) {
                        for (var n = 0; n < e.length; n++) {
                            var r = e[n];
                            r.enumerable = r.enumerable || !1, r.configurable = !0, "value" in r && (r.writable = !0), Object.defineProperty(t, r.key, r)
                        }
                    }
                    return function(e, n, r) {
                        return n && t(e.prototype, n), r && t(e, r), e
                    }
                }();

            function o(t) {
                if (Array.isArray(t)) {
                    for (var e = 0, n = Array(t.length); e < t.length; e++) n[e] = t[e];
                    return n
                }
                return Array.from(t)
            }
            var i = n(173),
                u = function(t) {
                    function e(t) {
                        ! function(t, e) {
                            if (!(t instanceof e)) throw new TypeError("Cannot call a class as a function")
                        }(this, e);
                        var n = function(t, e) {
                            if (!t) throw new ReferenceError("this hasn't been initialised - super() hasn't been called");
                            return !e || "object" != typeof e && "function" != typeof e ? t : e
                        }(this, (e.__proto__ || Object.getPrototypeOf(e)).call(this));
                        return n._messageId = 1, n._messages = new Map, n._worker = t, n._worker.onmessage = n._onMessage.bind(n), n._id = Math.ceil(1e7 * Math.random()), n
                    }
                    return function(t, e) {
                        if ("function" != typeof e && null !== e) throw new TypeError("Super expression must either be null or a function, not " + typeof e);
                        t.prototype = Object.create(e && e.prototype, {
                            constructor: {
                                value: t,
                                enumerable: !1,
                                writable: !0,
                                configurable: !0
                            }
                        }), e && (Object.setPrototypeOf ? Object.setPrototypeOf(t, e) : t.__proto__ = e)
                    }(e, i), a(e, [{
                        key: "terminate",
                        value: function() {
                            this._worker.terminate()
                        }
                    }, {
                        key: "isFree",
                        value: function() {
                            return 0 === this._messages.size
                        }
                    }, {
                        key: "jobsLength",
                        value: function() {
                            return this._messages.size
                        }
                    }, {
                        key: "exec",
                        value: function(t) {
                            var e = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : null,
                                n = this,
                                r = arguments.length > 2 && void 0 !== arguments[2] ? arguments[2] : [],
                                a = arguments[3];
                            return new Promise(function(o, i) {
                                var u = n._messageId++;
                                n._messages.set(u, [o, i, a]), n._worker.postMessage([u, e, t], r || [])
                            })
                        }
                    }, {
                        key: "postMessage",
                        value: function() {
                            var t = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : null,
                                e = this,
                                n = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : [],
                                r = arguments[2];
                            return new Promise(function(a, o) {
                                var i = e._messageId++;
                                e._messages.set(i, [a, o, r]), e._worker.postMessage([i, t], n || [])
                            })
                        }
                    }, {
                        key: "emit",
                        value: function(t) {
                            for (var e = arguments.length, n = Array(e > 1 ? e - 1 : 0), r = 1; r < e; r++) n[r - 1] = arguments[r];
                            this._worker.postMessage({
                                eventName: t,
                                args: n
                            })
                        }
                    }, {
                        key: "_onMessage",
                        value: function(t) {
                            var n;
                            if (!Array.isArray(t.data) && t.data.eventName) return (n = function t(e, n, r) {
                                null === e && (e = Function.prototype);
                                var a = Object.getOwnPropertyDescriptor(e, n);
                                if (void 0 === a) {
                                    var o = Object.getPrototypeOf(e);
                                    return null === o ? void 0 : t(o, n, r)
                                }
                                if ("value" in a) return a.value;
                                var i = a.get;
                                return void 0 !== i ? i.call(r) : void 0
                            }(e.prototype.__proto__ || Object.getPrototypeOf(e.prototype), "emit", this)).call.apply(n, [this, t.data.eventName].concat(o(t.data.args)));
                            var r, a = (r = t.data, Array.isArray(r) ? r : Array.from(r)),
                                i = a[0],
                                u = a.slice(1);
                            if (1 === i) this._onEvent.apply(this, o(u));
                            else {
                                if (0 !== i) throw new Error("Wrong message type '" + i + "'");
                                this._onResult.apply(this, o(u))
                            }
                        }
                    }, {
                        key: "_onResult",
                        value: function(t, e, n) {
                            var a = this._messages.get(t),
                                o = r(a, 2),
                                i = o[0],
                                u = o[1];
                            return this._messages.delete(t), 1 === e ? i(n) : u(n)
                        }
                    }, {
                        key: "_onEvent",
                        value: function(t, e, n) {
                            var a = this._messages.get(t),
                                o = r(a, 3)[2];
                            o && o(e, n)
                        }
                    }]), e
                }();
            t.exports = u
        },
        16: function(t, e, n) {
            "use strict";
            Object.defineProperty(e, "__esModule", {
                value: !0
            }), e.newInstance = e.STATIC = void 0, e.extend = p;
            var r = i(n(40)),
                a = i(n(2)),
                o = i(n(9));

            function i(t) {
                return t && t.__esModule ? t : {
                    default: t
                }
            }
            var u = r.default.DefaultDataType,
                c = [];

            function f() {
                var t = Number.MAX_VALUE,
                    e = -Number.MAX_VALUE,
                    n = 0,
                    r = 0;
                return {
                    add: function(a) {
                        t > a && (t = a), e < a && (e = a), n++, r += a
                    },
                    get: function() {
                        return {
                            min: t,
                            max: e,
                            count: n,
                            sum: r,
                            mean: r / n
                        }
                    },
                    getRange: function() {
                        return {
                            min: t,
                            max: e
                        }
                    }
                }
            }

            function s(t) {
                var e = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : 0,
                    n = arguments.length > 2 && void 0 !== arguments[2] ? arguments[2] : 1,
                    r = f(),
                    a = t.length,
                    o = 0;
                if (e < 0 && n > 1) {
                    for (var i = 0; i < a; i += n) {
                        o = 0;
                        for (var u = 0; u < n; u++) o += t[i + u] * t[i + u];
                        o = Math.pow(o, .5), r.add(o)
                    }
                    return r.getRange()
                }
                for (var c = e < 0 ? 0 : e; c < a; c += n) r.add(t[c]);
                return r.getRange()
            }

            function l(t) {
                return Object.prototype.toString.call(t).split(" ")[1].slice(0, -1)
            }
            var d = e.STATIC = {
                computeRange: s,
                createRangeHelper: f,
                getDataType: l,
                getMaxNorm: function(t) {
                    for (var e = t.getNumberOfComponents(), n = 0, r = 0; r < t.getNumberOfTuples(); ++r) {
                        var a = o.default.norm(t.getTuple(r), e);
                        a > n && (n = a)
                    }
                    return n
                }
            };
            var v = {
                name: "",
                numberOfComponents: 1,
                size: 0,
                dataType: u,
                rangeTuple: [0, 0]
            };

            function p(t, e) {
                var n = arguments.length > 2 && void 0 !== arguments[2] ? arguments[2] : {};
                if (Object.assign(e, v, n), !e.empty && !e.values && !e.size) throw new TypeError("Cannot create vtkDataArray object without: size > 0, values");
                e.values ? Array.isArray(e.values) && (e.values = window[e.dataType].from(e.values)) : e.values = new window[e.dataType](e.size), e.values && (e.size = e.values.length, e.dataType = l(e.values)), a.default.obj(t, e), a.default.set(t, e, ["name", "numberOfComponents"]),
                    function(t, e) {
                        function n() {
                            e.ranges = null, t.modified()
                        }
                        e.classHierarchy.push("vtkDataArray"), t.getElementComponentSize = function() {
                            return e.values.BYTES_PER_ELEMENT
                        }, t.getComponent = function(t) {
                            var n = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : 0;
                            return e.values[t * e.numberOfComponents + n]
                        }, t.setComponent = function(t, r, a) {
                            a !== e.values[t * e.numberOfComponents + r] && (e.values[t * e.numberOfComponents + r] = a, n())
                        }, t.getData = function() {
                            return e.values
                        }, t.getRange = function() {
                            var t = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : -1,
                                n = t < 0 ? e.numberOfComponents : t,
                                r = null;
                            return e.ranges || (e.ranges = function(t) {
                                for (var e = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : 0, n = t || []; n.length <= e;) n.push(null);
                                return n
                            }(e.ranges, e.numberOfComponents)), (r = e.ranges[n]) ? (e.rangeTuple[0] = r.min, e.rangeTuple[1] = r.max, e.rangeTuple) : (r = s(e.values, t, e.numberOfComponents), e.ranges[n] = r, e.rangeTuple[0] = r.min, e.rangeTuple[1] = r.max, e.rangeTuple)
                        }, t.setTuple = function(t, n) {
                            for (var r = t * e.numberOfComponents, a = 0; a < e.numberOfComponents; a++) e.values[r + a] = n[a]
                        }, t.getTuple = function(t) {
                            var n = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : c,
                                r = e.numberOfComponents || 1;
                            n.length !== r && (n.length = r);
                            var a = t * r;
                            if (1 === r) n[0] = e.values[a];
                            else if (2 === r) n[0] = e.values[a], n[1] = e.values[a + 1];
                            else if (3 === r) n[0] = e.values[a], n[1] = e.values[a + 1], n[2] = e.values[a + 2];
                            else if (4 === r) n[0] = e.values[a], n[1] = e.values[a + 1], n[2] = e.values[a + 2], n[3] = e.values[a + 3];
                            else
                                for (var o = 0; o < r; o++) n[o] = e.values[a + o];
                            return n
                        }, t.getTupleLocation = function() {
                            return (arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : 1) * e.numberOfComponents
                        }, t.getNumberOfComponents = function() {
                            return e.numberOfComponents
                        }, t.getNumberOfValues = function() {
                            return e.values.length
                        }, t.getNumberOfTuples = function() {
                            return e.values.length / e.numberOfComponents
                        }, t.getDataType = function() {
                            return e.dataType
                        }, t.newClone = function() {
                            return m({
                                empty: !0,
                                name: e.name,
                                dataType: e.dataType,
                                numberOfComponents: e.numberOfComponents
                            })
                        }, t.getName = function() {
                            return e.name || (t.modified(), e.name = "vtkDataArray" + t.getMTime()), e.name
                        }, t.setData = function(t, r) {
                            e.values = t, e.size = t.length, e.dataType = l(t), r && (e.numberOfComponents = r), e.size % e.numberOfComponents != 0 && (e.numberOfComponents = 1), n()
                        }, t.shallowCopy = function() {
                            return m(Object.assign({}, e))
                        }, t.getState = function() {
                            var n = Object.assign({}, e, {
                                vtkClass: t.getClassName()
                            });
                            n.values = Array.from(n.values), delete n.buffer, Object.keys(n).forEach(function(t) {
                                n[t] || delete n[t]
                            });
                            var r = {};
                            return Object.keys(n).sort().forEach(function(t) {
                                r[t] = n[t]
                            }), r.mtime && delete r.mtime, r
                        }
                    }(t, e)
            }
            var m = e.newInstance = a.default.newInstance(p, "vtkDataArray");
            e.default = Object.assign({
                newInstance: m,
                extend: p
            }, d, r.default)
        },
        173: function(t, e, n) {
            "use strict";
            var r = function() {
                function t(t, e) {
                    for (var n = 0; n < e.length; n++) {
                        var r = e[n];
                        r.enumerable = r.enumerable || !1, r.configurable = !0, "value" in r && (r.writable = !0), Object.defineProperty(t, r.key, r)
                    }
                }
                return function(e, n, r) {
                    return n && t(e.prototype, n), r && t(e, r), e
                }
            }();
            var a = function() {
                function t() {
                    ! function(t, e) {
                        if (!(t instanceof e)) throw new TypeError("Cannot call a class as a function")
                    }(this, t), Object.defineProperty(this, "__listeners", {
                        value: {},
                        enumerable: !1,
                        writable: !1
                    })
                }
                return r(t, [{
                    key: "emit",
                    value: function(t) {
                        for (var e = arguments.length, n = Array(e > 1 ? e - 1 : 0), r = 1; r < e; r++) n[r - 1] = arguments[r];
                        var a = !0,
                            o = !1,
                            i = void 0;
                        try {
                            for (var u, c = this.__listeners[t][Symbol.iterator](); !(a = (u = c.next()).done); a = !0) {
                                u.value.apply(void 0, n)
                            }
                        } catch (t) {
                            o = !0, i = t
                        } finally {
                            try {
                                !a && c.return && c.return()
                            } finally {
                                if (o) throw i
                            }
                        }
                        return this
                    }
                }, {
                    key: "once",
                    value: function(t, e) {
                        var n = this,
                            r = function r() {
                                n.off(t, r), e.apply(void 0, arguments)
                            };
                        return this.on(t, r)
                    }
                }, {
                    key: "on",
                    value: function(t, e) {
                        return this.__listeners[t] || (this.__listeners[t] = []), this.__listeners[t].push(e), this
                    }
                }, {
                    key: "off",
                    value: function(t, e) {
                        return this.__listeners[t] = e ? this.__listeners[t].filter(function(t) {
                            return t !== e
                        }) : [], this
                    }
                }]), t
            }();
            t.exports = a
        },
        19: function(t, e, n) {
            e.glMatrix = n(28), e.mat2 = n(196), e.mat2d = n(195), e.mat3 = n(114), e.mat4 = n(194), e.quat = n(193), e.vec2 = n(192), e.vec3 = n(113), e.vec4 = n(112)
        },
        192: function(t, e, n) {
            var r, a = n(28),
                o = {};
            o.create = function() {
                var t = new a.ARRAY_TYPE(2);
                return t[0] = 0, t[1] = 0, t
            }, o.clone = function(t) {
                var e = new a.ARRAY_TYPE(2);
                return e[0] = t[0], e[1] = t[1], e
            }, o.fromValues = function(t, e) {
                var n = new a.ARRAY_TYPE(2);
                return n[0] = t, n[1] = e, n
            }, o.copy = function(t, e) {
                return t[0] = e[0], t[1] = e[1], t
            }, o.set = function(t, e, n) {
                return t[0] = e, t[1] = n, t
            }, o.add = function(t, e, n) {
                return t[0] = e[0] + n[0], t[1] = e[1] + n[1], t
            }, o.subtract = function(t, e, n) {
                return t[0] = e[0] - n[0], t[1] = e[1] - n[1], t
            }, o.sub = o.subtract, o.multiply = function(t, e, n) {
                return t[0] = e[0] * n[0], t[1] = e[1] * n[1], t
            }, o.mul = o.multiply, o.divide = function(t, e, n) {
                return t[0] = e[0] / n[0], t[1] = e[1] / n[1], t
            }, o.div = o.divide, o.min = function(t, e, n) {
                return t[0] = Math.min(e[0], n[0]), t[1] = Math.min(e[1], n[1]), t
            }, o.max = function(t, e, n) {
                return t[0] = Math.max(e[0], n[0]), t[1] = Math.max(e[1], n[1]), t
            }, o.scale = function(t, e, n) {
                return t[0] = e[0] * n, t[1] = e[1] * n, t
            }, o.scaleAndAdd = function(t, e, n, r) {
                return t[0] = e[0] + n[0] * r, t[1] = e[1] + n[1] * r, t
            }, o.distance = function(t, e) {
                var n = e[0] - t[0],
                    r = e[1] - t[1];
                return Math.sqrt(n * n + r * r)
            }, o.dist = o.distance, o.squaredDistance = function(t, e) {
                var n = e[0] - t[0],
                    r = e[1] - t[1];
                return n * n + r * r
            }, o.sqrDist = o.squaredDistance, o.length = function(t) {
                var e = t[0],
                    n = t[1];
                return Math.sqrt(e * e + n * n)
            }, o.len = o.length, o.squaredLength = function(t) {
                var e = t[0],
                    n = t[1];
                return e * e + n * n
            }, o.sqrLen = o.squaredLength, o.negate = function(t, e) {
                return t[0] = -e[0], t[1] = -e[1], t
            }, o.inverse = function(t, e) {
                return t[0] = 1 / e[0], t[1] = 1 / e[1], t
            }, o.normalize = function(t, e) {
                var n = e[0],
                    r = e[1],
                    a = n * n + r * r;
                return a > 0 && (a = 1 / Math.sqrt(a), t[0] = e[0] * a, t[1] = e[1] * a), t
            }, o.dot = function(t, e) {
                return t[0] * e[0] + t[1] * e[1]
            }, o.cross = function(t, e, n) {
                var r = e[0] * n[1] - e[1] * n[0];
                return t[0] = t[1] = 0, t[2] = r, t
            }, o.lerp = function(t, e, n, r) {
                var a = e[0],
                    o = e[1];
                return t[0] = a + r * (n[0] - a), t[1] = o + r * (n[1] - o), t
            }, o.random = function(t, e) {
                e = e || 1;
                var n = 2 * a.RANDOM() * Math.PI;
                return t[0] = Math.cos(n) * e, t[1] = Math.sin(n) * e, t
            }, o.transformMat2 = function(t, e, n) {
                var r = e[0],
                    a = e[1];
                return t[0] = n[0] * r + n[2] * a, t[1] = n[1] * r + n[3] * a, t
            }, o.transformMat2d = function(t, e, n) {
                var r = e[0],
                    a = e[1];
                return t[0] = n[0] * r + n[2] * a + n[4], t[1] = n[1] * r + n[3] * a + n[5], t
            }, o.transformMat3 = function(t, e, n) {
                var r = e[0],
                    a = e[1];
                return t[0] = n[0] * r + n[3] * a + n[6], t[1] = n[1] * r + n[4] * a + n[7], t
            }, o.transformMat4 = function(t, e, n) {
                var r = e[0],
                    a = e[1];
                return t[0] = n[0] * r + n[4] * a + n[12], t[1] = n[1] * r + n[5] * a + n[13], t
            }, o.forEach = (r = o.create(), function(t, e, n, a, o, i) {
                var u, c;
                for (e || (e = 2), n || (n = 0), c = a ? Math.min(a * e + n, t.length) : t.length, u = n; u < c; u += e) r[0] = t[u], r[1] = t[u + 1], o(r, r, i), t[u] = r[0], t[u + 1] = r[1];
                return t
            }), o.str = function(t) {
                return "vec2(" + t[0] + ", " + t[1] + ")"
            }, t.exports = o
        },
        193: function(t, e, n) {
            var r, a, o, i, u, c, f = n(28),
                s = n(114),
                l = n(113),
                d = n(112),
                v = {};
            v.create = function() {
                var t = new f.ARRAY_TYPE(4);
                return t[0] = 0, t[1] = 0, t[2] = 0, t[3] = 1, t
            }, v.rotationTo = (r = l.create(), a = l.fromValues(1, 0, 0), o = l.fromValues(0, 1, 0), function(t, e, n) {
                var i = l.dot(e, n);
                return i < -.999999 ? (l.cross(r, a, e), l.length(r) < 1e-6 && l.cross(r, o, e), l.normalize(r, r), v.setAxisAngle(t, r, Math.PI), t) : i > .999999 ? (t[0] = 0, t[1] = 0, t[2] = 0, t[3] = 1, t) : (l.cross(r, e, n), t[0] = r[0], t[1] = r[1], t[2] = r[2], t[3] = 1 + i, v.normalize(t, t))
            }), v.setAxes = (i = s.create(), function(t, e, n, r) {
                return i[0] = n[0], i[3] = n[1], i[6] = n[2], i[1] = r[0], i[4] = r[1], i[7] = r[2], i[2] = -e[0], i[5] = -e[1], i[8] = -e[2], v.normalize(t, v.fromMat3(t, i))
            }), v.clone = d.clone, v.fromValues = d.fromValues, v.copy = d.copy, v.set = d.set, v.identity = function(t) {
                return t[0] = 0, t[1] = 0, t[2] = 0, t[3] = 1, t
            }, v.setAxisAngle = function(t, e, n) {
                n *= .5;
                var r = Math.sin(n);
                return t[0] = r * e[0], t[1] = r * e[1], t[2] = r * e[2], t[3] = Math.cos(n), t
            }, v.add = d.add, v.multiply = function(t, e, n) {
                var r = e[0],
                    a = e[1],
                    o = e[2],
                    i = e[3],
                    u = n[0],
                    c = n[1],
                    f = n[2],
                    s = n[3];
                return t[0] = r * s + i * u + a * f - o * c, t[1] = a * s + i * c + o * u - r * f, t[2] = o * s + i * f + r * c - a * u, t[3] = i * s - r * u - a * c - o * f, t
            }, v.mul = v.multiply, v.scale = d.scale, v.rotateX = function(t, e, n) {
                n *= .5;
                var r = e[0],
                    a = e[1],
                    o = e[2],
                    i = e[3],
                    u = Math.sin(n),
                    c = Math.cos(n);
                return t[0] = r * c + i * u, t[1] = a * c + o * u, t[2] = o * c - a * u, t[3] = i * c - r * u, t
            }, v.rotateY = function(t, e, n) {
                n *= .5;
                var r = e[0],
                    a = e[1],
                    o = e[2],
                    i = e[3],
                    u = Math.sin(n),
                    c = Math.cos(n);
                return t[0] = r * c - o * u, t[1] = a * c + i * u, t[2] = o * c + r * u, t[3] = i * c - a * u, t
            }, v.rotateZ = function(t, e, n) {
                n *= .5;
                var r = e[0],
                    a = e[1],
                    o = e[2],
                    i = e[3],
                    u = Math.sin(n),
                    c = Math.cos(n);
                return t[0] = r * c + a * u, t[1] = a * c - r * u, t[2] = o * c + i * u, t[3] = i * c - o * u, t
            }, v.calculateW = function(t, e) {
                var n = e[0],
                    r = e[1],
                    a = e[2];
                return t[0] = n, t[1] = r, t[2] = a, t[3] = Math.sqrt(Math.abs(1 - n * n - r * r - a * a)), t
            }, v.dot = d.dot, v.lerp = d.lerp, v.slerp = function(t, e, n, r) {
                var a, o, i, u, c, f = e[0],
                    s = e[1],
                    l = e[2],
                    d = e[3],
                    v = n[0],
                    p = n[1],
                    m = n[2],
                    h = n[3];
                return (o = f * v + s * p + l * m + d * h) < 0 && (o = -o, v = -v, p = -p, m = -m, h = -h), 1 - o > 1e-6 ? (a = Math.acos(o), i = Math.sin(a), u = Math.sin((1 - r) * a) / i, c = Math.sin(r * a) / i) : (u = 1 - r, c = r), t[0] = u * f + c * v, t[1] = u * s + c * p, t[2] = u * l + c * m, t[3] = u * d + c * h, t
            }, v.sqlerp = (u = v.create(), c = v.create(), function(t, e, n, r, a, o) {
                return v.slerp(u, e, a, o), v.slerp(c, n, r, o), v.slerp(t, u, c, 2 * o * (1 - o)), t
            }), v.invert = function(t, e) {
                var n = e[0],
                    r = e[1],
                    a = e[2],
                    o = e[3],
                    i = n * n + r * r + a * a + o * o,
                    u = i ? 1 / i : 0;
                return t[0] = -n * u, t[1] = -r * u, t[2] = -a * u, t[3] = o * u, t
            }, v.conjugate = function(t, e) {
                return t[0] = -e[0], t[1] = -e[1], t[2] = -e[2], t[3] = e[3], t
            }, v.length = d.length, v.len = v.length, v.squaredLength = d.squaredLength, v.sqrLen = v.squaredLength, v.normalize = d.normalize, v.fromMat3 = function(t, e) {
                var n, r = e[0] + e[4] + e[8];
                if (r > 0) n = Math.sqrt(r + 1), t[3] = .5 * n, n = .5 / n, t[0] = (e[5] - e[7]) * n, t[1] = (e[6] - e[2]) * n, t[2] = (e[1] - e[3]) * n;
                else {
                    var a = 0;
                    e[4] > e[0] && (a = 1), e[8] > e[3 * a + a] && (a = 2);
                    var o = (a + 1) % 3,
                        i = (a + 2) % 3;
                    n = Math.sqrt(e[3 * a + a] - e[3 * o + o] - e[3 * i + i] + 1), t[a] = .5 * n, n = .5 / n, t[3] = (e[3 * o + i] - e[3 * i + o]) * n, t[o] = (e[3 * o + a] + e[3 * a + o]) * n, t[i] = (e[3 * i + a] + e[3 * a + i]) * n
                }
                return t
            }, v.str = function(t) {
                return "quat(" + t[0] + ", " + t[1] + ", " + t[2] + ", " + t[3] + ")"
            }, t.exports = v
        },
        194: function(t, e, n) {
            var r = n(28),
                a = {
                    create: function() {
                        var t = new r.ARRAY_TYPE(16);
                        return t[0] = 1, t[1] = 0, t[2] = 0, t[3] = 0, t[4] = 0, t[5] = 1, t[6] = 0, t[7] = 0, t[8] = 0, t[9] = 0, t[10] = 1, t[11] = 0, t[12] = 0, t[13] = 0, t[14] = 0, t[15] = 1, t
                    },
                    clone: function(t) {
                        var e = new r.ARRAY_TYPE(16);
                        return e[0] = t[0], e[1] = t[1], e[2] = t[2], e[3] = t[3], e[4] = t[4], e[5] = t[5], e[6] = t[6], e[7] = t[7], e[8] = t[8], e[9] = t[9], e[10] = t[10], e[11] = t[11], e[12] = t[12], e[13] = t[13], e[14] = t[14], e[15] = t[15], e
                    },
                    copy: function(t, e) {
                        return t[0] = e[0], t[1] = e[1], t[2] = e[2], t[3] = e[3], t[4] = e[4], t[5] = e[5], t[6] = e[6], t[7] = e[7], t[8] = e[8], t[9] = e[9], t[10] = e[10], t[11] = e[11], t[12] = e[12], t[13] = e[13], t[14] = e[14], t[15] = e[15], t
                    },
                    identity: function(t) {
                        return t[0] = 1, t[1] = 0, t[2] = 0, t[3] = 0, t[4] = 0, t[5] = 1, t[6] = 0, t[7] = 0, t[8] = 0, t[9] = 0, t[10] = 1, t[11] = 0, t[12] = 0, t[13] = 0, t[14] = 0, t[15] = 1, t
                    },
                    transpose: function(t, e) {
                        if (t === e) {
                            var n = e[1],
                                r = e[2],
                                a = e[3],
                                o = e[6],
                                i = e[7],
                                u = e[11];
                            t[1] = e[4], t[2] = e[8], t[3] = e[12], t[4] = n, t[6] = e[9], t[7] = e[13], t[8] = r, t[9] = o, t[11] = e[14], t[12] = a, t[13] = i, t[14] = u
                        } else t[0] = e[0], t[1] = e[4], t[2] = e[8], t[3] = e[12], t[4] = e[1], t[5] = e[5], t[6] = e[9], t[7] = e[13], t[8] = e[2], t[9] = e[6], t[10] = e[10], t[11] = e[14], t[12] = e[3], t[13] = e[7], t[14] = e[11], t[15] = e[15];
                        return t
                    },
                    invert: function(t, e) {
                        var n = e[0],
                            r = e[1],
                            a = e[2],
                            o = e[3],
                            i = e[4],
                            u = e[5],
                            c = e[6],
                            f = e[7],
                            s = e[8],
                            l = e[9],
                            d = e[10],
                            v = e[11],
                            p = e[12],
                            m = e[13],
                            h = e[14],
                            y = e[15],
                            g = n * u - r * i,
                            b = n * c - a * i,
                            M = n * f - o * i,
                            A = r * c - a * u,
                            I = r * f - o * u,
                            T = a * f - o * c,
                            O = s * m - l * p,
                            w = s * h - d * p,
                            x = s * y - v * p,
                            E = l * h - d * m,
                            k = l * y - v * m,
                            S = d * y - v * h,
                            N = g * S - b * k + M * E + A * x - I * w + T * O;
                        return N ? (N = 1 / N, t[0] = (u * S - c * k + f * E) * N, t[1] = (a * k - r * S - o * E) * N, t[2] = (m * T - h * I + y * A) * N, t[3] = (d * I - l * T - v * A) * N, t[4] = (c * x - i * S - f * w) * N, t[5] = (n * S - a * x + o * w) * N, t[6] = (h * M - p * T - y * b) * N, t[7] = (s * T - d * M + v * b) * N, t[8] = (i * k - u * x + f * O) * N, t[9] = (r * x - n * k - o * O) * N, t[10] = (p * I - m * M + y * g) * N, t[11] = (l * M - s * I - v * g) * N, t[12] = (u * w - i * E - c * O) * N, t[13] = (n * E - r * w + a * O) * N, t[14] = (m * b - p * A - h * g) * N, t[15] = (s * A - l * b + d * g) * N, t) : null
                    },
                    adjoint: function(t, e) {
                        var n = e[0],
                            r = e[1],
                            a = e[2],
                            o = e[3],
                            i = e[4],
                            u = e[5],
                            c = e[6],
                            f = e[7],
                            s = e[8],
                            l = e[9],
                            d = e[10],
                            v = e[11],
                            p = e[12],
                            m = e[13],
                            h = e[14],
                            y = e[15];
                        return t[0] = u * (d * y - v * h) - l * (c * y - f * h) + m * (c * v - f * d), t[1] = -(r * (d * y - v * h) - l * (a * y - o * h) + m * (a * v - o * d)), t[2] = r * (c * y - f * h) - u * (a * y - o * h) + m * (a * f - o * c), t[3] = -(r * (c * v - f * d) - u * (a * v - o * d) + l * (a * f - o * c)), t[4] = -(i * (d * y - v * h) - s * (c * y - f * h) + p * (c * v - f * d)), t[5] = n * (d * y - v * h) - s * (a * y - o * h) + p * (a * v - o * d), t[6] = -(n * (c * y - f * h) - i * (a * y - o * h) + p * (a * f - o * c)), t[7] = n * (c * v - f * d) - i * (a * v - o * d) + s * (a * f - o * c), t[8] = i * (l * y - v * m) - s * (u * y - f * m) + p * (u * v - f * l), t[9] = -(n * (l * y - v * m) - s * (r * y - o * m) + p * (r * v - o * l)), t[10] = n * (u * y - f * m) - i * (r * y - o * m) + p * (r * f - o * u), t[11] = -(n * (u * v - f * l) - i * (r * v - o * l) + s * (r * f - o * u)), t[12] = -(i * (l * h - d * m) - s * (u * h - c * m) + p * (u * d - c * l)), t[13] = n * (l * h - d * m) - s * (r * h - a * m) + p * (r * d - a * l), t[14] = -(n * (u * h - c * m) - i * (r * h - a * m) + p * (r * c - a * u)), t[15] = n * (u * d - c * l) - i * (r * d - a * l) + s * (r * c - a * u), t
                    },
                    determinant: function(t) {
                        var e = t[0],
                            n = t[1],
                            r = t[2],
                            a = t[3],
                            o = t[4],
                            i = t[5],
                            u = t[6],
                            c = t[7],
                            f = t[8],
                            s = t[9],
                            l = t[10],
                            d = t[11],
                            v = t[12],
                            p = t[13],
                            m = t[14],
                            h = t[15];
                        return (e * i - n * o) * (l * h - d * m) - (e * u - r * o) * (s * h - d * p) + (e * c - a * o) * (s * m - l * p) + (n * u - r * i) * (f * h - d * v) - (n * c - a * i) * (f * m - l * v) + (r * c - a * u) * (f * p - s * v)
                    },
                    multiply: function(t, e, n) {
                        var r = e[0],
                            a = e[1],
                            o = e[2],
                            i = e[3],
                            u = e[4],
                            c = e[5],
                            f = e[6],
                            s = e[7],
                            l = e[8],
                            d = e[9],
                            v = e[10],
                            p = e[11],
                            m = e[12],
                            h = e[13],
                            y = e[14],
                            g = e[15],
                            b = n[0],
                            M = n[1],
                            A = n[2],
                            I = n[3];
                        return t[0] = b * r + M * u + A * l + I * m, t[1] = b * a + M * c + A * d + I * h, t[2] = b * o + M * f + A * v + I * y, t[3] = b * i + M * s + A * p + I * g, b = n[4], M = n[5], A = n[6], I = n[7], t[4] = b * r + M * u + A * l + I * m, t[5] = b * a + M * c + A * d + I * h, t[6] = b * o + M * f + A * v + I * y, t[7] = b * i + M * s + A * p + I * g, b = n[8], M = n[9], A = n[10], I = n[11], t[8] = b * r + M * u + A * l + I * m, t[9] = b * a + M * c + A * d + I * h, t[10] = b * o + M * f + A * v + I * y, t[11] = b * i + M * s + A * p + I * g, b = n[12], M = n[13], A = n[14], I = n[15], t[12] = b * r + M * u + A * l + I * m, t[13] = b * a + M * c + A * d + I * h, t[14] = b * o + M * f + A * v + I * y, t[15] = b * i + M * s + A * p + I * g, t
                    }
                };
            a.mul = a.multiply, a.translate = function(t, e, n) {
                var r, a, o, i, u, c, f, s, l, d, v, p, m = n[0],
                    h = n[1],
                    y = n[2];
                return e === t ? (t[12] = e[0] * m + e[4] * h + e[8] * y + e[12], t[13] = e[1] * m + e[5] * h + e[9] * y + e[13], t[14] = e[2] * m + e[6] * h + e[10] * y + e[14], t[15] = e[3] * m + e[7] * h + e[11] * y + e[15]) : (r = e[0], a = e[1], o = e[2], i = e[3], u = e[4], c = e[5], f = e[6], s = e[7], l = e[8], d = e[9], v = e[10], p = e[11], t[0] = r, t[1] = a, t[2] = o, t[3] = i, t[4] = u, t[5] = c, t[6] = f, t[7] = s, t[8] = l, t[9] = d, t[10] = v, t[11] = p, t[12] = r * m + u * h + l * y + e[12], t[13] = a * m + c * h + d * y + e[13], t[14] = o * m + f * h + v * y + e[14], t[15] = i * m + s * h + p * y + e[15]), t
            }, a.scale = function(t, e, n) {
                var r = n[0],
                    a = n[1],
                    o = n[2];
                return t[0] = e[0] * r, t[1] = e[1] * r, t[2] = e[2] * r, t[3] = e[3] * r, t[4] = e[4] * a, t[5] = e[5] * a, t[6] = e[6] * a, t[7] = e[7] * a, t[8] = e[8] * o, t[9] = e[9] * o, t[10] = e[10] * o, t[11] = e[11] * o, t[12] = e[12], t[13] = e[13], t[14] = e[14], t[15] = e[15], t
            }, a.rotate = function(t, e, n, a) {
                var o, i, u, c, f, s, l, d, v, p, m, h, y, g, b, M, A, I, T, O, w, x, E, k, S = a[0],
                    N = a[1],
                    _ = a[2],
                    D = Math.sqrt(S * S + N * N + _ * _);
                return Math.abs(D) < r.EPSILON ? null : (S *= D = 1 / D, N *= D, _ *= D, o = Math.sin(n), u = 1 - (i = Math.cos(n)), c = e[0], f = e[1], s = e[2], l = e[3], d = e[4], v = e[5], p = e[6], m = e[7], h = e[8], y = e[9], g = e[10], b = e[11], M = S * S * u + i, A = N * S * u + _ * o, I = _ * S * u - N * o, T = S * N * u - _ * o, O = N * N * u + i, w = _ * N * u + S * o, x = S * _ * u + N * o, E = N * _ * u - S * o, k = _ * _ * u + i, t[0] = c * M + d * A + h * I, t[1] = f * M + v * A + y * I, t[2] = s * M + p * A + g * I, t[3] = l * M + m * A + b * I, t[4] = c * T + d * O + h * w, t[5] = f * T + v * O + y * w, t[6] = s * T + p * O + g * w, t[7] = l * T + m * O + b * w, t[8] = c * x + d * E + h * k, t[9] = f * x + v * E + y * k, t[10] = s * x + p * E + g * k, t[11] = l * x + m * E + b * k, e !== t && (t[12] = e[12], t[13] = e[13], t[14] = e[14], t[15] = e[15]), t)
            }, a.rotateX = function(t, e, n) {
                var r = Math.sin(n),
                    a = Math.cos(n),
                    o = e[4],
                    i = e[5],
                    u = e[6],
                    c = e[7],
                    f = e[8],
                    s = e[9],
                    l = e[10],
                    d = e[11];
                return e !== t && (t[0] = e[0], t[1] = e[1], t[2] = e[2], t[3] = e[3], t[12] = e[12], t[13] = e[13], t[14] = e[14], t[15] = e[15]), t[4] = o * a + f * r, t[5] = i * a + s * r, t[6] = u * a + l * r, t[7] = c * a + d * r, t[8] = f * a - o * r, t[9] = s * a - i * r, t[10] = l * a - u * r, t[11] = d * a - c * r, t
            }, a.rotateY = function(t, e, n) {
                var r = Math.sin(n),
                    a = Math.cos(n),
                    o = e[0],
                    i = e[1],
                    u = e[2],
                    c = e[3],
                    f = e[8],
                    s = e[9],
                    l = e[10],
                    d = e[11];
                return e !== t && (t[4] = e[4], t[5] = e[5], t[6] = e[6], t[7] = e[7], t[12] = e[12], t[13] = e[13], t[14] = e[14], t[15] = e[15]), t[0] = o * a - f * r, t[1] = i * a - s * r, t[2] = u * a - l * r, t[3] = c * a - d * r, t[8] = o * r + f * a, t[9] = i * r + s * a, t[10] = u * r + l * a, t[11] = c * r + d * a, t
            }, a.rotateZ = function(t, e, n) {
                var r = Math.sin(n),
                    a = Math.cos(n),
                    o = e[0],
                    i = e[1],
                    u = e[2],
                    c = e[3],
                    f = e[4],
                    s = e[5],
                    l = e[6],
                    d = e[7];
                return e !== t && (t[8] = e[8], t[9] = e[9], t[10] = e[10], t[11] = e[11], t[12] = e[12], t[13] = e[13], t[14] = e[14], t[15] = e[15]), t[0] = o * a + f * r, t[1] = i * a + s * r, t[2] = u * a + l * r, t[3] = c * a + d * r, t[4] = f * a - o * r, t[5] = s * a - i * r, t[6] = l * a - u * r, t[7] = d * a - c * r, t
            }, a.fromTranslation = function(t, e) {
                return t[0] = 1, t[1] = 0, t[2] = 0, t[3] = 0, t[4] = 0, t[5] = 1, t[6] = 0, t[7] = 0, t[8] = 0, t[9] = 0, t[10] = 1, t[11] = 0, t[12] = e[0], t[13] = e[1], t[14] = e[2], t[15] = 1, t
            }, a.fromScaling = function(t, e) {
                return t[0] = e[0], t[1] = 0, t[2] = 0, t[3] = 0, t[4] = 0, t[5] = e[1], t[6] = 0, t[7] = 0, t[8] = 0, t[9] = 0, t[10] = e[2], t[11] = 0, t[12] = 0, t[13] = 0, t[14] = 0, t[15] = 1, t
            }, a.fromRotation = function(t, e, n) {
                var a, o, i, u = n[0],
                    c = n[1],
                    f = n[2],
                    s = Math.sqrt(u * u + c * c + f * f);
                return Math.abs(s) < r.EPSILON ? null : (u *= s = 1 / s, c *= s, f *= s, a = Math.sin(e), i = 1 - (o = Math.cos(e)), t[0] = u * u * i + o, t[1] = c * u * i + f * a, t[2] = f * u * i - c * a, t[3] = 0, t[4] = u * c * i - f * a, t[5] = c * c * i + o, t[6] = f * c * i + u * a, t[7] = 0, t[8] = u * f * i + c * a, t[9] = c * f * i - u * a, t[10] = f * f * i + o, t[11] = 0, t[12] = 0, t[13] = 0, t[14] = 0, t[15] = 1, t)
            }, a.fromXRotation = function(t, e) {
                var n = Math.sin(e),
                    r = Math.cos(e);
                return t[0] = 1, t[1] = 0, t[2] = 0, t[3] = 0, t[4] = 0, t[5] = r, t[6] = n, t[7] = 0, t[8] = 0, t[9] = -n, t[10] = r, t[11] = 0, t[12] = 0, t[13] = 0, t[14] = 0, t[15] = 1, t
            }, a.fromYRotation = function(t, e) {
                var n = Math.sin(e),
                    r = Math.cos(e);
                return t[0] = r, t[1] = 0, t[2] = -n, t[3] = 0, t[4] = 0, t[5] = 1, t[6] = 0, t[7] = 0, t[8] = n, t[9] = 0, t[10] = r, t[11] = 0, t[12] = 0, t[13] = 0, t[14] = 0, t[15] = 1, t
            }, a.fromZRotation = function(t, e) {
                var n = Math.sin(e),
                    r = Math.cos(e);
                return t[0] = r, t[1] = n, t[2] = 0, t[3] = 0, t[4] = -n, t[5] = r, t[6] = 0, t[7] = 0, t[8] = 0, t[9] = 0, t[10] = 1, t[11] = 0, t[12] = 0, t[13] = 0, t[14] = 0, t[15] = 1, t
            }, a.fromRotationTranslation = function(t, e, n) {
                var r = e[0],
                    a = e[1],
                    o = e[2],
                    i = e[3],
                    u = r + r,
                    c = a + a,
                    f = o + o,
                    s = r * u,
                    l = r * c,
                    d = r * f,
                    v = a * c,
                    p = a * f,
                    m = o * f,
                    h = i * u,
                    y = i * c,
                    g = i * f;
                return t[0] = 1 - (v + m), t[1] = l + g, t[2] = d - y, t[3] = 0, t[4] = l - g, t[5] = 1 - (s + m), t[6] = p + h, t[7] = 0, t[8] = d + y, t[9] = p - h, t[10] = 1 - (s + v), t[11] = 0, t[12] = n[0], t[13] = n[1], t[14] = n[2], t[15] = 1, t
            }, a.fromRotationTranslationScale = function(t, e, n, r) {
                var a = e[0],
                    o = e[1],
                    i = e[2],
                    u = e[3],
                    c = a + a,
                    f = o + o,
                    s = i + i,
                    l = a * c,
                    d = a * f,
                    v = a * s,
                    p = o * f,
                    m = o * s,
                    h = i * s,
                    y = u * c,
                    g = u * f,
                    b = u * s,
                    M = r[0],
                    A = r[1],
                    I = r[2];
                return t[0] = (1 - (p + h)) * M, t[1] = (d + b) * M, t[2] = (v - g) * M, t[3] = 0, t[4] = (d - b) * A, t[5] = (1 - (l + h)) * A, t[6] = (m + y) * A, t[7] = 0, t[8] = (v + g) * I, t[9] = (m - y) * I, t[10] = (1 - (l + p)) * I, t[11] = 0, t[12] = n[0], t[13] = n[1], t[14] = n[2], t[15] = 1, t
            }, a.fromRotationTranslationScaleOrigin = function(t, e, n, r, a) {
                var o = e[0],
                    i = e[1],
                    u = e[2],
                    c = e[3],
                    f = o + o,
                    s = i + i,
                    l = u + u,
                    d = o * f,
                    v = o * s,
                    p = o * l,
                    m = i * s,
                    h = i * l,
                    y = u * l,
                    g = c * f,
                    b = c * s,
                    M = c * l,
                    A = r[0],
                    I = r[1],
                    T = r[2],
                    O = a[0],
                    w = a[1],
                    x = a[2];
                return t[0] = (1 - (m + y)) * A, t[1] = (v + M) * A, t[2] = (p - b) * A, t[3] = 0, t[4] = (v - M) * I, t[5] = (1 - (d + y)) * I, t[6] = (h + g) * I, t[7] = 0, t[8] = (p + b) * T, t[9] = (h - g) * T, t[10] = (1 - (d + m)) * T, t[11] = 0, t[12] = n[0] + O - (t[0] * O + t[4] * w + t[8] * x), t[13] = n[1] + w - (t[1] * O + t[5] * w + t[9] * x), t[14] = n[2] + x - (t[2] * O + t[6] * w + t[10] * x), t[15] = 1, t
            }, a.fromQuat = function(t, e) {
                var n = e[0],
                    r = e[1],
                    a = e[2],
                    o = e[3],
                    i = n + n,
                    u = r + r,
                    c = a + a,
                    f = n * i,
                    s = r * i,
                    l = r * u,
                    d = a * i,
                    v = a * u,
                    p = a * c,
                    m = o * i,
                    h = o * u,
                    y = o * c;
                return t[0] = 1 - l - p, t[1] = s + y, t[2] = d - h, t[3] = 0, t[4] = s - y, t[5] = 1 - f - p, t[6] = v + m, t[7] = 0, t[8] = d + h, t[9] = v - m, t[10] = 1 - f - l, t[11] = 0, t[12] = 0, t[13] = 0, t[14] = 0, t[15] = 1, t
            }, a.frustum = function(t, e, n, r, a, o, i) {
                var u = 1 / (n - e),
                    c = 1 / (a - r),
                    f = 1 / (o - i);
                return t[0] = 2 * o * u, t[1] = 0, t[2] = 0, t[3] = 0, t[4] = 0, t[5] = 2 * o * c, t[6] = 0, t[7] = 0, t[8] = (n + e) * u, t[9] = (a + r) * c, t[10] = (i + o) * f, t[11] = -1, t[12] = 0, t[13] = 0, t[14] = i * o * 2 * f, t[15] = 0, t
            }, a.perspective = function(t, e, n, r, a) {
                var o = 1 / Math.tan(e / 2),
                    i = 1 / (r - a);
                return t[0] = o / n, t[1] = 0, t[2] = 0, t[3] = 0, t[4] = 0, t[5] = o, t[6] = 0, t[7] = 0, t[8] = 0, t[9] = 0, t[10] = (a + r) * i, t[11] = -1, t[12] = 0, t[13] = 0, t[14] = 2 * a * r * i, t[15] = 0, t
            }, a.perspectiveFromFieldOfView = function(t, e, n, r) {
                var a = Math.tan(e.upDegrees * Math.PI / 180),
                    o = Math.tan(e.downDegrees * Math.PI / 180),
                    i = Math.tan(e.leftDegrees * Math.PI / 180),
                    u = Math.tan(e.rightDegrees * Math.PI / 180),
                    c = 2 / (i + u),
                    f = 2 / (a + o);
                return t[0] = c, t[1] = 0, t[2] = 0, t[3] = 0, t[4] = 0, t[5] = f, t[6] = 0, t[7] = 0, t[8] = -(i - u) * c * .5, t[9] = (a - o) * f * .5, t[10] = r / (n - r), t[11] = -1, t[12] = 0, t[13] = 0, t[14] = r * n / (n - r), t[15] = 0, t
            }, a.ortho = function(t, e, n, r, a, o, i) {
                var u = 1 / (e - n),
                    c = 1 / (r - a),
                    f = 1 / (o - i);
                return t[0] = -2 * u, t[1] = 0, t[2] = 0, t[3] = 0, t[4] = 0, t[5] = -2 * c, t[6] = 0, t[7] = 0, t[8] = 0, t[9] = 0, t[10] = 2 * f, t[11] = 0, t[12] = (e + n) * u, t[13] = (a + r) * c, t[14] = (i + o) * f, t[15] = 1, t
            }, a.lookAt = function(t, e, n, o) {
                var i, u, c, f, s, l, d, v, p, m, h = e[0],
                    y = e[1],
                    g = e[2],
                    b = o[0],
                    M = o[1],
                    A = o[2],
                    I = n[0],
                    T = n[1],
                    O = n[2];
                return Math.abs(h - I) < r.EPSILON && Math.abs(y - T) < r.EPSILON && Math.abs(g - O) < r.EPSILON ? a.identity(t) : (d = h - I, v = y - T, p = g - O, i = M * (p *= m = 1 / Math.sqrt(d * d + v * v + p * p)) - A * (v *= m), u = A * (d *= m) - b * p, c = b * v - M * d, (m = Math.sqrt(i * i + u * u + c * c)) ? (i *= m = 1 / m, u *= m, c *= m) : (i = 0, u = 0, c = 0), f = v * c - p * u, s = p * i - d * c, l = d * u - v * i, (m = Math.sqrt(f * f + s * s + l * l)) ? (f *= m = 1 / m, s *= m, l *= m) : (f = 0, s = 0, l = 0), t[0] = i, t[1] = f, t[2] = d, t[3] = 0, t[4] = u, t[5] = s, t[6] = v, t[7] = 0, t[8] = c, t[9] = l, t[10] = p, t[11] = 0, t[12] = -(i * h + u * y + c * g), t[13] = -(f * h + s * y + l * g), t[14] = -(d * h + v * y + p * g), t[15] = 1, t)
            }, a.str = function(t) {
                return "mat4(" + t[0] + ", " + t[1] + ", " + t[2] + ", " + t[3] + ", " + t[4] + ", " + t[5] + ", " + t[6] + ", " + t[7] + ", " + t[8] + ", " + t[9] + ", " + t[10] + ", " + t[11] + ", " + t[12] + ", " + t[13] + ", " + t[14] + ", " + t[15] + ")"
            }, a.frob = function(t) {
                return Math.sqrt(Math.pow(t[0], 2) + Math.pow(t[1], 2) + Math.pow(t[2], 2) + Math.pow(t[3], 2) + Math.pow(t[4], 2) + Math.pow(t[5], 2) + Math.pow(t[6], 2) + Math.pow(t[7], 2) + Math.pow(t[8], 2) + Math.pow(t[9], 2) + Math.pow(t[10], 2) + Math.pow(t[11], 2) + Math.pow(t[12], 2) + Math.pow(t[13], 2) + Math.pow(t[14], 2) + Math.pow(t[15], 2))
            }, t.exports = a
        },
        195: function(t, e, n) {
            var r = n(28),
                a = {
                    create: function() {
                        var t = new r.ARRAY_TYPE(6);
                        return t[0] = 1, t[1] = 0, t[2] = 0, t[3] = 1, t[4] = 0, t[5] = 0, t
                    },
                    clone: function(t) {
                        var e = new r.ARRAY_TYPE(6);
                        return e[0] = t[0], e[1] = t[1], e[2] = t[2], e[3] = t[3], e[4] = t[4], e[5] = t[5], e
                    },
                    copy: function(t, e) {
                        return t[0] = e[0], t[1] = e[1], t[2] = e[2], t[3] = e[3], t[4] = e[4], t[5] = e[5], t
                    },
                    identity: function(t) {
                        return t[0] = 1, t[1] = 0, t[2] = 0, t[3] = 1, t[4] = 0, t[5] = 0, t
                    },
                    invert: function(t, e) {
                        var n = e[0],
                            r = e[1],
                            a = e[2],
                            o = e[3],
                            i = e[4],
                            u = e[5],
                            c = n * o - r * a;
                        return c ? (c = 1 / c, t[0] = o * c, t[1] = -r * c, t[2] = -a * c, t[3] = n * c, t[4] = (a * u - o * i) * c, t[5] = (r * i - n * u) * c, t) : null
                    },
                    determinant: function(t) {
                        return t[0] * t[3] - t[1] * t[2]
                    },
                    multiply: function(t, e, n) {
                        var r = e[0],
                            a = e[1],
                            o = e[2],
                            i = e[3],
                            u = e[4],
                            c = e[5],
                            f = n[0],
                            s = n[1],
                            l = n[2],
                            d = n[3],
                            v = n[4],
                            p = n[5];
                        return t[0] = r * f + o * s, t[1] = a * f + i * s, t[2] = r * l + o * d, t[3] = a * l + i * d, t[4] = r * v + o * p + u, t[5] = a * v + i * p + c, t
                    }
                };
            a.mul = a.multiply, a.rotate = function(t, e, n) {
                var r = e[0],
                    a = e[1],
                    o = e[2],
                    i = e[3],
                    u = e[4],
                    c = e[5],
                    f = Math.sin(n),
                    s = Math.cos(n);
                return t[0] = r * s + o * f, t[1] = a * s + i * f, t[2] = r * -f + o * s, t[3] = a * -f + i * s, t[4] = u, t[5] = c, t
            }, a.scale = function(t, e, n) {
                var r = e[0],
                    a = e[1],
                    o = e[2],
                    i = e[3],
                    u = e[4],
                    c = e[5],
                    f = n[0],
                    s = n[1];
                return t[0] = r * f, t[1] = a * f, t[2] = o * s, t[3] = i * s, t[4] = u, t[5] = c, t
            }, a.translate = function(t, e, n) {
                var r = e[0],
                    a = e[1],
                    o = e[2],
                    i = e[3],
                    u = e[4],
                    c = e[5],
                    f = n[0],
                    s = n[1];
                return t[0] = r, t[1] = a, t[2] = o, t[3] = i, t[4] = r * f + o * s + u, t[5] = a * f + i * s + c, t
            }, a.fromRotation = function(t, e) {
                var n = Math.sin(e),
                    r = Math.cos(e);
                return t[0] = r, t[1] = n, t[2] = -n, t[3] = r, t[4] = 0, t[5] = 0, t
            }, a.fromScaling = function(t, e) {
                return t[0] = e[0], t[1] = 0, t[2] = 0, t[3] = e[1], t[4] = 0, t[5] = 0, t
            }, a.fromTranslation = function(t, e) {
                return t[0] = 1, t[1] = 0, t[2] = 0, t[3] = 1, t[4] = e[0], t[5] = e[1], t
            }, a.str = function(t) {
                return "mat2d(" + t[0] + ", " + t[1] + ", " + t[2] + ", " + t[3] + ", " + t[4] + ", " + t[5] + ")"
            }, a.frob = function(t) {
                return Math.sqrt(Math.pow(t[0], 2) + Math.pow(t[1], 2) + Math.pow(t[2], 2) + Math.pow(t[3], 2) + Math.pow(t[4], 2) + Math.pow(t[5], 2) + 1)
            }, t.exports = a
        },
        196: function(t, e, n) {
            var r = n(28),
                a = {
                    create: function() {
                        var t = new r.ARRAY_TYPE(4);
                        return t[0] = 1, t[1] = 0, t[2] = 0, t[3] = 1, t
                    },
                    clone: function(t) {
                        var e = new r.ARRAY_TYPE(4);
                        return e[0] = t[0], e[1] = t[1], e[2] = t[2], e[3] = t[3], e
                    },
                    copy: function(t, e) {
                        return t[0] = e[0], t[1] = e[1], t[2] = e[2], t[3] = e[3], t
                    },
                    identity: function(t) {
                        return t[0] = 1, t[1] = 0, t[2] = 0, t[3] = 1, t
                    },
                    transpose: function(t, e) {
                        if (t === e) {
                            var n = e[1];
                            t[1] = e[2], t[2] = n
                        } else t[0] = e[0], t[1] = e[2], t[2] = e[1], t[3] = e[3];
                        return t
                    },
                    invert: function(t, e) {
                        var n = e[0],
                            r = e[1],
                            a = e[2],
                            o = e[3],
                            i = n * o - a * r;
                        return i ? (i = 1 / i, t[0] = o * i, t[1] = -r * i, t[2] = -a * i, t[3] = n * i, t) : null
                    },
                    adjoint: function(t, e) {
                        var n = e[0];
                        return t[0] = e[3], t[1] = -e[1], t[2] = -e[2], t[3] = n, t
                    },
                    determinant: function(t) {
                        return t[0] * t[3] - t[2] * t[1]
                    },
                    multiply: function(t, e, n) {
                        var r = e[0],
                            a = e[1],
                            o = e[2],
                            i = e[3],
                            u = n[0],
                            c = n[1],
                            f = n[2],
                            s = n[3];
                        return t[0] = r * u + o * c, t[1] = a * u + i * c, t[2] = r * f + o * s, t[3] = a * f + i * s, t
                    }
                };
            a.mul = a.multiply, a.rotate = function(t, e, n) {
                var r = e[0],
                    a = e[1],
                    o = e[2],
                    i = e[3],
                    u = Math.sin(n),
                    c = Math.cos(n);
                return t[0] = r * c + o * u, t[1] = a * c + i * u, t[2] = r * -u + o * c, t[3] = a * -u + i * c, t
            }, a.scale = function(t, e, n) {
                var r = e[0],
                    a = e[1],
                    o = e[2],
                    i = e[3],
                    u = n[0],
                    c = n[1];
                return t[0] = r * u, t[1] = a * u, t[2] = o * c, t[3] = i * c, t
            }, a.fromRotation = function(t, e) {
                var n = Math.sin(e),
                    r = Math.cos(e);
                return t[0] = r, t[1] = n, t[2] = -n, t[3] = r, t
            }, a.fromScaling = function(t, e) {
                return t[0] = e[0], t[1] = 0, t[2] = 0, t[3] = e[1], t
            }, a.str = function(t) {
                return "mat2(" + t[0] + ", " + t[1] + ", " + t[2] + ", " + t[3] + ")"
            }, a.frob = function(t) {
                return Math.sqrt(Math.pow(t[0], 2) + Math.pow(t[1], 2) + Math.pow(t[2], 2) + Math.pow(t[3], 2))
            }, a.LDU = function(t, e, n, r) {
                return t[2] = r[2] / r[0], n[0] = r[0], n[1] = r[1], n[3] = r[3] - t[2] * n[1], [t, e, n]
            }, t.exports = a
        },
        197: function(t, e, n) {
            "use strict";
            Object.defineProperty(e, "__esModule", {
                value: !0
            }), e.getDataDescriptionFromExtent = u;
            var r, a = n(115),
                o = (r = a) && r.__esModule ? r : {
                    default: r
                };
            var i = o.default.StructuredType;

            function u(t) {
                for (var e = 0, n = 0; n < 3; ++n) t[2 * n] < t[2 * n + 1] && e++;
                return t[0] > t[1] || t[2] > t[3] || t[4] > t[5] ? i.EMPTY : 3 === e ? i.XYZ_GRID : 2 === e ? t[0] === t[1] ? i.YZ_PLANE : t[2] === t[3] ? i.XZ_PLANE : i.XY_PLANE : 1 === e ? t[0] < t[1] ? i.X_LINE : t[2] < t[3] ? i.Y_LINE : i.Z_LINE : i.SINGLE_POINT
            }
            e.default = Object.assign({
                getDataDescriptionFromExtent: u
            }, o.default)
        },
        198: function(t, e, n) {
            "use strict";
            Object.defineProperty(e, "__esModule", {
                value: !0
            });
            var r = e.FieldDataTypes = {
                    UNIFORM: 0,
                    DATA_OBJECT_FIELD: 0,
                    COORDINATE: 1,
                    POINT_DATA: 1,
                    POINT: 2,
                    POINT_FIELD_DATA: 2,
                    CELL: 3,
                    CELL_FIELD_DATA: 3,
                    VERTEX: 4,
                    VERTEX_FIELD_DATA: 4,
                    EDGE: 5,
                    EDGE_FIELD_DATA: 5,
                    ROW: 6,
                    ROW_DATA: 6
                },
                a = e.FieldAssociations = {
                    FIELD_ASSOCIATION_POINTS: 0,
                    FIELD_ASSOCIATION_CELLS: 1,
                    FIELD_ASSOCIATION_NONE: 2,
                    FIELD_ASSOCIATION_POINTS_THEN_CELLS: 3,
                    FIELD_ASSOCIATION_VERTICES: 4,
                    FIELD_ASSOCIATION_EDGES: 5,
                    FIELD_ASSOCIATION_ROWS: 6,
                    NUMBER_OF_ASSOCIATIONS: 7
                };
            e.default = {
                FieldDataTypes: r,
                FieldAssociations: a
            }
        },
        199: function(t, e, n) {
            "use strict";
            Object.defineProperty(e, "__esModule", {
                value: !0
            });
            var r = e.AttributeTypes = {
                    SCALARS: 0,
                    VECTORS: 1,
                    NORMALS: 2,
                    TCOORDS: 3,
                    TENSORS: 4,
                    GLOBALIDS: 5,
                    PEDIGREEIDS: 6,
                    EDGEFLAG: 7,
                    NUM_ATTRIBUTES: 8
                },
                a = e.AttributeLimitTypes = {
                    MAX: 0,
                    EXACT: 1,
                    NOLIMIT: 2
                },
                o = e.CellGhostTypes = {
                    DUPLICATECELL: 1,
                    HIGHCONNECTIVITYCELL: 2,
                    LOWCONNECTIVITYCELL: 4,
                    REFINEDCELL: 8,
                    EXTERIORCELL: 16,
                    HIDDENCELL: 32
                },
                i = e.PointGhostTypes = {
                    DUPLICATEPOINT: 1,
                    HIDDENPOINT: 2
                },
                u = e.AttributeCopyOperations = {
                    COPYTUPLE: 0,
                    INTERPOLATE: 1,
                    PASSDATA: 2,
                    ALLCOPY: 3
                },
                c = e.ghostArrayName = "vtkGhostType",
                f = e.DesiredOutputPrecision = {
                    DEFAULT: 0,
                    SINGLE: 1,
                    DOUBLE: 2
                };
            e.default = {
                AttributeCopyOperations: u,
                AttributeLimitTypes: a,
                AttributeTypes: r,
                CellGhostTypes: o,
                DesiredOutputPrecision: f,
                PointGhostTypes: i,
                ghostArrayName: c
            }
        },
        2: function(t, e, n) {
            "use strict";
            (function(t, r) {
                Object.defineProperty(e, "__esModule", {
                    value: !0
                }), e.EVENT_ABORT = e.TYPED_ARRAYS = e.VOID = void 0;
                var a = function() {
                        return function(t, e) {
                            if (Array.isArray(t)) return t;
                            if (Symbol.iterator in Object(t)) return function(t, e) {
                                var n = [],
                                    r = !0,
                                    a = !1,
                                    o = void 0;
                                try {
                                    for (var i, u = t[Symbol.iterator](); !(r = (i = u.next()).done) && (n.push(i.value), !e || n.length !== e); r = !0);
                                } catch (t) {
                                    a = !0, o = t
                                } finally {
                                    try {
                                        !r && u.return && u.return()
                                    } finally {
                                        if (a) throw o
                                    }
                                }
                                return n
                            }(t, e);
                            throw new TypeError("Invalid attempt to destructure non-iterable instance")
                        }
                    }(),
                    o = "function" == typeof Symbol && "symbol" == typeof Symbol.iterator ? function(t) {
                        return typeof t
                    } : function(t) {
                        return t && "function" == typeof Symbol && t.constructor === Symbol && t !== Symbol.prototype ? "symbol" : typeof t
                    };
                e.setLoggerFunction = p, e.vtkLogMacro = m, e.vtkInfoMacro = h, e.vtkDebugMacro = y, e.vtkErrorMacro = g, e.vtkWarningMacro = b, e.capitalize = A, e.uncapitalize = I, e.formatBytesToProperUnit = T, e.formatNumbersWithThousandSeparator = O, e.obj = E, e.get = k, e.set = _, e.setGet = D, e.getArray = C, e.setArray = P, e.setGetArray = L, e.algo = j, e.event = F, e.newInstance = B, e.chain = Y, e.isVtkObject = q, e.traverseInstanceTree = G, e.debounce = z, e.throttle = V, e.keystore = U, e.proxy = W, e.proxyPropertyMapping = H, e.proxyPropertyState = K, e.normalizeWheel = tt;
                var i = function(t) {
                    return t && t.__esModule ? t : {
                        default: t
                    }
                }(n(49));

                function u(t, e, n) {
                    return e in t ? Object.defineProperty(t, e, {
                        value: n,
                        enumerable: !0,
                        configurable: !0,
                        writable: !0
                    }) : t[e] = n, t
                }

                function c(t) {
                    if (Array.isArray(t)) {
                        for (var e = 0, n = Array(t.length); e < t.length; e++) n[e] = t[e];
                        return n
                    }
                    return Array.from(t)
                }
                var f = 0,
                    s = e.VOID = Symbol("void");
                var l = {};

                function d() {}["log", "debug", "info", "warn", "error", "time", "timeEnd", "group", "groupEnd"].forEach(function(t) {
                    l[t] = d
                }), t.console = console.hasOwnProperty("log") ? console : l;
                var v = {
                    debug: d,
                    error: t.console.error || d,
                    info: t.console.info || d,
                    log: t.console.log || d,
                    warn: t.console.warn || d
                };

                function p(t, e) {
                    v[t] && (v[t] = e || d)
                }

                function m() {
                    v.log.apply(v, arguments)
                }

                function h() {
                    v.info.apply(v, arguments)
                }

                function y() {
                    v.debug.apply(v, arguments)
                }

                function g() {
                    v.error.apply(v, arguments)
                }

                function b() {
                    v.warn.apply(v, arguments)
                }
                var M = e.TYPED_ARRAYS = {
                    Float32Array: Float32Array,
                    Float64Array: Float64Array,
                    Uint8Array: Uint8Array,
                    Int8Array: Int8Array,
                    Uint16Array: Uint16Array,
                    Int16Array: Int16Array,
                    Uint32Array: Uint32Array,
                    Int32Array: Int32Array
                };

                function A(t) {
                    return t.charAt(0).toUpperCase() + t.slice(1)
                }

                function I(t) {
                    return t.charAt(0).toLowerCase() + t.slice(1)
                }

                function T(t) {
                    for (var e = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : 2, n = arguments.length > 2 && void 0 !== arguments[2] ? arguments[2] : 1e3, r = ["TB", "GB", "MB", "KB"], a = Number(t), o = "B"; a > n;) a /= n, o = r.pop();
                    return a.toFixed(e) + " " + o
                }

                function O(t) {
                    for (var e = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : " ", n = [], r = t; r > 1e3;) n.push(("000" + r % 1e3).slice(-3)), r = Math.floor(r / 1e3);
                    return r > 0 && n.push(r), n.reverse(), n.join(e)
                }

                function w(t) {
                    Object.keys(t).forEach(function(e) {
                        Array.isArray(t[e]) && (t[e] = [].concat(t[e]))
                    })
                }

                function x(t) {
                    return t.isA ? t.getState() : t
                }

                function E() {
                    var t = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : {},
                        e = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : {};
                    w(e);
                    var n = [];

                    function r(t) {
                        n[t] = null
                    }
                    return Number.isInteger(e.mtime) || (e.mtime = ++f), e.classHierarchy = ["vtkObject"], t.isDeleted = function() {
                        return !!e.deleted
                    }, t.modified = function(r) {
                        e.deleted ? g("instance deleted - cannot call any method") : r && r < t.getMTime() || (e.mtime = ++f, n.forEach(function(e) {
                            return e && e(t)
                        }))
                    }, t.onModified = function(t) {
                        if (e.deleted) return g("instance deleted - cannot call any method"), null;
                        var a = n.length;
                        return n.push(t),
                            function(t) {
                                return Object.freeze({
                                    unsubscribe: function() {
                                        r(t)
                                    }
                                })
                            }(a)
                    }, t.getMTime = function() {
                        return e.mtime
                    }, t.isA = function(t) {
                        for (var n = e.classHierarchy.length; n--;)
                            if (e.classHierarchy[n] === t) return !0;
                        return !1
                    }, t.getClassName = function() {
                        var t = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : 0;
                        return e.classHierarchy[e.classHierarchy.length - 1 - t]
                    }, t.set = function() {
                        var n = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : {},
                            r = arguments.length > 1 && void 0 !== arguments[1] && arguments[1],
                            a = arguments.length > 2 && void 0 !== arguments[2] && arguments[2],
                            o = !1;
                        return Object.keys(n).forEach(function(i) {
                            var u = a ? null : t["set" + A(i)];
                            u && Array.isArray(n[i]) ? o = u.apply(void 0, c(n[i])) || o : u ? o = u(n[i]) || o : (-1 !== ["mtime"].indexOf(i) || r || b("Warning: Set value to model directly " + i + ", " + n[i]), e[i] = n[i], o = !0)
                        }), o
                    }, t.get = function() {
                        for (var t = arguments.length, n = Array(t), r = 0; r < t; r++) n[r] = arguments[r];
                        if (!n.length) return e;
                        var a = {};
                        return n.forEach(function(t) {
                            a[t] = e[t]
                        }), a
                    }, t.getReferenceByName = function(t) {
                        return e[t]
                    }, t.delete = function() {
                        Object.keys(e).forEach(function(t) {
                            return delete e[t]
                        }), n.forEach(function(t, e) {
                            return r(e)
                        }), e.deleted = !0
                    }, t.getState = function() {
                        var n = Object.assign({}, e, {
                            vtkClass: t.getClassName()
                        });
                        Object.keys(n).forEach(function(t) {
                            null === n[t] || void 0 === n[t] ? delete n[t] : n[t].isA ? n[t] = n[t].getState() : Array.isArray(n[t]) && (n[t] = n[t].map(x))
                        });
                        var r = {};
                        return Object.keys(n).sort().forEach(function(t) {
                            r[t] = n[t]
                        }), r.mtime && delete r.mtime, r
                    }, t.shallowCopy = function(n) {
                        var r = arguments.length > 1 && void 0 !== arguments[1] && arguments[1];
                        if (n.getClassName() !== t.getClassName()) throw new Error("Cannot ShallowCopy " + n.getClassName() + " into " + t.getClassName());
                        var a = n.get(),
                            o = Object.keys(e).sort();
                        Object.keys(a).sort().forEach(function(t) {
                            var n = o.indexOf(t); - 1 === n ? r && y("add " + t + " in shallowCopy") : o.splice(n, 1), e[t] = a[t]
                        }), o.length && r && y("Untouched keys: " + o.join(", ")), t.modified()
                    }, t
                }

                function k(t, e, n) {
                    n.forEach(function(n) {
                        "object" === (void 0 === n ? "undefined" : o(n)) ? t["get" + A(n.name)] = function() {
                            return e[n.name]
                        }: t["get" + A(n)] = function() {
                            return e[n]
                        }
                    })
                }
                var S = {
                    enum: function(t, e, n) {
                        return function(r) {
                            if ("string" == typeof r) {
                                if (void 0 !== n.enum[r]) return e[n.name] !== n.enum[r] && (e[n.name] = n.enum[r], t.modified(), !0);
                                throw g("Set Enum with invalid argument " + n + ", " + r), new RangeError("Set Enum with invalid string argument")
                            }
                            if ("number" == typeof r) {
                                if (e[n.name] !== r) {
                                    if (-1 !== Object.keys(n.enum).map(function(t) {
                                            return n.enum[t]
                                        }).indexOf(r)) return e[n.name] = r, t.modified(), !0;
                                    throw g("Set Enum outside numeric range " + n + ", " + r), new RangeError("Set Enum outside numeric range")
                                }
                                return !1
                            }
                            throw g("Set Enum with invalid argument (String/Number) " + n + ", " + r), new TypeError("Set Enum with invalid argument (String/Number)")
                        }
                    }
                };

                function N(t) {
                    if ("object" === (void 0 === t ? "undefined" : o(t))) {
                        var e = S[t.type];
                        if (e) return function(n, r) {
                            return e(n, r, t)
                        };
                        throw g("No setter for field " + t), new TypeError("No setter for field")
                    }
                    return function(e, n) {
                        return function(r) {
                            return n.deleted ? (g("instance deleted - cannot call any method"), !1) : n[t] !== r && (n[t] = r, e.modified(), !0)
                        }
                    }
                }

                function _(t, e, n) {
                    n.forEach(function(n) {
                        "object" === (void 0 === n ? "undefined" : o(n)) ? t["set" + A(n.name)] = N(n)(t, e): t["set" + A(n)] = N(n)(t, e)
                    })
                }

                function D(t, e, n) {
                    k(t, e, n), _(t, e, n)
                }

                function C(t, e, n) {
                    n.forEach(function(n) {
                        t["get" + A(n)] = function() {
                            return [].concat(e[n])
                        }, t["get" + A(n) + "ByReference"] = function() {
                            return e[n]
                        }
                    })
                }

                function P(t, e, n, r) {
                    var a = arguments.length > 4 && void 0 !== arguments[4] ? arguments[4] : void 0;
                    n.forEach(function(n) {
                        t["set" + A(n)] = function() {
                            for (var o = arguments.length, i = Array(o), u = 0; u < o; u++) i[u] = arguments[u];
                            if (e.deleted) return g("instance deleted - cannot call any method"), !1;
                            var c = i;
                            if (1 === c.length && Array.isArray(c[0]) && (c = c[0]), c.length !== r) {
                                if (!(c.length < r && void 0 !== a)) throw new RangeError("Invalid number of values for array setter");
                                for (c = [].concat(c); c.length < r;) c.push(a)
                            }
                            var f = !1;
                            return e[n].forEach(function(t, e) {
                                if (t !== c[e]) {
                                    if (f) return;
                                    f = !0
                                }
                            }), (f || e[n].length !== c.length) && (e[n] = [].concat(c), t.modified()), !0
                        }, t["set" + A(n) + "From"] = function(t) {
                            var r = e[n];
                            t.forEach(function(t, e) {
                                r[e] = t
                            })
                        }
                    })
                }

                function L(t, e, n, r) {
                    var a = arguments.length > 4 && void 0 !== arguments[4] ? arguments[4] : void 0;
                    C(t, e, n), P(t, e, n, r, a)
                }

                function j(t, e, n, r) {
                    function a(n) {
                        var r = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : 0;
                        if (e.deleted) g("instance deleted - cannot call any method");
                        else {
                            if (r >= e.numberOfInputs) {
                                var a = "algorithm " + t.getClassName() + " only has ";
                                return a += "" + e.numberOfInputs, void g(a += " input ports. To add more input ports, use addInputData()")
                            }(e.inputData[r] !== n || e.inputConnection[r]) && (e.inputData[r] = n, e.inputConnection[r] = null, t.modified && t.modified())
                        }
                    }

                    function o(n) {
                        var r = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : 0;
                        if (e.deleted) g("instance deleted - cannot call any method");
                        else {
                            if (r >= e.numberOfInputs) {
                                var a = "algorithm " + t.getClassName() + " only has ";
                                return a += "" + e.numberOfInputs, void g(a += " input ports. To add more input ports, use addInputConnection()")
                            }
                            e.inputData[r] = null, e.inputConnection[r] = n
                        }
                    }

                    function u() {
                        var n = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : 0;
                        return e.deleted ? (g("instance deleted - cannot call any method"), null) : (t.shouldUpdate() && t.update(), e.output[n])
                    }
                    if (e.inputData ? e.inputData = e.inputData.map(i.default) : e.inputData = [], e.inputConnection ? e.inputConnection = e.inputConnection.map(i.default) : e.inputConnection = [], e.output ? e.output = e.output.map(i.default) : e.output = [], e.inputArrayToProcess ? e.inputArrayToProcess = e.inputArrayToProcess.map(i.default) : e.inputArrayToProcess = [], e.numberOfInputs = n, t.shouldUpdate = function() {
                            for (var n = t.getMTime(), a = r, o = 1 / 0; a--;) {
                                if (!e.output[a]) return !0;
                                var i = e.output[a].getMTime();
                                if (i < n) return !0;
                                i < o && (o = i)
                            }
                            for (a = e.numberOfInputs; a--;)
                                if (e.inputConnection[a] && e.inputConnection[a].filter.shouldUpdate()) return !0;
                            for (a = e.numberOfInputs; a--;)
                                if (t.getInputData(a) && t.getInputData(a).getMTime() > o) return !0;
                            return !1
                        }, e.numberOfInputs) {
                        for (var c = e.numberOfInputs; c--;) e.inputData.push(null), e.inputConnection.push(null);
                        t.setInputData = a, t.setInputConnection = o, t.addInputData = function(t) {
                            e.deleted ? g("instance deleted - cannot call any method") : (e.numberOfInputs++, a(t, e.numberOfInputs - 1))
                        }, t.addInputConnection = function(t) {
                            e.deleted ? g("instance deleted - cannot call any method") : (e.numberOfInputs++, o(t, e.numberOfInputs - 1))
                        }, t.getInputData = function() {
                            var t = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : 0;
                            return e.inputConnection[t] && (e.inputData[t] = e.inputConnection[t]()), e.inputData[t]
                        }, t.getInputConnection = function() {
                            var t = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : 0;
                            return e.inputConnection[t]
                        }
                    }
                    r && (t.getOutputData = u, t.getOutputPort = function() {
                        var e = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : 0,
                            n = function() {
                                return u(e)
                            };
                        return n.filter = t, n
                    }), t.update = function() {
                        var n = [];
                        if (e.numberOfInputs)
                            for (var r = 0; r < e.numberOfInputs;) n[r] = t.getInputData(r), r++;
                        t.shouldUpdate() && t.requestData && t.requestData(n, e.output)
                    }, t.getNumberOfInputPorts = function() {
                        return e.numberOfInputs
                    }, t.getNumberOfOutputPorts = function() {
                        return r
                    }, t.getInputArrayToProcess = function(t) {
                        var n = e.inputArrayToProcess[t],
                            r = e.inputData[t];
                        return n && r ? r["get" + n.fieldAssociation]().getArray(n.arrayName) : null
                    }, t.setInputArrayToProcess = function(t, n, r) {
                        for (var a = arguments.length > 3 && void 0 !== arguments[3] ? arguments[3] : "Scalars"; e.inputArrayToProcess.length < t;) e.inputArrayToProcess.push(null);
                        e.inputArrayToProcess[t] = {
                            arrayName: n,
                            fieldAssociation: r,
                            attributeType: a
                        }
                    }
                }
                var R = e.EVENT_ABORT = Symbol("Event abort");

                function F(t, e, n) {
                    var r = [],
                        o = t.delete,
                        i = 1;

                    function u(t) {
                        for (var e = 0; e < r.length; ++e) {
                            if (a(r[e], 1)[0] === t) return void r.splice(e, 1)
                        }
                    }
                    t["invoke" + A(n)] = function() {
                        var n = arguments;
                        if (e.deleted) g("instance deleted - cannot call any method");
                        else
                            for (var o = r.slice(), i = function(e) {
                                    var r = a(o[e], 3),
                                        i = r[1],
                                        u = r[2];
                                    if (u < 0) setTimeout(function() {
                                        return i.apply(t, n)
                                    }, 1 - u);
                                    else if (i && i.apply(t, n) === R) return "break"
                                }, u = 0; u < o.length && "break" !== i(u); ++u);
                    }, t["on" + A(n)] = function(t) {
                        var n = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : 0;
                        if (e.deleted) return g("instance deleted - cannot call any method"), null;
                        var a = i++;
                        return r.push([a, t, n]), r.sort(function(t, e) {
                                return e[2] - t[2]
                            }),
                            function(t) {
                                return Object.freeze({
                                    unsubscribe: function() {
                                        u(t)
                                    }
                                })
                            }(a)
                    }, t.delete = function() {
                        o(), r.forEach(function(t) {
                            return u(a(t, 1)[0])
                        })
                    }
                }

                function B(t, e) {
                    var n = function() {
                        var e = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : {},
                            n = {};
                        return t(n, {}, e), Object.freeze(n)
                    };
                    return e && i.default.register(e, n), n
                }

                function Y() {
                    for (var t = arguments.length, e = Array(t), n = 0; n < t; n++) e[n] = arguments[n];
                    return function() {
                        for (var t = arguments.length, n = Array(t), r = 0; r < t; r++) n[r] = arguments[r];
                        return e.filter(function(t) {
                            return !!t
                        }).forEach(function(t) {
                            return t.apply(void 0, n)
                        })
                    }
                }

                function q(t) {
                    return t && t.isA && t.isA("vtkObject")
                }

                function G(t, e) {
                    var n = arguments.length > 2 && void 0 !== arguments[2] ? arguments[2] : [],
                        r = arguments.length > 3 && void 0 !== arguments[3] ? arguments[3] : [];
                    if (q(t)) {
                        if (r.indexOf(t) >= 0) return n;
                        r.push(t);
                        var a = e(t);
                        void 0 !== a && n.push(a);
                        var o = t.get();
                        Object.keys(o).forEach(function(t) {
                            var a = o[t];
                            Array.isArray(a) ? a.forEach(function(t) {
                                G(t, e, n, r)
                            }) : G(a, e, n, r)
                        })
                    }
                    return n
                }

                function z(t, e, n) {
                    var r = this,
                        a = void 0;
                    return function() {
                        for (var o = arguments.length, i = Array(o), u = 0; u < o; u++) i[u] = arguments[u];
                        var c = r,
                            f = n && !a;
                        clearTimeout(a), a = setTimeout(function() {
                            a = null, n || t.apply(c, i)
                        }, e), f && t.apply(c, i)
                    }
                }

                function V(t, e) {
                    var n = !1,
                        r = null;

                    function a() {
                        n = !1, null !== r && (o.apply(void 0, c(r)), r = null)
                    }

                    function o() {
                        for (var o = arguments.length, i = Array(o), u = 0; u < o; u++) i[u] = arguments[u];
                        n ? r = i : (n = !0, t.apply(void 0, i), setTimeout(a, e))
                    }
                    return o
                }

                function U(t, e) {
                    var n = arguments.length > 2 && void 0 !== arguments[2] ? arguments[2] : {};
                    e.keystore = Object.assign(e.keystore || {}, n), t.setKey = function(t, n) {
                        e.keystore[t] = n
                    }, t.getKey = function(t, n) {
                        return e.keystore[t]
                    }, t.getAllKeys = function(t, n) {
                        return Object.keys(e.keystore)
                    }, t.deleteKey = function(t, n) {
                        return delete e.keystore[t]
                    }, t.clearKeystore = function() {
                        return t.getAllKeys().forEach(function(t) {
                            return delete e.keystore[t]
                        })
                    }
                }
                var J = 1,
                    X = "__root__";

                function W(t, e) {
                    U(t, e);
                    var n = t.delete;
                    e.proxyId = "" + J++, e.ui = JSON.parse(JSON.stringify(e.ui || [])), k(t, e, ["proxyId", "proxyGroup", "proxyName"]), D(t, e, ["proxyManager"]);
                    var a = {},
                        o = {};

                    function i(t, e) {
                        o[e] || (o[e] = []);
                        for (var n = o[e], r = 0; r < t.length; r++) n.push(t[r].name), a[t[r].name] = t[r], t[r].children && t[r].children.length && i(t[r].children, t[r].name)
                    }

                    function c() {
                        for (var n = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : X, r = [], a = e.proxyId, i = function() {
                                var t = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : X;
                                return o[t]
                            }(n) || [], u = 0; u < i.length; u++) {
                            var f = i[u],
                                s = t["get" + A(f)],
                                l = {
                                    id: a,
                                    name: f,
                                    value: s ? s() : void 0
                                },
                                d = c(f);
                            d.length && (l.children = d), r.push(l)
                        }
                        return r
                    }
                    i(e.ui, X), t.updateUI = function(n) {
                        e.ui = JSON.parse(JSON.stringify(n || [])), Object.keys(a).forEach(function(t) {
                            return delete a[t]
                        }), Object.keys(o).forEach(function(t) {
                            return delete o[t]
                        }), i(e.ui, X), t.modified()
                    }, t.updateProxyProperty = function(t, e) {
                        var n = a[t];
                        n ? Object.assign(n, e) : a[t] = Object.assign({}, e)
                    }, t.activate = function() {
                        if (e.proxyManager) {
                            var n = "setActive" + A(t.getProxyGroup().slice(0, -1));
                            e.proxyManager[n] && e.proxyManager[n](t)
                        }
                    }, e.propertyLinkSubscribers = {}, t.registerPropertyLinkForGC = function(t, n) {
                        n in e.propertyLinkSubscribers || (e.propertyLinkSubscribers[n] = []), e.propertyLinkSubscribers[n].push(t)
                    }, t.gcPropertyLinks = function(n) {
                        for (var r = e.propertyLinkSubscribers[n] || []; r.length;) r.pop().unbind(t)
                    }, e.propertyLinkMap = {}, t.getPropertyLink = function(t) {
                        var n = arguments.length > 1 && void 0 !== arguments[1] && arguments[1];
                        if (e.propertyLinkMap[t]) return e.propertyLinkMap[t];
                        var r = null,
                            a = [],
                            o = 0,
                            i = !1;

                        function c(n) {
                            var c = arguments.length > 1 && void 0 !== arguments[1] && arguments[1];
                            if (i) return null;
                            var f = [],
                                s = null;
                            for (o = a.length; o--;) {
                                var l = a[o];
                                l.instance === n ? s = l : f.push(l)
                            }
                            var d = s.instance["get" + A(s.propertyName)]();
                            if (d !== r || c) {
                                for (r = d, i = !0; f.length;) {
                                    var v = f.pop();
                                    v.instance.set(u({}, v.propertyName, r))
                                }
                                i = !1
                            }
                            return e.propertyLinkMap[t].persistent && (e.propertyLinkMap[t].value = d), d
                        }

                        function f(t, e) {
                            var n = [];
                            for (o = a.length; o--;) {
                                var r = a[o];
                                r.instance !== t || r.propertyName !== e && void 0 !== e || (r.subscription.unsubscribe(), n.push(o))
                            }
                            for (; n.length;) a.splice(n.pop(), 1)
                        }
                        var s = {
                            bind: function(n, r) {
                                var o = arguments.length > 2 && void 0 !== arguments[2] && arguments[2],
                                    i = n.onModified(c),
                                    s = a[0];
                                return a.push({
                                    instance: n,
                                    propertyName: r,
                                    subscription: i
                                }), o && (e.propertyLinkMap[t].persistent && void 0 !== e.propertyLinkMap[t].value ? n.set(u({}, r, e.propertyLinkMap[t].value)) : s && c(s.instance, !0)), {
                                    unsubscribe: function() {
                                        return f(n, r)
                                    }
                                }
                            },
                            unbind: f,
                            unsubscribe: function() {
                                for (; a.length;) a.pop().subscription.unsubscribe()
                            },
                            persistent: n
                        };
                        return e.propertyLinkMap[t] = s, s
                    }, t.listPropertyNames = function() {
                        return c().map(function(t) {
                            return t.name
                        })
                    }, t.getPropertyByName = function(t) {
                        return c().find(function(e) {
                            return e.name === t
                        })
                    }, t.getPropertyDomainByName = function(t) {
                        return a[t].domain
                    }, t.getProxySection = function() {
                        return {
                            id: e.proxyId,
                            name: e.proxyGroup,
                            ui: e.ui,
                            properties: c()
                        }
                    }, t.delete = function() {
                        for (var r = Object.keys(e.propertyLinkMap), a = r.length; a--;) e.propertyLinkMap[r[a]].unsubscribe();
                        Object.keys(e.propertyLinkSubscribers).forEach(t.gcPropertyLinks), n()
                    }, r(function() {
                        if (e.links)
                            for (var n = 0; n < e.links.length; n++) {
                                var r = e.links[n],
                                    a = r.link,
                                    o = r.property,
                                    i = r.persistent,
                                    u = r.updateOnBind;
                                if ("application" === r.type) {
                                    var c = e.proxyManager.getPropertyLink(a, i);
                                    t.registerPropertyLinkForGC(c, "application"), c.bind(t, o, u)
                                }
                            }
                    })
                }

                function H(t, e, n) {
                    for (var r = t.delete, a = [], o = Object.keys(n), i = o.length; i--;) {
                        var u = o[i],
                            c = n[u],
                            f = c.modelKey,
                            s = c.property,
                            l = c.modified,
                            d = void 0 === l || l,
                            v = A(s),
                            p = A(u);
                        t["get" + p] = e[f]["get" + v], t["set" + p] = e[f]["set" + v], d && a.push(e[f].onModified(t.modified))
                    }
                    t.delete = function() {
                        for (; a.length;) a.pop().unsubscribe();
                        r()
                    }
                }

                function K(t, e) {
                    var n = arguments.length > 2 && void 0 !== arguments[2] ? arguments[2] : {},
                        r = arguments.length > 3 && void 0 !== arguments[3] ? arguments[3] : {};
                    e.this = t;
                    for (var a = Object.keys(r), o = a.length, i = function() {
                            var i = a[o];
                            e[i] = r[i];
                            var u = n[i];
                            t["set" + A(i)] = function(n) {
                                n !== e[i] && (e[i] = n, function(t) {
                                    for (var n = Object.keys(t), r = n.length; r--;) {
                                        var a = n[r];
                                        e[a].set(t[a])
                                    }
                                }(u[n]), t.modified())
                            }
                        }; o--;) i();
                    a.length && k(t, e, a)
                }
                var Z = 10,
                    Q = 40,
                    $ = 800;

                function tt(t) {
                    var e = 0,
                        n = 0,
                        r = 0,
                        a = 0;
                    return "detail" in t && (n = t.detail), "wheelDelta" in t && (n = -t.wheelDelta / 120), "wheelDeltaY" in t && (n = -t.wheelDeltaY / 120), "wheelDeltaX" in t && (e = -t.wheelDeltaX / 120), "axis" in t && t.axis === t.HORIZONTAL_AXIS && (e = n, n = 0), r = e * Z, a = n * Z, "deltaY" in t && (a = t.deltaY), "deltaX" in t && (r = t.deltaX), (r || a) && t.deltaMode && (1 === t.deltaMode ? (r *= Q, a *= Q) : (r *= $, a *= $)), r && !e && (e = r < 1 ? -1 : 1), a && !n && (n = a < 1 ? -1 : 1), {
                        spinX: e,
                        spinY: n,
                        pixelX: r,
                        pixelY: a
                    }
                }
                e.default = {
                    EVENT_ABORT: R,
                    VOID: s,
                    TYPED_ARRAYS: M,
                    algo: j,
                    capitalize: A,
                    uncapitalize: I,
                    chain: Y,
                    enumToString: function(t, e) {
                        return Object.keys(t).find(function(n) {
                            return t[n] === e
                        })
                    },
                    event: F,
                    get: k,
                    getArray: C,
                    getCurrentGlobalMTime: function() {
                        return f
                    },
                    getStateArrayMapFunc: x,
                    isVtkObject: q,
                    keystore: U,
                    newInstance: B,
                    obj: E,
                    safeArrays: w,
                    set: _,
                    setArray: P,
                    setGet: D,
                    setGetArray: L,
                    setLoggerFunction: p,
                    traverseInstanceTree: G,
                    vtkDebugMacro: y,
                    vtkErrorMacro: g,
                    vtkInfoMacro: h,
                    vtkLogMacro: m,
                    vtkWarningMacro: b,
                    debounce: z,
                    throttle: V,
                    proxy: W,
                    proxyPropertyMapping: H,
                    proxyPropertyState: K,
                    formatBytesToProperUnit: T,
                    formatNumbersWithThousandSeparator: O,
                    normalizeWheel: tt
                }
            }).call(this, n(23), n(120).setImmediate)
        },
        20: function(t, e) {
            t.exports = function() {
                throw new Error("define cannot be used indirect")
            }
        },
        200: function(t, e, n) {
            "use strict";
            Object.defineProperty(e, "__esModule", {
                value: !0
            }), e.newInstance = void 0, e.extend = c;
            var r = i(n(49)),
                a = i(n(2)),
                o = i(n(16));

            function i(t) {
                return t && t.__esModule ? t : {
                    default: t
                }
            }
            var u = {
                arrays: [],
                copyFieldFlags: [],
                doCopyAllOn: !0,
                doCopyAllOff: !1
            };

            function c(t, e) {
                var n = arguments.length > 2 && void 0 !== arguments[2] ? arguments[2] : {};
                Object.assign(e, u, n), a.default.obj(t, e),
                    function(t, e) {
                        e.classHierarchy.push("vtkFieldData");
                        var n = t.getState;
                        e.arrays && (e.arrays = e.arrays.map(function(t) {
                            return {
                                data: (0, r.default)(t.data)
                            }
                        })), t.initialize = function() {
                            t.initializeFields(), t.copyAllOn(), t.clearFieldFlags()
                        }, t.initializeFields = function() {
                            e.arrays = [], e.copyFieldFlags = {}, t.modified()
                        }, t.copyStructure = function(n) {
                            t.initializeFields(), e.copyFieldFlags = n.getCopyFieldFlags().map(function(t) {
                                return t
                            }), e.arrays = n.arrays().map(function(t) {
                                return {
                                    array: t
                                }
                            })
                        }, t.getNumberOfArrays = function() {
                            return e.arrays.length
                        }, t.getNumberOfActiveArrays = function() {
                            return e.arrays.length
                        }, t.addArray = function(t) {
                            return e.arrays = [].concat(e.arrays, {
                                data: t
                            }), e.arrays.length - 1
                        }, t.removeAllArrays = function() {
                            e.arrays = []
                        }, t.removeArray = function(t) {
                            e.arrays = e.arrays.filter(function(e) {
                                return t !== e.data.getName()
                            })
                        }, t.removeArrayByIndex = function(t) {
                            e.arrays = e.arrays.filter(function(e, n) {
                                return n !== t
                            })
                        }, t.getArrays = function() {
                            return e.arrays.map(function(t) {
                                return t.data
                            })
                        }, t.getArray = function(e) {
                            return "number" == typeof e ? t.getArrayByIndex(e) : t.getArrayByName(e)
                        }, t.getArrayByName = function(t) {
                            return e.arrays.reduce(function(e, n, r) {
                                return n.data.getName() === t ? n.data : e
                            }, null)
                        }, t.getArrayWithIndex = function(t) {
                            return e.arrays.reduce(function(e, n, r) {
                                return n.data && n.data.getName() === t ? {
                                    array: n.data,
                                    index: r
                                } : e
                            }, {
                                array: null,
                                index: -1
                            })
                        }, t.getArrayByIndex = function(t) {
                            return t >= 0 && t < e.arrays.length ? e.arrays[t].data : null
                        }, t.hasArray = function(e) {
                            return t.getArrayWithIndex(e).index >= 0
                        }, t.getArrayName = function(t) {
                            var n = e.arrays[t];
                            return n ? n.data.getName() : ""
                        }, t.getCopyFieldFlags = function() {
                            return e.copyFieldFlags
                        }, t.getFlag = function(t) {
                            return e.copyFieldFlags[t]
                        }, t.passData = function(n) {
                            var r = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : -1,
                                a = arguments.length > 2 && void 0 !== arguments[2] ? arguments[2] : -1;
                            n.getArrays().forEach(function(n) {
                                var i = t.getFlag(n.getName());
                                if (!1 !== i && (!e.doCopyAllOff || !0 === i) && n) {
                                    var u = t.getArrayByName(n.getName());
                                    if (u) {
                                        if (n.getNumberOfComponents() === u.getNumberOfComponents())
                                            if (r > -1 && r < n.getNumberOfTuples()) {
                                                var c = a > -1 ? a : r;
                                                u.setTuple(c, n.getTuple(r))
                                            } else
                                                for (var f = 0; f < n.getNumberOfTuples(); ++f) u.setTuple(f, n.getTuple(f))
                                    } else if (r < 0 || r > n.getNumberOfTuples()) t.addArray(n);
                                    else {
                                        var s = n.getNumberOfComponents(),
                                            l = n.getNumberOfValues(),
                                            d = a > -1 ? a : r;
                                        l < d * s && (l = (d + 1) * s), (u = o.default.newInstance({
                                            name: n.getName(),
                                            dataType: n.getDataType(),
                                            numberOfComponents: n.getNumberOfComponents(),
                                            size: l
                                        })).setTuple(d, n.getTuple(r)), t.addArray(u)
                                    }
                                }
                            })
                        }, t.copyFieldOn = function(t) {
                            e.copyFieldFlags[t] = !0
                        }, t.copyFieldOff = function(t) {
                            e.copyFieldFlags[t] = !1
                        }, t.copyAllOn = function() {
                            e.doCopyAllOn && !e.doCopyAllOff || (e.doCopyAllOn = !0, e.doCopyAllOff = !1, t.modified())
                        }, t.copyAllOff = function() {
                            !e.doCopyAllOn && e.doCopyAllOff || (e.doCopyAllOn = !1, e.doCopyAllOff = !0, t.modified())
                        }, t.clearFieldFlags = function() {
                            e.copyFieldFlags = {}
                        }, t.deepCopy = function(t) {
                            e.arrays = t.getArrays().map(function(t) {
                                var e = t.newClone();
                                return e.deepCopy(t), {
                                    data: e
                                }
                            })
                        }, t.copyFlags = function(t) {
                            return t.getCopyFieldFlags().map(function(t) {
                                return t
                            })
                        }, t.reset = function() {
                            return e.arrays.forEach(function(t) {
                                return t.data.reset()
                            })
                        }, t.getMTime = function() {
                            return e.arrays.reduce(function(t, e) {
                                return e.data.getMTime() > t ? e.data.getMTime() : t
                            }, e.mtime)
                        }, t.getNumberOfComponents = function() {
                            return e.arrays.reduce(function(t, e) {
                                return t + e.data.getNumberOfComponents()
                            }, 0)
                        }, t.getNumberOfTuples = function() {
                            return e.arrays.length > 0 ? e.arrays[0].getNumberOfTuples() : 0
                        }, t.getState = function() {
                            var t = n();
                            return t.arrays = e.arrays.map(function(t) {
                                return {
                                    data: t.data.getState()
                                }
                            }), t
                        }
                    }(t, e)
            }
            var f = e.newInstance = a.default.newInstance(c, "vtkFieldData");
            e.default = {
                newInstance: f,
                extend: c
            }
        },
        201: function(t, e, n) {
            "use strict";
            Object.defineProperty(e, "__esModule", {
                value: !0
            }), e.newInstance = void 0, e.extend = d;
            var r = u(n(2)),
                a = u(n(200)),
                o = u(n(199)),
                i = u(n(16));

            function u(t) {
                return t && t.__esModule ? t : {
                    default: t
                }
            }
            var c = o.default.AttributeTypes,
                f = o.default.AttributeCopyOperations,
                s = r.default.vtkWarningMacro;
            var l = {
                activeScalars: -1,
                activeVectors: -1,
                activeTensors: -1,
                activeNormals: -1,
                activeTCoords: -1,
                activeGlobalIds: -1,
                activePedigreeIds: -1
            };

            function d(t, e) {
                var n = arguments.length > 2 && void 0 !== arguments[2] ? arguments[2] : {};
                Object.assign(e, l, n), a.default.extend(t, e, n), r.default.setGet(t, e, ["activeScalars", "activeNormals", "activeTCoords", "activeVectors", "activeTensors", "activeGlobalIds", "activePedigreeIds"]), e.arrays || (e.arrays = {}),
                    function(t, e) {
                        var n = ["Scalars", "Vectors", "Normals", "TCoords", "Tensors", "GlobalIds", "PedigreeIds"];

                        function a(t) {
                            var e = n.find(function(e) {
                                return c[e.toUpperCase()] === t || "number" != typeof t && e.toLowerCase() === t.toLowerCase()
                            });
                            return void 0 === e && (e = null), e
                        }
                        e.classHierarchy.push("vtkDataSetAttributes"), t.checkNumberOfComponents = function(t) {
                            return !0
                        }, t.setAttribute = function(n, r) {
                            var o = a(r);
                            if (n && "PEDIGREEIDS" === o.toUpperCase() && !n.isA("vtkDataArray")) return s("Cannot set attribute " + o + ". The attribute must be a vtkDataArray."), -1;
                            if (n && !t.checkNumberOfComponents(n, o)) return s("Cannot set attribute " + o + ". Incorrect number of components."), -1;
                            var i = e["active" + o];
                            if (i >= 0 && i < e.arrays.length) {
                                if (e.arrays[i] === n) return i;
                                t.removeArrayByIndex(i)
                            }
                            return n ? (i = t.addArray(n), e["active" + o] = i) : e["active" + o] = -1, t.modified(), e["active" + o]
                        }, t.setActiveAttributeByName = function(e, n) {
                            return t.setActiveAttributeByIndex(t.getArrayWithIndex(e).index, n)
                        }, t.setActiveAttributeByIndex = function(n, r) {
                            var o = a(r);
                            if (n >= 0 && n < e.arrays.length) {
                                if ("PEDIGREEIDS" !== o.toUpperCase()) {
                                    var i = t.getArrayByIndex(n);
                                    if (!i.isA("vtkDataArray")) return s("Cannot set attribute " + o + ". Only vtkDataArray subclasses can be set as active attributes."), -1;
                                    if (!t.checkNumberOfComponents(i, o)) return s("Cannot set attribute " + o + ". Incorrect number of components."), -1
                                }
                                return e["active" + o] = n, t.modified(), n
                            }
                            return -1 === n && (e["active" + o] = n, t.modified()), -1
                        }, t.getActiveAttribute = function(e) {
                            var n = a(e);
                            return t["get" + n]()
                        }, t.removeAllArrays = function() {
                            e.arrays = [], n.forEach(function(t) {
                                e["active" + t] = -1
                            })
                        }, t.removeArray = function(t) {
                            e.arrays = e.arrays.filter(function(r, a) {
                                return t !== r.data.getName() || (n.forEach(function(t) {
                                    a === e["active" + t] && (e["active" + t] = -1)
                                }), !1)
                            })
                        }, t.removeArrayByIndex = function(t) {
                            e.arrays = e.arrays.filter(function(e, n) {
                                return n !== t
                            }), n.forEach(function(n) {
                                t === e["active" + n] && (e["active" + n] = -1)
                            })
                        }, n.forEach(function(n) {
                            var r = "active" + n;
                            t["get" + n] = function() {
                                return t.getArrayByIndex(e[r])
                            }, t["set" + n] = function(e) {
                                return t.setAttribute(e, n)
                            }, t["setActive" + n] = function(e) {
                                return t.setActiveAttributeByIndex(t.getArrayWithIndex(e).index, n)
                            }, t["copy" + n + "Off"] = function() {
                                t.initialize();
                                var r = n.toUpperCase();
                                e.copyAttributeFlags[f.PASSDATA][c[r]] = !1
                            }
                        }), t.initialize = r.default.chain(t.initialize, function() {
                            e.copyAttributeFlags = [], Object.keys(f).filter(function(t) {
                                return "ALLCOPY" !== t
                            }).forEach(function(t) {
                                e.copyAttributeFlags[f[t]] = Object.keys(c).filter(function(t) {
                                    return "NUM_ATTRIBUTES" !== t
                                }).reduce(function(t, e) {
                                    return t[c[e]] = !0, t
                                }, [])
                            }), e.copyAttributeFlags[f.COPYTUPLE][c.GLOBALIDS] = !1, e.copyAttributeFlags[f.INTERPOLATE][c.GLOBALIDS] = !1, e.copyAttributeFlags[f.COPYTUPLE][c.PEDIGREEIDS] = !1
                        }), e.dataArrays && Object.keys(e.dataArrays).length && Object.keys(e.dataArrays).forEach(function(n) {
                            e.dataArrays[n].ref || "vtkDataArray" !== e.dataArrays[n].type || t.addArray(i.default.newInstance(e.dataArrays[n]))
                        })
                    }(t, e)
            }
            var v = e.newInstance = r.default.newInstance(d, "vtkDataSetAttributes");
            e.default = Object.assign({
                newInstance: v,
                extend: d
            }, o.default)
        },
        202: function(t, e) {},
        203: function(t, e, n) {
            var r;
            ! function(a, o) {
                var i, u = this,
                    c = 256,
                    f = 6,
                    s = "random",
                    l = o.pow(c, f),
                    d = o.pow(2, 52),
                    v = 2 * d,
                    p = c - 1;

                function m(t, e, n) {
                    var r = [],
                        m = y(function t(e, n) {
                            var r, a = [],
                                o = typeof e;
                            if (n && "object" == o)
                                for (r in e) try {
                                    a.push(t(e[r], n - 1))
                                } catch (t) {}
                            return a.length ? a : "string" == o ? e : e + "\0"
                        }((e = 1 == e ? {
                            entropy: !0
                        } : e || {}).entropy ? [t, g(a)] : null == t ? function() {
                            try {
                                var t;
                                return i && (t = i.randomBytes) ? t = t(c) : (t = new Uint8Array(c), (u.crypto || u.msCrypto).getRandomValues(t)), g(t)
                            } catch (t) {
                                var e = u.navigator,
                                    n = e && e.plugins;
                                return [+new Date, u, n, u.screen, g(a)]
                            }
                        }() : t, 3), r),
                        b = new function(t) {
                            var e, n = t.length,
                                r = this,
                                a = 0,
                                o = r.i = r.j = 0,
                                i = r.S = [];
                            n || (t = [n++]);
                            for (; a < c;) i[a] = a++;
                            for (a = 0; a < c; a++) i[a] = i[o = p & o + t[a % n] + (e = i[a])], i[o] = e;
                            (r.g = function(t) {
                                for (var e, n = 0, a = r.i, o = r.j, i = r.S; t--;) e = i[a = p & a + 1], n = n * c + i[p & (i[a] = i[o = p & o + e]) + (i[o] = e)];
                                return r.i = a, r.j = o, n
                            })(c)
                        }(r),
                        M = function() {
                            for (var t = b.g(f), e = l, n = 0; t < d;) t = (t + n) * c, e *= c, n = b.g(1);
                            for (; t >= v;) t /= 2, e /= 2, n >>>= 1;
                            return (t + n) / e
                        };
                    return M.int32 = function() {
                        return 0 | b.g(4)
                    }, M.quick = function() {
                        return b.g(4) / 4294967296
                    }, M.double = M, y(g(b.S), a), (e.pass || n || function(t, e, n, r) {
                        return r && (r.S && h(r, b), t.state = function() {
                            return h(b, {})
                        }), n ? (o[s] = t, e) : t
                    })(M, m, "global" in e ? e.global : this == o, e.state)
                }

                function h(t, e) {
                    return e.i = t.i, e.j = t.j, e.S = t.S.slice(), e
                }

                function y(t, e) {
                    for (var n, r = t + "", a = 0; a < r.length;) e[p & a] = p & (n ^= 19 * e[p & a]) + r.charCodeAt(a++);
                    return g(e)
                }

                function g(t) {
                    return String.fromCharCode.apply(0, t)
                }
                if (o["seed" + s] = m, y(o.random(), a), "object" == typeof t && t.exports) {
                    t.exports = m;
                    try {
                        i = n(202)
                    } catch (t) {}
                } else void 0 === (r = function() {
                    return m
                }.call(e, n, e, t)) || (t.exports = r)
            }([], Math)
        },
        204: function(t, e, n) {
            (function(t) {
                var r;
                ! function(t, a, o) {
                    function i(t, e) {
                        return e.a = t.a, e.b = t.b, e.c = t.c, e.d = t.d, e
                    }

                    function u(t, e) {
                        var n = new function(t) {
                                var e = this,
                                    n = "";
                                e.next = function() {
                                    var t = e.b,
                                        n = e.c,
                                        r = e.d,
                                        a = e.a;
                                    return t = t << 25 ^ t >>> 7 ^ n, n = n - r | 0, r = r << 24 ^ r >>> 8 ^ a, a = a - t | 0, e.b = t = t << 20 ^ t >>> 12 ^ n, e.c = n = n - r | 0, e.d = r << 16 ^ n >>> 16 ^ a, e.a = a - t | 0
                                }, e.a = 0, e.b = 0, e.c = -1640531527, e.d = 1367130551, t === Math.floor(t) ? (e.a = t / 4294967296 | 0, e.b = 0 | t) : n += t;
                                for (var r = 0; r < n.length + 20; r++) e.b ^= 0 | n.charCodeAt(r), e.next()
                            }(t),
                            r = e && e.state,
                            a = function() {
                                return (n.next() >>> 0) / 4294967296
                            };
                        return a.double = function() {
                            do {
                                var t = ((n.next() >>> 11) + (n.next() >>> 0) / 4294967296) / (1 << 21)
                            } while (0 === t);
                            return t
                        }, a.int32 = n.next, a.quick = a, r && ("object" == typeof r && i(r, n), a.state = function() {
                            return i(n, {})
                        }), a
                    }
                    a && a.exports ? a.exports = u : n(20) && n(35) ? void 0 === (r = function() {
                        return u
                    }.call(e, n, e, a)) || (a.exports = r) : this.tychei = u
                }(0, "object" == typeof t && t, n(20))
            }).call(this, n(30)(t))
        },
        205: function(t, e, n) {
            (function(t) {
                var r;
                ! function(t, a, o) {
                    function i(t, e) {
                        return e.i = t.i, e.w = t.w, e.X = t.X.slice(), e
                    }

                    function u(t, e) {
                        null == t && (t = +new Date);
                        var n = new function(t) {
                                var e = this;
                                e.next = function() {
                                        var t, n, r = e.w,
                                            a = e.X,
                                            o = e.i;
                                        return e.w = r = r + 1640531527 | 0, n = a[o + 34 & 127], t = a[o = o + 1 & 127], n ^= n << 13, t ^= t << 17, n ^= n >>> 15, t ^= t >>> 12, n = a[o] = n ^ t, e.i = o, n + (r ^ r >>> 16) | 0
                                    },
                                    function(t, e) {
                                        var n, r, a, o, i, u = [],
                                            c = 128;
                                        for (e === (0 | e) ? (r = e, e = null) : (e += "\0", r = 0, c = Math.max(c, e.length)), a = 0, o = -32; o < c; ++o) e && (r ^= e.charCodeAt((o + 32) % e.length)), 0 === o && (i = r), r ^= r << 10, r ^= r >>> 15, r ^= r << 4, r ^= r >>> 13, o >= 0 && (i = i + 1640531527 | 0, a = 0 == (n = u[127 & o] ^= r + i) ? a + 1 : 0);
                                        for (a >= 128 && (u[127 & (e && e.length || 0)] = -1), a = 127, o = 512; o > 0; --o) r = u[a + 34 & 127], n = u[a = a + 1 & 127], r ^= r << 13, n ^= n << 17, r ^= r >>> 15, n ^= n >>> 12, u[a] = r ^ n;
                                        t.w = i, t.X = u, t.i = a
                                    }(e, t)
                            }(t),
                            r = e && e.state,
                            a = function() {
                                return (n.next() >>> 0) / 4294967296
                            };
                        return a.double = function() {
                            do {
                                var t = ((n.next() >>> 11) + (n.next() >>> 0) / 4294967296) / (1 << 21)
                            } while (0 === t);
                            return t
                        }, a.int32 = n.next, a.quick = a, r && (r.X && i(r, n), a.state = function() {
                            return i(n, {})
                        }), a
                    }
                    a && a.exports ? a.exports = u : n(20) && n(35) ? void 0 === (r = function() {
                        return u
                    }.call(e, n, e, a)) || (a.exports = r) : this.xor4096 = u
                }(0, "object" == typeof t && t, n(20))
            }).call(this, n(30)(t))
        },
        206: function(t, e, n) {
            (function(t) {
                var r;
                ! function(t, a, o) {
                    function i(t, e) {
                        return e.x = t.x.slice(), e.i = t.i, e
                    }

                    function u(t, e) {
                        null == t && (t = +new Date);
                        var n = new function(t) {
                                var e = this;
                                e.next = function() {
                                        var t, n, r = e.x,
                                            a = e.i;
                                        return t = r[a], n = (t ^= t >>> 7) ^ t << 24, n ^= (t = r[a + 1 & 7]) ^ t >>> 10, n ^= (t = r[a + 3 & 7]) ^ t >>> 3, n ^= (t = r[a + 4 & 7]) ^ t << 7, t = r[a + 7 & 7], n ^= (t ^= t << 13) ^ t << 9, r[a] = n, e.i = a + 1 & 7, n
                                    },
                                    function(t, e) {
                                        var n, r = [];
                                        if (e === (0 | e)) r[0] = e;
                                        else
                                            for (e = "" + e, n = 0; n < e.length; ++n) r[7 & n] = r[7 & n] << 15 ^ e.charCodeAt(n) + r[n + 1 & 7] << 13;
                                        for (; r.length < 8;) r.push(0);
                                        for (n = 0; n < 8 && 0 === r[n]; ++n);
                                        for (8 == n ? r[7] = -1 : r[n], t.x = r, t.i = 0, n = 256; n > 0; --n) t.next()
                                    }(e, t)
                            }(t),
                            r = e && e.state,
                            a = function() {
                                return (n.next() >>> 0) / 4294967296
                            };
                        return a.double = function() {
                            do {
                                var t = ((n.next() >>> 11) + (n.next() >>> 0) / 4294967296) / (1 << 21)
                            } while (0 === t);
                            return t
                        }, a.int32 = n.next, a.quick = a, r && (r.x && i(r, n), a.state = function() {
                            return i(n, {})
                        }), a
                    }
                    a && a.exports ? a.exports = u : n(20) && n(35) ? void 0 === (r = function() {
                        return u
                    }.call(e, n, e, a)) || (a.exports = r) : this.xorshift7 = u
                }(0, "object" == typeof t && t, n(20))
            }).call(this, n(30)(t))
        },
        207: function(t, e, n) {
            (function(t) {
                var r;
                ! function(t, a, o) {
                    function i(t, e) {
                        return e.x = t.x, e.y = t.y, e.z = t.z, e.w = t.w, e.v = t.v, e.d = t.d, e
                    }

                    function u(t, e) {
                        var n = new function(t) {
                                var e = this,
                                    n = "";
                                e.next = function() {
                                    var t = e.x ^ e.x >>> 2;
                                    return e.x = e.y, e.y = e.z, e.z = e.w, e.w = e.v, (e.d = e.d + 362437 | 0) + (e.v = e.v ^ e.v << 4 ^ t ^ t << 1) | 0
                                }, e.x = 0, e.y = 0, e.z = 0, e.w = 0, e.v = 0, t === (0 | t) ? e.x = t : n += t;
                                for (var r = 0; r < n.length + 64; r++) e.x ^= 0 | n.charCodeAt(r), r == n.length && (e.d = e.x << 10 ^ e.x >>> 4), e.next()
                            }(t),
                            r = e && e.state,
                            a = function() {
                                return (n.next() >>> 0) / 4294967296
                            };
                        return a.double = function() {
                            do {
                                var t = ((n.next() >>> 11) + (n.next() >>> 0) / 4294967296) / (1 << 21)
                            } while (0 === t);
                            return t
                        }, a.int32 = n.next, a.quick = a, r && ("object" == typeof r && i(r, n), a.state = function() {
                            return i(n, {})
                        }), a
                    }
                    a && a.exports ? a.exports = u : n(20) && n(35) ? void 0 === (r = function() {
                        return u
                    }.call(e, n, e, a)) || (a.exports = r) : this.xorwow = u
                }(0, "object" == typeof t && t, n(20))
            }).call(this, n(30)(t))
        },
        208: function(t, e, n) {
            (function(t) {
                var r;
                ! function(t, a, o) {
                    function i(t, e) {
                        return e.x = t.x, e.y = t.y, e.z = t.z, e.w = t.w, e
                    }

                    function u(t, e) {
                        var n = new function(t) {
                                var e = this,
                                    n = "";
                                e.x = 0, e.y = 0, e.z = 0, e.w = 0, e.next = function() {
                                    var t = e.x ^ e.x << 11;
                                    return e.x = e.y, e.y = e.z, e.z = e.w, e.w ^= e.w >>> 19 ^ t ^ t >>> 8
                                }, t === (0 | t) ? e.x = t : n += t;
                                for (var r = 0; r < n.length + 64; r++) e.x ^= 0 | n.charCodeAt(r), e.next()
                            }(t),
                            r = e && e.state,
                            a = function() {
                                return (n.next() >>> 0) / 4294967296
                            };
                        return a.double = function() {
                            do {
                                var t = ((n.next() >>> 11) + (n.next() >>> 0) / 4294967296) / (1 << 21)
                            } while (0 === t);
                            return t
                        }, a.int32 = n.next, a.quick = a, r && ("object" == typeof r && i(r, n), a.state = function() {
                            return i(n, {})
                        }), a
                    }
                    a && a.exports ? a.exports = u : n(20) && n(35) ? void 0 === (r = function() {
                        return u
                    }.call(e, n, e, a)) || (a.exports = r) : this.xor128 = u
                }(0, "object" == typeof t && t, n(20))
            }).call(this, n(30)(t))
        },
        209: function(t, e, n) {
            (function(t) {
                var r;
                ! function(t, a, o) {
                    function i(t, e) {
                        return e.c = t.c, e.s0 = t.s0, e.s1 = t.s1, e.s2 = t.s2, e
                    }

                    function u(t, e) {
                        var n = new function(t) {
                                var e, n = this,
                                    r = (e = 4022871197, function(t) {
                                        t = t.toString();
                                        for (var n = 0; n < t.length; n++) {
                                            var r = .02519603282416938 * (e += t.charCodeAt(n));
                                            r -= e = r >>> 0, e = (r *= e) >>> 0, e += 4294967296 * (r -= e)
                                        }
                                        return 2.3283064365386963e-10 * (e >>> 0)
                                    });
                                n.next = function() {
                                    var t = 2091639 * n.s0 + 2.3283064365386963e-10 * n.c;
                                    return n.s0 = n.s1, n.s1 = n.s2, n.s2 = t - (n.c = 0 | t)
                                }, n.c = 1, n.s0 = r(" "), n.s1 = r(" "), n.s2 = r(" "), n.s0 -= r(t), n.s0 < 0 && (n.s0 += 1), n.s1 -= r(t), n.s1 < 0 && (n.s1 += 1), n.s2 -= r(t), n.s2 < 0 && (n.s2 += 1), r = null
                            }(t),
                            r = e && e.state,
                            a = n.next;
                        return a.int32 = function() {
                            return 4294967296 * n.next() | 0
                        }, a.double = function() {
                            return a() + 1.1102230246251565e-16 * (2097152 * a() | 0)
                        }, a.quick = a, r && ("object" == typeof r && i(r, n), a.state = function() {
                            return i(n, {})
                        }), a
                    }
                    a && a.exports ? a.exports = u : n(20) && n(35) ? void 0 === (r = function() {
                        return u
                    }.call(e, n, e, a)) || (a.exports = r) : this.alea = u
                }(0, "object" == typeof t && t, n(20))
            }).call(this, n(30)(t))
        },
        210: function(t, e, n) {
            var r = n(209),
                a = n(208),
                o = n(207),
                i = n(206),
                u = n(205),
                c = n(204),
                f = n(203);
            f.alea = r, f.xor128 = a, f.xorwow = o, f.xorshift7 = i, f.xor4096 = u, f.tychei = c, t.exports = f
        },
        222: function(t, e, n) {
            (function(t, e) {
                ! function(t, n) {
                    "use strict";
                    if (!t.setImmediate) {
                        var r, a, o, i, u, c = 1,
                            f = {},
                            s = !1,
                            l = t.document,
                            d = Object.getPrototypeOf && Object.getPrototypeOf(t);
                        d = d && d.setTimeout ? d : t, "[object process]" === {}.toString.call(t.process) ? r = function(t) {
                            e.nextTick(function() {
                                p(t)
                            })
                        } : ! function() {
                            if (t.postMessage && !t.importScripts) {
                                var e = !0,
                                    n = t.onmessage;
                                return t.onmessage = function() {
                                    e = !1
                                }, t.postMessage("", "*"), t.onmessage = n, e
                            }
                        }() ? t.MessageChannel ? ((o = new MessageChannel).port1.onmessage = function(t) {
                            p(t.data)
                        }, r = function(t) {
                            o.port2.postMessage(t)
                        }) : l && "onreadystatechange" in l.createElement("script") ? (a = l.documentElement, r = function(t) {
                            var e = l.createElement("script");
                            e.onreadystatechange = function() {
                                p(t), e.onreadystatechange = null, a.removeChild(e), e = null
                            }, a.appendChild(e)
                        }) : r = function(t) {
                            setTimeout(p, 0, t)
                        } : (i = "setImmediate$" + Math.random() + "$", u = function(e) {
                            e.source === t && "string" == typeof e.data && 0 === e.data.indexOf(i) && p(+e.data.slice(i.length))
                        }, t.addEventListener ? t.addEventListener("message", u, !1) : t.attachEvent("onmessage", u), r = function(e) {
                            t.postMessage(i + e, "*")
                        }), d.setImmediate = function(t) {
                            "function" != typeof t && (t = new Function("" + t));
                            for (var e = new Array(arguments.length - 1), n = 0; n < e.length; n++) e[n] = arguments[n + 1];
                            var a = {
                                callback: t,
                                args: e
                            };
                            return f[c] = a, r(c), c++
                        }, d.clearImmediate = v
                    }

                    function v(t) {
                        delete f[t]
                    }

                    function p(t) {
                        if (s) setTimeout(p, 0, t);
                        else {
                            var e = f[t];
                            if (e) {
                                s = !0;
                                try {
                                    ! function(t) {
                                        var e = t.callback,
                                            r = t.args;
                                        switch (r.length) {
                                            case 0:
                                                e();
                                                break;
                                            case 1:
                                                e(r[0]);
                                                break;
                                            case 2:
                                                e(r[0], r[1]);
                                                break;
                                            case 3:
                                                e(r[0], r[1], r[2]);
                                                break;
                                            default:
                                                e.apply(n, r)
                                        }
                                    }(e)
                                } finally {
                                    v(t), s = !1
                                }
                            }
                        }
                    }
                }("undefined" == typeof self ? void 0 === t ? this : t : self)
            }).call(this, n(23), n(99))
        },
        23: function(t, e) {
            var n;
            n = function() {
                return this
            }();
            try {
                n = n || Function("return this")() || (0, eval)("this")
            } catch (t) {
                "object" == typeof window && (n = window)
            }
            t.exports = n
        },
        28: function(t, e) {
            var n = {
                EPSILON: 1e-6
            };
            n.ARRAY_TYPE = "undefined" != typeof Float32Array ? Float32Array : Array, n.RANDOM = Math.random, n.setMatrixArrayType = function(t) {
                GLMAT_ARRAY_TYPE = t
            };
            var r = Math.PI / 180;
            n.toRadian = function(t) {
                return t * r
            }, t.exports = n
        },
        30: function(t, e) {
            t.exports = function(t) {
                return t.webpackPolyfill || (t.deprecate = function() {}, t.paths = [], t.children || (t.children = []), Object.defineProperty(t, "loaded", {
                    enumerable: !0,
                    get: function() {
                        return t.l
                    }
                }), Object.defineProperty(t, "id", {
                    enumerable: !0,
                    get: function() {
                        return t.i
                    }
                }), t.webpackPolyfill = 1), t
            }
        },
        35: function(t, e) {
            (function(e) {
                t.exports = e
            }).call(this, {})
        },
        389: function(t, e, n) {
            "use strict";
            n.r(e);
            var r = n(124),
                a = n.n(r),
                o = {
                    itkModulesPath: "itk"
                };
            e.default = function(t, e, n, r) {
                var i = t;
                return i || (i = new window.Worker(o.itkModulesPath + "/WebWorkers/ImageIO.worker.js")), new a.a(i).postMessage({
                    operation: "readImage",
                    name: n,
                    type: r,
                    data: e,
                    config: o
                }, [e]).then(function(t) {
                    return Promise.resolve({
                        image: t,
                        webWorker: i
                    })
                })
            }
        },
        393: function(t, e) {
            var n = {
                bmp: "itkBMPImageIOJSBinding",
                BMP: "itkBMPImageIOJSBinding",
                dcm: "itkDCMTKImageIOJSBinding",
                DCM: "itkDCMTKImageIOJSBinding",
                gipl: "itkGiplImageIOJSBinding",
                "gipl.gz": "itkGiplImageIOJSBinding",
                hdf5: "itkHDF5ImageIOJSBinding",
                jpg: "itkJPEGImageIOJSBinding",
                JPG: "itkJPEGImageIOJSBinding",
                jpeg: "itkJPEGImageIOJSBinding",
                JPEG: "itkJPEGImageIOJSBinding",
                json: "itkJSONImageIOJSBinding",
                lsm: "itkLSMImageIOJSBinding",
                mnc: "itkMINCImageIOJSBinding",
                MNC: "itkMINCImageIOJSBinding",
                "mnc.gz": "itkMINCImageIOJSBinding",
                "MNC.GZ": "itkMINCImageIOJSBinding",
                mnc2: "itkMINCImageIOJSBinding",
                MNC2: "itkMINCImageIOJSBinding",
                mgh: "itkMGHImageIOJSBinding",
                mgz: "itkMGHImageIOJSBinding",
                "mgh.gz": "itkMGHImageIOJSBinding",
                mha: "itkMetaImageIOJSBinding",
                mhd: "itkMetaImageIOJSBinding",
                mrc: "itkMRCImageIOJSBinding",
                nia: "itkNiftiImageIOJSBinding",
                nii: "itkNiftiImageIOJSBinding",
                "nii.gz": "itkNiftiImageIOJSBinding",
                hdr: "itkNiftiImageIOJSBinding",
                nrrd: "itkNrrdImageIOJSBinding",
                NRRD: "itkNrrdImageIOJSBinding",
                nhdr: "itkNrrdImageIOJSBinding",
                NHDR: "itkNrrdImageIOJSBinding",
                png: "itkPNGImageIOJSBinding",
                PNG: "itkPNGImageIOJSBinding",
                pic: "itkBioRadImageIOJSBinding",
                PIC: "itkBioRadImageIOJSBinding",
                tif: "itkTIFFImageIOJSBinding",
                TIF: "itkTIFFImageIOJSBinding",
                tiff: "itkTIFFImageIOJSBinding",
                TIFF: "itkTIFFImageIOJSBinding",
                vtk: "itkVTKImageIOJSBinding",
                VTK: "itkVTKImageIOJSBinding"
            };
            t.exports = n
        },
        394: function(t, e, n) {
            "use strict";
            Object.defineProperty(e, "__esModule", {
                value: !0
            });
            var r = i(n(2)),
                a = i(n(57)),
                o = i(n(16));

            function i(t) {
                return t && t.__esModule ? t : {
                    default: t
                }
            }
            var u = r.default.vtkErrorMacro;
            e.default = {
                convertItkToVtkImage: function(t) {
                    var e = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : {};
                    switch (t.imageType.pixelType) {
                        case 1:
                        case 2:
                        case 3:
                            break;
                        default:
                            return u("Cannot handle ITK.js pixel type " + t.imageType.pixelType), null
                    }
                    for (var n = {
                            origin: [0, 0, 0],
                            spacing: [1, 1, 1]
                        }, r = [1, 1, 1], i = [1, 0, 0, 0, 1, 0, 0, 0, 1], c = 0; c < t.imageType.dimension; ++c) {
                        n.origin[c] = t.origin[c], n.spacing[c] = t.spacing[c], r[c] = t.size[c];
                        for (var f = 0; f < t.imageType.dimension; ++f) i[f + 3 * c] = t.direction.data[c + f * t.imageType.dimension]
                    }
                    var s = a.default.newInstance(n),
                        l = o.default.newInstance({
                            name: e.scalarArrayName || "Scalars",
                            values: t.data,
                            numberOfComponents: t.imageType.components
                        });
                    return s.setDirection(i), s.setDimensions.apply(s, r), s.getPointData().setScalars(l), s
                }
            }
        },
        395: function(t, e, n) {
            "use strict";
            Object.defineProperty(e, "__esModule", {
                value: !0
            }), e.newInstance = void 0, e.extend = f;
            var r = a(n(2));

            function a(t) {
                return t && t.__esModule ? t : {
                    default: t
                }
            }
            var o = a(n(394)).default.convertItkToVtkImage,
                i = null,
                u = function(t) {
                    return t
                };
            var c = {
                fileName: ""
            };

            function f(t, e) {
                var n = arguments.length > 2 && void 0 !== arguments[2] ? arguments[2] : {};
                Object.assign(e, c, n), r.default.obj(t, e), r.default.algo(t, e, 0, 1), r.default.setGet(t, e, ["fileName"]),
                    function(t, e) {
                        e.classHierarchy.push("vtkITKImageReader"), t.parseAsArrayBuffer = function(n) {
                            return n && n !== e.rawDataBuffer ? (e.rawDataBuffer = n, i(n, e.fileName).then(u).then(function(n) {
                                var r = o(n);
                                e.output[0] = r, t.modified()
                            })) : Promise.resolve()
                        }, t.requestData = function(n, r) {
                            t.parseAsArrayBuffer(e.rawDataBuffer, e.fileName)
                        }
                    }(t, e), i || console.error("\n      // Dependency needs to be added inside your project\n      import readImageArrayBuffer from 'itk/readImageArrayBuffer';\n      vtkITKImageReader.setReadImageArrayBufferFromITK(readImageArrayBuffer);\n      ")
            }
            var s = e.newInstance = r.default.newInstance(f, "vtkITKImageReader");
            e.default = {
                newInstance: s,
                extend: f,
                setReadImageArrayBufferFromITK: function(t) {
                    4 === (i = t).length && (i = function() {
                        for (var e = arguments.length, n = Array(e), r = 0; r < e; r++) n[r] = arguments[r];
                        return t.apply(void 0, [null].concat(n))
                    }, u = function(t) {
                        var e = t.webWorker,
                            n = t.image;
                        return e.terminate(), n
                    })
                }
            }
        },
        396: function(t, e, n) {
            "use strict";
            Object.defineProperty(e, "__esModule", {
                value: !0
            }), e.extensions = void 0, e.registerToGlance = c;
            var r = i(n(395)),
                a = i(n(393)),
                o = i(n(389));

            function i(t) {
                return t && t.__esModule ? t : {
                    default: t
                }
            }
            r.default.setReadImageArrayBufferFromITK(o.default);
            var u = e.extensions = Array.from(new Set(Object.keys(a.default).map(function(t) {
                return t.toLowerCase()
            })));

            function c(t) {
                t && u.forEach(function(e) {
                    return t.registerReader({
                        extension: e,
                        name: e.toUpperCase() + " Reader",
                        vtkReader: r.default,
                        binary: !0,
                        fileNameMethod: "setFileName"
                    })
                })
            }
            e.default = {
                extensions: u,
                registerToGlance: c
            }, c(("undefined" == typeof window ? {} : window).Glance)
        },
        397: function(t, e, n) {
            (function(e) {
                e.Glance || (e.Glance = {}), e.Glance.externals || (e.Glance.externals = {}), t.exports = e.Glance.externals.ITKReader = n(396)
            }).call(this, n(23))
        },
        40: function(t, e, n) {
            "use strict";
            Object.defineProperty(e, "__esModule", {
                value: !0
            });
            var r = e.DataTypeByteSize = {
                    Int8Array: 1,
                    Uint8Array: 1,
                    Uint8ClampedArray: 1,
                    Int16Array: 2,
                    Uint16Array: 2,
                    Int32Array: 4,
                    Uint32Array: 4,
                    Float32Array: 4,
                    Float64Array: 8
                },
                a = e.VtkDataTypes = {
                    VOID: "",
                    CHAR: "Int8Array",
                    SIGNED_CHAR: "Int8Array",
                    UNSIGNED_CHAR: "Uint8Array",
                    SHORT: "Int16Array",
                    UNSIGNED_SHORT: "Uint16Array",
                    INT: "Int32Array",
                    UNSIGNED_INT: "Uint32Array",
                    FLOAT: "Float32Array",
                    DOUBLE: "Float64Array"
                },
                o = e.DefaultDataType = a.FLOAT;
            e.default = {
                DefaultDataType: o,
                DataTypeByteSize: r,
                VtkDataTypes: a
            }
        },
        49: function(t, e, n) {
            "use strict";
            (function(t) {
                Object.defineProperty(e, "__esModule", {
                    value: !0
                });
                var n = "function" == typeof Symbol && "symbol" == typeof Symbol.iterator ? function(t) {
                    return typeof t
                } : function(t) {
                    return t && "function" == typeof Symbol && t.constructor === Symbol && t !== Symbol.prototype ? "symbol" : typeof t
                };
                e.default = a;
                var r = {
                    vtkObject: function() {
                        return null
                    }
                };

                function a(e) {
                    if (null === e || void 0 === e) return e;
                    if (e.isA) return e;
                    if (!e.vtkClass) return t.console && t.console.error && t.console.error("Invalid VTK object"), null;
                    var o = r[e.vtkClass];
                    if (!o) return t.console && t.console.error && t.console.error("No vtk class found for Object of type " + e.vtkClass), null;
                    var i = Object.assign({}, e);
                    Object.keys(i).forEach(function(t) {
                        i[t] && "object" === n(i[t]) && i[t].vtkClass && (i[t] = a(i[t]))
                    });
                    var u = o(i);
                    return u && u.modified && u.modified(), u
                }
                a.register = function(t, e) {
                    r[t] = e
                }
            }).call(this, n(23))
        },
        57: function(t, e, n) {
            "use strict";
            Object.defineProperty(e, "__esModule", {
                value: !0
            }), e.newInstance = void 0, e.extend = l;
            var r = c(n(2)),
                a = c(n(116)),
                o = c(n(197)),
                i = n(115),
                u = n(19);

            function c(t) {
                return t && t.__esModule ? t : {
                    default: t
                }
            }
            var f = r.default.vtkErrorMacro;
            var s = {
                direction: null,
                indexToWorld: null,
                worldToIndex: null,
                spacing: [1, 1, 1],
                origin: [0, 0, 0],
                extent: [0, -1, 0, -1, 0, -1],
                dataDescription: i.StructuredType.EMPTY
            };

            function l(t, e) {
                var n = arguments.length > 2 && void 0 !== arguments[2] ? arguments[2] : {};
                if (Object.assign(e, s, n), a.default.extend(t, e, n), e.direction) {
                    if (Array.isArray(e.direction)) {
                        var c = e.direction.slice(0);
                        e.direction = u.mat3.create();
                        for (var l = 0; l < 9; ++l) e.direction[l] = c[l]
                    }
                } else e.direction = u.mat3.create();
                e.indexToWorld = u.mat4.create(), e.worldToIndex = u.mat4.create(), r.default.get(t, e, ["direction", "indexToWorld", "worldToIndex"]), r.default.setGetArray(t, e, ["origin", "spacing"], 3), r.default.getArray(t, e, ["extent"], 6),
                    function(t, e) {
                        e.classHierarchy.push("vtkImageData"), t.setExtent = function() {
                            for (var n = arguments.length, r = Array(n), a = 0; a < n; a++) r[a] = arguments[a];
                            if (e.deleted) f("instance deleted - cannot call any method");
                            else if (r && 6 === r.length) {
                                var i = !1;
                                e.extent.forEach(function(t, e) {
                                    if (t !== r[e]) {
                                        if (i) return;
                                        i = !0
                                    }
                                }), i && (e.extent = [].concat(r), e.dataDescription = o.default.getDataDescriptionFromExtent(e.extent), t.modified())
                            }
                        }, t.setDimensions = function() {
                            var n = void 0,
                                r = void 0,
                                a = void 0;
                            if (e.deleted) f("instance deleted - cannot call any method");
                            else {
                                if (1 === arguments.length) {
                                    var o = arguments.length <= 0 ? void 0 : arguments[0];
                                    n = o[0], r = o[1], a = o[2]
                                } else {
                                    if (3 !== arguments.length) return void f("Bad dimension specification");
                                    n = arguments.length <= 0 ? void 0 : arguments[0], r = arguments.length <= 1 ? void 0 : arguments[1], a = arguments.length <= 2 ? void 0 : arguments[2]
                                }
                                t.setExtent(0, n - 1, 0, r - 1, 0, a - 1)
                            }
                        }, t.getDimensions = function() {
                            return [e.extent[1] - e.extent[0] + 1, e.extent[3] - e.extent[2] + 1, e.extent[5] - e.extent[4] + 1]
                        }, t.getNumberOfCells = function() {
                            for (var e = t.getDimensions(), n = 1, r = 0; r < 3; r++) {
                                if (0 === e[r]) return 0;
                                e[r] > 1 && (n *= e[r] - 1)
                            }
                            return n
                        }, t.getNumberOfPoints = function() {
                            var e = t.getDimensions();
                            return e[0] * e[1] * e[2]
                        }, t.getPoint = function(n) {
                            var r = t.getDimensions(),
                                a = u.vec3.fromValues(0, 0, 0),
                                o = [0, 0, 0];
                            if (0 === r[0] || 0 === r[1] || 0 === r[2]) return f("Requesting a point from an empty image."), null;
                            switch (e.dataDescription) {
                                case i.StructuredType.EMPTY:
                                    return null;
                                case i.StructuredType.SINGLE_POINT:
                                    break;
                                case i.StructuredType.X_LINE:
                                    a[0] = n;
                                    break;
                                case i.StructuredType.Y_LINE:
                                    a[1] = n;
                                    break;
                                case i.StructuredType.Z_LINE:
                                    a[2] = n;
                                    break;
                                case i.StructuredType.XY_PLANE:
                                    a[0] = n % r[0], a[1] = n / r[0];
                                    break;
                                case i.StructuredType.YZ_PLANE:
                                    a[1] = n % r[1], a[2] = n / r[1];
                                    break;
                                case i.StructuredType.XZ_PLANE:
                                    a[0] = n % r[0], a[2] = n / r[0];
                                    break;
                                case i.StructuredType.XYZ_GRID:
                                    a[0] = n % r[0], a[1] = n / r[0] % r[1], a[2] = n / (r[0] * r[1]);
                                    break;
                                default:
                                    f("Invalid dataDescription")
                            }
                            var c = u.vec3.create();
                            return t.indexToWorldVec3(a, c), u.vec3.copy(o, c), o
                        }, t.getBounds = function() {
                            return t.extentToBounds(e.extent)
                        }, t.extentToBounds = function(e) {
                            var n = [e[0], e[2], e[4], e[1], e[2], e[4], e[0], e[3], e[4], e[1], e[3], e[4], e[0], e[2], e[5], e[1], e[2], e[5], e[0], e[3], e[5], e[1], e[3], e[5]],
                                r = u.vec3.fromValues(n[0], n[1], n[2]),
                                a = u.vec3.create();
                            t.indexToWorldVec3(r, a);
                            for (var o = [a[0], a[0], a[1], a[1], a[2], a[2]], i = 3; i < 24; i += 3) u.vec3.set(r, n[i], n[i + 1], n[i + 2]), t.indexToWorldVec3(r, a), a[0] < o[0] && (o[0] = a[0]), a[1] < o[2] && (o[2] = a[1]), a[2] < o[4] && (o[4] = a[2]), a[0] > o[1] && (o[1] = a[0]), a[1] > o[3] && (o[3] = a[1]), a[2] > o[5] && (o[5] = a[2]);
                            return o
                        }, t.computeTransforms = function() {
                            var t = u.quat.create();
                            u.quat.fromMat3(t, e.direction);
                            var n = u.vec3.fromValues(e.origin[0], e.origin[1], e.origin[2]),
                                r = u.vec3.fromValues(e.spacing[0], e.spacing[1], e.spacing[2]);
                            u.mat4.fromRotationTranslationScale(e.indexToWorld, t, n, r), u.mat4.invert(e.worldToIndex, e.indexToWorld)
                        }, t.setDirection = function() {
                            for (var n = arguments.length, r = Array(n), a = 0; a < n; a++) r[a] = arguments[a];
                            if (e.deleted) return f("instance deleted - cannot call any method"), !1;
                            var o = r;
                            if (1 === o.length && Array.isArray(o[0]) && (o = o[0]), 9 !== o.length) throw new RangeError("Invalid number of values for array setter");
                            var i = !1;
                            if (e.direction.forEach(function(t, e) {
                                    if (t !== o[e]) {
                                        if (i) return;
                                        i = !0
                                    }
                                }), i) {
                                for (var u = 0; u < 9; ++u) e.direction[u] = o[u];
                                t.modified()
                            }
                            return !0
                        }, t.indexToWorldVec3 = function(t, n) {
                            u.vec3.transformMat4(n, t, e.indexToWorld)
                        }, t.indexToWorld = function(t, n) {
                            var r = u.vec3.fromValues(t[0], t[1], t[2]),
                                a = u.vec3.create();
                            u.vec3.transformMat4(a, r, e.indexToWorld), u.vec3.copy(n, a)
                        }, t.worldToIndexVec3 = function(t, n) {
                            u.vec3.transformMat4(n, t, e.worldToIndex)
                        }, t.worldToIndex = function(t, n) {
                            var r = u.vec3.fromValues(t[0], t[1], t[2]),
                                a = u.vec3.create();
                            u.vec3.transformMat4(a, r, e.worldToIndex), u.vec3.copy(n, a)
                        }, t.onModified(t.computeTransforms), t.computeTransforms()
                    }(t, e)
            }
            var d = e.newInstance = r.default.newInstance(l, "vtkImageData");
            e.default = {
                newInstance: d,
                extend: l
            }
        },
        9: function(t, e, n) {
            "use strict";
            Object.defineProperty(e, "__esModule", {
                value: !0
            });
            var r = function() {
                    return function(t, e) {
                        if (Array.isArray(t)) return t;
                        if (Symbol.iterator in Object(t)) return function(t, e) {
                            var n = [],
                                r = !0,
                                a = !1,
                                o = void 0;
                            try {
                                for (var i, u = t[Symbol.iterator](); !(r = (i = u.next()).done) && (n.push(i.value), !e || n.length !== e); r = !0);
                            } catch (t) {
                                a = !0, o = t
                            } finally {
                                try {
                                    !r && u.return && u.return()
                                } finally {
                                    if (a) throw o
                                }
                            }
                            return n
                        }(t, e);
                        throw new TypeError("Invalid attempt to destructure non-iterable instance")
                    }
                }(),
                a = i(n(210)),
                o = i(n(2));

            function i(t) {
                return t && t.__esModule ? t : {
                    default: t
                }
            }
            var u = o.default.vtkErrorMacro,
                c = o.default.vtkWarningMacro,
                f = 0,
                s = 20,
                l = 1e-12;

            function d(t) {
                return function() {
                    return u("vtkMath::" + t + " - NOT IMPLEMENTED")
                }
            }

            function v(t, e) {
                for (var n = 0; n < 3; n++) {
                    var r = t[n];
                    t[n] = e[n], e[n] = r
                }
            }

            function p() {
                for (var t = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : 3, e = []; e.length < t;) e.push(0);
                return e
            }
            var m = Math.round,
                h = Math.floor,
                y = Math.ceil,
                g = Math.min,
                b = Math.max;
            var M = d("ceilLog2"),
                A = d("factorial");

            function I(t) {
                for (var e = 1; e < t;) e *= 2;
                return e
            }
            var T = d("gaussian");

            function O(t, e) {
                t[0] *= e, t[1] *= e, t[2] *= e
            }

            function w(t, e) {
                t[0] *= e, t[1] *= e
            }

            function x(t, e) {
                return t[0] * e[0] + t[1] * e[1] + t[2] * e[2]
            }

            function E(t, e, n) {
                var r = t[1] * e[2] - t[2] * e[1],
                    a = t[2] * e[0] - t[0] * e[2],
                    o = t[0] * e[1] - t[1] * e[0];
                n[0] = r, n[1] = a, n[2] = o
            }

            function k(t) {
                var e = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : 3;
                switch (e) {
                    case 1:
                        return Math.abs(t);
                    case 2:
                        return Math.sqrt(t[0] * t[0] + t[1] * t[1]);
                    case 3:
                        return Math.sqrt(t[0] * t[0] + t[1] * t[1] + t[2] * t[2]);
                    default:
                        for (var n = 0, r = 0; r < e; r++) n += t[r] * t[r];
                        return Math.sqrt(n)
                }
            }

            function S(t) {
                var e = k(t);
                return 0 !== e && (t[0] /= e, t[1] /= e, t[2] /= e), e
            }

            function N(t, e) {
                return t[0] * e[0] + t[1] * e[1]
            }

            function _(t) {
                return Math.sqrt(t[0] * t[0] + t[1] * t[1])
            }

            function D() {
                for (var t = arguments.length, e = Array(t), n = 0; n < t; n++) e[n] = arguments[n];
                return 2 === e.length ? e[0][0] * e[1][1] - e[1][0] * e[0][1] : 4 === e.length ? e[0] * e[3] - e[1] * e[2] : Number.NaN
            }

            function C(t, e, n) {
                for (var r = [
                        [0, 0, 0],
                        [0, 0, 0],
                        [0, 0, 0]
                    ], a = 0; a < 3; a++) r[0][a] = t[0][0] * e[0][a] + t[0][1] * e[1][a] + t[0][2] * e[2][a], r[1][a] = t[1][0] * e[0][a] + t[1][1] * e[1][a] + t[1][2] * e[2][a], r[2][a] = t[2][0] * e[0][a] + t[2][1] * e[1][a] + t[2][2] * e[2][a];
                for (var o = 0; o < 3; o++) n[o][0] = r[o][0], n[o][1] = r[o][1], n[o][2] = r[o][2]
            }

            function P(t, e) {
                var n = void 0;
                n = t[1][0], e[1][0] = t[0][1], e[0][1] = n, n = t[2][0], e[2][0] = t[0][2], e[0][2] = n, n = t[2][1], e[2][1] = t[1][2], e[1][2] = n, e[0][0] = t[0][0], e[1][1] = t[1][1], e[2][2] = t[2][2]
            }

            function L(t) {
                for (var e = 0; e < 3; e++) t[e][0] = t[e][1] = t[e][2] = 0, t[e][e] = 1
            }

            function j(t) {
                return t[0][0] * t[1][1] * t[2][2] + t[1][0] * t[2][1] * t[0][2] + t[2][0] * t[0][1] * t[1][2] - t[0][0] * t[2][1] * t[1][2] - t[1][0] * t[0][1] * t[2][2] - t[2][0] * t[1][1] * t[0][2]
            }

            function R(t, e) {
                var n = t[0] * t[0],
                    r = t[0] * t[1],
                    a = t[0] * t[2],
                    o = t[0] * t[3],
                    i = t[1] * t[1],
                    u = t[2] * t[2],
                    c = t[3] * t[3],
                    f = t[1] * t[2],
                    s = t[1] * t[3],
                    l = t[2] * t[3],
                    d = i + u + c,
                    v = 1 / (n + d),
                    p = (n - d) * v;
                v *= 2, e[0][0] = i * v + p, e[1][0] = (f + o) * v, e[2][0] = (s - a) * v, e[0][1] = (f - o) * v, e[1][1] = u * v + p, e[2][1] = (l + r) * v, e[0][2] = (s + a) * v, e[1][2] = (l - r) * v, e[2][2] = c * v + p
            }

            function F(t, e, n, r) {
                var a = void 0,
                    o = void 0,
                    i = void 0,
                    u = void 0,
                    f = void 0,
                    l = void 0,
                    d = void 0,
                    v = void 0,
                    m = void 0,
                    h = void 0,
                    y = void 0,
                    g = void 0,
                    b = void 0,
                    M = void 0,
                    A = void 0,
                    I = void 0,
                    T = p(e),
                    O = p(e),
                    w = function(t, e, n, r, a) {
                        M = t[e][n], b = t[r][a], t[e][n] = M - g * (b + M * h), t[r][a] = b + g * (M - b * h)
                    };
                for (f = 0; f < e; f++) {
                    for (u = 0; u < e; u++) r[f][u] = 0;
                    r[f][f] = 1
                }
                for (f = 0; f < e; f++) T[f] = n[f] = t[f][f], O[f] = 0;
                for (a = 0; a < s; a++) {
                    for (y = 0, f = 0; f < e - 1; f++)
                        for (u = f + 1; u < e; u++) y += Math.abs(t[f][u]);
                    if (0 === y) break;
                    for (d = a < 3 ? .2 * y / (e * e) : 0, f = 0; f < e - 1; f++)
                        for (u = f + 1; u < e; u++)
                            if (M = 100 * Math.abs(t[f][u]), a > 3 && Math.abs(n[f]) + M === Math.abs(n[f]) && Math.abs(n[u]) + M === Math.abs(n[u])) t[f][u] = 0;
                            else if (Math.abs(t[f][u]) > d) {
                        for (b = n[u] - n[f], Math.abs(b) + M === Math.abs(b) ? m = t[f][u] / b : (v = .5 * b / t[f][u], m = 1 / (Math.abs(v) + Math.sqrt(1 + v * v)), v < 0 && (m = -m)), A = 1 / Math.sqrt(1 + m * m), h = (g = m * A) / (1 + A), b = m * t[f][u], O[f] -= b, O[u] += b, n[f] -= b, n[u] += b, t[f][u] = 0, o = 0; o <= f - 1; o++) w(t, o, f, o, u);
                        for (o = f + 1; o <= u - 1; o++) w(t, f, o, o, u);
                        for (o = u + 1; o < e; o++) w(t, f, o, u, o);
                        for (o = 0; o < e; o++) w(r, o, f, o, u)
                    }
                    for (f = 0; f < e; f++) T[f] += O[f], n[f] = T[f], O[f] = 0
                }
                if (a >= s) return c("vtkMath::Jacobi: Error extracting eigenfunctions"), 0;
                for (o = 0; o < e - 1; o++) {
                    for (I = n[i = o], a = o + 1; a < e; a++) n[a] >= I && (I = n[i = a]);
                    if (i !== o)
                        for (n[i] = n[o], n[o] = I, a = 0; a < e; a++) I = r[a][o], r[a][o] = r[a][i], r[a][i] = I
                }
                var x = (e >> 1) + (1 & e);
                for (o = 0; o < e; o++) {
                    for (l = 0, a = 0; a < e; a++) r[a][o] >= 0 && l++;
                    if (l < x)
                        for (a = 0; a < e; a++) r[a][o] *= -1
                }
                return 1
            }

            function B(t, e) {
                var n = [
                    [0, 0, 0, 0],
                    [0, 0, 0, 0],
                    [0, 0, 0, 0],
                    [0, 0, 0, 0]
                ];
                n[0][0] = t[0][0] + t[1][1] + t[2][2], n[1][1] = t[0][0] - t[1][1] - t[2][2], n[2][2] = -t[0][0] + t[1][1] - t[2][2], n[3][3] = -t[0][0] - t[1][1] + t[2][2], n[0][1] = n[1][0] = t[2][1] - t[1][2], n[0][2] = n[2][0] = t[0][2] - t[2][0], n[0][3] = n[3][0] = t[1][0] - t[0][1], n[1][2] = n[2][1] = t[1][0] + t[0][1], n[1][3] = n[3][1] = t[0][2] + t[2][0], n[2][3] = n[3][2] = t[2][1] + t[1][2];
                for (var r = [
                        [0, 0, 0, 0],
                        [0, 0, 0, 0],
                        [0, 0, 0, 0],
                        [0, 0, 0, 0]
                    ], a = [0, 0, 0, 0], o = [0, 0, 0, 0], i = 0; i < 4; i++) a[i] = n[i], o[i] = r[i];
                F(a, 4, [0, 0, 0, 0], o), e[0] = r[0][0], e[1] = r[1][0], e[2] = r[2][0], e[3] = r[3][0]
            }

            function Y(t, e) {
                for (var n = 0; n < 3; n++) e[0][n] = t[0][n], e[1][n] = t[1][n], e[2][n] = t[2][n];
                for (var r = p(3), a = p(3), o = void 0, i = 0; i < 3; i++) {
                    var u = Math.abs(e[i][0]),
                        c = Math.abs(e[i][1]),
                        f = Math.abs(e[i][2]);
                    o = f > (o = c > u ? c : u) ? f : o, r[i] = 1, 0 !== o && (r[i] /= o)
                }
                var s = Math.abs(e[0][0]) * r[0],
                    l = Math.abs(e[1][0]) * r[1],
                    d = Math.abs(e[2][0]) * r[2];
                a[0] = 0, l >= (o = s) && (o = l, a[0] = 1), d >= o && (a[0] = 2), 0 !== a[0] && (v(e[a[0]], e[0]), r[a[0]] = r[0]);
                var m = Math.abs(e[1][1]) * r[1],
                    h = Math.abs(e[2][1]) * r[2];
                a[1] = 1, h >= (o = m) && (a[1] = 2, v(e[2], e[1])), a[2] = 2;
                var y = 0;
                if (j(e) < 0) {
                    y = 1;
                    for (var g = 0; g < 3; g++) e[0][g] = -e[0][g], e[1][g] = -e[1][g], e[2][g] = -e[2][g]
                }
                var b = p(4);
                if (B(e, b), R(b, e), y)
                    for (var M = 0; M < 3; M++) e[0][M] = -e[0][M], e[1][M] = -e[1][M], e[2][M] = -e[2][M];
                1 !== a[1] && v(e[a[1]], e[1]), 0 !== a[0] && v(e[a[0]], e[0])
            }

            function q(t, e, n) {
                var r = void 0,
                    a = void 0,
                    o = void 0,
                    i = void 0,
                    u = void 0,
                    c = void 0,
                    f = [p(3), p(3), p(3)],
                    s = p(3),
                    l = p(3);
                for (r = 0; r < 3; r++) f[r][0] = t[r][0], f[r][1] = t[r][1], f[r][2] = t[r][2], s[r] = f[r], l[r] = n[r];
                if (F(s, 3, e, l), e[0] !== e[1] || e[0] !== e[2]) {
                    for (P(n, n), r = 0; r < 3; r++)
                        if (e[(r + 1) % 3] === e[(r + 2) % 3]) {
                            for (c = Math.abs(n[r][0]), i = 0, a = 1; a < 3; a++) c < (u = Math.abs(n[r][a])) && (c = u, i = a);
                            return i !== r && (u = e[i], e[i] = e[r], e[r] = u, v(n[r], n[i])), n[i][i] < 0 && (n[i][0] = -n[i][0], n[i][1] = -n[i][1], n[i][2] = -n[i][2]), o = (i + 2) % 3, n[a = (i + 1) % 3][0] = 0, n[a][1] = 0, n[a][2] = 0, n[a][a] = 1, E(n[i], n[a], n[o]), S(n[o]), E(n[o], n[i], n[a]), void P(n, n)
                        }
                    for (c = Math.abs(n[0][0]), i = 0, r = 1; r < 3; r++) c < (u = Math.abs(n[r][0])) && (c = u, i = r);
                    for (0 !== i && (u = e[i], e[i] = e[0], e[0] = u, v(n[i], n[0])), Math.abs(n[1][1]) < Math.abs(n[2][1]) && (u = e[2], e[2] = e[1], e[1] = u, v(n[2], n[1])), r = 0; r < 2; r++) n[r][r] < 0 && (n[r][0] = -n[r][0], n[r][1] = -n[r][1], n[r][2] = -n[r][2]);
                    j(n) < 0 && (n[2][0] = -n[2][0], n[2][1] = -n[2][1], n[2][2] = -n[2][2]), P(n, n)
                } else L(n)
            }

            function G(t, e, n) {
                var r = void 0,
                    a = void 0,
                    o = void 0,
                    i = void 0,
                    u = 0,
                    f = void 0,
                    s = void 0,
                    d = void 0,
                    v = p(n);
                for (r = 0; r < n; r++) {
                    for (i = 0, a = 0; a < n; a++)(d = Math.abs(t[r][a])) > i && (i = d);
                    if (0 === i) return c("Unable to factor linear system"), 0;
                    v[r] = 1 / i
                }
                for (a = 0; a < n; a++) {
                    for (r = 0; r < a; r++) {
                        for (f = t[r][a], o = 0; o < r; o++) f -= t[r][o] * t[o][a];
                        t[r][a] = f
                    }
                    for (i = 0, r = a; r < n; r++) {
                        for (f = t[r][a], o = 0; o < a; o++) f -= t[r][o] * t[o][a];
                        t[r][a] = f, (s = v[r] * Math.abs(f)) >= i && (i = s, u = r)
                    }
                    if (a !== u) {
                        for (o = 0; o < n; o++) s = t[u][o], t[u][o] = t[a][o], t[a][o] = s;
                        v[u] = v[a]
                    }
                    if (e[a] = u, Math.abs(t[a][a]) <= l) return c("Unable to factor linear system"), 0;
                    if (a !== n - 1)
                        for (s = 1 / t[a][a], r = a + 1; r < n; r++) t[r][a] *= s
                }
                return 1
            }

            function z(t, e, n, r) {
                var a = void 0,
                    o = void 0,
                    i = void 0,
                    u = void 0,
                    c = void 0;
                for (i = -1, a = 0; a < r; a++) {
                    if (c = n[u = e[a]], n[u] = n[a], i >= 0)
                        for (o = i; o <= a - 1; o++) c -= t[a][o] * n[o];
                    else 0 !== c && (i = a);
                    n[a] = c
                }
                for (a = r - 1; a >= 0; a--) {
                    for (c = n[a], o = a + 1; o < r; o++) c -= t[a][o] * n[o];
                    n[a] = c / t[a][a]
                }
            }

            function V(t, e, n) {
                var r = arguments.length > 3 && void 0 !== arguments[3] ? arguments[3] : null,
                    a = arguments.length > 4 && void 0 !== arguments[4] ? arguments[4] : null,
                    o = r || p(n),
                    i = a || p(n);
                if (0 === G(t, o, n)) return 0;
                for (var u = 0; u < n; u++) {
                    for (var c = 0; c < n; c++) i[c] = 0;
                    i[u] = 1, z(t, o, i, n);
                    for (var f = 0; f < n; f++) e[f][u] = i[f]
                }
                return 1
            }

            function U(t, e, n, r) {
                if (t < n) return c("Insufficient number of samples. Underdetermined."), 0;
                var a = void 0,
                    o = void 0,
                    i = void 0,
                    u = p(n),
                    f = p(n),
                    s = p(n);
                for (a = 0; a < n; a++)
                    for (s[a] = p(n), u[a] = p(n), o = 0; o < n; o++) u[a][o] = 0;
                for (i = 0; i < t; i++)
                    for (a = 0; a < n; a++)
                        for (o = a; o < n; o++) u[a][o] += e[i][a] * e[i][o];
                for (a = 0; a < n; a++)
                    for (o = 0; o < a; o++) u[a][o] = u[o][a];
                for (F(u, n, f, s), a = 0; a < n; a++) r[a][0] = s[a][n - 1];
                return 1
            }

            function J(t, e) {
                var n = r(t, 3),
                    a = (n[0] + 16) / 116,
                    o = n[1] / 500 + a,
                    i = a - n[2] / 200;
                a = Math.pow(a, 3) > .008856 ? Math.pow(a, 3) : (a - 16 / 116) / 7.787, o = Math.pow(o, 3) > .008856 ? Math.pow(o, 3) : (o - 16 / 116) / 7.787, i = Math.pow(i, 3) > .008856 ? Math.pow(i, 3) : (i - 16 / 116) / 7.787;
                e[0] = .9505 * o, e[1] = 1 * a, e[2] = 1.089 * i
            }

            function X(t, e) {
                var n = r(t, 3),
                    a = n[0] / .9505,
                    o = n[1] / 1,
                    i = n[2] / 1.089;
                a = a > .008856 ? Math.pow(a, 1 / 3) : 7.787 * a + 16 / 116, o = o > .008856 ? Math.pow(o, 1 / 3) : 7.787 * o + 16 / 116, i = i > .008856 ? Math.pow(i, 1 / 3) : 7.787 * i + 16 / 116, e[0] = 116 * o - 16, e[1] = 500 * (a - o), e[2] = 200 * (o - i)
            }

            function W(t, e) {
                var n = r(t, 3),
                    a = n[0],
                    o = n[1],
                    i = n[2],
                    u = 3.2406 * a + -1.5372 * o + -.4986 * i,
                    c = -.9689 * a + 1.8758 * o + .0415 * i,
                    f = .0557 * a + -.204 * o + 1.057 * i;
                u > .0031308 ? u = 1.055 * Math.pow(u, 1 / 2.4) - .055 : u *= 12.92, c > .0031308 ? c = 1.055 * Math.pow(c, 1 / 2.4) - .055 : c *= 12.92, f > .0031308 ? f = 1.055 * Math.pow(f, 1 / 2.4) - .055 : f *= 12.92;
                var s = u;
                s < c && (s = c), s < f && (s = f), s > 1 && (u /= s, c /= s, f /= s), u < 0 && (u = 0), c < 0 && (c = 0), f < 0 && (f = 0), e[0] = u, e[1] = c, e[2] = f
            }

            function H(t, e) {
                var n = r(t, 3),
                    a = n[0],
                    o = n[1],
                    i = n[2];
                a > .04045 ? a = Math.pow((a + .055) / 1.055, 2.4) : a /= 12.92, o > .04045 ? o = Math.pow((o + .055) / 1.055, 2.4) : o /= 12.92, i > .04045 ? i = Math.pow((i + .055) / 1.055, 2.4) : i /= 12.92, e[0] = .4124 * a + .3576 * o + .1805 * i, e[1] = .2126 * a + .7152 * o + .0722 * i, e[2] = .0193 * a + .1192 * o + .9505 * i
            }
            var K = d("GetScalarTypeFittingRange"),
                Z = d("GetAdjustedScalarRange");
            var Q = Number.isFinite,
                $ = Number.isNaN;
            e.default = {
                Pi: function() {
                    return Math.PI
                },
                radiansFromDegrees: function(t) {
                    return t / 180 * Math.PI
                },
                degreesFromRadians: function(t) {
                    return 180 * t / Math.PI
                },
                round: m,
                floor: h,
                ceil: y,
                ceilLog2: M,
                min: g,
                max: b,
                arrayMin: function(t) {
                    for (var e = 1 / 0, n = 0, r = t.length; n < r; ++n) t[n] < e && (e = t[n]);
                    return e
                },
                arrayMax: function(t) {
                    for (var e = -1 / 0, n = 0, r = t.length; n < r; ++n) e < t[n] && (e = t[n]);
                    return e
                },
                isPowerOfTwo: function(t) {
                    return t === I(t)
                },
                nearestPowerOfTwo: I,
                factorial: A,
                binomial: function(t, e) {
                    for (var n = 1, r = 1; r <= e; ++r) n *= (t - r + 1) / r;
                    return Math.floor(n)
                },
                beginCombination: function(t, e) {
                    if (t < e) return 0;
                    for (var n = p(e), r = 0; r < e; ++r) n[r] = r;
                    return n
                },
                nextCombination: function(t, e, n) {
                    for (var r = 0, a = e - 1; a >= 0; --a)
                        if (n[a] < t - e + a) {
                            for (var o = n[a] + 1; a < e;) n[a++] = o++;
                            r = 1;
                            break
                        }
                    return r
                },
                randomSeed: function(t) {
                    (0, a.default)("" + t, {
                        global: !0
                    }), f = t
                },
                getSeed: function() {
                    return f
                },
                random: function() {
                    var t = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : 0;
                    return t + ((arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : 1) - t) * Math.random()
                },
                gaussian: T,
                add: function(t, e, n) {
                    n[0] = t[0] + e[0], n[1] = t[1] + e[1], n[2] = t[2] + e[2]
                },
                subtract: function(t, e, n) {
                    n[0] = t[0] - e[0], n[1] = t[1] - e[1], n[2] = t[2] - e[2]
                },
                multiplyScalar: O,
                multiplyScalar2D: w,
                dot: x,
                outer: function(t, e, n) {
                    for (var r = 0; r < 3; r++)
                        for (var a = 0; a < 3; a++) n[r][a] = t[r] * e[a]
                },
                cross: E,
                norm: k,
                normalize: S,
                perpendiculars: function(t, e, n, r) {
                    var a = t[0] * t[0],
                        o = t[1] * t[1],
                        i = t[2] * t[2],
                        u = Math.sqrt(a + o + i),
                        c = void 0,
                        f = void 0,
                        s = void 0;
                    a > o && a > i ? (c = 0, f = 1, s = 2) : o > i ? (c = 1, f = 2, s = 0) : (c = 2, f = 0, s = 1);
                    var l = t[c] / u,
                        d = t[f] / u,
                        v = t[s] / u,
                        p = Math.sqrt(l * l + v * v);
                    if (0 !== r) {
                        var m = Math.sin(r),
                            h = Math.cos(r);
                        e && (e[c] = (v * h - l * d * m) / p, e[f] = m * p, e[s] = (-l * h - d * v * m) / p), n && (n[c] = (-v * m - l * d * h) / p, n[f] = h * p, n[s] = (l * m - d * v * h) / p)
                    } else e && (e[c] = v / p, e[f] = 0, e[s] = -l / p), n && (n[c] = -l * d / p, n[f] = p, n[s] = -d * v / p)
                },
                projectVector: function(t, e, n) {
                    var r = x(e, e);
                    if (0 === r) return n[0] = 0, n[1] = 0, n[2] = 0, !1;
                    for (var a = x(t, e) / r, o = 0; o < 3; o++) n[o] = e[o];
                    return O(n, a), !0
                },
                projectVector2D: function(t, e, n) {
                    var r = N(e, e);
                    if (0 === r) return n[0] = 0, n[1] = 0, !1;
                    for (var a = N(t, e) / r, o = 0; o < 2; o++) n[o] = e[o];
                    return w(n, a), !0
                },
                distance2BetweenPoints: function(t, e) {
                    return (t[0] - e[0]) * (t[0] - e[0]) + (t[1] - e[1]) * (t[1] - e[1]) + (t[2] - e[2]) * (t[2] - e[2])
                },
                angleBetweenVectors: function(t, e) {
                    var n = [0, 0, 0];
                    return E(t, e, n), Math.atan2(k(n), x(t, e))
                },
                gaussianAmplitude: function(t, e, n) {
                    var r = Math.abs(t - n);
                    return 1 / Math.sqrt(2 * Math.PI * e) * Math.exp(-Math.pow(r, 2) / (2 * e))
                },
                gaussianWeight: function(t, e, n) {
                    var r = Math.abs(t - n);
                    return Math.exp(-Math.pow(r, 2) / (2 * e))
                },
                dot2D: N,
                outer2D: function(t, e, n) {
                    for (var r = 0; r < 2; r++)
                        for (var a = 0; a < 2; a++) n[r][a] = t[r] * e[a]
                },
                norm2D: _,
                normalize2D: function(t) {
                    var e = _(t);
                    return 0 !== e && (t[0] /= e, t[1] /= e), e
                },
                determinant2x2: D,
                LUFactor3x3: function(t, e) {
                    for (var n = void 0, r = void 0, a = void 0, o = [0, 0, 0], i = 0; i < 3; i++) a = Math.abs(t[i][0]), (r = Math.abs(t[i][1])) > a && (a = r), (r = Math.abs(t[i][2])) > a && (a = r), o[i] = 1 / a;
                    a = o[0] * Math.abs(t[0][0]), n = 0, (r = o[1] * Math.abs(t[1][0])) >= a && (a = r, n = 1), (r = o[2] * Math.abs(t[2][0])) >= a && (n = 2), 0 !== n && (v(t[n], t[0]), o[n] = o[0]), e[0] = n, t[1][0] /= t[0][0], t[2][0] /= t[0][0], t[1][1] -= t[1][0] * t[0][1], t[2][1] -= t[2][0] * t[0][1], a = o[1] * Math.abs(t[1][1]), n = 1, (r = o[2] * Math.abs(t[2][1])) >= a && (n = 2, v(t[2], t[1]), o[2] = o[1]), e[1] = n, t[2][1] /= t[1][1], t[1][2] -= t[1][0] * t[0][2], t[2][2] -= t[2][0] * t[0][2] + t[2][1] * t[1][2], e[2] = 2
                },
                LUSolve3x3: function(t, e, n) {
                    var r = n[e[0]];
                    n[e[0]] = n[0], n[0] = r, r = n[e[1]], n[e[1]] = n[1], n[1] = r - t[1][0] * n[0], r = n[e[2]], n[e[2]] = n[2], n[2] = r - t[2][0] * n[0] - t[2][1] * n[1], n[2] /= t[2][2], n[1] = (n[1] - t[1][2] * n[2]) / t[1][1], n[0] = (n[0] - t[0][1] * n[1] - t[0][2] * n[2]) / t[0][0]
                },
                linearSolve3x3: function(t, e, n) {
                    var r = t[0][0],
                        a = t[0][1],
                        o = t[0][2],
                        i = t[1][0],
                        u = t[1][1],
                        c = t[1][2],
                        f = t[2][0],
                        s = t[2][1],
                        l = t[2][2],
                        d = +D(u, s, c, l),
                        v = -D(i, f, c, l),
                        p = +D(i, f, u, s),
                        m = -D(a, s, o, l),
                        h = +D(r, f, o, l),
                        y = -D(r, f, a, s),
                        g = +D(a, u, o, c),
                        b = -D(r, i, o, c),
                        M = +D(r, i, a, u),
                        A = r * d + a * v + o * p,
                        I = d * e[0] + m * e[1] + g * e[2],
                        T = v * e[0] + h * e[1] + b * e[2],
                        O = p * e[0] + y * e[1] + M * e[2];
                    n[0] = I / A, n[1] = T / A, n[2] = O / A
                },
                multiply3x3_vect3: function(t, e, n) {
                    var r = t[0][0] * e[0] + t[0][1] * e[1] + t[0][2] * e[2],
                        a = t[1][0] * e[0] + t[1][1] * e[1] + t[1][2] * e[2],
                        o = t[2][0] * e[0] + t[2][1] * e[1] + t[2][2] * e[2];
                    n[0] = r, n[1] = a, n[2] = o
                },
                multiply3x3_mat3: C,
                multiplyMatrix: function(t, e, n, r, a, o, i) {
                    r !== a && u("Number of columns of A must match number of rows of B.");
                    for (var c = 0; c < n; c++)
                        for (var f = 0; f < o; f++) {
                            i[c][f] = 0;
                            for (var s = 0; s < r; s++) i[c][f] += t[c][s] * e[s][f]
                        }
                },
                transpose3x3: P,
                invert3x3: function(t, e) {
                    var n = t[0][0],
                        r = t[0][1],
                        a = t[0][2],
                        o = t[1][0],
                        i = t[1][1],
                        u = t[1][2],
                        c = t[2][0],
                        f = t[2][1],
                        s = t[2][2],
                        l = +D(i, f, u, s),
                        d = -D(o, c, u, s),
                        v = +D(o, c, i, f),
                        p = -D(r, f, a, s),
                        m = +D(n, c, a, s),
                        h = -D(n, c, r, f),
                        y = +D(r, i, a, u),
                        g = -D(n, o, a, u),
                        b = +D(n, o, r, i),
                        M = n * l + r * d + a * v;
                    e[0][0] = l / M, e[1][0] = d / M, e[2][0] = v / M, e[0][1] = p / M, e[1][1] = m / M, e[2][1] = h / M, e[0][2] = y / M, e[1][2] = g / M, e[2][2] = b / M
                },
                identity3x3: L,
                determinant3x3: j,
                quaternionToMatrix3x3: R,
                areMatricesEqual: function(t, e) {
                    return !t.length !== e.length && t.every(function(t, n) {
                        return t === e[n]
                    })
                },
                matrix3x3ToQuaternion: B,
                multiplyQuaternion: function(t, e, n) {
                    var r = t[0] * e[0],
                        a = t[0] * e[1],
                        o = t[0] * e[2],
                        i = t[0] * e[3],
                        u = t[1] * e[0],
                        c = t[1] * e[1],
                        f = t[1] * e[2],
                        s = t[1] * e[3],
                        l = t[2] * e[0],
                        d = t[2] * e[1],
                        v = t[2] * e[2],
                        p = t[2] * e[3],
                        m = t[3] * e[0],
                        h = t[3] * e[1],
                        y = t[3] * e[2],
                        g = t[3] * e[3];
                    n[0] = r - c - v - g, n[1] = a + u + p - y, n[2] = o - s + l + h, n[3] = i + f - d + m
                },
                orthogonalize3x3: Y,
                diagonalize3x3: q,
                singularValueDecomposition3x3: function(t, e, n, r) {
                    var a = void 0,
                        o = [p(3), p(3), p(3)];
                    for (a = 0; a < 3; a++) o[0][a] = t[0][a], o[1][a] = t[1][a], o[2][a] = t[2][a];
                    var i = j(o);
                    if (i < 0)
                        for (a = 0; a < 3; a++) o[0][a] = -o[0][a], o[1][a] = -o[1][a], o[2][a] = -o[2][a];
                    Y(o, e), P(o, o), C(o, e, r), q(r, n, r), C(e, r, e), P(r, r), i < 0 && (n[0] = -n[0], n[1] = -n[1], n[2] = -n[2])
                },
                solveLinearSystem: function(t, e, n) {
                    if (2 === n) {
                        var r = p(2),
                            a = D(t[0][0], t[0][1], t[1][0], t[1][1]);
                        return 0 === a ? 0 : (r[0] = (t[1][1] * e[0] - t[0][1] * e[1]) / a, r[1] = (-t[1][0] * e[0] + t[0][0] * e[1]) / a, e[0] = r[0], e[1] = r[1], 1)
                    }
                    if (1 === n) return 0 === t[0][0] ? 0 : (e[0] /= t[0][0], 1);
                    var o = p(n);
                    return 0 === G(t, o, n) ? 0 : (z(t, o, e, n), 1)
                },
                invertMatrix: V,
                luFactorLinearSystem: G,
                luSolveLinearSystem: z,
                estimateMatrixCondition: function(t, e) {
                    for (var n = +Number.MAX_VALUE, r = -Number.MAX_VALUE, a = 0; a < e; a++)
                        for (var o = a; o < e; o++) Math.abs(t[a][o]) > b && (r = Math.abs(t[a][o]));
                    for (var i = 0; i < e; i++) Math.abs(t[i][i]) < g && (n = Math.abs(t[i][i]));
                    return 0 === n ? Number.MAX_VALUE : r / n
                },
                jacobi: function(t, e, n) {
                    return F(t, 3, e, n)
                },
                jacobiN: F,
                solveHomogeneousLeastSquares: U,
                solveLeastSquares: function(t, e, n, r, a, o) {
                    var i = !(arguments.length > 6 && void 0 !== arguments[6]) || arguments[6];
                    if (t < n || t < a) return c("Insufficient number of samples. Underdetermined."), 0;
                    var u = p(a),
                        f = 1,
                        s = void 0,
                        d = 0,
                        v = void 0,
                        m = void 0,
                        h = void 0,
                        y = 0;
                    if (i) {
                        for (m = 0; m < a; m++) u[m] = 1;
                        for (v = 0; v < t; v++)
                            for (m = 0; m < a; m++) Math.abs(r[v][m]) > l && (f = 0, u[m] = 0);
                        if (f && 1 === a) return c("Detected homogeneous system (Y=0), calling SolveHomogeneousLeastSquares()"), U(t, e, n, o);
                        if (f) y = 1;
                        else
                            for (m = 0; m < a; m++) u[m] && (y = 1)
                    }
                    if (y) {
                        for (s = p(n), m = 0; m < n; m++) s[m] = [0];
                        d = U(t, e, n, s)
                    }
                    var g = p(n),
                        b = p(n),
                        M = p(n);
                    for (v = 0; v < n; v++) {
                        for (g[v] = p(n), b[v] = p(n), m = 0; m < n; m++) g[v][m] = 0, b[v][m] = 0;
                        for (M[v] = p(a), m = 0; m < a; m++) M[v][m] = 0
                    }
                    for (h = 0; h < t; h++)
                        for (v = 0; v < n; v++) {
                            for (m = v; m < n; m++) g[v][m] += e[h][v] * e[h][m];
                            for (m = 0; m < a; m++) M[v][m] += e[h][v] * r[h][m]
                        }
                    for (v = 0; v < n; v++)
                        for (m = 0; m < v; m++) g[v][m] = g[m][v];
                    var A = V(g, b, n);
                    if (A)
                        for (v = 0; v < n; v++)
                            for (m = 0; m < a; m++)
                                for (o[v][m] = 0, h = 0; h < n; h++) o[v][m] += b[v][h] * M[h][m];
                    if (y)
                        for (m = 0; m < a; m++)
                            if (u[m])
                                for (v = 0; v < n; v++) o[v][m] = s[v][0];
                    return y ? d && A : A
                },
                hex2float: function(t) {
                    var e = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : [0, .5, 1];
                    switch (t.length) {
                        case 3:
                            return e[0] = 17 * parseInt(t[0], 16) / 255, e[1] = 17 * parseInt(t[1], 16) / 255, e[2] = 17 * parseInt(t[2], 16) / 255, e;
                        case 4:
                            return e[0] = 17 * parseInt(t[1], 16) / 255, e[1] = 17 * parseInt(t[2], 16) / 255, e[2] = 17 * parseInt(t[3], 16) / 255, e;
                        case 6:
                            return e[0] = parseInt(t.substr(0, 2), 16) / 255, e[1] = parseInt(t.substr(2, 2), 16) / 255, e[2] = parseInt(t.substr(4, 2), 16) / 255, e;
                        case 7:
                            return e[0] = parseInt(t.substr(1, 2), 16) / 255, e[1] = parseInt(t.substr(3, 2), 16) / 255, e[2] = parseInt(t.substr(5, 2), 16) / 255, e;
                        case 9:
                            return e[0] = parseInt(t.substr(1, 2), 16) / 255, e[1] = parseInt(t.substr(3, 2), 16) / 255, e[2] = parseInt(t.substr(5, 2), 16) / 255, e[3] = parseInt(t.substr(7, 2), 16) / 255, e;
                        default:
                            return e
                    }
                },
                rgb2hsv: function(t, e) {
                    var n = void 0,
                        a = void 0,
                        o = r(t, 3),
                        i = o[0],
                        u = o[1],
                        c = o[2],
                        f = i,
                        s = i;
                    u > f ? f = u : u < s && (s = u), c > f ? f = c : c < s && (s = c);
                    var l = f;
                    (a = l > 0 ? (f - s) / f : 0) > 0 ? (n = i === f ? 1 / 6 * (u - c) / (f - s) : u === f ? 1 / 3 + 1 / 6 * (c - i) / (f - s) : 2 / 3 + 1 / 6 * (i - u) / (f - s)) < 0 && (n += 1) : n = 0, e[0] = n, e[1] = a, e[2] = l
                },
                hsv2rgb: function(t, e) {
                    var n = r(t, 3),
                        a = n[0],
                        o = n[1],
                        i = n[2],
                        u = void 0,
                        c = void 0,
                        f = void 0;
                    a > 1 / 6 && a <= 1 / 3 ? (c = 1, u = (1 / 3 - a) / (1 / 6), f = 0) : a > 1 / 3 && a <= .5 ? (c = 1, f = (a - 1 / 3) / (1 / 6), u = 0) : a > .5 && a <= 2 / 3 ? (f = 1, c = (2 / 3 - a) / (1 / 6), u = 0) : a > 2 / 3 && a <= 5 / 6 ? (f = 1, u = (a - 2 / 3) / (1 / 6), c = 0) : a > 5 / 6 && a <= 1 ? (u = 1, f = (1 - a) / (1 / 6), c = 0) : (u = 1, c = a / (1 / 6), f = 0), u = o * u + (1 - o), c = o * c + (1 - o), f = o * f + (1 - o), u *= i, c *= i, f *= i, e[0] = u, e[1] = c, e[2] = f
                },
                lab2xyz: J,
                xyz2lab: X,
                xyz2rgb: W,
                rgb2xyz: H,
                rgb2lab: function(t, e) {
                    var n = [0, 0, 0];
                    H(t, n), X(n, e)
                },
                lab2rgb: function(t, e) {
                    var n = [0, 0, 0];
                    J(t, n), W(n, e)
                },
                uninitializeBounds: function(t) {
                    t[0] = 1, t[1] = -1, t[2] = 1, t[3] = -1, t[4] = 1, t[5] = -1
                },
                areBoundsInitialized: function(t) {
                    return !(t[1] - t[0] < 0)
                },
                clampValue: function(t, e, n) {
                    return t < e ? e : t > n ? n : t
                },
                clampAndNormalizeValue: function(t, e) {
                    var n = 0;
                    return e[0] !== e[1] && (n = ((n = t < e[0] ? e[0] : t > e[1] ? e[1] : t) - e[0]) / (e[1] - e[0])), n
                },
                getScalarTypeFittingRange: K,
                getAdjustedScalarRange: Z,
                extentIsWithinOtherExtent: function(t, e) {
                    if (!t || !e) return 0;
                    for (var n = 0; n < 6; n += 2)
                        if (t[n] < e[n] || t[n] > e[n + 1] || t[n + 1] < e[n] || t[n + 1] > e[n + 1]) return 0;
                    return 1
                },
                boundsIsWithinOtherBounds: function(t, e, n) {
                    if (!t || !e) return 0;
                    for (var r = 0; r < 6; r += 2)
                        if (t[r] + n[r / 2] < e[r] || t[r] - n[r / 2] > e[r + 1] || t[r + 1] + n[r / 2] < e[r] || t[r + 1] - n[r / 2] > e[r + 1]) return 0;
                    return 1
                },
                pointIsWithinBounds: function(t, e, n) {
                    if (!t || !e || !n) return 0;
                    for (var r = 0; r < 3; r++)
                        if (t[r] + n[r] < e[2 * r] || t[r] - n[r] > e[2 * r + 1]) return 0;
                    return 1
                },
                solve3PointCircle: function(t, e, n, r) {
                    for (var a = p(3), o = p(3), i = p(3), u = p(3), c = p(3), f = p(3), s = 0; s < 3; ++s) a[s] = t[s] - e[s], o[s] = e[s] - n[s], i[s] = n[s] - t[s], u[s] = -a[s], c[s] = -o[s], f[s] = -i[s];
                    var l = k(u),
                        d = k(c),
                        v = k(i),
                        m = p(3);
                    E(a, o, m);
                    for (var h = k(m), y = l * d * v / (2 * h), g = 2 * h * h, b = d * d * x(a, f) / g, M = v * v * x(u, o) / g, A = l * l * x(i, c) / g, I = 0; I < 3; ++I) r[I] = b * t[I] + M * e[I] + A * n[I];
                    return y
                },
                inf: 1 / 0,
                negInf: -1 / 0,
                isInf: function(t) {
                    return !Number.isFinite(t)
                },
                isNan: $,
                isNaN: $,
                isFinite: Q,
                createUninitializedBounds: function() {
                    return [].concat([Number.MAX_VALUE, -Number.MAX_VALUE, Number.MAX_VALUE, -Number.MAX_VALUE, Number.MAX_VALUE, -Number.MAX_VALUE])
                }
            }
        },
        99: function(t, e) {
            var n, r, a = t.exports = {};

            function o() {
                throw new Error("setTimeout has not been defined")
            }

            function i() {
                throw new Error("clearTimeout has not been defined")
            }

            function u(t) {
                if (n === setTimeout) return setTimeout(t, 0);
                if ((n === o || !n) && setTimeout) return n = setTimeout, setTimeout(t, 0);
                try {
                    return n(t, 0)
                } catch (e) {
                    try {
                        return n.call(null, t, 0)
                    } catch (e) {
                        return n.call(this, t, 0)
                    }
                }
            }! function() {
                try {
                    n = "function" == typeof setTimeout ? setTimeout : o
                } catch (t) {
                    n = o
                }
                try {
                    r = "function" == typeof clearTimeout ? clearTimeout : i
                } catch (t) {
                    r = i
                }
            }();
            var c, f = [],
                s = !1,
                l = -1;

            function d() {
                s && c && (s = !1, c.length ? f = c.concat(f) : l = -1, f.length && v())
            }

            function v() {
                if (!s) {
                    var t = u(d);
                    s = !0;
                    for (var e = f.length; e;) {
                        for (c = f, f = []; ++l < e;) c && c[l].run();
                        l = -1, e = f.length
                    }
                    c = null, s = !1,
                        function(t) {
                            if (r === clearTimeout) return clearTimeout(t);
                            if ((r === i || !r) && clearTimeout) return r = clearTimeout, clearTimeout(t);
                            try {
                                r(t)
                            } catch (e) {
                                try {
                                    return r.call(null, t)
                                } catch (e) {
                                    return r.call(this, t)
                                }
                            }
                        }(t)
                }
            }

            function p(t, e) {
                this.fun = t, this.array = e
            }

            function m() {}
            a.nextTick = function(t) {
                var e = new Array(arguments.length - 1);
                if (arguments.length > 1)
                    for (var n = 1; n < arguments.length; n++) e[n - 1] = arguments[n];
                f.push(new p(t, e)), 1 !== f.length || s || u(v)
            }, p.prototype.run = function() {
                this.fun.apply(null, this.array)
            }, a.title = "browser", a.browser = !0, a.env = {}, a.argv = [], a.version = "", a.versions = {}, a.on = m, a.addListener = m, a.once = m, a.off = m, a.removeListener = m, a.removeAllListeners = m, a.emit = m, a.prependListener = m, a.prependOnceListener = m, a.listeners = function(t) {
                return []
            }, a.binding = function(t) {
                throw new Error("process.binding is not supported")
            }, a.cwd = function() {
                return "/"
            }, a.chdir = function(t) {
                throw new Error("process.chdir is not supported")
            }, a.umask = function() {
                return 0
            }
        }
    })
});