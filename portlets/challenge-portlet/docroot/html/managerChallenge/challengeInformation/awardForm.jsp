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

<script src="https://code.jquery.com/jquery-2.2.3.min.js" ></script>
<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js" ></script>
<link type="text/css" href="https://code.jquery.com/ui/1.11.4/themes/south-street/jquery-ui.css" rel="stylesheet" />


<%@ include file="/html/init.jsp" %>


<% 
String challengeID = ParamUtil.getString(request, "cid");
String childChallengeID = ParamUtil.getString(request, "ccid");
%>



<div class="container">
	<portlet:actionURL name="createAward" var="createAward" windowState="<%=LiferayWindowState.POP_UP.toString() %>">
		<portlet:param name="mvcPath" value="/html/managerChallenge/childChallenge.jsp" />
		<portlet:param name="cid" value="<%=challengeID %>" />
		<portlet:param name="ccid" value="<%=childChallengeID %>" />
		<portlet:param name="redirect" value="<%=PortalUtil.getCurrentURL(renderRequest) %>" />
	</portlet:actionURL>
	<form class="form-horizontal" id="<portlet:namespace/>awardForm" action="<%=createAward%>" method="POST">
		<!-- Award grade Name -->
		<div class="control-group">
			<label class="control-label" for="inputGradeName"><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-award-gradename") %></label>
			<div class="controls">
				<liferay-ui:input-localized id="inputGradeName" name="inputGradeName" xml=""></liferay-ui:input-localized>
			</div>
		</div>
		
		<!-- Award Name -->
		<div class="control-group">
			<label class="control-label" for="inputAwardName"><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-award-name") %></label>
			<div class="controls">
				<liferay-ui:input-localized id="inputAwardName" name="inputAwardName" xml=""></liferay-ui:input-localized>
			</div>
		</div>
		
		<!-- Prize -->
		<div class="control-group">
			<label class="control-label" for="inputPrize"><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-award-prize") %></label>
			<div class="controls">
				<liferay-ui:input-localized id="inputPrize" name="inputPrize" xml=""></liferay-ui:input-localized>
			</div>
		</div>

		<!-- Number of Team -->
		<div class="control-group">
			<label class="control-label" for="inputAwardNumber"><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-award-number") %></label>
			<div class="controls">
				<select id="inputAwardNumber"  name="<portlet:namespace/>inputAwardNumber">
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
				</select>
			</div>
		</div>
		
		<div class="control-group">
			<div class="controls">
				<button class="btn btn-primary" id="<portlet:namespace/>submitAward" type="button"><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-create") %></button>
				<script>
					$('#<portlet:namespace/>submitAward').click(function(){
						AUI().use(
								'aui-io-request',
								function(A) {
									A.io.request(
											'<%=createAward.toString()%>',
										{
											method : 'POST',
											form : {id:'<portlet:namespace/>awardForm'},
											on : {
												
												success : function() {
													Liferay.Util.getOpener().<portlet:namespace/>closePopupwithAward('<portlet:namespace/>awardDialog');
												}
											}
										}
									);
							}
						);
					});
				</script>
				<button class="btn btn-primary" id="<portlet:namespace/>cancelAward" type="button"><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-cancel") %></button>
				
				<script>
					$('#<portlet:namespace/>cancelAward').click(function(){
						Liferay.Util.getOpener().<portlet:namespace/>closePopupwithAward('<portlet:namespace/>awardDialog');
					});
				</script>
				
			</div>
		</div>
	</form>
</div>