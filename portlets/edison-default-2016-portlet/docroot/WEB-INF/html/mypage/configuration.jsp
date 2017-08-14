<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>
<link type="text/css" rel="stylesheet" href="/edison-default-2016-portlet/css/mypage.css" media="screen"/>

<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<%
	Map prefsMap = portletPreferences.getMap();

	Set es = prefsMap.entrySet();
	Iterator entries = null;
	if(es != null){
		entries = es.iterator();
	}
	
	int j=1;
%>


<aui:form action="<%= configurationURL %>" method="post" name="configForm">
<input name="<portlet:namespace/>mypageTab" id="<portlet:namespace/>mypageTab"  type="hidden" />

<div class="sideBySide">
	<div class="dialogSelectGroupLeft">
		<h4>
			<liferay-ui:message key='edison-content-config-select-group'/>
			<input type="button" value="초기화" onclick="initialMyPageSelectedTab()"/>
		</h4>
			<ul class="target connected">
				<c:forEach var="select" items="${selectedTab }" varStatus="status" >
					<li value="${status.index}">${select}</li>	
				</c:forEach>
			</ul>
	</div>
	<div class="dialogAllgroupRight">
		<h4><liferay-ui:message key='edison-content-config-all-group'/></h4>
		<ul class="source connected">
			<c:forEach var="myPageTab" items="${tabArray }" varStatus="status">
				<li value="${myPageTab}">${myPageTab}</li>	
			</c:forEach>
		</ul>
	</div>
</div>

<div>
	<input type="button" value="<liferay-ui:message key='edison-button-save'/>"  onclick="<portlet:namespace/>doSubmit()"/>
</div>
</aui:form>

<script type="text/javascript">
var items = [];
$(function () {
			
	$("ul.target").children().each(function() {
		items.push($(this).prop("textContent"));
	});
	
	$(".source li").draggable({
		addClasses: false,
		appendTo: "body",
		helper: "clone"
	});
	
	$(".target").droppable({
		addClasses: false,
		activeClass: "listActive",
		accept: ":not(.ui-sortable-helper)",
		drop: function(event, ui) {
			check = updateValues("add", ui.draggable.text());
			if(check){
				$(this).find(".placeholder").remove();
// 				var link = $("<a href='#' class='dismiss'>x</a>");
				var list = $("<li></li>").attr("value",ui.draggable.val()).text(ui.draggable.text());
// 				$(list).append(link);
				$(list).appendTo(this);
			}
		}
	}).on("click", ".dismiss", function(event) {
		check = updateValues("delete", $(this).parent().prop("textContent"));
		if(check){
			event.preventDefault();
			$(this).parent().remove();
		}
	}).sortable({
		items: "li:not(.placeholder)",
		sort: function() {
			$(this).removeClass("listActive");
		},
		update: function() {
			updateValues("sort");
		}
	});
	
});

function initialMyPageSelectedTab(){
	items = [];
	
	$("ul.target").children().each(function() {
		$(this).remove();
	});
}
function updateValues(mode, targetValue) {
	var index = items.indexOf(targetValue);
	var checkExist = true;
	if(mode == "add"){
		if(index == -1){
			items.push(targetValue);
		}else{
			alert(Liferay.Language.get('edison-content-config-register-groupid-alret'));
			checkExist = false;
		}
	}else if(mode == "delete"){
		if(index != -1){
			items.splice(index, 1);
		}
	}else if(mode == "sort"){
		items = [];
		
		$("ul.target").children().each(function() {
			items.push($(this).prop("textContent"));
		});
	}

	return checkExist;
};

function <portlet:namespace/>doSubmit(){
	if(items != null && items.length > 0){
		$("#<portlet:namespace/>mypageTab").val(items);
	}
	
	if(items.length == 0){
		alert(Liferay.Language.get('edison-content-config-no-register-groupid-alret'));
		return false;
	}
	
	$("form[name=<portlet:namespace/>configForm]").submit();
}
</script>