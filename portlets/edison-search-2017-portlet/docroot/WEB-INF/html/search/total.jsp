<%@page import="com.liferay.portal.service.PortletLocalServiceUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>
<style>
.tab-sub-title {
 float: left;
 border-bottom: none !important;
}

.tab-sub-title-hr {
 clear: both;
 border-bottom: solid 1px #444;
}

</style>

<portlet:defineObjects />

<liferay-portlet:resourceURL var="getEncodedUrlUrl" id="getEncodedUrl" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="appSearchUrl" id="appSearch"  copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="contentSearchUrl" id="contentSearch"  copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="projectSearchUrl" id="projectSearch"  copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="dataSearchUrl" id="dataSearch"  copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="downloadManualURL" id="downloadManual" copyCurrentRenderParameters="false"/>
<liferay-portlet:renderURL var="currentUrl" copyCurrentRenderParameters="false"/>

<liferay-portlet:renderURL var="workbenchURL" copyCurrentRenderParameters="false" plid="${workBenchPlid}" 
  windowState="<%=LiferayWindowState.POP_UP.toString()%>"
  portletName="SimulationWorkbench_WAR_OSPWorkbenchportlet">
    <liferay-portlet:param name="workbenchType" value="SIMULATION_WITH_APP"/>
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="projectDetailUrl" portletName="edisonsimulationproject_WAR_edisonsimulationproject2017portlet" 
  windowState="<%=LiferayWindowState.MAXIMIZED.toString() %>" >
  <liferay-portlet:param name="myRender" value="detailView" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="scienceAppDetailUrl" portletName="edisonscienceAppstore_WAR_edisonappstore2016portlet" 
  windowState="<%=LiferayWindowState.MAXIMIZED.toString() %>" >
  <liferay-portlet:param name="myaction" value="detailView" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="contentDetailUrl" portletName="edisoncontent_WAR_edisoncontent2016portlet" 
  windowState="<%=LiferayWindowState.MAXIMIZED.toString() %>" >
  <liferay-portlet:param name="myaction" value="generalModifyView" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="openDataDetailUrl" portletName="datasearch_WAR_SDR_baseportlet" 
  windowState="<%=LiferayWindowState.MAXIMIZED.toString() %>" >
  <liferay-portlet:param name="controller" value="Collection"/>
  <liferay-portlet:param name="action" value="collectionDetail"/>
</liferay-portlet:renderURL>

<div class="nav-tabs-custom">
    <ul id="<portlet:namespace/>search-tab-button" class="nav nav-tabs" style="${isSingleSearch ? 'display:none;' : ''}">
        <li class="active" onclick="<portlet:namespace/>toggleTab(this);"><a id="total-tab-button"
            href="#<portlet:namespace/>total-search-tab" data-toggle="tab"> <liferay-ui:message
                    key="edison-search-total" /><span class="hidden-xs hidden-sm">(${searchResults.appCount + searchResults.contentCount + searchResults.projectCount + searchResults.dataCount})</span>
        </a></li>
        <c:if test="${param.areaScienceApp}">
            <li onclick="<portlet:namespace/>toggleTab(this);"><a id="app-tab-button"
                href="#<portlet:namespace/>app-search-tab" data-toggle="tab"> <liferay-ui:message
                        key="edison-search-science-app" /><span class="hidden-xs hidden-sm">(${searchResults.appCount})</span>
            </a></li>
        </c:if>
        <c:if test="${param.areaContents}">
            <li onclick="<portlet:namespace/>toggleTab(this);"><a id="content-tab-button"
                href="#<portlet:namespace/>content-search-tab" data-toggle="tab"> <liferay-ui:message
                        key="edison-search-contents" /><span class="hidden-xs hidden-sm">(${searchResults.contentCount})</span>
            </a></li>
        </c:if>
        <c:if test="${param.areaSimulationProject}">
            <li onclick="<portlet:namespace/>toggleTab(this);"><a id="project-tab-button"
                href="#<portlet:namespace/>project-search-tab" data-toggle="tab"> <liferay-ui:message
                        key="edison-search-simulation-project" /><span class="hidden-xs hidden-sm">(${searchResults.projectCount})</span></a>
            </li>
        </c:if>
        <c:if test="${param.areaScienceData}">
            <li onclick="<portlet:namespace/>toggleTab(this);"><a id="data-tab-button"
                href="#<portlet:namespace/>data-search-tab" data-toggle="tab"> <liferay-ui:message
                        key="edison-search-science-data" /><span class="hidden-xs hidden-sm">(${searchResults.dataCount})</span></a>
            </li>
        </c:if>
    </ul>
    <div class="tab-content box">
        <c:if test="${!isSingleSearch}">
            <div id="<portlet:namespace/>total-search-tab" class="tab-pane fade in active">
                <c:if test="${searchResults.appCount > 0}">
                    <div class="box-header">
                        <h3 class="styleh3">
                            <liferay-ui:message key="edison-search-science-app" />
                            (${searchResults.appCount}
                            <liferay-ui:message key="edison-search-cnt" />
                            )
                        </h3>
                        <div class="box-tools pull-right"></div>
                    </div>

                    <div class="search-results box-body">
                        <ul class="products-list product-list-in-box">
                            <c:forEach items="${searchResults.appResults}" var="element">
                                <li class="item">
                                    <div class="product-img">
                                        <img class="profile-user-img img-responsive" alt="${element.currentTitle}"
                                            src="/documents/${element.iconRepositoryId}/${element.iconId}/${element.iconTitle}/${element.iconUuid}?imageThumbnail=2"
                                            width="67px" style="height: 50px !important;"
                                            onerror="this.src='${contextPath}/images/noimage.png'">
                                    </div>
                                    <div class="product-info">
                                        <a class="product-title" href="#appdetail"
                                            onclick="<portlet:namespace/>moveScienceAppDetail(${element.groupId}, ${element.scienceAppId}); return false;">
                                            ${element.name}</a>
                                        <div class="pull-right hidden-sm hidden-xs"
                                            style="line-height: 2.3em !important;">
                                            <c:if
                                                test="${!empty element.current_manualId and element.current_manualId ne 0}">
                                                <img src="${contextPath}/images/search/btn_manual.jpg"
                                                    style="height: 24px; cursor: pointer;"
                                                    onClick="<portlet:namespace/>fileDownload('${element.current_manualId}')" />
                                            </c:if>
                                            <c:if
                                                test="${empty element.current_manualId or element.current_manualId eq 0}">
                                                <img src="${contextPath}/images/search/btn_manual_none.jpg"
                                                    style="height: 24px; cursor: default;" />
                                            </c:if>
                                            <c:if
                                                test="${workBenchPlid ne 0 and isSignedIn and element.openLevel ne downloadOnly and element.appType eq 'Solver'}">
                                                <img src="${contextPath}/images/search/btn_run.jpg"
                                                    style="cursor: pointer; height: 24px;"
                                                    onClick="<portlet:namespace/>moveWorkBench('${element.scienceAppId}');" />
                                            </c:if>
                                            <c:if test="${workBenchPlid ne 0 and element.openLevel eq downloadOnly}">
                                                <img src="${contextPath}/images/download_btn.gif"
                                                    style="cursor: pointer; height: 24px;"
                                                    onClick="<portlet:namespace/>fileDownload('${element.srcFileName}')" />
                                            </c:if>
                                        </div>
                                        <span class="product-description"> <c:if test="${!empty element.title}">
                                ${element.title}<br />version : ${element.version} / <liferay-ui:message
                                                    key="edison-virtuallab-owner" /> : ${element.screenName}
                              </c:if> <c:if test="${empty element.title}">
                                                <liferay-ui:message key="edison-search-no-detail" />
                                            </c:if>
                                        </span>
                                        <div class="hidden-md hidden-lg" style="line-height: 2.3em !important;">
                                            <c:if
                                                test="${!empty element.current_manualId and element.current_manualId ne 0}">
                                                <img src="${contextPath}/images/search/btn_manual.jpg"
                                                    style="height: 24px; cursor: pointer;"
                                                    onClick="<portlet:namespace/>fileDownload('${element.current_manualId}')" />
                                            </c:if>
                                            <c:if
                                                test="${empty element.current_manualId or element.current_manualId eq 0}">
                                                <img src="${contextPath}/images/search/btn_manual_none.jpg"
                                                    style="height: 24px; cursor: default;" />
                                            </c:if>
                                            <c:if
                                                test="${workBenchPlid ne 0 and isSignedIn and element.openLevel ne downloadOnly and element.appType eq 'Solver'}">
                                                <img src="${contextPath}/images/search/btn_run.jpg"
                                                    style="cursor: pointer; height: 24px;"
                                                    onClick="<portlet:namespace/>moveWorkBench('${element.scienceAppId}');" />
                                            </c:if>
                                            <c:if test="${workBenchPlid ne 0 and element.openLevel eq downloadOnly}">
                                                <img src="${contextPath}/images/download_btn.gif"
                                                    style="cursor: pointer; height: 24px;"
                                                    onClick="<portlet:namespace/>fileDownload('${element.srcFileName}')" />
                                            </c:if>
                                        </div>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                    <c:if test="${searchResults.appCount > 5}">
                        <div class="box-footer text-center">
                            <a href="#appMore" onclick="<portlet:namespace/>moreTab('app'); return false;"
                                class="uppercase"> <liferay-ui:message key="edison-search-more" />
                            </a>
                        </div>
                    </c:if>
                </c:if>

                <c:if test="${searchResults.contentCount > 0}">
                    <div class="box-header">
                        <h3 class="styleh3">
                            <liferay-ui:message key="edison-search-contents" />
                            (${searchResults.contentCount}
                            <liferay-ui:message key="edison-search-cnt" />
                            )
                        </h3>
                        <div class="box-tools pull-right"></div>
                    </div>

                    <div class="search-results box-body">
                        <ul class="products-list product-list-in-box">
                            <c:forEach items="${searchResults.contentResults}" var="element">
                                <li class="item">
                                    <div class="product-img">
                                        <img class="profile-user-img img-responsive" alt="${element.currentTitle}"
                                            src="/documents/${element.iconRepositoryId}/${element.iconId}/${element.iconTitle}/${element.iconUuid}?imageThumbnail=2"
                                            width="67px" style="height: 50px !important;"
                                            onerror="this.src='${contextPath}/images/noimage.png'">
                                    </div>
                                    <div class="product-info">
                                        <a class="product-title" href="#contentDetail"
                                            onclick="<portlet:namespace/>moveContentDetail(${element.contentSeq}, ${element.contentDiv}); return false;">
                                            ${element.title}</a> <span class="product-description"> ${element.resume}
                                            <c:if test="${empty element.resume}">
                                                <liferay-ui:message key="edison-search-no-detail" />
                                            </c:if>
                                        </span>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                        <c:if test="${searchResults.contentCount > 5}">
                            <div class="box-footer text-center">
                                <a href="#appMore" onclick="<portlet:namespace/>moreTab('content'); return false;"
                                    class="uppercase"> <liferay-ui:message key="edison-search-more" />
                                </a>
                            </div>
                        </c:if>
                    </div>
                </c:if>
                <c:if test="${searchResults.projectCount > 0}">
                    <div class="box-header">
                        <h3 class="styleh3">
                            <liferay-ui:message key="edison-search-simulation-project" />
                            (${searchResults.projectCount}
                            <liferay-ui:message key="edison-search-cnt" />
                            )
                        </h3>
                        <div class="box-tools pull-right"></div>
                    </div>
                    <div class="search-results box-body">
                        <ul class="products-list product-list-in-box">
                            <c:forEach items="${searchResults.projectResults}" var="element">
                                <li class="item">
                                    <div class="product-img">
                                        <img class="profile-user-img img-responsive" alt="${element.currentTitle}"
                                            src="/documents/${element.iconRepositoryId}/${element.iconId}/${element.iconTitle}/${element.iconUuid}?imageThumbnail=2"
                                            width="67px" style="height: 50px !important;"
                                            onerror="this.src='${contextPath}/images/noimage.png'">
                                    </div>
                                    <div class="product-info">
                                        <a href="#simulationProjectDetail"
                                            onclick="<portlet:namespace/>moveSimulationProjectDetail(${element.simulationProjectId}); return false;">${element.title}</a>
                                        <span class="product-description">
                                            ${element.explain}
                                            <c:if test="${empty element.explain}">
                                                <liferay-ui:message key="edison-search-no-detail" />
                                            </c:if>
                                        </span>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                        <c:if test="${searchResults.projectCount > 5}">
                            <div class="box-footer text-center">
                                <a href="#appMore" onclick="<portlet:namespace/>moreTab('project'); return false;"
                                    class="uppercase"> <liferay-ui:message key="edison-search-more" />
                                </a>
                            </div>
                        </c:if>
                    </div>
                </c:if>

                <c:if test="${searchResults.dataCount > 0}">
                    <div class="box-header">
                        <h3 class="styleh3">
                            <liferay-ui:message key="edison-search-science-data" />
                            (${searchResults.dataCount}
                            <liferay-ui:message key="edison-search-cnt" />
                            )
                        </h3>
                        <div class="box-tools pull-right"></div>
                    </div>
                    
                    <div class="search-results box-body">
                        <ul class="products-list product-list-in-box">
                            <c:forEach items="${searchResults.dataResults}" var="element">
                                <li class="item">
                                    <div class="product-img">
                                        <img class="profile-user-img img-responsive" alt="${element.vo.title}"
                                            src="/documents/${element.iconRepositoryId}/${element.iconId}/${element.iconTitle}/${element.iconUuid}?imageThumbnail=2"
                                            width="67px" style="height: 50px !important;"
                                            onerror="this.src='${contextPath}/images/noimage.png'">
                                    </div>
                                    <div class="product-info">
                                        <a href="#dataDetail"
                                             onclick="<portlet:namespace/>moveDataDetail(${element.vo.collectionId}); return false;">${element.vo.title}
                                        <c:if test="${!empty element.dsCnt && element.dsCnt ne 0}">(${element.dsCnt })</c:if>
                                        <span class="label label-warning pull-right">
                                            <c:choose>
                                                <c:when test="${empty element.embago}">
                                                    Inavailable
                                                </c:when>
                                                <c:when test="${element.embago == 0}">
                                                    Available now.
                                                </c:when>
                                                <c:otherwise>
                                                    Available in
                                                    <c:if test="${element.embago > 30}">
                                                        <fmt:parseNumber value="${element.embago / 30}" integerOnly="true" /> month.
                                                    </c:if>
                                                    <c:if test="${element.embago <= 30}">
                                                        ${element.embago} day.
                                                    </c:if>
                                                </c:otherwise>
                                            </c:choose>
                                        </span>
                                        </a>
                                        <span class="product-description">
                                            ${element.vo.description}
                                        </span>
                                        <span class="product-description">
                                        <fmt:formatDate value="${element.vo.createDate }" pattern="yyyy-MM-dd" />
                                        | ${element.vo.doi } | ${element.communityname }
                                        <c:choose>
                                            <c:when test="${!empty element.contributorNames[0] }">
                                          | ${element.contributorNames[0] }
                                          <c:if test="${fn:length(element.contributorNames) > 1 }">
                                              and ${fn:length(element.contributorNames) - 1} others
                                          </c:if>
                                                        </c:when>
                                                        <c:otherwise> | ${element.userName }</c:otherwise>
                                                    </c:choose>
                                        </span>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                        <c:if test="${searchResults.dataCount > 5}">
                            <div class="box-footer text-center">
                                <a href="#appMore" onclick="<portlet:namespace/>moreTab('data'); return false;"
                                    class="uppercase"> <liferay-ui:message key="edison-search-more" />
                                </a>
                            </div>
                        </c:if>
                    </div>
                </c:if>
                <c:if
                    test="${searchResults.appCount + searchResults.contentCount + searchResults.projectCount + searchResults.dataCount eq 0}">
                    <div class="search-results">
                        <ul style="min-height: 100px; margin-top: 20px;">
                            <li><span class="result-none"><liferay-ui:message key="edison-search-no-result" /></span></li>
                        </ul>
                    </div>
                </c:if>
            </div>
        </c:if>
        <c:if test="${param.areaScienceApp}">
            <div id="<portlet:namespace/>app-search-tab" class="search-tab conlist" style="display: none;">
                <div class="box-header">
                    <h3 class="styleh3">
                        <liferay-ui:message key="edison-search-science-app" />
                        (${searchResults.appCount}
                        <liferay-ui:message key="edison-search-cnt" />
                        )
                    </h3>
                    <div class="box-tools pull-right col-xs-4">
                        <div class="input-group">
                            <select class="form-control" name="<portlet:namespace/>sort-field" tab-type="app">
                                <option value="${SORT_FIELD_CREATED}">Latest</option>
                                <%-- <option value="${SORT_FIELD_VIEW}">View</option> --%>
                                <option value="${SORT_FIELD_NAME}">Name</option>
                            </select>
                            <div class="input-group-btn">
                                <button class="btn btn-default sort-order" title="${SORT_ORDER_DESC}" tab-type="app">
                                    <i class="icon-sort-by-attributes-alt"> </i>
                                </button>
                                <input type="hidden" name="<portlet:namespace/>sort-order" value="${SORT_ORDER_DESC}"
                                    tab-type="app" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="search-results"></div>
            </div>
        </c:if>
        <c:if test="${param.areaContents}">
          <div id="<portlet:namespace/>content-search-tab" class="search-tab conlist" style="display: none;">
            <div class="box-header">
                <h3 class="styleh3">
                    <liferay-ui:message key="edison-search-contents" />
                    (${searchResults.contentCount}
                    <liferay-ui:message key="edison-search-cnt" />
                    )
                </h3>
                <div class="box-tools pull-right col-xs-4">
                    <div class="input-group">
                        <select class="form-control" name="<portlet:namespace/>sort-field" tab-type="content">
                          <option value="${SORT_FIELD_CREATED}">Latest</option>
                          <option value="${SORT_FIELD_VIEW}">View</option>
                          <%-- <option value="${SORT_FIELD_NAME}">Name</option> --%>
                        </select>
                        <div class="input-group-btn">
                            <button class="btn btn-default sort-order"
                                title="${SORT_ORDER_DESC}" tab-type="content">
                                <i class="icon-sort-by-attributes-alt"> </i>
                            </button>
                            <input type="hidden" name="<portlet:namespace/>sort-order" value="${SORT_ORDER_DESC}"
                                tab-type="content" />
                        </div>
                    </div>
                </div>
            </div>
            <div class="search-results"></div>
          </div>
        </c:if>
        <c:if test="${param.areaSimulationProject}">
          <div id="<portlet:namespace/>project-search-tab" class="search-tab conlist" style="display: none;">
          
          <div class="box-header">
                <h3 class="styleh3">
                    <liferay-ui:message key="edison-search-simulation-project" />
                    (${searchResults.projectCount}
                    <liferay-ui:message key="edison-search-cnt" />
                    )
                </h3>
            </div>
            <div class="search-results"></div>
          </div>
        </c:if>
        <c:if test="${param.areaScienceData}">
          <div id="<portlet:namespace/>data-search-tab" class="search-tab conlist" style="display: none;">
            <div class="box-header">
                <h3 class="styleh3">
                    <liferay-ui:message key="edison-search-science-data" />
                    (${searchResults.dataCount}
                    <liferay-ui:message key="edison-search-cnt" />
                    )
                </h3>
                <div class="box-tools pull-right col-xs-4">
                    <div class="input-group">
                        <select class="form-control" name="<portlet:namespace/>sort-field" tab-type="data">
                            <option value="${SORT_FIELD_CREATED}">Latest</option>
                            <%-- <option value="${SORT_FIELD_VIEW}">View</option> --%>
                            <option value="${SORT_FIELD_NAME}">Name</option>
                        </select>
                        <div class="input-group-btn">
                            <button class="btn btn-default sort-order" title="${SORT_ORDER_DESC}" 
                                tab-type="data">
                                <i class="icon-sort-by-attributes-alt"> </i>
                            </button>
                            <input type="hidden" name="<portlet:namespace/>sort-order"
                                value="${SORT_ORDER_DESC}" tab-type="data" />
                        </div>
                    </div>
                </div>
            </div>
            <div class="search-results"></div>
          </div>
        </c:if>
<script>
var <portlet:namespace/>TAB_TYPE = {
  "APP": "app",  
  "CONTENT": "content",  
  "PROJECT": "project",  
  "DATA": "data"
};


$(document).ready(function(){
  var <portlet:namespace/>searchTypes = {
      areaScienceApp: "app",
      areaContents: "content",
      areaSimulationProject: "project",
      areaScienceData: "data"
  };
  var isSingleSearch = ${isSingleSearch};
    
  $("#<portlet:namespace/>search-tab-button a").click(function(e){
    e.preventDefault();
  });

  $(".sort-order").click(function(e){
    e.preventDefault();
    var jqChildI = $(this).children("i");
    var tabType = $(this).attr("tab-type");
    var jqSortOrderInput = $(this).siblings("input[name='<portlet:namespace/>sort-order']");
    if(jqChildI.hasClass("icon-sort-by-attributes-alt")){
      jqChildI.removeClass("icon-sort-by-attributes-alt");
      jqChildI.addClass("icon-sort-by-attributes");
      jqSortOrderInput.val("${SORT_ORDER_ASC}");
      $(this).attr("title", "${SORT_ORDER_ASC}");
    }else{
      jqChildI.removeClass("icon-sort-by-attributes");
      jqChildI.addClass("icon-sort-by-attributes-alt");
      jqSortOrderInput.val("${SORT_ORDER_DESC}");
      $(this).attr("title", "${SORT_ORDER_DESC}");
    }
    <portlet:namespace/>loadTab(tabType, 1);
  });

  $("select[name='<portlet:namespace/>sort-field']").on("change", function(){
    var tabType = $(this).attr("tab-type");
    <portlet:namespace/>loadTab(tabType, 1);
  });
  
  if(isSingleSearch){
    var tabId = <portlet:namespace/>getTabId(<portlet:namespace/>searchTypes['${singleSearchType}']);
    <portlet:namespace/>loadTab(<portlet:namespace/>searchTypes['${singleSearchType}'], 1);
    $("#" + tabId).show();
  }
  
});

function <portlet:namespace/>moreTab(tabType) {
    $("#" + tabType + "-tab-button").click();
}

function <portlet:namespace/>toggleTabByTabType(tabType){
  $("#<portlet:namespace/>search-tab-button > li").removeClass("sel");
  $("#" + tabType + "-tab-button").parent().addClass("sel");
  $(".search-tab").each(function(){
    var thisTab = this;
    if($(thisTab).attr("id") == <portlet:namespace/>getTabId(tabType)){
      if(!$(thisTab).hasClass("loaded")){
        <portlet:namespace/>loadTab(tabType);
      }
      $(thisTab).show();
    }else{
      $(thisTab).hide();
    }
  });
}

function <portlet:namespace/>toggleTab(that){
  var tabType = $(that).children("a").attr("id").replace("-tab-button", "");
  <portlet:namespace/>toggleTabByTabType(tabType);
}

function <portlet:namespace/>getTabId(tabType){
  return "<portlet:namespace/>" + tabType + "-search-tab";
}

function <portlet:namespace/>loadApps(currentPage){
  <portlet:namespace/>loadTab(<portlet:namespace/>TAB_TYPE.APP, currentPage);
}

function <portlet:namespace/>loadContents(currentPage){
  <portlet:namespace/>loadTab(<portlet:namespace/>TAB_TYPE.CONTENT, currentPage);
}

function <portlet:namespace/>loadProjects(currentPage){
  <portlet:namespace/>loadTab(<portlet:namespace/>TAB_TYPE.PROJECT, currentPage);
}

function <portlet:namespace/>loadDatas(currentPage){
  <portlet:namespace/>loadTab(<portlet:namespace/>TAB_TYPE.DATA, currentPage);
}

function <portlet:namespace/>loadTab(tabType, currentPage){
  var tabId = <portlet:namespace/>getTabId(tabType);
  var tabSearchUrl = {};
  tabSearchUrl[<portlet:namespace/>TAB_TYPE.APP] = "${appSearchUrl}";
  tabSearchUrl[<portlet:namespace/>TAB_TYPE.CONTENT] = "${contentSearchUrl}";
  tabSearchUrl[<portlet:namespace/>TAB_TYPE.PROJECT] = "${projectSearchUrl}";
  tabSearchUrl[<portlet:namespace/>TAB_TYPE.DATA] = "${dataSearchUrl}";
  var postData = <portlet:namespace/>getPostData();
  var sortOrder = $("input[name='<portlet:namespace/>sort-order'][tab-type='" + tabType + "']").val();
  var sortField = $("select[name='<portlet:namespace/>sort-field'][tab-type='" + tabType + "']").val();
  if(currentPage){
    postData["<portlet:namespace/>currentPage"] = currentPage;
  }
  if(sortOrder){
    postData["<portlet:namespace/>sortOrder"] = sortOrder;
  }
  if(sortField){
    postData["<portlet:namespace/>sortField"] = sortField;
  }
  $("#" + tabId + " > .search-results").empty();
  $("#" + tabId + " > .search-results").load(tabSearchUrl[tabType], postData, 
    function(){
      if(!$("#" + tabId).hasClass("loaded")){
        $("#" + tabId).addClass("loaded");
      }
    });
}

function <portlet:namespace/>setRedirectUrlAndLocationHref(move, portletURL){
  var parameters = <portlet:namespace/>getPostData();
  parameters["<portlet:namespace/>currentUrl"] = <portlet:namespace/>redirectURL;
  synchronousAjaxHelper.post("${getEncodedUrlUrl}", parameters, function(result){
    move(result, portletURL);
  });
}

function <portlet:namespace/>moveScienceAppDetail(groupId, scienceAppId) {
  <portlet:namespace/>setRedirectUrlAndLocationHref(function(redirectURL){
    var thisPortletNamespace = "_edisonscienceAppstore_WAR_edisonappstore2016portlet_";
    var params = "&" + thisPortletNamespace + "solverId=" + scienceAppId;
  	    params += "&" + thisPortletNamespace + "groupId=" + groupId;
        params += "&" + thisPortletNamespace + "redirectName=" + "<liferay-ui:message key='edison-search-total'/>";
        params += "&" + thisPortletNamespace + "redirectURL=" + redirectURL;
    location.href = "<%=scienceAppDetailUrl%>" + params;
  });
}

function <portlet:namespace/>moveContentDetail(contentSeq, contentDiv) {
  AUI().use("liferay-portlet-url", function(a) {
	<portlet:namespace/>setRedirectUrlAndLocationHref(function(redirectURL){
		var thisPortletNamespace = "_edisoncontent_WAR_edisoncontent2016portlet_";
		var params = "&" + thisPortletNamespace + "contentDiv=" + contentDiv;
		params += "&" + thisPortletNamespace + "contentSeq=" + contentSeq;
		params += "&" + thisPortletNamespace + "redirectName=" + "<liferay-ui:message key='edison-search-total'/>";
        params += "&" + thisPortletNamespace + "redirectURL=" + redirectURL;
		location.href = "<%=contentDetailUrl%>" + params;
	});
  });
}

function <portlet:namespace/>moveSimulationProjectDetail(simulationProjectId) {
  <portlet:namespace/>setRedirectUrlAndLocationHref(function(redirectURL){
    var thisPortletNamespace = "_edisonsimulationproject_WAR_edisonsimulationproject2017portlet_";
    var params = "&" + thisPortletNamespace + "simulationProjectId=" + simulationProjectId;
        params += "&" + thisPortletNamespace + "redirectName=" + "<liferay-ui:message key='edison-search-total'/>";
        params += "&" + thisPortletNamespace + "redirectURL=" + redirectURL;
    location.href = "<%=projectDetailUrl%>" + params;
  });
}

function <portlet:namespace/>moveDataDetail(collectionId) {
  <portlet:namespace/>setRedirectUrlAndLocationHref(function(redirectURL){
    var thisPortletNamespace = "_datasearch_WAR_SDR_baseportlet_";
    var params = "&" + thisPortletNamespace + "collectionId=" + collectionId;
        params += "&" + thisPortletNamespace + "redirectName=" + "<liferay-ui:message key='edison-search-total'/>";
        params += "&" + thisPortletNamespace + "redirectURL=" + redirectURL;
    location.href = "<%=openDataDetailUrl%>" + params;
  });
}

function <portlet:namespace/>liferayScriptMove(redirectURL, portletURL){
  portletURL.setParameter("redirectName", "<liferay-ui:message key='edison-search-total'/>");
  portletURL.setParameter("redirectURL", redirectURL);
  window.location.href = portletURL.toString();
}

function <portlet:namespace/>fileDownload(manualId){
    location.href = "<%=downloadManualURL%>&<portlet:namespace/>manualId="+manualId;    
}

function <portlet:namespace/>moveWorkBench(targetScienceAppId) {
    var URL = "<%=workbenchURL%>";
    URL += "&_SimulationWorkbench_WAR_OSPWorkbenchportlet_scienceAppId="+targetScienceAppId;
    window.open(URL);
    //location.href= URL;
}

</script>
