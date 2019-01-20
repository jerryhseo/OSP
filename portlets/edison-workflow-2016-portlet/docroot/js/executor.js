var SimulationExecutor = (function (namespace, $, designer, toastr) {
    'use strict';

    var URI_PREFIX = '/delegate/services'
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

    /////////////////////////////////////////// renew start

    function createSimulation(params, callback, errorCallback) {
        _clearTimeout(STATUS_TIMER);
        aSyncAjaxHelper.post(URI_PREFIX + "/simulation/create",
            params,
            function (simulation) {
                if (callback) {
                    callback(simulation);
                }
            }, errorCallback
        );
    }

    function updateSimulation(params, callback, errorCallback) {
        _clearTimeout(STATUS_TIMER);
        if(params && params.simulationId) {
            aSyncAjaxHelper.post(URI_PREFIX + "/simulation/" + params.simulationId + "/update",
                params,
                function (simulation) {
                    if (callback) {
                        callback(simulation);
                    }
                }, errorCallback
            );
        }
    }

    function deleteSimulation(simulationId, callback, errorCallback) {
        _clearTimeout(STATUS_TIMER);
        if(simulationId) {
            aSyncAjaxHelper.post(URI_PREFIX + "/simulation/" + simulationId + "/delete",
                {},
                function (simulation) {
                    if (callback) {
                        callback(simulation);
                    }
                }, errorCallback
            );
        }
    }

    function fetchSimulationJobs(simulationId, params, callback, errorCallback){
        _clearTimeout(STATUS_TIMER);
        aSyncAjaxHelper.getWithParams(URI_PREFIX + "/simulation/" + simulationId + "/list",
            params,
            function(simulationJobs){
                callback(simulationJobs);
            }, errorCallback);
    }

    function createSimulationJob(params, callback, errorCallback) {
        _clearTimeout(STATUS_TIMER);
        aSyncAjaxHelper.post(URI_PREFIX + "/simulation/" + params.simulationId + "/job/create",
            params,
            function (simulationJob) {
                if (callback) {
                    callback(simulationJob);
                }
            }, errorCallback
        );
    }

    function updateSimulationJob(params, callback, errorCallback) {
        _clearTimeout(STATUS_TIMER);
        aSyncAjaxHelper.post(
            URI_PREFIX + "/simulation/" + params.simulationId + "/job/" + params.simulationJobId + "/update",
            params,
            function (simulationJob) {
                if (callback) {
                    callback(simulationJob);
                }
            }, errorCallback
        );
    }

    function copySimulationJob(params, callback, errorCallback) {
        _clearTimeout(STATUS_TIMER);
        aSyncAjaxHelper.post(
            URI_PREFIX + "/simulation/" + params.simulationId + "/job/" + params.simulationJobId + "/copy",
            params,
            function (simulationJob) {
                if (callback) {
                    callback(simulationJob);
                }
            }, errorCallback
        );
    }

    function deleteSimulationJob(params, callback, errorCallback) {
        _clearTimeout(STATUS_TIMER);
        aSyncAjaxHelper.post(
            URI_PREFIX + "/simulation/" + params.simulationId + "/job/" + params.simulationJobId + "/delete",
            {},
            function (simulationJob) {
                if (callback) {
                    callback(simulationJob);
                }
            }, errorCallback
        );
    }

    function createSimulationJobEngine(params, callback, errorCallback) {
        _clearTimeout(STATUS_TIMER);
        aSyncAjaxHelper.post(
            URI_PREFIX + "/simulation/" + params.simulationId + "/job/" + params.simulationJobId + "/create/engine",
            params,
            function (simulationJob) {
                if (callback) {
                    callback(simulationJob);
                }
            }, errorCallback
        );
    }

    function fetchSimulationJobSeq(simulationId, callback, errorCallback) {
        _clearTimeout(STATUS_TIMER);
        aSyncAjaxHelper.get(URI_PREFIX + "/simulation/" + simulationId + "/job/seq",
        function (seqMap) {
            callback(seqMap)
        }, errorCallback)
    }

    function getWorkflowStatus(simulationJobId, callback) {
        aSyncAjaxHelper.get(URI_PREFIX + "/simulation/job/" + simulationJobId + "/status",
            function (workflowStatus) {
                if (callback) {
                    callback(workflowStatus);
                }
            });
    }

    function updateStatus(simulationJobId, initStatus, callback) {
        getWorkflowStatus(simulationJobId,
            function (workflowStatus) {
                if (workflowStatus.workflow.status === WF_STATUS_CODE.NOT_FOUND) {
                    toastr["error"]("", var_workflow_status_not_found_message + workflowStatus.workflow.uuid);
                } else {
                    callback(workflowStatus)
                    // TODO : button Control
                    _clearTimeout(STATUS_TIMER);
                    STATUS_TIMER = _delay(updateStatus, 1000, simulationJobId, workflowStatus, callback);
                }
            });
    }

    function pauseSimulationJob(simulationJobId, callback) {
        _clearTimeout(STATUS_TIMER);
        aSyncAjaxHelper.post(
            URI_PREFIX + "/simulation/job/" + simulationJobId + "/pause", {},
            function (workflowStatus) {
                if (callback) {
                    callback(workflowStatus);
                }
            }, function () { });
    }

    function resumeSimulationJob(simulationJobId, callback) {
        _clearTimeout(STATUS_TIMER);
        aSyncAjaxHelper.post(
            URI_PREFIX + "/simulation/job/" + simulationJobId + "/resume", {},
            function (workflowStatus) {
                if (callback) {
                    callback(workflowStatus);
                }
            }, function () { });
    }

    function pauseSingleNode(simulationJobId, uuid, callback) {
        _clearTimeout(STATUS_TIMER);
        aSyncAjaxHelper.post(
            URI_PREFIX + "/simulation/job/" + simulationJobId + "/pause/"+ uuid, {},
            function (workflowStatus) {
                if (callback) {
                    callback(workflowStatus);
                }
            }, function () { });
    }

    function resumeSingleNode(simulationJobId, uuid, callback) {
        _clearTimeout(STATUS_TIMER);
        aSyncAjaxHelper.post(
            URI_PREFIX + "/simulation/job/" + simulationJobId + "/resume/"+ uuid, {},
            function (workflowStatus) {
                if (callback) {
                    callback(workflowStatus);
                }
            }, function () { });
    }

    function rerunSimulationJobEngine(params, callback, errorCallback) {
        _clearTimeout(STATUS_TIMER);
        aSyncAjaxHelper.post(
            URI_PREFIX + "/simulation/" + params.simulationId + "/job/" + params.simulationJobId + "/rerun",
            params,
            function (simulationJob) {
                if (callback) {
                    callback(simulationJob);
                }
            }, errorCallback
        );
    }
    function exportSimulationJob(params, callback, errorCallback) {
        _clearTimeout(STATUS_TIMER);
        aSyncAjaxHelper.post(
            URI_PREFIX + "/simulation/" + params.simulationId + "/job/" + params.simulationJobId + "/export",
            params,
            function (workflowStatus) {
                if (callback) {
                    callback(workflowStatus);
                }
            }, errorCallback
        );
    }

    function insertIbUuid(params, callback, errorCallback) {
        _clearTimeout(STATUS_TIMER);
        ///job/{simulationJobId}/update-ib
        aSyncAjaxHelper.post(
            URI_PREFIX + "/job/" + params.simulationJobId + "/update-ib",
            params,
            function (strExportJson) {
                if (callback) {
                    callback(strExportJson);
                }
            }, errorCallback
        );
    }

    /////////////////////////////////////////// renew end

    function getWorkflowInstance(workflowInstanceId, callback, errorCallback){
        _clearTimeout(STATUS_TIMER);
        aSyncAjaxHelper.get(URI_PREFIX + "/workflows/instance/" + workflowInstanceId,
        function(workflowInstance){
            callback(workflowInstance);
        }, errorCallback);
    }

    function loadWorkflowInstance(workflowInstanceId, callback, errorCallback){
        _clearTimeout(STATUS_TIMER);
        aSyncAjaxHelper.get(URI_PREFIX + "/workflows/instance/" + workflowInstanceId,
        function(workflowInstance){
            callback(workflowInstance);
            if(workflowInstance.workflowUUID){
                updateStatus(workflowInstanceId);
            }
        }, errorCallback);
    }

    function createWorkfowInstance(workflowId, workflowInstanceTitle, callback){
        _clearTimeout(STATUS_TIMER);
        aSyncAjaxHelper.post(URI_PREFIX + "/workflows/instance/create", {
            workflowId: workflowId,
            workflowInstanceTitle: workflowInstanceTitle
        }, function(workflowInstance){
            if(callback){
                callback(workflowInstance);
            }
        }, function(){});
    }

    function deleteWorkflowInstance(workflowInstanceId, callback){
        _clearTimeout(STATUS_TIMER);
        aSyncAjaxHelper.post(URI_PREFIX + "/workflows/instance/" + workflowInstanceId + "/delete",
            {},
            function (workflowInstance) {
                if (callback) {
                    callback(workflowInstance);
                }
            }, function () { });
    }

    function updateWorkflowInstance(workflowInstanceId, workflowInstanceTitle, screenLogic, callback){
        _clearTimeout(STATUS_TIMER);
        var workflowInstanceData = {
            workflowInstanceId: workflowInstanceId,
            workflowInstanceTitle: workflowInstanceTitle
        };
        if(screenLogic){
            workflowInstanceData.screenLogic = JSON.stringify(screenLogic) ;
        }
        aSyncAjaxHelper.post(URI_PREFIX + "/workflows/instance/" + workflowInstanceId + "/update",
            workflowInstanceData,
            function (workflowInstance) {
                if (callback) {
                    callback(workflowInstance);
                }
            }, function () { });
    }

    function runWorkflowInstance(workflowInstanceId, icebreakerAccessToken){
        aSyncAjaxHelper.post(
            URI_PREFIX + "/workflows/instance/" + workflowInstanceId + "/run", icebreakerAccessToken,
            function (status) {
                if (status) {
                    drawWorkflowInstanceStatus(status);
                    updateStatus(workflowInstanceId);
                }
            });
    }

    function reRunWorkflowInstance(workflowInstanceId, screenLogic, callback){
        var workflowInstanceData = {};
        if(screenLogic){
            workflowInstanceData.screenLogic = JSON.stringify(screenLogic) ;
        }
        aSyncAjaxHelper.post(
            URI_PREFIX + "/workflows/instance/" + workflowInstanceId + "/reuse", workflowInstanceData,
            function (workflowInstance) {
                if (workflowInstance && callback) {
                    callback(workflowInstance);
                    // drawWorkflowInstanceStatus(status);
                    // updateStatus(workflowInstanceId);
                }
            });
    }

    function getWorkflowInstanceStatus(workflowInstanceId, callback) {
        aSyncAjaxHelper.get(URI_PREFIX + "/workflows/instance/" + workflowInstanceId + "/status",
            function (workflowStatus) {
                if (callback) {
                    callback(workflowStatus);
                }
            });
    }

    // function updateStatus(workflowInstanceId) {
    //     getWorkflowInstanceStatus(workflowInstanceId, function(workflowStatus){
    //         if (workflowStatus.workflow.status === WF_STATUS_CODE.NOT_FOUND) {
    //             toastr["error"]("", var_workflow_status_not_found_message + workflowStatus.workflow.uuid);
    //         } else {
    //             drawWorkflowInstanceStatus(workflowStatus);
    //             // TODO : button Control
    //             if (workflowStatus.workflow.status === WF_STATUS_CODE.COMPLETED) {
    //
    //             } else if (workflowStatus.workflow.status === WF_STATUS_CODE.PAUSED) {
    //
    //             } else if (workflowStatus.workflow.status === WF_STATUS_CODE.RUNNING ||
    //                 workflowStatus.workflow.status === WF_STATUS_CODE.CREATED) {
    //                 _clearTimeout(STATUS_TIMER);
    //                 STATUS_TIMER = _delay(updateStatus, 1000, workflowInstanceId);
    //             }
    //         }
    //     });
    // }

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
            $(wfBoxSelector + " .wf-app-status").html("Running" + " <i class='fa fa-cog fa-spin fa-lg fa-fw'></i>");
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
    function drawModal(title, body, bodyHeightPixel, footer, callback, footerCallback){
        var modal = $("#" + namespace + "wf-modal");
        modal.find(".modal-title").text(title);
        if(body){
            if(bodyHeightPixel){
                modal.find(".modal-body").css("height", bodyHeightPixel + "px");
            }else{
                modal.find(".modal-body").css("height", "inherit");
            }
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

    $.contextMenu({
        selector: '.jsplumb-endpoint.output-port',
        build: function ($trigger, e) {
            var sciApp = $($trigger[0]._jsPlumbRelatedElement).data();
            if (sciApp.appType === WF_APP_TYPES.DYNAMIC_CONVERTER.NAME) {
                toastr["error"]("", var_no_available_analyzer_message);
                return false;
            }
            if(sciApp.workflowStatus && sciApp.workflowStatus.status === WF_STATUS_CODE.COMPLETED){
                var items = {};
                var port = $trigger[0]._jsPlumb.getParameter("data");
                var jsPlumbWindowId = $($($trigger[0]._jsPlumbRelatedElement)[0]).attr("id");
                var analyzers = synchronousAjaxHelper.post(URI_PREFIX + "/app/outputports/analyzer",
                    {
                        "name": port.dataType().name,
                        "version": port.dataType().version
                    });
                $.each(analyzers, function (_) {
                    var analyzer = this;
                    items[analyzer.name] = {
                        name: analyzer.name,
                        icon: "edit",
                        callback: function (key, options) {
                            popAnalyzerWindow(analyzer, port, jsPlumbWindowId, sciApp);
                        }
                    };
                });
                return { items: items };
            }else{
                toastr["error"]("", var_no_available_analyzer_message);
                return false;
            }
        }
    });

    function popAnalyzerWindow(analyzer, port, jsPlumbWindowId, sciApp) {
        var portletId = analyzer.exeFileName;
        var callbackFunc = function () {
            var ospPath = port.outputData();
            console.log("ospPath : ", ospPath);
            var inputData = new OSP.InputData();
            var simUuid = sciApp.workflowStatus.jobs[0].ibSimUuid;
            var jobUuid = sciApp.workflowStatus.jobs[0].ibUuid;
            var parentPaths = [simUuid, jobUuid + ".job"];
            if(ospPath.type_){
                inputData.type(ospPath.type());
            }else{
                inputData.type(OSP.Enumeration.PathType.FILE);
            }
            if(ospPath.parent()){
                parentPaths.push(ospPath.parent());
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
            portletURL.setParameter('eventEnable', true);
            portletURL.setParameter('connector', DESIGNER_PORTLET_ID);
            portletURL.setWindowState('exclusive');
            $.ajax({
                url: portletURL.toString(),
                type: 'POST',
                dataType: 'text',
                success: function (renderResult) {
                    drawModal(analyzer.name, renderResult, 600, null, callbackFunc);
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
            URI_PREFIX + "/workflows/instance/" + workflowInstanceId + "/status",
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
                    URI_PREFIX + "/workflows/" + localWorkflow["workflowId"] + "/run", postData,
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
        "createSimulation": createSimulation,
        "updateSimulation": updateSimulation,
        "deleteSimulation": deleteSimulation,
        "fetchSimulationJobs": fetchSimulationJobs,
        "createSimulationJob": createSimulationJob,
        "updateSimulationJob": updateSimulationJob,
        "copySimulationJob": copySimulationJob,
        "deleteSimulationJob": deleteSimulationJob,
        "fetchSimulationJobSeq": fetchSimulationJobSeq,
        "updateStatus": updateStatus,
        "clearStatusTimeout": function() {
            _clearTimeout(STATUS_TIMER)
        },
        "createSimulationJobEngine": createSimulationJobEngine,
        "pauseSimulationJob": pauseSimulationJob,
        "resumeSimulationJob": resumeSimulationJob,
        "rerunSimulationJobEngine": rerunSimulationJobEngine,
        "pauseSingleNode": pauseSingleNode,
        "resumeSingleNode": resumeSingleNode,
        "exportSimulationJob": exportSimulationJob,
        "insertIbUuid": insertIbUuid,
        /////////////////////////// renew
        "createWorkfowInstance": createWorkfowInstance,
        "updateWorkflowInstance": updateWorkflowInstance,
        "deleteWorkflowInstance": deleteWorkflowInstance,
        "runWorkflowInstance": runWorkflowInstance,
        "reRunWorkflowInstance": reRunWorkflowInstance,
        // "pauseWorkflowInstance": pauseWorkflowInstance,
        // "resumeWorkflowInstance": resumeWorkflowInstance,
        "getWorkflowInstanceStatus": getWorkflowInstanceStatus,
        "getWorkflowInstance": getWorkflowInstance,
        "loadWorkflowInstance": loadWorkflowInstance
    };
});
