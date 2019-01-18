var UIPanelExecutor = (function (namespace, $, designer, executor, toastr) {
    /*jshint -W069 */
    'use strict';
    var simulationUuid = "0028ec20-8d46-4bde-890b-7e2ac0520a32";
    var jobUuid = "fa796ee7-4b2e-424e-b665-5df2d26edfc9";
    var currNodes = eStruct("id")
    var currSimulations = eStruct("simulationId")
    var currJobs = eStruct("id", "data")
    var currInputPorts = eStruct("id")
    var currOutputPorts = eStruct("id")
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

    $(JQ_PORTLET_BOUNDARY_ID + " .sidbar-run-btn").click(function (e) {
        e.preventDefault();
        var btnType = $(this).attr("data-btn-type");
        if(!PANEL_DATA.setting.form.simulationId){
            toastr["info"]("", "Create Simulation First.");
            $(JQ_PORTLET_BOUNDARY_ID + " .sidebar-btn[data-btn-type='new']").click();
            return false;
        }
        if (btnType === "run") { run(); }
        if (btnType === "rerun") { rerun(); }
        if (btnType === "pause") { pause(); }
        if (btnType === "restart") { restart(); }
        if (btnType === "status") { status(this, "status"); }
    });

    /////////////////////////////////////////// renew start

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
        if (workflowStatus && workflowStatus.workflow && workflowStatus.workflow.simulations) {
            $.each(workflowStatus.workflow.simulations, function () {
                var simulation = this
                var nodeId = simulation.clientId
                $("#" + nodeId).removeClass(
                    "WAITING CANCELED CREATED NOT_FOUND RUNNING " +
                    "FAILED DONE SUCCESS COMPLETED PAUSED")
                $("#" + nodeId).addClass(simulation.status)
                var html = $("#" + nodeId + " .wf-node-execute-status").text(simulation.status)
                if(simulation.status === "RUNNING") {
                    $("#" + nodeId + " .top-cog-icon").addClass("fa-spin")
                } else {
                    $("#" + nodeId + " .top-cog-icon").removeClass("fa-spin")
                }
            })
        }
        // console.log(workflowStatus)
    }

    function fetchJobs(simulationId, searchKeyword, currentPage, linePerPage, selectedJobId) {
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
                        if(job) {
                            if (_isBlank(job.workflowUUID)) {
                                $(".before-submit").show()
                                $(".after-submit").hide()
                            } else {
                                $(".before-submit").hide()
                                $(".after-submit").show()
                                var initStatus = JSON.parse(job.statusResponse)
                                updateNodeStatus(initStatus)
                                executor.updateStatus(id, initStatus, updateNodeStatus)
                            }
                        }
                        $(".after-pause").hide()
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
            '    <span>{{title}}</span>\n' +
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
        jobsPagination(jobsMap.pagination, simulationId)

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
            if(isInput) {
                openInputPort(nodeId, portId)
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
            '       <a href="#"><i class="fa fa-laptop"></i><span>input</span></a>' +
            '       <ul class="treeview-menu">' +
            '       {{#data.arrInputPorts}}' +
            '       <li class="treeview port-li input" port-id="{{id}}">\n' +
            '           <a href="#" class="sidebar-btn job-li" port-id=\"{{id}}\">\n' +
            '               <i class="fa fa-edit"></i>\n' +
            '               <span>{{name_}}</span>\n' +
            // '               <span class="label label-primary pull-right sidebar-btn">\n' +
            // '               <i class="icon-arrow-right"></i>\n' +
            // '               </span>' +
            '           </a>\n' +
            '       </li>' +
            '       {{/data.arrInputPorts}}' +
            '       </ul>' +
            '    </li>\n' +
            '    <li class="treeview output">' +
            '       <a href="#"><i class="fa fa-laptop"></i><span>output</span></a>' +
            '       <ul class="treeview-menu">' +
            '       {{#data.arrOutputPorts}}' +
            '       <li class="treeview port-li output" port-id="{{id}}">\n' +
            '           <a href="#" class="sidebar-btn job-li" port-id=\"{{id}}\">\n' +
            '               <i class="fa fa-edit"></i>\n' +
            '               <span>{{name_}}</span>\n' +
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
        currNodes.set(
            designer.getCurrentJsPlumbInstance().getNodes(),
            function (id) {
            })

        $.each(currNodes.getArray(), function(i){
            var node = this
            node.data.arrInputPorts = []
            node.data.arrOutputPorts = []
            pushPorts(node.id, node.data.inputPorts, inputPorts, node.data.arrInputPorts)
            pushPorts(node.id, node.data.outputPorts, outputPorts, node.data.arrOutputPorts)
            setPortsId(node.id, node.data.inputPorts, CONSTS.WF_JSPLUMB_TYPES.INPUT)
            setPortsId(node.id, node.data.outputPorts, CONSTS.WF_JSPLUMB_TYPES.OUTPUT)
        })

        currInputPorts.set(inputPorts)
        currOutputPorts.set(outputPorts)

        var move = _instantDelay(100)
        $("#nodes-" + simulationJobId).empty()
            .append(Mustache.render(nodeLi, { "nodes": currNodes.getArray() }))
            .children("li")
            .each(function(i) {
                var nodeId = $(this).attr("node-id")
                $(this).hover(
                    function (e) {
                        move(function () { renderer.centerOnAndZoom(nodeId, 0.3) })
                        $("#" + nodeId).addClass("wf-selected-node")
                    },
                    function (e) {
                        $("#" + nodeId).removeClass("wf-selected-node")
                    })
                $(this).children("a").click(function(e){
                    currNodes.select(nodeId)
                })
                $(this).children("a").children("span.sidebar-btn").click(function(e) {
                    e.stopPropagation()
                })

                $(this).find("li.treeview.port-li").each(function(){
                    addPortHandler(this, nodeId, $(this).hasClass("input"))
                })
            })
    }

    function setPortData(nodeId, portName, strPortDataJson) {
        var portId = nodeId + "." + portName
        console.log(strPortDataJson)
        if (strPortDataJson && strPortDataJson !== "false") {
            var prevPortData = currInputPorts.get(portId)
            var inputData = JSON.parse(strPortDataJson)
            prevPortData[OSP.Constants.INPUTS] = JSON.parse(strPortDataJson)
            currInputPorts.update(portId, prevPortData)
        }
        if(currJobs.selected()) {
            validateSimulationJob()
        }
    }

    function openInputPortData(portData) {
        // console.log(portData)
        openInputPort(portData.nodeId, portData.nodeId + "." +portData.portId)
    }

    function openInputPort(nodeId, portId) {
        console.log(currJobs.selected())
        var userId = currJobs.selected() ? currJobs.selected().userId : null
        var portData = {}
        var currPortData = $.extend({}, currInputPorts.get(portId))
        delete currPortData.id
        portData[currInputPorts.get(portId)[OSP.Constants.NAME]] = currPortData;

        var node = currNodes.get(nodeId)
        if(node && node.data && node.data.ibData) {
            node.data.ibData.simulationUuid || (node.data.ibData.simulationUuid= getGUID())
            // node.data.ibData.jobUuid || (node.data.ibData.jobUuid= getGUID())

        }else{
            toastr['error']('', CONSTS.MESSAGE.edison_wfsimulation_no_valid_node_data_message)
            return false
        }

        console.log(node)

        window.AUI().use('liferay-portlet-url', function (A) {
            var portletURL = window.Liferay.PortletURL.createRenderURL();
            portletURL.setPortletId("ModuleViewer_WAR_OSPWorkbenchportlet");
            portletURL.setParameter('simulationUuid', node.data.ibData.simulationUuid);
            // portletURL.setParameter('simulationUuid', node.data.ibData.jobUuid);
            portletURL.setParameter('portData', JSON.stringify(portData));
            portletURL.setParameter('portType', "inputPorts");
            portletURL.setParameter('nodeId', nodeId);
            portletURL.setParameter('userId', userId);
            portletURL.setWindowState('pop_up');

            var wWidth = $(window).width();
            var wHeight = $(window).height();
            $("body").css('overflow', 'hidden')
            Liferay.Util.openWindow({
                dialog: {
                    width: wWidth,
                    height: wHeight,
                    cache: false,
                    draggable: false,
                    resizable: false,
                    modal: true,
                    destroyOnClose: true,
                    after: {
                        render: function (event) {
                            $("button.btn.close").on("click", function (e) {
                                $("body").css('overflow', '');
                            });
                        }
                    }
                },
                id: namespace + "inputPort",
                uri: portletURL.toString(),
                title: node.data.scienceAppData.name + " " + currPortData.name_
            });
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
        var inputs = [{"name": "Title", "value": "copy " + sourceJob.title}];
        var btns = {"ok": "Save", "cancel": "Cancel"};
        createOpenModal("Copy", inputs, btns, function(e){
            var title = $("#" + namespace + "wf-modal").find("input[name='Title']").val();
            copySimulationJob(sourceJob, title)
            $("#" + namespace + "wf-modal").modal("hide");
        });
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

    function drawWorkflowInstances(params) {
        var simulationId = getMetaData().simulationId;
        var instanceTreeSelector = "#" + namespace + "menu-panel-box .open .box-body";
        aSyncAjaxHelper.post("/delegate/services/simluation/" + simulationId + "/list",
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
            var nodeId = portData.getNode().id
            if(port.inputs_ || portData.getEdges().length > 0) {
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
    }

    function submitSimulationJob() {
        resetSubmitData()
        // console.log(designer.getCurrentJsPlumbInstance())
        var prefix = CONSTS.WF_ENGINE.CMD_PREFIX
        var jp = designer.getCurrentJsPlumbInstance()
        var outputPorts = currOutputPorts.getArray()
        $.each(outputPorts, function(){
            // console.log(this)
            var currSourcePortData = this
            var sourcePort = jp.getPort(this.id)
            // console.log(jp.getPort(sourcePort.id))
            $.each(sourcePort.getSourceEdges(), function(){
                var targetPort = this.target
                if(targetPort) {
                    // console.log(sourcePort)
                    // console.log(targetPort)
                    var prefixedId = prefix + targetPort.id
                    var sourceNode = sourcePort.getNode()
                    var targetNode = targetPort.getNode()
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
                    console.log(currSourcePortData)
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

        if(!validateSimulationJob()){
            return false
        }

        var json = designer.getCurrentJsPlumbInstance().exportData({ type: "json" })
        console.log(json)
        var simulationId = currJobs.selected().simulationId
        var simulationJobId = currJobs.selected().simulationJobId
        var token = getIcebreakerAccessToken()
        executor.createSimulationJobEngine({
            simulationId: simulationId,
            simulationJobId: simulationJobId,
            icebreakerVcToken: token.icebreakerVcToken,
            groupId: token.groupId,
            strNodes: JSON.stringify(json.nodes)
        }, function(simulationJob) {
            // console.log(submitData)
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
                    executor.updateStatus(simulationJob.id, initStatus, updateNodeStatus)
                }
            } else {
                // TODO : currentPage
                fetchJobs(simulationId, null, currPageJob, null, simulationJobId)
            }

        })
        // console.log(designer.getCurrentJsPlumbInstance().getNodes())
    }

    /////////////////////////////////////////// renew end

    function run(){
        // console.log("run");
        var simulationId = PANEL_DATA.setting.form.simulationId;
        var simulationTitle = PANEL_DATA.setting.form.simulationTitle;
        executor.getWorkflowInstance(simulationId,
            function (currentWorkflowInstance) {
                if(currentWorkflowInstance.status === WF_STATUS_CODE.CREATED){
                    var ibToken = getIcebreakerAccessToken();
                    saveWorkflowInstance(simulationId, simulationTitle,
                        function (workflowInstance) {
                            executor.runWorkflowInstance(simulationId, ibToken);
                            toastr["success"]("", var_success_run_workflow_message);
                        });
                }else{
                    toastr["error"]("", var_already_run_message);
                }
            }, function () { });

    }
    function rerun(){
        if($(".wf-box.reset").length){
            var simulationId = PANEL_DATA.setting.form.simulationId;
            executor.reRunWorkflowInstance(simulationId,
                designer.getWorkflowDefinition(designer.getCurrentJsPlumbInstance()),
                function (workflowInstance) {
                    var ibToken = getIcebreakerAccessToken();
                    executor.runWorkflowInstance(workflowInstance.simulationId, ibToken);
                    toastr["success"]("", var_success_run_workflow_message);
                });
        }else{
            toastr["error"]("","Reset First.");
        }

    }
    function pause(){
        var simulationId = PANEL_DATA.setting.form.simulationId;
        executor.getWorkflowInstance(simulationId,
            function (currentWorkflowInstance) {
                if(currentWorkflowInstance.status === WF_STATUS_CODE.RUNNING){
                    executor.pauseWorkflowInstance(simulationId, function(workflowInstance){
                        toastr["success"]("", var_pause_success_message);
                    });
                }else{
                    toastr["error"]("", "Workflow is not running.");
                }});
    }

    function restart(){
        var simulationId = PANEL_DATA.setting.form.simulationId;
        executor.getWorkflowInstance(simulationId,
            function (currentWorkflowInstance) {
                if(currentWorkflowInstance.status === WF_STATUS_CODE.PAUSED){
                    executor.resumeWorkflowInstance(simulationId, function(workflowInstance){
                        toastr["success"]("", var_resume_success_message);
                    });
                }else{
                    toastr["error"]("", "Workflow is not paused.");
                }});
    }

    function drawWorkflowStatus(simulationId){
        executor.getWorkflowInstanceStatus(simulationId, function(workflowStatus){
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
        var simulationId = PANEL_DATA.setting.form.simulationId;
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

        drawWorkflowStatus(simulationId);

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
            var selectedWorkflowInstanceId = PANEL_DATA.setting.form.simulationId;
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
                var simulationId = node.data.simulationId;
                loadInstance(simulationId);
                //displayJob(nodeId);
            }
        }).bind("hover_node.jstree", function(event, data){
        });
    }

    function loadInstance(simulationId) {
        executor.loadWorkflowInstance(simulationId,
            function (workflowInstance) {
                designer.resetWorkflow();
                designer.drawScreenLogic(workflowInstance.screenLogic);
                setMetaData({
                    "title": PANEL_DATA.setting.form.title,
                    "description": PANEL_DATA.setting.form.description,
                    "workflowId": PANEL_DATA.setting.form.workflowId,
                    "simulationTitle": workflowInstance.title,
                    "simulationId": workflowInstance.simulationId
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

    function saveOrUpdateWorkflowInstance(panelDataType) {
        if (isValidate()) {
            if (_isEmpty(PANEL_DATA.setting.form.simulationId)) {
                newSimulation(panelDataType);
            } else {
                var simulationId = PANEL_DATA.setting.form.simulationId;
                var simulationTitle = PANEL_DATA.setting.form.simulationTitle;
                saveWorkflowInstance(simulationId, simulationTitle,
                    function (workflowInstance) {
                        setMetaData({
                            "title": PANEL_DATA.setting.form.title,
                            "description": PANEL_DATA.setting.form.description,
                            "workflowId": PANEL_DATA.setting.form.workflowId,
                            "simulationTitle": workflowInstance.title,
                            "simulationId": workflowInstance.simulationId
                        });
                        toastr["success"]("", var_save_success_message);
                        closePanel();
                    });
            }
        }
    }

    function saveWorkflowInstance(simulationId, simulationTitle, callback) {
        executor.updateWorkflowInstance(simulationId, simulationTitle,
            designer.getWorkflowDefinition(designer.getCurrentJsPlumbInstance()), callback);
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
		var modal = $("#" + namespace + "science-app-workbench-modal");
		var openWorkbench = true;
		
		var nodeData = node.data;
		var wfId = nodeData.id;

		if(nodeData[CONSTS.WF_NODE_CODE.IB_DATA] == 'undefined' || nodeData[CONSTS.WF_NODE_CODE.IB_DATA] == null){
			nodeData[CONSTS.WF_NODE_CODE.IB_DATA] = {};
		}
		var simulationUuid = nodeData[CONSTS.WF_NODE_CODE.IB_DATA][CONSTS.WF_NODE_CODE.IB_SIM_UUID];
		var jobUuid = nodeData[CONSTS.WF_NODE_CODE.IB_DATA][CONSTS.WF_NODE_CODE.IB_UUID];
		var scienceAppData = nodeData.scienceAppData;
		var scienceAppId = scienceAppData.scienceAppId;
		var ports = node.getPorts(); 
		var inputPorts = nodeData.inputPorts;
		
		/* test Uuid */
		/*simulationUuid = "0028ec20-8d46-4bde-890b-7e2ac0520a32";
		jobUuid = "fa796ee7-4b2e-424e-b665-5df2d26edfc9";*/
		
		/* Parents node status check */
		openWorkbench = checkParentsNodeStatus(ports);
		/* Parents node exist and parents node not successed _ Not open workbench */
		if(!openWorkbench){
			return false;
		}
		
		/* Get Connected Input Ports and Disconnected Input Ports */
		var currNodeInputPortsInfo = getNodeInputPortsInfo(ports, simulationUuid, jobUuid);
		var connectedInputPorts = currNodeInputPortsInfo.connectedInputPorts;
		var disconnectedInputPorts = currNodeInputPortsInfo.disconnectedInputPorts
		var jobDataArr = currNodeInputPortsInfo.jobDataArr

		if (0 < jobDataArr.length) {
			/* Call API get-simulation-job */
			if(simulationUuid != 'undefined' && simulationUuid != '' && simulationUuid != null){
				if(jobUuid != 'undefined' && jobUuid != '' && jobUuid != null){
					getSimulationJob(Liferay.ThemeDisplay.getUserId(), scienceAppData.name, scienceAppData.version, 
											simulationUuid, jobUuid, JSON.stringify(jobDataArr), scienceAppId,
											connectedInputPorts, wfId);
				} else {
					addSimulation(Liferay.ThemeDisplay.getUserId(), scienceAppData.name, 
										scienceAppData.version, JSON.stringify(jobDataArr), scienceAppId,
										connectedInputPorts, wfId);
				}
			} else {
				addSimulation(Liferay.ThemeDisplay.getUserId(), scienceAppData.name, 
									scienceAppData.version, JSON.stringify(jobDataArr), scienceAppId,
									connectedInputPorts, wfId);
			}
			
		} else {
			toastr["error"]("", "JobData not found!!");
		}
	}
	
	function addSimulation(userId, appName, appVersion, jobData, appId, connInputPorts, wfId){
		
		Liferay.Service(
			'/edison-simulation-portlet.simulation/add-simulation',
			{
				userId : userId,
				appName : appName,
				appVersion : appVersion,
				simulationTitle : "default_simulation",
				jobData : jobData
			}, function(obj) {
				if(obj.isValid){
					openWorkbenchPopup(appId, null, null, connInputPorts, wfId);
				} else {
					toastr["error"]("", "Simulation not exist!!");
				}
			}
		);
	}
	
	function getSimulationJob(userId, appName, appVersion, simulationUuid, jobUuid, jobData, appId, connInputPorts, wfId){
		Liferay.Service(
			'/edison-simulation-portlet.simulation/get-simulation-job',
			{
				userId : userId,
				appName : appName,
				appVersion : appVersion,
				simulationUuid : simulationUuid,
				jobUuid : jobUuid,
				jobData : jobData
			}, function(obj) {
				if(obj.hasSimulationInfo){
					openWorkbenchPopup(appId, simulationUuid, jobUuid, connInputPorts, wfId);
				} else {
					toastr["error"]("", "Simulation not exist!!");
				}
			}
		);
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
					var thisNode = currPort.getNode();
					var status = thisNode.data.status.status;
					
					if(status == OSP.Enumeration.JobStatus.SUCCESS){
						openWorkbench = true;
					} else {
						openWorkbench = false;
						break checkStatusLoof;
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
		var connectedInputPorts = new Array();
		var disconnectedInputPorts = new Array();
		var jobDataArr = new Array();
		
		for (var portIndex in ports) {
			var connectedInputPortsObj = new Object();
			var port = ports[portIndex];
			var portType = port["data"]["type"];
			
			if (portType == "inputPorts") {
				if (port.getAllEdges().length == 1) {
					
					/* Get Parent Node's JobData */
					var targetEdges = port.getTargetEdges();
					var inputPortName = port.data.id;
					var getParentObj = getParentPortsJobData(targetEdges[0], inputPortName);
					
					if (getParentObj != 'undefined' && getParentObj != null && getParentObj != '') {
						
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
						/*getParentObj[OSP.Constants.PORT_NAME] = getJobData;*/
						jobDataArr = jobDataArr.concat(getJobData);
						
						getParentObj.simulationUuid = simulationUuid;
						getParentObj.simulationJobUuid = jobUuid;
						if(sourceJobDataType.toLowerCase() == "file"){
							getParentObj["targetRepositoryType"] = OSP.Enumeration.RepositoryTypes.USER_HOME;
							parentNodeFileCopy(getParentObj);
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
		
		returnObj.connectedInputPorts = connectedInputPorts;
		returnObj.disconnectedInputPorts = disconnectedInputPorts;
		returnObj.jobDataArr = jobDataArr;
		
		return returnObj;
	}
	
	function parentNodeFileCopy(parentObjData){
		var workflowId = PANEL_DATA.open.form.params.workflowId;
		parentObjData["workflowId"] = workflowId;
		var fn = window[namespace + "copyParentNodeFiles"];
		fn.apply(null, [parentObjData]);
	}

	function getInputPortsJobData(portNode, portId) {
		var jobDataArr = new Array();
		jobDataArr = [];
		
		var inputPorts = portNode.data.inputPorts;
		var inputPort = inputPorts[portId];
		var isWfSample = inputPort.isWfSample_;
		var inputPortData = inputPort.inputData_;
		if(isWfSample){
			inputPortData = inputPort.wfSample_;
		} else {
			if(inputPort.inputData_){
				inputPortData = inputPort.inputData_;
			} else {
				inputPortData = inputPort.sample_;
			}
		}
		
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
						sourcePort = targetEdges[0].source;
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
				if (outputDataType != 'dlEntryId_') {
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
			/*portletURL.setParameter('simulationUuid',
					simulationUuid);*/
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
	function setSelectedJobFromWorkbench(nodeId, simulationUuid, jobUuid){
		var node = designer.getCurrentJsPlumbInstance().getNode(nodeId);
		var nodeData = node.data;
		if(nodeData) {
			if(!nodeData[CONSTS.WF_NODE_CODE.IB_DATA]) {
				nodeData[CONSTS.WF_NODE_CODE.IB_DATA] = {}
			}
			nodeData[CONSTS.WF_NODE_CODE.IB_DATA][CONSTS.WF_NODE_CODE.IB_SIM_UUID] = simulationUuid;
			nodeData[CONSTS.WF_NODE_CODE.IB_DATA][CONSTS.WF_NODE_CODE.IB_UUID] = jobUuid;

			/* TODO Workflow Status Setting */

			nodeData[CONSTS.WF_NODE_CODE.STATUS].status = "";
		}
	}

	return {
		"openWorkflow" : openWorkflowByWorkflowId,
		"openInputPortData" : openInputPortData,
		"openScienceAppWorkbench" : openScienceAppWorkbench,
		"setSelectedJobFromWorkbench" : setSelectedJobFromWorkbench,
		"openInputPort" : openInputPort,
		"setPortData" : setPortData,
		"isEmpty" : function() {
			return _isEmpty(PANEL_DATA.setting.form.workflowId
					&& PANEL_DATA.setting.form.simulationId);
		}
	};
});
