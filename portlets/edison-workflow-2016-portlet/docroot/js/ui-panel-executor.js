var UIPanelExecutor = (function (namespace, $, designer, executor, toastr) {
    /*jshint -W069 */
    'use strict';
    var uiPanelDesigner = new UIPanel(namespace, $, designer, toastr, undefined);
    var currNodes = eStruct("id")
    var currSimulations = eStruct("simulationId")
    var currJobs = eStruct("id", "data")
    var currInputPorts = eStruct("id")
    var currOutputPorts = eStruct("id")
    var currOpenPort = eMap()
    var currPageJob = 1
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
        }, "new-job": {
            "col": 6,
            "panel-type": "new-job",
            "body": "tpl-menu-panel-new",
            "form": {},
            "btn": {
                "create": newSimulationJob
            }
        },"open": {
            "col": 10,
            "panel-type": "open",
            "body": "tpl-menu-panel-load",
            "header": {
                "id": "tpl-menu-panel-search-header",
                "search-input-name": "search",
                "theads": ["Title", "User", "Created"]
            },
            "form": {},
            "footer": {
                "id": "tpl-menu-panel-pagination",
                "btns": [
                    { "name": "New", "func": openNewSimulationPanel },
                    { "name": "Open", "func": openSimulation },
                    { "name": "Rename", "func": renameSimulation },
                    { "name": "Delete", "func": deleteSimulation }
                ]
            }
        }, "jobs": {
            "col": 4,
            "panel-type": "jobs",
            "header":{
                "id": "tpl-menu-panel-search-header",
                "search-input-name": "search"
            },
            "form": {}
        }, "status": {
            "col": 10,
            "panel-type": "status",
            "body": "tpl-menu-panel-load",
            "header": {
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
        }, "setting": {
            "col": 6,
            "panel-type": "setting",
            "body": "tpl-menu-panel-setting",
            "form": {},
            "btn": {
                "rename": renameSimulation,
                "delete": deleteSimulation
            }
        }, "job-setting": {
            "col": 6,
            "panel-type": "setting",
            "body": "tpl-job-panel-setting",
            "form": {},
            "btn": {
                "update": renameSimulationJobInPanel,
                "copy": copySimulationJobInPanel,
                "delete": deleteSimulationJobInPanel
            }
        }
    };

    /////////////////////////////////////////// renew start
    function isReUsableNode(node) {
        if (node && node.data && node.data.status &&
            node.data.status.status === CONSTS.WF_STATUS_CODE.COMPLETED) {
            return true
        } else {
            return false
        }
    }

    function consoleNodeInfo(node) {
        console.log(node)
    }

    function isPauseAbleNode(node) {
        var currJob = currJobs.selected()
        if (currJob && node.data && node.data.status &&
            currJob.status !== CONSTS.WF_STATUS_CODE.PAUSED &&
            currJob.status !== CONSTS.WF_STATUS_CODE.FAILED &&
            currJob.status !== CONSTS.WF_STATUS_CODE.SUCCESS &&
            node.data.status.pause !== 'pause') {
            return true
        } else {
            return false
        }
    }

    function isResumeAbleNode(node) {
        var currJob = currJobs.selected()
        if (currJob && node.data && node.data.status &&
            currJob.status !== '' &&
            currJob.status !== CONSTS.WF_STATUS_CODE.FAILED &&
            currJob.status !== CONSTS.WF_STATUS_CODE.SUCCESS &&
            node.data.status.status === CONSTS.WF_STATUS_CODE.PAUSED) {
            return true
        } else {
            return false
        }
    }
    
    /* Reuse node status setting for reuse copy job */
    function setReUseNodeStatusByReuseCopy(node) {
        if (node && !!node.isReUseNode) {
            $('#' + node.id).addClass('is-re-use-node')
            $('#' + node.id + ' .top-cog-icon').removeClass('fa-cog').addClass('fa-recycle')
        } else {
            $('#' + node.id).removeClass('is-re-use-node')
            $('#' + node.id + ' .top-cog-icon').removeClass('fa-recycle').addClass('fa-cog')
        }
    }
    
    function setReUseNodeStatus(node) {
        if (node && node.data && !!node.data.isReUseNode) {
            $('#' + node.id).addClass('is-re-use-node')
            $('#' + node.id + ' .top-cog-icon').removeClass('fa-cog').addClass('fa-recycle')
        } else {
            $('#' + node.id).removeClass('is-re-use-node')
            $('#' + node.id + ' .top-cog-icon').removeClass('fa-recycle').addClass('fa-cog')
        }
    }

    function setReuseNode(node, isReUsable) {
        if (node) {
            node.data.isReUseNode = !!isReUsable
            setReUseNodeStatus(node)
            
            if(!!isReUsable){
            	setReuseParentNodes(node);
            }
        }
    }
    
    function reuseNodeCheck(){
    	/* reuse copy btn */
		if($(".is-re-use-node").length > 0){
			$("#p_p_id" + namespace + " .reuse-copy-btn").show();
		} else {
			$("#p_p_id" + namespace + " .reuse-copy-btn").hide();
		}
    }
    
    /* 2019.02.25 _ Set reuse flag in parentNodes */
    function setReuseParentNodes(node){
    	var parentNodes = node.data.status.parentNodes;
    	if(parentNodes.length > 0){
    		for(var idx in parentNodes){
    			var parentNodeId = parentNodes[idx];
    			var parentNode = currNodes.get(parentNodeId);
    			if(!parentNode.data.isReUseNode){
    				setReuseNode(parentNode, true);
    			}
    		}
    	}
    }

    function insertIbUuid(node) {
        if (node.data && node.data[CONSTS.WF_NODE_CODE.WORKBENCH_DATA]) {
            var job = currJobs.selected()
            var nodeId = node.id
            var params = {
                simulationJobId: job.simulationJobId,
                ibSimUuid: node.data[CONSTS.WF_NODE_CODE.WORKBENCH_DATA].ibSimUuid,
                ibJobUuid: node.data[CONSTS.WF_NODE_CODE.WORKBENCH_DATA].ibUuid,
            }
            if(node.data.status.jobs && node.data.status.jobs[0] &&
                (node.data.status.jobs[0].status === CONSTS.WF_STATUS_CODE.PAUSED ||
                    node.data.status.jobs[0].status === CONSTS.WF_STATUS_CODE.CREATED)) {
                params.jobUuid = node.data.status.jobs[0].uuid
                bStart()
                _delay(function() {
                    executor.insertIbUuid(params, function () {
                        bEnd()
                        resume()
                    })
                }, 2000)
            } else {
                updateAncestorReuse(node, true)
                rerun(nodeId, function(nodes) {
                    $.each(nodes, function(i, iNode) {
                        if (iNode.data.status && iNode.data.status.status &&
                            iNode.data.status.pause === 'pause') {
                            params.jobUuid = iNode.data.status.jobs[0].uuid
                            executor.insertIbUuid(params, function () {
                                resume()
                            })
                        }
                    })
                })
            }
            if(console) {
                console.log('ib insertion params')
                console.log(params)
            }
        }
    }

    function updateAncestorReuse(node, isFirstNode){
        if(isReUsableNode(node)) {
            if(!isFirstNode) {
                setReuseNode(node, true)
            }
            var jp = designer.getCurrentJsPlumbInstance()
            var prevNodes = []
            if(node.data && node.data.arrInputPorts) {
                $.each(node.data.arrInputPorts, function(i, port){
                    var sourcePort = jp.getPort(port.id)
                    $.each(sourcePort.getAllEdges(), function(i, edge){
                        if (edge.source && edge.source.getNode()) {
                            var parentNode = edge.source.getNode()
                            if($.inArray(parentNode.id, prevNodes) === -1){
                                prevNodes.push(parentNode.id)
                                updateAncestorReuse(parentNode)
                            }
                        }
                    })
                })
            }
        }
    }

    function pauseNode(node, pause, callback, isMsg) {
    	isMsg = (isMsg == true || !isMsg) ?  true : false;
    	
        var currJob = currJobs.selected()
        if (isPauseAbleNode(node) && currJob && node.data.status && pause) {
            if(node.data.status === '' || node.data.status === CONSTS.WF_STATUS_CODE.CREATED) {
                node.data.status.pause = 'pause'
                node.data.pause = 'pause'
            }else {
                executor.pauseSingleNode(currJob.simulationJobId, node.data.status.uuid,
                    function (status) {
                		if(!isMsg){
                			toastr["success"]("", CONSTS.MESSAGE.edison_wfsimulation_pause_success_message)
                		}
                        updateNodeStatus(status)
                        executor.updateStatus(currJob.simulationJobId, status, updateNodeStatus)
                        if(callback) {
                            callback()
                        }
                    },
                    function () {
                        toastr["error"]("", CONSTS.MESSAGE.edison_wfsimulation_pause_fail_message);
                    })
            }
        }else if (isResumeAbleNode(node) && currJob && node.data.status && !pause){
            executor.resumeSingleNode(currJob.simulationJobId, node.data.status.uuid, function (status) {
                    toastr["success"]("", CONSTS.MESSAGE.edison_wfsimulation_resume_success_message)
                    updateNodeStatus(status)
                    executor.updateStatus(currJob.simulationJobId, status, updateNodeStatus)
                    if(callback) {
                        callback()
                    }
                },
                function () {
                    toastr["error"]("", CONSTS.MESSAGE.edison_wfsimulation_resume_fail_message);
                })
        }else {
            toastr['error']('', pause ? 'Cannot pause' : 'Cannot resume')
        }
    }
    
    function isDataComponentNode(node) {
        if(node && node.data && node.data.scienceAppData &&
            node.data.scienceAppData.runType === CONSTS.WF_APP_TYPES.FILE_COMPONENT.NAME){
            return true
        }else{
            return false
        }
    }

    function openNodeHandler(node) {
        if(isDataComponentNode(node)){
            dataComponentHandler(node)
        }else {
            if (node.data && node.data.scienceAppData && node.data.scienceAppData.runType &&
                node.data.scienceAppData.runType === CONSTS.WF_APP_TYPES.DYNAMIC_CONVERTER.NAME) {
                uiPanelDesigner.openWfAppDataSetting(
                    node.id, node.data.scienceAppData.runType , node.data.scienceAppData.name);
            }
            consoleNodeInfo(node)
        }
    }

    function setDataComponent(node) {
        if(isDataComponentNode(node)) {
            node.data.dataChildren = []
            $.each(node.getAllEdges(), function (i, edge) {
                var targetPort = edge.target
                if (targetPort) {
                    var portName = targetPort.data.id
                    var targetNode = targetPort.getNode()
                    if (targetNode.data.inputPorts) {
                        var portId = targetNode.data.inputPorts[portName].id
                        var portData = currInputPorts.get(portId)
                        portData["dataComponentNodeId"] = node.id
                        node.data.dataChildren.push(portId)
                    }
                }
            })
        }
    }

    function dataComponentHandler(node) {
        if(node && node.data.dataChildren && node.data.dataChildren.length > 0) {
            var portId = node.data.dataChildren[0]
            var nodeId = portId.split('.')[0]
            openInputPort(nodeId, portId, true)
        }else {
            toastr['warning']('', 'There are no connected port')
        }
    }

    function createPanel(boxTitle, templateData, btnType) {
        if(currSimulations.selected() && currSimulations.selected().id) {
            currSimulations.select(currSimulations.selected().id)
        }
        templateData.boxtitle = boxTitle;
        $("#" + namespace + "menu-panel-box").show();
        $("#" + namespace + "menu-panel-box").empty().mustache('tpl-menu-panel-box', templateData);
        $("#" + namespace + "menu-panel-box .box-body").mustache(templateData.body, templateData);
        if (templateData.header) {
            var boxTitleSelecotr = "#" + namespace + "menu-panel-box .box-header.with-border.header-inner > .box-title";
            $(boxTitleSelecotr).replaceWith($.Mustache.render(templateData.header.id, templateData));
            var _delay600 = _instantDelay(600);
            $(boxTitleSelecotr + " > .search-input").keyup(function (e) {
                _delay600(loadPaginatedSimulations, btnType);
            });
        }

        if (templateData.footer) {
            $("#" + namespace + "menu-panel-box .box")
                .append($.Mustache.render(templateData.footer.id, templateData));
        }

        $(".menu-panel .menu-panel-close").click(function (e) {
            e.preventDefault();
            closePanel();
        });

        $("#" + namespace + "menu-panel-box .data-binded").each(function() {
            var that = this
            $(that).change(function (e) {
                var thisValue = $(this).val();
                var thisName = $(this).attr("name");
                templateData.form[thisName] = thisValue;
            });

            if (templateData.btn && templateData.btn["create"]) {
                _enterkey(that, templateData.btn["create"]);
            } else if (templateData.btn && templateData.btn["save"]) {
                _enterkey(that, templateData.btn["save"]);
            } else if (templateData.btn && templateData.btn["update"]) {
                _enterkey(that, templateData.btn["update"]);
            }
        })

        $("#" + namespace + "menu-panel-box .func").each(function (_) {
            var thisName = $(this).attr("name");
            if (templateData.btn && templateData.btn[thisName]) {
                $(this).click(function (e) {
                    templateData.btn[thisName](btnType, this, e);
                });
            }
        });
    }

    $(JQ_PORTLET_BOUNDARY_ID + " .top-btn").click(function (e) {
        e.preventDefault();
        var btnType = $(this).attr("data-btn-type");
        var templateData = PANEL_DATA[btnType];

        if(btnType === "designer"){
            var fn = window[namespace + "moveToDesigner"];
            fn.apply();
        }

        if (btnType === 'new-job') {
            if (_isEmpty(currSimulations.selected(),
                CONSTS.MESSAGE.edison_wfsimulation_select_first_message)) {
                return false
            } else {
                executor.fetchSimulationJobSeq(
                    currSimulations.selected().simulationId,
                    function (seqMap) {
                        setTimeout(function () {
                            $('.new-job #title').val(seqMap.seq)
                            $('.new-job #title').focus()
                            templateData.form.title = seqMap.seq
                        }, 100)
                    })
            }
        }

        if (templateData) {
            var boxTitle = btnType === 'new-job' ? 'New Simulation Job' : $(this).text();
            activateLi(this);
            /* 2019.01.16 _ simulationTitle setting in edit panel */
            if (btnType === 'setting') {
            	var currSimulationId = templateData.form.simulationId;
            	var simulation = currSimulations.get(currSimulationId);
            	templateData.form["simulationTitle"] = simulation.title;
            }
            createPanel(boxTitle, templateData, btnType);
            if (btnType === 'open') {
                loadPaginatedSimulations(btnType);
            }

            if($(this).hasClass("menu-open")){
                $(".menu-panel").toggle('slide', { direction: 'left' }, 500);
            }else{
                $(".menu-panel").show('slide', { direction: 'left' }, 500);
            }
            $(JQ_PORTLET_BOUNDARY_ID + " li.top-btn").removeClass("menu-open");
            $(this).addClass("menu-open")
        }
    });

    function getParams(workflowId, searchKeyword, currentPage, linePerPage) {
        var params = {};
        if (workflowId) {
            params.workflowId =workflowId;
        }
        if (searchKeyword || searchKeyword === 0) {
            params.title = searchKeyword;
        }
        if (currentPage) {
            params.p_curPage = currentPage;
        }
        if (linePerPage) {
            params.linePerPage = linePerPage;
        }
        return params;
    }

    function loadPaginatedSimulations(panelType, currentPage, callback){
        var workflowId = getMetaData().workflowId;
        currentPage = currentPage || 1;
        var templateData = PANEL_DATA.open;
        var params = getParams(
            workflowId,
            getValueByInputName(
                templateData.header["search-input-name"]),
            currentPage);
        templateData.form.params = params;

        fetchPaginatedSimulations(params, callback);
    }

    function fetchPaginatedSimulations(params, callback) {
        aSyncAjaxHelper.post("/delegate/services/simulation/list",
            params,
            function (paginatedSimulations) {
                if (paginatedSimulations.simulations && paginatedSimulations.simulations.length > 0) {
                    currSimulations.set(paginatedSimulations.simulations,
                        function (id) {
                            if(id) {
                                var simulation = currSimulations.get(id)
                                PANEL_DATA.setting.form.simulationTitle = simulation.title
                                PANEL_DATA.setting.form.simulationId = id
                                PANEL_DATA.open.form.selected = id
                            }
                        });
                } else {
                    currSimulations.set([])
                }
                renderSimulationTable(paginatedSimulations)
                if(callback) {
                   _delay(callback, 1)
                }
            },
            function (msg) {
                toastr["error"]("", msg);
            });
    }

    function renderSimulationTable(simulationsMap) {
        var tbodyTemplate =
            '{{#rows}}'+
            '    <tr simulation-id="{{simulationId}}">'+
            '        <td>{{title}}</td>'+
            '        <td>{{userName}}</td>'+
            '        <td>{{createDate}}</td>'+
            '    </tr>'+
            '{{/rows}}';
        tbodyTemplate +=
            '{{^rows}}'+
            '    <tr>'+
            '        <td colspan="4">No Data</td>'+
            '    </tr>'+
            '{{/rows}}';

        var paginationTemplate =
            '<li class="prev"><a href="#">«</a></li>' +
            '{{#pages}}' +
            '<li class="page-num {{active}}"><a href="#" page-num="{{num}}">{{num}}</a></li>' +
            '{{/pages}}' +
            '<li class="next"><a href="#">»</a></li>';
        var buttonTemplate =
            '{{#buttons}}' +
            '<button type="button" class="btn btn-flat func" name="{{name}}">{{name}}</button>' +
            '{{/buttons}}';

        tbody(simulationsMap, tbodyTemplate, 'open');
        pagination(simulationsMap.pagination, paginationTemplate, 'open');
        buttons(buttonTemplate, 'open');
    }

    function tbody(simulationMap, tbodyTemplate, panelType) {
        var simulations = simulationMap.simulations
        if (simulations && simulations.length > 0) {
            $.each(simulations, function (i) {
                simulations[i].userName = simulationMap.userName
                simulations[i].createDate = $.format.date(
                    new Date(simulations[i].createDate), "yyyy.MM.dd HH:mm");
            });

            $(JQ_PORTLET_BOUNDARY_ID + " .menu-panel tbody.panel-tbody").empty().append(
                Mustache.render(tbodyTemplate, { "rows": simulations }))
                .children("tr").each(function(i){
                var simulationId = $(this).attr("simulation-id");
                if(simulationId === PANEL_DATA.open.form.selected){
                    activate(this);
                }
                $(this).click(function(e){
                    PANEL_DATA.open.form.selected = simulationId;
                    activate(this);
                });
            });
        }else{
            $(JQ_PORTLET_BOUNDARY_ID + " .menu-panel tbody.panel-tbody").empty().append(
                Mustache.render(tbodyTemplate, { "rows": simulations }));
        }
    }

    function activate(that){
        $(that).siblings().removeClass("active");
        $(that).addClass("active");
    }

    function pagination(paginationData, paginationTemplate, panelType){
        var pages = [];
        for(var i = paginationData.startPage; i <= paginationData.endPage; i++){
            pages.push({
                "active": paginationData.currentPage === i ? "active" : "",
                "num": i
            });
        }
        $(JQ_PORTLET_BOUNDARY_ID + " .menu-panel .pagination").empty().append(
            Mustache.render(paginationTemplate, { "pages": pages })).find("li.page-num > a").click(function (e) {
            e.preventDefault();
            loadPaginatedSimulations(panelType, $(this).attr("page-num"));
        });

        if (paginationData.curBlock > 1 && paginationData.curBlock <= paginationData.totalBlock) {
            $(JQ_PORTLET_BOUNDARY_ID + " .menu-panel .pagination .prev > a").click(function (e) {
                e.preventDefault();
                loadPaginatedSimulations(panelType, paginationData.startPage - 1);
            });
        }else{
            $(JQ_PORTLET_BOUNDARY_ID + " .menu-panel .pagination .prev").addClass("disabled");
        }
        if (paginationData.curBlock < paginationData.totalBlock) {
            $(JQ_PORTLET_BOUNDARY_ID + " .menu-panel .pagination .next > a").click(function (e) {
                e.preventDefault();
                loadPaginatedSimulations(panelType, paginationData.endPage + 1);
            });
        }else{
            $(JQ_PORTLET_BOUNDARY_ID + " .menu-panel .pagination .next").addClass("disabled");
        }
    }

    function buttons(buttonTemplate, panelType) {
        $(JQ_PORTLET_BOUNDARY_ID + " .menu-panel .btn-group").empty().append(
            Mustache.render(buttonTemplate, { "buttons": PANEL_DATA[panelType].footer.btns }))
            .children("button").each(function (i) {
            var fn = PANEL_DATA[panelType].footer.btns[i].func;
            $(this).click(function (e) {
                e.preventDefault();
                fn(panelType, this, e);
            });
        });
    }

    function initJobs() {
        currJobs.set([])
        renderJobs()
    }

    function updateNodeStatus(workflowStatus) {
        if (workflowStatus && workflowStatus.workflow) {
            if (workflowStatus.workflow.status === CONSTS.WF_STATUS_CODE.PAUSED) {
                $(".before-pause").hide()
                $(".after-pause").show()
                $(".after-stop").hide()
            } else if (workflowStatus.workflow.status === CONSTS.WF_STATUS_CODE.RUNNING) {
                $(".after-pause").hide()
                $(".before-pause").show()
                $(".after-stop").hide()
            } else if (workflowStatus.workflow.status === CONSTS.WF_STATUS_CODE.FAILED ||
                workflowStatus.workflow.status === CONSTS.WF_STATUS_CODE.CANCELED ||
                workflowStatus.workflow.status === CONSTS.WF_STATUS_CODE.COMPLETED) {
                $(".after-pause").hide()
                $(".before-pause").hide()
                $(".after-stop").show()
            } else {
                /* 2019.02.26 _ Reuse node execute button */
            	$(".after-pause").hide()
                $(".before-pause").hide()
                $(".before-submit").show();
                $(".after-submit").hide();
            }
        } else {
        	$(".before-pause").hide();
            $(".after-pause").hide();
            $(".after-stop").hide();
            $(".before-submit").show();
            $(".after-submit").hide();
        }
        if (workflowStatus && workflowStatus.workflow && workflowStatus.workflow.simulations) {
            $.each(workflowStatus.workflow.simulations, function () {
                var simulation = this
                var nodeId = simulation.clientId
                var node = currNodes.get(nodeId)
                if(!node) {
                    return
                }
                
                node.data.status = simulation
                // "ibUuid": "e3e949e4-c288-47c0-b6b3-f576f097a264",
                //     "ibSimUuid": "a859bcbb-1010-4c20-a0ca-1e229d859ac5"

                if(node.data.status && node.data.status.jobs &&
                    node.data.status.jobs[0] && node.data.status.jobs[0].ibUuid &&
                    !node.data.ibData[CONSTS.WF_NODE_CODE.WORKBENCH]
                ) {
                    node.data.ibData = {
                        ibUuid: node.data.status.jobs[0].ibUuid,
                        ibSimUuid: node.data.status.jobs[0].ibSimUuid,
                    }
                }

                var statusCode = simulation.status
                if(simulation.pause && simulation.pause === 'pause' &&
                    simulation.status === CONSTS.WF_STATUS_CODE.CREATED) {
                    statusCode = CONSTS.WF_STATUS_CODE.PAUSED
                }
                // console.log(statusCode)
                $("#" + nodeId).removeClass(
                    "WAITING CANCELED CREATED NOT_FOUND RUNNING " +
                    "FAILED DONE SUCCESS COMPLETED PAUSED")
                $("#" + nodeId).addClass(statusCode)
                setReUseNodeStatus(node)
                $("#" + nodeId + " .wf-node-execute-status").text(statusCode)
                if(statusCode === "RUNNING") {
                    $("#" + nodeId + " .top-cog-icon").addClass("fa-spin")
                } else {
                    $("#" + nodeId + " .top-cog-icon").removeClass("fa-spin")
                }
            })
        }
        // console.log(workflowStatus)
    }

    function fetchJobs(simulationId, searchKeyword, currentPage, linePerPage, selectedJobId, pauseCallback) {
        if (currSimulations.contains(simulationId)) {
            var params = {}
            if (currentPage) {
                params.p_curPage = currentPage;
            }
            if (linePerPage) {
                params.linePerPage = linePerPage;
            }
            if (searchKeyword || searchKeyword === 0) {
                params.title = searchKeyword;
            }
            executor.fetchSimulationJobs(
                simulationId,
                params,
                function (jobsMap) {
                    currSimulations.select(simulationId)
                    if (currentPage) {
                        currPageJob = currentPage
                    }
                    currJobs.set(jobsMap.jobs, function(id) {
                        bStart()
                        executor.clearStatusTimeout()
                        var job = currJobs.get(id)
                        if(console) {
//                        	console.log(job)
                        	if(job){
                        		var thisJobScreenLogic = JSON.parse(job.screenLogic);
                        		var nodes = thisJobScreenLogic.nodes;
                        		for(var nodeIdx in nodes) {
                        			setReUseNodeStatusByReuseCopy(nodes[nodeIdx]);
                        		}
                        		
                        		reuseNodeCheck();
                        	}
                        }
                        if(job) {
                            if (_isBlank(job.workflowUUID)) {
                                $(".before-submit").show()
                                $(".after-submit").hide()
                                $(".after-stop").hide()
                            } else {
                                $(".before-submit").hide()
                                $(".after-submit").show()
                                $(".after-pause").hide()
                                $(".after-stop").hide()

                                // 2019.02.26 _ Reuse Copy -> job.statusResponse is null.
                                var initStatus = null;
                                if(job.statusResponse){
                                	initStatus = JSON.parse(job.statusResponse)
                                }
                                
                                if(pauseCallback) {
                                    pauseCallback(currNodes.getArray())
                                }else{
                                    executor.updateStatus(id, initStatus, updateNodeStatus)
                                }
                            }
                        }
                        if (job) {
                            validateSimulationJob()
                        }
                        bEnd()
                    })
                    currJobs.select(selectedJobId)
                    renderJobs(jobsMap, simulationId, currentPage)
                },
                function () {
                    currSimulations.select()
                }
            )
            closePanel()
        }
    }

    function renderJobs(jobsMap, simulationId, currentPage) {
        var isFirstPage = currentPage == 1
        var ulSelector = "#" + namespace + "column-1 > ul";
        var li =
            '<li class="header">\n' +
            '  <div class="header-inner">\n' +
            '    <i class="fa fa-folder"></i> {{simulationTitle}}\n' +
            '  </div>\n' +
            '</li>' +
            '{{#jobs}}' +
            '<li class="treeview" job-id="{{simulationJobId}}">\n' +
            '  <a href="#" class="sidebar-btn job-li" job-id=\"{{simulationJobId}}\" job-status=\"{{status}}\">\n' +
            '    <i class="fa fa-file"></i>\n' +
            '    <div>{{title}}</div>\n' +
            '  <span class="label label-primary pull-right sidebar-btn">\n' +
            '    <i class="icon-arrow-right"></i>\n' +
            '  </span>' +
            '  </a>\n' +
            '  <ul id="nodes-{{simulationJobId}}" class="treeview-menu">\n' +
            '  </ul>' +
            '</li>' +
            '{{/jobs}}' +
            '<li class="text-center">' +
            '  <ul id="job-pagination" class="pagination">\n' +
            '  </ul>' +
            '</li>';

        var selectedJobId = currJobs.selected() ? currJobs.selected()["simulationJobId"] : undefined
        var selectedSimulationTitle = currSimulations.selected() ? currSimulations.selected()['title'] : 'No available Simulation Job'
        $(ulSelector)
            .empty()
            .append(
                Mustache.render(li, {
                    "simulationTitle": selectedSimulationTitle,
                    "jobs": currJobs.getArray()
                })
            )
            .children("li")
            .each(function(i) {
                if (i === 0) return
                var that = this
                var simulationJobId = $(that).attr("job-id")
                $(that).children("a").click(function(e) {
                    $(this).parent("li").siblings("li").removeClass("action")
                    $(this).parent("li").addClass("action")
                    selectSimulationJob(simulationJobId)
                })
                $(that).children("a").children("span.sidebar-btn").click(function(e) {
                	e.stopPropagation()

                	/* 2019.01.15 _ Open Job Update Panel */
                	var boxTitle = 'Job Information';
                	var templateData = PANEL_DATA["job-setting"];
                	var job = currJobs.get(simulationJobId);
                	var jobTitle = job.title;
                	var getStatusInfo = getStatusAndStatusImg(job);
                	var jobStatus = getStatusInfo.jobStatus;
                	var jobStatusImg = getStatusInfo.jobStatusImg;
                	var jobStartTime = job.startTime;

                	/*
                	 * Calculated in Greenwich mean time.
                	 * KST : Greenwich mean time - 9 hour (DB start/end Date).
                	 */
                	if(jobStartTime != null){
                		jobStartTime = new Date((job.startTime)-(60*60*1000*9)).toLocaleString();
                	}
                	var jobEndTime = job.endTime;
                	if(jobEndTime != null){
                		jobEndTime = new Date((job.endTime)-(60*60*1000*9)).toLocaleString();
                	}

                	templateData.form.jobId = simulationJobId;
                	templateData.form.jobTitle = jobTitle;
                	templateData.form.jobStatus = jobStatus;
                	templateData.form.jobStatusImg = jobStatusImg;
                	templateData.form.startTime = jobStartTime;
                	templateData.form.endTime = jobEndTime;
                    createPanel(boxTitle, templateData, "job-setting");
                    if(!$(this).hasClass("is-open")) {
                        $(".menu-panel").show('slide', { direction: 'left' }, 500);
                        $(JQ_PORTLET_BOUNDARY_ID + " .job-li > span.sidebar-btn").removeClass("is-open")
                        $(JQ_PORTLET_BOUNDARY_ID + " .top-btn").removeClass("menu-open")
                        $(this).addClass("is-open")
                    }
                })
                if (i === 1 && isFirstPage && !selectedJobId) {
                    _delay(function() {
                        $(that).children("a").click()
                    }, 100)
                    return
                }
                if(simulationJobId == selectedJobId) {
                    _delay(function() {
                        $(that).children("a").click()
                    }, 100)
                }
            })
        
	        if(currJobs.getArray().length > 0){
	        	$(JQ_PORTLET_BOUNDARY_ID + " .top-btn").show();
	        	$(JQ_PORTLET_BOUNDARY_ID + " .top-btn[data-btn-type='new-job']").css("color", "#333333");
	        	jobsPagination(jobsMap.pagination, simulationId);
	        } else {
	        	_delay(function() {
	        		/* Init layout */
	        		var getCurrentInstance = designer.getCurrentJsPlumbInstance();
	        		getCurrentInstance.clear();
	        		
	        		/* Init top button */
	        		$(JQ_PORTLET_BOUNDARY_ID + " .top-btn").hide();
	        		$(JQ_PORTLET_BOUNDARY_ID + " .top-btn[data-is-init='true']").show();
	        		
	        		/* Empty job message */
	        		toastr["error"]("", var_workflow_not_exist_job_message);
	        		$(JQ_PORTLET_BOUNDARY_ID + " .top-btn[data-btn-type='new-job']").css("color", "red");
	        	}, 100)
	        }
    }
    
    function jobsPagination(paginationData, simulationId){
        var paginationTemplate =
            '<li class="prev"><a href="#">«</a></li>' +
            '{{#pages}}' +
            '<li class="page-num {{active}}"><a href="#" page-num="{{num}}">{{num}}</a></li>' +
            '{{/pages}}' +
            '<li class="next"><a href="#">»</a></li>';
        var pages = [];
        for(var i = paginationData.startPage; i <= paginationData.endPage; i++){
            pages.push({
                "active": paginationData.currentPage === i ? "active" : "",
                "num": i
            });
        }
        $(JQ_PORTLET_BOUNDARY_ID + " #job-pagination").empty().append(
            Mustache.render(paginationTemplate, { "pages": pages })).find("li.page-num > a").click(function (e) {
            e.preventDefault();
            fetchJobs(simulationId, null, $(this).attr("page-num"), null)
            // loadPaginatedSimulations(panelType, $(this).attr("page-num"));
        });

        if (paginationData.curBlock > 1 && paginationData.curBlock <= paginationData.totalBlock) {
            $(JQ_PORTLET_BOUNDARY_ID + " #job-pagination.pagination .prev > a").click(function (e) {
                e.preventDefault();
                fetchJobs(simulationId, null, paginationData.startPage - 1, null)
                // loadPaginatedSimulations(panelType, paginationData.startPage - 1);
            });
        }else{
            $(JQ_PORTLET_BOUNDARY_ID + " #job-pagination.pagination .prev").addClass("disabled");
        }
        if (paginationData.curBlock < paginationData.totalBlock) {
            $(JQ_PORTLET_BOUNDARY_ID + " #job-pagination.pagination .next > a").click(function (e) {
                e.preventDefault();
                fetchJobs(simulationId, null, paginationData.endPage + 1, null)
                // loadPaginatedSimulations(panelType, paginationData.endPage + 1);
            });
        }else{
            $(JQ_PORTLET_BOUNDARY_ID + " #job-pagination.pagination .next").addClass("disabled");
        }
    }

    /* 2019.01.15 _ Get JobStatus */
    function getStatusAndStatusImg(job){
    	var status = job.status;

    	var returnObj = new Object;
    	var jobStatus = "";
    	var jobStatusImg = null;
    	switch(status){
	        case 1701002:
	        case 1701004:
	        case 1701012:
	        	jobStatus = "FAILED";
	        	jobStatusImg = "FAILED";
	        case 1701005:
	        	jobStatus = "QUEUED";
	        	jobStatusImg = "QUEUED";
	        case 1701006:
	        	jobStatus = "RUNNING";
	        	jobStatusImg = "RUNNING";
	        case 1701010:
	        	jobStatus = "CANCELED";
	        	jobStatusImg = "CANCELED";
	        case 1701011:
	        	jobStatus = "SUCCESS";
	        	jobStatusImg = "SUCCESS";
	        default:
	        	jobStatus = "INITIALIZED";
	        	jobStatusImg = "QUEUED";
    	}

    	returnObj.jobStatus = jobStatus;
    	returnObj.jobStatusImg = jobStatusImg;
    	return returnObj;
    }

    function pushPorts(nodeId, ports, classPortArray, nodePortArray) {
        if (ports) {
            $.each(ports, function (key, value) {
                value.id = nodeId + "." + key
                classPortArray.push(value)
                nodePortArray.push(value)
            })
        }
    }

    function setPortsId(nodeId, ports, type) {
        if (ports) {
            var jtkType = type === CONSTS.WF_JSPLUMB_TYPES.INPUT ? 'target' : 'source'
            $.each(ports, function (key, value) {
                var portId = nodeId + "." + key
                $("#" + nodeId)
                    .find("jtk-" + jtkType + "[port-id='" + value.name_ + "']")
                    .parent("div")
                    .addClass(CONSTS.WF_JSPLUMB_TYPES.PORT_ELEMENT)
                    .addClass(type)
                    .attr("port-id", portId)
            })
        }
    }

    function addPortHandler(that, nodeId, isInput) {
        var portId = $(that).attr("port-id")
        var port = isInput ? currInputPorts.get(portId) : currOutputPorts.get(portId)
        $(that).children("a").click(function (e) {
            if (port.dataType_.name === CONSTS.WF_APP_TYPES.CONTROLLER.INPUT_DATA_TYPE) {
                return
            }
            if (port.dataType_.name === CONSTS.WF_APP_TYPES.CONTROLLER.OUTPUT_DATA_TYPE) {
                return
            }
            if (port.dataType_.name === CONSTS.WF_APP_TYPES.DYNAMIC_CONVERTER.INPUT_DATA_TYPE) {
                return
            }
            if (isInput) {
                openInputPort(nodeId, portId)
            } else {
                openOutputPort(nodeId, portId)
            }
        })
        $(that).children("a").hover(
            function (e) {
                $("#" + nodeId).addClass("wf-selected-node")
                $("." + CONSTS.WF_JSPLUMB_TYPES.PORT_ELEMENT + "[port-id='" + portId + "']")
                    .addClass("wf-selected-port").siblings("label").addClass("wf-selected-port-label")

            },
            function (e) {
                $("#" + nodeId).removeClass("wf-selected-node")
                $("." + CONSTS.WF_JSPLUMB_TYPES.PORT_ELEMENT + "[port-id='" + portId + "']")
                    .removeClass("wf-selected-port").siblings("label").removeClass("wf-selected-port-label")
            })
        $(that).children("a").children("span.sidebar-btn").click(function (e) {
            e.stopPropagation()
        })
    }

    function selectSimulationJob(simulationJobId) {
        var renderer = designer.getCurrentJsPlumbRenderer()
        var nodeLi =
            '{{#nodes}}' +
            '<li node-id="{{id}}" class="node-li treeview">\n' +
            '  <a href="#" class="port sidebar-btn job-li" node-id=\"{{id}}\">\n' +
            '    <i class="fa fa-cog"></i>\n' +
            '    <div class="li-node-name">{{data.scienceAppData.name}}</div>\n' +
            '  <span class="label pull-right">\n' +
            // '    <i class="fa fa-question"> 3</i>\n' +
            '  </span>' +
            '  </a>\n' +
            '  <ul class="treeview-menu">\n' +
            '    <li class="treeview input">' +
            '       <a href="#"><i class="fa fa-laptop"></i><div>input</div></a>' +
            '       <ul class="treeview-menu">' +
            '       {{#data.arrInputPorts}}' +
            '       <li class="treeview port-li input" port-id="{{id}}">\n' +
            '           <a href="#" class="sidebar-btn job-li" port-id=\"{{id}}\">\n' +
            '               <i class="fa fa-edit"></i>\n' +
            '               <div>{{name_}}</div>\n' +
            // '               <span class="label label-primary pull-right sidebar-btn">\n' +
            // '               <i class="icon-arrow-right"></i>\n' +
            // '               </span>' +
            '           </a>\n' +
            '       </li>' +
            '       {{/data.arrInputPorts}}' +
            '       </ul>' +
            '    </li>\n' +
            '    <li class="treeview log">' +
            '       <a href="#"><i class="fa fa-laptop"></i><div>log</div></a>' +
            '       <ul class="treeview-menu">' +
            '          {{#data.arrLogPorts}}' +
            '             <li class="treeview port-li log" port-id="{{id}}">\n' +
            '                 <a href="#" class="sidebar-btn job-li" port-id=\"{{id}}\">\n' +
            '                     <i class="fa fa-edit"></i>\n' +
            '                     <div>{{name_}}</div>\n' +
            '                 </a>\n' +
            '             </li>' +
            '          {{/data.arrLogPorts}}' +
            '       </ul>' +
            '    </li>\n' +
            
            '    <li class="treeview output">' +
            '       <a href="#"><i class="fa fa-laptop"></i><div>output</div></a>' +
            '       <ul class="treeview-menu">' +
            '       {{#data.arrOutputPorts}}' +
            '       <li class="treeview port-li output" port-id="{{id}}">\n' +
            '           <a href="#" class="sidebar-btn job-li" port-id=\"{{id}}\">\n' +
            '               <i class="fa fa-edit"></i>\n' +
            '               <div>{{name_}}</div>\n' +
            // '               <span class="label label-primary pull-right sidebar-btn">\n' +
            // '               <i class="icon-arrow-right"></i>\n' +
            // '               </span>' +
            '           </a>\n' +
            '       </li>' +
            '       {{/data.arrOutputPorts}}' +
            '</ul>' +
            '    </li>\n' +
            '  </ul>' +
            '</li>' +
            '{{/nodes}}';

        currJobs.select(simulationJobId)
        var job = currJobs.selected()
        designer.resetWorkflow()
        designer.drawScreenLogic(job.screenLogic)
        // var screenLogic = jpInstance.exportData({ type: "json" })
        var inputPorts = []
        var outputPorts = []
        var dataComponentNodes = []
        currNodes.set(
            designer.getCurrentJsPlumbInstance().getNodes(),
            function (id) {
            })
        
        $.each(currNodes.getArray(), function(i, node){
            node.data.arrInputPorts = []
            node.data.arrOutputPorts = []
            pushPorts(node.id, node.data.inputPorts, inputPorts, node.data.arrInputPorts)
            pushPorts(node.id, node.data.outputPorts, outputPorts, node.data.arrOutputPorts)
            setPortsId(node.id, node.data.inputPorts, CONSTS.WF_JSPLUMB_TYPES.INPUT)
            setPortsId(node.id, node.data.outputPorts, CONSTS.WF_JSPLUMB_TYPES.OUTPUT)
        })

        currInputPorts.set(inputPorts)
        currOutputPorts.set(outputPorts)

        $.each(currNodes.getArray(), function(i, node){
            setDataComponent(node)
            if (isDataComponentNode(node)) {
                dataComponentNodes.push(node.id)
            }
        })

        var move = _instantDelay(100)
        $("#nodes-" + simulationJobId).empty()
            .append(Mustache.render(nodeLi, { "nodes": currNodes.getArray() }))
            .children("li")
            .each(function(i) {
                // console.log(this)
                var nodeId = $(this).attr("node-id")
                $(this).hover(
                    function (e) {
                        $("#" + nodeId).addClass("wf-selected-node")
                    },
                    function (e) {
                        $("#" + nodeId).removeClass("wf-selected-node")
                    })
                $(this).children("a").hover(
                    function (e) {
                        $("#" + nodeId + " ." + CONSTS.WF_JSPLUMB_TYPES.PORT_ELEMENT + ".not-ready")
                        	.addClass("wf-selected-port").siblings("label").addClass("wf-selected-port-label")
                    },
                    function (e) {
                        $("#" + nodeId + " ." + CONSTS.WF_JSPLUMB_TYPES.PORT_ELEMENT)
                        	.removeClass("wf-selected-port").siblings("label").removeClass("wf-selected-port-label")
                    })
                $(this).click(
                	function(e){
                		move(function () { renderer.centerOnAndZoom(nodeId, 0.3) });
                	}
                )
                $(this).children("a").click(function(e){
                    currNodes.select(nodeId)
                })
                if ($.inArray(nodeId, dataComponentNodes) >= 0) {
                    $(this).find('ul > li.treeview.input > a').click(function (e) {
                        e.stopPropagation()
                        e.preventDefault()
                        dataComponentHandler(currNodes.get(nodeId))
                    })
                }
                $(this).children("a").children("span.sidebar-btn").click(function(e) {
                    e.stopPropagation()
                })

                $(this).find("li.treeview.port-li").each(function(){
                    addPortHandler(this, nodeId, $(this).hasClass("input"))
                })
            })
    }

    function updatePortData(prevPortData, inputData) {
        prevPortData[OSP.Constants.INPUTS] = inputData
        currInputPorts.update(prevPortData.id, prevPortData)
    }

    function getPortData(nodeId, portName){
    	var portId = nodeId + "." + portName;
    	var portData = {};
    	
    	var currPortData = $.extend({}, currInputPorts.get(portId))
        delete currPortData.id;
        
        portData[currInputPorts.get(portId)[OSP.Constants.NAME]] = currPortData;
    	
    	return portData;
    }
    
    function setPortData(nodeId, portName, strPortDataJson) {
        var portId = nodeId + "." + portName
        if (strPortDataJson && strPortDataJson !== "false") {
            var prevPortData = currInputPorts.get(portId)
            var inputData = JSON.parse(strPortDataJson)
            if (prevPortData.dataComponentNodeId) {
                var dataComponent = designer.getCurrentJsPlumbInstance().getNode(prevPortData.dataComponentNodeId)
                $.each(dataComponent.data.dataChildren, function(i, childPortId){
                    updatePortData(currInputPorts.get(childPortId), inputData);
                })
            } else {
                updatePortData(prevPortData, inputData);
            }
        }
        if(currJobs.selected()) {
            validateSimulationJob()
        }
    }

    function openInputPortData(portData) {
        // console.log(portData)
        openInputPort(portData.nodeId, portData.nodeId + "." +portData.portId)
    }
    function openOutputPortData(portData) {
        openOutputPort(portData.nodeId, portData.nodeId + "." +portData.portId)
    }

    function closePortPopup(nodeId, portName, dialogId) {
        Liferay.Util.getWindow(dialogId).destroy()
        currOpenPort.remove(nodeId + "." + portName)
    }

    function openOutputPort(nodeId, portId) {
        var node = currNodes.get(nodeId)
        var portData = {}
        var currPortData = $.extend({}, currOutputPorts.get(portId))
        delete currPortData.id
        portData[currOutputPorts.get(portId)[OSP.Constants.NAME]] = currPortData;

        popPortDialog(node, portId, portData);
    }

    function openInputPort(nodeId, portId, isDataComponentCall) {
        var node = currNodes.get(nodeId)
        var portData = {}
        var currPortData = $.extend({}, currInputPorts.get(portId))
        delete currPortData.id

        portData[currInputPorts.get(portId)[OSP.Constants.NAME]] = currPortData;

        if ((currPortData.isReady && !currPortData.inputs_) ||
            (!isDataComponentCall &&  !!currPortData.dataComponentNodeId)) {
            toastr['warning']('','Connected input port')
            return false
        }

        popPortDialog(node, portId, portData, isDataComponentCall);
    }
    
    function popPortDialog(node, portId, portData, isDataComponentCall) {
        var portName = ''
        var nodeId = node.id
        var userId = currJobs.selected() ? currJobs.selected().userId : null
        var isReUseNode = node.data.isReUseNode;
        var saveFlag = false
        $.each(portData, function () { portName = this['name_'] })
        if (node && node.data && node.data.ibData) {
            node.data.ibData.ibSimUuid || (node.data.ibData.ibSimUuid = getGUID())
            // node.data.ibData.jobUuid || (node.data.ibData.jobUuid= getGUID())
            saveFlag = node.data.status && node.data.status.status !== CONSTS.WF_STATUS_CODE.RUNNING
        } else {
            toastr['error']('', CONSTS.MESSAGE.edison_wfsimulation_no_valid_node_data_message)
            return false
        }
        if (currOpenPort.containsKey(portId)) {
        	toastr['warning']('', 'Already open')
        	return false
        }
        
        var dialogId = namespace + getGUID()
        currOpenPort.put(portId, dialogId)
        window.AUI().use('liferay-portlet-url', function (A) {
            var portletURL = window.Liferay.PortletURL.createRenderURL();
            portletURL.setPortletId("ModuleViewer_WAR_OSPWorkbenchportlet");
            portletURL.setParameter('simulationUuid', node.data.ibData.ibSimUuid);
            if(node.data.status){
            	if(isReUseNode){
            		portletURL.setParameter('status', 'SUCCESS');
            	} else {
            		portletURL.setParameter('status', node.data.status.status);
            	}
            }
            if (node.data.ibData.ibUuid) {
                portletURL.setParameter('jobUuid', node.data.ibData.ibUuid);
            }
            
//            portletURL.setParameter('portData', JSON.stringify(portData));
            
            portletURL.setParameter('portName', portName);
            portletURL.setParameter('portType',currInputPorts.contains(portId) ? CONSTS.WF_JSPLUMB_TYPES.INPUT_PORTS : CONSTS.WF_JSPLUMB_TYPES.OUTPUT_PORTS);
            portletURL.setParameter('nodeId', nodeId);
            portletURL.setParameter('userId', userId);
            portletURL.setParameter('dialogId', dialogId);
            portletURL.setParameter('saveFlag', saveFlag);
            portletURL.setWindowState('pop_up');

            var wWidth = $(window).width()
            /*var wHeight =$(window).height()*/

            /* init popup size */
            var fWidth = '';
            var fHeight = wWidth > 2000 ? '50vh' : '650px'
            if(wWidth > 2000){
            	fWidth = '50vw';
            } else if(wWidth > 1500 && wWidth < 2000){
            	fWidth = '1024px';
        	} else {
        		fWidth = wWidth * 0.7 + 'px';
        		fHeight = wWidth * 0.6 + 'px';
        	}
            	
            /* init popup position */
            var popupCnt = $('.wf-port-popup').length;
            var positionLeft = 230;
            var positionTop = 72;
            if(0 < popupCnt){
            	var firstPopup = $('.wf-port-popup:first');
            	if(firstPopup.position().left != 0 && firstPopup.position().top != 0){
            		positionLeft = firstPopup.position().left + 40;
            		positionTop = firstPopup.position().top + 40;
            	}
            }
            
            /*Liferay.Util.openWindow({*/
        	var openPortPopup = Liferay.Util.Window.getWindow({
            	dialog: {
            		width: fWidth,
            		height: fHeight,
            		cache: false,
            		centered: false,
            		draggable: true,
            		resizable: true,
            		modal: false,
            		destroyOnClose: true,
            		after: {
            			render: function (event) {
            				$('#' + dialogId).addClass('wf-port-popup')
            				$("button.btn.close").on("click", function (e) {
            					currOpenPort.remove(portId)
            				});
            			},
            		},
            	},
            	id: dialogId,
            	uri: portletURL.toString(),
            	title: (isDataComponentCall ? 'DataComponent' : node.data.scienceAppData.name ) + " " + portName
            });
        	
//            A.one('body').on('key', function(event){
//        		openPortPopup.once('visibleChange', function(event){
//        			if(event.prevVal == true){
//        				event.newVal = true;
//        			}
//        		});
//        	}, 'esc');
        	
            $('#' + dialogId).css('top', positionTop+'px').css('left', positionLeft+'px')
        });
    }
    
    function openSimulation() {
        var simulationId = PANEL_DATA.open.form.selected;
        if(_isEmpty(simulationId, CONSTS.MESSAGE.edison_wfsimulation_select_first_message)){
            return false
        }
        _confirm(CONSTS.MESSAGE.edison_wfsimulation_new_confirm_message,
            function(){
        		fetchJobs(simulationId, null, 1)

        		/* 2019.01.16 _  SimulationId Setting in PANEL_DATA["setting"] */
        		PANEL_DATA["setting"].form["simulationId"] =simulationId;
    	});
    }

    function renameSimulation() {
        var simulationId = PANEL_DATA.open.form.selected;
        if(_isEmpty(simulationId, CONSTS.MESSAGE.edison_wfsimulation_select_first_message)){
            return false;
        }
        var simulation = currSimulations.get(simulationId);
        var inputs = [{"name": "Title", "value": simulation.title}];
        var btns = {"ok": "Save", "cancel": "Cancel"};
        createOpenModal("Rename", inputs, btns, function(e){
            var simulationData = $.extend({}, simulation);
            simulationData.title = $("#" + namespace + "wf-modal").find("input[name='Title']").val();
            delete simulationData.createDate;
            executor.updateSimulation(simulationData, function(){
                if (PANEL_DATA.setting.form.simulationId === simulationId) {
                    PANEL_DATA.setting.form.title = simulationData.title
                }
                loadPaginatedSimulations('open', PANEL_DATA.open.form.params.p_curPage,
                    function () {
                        if (currSimulations.getArray().length > 0) {
                            var simulationId = currSimulations.getArray()[0].simulationId
                            currSimulations.select(simulationId)
                            fetchJobs(simulationId, null, 1)
                        }
                    })
                $("#" + namespace + "wf-modal").modal("hide");
            });
        });
    }

    function deleteSimulation(panelType, that, e) {
        var simulationId = PANEL_DATA.open.form.selected;
        if(_isEmpty(simulationId, CONSTS.MESSAGE.edison_wfsimulation_select_first_message)){
            return false;
        }
        _confirm(CONSTS.MESSAGE.edison_wfsimulation_remove_confirm_message,
            function () {
                if (currSimulations.contains(simulationId)) {
                    executor.deleteSimulation(
                        simulationId,
                        function (simulation) {
                            if (simulation) {
                                loadPaginatedSimulations(panelType, PANEL_DATA.open.form.params.p_curPage, function() {
                                    if (currSimulations.getArray().length > 0) {
                                        var simulationId = currSimulations.getArray()[0].simulationId
                                        currSimulations.select(simulationId)
                                        fetchJobs(simulationId, null,  1)
                                    } else {
                                        initJobs()
                                    }
                                })
                            }
                        },
                        function () {
                        })
                }
            });
    }

    function openNewSimulationPanel() {
    	createPanel('New Simulation', PANEL_DATA['new'], 'new')
    }

    function newSimulation(){
        if (isValidate()) {
            var _f = function(){
                executor.createSimulation({
                    workflowId: PANEL_DATA.setting.form.workflowId,
                    title: PANEL_DATA.new.form.title,
                }, function(){
                    PANEL_DATA.new.form.title = "";
                    loadPaginatedSimulations('open', 1, function() {
                        if (currSimulations.getArray().length > 0) {
                            var simulationId = currSimulations.getArray()[0].simulationId
                            currSimulations.select(simulationId)
                            fetchJobs(simulationId, null,  1)
                        } else {
                            openNewSimulationPanel()
                        }
                    })
                    toastr["success"]("", var_create_success_message);

                });
                closePanel();
            };
            if (PANEL_DATA.setting.form.simulationId){
                _confirm(CONSTS.MESSAGE.edison_wfsimulation_new_confirm_message, _f, closePanel);
            }else{
                _f();
            }
        }
    }

    function newSimulationJob(){
        if (isValidate()) {
            // var ibToken = getIcebreakerAccessToken()
            var simulationId = currSimulations.selected()['simulationId']
            var _f = function(){
                executor.createSimulationJob({
                    simulationId: simulationId,
                    title: PANEL_DATA["new-job"].form.title,
                    // icebreakerVcToken: ibToken,
                }, function(simulationJob){
                    fetchJobs(simulationId, null, 1)
                    toastr["success"]("", CONSTS.MESSAGE.edison_wfsimulation_create_success_message);
                });
                closePanel();
            };
            _confirm(CONSTS.MESSAGE.edison_wfsimulation_new_confirm_message, _f, closePanel);
        }
    }

    $("#" + namespace + "header-li-save").click(function(e){
        if(currJobs.selected()) {
            saveSimulationJob(currJobs.selected())
        } else {
            toastr["error"]("", CONSTS.MESSAGE.edison_wfsimulation_no_selected_job_message);
        }
    })

    function saveSimulationJob(job, callback) {
        var jqInstance = designer.getCurrentJsPlumbInstance()
        job.screenLogic = JSON.stringify(jqInstance.exportData({ type: "json" }))
        executor.updateSimulationJob(
            job,
            function (simulationJob) {
                if (callback) {
                    callback(simulationJob)
                } else {
                    fetchJobs(simulationJob.simulationId, null, currPageJob ? currPageJob : 1, null, job.simulationJobId)
                    _delay(function () {
                        toastr["success"]("", CONSTS.MESSAGE.edison_wfsimulation_save_complete_message)
                    }, 100)
                }
            },
            function () {
            })
    }

    $("#" + namespace + "header-li-copy").click(function (e) {
        if(_isEmpty(currJobs.selected(), CONSTS.MESSAGE.edison_wfsimulation_no_selected_job_message)){
            return false;
        }
        var sourceJob = currJobs.selected();
        executor.fetchSimulationJobSeq(
            currSimulations.selected().simulationId,
            function (seqMap) {
                var inputs = [{"name": "Title", "value": seqMap.seq}];
                var btns = {"ok": "Save", "cancel": "Cancel"};
                createOpenModal("Copy", inputs, btns, function(e){
                	
                	var title = $("#" + namespace + "wf-modal").find("input[name='Title']").val();
                	$.confirm({
                		title: "Copy",
                		content: "Do you want to include the reuse node when copying?",
                		buttons: {
                			YES : function(){
                				reuseCopy(title);
                			},
                			NO : function(){
                				copySimulationJob(sourceJob, title);
                			}
                		}
                	});
                    $("#" + namespace + "wf-modal").modal("hide");
                });
            })
    })

    /* 2019.01.15 _ Job Rename Function */
    function renameSimulationJobInPanel(panelType, that, e){
    	var simulationJobId = PANEL_DATA[panelType].form.jobId;
    	var simulationJobTitle = PANEL_DATA[panelType].form.jobTitle;
    	var job = currJobs.get(simulationJobId);
    	job.title = simulationJobTitle;
    	saveSimulationJob(job);
    }

    /* 2019.01.15 _ Job Copy Function */
    function copySimulationJobInPanel(){
    	$("#" + namespace + "header-li-copy").click();
    }

    /* 2019.01.15 _ Job Delete Function */
    function deleteSimulationJobInPanel(){
    	$("#" + namespace + "header-li-delete").click();
    }

    function copySimulationJob(job, title, callback) {
        saveSimulationJob(job, function(sourceJob){
            var params = {
                simulationJobId: sourceJob.simulationJobId,
                title: title,
            }
            executor.copySimulationJob(
                params,
                function (simulationJob) {
                    if (callback) {
                        callback(simulationJob)
                    } else {
                        fetchJobs(simulationJob.simulationId, null, 1)
                        _delay(function () {
                            toastr["success"]("", CONSTS.MESSAGE.edison_wfsimulation_save_complete_message)
                        }, 100)
                    }
                })
        })
    }

    function createOpenModal(title, inputs, btns, saveHandler) {
        var modal = $("#" + namespace + "wf-modal");
        modal.find(".modal-title").text(title);
        modal.find(".modal-body").empty().append($.Mustache.render("tpl-modal-body", { "inputs": inputs }));
        modal.find(".modal-footer").empty().append($.Mustache.render("tpl-modal-footer", btns));
        modal.modal({ "backdrop": "static", "keyboard": false });
        $("#" + namespace + "wf-modal").find("input[name='Title']").select();
        _delay(function (selector) { $(selector).find("input[name='Title']").select(); }, 500, "#" + namespace + "wf-modal");
        $("#" + namespace + "wf-modal").find("button[name='Save']").click(saveHandler);
        _enterkey("#" + namespace + "wf-modal input[name='Title']", saveHandler);
    }

    $("#" + namespace + "header-li-delete").click(function (e) {
        if (_isEmpty(currJobs.selected(), CONSTS.MESSAGE.edison_wfsimulation_no_selected_job_message)) {
            return false;
        }
        _confirm(CONSTS.MESSAGE.edison_wfsimulation_remove_confirm_message,
            function () {
                var simulationId = currJobs.selected().simulationId
                executor.deleteSimulationJob(currJobs.selected(), function () {
                    fetchJobs(simulationId, null, 1)
                    toastr["success"]("", CONSTS.MESSAGE.edison_wfsimulation_delete_success_message)
                })
            },
            function () {
            });
    })

    $("#" + namespace + "header-li-submit").click(function (e) {
        submitSimulationJob()
    })

    $("#" + namespace + "header-li-rerun").click(function (e) {
        rerun()
    })

    $("#" + namespace + "header-li-pause").click(function (e) {
        pause()
    })

    $("#" + namespace + "header-li-resume").click(function (e) {
        resume()
    })
    $("#" + namespace + "header-li-export").click(function (e) {
        exportJson()
    })

    function exportJson() {
        bStart()
        _delay(function() {
            saveSimulationJob(currJobs.selected(), function() {
                resetSubmitData()
                // console.log(designer.getCurrentJsPlumbInstance())
                var prefix = CONSTS.WF_ENGINE.CMD_PREFIX
                var jp = designer.getCurrentJsPlumbInstance()
                var outputPorts = currOutputPorts.getArray()
                var isReRun = false
                $.each(outputPorts, function(){
                    // console.log(this)
                    var currSourcePortData = this
                    var sourcePort = jp.getPort(this.id)
                    var sourceNode = sourcePort.getNode()
                    if(isDataComponentNode(sourceNode)) {
                        return
                    }
                    $.each(sourcePort.getSourceEdges(), function(){
                        var targetPort = this.target
                        if(targetPort) {
                            var prefixedId = prefix + targetPort.id
                            var targetNode = targetPort.getNode()

                            if(isDataComponentNode(targetNode)) {
                                isReRun = true
                                return
                            }

                            sourceNode.data.childNodes || (sourceNode.data.childNodes = [])
                            if($.inArray(targetNode.getFullId(), sourceNode.data.childNodes) < 0){
                                sourceNode.data.childNodes.push(targetNode.getFullId())
                            }

                            sourceNode.data.outPort || (sourceNode.data.outPort = {})
                            sourceNode.data.outPort.hasOwnProperty(prefixedId) || (sourceNode.data.outPort[prefixedId] = [])
                            if($.inArray(targetNode.getFullId(), sourceNode.data.outPort[prefixedId]) < 0){
                                sourceNode.data.outPort[prefixedId].push(targetNode.getFullId())
                            }

                            sourceNode.data.outPortFile || (sourceNode.data.outPortFile = {})
                            if (currSourcePortData[OSP.Constants.OUTPUT_DATA]) {
                                sourceNode.data.outPortFile[prefixedId] = currSourcePortData[OSP.Constants.OUTPUT_DATA][OSP.Constants.NAME]
                            }

                            targetNode.data.parentNodes || (targetNode.data.parentNodes = [])
                            if($.inArray(sourceNode.getFullId(), targetNode.data.parentNodes) < 0){
                                targetNode.data.parentNodes.push(sourceNode.getFullId())
                            }

                            targetNode.data.connectedPorts || (targetNode.data.connectedPorts = [])
                            if($.inArray(targetPort.data.id, targetNode.data.connectedPorts) < 0){
                                targetNode.data.connectedPorts.push(targetPort.data.id)
                            }
                        }
                    })
                })

                var json = designer.getCurrentJsPlumbInstance().exportData({ type: "json" })
                var nodes = []
                $.each(json.nodes, function(i, node){
                    if (isReRun) {
                        node.regenerateClientId = true
                    } else {
                        node.regenerateClientId = false
                    }
                    if (!isDataComponentNode({data: node})) {
                        nodes.push(node)
                    }
                })
                json.nodes = nodes
                var simulationId = currJobs.selected().simulationId
                var simulationJobId = currJobs.selected().simulationJobId
                var token = getIcebreakerAccessToken()

                executor.exportSimulationJob({
                    simulationId: simulationId,
                    simulationJobId: simulationJobId,
                    icebreakerVcToken: token.icebreakerVcToken,
                    groupId: token.groupId,
                    strNodes: JSON.stringify(json.nodes)
                }, function(exportJson) {
                    // console.log(submitData)
                    toastr['success']('', CONSTS.MESSAGE.edison_wfsimulation_export_success_message)
                    var currJob = currJobs.selected()
                    var currSimulation = currSimulations.selected()
                    var fileName = currSimulation.title + '-' + currJob.title
                    fileName = fileName ? fileName.replace(/[^a-z0-9]/gi, '_').toLowerCase() : 'workflowExport';
                    bEnd()
                    try {
                        var blob = new Blob([exportJson], {type: "application/json;charset=utf-8"})
                        window.saveAs(blob, fileName)
                    } catch (e) {
                        if(console) {
                            console.log(e)
                        }
                    }
                }, function() {
                    bEnd()
                    toastr['error']('', CONSTS.MESSAGE.edison_wfsimulation_export_fail_message)
                })
            }, function () {
                bEnd()
                toastr['error']('', CONSTS.MESSAGE.edison_wfsimulation_export_fail_message)
            })
        }, 2000)

    }

    function rerun(pauseNodId, pauseCallback) {
        submitSimulationJob(true, pauseNodId, pauseCallback)
    }
    
    function resume(){
        var simulationJobId = currJobs.selected() ? currJobs.selected().simulationJobId : undefined
        if (simulationJobId) {
            executor.resumeSimulationJob(simulationJobId,
                function (status) {
                    toastr["success"]("", CONSTS.MESSAGE.edison_wfsimulation_resume_success_message)
                    updateNodeStatus(status)
                    executor.updateStatus(simulationJobId, status, updateNodeStatus)
                },
                function () {
                    toastr["error"]("", CONSTS.MESSAGE.edison_wfsimulation_resume_fail_message);
                })
        }
    }

    function pause(callback){
    	designer.allNodesPause();
        /*var simulationJobId = currJobs.selected() ? currJobs.selected().simulationJobId : undefined
        		console.log(currNodes);
        console.log(currNodes.selected());
        if (simulationJobId) {
            executor.pauseSimulationJob(simulationJobId,
                function (status) {
                    toastr["success"]("", CONSTS.MESSAGE.edison_wfsimulation_pause_success_message)
                    updateNodeStatus(status)
                    executor.updateStatus(simulationJobId, status, updateNodeStatus)
                    if(callback) {
                    	callback()
                    }
                },
                function () {
                    toastr["error"]("", CONSTS.MESSAGE.edison_wfsimulation_pause_fail_message);
                })
        }*/
    }

    function resetSubmitData(){
        $.each(designer.getCurrentJsPlumbInstance().getNodes(),
            function () {
                var node = this
                delete node.data.parentNodes
                delete node.data.childNodes
                delete node.data.connectedPorts
                delete node.data.outPort
                delete node.data.outPortFile
            })
    }

    function validateSimulationJob(){
        var inputPorts = currInputPorts.getArray()
        var jp = designer.getCurrentJsPlumbInstance()
        var notReady = {}
        var valid = true
        $.each(inputPorts, function(){
            var port = this
            var portData = jp.getPort(port.id)
            var node = portData.getNode()
            var nodeId = node.id
            var currPortData = currInputPorts.get(port.id)
            var dataComponentNodeId = currPortData.dataComponentNodeId
            var isDataComponentPort = !!dataComponentNodeId
            if(port.inputs_ || (portData.getEdges().length > 0 && !isDataComponentPort) ) {
                port.isReady = true
                $('a.job-li[port-id="'+ port.id + '"]').addClass('is-ready')
                $('a.job-li[port-id="'+ port.id + '"]').removeClass('not-ready')
                $('.port-element.input[port-id="'+ port.id + '"]').addClass('is-ready')
                $('.port-element.input[port-id="'+ port.id + '"]').removeClass('not-ready')
            } else {
                port.isReady = false
                if(notReady[nodeId] || notReady[nodeId] === 0) {
                    notReady[nodeId] = notReady[nodeId] + 1
                } else {
                    notReady[nodeId] = 1
                }
                notReady[dataComponentNodeId] = 1

                $('a.job-li[port-id="'+ port.id + '"]').removeClass('is-ready')
                $('a.job-li[port-id="'+ port.id + '"]').addClass('not-ready')
                $('.port-element.input[port-id="'+ port.id + '"]').removeClass('is-ready')
                $('.port-element.input[port-id="'+ port.id + '"]').addClass('not-ready')
            }
        })
        $('li.node-li').removeClass('not-ready')
        $('li.node-li a.port.sidebar-btn.job-li > span.label').text('')
        $.each(notReady, function(key, value) {
            valid = false
            $('li.node-li[node-id="' + key + '"]').addClass('not-ready')
            $('li.node-li[node-id="' + key + '"] a.port.sidebar-btn.job-li > span.label').text(value)
        })
        return valid
        // return false
    }

    function submitSimulationJob(isReRun, pauseNodId, pauseCallback) {
        if(!currJobs.selected()) {
            toastr['error']('', CONSTS.MESSAGE.edison_wfsimulation_no_valid_node_data_message)
            return false
        }

        if(!validateSimulationJob()){
            toastr['warning']('',CONSTS.MESSAGE.edison_wfsimulation_validation_fail_message)
            return false
        }

        executor.clearStatusTimeout()

        bStart()
        _delay(function() {
            if (pauseNodId && currNodes.get(pauseNodId)) {
                var pauseNode = currNodes.get(pauseNodId)
                pauseNode.data.pause = 'pause'
            }
            saveSimulationJob(currJobs.selected(), function() {
                resetSubmitData()
                // console.log(designer.getCurrentJsPlumbInstance())
                var prefix = CONSTS.WF_ENGINE.CMD_PREFIX
                var jp = designer.getCurrentJsPlumbInstance()
                var outputPorts = currOutputPorts.getArray()
                $.each(outputPorts, function(){
                    // console.log(this)
                    var currSourcePortData = this
                    var sourcePort = jp.getPort(this.id)
                    var sourceNode = sourcePort.getNode()
                    if(isDataComponentNode(sourceNode)) {
                        return
                    }
                    // console.log(jp.getPort(sourcePort.id))
                    $.each(sourcePort.getSourceEdges(), function(){
                        var targetPort = this.target
                        if(targetPort) {
                            // console.log(sourcePort)
                            // console.log(targetPort)
                            var prefixedId = prefix + targetPort.id
                            var targetNode = targetPort.getNode()

                            if(isDataComponentNode(targetNode)) {
                                return
                            }

                            sourceNode.data.childNodes || (sourceNode.data.childNodes = [])
                            if($.inArray(targetNode.getFullId(), sourceNode.data.childNodes) < 0){
                                sourceNode.data.childNodes.push(targetNode.getFullId())
                            }

                            sourceNode.data.outPort || (sourceNode.data.outPort = {})
                            sourceNode.data.outPort.hasOwnProperty(prefixedId) || (sourceNode.data.outPort[prefixedId] = [])
                            if($.inArray(targetNode.getFullId(), sourceNode.data.outPort[prefixedId]) < 0){
                                sourceNode.data.outPort[prefixedId].push(targetNode.getFullId())
                            }

                            sourceNode.data.outPortFile || (sourceNode.data.outPortFile = {})
                            if (currSourcePortData[OSP.Constants.OUTPUT_DATA]) {
                                sourceNode.data.outPortFile[prefixedId] = currSourcePortData[OSP.Constants.OUTPUT_DATA][OSP.Constants.NAME]
                            }

                            targetNode.data.parentNodes || (targetNode.data.parentNodes = [])
                            if($.inArray(sourceNode.getFullId(), targetNode.data.parentNodes) < 0){
                                targetNode.data.parentNodes.push(sourceNode.getFullId())
                            }

                            targetNode.data.connectedPorts || (targetNode.data.connectedPorts = [])
                            if($.inArray(targetPort.data.id, targetNode.data.connectedPorts) < 0){
                                targetNode.data.connectedPorts.push(targetPort.data.id)
                            }
                        }
                    })
                })
                // return false

                var json = designer.getCurrentJsPlumbInstance().exportData({ type: "json" })
                var nodes = []
                $.each(json.nodes, function(i, node){
                    if (isReRun) {
                        node.regenerateClientId = true
                    } else {
                        node.regenerateClientId = false
                    }
                    if (!isDataComponentNode({data: node})) {
                        nodes.push(node)
                    }
                })
                json.nodes = nodes
                // return false
                var simulationId = currJobs.selected().simulationId
                var simulationJobId = currJobs.selected().simulationJobId
                var token = getIcebreakerAccessToken()
                // console.log(token)
//                console.log(json.nodes)

                var exeFunction = !!isReRun ? executor.rerunSimulationJobEngine : executor.createSimulationJobEngine

                exeFunction({
                    simulationId: simulationId,
                    simulationJobId: simulationJobId,
                    icebreakerVcToken: token.icebreakerVcToken,
                    groupId: token.groupId,
                    strNodes: JSON.stringify(json.nodes)
                }, function(simulationJob) {
                    // console.log(submitData)
                    toastr['success']('', CONSTS.MESSAGE.edison_wfsimulation_submit_success_message)
                    if (currJobs.contains(simulationJob.id)) {
                        currJobs.update(simulationJob.id, simulationJob)
                        if (_isBlank(simulationJob.workflowUUID)) {
                            $(".before-submit").show()
                            $(".after-submit").hide()
                        } else {
                            $(".before-submit").hide()
                            $(".after-submit").show()
                            var initStatus = JSON.parse(simulationJob.statusResponse)
                            updateNodeStatus(initStatus)
                            pauseCallback(currNodes)
                            if(!pauseCallback) {
                                executor.updateStatus(simulationJob.id, initStatus, updateNodeStatus)
                            }
                        }
                    } else {
                        // TODO : currentPage
                        fetchJobs(simulationId, null, currPageJob, null, simulationJobId, pauseCallback)
                    }
                    bEnd()
                }, function() {
                    bEnd()
                    toastr['error']('', CONSTS.MESSAGE.edison_wfsimulation_submit_fail_message)
                })
            }, function () {
                bEnd()
                toastr['error']('', CONSTS.MESSAGE.edison_wfsimulation_submit_fail_message)
            })
        }, 2000)


        // console.log(designer.getCurrentJsPlumbInstance().getNodes())
    }
    
    $("#" + namespace + "header-li-reuse-copy").click(function (e) {
        if(_isEmpty(currJobs.selected(), CONSTS.MESSAGE.edison_wfsimulation_no_selected_job_message)){
            return false;
        }
        var sourceJob = currJobs.selected();
        executor.fetchSimulationJobSeq(
            currSimulations.selected().simulationId,
            function (seqMap) {
                var inputs = [{"name": "Title", "value": seqMap.seq}];
                var btns = {"ok": "Save", "cancel": "Cancel"};
                createOpenModal("Copy", inputs, btns, function(e){
                	var title = $("#" + namespace + "wf-modal").find("input[name='Title']").val();
                	reuseCopy(title);
                    $("#" + namespace + "wf-modal").modal("hide");
                });
            })
    })
    
    /* 2019.02.25 _ Add reuse copy function */
    function reuseCopy(title, pauseNodId, pauseCallback) {
        if(!currJobs.selected()) {
            toastr['error']('', CONSTS.MESSAGE.edison_wfsimulation_no_valid_node_data_message)
            return false
        }

        if(!validateSimulationJob()){
            toastr['warning']('',CONSTS.MESSAGE.edison_wfsimulation_validation_fail_message)
            return false
        }

        executor.clearStatusTimeout()

        bStart()
        _delay(function() {
            if (pauseNodId && currNodes.get(pauseNodId)) {
                var pauseNode = currNodes.get(pauseNodId)
                pauseNode.data.pause = 'pause'
            }
            saveSimulationJob(currJobs.selected(), function() {
                resetSubmitData()
                var prefix = CONSTS.WF_ENGINE.CMD_PREFIX
                var jp = designer.getCurrentJsPlumbInstance()
                var outputPorts = currOutputPorts.getArray()
                $.each(outputPorts, function(){
                    var currSourcePortData = this
                    var sourcePort = jp.getPort(this.id)
                    var sourceNode = sourcePort.getNode()
                    if(isDataComponentNode(sourceNode)) {
                        return
                    }
                    $.each(sourcePort.getSourceEdges(), function(){
                        var targetPort = this.target
                        if(targetPort) {
                            var prefixedId = prefix + targetPort.id
                            var targetNode = targetPort.getNode()

                            if(isDataComponentNode(targetNode)) {
                                return
                            }

                            sourceNode.data.childNodes || (sourceNode.data.childNodes = [])
                            if($.inArray(targetNode.getFullId(), sourceNode.data.childNodes) < 0){
                                sourceNode.data.childNodes.push(targetNode.getFullId())
                            }

                            sourceNode.data.outPort || (sourceNode.data.outPort = {})
                            sourceNode.data.outPort.hasOwnProperty(prefixedId) || (sourceNode.data.outPort[prefixedId] = [])
                            if($.inArray(targetNode.getFullId(), sourceNode.data.outPort[prefixedId]) < 0){
                                sourceNode.data.outPort[prefixedId].push(targetNode.getFullId())
                            }

                            sourceNode.data.outPortFile || (sourceNode.data.outPortFile = {})
                            if (currSourcePortData[OSP.Constants.OUTPUT_DATA]) {
                                sourceNode.data.outPortFile[prefixedId] = currSourcePortData[OSP.Constants.OUTPUT_DATA][OSP.Constants.NAME]
                            }

                            targetNode.data.parentNodes || (targetNode.data.parentNodes = [])
                            if($.inArray(sourceNode.getFullId(), targetNode.data.parentNodes) < 0){
                                targetNode.data.parentNodes.push(sourceNode.getFullId())
                            }

                            targetNode.data.connectedPorts || (targetNode.data.connectedPorts = [])
                            if($.inArray(targetPort.data.id, targetNode.data.connectedPorts) < 0){
                                targetNode.data.connectedPorts.push(targetPort.data.id)
                            }
                        }
                    })
                })
                // return false

                var json = designer.getCurrentJsPlumbInstance().exportData({ type: "json" })
                var nodes = []
                $.each(json.nodes, function(i, node){
                	node.regenerateClientId = true
                    if (!isDataComponentNode({data: node})) {
                        nodes.push(node)
                    }
                })
                json.nodes = nodes
                // return false
                var simulationId = currJobs.selected().simulationId
                var simulationJobId = currJobs.selected().simulationJobId
                var token = getIcebreakerAccessToken()

                var exeFunction = executor.reuseCopySimulationJobEngine;

                exeFunction({
                    simulationId: simulationId,
                    simulationJobId: simulationJobId,
                    icebreakerVcToken: token.icebreakerVcToken,
                    groupId: token.groupId,
                    strNodes: JSON.stringify(json.nodes),
                    title: title,
                }, function(simulationJob) {
                    toastr['success']('', CONSTS.MESSAGE.edison_wfsimulation_submit_success_message)
                    if (currJobs.contains(simulationJob.simulationJobId)) {
                        currJobs.update(simulationJob.id, simulationJob)
                        if (_isBlank(simulationJob.workflowUUID)) {
                            $(".before-submit").show()
                            $(".after-submit").hide()
                        } else {
                            $(".before-submit").hide()
                            $(".after-submit").show()
                            var initStatus = JSON.parse(simulationJob.statusResponse)
                            updateNodeStatus(initStatus)
                            pauseCallback(currNodes)
                            if(!pauseCallback) {
                                executor.updateStatus(simulationJob.id, initStatus, updateNodeStatus)
                            }
                        }
                        
                    } else {
                        fetchJobs(simulationId, null, currPageJob, null, simulationJobId, pauseCallback)
                    }
                    
                    fetchJobs(simulationJob.simulationId, null, 1);
                    bEnd()
                }, function() {
                    bEnd()
                    toastr['error']('', CONSTS.MESSAGE.edison_wfsimulation_submit_fail_message)
                })
            }, function () {
                bEnd()
                toastr['error']('', CONSTS.MESSAGE.edison_wfsimulation_submit_fail_message)
            })
        }, 2000)
    }

    /////////////////////////////////////////// renew end

    function getIcebreakerAccessToken(){
        var fn = window[namespace + "getIcebreakerAccessToken"];
        return fn.apply();
    }

    function openWorkflowByWorkflowId(workflowId, isNotNew, callback){
        designer.loadWorkflowDefinition(workflowId, function(workflow){
            if(!isNotNew){
            	var metaData = {
        			"workflowTitle": workflow.title,
                    "workflowDescription": workflow.description,
                    "workflowId": workflowId
            	}

            	setMetaData(metaData);

                loadPaginatedSimulations('open', 1, function() {
                    if (currSimulations.getArray().length > 0) {
                        var simulationId = currSimulations.getArray()[0].simulationId
                        currSimulations.select(simulationId)

                        /* 2019.01.16 _  SimulationId Setting in PANEL_DATA["setting"] */
                        metaData["simulationId"] = simulationId;
                        setMetaData(metaData);
                        fetchJobs(simulationId, null,  1)
                    } else {
                        _delay(function() {
                            $("#" + namespace + "header-li-simulation").click()
                            openNewSimulationPanel()
                        }, 500)
                    }
                })

                if(callback) {
                    callback()
                }
            }
        });
    }

    function getMetaData(){
        return PANEL_DATA.setting.form;
    }

    function setMetaData(metadata){
        PANEL_DATA.setting.form = $.extend({}, metadata);
    }

    function isValidate() {
        $("#" + namespace + "menu-panel-box form").validator('validate');
        return $("#" + namespace + "menu-panel-box form").find(".has-error").length === 0;
    }

    function closePanel() {
        $(".menu-panel").hide('slide', { direction: 'left' }, 500);
        $(JQ_PORTLET_BOUNDARY_ID + " li.top-btn.active").removeClass("active");
    }

    function activateLi(jqLink) {
        $(JQ_PORTLET_BOUNDARY_ID + " li.top-btn.active").removeClass("active");
        $(jqLink).addClass("active");
    }

    function getValueByInputName(inputName){
        return $("input[name='" + inputName + "']").val();
    }

    function _isEmpty(value, msg){
        if(!value){
            if(msg){
                toastr['warning']("", msg);
            }
            return true;
        }
        return false;
    }

    $(document).bind('keydown.uiPanel',function (event) {
        if ((event.which == 115 || event.which == 83) &&
            (event.ctrlKey || event.metaKey) || (event.which == 19)) {
            event.preventDefault();
            if(currJobs.selected()) {
                saveSimulationJob(currJobs.selected())
            } else {
                toastr["error"]("", CONSTS.MESSAGE.edison_wfsimulation_no_selected_job_message);
            }
            return false;
        }
        return true;
    });

	/* 2019.01.02 _ Popup Button Event */
	var openScienceAppWorkbench = function(node) {
		executor.clearStatusTimeout()
		_delay(function() {
            var nodeData = node.data;
            var wfId = nodeData.id;

            if(!nodeData[CONSTS.WF_NODE_CODE.IB_DATA]){
                nodeData[CONSTS.WF_NODE_CODE.IB_DATA] = {};
            }
            if(!nodeData[CONSTS.WF_NODE_CODE.WORKBENCH_DATA]) {
            	nodeData[CONSTS.WF_NODE_CODE.WORKBENCH_DATA] = {};
            }
            var simulationUuid = nodeData[CONSTS.WF_NODE_CODE.WORKBENCH_DATA][CONSTS.WF_NODE_CODE.IB_SIM_UUID];
            var jobUuid = nodeData[CONSTS.WF_NODE_CODE.WORKBENCH_DATA][CONSTS.WF_NODE_CODE.IB_UUID];
            var scienceAppData = nodeData.scienceAppData;
            var scienceAppId = scienceAppData.scienceAppId;

            /* Call API */
            if(!!simulationUuid){
            	if(!!jobUuid){
            		getSimulationJob(Liferay.ThemeDisplay.getUserId(), scienceAppData.name, scienceAppData.version,
            				simulationUuid, jobUuid, scienceAppId, wfId, node);
            	} else {
            		addSimulation(Liferay.ThemeDisplay.getUserId(), scienceAppData.name,
            				scienceAppData.version, "[]", scienceAppId, wfId, node);
            	}
            } else {
            	addSimulation(Liferay.ThemeDisplay.getUserId(), scienceAppData.name,
            			scienceAppData.version, "[]", scienceAppId, wfId, node);
            }
        })
    }

	function addSimulation(userId, appName, appVersion, jobData, appId, wfId, node){
	    var data = {
            userId : userId,
            appName : appName,
            appVersion : appVersion,
            simulationTitle : "default_simulation",
            jobData : jobData
        };
		var nodeData = node.data;
		window.Liferay.Service(
			'/edison-simulation-portlet.simulation/add-simulation',
            data, function(obj) {
				if(obj.isValid){
					/* IB_DATA Setting */
					var thisJob = currJobs.selected();
					var simulationJobId = thisJob.simulationJobId;
                    var simulationUuid = obj.simulationUuid;
                    var simulationJobUuid = obj.simulationJobUuid;
                    nodeData[CONSTS.WF_NODE_CODE.IB_DATA][CONSTS.WF_NODE_CODE.IB_SIM_UUID]= simulationUuid;
                    nodeData[CONSTS.WF_NODE_CODE.IB_DATA][CONSTS.WF_NODE_CODE.IB_UUID]= simulationJobUuid;

                    /* 2019.01.23 _ getSimulationJob*/
                    getSimulationJob(userId, appName, appVersion, simulationUuid, simulationJobUuid, appId, wfId, node);
				} else {
					toastr["error"]("", "Simulation not exist!!");
				}
			}
		);
	}

	function getSimulationJob(userId, appName, appVersion, simulationUuid, jobUuid, appId, wfId, node){

		/* Get Connected Input Ports and Disconnected Input Ports */
		var ports = node.getPorts();
        var currNodeInputPortsInfo = getNodeInputPortsInfo(ports, simulationUuid, jobUuid);
        var connectedInputPorts = currNodeInputPortsInfo.connectedInputPorts;
        var disconnectedInputPorts = currNodeInputPortsInfo.disconnectedInputPorts
        var jobDataArr = currNodeInputPortsInfo.jobDataArr
        var copyError = currNodeInputPortsInfo.error;
        if(copyError != ""){
        	toastr['warning']("", copyError);
        	return false;
        }

        /* Add flag for keeping SimulationUuid and JobUuid */
        if (0 < jobDataArr.length && 0 < disconnectedInputPorts.length) {
        	var data = {
        			userId : userId,
        			appName : appName,
        			appVersion : appVersion,
        			simulationUuid : simulationUuid,
        			jobUuid : jobUuid,
        			jobData : JSON.stringify(jobDataArr)
        	}
        	Liferay.Service(
        			'/edison-simulation-portlet.simulation/get-simulation-job',
        			data, function(obj) {
        				if(obj.hasSimulationInfo){
        					node.data[CONSTS.WF_NODE_CODE.WORKBENCH_DATA][CONSTS.WF_NODE_CODE.IB_SIM_UUID] = simulationUuid;
    						node.data[CONSTS.WF_NODE_CODE.WORKBENCH_DATA][CONSTS.WF_NODE_CODE.IB_UUID] = jobUuid;
        					var job = currJobs.selected();
        					saveSimulationJob(job, function (simulationJob){
        						if (isPauseAbleNode(node)) {
        						    pauseNode(node, true, function () {
                                        openWorkbenchPopup(appId, simulationUuid, null, connectedInputPorts, wfId);
                                    });
                                } else {
                                    openWorkbenchPopup(appId, simulationUuid, null, connectedInputPorts, wfId);
                                }
        					});
        				} else {
        					toastr["error"]("", "Simulation not exist!!");
        				}
        			}
        	);
        } else {
            toastr["error"]("", "You can not run the workbench.!!");
            return false;
        }
	}

	/* check parents node status */
	function checkParentsNodeStatus(port){
		var openWorkbench = true;

		checkStatusLoof : for ( var portIndex in port) {

			var currPort = port[portIndex];
			var portType = currPort["data"]["type"];

			if (portType == "inputPorts") {
				if (currPort.getAllEdges().length == 1) {
					 /*status is success : return true, else return false*/
					var thisNode = currPort.getAllEdges();
					var parentEdges = currPort.getTargetEdges();
					for(var parentEdgesIdx in parentEdges){
						var parentEdge = parentEdges[parentEdgesIdx];
						var sourcePort = parentEdge.source;
						var sourceNode = sourcePort.getNode();

						var status = sourceNode.data.status.status;

						if(status == OSP.Enumeration.JobStatus.SUCCESS){
							openWorkbench = true;
						} else {
							openWorkbench = false;
							break checkStatusLoof;
						}
					}

				} else {
					openWorkbench = true;
				}
			}
		}

		return openWorkbench;
	}

	function getNodeInputPortsInfo(ports, simulationUuid, jobUuid){

		var returnObj = new Object();
		var connectedParentObj = new Array();
		var connectedInputPorts = new Array();
		var disconnectedInputPorts = new Array();
		var jobDataArr = new Array();
		var copyError = ""

		for (var portIndex in ports) {
			var connectedInputPortsObj = new Object();
			var port = ports[portIndex];
			var portType = port["data"]["type"];

			if (portType == "inputPorts") {
				if (port.getAllEdges().length == 1) {

					/* Get Parent Node's JobData */
					var targetEdges = port.getTargetEdges();
					var inputPortName = port.data.id;
					for(var targetEdgesIdx in targetEdges){

						var getParentObj = getParentPortsJobData(targetEdges[targetEdgesIdx], inputPortName);

						if (getParentObj != 'undefined' && getParentObj != null && getParentObj != '') {

							connectedParentObj.push(getParentObj);

							var sourceJobDataType = getParentObj.jobData[0][OSP.Constants.TYPE];
							var isDataComponent = getParentObj.isDataComponent;

							connectedInputPortsObj[OSP.Constants.PORT_NAME] = port.id;
							if(isDataComponent){
								getParentObj[OSP.Constants.REPOSITORY_TYPE] = OSP.Enumeration.RepositoryTypes.USER_HOME;
								connectedInputPortsObj[OSP.Constants.REPOSITORY_TYPE] = OSP.Enumeration.RepositoryTypes.USER_HOME;
							} else {
								getParentObj[OSP.Constants.REPOSITORY_TYPE] = OSP.Enumeration.RepositoryTypes.USER_JOBS;
								connectedInputPortsObj[OSP.Constants.REPOSITORY_TYPE] = OSP.Enumeration.RepositoryTypes.USER_JOBS;
							}

							connectedInputPorts.push(connectedInputPortsObj);

							var getJobData = getParentObj.jobData;

							if(sourceJobDataType.toLowerCase() == "file"){
								getParentObj["targetRepositoryType"] = OSP.Enumeration.RepositoryTypes.USER_HOME;
								var copyResult = parentNodeFileCopy(getParentObj, getParentObj.simulationUuid, getParentObj.simulationJobUuid);
								copyError = copyResult.error;
								if(copyError != ""){
									returnObj.error = copyError;
									return returnObj;
								}
								if(copyResult.copyFileResult){
									jobDataArr = jobDataArr.concat(copyResult.jobData);
								} else {
									jobDataArr = jobDataArr.concat(getJobData);
								}
							}
						}
					}
					
				} else {
					disconnectedInputPorts.push(port.id);

					/* Get Node's JobData */
					var getJobData = getInputPortsJobData(port.getNode(), port.id)
					if (getJobData != 'undefined' && getJobData != null && getJobData != '') {
						jobDataArr = jobDataArr.concat(getJobData);
					}
				}
			}
		}

		returnObj.connectedParentObj = connectedParentObj;
		returnObj.connectedInputPorts = connectedInputPorts;
		returnObj.disconnectedInputPorts = disconnectedInputPorts;
		returnObj.jobDataArr = jobDataArr;
		returnObj.error = copyError;

		return returnObj;
	}

	function parentNodeFileCopy(parentObj, simulationUuid, jobUuid){
	    var simulationJob = currJobs.selected()
		parentObj.simulationUuid = simulationUuid;
		parentObj.simulationJobUuid = jobUuid;
		parentObj.simulationJobId = simulationJob.simulationJobId;

		var fn = window[namespace + "copyParentNodeFiles"];
		return fn.apply(null, [parentObj]);
	}

	function getInputPortsJobData(portNode, portId) {
		var jobDataArr = new Array();
		jobDataArr = [];

		var inputPorts = portNode.data.inputPorts;
		var inputPort = inputPorts[portId];
		var isWfSample = inputPort.isWfSample_;
		var inputPortData = inputPort.inputData_;

		if(inputPort[OSP.Constants.INPUTS]){
			inputPortData = inputPort[OSP.Constants.INPUTS];
		}else{
			if(isWfSample){
				inputPortData = inputPort.wfSample_;
			}else{
				inputPortData = inputPort.sample_;
			}
		}

		inputPortData[OSP.Constants.ORDER] = inputPort[OSP.Constants.ORDER];

		inputPortData[OSP.Constants.PORT_NAME] = portId;
		jobDataArr.push(inputPortData);
		return jobDataArr;
	}

	function getParentPortsJobData(targetEdge, inputPortName) {
		var jobDataArr = new Array();
		jobDataArr = [];
		var sourcePort = targetEdge.source;
		var sourceNode = sourcePort.getNode();
		var sourceNodeData = sourceNode.data;
		var runType = sourceNodeData.scienceAppData.runType;

		findJobData: while (runType == 'Controller'
				|| runType == 'DynamicConverter') {
			for ( var portIndex in sourceNode.getPorts()) {
				var port = sourceNode.getPorts()[portIndex];
				if ((runType == 'Controller' && port.id == 'transfer')
						|| (runType == 'DynamicConverter' && port.id == 'localfile0')) {

					var targetEdges = port.getTargetEdges();
					if (targetEdges == 'undefined' || targetEdges == null
							|| targetEdges == '') {
						toastr["error"]("", "JobData not found!!");
						break findJobData;
					} else {
						for(var targetEdgesIdx in targetEdges){
							sourcePort = targetEdges[targetEdgesIdx].source;
							if (sourcePort == undefined) {
								break findJobData;
							} else {
								sourceNode = sourcePort.getNode();
								sourceNodeData = sourceNode.data;
								runType = sourceNodeData.scienceAppData.runType;
								continue findJobData;
							}
						}
					}
				}
			}
		}

		var simulationUuid = null;
		var simulationJobUuid = null;
		var outputPortDataName = null;
		var isDataComponent = false;
		var isDlEntryId = false;
		if (runType != CONSTS.WF_APP_TYPES.CONTROLLER.NAME
				&& runType != CONSTS.WF_APP_TYPES.DYNAMIC_CONVERTER.NAME) {
			/* get outputData in outputPorts */
			var sourcePortId = sourcePort.data.id;
			var outputPorts = sourceNodeData.outputPorts;
			var outputPort = outputPorts[sourcePortId];
			if (outputPort != 'undefined' && outputPort != null) {
				var outputData = "";
				var isWfSample = outputPort.isWfSample_;
				if(runType == CONSTS.WF_APP_TYPES.FILE_COMPONENT.NAME){
					isDataComponent = true;
					if(isWfSample){
						outputData = outputPort.wfSample_;
					} else {
						outputData = outputPort.sample_;
					}
				} else{
					simulationUuid = sourceNodeData[CONSTS.WF_NODE_CODE.IB_DATA][CONSTS.WF_NODE_CODE.IB_SIM_UUID];
					simulationJobUuid = sourceNodeData[CONSTS.WF_NODE_CODE.IB_DATA][CONSTS.WF_NODE_CODE.IB_UUID];
					outputData = outputPort.outputData_;
				}
				outputData[OSP.Constants.PORT_NAME] = inputPortName;
				outputPortDataName = outputData.name_;

				var outputDataType = outputData.type_;
				jobDataArr.push(outputData);
				if (outputDataType != OSP.Constants.DLENTRY_ID) {
					isDlEntryId = true;
				}
			}
		}

		var resultObj = new Object();
		resultObj.jobData = jobDataArr;
		resultObj.simulationUuid = simulationUuid;
		resultObj.simulationJobUuid = simulationJobUuid;
		resultObj.fileName = outputPortDataName;
		resultObj.isDataComponent = isDataComponent;
		resultObj.isDlEntryId = isDlEntryId;
		resultObj.sourcePortName = sourcePort.data.id;

		return resultObj;
	}

	function openWorkbenchPopup(scienceAppId, simulationUuid, jobUuid, connectedInputPorts, nodeId) {
		var getWorkbenchHtml = null;
		window.AUI().use('liferay-portlet-url', function(A) {
			var portletURL = window.Liferay.PortletURL
					.createRenderURL();
			portletURL
					.setPortletId("SimulationWorkbench_WAR_OSPWorkbenchportlet");
			portletURL.setParameter('workbenchType',
					"SIMULATION_WITH_WORKFLOW");
			portletURL.setParameter('scienceAppId',
					scienceAppId);
			portletURL.setParameter('simulationUuid',
					simulationUuid);
			portletURL.setParameter('jobUuid', jobUuid);
			portletURL.setParameter('blockInputPorts',
					connectedInputPorts.toString());
			portletURL.setParameter('nodeId',
					nodeId);
			portletURL.setWindowState('pop_up');

			var wWidth = $(window).width();
			var wHeight = $(window).height();
			$("body").css('overflow', 'hidden')
			Liferay.Util.openWindow({
				dialog : {
					width : wWidth,
					height : wHeight,
					cache : false,
					draggable : false,
					resizable : false,
					modal : true,
					destroyOnClose : true,
					after : {
						render : function(event) {
							$("button.btn.close").on("click", function(e) {
								$("body").css('overflow','');
							});
						}
					}
				},
				id : "dataTypeSearchDialog",
				uri : portletURL.toString(),
				title : "Workbench"
			});
		});
	}

	/* 2019.01.07 _ Setting selected simulationUuid and jobUuid in the workbench */
	function setSelectedJobFromWorkbench(nodeId, simulationUuid, jobUuid, jobDataArr){
		var node = currNodes.get(nodeId);
		var nodeData = node.data;

		if(nodeData) {
			nodeData[CONSTS.WF_NODE_CODE.WORKBENCH_DATA][CONSTS.WF_NODE_CODE.IB_SIM_UUID] = simulationUuid;
			nodeData[CONSTS.WF_NODE_CODE.WORKBENCH_DATA][CONSTS.WF_NODE_CODE.IB_UUID] = jobUuid;

			var inputPorts = nodeData.inputPorts;
			var jobDataObj = JSON.parse(jobDataArr);
			for(var jobDataIdx in jobDataObj){
				var jobData = jobDataObj[jobDataIdx];
				var currPortName = jobData[OSP.Constants.PORT_NAME];
				inputPorts[currPortName][OSP.Constants.INPUTS] = jobData;
			}
		}

		var rerunMsg = $("<div/>").text(Liferay.Language.get("edison-workflow-rerun-message"))
									.css("font-size", "16px");
		$.confirm({
			icon: 'fa fa-warning',
			title: '',
			content: rerunMsg,
			theme: 'modern',
			buttons: {
				yes : {
					text: 'YES',
					action: function(){
                        insertIbUuid(node)
						// alert("YES~");
					}
				},
				no : {
					text: 'NO',
					action: function(){
					}
				}
			}
		});
	}

	return {
		"openWorkflow" : openWorkflowByWorkflowId,
		"openInputPortData" : openInputPortData,
		"openOutputPortData" : openOutputPortData,
		"openScienceAppWorkbench" : openScienceAppWorkbench,
		"setSelectedJobFromWorkbench" : setSelectedJobFromWorkbench,
		"openInputPort" : openInputPort,
		"setPortData" : setPortData,
		"getPortData" : getPortData,
		"closePortPopup" : closePortPopup,
		"openNodeHandler" : openNodeHandler,
		"isPauseAbleNode" : isPauseAbleNode,
		"isResumeAbleNode" : isResumeAbleNode,
		"consoleNodeInfo" : consoleNodeInfo,
		"isReUsableNode" : isReUsableNode,
		"setReuseNode" : setReuseNode,
		// "insertIbUuid" : insertIbUuid,
		"pauseNode" : pauseNode,
		"reuseNodeCheck" : reuseNodeCheck,
		"isEmpty" : function() {
			return _isEmpty(PANEL_DATA.setting.form.workflowId
					&& PANEL_DATA.setting.form.simulationId);
		}
	};
});
