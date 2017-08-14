<%@page import="org.kisti.edison.science.service.constants.ScienceAppConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.liferay.portal.kernel.util.StringPool"%>
<%@page import="org.kisti.edison.search.domain.SearchConstants"%>
<%@ include file="/common/init.jsp"%>
<portlet:defineObjects />
<%
boolean areaScienceApp = GetterUtil.getBoolean(portletPreferences.getValue(SearchConstants.AREA_SCIENCE_APP, StringPool.TRUE));
boolean areaContents = GetterUtil.getBoolean(portletPreferences.getValue(SearchConstants.AREA_CONTENTS, StringPool.TRUE));
boolean areaSimulationProject = GetterUtil.getBoolean(portletPreferences.getValue(SearchConstants.AREA_SIMULATION_PROJECT, StringPool.TRUE));
boolean areaScienceData = GetterUtil.getBoolean(portletPreferences.getValue(SearchConstants.AREA_SCIENCE_DATA, StringPool.TRUE));
int areaCount = 0;
String areaLabel = "";
if(areaScienceApp){
  areaCount++;
  areaLabel = SearchConstants.AREA_SCIENCE_APP_LABEL;
}
if(areaContents){
  areaCount++;
  areaLabel = SearchConstants.AREA_CONTENTS_LABEL;
}
if(areaSimulationProject){
  areaCount++;
  areaLabel = SearchConstants.AREA_SIMULATION_PROJECT_LABEL;
}
if(areaScienceData){
  areaCount++;
  areaLabel = SearchConstants.AREA_SCIENCE_DATA_LABEL;
}
%>
<liferay-portlet:resourceURL var="totalSearchUrl" id="totalSearch" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="categorySearchUrl" id="categorySearch"/>
<liferay-portlet:renderURL var="currentUrl" copyCurrentRenderParameters="false" />

<h2 class="search-main-title"><img src="${contextPath}/images/search/title_virtual.png" /><span><c:if test="<%=areaCount == 1%>"><liferay-ui:message key="<%=areaLabel%>"/> <liferay-ui:message key="search"/></c:if><c:if test="<%=areaCount != 1%>"><liferay-ui:message key="edison-search-total"/></c:if>
</span></h2>
<aui:form name="search-condition" method="post" action="<%= totalSearchUrl %>">
  <div class="top_category">
    <aui:field-wrapper name="search-panel" label="" inlineLabel="true" inlineField="true" cssClass="category01" >
      <ul>
        <c:if test="<%=areaScienceApp%>">
          <li>
            <aui:input name="<%=SearchConstants.AREA_SCIENCE_APP%>" cssClass="search-area"
              label="<%=SearchConstants.AREA_SCIENCE_APP_LABEL%>" type="checkbox" checked="true" />
              <a id="<portlet:namespace/>app-expand" class="content-expand" href="#app-expand"><i class="icon-angle-down"></i></a>
          </li>
        </c:if>
        <c:if test="<%=areaContents%>">
        <li>
          <aui:input name="<%=SearchConstants.AREA_CONTENTS%>" cssClass="search-area"
            label="<%=SearchConstants.AREA_CONTENTS_LABEL%>" type="checkbox" checked="true" />
            <a id="<portlet:namespace/>content-expand" class="content-expand" href="#content-expand"><i class="icon-angle-down"></i></a>
         </li>
        </c:if>
        <c:if test="<%=areaSimulationProject%>">
        <li>
          <aui:input name="<%=SearchConstants.AREA_SIMULATION_PROJECT%>" cssClass="search-area"
            label="<%=SearchConstants.AREA_SIMULATION_PROJECT_LABEL%>" type="checkbox" checked="true" />
        </li>
        </c:if>
        <c:if test="<%=areaScienceData%>">
        <li>
          <aui:input name="<%=SearchConstants.AREA_SCIENCE_DATA%>" cssClass="search-area"
            label="<%=SearchConstants.AREA_SCIENCE_DATA_LABEL%>" type="checkbox" checked="true" />
        </li>
        </c:if>
      </ul>
    </aui:field-wrapper>
  </div>
  <aui:field-wrapper name="search-btn" label="" inlineLabel="true" inlineField="true" cssClass="rightsearch" >
    <aui:input name="searchKeyword" type="text" label="" cssClass="search-input"/>
    <aui:button type="submit" value="edison-button-search" name="searchSubmit" cssClass="btn_blue"/>
    <aui:button type="button" value="edison-button-board-initialize" name="searchInit" cssClass="btn_blue"/>
  </aui:field-wrapper>
  <div class="category02wrap">
    <div id="<portlet:namespace/>content-expand-div">
      <div class="category02arrow"><img src="${contextPath}/images/search/arrow02.png" width="13" height="8"></div>
       <aui:field-wrapper name="search-sub-panel" label="" inlineLabel="true" inlineField="true" cssClass="category02">
        <ul>
          <li>
            <aui:input name="<%=SearchConstants.DIV_CONTENTS_CLASSNOTE%>" cssClass="content-div"
              label="<%=SearchConstants.DIV_CONTENTS_CLASSNOTE_LABEL%>" type="checkbox" checked="true" />
          </li>
          <li>
            <aui:input name="<%=SearchConstants.DIV_CONTENTS_MANUAL%>" cssClass="content-div"
              label="<%=SearchConstants.DIV_CONTENTS_MANUAL_LABEL%>" type="checkbox" checked="true" />
          </li>
          <li>
            <aui:input name="<%=SearchConstants.DIV_CONTENTS_REFERENCE%>" cssClass="content-div"
              label="<%=SearchConstants.DIV_CONTENTS_REFERENCE_LABEL%>" type="checkbox" checked="true" />
          </li>
          <li>
            <aui:input name="<%=SearchConstants.DIV_CONTENTS_ADVANCED%>" cssClass="content-div"
              label="<%=SearchConstants.DIV_CONTENTS_ADVANCED_LABEL%>" type="checkbox" checked="true" />
          </li>
        </ul>
      </aui:field-wrapper>
    </div>
  </div>
  
  <div class="category02wrap">
    <div id="<portlet:namespace/>app-expand-div">
      <div class="category02arrow app"><img src="${contextPath}/images/search/arrow02.png" width="13" height="8"></div>
       <aui:field-wrapper name="search-sub-panel" label="" inlineLabel="true" inlineField="true" cssClass="category02">
        <ul>
          <li>
            <aui:input name="<%=ScienceAppConstants.APP_TYPE_SOLVER%>" cssClass="content-div"
              label="<%=ScienceAppConstants.APP_TYPE_SOLVER%>" type="checkbox" checked="true" />
          </li>
          <li>
            <aui:input name="<%=ScienceAppConstants.APP_TYPE_CONVERTER%>" cssClass="content-div"
              label="<%=ScienceAppConstants.APP_TYPE_CONVERTER%>" type="checkbox" checked="true" />
          </li>
          <li>
            <aui:input name="<%=ScienceAppConstants.APP_TYPE_EDITOR%>" cssClass="content-div"
              label="<%=ScienceAppConstants.APP_TYPE_EDITOR%>" type="checkbox" checked="true" />
          </li>
          <li>
            <aui:input name="<%=ScienceAppConstants.APP_TYPE_ANALYZER%>" cssClass="content-div"
              label="<%=ScienceAppConstants.APP_TYPE_ANALYZER%>" type="checkbox" checked="true" />
          </li>
        </ul>
      </aui:field-wrapper>
    </div>
  </div>
</aui:form>

<div class="bottom">
  <div id="category-list" class="leftm"></div>
  <div id="search-content" class="rightcon search-content-wrapper">
    <div class="path connav">
      <ul>
        <li>Categories</li>
      </ul>
    </div>
    <div class="content boxlist">
      <ul>
        <c:forEach items="${lv1Categories}" var="rootCategory">
          <li class="category-card" id="content-${rootCategory.categoryId}" category-id="${rootCategory.categoryId}">
            <div class="block left">
              <span class="align-helper"></span>
              <c:if test="${!empty rootCategory.image}">
                <img src="${contextPath}/images/category/${rootCategory.image}.png">
              </c:if>
              <c:if test="${empty rootCategory.image}">
                <img src="${contextPath}/images/search/imagesample.png">
              </c:if>
            </div>
            <div class="block right">
              <div class="tit">
                ${rootCategory.title}
              </div>
              <div class="des">
                ${rootCategory.description}
              </div>
            </div>
          </li>
        </c:forEach>
      </ul>
    </div>
  </div>
</div>
<img id="loadingBox" src="${contextPath}/images/loading.gif" style="display: none;"/>
<script>
  var <portlet:namespace/>initData = ${categoriesJsonString};
  var <portlet:namespace/>redirectURL = '${redirectURL}';
  $(document).ajaxStart(function() {
    $("#category-list").block({
      message: "",
      overlayCSS:  { backgroundColor: "#ffffff", opacity:0.0, cursor:"wait" }
    });
    $("#search-content").block({
      message: $("#loadingBox"),
      css: { top: "3%"},
      overlayCSS:  { backgroundColor: "#ffffff", opacity:0.0, cursor:"wait" }
    });
  }).ajaxStop(function() {
    $("#search-content").unblock();
    $("#category-list").unblock();
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
  
  function <portlet:namespace/>totalSearchSubmit(e){
    if($("#<portlet:namespace/>searchKeyword").val() && 
        $.trim($("#<portlet:namespace/>searchKeyword").val()) != ""){
      <portlet:namespace/>searchSubmit(e);
    }else{
      e.preventDefault();
      alert("검색어가 입력되지 않았습니다.");
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
    var categoryData = getCategory($(this).attr("category-id"));
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
    
    $("#<portlet:namespace/>searchSubmit").click(function(e){
      <portlet:namespace/>totalSearchSubmit(e);
    });
    
    $("#<portlet:namespace/>searchInit").click(function(e){
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

    $("#<portlet:namespace/>searchKeyword").keypress(function(e){
      if (e.keyCode === 13) {
        <portlet:namespace/>totalSearchSubmit(e);
      }
    });
    
    if($("#<portlet:namespace/>searchKeyword").val()){
      <portlet:namespace/>searchSubmit();
    }
  });
</script>