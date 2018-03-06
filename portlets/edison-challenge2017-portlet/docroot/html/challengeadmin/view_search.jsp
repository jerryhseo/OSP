<%@include file="/html/init.jsp"%>

<%
    String keywords = ParamUtil.getString(request, "keywords");
%>

<liferay-portlet:renderURL varImpl="searchURL">
	<portlet:param name="mvcPath" value="/html/challengeadmin/view_search.jsp" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL varImpl="viewURL">
	<portlet:param name="mvcPath" value="/html/challengeadmin/view.jsp" />
</liferay-portlet:renderURL>

<aui:form action="<%= searchURL %>" method="get" name="fm">
    <liferay-portlet:renderURLParams varImpl="searchURL" />

    <liferay-ui:header
        backURL="<%= viewURL.toString() %>"
        title="search"
    />
    
    <div class="search-form">
		<span class="aui-search-bar">
		    <aui:input inlineField="<%= true %>" label="" name="keywords" size="30" title="search-entries" type="text" />
		
		    <aui:button type="submit" value="search" />
		</span>
    </div>
</aui:form>

<%
	SearchContext searchContext = SearchContextFactory.getInstance(request);

	searchContext.setKeywords(keywords);
	searchContext.setAttribute("paginationType", "more");
	searchContext.setStart(0);
	searchContext.setEnd(10);
	
	Indexer indexer = IndexerRegistryUtil.getIndexer(ChildChallenge.class);

	Hits hits = indexer.search(searchContext);
	
	List<ChildChallenge> childChallenges = new ArrayList<ChildChallenge>();
	
	
	for (int i = 0; i < hits.getDocs().length; i++) {
		Document doc = hits.doc(i);

		long childChallengeId = GetterUtil.getLong(doc.get(Field.ENTRY_CLASS_PK));

		ChildChallenge childChallenge = null;
		
		try {
			childChallenge = ChildChallengeLocalServiceUtil.getChildChallenge(childChallengeId);
		} catch (PortalException pe) {
			_log.error(pe.getLocalizedMessage());
		} catch (SystemException se) {
			_log.error(se.getLocalizedMessage());
		}
		
		if(childChallenge != null)
			childChallenges.add(childChallenge);
	}
	
	//System.out.println("\n\n\n\n\n\ntest search");
	//System.out.println(childChallenges.toString());
	
	List<Challenge> challenges = ChallengeLocalServiceUtil.getChallenges(scopeGroupId, WorkflowConstants.STATUS_APPROVED);
	
	Map<String, String> challengeMap = new HashMap<String, String>();
	
	for (Challenge challenge : challenges) {
		challengeMap.put( Long.toString(challenge.getChallengeId()), challenge.getField(themeDisplay.getLocale()) );
	}
	
	//System.out.println(challengeMap.toString());
%>


<liferay-ui:search-container delta="10" emptyResultsMessage="no-childchallenge-were-found">
	<liferay-ui:search-container-results
		results="<%= childChallenges %>"
		total="<%= childChallenges.size() %>"
	/>

	<liferay-ui:search-container-row
		className="kisti.edison.challenge.model.ChildChallenge" 
		keyProperty="childChallengeId" modelVar="childChallenge"
		escapedModel="<%=true%>">
		
		<liferay-ui:search-container-column-text name="Challenge"
			value="<%=challengeMap.get(Long.toString(childChallenge.getChallengeId()))%>" />
		
		<liferay-ui:search-container-column-text property="number" />

		<liferay-ui:search-container-column-text property="challengeEndDay" />

		<liferay-ui:search-container-column-jsp
			path="/html/challengeadmin/childchallenge_actions.jsp" align="right" />
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>

<%
	if (Validator.isNotNull(keywords)) {
		String currentURL = PortalUtil.getCurrentURL(request);
		PortalUtil.addPortletBreadcrumbEntry(request, 
				LanguageUtil.get(pageContext, "search") + ": " + keywords, currentURL);
	}
%>

<%!
	private static Log _log = LogFactoryUtil.getLog("docroot.html.challenge.view_search_jsp");
%>
