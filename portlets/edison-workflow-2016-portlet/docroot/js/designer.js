var Designer = (function(namespace, $, OSP, toastr, isFixed, editorPortletIds, isDesigner) {
    /*jshint -W018 */
    /*jshint -W069 */
    /*jshint -W014 */
    isFixed = isFixed === true ? true : false;

    var uiPanelInstance = undefined;

    var setUiPanelInstance = function(uiPanel) {
        uiPanelInstance = uiPanel;
    }

    var currentJsPlumbInstance;
    var wfPortletGlobalData = wfPortletGlobalData ? wfPortletGlobalData : { wfElements: {} };
    var modifyingWorkflow;

    /** application **/
    var portDropOption = {
        // tolerance: "touch",
        hoverClass: "dropHover",
        activeClass: "dragActive"
    };

    var inputPortColor = "#416EC5";
    var outputPortColor = "#D6442D";
    var connectionColor = "#11C7E7";

    function turnOffBeforeConnect(jpInstance) {
    	jpInstance.beforeStartConnect = function() { return false; }
        jpInstance.beforeConnect = function() { return false; }
    }

    function turnOnBeforeConnect(jpInstance) {
        jpInstance.beforeConnect = beforeConnectHandler;
    }

    function beforeConnectHandler(source, target, edgeData) {
        if (source.objectType !== "Node" && target.objectType !== "Node") {

            var sourcePortName = source.getNode()["data"]["outputPorts"][source.id][OSP.Constants.NAME];
            var targetPortName = target.getNode()["data"]["inputPorts"][target.id][OSP.Constants.NAME];
            
            /* Port Connect Bug fix - Change target port name of first connected node */
            /*if (sourcePortName != source.id) {
                source.getNode()["data"]["outputPorts"][source.id][OSP.Constants.NAME] = source.id;
            }*/

            if (targetPortName != target.id) {
                target.getNode()["data"]["inputPorts"][target.id][OSP.Constants.NAME] = target.id;
            }

            if (source === target) {
                return false;
            }

            if (source.getNode() === target.getNode()) {
                return false;
            }

            if (source.getType() === 'sourceAll' && target.getType() === 'targetAll') {
                if (source.getNode().data.scienceAppData.runType === WF_APP_TYPES.FILE_COMPONENT.NAME) {
                    return false;
                } else {
                    return true;
                }
            } else {
            	if (source.getType() === 'sourceAll' || target.getType() === 'targetAll') {
                    if (source.getNode().data.scienceAppData.runType === WF_APP_TYPES.FILE_COMPONENT.NAME) {
                        var isEqualsPortType = false;
                        var sourceData = source.getNode().data,
                            targetData = target.getNode().data;
                        var sourcePortDataType = sourceData.outputPorts[source.id][OSP.Constants.DATA_TYPE];
                        if (sourcePortDataType == 'undefined' || sourcePortDataType == null || sourcePortDataType == '') {
                            sourceData.outputPorts[source.id] = targetData[target.getType()][target.id];
                            sourceData.outputPorts[source.id][OSP.Constants.NAME] = WF_APP_TYPES.FILE_COMPONENT.OUTPUT_NAME;
                            sourceData.outputPorts[source.id][OSP.Constants.IS_WF_SAMPLE] = false;
                            if (sourceData.outputPorts[source.id][OSP.Constants.WF_SAMPLE]) {
                                sourceData.outputPorts[source.id][OSP.Constants.WF_SAMPLE] = {};
                            }
                            
                            console.log(sourceData);
                            console.log(targetData);

                            isEqualsPortType = true;
                        } else {
                        	console.log("====================");
                        	console.log(sourceData);
                        	console.log(targetData);
                            isEqualsPortType = checkPortTypeForConnection(source, target, true);
                        }
                        return isEqualsPortType;
                    }
                    return true;
                } else if (source.getType() === 'inputPorts') {
                    return false;
                } else {
                    return checkPortTypeForConnection(source, target, false);
                }
            }
        } else {
            return false;
        }
    }

    var wfWorkflowJsPlumbInstance = jsPlumbToolkit.newInstance({
        beforeConnect: beforeConnectHandler,
        beforeStartDetach: function() {
            return isDesigner;
        },
        beforeDetach: function(source, target, edgeData) {
            var sourceData = source.getNode().data;
            if (source.getNode().data.scienceAppData.runType === WF_APP_TYPES.FILE_COMPONENT.NAME) {
                if (source.getAllEdges().length - 1 == 0) {
                    sourceData.outputPorts[source.id] = {};
                    sourceData.outputPorts[source.id][OSP.Constants.NAME] = source.id;
                }
            }
        }
    });

    function checkPortTypeForConnection(source, target, isFileComponent) {
        var sourceData = source.getNode().data,
            targetData = target.getNode().data;

        var sourcePortDataType = null;
        if (isFileComponent) {
            sourcePortDataType = sourceData.outputPorts[source.id].dataType_;
        } else {
            sourcePortDataType = sourceData[source.getType()][source.id].dataType_;
        }

        var targetPortDataType = targetData["inputPorts"][target.id].dataType_;
        var sourectDataTypeStr = sourcePortDataType.name + sourcePortDataType.version;
        var targetDataTypeStr = targetPortDataType.name + targetPortDataType.version;

        if (sourectDataTypeStr != targetDataTypeStr) {
            return false;
        } else {
            return true;
        }
    }

	function edgesHandler(){
		var edgesData = {};
		if(isDesigner){
			edgesData = {
				"default": {
					overlays: [
						["Label", {
							cssClass: "delete-relationship",
							label: "<i class='fa fa-times'></i>",
							events: {
								"tap": function (params) {
									currentJsPlumbInstance.removeEdge(params.edge);
								}
							}
						} ]
					]
				}
			}
		}
		return edgesData;
	}

    var view = {
        nodes: {
            "scienceApp": {
                template: "scienceApp-templete",
                events: {
                    dblclick: function(obj) {
                        if (!isDesigner && uiPanelInstance) {
                            uiPanelInstance.openNodeHandler(obj.node);
                        }
                    }
                }
            },
            "workflowApp": {
                template: "workflowApp-templete",
                events: {
                    dblclick: function(obj) {
                        if (isDesigner) {
                        	openWfAppDataSettingHandler(obj.node);
                        } else if (!isDesigner && uiPanelInstance) {
                            uiPanelInstance.openNodeHandler(obj.node);
                        }
                    }
                }
            }
        },
        groups: {
            "workflowGroup": {
                template: "workflowGroup-templete",
                autoSize: true,
                droppable: true,
                revert: false,
                orphan: true,
                constrain: false,
                layout: {
                    type: "Absolute"
                }
            }
        },
        ports: {
            "inputPorts": {
                template: "input-port-templete",
                anchor: ["Left"],
                events: {
                    click: function(obj) {
                        if (!isDesigner && uiPanelInstance) {
                            uiPanelInstance.openInputPortData(obj)
                        }
                    },
                    dblclick: function(obj) {
                        if (obj.port.getAllEdges().length == 0) {
                            var nodeId = obj.nodeId;
                            var portId = obj.portId;
                            var portType = obj.portType;
                            var nodeData = obj.node.data;
                            if (isDesigner && uiPanelInstance) {
                                uiPanelInstance.openWfAppFileDataSetting(nodeId, nodeData.scienceAppData.name, portId, portType);
                            } else if(!isDesigner){
                            	uiPanelInstance.openInputPortData(nodeId, portId);
                            }
                        } else {
                            return false;
                        }
                    }
                }
            },
            "outputPorts": {
                template: "output-port-templete",
                events: {
                    click: function(obj) {
                        if (!isDesigner && uiPanelInstance) {
                        	var status = obj.node.data.status.status;
                        	if(status != "WAITING" && status != "PAUSED" && status != "RUNNING"){
                        		uiPanelInstance.openOutputPortData(obj)
                        	} else {
                        		toastr['warning']('', 'No execution results.');
                        	}
                        }
                    },
                },
                anchor: ["Right"]
            },
            "sourceAll": {
                template: "wf-output-port-templete",
                anchor: ["Right"]
            },
            "targetAll": {
                template: "wf-input-port-templete",
                anchor: ["Left"]
            }
        },
        edges: edgesHandler()
    }

    function openWfAppDataSettingHandler(node) {
        var wfId = node.id;
        var data = node.data;
        var runType = data.scienceAppData.runType;
        if (uiPanelInstance && isDesigner) {
            if (runType != WF_APP_TYPES.FILE_COMPONENT.NAME) {
                uiPanelInstance.openWfAppDataSetting(wfId, runType, data.scienceAppData.name);
            } else {
                if (node.getAllEdges().length != 0) {
                    uiPanelInstance.openWfAppFileDataSetting(wfId, data.scienceAppData.name, WF_APP_TYPES.FILE_COMPONENT.OUTPUT_NAME, "outputPorts");
                } else {
                    toastr["error"]("", var_workflow_config_data_error_message);
                }
            }
        }
    }

    var canvasElement = document.querySelector("#wf-workflow-canvas");
    var renderer = wfWorkflowJsPlumbInstance.render({
        container: canvasElement,
        view: view,
        layout: {
            type: "Absolute"
        },
        events: {
            canvasClick: function(e) {
                wfWorkflowJsPlumbInstance.clearSelection();
            },
            groupAdded: function(group) {
            }
        },
        miniview: {
            container: "miniview"
        },
        enablePanButtons: false,
        zoomToFit: true,
        dragOptions: {
            start: function() {
                $(".menu-panel > .menu-panel-box-app").addClass("hidden");
            },
            stop: function() {
                $(".menu-panel > .menu-panel-box-app").removeClass("hidden");
            }
        },
        lassoFilter: ".controls, .controls *, .miniview, .miniview *",
        jsPlumb: {
        	Endpoint: "Blank",
            HoverPaintStyle: { strokeWidth: 5, stroke: "#FF6600" },
            PaintStyle: { strokeWidth: 3, stroke: "#445566" },
            Connector: "StateMachine",
            ConnectionOverlays: [
                ["Arrow", { location: 1, width: 15, length: 10 }]
            ],
        },
        elementsDraggable: isDesigner
    });

    var controls = canvasElement.querySelector(".controls");

    /* on home button click, zoom content to fit. */
    jsPlumb.on(controls, "tap", "[reset]", function() {
        renderer.setZoom(2.9, false);
        wfWorkflowJsPlumbInstance.clearSelection();
        renderer.zoomToFit();
    });

    /* listener for mode change on renderer. */
    renderer.bind("modeChanged", function(mode) {
        jsPlumb.removeClass(controls.querySelectorAll("[mode]"), "selected-mode");
        jsPlumb.addClass(controls.querySelectorAll("[mode='" + mode + "']"), "selected-mode");
    });

    renderer.bind("group:addMember", function(params) {
        if (params.group != true) {
            wfWorkflowJsPlumbInstance.clearSelection();
            renderer.sizeGroupToFit(params.group);
        }

        renderer.setZoom(2.9, false);
        renderer.zoomToFit();
    });

    /* pan mode/select mode */
    jsPlumb.on(controls, "tap", "[mode]", function() {
        renderer.setMode(this.getAttribute("mode"));
    });

    /* zoom-in/zoom-out function */
    jsPlumb.on(controls, "tap", "[zoom]", function() {
        zoomValue = this.getAttribute("zoom");
        zoomLevel = renderer.getZoom();
        if (zoomValue == "in" && zoomLevel < 2.9) {
            zoomLevel += 0.25;
            renderer.setZoom(zoomLevel, true);
        } else if (zoomValue == "out" && 0.5 < zoomLevel) {
            zoomLevel -= 0.25;
            renderer.setZoom(zoomLevel, true);
        }
    });


    jsPlumb.on(canvasElement, "tap", ".group-delete", function(e) {
        var info = wfWorkflowJsPlumbInstance.getObjectInfo(this);
        _confirm(var_remove_app_confirm, function() {
            wfWorkflowJsPlumbInstance.removeGroup(info.obj, true);
        });
    });

    var outputPortPoint = {
        endpoint: ["Rectangle", { width: 10, height: 10 }, { cssClass: "output-port" }],
        type: WF_JSPLUMB_TYPES.ENDPOINT + " " + WF_JSPLUMB_TYPES.OUTPUT,
        paintStyle: { fill: outputPortColor },
        isSource: false,
        maxConnections: -1,
        connector: ["Bezier", { curviness: 100 }],
        connectorStyle: {
            width: 5,
            stroke: connectionColor
        },
        ConnectionsDetachable: !isFixed,
        isTarget: false,
        dropOptions: portDropOption
    };

    var inputPortPoint = {
        endpoint: ["Rectangle", { width: 18, height: 18 }, { cssClass: "input-port" }],
        type: WF_JSPLUMB_TYPES.ENDPOINT + " " + WF_JSPLUMB_TYPES.INPUT,
        paintStyle: { fill: inputPortColor },
        isSource: false,
        isTarget: !isFixed,
        ConnectionsDetachable: !isFixed,
        beforeDrop: function(params) {
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
        if (element.hasType(WF_JSPLUMB_TYPES.ENDPOINT) &&
            element.hasType(WF_JSPLUMB_TYPES.INPUT) &&
            element.connections.length === 0) {
            /*call parent Div data*/
            var sciApp = $(element.getElement()).data();
            var port = element.getParameter("data");

            aSyncAjaxHelper.post("/delegate/services/app/" +
                sciApp["scienceAppId"] + "/inputports/editor/default", {
                    "name": port.dataType().name,
                    "version": port.dataType().version
                },
                function(result) {
                    var jsPlumbWindowId = element.elementId;
                });
        }
    }

    currentJsPlumbInstance = wfWorkflowJsPlumbInstance;

    var decision = new Decision(namespace, $, currentJsPlumbInstance);

    currentJsPlumbInstance.bind("dblclick", jsPlumbDblClickCallback);
    currentJsPlumbInstance.bind("connection", jsPlumbConnectionCallback);
    currentJsPlumbInstance.bind("connectionDetached", jsPlumbConnectionDetachedCallback);

    function addScienceApp(pageX, pageY, data) {
        if (data["appType"] && data["appType"] == WF_APP_TYPES.APP.NAME || data["appType"] && data["appType"] == WF_APP_TYPES.STATIC_CONVERTER.NAME) {
            drawScienceAppDiv(pageX, pageY, data);
        } else {
            if (data["appType"] == WF_APP_TYPES.GROUP.NAME) {
                drawGroupDiv(pageX, pageY, data);
            } else {
                drawWorkFlowAppDiv(pageX, pageY, data);
            }

        }
    }

    function drawController(target, pageX, pageY, data, wfId) {
        var html = '<div id="{{id}}" class="wf-box wf-controller ui-selectee">' +
            '  <svg xmlns="http://www.w3.org/2000/svg">' +
            '    <g class="fc-decision">' +
            '      <polygon points="0,60 75,0 150,60 75,120" class="fc-rhombus"></polygon>' +
            '      <text x="42" y="65">{{name}}</text>' +
            '    </g>' +
            '  </svg>' +
            '</div>';
        return $(Mustache.render(html, {
            "id": wfId,
            "name": data.name
        })).appendTo(target);
    }

    function drawScienceAppDiv(pageX, pageY, data, savedId) {
        var wfId = savedId ? savedId : getGUID();
        var parallelModule = data.parallelModule ? data.parallelModule : '';
        var scienceAppData = {
            scienceAppId: data.scienceAppId,
            runType: data.runType,
            parallelModule: parallelModule,
            name: data.name,
            version: data.version,
            text: data.text,
            defaultCpus: data.defaultCpus,
            maxCpus: data.maxCpus,
            minCpus: data.minCpus
        };

        var inputports = getScienceAppInputPort(data.scienceAppId);
        var inputPortsObj = new Object();

        if (!$.isEmptyObject(inputports)) {
            inputPortsObj = $.parseJSON(inputports);
        }

        var outputports = getScienceAppOutputPort(data.scienceAppId);
        var outputPortsObj = new Object();
        if (!$.isEmptyObject(outputports)) {
            outputPortsObj = $.parseJSON(outputports);
        }
        
        var logPorts = getScienceAppLogPort(data.scienceAppId);
        var logPortsObj = new Object();
        if (!$.isEmptyObject(outputports)) {
        	logPortsObj = $.parseJSON(logPorts);
        }

        var ibDataObj = {};

        var statusObj = {
            status: "WAITING"
        };

        currentJsPlumbInstance.addFactoryNode("scienceApp", {
            id: wfId,
            left: pageX,
            top: pageY,
            scienceAppData: scienceAppData,
            inputPorts: inputPortsObj,
            outputPorts: outputPortsObj,
            logPorts: logPortsObj,
            ibData: ibDataObj,
            status: statusObj,
            startPoint: false
        });

        var node = currentJsPlumbInstance.getNode(wfId);
        renderer.zoomToFit();
        console.log(JSON.stringify(currentJsPlumbInstance.exportData({ type: "json" })));
        return wfId;
    }


    function getNodeData(nodeId) {
        var node = currentJsPlumbInstance.getNode(nodeId);
        return node.data;
    }

    function setNodeData(nodeId, nodeData) {
        var node = currentJsPlumbInstance.getNode(nodeId);
        node["data"] = nodeData;
    }

    function setAppSampleData(nodeId, sampleData) {
        var nodeData = currentJsPlumbInstance.getNode(nodeId).data;
        if (sampleData.hasOwnProperty(OSP.Constants.ID)) {
            nodeData["inputData_"] = sampleData;
            nodeData["files"] = [sampleData[OSP.Constants.ID]];
        }
        wfBackgroupSave();
    }

    function setPortSampleData(nodeId, portType, portId, preFileId, defaultEditor, isWfSample, sampleData) {
        var nodeData = currentJsPlumbInstance.getNode(nodeId).data;
        var portData = nodeData[portType][portId];
        portData["defaultEditor_"] = defaultEditor;
        portData[OSP.Constants.IS_WF_SAMPLE] = isWfSample == 'true';
        if (sampleData.hasOwnProperty(OSP.Constants.CONTENT)) {
            portData[OSP.Constants.WF_SAMPLE] = sampleData;
            var nodeFiles = nodeData["files"];
            if (nodeFiles) {
                nodeFiles = nodeFiles.splice(nodeFiles.indexOf(preFileId), 1);
                nodeFiles.push(sampleData[OSP.Constants.CONTENT]);
                nodeData["files"] = nodeFiles;
            } else {
                nodeData["files"] = [sampleData[OSP.Constants.CONTENT]];
            }
        }

        wfBackgroupSave();
//        console.log(JSON.stringify(currentJsPlumbInstance.exportData({ type: "json" })));
    }
    function wfBackgroupSave() {
        var localWorkflow = modifyingWorkflow;
        var wfId = localWorkflow.workflowId;

        var wfDataJsonString = JSON.stringify(currentJsPlumbInstance.exportData({ type: "json" }));
        localWorkflow.screenLogic = wfDataJsonString;
        aSyncAjaxHelper.jsonPost("/delegate/services/workflows/" +
            localWorkflow.workflowId + "/update",
            JSON.stringify(localWorkflow),
            null);
    }

    function drawGroupDiv(pageX, pageY, data, savedId) {
        var wfId = savedId ? savedId : getGUID();
        currentJsPlumbInstance.addGroup({
            id: wfId,
            type: "workflowGroup",
            left: pageX,
            top: pageY,
            title: "Group " + (currentJsPlumbInstance.getGroupCount() + 1)
        });

        renderer.zoomToFit();
//        console.log(JSON.stringify(currentJsPlumbInstance.exportData({ type: "json" })));
    }

    function drawWorkFlowAppDiv(pageX, pageY, data, savedId) {
        var wfId = savedId ? savedId : getGUID();
        var scienceAppData = {
            runType: data.appType,
            name: data.name
        };

        currentJsPlumbInstance.addFactoryNode("workflowApp", {
            id: wfId,
            left: pageX,
            top: pageY,
            scienceAppData: scienceAppData,
            inputPorts: data.inputports,
            outputPorts: data.outputports,
            ibData: data.ibData,
            status: data.status
        });

        renderer.zoomToFit();
//        console.log(JSON.stringify(currentJsPlumbInstance.exportData({ type: "json" })));
    }

    function getScienceAppInputPort(scienceAppId) {
        var inputports = synchronousAjaxHelper.get("/delegate/services/app/" + scienceAppId + "/inputports");
        return inputports;
    }

    function addEndPointToController(wfId, portJson, isInputPort) {
        if (!$.isEmptyObject(portJson)) {
            var ports = getPortsArrayFromPortJson(portJson, isInputPort);

            var endPointType = isInputPort ? inputPortPoint : outputPortPoint;
            var anchors = isInputPort ? ["Top", [0.25, 0.25, 0, 0]] : ["Bottom", "Left"];
            var isModifiable = $(currentJsPlumbInstance.getContainer()).attr("id") == "wf-workflow-canvas";
            $.each(ports, function(_, port) {
                var connectionScope = port.dataType().name + "_" + port.dataType().version;
                if (isInputPort) {
                    connectionScope += " " + WF_APP_TYPES.DYNAMIC_CONVERTER.INPUT_SCOPE;
                    connectionScope += " " + WF_APP_TYPES.CONTROLLER.INPUT_SCOPE;
                } else {
                    connectionScope += " " + WF_APP_TYPES.DYNAMIC_CONVERTER.OUTPUT_SCOPE;
                    connectionScope += " " + WF_APP_TYPES.CONTROLLER.OUTPUT_SCOPE;
                    if (isModifiable) {
                        endPointType["isSource"] = true;
                    } else {
                        endPointType["isSource"] = false;
                    }
                }
                endPointType["scope"] = connectionScope;

                var labelLocation = isInputPort ? [-1 * ((port.name() + "").length / 5.7), 0.5] : [-0.6, 0.6];
                var endPointGuid = wfId + port.name() + isInputPort;
                currentJsPlumbInstance.addEndpoint(
                    wfId, {
                        anchor: anchors[_],
                        uuid: endPointGuid,
                        overlays: [
                            ["Label", {
                                label: port.name(),
                                location: labelLocation
                            }]
                        ]
                    },
                    endPointType).setParameter("data", port);
            });
        }
    }

    function getScienceAppOutputPort(scienceAppId) {
        var outputports = synchronousAjaxHelper.get("/delegate/services/app/" + scienceAppId + "/outputports");
        return outputports;
    }
    
    function getScienceAppLogPort(scienceAppId) {
        var logports = synchronousAjaxHelper.get("/delegate/services/app/" + scienceAppId + "/logports");
        return logports;
    }

    function getPortsArrayFromPortJson(portJsonObject, isInputPort) {
        var scienceApp = new OSP.ScienceApp();
        if (isInputPort) {
            scienceApp.inputPorts(scienceApp.deserializeInputPorts(portJsonObject));
            return scienceApp.inputPortsArray();
        } else {
            scienceApp.outputPorts(scienceApp.deserializeOutputPorts(portJsonObject));
            return scienceApp.outputPortsArray();
        }
    }

    function prepareEndpoint(appDivId, portJson, isInputPort) {
        var ports = getPortsArrayFromPortJson(portJson, isInputPort);
        var anchorUnit = (function(ports) {
            var anchorUnit = 0.7;
            if (ports && ports.length > 1) {
                anchorUnit = anchorUnit / (ports.length - 1);
            }
            return anchorUnit;
        })(ports);
        return function traversePortsAndAddEndPoint(jsPlumbInstance) {
            var endPointType = isInputPort ? inputPortPoint : outputPortPoint;
            var defaultAnchor = isInputPort ? [0, 0.15, -wjs1, 0] : [1, 0.15, 1, 0];
            var isModifiable = $(jsPlumbInstance.getContainer()).attr("id") == "wf-workflow-canvas";
            $.each(ports, function(_, port) {
                var connectionScope = port.dataType().name + "_" + port.dataType().version;
                if (isInputPort) {
                    connectionScope += " " + WF_APP_TYPES.CONTROLLER.INPUT_SCOPE;
                    connectionScope += " " + WF_APP_TYPES.DYNAMIC_CONVERTER.INPUT_SCOPE;
                } else {
                    connectionScope += " " + WF_APP_TYPES.CONTROLLER.OUTPUT_SCOPE;
                    connectionScope += " " + WF_APP_TYPES.DYNAMIC_CONVERTER.OUTPUT_SCOPE;
                    if (isModifiable) {
                        endPointType["isSource"] = true;
                    } else {
                        endPointType["isSource"] = false;
                    }
                }
                endPointType["scope"] = connectionScope;

                var labelLocation =
                    isInputPort ? [-1 * ((port.name() + "").length / 5.7), 0.5] : [9.5, 0.6];
                var endPointGuid = appDivId + port.name() + isInputPort;
                var endPoint = jsPlumbInstance.addEndpoint(
                    appDivId, {
                        anchor: defaultAnchor,
                        uuid: endPointGuid,
                        overlays: [
                            ["Label", {
                                label: isInputPort ? port.name() : "<p style=\"width:300px;margin-top:8px;\">" + labelAddSpace(port.outputData()) + "<p>",
                                location: labelLocation
                            }]
                        ]
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
        _confirm(var_remove_app_confirm, function() {
            $el.each(function(_) {
                var wfId = $(this).attr("id");
                var node = currentJsPlumbInstance.getNode(wfId);
                /* Delete Workflow SampleFiles */
                var fn = window[namespace + "deleteWfSampleFiles"];
                if (fn) {
                    fn.apply(null, [node.data]);
                }

                currentJsPlumbInstance.removeNode(node);

                if (node.data.type === WF_APP_TYPES.APP.NAME || node.data.type === WF_APP_TYPES.STATIC_CONVERTER.NAME) {

                } else {
                    if (node.data.scienceAppData.runType === WF_APP_TYPES.FILE_COMPONENT.NAME) {

                    }
                }
//                console.log(JSON.stringify(currentJsPlumbInstance.exportData({ type: "json" })));
            });
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
        if (data.appType === WF_APP_TYPES.CONTROLLER.NAME) {
            addEndPointToController(wfId, data.inputports, true);
            addEndPointToController(wfId, data.outputports, false);
        } else {
            loadScienceAppPort(wfId, data.inputports, true);
            loadScienceAppPort(wfId, data.outputports, false);
        }
        $("#" + conainerId + " #" + wfId).data(data);
    }

    $.contextMenu({
        selector: '.wf-box',
        determinePosition: function($menu) {
            $menu.css('display', 'block').position({ my: "left bottom", at: "center bottom", of: this, offset: "0 5" });
        },
        build: function($trigger, e) {
//            console.log($trigger);
            var wfWindowId = $trigger.attr("id");
            var appData = $trigger.data();

            var node = currentJsPlumbInstance.getNode(wfWindowId);
            var nodeData = node.data;
            var runType = nodeData.scienceAppData.runType;

            var cpuNumber = appData["cpuNumber"] ? appData["cpuNumber"] : "" + appData["defaultCpus"];
            var items = { items: {} };

            if(isDesigner) {
                if (runType == WF_APP_TYPES.DYNAMIC_CONVERTER.NAME) {
                    items["items"]["open-texteditor"] = {
                        name: "Converter Script",
                        icon: "edit",
                        callback: function(key, options) {
                            openWfAppDataSettingHandler(node);
                        }
                    };
                } else if (runType == WF_APP_TYPES.CONTROLLER.NAME) {
                    items["items"]["open-texteditor"] = {
                        name: "Condition Script",
                        icon: "edit",
                        callback: function(key, options) {
                            openWfAppDataSettingHandler(node);
                        }
                    };
                } else if (runType == WF_APP_TYPES.FILE_COMPONENT.NAME) {
                    items["items"]["open-texteditor"] = {
                        name: "Cofiguration Data",
                        icon: "fa-cogs",
                        callback: function(key, options) {
                            openWfAppDataSettingHandler(node);
                        }
                    };
                }
            }

            if (nodeData["type"]) {
                var type = nodeData["type"];
                if (type === "scienceApp") {
                    items["items"]["open-info"] = {
                        name: "App Information",
                        icon: "info",
                        callback: function(key, options) {
                            var scienceAppId = node.data.scienceAppData.scienceAppId;
                            var fn = window[namespace + "openSolverDeatilPopup"];
                            if (fn) {
                                fn.apply(null, [scienceAppId]);
                            }
                        }
                    };
                    if (!isDesigner && uiPanelInstance) {
                    	var isReusableNode = node.data.status.status == CONSTS.WF_STATUS_CODE.SUCCESS ? true : false;
                    	if(uiPanelInstance.isReUsableJob() || isReusableNode){
                    		if(uiPanelInstance.isReUsableNode(node) || isReusableNode){
                    			var isReUseNode = (!!node && !!node.data && !!node.data.isReUseNode)
                    			items["items"]["open-reuse-handler"] = {
                    				name: isReUseNode ? "Do not reuse" : "ReUse",
                    						icon: isReUseNode ? "fa-ban" : "fa-recycle",
                    								callback: function(key, options) {
                    									uiPanelInstance.setReuseNode(node, !isReUseNode)
                    								}
                    			}
                    		}
                    	}
                        if(uiPanelInstance.isPauseAbleNode(node)){
                            var status = node.data.status.status
                            if(status != CONSTS.WF_STATUS_CODE.SUCCESS){
                            	items["items"]["open-pause-handler"] = {
                            			name: "Pause",
                            			icon: "fa-pause-circle",
                            			callback: function(key, options) {
                            				uiPanelInstance.pauseNode(node, true)
                            			}
                            	}
                            }
                        } else if(uiPanelInstance.isResumeAbleNode(node)) {
                            var status = node.data.status.status
                            items["items"]["open-pause-handler"] = {
                                name: "Resume",
                                icon: "fa-play-circle",
                                callback: function(key, options) {
                                    uiPanelInstance.pauseNode(node, false)
                                }
                            }
                        }
                    }
                }else if (type === "workflowApp") {
                    if (!isDesigner && uiPanelInstance) {
                        items["items"]["open-handler"] = {
                            name: "Open Port",
                            icon: "fa-cogs",
                            callback: function(key, options) {
                                uiPanelInstance.openNodeHandler(node)
                            }
                        }
                    }
                }
            }

            if (nodeData.startPoint) {
                items["items"]["is-start-point"] = {
                    name: "Start Point",
                    icon: "fa-check-square",
                    disabled: true
                };
                items["items"]["sep0"] = "---------";
            }

            if (!appData.workflowStatus) {
                if (nodeData.startPoint) {
                    items["items"]["start-point"] = {
                        name: "Remove Start Point",
                        icon: "fa-remove",
                        callback: function(key, options) {
                            nodeData = node.data;
                            nodeData.startPoint = false;

                            $("#" + node.id).removeClass("true");
                        }
                    };

                }

                if (runType !== WF_APP_TYPES.CONTROLLER.NAME && runType !== WF_APP_TYPES.FILE_COMPONENT.NAME && !nodeData.startPoint) {
                    items["items"]["start-point"] = {
                        name: "Start Point",
                        icon: "fa-play",
                        callback: function(key, options) {
                            nodeData = node.data;
                            nodeData.startPoint = true;

                            $("#" + node.id).addClass("true");
                        }
                    };
                }

                if (runType === "Parallel") {
                    items["items"]["mpi-title"] = {
                        name: "MPI Setting",
                        icon: "edit",
                        disabled: true
                    };
                    items["items"]["mpi-input"] = {
                        name: "Cpu Number (scope : " + nodeData.scienceAppData.minCpus + " ~ " + nodeData.scienceAppData.maxCpus + ")",
                        type: 'text',
                        value: nodeData.scienceAppData.defaultCpus,
                        events: {
                            keyup: function(e) {
                                nodeData.scienceAppData.defaultCpus = $(this).val();
                            }
                        }
                    };
                    items["items"]["sep1"] = "---------";
                }
            } else {
                items["items"]["reset"] = {
                    name: "Reset Simulation",
                    icon: "fa-eraser",
                    callback: function(key, options) {
                        delete appData["workflowStatus"];
                        $trigger.removeClass("donebox runningbox failbox").addClass("waitingbox reset");
                        $trigger.find(".addIp").text("Waiting")
                        $trigger.attr("id", getGUID());
                    }
                };
            }

            if (!isFixed) {
                items["items"]["delete"] = {
                    name: "Delete App",
                    icon: "delete",
                    callback: function(key, options) {
                        if ($(".ui-selected").length > 0) {
                            currentJsPlumbInstance.removeFromPosse($(".wf-box"), "posse");
                            removeSicenceApps($(".ui-selected"));
                        } else {
                            removeSicenceApps($(this));
                        }
                    }
                };
            }

            /* 2019.01.03 _ Add Context menu('Open Workbench') in execute page */
            if (!isDesigner && runType != WF_APP_TYPES.CONTROLLER.NAME &&
                runType != WF_APP_TYPES.FILE_COMPONENT.NAME &&
                runType != WF_APP_TYPES.DYNAMIC_CONVERTER.NAME) {
                items["items"]["open-workbench"] = {
                    name: "Open Workbench",
                    icon: "fa-external-link",
                    callback: function(key, options) {
                        uiPanelInstance.openScienceAppWorkbench(node);
                    }
                };
                
                /* 2019.03.08 _ Add system log dialog and log file */
                var nodeStatus = node.data.status.status;
                if(nodeStatus == CONSTS.WF_STATUS_CODE.SUCCESS || 
                		nodeStatus == CONSTS.WF_STATUS_CODE.DONE ||
                		nodeStatus == CONSTS.WF_STATUS_CODE.FAILED  ||
                		nodeStatus == CONSTS.WF_STATUS_CODE.RUNNING) {
                	items["items"]["system-log"] = {
                        name: "System Log",
                        icon: "fa-bar-chart",
                        callback: function(key, options) {
                            var simulationUuid = node.data[CONSTS.WF_NODE_CODE.IB_DATA][CONSTS.WF_NODE_CODE.IB_SIM_UUID];
                        	var jobUuid = node.data[CONSTS.WF_NODE_CODE.IB_DATA][CONSTS.WF_NODE_CODE.IB_UUID];
                        	
                            uiPanelInstance.jobSystemLog(simulationUuid, jobUuid, 0, 'out');
                        }
                    };
                	
                	items["items"]["log-file-download"] = {
                        name: "Download",
                        icon: "fa-cloud-download",
                        callback: function(key, options) {
                        	var simulationUuid = node.data[CONSTS.WF_NODE_CODE.IB_DATA][CONSTS.WF_NODE_CODE.IB_SIM_UUID];
                        	var jobUuid = node.data[CONSTS.WF_NODE_CODE.IB_DATA][CONSTS.WF_NODE_CODE.IB_UUID];
                        	
                        	uiPanelInstance.jobResultFileView(simulationUuid, jobUuid);
                        }
                    };
                }
            }
            
            return items;
        }
    });

    function getWorkflowDefinition(currentJsPlumbInstance) {
        var wfData = { elements: [], connections: [], outPorts: {} };
        if (wfPortletGlobalData) {
            wfData["wfPortletGlobalData"] = wfPortletGlobalData;
        }

        $(currentJsPlumbInstance.getContainer()).children(".wf-box").each(function() {
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
                if (wfPortletGlobalData["wfElements"]["" + scienceAppClientId] &&
                    wfPortletGlobalData["wfElements"]["" + scienceAppClientId]["converter-script"]) {
                    thisData["inputports"]["script"] = {
                        "input-value": wfPortletGlobalData["wfElements"]["" +
                            scienceAppClientId
                        ]["converter-script"]["input-value"],
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

        var switcherFiles = getSwticherFiles(currentJsPlumbInstance.getAllConnections(), wfData.elements);

        $.each(currentJsPlumbInstance.getAllConnections(), function(idx, connection) {
            var sourceOutputPort = connection.endpoints[0].getParameter("data");
            var targetInputPort = connection.endpoints[1].getParameter("data");
            //             console.log("sourceOutputPort ", sourceOutputPort);
            //             console.log("targetInputPort ", targetInputPort);
            wfData.connections.push({
                connectionId: connection.id,
                pageSourceId: connection.sourceId,
                pageTargetId: connection.targetId,
                sourceUuid: connection.endpoints[0].getUuid(),
                targetUuid: connection.endpoints[1].getUuid()
            });

            var targetInputPortName = "cmd" + targetInputPort.name();
            if (!wfData.outPorts[connection.sourceId]) {
                wfData.outPorts[connection.sourceId] = { outPort: {}, outPortFile: {} };
            }
            if (wfData.outPorts[connection.sourceId].outPort[targetInputPortName]) {
                wfData.outPorts[connection.sourceId].outPort[targetInputPortName].push(connection.targetId);
            } else {
                wfData.outPorts[connection.sourceId].outPort[targetInputPortName] = [connection.targetId];
                if (switcherFiles[connection.sourceId]) {
                    wfData.outPorts[connection.sourceId].outPortFile[targetInputPortName] = switcherFiles[connection.sourceId];
                } else {
                    wfData.outPorts[connection.sourceId].outPortFile[targetInputPortName] = sourceOutputPort.outputData().name();
                }
            }
        });

        consoleLog.info("before saving!");
        consoleLog.info(wfData);
        return wfData;
    }

    function getSwticherFiles(connections, elements) {
        var switcherFiles = {};
        $.each(connections, function(idx, connection) {
            var sourceOutputPort = connection.endpoints[0].getParameter("data");
            var targetInputPort = connection.endpoints[1].getParameter("data");
            if (amIController(elements, connection.targetId) && targetInputPort.name() == "transfer") {
                switcherFiles[connection.targetId] = sourceOutputPort.outputData().name();
            }
        });
        return switcherFiles;
    }

    function amIController(elements, clientId) {
        var amI = false;
        $.each(elements, function(i) {
            if (this.id == clientId) {
                amI = true;
            }
        });
        return amI;
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
            function(workflowData) {
                toastr["success"]("", var_save_success_message);
                if (callback) {
                    callback(workflowData);
                }
            });
    }

    function saveOrUpdateWorkflowDefinition(workflowMetaData, callback, backgroudSave) {
        var localWorkflow = modifyingWorkflow;
        var title = workflowMetaData.title;

        /* validation */
        if (!title || title === "" || title.trim() === "") {
            toastr["error"]("", var_create_first_message);
            return false;
        }

        /* 2018.12.24 _ Save Workflow Data */
        var wfDataJsonString = JSON.stringify(currentJsPlumbInstance.exportData({ type: "json" }));
        if (localWorkflow) {
            localWorkflow.title = title;
            localWorkflow.description = workflowMetaData.description;
            localWorkflow.screenLogic = wfDataJsonString;
            if (workflowMetaData.isPublic) {
                localWorkflow.isPublic = workflowMetaData.isPublic;
            }
            aSyncAjaxHelper.jsonPost("/delegate/services/workflows/" +
                localWorkflow.workflowId + "/update",
                JSON.stringify(localWorkflow),
                function(workflowData) {
                    afterSave(workflowData, callback, backgroudSave);
                });
        } else {
            aSyncAjaxHelper
                .post("/delegate/services/workflows/add", {
                    title: title,
                    description: workflowMetaData.description,
                    screenLogic: wfDataJsonString
                }, function(workflowData) {
                    afterSave(workflowData, callback, backgroudSave);
                }, function(errorMessage) {
                    toastr["error"]("", errorMessage);
                });
        }
    }

    function saveAsWorkflowDefinition(workflowMetaData, callback) {
        if (!modifyingWorkflow) {
            saveOrUpdateWorkflowDefinition(workflowMetaData, callback, false);
            return false;
        } else {
            var currentWorkflowId = modifyingWorkflow["workflowId"];
            var wfDataJsonString = JSON.stringify(currentJsPlumbInstance.exportData({ type: "json" }));

            aSyncAjaxHelper
                .post("/delegate/services/workflows/" + currentWorkflowId + "/saveas", {
                    title: workflowMetaData.title,
                    description: workflowMetaData.description,
                    screenLogic: wfDataJsonString
                }, function(workflowData) {
                    if (callback) {
                        callback(workflowData.workflowId);
                    }
                    toastr["success"]("", var_save_success_message);
                    modifyingWorkflow = workflowData;
                }, function(msg) {
                    toastr["error"]("", msg);
                });
        }
    }

    function deleteWorkflowDefinition(workflowId, callback, screenLogic) {
        deleteWorkflowWfFiles(screenLogic);
        aSyncAjaxHelper.jsonPost("/delegate/services/workflows/" + workflowId + "/delete", {}, function(_) {
            if (callback) {
                callback();
            }
        });
    }

    function deleteWorkflowDefinitionWithScienceApp(workflowId, scienceAppId, callback, screenLogic) {
        deleteWorkflowWfFiles(screenLogic);
        aSyncAjaxHelper.jsonPost("/delegate/services/workflows/" + workflowId + "/app/" + scienceAppId + "/delete", {}, function(_) {
            if (callback) {
                callback();
            }
        });
    }

    /* 2018.12.31 _ Delete wfFiles in Workflow Nodes */
    function deleteWorkflowWfFiles(screenLogic) {
        var wfNodes = screenLogic.nodes;

        for (var i = 0; i < wfNodes.length; i++) {
            var nodeData = wfNodes[i];

            var fn = window[namespace + "deleteWfSampleFiles"];
            if (fn) {
                fn.apply(null, [nodeData]);
            }
        }
    }

    function duplicateWorkflowDefinition(workflowId, workflowTitle, callback) {
        var param = workflowTitle ? { "title": workflowTitle } : {};
        aSyncAjaxHelper
            .jsonPost("/delegate/services/workflows/" + workflowId + "/copy",
                JSON.stringify(param),
                function(_) {
                    if (callback) {
                        callback(_);
                    }
                });
    }

    function loadWorkflowDefinition(workflowId, fnCallback) {
        aSyncAjaxHelper.get("/delegate/services/workflows/" + workflowId,
            function(workflow) {
                if (fnCallback) {
                    fnCallback(workflow);
                }
                drawWorkflowDefinition(workflow);
            },
            function() {
                toastr["error"]("", "load Workflow Definition fail.");
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

    function drawScreenLogic(screenLogic) {
        var wfData = $.parseJSON(screenLogic);

        if (!isDesigner) {
            turnOnBeforeConnect(currentJsPlumbInstance);
        }

        /* 2018.12.24 _ Open workflow, jsplumb */
        currentJsPlumbInstance.load({
            type: "json",
            data: wfData
        });

        if (!isDesigner) {
            turnOffBeforeConnect(currentJsPlumbInstance);
        }else{
        	renderer.zoomToFit();
        }
    }

    function resetCurrentJsPlumbInstance() {
        /* 2018.12.24 _ Clear Nodes */
        currentJsPlumbInstance.clear();
    }

    function resetWorkflow() {
        resetCurrentJsPlumbInstance();
        currentJsPlumbInstance.bind("dblclick", jsPlumbDblClickCallback);
        currentJsPlumbInstance.bind("connectionDetached", jsPlumbConnectionDetachedCallback);
        currentJsPlumbInstance.bind("connection", jsPlumbConnectionCallback);
        wfPortletGlobalData.wfElements = {};
        modifyingWorkflow = undefined;
    }
    
    function allNodesPause(){
    	$.each(currentJsPlumbInstance.getNodes(), function(){
    		var node = this;
    		uiPanelInstance.pauseNode(node, true, null, false);
    	});
    }

    return {
    	"allNodesPause": allNodesPause,
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
        "deleteWorkflowDefinitionWithScienceApp": deleteWorkflowDefinitionWithScienceApp,
        "drawWorkflowDefinition": drawWorkflowDefinition,
        "resetWorkflow": resetWorkflow,
        "getScienceAppLogPort": getScienceAppLogPort,
        "getWfPortletGlobalData": function() {
            return wfPortletGlobalData;
        },
        "getCurrentJsPlumbContainerId": function() {
            //            return $(currentJsPlumbInstance.getContainer()).attr("id");
            return "";
        },
        "getCurrentJsPlumbInstance": function() {
            return currentJsPlumbInstance;
        },
        "getCurrentJsPlumbRenderer": function() {
            return renderer;
        },
        "setUiPanelInstance": setUiPanelInstance,
        "getNodeData": getNodeData,
        "setNodeData": setNodeData,
        "setPortSampleData": setPortSampleData,
        "setAppSampleData": setAppSampleData,
        "turnOnBeforeConnect": turnOnBeforeConnect,
        "turnOffBeforeConnect": turnOffBeforeConnect
    };
});
