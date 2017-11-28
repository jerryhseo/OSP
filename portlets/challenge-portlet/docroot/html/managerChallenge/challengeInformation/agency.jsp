<%@page import="com.liferay.portal.model.Portlet"%>
<%@page import="com.liferay.portal.model.LayoutTypePortlet"%>
<%@page import="com.liferay.portal.theme.ThemeDisplay"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayPortletURL"%>
<%@page import="com.liferay.portlet.PortletURLFactoryUtil"%>
<%@page import="com.liferay.portal.util.PortalUtil" %>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>

<%@page import="javax.portlet.PortletSession"%>
<%@page import="javax.portlet.PortletRequest"%>
<%@page import="javax.portlet.PortletMode"%>
<%@page import="javax.portlet.PortletURL"%>

<%@page import="java.util.List"%>

<%@ include file="/html/init.jsp" %>


<blockquote>
<h3><b>Agency for </b> Challenge</h3><br>
</blockquote>

<%
	long ccID = ParamUtil.getLong(request, "ccid");
	long cID = ParamUtil.getLong(request, "cid");
	
	String currentPortletID = themeDisplay.getPortletDisplay().getId();
	
%>

<!-- data of ChallengeInformation  -->
<%
	List<Agency> agencyFullList = AgencyLocalServiceUtil.getAgencyByChild(ccID);
	
	String tabValue = ParamUtil.getString(request, "tabViewofChild", "Agency");
	String includeURL = "/html/managerChallenge/challengeInformation/childChallenge.jsp" + tabValue.trim() + ".jsp";
	
%>

<liferay-portlet:renderURL varImpl="interatorAgencyURL">
	<portlet:param name="mvcPath" value="/html/managerChallenge/childChallenge.jsp"/>
	<portlet:param name="tabViewofChild" value="<%=tabValue %>" />
	<portlet:param name="cid" value="<%=String.valueOf(cID)%>" />
	<portlet:param name="ccid" value="<%=String.valueOf(ccID) %>" />
	<portlet:param name="seletTab" value="<%=tabValue %>"/>
</liferay-portlet:renderURL>

<liferay-ui:search-container iteratorURL="<%=interatorAgencyURL %>" id="agencyList" delta="5"	deltaConfigurable="true" emptyResultsMessage="There-are-no-Agency-challenge-Information">
	<liferay-ui:search-container-results		
		total="<%=agencyFullList.size()  %>"
		results="<%=ListUtil.subList(agencyFullList, searchContainer.getStart(), searchContainer.getEnd())  %>"
	/>
		<liferay-ui:search-container-row
			className="edison.challenge.service.builder.model.Agency" 
			modelVar="currentAgency" 
		>
			<liferay-ui:search-container-column-text name='<%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-agency-name") %>' value="<%=currentAgency.getName(themeDisplay.getLocale()) %>"/>
			<liferay-ui:search-container-column-text name='<%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-agency-level") %>' property="level"/>
			<liferay-ui:search-container-column-text name="URL" property="url"/>
			<liferay-ui:search-container-column-text name='<%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-delete") %>'>
				<liferay-ui:icon-menu>
					<portlet:actionURL name="deleteAgency" var="deleteAgency" windowState="normal">
						<portlet:param name="agencyID" value="<%=String.valueOf(currentAgency.getPrimaryKey())%>" />
						<portlet:param name="ccid" value="<%=String.valueOf(ccID)%>" />
						<portlet:param name="redirect" value="<%=PortalUtil.getCurrentURL(renderRequest) %>" />
					</portlet:actionURL>
					<liferay-ui:icon-delete image="delete" message='<%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-delete") %>' url="<%=deleteAgency.toString() %>" />
				</liferay-ui:icon-menu>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>
		
		<liferay-ui:search-iterator/>
</liferay-ui:search-container>


		<portlet:renderURL var="createAgency" windowState="<%=LiferayWindowState.POP_UP.toString() %>">
			<portlet:param name="mvcPath" value="/html/managerChallenge/challengeInformation/agencyForm.jsp" />
			<portlet:param name="cid" value="<%=String.valueOf(cID) %>" />
			<portlet:param name="ccid" value="<%=String.valueOf(ccID) %>" />
			<portlet:param name="redirect" value="<%=PortalUtil.getCurrentURL(renderRequest) %>" />
		</portlet:renderURL>
		
		<portlet:renderURL var="refreshAgency" windowState="normal">
			<portlet:param name="mvcPath" value="/html/managerChallenge/challengeInformation/agencyForm.jsp" />
			<portlet:param name="cid" value="<%=String.valueOf(cID) %>" />
			<portlet:param name="ccid" value="<%=String.valueOf(ccID) %>" />
		</portlet:renderURL>
		
		<button class="btn btn-primary" id="<portlet:namespace/>createAgencyButton"><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-create") %></button>

		<script>
		//Add a portlet to a dialog box using redirection to a jsp file
		$('#<portlet:namespace/>createAgencyButton').click(function(){
			window.<portlet:namespace/>openDialogWithPortletUsingJsp();
		});
		
		Liferay.provide(
			window,
			'<portlet:namespace/>openDialogWithPortletUsingJsp',
			function(){
				// Select a portlet to be displayed on dialog box, randomly
				var jspUrl =  '<%=createAgency.toString()%>';
			  	console.log('JSP URL:' + jspUrl );

				Liferay.Util.openWindow({
					dialog: {
						headerContent: '<h3>Agency Input</h3>',
						cache: false,
			          	destroyOnClose: true,
						centered: true,
						modal: true,
						resizable: false,
						width:770, 
						height:370
					},
					id: '<portlet:namespace/>agencyDialog',
		            title: '<liferay-ui:message key="Agency Create" />',
					uri:jspUrl
				});
			},
			['liferay-util-window','aui-dialog-iframe-deprecated','aui-io-plugin-deprecated','liferay-portlet-url','aui-node']
		);
		
		Liferay.provide(
				window,
				'<portlet:namespace/>closePopupwithAgency',
				function(popupIdToClose){
					var popupDialog = Liferay.Util.Window.getById(popupIdToClose);
					popupDialog.destroy();
					location.reload();
				},
				['liferay-util-window']
		);
	</script>

