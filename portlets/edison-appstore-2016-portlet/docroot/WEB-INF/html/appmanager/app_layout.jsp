<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.portal.kernel.util.Constants"%>
<%@ page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@ page import="com.liferay.portal.kernel.util.HttpUtil"%>

<link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
<script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>

<portlet:actionURL var="submitURL" copyCurrentRenderParameters="<%=false%>" name="appAction">
	<portlet:param name="clickTab" value="${clickTab}"/>
	<portlet:param name="actionType" value="appLayout"/>
	<portlet:param name="isPort" value="${isPort}"/>
	<portlet:param name="scienceAppId" value="${scienceAppId}"/>
	
	<portlet:param name="searchValue" value="${searchValue}"/>
	<portlet:param name="searchOption" value="${searchOption}"/>
	<portlet:param name="searchStatus" value="${searchStatus}"/>
	<portlet:param name="p_curPage" 	value="${p_curPage}" />
	<portlet:param name="redirectURL" 	value="${redirectURL}" />
</portlet:actionURL>

<liferay-portlet:renderURL var="appLayoutRenderURL" copyCurrentRenderParameters="<%=true%>" windowState="<%=LiferayWindowState.MAXIMIZED.toString()%>"/>

<%
	String layoutURL = HttpUtil.removeParameter(appLayoutRenderURL, renderResponse.getNamespace()+"templateId");
	String templateJSP = GetterUtil.get(request.getAttribute("templateJSP"), "");
%>
<style type="text/css">
	.science-app-manager-portlet .layout-wrap .btn {
		border-color: #dddddd;
	}
	
	.science-app-manager-portlet .layout-wrap .layoutBtnGroup {
		max-width: 800px;
		margin: auto;
	}
	
	.science-app-manager-portlet .layout-wrap .layoutBtnGroup .active {
		outline: none !important;
		background-color: #f1f1f1;
	}
	.science-app-manager-portlet .layout-wrap .layoutBtnGroup .btn.active {
		border-color: #bbbbbb;
	}
	
	.science-app-manager-portlet .layout-wrap .layoutBtnGroup .active .method {
		border-color: #4cd264;
		outline: none !important;
	}
	
	.science-app-manager-portlet .layout-wrap .layoutBtnGroup .layoutMethod{
		padding: 40px;
		position: relative;
	}
	
	.science-app-manager-portlet .layout-wrap .layoutBtnGroup .layoutMethod .method {
		position: absolute;
		right: 3px;
		top: 3px;
		bottom: 3px;
		left: 3px;
		background-position: center;
		background-repeat: no-repeat;
		border: 2px solid transparent;
		transition: all 0.5s;
	}
	
	.science-app-manager-portlet .layout-wrap .layoutBtnGroup .method.layout-1{
		background-image:url(/edison-appstore-2016-portlet/images/appmanager/layout/layout01.png);
		background-size: 50px;
	}
	.science-app-manager-portlet .layout-wrap .layoutBtnGroup .method.layout-2{
		background-image:url(/edison-appstore-2016-portlet/images/appmanager/layout/layout02.png);
		background-size: 50px;
	}
	.science-app-manager-portlet .layout-wrap .layoutBtnGroup .method.layout-3{
		background-image:url(/edison-appstore-2016-portlet/images/appmanager/layout/layout03.png);
		background-size: 50px;
	}
	.science-app-manager-portlet .layout-wrap .layoutBtnGroup .method.layout-4{
		background-image:url(/edison-appstore-2016-portlet/images/appmanager/layout/layout04.png);
		background-size: 50px;
	}
	.science-app-manager-portlet .layout-wrap .layoutBtnGroup .method.layout-5{
		background-image:url(/edison-appstore-2016-portlet/images/appmanager/layout/layout05.png);
		background-size: 50px;
	}
	.science-app-manager-portlet .layout-wrap .layoutBtnGroup .method.layout-6{
		background-image:url(/edison-appstore-2016-portlet/images/appmanager/layout/layout06.png);
		background-size: 50px;
	}
	.science-app-manager-portlet .layout-wrap .layoutBtnGroup .method.layout-7{
		background-image:url(/edison-appstore-2016-portlet/images/appmanager/layout/layout07.png);
		background-size: 50px;
	}
	
	.science-app-manager-portlet .layout-wrap .layoutBtnGroup .method:hover {
		border : solid 3px #ffffff;
		outline: none !important;
	}
	
	/*aui css error*/
	.science-app-manager-portlet .collapse {
		display: none;
	}
	.science-app-manager-portlet .sortableLayout .row{
		margin-right: 0px;
	}
	
	.science-app-manager-portlet .sortableLayout ul.sortable-list{
		min-height: 100px;
		padding: 0px;
		margin: 20px 0px;
	}
	
	.science-app-manager-portlet .sortableLayout ul.sortable-list li.sortable-item{
		border: solid 1px #cccccc;
		cursor: move;
		margin: 5px 0;
	}
	
	.science-app-manager-portlet .sortableLayout ul.sortable-list li.sortable-item .sortRemove{
		display:none;
		cursor: pointer;
		float: right;
	}
	
	.science-app-manager-portlet .gridLayoutArea ul.sortable-list li.sortable-item .sortRemove{
		display: block;
	}
	
	.science-app-manager-portlet .gridLayoutArea ul.sortable-list li.list-group-item-success:after{
		content: "                <liferay-ui:message key='edison-science-appstore-toolkit-input-port' />";
	}
	
	.science-app-manager-portlet .gridLayoutArea ul.sortable-list li.list-group-item-warning:after{
		content: "                <liferay-ui:message key='edison-science-appstore-toolkit-log-port' />";
	}
	
	.science-app-manager-portlet .gridLayoutArea ul.sortable-list li.list-group-item-danger:after{
		content: "                <liferay-ui:message key='edison-science-appstore-toolkit-out-port' />";
	}
	
	.science-app-manager-portlet .sortableLayout .row .col{
		border: solid 2px #fafafa;
		background-color: #D2EBEE;
	}
	
	.ui-state-highlight { height: 44px; line-height: 10px;}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$('.science-app-manager-portlet .sortable-list').sortable({
		connectWith: '.science-app-manager-portlet .gridLayoutArea .sortable-list',
		placeholder: "ui-state-highlight"
	});
	
	<portlet:namespace/>layoutAreaViewInit();
	
	$(".layoutMethod>input[name=templates]").change(function() {
		if('${data.templateId}'!= $(this).val()){
			var searchParameter = "&<portlet:namespace/>templateId="+$(this).val();
			location.href="<%=layoutURL%>"+searchParameter;
		}
	});
});

var scienceApp = new OSP.ScienceApp();
function <portlet:namespace/>layoutAreaViewInit(){
	if('${data.templateId}'!=""){
		if('${data.templateId}'.indexOf("flow")>-1){
			$("#<portlet:namespace/>noFlowLayoutArea").css("display","none");
			$("#<portlet:namespace/>flowLayoutArea").css("display","block");
		}else{
			$("#<portlet:namespace/>noFlowLayoutArea").css("display","block");
			$("#<portlet:namespace/>flowLayoutArea").css("display","none");
		}
		
		$radioObject = $(".layoutMethod>input[name=templates][value="+'${data.templateId}'+"]");
		$radioObject.prop('checked', true);
		$radioObject.parent().addClass("active");
		
		if('${data.isPortDraw}'=='true'){
			<portlet:namespace/>drawPort('INPUT','${data.inputPorts}');
			<portlet:namespace/>drawPort('LOG','${data.logPorts}');
			<portlet:namespace/>drawPort('OUTPUT','${data.outputPorts}');
		}else{
			<portlet:namespace/>drawLayout('${data.layout}');
		}
	}
}

function <portlet:namespace/>layoutAreaViewEvent(){
	if($("#<portlet:namespace/>layoutAreaButton").prop("checked")){
		$("#<portlet:namespace/>noFlowLayoutArea").css("display","none");
		$("#<portlet:namespace/>flowLayoutArea").css("display","block");
	}else{
		$("#<portlet:namespace/>noFlowLayoutArea").css("display","block");
		$("#<portlet:namespace/>flowLayoutArea").css("display","none");
	}
}

function <portlet:namespace/>layoutResult(){
	$( ".gridLayoutArea .sortable-list" ).each(function() {
		var sortedIDs = $(this).sortable("toArray");
		for(var i=0;i<sortedIDs.length;i++){
			var portId = sortedIDs[i];
			alert("portID ===>"+portId);
			var portInstanceId = $(".sortable-list li[id='"+portId+"']").attr("data-port-portlet");
			alert("portID ===>"+portInstanceId);
		}
	});
}

function <portlet:namespace/>cancelSortable(id){
	$("#"+id).detach().appendTo('#systemTool');
}

function <portlet:namespace/>actionCall(mode){
	if(mode=='<%=Constants.DELETE%>'){
		if(confirm(Liferay.Language.get('edison-appstore-delete-data-alert'))){
			<portlet:namespace/>frm.<portlet:namespace/>actionMode.value = mode;
		}else{
			return false;
		}
	}
	
	var templateId = $(":input:radio[name=templates]:checked").val();
	if(typeof templateId =='undefined'){
		alert("template을 선택 해 주세요");
		return false;
	}else{
		var Layout = new OSP.Layout();
		Layout.templateId(templateId);
		
		//System Default Portlet Set
		Layout.addPortlet('column-1','Simulation',true);
		Layout.addPortlet('column-2','SimulationJob',true);
		
		
		$( ".gridLayoutArea .sortable-list" ).each(function() {
			var columnId = $(this).attr("id");
			var sortedIDs = $(this).sortable("toArray");
			var portId = "";
			var portInstanceId = "";
			var currentPortlet = true;
			if(sortedIDs.length==0){
				Layout.addPortlet(columnId,portInstanceId,false,portId);
			}else{
				for(var i=0;i<sortedIDs.length;i++){
					portId = nullToStr(sortedIDs[i]);
					portInstanceId = nullToStr($(".sortable-list li[id='"+portId+"']").attr("data-port-portlet"));
					Layout.addPortlet(columnId,portInstanceId,currentPortlet,portId);
					currentPortlet = false;
				}
			}
			
		});
		
		$("#<portlet:namespace/>layout").val(JSON.stringify(Layout));
		$("#<portlet:namespace/>templetId").val(templateId);
		
	 	submitForm(<portlet:namespace/>frm);
	}

}

function <portlet:namespace/>drawPort(portType,data){
	if(data!=''){
		var targetUL,liStyle,array,dataPortPortlet;
		if(portType=='INPUT'){
			scienceApp.deserializeInputPorts(JSON.parse(data));
			targetUL = $(".gridLayoutArea ul[data-init-area*='input']");
			liStyle = "list-group-item-success";
			array = scienceApp.inputPortsArray();
			dataPortPortlet = OSP.Constants.DEFAULT_EDITOR;
		}else if(portType=='OUTPUT'){
			scienceApp.deserializeOutputPorts(JSON.parse(data));
			targetUL = $(".gridLayoutArea ul[data-init-area*='output']");
			liStyle = "list-group-item-danger";
			array = scienceApp.outputPortsArray();
			dataPortPortlet = OSP.Constants.DEFAULT_ANALYZER;
		}else if(portType=='LOG'){
			scienceApp.deserializeLogPorts(JSON.parse(data));
			targetUL = $(".gridLayoutArea ul[data-init-area*='log']");
			liStyle = "list-group-item-warning";
			array = scienceApp.logPortsArray();
			dataPortPortlet = OSP.Constants.DEFAULT_ANALYZER;
		}
		
		
		for(var i=0; i<array.length;i++){
			var data = array[i];
			$portLi = $("<li/>").addClass("sortable-item list-group-item "+liStyle).attr("id",data[OSP.Constants.NAME])
								.attr("data-port-portlet",data[dataPortPortlet])
								.append(
									$("<span/>").addClass("icon-move").text("   "+data[OSP.Constants.NAME])
								);
			targetUL.append($portLi);
		}
	}
}

function <portlet:namespace/>drawLayout(layout){
	var Layout = new OSP.Layout();
	Layout.deserialize(JSON.parse(layout));
	var columns = Layout.getColumnIds();
	for(var i=0;i<columns.length;i++){
		var column = Layout.getColumn(columns[i]);
		$targetUL = $(".gridLayoutArea ul[id='"+column.id()+"']");
		if(typeof $targetUL.attr("id") !='undefined'){
			for(var j=0; j<column.portlets().length;j++){
				var portlet = column.portlets()[j];
				
				var instanceId = portlet.instanceId();
				if(!portlet.portName()){
					$("#"+instanceId).detach().appendTo($targetUL);
				}else{
					var portName = portlet.portName();
					$portLi = $("<li/>").addClass("sortable-item list-group-item").attr("id",portName)
										.attr("data-port-portlet",instanceId)
										.append(
											$("<span/>").addClass("icon-move").text("   "+portName)
										);
					$targetUL.append($portLi);
				}
			}
		}
	}
}
</script>

<aui:form name="frm" method="POST" action="<%=submitURL%>">
	<aui:input name="layout" type="hidden" value="" label=""/>
	<aui:input name="templetId" type="hidden" value="" label=""/>
	<aui:input name="actionMode" value="${mode}" type="hidden"/>
</aui:form>

<div class="panel panel-default edison-panel">
	<div class="panel-heading clearfix">
		<h3 class="panel-title pull-left" style="padding-top: 0px;">
			<label class="checkbox-inline">
				<c:if test="${fn:indexOf(data.templateId, 'flow') > -1}"><c:set value="checked" var="layoutChecked"/></c:if>

				<input id="<portlet:namespace/>layoutAreaButton" type="checkbox" data-toggle="toggle" data-onstyle="success" data-offstyle="danger" data-on="Enabled" data-off="Disabled"  onchange="<portlet:namespace/>layoutAreaViewEvent();" ${layoutChecked}>
				<span style="font-weight: 600;"> Flow WorkBench</span>
				<liferay-ui:icon-help message="edison-science-appstore-toolkit-descriptive-message"/>
			</label>
		</h3>
		<div class="btn-group pull-right">
			<input class=" button02_2" onclick="<portlet:namespace/>goList();" value="<liferay-ui:message key='edison-button-board-list'/>" type="button">
			
			<c:if test="${data.status gt 1901003}">
				<input class=" button02_1" onclick="<portlet:namespace/>copyScienceApp();" value="<liferay-ui:message key='edison-appstore-copy'/>" type="button">
			</c:if>
			<c:if test="${appStatusButtonView}">
				<c:if test="${data.status eq '1901001'}">
					<input class=" button02_3" onclick="<portlet:namespace/>statusSubmit('1901002');" value="<liferay-ui:message key='edison-appstore-status-request'/>" type="button">
				</c:if>
				<c:if test="${data.status eq '1901002' && isAdmin}">
					<input class=" button02_3" onclick="<portlet:namespace/>statusSubmit('1901001');" value="<liferay-ui:message key='edison-appstore-status-denial'/>" type="button">
					<input class=" button02_3" onclick="<portlet:namespace/>statusSubmit('1901003');" value="<liferay-ui:message key='edison-appstore-status-private'/>" type="button">
					<input class=" button02_3" onclick="<portlet:namespace/>statusSubmit('1901004');" value="<liferay-ui:message key='edison-appstore-status-service'/>" type="button">
				</c:if>
				<c:if test="${data.status eq '1901003'}">
					<input class=" button02_3" onclick="<portlet:namespace/>statusSubmit('1901004');" value="<liferay-ui:message key='edison-appstore-status-service'/>" type="button">
				</c:if>
				
				<c:if test="${data.status eq '1901004'}">
					<input class=" button02_3" onclick="<portlet:namespace/>statusSubmit('1901003');" value="<liferay-ui:message key='edison-appstore-status-private'/>" type="button">
				</c:if>
			</c:if>
			
			<input class=" button02_1" onclick="<portlet:namespace/>actionCall('<%=Constants.ADD%>');return false;" value="<liferay-ui:message key='edison-button-save'/>" type="button">
			
			<c:if test="${ownerThan}">
				<input class=" button02_1" onclick="<portlet:namespace/>actionCall('<%=Constants.DELETE%>');return false;" value="<liferay-ui:message key='delete'/>" type="button">
			</c:if>
		</div>
	</div>
	
	<div class="panel-body layout-wrap">
		<div class="btn-group layoutBtnGroup btn-group-justified" data-toggle="buttons" id="<portlet:namespace/>noFlowLayoutArea">
			<label class="btn layoutMethod">
				<div class="method layout-1"></div>
				<input type="radio" name="templates" value="2-row-2-2-column"> 
			</label>
			<label class="btn layoutMethod">
				<div class="method layout-2"></div>
				<input type="radio" name="templates" value="2-row-1-1-column"> 
			</label>
			<label class="btn layoutMethod">
				<div class="method layout-3"></div>
				<input type="radio" name="templates" value="1-row-2-column"> 
			</label>
			<label class="btn layoutMethod">
				<div class="method layout-4"></div>
				<input type="radio" name="templates" value="2-row-2-1-column"> 
			</label>
			<label class="btn layoutMethod">
				<div class="method layout-5"></div>
				<input type="radio" name="templates" value="2-row-1-2-column"> 
			</label>
		</div>
		
		<div class="btn-group layoutBtnGroup btn-group-justified" data-toggle="buttons" id="<portlet:namespace/>flowLayoutArea" style="display: none;">
			<label class="btn layoutMethod">
				<div class="method layout-6"></div>
				<input type="radio" name="templates" value="flow-1-row-2-column"> 
			</label>
			<label class="btn layoutMethod">
				<div class="method layout-7"></div>
				<input type="radio" name="templates" value="flow-2-row-2-1-column"> 
			</label>
			<label class="btn layoutMethod">
				<div class="method layout-5"></div>
				<input type="radio" name="templates" value="flow-2-row-1-2-column"> 
			</label>
		</div>
	</div>
	<c:if test="${!empty data.templateId}">
		<div class="panel-footer">
			<div class="row">
				<div class="panel-group col-md-4" id="accordion" style="margin-top: 15px;">
			        <div class="panel panel-default">
			            <div class="panel-heading">
			                <h4 class="panel-title">
			                    <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">System Tool <liferay-ui:icon-help message="edison-science-appstore-toolkit-descriptive-message"/></a>
			                </h4>
			            </div>
			            <div id="collapseOne" class="panel-collapse collapse in">
			                <div class="panel-body sortableLayout">
								<ul class="sortable-list ui-sortable list-group" id="systemTool">
									<li class="sortable-item list-group-item list-group-item-info" id="system-1">
										<span class="icon-move"> App Log Viewer   <liferay-ui:icon-help message="edison-science-appstore-toolkit-descriptive-message"/> 
											<i class="icon-remove sortRemove" onClick="<portlet:namespace/>cancelSortable('system-1');"></i>
										</span>
									</li>
									<li class="sortable-item list-group-item list-group-item-info" id="system-2">
										<span class="icon-move"> Result File Download   <liferay-ui:icon-help message="edison-science-appstore-toolkit-descriptive-message"/>
											<i class="icon-remove sortRemove" onClick="<portlet:namespace/>cancelSortable('system-2');"></i>
										</span>
									</li>
								</ul>
			                </div>
			            </div>
			        </div>
			    </div>
			    <div class="col-md-8">
			    	<liferay-util:include page='<%= "/WEB-INF/html/appmanager/layout/" + templateJSP + ".jsp"%>' servletContext="<%=this.getServletContext() %>" >
					</liferay-util:include>
			    </div>
		    </div>
		</div>
	</c:if>
</div>
<button class="btn btn-default" type="button" onClick="<portlet:namespace/>layoutResult();"><span class="icon-user"> Result</span></button>

