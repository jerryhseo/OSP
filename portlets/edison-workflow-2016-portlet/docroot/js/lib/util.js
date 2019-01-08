var slice = Array.prototype.slice;
var consoleLog = {
  loggingLevel: { info: true, debug: false, error: true },
  setLoggingLevel : function(loggingLevelJson){
    this.loggingLevel = loggingLevelJson;
  },
  info : function (msg) {
    if(console && this.loggingLevel.info){
      console.log(msg);
    }
  },
  debug : function (msg) {
    if(console && this.loggingLevel.debug){
      console.log(msg);
    }
  },
  error : function (msg) {
    if(console && this.loggingLevel.error){
      console.log(msg);
    }
  }
};

var aSyncAjaxHelper = {
  "get": function (requestUrl, callback, errorCallback) {
    $.ajax({
      url: requestUrl,
      async: true,
      method: 'GET',
      timeout: 10000,
    }).done(function (result) {
      if(callback){ callback(result); }
      consoleLog.debug(requestUrl + " success");
    }).error(function () {
      consoleLog.debug(requestUrl + " error");
      if(errorCallback){ errorCallback(); }
    }).always(function () {
      consoleLog.debug(requestUrl + " complete");
    });
  },
  "getWithParams": function (requestUrl, params, callback, errorCallback) {
    $.ajax({
      url: requestUrl,
      async: true,
      data: params,
      method: 'GET',
      timeout: 10000,
    }).done(function (result) {
      if(callback){ callback(result); }
      consoleLog.debug(requestUrl + " success");
    }).error(function () {
      consoleLog.debug(requestUrl + " error");
      if(errorCallback){ errorCallback(); }
    }).always(function () {
      consoleLog.debug(requestUrl + " complete");
    });
  },
  "post": function (requestUrl, jsonData, callback, errorCallback) {
    $.ajax({
      url: requestUrl,
      async: true,
      data : jsonData,
      method: 'POST',
      timeout: 10000,
    }).done(function (result) {
      if(callback){ callback(result); }
      consoleLog.debug(requestUrl + " success");
    }).error(function (msg) {
      consoleLog.info(requestUrl + " error ");
      consoleLog.info(msg);
      if(errorCallback){ errorCallback(msg.responseText); }
    }).always(function (msg) {
      consoleLog.debug(requestUrl + " complete");
      consoleLog.debug(msg);
    });
  },
  "jsonPost": function (requestUrl, jsonData, callback, errorCallback) {
    $.ajax({
      url: requestUrl,
      async: true,
      contentType: "application/json; charset=utf-8",
      data : jsonData,
      method: "POST",
      dataType: "json",
      timeout: 10000,
    }).done(function (result) {
      if(callback){ callback(result); }
      consoleLog.debug(requestUrl + " success");
    }).error(function (msg) {
      consoleLog.info(requestUrl + " error ");
      consoleLog.info(msg);
      if(errorCallback){ errorCallback(msg.responseText); }
    }).always(function (msg) {
      consoleLog.debug(requestUrl + " complete");
      consoleLog.debug(msg);
    });
  }
};

var synchronousAjaxHelper = {
  "get": function (requestUrl, callback, errorCallback) {
    var resultData;
    $.ajax({
      url: requestUrl,
      async: false,
      method: 'GET',
      timeout: 10000,
    }).done(function (result) {
      resultData = result;
      if(callback){ callback(result); }
      consoleLog.debug(requestUrl + " success");
    }).error(function (msg) {
      consoleLog.debug(requestUrl + " error");
      if(errorCallback){ errorCallback(msg.responseText); }
    }).always(function () {
      consoleLog.debug(requestUrl + " complete");
    });
    return resultData;
  },
  "post": function (requestUrl, jsonData, callback, errorCallback) {
    var resultData;
    $.ajax({
      url: requestUrl,
      async: false,
      data : jsonData,
      method: 'POST',
      timeout: 10000,
    }).done(function (result) {
      resultData = result;
      if(callback){ callback(result); }
      consoleLog.debug(requestUrl + " success");
    }).error(function (msg) {
      consoleLog.info(requestUrl + " error ");
      consoleLog.info(msg);
      if(errorCallback){ errorCallback(msg.responseText); }
    }).always(function (msg) {
      consoleLog.debug(requestUrl + " complete");
      consoleLog.debug(msg);
    });
    return resultData;
  },
  "jsonPost": function (requestUrl, jsonData, callback, errorCallback) {
    var resultData;
    $.ajax({
      url: requestUrl,
      async: false,
      contentType: "application/json; charset=utf-8",
      data : jsonData,
      method: "POST",
      dataType: "json",
      timeout: 10000,
    }).done(function (result) {
      resultData = result;
      if(callback){ callback(result); }
      consoleLog.debug(requestUrl + " success");
    }).error(function (msg) {
      consoleLog.info(requestUrl + " error ");
      consoleLog.info(msg);
      if(errorCallback){ errorCallback(msg.responseText); }
    }).always(function (msg) {
      consoleLog.debug(requestUrl + " complete");
      consoleLog.debug(msg);
    });
    return resultData;
  }

};

var getGUID = function () {
  function s4() {
    return Math.floor((1 + Math.random()) * 0x10000)
      .toString(16)
      .substring(1);
  }
  return s4() + s4() + '-' + s4() + '-' + s4() + '-' +
    s4() + '-' + s4() + s4() + s4();
};

function _enterkey(selector, runEventHandler){
  $(selector).keypress(function(e){
    if(e.which == 13){
      runEventHandler(e);
    }
  });
}

function _instantDelay(wait) {
  var timer = 0;
  return function (func) {
    clearTimeout(timer);
    var args = slice.call(arguments, 1);
    timer = setTimeout(function () { return func.apply(null, args); }, wait);
  };
}

function _delay(func, wait) {
  var args = slice.call(arguments, 2);
  return setTimeout(function(){ return func.apply(null, args); }, wait);
}

function _clearTimeout(timer){
  if (timer) {
    clearTimeout(timer);
  }
}

function _confirm(msg, _of, _cf){
  _of = _of || function(){};
  _cf = _cf || function(){};
  $.confirm({
      "title": "",
      "content": msg,
      "buttons": {
          "ok": _of,
          "cancel": _cf
      }
  });
}

function eStruct(idName, dataName) {
  var map
  var list = []
  var selectedId
  return {
    set: function (items) {
      map = {};
      list = []
      $.each(items, function () {
        var item = this;
        if (dataName) {
          list.push(item[dataName])
          map[item[idName]] = item[dataName];
        } else {
          list.push(item)
          map[item[idName]] = item;
        }

      });
    },
    get: function (key) {
      return arguments.length === 0 ? map : map[key];
    },
    getArray: function (key) {
      return arguments.length === 0 ? list : list[key];
    },
    select: function (key) {
      if (key) {
        selectedId = key
      } else {
        selectedId = undefined
      }
    },
    selected: function () {
      return selectedId && map ? map[selectedId] : undefined;
    },
    contains: function (key) {
      if (!key) {
        return false;
      }
      if ($.isEmptyObject(map)) {
        return false;
      }
      return map.hasOwnProperty(key);
    }
  };
}
