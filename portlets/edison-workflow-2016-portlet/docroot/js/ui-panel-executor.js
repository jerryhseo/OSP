var UIPanelExecutor = (function (namespace, $, designer, toastr) {
    /*jshint -W069 */
    'use strict';
    var JQ_PORTLET_BOUNDARY_ID = "#p_p_id" + namespace;

    $(JQ_PORTLET_BOUNDARY_ID + " .sidebar-btn").click(function (e) {
        e.preventDefault();
        var btnType = $(this).attr("data-btn-type");
        if(btnType === "designer"){
            var fn = window[namespace + "moveToDesigner"];
            fn.apply();
        }
    });

});

