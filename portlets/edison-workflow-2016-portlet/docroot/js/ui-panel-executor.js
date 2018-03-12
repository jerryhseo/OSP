var UIPanelExecutor = (function (namespace, $, designer, executor, toastr) {
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
            },
            "form": {}
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

    /*
    function run(){}
    function rerun(){}
    function pause(){}
    function restart(){}
    function status(){}
     */

    $(JQ_PORTLET_BOUNDARY_ID + " .sidbar-run-btn").click(function (e) {
        e.preventDefault();
        var btnType = $(this).attr("data-btn-type");
        if (btnType === "run") { run(); }
        if (btnType === "rerun") { rerun(); }
        if (btnType === "pause") { pause(); }
        if (btnType === "restart") { restart(); }
        if (btnType === "status") { status(); }
    });

    function run(){
        // console.log("run");
        var ibToken = getIcebreakerAccessToken();
        var workflowInstanceId = PANEL_DATA.setting.form.workflowInstanceId;
        var workflowInstanceTitle = PANEL_DATA.setting.form.workflowInstanceTitle;
        saveWorkflowInstance(workflowInstanceId, workflowInstanceTitle,
            function (workflowInstance) {
                // console.log(ibToken);
                executor.runWorkflowInstance(workflowInstanceId, ibToken);
                toastr["success"]("", var_success_run_workflow_message);
            });
    }
    function rerun(){
        
    }
    function pause(){
        
    }
    function restart(){
        
    }
    function status(){
        
    }

    function getIcebreakerAccessToken(){
        var fn = window[namespace + "getIcebreakerAccessToken"];
        return fn.apply();
    }

    $(JQ_PORTLET_BOUNDARY_ID + " .sidebar-btn").click(function (e) {
        e.preventDefault();
        var btnType = $(this).attr("data-btn-type");
        var templateData = PANEL_DATA[btnType];
        
        if(btnType === "designer"){
            var fn = window[namespace + "moveToDesigner"];
            fn.apply();
        }

        if(btnType === "save"){
            if(PANEL_DATA.setting.form.workflowInstanceTitle){
                saveOrUpdateWorkflowInstance("setting");
            }else{
                toastr["error"]("", var_create_first_message);
            }
        }

        if (templateData) {
            activateLi(this);
            templateData.boxtitle = $(this).text();
            if(btnType === "new"){
            }
            $("#" + namespace + "menu-panel-box").show();
            $("#" + namespace + "menu-panel-box").empty().mustache('tpl-menu-panel-box', templateData);
            $("#" + namespace + "menu-panel-box .box-body").mustache(templateData.body, templateData);
            if(templateData.header){
                var boxTitleSelecotr = "#" + namespace + "menu-panel-box .box-header.with-border.header-inner > .box-title";
                $(boxTitleSelecotr).replaceWith($.Mustache.render(templateData.header.id, templateData));
                var _delay600 = _instantDelay(600);
                $(boxTitleSelecotr + " > .search-input").keyup(function(e){
                    _delay600(loadWorkflowInstances, btnType);
                });
            }

            loadWorkflowInstances(btnType);

            $(".menu-panel .menu-panel-close").click(function (e) {
                e.preventDefault();
                closePanel();
            });

            $("#" + namespace + "menu-panel-box .data-binded").change(function (e) {
                var thisValue = $(this).val();
                var thisName = $(this).attr("name");
                templateData.form[thisName] = thisValue;
            });

            $("#" + namespace + "menu-panel-box .func").each(function (_) {
                var thisName = $(this).attr("name");
                if (templateData.btn && templateData.btn[thisName]) {
                    $(this).click(function (e) {
                        templateData.btn[thisName](btnType, this, e);
                    });
                }
            });

            if($(this).parent("li").hasClass("menu-open")){
                $(".menu-panel").toggle('slide', { direction: 'left' }, 500);
                $(JQ_PORTLET_BOUNDARY_ID + " .sidebar-menu > li").removeClass("menu-open");
            }else{
                $(".menu-panel").show('slide', { direction: 'left' }, 500);
            }
        }
    });

    function initJstree(instanceTreeSelector, instancesData){
        $(instanceTreeSelector).jstree({
            "core" : {
                "check_callback" : true,
                "data" : instancesData,
                "themes" : {
                    "name" : "proton",
                    "responsive" : true
                }
            },
            "types": {
                "root": {},
                "workflow": {},
                "instance": {
                    "icon": "icon-file"
                }
            },
            "progressive_render" : true,
            "plugins" : ["types", "search"]
        }).bind("loaded.jstree", function(event, data){
            $(instanceTreeSelector).jstree(true).open_all();
            var _delay600 = _instantDelay(600);
            var boxTitleSelecotr = "#" + namespace + 
                "menu-panel-box .box-header.with-border.header-inner > .box-title";
            var selectedWorkflowInstanceId = PANEL_DATA.setting.form.workflowInstanceId;
            $(boxTitleSelecotr + " > .search-input").keyup(function (e) {
                var searchString = $(this).val();
                _delay600(function(){
                    $(instanceTreeSelector).jstree(true).search(searchString);
                    if(!_isEmpty(selectedWorkflowInstanceId)){
                        $(instanceTreeSelector + " .jstree-node#" + selectedWorkflowInstanceId).
                            addClass("selected-jstree");
                    }
                });
            });
        }).bind("load_node.jstree", function(event, data){
        }).bind("select_node.jstree", function(event, data){
            var nodeId = data.node.id;
            var node = data.node;
            if(node.type === "workflow"){
              if(!$("#" + nodeId).hasClass("jstree-open")){
                  openJstreeNode(instanceTreeSelector, nodeId, node);
              }else{
                $(instanceTreeSelector).jstree("close_node", node);
              }
            }else if(node.type === "instance"){
                var workflowInstanceId = node.data.workflowInstanceId;
                loadInstance(workflowInstanceId);
                //displayJob(nodeId);
            }
        }).bind("hover_node.jstree", function(event, data){
        });
    }

    function loadInstance(workflowInstanceId) {
        executor.loadWorkflowInstance(workflowInstanceId,
            function (workflowInstance) {
                designer.resetWorkflow();
                designer.drawScreenLogic(workflowInstance.screenLogic);
                setMetaData({
                    "title": PANEL_DATA.setting.form.title,
                    "description": PANEL_DATA.setting.form.description,
                    "workflowId": PANEL_DATA.setting.form.workflowId,
                    "workflowInstanceTitle": workflowInstance.title,
                    "workflowInstanceId": workflowInstance.workflowInstanceId
                });
            }, function (err) {
                if(console){
                    console.log(err);
                }
            });
    }

    function openJstreeNode(instanceTreeSelector, nodeId, node){
        if($("#" + nodeId).hasClass("is-loaded")){
            $(instanceTreeSelector).jstree("open_node", node);
        }
    }


    function loadWorkflowInstances(panelType, currentPage){
        if(panelType === 'open' || panelType === 'import'){
            drawWorkflowInstances({});
        }
    }

    function drawWorkflowInstances(params) {
        var workflowId = getMetaData().workflowId;
        var instanceTreeSelector = "#" + namespace + "menu-panel-box .open .box-body";
        aSyncAjaxHelper.post("/delegate/services/workflows/" + workflowId + "/instances",
            params,
            function (workflowInstances) {
                if($(instanceTreeSelector).hasClass("jstree")){
                    $(instanceTreeSelector).jstree(true).settings.core.data = workflowInstances;
                    $(instanceTreeSelector).jstree(true).refresh();
                }else{
                    initJstree(instanceTreeSelector, workflowInstances);
                }
            },
            function (msg) {
                toastr["error"]("", msg);
            });
    }

    function newSimulation(panelDataType){
        if (isValidate()) {
            var _f = function(){
                executor.createWorkfowInstance(PANEL_DATA.setting.form.workflowId, 
                    PANEL_DATA[panelDataType].form.workflowInstanceTitle, function(workflowInstance){
                        if(panelDataType === "new"){
                            PANEL_DATA[panelDataType].form.workflowInstanceTitle = "";
                            designer.resetWorkflow();
                            openWorkflowByWorkflowId(PANEL_DATA.setting.form.workflowId, true);
                        }
                        setMetaData({
                            "title": PANEL_DATA.setting.form.title,
                            "description": PANEL_DATA.setting.form.description,
                            "workflowId": PANEL_DATA.setting.form.workflowId,
                            "workflowInstanceTitle": workflowInstance.title,
                            "workflowInstanceId": workflowInstance.workflowInstanceId
                        });
                        toastr["success"]("", var_create_success_message);
                    });
                closePanel();
            };
            if (PANEL_DATA.setting.form.workflowInstanceId){
                _confirm(var_new_workflow_confirm_message, _f, closePanel);
            }else{
                _f();
            }
        }
    }

    function saveOrUpdateWorkflowInstance(panelDataType) {
        if (isValidate()) {
            if (_isEmpty(PANEL_DATA.setting.form.workflowInstanceId)) {
                newSimulation(panelDataType);
            } else {
                var workflowInstanceId = PANEL_DATA.setting.form.workflowInstanceId;
                var workflowInstanceTitle = PANEL_DATA.setting.form.workflowInstanceTitle;
                saveWorkflowInstance(workflowInstanceId, workflowInstanceTitle,
                    function (workflowInstance) {
                        setMetaData({
                            "title": PANEL_DATA.setting.form.title,
                            "description": PANEL_DATA.setting.form.description,
                            "workflowId": PANEL_DATA.setting.form.workflowId,
                            "workflowInstanceTitle": workflowInstance.title,
                            "workflowInstanceId": workflowInstance.workflowInstanceId
                        });
                        toastr["success"]("", var_save_success_message);
                        closePanel();
                    });
            }
        }
    }

    function saveWorkflowInstance(workflowInstanceId, workflowInstanceTitle, callback) {
        executor.updateWorkflowInstance(workflowInstanceId, workflowInstanceTitle,
            designer.getWorkflowDefinition(designer.getCurrentJsPlumbInstance()), callback);
    }
    
    
    function deleteWorkflowInstance(panelDataType){
        if (!_isEmpty(PANEL_DATA.setting.form.workflowInstanceId, var_no_workflow_instance_msg)) {
            _confirm(var_remove_workflow_confirm_message, function () {
                executor.deleteWorkflowInstance(PANEL_DATA.setting.form.workflowInstanceId,
                    function (reponseStatus) {
                        toastr["success"]("", var_success_remove_workflow_message);
                        var workflowId = PANEL_DATA.setting.form.workflowId;
                        resetWorkflowInstance();
                        openWorkflowByWorkflowId(workflowId);
                    });
                closePanel();
            }, closePanel);
        }
    }

    function resetWorkflowInstance(){
        setMetaData({});
        setTitle();
    }

    function openWorkflowByWorkflowId(workflowId, isNotNew){
        designer.loadWorkflowDefinition(workflowId, function(workflow){
            if(!isNotNew){
                setMetaData({
                    "title": workflow.title,
                    "description": workflow.description,
                    "workflowId": workflowId
                });
                closePanel();
            }
        });
    }

    function getMetaData(){
        return PANEL_DATA.setting.form;
    }

    function setMetaData(metadata){
        setTitle(metadata.title, metadata.workflowInstanceTitle);
        PANEL_DATA.setting.form = $.extend({}, metadata);
    }

    function setTitle(titleText, workflowInstanceTitle) {
        titleText = titleText || "";
        workflowInstanceTitle = workflowInstanceTitle || "";
        $("#" + namespace + "workflow-title").text(titleText);
        $("#" + namespace + "workflow-sub-title").text(workflowInstanceTitle);
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

    function getValueByInputName(inputName){
        return $("input[name='" + inputName + "']").val();
    }

    function _isEmpty(value, msg){
        if(!value){
            if(msg){
                toastr["info"]("", msg);
            }
            return true;
        }
        return false;
    }

    return {
        "openWorkflow": openWorkflowByWorkflowId
    };
});


