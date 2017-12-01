<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<link type="text/css" rel="stylesheet" href="${contextPath}/css/style.css" media="screen"/>

<liferay-portlet:resourceURL var="virtualLabRegistrationListURL" id="virtualLabRegistrationList" copyCurrentRenderParameters="false" />

<liferay-portlet:resourceURL var="virtualLabCancelResourceURL" id="cancelVirtualLabResource" copyCurrentRenderParameters="false" />

<liferay-portlet:renderURL var="virtualLabRequestURL" portletMode="view" copyCurrentRenderParameters="false">
	<liferay-portlet:param name="myrender" value="virtualLabRequest" />
</liferay-portlet:renderURL>

<liferay-portlet:actionURL var="virtualLabCreateURL" portletMode="view" copyCurrentRenderParameters="false" >
	<liferay-portlet:param name="myaction" value="createVirtualLab" />
</liferay-portlet:actionURL>

<liferay-portlet:renderURL 
	portletName="edisonorgcodesearch_WAR_edisondefault2016portlet" 
	portletMode="view"
	windowState="<%= LiferayWindowState.POP_UP.toString() %>" var="syscommoncdURL">
	<liferay-portlet:param name="up_code" value="1501"/>
	<liferay-portlet:param name="com_search_type" value="orgSearch"/>
	<liferay-portlet:param name="popup_title" value="edison-org-code-search"/>
	<liferay-portlet:param name="universityFieldNm" value="virtualLabUniversityField"/>
	<liferay-portlet:param name="universityField" value="universityField"/>
</liferay-portlet:renderURL>

<script type="text/javascript">
AUI().ready(function() {
	<portlet:namespace/>dataSearchList(1);
	$("#<portlet:namespace/>request-denied-dialog").dialog({
		autoOpen: false,
	    width: 'auto',
	    height: 'auto',
	    modal: true,
	    resizable: false,
	    show: {effect:'fade', speed: 800}, 
        hide: {effect:'fade', speed: 800},
	    open: function(event, ui) {
	    	$(this).css('overflow', 'hidden');
	    	$(this).parent().removeClass("ui-widget-content");
	    	$(this).parent().removeClass("ui-widget");
	    	$(this).removeClass("ui-widget-content");
	    	$(this).removeClass("ui-dialog-content");
	    },
	    close: function() {
	    	
	    }
	}).dialog("widget").find(".ui-dialog-titlebar").remove();
	$("#<portlet:namespace/>request-denied-dialog-close-btn").click(function() {
		$("#<portlet:namespace/>request-denied-dialog").dialog("close");
	});

});

function <portlet:namespace/>dataSearchList(pageNumber) {
	if(pageNumber == 0) {
		$("#<portlet:namespace/>cur_page").val(1);
		$("#<portlet:namespace/>searchField").val("");
		$("#<portlet:namespace/>selectStatus").val("0");
	} else {
		$("#<portlet:namespace/>cur_page").val(pageNumber);
	}
	
	var searchForm = $("form[name=searchForm]").serialize();
	console.log(searchForm);
	
	jQuery.ajax({
		type: "POST",
		url: "<%=virtualLabRegistrationListURL%>",
		async : false,
		data : searchForm,
		success: function(msg) {
		    console.log(msg);
			var virtualLabRegisterList = msg.virtualLabRegisterList;
			var groupId = msg.groupId;
			var curPage = msg.curPage;
			var selectLine = msg.selectLine;
			var virtualLabCount = msg.virtualLabCount - ((curPage -1) * selectLine);
			var pageList = msg.pageList;
			var labOwner = msg.labOwner;
			
			var rowResult;
			$("#<portlet:namespace/>registrationListBody tr:not(:has(#1))").remove();
			
			if(virtualLabRegisterList.length == 0) {
				$rowResult = $("<tr/>");
				$("<td/>").attr("colspan", "8")
						  .css("text-align","center")
						  .text("<liferay-ui:message key='edison-search-no-result' />")
						  .appendTo($rowResult);
				$("#<portlet:namespace/>registrationListBody").append($rowResult);
			} else {
				
					console.log("virtualLabRegisterList ", virtualLabRegisterList);
				for(var i = 0; i < virtualLabRegisterList.length; i++) {
					$rowResult = $("<tr/>");
					
 					if(i%2 == 1){
 						$rowResult.addClass("tablebgtr");
 					}
					
					$("<td/>").text(virtualLabRegisterList[i].groupName)
							  .css("text-align","center")
							  .appendTo($rowResult);
					$("<td/>").text(virtualLabRegisterList[i].virtualLabTitle)
							  .css("text-align","left")
							  .appendTo($rowResult);
					$("<td/>").text(virtualLabRegisterList[i].virtualLabUniversityField)
							  .css("text-align","center")
							  .appendTo($rowResult);
					$("<td/>").text(virtualLabRegisterList[i].virtualLabPersonName)
							  .css("text-align","center")
							  .appendTo($rowResult);
					$("<td/>").text(virtualLabRegisterList[i].virtualLabRequestDt)
							  .css("text-align","center")
							  .appendTo($rowResult);
					if(virtualLabRegisterList[i].virtualLabStatus == "1401002") {
						$("<td/>").css("text-align","center")
						  .append($("<p/>").text("<liferay-ui:message key='edison-simulation-job-create-success' />")
										   .css("font-weight", "600")
										   .css("margin","0")
						  )
						  .appendTo($rowResult);
						
						$("<td/>").css("text-align","center")
								  .append($("<input/>").attr("value", "<liferay-ui:message key='edison-virtuallab-move' />")
													   .addClass("button01b")
													   .attr("type", "button")
													   .css("text-align","center")
													   .attr("onClick","<portlet:namespace/>moveVirtualLab('" + virtualLabRegisterList[i].virtualLabId + "','" + virtualLabRegisterList[i].groupId + "')")
								  )
						  .appendTo($rowResult);
						
					} else if (virtualLabRegisterList[i].virtualLabStatus == "1401001") {
						$("<td/>").css("text-align","center")
								  .append($("<p/>").text("<liferay-ui:message key='edison-virtuallab-lab-creation-request' />")
												   .css("font-weight", "600")
												   .css("margin","0")
								  )
								  .appendTo($rowResult);
						
						$("<td/>").css("text-align","center")
								  .append($("<input/>").attr("value", "<liferay-ui:message key='edison-virtuallab-lab-creation-request-cancel' />")
													   .addClass("button01b")
													   .attr("type", "button")
													   .css("text-align","center")
													   .attr("onClick","<portlet:namespace/>cancelVirtualLabRequest('" + virtualLabRegisterList[i].virtualLabId + "')")
								  )
								  .appendTo($rowResult);
						
						virtualLabCount--;
					} else if (virtualLabRegisterList[i].virtualLabStatus == "1401003") {
						$("<td/>").css("text-align","center")
							  .append($("<p/>").text("<liferay-ui:message key='edison-virtuallab-lab-creation-request-denial' />")
											   .css("font-weight", "600")
											   .css("color", "red")
											   .css("margin","0")
							  )
							  .appendTo($rowResult);
						$("<td/>").css("text-align","center")
							  .append($("<input/>").attr("value", "<liferay-ui:message key='edison-virtuallab-virtualLabClassRegistrationList-reason-denial' />")
												   .addClass("button01b")
												   .attr("type", "button")
												   .attr("onClick","<portlet:namespace/>openDeniedDialog('" 
												         + "<liferay-ui:message key='edison-virtuallab-virtualLabClassRegistrationList-reason-denial' />" + "','" 
												         + virtualLabRegisterList[i].virtualLabConfirmDescription + "','" 
												         + virtualLabRegisterList[i].virtualLabId +"')")
							  )
							  .appendTo($rowResult);
						
						virtualLabCount--;
					} else {
						$("<td/>").text("<liferay-ui:message key='edison-virtuallab-virtualLabClassRegistrationList-unknown-class' />")
								  .css("text-align","center")
								  .appendTo($rowResult);
					}
					
					$("#<portlet:namespace/>registrationListBody").append($rowResult);
				}
			}
			
			$("#<portlet:namespace/>pageListDiv").html(pageList);
		},error:function(msg,e){ 
			alert(e);
			return false;
		}
	});
}

function <portlet:namespace/>moveVirtualLab(virtualLabId, groupId) {
	var URL = "${labURL}" + "&_edisonvirtuallabmanagementlist_WAR_edisonvirtuallab2016portlet_virtualLabId=" + virtualLabId+"&_edisonvirtuallabmanagementlist_WAR_edisonvirtuallab2016portlet_groupId="+groupId;
	window.location.href = URL;
}

function deleteVirtualLabRequest() {
	var tempVirtualLabId = $("#<portlet:namespace/>tempVirtualLabId").val();
	<portlet:namespace/>cancelVirtualLabRequest(tempVirtualLabId);
	$("#<portlet:namespace/>request-denied-dialog").dialog("close");
}


function <portlet:namespace/>cancelVirtualLabRequest(virtualLabId) {
	if(confirm("<liferay-ui:message key='edison-virtuallab-cancel-request-alert' />")){	
		var checkForm = {
			"<portlet:namespace/>virtualLabId" : virtualLabId
		}
		
		jQuery.ajax({
			type: "POST",
			url: "<%=virtualLabCancelResourceURL%>",
			async : false,
			data : checkForm,
			success: function(msg) {
				<portlet:namespace/>dataSearchList();
			},error:function(msg,e){ 
				alert(e);
				return false;
			}
		});
	}
}

function <portlet:namespace/>VirtualLabRequest() {
	var redirectUrl = "${redirectURL }";
	location.href = "<%=virtualLabRequestURL%>&<portlet:namespace/>redirectURL="+ redirectUrl; 
}

function <portlet:namespace/>openDeniedDialog(title, content, virtualLabId) {
	if(virtualLabId > 0) {
		$("#<portlet:namespace/>register_delete_button").css("visibility", "visible");
	}else {
		$("#<portlet:namespace/>register_delete_button").css("visibility", "hidden");
	}
	$("#<portlet:namespace/>tempVirtualLabId").val(virtualLabId);
	$("#<portlet:namespace/>request-denied-title").text(title);
	$("#<portlet:namespace/>request-denied-content").text(content);
	$("#<portlet:namespace/>request-denied-dialog").dialog("open");
}

function <portlet:namespace/>checkValidation(form) {
	if (form.submitted) return false;
	if(!checkValue(form.<portlet:namespace/>virtualLabPersonName, "<liferay-ui:message key='edison-table-list-header-tutor' />", 1, 30)) {
		return false;
	} else if (!checkValue(form.<portlet:namespace/>virtualLabTitle, "<liferay-ui:message key='edison-virtuallab-tablerow-virtuallab' />", 1, 75)) {
		return false;
	} else if (!checkValue(form.<portlet:namespace/>universityField, "<liferay-ui:message key='edison-create-account-field-title-university' />", 1, 10)) {
		return false;
	} else if (!checkValue(form.<portlet:namespace/>virtualLabDescription, "<liferay-ui:message key='edison-table-list-header-resume' />", 1, 100)) {
		return false;
	} else {
		form.submitted = true;
		return true;
	}
	return false;
}

function <portlet:namespace/>syscommoncdPopup(){
	var URL = "<%=syscommoncdURL%>";
	w = 850;
	h = 550;		
	x = (screen.availWidth - w) / 2;
	y = (screen.availHeight - h) / 2;
	var options = "width="+w+", height="+h+", left="+x+",top="+y+",toolbar=no, menubar=no, scrollbars=yes, resizable=no, copyhistory=no";
	var openPop;
	if(openPop != null){
		openPop.focus();
	}else{
		openPop = window.open(URL, "syscommoncdPopup",options);
		openPop.focus();
	}
}

function checkGroup(val) {
	document.<portlet:namespace/>createVirtualLabForm.<portlet:namespace/>groupId.value = val;
}
	  
function <portlet:namespace/>onKeyDown() {
	if(event.keyCode == 13 && $("#<portlet:namespace/>search_parameter").val() != ""){
		<portlet:namespace/>dataSearchList();
	}
}

</script>
</body>
<div class="virtitlebox"><img src="${contextPath}/images/title_virtual.png" width="20" height="20" /> 
	<div class="virtitle"><liferay-ui:message key='edison-virtuallab-virtualLabRegistrationList-registration-status' /></div>
	<div class="buttonbox0801" style="padding-top: 25px;  float: right;">
		<c:choose>
			<c:when test="${role eq 'LABOWNER' }">
				<input id="<portlet:namespace/>virtualLab-request-button" name="<portlet:namespace/>virtualLab-request-button" type="button" onclick="<portlet:namespace/>VirtualLabRequest();" class="button0801" value="<liferay-ui:message key='edison-virtuallab-creation' />" />
			</c:when>
			<c:otherwise>
				<input id="<portlet:namespace/>virtualLab-request-button" name="<portlet:namespace/>virtualLab-request-button" type="button" onclick="<portlet:namespace/>VirtualLabRequest();" class="button0801" value="<liferay-ui:message key='edison-virtuallab-creation-request' />" />
			</c:otherwise>
		</c:choose>
	</div>
</div>

<div class="h20"></div>

<div class="tabletopbox">
	<form method="post" name="searchForm" style="margin: 0px;" onsubmit="return false;">
		<input id="<portlet:namespace/>cur_page" name="<portlet:namespace/>cur_page" type="hidden" value="1"/>
		<div class="search">
			<div class="searchbox">
				<input id="<portlet:namespace/>searchField" name="<portlet:namespace/>searchField" type="text" maxlength="15" placeholder="<liferay-ui:message key='edison-virtuallab-tablerow-virtuallab' />" onKeydown="if(event.keyCode ==13)<portlet:namespace/>dataSearchList('1');" value="${searchField}"/>
				<input id="search_button" name="<portlet:namespace />search_button" type="button" class="btnsearch" onclick="<portlet:namespace/>dataSearchList(1);"/>
			</div>
			<input id="total_search_button" name="<portlet:namespace />total_search_button" type="button" value="<liferay-ui:message key='edison-button-all-search' />" class="button01" onclick="<portlet:namespace/>dataSearchList(0)" />
		</div>
		<div class="tabletopright" style="right: 150px;">
			<select id="<portlet:namespace/>selectStatus" name="<portlet:namespace/>selectStatus" onchange="<portlet:namespace/>dataSearchList(1)" class="selectview" style="line-height: 15px;">
				<option value="0">ALL</option>
				<option value="1401001"><liferay-ui:message key='edison-virtuallab-lab-creation-request' /></option>
				<option value="1401002"><liferay-ui:message key='edison-simulation-job-create-success' /></option>
				<option value="1401003"><liferay-ui:message key='edison-virtuallab-lab-creation-request-denial' /></option>
			</select>
		</div>
		<div class="tabletopright">
			<select id="<portlet:namespace/>select_line" name="<portlet:namespace/>select_line" onchange="<portlet:namespace/>dataSearchList(1);" class="selectview" style="line-height: 15px;">
				<option value="10" <c:if test="${selectLine == '10' }"> selected="selected" </c:if>>10<liferay-ui:message key='edison-search-views' /></option>
				<option value="20" <c:if test="${selectLine == '20' }"> selected="selected" </c:if>>20<liferay-ui:message key='edison-search-views' /></option>
				<option value="30" <c:if test="${selectLine == '30' }"> selected="selected" </c:if>>30<liferay-ui:message key='edison-search-views' /></option>
				<option value="40" <c:if test="${selectLine == '40' }"> selected="selected" </c:if>>40<liferay-ui:message key='edison-search-views' /></option>
			</select>
		</div>
	</form>
</div>

<div class="table7_list">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<colgroup>
			<col width="9%" />
			<col width="20%" />
			<col width="20%" />
			<col width="10%" />
			<col width="20%" />
			<col width="15%" />
			<col width="10%" />
		</colgroup>
		<thead>
			<tr>
				<th align="center" scope="col"><liferay-ui:message key='edison-virtuallab-tablerow-site' /></th>
				<th align="center" scope="col"><liferay-ui:message key='edison-virtuallab-tablerow-virtuallab' /></th>
				<th align="center" scope="col"><liferay-ui:message key='edison-virtuallab-tablerow-affiliation' /></th>
				<th align="center" scope="col"><liferay-ui:message key='edison-virtuallab-tablerow-professor' /></th>
				<th align="center" scope="col"><liferay-ui:message key='edison-virtuallab-tablerow-request-date' /></th>
				<th align="center" scope="col"><liferay-ui:message key='edison-virtuallab-tablerow-status' /></th>
				<th align="center" scope="col"></th>
			</tr>
		</thead>
		<tbody id="<portlet:namespace/>registrationListBody">
		</tbody>
	</table>
	<div id="<portlet:namespace/>spaceDiv" align="center"></div>
	<div id="<portlet:namespace/>pageListDiv" class="paging"></div>
</div>
</body>


<div id="<portlet:namespace/>request-denied-dialog" title="<liferay-ui:message key='edison-virtuallab-virtualLabClassRegistrationList-course-registration' />" class="newWindow" style="background-color: #fff; display:none;" >
	<input id="<portlet:namespace/>tempVirtualLabId" name="<portlet:namespace/>tempVirtualLabId" type="hidden">
	<div class="newWheader">
		<div class="newWtitlebox"><img src="<%=renderRequest.getContextPath()%>/images/title_newWindow.png" width="34" height="34">
			<div class="newWtitle">
				<liferay-ui:message key='edison-virtuallab-virtualLabClassRegistrationList-reason-denial' />
			</div>
		</div>
		<div class="newWclose">
			<img id="<portlet:namespace/>request-denied-dialog-close-btn" name="<portlet:namespace/>request-denied-dialog-close-btn" src="<%=renderRequest.getContextPath()%>/images/btn_closeWindow.png" width="21" height="21" style="cursor:pointer; float: right;"/>
		</div>
	</div>
	
	<div class="newWcont01">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" style="width:350px; min-height:50px;">
			<tbody>
				<tr>
					<td id="<portlet:namespace/>request-denied-content" class="puptxt2"></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div style="text-align: right; margin:0px 25px 30px 0px;">
		<input id="<portlet:namespace/>register_delete_button" name="<portlet:namespace/>register_delete_button" class="button06" type="button" onclick="deleteVirtualLabRequest()" value="<liferay-ui:message key='edison-virtuallab-cancel-request' />" />
	</div>
</div>
