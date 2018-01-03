<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

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
	
<%
    String renderUrl = HttpUtil.removeParameter(solverRenderURL, renderResponse.getNamespace()+"clickTab");
%>
<style type="text/css">
	.no-dialog-padding .ui-dialog-content{
		padding : 0px;
	}
</style>
<div class="container">
	<div class="swleft">
		${tabStr}
	</div>
	<div class="swrightcont">
		<liferay-util:include page='<%= "/WEB-INF/html/appmanager/" + jspFile + ".jsp" %>' servletContext="<%=this.getServletContext() %>">
		</liferay-util:include>
	</div>
</div>
<script type="text/javascript">
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
		if(!confirm(Liferay.Language.get('edison-science-appstore-toolkit-app-copy-message'))) return;
		
		jQuery.ajax({
			type: "POST",
			url: "<%=copyScienceAppURL%>",
			async : false,
			dataType: 'json',
			success: function(result) {
				var confirmMsg = Liferay.Language.get('edison-science-appstore-toolkit-app-copy-success-message')+' '+result.newAppVersion;
				if(confirm(confirmMsg)){
					var searchParameter = "";
					searchParameter += "&<portlet:namespace/>scienceAppId="+result.newAppId;
					location.href="<%=newAppRenderURL%>"+searchParameter;
				}else{
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
	}
</script>
