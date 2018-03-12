<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp" %>
<%@ page import="com.liferay.portal.model.Layout" %>
<%@ page import="com.liferay.portal.service.LayoutLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.util.LocaleUtil" %>
<!-- ckeditor -->
<%@ page import="com.liferay.portal.kernel.util.StringBundler"%>
<%@ page import="com.liferay.portal.kernel.util.HttpUtil"%>
<%@ page import="com.liferay.portal.kernel.util.StringPool"%>
<%@ page import="com.liferay.portal.service.GroupLocalServiceUtil"%>
<%@ page import="com.liferay.portal.model.Group"%>
<!-- ------- -->

<head>
<liferay-portlet:renderURL plid="${simulationPlid}" portletName="_SIMULATION_WAR_edisonportlet_" portletMode="view" var="exeURL">
	<liferay-portlet:param name="p_p_state" value="normal"/>
</liferay-portlet:renderURL>

<liferay-portlet:resourceURL var="solverFavoriteURL" 		escapeXml="false" id="solverFavorite"  copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="edisonFileDownloadURL" escapeXml="false" id="edisonFileDownload" copyCurrentRenderParameters="false"/>
	
<liferay-portlet:actionURL copyCurrentRenderParameters="false" var="updateSovelInfoActionUrl" portletMode="view" >
	<portlet:param name="myAction" value="updateSolverInfoAction"/>
	<portlet:param name="descriptionId" value="${solver.descriptionId}"/>
	<portlet:param name="solverId" value="${solver.scienceAppId}"/>
	<portlet:param name="redirectName" value="${redirectName}"/>
	<portlet:param name="redirectURL" value="${redirectURL}"/>
</liferay-portlet:actionURL>

<liferay-portlet:renderURL var="renderViewURL">
	<portlet:param name="myaction" value="detailView" />
	<portlet:param name="edionCopyParam" value="true" />
</liferay-portlet:renderURL>
<liferay-portlet:resourceURL var="addFavoriteAppURL" id="addFavoriteApp" copyCurrentRenderParameters="false" />
<liferay-portlet:resourceURL var="deleteFavoriteAppURL" id="deleteFavoriteApp" copyCurrentRenderParameters="false" />
<liferay-portlet:resourceURL var="scienceAppCategoryURL" id="scienceAppCategory" copyCurrentRenderParameters="false" />


<liferay-portlet:renderURL var="workbenchURL" copyCurrentRenderParameters="false" plid="${workBenchPlid}" portletName="Workbench_WAR_OSPWorkbenchportlet">
	<liferay-portlet:param name="workbenchType" value="SIMULATION_WITH_APP"/>
	<liferay-portlet:param name="classId" value="${classId}"/>
	<liferay-portlet:param name="customId" value="${customId}"/>
	<liferay-portlet:param name="jobUuid" value="0"/>
	<liferay-portlet:param name="testYn" value="false"/>
</liferay-portlet:renderURL>


<c:set var="actionUrl" value="<%=updateSovelInfoActionUrl%>"/>
<%
	Locale[] locales = LanguageUtil.getAvailableLocales();
	String localesStr = "";
	
	/* QNA 이동시 P_P_ID 확인 */
	HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(renderRequest);
	httpRequest = PortalUtil.getOriginalServletRequest(httpRequest);
	String p_p_id = CustomUtil.strNull(httpRequest.getParameter("p_p_id"), "");
	/* QNA 이동시 P_P_ID 확인 */
	
%>
<!-- ckeditor -->
 <%
 		Map<String, String> fileBrowserParamsMap = (Map<String, String>)request.getAttribute("liferay-ui:input-editor:fileBrowserParams");
 		
 		String fileBrowserParams = marshallParams(fileBrowserParamsMap);
		
 		StringBundler sb = new StringBundler(8);
		String portletId = portletDisplay.getRootPortletId();
		String mainPath = themeDisplay.getPathMain();

		String doAsUserId = themeDisplay.getDoAsUserId();
		long doAsGroupId = themeDisplay.getDoAsGroupId();
 		Locale siteLocale = themeDisplay.getLocale();
		String doasLocale = siteLocale.getLanguage();

		if (doAsGroupId == 0) {
			doAsGroupId = (Long)themeDisplay.getSiteGroupId();
		}
		
		Group group = GroupLocalServiceUtil.getGroup(doAsGroupId);
		String currentFolder = "/"+doAsGroupId+" - "+"edison"+"/";
		
		sb.append(mainPath);
		sb.append("/portal/fckeditor?p_p_id=");
		sb.append(HttpUtil.encodeURL(portletId));
		sb.append("&doAsUserId=");
		sb.append(HttpUtil.encodeURL(doAsUserId));
		sb.append("&doAsGroupId=");
		sb.append(HttpUtil.encodeURL(String.valueOf(doAsGroupId)));
		sb.append(fileBrowserParams);

		String connectorURL = HttpUtil.encodeURL(sb.toString());
%>
<!-- ------------- -->


<style type="text/css">
.yellowbtn{padding:5px 20px 8px 20px; background:#ffc75d;  border-radius:3px; -webkit-border-radius:3px; border:solid 1px #ccc; color:#704c1d; font-weight:600; font-size:15px;}
.yellowbtn a:hover{ color:#000;}

.favorites {
	cursor:pointer;
	display:none;
	
}

.appcell01 {
	text-align:center;
	font-size: 13px;
	font-weight: 600;
	color: #0e445a;
	background-color: #c7c7c7;
	padding: 9px;
	border-bottom: solid 1px #c0c0c0;
	max-width: 100px;
}

.appcell02 {
	font-size: 13px;
	font-weight: 600;
	color: #0e445a;
	background-color: #ddd;
	max-width: 100px;
	padding: 9px;
	border-bottom: solid 1px #c0c0c0;
}

.rolling-image {width: 1220px;margin: 0 auto;}
.rolling-image>.rolling-button { float: left; padding-left: 5px; margin-top:32px; margin-right:21px;}

.infoBtnbox input{float: right; margin-left: 3px; }

.wbba {word-break: break-all;}

</style>

<link href="${contextPath}/css/owl-carousel/owl.carousel.css" rel="stylesheet" type="text/css" />
<link href="${contextPath}/css/owl-carousel/owl.theme.css" rel="stylesheet" type="text/css" />
<link href="${contextPath}/css/fancybox/jquery.fancybox.css" rel="stylesheet" type="text/css" />
<link href="${contextPath}/css/commu.css" rel="stylesheet" type="text/css" />

</head>
<body>
	<c:if test="${isMainAppSearch eq true }">
		<div class="subvisualwrap"> 
			<div class="subnaviwrap"> 
				<div class="subnaviwrap">  </div> 
			</div> 
		</div>
	</c:if>

	<form name="exeform" method="post" action="<%=exeURL%>" style="margin:0px;">
		<input name="<portlet:namespace />id"    type="hidden"/>
	</form>	
	
	<form method="post" name="formFavorite" id="formFavorite" style="margin:0px;">
		<!-- 검색어 저장 Start -->
		<input name="<portlet:namespace/>solverId"	 id="<portlet:namespace/>solverId"			type="hidden" value="${params.solverId}"/>
		<input name="<portlet:namespace/>groupId"	 id="<portlet:namespace/>groupId"			type="hidden" value="${params.groupId}"/>
		<input name="<portlet:namespace/>userId"	 id="<portlet:namespace/>userId"			type="hidden" value="${params.userId}"/>
		<!-- 검색어 저장 End -->
	</form>
	
	<div class="topvisual">
		<div class="visualimg">
			<img
				src="/documents/${solver.iconRepositoryId}/${solver.iconId}/${solver.iconTitle}/${solver.iconUuid}?imageThumbnail=2"
				width="104px" style="height: 78px !important;"
				onerror="this.src='${contextPath}/images/comm_proj/noimage.png'">
		</div> 
		<div class="visualtxt" id="scienceAppTitle"></div>
		<div class="visualtxt2">
			<c:choose>
				<c:when test="${empty redirectName }">
					${solver.name}
				</c:when>
				<c:otherwise>
					<a onClick="<portlet:namespace/>historyBack();"
						style="cursor: pointer;"> ${redirectName} </a>  > ${solver.name}
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<!-- 좌측	 -->
	<div class="commleft panel edison-panel" style="padding: 0px 18px 0px 0px;">
		<form id="<portlet:namespace/>solverInfoForm" name="<portlet:namespace/>solverInfoForm" method="POST"  action="${actionUrl}" onsubmit="return <portlet:namespace/>solverInfoFormCheck()">
			<input type="hidden" id="<portlet:namespace/>selectLocaleId" name="<portlet:namespace/>selectLocaleId" value="${solver.selectLocaleId}"/>
			<div class="panel-heading clearfix" style="border-bottom: 0px;">
				<h3 class="panel-title pull-left">
					<img src="${contextPath}/images/title_virtual.png" width="20" height="20" />
					<liferay-ui:message key='edison-science-appstore-view-tab-detail-view' />
				</h3>
				
				<div class="btn-group pull-right">
					<c:if test="${contentCheckAuth eq 'TRUE' }">
						<input type="button" id="tabs-1" class="btn btn-default" value="<liferay-ui:message key='edison-button-board-modify' />" onClick="<portlet:namespace/>detailInfoModify(); return false;"/>
					</c:if>
						<input type="button" id="tabs-1" class="btn btn-default" 
							value="<liferay-ui:message key='edison-science-appstore-view-tab-sw-statistics' />" onClick="<portlet:namespace/>moveScienceAppExecStatistice('${solver.name}','${solverGroupId}');"/>	
				</div>
			</div>
				
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<c:if test="${contentCheckAuth eq 'FALSE' }">
					<tr>
					<td width="100%">
						<%
							for(Locale aLocale : locales){
								String languageId = LocaleUtil.toLanguageId(aLocale);
								String descriptionKey = "description_"+languageId;
						%>	
							<c:set var="descriptionKey" value="<%=descriptionKey%>"></c:set>
							<div id="<portlet:namespace/>descriptionDiv_<%=languageId%>">
								${solver.description[descriptionKey] }
							</div>
						<%
							}
						%>
					</td>	
					</tr>
				</c:if>
				<c:if test="${contentCheckAuth eq 'TRUE' }">
					<tr>
						<td width="100%">
							<aui:select name="serviceLocaleId" label="" onChange="changeLocale(this.value)">
								<%
								for(Locale aLocale : locales){
									String languageId = LocaleUtil.toLanguageId(aLocale);
									if(localesStr.equals("")){
										localesStr += languageId;
									}else{
										localesStr += ","+languageId;
									}
									
									String languageNm =aLocale.getDisplayName(themeDisplay.getLocale());
								%>
									<c:set var="langId" value="<%=languageId%>"></c:set>
									<aui:option label="<%=languageNm%>" value="<%=languageId%>" selected="${solver.selectLocaleId eq langId ? true : false}"/>
								<%} %>
							</aui:select>
							<br>
						</td>
					</tr>
					<tr>
						<td width="100%">
						<%
							for(Locale aLocale : locales){
								String languageId = LocaleUtil.toLanguageId(aLocale);
								String descriptionKey = "description_"+languageId;
						%>	
							<c:set var="descriptionKey" value="<%=descriptionKey%>"></c:set>
							<div id="<portlet:namespace/>descriptionDiv_<%=languageId%>">
								<textarea id="<portlet:namespace/>description_<%=languageId%>" name="<portlet:namespace/>description_<%=languageId%>" style="width:100%;height:300px;">${solver.description[descriptionKey] }</textarea>
							</div>
						<%
							}
						%>	
						</td>
					</tr>
				</c:if>
			</table>
			
			<c:if test="${!empty solver.openLevel && !empty solver.srcFileId}">
				<div class="h4" style="float: left;">	
					<c:if test="${solver.openLevel eq 'OPEN_SOURCE'}">
						<liferay-ui:message key='edison-science-appstore-view-source-code-download' />
					</c:if>
					<c:if test="${solver.openLevel eq 'OPEN_RUN_ONLY'}">
						<liferay-ui:message key='edison-science-appstore-view-execute-file-download' />
					</c:if>
					<c:if test="${!empty solver.srcFileId }">
						<table width="100%" border="0" cellpadding="0" cellspacing="0" style="margin-top: 10px;">
							<tr>
								<td>
									<span style="cursor:pointer; margin-right:5px;" onclick="<portlet:namespace/>fileDownload('${solver.srcFileId }')" class="onMouseHover">
										<img src="${contextPath}/images/fileicon2.png" width="16" height="16" />&nbsp;${solver.srcFileTitle }
									</span>
								</td>
							</tr>	
						</table>
					</c:if>
				</div>	
			</c:if>	
			
		</form>
		<!--추천 프로젝트-->
		<div>
			<liferay-portlet:runtime
				portletName="edisonscienceappprolink_WAR_edisonappstore2016portlet"
				defaultPreferences="" 
				queryString="&entryId=${scienceAppEntryId}&redirectName=${redirectName}&redirectURL=${redirectURL}&isMgrBtn=${contentCheckAuth eq 'TRUE' ? 'true' : 'false' }"/>
		</div>
		<!--관련자료 -->
		<div>
			<liferay-portlet:runtime portletName="edisonrelateasset_WAR_edisondefault2016portlet" defaultPreferences="" queryString="&entryId=${scienceAppEntryId}&isMgrBtn=${contentCheckAuth eq 'TRUE' ? 'true' : 'false' }&isVirTitle=true&isAppstore=true"/>
		</div>
		<!--Q&A 게시판 -->
		<div>
		</div>
	</div>
	
	<div class="commrighttop" style="border-radius: 0; width: 210px;">
		<div class="commtopbox" style="border-radius: 0; border: none;">
			<img src="${ownerInfo.portraitURL}" style="width:65px !important; height:65px !important;">
		</div>
		<div class="commtopboxtxt" style="top: 7px; line-height: 1.2em;">
			<div>${ownerInfo.firstName}</div>
			<div>
				<span>${ownerInfo.screenName}</span>
			</div>
			<div class="ellipsis">
				<span>${ownerInfo.universityFieldNm}</span>
			</div>
		</div>
	</div>
	<!-- 우측	 -->
	<div class="commright">
		<div class="commrighttxt">
			<ul>
				<li class="stxt2">
				<% if(isLogin) {%>
					<div id="favorites_off" class="favorites" style="display:none;" onclick="<portlet:namespace/>addFavoriteApp(${solver.scienceAppId}, ${params.solverGroupId}); return false;">
						<img src="${contextPath}/images/scienceappstorelist/favoriteicon.png" width="20" height="18" /> <liferay-ui:message key='edison-appstore-bookmark' />
					</div>
					<div id="favorites_on" class="favorites" style="display:none;" onclick="<portlet:namespace/>deleteFavoriteApp(${solver.scienceAppId}, ${params.solverGroupId}); return false;">
						<img src="${contextPath}/images/scienceappstorelist/favoriteiconon.png" width="20" height="18" /> <liferay-ui:message key='edison-appstore-bookmark' />
					</div>
				<% } %>
				</li>
				<li class="stxt2 wbba"><liferay-ui:message key='edison-virtuallab-version' /> : ${solver.version }</li>
				<li class="stxt2 wbba"><liferay-ui:message key='edison-table-list-header-orgNm' /> : ${solver.affiliation }</li>
				<li class="stxt2 wbba"><liferay-ui:message key='edison-table-list-header-date' /> : ${solver.statusDate }</li>
				<li class="stxt2 wbba">
					<ul>
						<li class="stxt2 wbba"><liferay-ui:message key='developer' /></li>
						<li class="stxt2 wbba" style="padding-bottom: 0px;">
							<c:forEach var="developer" items="${solver.developers }" varStatus="status">
								<c:if test="${not status.last}"><p style="margin: 0 0 12px 22px;">${developer}</p></c:if>
								<c:if test="${status.last}"><p style="margin: 0 0 0 22px;">${developer}</p></c:if>
							</c:forEach>
						</li>
					</ul>
				</li>
				<li class="stxt2 last" style="text-align:center;">
					<c:if test="${!empty solver.current_manualId}">
						<img src="${contextPath}/images/scienceappstorelist/btn_manual.jpg" width="75" height="30" style="cursor:pointer;" onClick="<portlet:namespace/>fileDownload('${solver.current_manualId}')" />
					</c:if>
					<c:if test="${empty solver.current_manualId}">
						<img src="${contextPath}/images/btn_manual_none.jpg" width="75" height="30" />
					</c:if>
					
					<c:if test="${solver.appType eq 'Solver' and workBenchPlid ne 0 and isSignedIn and solver.openLevel ne downloadOnly}">
						<img src="${contextPath}/images/scienceappstorelist/btn_run.jpg" width="75" height="30" style="cursor:pointer;" onClick="<portlet:namespace/>goWorkbench('${params.solverId}');"/>
					</c:if>
				</li>
			</ul>
			<ul>
				<li><liferay-ui:message
						key='edison-science-appstore-view-tab-category' /></li>
				<c:if test="${childrenCategoryList ne null}">
					<c:forEach var="childrenCategory"
						items="${childrenCategoryList}" varStatus="status">
						<c:if test="${not status.last}">
							<li class="stxt2 wbba">${childrenCategory.name}</li>
						</c:if>
						<c:if test="${status.last}">
							<li class="stxt2 wbba last">${childrenCategory.name}</li>
						</c:if>
					</c:forEach>
				</c:if>
				<c:if test="${childrenCategoryList eq null}">
					<li class="stxt2 wbba last"></li>
				</c:if>
			</ul>
		</div>
		
		<!--관련자료 통계-->
		<div>
			<liferay-portlet:runtime
				portletName="edisonrelateassetstatistic_WAR_edisonsimulationproject2017portlet"
				defaultPreferences=""
				queryString="&modelId=${scienceAppEntryId}" />
		</div>
		<c:if test="${fn:length(historyAppList) > 0}">
			<div class="commrighttxt">
				<ul>
					<li>
						History
					</li>
				</ul>
			</div>			
			<div class="commrighttxt">
				<c:forEach var="historyApp" items="${historyAppList}" varStatus="status">
					<ul>
						<li class="stxt2 wbba"><liferay-ui:message key="edison-virtuallab-app-name"/> : ${solver.name}</li>
						<li class="stxt2 wbba"><liferay-ui:message key='edison-virtuallab-version' /> : ${historyApp.version }</li>
						<li class="stxt2 wbba"><liferay-ui:message key='edison-table-list-header-date' /> : ${historyApp.statusDate }</li>
						<li class="stxt2 wbba <c:if test="${not status.last}">last</c:if>" style="text-align:center;">
							<c:if test="${!empty historyApp.current_manualId}">
								<img src="${contextPath}/images/scienceappstorelist/btn_manual.jpg" width="75" height="30" style="cursor:pointer;" onClick="<portlet:namespace/>fileDownload('${historyApp.current_manualId}')" />
							</c:if>
							<c:if test="${empty historyApp.current_manualId}">
								<img src="${contextPath}/images/btn_manual_none.jpg" width="75" height="30" />
							</c:if>
							<c:if test="${historyApp.appType eq 'Solver' and workBenchPlid ne 0 and isSignedIn}">
								<img src="${contextPath}/images/scienceappstorelist/btn_run.jpg" width="75" height="30" style="cursor:pointer;" onClick="<portlet:namespace/>moveWorkBench('${historyApp.scienceAppId}');"/>
							</c:if>
							<c:if test="${historyApp.appType eq 'Editor' and isSignedIn}">							
								<img src="${contextPath}/images/scienceappstorelist/btn_run.jpg" width="75" height="30" style="cursor:pointer;" onClick="<portlet:namespace/>runEditor('${historyApp.scienceAppId}', ${historyApp.exeFileNm});"/>
							</c:if>
							<c:if test="${historyApp.appType eq 'Analyzer' and isSignedIn}">
							${historyApp}
								<img src="${contextPath}/images/scienceappstorelist/btn_run.jpg" width="75" height="30" style="cursor:pointer;" onClick="<portlet:namespace/>runAnalizer(('${historyApp.scienceAppId}');"/>
							</c:if>
						</li>
					</ul>
				</c:forEach>
			</div>
		</c:if>
	</div>
	<c:if test="${isMainAppSearch eq true }">	
	</div>
	</c:if>

	<div id="<portlet:namespace/>show-analyzer-dialog">
		<div id="<portlet:namespace/>show-analyzer-dialog-content"></div>
	</div>

</body>
<style>
	tab_content{
		display:none;
	}
</style>

<!-- ckeditor  -->
<%!
public String marshallParams(Map<String, String> params) {
	StringBundler sb = new StringBundler();

	if (params != null) {
		for (Map.Entry<String, String> configParam : params.entrySet()) {
				sb.append(StringPool.AMPERSAND);
				sb.append(configParam.getKey());
				sb.append(StringPool.EQUAL);
				sb.append(HttpUtil.encodeURL(configParam.getValue()));
		}
	}

	return sb.toString();
}
%>
<!-- -------------  -->
<script type="text/javascript" src="${contextPath}/js/highcharts.js"></script>
<script src="${contextPath}/js/owl-carousel/owl.carousel.min.js"></script>
<script src="${contextPath}/js/fancybox/jquery.fancybox.pack.js"></script>
<script type="text/javascript" src="${contextPath}/editor/ckeditor/ckeditor.js" charset="utf-8"></script>

<script>
var chart1;
// var solverId = "${params.solverId}";

$(document).ready(function(){
	$("#scienceAppTitle").append(cutStr("${solver.currentTitle }", 80));
	
	var fa = "${favorite}";
	
	if(fa == 0){
		$("#favorites_off").show();
		$("#favorites_off").css("display", "inline");
	}else{
		$("#favorites_on").show();
		$("#favorites_on").css("display", "inline");;
	}

	setCKeditor();
	
	var viewStatus = "${viewStatus}";
	
	if(viewStatus == "shortcuts"){
		$(".edison #wrap .content").css("width", "1220px").css("margin", "auto");
	}
	
	$("#<portlet:namespace/>show-analyzer-dialog").dialog({
		autoOpen: false,
		width: 1000,
		height: 'auto',
		modal: true,
		resizable: true,
		show: {effect:'fade', speed: 800}, 
		hide: {effect:'fade', speed: 800},
		open: function(event, ui) {
	    },
	    close: function() {
// 	    	$("body").css('overflow','');
	    }
	})

	
});
    
$(document).on({
	mouseenter: function(){
		$(this).attr("src","${contextPath}/images/scienceappstoreview/<%=themeDisplay.getLanguageId()%>/btn_original_over.png");
	},
	mouseleave: function(){
		$(this).attr("src","${contextPath}/images/scienceappstoreview/<%=themeDisplay.getLanguageId()%>/btn_original.png");
	}
}, "#linkImg");

//ReferenceLink popup
$(document).on( "click", "#linkImg", function(){
	var url = spaceDelete($(this).attr("data-url"));
	window.open(url,"_blank");
});

function <portlet:namespace/>fileDownload(p_fileEntryId){
	location.href = "<%=edisonFileDownloadURL%>&<portlet:namespace/>fileEntryId="+p_fileEntryId;	
}

function <portlet:namespace/>submitForm(){
    if(
    	document.<portlet:namespace/>solverInfoForm.onsubmit &&
    	!document.<portlet:namespace/>solverInfoForm.onsubmit()
    ){
        return false;
    }
 	document.<portlet:namespace/>solverInfoForm.submit();
}

function <portlet:namespace/>detailInfoModify(){
	var formFavorite = document.formFavorite;
	var solverInfoForm = document.<portlet:namespace/>solverInfoForm;
	
	var solverId = formFavorite.<portlet:namespace/>solverId.value;
	var groupId = formFavorite.<portlet:namespace/>groupId.value;
		
	$('<input />').attr('type', 'hidden').attr('name', "<portlet:namespace/>groupId").attr('value', groupId).appendTo('#<portlet:namespace/>solverInfoForm');
	
	<portlet:namespace/>submitForm();
}

function <portlet:namespace/>solverInfoFormCheck(){
	   // 에디터의 내용이 textarea에 적용된다.
	   
	<%
		for(Locale aLocale : locales){
			String languageId = LocaleUtil.toLanguageId(aLocale);
	%>	
			var languageId = "<%=languageId%>";   
			var contentValue = CKEDITOR.instances["<portlet:namespace/>description_"+languageId].getData();
			var content = $.trim(contentValue.replace(/&nbsp;/g, ''));
			$("#<portlet:namespace/>description_"+languageId).val(content);
	<%	
		}
	%>
	return true;
}

function setCKeditor(){  
	var fileBrowserConectorURL = "<%=connectorURL%>";
	fileBrowserConectorURL = fileBrowserConectorURL +"&currentFolder=${currentFolder}";
	var ckEditorLanguage = "<%=doasLocale%>";
	CKEDITOR.config.autoParagraph = false;
	CKEDITOR.config.tabSpaces = 0;
	if("${contentCheckAuth}" == 'TRUE'){
		<%
			for(Locale aLocale : locales){
				String languageId = LocaleUtil.toLanguageId(aLocale);
		%>	
			var languageId = "<%=languageId%>";
			CKEDITOR.replace( "<portlet:namespace/>description_"+languageId , {
				filebrowserImageBrowseUrl: "/edison-appstore-2016-portlet/editor/ckeditor/filemanger/browser.html?Connector="+fileBrowserConectorURL,
				language: ckEditorLanguage,
			    filebrowserUploadUrl: null,
			    toolbar : [
			        		['Styles', 'FontSize', '-', 'TextColor', 'BGColor'],
			         		['Bold', 'Italic', 'Underline', 'Strike'],
			         		['Subscript', 'Superscript'],
			         		'/',
			         		['Undo', 'Redo', '-', 'Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord', '-', 'SelectAll', 'RemoveFormat'],
			         		['Find', 'Replace', 'SpellChecker', 'Scayt'],
			         		['Outdent','Indent','Blockquote'],
			         		['JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock'],
			         		'/',
			         		['Source'],
			         		['Link', 'Unlink', 'Anchor'],
			         		['Image', 'Flash',  'Table', '-', 'Smiley', 'SpecialChar', 'LiferayPageBreak'],
			         		['A11YBtn']
			         	]
			});
			
			if(languageId != "${solver.selectLocaleId}" ){
				$("#<portlet:namespace/>descriptionDiv_"+languageId).hide();
			}
		<%	
			}
		%>
	}else{
		<%
		for(Locale aLocale : locales){
			String languageId = LocaleUtil.toLanguageId(aLocale);
		%>	
			var languageId = "<%=languageId%>";
			if(languageId != "${solver.selectLocaleId}" ){
				$("#<portlet:namespace/>descriptionDiv_"+languageId).hide();
			}
		<%	
			}
		%>
	}
}
function changeLocale(selectLocaleId){
	<%
	for(Locale aLocale : locales){
		String languageId = LocaleUtil.toLanguageId(aLocale);
	%>	
		var languageId = "<%=languageId%>";
		
		if(languageId != selectLocaleId ){
			$("#<portlet:namespace/>descriptionDiv_"+languageId).hide();
		}else{
			$("#<portlet:namespace/>descriptionDiv_"+languageId).show();
		}
	<%	
		}
	%>
	var solverInfoForm = document.<portlet:namespace/>solverInfoForm;
	solverInfoForm.<portlet:namespace/>selectLocaleId.value = selectLocaleId;
}

function <portlet:namespace/>addFavoriteApp(solverId,groupId) {
	var dataForm = {
		"<portlet:namespace/>solverId" : solverId,
		"<portlet:namespace/>groupId" : groupId
	};
	
	jQuery.ajax({
		type: "POST",
		url: "<%=addFavoriteAppURL%>",
		async : false,
		data : dataForm,
		success: function(msg) {
			var result = msg.result;
			if(result == '200') {
				$("#favorites_off").hide();
				$("#favorites_on").show();
				$("#favorites_on").css("display", "inline");
			}else{
				alert("<liferay-ui:message key='edison-data-insert-error' />");
			}
		},error:function(msg,e){ 
			alert(e);
			return false;
		}
	});
}

function <portlet:namespace/>deleteFavoriteApp(solverId,groupId) {
	if(confirm("<liferay-ui:message key='edison-appstore-favorite-app-delete-alert' />")){	
		var dataForm = {
			"<portlet:namespace/>solverId" : solverId,
			"<portlet:namespace/>groupId" : groupId
		};
		
		jQuery.ajax({
			type: "POST",
			url: "<%=deleteFavoriteAppURL%>",
			async : false,
			data : dataForm,
			success: function(msg) {
				var result = msg.result;
				if(result == '200') {
					$("#favorites_off").show();
					$("#favorites_on").hide();
					$("#favorites_off").css("display", "inline");
				}
			},error:function(msg,e){ 
				alert(e);
				return false;
			}
		});

	}
}
function <portlet:namespace/>historyBack(){
	location.href = "${redirectOrignURL}";
}


function <portlet:namespace/>goWorkbench(targetScienceAppId){
	AUI().use("liferay-portlet-url", function(a) {
		var portletURL = Liferay.PortletURL.createRenderURL();
		portletURL.setPortletMode("view");
		portletURL.setWindowState("<%=LiferayWindowState.NORMAL.toString()%>");
		portletURL.setPlid("${workBenchPlid}");
		portletURL.setPortletId("SimulationWorkbench_WAR_OSPWorkbenchportlet");
		portletURL.setParameter("workbenchType", "SIMULATION_WITH_APP");
		portletURL.setParameter("scienceAppId", targetScienceAppId);
		
		portletURL.setParameter("redirectName", "My Project");
		portletURL.setParameter("redirectURL", "${redirectURL}");
		window.location.href = portletURL;
	});
}

</script>

<aui:script>
function <portlet:namespace/>moveWorkBench(targetScienceAppId) {
	var URL = "<%=workbenchURL%>";
	URL += "&_Workbench_WAR_OSPWorkbenchportlet_scienceAppId="+targetScienceAppId;
	
 	location.href= URL;
    
}

function <portlet:namespace/>moveScienceAppExecStatistice(solverName, groupId) {
	AUI().use("liferay-portlet-url", function(a) {
		var portletURL = Liferay.PortletURL.createRenderURL();
		portletURL.setPlid("${scienceAppExecPlid}");  
		portletURL.setPortletId("edisonstatisticsappexec_WAR_edisonsimulationportlet");
		portletURL.setParameter("scienceAppName", solverName);
		portletURL.setParameter("appExecGroupId", groupId);
		window.open(portletURL.toString(), '_blank');
	});
}

function <portlet:namespace/>runAnalizer() {
	alert(Liferay.Language.get("edison-science-appsotre-view-develop-not-yet-exception-alert"));
	/* AUI().use("liferay-portlet-url", function(a) {
		renderURL.setPortletId( 'Workbench_WAR_OSPWorkbenchportlet');
		renderURL.setWindowState('<%=LiferayWindowState.POP_UP.toString() %>');
	  	renderURL.setParameter("workbenchType", "SINGLERUN");
		renderURL.setParameter("portletId", portletId);
	  	
// 		renderURL.setParameter("pathType", "file");
// 		renderURL.setParameter("parentPath", filePath);
// 		renderURL.setParameter("fileName", fileName);
// 		renderURL.setParameter("relative", false);
// 		renderURL.setParameter("loadNow", true);
		
		$("#<portlet:namespace/>show-analyzer-dialog-content").load(renderURL.toString(),{}, function(){
		  $("#<portlet:namespace/>show-analyzer-dialog").dialog('open');
		 });
		
	}); */
}

function <portlet:namespace/>runEditor() {
	alert(Liferay.Language.get("edison-science-appsotre-view-develop-not-yet-exception-alert"));
}
</aui:script>