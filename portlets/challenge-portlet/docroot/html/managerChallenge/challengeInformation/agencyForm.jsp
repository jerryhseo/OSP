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


<%@ include file="/html/init.jsp" %>

<% 
String challengeID = ParamUtil.getString(request, "cid");
String childChallengeID = ParamUtil.getString(request, "ccid");


%>



<div class="container">
	<portlet:actionURL name="createAgency" var="createAgency" windowState="<%=LiferayWindowState.POP_UP.toString() %>">
		<portlet:param name="mvcPath" value="/html/managerChallenge/childChallenge.jsp" />
		<portlet:param name="cid" value="<%=challengeID %>" />
		<portlet:param name="ccid" value="<%=childChallengeID %>" />
		<portlet:param name="redirect" value="<%=PortalUtil.getCurrentURL(renderRequest) %>" />
	</portlet:actionURL>
	<form class="form-horizontal" id="<portlet:namespace/>agencyForm" action="<%=createAgency%>" method="POST">
		<!-- Agency Name -->
		<div class="control-group">
			<label class="control-label" for="inputName"><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-agency-name") %></label>
			<div class="controls">
				<liferay-ui:input-localized id="inputName" name="inputName" xml=""></liferay-ui:input-localized>
			</div>
		</div>

		<!-- Agency Level -->
		<div class="control-group">
			<label class="control-label" for="inputAgencyLevel"><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-agency-level") %></label>
			<div class="controls">
				<select id="<portlet:namespace/>inputAgencyLevel"  name="<portlet:namespace/>inputAgencyLevel">
					<option value="Host"><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-agency-level-host") %></option>
					<option value="Conduct"><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-agency-level-conduct") %></option>
					<option value="Support"><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-agency-level-support") %></option>
				</select>
			</div>
		</div>

		<!-- Agency URL -->
		<div class="control-group">
			<label class="control-label" for="inputURL">URL</label>
			<div class="controls">
				<input type="text" name="<portlet:namespace/>inputURL"></input>
			</div>
		</div>
		
		<div class="control-group">
			<div class="controls">
				<button class="btn btn-primary" id="<portlet:namespace/>submitAgency" type="button"><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-create") %></button>
				<script>
					$('#<portlet:namespace/>submitAgency').click(function(){
						AUI().use(
								'aui-io-request',
								function(A) {
									A.io.request(
											'<%=createAgency.toString()%>',
										{
											method : 'POST',
											form : {id:'<portlet:namespace/>agencyForm'},
											on : {
												
												success : function() {
													Liferay.Util.getOpener().<portlet:namespace/>closePopupwithAgency('<portlet:namespace/>agencyDialog');
												}
											}
										}
									);
							}
						);
					});
				</script>
				<button class="btn btn-primary" id="<portlet:namespace/>cancelAgency" type="button"><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-cancel") %></button>
				
				<script>
					$('#<portlet:namespace/>cancelAgency').click(function(){
						Liferay.Util.getOpener().<portlet:namespace/>closePopupwithAgency('<portlet:namespace/>agencyDialog');
					});
				</script>
				
			</div>
		</div>
	</form>
</div>