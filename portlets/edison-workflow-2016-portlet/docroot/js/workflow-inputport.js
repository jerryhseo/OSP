var WorkflowInputPort = (function(namespace, $, designer, toastr, uiPanel, editorPortletIds){
    'use strict';
    var DESIGNER_PORTLET_ID = namespace.slice(1, -1);
    var WF_PORTLET_GLOBAL_DATA = designer.getWfPortletGlobalData();
    var currentJsPlumbInstance = designer.getCurrentJsPlumbInstance();

    $.contextMenu({
        selector: '.jsplumb-endpoint.input-port',
        build: function ($trigger, e) {
            if(uiPanel.isEmpty()){
                return false;
            }
            var items = {};
            var sciApp = $($trigger[0]._jsPlumbRelatedElement).data();
            var port = $trigger[0]._jsPlumb.getParameter("data");
            var jsPlumbWindowId = $($($trigger[0]._jsPlumbRelatedElement)[0]).attr("id");
            var editors;
            if (sciApp.appType === WF_APP_TYPES.DYNAMIC_CONVERTER.NAME) {
                editors = [
                    {
                        appType: "Editor",
                        editorType: "File",
                        exeFileName: editorPortletIds.File,
                        name: "FILE_SELECTOR"
                    }, {
                        appType: "Editor",
                        editorType: "Text",
                        exeFileName: editorPortletIds.Text,
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
            $.each(editors, function (_) {
                var editor = this;
                items[editor.name] = {
                    name: editor.name,
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
        var portName = port.name();
        var editorType = editor.editorType;
        var portletId = editor.exeFileName;
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
            srcData.type(OSP.Enumeration.PathType.STRUCTURED_DATA);
            if (WF_PORTLET_GLOBAL_DATA.wfElements[jsPlumbWindowId] &&
                WF_PORTLET_GLOBAL_DATA.wfElements[jsPlumbWindowId][portName] &&
                WF_PORTLET_GLOBAL_DATA.wfElements[jsPlumbWindowId][portName]["editorType"] === "Inputdeck") {
                var localInputValue = WF_PORTLET_GLOBAL_DATA.wfElements[jsPlumbWindowId][portName]["input-value"];
                if(typeof localInputValue === "string"){
                    var portData = JSON.parse(localInputValue);
                    srcData.context(portData.context_);
                }else{
                    srcData.context(localInputValue.context_);
                }
            } else {
                srcData.context(JSON.parse(editor.structure));
            }
        } else if (editorType == "Text") {
            srcData.type(OSP.Enumeration.PathType.CONTEXT);
            srcData.repositoryType("USER_HOME");
            if (WF_PORTLET_GLOBAL_DATA.wfElements[jsPlumbWindowId] && 
                WF_PORTLET_GLOBAL_DATA.wfElements[jsPlumbWindowId][portName]) {
                srcData.context(WF_PORTLET_GLOBAL_DATA.wfElements[jsPlumbWindowId][portName]["file-content"]);
            } else {
                srcData.context("");
            }
        } else if (editorType == "File") {
            srcData.setPath('', '', '', OSP.Constants.FOLDER, true);
            srcData.repositoryType("USER_HOME");
        }
        // console.log("srcData ", srcData);
        // console.log("toJSON srcData ", OSP.Util.toJSON(srcData));
        showEditorWindow(editor, srcData, saveBtnHandler, sciApp);
    }
    
    function showEditorWindow(editor, inputData, saveBtnHandler, sciApp){
        window.AUI().use('liferay-portlet-url', function (A) {
            var portletURL = window.Liferay.PortletURL.createRenderURL();
            portletURL.setPortletId(editor.exeFileName);
            portletURL.setParameter('eventEnable', true);
            portletURL.setParameter('connector', DESIGNER_PORTLET_ID);
            portletURL.setWindowState('exclusive');
            $.ajax({
                url: portletURL.toString(),
                type: 'POST',
                dataType: 'text',
                success: function (renderResult) {
                    var saveBtns = { "ok": "Save", "cancel": "Cancel" };
                    if(sciApp.workflowStatus){
                       saveBtns = null;
                    }
                    drawModal(editor.name, renderResult, 600,
                        saveBtns,
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

    Liferay.on(OSP.Event.OSP_RESPONSE_DATA, function (eventData) {
        if (eventData.targetPortlet === DESIGNER_PORTLET_ID) {
            console.log("OSP_RESPONSE_DATA ", eventData);
            var workflowInfo = eventData.params;
            var editorType = workflowInfo.editorType;
            var editorInputData = new OSP.InputData(eventData.data);
            var fileContent = "";

            if (WF_PORTLET_GLOBAL_DATA.wfElements[workflowInfo.jsPlumbWindowId]) {
                if (!WF_PORTLET_GLOBAL_DATA.wfElements[workflowInfo.jsPlumbWindowId][workflowInfo.portName]) {
                    WF_PORTLET_GLOBAL_DATA.wfElements[workflowInfo.jsPlumbWindowId][workflowInfo.portName] = {};
                }
                WF_PORTLET_GLOBAL_DATA.wfElements[workflowInfo.jsPlumbWindowId][workflowInfo.portName]["input-value"] = editorInputData;
                WF_PORTLET_GLOBAL_DATA.wfElements[workflowInfo.jsPlumbWindowId][workflowInfo.portName]["editorType"] = workflowInfo.editorType;
            } else {
                var portJson = {};
                portJson[workflowInfo.portName] = {};
                portJson[workflowInfo.portName]["input-value"] = editorInputData;
                portJson[workflowInfo.portName]["editorType"] = workflowInfo.editorType;
                WF_PORTLET_GLOBAL_DATA.wfElements[workflowInfo.jsPlumbWindowId] = portJson;
            }

            if (editorType == "Inputdeck") {
                var dataType = new OSP.DataType();
                var dataStructure = dataType.deserializeStructure(editorInputData.context());
                fileContent = _join(dataType.structure().activeParameterFormattedInputs()[0], "");
            } else if (editorType == "Text") {
                fileContent = editorInputData.context();
            } else if (editorType == "File") {
                var portInfo = WF_PORTLET_GLOBAL_DATA.wfElements[workflowInfo.jsPlumbWindowId][workflowInfo.portName];
                portInfo.fileName = editorInputData.name();
                portInfo.parentPath = editorInputData.parent();
                portInfo.pathType = editorInputData.type();
            }
            WF_PORTLET_GLOBAL_DATA.wfElements[workflowInfo.jsPlumbWindowId][workflowInfo.portName]["file-content"] = fileContent;
        }
        console.log("WF_PORTLET_GLOBAL_DATA : ", WF_PORTLET_GLOBAL_DATA);
    });

    function _join(arr, sep){
        var rValue = "";
        $.each(arr, function(i){
            var that = arr[i];
            if(i !== 0){
                rValue += sep;
            }
            rValue += that;
        });
        return rValue;
    }

    return {
        "showEditorWindow": showEditorWindow,
        "popEditorWindow": popEditorWindow
    };
});
