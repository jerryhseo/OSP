var SimulationExecutor = (function (namespace, $, designer, toastr) {
    'use strict';
    var currentJsPlumbInstance = designer.getCurrentJsPlumbInstance();

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
            workflowInstanceData.screenLogic = screenLogic;
        }

        aSyncAjaxHelper.post("/delegate/services/workflows/instance/" + workflowInstanceId + "/update",
            workflowInstanceData,
            function (workflowInstance) {
                if (callback) {
                    callback(workflowInstance);
                }
            }, function () { });
    }
    
    function runEventHandler(e) {
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
    }
    return {
        "createWorkfowInstance": createWorkfowInstance,
        "updateWorkflowInstance": updateWorkflowInstance,
        "deleteWorkflowInstance": deleteWorkflowInstance
    };
});