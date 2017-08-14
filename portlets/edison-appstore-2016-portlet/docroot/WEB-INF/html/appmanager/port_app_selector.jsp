<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>
<style type="text/css">
#port-app-selector-dialog label{
	display: inline;
	margin-bottom: 0px;
	font-size: 17px;
}

.defaultAppRadio{
	margin: 0px;
}

</style>

<div class="newWindow" style="background-color: #fff; overflow-x: hidden;overflow-y: hidden;">
	<div class="newWheader">
		<div class="newWtitlebox"><img src="<%=renderRequest.getContextPath()%>/images/title_newWindow.png" width="34" height="34">
			<div class="newWtitle">
				Change Default App
			</div>
		</div>
		<div class="newWclose">
			<img id="default-app-close-btn" name="default-app-close-btn" src="<%=renderRequest.getContextPath()%>/images/btn_closeWindow.png" width="21" height="21" style="cursor:pointer; float: right;"/>
		</div>
	</div>
	<div class="newWcont01">
		<c:if test="${!empty appList}">
			<div style="width:50%; float:right; text-align:right; padding-bottom:15px;">
				<input class="addIp button02_1" onclick="<portlet:namespace/>appSave();return false;" value="<liferay-ui:message key='edison-button-save'/>" type="button"/>
			</div>
		</c:if>	
		<table width="470" border="0" cellpadding="0" cellspacing="0" class="table1" style="word-break: break-all;">
			<colgroup>
				<col width="*" />
			</colgroup>
			<thead>
				<tr>
					<th scope="col" class="left">Default App</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${!empty appList}">
						<c:forEach items="${appList}" var="data" varStatus="status">
							<tr>
								<td>
									<input type="radio" name="defaultAppId" class="defaultAppRadio" id="defaultAppRadio_${status.index}" value="${data.scienceAppId}" 
										<c:if test="${'data.scienceAppId' == 'defaultAppId' }">checked="checked"</c:if>
									/>
									<label for='defaultAppRadio_${status.index}'>
										${data.name}
									</label>
								</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td class="TC"><liferay-ui:message key='edison-there-are-no-data'/></td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
</div>

<script type="text/javascript">
AUI().ready(function() {
	$("#default-app-close-btn").click(function() {
		$("#port-app-selector-dialog").dialog("close");
	});
	
	<portlet:namespace/>appCheckFromRadio('${defaultAppId}');
});

function <portlet:namespace/>appCheckFromRadio(defaultAppId){
	if(defaultAppId!=0){
		$("input:radio[name='defaultAppId'][value='"+defaultAppId+"']").prop("checked",true);
	}
}
function <portlet:namespace/>appSave(){
	if($("input:radio[name='defaultAppId']").is(":checked")){
		var defaultAppId = $("input:radio[name='defaultAppId']:checked").val();
		var targetObejct = null;
		if('${portType}'=='INPUT'){
			targetObejct = scienceApp.inputPort('${portName}');
			targetObejct.defaultEditor(defaultAppId);
		}else if('${portType}'=='LOG'){
			targetObejct = scienceApp.logPort('${portName}');
			targetObejct.defaultAnalyzer(defaultAppId);
		}else if('${portType}'=='OUTPUT'){
			targetObejct = scienceApp.outputPort('${portName}');
			targetObejct.defaultAnalyzer(defaultAppId);
		}
		<portlet:namespace/>drawPort('${portType}','','');
		$("#port-app-selector-dialog").dialog("close");
	}else{
		alert(Liferay.Language.get('edison-this-field-is-required',['app']));
		return false;
	}
	
}
</script>