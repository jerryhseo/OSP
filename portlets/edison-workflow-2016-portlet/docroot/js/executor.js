var SimulationExecutor = (function (namespace, $, designer, toastr, windowState) {
    'use strict';
    var WF_STATUS_CODE = {
        CREATED: "CREATED",
        RUNNING: "RUNNING",
        PAUSED: "PAUSED",
        COMPLETED: "SUCCESS",
        DONE: "DONE",
        FAILED: "FAILED",
        CANCELED: "CANCELED",
        NOT_FOUND: "NOT_FOUND"
    };
    var WF_STATUS_CODE_STRING = {
        CREATED: "Created",
        RUNNING: "Running",
        PAUSED: "Paused",
        COMPLETED: "Completed",
        SUCCESS: "Success",
        DONE: "Done",
        FAILED: "Failed",
        CANCELED: "Canceled"
    };

    var WF_JSPLUMB_TYPES = {
        ENDPOINT: "endpoint",
        INPUT: "input",
        OUTPUT: "output",
        LOOP: "loop"
    };

    var WF_APP_TYPES = {
        DYNAMIC_CONVERTER: {
            NAME: "DynamicConverter",
            INPUT_DATA_TYPE: "converter_input",
            OUTPUT_DATA_TYPE: "converter_stdout",
            INPUT_SCOPE: "converter_stdout_",
            OUTPUT_SCOPE: "converter_input_",
        },CONTROLLER: {
            NAME: "Controller",
            INPUT_DATA_TYPE: "controller_input",
            OUTPUT_DATA_TYPE: "controller_stdout",
            INPUT_SCOPE: "controller_stdout_",
            OUTPUT_SCOPE: "controller_input_",
        },
        STATIC_CONVERTER: {
            NAME: "Converter"
        },
        APP: {
            NAME: "Solver"
        }
    };
    var DESIGNER_PORTLET_ID = namespace.slice(1, -1);
    var STATUS_TIMER;
    var WF_PORTLET_GLOBAL_DATA = designer.getWfPortletGlobalData();

    var currentJsPlumbInstance = designer.getCurrentJsPlumbInstance();

    function loadWorkflowInstance(workflowInstanceId, callback, errorCallback){
        aSyncAjaxHelper.get("/delegate/services/workflows/instance/" + workflowInstanceId, 
        function(workflowInstance){
            callback(workflowInstance);
            if(workflowInstance.workflowUUID){
                updateStatus(workflowInstanceId);
            }
        }, errorCallback);
    }

    function createWorkfowInstance(workflowId, workflowInstanceTitle, callback){
        aSyncAjaxHelper.post("/delegate/services/workflows/instance/create", {
            workflowId: workflowId,
            workflowInstanceTitle: workflowInstanceTitle
        }, function(workflowInstance){
            console.log(workflowInstance);
            if(callback){
                callback(workflowInstance);
            }
        }, function(){});
    }

    function deleteWorkflowInstance(workflowInstanceId, callback){
        aSyncAjaxHelper.post("/delegate/services/workflows/instance/" + workflowInstanceId + "/delete",
            {},
            function (workflowInstance) {
                if (callback) {
                    callback(workflowInstance);
                }
            }, function () { });
    }

    function updateWorkflowInstance(workflowInstanceId, workflowInstanceTitle, screenLogic, callback){
        var workflowInstanceData = {
            workflowInstanceId: workflowInstanceId,
            workflowInstanceTitle: workflowInstanceTitle
        };
        if(screenLogic){
            workflowInstanceData.screenLogic = JSON.stringify(screenLogic) ;
        }
        aSyncAjaxHelper.post("/delegate/services/workflows/instance/" + workflowInstanceId + "/update",
            workflowInstanceData,
            function (workflowInstance) {
                if (callback) {
                    callback(workflowInstance);
                }
            }, function () { });
    }

    function runWorkflowInstance(workflowInstanceId, icebreakerAccessToken){
        aSyncAjaxHelper.post(
            "/delegate/services/workflows/instance/" + workflowInstanceId + "/run", icebreakerAccessToken,
            function (status) {
                if (status) {
                    console.log(status);
                    drawWorkflowInstanceStatus(status);
                    updateStatus(workflowInstanceId);
                }
            });
    }

    function getWorkflowInstanceStatus(workflowInstanceId, callback) {
        aSyncAjaxHelper.get("/delegate/services/workflows/instance/" + workflowInstanceId + "/status",
            function (workflowStatus) {
                if (callback) {
                    callback(workflowStatus);
                }
            });
    }

    function updateStatus(workflowInstanceId) {
        getWorkflowInstanceStatus(workflowInstanceId, function(workflowStatus){
            console.log(workflowStatus);
            if (workflowStatus.workflow.status === WF_STATUS_CODE.NOT_FOUND) {
                toastr["error"]("", var_workflow_status_not_found_message + workflowStatus.workflow.uuid);
            } else {
                drawWorkflowInstanceStatus(workflowStatus);
                // TODO : button Controll
                if (workflowStatus.workflow.status === WF_STATUS_CODE.COMPLETED) {

                } else if (workflowStatus.workflow.status === WF_STATUS_CODE.PAUSED) {

                } else if (workflowStatus.workflow.status === WF_STATUS_CODE.RUNNING ||
                    workflowStatus.workflow.status === WF_STATUS_CODE.CREATED) {
                    _clearTimeout(STATUS_TIMER);
                    STATUS_TIMER = _delay(updateStatus, 3000, workflowInstanceId);
                }
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

    function drawWorkflowInstanceStatus(workflowStatus) {
        var simulations = workflowStatus.workflow.simulations;
        var currentCanvasId = "#" + designer.getCurrentJsPlumbContainerId();
        if (!simulations.length || simulations.length < 1) {
            toastr["error"]("", "No Simulations");
            return false;
        } else {
            sortSimulations(simulations);
        }
        
        for (var i = 0; i < simulations.length; i++) {
            var clientId = currentCanvasId + " #" + simulations[i].clientId;
            var appData = $(clientId).data();
            appData["workflowStatus"] = simulations[i];
            appData["workflowStatus"]["workflowUuid"] = workflowStatus["workflow"]["uuid"];
            if (workflowStatus["workflow"]["userId"]) {
                appData["workflowStatus"]["userId"] = workflowStatus["workflow"]["userId"];
            }
            $(clientId).removeClass("waitingbox runningbox pausebox failbox donebox");
            drawWfBoxStatus(simulations[i].status, clientId);
        }
    }

    function drawWfBoxStatus(status, wfBoxSelector) {
        if (status === WF_STATUS_CODE.CREATED) {
            $(wfBoxSelector).addClass("waitingbox");
            $(wfBoxSelector + " .wf-app-status").text("Waiting");
        } else if (status === WF_STATUS_CODE.RUNNING) {
            $(wfBoxSelector).addClass("runningbox");
            $(wfBoxSelector + " .wf-app-status").html("Running");
        } else if (status === WF_STATUS_CODE.PAUSED) {
            $(wfBoxSelector).addClass("pausebox");
            $(wfBoxSelector + " .wf-app-status").html("Paused");
        } else if (status === WF_STATUS_CODE.CANCELED) {
            $(wfBoxSelector).addClass("failbox");
            $(wfBoxSelector + " .wf-app-status").text("Canceled");
        } else if (status === WF_STATUS_CODE.FAILED) {
            $(wfBoxSelector).addClass("failbox");
            $(wfBoxSelector + " .wf-app-status").text("Failed");
        } else if (status === WF_STATUS_CODE.COMPLETED
            || status === WF_STATUS_CODE.DONE) {
            $(wfBoxSelector).addClass("donebox");
            $(wfBoxSelector + " .wf-app-status").text("Done");
        } else {
            $(wfBoxSelector).addClass("waitingbox");
            $(wfBoxSelector + " .wf-app-status").text("Waiting");
        }
    }

    function log(simulations) {
        var $logTbody = $("#running-workflow-log-tbody");
        $logTbody.children().remove();
        for (var i = 0; i < simulations.length; i++) {
            var $tr = $("<tr>").addClass("bgcolor");
            $tr.append($("<td>").addClass("pdleft20").text(simulations[i].title));
            if (simulations[i].status) {
                $tr.append($("<td>").addClass("TC").text(WF_STATUS_CODE_STRING[simulations[i].status]));
            } else {
                $tr.append($("<td>").addClass("TC").text("Waiting"));
            }
            $tr.append($("<td>").addClass("TC").text(
                simulations[i].jobs[0].submittedTime
                    ? $.format.date(new Date(simulations[i].jobs[0].submittedTime), "yyyy.MM.dd HH:mm:ss")
                    : ''));
            $tr.append($("<td>").addClass("TC").text(
                simulations[i].jobs[0].endTime
                    ? $.format.date(new Date(simulations[i].jobs[0].endTime), "yyyy.MM.dd HH:mm:ss")
                    : ''));
            //$tr.append($("<td>").addClass("TC").text(""));
            $logTbody.append($tr);
        }
    }

    
    /*********************************************************************************************** TODO : move to executor */

     /** context menu **/
     $.contextMenu({
        selector: '.jsplumb-endpoint.input-port',
        build: function ($trigger, e) {
            var items = {};
            var sciApp = $($trigger[0]._jsPlumbRelatedElement).data();
            var port = $trigger[0]._jsPlumb.getParameter("data");
            var jsPlumbWindowId = $($($trigger[0]._jsPlumbRelatedElement)[0]).attr("id");
            // appType: "DynamicConverter"
            var editors;
            if (sciApp.appType === WF_APP_TYPES.DYNAMIC_CONVERTER.NAME) {
                editors = [
                    {
                        appType: "Editor",
                        editorType: "File",
                        exeFileName: "FileExplorer_WAR_OSPEditorsportlet",
                        name: "FILE_SELECTOR"
                    }, {
                        appType: "Editor",
                        editorType: "Text",
                        exeFileName: "TextEditor_WAR_OSPEditorsportlet",
                        name: "TEXT_EDITOR"
                    }
                ];
            } else {
                editors = synchronousAjaxHelper.post("/delegate/services/app/inputports/editor",
                    {
                        "name": port.dataType().name,
                        "version": port.dataType().version
                    });
            }
            //console.log(editors);
            $.each(editors, function (_) {
                var editor = this;
                items[editor["name"]] = {
                    name: editor["name"],
                    icon: "edit",
                    callback: function (key, options) {
                        // TODO : popEditorWindow(editor, port, jsPlumbWindowId);
                        popEditorWindow(editor, port, sciApp, jsPlumbWindowId);
                    }
                };
            });
            return { items: items };
        }
    });

    function popEditorWindow(editor, port, sciApp, jsPlumbWindowId){
        var portData = "";
        var portName = port.name();
        var editorType = editor["editorType"];
        var portletId = editor["exeFileName"];
        var saveBtnHandler = function(){
            window.AUI().use(function (A) {
                fire(OSP.Event.OSP_REQUEST_DATA,
                    editor.exeFileName, {}, {
                        jsPlumbWindowId: jsPlumbWindowId,
                        portName: portName,
                        editorType: editorType
                    });
            });
            $("#" + namespace + "wf-modal").modal("hide");
        };
        var inputData = "";
        var srcData = new OSP.InputData();
        if (editorType == "Inputdeck") {
            if (WF_PORTLET_GLOBAL_DATA["wfElements"][jsPlumbWindowId]
                && WF_PORTLET_GLOBAL_DATA["wfElements"][jsPlumbWindowId][portName]
                && WF_PORTLET_GLOBAL_DATA["wfElements"][jsPlumbWindowId][portName]["editorType"] === "Inputdeck") {
                portData = WF_PORTLET_GLOBAL_DATA["wfElements"][jsPlumbWindowId][portName]["input-value"];
            } else {
                portData = editor["structure"];
            }
            srcData.type(OSP.Enumeration.PathType.STRUCTURED_DATA);
            srcData.context(JSON.parse(portData));
        } else if (editorType == "Text") {
            if (WF_PORTLET_GLOBAL_DATA["wfElements"][jsPlumbWindowId]
                && WF_PORTLET_GLOBAL_DATA["wfElements"][jsPlumbWindowId][portName]) {
                portData = WF_PORTLET_GLOBAL_DATA["wfElements"][jsPlumbWindowId][portName]["file-content"];
            } else {
                portData = "";
            }
            srcData.type(OSP.Enumeration.PathType.CONTEXT);
            srcData.context(Liferay.Util.escapeHTML(portData));
            inputData = JSON.stringify(srcData);
        } else if (editorType == "File") {
            srcData.setPath('', '', '', OSP.Constants.FOLDER, true);
            srcData.repositoryType("USER_HOME");
            inputData = JSON.stringify(srcData);
        }
        console.log("srcData ", srcData);
        console.log("toJSON srcData ", OSP.Util.toJSON(srcData));
        showEditorWindow(editor, srcData, saveBtnHandler);
    }

    function showEditorWindow(editor, inputData, saveBtnHandler){
        window.AUI().use('liferay-portlet-url', function (A) {
            var portletURL = window.Liferay.PortletURL.createRenderURL();
            portletURL.setPortletId(editor.exeFileName);
            portletURL.setParameter('eventEnable', false);
            portletURL.setParameter('connector', DESIGNER_PORTLET_ID);
            if(windowState){
                portletURL.setWindowState(windowState);
            }
            console.log(portletURL.toString());
            $.ajax({
                url: portletURL.toString(),
                type: 'POST',
                dataType: 'text',
                success: function (renderResult) {
                    drawModal(editor.name, renderResult,
                        { "ok": "Save", "cancel": "Cancel" },
                        function () {
                            fire(OSP.Event.OSP_LOAD_DATA, editor.exeFileName, OSP.Util.toJSON(inputData));
                        }, saveBtnHandler);
                },
                error: function () {
                    console.log('AJAX loading failed');
                }
            });
        });
    }

    function drawModal(title, body, footer, callback, footerCallback){
        var modal = $("#" + namespace + "wf-modal");
        modal.find(".modal-title").text(title);
        if(body){
            modal.find(".modal-body").empty().html(body).
                promise().done(function () {
                    callback();
                    modal.modal({ "backdrop": "static", "keyboard": false });
                });
        }
        if(footer){
            modal.find(".modal-footer").empty().append($.Mustache.render("tpl-modal-footer", footer));
            modal.find("button[name='Save']").click(footerCallback);
        }
    }

    function fire(event, targetPortletId, data, params) {
        var eventData = {
            portletId: DESIGNER_PORTLET_ID,
            targetPortlet: targetPortletId,
            data: data
        };
        if(params){
            eventData.params = params;
        }
        Liferay.fire(event, eventData);
    }

    Liferay.on(OSP.Event.OSP_RESPONSE_DATA, function (eventData) {
        if (eventData.targetPortlet === DESIGNER_PORTLET_ID) {
            console.log("OSP_RESPONSE_DATA ", eventData);
            var workflowInfo = eventData.params;
            var editorType = workflowInfo["editorType"];
            var editorData = JSON.stringify(eventData.data);
            var fileContent = "";

            if (WF_PORTLET_GLOBAL_DATA["wfElements"][workflowInfo["jsPlumbWindowId"]]) {
                if (!WF_PORTLET_GLOBAL_DATA["wfElements"][workflowInfo["jsPlumbWindowId"]][workflowInfo["portName"]]) {
                    WF_PORTLET_GLOBAL_DATA["wfElements"][workflowInfo["jsPlumbWindowId"]][workflowInfo["portName"]] = {};
                }
                WF_PORTLET_GLOBAL_DATA["wfElements"][workflowInfo["jsPlumbWindowId"]][workflowInfo["portName"]]["input-value"] = editorData;
                WF_PORTLET_GLOBAL_DATA["wfElements"][workflowInfo["jsPlumbWindowId"]][workflowInfo["portName"]]["editorType"] = workflowInfo["editorType"];
            } else {
                var portJson = {};
                portJson[workflowInfo["portName"]] = {};
                portJson[workflowInfo["portName"]]["input-value"] = editorData;
                portJson[workflowInfo["portName"]]["editorType"] = workflowInfo["editorType"];
                WF_PORTLET_GLOBAL_DATA["wfElements"][workflowInfo["jsPlumbWindowId"]] = portJson;
            }

            if (editorType == "Inputdeck") {
                var inputData = new OSP.InputData(eventData.data);
                var dataType = new OSP.DataType();
                var dataStructure = dataType.deserializeStructure(inputData.context());
                fileContent = dataType.structure().activeParameterFormattedInputs().join("");
            } else if (editorType == "Text") {
                fileContent = editorData;
            } else if (editorType == "File") {
                var portInfo = WF_PORTLET_GLOBAL_DATA["wfElements"][workflowInfo["jsPlumbWindowId"]][workflowInfo["portName"]];
                var inputData = new OSP.InputData(eventData.data);
                portInfo["fileName"] = inputData.name();
                portInfo["parentPath"] = inputData.parent();
                portInfo["pathType"] = inputData.type();
            }
            WF_PORTLET_GLOBAL_DATA["wfElements"][workflowInfo["jsPlumbWindowId"]][workflowInfo["portName"]]["file-content"] = fileContent;
        }
        console.log("WF_PORTLET_GLOBAL_DATA : ", WF_PORTLET_GLOBAL_DATA);
    });

    $.contextMenu({
        selector: '.jsplumb-endpoint.output-port',
        build: function ($trigger, e) {
            if ($($($trigger[0]._jsPlumbRelatedElement)[0]).hasClass("loop-child-box")) {
                alert(var_no_available_analyzer_message);
                return false;
            }
            var items = {};
            var sciApp = $($trigger[0]._jsPlumbRelatedElement).data();
            var port = $trigger[0]._jsPlumb.getParameter("data");
            var jsPlumbWindowId = $($($trigger[0]._jsPlumbRelatedElement)[0]).attr("id");
            var analyzers;
            if (sciApp.appType === WF_APP_TYPES.DYNAMIC_CONVERTER.NAME) {
                alert(var_no_available_analyzer_message);
                return false;
            } else {
                analyzers = synchronousAjaxHelper.post("/delegate/services/app/outputports/analyzer",
                    {
                        "name": port.dataType().name,
                        "version": port.dataType().version
                    });
            }
            $.each(analyzers, function (_) {
                var analyzer = this;
                items[analyzer["name"]] = {
                    name: analyzer["name"],
                    icon: "edit",
                    callback: function (key, options) {
                        popAnalyzerWindow(analyzer, port, jsPlumbWindowId, sciApp);
                    }
                };
            });
            return { items: items };
        }
    });

    function popAnalyzerWindow(analyzer, port, jsPlumbWindowId, sciApp) {
        var portletId = analyzer.exeFileName;
        var callbackFunc = function () {
            var ospPath = port.outputData();
            var inputData = new OSP.InputData();
            var simUuid = sciApp.workflowStatus.jobs[0].ibSimUuid;
            var jobUuid = sciApp.workflowStatus.jobs[0].ibUuid;
            var parentPaths = [simUuid, jobUuid + ".job"];
            inputData.type(OSP.Enumeration.PathType.FILE);
            if(ospPath.parent()){
                parentPaths.push( ospPath.parent());
            }
            inputData.parent(parentPaths.join("/"));
            inputData.name(ospPath.name());
            inputData.repositoryType("USER_JOBS");
            _instantDelay(100)(fire, OSP.Event.OSP_LOAD_DATA, analyzer.exeFileName, inputData);
        };
        showAnalyzerWindow(analyzer, callbackFunc);
    }
    
    function showAnalyzerWindow(analyzer, callbackFunc) {
        window.AUI().use('liferay-portlet-url', function (A) {
            var portletURL = window.Liferay.PortletURL.createRenderURL();
            portletURL.setPortletId(analyzer.exeFileName);
            portletURL.setParameter('eventEnable', false);
            portletURL.setParameter('connector', DESIGNER_PORTLET_ID);
            if (windowState) {
                portletURL.setWindowState(windowState);
            }
            console.log(portletURL.toString());
            $.ajax({
                url: portletURL.toString(),
                type: 'POST',
                dataType: 'text',
                success: function (renderResult) {
                    drawModal(analyzer.name, renderResult, null, callbackFunc);
                },
                error: function () {
                    console.log('AJAX loading failed');
                }
            });
        });
    }


    /*********************************************************************************************** TODO : move to executor */


    /* function updateStatus(workflowStatus){
        if(workflowStatus.workflow.status === WF_STATUS_CODE.NOT_FOUND){
            alert(var_workflow_status_not_found_message + workflowStatus.workflow.uuid);
          }else{
            drawWorkflowInstanceStatus(workflowStatus);
            drawRunningWorkflowsWithPaging(
                $("#running-workflow-paging > ul > li.select").text(),
            "running-workflow");
            if(workflowStatus.workflow.status === WF_STATUS_CODE.COMPLETED){
              currentDoneWorkflowInstanceId = currentWorkflowInstanceId;
              currentWorkflowInstanceId = undefined;
              $("#wf-runing-pause-button").hide();
              $("#wf-runing-resume-button").hide();
            }else if(workflowStatus.workflow.status === WF_STATUS_CODE.PAUSED){
              $("#wf-runing-pause-button").hide();
              $("#wf-runing-resume-button").show();
            }else if(workflowStatus.workflow.status === WF_STATUS_CODE.RUNNING
                ||workflowStatus.workflow.status === WF_STATUS_CODE.CREATED){
              $("#wf-runing-pause-button").show();
              $("#wf-runing-resume-button").hide();
              updateWorkflowInstanceStatus(workflowInstanceId, 3000);
            }
            if(callback){
              callback();
            }
          }
    } */

   /*  function updateWorkflowInstanceDiagram(workflowInstanceId, callback) {
        return synchronousAjaxHelper.get(
            "/delegate/services/workflows/instance/" + workflowInstanceId + "/status",
            function (workflowStatus) {
            });
    } */
    
    /* function runEventHandler(e) {
        e.preventDefault();
        if (runEventHandler.isLoaded != true) {
            if ($("#workflow-instance-name-input").val().length !== 0) {
                runEventHandler.isLoaded = true;
                var localWorkflow = modifyingWorkflow;
                var postData = getIcebreakerAccessToken();
                postData["workflowInstanceTitle"] = $("#workflow-instance-name-input").val();
                postData["workflowLoopStatusCheck"] = $("#workflow-instance-loop-check").is(":checked"); //add Loop - 20161024

                var wfData = $.parseJSON(localWorkflow["screenLogic"]);
                var workflowRquestJson = synchronousAjaxHelper.post(
                    "/delegate/services/workflows/" + localWorkflow["workflowId"] + "/run", postData,
                    function (workflowInstance) {
                        controlTab($("#my-workflow .running-workflow"));
                        $("#running-workflow > .alert").removeClass("alert-success alert-error")
                            .addClass("alert-success").text(var_success_run_workflow_message);
                        $("#running-workflow > .alert")
                            .fadeIn(100, function (_) { $("#running-workflow > .alert").fadeOut(2000); });
                        if (workflowInstance) {
                            loadWorkflowInstance(workflowInstance["workflowInstanceId"], currentJsPlumbInstance);
                        }
                    });
                $("#workflow-instance-dialog").dialog("close");
            } else {
                $("#workflow-instance-name-input").attr("placeholder", var_validation_required_message);
                $("#workflow-instance-name-input").addClass("form-validation-error");
                $("#workflow-instance-name-input").focus();
                $("#workflow-instance-name-input").on("focusout.wfname input.wfname", function () {
                    if ($(this).hasClass("form-validation-error")) {
                        $(this).removeClass("form-validation-error");
                        $(this).removeAttr("placeholder");
                    }
                    $(this).off("focusout.wfname input.wfname");
                });
            }
        }
    } */
    return {
        "createWorkfowInstance": createWorkfowInstance,
        "updateWorkflowInstance": updateWorkflowInstance,
        "deleteWorkflowInstance": deleteWorkflowInstance,
        "runWorkflowInstance": runWorkflowInstance,
        "loadWorkflowInstance": loadWorkflowInstance
    };
});