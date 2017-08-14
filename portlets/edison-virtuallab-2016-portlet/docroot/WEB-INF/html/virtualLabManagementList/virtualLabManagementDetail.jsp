<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<head>
</head>
<body>
	<div class="columns-1-2-1" id="main-content" role="main">
	    <div class="portlet-layout row-fluid">
	        <div class="portlet-column portlet-column-only span12" id="column-1">
	            <liferay-portlet:runtime portletName="edisonvirtuallabmainvisual_WAR_edisonvirtuallab2016portlet_INSTANCE_EhuMMjcuMQwq"  queryString="&virtualLabId=${virtualLabId}&groupId=${groupId}&admin=${admin} }" />
	        </div>
	    </div>
	    
	    <c:if test="${professorYn eq 'Y' }">
		    <div class="portlet-layout row-fluid">
		        <div class="portlet-column portlet-column-only span12" id="column-4">
		            <liferay-portlet:runtime portletName="edisonprofessormanagement_WAR_edisonvirtuallab2016portlet"   queryString="&virtualLabId=${virtualLabId}&groupId=${groupId}&admin=${admin}" />
		        </div>
		    </div>
	    </c:if>
	    
	    <div class="portlet-layout row-fluid">
	        <div class="portlet-column portlet-column-only span12" id="column-2">
	            <liferay-portlet:runtime portletName="edisonvirtuallabclassscienceapplist_WAR_edisonvirtuallab2016portlet"  queryString="&virtualLabId=${virtualLabId}&groupId=${groupId}&admin=${admin}" />
	        </div>
	    </div>
		<c:choose>
			<c:when test="${admin eq 'Y' }">
			    <div class="portlet-layout row-fluid">
			        <div class="portlet-column portlet-column-only span12" id="column-4">
			            <liferay-portlet:runtime portletName="edisonvirtuallabmanagermanagement_WAR_edisonvirtuallab2016portlet"  queryString="&virtualLabId=${virtualLabId}&groupId=${groupId}&admin=${admin}" />
			        </div>
			    </div>
			    <div class="portlet-layout row-fluid">
			        <div class="portlet-column portlet-column-only span12" id="column-4">
			            <liferay-portlet:runtime portletName="edisonvirtuallabclassmanagement_WAR_edisonvirtuallab2016portlet"   queryString="&virtualLabId=${virtualLabId}&groupId=${groupId}&admin=${admin}" />
			        </div>
			    </div>
				<div class="portlet-layout row-fluid">
			        <div class="portlet-column portlet-column-only span12" id="column-4">
			            <liferay-portlet:runtime portletName="edisonvirtuallabsurveyoption_WAR_edisonvirtuallab2016portlet"  queryString="&virtualLabId=${virtualLabId}&groupId=${groupId}&admin=${admin}" />
			        </div>
			    </div>
			</c:when>
			<c:otherwise>
			    
			    <div class="portlet-layout row-fluid">
			        <div class="portlet-column portlet-column-only span12" id="column-4">
			            <liferay-portlet:runtime portletName="edisonvirtuallabclassmanagementlist_WAR_edisonvirtuallab2016portlet"   queryString="&virtualLabId=${virtualLabId}&groupId=${groupId}&admin=${admin}" />
			        </div>
			    </div>
			</c:otherwise>
		</c:choose>
		
	    
	    
	</div>
</body>
