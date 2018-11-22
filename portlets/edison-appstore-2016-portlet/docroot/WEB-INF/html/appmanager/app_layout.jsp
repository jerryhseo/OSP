<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util"%>

<%@ page import="com.liferay.portal.kernel.util.Constants"%>
<%@ page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@ page import="com.liferay.portal.kernel.util.HttpUtil"%>

<link href="${contextPath}/css/bootstrap-toggle.min.css" rel="stylesheet">
<script src="${contextPath}/js/bootstrap-toggle.min.js"></script>

<portlet:actionURL var="submitURL" copyCurrentRenderParameters="<%=false%>" name="appAction">
	<portlet:param name="clickTab" value="${clickTab}" />
	<portlet:param name="actionType" value="appLayout" />
	<portlet:param name="isPort" value="${isPort}" />
	<portlet:param name="scienceAppId" value="${scienceAppId}" />

	<portlet:param name="searchValue" value="${searchValue}" />
	<portlet:param name="searchOption" value="${searchOption}" />
	<portlet:param name="searchStatus" value="${searchStatus}" />
	<portlet:param name="p_curPage" value="${p_curPage}" />
	<portlet:param name="redirectURL" value="${redirectURL}" />
</portlet:actionURL>

<liferay-portlet:renderURL var="appLayoutRenderURL" copyCurrentRenderParameters="<%=true%>" windowState="<%=LiferayWindowState.MAXIMIZED.toString()%>">
	<portlet:param name="isStepLayout" value="${isStepLayout}" />
</liferay-portlet:renderURL>

<%
	String layoutURL = HttpUtil.removeParameter(appLayoutRenderURL,renderResponse.getNamespace() + "templateId");
	String templateJSP = GetterUtil.get(request.getAttribute("templateJSP"), "");
%>
<style type="text/css">
.science-app-manager-portlet .panel-success>.panel-heading {
	background-color: #dff0d8;
}

.science-app-manager-portlet .panel-success>.panel-heading a:after {
	content:
		"<liferay-ui:message key='edison-science-appstore-toolkit-input-port' />";
}

.science-app-manager-portlet .panel-warning>.panel-heading {
	background-color: #fcf8e3;
}

.science-app-manager-portlet .panel-warning>.panel-heading a:after {
	content:
		"<liferay-ui:message key='edison-science-appstore-toolkit-log-port' />";
}

.science-app-manager-portlet .panel-danger>.panel-heading {
	background-color: #f2dede;
}

.science-app-manager-portlet .panel-danger>.panel-heading a:after {
	content:
		"<liferay-ui:message key='edison-science-appstore-toolkit-out-port' />";
}

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

.science-app-manager-portlet .layout-wrap .layoutBtnGroup .active .method
	{
	border-color: #4cd264;
	outline: none !important;
}

.science-app-manager-portlet .layout-wrap .layoutBtnGroup .layoutMethod
	{
	padding: 40px;
	position: relative;
}

.science-app-manager-portlet .layout-wrap .layoutBtnGroup .layoutMethod .method
	{
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

.science-app-manager-portlet .layout-wrap .layoutBtnGroup .method.layout-1
	{
	background-image:
		url(/edison-appstore-2016-portlet/images/appmanager/layout/layout01.png);
	background-size: 50px;
}

.science-app-manager-portlet .layout-wrap .layoutBtnGroup .method.layout-1-type-2
	{
	background-image:
		url(/edison-appstore-2016-portlet/images/appmanager/layout/layout01-type-02.png);
	background-size: 50px;
}

.science-app-manager-portlet .layout-wrap .layoutBtnGroup .method.layout-2
	{
	background-image:
		url(/edison-appstore-2016-portlet/images/appmanager/layout/layout02.png);
	background-size: 50px;
}

.science-app-manager-portlet .layout-wrap .layoutBtnGroup .method.layout-3
	{
	background-image:
		url(/edison-appstore-2016-portlet/images/appmanager/layout/layout03.png);
	background-size: 50px;
}

.science-app-manager-portlet .layout-wrap .layoutBtnGroup .method.layout-4
	{
	background-image:
		url(/edison-appstore-2016-portlet/images/appmanager/layout/layout04.png);
	background-size: 50px;
}

.science-app-manager-portlet .layout-wrap .layoutBtnGroup .method.layout-5
	{
	background-image:
		url(/edison-appstore-2016-portlet/images/appmanager/layout/layout05.png);
	background-size: 50px;
}

.science-app-manager-portlet .layout-wrap .layoutBtnGroup .method.layout-6
	{
	background-image:
		url(/edison-appstore-2016-portlet/images/appmanager/layout/layout06.png);
	background-size: 50px;
}

.science-app-manager-portlet .layout-wrap .layoutBtnGroup .method.layout-7
	{
	background-image:
		url(/edison-appstore-2016-portlet/images/appmanager/layout/layout07.png);
	background-size: 50px;
}

.science-app-manager-portlet .layout-wrap .layoutBtnGroup .method.layout-8
	{
	background-image:
		url(/edison-appstore-2016-portlet/images/appmanager/layout/layout08.png);
	background-size: 50px;
}

.science-app-manager-portlet .layout-wrap .layoutBtnGroup .method.layout-9
	{
	background-image:
		url(/edison-appstore-2016-portlet/images/appmanager/layout/layout09.png);
	background-size: 50px;
}

.science-app-manager-portlet .layout-wrap .layoutBtnGroup .method:hover
	{
	border: solid 3px #ffffff;
	outline: none !important;
}

/*aui css error*/
.science-app-manager-portlet .collapse {
	display: none;
}

.science-app-manager-portlet .sortableLayout .row {
	/* 		margin-right: 0px; */
	position: relative;
}

.science-app-manager-portlet .gridLayoutArea {
	height: 400px;
}

.science-app-manager-portlet .gridLayoutArea div {
	/* 		height: inherit; */
	
}

.science-app-manager-portlet .gridLayoutArea ul.sortable-list {
	min-height: 100px;
	padding: 0px;
	margin: 20px 0px;
}

.science-app-manager-portlet .sortableLayout.portLayoutArea ul.sortable-list li.sortable-item
	{
	background-color: #ffffff;
}

.science-app-manager-portlet .sortableLayout ul.sortable-list li.sortable-item
	{
	border: solid 1px #cccccc;
	cursor: move;
	margin: 5px 0;
}

.science-app-manager-portlet .sortableLayout ul.sortable-list li.sortable-item .sortRemove
	{
	display: none;
	cursor: pointer;
	float: right;
}

.science-app-manager-portlet .gridLayoutArea ul.sortable-list li.sortable-item .sortRemove
	{
	display: block;
}

.science-app-manager-portlet .gridLayoutArea ul.sortable-list li.list-group-item-success:after
	{
	content:
		"                <liferay-ui:message key='edison-science-appstore-toolkit-input-port' />";
}

.science-app-manager-portlet .gridLayoutArea ul.sortable-list li.list-group-item-warning:after
	{
	content:
		"                <liferay-ui:message key='edison-science-appstore-toolkit-log-port' />";
}

.science-app-manager-portlet .gridLayoutArea ul.sortable-list li.list-group-item-danger:after
	{
	content:
		"                <liferay-ui:message key='edison-science-appstore-toolkit-out-port' />";
}

.science-app-manager-portlet .gridLayoutArea ul.sortable-list li.list-group-item-default.INPUT:after
	{
	content:
		"                <liferay-ui:message key='edison-science-appstore-toolkit-input-port' />";
}

.science-app-manager-portlet .gridLayoutArea ul.sortable-list li.list-group-item-default.LOG:after
	{
	content:
		"                <liferay-ui:message key='edison-science-appstore-toolkit-log-port' />";
}

.science-app-manager-portlet .gridLayoutArea ul.sortable-list li.list-group-item-default.OUTPUT:after
	{
	content:
		"                <liferay-ui:message key='edison-science-appstore-toolkit-out-port' />";
}

.science-app-manager-portlet .sortableLayout .row .col {
	border: solid 2px #fafafa;
	background-color: #D2EBEE;
}

.science-app-manager-portlet .ui-state-highlight {
	height: 44px;
	line-height: 10px;
}

.science-app-manager-portlet .taglib-icon-help {
	cursor: pointer;
}

.science-app-manager-portlet .gridLayoutArea .devider {
	position: absolute;
	z-index: 3;
	width: 40px;
	height: 40px;
	cursor: move;
}

.science-app-manager-portlet .gridLayoutArea .vertical {
	top: 50%;
	left: 50%;
	margin-top: -20px;
	margin-left: -20px;
	background:
		url(/edison-appstore-2016-portlet/images/appmanager/layout/arrow_lr.png)
		center no-repeat;
}

.science-app-manager-portlet .gridLayoutArea .horizontal {
	left: 50%;
	margin-top: -20px;
	margin-left: -20px;
	background:
		url(/edison-appstore-2016-portlet/images/appmanager/layout/arrow_tb.png)
		center no-repeat;
}

.science-app-manager-portlet .gridLayoutArea .moving {
	background-color: black !important;
}

.science-app-manager-portlet .gridLayoutArea .moving h1 {
	color: white;
	text-align: center;
	position: relative;
	top: 30%;
	font-size: 50px;
	display: block;
}

.science-app-manager-portlet .gridLayoutArea h1 {
	display: none;
}

.science-app-manager-portlet .gridLayoutArea .moving ul,
	.science-app-manager-portlet .gridLayoutArea .moving div {
	display: none;
}

.science-app-manager-portlet div.edison-panel ul.custom-tabs {
	margin-bottom: 5px;
}

.science-app-manager-portlet div.edison-panel ul.custom-tabs li a {
	padding-top: 10px;
	padding-bottom: 10px;
}
.science-app-manager-portlet div.edison-panel ul.custom-tabs li.isNULL a {
	color: #ff00009c;
}
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
			var isStepLayout = $("#<portlet:namespace/>layoutAreaButton").prop("checked");
			var searchParameter = "&<portlet:namespace/>templateId="+$(this).val();
			var renewURL = <portlet:namespace/>updateURLParameter("<%=layoutURL%>", "isStepLayout", isStepLayout);
			location.href=renewURL+searchParameter;
		}
	});
	
	
	/**
	* Layout Scroll
	* vertical Left 기준 Width check -> data-equal-id="LEFT DIV" data-remainder-id="RIGHT DIV"
	* horizontal top 기준 Width check -> data-equal-id="BOTTOM DIV" data-remainder-id="TOP DIV"
	*
	* set-equal-ids = 마우스 드래그에 따른 동일한 값의 Width, Height 객체를 Setting 하는 id들 ,구분자 |
	* set-remainder-ids = 마우스 드래그에 따른 나머지 값의 Width, Height 객체를 Setting 하는 id들 ,구분자 |
	**/
	
	var isDevider = false;
	var object,container,equalDiv,remainderDiv,moveType,setEqualText,setRemainderText;
	$(".devider").mousedown(function(e) {
		isDevider = true;
		object = e.currentTarget;
		container = $(this).parent();
		equalDiv = $("#"+$(this).attr("data-equal-id"));
		remainderDiv = $("#"+$(this).attr("data-remainder-id"));
		moveType = $(this).hasClass("vertical") ? "vertical" : "horizontal"
		
		if(moveType =="vertical"){
			equalDiv.addClass("moving");
			remainderDiv.addClass("moving");
		}else{
			$(equalDiv).find("div.sub-col").addClass("moving");
			$(remainderDiv).find("div.sub-col").addClass("moving");
		}
		
		setEqualText = $(this).attr('set-equal-ids');
		setRemainderText = $(this).attr('set-remainder-ids');
		<portlet:namespace/>layoutAreaMovingEvent(moveType);
	});
	
	
	$("body").mouseup(function(a) {
		if(isDevider){
			isDevider = false;
			if(moveType =="vertical"){
				equalDiv.removeClass("moving");
				remainderDiv.removeClass("moving");
				
				var equalPerText = $(equalDiv).attr("width-per");
				var remainderPerText = $(remainderDiv).attr("width-per");
				<portlet:namespace/>layoutAreaSetColData(setEqualText,"width-per",equalPerText);
				<portlet:namespace/>layoutAreaSetColData(setRemainderText,"width-per",remainderPerText);
				
			}else{
				$(equalDiv).find("div.sub-col").removeClass("moving");
				$(remainderDiv).find("div.sub-col").removeClass("moving");
				
				var equalPerText = $(equalDiv).find("div.sub-col").attr("height-per");
				var remainderPerText = $(remainderDiv).find("div.sub-col").attr("height-per");
				<portlet:namespace/>layoutAreaSetColData(setEqualText,"height-per",equalPerText);
				<portlet:namespace/>layoutAreaSetColData(setRemainderText,"height-per",remainderPerText);
			}
		}
	});
	
	$("body").mousemove(function(e) {
		if(isDevider){
			if(moveType =="vertical"){
				var offsetRight = container.width() - (e.clientX - container.offset().left);
				var moveLeft = Math.round(offsetRight / container.width() * 100);
				
				var objectLeftPositon = 100-moveLeft;
				if(objectLeftPositon<20){
					objectLeftPositon = 20;
				}else if(objectLeftPositon>80){
					objectLeftPositon = 80;
				}
				var objectRightPositon = 100-objectLeftPositon;
				
				$(object).css("left",objectLeftPositon+"%");
				$(object).attr("left-per",objectLeftPositon+"%");
				$(equalDiv).css("width",objectLeftPositon+"%");
				$(remainderDiv).css("width",objectRightPositon+"%");
				
				$(equalDiv).attr("width-per",objectLeftPositon+"%");
				$(remainderDiv).attr("width-per",objectRightPositon+"%");
			}else{
				var offsetTop = e.pageY-container.offset().top;
				var moveTop = Math.round(offsetTop / container.height() * 100);
				
				var objectBottomPositon = 100-moveTop;
				if(objectBottomPositon<20){
					objectBottomPositon = 20;
				}else if(objectBottomPositon>80){
					objectBottomPositon = 80;
				}
				var objectTopPositon = 100-objectBottomPositon;
				
				$(equalDiv).css("height",objectTopPositon+"%");
				$(remainderDiv).css("height",objectBottomPositon+"%");
				$(object).css("top",objectTopPositon+"%");
				$(object).attr("top-per",objectTopPositon+"%");
				
				$(equalDiv).find("div.sub-col").attr("height-per",objectTopPositon+"%");
				$(remainderDiv).find("div.sub-col").attr("height-per",objectBottomPositon+"%");
			}
			
			<portlet:namespace/>layoutAreaMovingEvent(moveType);
			
		}
	});
});

function <portlet:namespace/>updateURLParameter(url, param, paramVal){
	var param  = "<portlet:namespace/>"+param;
	var newAdditionalURL = "";
	var tempArray = url.split("?");
	var baseURL = tempArray[0];
	var additionalURL = tempArray[1];
	var temp = "";
	if (additionalURL) {
		tempArray = additionalURL.split("&");
		for (var i=0; i<tempArray.length; i++){
			if(tempArray[i].split('=')[0] != param){
				newAdditionalURL += temp + tempArray[i];
				temp = "&";
			}
		}
	}

	var rows_txt = temp + "" + param + "=" + paramVal;
	return baseURL + "?" + newAdditionalURL + rows_txt;
}


function <portlet:namespace/>tabChange(tab){
	var isStepLayout = $("#<portlet:namespace/>layoutAreaButton").prop("checked");
	
	var renewURL = "<%=layoutURL%>";
	var renewURL1 = <portlet:namespace/>updateURLParameter(renewURL, "stepTabsValue", tab);
	var renewURL2 = <portlet:namespace/>updateURLParameter(renewURL1, "isStepLayout", isStepLayout);
	location.href=renewURL2;
}

function <portlet:namespace/>layoutAreaSetColData(targetTextIds,attribute,percent){
	var targetIds = targetTextIds.split('|');
	for(var i=0;i<targetIds.length;i++){
		var targetId = targetIds[i];
		$("#"+targetId).attr(attribute,percent);
	}
}

function <portlet:namespace/>layoutAreaMovingEvent(moveType){
	var findText = '';
	if(moveType=='vertical'){
		findText = 'width-per';
	}else{
		findText = 'height-per';
	}
	
	$(".science-app-manager-portlet div.moving").each(function(){
		var text = $(this).find('h1');
		if(text.length==0){
			text = $('<h1></h1>').appendTo($(this));
		}
// 		var width = Math.floor(( 100 * parseFloat($(this).css('width')) / parseFloat($(this).parent().css('width')))) + '%';
		var hText = $(this).attr(findText);
		text.html(hText);
	});
}


var scienceApp = new OSP.ScienceApp();
function <portlet:namespace/>layoutAreaViewInit(){
	if('${isStepLayout}' == 'true'){
		$("#<portlet:namespace/>layoutAreaButton").bootstrapToggle('on');
	}else{
		$("#<portlet:namespace/>layoutAreaButton").bootstrapToggle('off');
	}
	
	if('${data.templateId}'!=""){
		$radioObject = $(".layoutMethod>input[name=templates][value="+'${data.templateId}'+"]");
		$radioObject.prop('checked', true);
		$radioObject.parent().addClass("active");
		
		if('${data.isSamplePortPrint}'=='true'){
			if('${isStepLayout}'=='true'){
				if('${stepTabsValue}'=='INPUT'){
					<portlet:namespace/>drawPortFromLayout('INPUT','${data.inputPorts}');
				}else if('${stepTabsValue}'=='LOG'){
					<portlet:namespace/>drawPortFromLayout('LOG','${data.logPorts}');
				}else if('${stepTabsValue}'=='OUTPUT'){
					<portlet:namespace/>drawPortFromLayout('OUTPUT','${data.outputPorts}');
				}
			}else{
				<portlet:namespace/>drawPortFromLayout('INPUT','${data.inputPorts}');
				<portlet:namespace/>drawPortFromLayout('LOG','${data.logPorts}');
				<portlet:namespace/>drawPortFromLayout('OUTPUT','${data.outputPorts}');
			}
		}else{
			<portlet:namespace/>drawLayout('${data.layout}');
		}
		
		//draw 할 port가 없을 경우 Setting
		var portAreaDisplayNone = true;
		$("#<portlet:namespace/>portCol > .panel").each(function(i,e){
			if($(this).css("display")!="none"){
				portAreaDisplayNone = false;
				return false;
			}
		});
		
		if(portAreaDisplayNone){
			$("#<portlet:namespace/>portCol").css("display","none");
			$("#<portlet:namespace/>layoutCol").attr("class","col-md-12");
		}
	}
}

function <portlet:namespace/>layoutAreaViewEvent(){
	var layoutAeaButtonStatus = $("#<portlet:namespace/>layoutAreaButton").prop("checked");
	if(layoutAeaButtonStatus){
		$("#<portlet:namespace/>layoutTabs").css("display","block");
		$("#<portlet:namespace/>saveBtn").css("display","none");
	}else{
		$("#<portlet:namespace/>layoutTabs").css("display","none");
		$("#<portlet:namespace/>saveBtn").css("display","inline-block");
	}
	
	/*화면에서 Flow WorkBench를 변경 하였을 경우 Grid 영역을 숨김*/
	if(layoutAeaButtonStatus.toString()!='${isStepLayout}'){
		$("#<portlet:namespace/>layoutGrid").css("display","none");
	}else{
		$("#<portlet:namespace/>layoutGrid").css("display","block");
	}
}

function <portlet:namespace/>cancelSortable(target,id){
	$("#"+id).detach().appendTo('#'+target);
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
		alert(Liferay.Language.get('edison-this-field-is-required',['template']));
		return false;
	}else{
		/*template 작성 여부 체크*/
		if($("div#<portlet:namespace/>layoutGrid").css('display')=='none'){
			alert(Liferay.Language.get('edison-science-appstore-toolkit-template-write-error-message'));
			return false;
		}
		
		var Layouts;
		var isStepLayout = $("#<portlet:namespace/>layoutAreaButton").prop("checked");
		
		/*현재 DB의 Layout 타입과 작성중인 Layout 타입(Flow Workbench 여부)가 변경이 되었을 경우 새로 작성*/
		if(isStepLayout&&'${data.layouts}'!=""){
			Layouts = new OSP.Layouts(JSON.parse('${data.layouts}'));
		}else{
			Layouts = new OSP.Layouts();
		}
		
		Layouts.isStepLayout(isStepLayout);
		var Layout = Layouts.newLayout();
		var templateId = $(":input:radio[name=templates]:checked").val();
		Layout.templateId(templateId);
		
		//System Default Portlet Set
		Layout.addPortlet('column-1','SimulationDashboard_WAR_edisonsimulationportlet',true);
		Layout.addPortlet('column-2','SimulationJobController_WAR_edisonsimulationportlet',true);
		
		
		$( ".gridLayoutArea .sortable-list" ).each(function() {
			var columnId = $(this).attr("id");
			var sortedIDs = $(this).sortable("toArray");
			var portInstanceId = "";
			var currentPortlet = true;
			
			/*Add Column-Height,width*/
			var columnHeight = nullToStr($(this).attr("height-per"));
			var columnWidth = nullToStr($(this).attr("width-per"));
			
			if(sortedIDs.length!=0){
				for(var i=0;i<sortedIDs.length;i++){
					$liObject = $(".sortable-list li[id='"+sortedIDs[i]+"']");
					var portName = $liObject.attr("data-port-name");
					var portInstanceId = $liObject.attr("data-port-portlet");
	// 				alert("columnId==>"+columnId+"___portInstanceId==>"+portInstanceId+"__portName==>"+portName+"__currentPortlet==>"+currentPortlet);
					Layout.addPortlet(columnId,portInstanceId,currentPortlet,portName,'',columnHeight,columnWidth);
					currentPortlet = false;
				}
			}else{
				Layout.addPortlet(columnId,'','','','',columnHeight,columnWidth);
			}
		});
		
		
		/*devider 정보 Layout Setting - GPLUS - 20181004*/
		$(".devider").each(function(){
			Layout.addDeviderObject($(this).attr('id'),$(this).attr('left-per'),$(this).attr('top-per'));
		});
		
		if(isStepLayout){
			Layouts.addLayout('${stepTabsValue}',Layout);
		}else{
			Layouts.addLayout(OSP.Enumeration.LayoutKey.LAYOUT,Layout);
		}

		$("#<portlet:namespace/>layout").val(JSON.stringify(Layouts));
		$("#<portlet:namespace/>isStepLayout").val(isStepLayout);
// 		console.log(JSON.stringify(Layouts));
	 	submitForm(<portlet:namespace/>frm);

	}

}

function <portlet:namespace/>drawPortFromLayout(portType,data){
	if(data!=''){
		var targetUL,array,dataPortPortlet;
		if(portType=='INPUT'){
			scienceApp.deserializeInputPorts(JSON.parse(data));
			targetUL = $(".gridLayoutArea ul[data-init-area*='input']");
			array = scienceApp.inputPortsArray();
			dataPortPortlet = OSP.Constants.DEFAULT_EDITOR;
		}else if(portType=='OUTPUT'){
			scienceApp.deserializeOutputPorts(JSON.parse(data));
			targetUL = $(".gridLayoutArea ul[data-init-area*='output']");
			array = scienceApp.outputPortsArray();
			dataPortPortlet = OSP.Constants.DEFAULT_ANALYZER;
		}else if(portType=='LOG'){
			scienceApp.deserializeLogPorts(JSON.parse(data));
			targetUL = $(".gridLayoutArea ul[data-init-area*='log']");
			array = scienceApp.logPortsArray();
			dataPortPortlet = OSP.Constants.DEFAULT_ANALYZER;
		}
		
		for(var i=0; i<array.length;i++){
			var data = array[i];
			$("#"+data[OSP.Constants.NAME]+"_"+data[dataPortPortlet]).detach().appendTo(targetUL);
		}
	}
}

function <portlet:namespace/>drawLayout(layouts){
	var Layouts = new OSP.Layouts(JSON.parse(layouts));
	var Layout = Layouts.getLayoutFromKey('${stepTabsValue}');
	var columns = Layout.getColumnIds();
	
	for(var i=0;i<columns.length;i++){
		var column = Layout.getColumn(columns[i]);
		
		if(column.width()){
			var movingDiv = $("div#col-"+column.id());
			movingDiv.css('width',column.width());
			
			$("#"+column.id()).attr("width-per",column.width());
		}
	
		if(column.height()){
			var movingDiv = $("div#row-"+column.id());
			movingDiv.css('height',column.height());
			
			$("#"+column.id()).attr("height-per",column.height());
		}
		
		$targetUL = $(".gridLayoutArea ul[id='"+column.id()+"']");
		if(typeof $targetUL.attr("id") !='undefined'){
			for(var j=0; j<column.portlets().length;j++){
				var portlet = column.portlets()[j];
				
				var portName = portlet.portName();
				var instanceId = <portlet:namespace/>destroyInstanceId(portlet.instanceId());
				var liObjectId = portName+"_"+instanceId;
				$("#"+liObjectId).detach().appendTo($targetUL);
			}
		}
	}
	
	<portlet:namespace/>moveDeviderPosition(Layout);
}

/*Layout 데이터 width,height 값에 따른 devider Position 값 변경*/
function <portlet:namespace/>moveDeviderPosition(Layout){
	var deviders = Layout.getDeviderIds();
	for(var i=0;i<deviders.length;i++){
		var devider = Layout.getDevider(deviders[i]);
		
		var deviderObj = $("div#"+devider.id());
		if(devider.left()){
			deviderObj.css('left',devider.left());
			deviderObj.attr('left-per',devider.left());
		}
		
		if(devider.top()){
			deviderObj.css('top',devider.top());
			deviderObj.css('top-per',devider.top());
		}
		
	}
}


function <portlet:namespace/>destroyInstanceId(instanceId){
	var instanceIndex = instanceId.indexOf('_INSTANCE_');
	if(instanceIndex > -1){
		return instanceId.substring(0,instanceIndex);
	}else{
		return instanceId;
	}
}
</script>

<aui:form name="frm" method="POST" action="<%=submitURL%>">
	<aui:input name="layout" type="hidden" value="" label="" />
	<aui:input name="isStepLayout" type="hidden" value="" label="" />
	<aui:input name="stepTabsValue" type="hidden" value="${stepTabsValue}" label="" />
	<aui:input name="actionMode" value="${mode}" type="hidden" />
</aui:form>

<div class="panel panel-default edison-panel">
	<div class="panel-heading clearfix">
		<h3 class="panel-title pull-left" style="padding-top: 0px;">
			<label class="checkbox-inline"> 
				<input id="<portlet:namespace/>layoutAreaButton" type="checkbox" data-toggle="toggle" data-onstyle="success" data-offstyle="danger" data-on="Enabled" data-off="Disabled" onchange="<portlet:namespace/>layoutAreaViewEvent();"> <span style="font-weight: 600;">Flow WorkBench</span>
				<liferay-ui:icon-help message="edison-science-appstore-toolkit-flow-message" />
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

			<input class=" button02_1" id="<portlet:namespace/>saveBtn" onclick="<portlet:namespace/>actionCall('<%=Constants.ADD%>');return false;" value="<liferay-ui:message key='edison-button-save'/>" type="button">

			<c:if test="${ownerThan}">
				<input class=" button02_1" onclick="<portlet:namespace/>actionCall('<%=Constants.DELETE%>');return false;" value="<liferay-ui:message key='delete'/>" type="button">
				<c:if test="${workBenchPlid ne 0 && appTestButtonView}">
					<input class=" button02_1" onclick="<portlet:namespace/>appTest();return false;" value="<liferay-ui:message key='edison-table-list-header-run'/>" type="button">
				</c:if>
			</c:if>
		</div>
	</div>
	
	<div class="panel-body layout-wrap">
		<ul class="nav nav-tabs custom-tabs" id="<portlet:namespace/>layoutTabs" style="display: none;">
			<c:forEach var="tab" items="${tabList}">
				<c:choose>
					<c:when test="${tab.name eq stepTabsValue}">
						<li class="active ${tab.className}"><a href="javascript:void(0);">${tab.name}</a></li>
					</c:when>
					<c:otherwise>
						<li class="${tab.className}"><a href="#" onclick="<portlet:namespace/>tabChange('${tab.name}');">${tab.name}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<li class="pull-right">
				<button class="btn btn-primary" onclick="<portlet:namespace/>actionCall('<%=Constants.ADD%>');return false;"><liferay-ui:message key='edison-button-save'/></button>
			</li>
		</ul>


		<div class="btn-group layoutBtnGroup btn-group-justified" data-toggle="buttons" id="<portlet:namespace/>layoutArea">
			<label class="btn layoutMethod">
				<div class="method layout-3"></div> <input type="radio" name="templates" value="1-row-2-column">
			</label> <label class="btn layoutMethod">
				<div class="method layout-1"></div> <input type="radio" name="templates" value="2-row-2-2-column">
			</label> <label class="btn layoutMethod">
				<div class="method layout-1-type-2"></div> <input type="radio" name="templates" value="2-row-2-2-column-type-2">
			</label> <label class="btn layoutMethod">
				<div class="method layout-2"></div> <input type="radio" name="templates" value="2-row-1-1-column">
			</label> <label class="btn layoutMethod">
				<div class="method layout-4"></div> <input type="radio" name="templates" value="2-row-2-1-column">
			</label> <label class="btn layoutMethod">
				<div class="method layout-5"></div> <input type="radio" name="templates" value="2-row-1-2-column">
			</label> <label class="btn layoutMethod">
				<div class="method layout-7"></div> <input type="radio" name="templates" value="1-1-row-2-1-column">
			</label> <label class="btn layoutMethod">
				<div class="method layout-9"></div> <input type="radio" name="templates" value="1-1-row-1-2-column">
			</label>
		</div>
	</div>
	<c:if test="${!empty data.templateId}">
		<div class="panel-footer" id="<portlet:namespace/>layoutGrid">
			<div class="row" style="margin: 0px;">
				<div class="panel-group col-md-4" id="<portlet:namespace/>portCol" style="margin-top: 15px;">
					<c:if test="${!empty data.portList}">
						<c:set var="panelCss" value="panel panel-defalut"></c:set>
						<c:set var="liCss" value="list-group-item-default"></c:set>
						<c:forEach items="${data.portList}" var="portMap" varStatus="status">
							<c:set var="panelStyle" value="display:none;"></c:set>
							<c:choose>
								<c:when test="${isStepLayout eq 'true'}">
									<c:if test="${portMap.portType eq stepTabsValue && fn:length(portMap.appList) gt 1}">
										<c:set var="panelStyle" value="display:block;"></c:set>
									</c:if>
								</c:when>
								<c:otherwise>
									<c:if test="${fn:length(portMap.appList) gt 1}">
										<c:set var="panelStyle" value="display:block;"></c:set>
									</c:if>
								</c:otherwise>
							</c:choose>
	
	
							<c:if test="${portMap.portType eq 'INPUT' }">
								<c:set var="panelCss" value="panel  panel-success"></c:set>
								<c:set var="liCss" value="list-group-item-success"></c:set>
							</c:if>
							<c:if test="${portMap.portType eq 'LOG' }">
								<c:set var="panelCss" value="panel  panel-warning "></c:set>
								<c:set var="liCss" value="list-group-item-warning "></c:set>
							</c:if>
							<c:if test="${portMap.portType eq 'OUTPUT' }">
								<c:set var="panelCss" value="panel  panel-danger  "></c:set>
								<c:set var="liCss" value="list-group-item-danger  "></c:set>
							</c:if>
	
							<div class="${panelCss}" style="${panelStyle}">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a data-toggle="collapse" data-parent="#accordion" href="#collapse_${status.index}"> ${portMap.portName} </a>
									</h4>
								</div>
								<div id="collapse_${status.index}" class="panel-collapse collapse">
									<div class="panel-body sortableLayout portLayoutArea">
										<ul class="sortable-list ui-sortable list-group" id="port_${portMap.portName}_ul">
											<c:forEach items="${portMap.appList}" var="portAppData">
												<c:if test="${portAppData.type eq 'Editor' }">
													<c:set var="icon" value="icon-edit"></c:set>
												</c:if>
												<c:if test="${portAppData.type eq 'Analyzer' }">
													<c:set var="icon" value="icon-picture"></c:set>
												</c:if>
	
												<c:choose>
													<c:when test="${portAppData.isDefault}">
														<li class="sortable-item list-group-item list-group-item-default ${portMap.portType}" id="${portMap.portName}_${portAppData.exeFileName}" data-port-portlet="${portAppData.exeFileName}" data-port-name="${portMap.portName}"><span class="icon-move"> <i class="${icon}"></i> ${portMap.portName}_DEFAULT <liferay-ui:icon-help message="${portAppData.title}" />
														</span></li>
													</c:when>
													<c:otherwise>
														<li class="sortable-item list-group-item ${liCss}" id="${portMap.portName}_${portAppData.exeFileName}" data-port-portlet="${portAppData.exeFileName}" data-port-name="${portMap.portName}"><span class="icon-move"> <i class="${icon}"></i> ${portMap.portName}_${portAppData.name} <liferay-ui:icon-help message="${portAppData.title}" /> <i class="icon-remove sortRemove" onClick="<portlet:namespace/>cancelSortable('port_${portMap.portName}_ul','${portMap.portName}_${portAppData.exeFileName}');"></i>
														</span></li>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</ul>
									</div>
								</div>
							</div>
						</c:forEach>
					</c:if>
				</div>
				<div class="col-md-8" id="<portlet:namespace/>layoutCol">
					<liferay-util:include page='<%="/WEB-INF/html/appmanager/layout/" + templateJSP + ".jsp"%>' servletContext="<%=this.getServletContext()%>">
					</liferay-util:include>
				</div>
			</div>
		</div>
	</c:if>
</div>

