<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>


<body>
	<div class="columns-1-2-1" id="main-content" role="main">
	
	<!-- Virtual Lab Class > main visual -->
	<div>
	    <div class="portlet-column portlet-column-only span12" id="column-1">
	        <liferay-portlet:runtime portletName="edisonvirtuallabclassmainvisual_WAR_edisonvirtuallab2016portlet_INSTANCE_44oBtU3CyBWo"  queryString="&classId=${classId}&groupId=${groupId}&redirectName=${classTitle}&redirectURL=${redirectURL}&isDefaultUserWrite=${isDefaultUserWrite}&isCustomAdmin=${isCustomAdmin }" />
	    </div>
	</div>
	
	<div>
	    <div class="portlet-column portlet-column-only span12" id="column-2">
	        <liferay-portlet:runtime portletName="edisonvirtuallabclassscienceapplist_WAR_edisonvirtuallab2016portlet"  queryString="&classId=${classId}&groupId=${groupId}" />
	    </div>
	</div>
	
	<div>
		<div class="scienceappwrap">
		    <div class="portlet-column portlet-column-first span6" id="column-4" style="width: 580px; margin-right: 30px;">
		    	<liferay-portlet:runtime portletName="edisonvirtuallabclassnote_WAR_edisonvirtuallab2016portlet"  queryString="&classId=${classId}&groupId=${groupId}" />
		    </div>
		
		    <div class="portlet-column portlet-column-last span6" id="column-5" style="width: 580px;">
		        <liferay-portlet:runtime portletName="edisonmultiboard_WAR_edisonboard2016portlet_INSTANCE_HRmNo2UFaZ1Z"  queryString="&customId=class_${classId}&boardGroupId=${groupId}&redirectName=${classTitle}&redirectURL=${redirectURL}&isDefaultUserWrite=${isDefaultUserWrite}&isCustomAdmin=${isCustomAdmin }" />
		    </div>
		</div>
	</div>
	
	<!-- 관리자 또는 강의 수강생만 코멘트 기능 사용 -->
	<%-- <c:if test="${authYn == 'Y'}">
	    <div class="portlet-layout row">
	        <div class="portlet-column portlet-column-only span12" id="column-7">
	            <liferay-portlet:runtime portletName="edisoncomment_WAR_edisonboard2016portlet" queryString="&customId=class_${classId}&authYn=${authYn}&modelId=${classId}" />
	        </div>
	    </div>
    </c:if> --%>
    
    <div>
      
    </div>
    
</div>
