var UIPanelExecutor = (function (namespace, $, designer, toastr) {
    /*jshint -W069 */
    'use strict';
    var JQ_PORTLET_BOUNDARY_ID = "#p_p_id" + namespace;
    var PANEL_DATA = {
        "new": {
            "col": 6,
            "panel-type": "new",
            "body": "tpl-menu-panel-new",
            "form": {},
            "btn": {
                "create": newSimulation
            }
        }, "open": {
            "col": 4,
            "panel-type": "open",
            "header":{
                "id": "tpl-menu-panel-search-header",
                "search-input-name": "search"
            } 
        }, "setting": {
            "col": 6,
            "panel-type": "setting",
            "body": "tpl-menu-panel-setting",
            "form": {},
            "btn": {
                "update": saveOrUpdateWorkflowInstance,
                "delete": deleteWorkflowInstance
            }
        }
    };

    $(JQ_PORTLET_BOUNDARY_ID + " .sidebar-btn").click(function (e) {
        e.preventDefault();
        var btnType = $(this).attr("data-btn-type");
        var templateData = PANEL_DATA[btnType];
        console.log("btnType", btnType);
        console.log("templateData", templateData);
        
        if(btnType === "designer"){
            var fn = window[namespace + "moveToDesigner"];
            fn.apply();
        }

        if (templateData) {
            activateLi(this);
            templateData.boxtitle = $(this).text();
            if(btnType === "new"){
            }
            $("#" + namespace + "menu-panel-box").show();
            $("#" + namespace + "menu-panel-box").empty().mustache('tpl-menu-panel-box', templateData);
            $("#" + namespace + "menu-panel-box .box-body").mustache(templateData.body, templateData);

            $(".menu-panel .menu-panel-close").click(function (e) {
                e.preventDefault();
                closePanel();
            });

            $("#" + namespace + "menu-panel-box .data-binded").change(function (e) {
                var thisValue = $(this).val();
                var thisName = $(this).attr("name");
                templateData.form[thisName] = thisValue;
            });

            if($(this).parent("li").hasClass("menu-open")){
                $(".menu-panel").toggle('slide', { direction: 'left' }, 500);
                $(JQ_PORTLET_BOUNDARY_ID + " .sidebar-menu > li").removeClass("menu-open");
            }else{
                $(".menu-panel").show('slide', { direction: 'left' }, 500);
            }
        }
    });

    function newSimulation(){

    }

    function saveOrUpdateWorkflowInstance(){

    }

    function deleteWorkflowInstance(){

    }

    function openWorkflowByWorkflowId(workflowId){
        designer.loadWorkflowDefinition(workflowId, function(workflow){
            setMetaData({
                "title": workflow.title,
                "description": workflow.description,
                "workflowId": workflowId
            });
            closePanel();
        });
    }

    function setMetaData(metadata){
        setTitle(metadata.title);
        PANEL_DATA.setting.form = $.extend({}, metadata);
    }

    function setTitle(titleText) {
        titleText = titleText || "";
        $("#" + namespace + "workflow-title").text(titleText);
    }

    function isValidate() {
        $("#" + namespace + "menu-panel-box form").validator('validate');
        return $("#" + namespace + "menu-panel-box form").find(".has-error").length === 0;
    }

    function closePanel() {
        $(".menu-panel").hide('slide', { direction: 'left' }, 500);
        $(JQ_PORTLET_BOUNDARY_ID + " .sidebar > .sidebar-menu > li.active").removeClass("active");
    }

    function activateLi(jqLink) {
        $(JQ_PORTLET_BOUNDARY_ID + " .sidebar > .sidebar-menu > li.active").removeClass("active");
        $(jqLink).parent("li").addClass("active");
    }

    return {
        "openWorkflow": openWorkflowByWorkflowId
    };
});

