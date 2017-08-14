<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<liferay-portlet:renderURL var="scienceAppProLinkViewDialogueURL"
	windowState="<%=LiferayWindowState.POP_UP.toString()%>"
	copyCurrentRenderParameters="false">
	<liferay-portlet:param name="myRender" value="scienceAppProLinkViewDialogue" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="projectDetailUrl" portletName="edisonsimulationproject_WAR_edisonsimulationproject2017portlet" 
  windowState="<%=LiferayWindowState.MAXIMIZED.toString() %>" >
  <liferay-portlet:param name="myRender" value="detailView" />
</liferay-portlet:renderURL>

<c:if test="${fn:length(selectSimulationProjectList) > 0 || isMgrBtn == true}">
<div style="margin-bottom: 60px;">
	<div class="virtitlebox">
		<img src="${contextPath}/images/title_virtual.png" width="20" height="20" /> 
		<div class="virtitle">
			<liferay-ui:message key='edison-simulation-project-recommend-title' />			
		</div>
		<div style="float:right; padding-top:22px;">
		<c:if test="${isMgrBtn == true }">
			<input type="button"
				value="<liferay-ui:message key='edison-simulation-project-manage-btn'/>"
				class="topbtn btn_blueb"
				onclick="<portlet:namespace/>linkManagementPopup();" />
		</c:if>	
		</div>
	</div>
	
</div>
<div class="h10"></div>

<input type="hidden" name="<portlet:namespace/>sourceEntryId" id="<portlet:namespace/>sourceEntryId" value="${sourceEntryId }"/>
<input type="hidden" name="<portlet:namespace/>sourceClassName" id="<portlet:namespace/>sourceClassName" value="${sourceClassName}"/>
<input type="hidden" name="<portlet:namespace/>sourceClassPK" id="<portlet:namespace/>sourceClassPK" value="${sourceClassPK}"/>

<!--박스 나열-->
<div class="msbox" style="overflow: visible !important;">
    <ul>
    	<c:forEach var="selectSimulationProject" items="${selectSimulationProjectList}" varStatus="status">
	        <li class="txtnum" style="cursor: pointer; margin-bottom: 10px; ${(status.index +1) % 5 eq 0 ? 'margin-right: 0px;' : ''}" 
	        	onclick="<portlet:namespace/>moveSimulationProjectDetail('${selectSimulationProject.modelSeq}');">
	        	<img src="/documents/${selectSimulationProject.iconRepositoryId}/${selectSimulationProject.iconId}/${selectSimulationProject.iconTitle}/${selectSimulationProject.iconUuid}?imageThumbnail=2" 
	        		width="89" height="58" onerror="this.src='${contextPath}/images/comm_proj/img.gif'" />
	        	<br>
	        	<div class="ellipsis" style="line-height: 2.6em; height: 2.6em;">
	        		${selectSimulationProject.title}
	        	</div>	
			</li>
		</c:forEach>
    </ul>
</div>
</c:if>

<script type="text/javascript">

function <portlet:namespace/>moveSimulationProjectDetail(simulationProjectId) {
	var thisPortletNamespace = "_edisonsimulationproject_WAR_edisonsimulationproject2017portlet_";
	var params = "&" + thisPortletNamespace + "simulationProjectId=" + simulationProjectId;
	    params += "&" + thisPortletNamespace + "isRedirect=" + false;
	window.open("<%=projectDetailUrl%>" + params, '_blank');
}

function <portlet:namespace/>linkManagementPopup() //Relate AssetEntry 팝업 띄우기
{
	
	if(!confirm(Liferay.Language.get("edison-appstore-button-popup-alert"))){
		return;
	}
	
	var sourceEntryId = $("#<portlet:namespace/>sourceEntryId").val();
	var sourceClassName = $("#<portlet:namespace/>sourceClassName").val();
	var sourceClassPK = $("#<portlet:namespace/>sourceClassPK").val();
	
	var URL = '<%=scienceAppProLinkViewDialogueURL.toString()%>' 
		+ '&<portlet:namespace/>sourceEntryId='+ sourceEntryId
		+ '&<portlet:namespace/>sourceClassName='+ sourceClassName
		+ '&<portlet:namespace/>sourceClassPK='+ sourceClassPK
		;
				
	Liferay.Util.openWindow({
		dialog: {
			cache: false,
          	destroyOnClose: true,
			after: {
				render: function(event) {
					$("button.btn.close").on("click", function(e){
						$("body").css('overflow','');
						
						location.reload(); 
					});
				}
			},
			on: {
				close: function(event) {
					$("body").css('overflow','');
				}
			},  
			centered: true,
			modal: true,
			resizable: false,
			width:1000, 
			height:720
		},
		title: Liferay.Language.get("edison-search-simulation-project"),
		uri : URL,
		dialogIframe: {
			on: {
				load : function(evt) {
					$("body").css('overflow','hidden');
				}
			}
		}
	});  

}

</script>