<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

 <%
	Map prefsMap = portletPreferences.getMap();

	Set es = prefsMap.entrySet();
	Iterator entries = null;
	if(es != null){
		entries = es.iterator();
	}
	
	int j=1;
	
	String key = "";
	String value = "";
	String bcUse = "";
	while (entries.hasNext()) {
		Map.Entry<String, String[]> thisEntry = (Map.Entry) entries.next();
		key = CustomUtil.strNull(thisEntry.getKey());
		value = CustomUtil.strNull(thisEntry.getValue()[0]);
		
		if(key.equals("bcUse")){
			bcUse=value;
		}
	}
%> 
<aui:form action="<%= configurationURL %>" method="post" name="configForm">
	<h5>Boundary Condition Use</h5> 
	<aui:select name="bcUse" label="">
		<option value="true" <%if(bcUse.equals("true")){out.print("selected");}%>>사용</option>
		<option value="false"<%if(bcUse.equals("false")){out.print("selected");}%>>미사용</option>
	</aui:select>
	
	<input type="button" value="<liferay-ui:message key='edison-button-save'/>"  onclick="<portlet:namespace/>doSubmit()"/>
</aui:form>

<script>
function <portlet:namespace/>doSubmit(){
	
	$("form[name=<portlet:namespace/>configForm]").submit();
	
}

</script>