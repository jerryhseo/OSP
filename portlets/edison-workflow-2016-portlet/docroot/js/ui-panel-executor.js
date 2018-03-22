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
        },"status": {
            "col": 10,
            "panel-type": "status",
            "body": "tpl-menu-panel-load",
            "header":{
                "id": "tpl-menu-panel-search-header",
                "search-input-name": "search",
                "theads": ["Title", "Status", "Submitted Time", "End Time"]
            },
            "form": {}/* ,
            "footer":{
                "id": "tpl-menu-panel-pagination",
                "btns" : [
                    { "name": "Open", "func": openWorkflow },
                    { "name": "Rename", "func": renameWorkflow },
                    { "name": "Duplicate", "func": duplicateWorkflow },
                    { "name": "Delete", "func": deleteWorkflow }
                ]
            } */
        },  "setting": {
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

    $(JQ_PORTLET_BOUNDARY_ID + " .sidbar-run-btn").click(function (e) {
        e.preventDefault();
        var btnType = $(this).attr("data-btn-type");
        console.log(PANEL_DATA.setting.form.workflowInstanceId);
        if(!PANEL_DATA.setting.form.workflowInstanceId){
            toastr["error"]("", var_create_first_message);
            return false;
        }
        if (btnType === "run") { run(); }
        if (btnType === "rerun") { rerun(); }
        if (btnType === "pause") { pause(); }
        if (btnType === "restart") { restart(); }
        if (btnType === "status") { status(this, "status"); }
    });

    function run(){
        // console.log("run");
        var workflowInstanceId = PANEL_DATA.setting.form.workflowInstanceId;
        var workflowInstanceTitle = PANEL_DATA.setting.form.workflowInstanceTitle;
        executor.getWorkflowInstance(workflowInstanceId,
            function (currentWorkflowInstance) {
                if(currentWorkflowInstance.status === WF_STATUS_CODE.CREATED){
                    var ibToken = getIcebreakerAccessToken();
                    saveWorkflowInstance(workflowInstanceId, workflowInstanceTitle,
                        function (workflowInstance) {
                            executor.runWorkflowInstance(workflowInstanceId, ibToken);
                            toastr["success"]("", var_success_run_workflow_message);
                        });
                }else{
                    toastr["error"]("", var_already_run_message);
                }
            }, function () { });
        
    }
    function rerun(){
        if($(".wf-box.reset").length){
            var workflowInstanceId = PANEL_DATA.setting.form.workflowInstanceId;
            executor.reRunWorkflowInstance(workflowInstanceId,
                designer.getWorkflowDefinition(designer.getCurrentJsPlumbInstance()),
                function (workflowInstance) {
                    var ibToken = getIcebreakerAccessToken();
                    executor.runWorkflowInstance(workflowInstance.workflowInstanceId, ibToken);
                    toastr["success"]("", var_success_run_workflow_message);
                });
        }else{
            toastr["error"]("","Reset First.");
        }
        
    }
    function pause(){
        var workflowInstanceId = PANEL_DATA.setting.form.workflowInstanceId;
        executor.getWorkflowInstance(workflowInstanceId,
            function (currentWorkflowInstance) {
                if(currentWorkflowInstance.status === WF_STATUS_CODE.RUNNING){
                    executor.pauseWorkflowInstance(workflowInstanceId, function(workflowInstance){
                        toastr["success"]("", var_pause_success_message);
                    });
                }else{
                    toastr["error"]("", "Workflow is not running.");
                }});
    }
    
    function restart(){
        var workflowInstanceId = PANEL_DATA.setting.form.workflowInstanceId;
        executor.getWorkflowInstance(workflowInstanceId,
            function (currentWorkflowInstance) {
                if(currentWorkflowInstance.status === WF_STATUS_CODE.PAUSED){
                    executor.resumeWorkflowInstance(workflowInstanceId, function(workflowInstance){
                        toastr["success"]("", var_resume_success_message);
                    });
                }else{
                    toastr["error"]("", "Workflow is not paused.");
                }});
    }

    function drawWorkflowStatus(workflowInstanceId){
        executor.getWorkflowInstanceStatus(workflowInstanceId, function(workflowStatus){
            var simulations = workflowStatus.workflow.simulations;
            if (!simulations.length || simulations.length < 1) {
                toastr["error"]("", "No Simulations");
                return false;
            } 
            sortSimulations(simulations);
            var tableSimulations = [];
            for (var i = 0; i < simulations.length; i++) {
                var tableSimulation = {};
                tableSimulation.title = simulations[i].title;
                if (simulations[i].status) {
                    tableSimulation.status = WF_STATUS_CODE_STRING[simulations[i].status]; 
                } else {
                    tableSimulation.status = "Waiting";
                }
                if(simulations[i].jobs[0].submittedTime){
                    tableSimulation.submittedTime = $.format.date(new Date(simulations[i].jobs[0].submittedTime), "yyyy.MM.dd HH:mm:ss")
                }else{
                    tableSimulation.submittedTime = "";
                }
                if(simulations[i].jobs[0].endTime){
                    tableSimulation.endTime = $.format.date(new Date(simulations[i].jobs[0].endTime), "yyyy.MM.dd HH:mm:ss");
                }else{
                    tableSimulation.endTime = "";
                }
                tableSimulations.push(tableSimulation);
            }
            console.log(tableSimulations);
            var tbodyTemplate = '{{#rows}}' +
                '    <tr>' +
                '        <td>{{title}}</td>' +
                '        <td>{{status}}</td>' +
                '        <td>{{submittedTime}}</td>' +
                '        <td>{{endTime}}</td>' +
                '    </tr>' +
                '{{/rows}}';
            tbodyTemplate +=
                '{{^rows}}' +
                '    <tr>' +
                '        <td colspan="4">No Data</td>' +
                '    </tr>' +
                '{{/rows}}';

            if (tableSimulations && tableSimulations.length > 0) {
                $(JQ_PORTLET_BOUNDARY_ID + " .menu-panel tbody.panel-tbody").empty().append(
                    Mustache.render(tbodyTemplate, { "rows": tableSimulations }));
            }else{
                $(JQ_PORTLET_BOUNDARY_ID + " .menu-panel tbody.panel-tbody").empty().append(
                    Mustache.render(tbodyTemplate, { "rows": tableSimulations }));
            }

        });
    }

    function sortSimulations(simulations){
        simulations.sort(function (a, b) {
            if (!a.jobs[0].submittedTime || !b.jobs[0].submittedTime)
                return 1;
            if (a.jobs[0].submittedTime > b.jobs[0].submittedTime)
                return 1;
            if (a.jobs[0].submittedTime < b.jobs[0].submittedTime)
                return -1;
            return 0;
        });
    }

    function status(that, btnType){
        var templateData = PANEL_DATA[btnType];
        templateData.boxtitle = "Simulation Status";
        var workflowInstanceId = PANEL_DATA.setting.form.workflowInstanceId;
        $("#" + namespace + "menu-panel-box").show();
        $("#" + namespace + "menu-panel-box").empty().mustache('tpl-menu-panel-box', templateData);
        $("#" + namespace + "menu-panel-box .box-body").mustache(templateData.body, templateData);
        if (templateData.header) {
            var boxTitleSelecotr = "#" + namespace + "menu-panel-box .box-header.with-border.header-inner > .box-title";
            $(boxTitleSelecotr).replaceWith($.Mustache.render(templateData.header.id, templateData));
            var _delay600 = _instantDelay(600);
            $(boxTitleSelecotr + " > .search-input").keyup(function (e) {
                _delay600(drawWorkflowStatus, btnType);
            });
        }

        drawWorkflowStatus(workflowInstanceId);

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

        if($(that).parent("li").hasClass("menu-open")){
            $(".menu-panel").toggle('slide', { direction: 'left' }, 500);
            $(JQ_PORTLET_BOUNDARY_ID + " .sidebar-menu > li").removeClass("menu-open");
        }else{
            $(that).parent("li").addClass("menu-open")
            $(".menu-panel").show('slide', { direction: 'left' }, 500);
        }
        
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

        if(btnType === "save" ){
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
            if(!_isEmpty(PANEL_DATA.setting.form.workflowInstanceId)){
                silentSave();
            }
            drawWorkflowInstances({});
        }
    }

    function silentSave(){
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
            });
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

    $(document).bind('keydown.uiPanel',function (event) {
        if ((event.which == 115 || event.which == 83) && 
            (event.ctrlKey || event.metaKey) || (event.which == 19)) {
            event.preventDefault();
            if(PANEL_DATA.setting.form.workflowInstanceTitle){
                saveOrUpdateWorkflowInstance("setting");
            }else{
                toastr["error"]("", var_create_first_message);
            }
            return false;
        }
        return true;
    });

    return {
        "openWorkflow": openWorkflowByWorkflowId,
        "isEmpty": function(){
            return _isEmpty(PANEL_DATA.setting.form.workflowId && PANEL_DATA.setting.form.workflowInstanceId);
        }
    };
});


