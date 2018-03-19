<%@page import="com.liferay.portal.kernel.util.LocaleUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<link type="text/css" rel="stylesheet" href="${contextPath}/css/style.css" media="screen"/>
<link type="text/css" rel="stylesheet" href="${contextPath}/css/commu.css" media="screen"/>

<%
	String languageId = (String)request.getAttribute("locale");
	String title = "title_"+languageId;
	String resume = "resume_"+languageId;
	
	Locale[] locales = LanguageUtil.getAvailableLocales();
	
%>

<liferay-portlet:renderURL var="contentManageViewURL" copyCurrentRenderParameters="false">
	<liferay-portlet:param name="myaction" value="contentManageView" />
	<liferay-portlet:param name="redirectName" value="${redirectName}" />
	<liferay-portlet:param name="redirectURL" value="${redirectURL}" />
	<liferay-portlet:param name="contentSeq" value="${content.contentSeq}" />
	<liferay-portlet:param name="contentDiv" value="${content.contentDiv}" />
	<liferay-portlet:param name="mode" value="<%=Constants.UPDATE %>" />
	
</liferay-portlet:renderURL>

<liferay-portlet:resourceURL var="contentfiledownloadURL" escapeXml="false" id="contentfiledownload" copyCurrentRenderParameters="false">
	<liferay-portlet:param name="contentSeq" value="${content.contentSeq}" />
</liferay-portlet:resourceURL>

<liferay-portlet:renderURL var="contentListURL" copyCurrentRenderParameters="<%=false%>" portletMode='view'></liferay-portlet:renderURL>

<style>
.contentResumeDiv{
	height: 200px;
	overflow-y: auto;
	padding: 5px;
}
.relatedAssetPortlet{
	margin-top : 5%;
}
.buttonGroup{
	text-align: right;
}

.detailViewSubTitle{
	padding-left: 0px !important;
}

</style>

<script>

$(function(){
	var viewStatus = "${viewStatus}";
	
	if(viewStatus == "shortcuts"){
		$(".edison #wrap .content").css("width", "1220px").css("margin", "auto");
	}
});


//일반콘텐츠 등록 Dialog open
function <portlet:namespace/>updateContent(mode){
// 	$("#<portlet:namespace/>mode").val(mode);
	
// 	<portlet:namespace/>contentDetailForm.submit();
	location.href = "<%=contentManageViewURL%>";
}

function <portlet:namespace/>contentFileDownload(contentSeq, contentDiv, languageId){
	var URL = "<%=contentfiledownloadURL%>";
	if(contentDiv == 2001002){
		URL += "&<portlet:namespace/>languageId="+languageId;
	}
	location.href = URL.toString();
	
	<%-- jQuery.ajax({
		type: "POST",
		url: "<%=contentfiledownloadURL%>",
		data: {
			"<portlet:namespace/>languageId" : languageId
		},
  		async : false,
		success: function(data) {
			
		}
	});  --%>
}

<%-- function  <portlet:namespace/>goContentList(){
	location.href = "<%=contentListURL%>";
} --%>

function <portlet:namespace/>historyBack(){
	location.href = "${redirectOrignURL}";
}

function <portlet:namespace/>openContentViewDialogWithData(contentFolderPath){
	AUI().use("liferay-portlet-url", function(a) {
		var inputData = new OSP.InputData();
		inputData.uri(contentFolderPath);
		inputData.type(OSP.Enumeration.PathType.URL);
		
		var renderURL = Liferay.PortletURL.createRenderURL();
		renderURL.setPortletId( 'HtmlViewer_WAR_OSPAnalyzersportlet');
		renderURL.setWindowState('<%=LiferayWindowState.POP_UP%>');
		renderURL.setParameter("inputData", JSON.stringify(inputData));
		renderURL.setParameter('eventEnable', false);

		window.open(renderURL.toString());
	});
} 
function <portlet:namespace/>closeDialog ( data ){
	//alert( JSON.stringify(data,null,4));
	<portlet:namespace/>dialogSection.dialog('close');
}
</script>

<aui:form action="<%=contentManageViewURL%>" name="contentDetailForm" method="get">
	
		<!--좌측-->
	
		<div class="topvisual">
			<div class="visualimg">
				<c:choose>
					<c:when test="${fn:length(fileList) > 0}">
						<c:forEach var="file" items="${fileList }">
							<img src="/documents/${file.fileRepositoryId }/${file.fileEntryId }/${file.fileTitle }/${file.fileUuid }?imageThumbnail=2" style="width:104px;height:78px;" onerror="this.src='${contextPath }/images/noimage.png'">
						</c:forEach> 
					</c:when>
					<c:otherwise>
						<img src="${contextPath }/images/noimage.png">
					</c:otherwise>
				</c:choose>
			</div>
			<c:set value="<%=title%>" var="title"/>
			<div class="visualtxt">${content[title]}</div>
			<div class="visualtxt2">
				<c:if test="${redirectURL ne ''}"> 
					<span><a onClick="<portlet:namespace/>historyBack()" style="cursor: pointer;"> ${redirectName } </a>  > </span> Content
				</c:if>
			</div>
		</div>
		<c:if test="${isOwner == true || isManager == true}"> 
			<div class="topvisualbtnbox" style="padding-right: 10px;">&nbsp;<input type="button" value="<liferay-ui:message key='edison-virtuallab-scienceapp-management'/>" class="btn btn-default topbtn" onclick="<portlet:namespace/>updateContent('<%=Constants.UPDATE%>');" style="width: 100px;" /></div>
		</c:if>
	
	
		<div class="commleft panel edison-panel">
		
			<!-- 파일 목록 -->
			<c:choose>
				<c:when test="${content.contentDiv eq 2001004 && content.advancedStartFileNm ne ''}">
					<div class="panel-heading clearfix detailViewSubTitle" style="border-bottom: 0px;">
						<h3 class="panel-title pull-left">
							<img src="${contextPath}/images/title_virtual.png" width="20" height="20" /> 
							<liferay-ui:message key="edison-content-file"/>
						</h3>
					</div>
					<div class="h10"></div>
					<c:if test="${content.advancedContentFolderPath != ''}">
						<div class="msbox">
							<ul>
								<li class="txtnum"><span style="cursor:pointer" onclick="<portlet:namespace/>openContentViewDialogWithData('${content.advancedContentFolderPath }/${content.advancedStartFileNm }')">${content.advancedStartFileNm }</span></li>
							</ul>
						</div>
					</c:if>
				</c:when>
				<c:otherwise>
					<c:if test="${content.contentFileNm ne '' && content.contentFileNm ne null}">
						<div>
							<div class="panel-heading clearfix detailViewSubTitle" style="border-bottom: 0px;">
								<h3 class="panel-title pull-left">
									<img src="${contextPath}/images/title_virtual.png" width="20" height="20" /> 
									<liferay-ui:message key="edison-content-file"/>
								</h3>
							</div>
							<div class="h10"></div>
				
				
							<div class="msbox">
								<ul>
									<c:choose>
										<c:when test="${content.contentDiv == 2001002}"><!-- 메뉴얼일떄 -->
											<%for(Locale aLocale : locales){
												String localeLanguageId = LocaleUtil.toLanguageId(aLocale);
												String languageNm =aLocale.getDisplayName(themeDisplay.getLocale());
												String manualId = "contentFileNm_manual_"+localeLanguageId;
											%>
												<c:set value="<%=manualId%>" var="manualId"/>
												<c:if test="${content[manualId] ne null}">
													<li class="txtTitle"><%=languageNm%></li>
													<span style="cursor:pointer" onclick="<portlet:namespace/>contentFileDownload('${content.contentSeq }', '${content.contentDiv}', '<%=localeLanguageId %>')">
													<li class="txtnum">	
														${content[manualId]}
														 &nbsp;&nbsp;<img src="<%=themeDisplay.getPathThemeImages() %>/custom/portlet/fileicon2.png" width="16" height="16" />
													</li>
													</span>
													<br/>
													<br/>
												</c:if>
											<%} %>
										</c:when>
										<c:otherwise><!-- 메뉴얼이 아닐때 -->
											<li class="txtnum"><span style="cursor:pointer" onclick="<portlet:namespace/>contentFileDownload('${content.contentSeq }', '${content.contentDiv}', '')">${content.contentFileNm }</span></li>
										</c:otherwise>
									</c:choose>
								</ul>
							</div>
						</div>
					</c:if>
				</c:otherwise>
			</c:choose>
			
			<c:if test="${isOwner == true || isManager == true}"> 
				<div class="panel-heading clearfix detailViewSubTitle">
					<h3 class="panel-title pull-left">
						<img src="${contextPath}/images/title_virtual.png" width="20" height="20" /> 
						<liferay-ui:message key="edison-content-manager"/>
					</h3>
				</div>
				
				<!-- 관리자 -->
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table1_list table table-bordered table-hover edison-table">
					<colgroup>
						<col width="30%" />
						<col width="30%" />
						<col width="30%" />
					</colgroup>
					
					<thead>
						<tr>
							<th align="center" scope="col"><liferay-ui:message key='edison-table-list-header-userid' /></th>
							<th align="center" scope="col"><liferay-ui:message key='edison-table-list-header-usernm' /></th>
							<th align="center" scope="col"><liferay-ui:message key='edison-table-list-header-email' /></th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${fn:length(contentManagerList) == 0 }">
								<tr>
									<td class="TC" colspan="3"><liferay-ui:message key="edison-there-are-no-data"/></td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach var="manager" items="${contentManagerList }">
									<tr>
										<td class="TC">${manager.userScreenName}</td>
										<td class="TC">${manager.userFullName}</td>
										<td class="TC">${manager.userEmailAddress}</td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</c:if>
			<div class="relatedAssetPortlet">
				<liferay-portlet:runtime portletName="edisonrelateasset_WAR_edisondefault2016portlet" 
				defaultPreferences="" 
				queryString="&entryId=${entryId}&isMgrBtn=false&isVirTitle=true&redirectURL=${redirectURL }&redirectName=${redirectName }"/>
			</div>
		</div>
		
		<div class="commrighttop" style = "border-radius: 0; width:210px;">
			<div class="commtopbox" style = "border-radius: 0; border:none;">
		        <img src="${ownerInfo.portraitURL}" style="width:65px !important; height:65px !important;">
		    </div>
		    <div class="commtopboxtxt">
		    	${ownerInfo.firstName}<br>
				<span>${ownerInfo.screenName}(${ownerInfo.universityFieldNm})</span><br>
			</div>	
		</div>
		
		<!--우측-->
		<div class="commright">
			<div class="commrighttxt">
				<ul>
					<li><liferay-ui:message key="edison-content-type" /></li>
					<li class="stxt">${content.contentDivNm }</li>
	
					<li><liferay-ui:message key="edison-simulation-execute-simulation-description"/></li>
					<li class="stxt">
						<c:set value="<%=resume%>" var="resume"/>
						<div style="word-wrap: break-word;max-height:200px; overflow-y: auto">
						
							<c:forEach var="resumeTxt" items="${content[resume]}" varStatus="status">
								<p style="margin: 0px">${resumeTxt}</p>
							</c:forEach>
						</div>
					</li>
					
					<li><liferay-ui:message key="edison-science-appstore-view-tab-category"/></li>
					<li class="stxt">
						<!-- 카테고리 -->
						<c:forEach var="category" items="${categoryNameList }">
							${category } <br/>
						</c:forEach>
					</li>
					
						<!--관련자료 -->
				</ul>
			</div>
			<div>
				<liferay-portlet:runtime portletName="edisonrelateassetstatistic_WAR_edisonsimulationproject2017portlet" defaultPreferences="" queryString="&modelId=${entryId}"/>		
			</div>	
		</div>
</aui:form>