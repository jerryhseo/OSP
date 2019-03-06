<%@page import="org.kisti.edison.science.service.constants.ScienceAppConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.liferay.portal.kernel.util.StringPool"%>
<%@page import="org.kisti.edison.search.domain.SearchConstants"%>
<%@ include file="/common/init.jsp"%>
<portlet:defineObjects />
<%
    boolean areaScienceApp = GetterUtil
                    .getBoolean(portletPreferences.getValue(SearchConstants.AREA_SCIENCE_APP, StringPool.TRUE));
            boolean areaContents = GetterUtil
                    .getBoolean(portletPreferences.getValue(SearchConstants.AREA_CONTENTS, StringPool.TRUE));
            boolean areaSimulationProject = GetterUtil
                    .getBoolean(portletPreferences.getValue(SearchConstants.AREA_SIMULATION_PROJECT, StringPool.TRUE));
            boolean areaScienceData = GetterUtil
                    .getBoolean(portletPreferences.getValue(SearchConstants.AREA_SCIENCE_DATA, StringPool.TRUE));
            int areaCount = 0;
            String areaLabel = "";
            if (areaScienceApp) {
                areaCount++;
                areaLabel = SearchConstants.AREA_SCIENCE_APP_LABEL;
            }
            if (areaContents) {
                areaCount++;
                areaLabel = SearchConstants.AREA_CONTENTS_LABEL;
            }
            if (areaSimulationProject) {
                areaCount++;
                areaLabel = SearchConstants.AREA_SIMULATION_PROJECT_LABEL;
            }
            if (areaScienceData) {
                areaCount++;
                areaLabel = SearchConstants.AREA_SCIENCE_DATA_LABEL;
            }
%>
<liferay-portlet:resourceURL var="totalSearchUrl" id="totalSearch" copyCurrentRenderParameters="false" />
<liferay-portlet:resourceURL var="categorySearchUrl" id="categorySearch" />
<liferay-portlet:renderURL var="currentUrl" copyCurrentRenderParameters="false" />

<link rel="stylesheet" href="${contextPath}/css/adminlte/AdminLTE.css">
<link rel="stylesheet" href="${contextPath}/css/adminlte/skins/skin-black-light.css">
<link rel="stylesheet" href="${contextPath}/css/search.css">
<script src="${contextPath}/js/adminlte/adminlte.js"></script>

<h2 class="search-main-title">
    <img src="${contextPath}/images/sub_tit_bl.png" class="search-main-title-image" /> <span> <c:if
            test="<%=areaCount == 1%>">
            <liferay-ui:message key="<%=areaLabel%>" />
            <liferay-ui:message key="search" />
        </c:if> <c:if test="<%=areaCount != 1%>">
            <liferay-ui:message key="edison-search-total" />
        </c:if>
    </span>
</h2>
<aui:form name="search-condition" method="post" action="<%=totalSearchUrl%>">
    <div class="hidden-check">
        <c:if test="<%=areaScienceApp%>">
            <aui:input name="<%=SearchConstants.AREA_SCIENCE_APP%>" type="checkbox" checked="true" />
        </c:if>
        <c:if test="<%=areaContents%>">
            <aui:input name="<%=SearchConstants.AREA_CONTENTS%>" type="checkbox" checked="true" />
        </c:if>
        <c:if test="<%=areaSimulationProject%>">
            <aui:input name="<%=SearchConstants.AREA_SIMULATION_PROJECT%>" type="checkbox" checked="true" />
        </c:if>
        <c:if test="<%=areaScienceData%>">
            <aui:input name="<%=SearchConstants.AREA_SCIENCE_DATA%>" type="checkbox" checked="true" />
        </c:if>
    </div>

    <div class="hidden-md hidden-lg" style="margin-top: 10px;">
        <div class="row">
            <div class="col-md-12 col-xs-12 col-sm-12 hidden-sm hidden-xs" style="padding: 10px 10px 0px 10px !important;">
                <div class="input-group-btn">
                    <c:if test="<%=areaScienceApp%>">
                    <div class="input-group-btn">
                        <button type="button" btn-id="<%=SearchConstants.AREA_SCIENCE_APP%>" class="btn btn-primary btn-flat btn-category-check">
                            <liferay-ui:message key="<%=SearchConstants.AREA_SCIENCE_APP_LABEL%>" />
                        </button>
                        <button type="button" btn-id="<%=SearchConstants.AREA_SCIENCE_APP%>" class="btn btn-primary btn-flat btn-category-check dropdown-toggle" data-toggle="dropdown">
                            <i class="icon-angle-down"></i>
                        </button>
                        <ul class="dropdown-menu">
                            <li><aui:input name="<%=ScienceAppConstants.APP_TYPE_SOLVER%>" cssClass="content-div"
                                    label="<%=ScienceAppConstants.APP_TYPE_SOLVER%>" type="checkbox" checked="true" /></li>
                            <li><aui:input name="<%=ScienceAppConstants.APP_TYPE_CONVERTER%>"
                                    cssClass="content-div" label="<%=ScienceAppConstants.APP_TYPE_CONVERTER%>"
                                    type="checkbox" checked="true" /></li>
                            <li><aui:input name="<%=ScienceAppConstants.APP_TYPE_EDITOR%>" cssClass="content-div"
                                    label="<%=ScienceAppConstants.APP_TYPE_EDITOR%>" type="checkbox" checked="true" /></li>
                            <li><aui:input name="<%=ScienceAppConstants.APP_TYPE_ANALYZER%>" cssClass="content-div"
                                    label="<%=ScienceAppConstants.APP_TYPE_ANALYZER%>" type="checkbox" checked="true" /></li>
                        </ul>
                    </div>
                    </c:if>
                    <c:if test="<%=areaContents%>">
                    <div class="input-group-btn">
                        <button type="button" btn-id="<%=SearchConstants.AREA_CONTENTS%>" class="btn btn-primary btn-flat btn-category-check">
                            <liferay-ui:message key="<%=SearchConstants.AREA_CONTENTS_LABEL%>" />
                        </button>
                        <button type="button" btn-id="<%=SearchConstants.AREA_CONTENTS%>" class="btn btn-primary btn-flat btn-category-check dropdown-toggle" data-toggle="dropdown">
                            <i class="icon-angle-down"></i>
                        </button>
                        <ul class="dropdown-menu">
                            <li><aui:input name="<%=SearchConstants.DIV_CONTENTS_CLASSNOTE%>"
                                    cssClass="content-div" label="<%=SearchConstants.DIV_CONTENTS_CLASSNOTE_LABEL%>"
                                    type="checkbox" checked="true" /></li>
                            <li><aui:input name="<%=SearchConstants.DIV_CONTENTS_MANUAL%>"
                                    cssClass="content-div" label="<%=SearchConstants.DIV_CONTENTS_MANUAL_LABEL%>"
                                    type="checkbox" checked="true" /></li>
                            <li><aui:input name="<%=SearchConstants.DIV_CONTENTS_REFERENCE%>"
                                    cssClass="content-div" label="<%=SearchConstants.DIV_CONTENTS_REFERENCE_LABEL%>"
                                    type="checkbox" checked="true" /></li>
                            <li><aui:input name="<%=SearchConstants.DIV_CONTENTS_ADVANCED%>"
                                    cssClass="content-div" label="<%=SearchConstants.DIV_CONTENTS_ADVANCED_LABEL%>"
                                    type="checkbox" checked="true" /></li>
                        </ul>
                    </div>
                    </c:if>
                    <c:if test="<%=areaSimulationProject%>">
                    <div class="input-group-btn">
                        <button type="button" btn-id="<%=SearchConstants.AREA_SIMULATION_PROJECT%>" class="btn btn-primary btn-flat btn-category-check">
                            <liferay-ui:message key="<%=SearchConstants.AREA_SIMULATION_PROJECT_LABEL%>" />
                        </button>
                    </div>
                    </c:if>
                    <c:if test="<%=areaScienceData%>">
                    <div class="input-group-btn">
                        <button type="button" btn-id="<%=SearchConstants.AREA_SCIENCE_DATA%>" class="btn btn-primary btn-flat btn-category-check">
                            <liferay-ui:message key="<%=SearchConstants.AREA_SCIENCE_DATA_LABEL%>" />
                        </button>
                    </div>
                    </c:if>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12 col-xs-12 col-sm-12" style="padding: 0px 10px 10px 10px !important;">
                <div class="input-group-btn">
                    <aui:input name="searchKeyword_mobile" type="text" label="" cssClass="form-control search-btn-height"
                    style="border-top-left-radius: 0; border-bottom-left-radius: 0; width: auto; float: left; margin-top: 5px;" 
                    placeholder="Search Keyword"/>
                    <aui:button type="button" value="edison-button-search" name="searchSubmit_mobile"
                        cssClass="btn btn-primary search-btn-height" />
                    <aui:button type="button" value="edison-button-board-initialize" name="searchInit_mobile"
                        cssClass="btn btn-primary btn-flat search-btn-height" />
                </div>
            </div>
        </div>
    </div>

    <div class="hidden-xs hidden-sm" style="margin-top: 10px;">
        <div class="row">
            <div class="col-md-12">
                <div class="input-group">
                    <div class="input-group-btn">
                        <c:if test="<%=areaScienceApp%>">
                        <div class="input-group-btn">
                            <button type="button" btn-id="<%=SearchConstants.AREA_SCIENCE_APP%>" class="btn btn-primary btn-flat btn-category-check search-btn-height">
                                <liferay-ui:message key="<%=SearchConstants.AREA_SCIENCE_APP_LABEL%>" />
                            </button>
                            <button type="button" btn-id="<%=SearchConstants.AREA_SCIENCE_APP%>" class="btn btn-primary btn-flat btn-category-check dropdown-toggle search-btn-height"
                                data-toggle="dropdown">
                                <i class="icon-angle-down"></i>
                            </button>
                            <ul class="dropdown-menu" style="padding: 10px 0px 10px 10px;">
                                <li><aui:input name="<%=ScienceAppConstants.APP_TYPE_SOLVER%>"
                                        cssClass="content-div" label="<%=ScienceAppConstants.APP_TYPE_SOLVER%>"
                                        type="checkbox" checked="true" /></li>
                                <li><aui:input name="<%=ScienceAppConstants.APP_TYPE_CONVERTER%>"
                                        cssClass="content-div" label="<%=ScienceAppConstants.APP_TYPE_CONVERTER%>"
                                        type="checkbox" checked="true" /></li>
                                <li><aui:input name="<%=ScienceAppConstants.APP_TYPE_EDITOR%>"
                                        cssClass="content-div" label="<%=ScienceAppConstants.APP_TYPE_EDITOR%>"
                                        type="checkbox" checked="true" /></li>
                                <li><aui:input name="<%=ScienceAppConstants.APP_TYPE_ANALYZER%>"
                                        cssClass="content-div" label="<%=ScienceAppConstants.APP_TYPE_ANALYZER%>"
                                        type="checkbox" checked="true" /></li>
                            </ul>
                        </div>
                        </c:if>
                        <c:if test="<%=areaContents%>">
                        <div class="input-group-btn">
                            <button type="button" btn-id="<%=SearchConstants.AREA_CONTENTS%>" class="btn btn-primary btn-flat btn-category-check search-btn-height">
                                <liferay-ui:message key="<%=SearchConstants.AREA_CONTENTS_LABEL%>" />
                            </button>
                            <button type="button" btn-id="<%=SearchConstants.AREA_CONTENTS%>" class="btn btn-primary btn-flat btn-category-check dropdown-toggle search-btn-height"
                                data-toggle="dropdown">
                                <i class="icon-angle-down"></i>
                            </button>
                            <ul class="dropdown-menu" style="padding: 10px 0px 10px 10px;">
                                <li><aui:input name="<%=SearchConstants.DIV_CONTENTS_CLASSNOTE%>"
                                        cssClass="content-div" label="<%=SearchConstants.DIV_CONTENTS_CLASSNOTE_LABEL%>"
                                        type="checkbox" checked="true" /></li>
                                <li><aui:input name="<%=SearchConstants.DIV_CONTENTS_MANUAL%>"
                                        cssClass="content-div" label="<%=SearchConstants.DIV_CONTENTS_MANUAL_LABEL%>"
                                        type="checkbox" checked="true" /></li>
                                <li><aui:input name="<%=SearchConstants.DIV_CONTENTS_REFERENCE%>"
                                        cssClass="content-div" label="<%=SearchConstants.DIV_CONTENTS_REFERENCE_LABEL%>"
                                        type="checkbox" checked="true" /></li>
                                <li><aui:input name="<%=SearchConstants.DIV_CONTENTS_ADVANCED%>"
                                        cssClass="content-div" label="<%=SearchConstants.DIV_CONTENTS_ADVANCED_LABEL%>"
                                        type="checkbox" checked="true" /></li>
                            </ul>
                        </div>                            
                        </c:if>
                        <c:if test="<%=areaSimulationProject%>">
                        <div class="input-group-btn">
                            <button type="button" btn-id="<%=SearchConstants.AREA_SIMULATION_PROJECT%>" class="btn btn-primary btn-flat btn-category-check search-btn-height" style="margin-left: -1px;">
                                <liferay-ui:message key="<%=SearchConstants.AREA_SIMULATION_PROJECT_LABEL%>" />
                            </button>
                        </div>
                        </c:if>
                        <c:if test="<%=areaScienceData%>">
                        <div class="input-group-btn">
                            <button type="button" btn-id="<%=SearchConstants.AREA_SCIENCE_DATA%>" class="btn btn-primary btn-flat btn-category-check search-btn-height" style="margin-left: -1px;">
                                <liferay-ui:message key="<%=SearchConstants.AREA_SCIENCE_DATA_LABEL%>" />
                            </button>
                        </div>
                        </c:if>
                    </div>
                    <aui:input name="searchKeyword" type="text" label="" cssClass="form-control search-btn-height" 
                    style="border-top-left-radius: 0; border-bottom-left-radius: 0;" />
                    <div class="input-group-btn">
                        <aui:button type="button" value="edison-button-search" name="searchSubmit"
                            cssClass="btn btn-primary search-btn-height" />
                        <aui:button type="button" value="edison-button-board-initialize" name="searchInit"
                            cssClass="btn btn-primary btn-flat search-btn-height" />
                    </div>
                </div>
            </div>
        </div>
    </div>
</aui:form>

<div class="row">
    <div class="col-md-3 hidden-xs hidden-sm" style="padding-right: 0px !important;">
        <div id="category-list" class="leftm"></div>
    </div>
    <div id="search-content" class="col-md-9" style="padding: 10px !important;">
        <div class="path connav hidden-xs hidden-sm" style="margin-bottom: 10px;">
            <ul>
                <li></li>
            </ul>
        </div>
        <div class="content boxlist">
        </div>
    </div>

    <img id="loadingBox" src="${contextPath}/images/loading.gif" style="display: none;" />
    <script>
  var <portlet:namespace/>initData = ${categoriesJsonString};
  var <portlet:namespace/>redirectURL = '${redirectURL}';
  $(document).ajaxStart(function() {
    /* $("#category-list").block({
      message: "",
      overlayCSS:  { backgroundColor: "#ffffff", opacity:0.0, cursor:"wait" }
    });
    $("#search-content").block({
      message: $("#loadingBox"),
      css: { top: "3%"},
      overlayCSS:  { backgroundColor: "#ffffff", opacity:0.0, cursor:"wait" }
    }); */
    bStart();
  }).ajaxStop(function() {
      bEnd();
    /* $("#search-content").unblock();
    $("#category-list").unblock(); */
  });
  
  function getSelectedCategoryId(){
    var selectedCategory = $("#category-list").jstree("get_selected");
    if(selectedCategory
        && $.isArray(selectedCategory) && selectedCategory.length > 0){
      return selectedCategory[0];  
    }
    return null;
  }
  
  function isParentCategory(selectedCategoryId){
    var nodeData = $("#category-list")
    .jstree(true).get_node(selectedCategoryId).data;
    if(nodeData["parentCategoryId"] === 0){
      return true;
    }
    return false;
  }
  
  function <portlet:namespace/>getPostData(){
    var selectedCategoryId = getSelectedCategoryId();
    var postData = $("#<portlet:namespace/>search-condition").serializeObject();
    if(selectedCategoryId){
      postData["<portlet:namespace/>categoryId"] = selectedCategoryId;
      postData["<portlet:namespace/>parentCategory"] = isParentCategory(selectedCategoryId);
    }
    return postData;
  }
  
  function <portlet:namespace/>totalSearchSubmit(e, element){
    var elementId = $(element).attr("id");
    if(elementId && elementId.indexOf("mobile") > 0){
      $("#<portlet:namespace/>searchKeyword").val($("#<portlet:namespace/>searchKeyword_mobile").val())
    }
    if($("#<portlet:namespace/>searchKeyword").val() && 
        $.trim($("#<portlet:namespace/>searchKeyword").val()) != ""){
        <portlet:namespace/>searchSubmit(e);
    }else{
      e.preventDefault();
      alert("<liferay-ui:message key='edison-search-no-search-keyword' />");
    }
  }

  function <portlet:namespace/>searchSubmit(e){
    if(e){
      e.preventDefault();
    }
    
    $("#search-content > .content").empty();
    var postData = <portlet:namespace/>getPostData();
    $("#search-content > .content").load("${totalSearchUrl}",
        <portlet:namespace/>getPostData(), 
      function(){
        drawPaths(getSelectedCategoryId());
      });
  }

  function getCategory(categoryId) {
    var category = {};
    $.each(<portlet:namespace/>initData, function(i) {
      var that = this;
      if(that.categoryId == categoryId) {
        category = that;
      }
    });
    return category;
  }

  function getSubCategories(parentCategoryId) {
    var subCategories = [];
    $.each(<portlet:namespace/>initData, function(i) {
      var that = this;
      if (that.parentCategoryId == parentCategoryId) {
        subCategories.push(that);
      }
    });
    return subCategories;
  }

  function getContentCategoryFragment(categories) {
    if (!$.isArray(categories)) {
      return;
    }
    var ulFrag = $("<ul/>", {});
    var liFrag = $("<li/>", {
      "class" : "category-card"
    });
    
    liFrag.append($("<div/>", {
      "class" : "block left"
    }).append($("<span/>", {
        "class" : "align-helper"
    })).append($("<img/>", {
      "src" : "${contextPath}/images/search/imagesample.png"
    })));
    liFrag.append($("<div/>", {
      "class" : "block right"
    }));
    $.each(categories, function(i) {
      var that = this;
      var liNode = liFrag.clone();
      liNode.attr("id", that.categoryId);
      liNode.attr("category-id", that.categoryId);
      liNode.find(".block.right").append($("<div/>", {
        "class": "tit",
        "text" : that.title
      }));
      liNode.find(".block.right").append($("<div/>", {
        "class": "des",
        "text" : that.description
      }));
      if(that["image"]){
        liNode.find(".block.left > img")
          .attr("src", "${contextPath}/images/solverType/"+ that["image"] +".png");
      }
      liNode.on("click", categoryCardClickHandler);
      ulFrag.append(liNode);
    });
    return ulFrag;
  }

  function drawPaths(categoryId) {
    if(categoryId){
      var pathText = "<li>Paths</li>";
      var paths = $("#category-list").jstree(true).get_path("#" + categoryId, ',').split(",");
      $.each(paths, function(){
        if(this){
          pathText += "<li>&gt;</li>"; 
          pathText += "<li>" + this + "</li>"; 
        }
      });
      $("#search-content .path > ul").html(pathText);
    }
  }

  function drawContentCategory(ulFrag) {
    $("#search-content > .content").empty();
    $("#search-content > .content").append(ulFrag);
  }
  
  function getJsTreeNodeById(nodeId){
    return $("#category-list").jstree(true).get_node(nodeId);
  }

  function selectJstreeNode(categoryId) {
    $("#category-list").jstree("deselect_all");
    $("#category-list").jstree("select_node", "#" + categoryId);
  }

  function categoryCardClickHandler(e) {
    e.preventDefault();
    selectCategory($(this).attr("category-id"));
  }
  
  function selectCategory(categoryId){
      var categoryData = getCategory(categoryId);
      if(categoryData.type === "category") {
        var categories = getSubCategories(categoryData.categoryId);
        var ulFrag = getContentCategoryFragment(categories);
        var jsTreeNode = getJsTreeNodeById(categoryData.categoryId);
        drawContentCategory(ulFrag);
        drawPaths(categoryData.id);
        jsTreeNode["data"]["isParentCardClick"] = true;
      }
      selectJstreeNode(categoryData.categoryId);  
  }
  
  function categorySelectHandler(e, jstreeData){
    drawPaths(jstreeData.id);
    loadCategoryDataView(e, jstreeData.id);
  }

  function loadCategoryDataView(e, categoryId) {
    <portlet:namespace/>searchSubmit(e);
  }

  /* doc ready */
  $(document).ready(function() {
    $("#category-list").jstree({
      "core" : {
        "check_callback" : function(operation, node, node_parent, node_position, more) {
          if (operation === 'move_node') {
            return false;
          }
          return true;
        },
        "data" : <portlet:namespace/>initData
      },
      "types" : {
        "category" : {},
        "subCategory" : {}
      },
      "plugins" : [ "types" ]
    }).bind("hover_node.jstree", function(node) {
    }).bind("loaded.jstree", function(event, data) {
      var parameterCategoryId = "${param.categoryId}"; 
      if(parameterCategoryId){
        selectJstreeNode(parameterCategoryId);
      }
      if(${isSingleCategory}){
        selectJstreeNode(${singleCategoryId});
      }
      
    }).bind("select_node.jstree", function(event, data) {
      var nodeId = data.node.id;
      var node = data.node;
      if(node.type === "category" 
          && !$("#" + nodeId).hasClass("jstree-open")){
        $("#category-list").jstree("close_all");
        $("#category-list").jstree("open_node", node);
      }
      if(!data.node.data["isParentCardClick"]){
        categorySelectHandler(event, node);
      }else{
        data.node.data["isParentCardClick"] = false;
      }
    });
    $(".category-card").on("click", categoryCardClickHandler);
    
    $("#<portlet:namespace/>searchSubmit, #<portlet:namespace/>searchSubmit_mobile").click(function(e){
        <portlet:namespace/>totalSearchSubmit(e, this);
    });
    
    $("#<portlet:namespace/>searchInit, #<portlet:namespace/>searchInit_mobile").click(function(e){
      location.reload();
    });
    
    $("#<portlet:namespace/>app-expand-div").hide();
    $("#<portlet:namespace/>app-expand").click(function(e){
      $(this).children("i").toggleClass("icon-angle-down");
      $(this).children("i").toggleClass("icon-angle-up");
      $("#<portlet:namespace/>content-expand").children("i").removeClass("icon-angle-up");
      $("#<portlet:namespace/>content-expand").children("i").removeClass("icon-angle-down");
      $("#<portlet:namespace/>content-expand").children("i").addClass("icon-angle-down");
      $("#<portlet:namespace/>content-expand-div").hide();
      $("#<portlet:namespace/>app-expand-div").slideToggle();
    });
    
    $("#<portlet:namespace/>content-expand-div").hide();
    $("#<portlet:namespace/>content-expand").click(function(e){
      $(this).children("i").toggleClass("icon-angle-down");
      $(this).children("i").toggleClass("icon-angle-up");
      $("#<portlet:namespace/>app-expand").children("i").removeClass("icon-angle-up");
      $("#<portlet:namespace/>app-expand").children("i").removeClass("icon-angle-down");
      $("#<portlet:namespace/>app-expand").children("i").addClass("icon-angle-down");
      $("#<portlet:namespace/>app-expand-div").hide();
      $("#<portlet:namespace/>content-expand-div").slideToggle();
    });

    $("#<portlet:namespace/>searchKeyword, #<portlet:namespace/>searchKeyword_mobile").keypress(function(e){
      if (e.keyCode === 13) {
        <portlet:namespace/>totalSearchSubmit(e, this);
      }
    });
    
    $(".btn-category-check").click(function (e){
        if(!$(this).hasClass("dropdown-toggle")) {
            var btnId = $(this).attr("btn-id");
            console.log(btnId);
            $("#<portlet:namespace/>" + btnId + "Checkbox").click();
            if($("#<portlet:namespace/>" + btnId).val() == "false") {
                $("button[btn-id='" + btnId + "']").removeClass("btn-primary")
                $("button[btn-id='" + btnId + "']").addClass("btn-default")
            } else {
                $("button[btn-id='" + btnId + "']").addClass("btn-primary")
                $("button[btn-id='" + btnId + "']").removeClass("btn-default")
            }
        }
    });
    
    if(!${isSingleCategory}){
      <portlet:namespace/>searchSubmit();
    }
    /* if($("#<portlet:namespace/>searchKeyword").val()){
    } */
  });
</script>