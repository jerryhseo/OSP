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
%>
<aui:form action="<%= configurationURL %>" method="post" name="configForm">
	
	<div class="table1_list borderno">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="list" >
		<colgroup>
			<col width="25%" />
			<col width="25%" />
			<col width="25%" />
			<col width="25%" />
		</colgroup>
       	<thead>
			<tr>
				<th>Number</th>
				<th>Key</th>
				<th>Value</th>
			</tr>       	
	    </thead>
		<tbody id="keySetBody">
		<%
			String key = "";
			String value = "";
			int tabViewCount = 0;
		 	while (entries.hasNext()) {

				Map.Entry<String, String[]> thisEntry = (Map.Entry) entries.next();
				key = CustomUtil.strNull(thisEntry.getKey());
				value = CustomUtil.strNull(thisEntry.getValue()[0]);
				
				if(key.equals("siteYn")){
					tabViewCount++;
					String selectedY = value.equals("Y") ? "selected" : "";
					String selectedN = value.equals("N") ? "selected" : "";
					out.print("<tr id=\"_"+PortalUtil.getPortletId(request)+"_tr_"+j+"\">\n");
					out.print("	<td>"+j+"</td>\n");
					out.print("	<td>SiteYn<input type=\"hidden\" id=\"_"+PortalUtil.getPortletId(request)+"_keyTextBox\" name=\"_"+PortalUtil.getPortletId(request)+"_keyTextBox\" value=\"siteYn\" size=\"20\"></td>\n");
					out.print("	<td><select id=\"_"+PortalUtil.getPortletId(request)+"_valueTextBox\" name=\"_"+PortalUtil.getPortletId(request)+"_valueTextBox\"><option value=\"Y\" "+selectedY+" >Y</option><option value=\"N\" "+selectedN+" >N</option></select>\n");
					out.print("</tr>\n");
				}
				j=j+1;
			}
		 	if(tabViewCount == 0){
		 		out.print("<tr id=\"_"+PortalUtil.getPortletId(request)+"_tr_1\">\n");
				out.print("	<td>1</td>\n");
				out.print("	<td>SiteYn<input type=\"hidden\" id=\"_"+PortalUtil.getPortletId(request)+"_keyTextBox\" name=\"_"+PortalUtil.getPortletId(request)+"_keyTextBox\" value=\"siteYn\" size=\"20\"></td>\n");
				out.print("	<td><select id=\"_"+PortalUtil.getPortletId(request)+"_valueTextBox\" name=\"_"+PortalUtil.getPortletId(request)+"_valueTextBox\" ><option value=\"Y\">Y</option><option value=\"N\" selected=\"selected\" >N</option></select>\n");
				out.print("</tr>\n");
		 	}
		%>
		</tbody>
		</table>
	</div>
<h1 class="h40"></h1>
<div>
	<input type="submit" value="<liferay-ui:message key='edison-button-save'/>"  />
</div>
</aui:form>
