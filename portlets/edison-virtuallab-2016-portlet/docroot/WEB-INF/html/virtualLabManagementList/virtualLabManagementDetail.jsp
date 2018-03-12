<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<head>

<liferay-portlet:renderURL var="virtualLabManagementDetailURL">
	<liferay-portlet:param name="myRender" value="virtualLabManagementDetail" />
</liferay-portlet:renderURL>

<style>
	.virtual-lab-qna{
		width: 100%;
	}
	.virtual-lab-qna .conwrap2right{
		width: 100%;
	}
</style>

</head>
<body>
	<div class="columns-1-2-1" id="main-content" role="main" style="width: 1200px; margin: 0 auto;">
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
			    <!-- 강좌 내 강의들의 질문답변 전체 출력 -->
			    <div class="portlet-layout row-fluid">
			        <div class="portlet-column portlet-column-last span6 virtual-lab-qna" id="column-5">
			            <liferay-portlet:runtime portletName="edisonmultiboard_WAR_edisonboard2016portlet_INSTANCE_HRmNo2UFaZ1Z"  queryString="&customId=class_${classId}&boardGroupId=${groupId}&redirectName=${classTitle}&redirectURL=${redirectURL}&isDefaultUserWrite=${isDefaultUserWrite}&isCustomAdmin=${isCustomAdmin }&virtualLabId=${virtualLabId}&isVirtualClass=true" />
			        </div>
			    </div>
			</c:when>
			<%-- <c:when test="${admin eq 'Y' || }">
			
			</c:when> --%>
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
