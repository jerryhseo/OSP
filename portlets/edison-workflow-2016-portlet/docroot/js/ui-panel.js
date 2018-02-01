var UIPanel = (function (namespace, $, designer, toastr) {
    'use strict';
    var PANEL_DATA = {
        "apps": {
            "col": 4,
            "panel-type": "apps",
            "header":{
                "id": "tpl-menu-panel-search-header",
                "search-input-name": "search"
            } 
        }, "new": {
            "col": 6,
            "panel-type": "new",
            "body": "tpl-menu-panel-new",
            "form": {},
            "btn": {
                "create": newDesigner
            }
        }, "open": {
            "col": 10,
            "panel-type": "open",
            "body": "tpl-menu-panel-load",
            "header":{
                "id": "tpl-menu-panel-search-header",
                "search-input-name": "search",
                "theads": ["Title", "Description", "Copied from", "Created"]
            },
            "form": {},
            "footer": "tpl-menu-panel-pagination"
        }, "import": {
            "col": 10,
            "panel-type": "import",
            "body": "tpl-menu-panel-load",
            "header":{
                "id": "tpl-menu-panel-search-header",
                "search-input-name": "search",
                "theads": ["Title", "Version", "User ID", "Description"]
            },
            "form": {},
            "footer": "tpl-menu-panel-pagination"
        }, "save-as": {
            "col": 6,
            "panel-type": "save",
            "body": "tpl-menu-panel-save",
            "form": {},
            "btn": {
                "save": saveAsDesigner
            }
        }, "setting": {
            "col": 6,
            "panel-type": "setting",
            "body": "tpl-menu-panel-setting",
            "form": {}
        }
    };
    var JQ_PORTLET_BOUNDARY_ID = "#p_p_id" + namespace;

    $(JQ_PORTLET_BOUNDARY_ID + " .sidebar-btn[data-btn-type='save']").click(function (e) {
        e.stopPropagation();
        designer.saveOrUpdateWorkflowDefinition(PANEL_DATA.setting.form);
    });

    $(JQ_PORTLET_BOUNDARY_ID + " .sidebar-btn").click(function (e) {
        e.preventDefault();
        var btnType = $(this).attr("data-btn-type");
        var templateData = PANEL_DATA[btnType];

        if (templateData) {
            activateLi(this);
            templateData.boxtitle = $(this).text();
            if (btnType === "apps") {
                if (!$("#" + namespace + "menu-panel-box-app").hasClass("loaded")) {
                }
                $("#" + namespace + "menu-panel-box-app").show();
                $("#" + namespace + "menu-panel-box").hide();
            } else {
                $("#" + namespace + "menu-panel-box-app").hide();
                $("#" + namespace + "menu-panel-box").show();
                $("#" + namespace + "menu-panel-box").empty().mustache('tpl-menu-panel-box', templateData);
                $("#" + namespace + "menu-panel-box .box-body").mustache(templateData.body, templateData);
                if(templateData.header){
                    $("#" + namespace + "menu-panel-box .box-header.with-border.header-inner > .box-title")
                        .replaceWith($.Mustache.render(templateData.header.id, templateData));
                }
                if (templateData.footer) {
                    $("#" + namespace + "menu-panel-box .box")
                        .append($.Mustache.render(templateData.footer, templateData));
                }
            }
            loadWorkflowDefinitions(btnType);
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
            $(".menu-panel").show('slide', { direction: 'left' }, 500);
        }
    });

    function newDesigner(panelDataType, that, event) {
        if (isValidate()) {
            if (PANEL_DATA.setting.form.title && !confirm(var_new_workflow_confirm_message)) {
                closePanel();
                return false;
            }
            designer.resetWorkflow();
            setTitle(PANEL_DATA[panelDataType].form.title);
            PANEL_DATA.setting.form = $.extend({}, PANEL_DATA[panelDataType].form);
            PANEL_DATA["save-as"].form = PANEL_DATA.setting.form;
            PANEL_DATA[panelDataType].form.title = "";
            PANEL_DATA[panelDataType].form.description = "";
            toastr["success"]("", var_create_success_message);
            closePanel();

        }
    }

    function saveAsDesigner(panelDataType, that, event) {
        if (isValidate()) {
            setTitle(PANEL_DATA[panelDataType].form.title);
            PANEL_DATA.setting.form = $.extend({}, PANEL_DATA[panelDataType].form);
            designer.saveAsWorkflowDefinition(PANEL_DATA[panelDataType].form);
        }
    }

    function setTitle(titleText) {
        $("#" + namespace + "workflow-title").text(titleText);
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

    function loadWorkflowDefinitions(panelType, currentPage){
        if(panelType === 'open' || panelType === 'import'){
            currentPage = currentPage || 1;
            var templateData = PANEL_DATA[panelType];
            var isPublic = panelType === 'import';
            var params = getWorkflowDefinitionParam(
                getValueByInputName(
                    templateData.header["search-input-name"]), currentPage, isPublic);
            templateData.form.params = params;
                
            drawWorkflowDefinitions(params, isPublic);
        }
    }

    function drawWorkflowDefinitions(params, isPublic) {
        aSyncAjaxHelper.post("/delegate/services/workflows",
            params,
            function (result) {
                drawWorkflowTable(result, isPublic);
            },
            function (msg) {
                toastr["error"]("", msg);
            });
    }

    function getValueByInputName(inputName){
        return $("input[name='" + inputName + "']").val();
    }

    function getWorkflowDefinitionParam(searchKeyword, p_curPage, isPublic, linePerPage){
        var params = {};
        if (searchKeyword || searchKeyword === 0) {
            params.title = searchKeyword;
        }
        if (p_curPage) {
            params.p_curPage = p_curPage;
        }
        if (linePerPage) {
            params.linePerPage = linePerPage;
        }
        if (isPublic) {
            params.isPublic = "true";
        }
        return params;
    }

    function drawWorkflowTable(workflowsMap, isPublic) {
        var tbodyTemplate = isPublic ? 
            '{{#rows}}'+
            '    <tr workflow-id="{{workflowId}}">'+
            '        <td>{{title}}</td>'+
            '        <td>{{version}}</td>'+
            '        <td>{{screenName}}</td>'+
            '        <td>{{descrption}}</td>'+
            '    </tr>'+
            '{{/rows}}' :
            '{{#rows}}'+
            '    <tr workflow-id="{{workflowId}}">'+
            '        <td>{{title}}</td>'+
            '        <td>{{descrption}}</td>'+
            '        <td>{{parentTitle}}</td>'+
            '        <td>{{createDate}}</td>'+
            '    </tr>'+
            '{{/rows}}';
        var paginationTemplate =
            '<li class="prev"><a href="#">«</a></li>' +
            '{{#pages}}' +
            '<li class="page-num {{active}}"><a href="#" page-num="{{num}}">{{num}}</a></li>' +
            '{{/pages}}' +
            '<li class="next"><a href="#">»</a></li>';
        
        tbody(workflowsMap.workflows, tbodyTemplate);
        pagination(workflowsMap.pagination, paginationTemplate, isPublic);
    }

    function panelType(isPublic){
        return isPublic ? "import" : "open";
    }

    function pagination(paginationData, paginationTemplate, isPublic){
        console.log(paginationData);
        var pages = [];
        for(var i = paginationData.startPage; i <= paginationData.endPage; i++){
            pages.push({
                "active": paginationData.currentPage === i ? "active" : "",
                "num": i
            });
        }
        $(JQ_PORTLET_BOUNDARY_ID + " .menu-panel .pagination").empty().append(
            Mustache.render(paginationTemplate, { "pages": pages }));
        $(JQ_PORTLET_BOUNDARY_ID + " .menu-panel .pagination .page-num > a").click(function (e) {
            e.preventDefault();
            loadWorkflowDefinitions(panelType(isPublic), $(this).attr("page-num"));
        });
        if (paginationData.curBlock > 1 && paginationData.curBlock <= paginationData.totalBlock) {
            $(JQ_PORTLET_BOUNDARY_ID + " .menu-panel .pagination .prev > a").click(function (e) {
                e.preventDefault();
                loadWorkflowDefinitions(panelType(isPublic), paginationData.startPage - 1);
            });
        }else{
            $(JQ_PORTLET_BOUNDARY_ID + " .menu-panel .pagination .prev").addClass("disabled");
        }
        if (paginationData.curBlock < paginationData.totalBlock) {
            $(JQ_PORTLET_BOUNDARY_ID + " .menu-panel .pagination .next > a").click(function (e) {
                e.preventDefault();
                loadWorkflowDefinitions(panelType(isPublic), paginationData.endPage + 1);
            });
        }else{
            $(JQ_PORTLET_BOUNDARY_ID + " .menu-panel .pagination .next").addClass("disabled");
        }
    }

    function tbody(workflows, tbodyTemplate) {
        if (workflows && workflows.length > 0) {
            $.each(workflows, function (i) {
                workflows[i].createDate = $.format.date(
                    new Date(workflows[i].createDate), "yyyy.MM.dd HH:mm");
            });
            $(JQ_PORTLET_BOUNDARY_ID + " .menu-panel tbody.panel-tbody").empty().append(
                Mustache.render(tbodyTemplate, { "rows": workflows }));
        }
    }
    
});


