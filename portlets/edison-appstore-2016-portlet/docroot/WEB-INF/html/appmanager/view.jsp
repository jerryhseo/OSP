<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayPortletMode"%>

<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@ page import="com.liferay.portal.kernel.util.HttpUtil"%>

<portlet:defineObjects />
<liferay-theme:defineObjects />

<c:set var="contextPath" value="<%=request.getContextPath() %>" scope="page" />
<link type="text/css" rel="stylesheet" href="${contextPath}/css/scienceappmanager.css" media="screen"/>

<%
	String clickTab = GetterUtil.get(request.getParameter("clickTab"), "m01");
	String jspFile = "";
	if(clickTab.equals("m01")){
		jspFile = "app_infomation";
	}else if(clickTab.equals("m02")){
		jspFile = "execute_infomation";
	}else if(clickTab.equals("m03")){
		jspFile = "port_infomation";
	}else if(clickTab.equals("m04")){
		jspFile = "app_layout";
	}else if(clickTab.equals("m05")){
		jspFile = "public_infomation";
	}
	
%>

<liferay-portlet:renderURL var="listURL" copyCurrentRenderParameters="<%=false%>" portletMode='view'>
	<portlet:param name="searchValue" value="${searchValue}"/>
	<portlet:param name="searchOption" value="${searchOption}"/>
	<portlet:param name="searchStatus" value="${searchStatus}"/>
	<portlet:param name="p_curPage" 	value="${p_curPage}" />
</liferay-portlet:renderURL>


<liferay-portlet:renderURL var="solverRenderURL" copyCurrentRenderParameters="<%=true%>" windowState="<%=LiferayWindowState.MAXIMIZED.toString()%>"/>


<liferay-portlet:resourceURL var="edisonFileDownloadURL" escapeXml="false" id="edisonFileDownload" copyCurrentRenderParameters="false"/>

<liferay-portlet:resourceURL var="deleteFileURL" escapeXml="false" id="deleteFile" copyCurrentRenderParameters="false">
	<portlet:param name="scienceAppId" value="${scienceAppId}"/>
</liferay-portlet:resourceURL>

<liferay-portlet:resourceURL var="updateAppStatusURL" escapeXml="false" id="updateAppStatus" copyCurrentRenderParameters="false">
	<portlet:param name="scienceAppId" value="${scienceAppId}"/>
</liferay-portlet:resourceURL>

<liferay-portlet:resourceURL var="copyScienceAppURL" escapeXml="false" id="copyScienceApp" copyCurrentRenderParameters="false">
	<portlet:param name="scienceAppId" value="${scienceAppId}"/>
	
	
</liferay-portlet:resourceURL>

<liferay-portlet:renderURL var="newAppRenderURL" copyCurrentRenderParameters="<%=false%>">	
	<portlet:param name="myRender" value="solverRender" />
	<portlet:param name="searchValue" value="${searchValue}"/>
	<portlet:param name="searchOption" value="${searchOption}"/>
	<portlet:param name="searchStatus" value="${searchStatus}"/>
	<portlet:param name="p_curPage" 	value="${p_curPage}" />
	
	<portlet:param name="redirectURL" 	value="${redirectURL}" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="appTestURL" plid="${workBenchPlid}" portletName="SimulationWorkbench_WAR_OSPWorkbenchportlet" windowState="<%=LiferayWindowState.POP_UP.toString()%>" portletMode="<%=LiferayPortletMode.VIEW.toString()%>">
	<liferay-portlet:param name="workbenchType" value="SIMULATION_WITH_APP"/>
	<liferay-portlet:param name="scienceAppId" value="${scienceAppId}"/>
	
	<portlet:param name="redirectURL" 	value="${redirectURL}"/>
	<portlet:param name="redirectName" 	value="MY EDISON" />
</liferay-portlet:renderURL>

<liferay-portlet:resourceURL var="getScienceAppInfoURL" escapeXml="false" id="getScienceAppInfo" copyCurrentRenderParameters="false"/>
	
<%
    String renderUrl = HttpUtil.removeParameter(solverRenderURL, renderResponse.getNamespace()+"clickTab");
%>
<style type="text/css">
	.no-dialog-padding .ui-dialog-content{
		padding : 0px;
	}
	
	.<portlet:namespace/>app-version{
		width: 50px !important;
		text-align: center;
	}
	
	
	.tooltips {
		position: relative;
		display: inline-block;
	}

	.tooltips .tooltiptext {
		visibility: hidden;
		background-color: #e7e7e7;
		text-align: center;
		border-radius: 10px;
		padding: 6px 10px;
		width: max-content;
		
		/* Position the tooltip */
		position: absolute;
		left: -290px;
		top: 35px;
		z-index: 1;
	}
	
	.tooltips:hover .tooltiptext {
		visibility: visible;
	}
</style>
<div class="container">
	<input type="hidden" id="<portlet:namespace/>currVersion" value="" />
	<div class="swleft">
		${tabStr}
	</div>
	<div class="swrightcont">
		<liferay-util:include page='<%= "/WEB-INF/html/appmanager/" + jspFile + ".jsp" %>' servletContext="<%=this.getServletContext() %>">
		</liferay-util:include>
	</div>
</div>

<link media="all" rel="stylesheet" href="${contextPath}/css/jquery-confirm.css" />
<script src="${contextPath}/js/jquery-confirm.js"></script>

<script type="text/javascript">
	
	$(document).ready(function(){
		<portlet:namespace/>getScienceAppVersion()
		$('[data-toggle="tooltip"]').tooltip();
	});
	
	function tabAction(tabValue){
		var searchParameter = "";
		searchParameter += "&<portlet:namespace/>clickTab="+tabValue;
		location.href="<%=renderUrl%>"+searchParameter;
	}
	function <portlet:namespace/>statusSubmit(status){
		var statusMsg = "";
		if(status=='1901001'){
			statusMsg = Liferay.Language.get('edison-appstore-status-write');
		}else if(status=='1901002'){
			statusMsg = Liferay.Language.get('edison-appstore-status-request');
		}else if(status=='1901003'){
			statusMsg = Liferay.Language.get('edison-appstore-status-private');
		}else if(status=='1901004'){
			statusMsg = Liferay.Language.get('edison-appstore-status-service');
		}
		
		var confirmMsg = Liferay.Language.get('edison-science-appstore-status-change-msg',[''+statusMsg+'']);
		
		if(confirm(confirmMsg)){
			var dataForm = {	
					"<portlet:namespace/>status"	: status
				};
			
			jQuery.ajax({
				type: "POST",
				url: "<%=updateAppStatusURL%>",
				async : false,
				data  : dataForm,
				success: function(result) {
					if(result=="SUCCESS"){
						location.reload();
					}
				},error:function(jqXHR, textStatus, errorThrown){
					if(jqXHR.responseText !== ''){
						alert(textStatus+": "+jqXHR.responseText);
					}else{
						alert(textStatus+": "+errorThrown);
					}  
				}
			});
		}else{
			return false;
		}
	}
	
	
	function <portlet:namespace/>fileDownload(p_fileEntryId){
		location.href = "<%=edisonFileDownloadURL%>&<portlet:namespace/>fileEntryId="+p_fileEntryId;	
	}
	
	function <portlet:namespace/>goList(){
		location.href = "${redirectOrignURL}";
	}
	
	function <portlet:namespace/>appTest(){
		window.location.href = "<%=appTestURL%>";
	}
	
	function <portlet:namespace/>deleteFile(p_fileEntryId,fileType,objectClass,language){
		if(!confirm(Liferay.Language.get('file-delete-confirm'))) return;
		var deleteForm = {
				"<portlet:namespace/>fileEntryId" : p_fileEntryId,
				"<portlet:namespace/>fileType" : fileType,
				"<portlet:namespace/>language" : language
				};
		
		jQuery.ajax({
			type: "POST",
 			url: "<%=deleteFileURL%>",
			data: deleteForm,
	  		async : false,
			success: function(data) {
				alert(Liferay.Language.get('edison-data-delete-success'));
				$("."+objectClass).remove();
				
				if(fileType=="portType"){
					var percentVal = '0%';
					$('.bar').width(percentVal);
					$('.percent').html(percentVal);
					$("#<portlet:namespace/>fileSave").show();
				}else if(fileType=="portSampleFile"){
					$("input:radio[name='CheckFile'][id='portSampleFile']").val('');
				}
			},error:function(data,e){ 
				alert("deleteFile System error!");	
			}
		});
	}
	
	function <portlet:namespace/>noUpdateDisabled(status){
		if(Number(status)>=1901003){
			$(".noupdate").prop('disabled', true);
			$(".noUpdateHidden").css('display', 'none');
		}
	}
	
	function <portlet:namespace/>copyScienceApp(){
		/* <liferay-ui:icon-help message="edison-science-appstore-port-type-message"/> */
		var confirmContent = '<div id="<portlet:namespace/>upgrageVersionBtn" style="width: 100%; margin: 10px 0px;" align="center">' + 
									 '<div class="pull-left" style="font-size:20px;">'+
										Liferay.Language.get('current-version') + ' : ${data.version}<br/><br/>' +
									 '</div>' + 
									 '<div class="pull-right">'+
										 '<div class="tooltips">' + 
											 '<button type="button" id="<portlet:namespace/>releaseUpgrade" class="btn btn-default"' + 
											 		' data-toggle="tooltip" data-placement="top" ' +
											 		' onclick="<portlet:namespace/>setNewUpgradeVersion(\'release\')" style="margin: 0px 5px;">' + 
												 'Release'+
											 '</button>' +
											 '<span class="tooltiptext"><i class="icon-exclamation-sign"></i>&nbsp;' + 
											 	Liferay.Language.get('edison-science-appstore-upgrade-version-description-release') + 
											 '</span>' +
										 '</div>' + 
										 '<div class="tooltips">' +
											 '<button type="button" id="<portlet:namespace/>majorUpgrade" class="btn btn-default"' +
											 		' data-toggle="tooltip" data-placement="top" ' +
											 		' onclick="<portlet:namespace/>setNewUpgradeVersion(\'major\')" style="margin: 0px 5px;">' + 
												 'Major'+
											 '</button>' +
											 '<span class="tooltiptext"><i class="icon-exclamation-sign"></i>&nbsp;' + 
											 	Liferay.Language.get('edison-science-appstore-upgrade-version-description-major') + 
											 '</span>' +
										 '</div>' + 
										 '<div class="tooltips">' +
											 '<button type="button" id="<portlet:namespace/>minorUpgrade" class="btn btn-default"' + 
											 		' data-toggle="tooltip" data-placement="top" ' +
											 		' onclick="<portlet:namespace/>setNewUpgradeVersion(\'minor\')" style="margin: 0px 5px;">' + 
												 'Minor'+
											 '</button>' +
											 '<span class="tooltiptext"><i class="icon-exclamation-sign"></i>&nbsp;' + 
											 	Liferay.Language.get('edison-science-appstore-upgrade-version-description-minor') + 
											 '</span>' +
										 '</div>' + 
									 '</div>' +
							 '</div>' +
							 '<div id="<portlet:namespace/>defaultVersionDescription" style="display:inline-block; margin:10px 30px 0px; line-height:25px;">' +
								 Liferay.Language.get('edison-science-appstore-upgrade-version-description-release') + '<br/>' + 
								 Liferay.Language.get('edison-science-appstore-upgrade-version-description-major') + '<br/>' + 
								 Liferay.Language.get('edison-science-appstore-upgrade-version-description-minor') + '<br/>' + 
								 '<span style="color:blue;"><i class="icon-info-sign"></i>&nbsp;</span>' + Liferay.Language.get('edison-science-appstore-upgrade-version-description-button') + '<br/>' +
							 '</div>' +
							 '<div class="h10" ></div>' +
							 '<div id="<portlet:namespace/>upgradeVersionForm" style="width: 100%; padding-top:20px; border-top:1px solid #efefef; display: none;">' + 
							 '<div class="input-group" style="width:100%; text-align:center; padding-left:35%;">' + 
								 '<input type="number" title="Release" class="form-control <portlet:namespace/>app-version" id="<portlet:namespace/>releaseNumber" style="float:left; margin:0px 5px;" min="0" value="" >' +
								 '<span style="float:left; padding-top:10px;">.</span>' +
								 '<input type="number" title="Major" class="form-control <portlet:namespace/>app-version" id="<portlet:namespace/>majorNumber" style="float:left; margin:0px 5px;" min="0" value="" >' +
								 '<span style="float:left; padding-top:10px;">.</span>' +
								 '<input type="number" title="Minor" class="form-control <portlet:namespace/>app-version" id="<portlet:namespace/>minorNumber" style="float:left; margin:0px 5px;" min="0" value="" >' +
							 '</div>' + 
							 '<div class="h10" ></div>' + 
								 Liferay.Language.get('edison-science-appstore-upgrade-new-version-massage') + '<br/>' +
								 Liferay.Language.get('edison-science-appstore-toolkit-app-copy-message') + 
							 '</div>';
							 
		$.confirm({
			boxWidth: '32%',
			useBootstrap: false,
			title: '',
			content: confirmContent,
			buttons: {
				upgrade: function () {
					
					var currVersion = $("#<portlet:namespace/>currVersion").val();
					var regVersion = new RegExp('[0-9]+[.][0-9]+[.][0-9]');
					var regVersionStr = new RegExp('[a-zA-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣]+');
					var newVersion = $("#<portlet:namespace/>releaseNumber").val() + "." + $("#<portlet:namespace/>majorNumber").val() + "." + $("#<portlet:namespace/>minorNumber").val();
					
					if(newVersion !=null && newVersion != '' && !regVersion.test(newVersion)){
						alert(Liferay.Language.get('edison-data-collection-enter-version-alert'));
						return;
					} else if(newVersion !=null && newVersion != '' && regVersionStr.test(newVersion)){
						alert(Liferay.Language.get('edison-data-collection-enter-version-alert'));
						return;
					} else {
						if(newVersion <= currVersion){
							alert(Liferay.Language.get('edison-science-appstore-upgrade-new-version-duplicate-massage'));
							return;
						}
					}
					
					
					jQuery.ajax({
						type: "POST",
						url: "<%=copyScienceAppURL%>",
						data: {"<portlet:namespace/>newVersion" : newVersion},
						async : false,
						dataType: 'json',
						success: function(result) {
							resultCopy = result.resultCopy;
							if(resultCopy){
								var confirmMsg = Liferay.Language.get('edison-science-appstore-toolkit-app-copy-success-message')+' '+result.newAppVersion;
								if(confirm(confirmMsg)){
									var searchParameter = "";
									searchParameter += "&<portlet:namespace/>scienceAppId="+result.newAppId;
									location.href="<%=newAppRenderURL%>"+searchParameter;
								}else{
									location.reload();
								}
							} else {
								alert(Liferay.Language.get('edison-science-appstore-upgrade-new-version-duplicate-massage'));
							}
						},error:function(jqXHR, textStatus, errorThrown){
							if(jqXHR.responseText !== ''){
								alert(textStatus+": "+jqXHR.responseText);
							}else{
								alert(textStatus+": "+errorThrown);
							}  
						}
					});
					
				},
				cancel: function () {
				}
			}
		});
	}
	
	/* 2018.11.05 Version Upgrade를 위한 현재 버전 가져오기 */
	function <portlet:namespace/>getScienceAppVersion(){
		var dataForm = {	
				"<portlet:namespace/>scienceAppId"	: "${scienceAppId}"
			};
		
		jQuery.ajax({
			type: "POST",
			url: "<%=getScienceAppInfoURL%>",
			async : false,
			data  : dataForm,
			success: function(data) {
				if(data.result==true){
					var scienceAppVersion = data.scienceAppVersion;
					$("#<portlet:namespace/>currVersion").val(scienceAppVersion);
				}
			},error:function(jqXHR, textStatus, errorThrown){
				if(jqXHR.responseText !== ''){
					alert(textStatus+": "+jqXHR.responseText);
				}else{
					alert(textStatus+": "+errorThrown);
				}  
			}
		});
	}
	
	/*  */
	function <portlet:namespace/>setNewUpgradeVersion(type){
		
		var currVersion = $("#<portlet:namespace/>currVersion").val();
		var appVersionArr = currVersion.split('.');
		
		$("#<portlet:namespace/>releaseNumber").attr("min", appVersionArr[0]);
		$("#<portlet:namespace/>majorNumber").attr("min", appVersionArr[1]);
		$("#<portlet:namespace/>minorNumber").attr("min", appVersionArr[2]);
		
		$("#<portlet:namespace/>defaultVersionDescription").hide();
		
		if(type == 'release'){
			$("#<portlet:namespace/>releaseNumber").val(appVersionArr[0]*1+1).attr("disabled", false);
			$("#<portlet:namespace/>majorNumber").val(0).attr("disabled", false);
			$("#<portlet:namespace/>minorNumber").val(0).attr("disabled", false);
		} else if(type == 'major'){
			$("#<portlet:namespace/>releaseNumber").val(appVersionArr[0]).attr("disabled", true);
			$("#<portlet:namespace/>majorNumber").val(appVersionArr[1]*1+1).attr("disabled", false);
			$("#<portlet:namespace/>minorNumber").val(0).attr("disabled", false);
		} else if(type == 'minor'){
			$("#<portlet:namespace/>releaseNumber").val(appVersionArr[0]).attr("disabled", true);
			$("#<portlet:namespace/>majorNumber").val(appVersionArr[1]).attr("disabled", true);
			$("#<portlet:namespace/>minorNumber").val(appVersionArr[2]*1+1).attr("disabled", false);
		}
		
		$("#<portlet:namespace/>upgradeVersionForm").show();
	}
</script>
