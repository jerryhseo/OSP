<%@page import="com.liferay.portal.service.PortletLocalServiceUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<portlet:defineObjects />

<liferay-portlet:resourceURL var="getEncodedUrlUrl" id="getEncodedUrl" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="appSearchUrl" id="appSearch"  copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="contentSearchUrl" id="contentSearch"  copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="projectSearchUrl" id="projectSearch"  copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="dataSearchUrl" id="dataSearch"  copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="downloadManualURL" id="downloadManual" copyCurrentRenderParameters="false"/>
<liferay-portlet:renderURL var="currentUrl" copyCurrentRenderParameters="false"/>

<liferay-portlet:renderURL var="workbenchURL" copyCurrentRenderParameters="false" plid="${workBenchPlid}" portletName="SimulationWorkbench_WAR_OSPWorkbenchportlet">
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

<div class="contabm">
  <ul id="<portlet:namespace/>search-tab-button">
    <li class="sel" onclick="<portlet:namespace/>toggleTab(this); return false;">
      <a id="total-tab-button" href="#total-tab"><liferay-ui:message key="edison-search-total"/>(${searchResults.appCount + searchResults.contentCount + searchResults.projectCount + searchResults.dataCount})</a>
    </li>
    <c:if test="${param.areaScienceApp}">
      <li onclick="<portlet:namespace/>toggleTab(this); return false;">
        <a id="app-tab-button" href="#app-tab"><liferay-ui:message key="edison-search-science-app"/>(${searchResults.appCount})</a>
      </li>
    </c:if>
    <c:if test="${param.areaContents}">
      <li onclick="<portlet:namespace/>toggleTab(this); return false;">
        <a id="content-tab-button" href="#content-tab"><liferay-ui:message key="edison-search-contents"/>(${searchResults.contentCount})</a>
      </li>
    </c:if>
    <c:if test="${param.areaSimulationProject}">
      <li onclick="<portlet:namespace/>toggleTab(this); return false;">
        <a id="project-tab-button" href="#project-tab"><liferay-ui:message key="edison-search-simulation-project"/>(${searchResults.projectCount})</a>
      </li>
    </c:if>
    <c:if test="${param.areaScienceData}">
      <li onclick="<portlet:namespace/>toggleTab(this); return false;">
        <a id="data-tab-button" href="#data-tab"><liferay-ui:message key="edison-search-science-data"/>(${searchResults.dataCount})</a>
      </li>
    </c:if>
  </ul>
</div>
<div id="<portlet:namespace/>total-search-tab" class="search-tab loaded conlist">
  <c:if test="${searchResults.appCount > 0}">
    <h3 class="styleh3"><liferay-ui:message key="edison-search-science-app"/>(${searchResults.appCount} <liferay-ui:message key="edison-search-cnt"/>)</h3>
    <div class="search-results">
      <ul>
        <c:forEach items="${searchResults.appResults}" var="element">
          <li>
            <a href="#appdetail" onclick="<portlet:namespace/>moveScienceAppDetail(${element.groupId}, ${element.scienceAppId}); return false;">${element.name}</a>
            <div style="float: right; line-height: 2.3em !important;">
                <c:if test="${!empty element.current_manualId and element.current_manualId ne 0}">
                    <img src="${contextPath}/images/search/btn_manual.jpg" style="height: 24px; cursor: pointer;" 
                        onClick="<portlet:namespace/>fileDownload('${element.current_manualId}')" />
                </c:if>
                <c:if test="${empty element.current_manualId or element.current_manualId eq 0}">
                    <img src="${contextPath}/images/search/btn_manual_none.jpg" style="height: 24px; cursor: default;"/>
                </c:if>
                <c:if test="${workBenchPlid ne 0 and isSignedIn and element.openLevel ne downloadOnly and element.appType eq 'Solver'}">
                    <img src="${contextPath}/images/search/btn_run.jpg"style="cursor:pointer; height: 24px;" 
                        onClick="<portlet:namespace/>moveWorkBench('${element.scienceAppId}');"/>
                </c:if>
            </div>
            <div>
              <c:if test="${!empty element.title}">
                ${element.title}<br/>version : ${element.version} / <liferay-ui:message key="edison-virtuallab-owner"/> : ${element.screenName}
              </c:if>
              <c:if test="${empty element.title}">
                            <liferay-ui:message key="edison-search-no-detail"/>
              </c:if>
            </div>
          </li>
        </c:forEach>
      </ul>
      <c:if test="${searchResults.appCount > 5}">
        <div class="more-button-wrapper">
          <a class="btn_s" href="#appMore" onclick="<portlet:namespace/>toggleTabByTabType('app'); return false;"><i class="icon-plus"></i>&nbsp;&nbsp;<liferay-ui:message key="edison-search-more"/></a>
        </div>
      </c:if>
    </div>
  </c:if>
  <c:if test="${searchResults.contentCount > 0}">
    <h3 class="styleh3"><liferay-ui:message key="edison-search-contents"/>(${searchResults.contentCount} <liferay-ui:message key="edison-search-cnt"/>)</h3>
    <div class="search-results">
      <ul>
        <c:forEach items="${searchResults.contentResults}" var="element">
          <li>
            <a href="#contentDetail" onclick="<portlet:namespace/>moveContentDetail(${element.contentSeq}, ${element.contentDiv}); return false;">${element.title}</a>
            <div>
              ${element.resume}
              <c:if test="${empty element.resume}">
                <liferay-ui:message key="edison-search-no-detail"/>
              </c:if>
            </div>
          </li>
        </c:forEach>
      </ul>
      <c:if test="${searchResults.contentCount > 5}">
        <div class="more-button-wrapper">
          <a class="btn_s" href="#contentMore" onclick="<portlet:namespace/>toggleTabByTabType('content'); return false;"><i class="icon-plus"></i>&nbsp;&nbsp;<liferay-ui:message key="edison-search-more"/></a>
        </div>
      </c:if>
    </div>
  </c:if>
  <c:if test="${searchResults.projectCount > 0}">
    <h3 class="styleh3"><liferay-ui:message key="edison-search-simulation-project"/>(${searchResults.projectCount} <liferay-ui:message key="edison-search-cnt"/>)</h3>
    <div class="search-results">
      <ul>
        <c:forEach items="${searchResults.projectResults}" var="element">
          <li>
            <a href="#simulationProjectDetail" onclick="<portlet:namespace/>moveSimulationProjectDetail(${element.simulationProjectId}); return false;">${element.title}</a>
            <div>
              ${element.explain}
              <c:if test="${empty element.explain}">
                <liferay-ui:message key="edison-search-no-detail"/>
              </c:if>
            </div>
          </li>
        </c:forEach>
      </ul>
      <c:if test="${searchResults.projectCount > 5}">
        <div class="more-button-wrapper">
          <a class="btn_s" href="#projectMore" onclick="<portlet:namespace/>toggleTabByTabType('project'); return false;"><i class="icon-plus"></i>&nbsp;&nbsp;<liferay-ui:message key="edison-search-more"/></a>
        </div>
      </c:if>
    </div>
  </c:if>
  <c:if test="${searchResults.dataCount > 0}">
    <h3 class="styleh3"><liferay-ui:message key="edison-search-science-data"/>(${searchResults.dataCount} <liferay-ui:message key="edison-search-cnt"/>)</h3>
    <div class="search-results">
      <ul>
        <c:forEach items="${searchResults.dataResults}" var="element">
          <li>
            <a href="#dataDetail" onclick="<portlet:namespace/>moveDataDetail(${element.vo.collectionId}); return false;">${element.vo.title}
            <c:if test="${!empty element.dsCnt && element.dsCnt ne 0}">(${element.dsCnt })</c:if></a>
            <div style="float: right; line-height: 2.3em !important;">
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
            </div>
            <div>
              <p class="description">${element.vo.description}</p>
              <fmt:formatDate value="${element.vo.createDate }" pattern="yyyy-MM-dd" /> |
              ${element.vo.doi } | 
              ${element.communityname }
              <c:choose>
                <c:when test="${!empty element.contributorNames[0] }">
                  | ${element.contributorNames[0] }
                  <c:if test="${fn:length(element.contributorNames) > 1 }">
                      and ${fn:length(element.contributorNames) - 1} others
                  </c:if>
                </c:when>
                <c:otherwise> | ${element.userName }</c:otherwise>
              </c:choose>
            </div>
          </li>
        </c:forEach>
      </ul>
      <c:if test="${searchResults.dataCount > 5}">
        <div class="more-button-wrapper">
          <a class="btn_s" href="#datatMore" onclick="<portlet:namespace/>toggleTabByTabType('data'); return false;"><i class="icon-plus"></i>&nbsp;&nbsp;<liferay-ui:message key="edison-search-more"/></a>
        </div>
      </c:if>
    </div>
  </c:if>
  <c:if test="${searchResults.appCount + searchResults.contentCount + searchResults.projectCount + searchResults.dataCount eq 0}">
    <div class="search-results">
      <ul>
        <li><span class="result-none"><liferay-ui:message key="edison-search-no-result"/></span></li>
      </ul>
    </div>
  </c:if>
</div>
<c:if test="${param.areaScienceApp}">
  <div id="<portlet:namespace/>app-search-tab" class="search-tab conlist" style="display: none;">
    <h3 class="styleh3"><liferay-ui:message key="edison-search-science-app"/>(${searchResults.appCount} <liferay-ui:message key="edison-search-cnt"/>)</h3>
    <div class="search-results"></div>
  </div>
</c:if>
<c:if test="${param.areaContents}">
  <div id="<portlet:namespace/>content-search-tab" class="search-tab conlist" style="display: none;">
    <h3 class="styleh3"><liferay-ui:message key="edison-search-contents"/>(${searchResults.contentCount} <liferay-ui:message key="edison-search-cnt"/>)</h3>
    <div class="search-results"></div>
  </div>
</c:if>
<c:if test="${param.areaSimulationProject}">
  <div id="<portlet:namespace/>project-search-tab" class="search-tab conlist" style="display: none;">
    <h3 class="styleh3"><liferay-ui:message key="edison-search-simulation-project"/>(${searchResults.projectCount} <liferay-ui:message key="edison-search-cnt"/>)</h3>
    <div class="search-results"></div>
  </div>
</c:if>
<c:if test="${param.areaScienceData}">
  <div id="<portlet:namespace/>data-search-tab" class="search-tab conlist" style="display: none;">
    <h3 class="styleh3"><liferay-ui:message key="edison-search-science-data"/>(${searchResults.dataCount} <liferay-ui:message key="edison-search-cnt"/>)</h3>
    <div class="search-results"></div>
  </div>
</c:if>
<script>

$(document).ready(function(){
  $("#<portlet:namespace/>search-tab-button a").click(function(e){
    e.preventDefault();
  });
});

var <portlet:namespace/>TAB_TYPE = {
  "APP": "app",  
  "CONTENT": "content",  
  "PROJECT": "project",  
  "DATA": "data"
};

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
  if(currentPage){
    postData["<portlet:namespace/>currentPage"] = currentPage;
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
