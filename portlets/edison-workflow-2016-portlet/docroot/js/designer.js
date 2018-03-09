var Designer = (function (namespace, $, OSP, toastr, isFixed) {
    /*jshint -W018 */
    /*jshint -W069 */
    /*jshint -W014 */
    isFixed = isFixed === true ? true : false;

    var currentJsPlumbInstance;
    var wfPortletGlobalData = wfPortletGlobalData ? wfPortletGlobalData : {wfElements : {}};
    var modifyingWorkflow;

    /** application **/
    var portDropOption = {
        // tolerance: "touch",
        hoverClass: "dropHover",
        activeClass: "dragActive"
    };

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

    var inputPortColor = "#416EC5";
    var outputPortColor = "#D6442D";
    var connectionColor = "#11C7E7";

    var outputPortPoint = {
        endpoint: ["Rectangle", {cssClass: "output-port"}],
        type: WF_JSPLUMB_TYPES.ENDPOINT + " " + WF_JSPLUMB_TYPES.OUTPUT,
        paintStyle: {width: 18, height: 18, fillStyle: outputPortColor},
        isSource: !isFixed,
        maxConnections: -1,
        connectorStyle: {
            lineWidth: 5,
            strokeStyle: connectionColor
        },
        ConnectionsDetachable: !isFixed,
        isTarget: false,
        dropOptions: portDropOption
    };

    var inputPortPoint = {
        endpoint: ["Rectangle", {cssClass: "input-port"}],
        type: WF_JSPLUMB_TYPES.ENDPOINT + " " + WF_JSPLUMB_TYPES.INPUT,
        paintStyle: {width: 18, height: 18, fillStyle: inputPortColor},
        isSource: false,
        isTarget: !isFixed,
        ConnectionsDetachable: !isFixed,
        beforeDrop: function (params) {
            if ($(currentJsPlumbInstance.getContainer()).attr("id") != "wf-workflow-canvas") {
                return false;
            }
            var sourceEndpoint = params.connection.endpoints[0].getParameter("data");
            var targetEndpoint = params.dropEndpoint.getParameter("data");
            if (targetEndpoint.dataType().name === WF_APP_TYPES.DYNAMIC_CONVERTER.INPUT_DATA_TYPE ||
                targetEndpoint.dataType().name === WF_APP_TYPES.CONTROLLER.INPUT_DATA_TYPE) {
                return true;
            }
            if (sourceEndpoint.dataType().name === WF_APP_TYPES.DYNAMIC_CONVERTER.OUTPUT_DATA_TYPE ||
                sourceEndpoint.dataType().name === WF_APP_TYPES.CONTROLLER.OUTPUT_DATA_TYPE) {
                return true;
            }
            return targetEndpoint.dataType().name === sourceEndpoint.dataType().name &&
                targetEndpoint.dataType().version === sourceEndpoint.dataType().version &&
                !(params.sourceId === params.targetId) &&
                sourceEndpoint.outputData().type() === "file";
        },
        dropOptions: portDropOption
    };

    var wfWorkflowJsPlumbInstance = jsPlumb.getInstance({
        Container: "wf-workflow-canvas",
        DragOptions: {containment: true, cursor: 'pointer'},
        Endpoint: "Rectangle",
        ConnectionsDetachable: !isFixed,
        Anchors: ["TopCenter", "TopCenter"],
        Overlays: [["Arrow", {location: 1, id: "arrow", length: 14, foldback: 1}]]
    });

    /** jsplumb callback **/
    function jsPlumbConnectionDetachedCallback(info, originalEvent) {
        if (!info.sourceEndpoint.hasType(WF_JSPLUMB_TYPES.ENDPOINT) || 
            !info.targetEndpoint.hasType(WF_JSPLUMB_TYPES.ENDPOINT)) {
            return;
        }
        var outputPortData = info.sourceEndpoint.getParameter("data");
        var inputPortData = info.targetEndpoint.getParameter("data");
        var sourceClientId = $(info.sourceEndpoint.getElement()).attr("id");
        var targetClientId = $(info.targetEndpoint.getElement()).attr("id");
        var sourceApp = $(info.sourceEndpoint.getElement()).data();
        var targetApp = $(info.targetEndpoint.getElement()).data();
        if (sourceApp["childNodes"] && $.inArray(targetClientId, sourceApp["childNodes"]) > -1) {
            sourceApp["childNodes"].splice($.inArray(targetClientId, sourceApp["childNodes"]), 1);
        }
        if (targetApp["parentNodes"] && $.inArray(sourceClientId, targetApp["parentNodes"]) > -1) {
            targetApp["parentNodes"].splice($.inArray(sourceClientId, targetApp["parentNodes"]), 1);
        }
        if (targetApp["inputports"] && targetApp["inputports"][inputPortData["name"]] ||
            targetApp["appType"] === WF_APP_TYPES.CONTROLLER.NAME ||
            targetApp["appType"] === WF_APP_TYPES.DYNAMIC_CONVERTER.NAME ||
            targetApp["appType"] === WF_APP_TYPES.STATIC_CONVERTER.NAME) {
            delete targetApp["inputports"][inputPortData["name_"]]["expectedSource"];
            delete targetApp["inputports"][inputPortData["name_"]]["expectedValue"];
            delete targetApp["inputports"][inputPortData["name_"]]["hasParent"];
        }
    }

    function jsPlumbConnectionCallback(info, originalEvent) {
        if (!info.sourceEndpoint.hasType(WF_JSPLUMB_TYPES.ENDPOINT)
            || !info.targetEndpoint.hasType(WF_JSPLUMB_TYPES.ENDPOINT)) {
            return;
        }
        var outputPortData = info.sourceEndpoint.getParameter("data");
        var inputPortData = info.targetEndpoint.getParameter("data");
        var sourceClientId = $(info.sourceEndpoint.getElement()).attr("id");
        var targetClientId = $(info.targetEndpoint.getElement()).attr("id");
        var sourceApp = $(info.sourceEndpoint.getElement()).data();
        var targetApp = $(info.targetEndpoint.getElement()).data();
        if (!sourceApp["childNodes"]) {
            sourceApp["childNodes"] = [];
        }
        if ($.inArray(targetClientId, sourceApp["childNodes"]) === -1) {
            sourceApp["childNodes"].push(targetClientId);
        }
        if (!targetApp["parentNodes"]) {
            targetApp["parentNodes"] = [sourceClientId];
        }
        if ($.inArray(sourceClientId, targetApp["parentNodes"]) === -1) {
            targetApp["parentNodes"].push(sourceClientId);
        }
        if (targetApp["inputports"] && targetApp["inputports"][inputPortData["name"]] ||
            targetApp["appType"] === WF_APP_TYPES.CONTROLLER.NAME ||
            sourceApp["appType"] === WF_APP_TYPES.CONTROLLER.NAME ||
            targetApp["appType"] === WF_APP_TYPES.STATIC_CONVERTER.NAME ||
            sourceApp["appType"] === WF_APP_TYPES.STATIC_CONVERTER.NAME ||
            targetApp["appType"] === WF_APP_TYPES.DYNAMIC_CONVERTER.NAME ||
            sourceApp["appType"] === WF_APP_TYPES.DYNAMIC_CONVERTER.NAME) {
            targetApp["inputports"][inputPortData.name()]["expectedSource"] = sourceClientId;
            targetApp["inputports"][inputPortData.name()]["expectedValue"] = outputPortData.outputData().fileName();
            targetApp["inputports"][inputPortData.name()]["hasParent"] = true;
        }
    }

    function jsPlumbDblClickCallback(element, event) {
        if (element.hasType(WF_JSPLUMB_TYPES.ENDPOINT)
            && element.hasType(WF_JSPLUMB_TYPES.INPUT)
            && element.connections.length === 0) {
            /*call parent Div data*/
            var sciApp = $(element.getElement()).data();
            var port = element.getParameter("data");

            aSyncAjaxHelper.post("/delegate/services/app/" +
                sciApp["scienceAppId"] + "/inputports/editor/default",
                {
                    "name": port.dataType().name,
                    "version": port.dataType().version
                },
                function (result) {
                    var jsPlumbWindowId = element.elementId;
                    // TODO : popEditorWindow(result, port, jsPlumbWindowId);
                });
        }
    }

    currentJsPlumbInstance = wfWorkflowJsPlumbInstance;

    var decision = new Decision(namespace, $, currentJsPlumbInstance);

    currentJsPlumbInstance.bind("dblclick", jsPlumbDblClickCallback);
    currentJsPlumbInstance.bind("connection", jsPlumbConnectionCallback);
    currentJsPlumbInstance.bind("connectionDetached", jsPlumbConnectionDetachedCallback);

    function addScienceApp(target, pageX, pageY, data){
        var wfId = drawScienceAppDiv(target, pageX, pageY, data);
        if(data["appType"] && data["appType"] == WF_APP_TYPES.DYNAMIC_CONVERTER.NAME){
            addEndPointToScienceApp(wfId, data["inputports"], true);
            addEndPointToScienceApp(wfId, data["outputports"], false);
        }else if(data["appType"] && data["appType"] == WF_APP_TYPES.CONTROLLER.NAME){
            addEndPointToController(wfId, data["inputports"], true);
            addEndPointToController(wfId, data["outputports"], false);
        }else{
            var inputports = addScienceAppInputPort(wfId, data.scienceAppId);
            var outputports = addScienceAppOutputPort(wfId, data.scienceAppId);

            data["inputports"] = inputports;
            data["outputports"] = outputports;
        }
        $("#" + wfId).data(data);
    }

    function drawController(target, pageX, pageY, data, wfId){
        var html = '<div id="{{id}}" class="wf-box wf-controller ui-selectee">'+
        '  <svg xmlns="http://www.w3.org/2000/svg">'+
        '    <g class="fc-decision">'+
        '      <polygon points="0,60 75,0 150,60 75,120" class="fc-rhombus"></polygon>'+
        '      <text x="42" y="65">{{name}}</text>'+
        '    </g>'+
        '  </svg>'+
        '</div>';
        return $(Mustache.render(html, { 
            "id": wfId,
            "name": data.name
        })).appendTo(target);
    }

    function drawScienceAppDiv(target, pageX, pageY, data, savedId){
        var wfId = savedId ? savedId : getGUID();
        var $wfDiv;
        if(data.appType && data.appType == WF_APP_TYPES.CONTROLLER.NAME){
            $wfDiv = drawController(target, pageX, pageY, data, wfId);
        }else{
            $wfDiv = $("<div class='waitingbox wf-box ui-selectee' id='" + wfId + 
            "'><div class='wf-app-title' alt='"+data.text+"'>" + 
            data.name + "</div><div class='addIp buttonwait wf-app-status'>Waiting</div></div>")
                .appendTo(target);
        }
        
        if(data.appType && data.appType == WF_APP_TYPES.DYNAMIC_CONVERTER.NAME){
            $wfDiv.addClass("wf-converter").addClass("wf-dynamic");
        }else if(data.appType && data.appType == WF_APP_TYPES.STATIC_CONVERTER.NAME){
            $wfDiv.addClass("wf-converter").addClass("wf-static");
        }else if(data.appType && data.appType == WF_APP_TYPES.APP.NAME){
            $wfDiv.addClass("wf-app");
        }

        $wfDiv.offset({top : pageY, left : pageX});
        currentJsPlumbInstance.draggable($wfDiv, {
            containment:"parent",
            start: function(el){
                $(".menu-panel > .menu-panel-box-app").addClass("hidden");
            },
            stop: function(){
                $(".menu-panel > .menu-panel-box-app").removeClass("hidden");
            }
        });
        //drawLoopArrow(wfId, data);
        return wfId;
    }

    function addScienceAppInputPort(wfId, scienceAppId){
        var inputports = synchronousAjaxHelper.get("/delegate/services/app/"+scienceAppId+"/inputports");
        return addEndPointToScienceApp(wfId, $.parseJSON(inputports), true);
    }

    function addEndPointToScienceApp(wfId, portJson, isInputPort){
        if(!$.isEmptyObject(portJson)){
            var addEndPoint = prepareEndpoint(wfId, portJson, isInputPort);
            addEndPoint(currentJsPlumbInstance);
            return portJson;
        }
    }

    function addEndPointToController(wfId, portJson, isInputPort){
        if(!$.isEmptyObject(portJson)){
            var ports = getPortsArrayFromPortJson(portJson, isInputPort);
            
            var endPointType = isInputPort ? inputPortPoint : outputPortPoint;
            var anchors = isInputPort ? ["Top", [0.25, 0.25, 0, 0]] : ["Bottom", "Left"];
            var isModifiable = $(currentJsPlumbInstance.getContainer()).attr("id") == "wf-workflow-canvas";
            $.each(ports, function(_, port){
                var connectionScope = port.dataType().name + "_" + port.dataType().version;
                if(isInputPort){
                    connectionScope += " " + WF_APP_TYPES.DYNAMIC_CONVERTER.INPUT_SCOPE;
                    connectionScope += " " + WF_APP_TYPES.CONTROLLER.INPUT_SCOPE;
                }else{
                    connectionScope += " " + WF_APP_TYPES.DYNAMIC_CONVERTER.OUTPUT_SCOPE;
                    connectionScope += " " + WF_APP_TYPES.CONTROLLER.OUTPUT_SCOPE;
                    if(isModifiable){
                        endPointType["isSource"] = true;
                    }else{
                        endPointType["isSource"] = false;
                    }
                }
                endPointType["scope"] = connectionScope;

                var labelLocation = isInputPort ? 
                    [-1 * ((port.name()+"").length/5.7), 0.5] : 
                    [-0.6, 0.6];
                var endPointGuid = wfId + port.name() + isInputPort;
                currentJsPlumbInstance.addEndpoint(
                    wfId,
                    {
                        anchor: anchors[_],
                        uuid: endPointGuid,
                        overlays: [["Label", {
                            label: port.name(),
                            location:labelLocation
                        }]]
                    },
                    endPointType).setParameter("data", port);
            });
        }
    }

    function addScienceAppOutputPort(wfId, scienceAppId){
        var outputports = synchronousAjaxHelper.get("/delegate/services/app/"+scienceAppId+"/outputports");
        var outputportsJson = $.parseJSON(outputports);
        delete outputportsJson["temp"]; /* 중간 확인 포트 제거  */
        if(!$.isEmptyObject(outputportsJson)){
            var addEndPoint = prepareEndpoint(wfId, outputportsJson, false);
            addEndPoint(currentJsPlumbInstance);
            return outputportsJson;
        }
    }

    function getPortsArrayFromPortJson(portJsonObject, isInputPort){
        var scienceApp = new OSP.ScienceApp();
        if(isInputPort){
            scienceApp.inputPorts(scienceApp.deserializeInputPorts(portJsonObject));
            return scienceApp.inputPortsArray();
        }else{
            scienceApp.outputPorts(scienceApp.deserializeOutputPorts(portJsonObject));
            return scienceApp.outputPortsArray();
        }
    }

    function prepareEndpoint(appDivId, portJson, isInputPort){
        var ports = getPortsArrayFromPortJson(portJson, isInputPort);
        var anchorUnit = (function(ports){
            var anchorUnit = 0.7;
            if(ports && ports.length > 1){
                anchorUnit = anchorUnit / (ports.length-1);
            }
            return anchorUnit;
        })(ports);
        return function traversePortsAndAddEndPoint(jsPlumbInstance){
            var endPointType = isInputPort ? inputPortPoint : outputPortPoint;
            var defaultAnchor = isInputPort ? [0, 0.15, -1, 0]: [1, 0.15, 1, 0];
            var isModifiable = $(jsPlumbInstance.getContainer()).attr("id") == "wf-workflow-canvas";
            $.each(ports, function(_, port){
                var connectionScope = port.dataType().name + "_" + port.dataType().version;
                if(isInputPort){
                    connectionScope += " " + WF_APP_TYPES.CONTROLLER.INPUT_SCOPE;
                    connectionScope += " " + WF_APP_TYPES.DYNAMIC_CONVERTER.INPUT_SCOPE;
                }else{
                    connectionScope += " " + WF_APP_TYPES.CONTROLLER.OUTPUT_SCOPE;
                    connectionScope += " " + WF_APP_TYPES.DYNAMIC_CONVERTER.OUTPUT_SCOPE;
                    if(isModifiable){
                        endPointType["isSource"] = true;
                    }else{
                        endPointType["isSource"] = false;
                    }
                }
                endPointType["scope"] = connectionScope;

                var labelLocation =
                    isInputPort
                        ? [-1 * ((port.name()+"").length/5.7), 0.5]
                        : [9.5, 0.6];
                var endPointGuid = appDivId + port.name() + isInputPort;
                var endPoint = jsPlumbInstance.addEndpoint(
                    appDivId,
                    {
                        anchor: defaultAnchor,
                        uuid: endPointGuid,
                        overlays: [["Label", {
                            label: isInputPort ? port.name() : "<p style=\"width:300px;margin-top:8px;\">"+labelAddSpace(port.outputData())+"<p>",
                            location: labelLocation
                        }]]
                    },
                    endPointType);
                defaultAnchor[1] = defaultAnchor[1] + anchorUnit;
                endPoint.setParameter("data", port);
            });
        };
    }

    function labelAddSpace(outputData) {
        var value;
        if (outputData.type() == "folder") {
            value = outputData.name() + "/*";
        } else if (outputData.type() == "ext") {
            value = "*." + outputData.name();
        } else {
            value = outputData.name();
        }
        var asValue = value + "";
        if (asValue.length > 51) {
            var addCnt = 50 - asValue.length;
            var addValue = "";
            for (var i = 0; i < addCnt; i++) {
                addValue += " ";
            }
            return asValue + addValue;
        } else {
            return asValue;
        }
    }

    function removeSicenceApps($el) {
        _confirm(var_remove_app_confirm, function(){
            $el.each(function (_) {
                var elId = $(this).attr("id");
                if (wfPortletGlobalData
                    && wfPortletGlobalData.hasOwnProperty('wfElements')
                    && wfPortletGlobalData["wfElements"].hasOwnProperty(elId)) {
                    delete wfPortletGlobalData["wfElements"][elId];
                }
                currentJsPlumbInstance.detachAllConnections(elId);
                currentJsPlumbInstance.removeAllEndpoints(elId);
                currentJsPlumbInstance.detach(elId);
            });
            $el.remove();
        });
    }

    function loadScienceAppPort(wfId, portJson, isInputPort) {
        var addEndPoint = prepareEndpoint(wfId, portJson, isInputPort);
        addEndPoint(currentJsPlumbInstance);
    }

    function loadScienceApp(id, offset, data) {
        var target = $(currentJsPlumbInstance.getContainer());
        var diff = offset.referencePoint - target.offset().left;
        var wfId = drawScienceAppDiv(target, offset.left - diff, offset.top, data, id);
        var conainerId = $(currentJsPlumbInstance.getContainer()).attr("id");
        if(data.appType === WF_APP_TYPES.CONTROLLER.NAME){
            addEndPointToController(wfId, data.inputports, true);
            addEndPointToController(wfId, data.outputports, false);
        }else{
            loadScienceAppPort(wfId, data.inputports, true);
            loadScienceAppPort(wfId, data.outputports, false);
        }
        $("#" + conainerId + " #" + wfId).data(data);
    }

    $.contextMenu({
        selector: '.wf-box',
        build: function ($trigger, e) {
            var wfWindowId = $trigger.attr("id");
            var appData = $trigger.data();
            var cpuNumber = appData["cpuNumber"] ? appData["cpuNumber"] : "" + appData["defaultCpus"];
            var items = { items: {} };
            if (appData["appType"] == WF_APP_TYPES.APP.NAME) {
                items["items"]["open-info"] = {
                    name: "App Information",
                    icon: "info",
                    callback: function (key, options) {
                        var scienceAppId = appData["scienceAppId"];
                        // TODO : openSolverDeatilPopup(scienceAppId);
                    }
                };
            }
            if (appData["appType"] == WF_APP_TYPES.DYNAMIC_CONVERTER.NAME) {
                items["items"]["open-texteditor"] = {
                    name: "Script",
                    icon: "edit",
                    callback: function (key, options) {
                        // TODO : popScriptEditorWindow(appData, wfWindowId);
                    }
                };
            }
            if (appData["appType"] == WF_APP_TYPES.CONTROLLER.NAME) {
                items["items"]["open-texteditor"] = {
                    name: "Edit Condition",
                    icon: "edit",
                    callback: function (key, options) {
                        // TODO : popScriptEditorWindow(appData, wfWindowId);
                        decision.condtions(wfWindowId);
                    }
                };
            }
            if (appData["runType"] === "Parallel") {
                items["items"]["mpi-title"] = {
                    name: "MPI Setting",
                    icon: "edit",
                    disabled: true
                };
                items["items"]["mpi-input"] = {
                    name: "Cpu Number (scope : " + appData["defaultCpus"] + " ~ " + appData["maxCpus"] + ")",
                    type: 'text',
                    value: cpuNumber,
                    events: {
                        keyup: function (e) {
                            appData["cpuNumber"] = $(this).val();
                        }
                    }
                };
                items["items"]["sep1"] = "---------";
            }
            items["items"]["delete"] = {
                name: "Delete App",
                icon: "delete",
                callback: function (key, options) {
                    if ($(".ui-selected").length > 0) {
                        currentJsPlumbInstance.removeFromPosse($(".wf-box"), "posse");
                        removeSicenceApps($(".ui-selected"));
                    } else {
                        removeSicenceApps($(this));
                    }
                }
            };
            return items;
        }
    });

    function getWorkflowDefinition(currentJsPlumbInstance) {
        var wfData = { elements: [], connections: [] };
        if (wfPortletGlobalData) {
            wfData["wfPortletGlobalData"] = wfPortletGlobalData;
        }

        // TODO : remove Loop Start App Add 
        if ($(currentJsPlumbInstance.getContainer()).children(".loopbox").length > 0) {
            wfData["loopStartElementId"] = $(currentJsPlumbInstance.getContainer()).children(".loopbox").attr("id");
        } else {
            wfData["loopStartElementId"] = "";
        }

        $(currentJsPlumbInstance.getContainer()).children(".wf-box").each(function () {
            var scienceAppClientId = $(this).attr("id");
            var thisData = jQuery.extend(true, {}, $(this).data()); /*deep copy*/
            delete thisData['selectableItem']; /* prevent converting circular structure error */
            if (thisData["inputports"] && wfPortletGlobalData) {
                for (var key in thisData["inputports"]) {
                    if (wfPortletGlobalData["wfElements"]["" + scienceAppClientId]) {
                        var inputValue = wfPortletGlobalData["wfElements"]["" + scienceAppClientId][key];
                        if (!inputValue) {
                            continue;
                        }
                        if (inputValue["editorType"] == "Inputdeck") {
                            thisData["inputports"][key]["input-value"] = inputValue["file-content"];
                        } else if (inputValue["editorType"] == "File") {
                            thisData["inputports"][key]["fileName"] = inputValue["fileName"];
                            thisData["inputports"][key]["parentPath"] = inputValue["parentPath"];
                            thisData["inputports"][key]["pathType"] = inputValue["pathType"];
                        } else {
                            thisData["inputports"][key]["input-value"] = inputValue["input-value"];
                        }
                        thisData["inputports"][key]["editorType"] = inputValue["editorType"];
                    }
                }
                if (wfPortletGlobalData["wfElements"]["" + scienceAppClientId]
                    && wfPortletGlobalData["wfElements"]["" + scienceAppClientId]["converter-script"]) {
                    thisData["inputports"]["script"] = {
                        "input-value": wfPortletGlobalData["wfElements"][""
                            + scienceAppClientId]["converter-script"]["input-value"],
                        editorType: "Text"
                    };
                }
            }
            var thisOffset = $(this).offset();
            thisOffset.referencePoint = $(currentJsPlumbInstance.getContainer()).offset().left;
            wfData.elements.push({
                id: scienceAppClientId,
                offset: thisOffset,
                data: thisData
            });
        });
        $.each(currentJsPlumbInstance.getAllConnections(), function (idx, connection) {
            consoleLog.debug(connection);
            // loop connection
            if (connection.hasType(WF_JSPLUMB_TYPES.LOOP)) {
                return;
            }
            wfData.connections.push({
                connectionId: connection.id,
                pageSourceId: connection.sourceId,
                pageTargetId: connection.targetId,
                sourceUuid: connection.endpoints[0].getUuid(),
                targetUuid: connection.endpoints[1].getUuid()
            });
        });
        consoleLog.info("before saving!");
        consoleLog.info(wfData);
        return wfData;
    }

    function afterSave(workflowData, callback, backgroudSave) {
        modifyingWorkflow = workflowData;
        if (callback) {
            callback(workflowData.workflowId);
        }
        if (!backgroudSave) {
            toastr["success"]("", var_save_success_message);
        }
    }

    function renameWorkflowDefinition(workflowData, callback, backgroudSave) {
        aSyncAjaxHelper.jsonPost("/delegate/services/workflows/" + 
                workflowData.workflowId + "/update", 
                JSON.stringify(workflowData), 
                function (workflowData) {
                    toastr["success"]("", var_save_success_message);
                    if(callback){
                        callback(workflowData);
                    }
                });
    }

    function saveOrUpdateWorkflowDefinition(workflowMetaData, callback, backgroudSave) {
        var localWorkflow = modifyingWorkflow;
        var title = workflowMetaData.title;
        var wfData = getWorkflowDefinition(currentJsPlumbInstance);

        /* validation */
        if (!title || title === "" || title.trim() === "") {
            toastr["error"]("", var_create_first_message);
            return false;
        }
        var wfDataJsonString = JSON.stringify(wfData);
        if (localWorkflow) {
            localWorkflow.title = title;
            localWorkflow.description = workflowMetaData.description;
            localWorkflow.screenLogic = wfDataJsonString;
            if(workflowMetaData.isPublic){
                localWorkflow.isPublic = workflowMetaData.isPublic;
            }
            aSyncAjaxHelper.jsonPost("/delegate/services/workflows/" + 
                localWorkflow.workflowId + "/update", 
                JSON.stringify(localWorkflow), 
                function (workflowData) {
                    afterSave(workflowData, callback, backgroudSave);
                });
        } else {
            aSyncAjaxHelper
                .post("/delegate/services/workflows/add", {
                    title: title,
                    description: workflowMetaData.description,
                    screenLogic: wfDataJsonString
                }, function (workflowData) {
                    afterSave(workflowData, callback, backgroudSave);
                }, function(errorMessage){
                    toastr["error"]("", errorMessage);
                });
        }
    }

    function saveAsWorkflowDefinition(workflowMetaData) {
        if (!modifyingWorkflow) {
            saveOrUpdateWorkflowDefinition(workflowMetaData, false);
            return false;
        }else{
            var currentWorkflowId = modifyingWorkflow["workflowId"];
            aSyncAjaxHelper
                .post("/delegate/services/workflows/" + currentWorkflowId + "/saveas", {
                    title: workflowMetaData.title,
                    description: workflowMetaData.description
                }, function (workflowData) {
                    toastr["success"]("", var_save_success_message);
                    modifyingWorkflow = workflowData;
                }, function (msg) {
                    toastr["error"]("", msg);
                });
        }
    }

    function deleteWorkflowDefinition(workflowId, callback){
        aSyncAjaxHelper.jsonPost("/delegate/services/workflows/" + workflowId + "/delete",
            {}, function (_) {
                if(callback){
                    callback();
                }
            });
    }
    function duplicateWorkflowDefinition(workflowId, workflowTitle, callback){
        var param = workflowTitle ? {"title": workflowTitle} : {};
        aSyncAjaxHelper
            .jsonPost("/delegate/services/workflows/" + workflowId + "/copy",
            JSON.stringify(param),
            function (_) {
                if (callback) {
                    callback(_);
                }
            });
    }

    function copyWorkflowDefinition(workflowId) {
        resetCurrentJsPlumbInstance();
        duplicateWorkflowDefinition(workflowId, function(workflow){
            var wfData = $.parseJSON(workflow["screenLogic"]);
            if ($(currentJsPlumbInstance.getContainer()).attr("id") == "wf-workflow-canvas") {
                $("#worfklow-definition-name").val(workflow["title"]);
                currentJsPlumbInstance.bind("dblclick", jsPlumbDblClickCallback);
                currentJsPlumbInstance.bind("connectionDetached", jsPlumbConnectionDetachedCallback);
                currentJsPlumbInstance.bind("connection", jsPlumbConnectionCallback);
            }
    
            $.each(wfData.elements, function (i) {
                loadScienceApp(this["id"], this["offset"], this["data"]);
            });
    
            $.each(wfData.connections, function (i) {
                var sourceEndpointUuid = this["sourceUuid"];
                var targetEndpointUuid = this["targetUuid"];
                currentJsPlumbInstance.connect({ uuids: [sourceEndpointUuid, targetEndpointUuid] });
            });
        });
    }

    function loadWorkflowDefinition(workflowId, fnCallback){
        aSyncAjaxHelper.get("/delegate/services/workflows/"+ workflowId, 
        function(workflow){
            if(fnCallback){
                fnCallback(workflow);
            }
            drawWorkflowDefinition(workflow);
        }, 
        function(){
            toastr["error"]("","load Workflow Definition fail.");
        });
    }

    function drawWorkflowDefinition(workflow) {
        resetWorkflow();
        drawScreenLogic(workflow.screenLogic);
        if (workflow.hasOwnProperty("createDate")) {
            delete workflow.createDate;
        }
        modifyingWorkflow = workflow;
    }

    function drawScreenLogic(screenLogic){
        var wfData = $.parseJSON(screenLogic);
        $.each(wfData.elements, function (i) {
            loadScienceApp(this.id, this.offset, this.data);
        });

        $.each(wfData.connections, function (i) {
            var sourceEndpointUuid = this.sourceUuid;
            var targetEndpointUuid = this.targetUuid;
            currentJsPlumbInstance.connect({ uuids: [sourceEndpointUuid, targetEndpointUuid] });
        });

        if(wfData.wfPortletGlobalData){
            wfPortletGlobalData.wfElements = wfData.wfPortletGlobalData.wfElements;
        }
    }

    function resetCurrentJsPlumbInstance() {
        currentJsPlumbInstance.reset();
        $(currentJsPlumbInstance.getContainer()).children(".wf-box").remove();
    }

    function resetWorkflow() {
        resetCurrentJsPlumbInstance();
        currentJsPlumbInstance.bind("dblclick", jsPlumbDblClickCallback);
        currentJsPlumbInstance.bind("connectionDetached", jsPlumbConnectionDetachedCallback);
        currentJsPlumbInstance.bind("connection", jsPlumbConnectionCallback);
        wfPortletGlobalData.wfElements = {};
        modifyingWorkflow = undefined;
    }

    return {
        "addScienceApp": addScienceApp,
        "removeSicenceApps": removeSicenceApps,
        "getWorkflowDefinition": getWorkflowDefinition,
        "drawScreenLogic": drawScreenLogic,
        "loadWorkflowDefinition": loadWorkflowDefinition,
        "saveOrUpdateWorkflowDefinition": saveOrUpdateWorkflowDefinition,
        "saveAsWorkflowDefinition": saveAsWorkflowDefinition,
        "renameWorkflowDefinition": renameWorkflowDefinition,
        "duplicateWorkflowDefinition": duplicateWorkflowDefinition,
        "deleteWorkflowDefinition": deleteWorkflowDefinition,
        "drawWorkflowDefinition": drawWorkflowDefinition,
        "resetWorkflow": resetWorkflow,
        "getWfPortletGlobalData": function(){
            return wfPortletGlobalData;
        },
        "getCurrentJsPlumbContainerId": function(){
            return $(currentJsPlumbInstance.getContainer()).attr("id");
        },
        "getCurrentJsPlumbInstance": function(){
            return currentJsPlumbInstance;
        }
    };
});