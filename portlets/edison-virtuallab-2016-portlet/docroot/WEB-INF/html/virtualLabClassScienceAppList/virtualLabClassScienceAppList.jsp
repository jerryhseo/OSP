<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>
<%@ page import="com.liferay.portal.model.Layout" %>
<%@ page import="com.liferay.portal.service.LayoutLocalServiceUtil" %>

<link type="text/css" rel="stylesheet" href="${contextPath}/css/course.css" media="screen"/>
<link type="text/css" rel="stylesheet" href="${contextPath}/css/virtualLabClass.css" media="screen"/>

<liferay-portlet:resourceURL var="virtualLabScienceAppListURL" id="virtualLabScienceAppList" copyCurrentRenderParameters="false" />
<liferay-portlet:resourceURL var="edisonFileDownloadURL" escapeXml="false" id="edisonFileDownload" copyCurrentRenderParameters="false"/>

<liferay-portlet:renderURL var="popupScienceAppListURL" copyCurrentRenderParameters="false" windowState="<%=LiferayWindowState.POP_UP.toString() %>" portletMode="view">
	<liferay-portlet:param name="myaction" value="popupScienceAppList" />
	<liferay-portlet:param name="classId" value="${classId}" />
	<liferay-portlet:param name="groupId" value="${groupId}" />
</liferay-portlet:renderURL>


<liferay-portlet:renderURL var="scienceAppDetailUrl" portletName="edisonscienceAppstore_WAR_edisonappstore2016portlet" 
  windowState="<%=LiferayWindowState.MAXIMIZED.toString() %>" >
  <liferay-portlet:param name="myaction" value="detailView" />
</liferay-portlet:renderURL>

<style type="text/css">
	.buttonbox0801{margin:0 auto; overflow:hidden; padding-top:18px; padding-bottom:5px; text-align:center; float:right;} 
	.apparrow{cursor: pointer;}
	.manualdnbtn.run-btn{
		cursor: pointer;
		right: 95px;
	}
	.manualdnbtn.run-btn.no-manual{
		right: 105px;
	}
</style>

<aui:script>
Liferay.provide(
        window,
       '<portlet:namespace />closePopup',
        function(popupIdToClose) {
            var dialog = Liferay.Util.getWindow(popupIdToClose);
            $("body:first").css('overflow','');
            dialog.destroy(); // You can try toggle/hide whatever You want
        },
        ['liferay-util-window','dialog-iframe']
);
</aui:script>
<script type="text/javascript">
AUI().ready(function() {
	<portlet:namespace/>dataSearchList();
});
	
function <portlet:namespace/>dataSearchList() {
	var dataForm = {
		"<portlet:namespace/>classId" : "${classId}",
		"<portlet:namespace/>groupId" : "${groupId}",
		"<portlet:namespace/>virtualLabId" : "${virtualLabId}"
	};
	
	jQuery.ajax({
		type: "POST",
		url: "<%=virtualLabScienceAppListURL%>",
		async : false,
		data : dataForm,
		success: function(msg) {
			var virtualLabScienceAppList = msg.virtualLabScienceAppList;
			var virtualScienceAppManualList = msg.virtualScienceAppManualList;
			var groupId = msg.groupId;
			$("body").css('overflow','');
			var rowResult;
			$("#<portlet:namespace/>virtualLabClassScienceAppBody tr:not(:has(#1))").remove();
			
			if(virtualLabScienceAppList.length<=4){
				$("#<portlet:namespace/>scienceapp .apparrow").hide();
			} else{
				$("#<portlet:namespace/>scienceapp .apparrow").show();
			}
			
			/* 수정해야 할 코드 */
			$("#<portlet:namespace/>scienceappContent").html("");
			if(virtualLabScienceAppList.length == 0) {
				$("#<portlet:namespace/>scienceapp").attr("align","center")
													.text("<liferay-ui:message key='edison-there-are-no-data' />");
			} else {
				
				scienceAppList = null;
				scienceappListSeq = 0;
				for(var i = 0; i < virtualLabScienceAppList.length; i++) {
					
					if(i%4 == 0){
						classActive = "";
						if(i==0){
							classActive = "active"
						}
						scienceappListSeq = $(".scienceappList").length;
						scienceAppList = $("<div/>").addClass("scienceappList item "+classActive)
													.attr("id", "scienceappList_"+scienceappListSeq);
					}
					
					scienceApp = $("<div/>").addClass("scienceapp");
					scienceappUl = $("<ul/>");
					
					/* science app title */
					scienceAppTitle = $("<a/>").text(virtualLabScienceAppList[i].scienceAppName)
											   .attr("onclick", "event.cancelBubble=true; <portlet:namespace/>moveScienceAppDetail('" + virtualLabScienceAppList[i].scienceAppId +"', '"+ groupId + "');")
											   .css("cursor", "pointer");
					
					$("<li/>").addClass("scienceappTitle")
							  .append(scienceAppTitle)
							  .appendTo(scienceappUl);
					
					/* science app version / univ name / user name */
					$("<li/>").html("Ver " + virtualLabScienceAppList[i].scienceAppVersion
									+"<br>" + virtualLabScienceAppList[i].scienceAppUniversityNm
									+"<br>" + virtualLabScienceAppList[i].userFirstName )
									.appendTo(scienceappUl);
					
					manualDownloadIcon = $("<img/>").attr("src", "${contextPath}/images/download_icon.png")
													.attr("width", "9")
													.attr("height", "12");
					var runBtnClassName = "run-btn";
					if(virtualScienceAppManualList[i].fileEntryId != undefined) {
						$("<div/>").addClass("manualdnbtn")
								   .text("MANUAL ")
								   .append(manualDownloadIcon)
								   .attr("onClick", "event.cancelBubble=true; <portlet:namespace/>fileDownload('" + virtualScienceAppManualList[i].fileEntryId + "');")
								   .css("cursor", "pointer")
								   .appendTo(scienceappUl);
					} else {
						runBtnClassName += " no-manual";
						$("<div/>").addClass("manualdnbtn")
						   .text("NO MANUAL")
						   .appendTo(scienceappUl);
					}
					/* TODO Run button 만들기 */
					
					$("<div/>").addClass("manualdnbtn " + runBtnClassName)
							   .attr("onclick", "<portlet:namespace/>moveWorkBench('"+virtualLabScienceAppList[i].scienceAppId+"');")
							   .text("RUN")
							   .append($("<i/>").addClass("icon-play-circle").css("margin-left","3px"))
							   .appendTo(scienceappUl);
					
					scienceappUl.appendTo(scienceApp);
					scienceApp.appendTo(scienceAppList);
					
					if(i%3 == 0 || i == virtualLabScienceAppList.length-1){
						$("#<portlet:namespace/>scienceappContent").append(scienceAppList);
					}
				}
			}
		},error:function(msg,e){ 
			alert(e);
			return false;
		}
	});
}
<%
long scienceAppPlid = themeDisplay.getLayout().getPlid();
%>
function <portlet:namespace/>openScienceAppListPopup() {
	AUI().use("liferay-portlet-url", function(a) {
		var portletURL = Liferay.PortletURL.createRenderURL();
		var classId = '${classId}'===''?0:'${classId}';
		var appTitle
		if(classId == 0){
			appTitle = "<liferay-ui:message key='edison-virtuallab-scienceapp' />";
		}else{
			appTitle = "<liferay-ui:message key='edison-virtuallab-class-scienceapp' />"
		}
		$("body").css('overflow','hidden');
		portletURL.setPlid(<%=scienceAppPlid%>); 
		portletURL.setPortletMode("view");
		portletURL.setWindowState("pop_up");
		portletURL.setPortletId("edisonvirtuallabclassscienceapplist_WAR_edisonvirtuallab2016portlet"); 
		portletURL.setParameter("myaction", "popupScienceAppList"); 
		portletURL.setParameter("classId", "${classId}");
		portletURL.setParameter("groupId", "${groupId}");
		portletURL.setParameter("virtualLabId", "${virtualLabId}");
		Liferay.Util.openWindow(
			{
				dialog: {
					width:1024,
					height:720,
					cache: false,
					draggable: false,
					resizable: false,
					modal: true,
					destroyOnClose: true,
					after: {
						render: function(event) {
							$("button.btn.close").on("click", function(e){
								$("body").css('overflow','');
								<portlet:namespace/>dataSearchList(1);
							});
						}
					}
				},
			id: "scienceAppDialog",
			uri: portletURL.toString(),
				title: appTitle + " " + "<liferay-ui:message key='edison-virtuallab-scienceapp-management' />",
			}
		);
		
	});
}

function <portlet:namespace/>fileDownload(p_fileEntryId){
	location.href = "<%=edisonFileDownloadURL%>&<portlet:namespace/>fileEntryId="+p_fileEntryId;	
}

function <portlet:namespace/>moveScienceAppList(btnType){
	$("#<portlet:namespace/>"+btnType+"Btn").click();
}

$("#<portlet:namespace/>scienceapp .carousel").carousel("pause");

</script>

<aui:script>

function <portlet:namespace/>moveScienceAppDetail(scienceAppId, groupId) {
	AUI().use("liferay-portlet-url", function(a) {
		var thisPortletNamespace = "_edisonscienceAppstore_WAR_edisonappstore2016portlet_";
		var classNameId = '${classNameId}';
		var classId = '${classId}';
		var redirectName = '${redirectName }';
		var redirectURL = '${redirectURL }';
		
		var params = "&" + thisPortletNamespace + "solverId=" + scienceAppId;
			params += "&" + thisPortletNamespace + "groupId=" + groupId;
			params += "&" + thisPortletNamespace + "redirectName=" + redirectName;
			params += "&" + thisPortletNamespace + "redirectURL=" + redirectURL;
			if(classId != 0){
				params += "&" + thisPortletNamespace + "classId=" + classId;
				params += "&" + thisPortletNamespace + "classNameId=" + classNameId;
			}
			location.href = "<%=scienceAppDetailUrl%>" + params;

	});
}

function <portlet:namespace/>moveWorkBench(scienceAppId) {
	AUI().use("liferay-portlet-url", function(a) {
		var portletURL = Liferay.PortletURL.createRenderURL();
		var classNameId = '${classNameId}'===''?0:'${classNameId}';
		var classId = '${classId}'===''?0:'${classId}';
		
		portletURL.setPlid("${workBenchPlid}"); /* workBench Plid */ 
		portletURL.setPortletId("SimulationWorkbench_WAR_OSPWorkbenchportlet");  
		portletURL.setParameter("scienceAppId", scienceAppId);
		if(classId != 0){
			portletURL.setParameter("classId", classId);
			portletURL.setParameter("classNameId", classNameId);
		}
		
		window.location.href = portletURL.toString();
	});
}

</aui:script>
<div>
	<c:choose>
		<c:when test="${virtualLabScienceAppList == 0 && role eq 'member' }">
		</c:when>
		<c:otherwise>
			<c:choose>
					<c:when test="${role eq 'admin' }">
						<%-- <img src="${contextPath}/images/title_virtual.png" width="20" height="20" /> --%> 
						<div class="classtitle">
							<c:choose>
								<c:when test="${empty classId || classId == 0}">
									<liferay-ui:message key='edison-virtuallab-scienceapp' />
								</c:when>
								<c:otherwise>
									<liferay-ui:message key='edison-virtuallab-class-scienceapp' />
								</c:otherwise>
							</c:choose>
							<div class="adminbtn">
								<img src="${contextPath}/images/class_admin_btn.png" width="81" height="36" onClick="<portlet:namespace/>openScienceAppListPopup()">
							</div>
						</div>
					</c:when>
					<c:otherwise>
						<div class="classtitle">
							<h3>
								<liferay-ui:message key='edison-virtuallab-class-scienceapp' />
							</h3>
						</div>
					</c:otherwise>
			</c:choose>
			
			<!--박스리스트-->
			<div class="scienceappwrap carousel slide" id="<portlet:namespace/>scienceapp" data-ride="false" >
			
				<div class="apparrow" onclick="<portlet:namespace/>moveScienceAppList('prev')">
					<img src="${contextPath}/images/class_l_arrow.png" width="28" height="49">
				</div>
				
				<!-- Science App List -->
				<div id="<portlet:namespace/>scienceappContent" class="carousel-inner" style="width: 95%; float: left;">
				</div>
				
				<div class="apparrow" onclick="<portlet:namespace/>moveScienceAppList('next')">
					<img src="${contextPath}/images/class_r_arrow.png" width="28" height="49">
				</div>
				
				<div style="display: none;">
					<a class="left carousel-control" href="#<portlet:namespace/>scienceapp" data-slide="prev" id="<portlet:namespace/>prevBtn">
						<span class="glyphicon glyphicon-chevron-left"></span>
						<span class="sr-only">Previous</span>
					</a>
					<a class="right carousel-control" href="#<portlet:namespace/>scienceapp" data-slide="next" id="<portlet:namespace/>nextBtn">
						<span class="glyphicon glyphicon-chevron-right"></span>
						<span class="sr-only">Next</span>
					</a>
				</div>
	
			</div>
		</c:otherwise>
	</c:choose>
</div>
