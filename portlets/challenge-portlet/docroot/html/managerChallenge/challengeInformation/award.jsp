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
<h3><b>Award for </b> Challenge</h3><br>
</blockquote>

<%
	long ccID = ParamUtil.getLong(request, "ccid");
	long cID = ParamUtil.getLong(request, "cid");
	
	String currentPortletID = themeDisplay.getPortletDisplay().getId();
	
%>

<!-- data of ChallengeInformation  -->
<%
	List<Award> awardFullList = AwardLocalServiceUtil.getAwardByChildCollet(ccID);
	
	String tabValue = ParamUtil.getString(request, "tabViewofChild", "Award");
	String includeURL = "/html/managerChallenge/challengeInformation/childChallenge.jsp" + tabValue.trim() + ".jsp";
	
%>

<liferay-portlet:renderURL varImpl="interatorAwardURL">
	<portlet:param name="mvcPath" value="/html/managerChallenge/childChallenge.jsp"/>
	<portlet:param name="tabViewofChild" value="<%=tabValue %>" />
	<portlet:param name="cid" value="<%=String.valueOf(cID)%>" />
	<portlet:param name="ccid" value="<%=String.valueOf(ccID) %>" />
	<portlet:param name="seletTab" value="<%=tabValue %>"/>
</liferay-portlet:renderURL>

<liferay-ui:search-container iteratorURL="<%=interatorAwardURL %>" id="awardList" delta="5"	deltaConfigurable="true" emptyResultsMessage="There-are-no-Agency-challenge-Information">
	<liferay-ui:search-container-results		
		total="<%=awardFullList.size()  %>"
		results="<%=ListUtil.subList(awardFullList, searchContainer.getStart(), searchContainer.getEnd())  %>"
	/>
		<liferay-ui:search-container-row
			className="edison.challenge.service.builder.model.Award" 
			modelVar="currentAward" 
		>
			<liferay-ui:search-container-column-text name='<%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-award-gradename") %>' value="<%=currentAward.getAwardGradeName(themeDisplay.getLocale()) %>"/>
			<liferay-ui:search-container-column-text name='<%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-award-name") %>' value="<%=currentAward.getAwardName(themeDisplay.getLocale()) %>"/>
			<liferay-ui:search-container-column-text name='<%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-award-prize") %>' value="<%=currentAward.getPrize(themeDisplay.getLocale()) %>"/>
			<liferay-ui:search-container-column-text name='<%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-award-number") %>' property="number"/>
			<liferay-ui:search-container-column-text name="Delete">
				<liferay-ui:icon-menu>
					<portlet:actionURL name="deleteAward" var="deleteAward" windowState="normal">
						<portlet:param name="awardID" value="<%=String.valueOf(currentAward.getPrimaryKey())%>" />
						<portlet:param name="ccid" value="<%=String.valueOf(ccID)%>" />
						<portlet:param name="redirect" value="<%=PortalUtil.getCurrentURL(renderRequest) %>" />
					</portlet:actionURL>
					<liferay-ui:icon-delete image="delete" message='<%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-delete") %>' url="<%=deleteAward.toString() %>" />
				</liferay-ui:icon-menu>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>
		
		<liferay-ui:search-iterator/>
</liferay-ui:search-container>


		<portlet:renderURL var="createAward" windowState="<%=LiferayWindowState.POP_UP.toString() %>">
			<portlet:param name="mvcPath" value="/html/managerChallenge/challengeInformation/awardForm.jsp" />
			<portlet:param name="cid" value="<%=String.valueOf(cID) %>" />
			<portlet:param name="ccid" value="<%=String.valueOf(ccID) %>" />
			<portlet:param name="redirect" value="<%=PortalUtil.getCurrentURL(renderRequest) %>" />
		</portlet:renderURL>
		
		<portlet:renderURL var="refreshAward" windowState="normal">
			<portlet:param name="mvcPath" value="/html/managerChallenge/challengeInformation/awardForm.jsp" />
			<portlet:param name="cid" value="<%=String.valueOf(cID) %>" />
			<portlet:param name="ccid" value="<%=String.valueOf(ccID) %>" />
		</portlet:renderURL>
		
		<button class="btn btn-primary" id="<portlet:namespace/>createAwardButton">Create</button>

		<script>
		//Add a portlet to a dialog box using redirection to a jsp file
		$('#<portlet:namespace/>createAwardButton').click(function(){
			window.<portlet:namespace/>openDialogWithPortletUsingJspAward();
		});
		
		Liferay.provide(
			window,
			'<portlet:namespace/>openDialogWithPortletUsingJspAward',
			function(){
				// Select a portlet to be displayed on dialog box, randomly
				var jspUrl =  '<%=createAward.toString()%>';
			  	console.log('JSP URL:' + jspUrl );

				Liferay.Util.openWindow({
					dialog: {
						headerContent: '<h3>Award Input</h3>',
						cache: false,
			          	destroyOnClose: true,
						centered: true,
						modal: true,
						resizable: false,
						width:770, 
						height:570
					},
					id: '<portlet:namespace/>awardDialog',
		            title: '<liferay-ui:message key="Award Create" />',
					uri:jspUrl
				});
			},
			['liferay-util-window','aui-dialog-iframe-deprecated','aui-io-plugin-deprecated','liferay-portlet-url','aui-node']
		);
		
		Liferay.provide(
				window,
				'<portlet:namespace/>closePopupwithAward',
				function(popupIdToClose){
					var popupDialog = Liferay.Util.Window.getById(popupIdToClose);
					popupDialog.destroy(); 
					location.reload();
					var url = PortalUtil.getCurrentURL(renderRequest);
					
					$('#awardList').load('url #awardList');
				},
				['liferay-util-window']
		);		
	</script>

